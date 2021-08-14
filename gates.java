import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.Serializable;

abstract class gates implements Serializable
{
    JPanel face;
    protected boolean estado=false;
    Point natDrag;
    Image img;
    outputPin p3;
    ArrayList<inputPin> child=new ArrayList();
    ArrayList<outputPin> parent=new ArrayList();
    Color light=new Color(45,56,82);
    ArrayList<Pin> pins=new ArrayList<Pin>();
    public gates(Point location)
    {
        face=new tete(this);
        gates este=this;
        face.setLocation(location);
        face.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                natDrag=new Point(me.getX(),me.getY());
            }

        });
        face.addMouseMotionListener(new MouseAdapter()
        {
            public void mouseDragged(MouseEvent me)
            {
                face.setLocation(face.getX()+me.getX()-natDrag.x,face.getY()+me.getY()-natDrag.y);
                gui.pan.repaint();
                gui.connector=null;
                //gui.pan.paintImmediately(face.getLocation().x-20,face.getLocation().y-20,face.getWidth()+40,face.getHeight()+40);
            }
        });
    }
    @Override
    public String toString()
    {
        return this.getClass().getName();
    }
        class tete extends JPanel
    {
        public tete(gates thisgate)
        {
            super();
            this.setLayout(null);
            JPopupMenu opt=new JPopupMenu();
            JMenuItem del=new JMenuItem("Remove this gate");
            tete este=this;
            del.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    gui.comps.remove(thisgate);
                    gui.pan.remove(este);
                    
                    for(inputPin p:child)//"pins" automatically refers to that gate object's pins of which this "tete" is a part
                    {
                        p.remove(getOutputPin());
                    }
                    for(outputPin p:parent)
                    {
                        p.remove(thisgate);
                        
                    }
                    gui.pan.repaint();
                }
            });
            opt.add(del);
            addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent me)
                {
                    if(SwingUtilities.isRightMouseButton(me))
                    {
                        opt.show(este,me.getX(),me.getY());
                    }
                }
            });
        }
        public void paintComponent(Graphics gg)
        {
            super.paintComponent(gg);
            Graphics2D g=(Graphics2D)gg;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(new Color(253, 235, 208,180));
            g.fill(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),10,10));
            g.setColor(Color.black);
            g.drawString(getShowName(),30,18);
            Color c=estado?Color.green:Color.red;
            g.setColor(c);
            //g.drawString("Parents: "+parent,30,50);
            //g.drawString("Children: "+child,30,80);
            g.fill(new RoundRectangle2D.Double(5,5,20,20,10,10));
        }
    }
    public boolean getState()
    {
        return estado;
    }
    abstract public void setState(boolean a,int b);
    abstract public boolean minterm();
    abstract public String getShowName();
    abstract public outputPin getOutputPin();
    
}
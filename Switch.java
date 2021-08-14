import java.util.*;
import java.awt.*;
import java.awt.event.*;
class Switch extends gates
{
    public Switch(Point p)
    {
        super(p);
        face.setLocation(p);
        face.setSize(200,60);
        
         p3=new outputPin(this,1,new Point(180,25));
        pins.add(p3);
        face.add(p3);
        pins.add(p3);
        face.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                setState(!estado,0);
                //System.out.println(state);
            }
        });
    }
    public outputPin getOutputPin()
    {
        return p3;
    }
    public boolean minterm()
    {
        return estado;
    }
    public void setState(boolean b,int pin)
    {
        estado=b;
        gui.pan.repaint();
        try
        {
            Thread.sleep(15);
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
        for(Pin p:child)
        {
            
                new Thread(new Runnable()
                {
                    public void run()
                    {
                        p.setState(estado);
                    }
                }).start();
        }
        gui.pan.repaint();
    }
    public String getShowName()
    {
        return "SWITCH(input)";
    }
}
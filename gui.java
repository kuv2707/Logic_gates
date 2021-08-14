import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.UIManager.*;
import java.io.*;

class gui implements Runnable,MouseListener,MouseMotionListener
{
    static JFrame frame;
    static JPanel pan;
    static ArrayList<gates> comps=new ArrayList<>();
    static ArrayList<gates> backup=new ArrayList<>();
    static Pin connector=null;
    static launchPad p;
    public static void main()
    {
        SwingUtilities.invokeLater(new gui());
    }
    public void run()
    {
        try
        {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch(Exception e){}
        frame=new JFrame("Logic circuit visualizer");
        frame.setSize(800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
        JMenuBar mb=new JMenuBar();
        JMenu m1=new JMenu("File");
        JMenuItem open=new JMenuItem("Open file");
        m1.add(open);
        JMenuItem save=new JMenuItem("Save file");
        m1.add(save);
        save.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser jf=new JFileChooser();
                if(jf.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
                {
                    File f=jf.getSelectedFile();
                    try
                    {
                        FileOutputStream fos=new FileOutputStream(f);
                        ObjectOutputStream oos=new ObjectOutputStream(fos);
                        oos.writeObject(gui.comps);
                    }
                    catch (Exception fnfe)
                    {
                        fnfe.printStackTrace();
                    }
                    
                }
            }
        });
        open.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser jf=new JFileChooser();
                if(jf.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
                {
                    File f=jf.getSelectedFile();
                    try
                    {
                        FileInputStream fos=new FileInputStream(f);
                        ObjectInputStream oos=new ObjectInputStream(fos);
                        gui.comps=(ArrayList<gates>)oos.readObject();
                        for(gates ggg:gui.comps)
                        gui.pan.add(ggg.face);
                        gui.frame.repaint();
                    }
                    catch (Exception fnfe)
                    {
                        fnfe.printStackTrace();
                    }
                    
                }
            }
        });
        JMenu m2=new JMenu("Options");
        JMenuItem cls=new JMenuItem("Clear screen");
        m2.add(cls);
        JMenuItem undo=new JMenuItem("Undo last clear screen");
        undo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                for(gates g:backup)
                {
                    comps.add(g);
                    pan.add(g.face);
                }
                m2.remove(undo);
                backup.clear();
                frame.repaint();
            }
        });
        cls.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                backup.clear();
                for(gates g:comps)
                {
                    pan.remove(g.face);
                    backup.add(g);
                }
                m2.add(undo);
                comps.clear();
                frame.repaint();
            }
        });
        mb.add(m1);
        mb.add(m2);
        frame.setJMenuBar(mb);
        pan=new canvas();
        p=new launchPad();
        pan.add(p);
        frame.add(new JScrollPane(pan,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
        frame.addComponentListener(new ComponentAdapter()
        {
            public void componentResized(ComponentEvent event)
            {
                p.setLocation(new Point(0,frame.getHeight()-150));
                p.setSize(frame.getWidth(),100);
            }
        });
        frame.setVisible(true);
    }
    static class canvas extends JPanel
    {
        public canvas()
        {
            super();
            setLayout(null);
            setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));
            for(int i=0;i<comps.size();i++)
            {
                this.add(comps.get(i).face);
            }
        }
        public void paintComponent(Graphics gg)
        {
            super.paintComponent(gg);
            Graphics2D g=(Graphics2D)gg;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g.setStroke(new BasicStroke(3));
            for(gates ga:comps)
            {
                outputPin out=ga.getOutputPin();
                for(inputPin chi:ga.child)
                {
                    Point start=ga.face.getLocation();
                    start.x+=out.getLocation().x;
                    start.y+=out.getLocation().y;
                    Point end=chi.getHolder().face.getLocation();
                    end.x+=chi.getLocation().x;
                    end.y+=chi.getLocation().y;
                    g.drawLine(start.x,start.y,end.x,end.y);
                    //g.drawString(out.toString(),start.x,start.y);
                    //g.drawString(chi.toString(),end.x,end.y);
                    //g.drawString("Main gate of this wire: "+ga,(start.x+end.x)/2,(start.y+end.y)/2);
                }
            }
        }
    }
    public void mouseEntered(MouseEvent me)
    {
        
        
    }
    public void mouseExited(MouseEvent me)
    {
        
    }
    public void mousePressed(MouseEvent me)
    {
        
    }
    public void mouseReleased(MouseEvent me)
    {
        
    }
    public void mouseClicked(MouseEvent me)
    {
        
    }
    public void mouseMoved(MouseEvent me)
    {
        
    }
    public void mouseDragged(MouseEvent me)
    {
        
    }
    static class launchPad extends JPanel
    {
        public launchPad()
        {
            setSize(frame.getWidth(),100);
            setLocation(new Point(0,frame.getHeight()-150));
            this.setLayout(new GridLayout(1,3));
            JButton andadd=new JButton("Add AND gate");
            andadd.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent me)
                {
                    and m=new and(new Point(500,100));
                    pan.add(m.face);
                    comps.add(m);
                    pan.repaint();
                }
            });
            
            this.add(andadd);
            
            
            JButton oradd=new JButton("Add OR gate");
            oradd.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent me)
                {
                    or m=new or(new Point(500,100));
                    pan.add(m.face);
                    comps.add(m);
                    pan.repaint();
                }
            });
            
            this.add(oradd);
            
            JButton notadd=new JButton("Add NOT gate");
            notadd.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent me)
                {
                    not m=new not(new Point(500,100));
                    pan.add(m.face);
                    comps.add(m);
                    pan.repaint();
                }
            });
            
            this.add(notadd);
            
            JButton switchadd=new JButton("Add SWITCH");
            switchadd.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent me)
                {
                    Switch m=new Switch(new Point(500,100));
                    pan.add(m.face);
                    comps.add(m);
                    pan.repaint();
                }
            });
            
            this.add(switchadd);
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
        }
    }
}
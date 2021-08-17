import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.*;

import java.io.*;

class gui extends JPanel implements Runnable,MouseListener,MouseMotionListener
{
     JFrame frame;
     JPanel pan;
     ArrayList<gates> comps = new ArrayList<>();
     ArrayList<gates> backup = new ArrayList<>();
     Pin connector = null;
     launchPad p;
     infoPanel info;
    public void setConnector(Pin p)
    {
        connector=p;
    }
    public void run() {
        
        frame = new JFrame("Logic circuit visualizer");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setBackground(new Color(163, 228, 215));
        frame.setJMenuBar(new guiMenu());
        pan = new canvas();
        p = new launchPad(frame,this);
        pan.add(p);
        //frame.add(new JScrollPane(pan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
        frame.add(pan);
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent event) {
                p.setLocation(new Point(0, frame.getHeight() - 200));
                p.setSize(frame.getWidth() - 60, 100);
            }
        });
        info = new infoPanel();
        pan.add(info);
        frame.setVisible(true);
    }

    class canvas extends JPanel {
        public canvas() {
            super();
            setLayout(null);
            setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
            for (int i = 0; i < comps.size(); i++) {
                this.add(comps.get(i));
            }
        }

        public void paintComponent(Graphics gg) {
            super.paintComponent(gg);
            Graphics2D g = (Graphics2D) gg;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setStroke(new BasicStroke(3));
            for (gates ga : comps) {
                outputPin out = ga.getOutputPin();
                for (inputPin chi : ga.child) {
                    Point start = ga.getLocation();
                    start.x += out.getLocation().x;
                    start.y += out.getLocation().y;
                    Point end = chi.getHolder().getLocation();
                    end.x += chi.getLocation().x;
                    end.y += chi.getLocation().y;
                    g.drawLine(start.x, start.y, end.x, end.y);
                    //g.drawString(out.toString(),start.x,start.y);
                    //g.drawString(chi.toString(),end.x,end.y);
                    //g.drawString("Main gate of this wire: "+ga,(start.x+end.x)/2,(start.y+end.y)/2);
                }
            }
        }
    }
    class guiMenu extends JMenuBar
    {
        public guiMenu()
        {
            super();
            JMenuBar mb = this;
        JMenu m1 = new JMenu("File");
        JMenuItem open = new JMenuItem("Open file");
        m1.add(open);
        JMenuItem save = new JMenuItem("Save file");
        m1.add(save);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent edfdfs) {
                loadFile();

            }
        });
        JMenu m2 = new JMenu("Options");
        JMenuItem cls = new JMenuItem("Clear screen");
        m2.add(cls);
        JMenuItem undo = new JMenuItem("Undo last clear screen");
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (gates g : backup) {
                    comps.add(g);
                    pan.add(g);
                }
                m2.remove(undo);
                backup.clear();
                frame.repaint();
            }
        });
        cls.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backup.clear();
                for (gates g : comps) {
                    pan.remove(g);
                    backup.add(g);
                }
                m2.add(undo);
                comps.clear();
                info.showDetailsFor(null);
                frame.repaint();
            }
        });
        mb.add(m1);
        mb.add(m2);
        JMenuItem exit=new JMenuItem("Exit");
        m1.add(exit);
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                /**
                 * ask for confirmation and give option to save before closing
                 * maintain a flag to know if work is saved or not
                 */
                Dialogue d=new Dialogue("Save circuit before closing?","Save","Discard");
                if(d.SHOW())
                {
                    //saving
                    saveFile();//ERROR here
                }
                System.exit(0);

            }
        });
        }
    }
    public void mouseEntered(MouseEvent me) {


    }

    public void mouseExited(MouseEvent me) {

    }

    public void mousePressed(MouseEvent me) {

    }

    public void mouseReleased(MouseEvent me) {

    }

    public void mouseClicked(MouseEvent me) {

    }

    public void mouseMoved(MouseEvent me) {

    }

    public void mouseDragged(MouseEvent me) {

    }

    class launchPad extends JPanel {
        public launchPad(JFrame frame,gui ggg) {
            setSize(frame.getWidth() - 60, 100);
            setLocation(new Point(0, frame.getHeight() - 200));
            this.setLayout(new GridLayout(1, 3));
            JButton andadd = new JButton("Add AND gate");
            andadd.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    and m = new and(new Point(500, 100),frame,ggg);
                    pan.add(m);
                    comps.add(m);
                    pan.repaint();
                }
            });

            this.add(andadd);


            JButton oradd = new JButton("Add OR gate");
            oradd.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    or m = new or(new Point(500, 100),frame,ggg);
                    pan.add(m);
                    comps.add(m);
                    pan.repaint();
                }
            });

            this.add(oradd);

            JButton notadd = new JButton("Add NOT gate");
            notadd.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    not m = new not(new Point(500, 100),frame,ggg);
                    pan.add(m);
                    comps.add(m);
                    pan.repaint();
                }
            });

            this.add(notadd);

            JButton switchadd = new JButton("Add SWITCH");
            switchadd.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    Switch m = new Switch(new Point(500, 100),frame,ggg);
                    pan.add(m);
                    comps.add(m);
                    pan.repaint();
                }
            });

            this.add(switchadd);
        }


    }

    public void loadFile()
    {
        JFileChooser jf = new JFileChooser();
        if (jf.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = jf.getSelectedFile();
            try {
                FileInputStream fos = new FileInputStream(f);
                ObjectInputStream oos = new ObjectInputStream(fos);
                comps = (ArrayList<gates>) oos.readObject();
                System.out.println(comps);
                for (gates ggg : comps) {
                    pan.add(ggg);
                    for (Pin h : ggg.pins) {
                        try {
                            h.addListeners();
                        } catch (Exception e) {
                            System.out.println(e);
                        }

                    }
                    ggg.addListeners();
                }
                for (gates d : comps) {
                    d.stimulate();
                }

                frame.repaint();
            } catch (Exception fnfe) {
                fnfe.printStackTrace();
            }
        }
    }
    public void saveFile()
    {
        JFileChooser jf = new JFileChooser();
        if (jf.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = jf.getSelectedFile();
            try {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(comps);
            } catch (Exception fnfe) {
                fnfe.printStackTrace();
            }

        }
    }
     class infoPanel extends JPanel {
         gates subject = null;
        Font font1=new Font("Comic Sans MS",Font.PLAIN,20);
         Font font2=new Font("Arial",Font.PLAIN,16);
        FontMetrics fm1=getFontMetrics(font1);
        private infoPanel()
        {
            super();
            setLocation(0,10);
            setSize(140,160);
            new javax.swing.Timer(200,e-> repaint());
        }
        @Override
        public void paintComponent(Graphics graphics)
        {
            super.paintComponent(graphics);
            Graphics2D g=(Graphics2D)graphics;
            g.setColor(Color.cyan);
            g.fill(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),20,20));
            g.setColor(Color.black);
            if(subject!=null)
            {
                g.setFont(font1);
                g.drawString(subject.toString(),
                        (getWidth() - fm1.stringWidth(subject.toString()) )/2,
                20);
                g.setFont(font2);
                g.drawString(subject.getLocation().x+","+subject.getLocation().y,2,50);
                g.drawString(subject.getState(),2,80);
                int p=120;
                for(Pin pf:subject.child)
                {
                    g.drawString(pf.toString(),2,p);
                    p+=25;
                }
                for(Pin pf:subject.parent)
                {
                    g.drawString(pf.toString(),2,p);
                    p+=25;
                }

            }


        }

        public  void showDetailsFor(gates g) {
            subject=g;
        }
    }
}
import java.awt.image.BufferedImage;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;

abstract class gates extends JPanel  {
    protected boolean estado = false;
    JFrame holdingFrame;
    gui holdingPanel;
    Point natDrag;

    outputPin p3;
    ArrayList<inputPin> child = new ArrayList<>();
    ArrayList<outputPin> parent = new ArrayList<>();
    Color light = new Color(45, 56, 82);
    ArrayList<Pin> pins = new ArrayList<Pin>();
    BufferedImage chehra;
    Point chehraOffset = new Point(0, 0);

    public gates(Point location, JFrame frame, gui pan) {
        this.setLayout(null);
        holdingFrame = frame;
        holdingPanel = pan;
        this.setLocation(location);
        this.addListeners();
    }

    public void addListeners() {
        this.addRightClickThings();
        gates este = this;
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                natDrag = new Point(me.getX(), me.getY());
            }

            public void mouseClicked(MouseEvent me) {
                holdingPanel.info.showDetailsFor(este);
                holdingFrame.repaint();
                stimulate();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent me) {
                setLocation(getX() + me.getX() - natDrag.x, getY() + me.getY() - natDrag.y);
                holdingFrame.repaint();
                holdingPanel.setConnector(null);

            }
        });
        if (this instanceof Switch) {
            this.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    setState(!estado, 0);
                    // System.out.println(state);
                }
            });
        }
    }

    public Image getFaceImage() {
        return chehra;
    }

    public Point getFaceImageLocation() {
        return chehraOffset;
    }

    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // g.drawImage(getFaceImage(),getFaceImageLocation().x,getFaceImageLocation().y,this);
        g.setColor(new Color(253, 235, 208, 180));
        // if(this instanceof Switch)
        g.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
        g.setColor(Color.black);
        g.draw(new RoundRectangle2D.Double(0 + 5, 0 + 5, getWidth() - 10, getHeight() - 10, 10, 10));
        g.drawString(getShowName(), 20, 18);
        // g.drawString("Parents: " + parent, 30, 30);
        // g.drawString("Children: " + child, 30, 40);
        Color c = estado ? Color.green : Color.red;
        g.setColor(c);

        g.fill(new RoundRectangle2D.Double(getWidth() - 30, 10, 20, 20, 10, 10));
    }

    public void addRightClickThings() {
        JPopupMenu opt = new JPopupMenu();
        JMenuItem del = new JMenuItem("Remove this gate");
        gates este = this;
        del.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                holdingPanel.comps.remove(este);
                holdingPanel.pan.remove(este);
                for (inputPin p : child)// "pins" automatically refers to that gate object's pins of which this "tete"
                                        // is a part
                {
                    p.remove(getOutputPin());
                }
                for (outputPin p : parent) {
                    System.out.println("remove called "+p.toString());
                    p.remove(este);

                }
                holdingPanel.info.showDetailsFor(null);
                holdingFrame.repaint();
            }
        });
        opt.add(del);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (SwingUtilities.isRightMouseButton(me)) {
                    opt.show(este, me.getX(), me.getY());
                }
            }
        });
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public String getState() {
        if (estado)
            return "On";
        else
            return "Off";
    }

    abstract public void setState(boolean a, int b);

    abstract public boolean minterm();

    abstract public String getShowName();

    abstract public outputPin getOutputPin();

    /**
     * when loading a file, if the setup contains infinite loop etc,then the loop
     * does not
     * start on its own, so this method will do that
     */
    abstract public void stimulate();
}

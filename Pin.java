import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;

/**
 * EXTEND THIS CLASS INTO INPUT AND OUTPUT PIN
 * AND OUTPIN CANNOT BE A CHILD
 * OUTPUT PIN KE THORUGH USKE HOLDER KA STATE CANT BE CHANGED
 */
abstract class Pin extends JButton implements Serializable {
    gates holder;

    int no;
    Point locationOfWire;
    int weight = 200;

    public Pin(gates holder, int no, Point locatio) {
        this.holder = holder;
        this.setSize(20, 20);
        this.no = no;
        this.locationOfWire = locatio;
        this.setLocation(locatio);

        addListeners();
    }

    public void addListeners() {
        Pin este = this;
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {

                if (getHolder().holdingPanel.connector != null) {
                    if (este instanceof inputPin) {
                        getHolder().holdingPanel.connector.addChild((inputPin) este);
                        getHolder().holdingFrame.repaint();
                        getHolder().holdingPanel.connector = null;
                    }

                } else {
                    if (este instanceof outputPin)
                        getHolder().holdingPanel.connector = este;
                }

            }
        });
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                // Perform actions when the mouse wheel is scrolled
                int notches = e.getWheelRotation();
                este.weight+=notches*2.5;
                getHolder().holdingFrame.repaint();
                System.out.println("Mouse wheel scrolled " + notches + " notch(es)");
            }
        });
    }

    @Override
    public String toString() {
        return holder + " at (" + holder.getLocation().x + "," + holder.getLocation().y + ")";
    }

    abstract public void addChild(inputPin g);

    abstract public void setState(boolean b);

    public Point getWireLoc() {
        return locationOfWire;
    }

    public gates getHolder() {
        return holder;
    }

    public int getPin() {
        return no;
    }
}
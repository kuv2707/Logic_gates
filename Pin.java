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
        this.setSize(55, 20);
        this.no = no;
        this.locationOfWire = locatio;
        this.setLocation(locatio);

        addListeners();
    }
    public void addListeners() {
        Pin este = this;
        //todo put this in child classes
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {

                if (getHolder().holdingPanel.connector != null) {
                    if (este instanceof inputPin) {
                        getHolder().holdingPanel.connector.addChild((inputPin) este);
                        ((inputPin) este).addParent(getHolder().holdingPanel.connector);
                        getHolder().holdingFrame.repaint();
                        getHolder().holdingPanel.connector = null;
                    }

                } else {
                    if (este instanceof outputPin)
                        getHolder().holdingPanel.connector = (outputPin)este;
                }

            }
        });
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                // Perform actions when the mouse wheel is scrolled
                int notches = e.getWheelRotation();
                este.weight += notches * 2.5;
                getHolder().holdingFrame.repaint();
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
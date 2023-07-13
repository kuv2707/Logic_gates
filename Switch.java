
import java.awt.*;

import javax.swing.*;

class Switch extends gates {
    public Switch(Point p, JFrame frame, gui pan) {
        super(p, frame, pan);
        this.setLocation(p);
        this.setSize(200, 60);

        p3 = new outputPin(this, 1, new Point(140, 30));
        pins.add(p3);
        this.add(p3);

        JTextField name = new JTextField("SWITCH");
        name.setSize(100, 30);
        name.setLocation(10,25);
        // name.setEditable(false);
        this.add(name);

    }

    public outputPin getOutputPin() {
        return p3;
    }

    public boolean minterm() {
        return estado;
    }

    public void stimulate() {
        for (inputPin p : child) {

            new Thread(new Runnable() {
                public void run() {
                    p.setState(estado);
                }
            }).start();
        }
    }

    public void setState(boolean b, int pin) {
        estado = b;
        holdingPanel.repaint();
        try {
            Thread.sleep(60);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        // gates g=this;
        for (Pin p : child) {

            new Thread(new Runnable() {
                public void run() {
                    p.setState(estado);
                }
            }).start();
        }
        holdingPanel.repaint();
    }

    public String getShowName() {
        return "SWITCH(input)";
    }
}
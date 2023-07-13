import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;

class Output extends gates {
    public Output(Point p, JFrame frame, gui ooo)// Output can only have 1 inputpin
    {
        super(p, frame, ooo);
        estado = false;
        // chehra=Main.not;
        this.setSize(180, 60);
        inputPin p1 = new inputPin(this, 1, new Point(5, 25));
        this.add(p1);
        pins.add(p1);
        
    }

    public outputPin getOutputPin() {
        return p3;
    }

    public void setState(boolean etate, int pin) {

        estado = etate;

        holdingPanel.repaint();
        try {
            Thread.sleep(60);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        for (Pin p : child) {

            new Thread(new Runnable() {
                public void run() {
                    p.setState(estado);
                }
            }).start();
        }

        holdingFrame.repaint();
    }

    public void stimulate() {
        for (Pin p : child) {

            new Thread(new Runnable() {
                public void run() {
                    p.setState(estado);
                }
            }).start();
        }
    }

    @Deprecated
    public boolean minterm() {
        return false;
    }

    public String getShowName() {
        return "OUTPUT";
    }
}

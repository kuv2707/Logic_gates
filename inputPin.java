import java.awt.*;

class inputPin extends Pin {
    public inputPin(gates parent, int no, Point locatio) {
        super(parent, no, locatio);
        setForeground(Color.black);
    }

    public void setState(boolean b) {
        for (Pin p : getHolder().parent) {
            b = b || p.getHolder().estado;
        }
        getHolder().setState(b, no);
        this.setText("" + b);
    }

    public void remove(outputPin p) {
        getHolder().parent.remove(p);
    }

    public void addChild(inputPin g) {
        // do nothing
    }
}
import java.awt.*;

public class outputPin extends Pin {
    public outputPin(gates parent, int no, Point locatio) {
        super(parent, no, locatio);
        this.setForeground(Color.magenta);
    }

    @Override
    public void setState(boolean b) {
        // do nothing as output pin cant be made to change the state of the gate
    }

    public void remove(gates g) {
        for (Pin p : g.pins) {
            if (p instanceof inputPin) {
                getHolder().child.remove(p);
                // return;// for all inputpins p in g, p will be removed from this gate's children, but p
                       // can only be removed once,other iterations are redundant
            }
        }
    }

    public void addChild(inputPin g) {
        getHolder().child.add(g);
        g.getHolder().parent.add(this);

        g.setState(this.getHolder().estado);

    }
}

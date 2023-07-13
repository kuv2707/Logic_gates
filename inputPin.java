import java.awt.*;
import java.util.*;
class inputPin extends Pin {
    public inputPin(gates parent, int no, Point locatio) {
        super(parent, no, locatio);
        setForeground(Color.black);
    }
    ArrayList<outputPin> parentPins=new ArrayList<>();
    public void setState(boolean b) {
        
        for (outputPin p : parentPins) {
            b = b || p.getHolder().estado;
        }
        // System.out.println(b);
        getHolder().setState(b, no);
        this.setText("" + b);
    }
    void addParent(outputPin p)
    {
        parentPins.add(p);
    }
    public void remove(outputPin p) {
        getHolder().parent.remove(p);
    }

    public void addChild(inputPin g) {
        // do nothing
    }
}
import java.awt.*;
import java.util.*;
import javax.swing.*;
class Output extends gates
{
    public Output(Point location, JFrame frame, gui pan) {
        super(location, frame, pan);
        this.setSize(200,60);
        inputPin p1=new inputPin(this,1,new Point(5,25));
        this.add(p1);
        pins.add(p1);

    }

    @Override
    public void setState(boolean a, int b) {
        
        for(outputPin p:parent)
        {
            a=a||p.getHolder().estado;
        }
        estado=a;
    }

    @Override
    @Deprecated
    public boolean minterm() {
        return false;
    }

    @Override
    public String getShowName() {
        return "Output Channel";
    }

    @Override
    public outputPin getOutputPin() {
        return null;
    }

    @Override
    public void stimulate() {
            //it wont take part in stimulate chain as it cant stimulate anyting else as it doesnt have output pin
    }
    @Override
    public String expression()
    {
        String s="";
        for(int i=0;i<parent.size();i++)
        {
            outputPin p=parent.get(i);
            s+=p.getHolder().expression();
            if(i<parent.size()-1)
            s+="||";
            
        }
        return s;
    }
}
import java.util.*;
import java.awt.*;
import java.awt.event.*;
class not extends gates
{
    public not(Point p)//not can only have 1 inputpin
    {
        super(p);
        estado=true;
        face.setSize(80,60);
        
        inputPin p1=new inputPin(this,1,new Point(5,25));
         p3=new outputPin(this,1,new Point(60,25));
        face.add(p1);
        face.add(p3);
        pins.add(p1);
        pins.add(p3);
        
    }
    public outputPin getOutputPin()
    {
        return p3;
    }
    public void setState(boolean etate,int pin)
    {
        
        estado=!etate;
        
        gui.pan.repaint();
        try
        {
            Thread.sleep(15);
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
        for(Pin p:child)
        {
            
                new Thread(new Runnable()
                {
                    public void run()
                    {
                        p.setState(estado);
                    }
                }).start();
        }
                
        gui.pan.repaint();
    }
    
    @Deprecated
    public boolean minterm()
    {
        return false;
    }
    public String getShowName()
    {
        return "NOT";
    }
}
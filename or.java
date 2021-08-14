import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
class or extends gates
{
    ArrayList<gates> parents;
    boolean[] state;
    final int ipins=2;
    public or(Point p)
    {
        super(p);
        state=new boolean[ipins];
        for(int i=0;i<ipins;i++)
        {
            state[i]=false;
        }
        face.setSize(200,200);
        face.setOpaque(true);
        
        inputPin p1=new inputPin(this,1,new Point(5,25));
        inputPin p2=new inputPin(this,2,new Point(5,180));
         p3=new outputPin(this,1,new Point(180,90));
        face.add(p1);
        face.add(p2);
        face.add(p3);
        pins.add(p1);
        pins.add(p2);
        pins.add(p3);
    }
    public outputPin getOutputPin()
    {
        return p3;
    }
    public void setState(boolean etate,int pin)
    {
        state[pin-1]=etate;
        estado=minterm();
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
    public boolean minterm()
    {
        boolean r=state[0];
        for(int i=1;i<ipins;i++)
        {
            r=(r)||(state[i]);//wont work for more than 2 pins
            
        }
        return r;
    }
    public String getShowName()
    {
        return "OR";
    }
}
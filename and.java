import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
class and extends gates
{
    
    boolean[] state;
    final int ipins=2;
    public and(Point lox)
    {
        super(lox);
        
        state=new boolean[ipins];
        for(int i=0;i<ipins;i++)
        {
            state[i]=false;
        }
        face.setSize(200,200);
        face.setOpaque(true);
        //face.setText("AND");
        
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
        gui.pan.repaint();
        //System.out.println("       state of and gate "+estado+"   what was received: "+etate);
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
        boolean r=state[0]&&state[1];
        return r;
    }
    public String getShowName()
    {
        return "AND";
    }
}
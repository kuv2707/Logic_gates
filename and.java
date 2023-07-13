import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
class and extends gates
{
    
    boolean[] state;
    final int ipins=2;
    public and(Point lox,JFrame frame,gui pan)
    {
        super(lox,frame,pan);
        // chehra=Main.and;
        chehraOffset.x=-70;
        chehraOffset.y=-20;
        
        state=new boolean[ipins];
        for(int i=0;i<ipins;i++)
        {
            state[i]=false;
        }
        this.setSize(230,200);
        this.setOpaque(true);
        //this.setText("AND");
        
        inputPin p1=new inputPin(this,1,new Point(5,45));
        inputPin p2=new inputPin(this,2,new Point(5,130));
        p3=new outputPin(this,1,new Point(210,90));
        this.add(p1);
        this.add(p2);
        this.add(p3);
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
        holdingPanel.repaint();
        //System.out.println("       state of and gate "+estado+"   what was received: "+etate);
        try
        {
            Thread.sleep(60);
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
        holdingPanel.repaint();
    }
    public void stimulate()
    {
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
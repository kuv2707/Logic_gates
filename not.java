import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
class not extends gates
{
    public not(Point p, JFrame frame, gui ooo)//not can only have 1 inputpin
    {
        super(p,frame,ooo);
        estado=true;
        // chehra=Main.not;
        this.setSize(80,50);
        inputPin p1=new inputPin(this,1,new Point(5,25));
         p3=new outputPin(this,1,new Point(60,25));
        this.add(p1);
        this.add(p3);
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
        
        holdingPanel.repaint();
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
                
        holdingFrame.repaint();
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
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * EXTEND THIS CLASS INTO INPUT AND OUTPUT PIN
 * AND OUTPIN CANNOT BE A CHILD
 * OUTPUT PIN KE THORUGH USKE HOLDER KA STATE CANT BE CHANGED
 */
abstract class Pin extends JButton implements Serializable
{
    gates holder;
    
    
    int no;
    Point locationOfWire;
    public Pin(gates holder,int no,Point locatio)
    {
        this.holder=holder;
        this.setSize(60,20);
        this.no=no;
        this.locationOfWire=locatio;
        this.setLocation(locatio);
        Pin este=this;
        addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                
                    if(gui.connector!=null)
                    {
                        if(este instanceof inputPin)
                        {
                            gui.connector.addChild((inputPin)este);
                            gui.pan.repaint();
                            gui.connector=null;
                        }
                        
                    }
                    else
                    {
                        if(este instanceof outputPin)
                        gui.connector=este;
                    }
                
            }
        });
    }
    @Override
    public String toString()
    {
        return "pin on "+holder;
    }
    abstract public void addChild(inputPin g);
    abstract public void setState(boolean b);
    public Point getWireLoc()
    {
        return locationOfWire;
    }
    public gates getHolder()
    {
        return holder;
    }
    public int getPin()
    {
        return no;
    }
}
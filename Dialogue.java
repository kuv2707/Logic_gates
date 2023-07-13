import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dialogue extends JDialog
{
    boolean decision;
    public Dialogue(String message,String yesOption,String noOption)
    {
        super();
        setSize(200,100);
        JPanel pan=new JPanel();

        pan.setLayout(new GridLayout(2,1));
        JButton but=new JButton(yesOption);
        JButton but2=new JButton(noOption);
        JLabel lab=new JLabel(message);
        JPanel sub=new JPanel();
        pan.setLayout(new GridLayout(1,2));
        sub.add(but);
        sub.add(but2);
        pan.add(lab);
        pan.add(sub);
        add(pan);
        lab.setFont(new Font("", Font.BOLD,25));
        but.setFont(new Font("", Font.BOLD,20));
        but2.setFont(new Font("", Font.BOLD,20));
        but.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                decision=true;
                dispose();
            }
        });
        but2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                decision=false;
                dispose();
            }
        });


    }
    public boolean SHOW()
    {
        setVisible(true);
        return decision;
    }
}

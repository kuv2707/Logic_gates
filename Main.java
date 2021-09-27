import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.*;
class Main
{
    public static void main()
    {
        main(new String[]{});
    }
    public static void main(String[] args)
    {
        try
        {
            initResources();
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
            {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception ioe)
        {
            ioe.printStackTrace();
        }
        SwingUtilities.invokeLater(new gui());
    }
    static BufferedImage and,or,not;
    public static void initResources() throws java.io.IOException
    {
        and=ImageIO.read(Main.class.getResource("/faces/and.jpg"));
        or=ImageIO.read(Main.class.getResource("/faces/or.jpg"));
        not=ImageIO.read(Main.class.getResource("/faces/not.jpg"));
    }
}
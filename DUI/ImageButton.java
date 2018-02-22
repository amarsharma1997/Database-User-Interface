package xyz;
import javax.swing.JButton;
import java.awt.event.*;
public class ImageButton extends JButton 
{
    String Path;
    ImageButton(String path)
    {
        super("Open");
        Path=path;
        addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                try
                {
                    Runtime.getRuntime().exec("rundll32.exe C:\\WINDOWS\\System32\\shimgvw.dll,ImageView_Fullscreen " + Details.addressdir +"\\" + Path);
                }
                catch(Exception e)
                {
                    
                }
            }
        });
    }
}

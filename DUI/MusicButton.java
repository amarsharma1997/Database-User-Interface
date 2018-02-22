package xyz;
import javax.swing.JButton;
import java.awt.event.*;
public class MusicButton extends JButton 
{
    String Path;
    MusicButton(String path)
    {
        super("Play");
        Path = path;
        addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                try
                {
                    Runtime.getRuntime().exec("C:\\Program Files\\Windows Media Player\\wmplayer.exe \"" +Details.addressdir + "\\" + Path + "\"");
                }
                catch(Exception e)
                {                   
                }
            }
        });
    }
}

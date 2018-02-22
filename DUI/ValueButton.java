package xyz;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import java.io.File;

public class ValueButton extends JButton
{
    int index;
    String type;
    ValueButton(int ind,String type)
    {
        super("Select File");
        index=ind;
        this.type=type;
        addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                JFileChooser jf=new JFileChooser();
                jf.showOpenDialog(null);
                try
                {
                    File file=jf.getSelectedFile();
                    FileCopy.filename[FileCopy.files] = file.getName();
                    FileCopy.filepath[FileCopy.files]=file.getAbsolutePath();
                    FileCopy.files++;
                    Details.currenttable.vals[index]=file.getName();
                }
                catch(NullPointerException e)
                {
                    javax.swing.JOptionPane.showMessageDialog(null,"File not selected.");
                }
            }
        });
    }
}

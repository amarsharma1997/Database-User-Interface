package xyz;
import javax.swing.JTextField;
import javax.swing.event.*;
public class ValueField extends JTextField 
{
    int ind;
    ValueField(int i)
    {
        ind=i;
        this.getDocument().addDocumentListener(new DocumentListener()
        {
            public void changedUpdate(DocumentEvent e) 
            {
                FUNC();
            }
            public void removeUpdate(DocumentEvent e) 
            {
                FUNC();
            }
            public void insertUpdate(DocumentEvent e) 
            {
                FUNC();
            }
            public void FUNC() 
            {
                Details.currenttable.vals[ind]=getText();
            } 
        });
    }
}

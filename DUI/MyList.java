package xyz;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class MyList extends JList<String>
{
    int Index;
   MyList(int index)
{
        DefaultListModel dsm=new DefaultListModel();
        dsm.addElement("<");
        dsm.addElement(">");
        dsm.addElement("=");
        dsm.addElement("<=");
        dsm.addElement(">=");
        dsm.addElement("<>");
        setModel(dsm);
        ensureIndexIsVisible( dsm.getSize() -1); 
        addListSelectionListener(new ListSelectionListener()
        {
           public void valueChanged(ListSelectionEvent lse)
           {
               Details.currenttable.Relation[index]=getSelectedValue();
               System.out.println(getSelectedValue());
           }
        });
    }
}

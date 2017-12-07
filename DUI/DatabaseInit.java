package xyz;
import java.awt.Dimension;
import java.sql.*;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;

public class DatabaseInit implements ActionListener 
{
    boolean f=false;
    Connection cn;
    JPanel panel1,panel2;
    public static String Dbname,User,Pass;
    JButton perm,ntable;
    int usedlength,tablesize;
    ButtonGroup butgroup;
    String s[]=new String[50];
    CheckTable cktable[]=new CheckTable[50];
    public void insertTables()
    {
        try
        {
            Details.command = "show tables";
            ResultSet rs = Details.st.executeQuery(Details.command);
            while(rs.next())
            {
                s[tablesize]=rs.getString(1);
                cktable[tablesize]=new CheckTable(s[tablesize]);
                cktable[tablesize].setBounds(50,usedlength,200,25);
                panel1.add(cktable[tablesize]);
                butgroup.add(cktable[tablesize]);
                usedlength+=35;
                tablesize++;
            }
            Data.MetaPane.setViewportView(panel1);
            Data.MetaPane.setPreferredSize(new Dimension(400,700));
            Data.MetaPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            //Data.MetaPane.revalidate();
        }
        catch(SQLException e)
        {
            
        }            
        
    }
    DatabaseInit(String dbname,String user,String pass)
    {
        Dbname=dbname;
        User=user;
        Pass=pass;
        panel1=new JPanel();
        panel2=new JPanel();
        butgroup=new ButtonGroup();
        initpanel1();
    }
    public void initpanel1()
    {
        JLabel title=new JLabel("Using Database:-   " + Dbname);
        perm=new JButton("Permissions");
        ntable=new JButton("New Table");
        JLabel tbtitle=new JLabel("Tables :- " );
        panel1.setLayout(null);
        title.setBounds(20,30,200,25);
        perm.setBounds(20,70,150,25);
        ntable.setBounds(220,70,150,25);
        perm.addActionListener(this);
        ntable.addActionListener(this);
        tbtitle.setBounds(20,120,200,25);
        usedlength=155;
        panel1.add(title);
        panel1.add(perm);
        panel1.add(ntable);
        panel1.add(tbtitle);
        insertTables();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton active=(JButton) e.getSource();
        if(active==perm)
        {
            try
            {
                Connection cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Dbname ,User,Pass);
                String line;
                   Statement st=cn.createStatement();
                line="grant all privileges on " + Dbname +".* to 'DUI'@'localhost' identified by 'DUI'" ;
                st.executeUpdate(line);
                line="revoke all privileges on " + Dbname +".* from 'DUI'@'localhost' identified by 'DUI'" ;
                st.executeUpdate(line);
            }
            catch(SQLException exc)
            {
                JOptionPane.showMessageDialog(null,"Current user does'nt have the permissions");
                return ;
            }
            new perm(Dbname,User,Pass);
        }
        else if(active==ntable)
        {
            //newTable();
        }            
    }
}

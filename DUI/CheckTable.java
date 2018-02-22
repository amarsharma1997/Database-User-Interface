package xyz;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import javax.swing.JLabel;
public class CheckTable extends JRadioButton implements ItemListener
{
    String Name;
    public JPanel p1;
    public int usedlength=0,tl=0;
    public JPanel foruse;
    public String Colomn[]=new String[100];
    public int Colomns=0;
    public String types[]=new String[100];
    public String vals[]=new String[100];
    JPanel labelPane;
    public String Relation[]=new String[100];
    CheckTable(String name)
    {
        super(name);
        foruse=new JPanel();
        p1=new JPanel();
        p1.setLayout(null);
        initPanes();
        initsubPanes();
        addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e)
    {
        if(isSelected()==true)
        {
            Details.currenttable=this;
            Details.displaypanel.MainPane.setViewportView(p1);
            Details.displaypanel.ComponentPane.setViewportView(foruse);
        }
    }

    public void initPanes()
    {
        try
        {
            p1=new JPanel();
            p1.setLayout(null);
            Details.command="select count(*) from " + Name;
            ResultSet rs=Details.st.executeQuery(Details.command);
            int row=0;
            rs.next();
            row = rs.getInt(1);
            Details.command = "select * from " + Name;
            rs=Details.st.executeQuery(Details.command);
            ResultSetMetaData rmd=rs.getMetaData();
            int i;
            Colomn[0]="";
            vals[0]="";
            types[0]="";
            Colomns=rmd.getColumnCount();
            String temp;
            labelPane = new JPanel();
            int length=0;
            for(i=1;i<=Colomns;i++)
            {
                vals[i]="";
                Colomn[i]=rmd.getColumnName(i);
                JLabel Jl=new JLabel(Colomn[i]);
                Jl.setBounds(length,0,100,25);
                p1.add(Jl);
                length+=100;
            }
            tl=length;
            usedlength=25;
            rs.next();
            boolean flag=false;
            if(row==0)
            {
                for(i=1;i<=Colomns;i++)
                {
                    types[i]="string";
                }
            }
            else
            {
                for(i=1;i<=Colomns;i++)
                {
                     temp=rs.getString(i);
                     if(temp.equalsIgnoreCase("image")==true)
                    {
                       flag=true;
                     types[i]="image";
                    }
                    else if(temp.equalsIgnoreCase("music")==true)
                    {
                     flag=true;
                     types[i]="music";
                    }
                    else if(temp.equalsIgnoreCase("string")==true)
                    {
                     flag=true;
                       types[i]="string";
                    }
                    else
                    {
                        types[i]="string";
                    }
                }
            }
            if(flag==false)
            {
                rs.previous();
            }
            while(rs.next())
            {
                JPanel subpanel =new JPanel();
                subpanel.setLayout(null);
                length=0;
                for(i=1;i<=Colomns;i++)
                {
                    if(types[i].equals("string"))
                    {
                        JTextField jtf=new JTextField(rs.getString(i));
                        jtf.setSize(100,25);
                        jtf.enable(false);
                        jtf.setBounds(length,0,100,25);
                        subpanel.add(jtf);
                    }
                    else if(types[i].equals("image"))
                    {
                        ImageButton jib=new ImageButton(DatabaseInit.Dbname + "\\" + Name + "\\" +rs.getString(i));
                        jib.setSize(100,25);
                        jib.setBounds(length,0,100,25);
                        subpanel.add(jib);
                    }
                    else if(types[i].equals("music"))
                    {
                        MusicButton jmb=new MusicButton(DatabaseInit.Dbname + "\\" + Name + "\\" +rs.getString(i));
                        jmb.setSize(100,25);
                        jmb.setBounds(length,0,100,25);
                        subpanel.add(jmb);
                    }
                    length += 100;
                }
                subpanel.setBounds(0,usedlength,length,25);
                p1.add(subpanel);
                usedlength += 25;
            }
            p1.repaint();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    public void initsubPanes()
    {
        foruse = new JPanel();
        int i;
        int length=0;
        vals[0]="";
        foruse.setLayout(null);
        for(i=1;i<=Colomns;i++)
        {
            vals[i]="";
            JLabel jl=new JLabel(Colomn[i]);
            jl.setBounds(length,0,100,25);
            foruse.add(jl);
            if(types[i].equalsIgnoreCase("string"))
            {
                ValueField vf=new ValueField(i);
                vf.setBounds(length,25,100,25);
                foruse.add(vf);
            }
            else if(types[i].equalsIgnoreCase("image"))
            {
                ValueButton vf = new ValueButton(i,"IMAGE FILES");
                vf.setBounds(length,25,100,25);
                foruse.add(vf);
            }
            else if(types[i].equalsIgnoreCase("music"))
            {
                ValueButton vf = new ValueButton(i,"MUSIC FILES");
                vf.setBounds(length,25,100,25);
                foruse.add(vf);
            }
            length+=100;
        }
        foruse.repaint();
    }
}

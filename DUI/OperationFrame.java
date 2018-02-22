package xyz;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.sql.*;
public class OperationFrame extends javax.swing.JFrame 
{   
    String s1,s2;
    public OperationFrame(String operation)
    {
       super(operation);
       s1="";
       s2="";
       initComponents();
       boolean flag=false;
       if(operation.equalsIgnoreCase("Update"))
       {
           s1="update " + Details.currenttable.Name + " set ";
           int i,count=0;
           for(i=1;i<=Details.currenttable.Colomns;i++)
           {
               if(!Details.currenttable.vals[i].equals(""))
               {
                   count++;
               }
           }
           for(i=1;i<=Details.currenttable.Colomns;i++)
           {
               if(!Details.currenttable.vals[i].equals(""))
               {
                   s1 += Details.currenttable.Colomn[i] + " = " + "'" + Details.currenttable.vals[i] +"' ";
                   count--;
                   if(count!=0)
                   {
                       s1 += ", ";
                   }
               }
           }
       }
       else if(operation.equalsIgnoreCase("Delete"))
       {
           s1 = "delete from " + Details.currenttable.Name + " ";
       }   
       else if(operation.equalsIgnoreCase("Search"))
       {
           s1 = "select * from " + Details.currenttable.Name + " ";
       }
       else
       {    
       }
       Details.currenttable.initsubPanes();
       JPanel jp=new JPanel();
       jp.setLayout(null);
       Details.currenttable.foruse.setBounds(0,0,Details.currenttable.tl,50);
       jp.add(Details.currenttable.foruse);
       int i,length=0;
       for(i=1;i<=Details.currenttable.Colomns;i++)
       {
           Details.currenttable.Relation[i]="";
           JScrollPane jsp=new JScrollPane();
           MyList ml=new MyList(i);
           jsp.setViewportView(ml);
           jsp.setBounds(length,50,100,25);
           jp.add(jsp);
           length+=100;
       }
       jScrollPane1.setViewportView(jp);
       setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jButton1.setText("Done");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(349, 349, 349)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int i=0,count=0;
        for(i=1;i<=Details.currenttable.Colomns;i++)
        {
           if(!Details.currenttable.vals[i].equals(""))
           {
               count++;
           }
        }
        if(count!=0)
        {
            s2="where ";
            for(i=1;i<=Details.currenttable.Colomns;i++)
            {
                if(!Details.currenttable.vals[i].equals(""))
                {
                   s2 += Details.currenttable.Colomn[i] + " " + Details.currenttable.Relation[i] + " '" + Details.currenttable.vals[i] +"' ";
                   count--;
                   if(count !=0)
                   {
                       s2 +=", ";
                   }
                   Details.currenttable.Relation[i]="";
                }
            }
            s1=s1+ " " +s2;
        }
        try
        {
            if(getTitle().equalsIgnoreCase("Search"))
            {
                ResultSet rs=Details.st.executeQuery(s1);
                new SearchFrame(rs);
            }
            else
            {
                Details.st.executeUpdate(s1);
                Details.currenttable.initPanes();
                Details.displaypanel.MainPane.setViewportView(Details.currenttable.p1);
                Details.currenttable.initsubPanes();
                Details.displaypanel.ComponentPane.setViewportView(Details.currenttable.foruse);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
           javax.swing.JOptionPane.showMessageDialog(null,"Check the values.");
        }
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

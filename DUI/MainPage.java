package xyz;

import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import java.io.File;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.event.*;

public class MainPage extends javax.swing.JFrame 
{
    public MainPage() 
    {
        setState(JFrame.NORMAL);
        initComponents();
        Data.DisplayPane = jScrollPane1;
        Data.MetaPane = jScrollPane2;
        Data.DisplayPane.setLayout(null);
        if(checkInit()==false)
        {
            System.exit(0);
        }
        setVisible(true);
    }
    @SuppressWarnings({"unchecked"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DataBase User InterFace");
        setPreferredSize(new java.awt.Dimension(1366, 768));
        setResizable(false);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 700));

        jScrollPane2.setBackground(new java.awt.Color(51, 255, 255));
        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(400, 700));

        jButton1.setLabel("New Database");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setLabel("Open Database");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new NewDataDialog().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new OpenDataDialog().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed
    
    
    public static void main(String args[]) 
    {
        Details.MP=new MainPage();
       
    }
    
    
    
    public static boolean checkInit()
    {
        Details.addressdir=new JFileChooser().getFileSystemView().getDefaultDirectory().toString()+"\\DatabaseUserInterface";
        File file= new File(Details.addressdir);
        try
        {
            if(file.exists()==true)
            {
                Details.cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/","DUI","DUI");
                return true;
            }
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");
            Statement st=cn.createStatement();
            String line="create user 'DUI'@'localhost' identified by 'DUI'";
            st.executeUpdate(line);
            line="grant all privileges on *.* to 'DUI'@'localhost' identified by 'DUI'";
            st.executeUpdate(line);
            line="grant grant option on *.* to 'DUI'@'localhost' identified by 'DUI'";
            st.executeUpdate(line);
            Details.cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/","DUI","DUI");
        }
        catch(ClassNotFoundException e)
        {
             return false;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Start the server first.");
            return false;    
        }
        file.mkdir();
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}

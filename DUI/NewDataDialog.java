package xyz;

import javax.swing.JOptionPane;
import java.sql.*;

public class NewDataDialog extends javax.swing.JFrame 
{
    public NewDataDialog() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create New Database");
        setAlwaysOnTop(true);
        setLocation(new java.awt.Point(0, 0));

        jLabel1.setText("Database Name:-");
        jLabel1.setName(""); // NOI18N

        jLabel2.setText("UserName:-");

        jLabel3.setText("Password:-");

        jCheckBox1.setText("New User");

        jButton1.setText("Create ");
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
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckBox1)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPasswordField1)))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String user=jTextField2.getText(),pass=new String(jPasswordField1.getPassword()),dbname=jTextField1.getText();
        Statement stmt;
        String command;
        Connection cn;
        
        if(dbname.length()==0||user.length()==0)
        {
                JOptionPane.showMessageDialog(null,"Fields are empty");
                return ;
        }
        try
        {
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname,"DUI","DUI");
            JOptionPane.showMessageDialog(null,"Database already exists");
            return ;
        }
        catch(SQLException e)
        {}
        if(jCheckBox1.isSelected()==true)
        {
            if(user.length()==0)
            {
                JOptionPane.showMessageDialog(null,"Username is Empty");
                return ;
            }
            try
            {
                command ="Create User '" + user + "'@'localhost' identified by '" + pass + "'";
                stmt=Details.cn.createStatement();
                stmt.executeUpdate(command);
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,"User already exists.\nTry with different username");
                return;
            }
        }
        try
        {
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/",user,pass);
            cn.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Wrong User or Password");
            return ;
        }
        try
        {
            stmt=Details.cn.createStatement();
            command="Create Database " + dbname;
            stmt.executeUpdate(command);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Database already exists.\nTry with different database name");
            return ;
        }
        try
        {
            Details.st=Details.cn.createStatement();
            command = "grant all privileges on " + dbname + ".* to '" + user + "'@'localhost' " + "identified by '" + pass + "'";
            Details.st.executeUpdate(command);
            command = "grant all privileges on mysql.* to '" + user + "'@'localhost' " + "identified by '" + pass + "' with grant option";
            Details.st.executeUpdate(command);
            command = "grant grant option on " + dbname + ".* to '" + user + "'@'localhost' " + "identified by '" + pass + "'";
            Details.st.executeUpdate(command);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Some error occured");
            return ;
        }
        new DatabaseInit(dbname,user,pass);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}

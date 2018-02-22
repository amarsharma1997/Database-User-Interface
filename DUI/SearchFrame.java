package xyz;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.sql.*;
import javax.swing.JTextField;
public class SearchFrame extends javax.swing.JFrame 
{
    java.sql.ResultSet res;
    public SearchFrame(java.sql.ResultSet res) 
    {
        this.res=res;
        initComponents();
        createVision();
        setVisible(true);
    }
    public void createVision()
    {
        JPanel jp=new JPanel();
        jp.setLayout(null);
        int i,length=0,ul=25;
        for(i=1;i<=Details.currenttable.Colomns;i++)
        {
            JLabel jl = new JLabel(Details.currenttable.Colomn[i]);
            jl.setBounds(length,0,100,25);
            jp.add(jl);
            length += 100;
        }
        length=0;
        try
        {
            while(res.next())
            {
                length=0;
                for(i=1;i<=Details.currenttable.Colomns;i++)
                {
                    String s=Details.currenttable.types[i];
                    if(s.equals("string"))
                    {
                        JTextField jtf=new JTextField(res.getString(i));
                        jtf.setSize(100,25);
                        jtf.enable(false);
                        jtf.setBounds(length,ul,100,25);
                        jp.add(jtf);
                    }
                    else if(s.equals("image"))
                    {
                        ImageButton jib=new ImageButton(DatabaseInit.Dbname + "\\" + Details.currenttable.Name + "\\" +res.getString(i));
                        jib.setSize(100,25);
                        jib.setBounds(length,ul,100,25);
                        jp.add(jib);
                    }
                    else if(s.equals("music"))
                    {
                        MusicButton jmb=new MusicButton(DatabaseInit.Dbname + "\\" + Details.currenttable.Name + "\\" +res.getString(i));
                        jmb.setSize(100,25);
                        jmb.setBounds(length,ul,100,25);
                        jp.add(jmb);
                    }        
                    length += 100;
                }
                ul+=25;
            }
            jp.repaint();
            jScrollPane1.setViewportView(jp);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);

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
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(321, 321, 321)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transsystem;

import java.awt.List;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import transsystem.NewAlertGUI;

/**
 *
 * @author Sabrina
 */
public class SetAlertGUI extends javax.swing.JFrame {

    /**
     * Creates new form SetAlertGUI
     */
    
    private ArrayList<Station> path;
    private ArrayList<Station> ts;
    private Station s;
    private Station d;
    private ArrayList<Integer> rl;
    private ArrayList<Integer> bl;
    private int rate;
    private int costTime;
        
    public SetAlertGUI() {
        initComponents();
        this.s=null;
        this.d=null;
        this.rl=null;
        this.bl=null;
        this.path=null;
        this.ts=null;
        this.rate=0;
        this.costTime=0;
        jButton2.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Set Alert");

        jLabel2.setText("Passenger ID: ");

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.DefaultListModel());
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jButton2.setText("Add New Alert");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Add New Passenger");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(203, 203, 203))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void login(){
        int checkLogin=0;
        for(Passenger x:TransSystem.getPassengers()){
            if(x.getCid()==Integer.parseInt(jTextField1.getText())){
                TransSystem.setPlogin(1);
                TransSystem.setP(x);
                JOptionPane.showMessageDialog(null, "You have successful logIn");
                jButton2.setEnabled(true);
                DefaultListModel m= (DefaultListModel)(jList1.getModel());
                m.clear();
                for(Alert y:x.getAlerts()){
                    m.addElement(y.getAlertInfo());
                }
                checkLogin=1;
            }
        }
        if(checkLogin==0){
            JOptionPane.showMessageDialog(null,"You id is wrong and you need to create a account");
        }
    }
    
    private void searchHistoryAlert(){
        String s0=(String) jList1.getSelectedValue();
        String[] s1=s0.split("\t");
        String[] h=s1[2].split(":");
        LocalTime at=LocalTime.of(Integer.parseInt(h[0]),Integer.parseInt(h[1]));
 
        s=TransSystem.getStation(s1[0]);
        d=TransSystem.getStation(s1[1]);
        path=TransSystem.searchBusRoute(s.getName(),d.getName());
        ts=TransSystem.findTransferStation(path);
        rl=TransSystem.findRoute(s,d,ts);
        bl=TransSystem.findBusList(s, d, ts, rl);
        costTime=TransSystem.calculateAlertTotalTime(s.getName(), d.getName(), at);
        rate = TransSystem.calcuateBusPathRouteRate(ts);
        int a=d.calculateAlertWaitingTime(bl.get(bl.size()-1), at);
        ArrayList<Carpool> c=TransSystem.searchCarpool(s1[0],s1[1],at,Integer.parseInt(s1[3]));
        int dif=at.minusMinutes(a+costTime).getHour()*60+at.minusMinutes(a+costTime).getMinute()-LocalTime.now().getHour()*60-LocalTime.now().getMinute();
        if((dif>0&&a<Integer.valueOf(s1[3]))||c.size()!=0){
            Alert al=new Alert(s,d,at,Integer.parseInt(s1[3]));
            TransSystem.getP().addAlert(al);
            if(dif>0&&a<Integer.valueOf(s1[3])){
                //LocalTime actualArriveTime=at.minusMinutes(a);
                SearchResult sr=new SearchResult(s,d,ts,rl,bl,costTime,rate);
                TransSystem.addSearchResult(sr);
                SearchResultsGUI srgui=new SearchResultsGUI(sr.getSrid(),c,al);
                srgui.setVisible(true);        
                //this.dispose();
            }else{
                SearchResultsGUI srgui=new SearchResultsGUI(c,al);
                srgui.setVisible(true);        
                //this.dispose();
            }

        }else{
            JOptionPane.showMessageDialog(null,"no bus/car for you to the destination at"+at);
        }        
    }
    
    
    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            searchHistoryAlert();
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(TransSystem.getPlogin()==1){
            NewAlertGUI nagui=new NewAlertGUI();
            nagui.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "You have to logIn first!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(TransSystem.getPlogin()==0){
            AddNewPassengerGUI anp=new AddNewPassengerGUI();
            anp.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "You cannot create a new customer!");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        login();
    }//GEN-LAST:event_jButton1ActionPerformed


    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SetAlertGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SetAlertGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SetAlertGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SetAlertGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SetAlertGUI().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

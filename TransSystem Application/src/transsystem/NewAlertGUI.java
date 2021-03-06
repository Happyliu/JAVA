/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transsystem;

import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Sabrina
 */
public class NewAlertGUI extends javax.swing.JFrame {

    /**
     * Creates new form NewAlertGUI
     */
    
        private ArrayList<Station> path;
        private ArrayList<Station> ts;
        private Station s;
        private Station d;
        private ArrayList<Integer> rl;
        private ArrayList<Integer> bl;
        private int rate;
        private int costTime;
    
    public NewAlertGUI() {
        initComponents();
        this.s=null;
        this.d=null;
        this.rl=null;
        this.bl=null;
        this.path=null;
        this.ts=null;
        this.rate=0;
        this.costTime=0;
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
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Add New Alert ");

        jLabel2.setText("Start Station:");

        jLabel3.setText("Destination Station:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Campus Lodge", "Lexington Crossing", "Stoneridge Apartment", "Butler Plaza", "IFAS Lab", "Lake Alice", "Reitz Union", "Rawlings Hall", "Oaks Mall", "Cabana Beach Apartment", "Hilton Conference Center", "Southwest Recreation Center", "Corry Village", "Hough Hall", "Diamond Apartment", "Tanglewood Village", "Tivoli Apartment", "Shands Hospital", "University Commons Apartment", "Windmeadows Apartment", "Magurie Village" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Campus Lodge", "Lexington Crossing", "Stoneridge Apartment", "Butler Plaza", "IFAS Lab", "Lake Alice", "Reitz Union", "Rawlings Hall", "Oaks Mall", "Cabana Beach Apartment", "Hilton Conference Center", "Southwest Recreation Center", "Corry Village", "Hough Hall", "Diamond Apartment", "Tanglewood Village", "Tivoli Apartment", "Shands Hospital", "University Commons Apartment", "Windmeadows Apartment", "Magurie Village" }));

        jLabel4.setText("Arrive Time: ");

        jLabel5.setText("Alert In Advance:");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel6.setText("min");

        jButton1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jButton1.setText("Search Route");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(167, 167, 167))
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox2, 0, 1, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField2)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)))
                        .addGap(49, 49, 49))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(33, 33, 33)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addNewAlert(){
        
        LocalTime at=LocalTime.of(Integer.parseInt(jTextField1.getText()), Integer.parseInt(jTextField2.getText()));
        
        s=TransSystem.getStation((String)jComboBox1.getSelectedItem());
        d=TransSystem.getStation((String)jComboBox2.getSelectedItem());
        path=TransSystem.searchBusRoute(s.getName(),d.getName());
        ts=TransSystem.findTransferStation(path);
        rl=TransSystem.findRoute(s,d,ts);
        bl=TransSystem.findBusList(s, d, ts, rl);
        costTime=TransSystem.calculateAlertTotalTime(s.getName(), d.getName(), at);
        rate = TransSystem.calcuateBusPathRouteRate(ts);
        int a=d.calculateAlertWaitingTime(bl.get(bl.size()-1), at);
        ArrayList<Carpool> c=TransSystem.searchCarpool((String)jComboBox1.getSelectedItem(),(String)jComboBox2.getSelectedItem(),at,Integer.valueOf(jTextField3.getText()));
        int dif=at.minusMinutes(a+costTime).getHour()*60+at.minusMinutes(a+costTime).getMinute()-LocalTime.now().getHour()*60-LocalTime.now().getMinute();
        if((dif>0&&a<Integer.valueOf(jTextField3.getText()))||c.size()!=0){
            Alert al=new Alert(s,d,at,Integer.parseInt(jTextField3.getText()));
            TransSystem.getP().addAlert(al);
            //JOptionPane.showMessageDialog(null,a+" "+costTime);
            if(dif>0&&a<Integer.valueOf(jTextField3.getText())){
                //LocalTime actualArriveTime=at.minusMinutes(a);
                SearchResult sr=new SearchResult(s,d,ts,rl,bl,costTime,rate);
                TransSystem.addSearchResult(sr);
                SearchResultsGUI srgui=new SearchResultsGUI(sr.getSrid(),c,al);
                srgui.setVisible(true);        
                this.dispose();
            }else{
                SearchResultsGUI srgui=new SearchResultsGUI(c,al);
                srgui.setVisible(true);        
                this.dispose();
            }

            //al.setActive(1);
            //al.setAlertTime(at);
            
            //JOptionPane.showMessageDialog(null,TransSystem.getP().getAlerts().get(0).getAlertInfo());
            //JOptionPane.showMessageDialog(null,"you will arrive you destination at"+actualArriveTime);
        }else{
            JOptionPane.showMessageDialog(null,"no bus/car for you to the destination at"+at);
        }
    }
    
    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        addNewAlert();
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
            java.util.logging.Logger.getLogger(NewAlertGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewAlertGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewAlertGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewAlertGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewAlertGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}

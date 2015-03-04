/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transsystem;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import transsystem.ResultsDetailGUI;
import transsystem.SearchRoutesGUI;

/**
 *
 * @author Sabrina
 */
public class SearchResultsGUI extends javax.swing.JFrame {

    /**
     * Creates new form SearchResultsGUI
     */
    private ArrayList<Carpool> a; 
    private int srid;
    private int hasBus=1;
    private Alert al=null;
    
    public SearchResultsGUI(){
        initComponents();
    }
    
    public SearchResultsGUI(ArrayList<Carpool> a,Alert al){
        this();
        this.a=a;
        this.al=al;
        this.hasBus=0;
        DefaultListModel m = (DefaultListModel)(jList1.getModel());
        m.clear();
        for(Carpool x: a){
            m.addElement(x.getBasicInfo());
        }
    }
    
    public SearchResultsGUI(int s,ArrayList<Carpool> a,Alert al){
        this();
        this.srid=s;
        this.a=a;
        this.al=al;
        //JOptionPane.showMessageDialog(null,TransSystem.getSearchResult(srid).displayResult());

        DefaultListModel m = (DefaultListModel)(jList1.getModel());
        m.clear();
        m.addElement(TransSystem.getSearchResult(srid).displayResult());
        for(Carpool x: a){
            m.addElement(x.getBasicInfo());
        }
    }
    
    public SearchResultsGUI(ArrayList<Carpool> a){
        this();
        this.a=a;
        this.hasBus=0;
        DefaultListModel m = (DefaultListModel)(jList1.getModel());
        m.clear();
        for(Carpool x: a){
            m.addElement(x.getSearchBasicInfo());
        }
    }
    
    public SearchResultsGUI(int s,ArrayList<Carpool> a) {
        this();
        this.srid=s;
        this.a=a;
        //JOptionPane.showMessageDialog(null,TransSystem.getSearchResult(srid).displayResult());

        DefaultListModel m = (DefaultListModel)(jList1.getModel());
        m.clear();
        m.addElement(TransSystem.getSearchResult(srid).displayResult());
        for(Carpool x: a){
            m.addElement(x.getSearchBasicInfo());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem4 = new javax.swing.JCheckBoxMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("jCheckBoxMenuItem2");

        jCheckBoxMenuItem3.setSelected(true);
        jCheckBoxMenuItem3.setText("jCheckBoxMenuItem3");

        jCheckBoxMenuItem4.setSelected(true);
        jCheckBoxMenuItem4.setText("jCheckBoxMenuItem4");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("jRadioButtonMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Search Results");

        jList1.setModel(new javax.swing.DefaultListModel());
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showDetail(){
        int index=jList1.getSelectedIndex();
            if(hasBus==0){
                ResultsDetailGUI rdgui;
                if(al==null){
                    rdgui=new ResultsDetailGUI(a.get(index));
                }else{
                    rdgui=new ResultsDetailGUI(a.get(index),al);
                }
                rdgui.setVisible(true); 
                //this.dispose();
            }else{
                if(index==0){
                    SearchResult busSearchResult=TransSystem.getSearchResult(srid);
                    ResultsDetailGUI rdgui;
                    if(al==null){
                        rdgui=new ResultsDetailGUI(srid);
                    }else{
                        rdgui=new ResultsDetailGUI(srid,al);
                    }
                    rdgui.setVisible(true); 
                    //this.dispose();
                }else{
                    ResultsDetailGUI rdgui;
                    if(al==null){
                        rdgui=new ResultsDetailGUI(a.get(index-1));
                    }else{
                        rdgui=new ResultsDetailGUI(a.get(index-1),al);
                    }
                    rdgui.setVisible(true); 
                    //this.dispose();
                }
            }   
    }
    
    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2) {
            showDetail();
        }
    }//GEN-LAST:event_jList1MouseClicked

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
            java.util.logging.Logger.getLogger(SearchResultsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchResultsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchResultsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchResultsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchResultsGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

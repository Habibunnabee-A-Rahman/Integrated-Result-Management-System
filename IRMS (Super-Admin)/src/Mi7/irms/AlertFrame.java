/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Mi7.irms;
import javax.swing.*;
/**
 *
 * @author himal
 */
public class AlertFrame extends javax.swing.JFrame {

    /**
     * Creates new form AlertFrame
     */
    LoginFrame lfrmx;
    EnterUniversityFrame eufrmx;
    ViewTableFrame vtfrmx;
    EnterUniversityAdminFrame euafrmx;
    EditUniversityInfoFrame euifrmx;
    EditUniversityAdminFrame eua2frmx;
    AddSchoolFrame asfrmx;
    EditSchoolFrame esfrmx;
    AddDepartmentFrame adfrmx;
    EditDepartmentFrame edfrmx;
    AddProgramFrame apfrmx;
    EditProgramFrame epfrmx;
    AddSyllabusFrame asbfrmx;
    EditSyllabusFrame esbfrmx;
    SelectSyllabusFrame ssbfrmx;
    EnterSyllabusInfoFrame esbifrmx;
    EnterCourseInfoFrame ecrifrmx;
    EnterEvaluationSetFrame eevsfrmx;
    //ChangePassFrame cpfrmx;
    //AddChangeEmployee acefrmx;
    //AddChangeStudent acsfrmx;
    //AddCourseFrame acfrmx;
    //ConnectCourseFrame ccfrmx;
    String parent=" ";
    int x;
    
    
    void passLoginFrame(LoginFrame lfrmx,String parent){
        
        this.parent=parent;
        this.lfrmx = lfrmx;
        
    }
    void passEnterUniversityFrame(EnterUniversityFrame eufrmx,String parent){
        this.parent = parent;
        this.eufrmx = eufrmx;
    }
    void passViewTableFrame(ViewTableFrame vtfrmx, String parent){
        this.vtfrmx = vtfrmx;
        this.parent = parent;
    }
    void passEnterUniversityAdminFrame(EnterUniversityAdminFrame euafrmx,String parent){
        this.euafrmx = euafrmx;
        this.parent = parent;
    }
    void passEditUniversityInfoFrame(EditUniversityInfoFrame euifrmx,String parent){
        this.euifrmx = euifrmx;
        this.parent = parent;
    }
    void passEditUniversityAdminFrame(EditUniversityAdminFrame eua2frmx,String parent){
        this.eua2frmx = eua2frmx;
        this.parent = parent;
    }
    void passAddSchoolFrame(AddSchoolFrame asfrmx,String parent){
        this.asfrmx = asfrmx;
        this.parent = parent;
    }
    void passEditSchoolFrame(EditSchoolFrame esfrmx, String parent){
        this.esfrmx = esfrmx;
        this.parent = parent;
    }
    void passAddDepartmentFrame(AddDepartmentFrame adfrmx, String parent){
        this.adfrmx = adfrmx;
        this.parent = parent;
    }
    void passEditDepartmentFrame(EditDepartmentFrame edfrmx, String parent){
        this.edfrmx = edfrmx;
        this.parent = parent;
    }
    void passAddProgramFrame(AddProgramFrame apfrmx, String parent){
        this.apfrmx = apfrmx;
        this.parent = parent;
    }
    void passEditProgramFrame(EditProgramFrame epfrmx, String parent){
        this.epfrmx = epfrmx;
        this.parent = parent;
    }
    void passAddSyllabusFrame(AddSyllabusFrame asbfrmx,String parent){
        this.asbfrmx = asbfrmx;
        this.parent = parent;
    }
    void passEditSyllabusFrame(EditSyllabusFrame esbfrmx,String parent){
        this.esbfrmx = esbfrmx;
        this.parent = parent;
    }
    void passSelectSyllabusFrame(SelectSyllabusFrame ssbfrmx,String parent){
        this.ssbfrmx = ssbfrmx;
        this.parent = parent;
    }
    void passEnterSyllabusInfoFrame(EnterSyllabusInfoFrame esbifrmx,String parent){
        this.esbifrmx = esbifrmx;
        this.parent = parent;
    }
    void passEnterCourseInfoFrame(EnterCourseInfoFrame ecrifrmx,String parent){
        this.ecrifrmx = ecrifrmx;
        this.parent = parent;
    }
    void passEnterEvaluationSetFrame(EnterEvaluationSetFrame eevsfrmx,String parent){
        this.eevsfrmx = eevsfrmx;
        this.parent = parent;
    }
    /*void passChangePassFrame(ChangePassFrame cpfrmx,String parent){
        
        this.parent=parent;
        this.cpfrmx = cpfrmx;
        
    }
    
    void passAddChangeEmployee(AddChangeEmployee acefrmx,String parent){
        this.acefrmx=acefrmx;
        this.parent = parent;
    }
    void passAddChangeStudent(AddChangeStudent acsfrmx,String parent){
        this.acsfrmx=acsfrmx;
        this.parent = parent;
    }
    void passAddCourseFrame(AddCourseFrame acfrmx,String parent){
        this.acfrmx=acfrmx;
        this.parent = parent;
    }
    void passConnectCourseFrame(ConnectCourseFrame ccfrmx,String parent){
        this.ccfrmx=ccfrmx;
        this.parent = parent;
    }*/
    
    
    public AlertFrame(int x,String msg1,String msg2,String msg3) {
        initComponents();
        this.x=x;
        if(x==1){
            
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/icon/warning_64.png")));
            this.setTitle("Warning!!!");
        }else if(x==2){
            
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/icon/error_2_64.png")));
            this.setTitle("Error!!!");
        }else if(x==3){
            
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/icon/server_error_2_64.png")));
            this.setTitle("Error!!!");
        }else if(x==4){
            
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/icon/error-page_64.png")));
            this.setTitle("Error!!!");
        }else if(x==5){
            
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/icon/success_64.png")));
            this.setTitle("Success!!!");
        }else if(x == 6){
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/icon/error_data_64.png")));
            this.setTitle("Error!!!");
        }else if(x == 7){
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/icon/not_found_64.png")));
            this.setTitle("Error!!!");
        }
        jLabel2.setText(msg1);
        jLabel3.setText(msg2);
        jLabel4.setText(msg3);
            
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Error!!");
        setMinimumSize(new java.awt.Dimension(417, 161));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel4.setPreferredSize(new java.awt.Dimension(470, 42));

        jPanel5.setPreferredSize(new java.awt.Dimension(330, 30));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel5);

        jButton1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton1.setText("OK");
        jButton1.setPreferredSize(new java.awt.Dimension(80, 26));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        getContentPane().add(jPanel4, java.awt.BorderLayout.SOUTH);

        jPanel1.setPreferredSize(new java.awt.Dimension(110, 124));

        jPanel6.setPreferredSize(new java.awt.Dimension(100, 20));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setPreferredSize(new java.awt.Dimension(350, 124));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel7.setPreferredSize(new java.awt.Dimension(300, 25));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel7);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setPreferredSize(new java.awt.Dimension(360, 22));
        jPanel2.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.setPreferredSize(new java.awt.Dimension(360, 22));
        jPanel2.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("jLabel2");
        jLabel4.setPreferredSize(new java.awt.Dimension(360, 22));
        jPanel2.add(jLabel4);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        /*else if(parent.toLowerCase().equals("changepassframe")){
            cpfrmx.setEnabled(true);
            if(x==5){
                cpfrmx.dispose();
            }
        }*/
        
        dispose();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        if(parent.toLowerCase().equals("loginframe")){
            lfrmx.setEnabled(true);
            lfrmx.setAlwaysOnTop(true);
            lfrmx.setAlwaysOnTop(false);
            if(x==3){
               lfrmx.reConnection(); 
            }
            
        }else if(parent.toLowerCase().equals("enteruniversityframe")){
            eufrmx.setEnabled(true);
            eufrmx.setAlwaysOnTop(true);
            eufrmx.setAlwaysOnTop(false);
            if(x==3){
                eufrmx.reConnection();
            }
        }else if(parent.toLowerCase().equals("viewtableframe")){
            vtfrmx.setEnabled(true);
            vtfrmx.setAlwaysOnTop(true);
            vtfrmx.setAlwaysOnTop(false);
            if(x==3){
                vtfrmx.reConnection();
            }
        }else if(parent.toLowerCase().equals("enteruniversityadminframe")){
            euafrmx.setEnabled(true);
            euafrmx.setAlwaysOnTop(true);
            euafrmx.setAlwaysOnTop(false);
            if(x==3){
                euafrmx.reConnection();
            }
        }else if(parent.toLowerCase().equals("edituniversityinfoframe")){
            euifrmx.setEnabled(true);
            euifrmx.setAlwaysOnTop(true);
            euifrmx.setAlwaysOnTop(false);
            if(x==3){
                euifrmx.reConnection();
            }
        }else if(parent.toLowerCase().equals("edituniversityadminframe")){
            eua2frmx.setEnabled(true);
            eua2frmx.setAlwaysOnTop(true);
            eua2frmx.setAlwaysOnTop(false);
            if(x==3){
                eua2frmx.reConnection();
            }
            
            
        }else if(parent.toLowerCase().equals("addschoolframe")){
            asfrmx.setEnabled(true);
            asfrmx.setAlwaysOnTop(true);
            asfrmx.setAlwaysOnTop(false);
            if(x==3){
                asfrmx.reConnection();
            }
        }else if(parent.toLowerCase().equals("editschoolframe")){
            esfrmx.setEnabled(true);
            esfrmx.setAlwaysOnTop(true);
            esfrmx.setAlwaysOnTop(false);
            if(x==3){
                esfrmx.reConnection();
            }
        }else if(parent.toLowerCase().equals("adddepartmentframe")){
            adfrmx.setEnabled(true);
            adfrmx.setAlwaysOnTop(true);
            adfrmx.setAlwaysOnTop(false);
            if(x==3){
                adfrmx.reConnection();
            }
        }else if(parent.toLowerCase().equals("editdepartmentframe")){
            edfrmx.setEnabled(true);
            edfrmx.setAlwaysOnTop(true);
            edfrmx.setAlwaysOnTop(false);
            if(x==3){
                edfrmx.reConnection();
            }
        }else if(parent.toLowerCase().equals("addprogramframe")){
            apfrmx.setEnabled(true);
            apfrmx.setAlwaysOnTop(true);
            apfrmx.setAlwaysOnTop(false);
            if(x==3){
                apfrmx.reConnection();
            }
        }else if(parent.toLowerCase().equals("editprogramframe")){
            epfrmx.setEnabled(true);
            epfrmx.setAlwaysOnTop(true);
            epfrmx.setAlwaysOnTop(false);
            if(x==3){
                epfrmx.reConnection();
            }
        }else if(parent.toLowerCase().equals("addsyllabusframe")){
            asbfrmx.setEnabled(true);
            asbfrmx.setAlwaysOnTop(true);
            asbfrmx.setAlwaysOnTop(false);
            if(x==3){
                asbfrmx.reConnection();
            }
        }else if(parent.toLowerCase().equals("editsyllabusframe")){
            esbfrmx.setEnabled(true);
            esbfrmx.setAlwaysOnTop(true);
            esbfrmx.setAlwaysOnTop(false);
            if(x==3){
                esbfrmx.reConnection();
            }
        }else if(parent.toLowerCase().equals("selectsyllabusframe")){
            ssbfrmx.setEnabled(true);
            ssbfrmx.setAlwaysOnTop(true);
            ssbfrmx.setAlwaysOnTop(false);
            if(x==3){
                ssbfrmx.reConnection();
            }
        }else if(parent.toLowerCase().equals("entersyllabusinfoframe")){
            esbifrmx.setEnabled(true);
            esbifrmx.setAlwaysOnTop(true);
            esbifrmx.setAlwaysOnTop(false);
            if(x==3){
                esbifrmx.reConnection();
            }
        }else if(parent.toLowerCase().equals("entercourseinfoframe")){
            ecrifrmx.setEnabled(true);
            ecrifrmx.setAlwaysOnTop(true);
            ecrifrmx.setAlwaysOnTop(false);
            if(x==3){
                ecrifrmx.reConnection();
            }
        }else if(parent.toLowerCase().equals("enterevaluationsetframe")){
            eevsfrmx.setEnabled(true);
            eevsfrmx.setAlwaysOnTop(true);
            eevsfrmx.setAlwaysOnTop(false);
            if(x==3){
                eevsfrmx.reConnection();
            }
        }
        /*if(parent.toLowerCase().equals("changepassframe")){
            cpfrmx.setEnabled(true);
            cpfrmx.setAlwaysOnTop(true);
            cpfrmx.setAlwaysOnTop(false);
            if(x==5){
                cpfrmx.dispose();
            }
            
        }else if(parent.toLowerCase().equals("addchangeemployee")){
            acefrmx.setEnabled(true);      
        }else if(parent.toLowerCase().equals("addchangestudent")){
            acsfrmx.setEnabled(true);      
        }else if(parent.toLowerCase().equals("addcourseframe")){
            acfrmx.setEnabled(true);     
            acfrmx.setAlwaysOnTop(true);
            acfrmx.setAlwaysOnTop(false);
        }else if(parent.toLowerCase().equals("connectcourseframe")){
            ccfrmx.setEnabled(true);     
            ccfrmx.setAlwaysOnTop(true);
            ccfrmx.setAlwaysOnTop(false);
        }*/
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(AlertFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlertFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlertFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlertFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlertFrame(1,"default!","default!","default!").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables
}

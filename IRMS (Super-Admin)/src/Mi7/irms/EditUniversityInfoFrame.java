/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Mi7.irms;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author himal
 */
public class EditUniversityInfoFrame extends javax.swing.JFrame {

    /**
     * Creates new form EnterUniversity
     */
    Connection connect;
    Statement stmt;
    String existing_id="";
    
    void reConnection(){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
    }
    void alert2bGeneration(int c,String msg1,String msg2,String msg3,String btext1,String btext2,String parent){
        
        
        this.setEnabled(false);
        AlertFrame2B a2frm = new AlertFrame2B(c,msg1,msg2,msg3,btext1,btext2);
        a2frm.passEditUniversityInfoFrame(this, parent);
        a2frm.setLocationRelativeTo(null);
        a2frm.setAlwaysOnTop(true);
        a2frm.setVisible(true);         
        
    }
    
    void alertGeneration(int c,String msg1,String msg2,String msg3){
        
        
        this.setEnabled(false);
        AlertFrame afrm = new AlertFrame(c,msg1,msg2,msg3);
        afrm.passEditUniversityInfoFrame(this,"edituniversityinfoframe");
        
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        afrm.setLocation(x+w/4, y+h/4);
        
        afrm.setAlwaysOnTop(true);
        afrm.setVisible(true);
    }
    
    void deleteUniversity(String delete_id){
        PreparedStatement ps;
        try {
            
            ps = connect.prepareStatement("SELECT * FROM `t01_university` WHERE university_id = ?;");
            ps.setString(1, delete_id);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                
                alertGeneration(6,"Error in Data Entry!!","University ID doesn't exist","Try Again!");
                universityID.setText("");
                universityName.setText("");
                universityAcronym.setText("");
                existingUniversityID.setText("");
                universityID.setEnabled(false);
                universityName.setEnabled(false);
                universityAcronym.setEnabled(false);
                updateButton.setEnabled(false);
                deleteButton.setEnabled(false);
                return;
            }
            
            ps = connect.prepareStatement("DELETE FROM `t01_university` WHERE `t01_university`.`university_id` = ?;");
            ps.setString(1, delete_id);
            ps.execute();
            
            
            alertGeneration(5,"University Delete Successful!!","","");
            universityID.setText("");
            universityName.setText("");
            universityAcronym.setText("");
            existingUniversityID.setText("");
            universityID.setEnabled(false);
            universityName.setEnabled(false);
            universityAcronym.setEnabled(false);
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
            
        } catch (SQLException ex) {
            //Logger.getLogger(EnterUniversityFrame.class.getName()).log(Level.SEVERE, null, ex);
            int code = ex.getErrorCode();
            if(code == 0){
                String msg = "Error Code: "+code;
                alertGeneration(3,"Error in Server Connection!!",msg,"Try Again!");
                System.out.println(ex);
            }else{
                String msg = "Error Code: "+code;
                alertGeneration(6,"Error in Data Entry!!",msg,"Try Again!");
                System.out.println(ex);
            }
            System.out.println("ERROR!! :  "+code);
        }
    }
    
    public EditUniversityInfoFrame() {
        initComponents();
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            universityID.setEnabled(false);
            universityName.setEnabled(false);
            universityAcronym.setEnabled(false);
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            alert2bGeneration(2,"Server Connection Failed!!",msg,"Please,Retry Connection or Quit!","Retry","Quit","EditUniversityInfoFrame");
            System.out.println(e);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        viewTableButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        existingUniversityID = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        universityID = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        universityName = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        universityAcronym = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        updateButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit University");
        setPreferredSize(new java.awt.Dimension(533, 600));
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(0, 80));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 5));

        jPanel9.setPreferredSize(new java.awt.Dimension(90, 10));
        jPanel1.add(jPanel9);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit_university_64.png"))); // NOI18N
        jLabel1.setText("EDIT UNIVERSITY INFO.");
        jPanel1.add(jLabel1);

        viewTableButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        viewTableButton.setText("View Table");
        viewTableButton.setPreferredSize(new java.awt.Dimension(100, 30));
        viewTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewTableButtonActionPerformed(evt);
            }
        });
        jPanel1.add(viewTableButton);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.GridLayout(6, 1));

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("Existing University ID : ");
        jPanel3.add(jLabel2);

        existingUniversityID.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        existingUniversityID.setPreferredSize(new java.awt.Dimension(200, 30));
        existingUniversityID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                existingUniversityIDKeyPressed(evt);
            }
        });
        jPanel3.add(existingUniversityID);

        searchButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        searchButton.setText("Search Info.");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        jPanel3.add(searchButton);

        deleteButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        deleteButton.setText("Delete University");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jPanel3.add(deleteButton);

        jPanel2.add(jPanel3);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("***Change the Information below if you want to update");
        jLabel6.setToolTipText("");
        jPanel8.add(jLabel6);

        jSeparator1.setForeground(new java.awt.Color(0, 51, 153));
        jSeparator1.setPreferredSize(new java.awt.Dimension(500, 10));
        jPanel8.add(jSeparator1);

        jPanel2.add(jPanel8);

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setText("University ID             : ");
        jLabel4.setToolTipText("");
        jPanel7.add(jLabel4);

        universityID.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        universityID.setPreferredSize(new java.awt.Dimension(200, 30));
        universityID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                universityIDActionPerformed(evt);
            }
        });
        jPanel7.add(universityID);

        jPanel2.add(jPanel7);

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("University Name      : ");
        jLabel3.setToolTipText("");
        jPanel4.add(jLabel3);

        universityName.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        universityName.setPreferredSize(new java.awt.Dimension(200, 30));
        universityName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                universityNameActionPerformed(evt);
            }
        });
        jPanel4.add(universityName);

        jPanel2.add(jPanel4);

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel5.setText("University Acronym  : ");
        jLabel5.setToolTipText("");
        jPanel5.add(jLabel5);

        universityAcronym.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        universityAcronym.setPreferredSize(new java.awt.Dimension(200, 30));
        universityAcronym.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                universityAcronymActionPerformed(evt);
            }
        });
        jPanel5.add(universityAcronym);

        jPanel2.add(jPanel5);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 100, 10));

        updateButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        updateButton.setText("UPDATE");
        updateButton.setPreferredSize(new java.awt.Dimension(150, 30));
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        jPanel6.add(updateButton);

        jPanel2.add(jPanel6);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void universityNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_universityNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_universityNameActionPerformed

    private void universityAcronymActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_universityAcronymActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_universityAcronymActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        //ADD Button
        String uni_id = universityID.getText().trim();
        String uni_name = universityName.getText().trim();
        String uni_accro = universityAcronym.getText().trim();
        String past_id = existingUniversityID.getText().trim();
        if(uni_id.isEmpty() || uni_name.isEmpty() || uni_accro.isEmpty() || past_id.isEmpty()){
            alertGeneration(6,"Error in Data Entry!!","Data Field is Empty!","Try Again!");
            return;
        }
        PreparedStatement ps;
        try {
            ps = connect.prepareStatement("SELECT * FROM `t01_university` WHERE university_id = ?;");
            ps.setString(1, past_id);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                
                alertGeneration(6,"Error in Data Entry!!","University ID doesn't exist","Try Again!");
                universityID.setText("");
                universityName.setText("");
                universityAcronym.setText("");
                existingUniversityID.setText("");
                universityID.setEnabled(false);
                universityName.setEnabled(false);
                universityAcronym.setEnabled(false);
                updateButton.setEnabled(false);
                deleteButton.setEnabled(false);
                return;
            }
            ps = connect.prepareStatement("UPDATE `t01_university` SET `university_id` = ?, `university_name` = ?, `acronym` = ? WHERE `t01_university`.`university_id` = ?;");
            ps.setString(1, uni_id);
            ps.setString(2, uni_name);
            ps.setString(3, uni_accro);
            ps.setString(4, past_id);
            ps.execute();
            
            
            alertGeneration(5,"University Information Update Successful!!","","");
            universityID.setText("");
            universityName.setText("");
            universityAcronym.setText("");
            existingUniversityID.setText("");
            universityID.setEnabled(false);
            universityName.setEnabled(false);
            universityAcronym.setEnabled(false);
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
            
        } catch (SQLException ex) {
            //Logger.getLogger(EnterUniversityFrame.class.getName()).log(Level.SEVERE, null, ex);
            int code = ex.getErrorCode();
            if(code == 0){
                String msg = "Error Code: "+code;
                alertGeneration(3,"Error in Server Connection!!",msg,"Try Again!");
                System.out.println(ex);
            }else{
                String msg = "Error Code: "+code;
                alertGeneration(6,"Error in Data Entry!!",msg,"Try Again!");
                System.out.println(ex);
            }
            System.out.println("ERROR!! :  "+code);
        }
        
    }//GEN-LAST:event_updateButtonActionPerformed

    private void viewTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewTableButtonActionPerformed
        // TODO add your handling code here:
        String [] tables = new String [] {"t01_university"};
        ViewTableFrame vtfrm = new ViewTableFrame(tables);
        vtfrm.setLocationRelativeTo(null);     //opens the frame in the middle of the screen
        vtfrm.setVisible(true);
    }//GEN-LAST:event_viewTableButtonActionPerformed

    private void universityIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_universityIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_universityIDActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        PreparedStatement ps;
        existing_id = existingUniversityID.getText().trim();
        if(existing_id.isEmpty()){
            alertGeneration(6,"Error in Data Entry!!","Data Field is Empty!","Try Again!");
            return;
        }
        try {
            ps = connect.prepareStatement("SELECT university_id,university_name,acronym FROM `t01_university` WHERE university_id = ? AND university_id!=\"UV-0000\"");
            ps.setString(1, existing_id);
            ResultSet rs = ps.executeQuery();
            boolean notfound = true;
            while(rs.next()){
                notfound = false;
                universityID.setText(rs.getString(1));
                universityID.setEnabled(true);
                universityName.setText(rs.getString(2));
                universityName.setEnabled(true);
                universityAcronym.setText(rs.getString(3));
                universityAcronym.setEnabled(true);
                updateButton.setEnabled(true);
                deleteButton.setEnabled(true);
            }
            if(notfound){
                alertGeneration(7,"University ID Not Found!!","Try different ID!!","Search Again!");
                return;
            }
        } catch (SQLException ex) {
            int code = ex.getErrorCode();
            
            String msg = "Error Code: "+code;
            alertGeneration(3,"Error in Server Connection!!",msg,"Try Again!");
            System.out.println(ex);
            
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        existing_id = existingUniversityID.getText().trim();
        String msg = "University ID: "+existing_id+" will be Deleted!!";
        alert2bGeneration(3,"WARNING!!!!!",msg,"Press Proceed to Delete","Proceed","Cancel","EditUniversityInfoFrame");

    }//GEN-LAST:event_deleteButtonActionPerformed

    private void existingUniversityIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_existingUniversityIDKeyPressed
        // TODO add your handling code here:
        universityID.setText("");
        universityName.setText("");
        universityAcronym.setText("");
            
        universityID.setEnabled(false);
        universityName.setEnabled(false);
        universityAcronym.setEnabled(false);
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }//GEN-LAST:event_existingUniversityIDKeyPressed

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
            java.util.logging.Logger.getLogger(EditUniversityInfoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditUniversityInfoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditUniversityInfoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditUniversityInfoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditUniversityInfoFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField existingUniversityID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField universityAcronym;
    private javax.swing.JTextField universityID;
    private javax.swing.JTextField universityName;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton viewTableButton;
    // End of variables declaration//GEN-END:variables
}

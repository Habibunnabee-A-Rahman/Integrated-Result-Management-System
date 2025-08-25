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
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author himal
 */
public class EditDepartmentFrame extends javax.swing.JFrame {

    /**
     * Creates new form EnterUniversity
     */
    Connection connect;
    Statement stmt;
    String existing_id="";
    String T01_id = "";
    String [] T03_id = new String [100];
    String existing_T03_id = "";
    
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
        a2frm.passEditDepartmentFrame(this, parent);
        a2frm.setLocationRelativeTo(null);
        a2frm.setAlwaysOnTop(true);
        a2frm.setVisible(true);         
        
    }
    
    void alertGeneration(int c,String msg1,String msg2,String msg3){
        
        
        this.setEnabled(false);
        AlertFrame afrm = new AlertFrame(c,msg1,msg2,msg3);
        afrm.passEditDepartmentFrame(this,"editdepartmentframe");
        
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        afrm.setLocation(x+w/4, y+h/4);
        
        afrm.setAlwaysOnTop(true);
        afrm.setVisible(true);
    }
    
    void deleteDepartment(String delete_id){
        PreparedStatement ps;
        try {
            
            ps = connect.prepareStatement("SELECT * FROM `t04_department` WHERE department_id = ? AND T03_id_fk = ?;");
            ps.setString(1, delete_id);
            ps.setString(2, existing_T03_id);
            
            ResultSet rs = ps.executeQuery();
            //rs.next();
            if(!rs.next()){
                
                alertGeneration(6,"Error in Data Entry!!","Department ID doesn't exist","Try Again!");
                DepartmentlID.setText("");
                DepartmentName.setText("");
                newSchoolComboBox.setSelectedIndex(0);
                existingDepartmentID.setText("");
                DepartmentlID.setEnabled(false);
                DepartmentName.setEnabled(false);
                updateButton.setEnabled(false);
                deleteButton.setEnabled(false);
                newSchoolComboBox.setEnabled(false);
                return;
            }
            
            ps = connect.prepareStatement("DELETE FROM `t04_department` WHERE `t04_department`.`department_id` = ? AND `t04_department`.`T03_id_fk` = ?;");
            ps.setString(1, delete_id);
            ps.setString(2, existing_T03_id);
            ps.execute();
            
            
            alertGeneration(5,"Department Delete Successful!!","","");
            DepartmentlID.setText("");
            DepartmentName.setText("");
            existingDepartmentID.setText("");
            newSchoolComboBox.setSelectedIndex(0);
            DepartmentlID.setEnabled(false);
            DepartmentName.setEnabled(false);
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
            newSchoolComboBox.setEnabled(false);
            
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
    
    public EditDepartmentFrame(String T01_id) {
        initComponents();
        this.T01_id = T01_id;
        int combo_len = 0;
        try{
            PreparedStatement ps;
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            
            ps = connect.prepareStatement("SELECT COUNT(*) FROM `t03_school` WHERE T01_id_fk = ?;");
            ps.setString(1, T01_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                combo_len = rs.getInt(1);
            }
            
            String [] temp = new String [combo_len];
            ps = connect.prepareStatement("SELECT school_id,school_name,T03_id FROM `t03_school` WHERE T01_id_fk = ?;");
            ps.setString(1, T01_id);
            rs = ps.executeQuery();
            rs.next();
            
            for(int i=0;i<combo_len;i++,rs.next()){
                temp [i] = rs.getString(1) + "--" + rs.getString(2);
                T03_id [i]=rs.getString(3);
            }
            schoolComboBox.setModel(new DefaultComboBoxModel(temp));
            newSchoolComboBox.setModel(new DefaultComboBoxModel(temp));
            
            newSchoolComboBox.setEnabled(false);
            DepartmentlID.setEnabled(false);
            DepartmentName.setEnabled(false);
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            alert2bGeneration(2,"Server Connection Failed!!",msg,"Please,Retry Connection or Quit!","Retry","Quit","EditDepartmentFrame");
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
        jLabel5 = new javax.swing.JLabel();
        schoolComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        existingDepartmentID = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        newSchoolComboBox = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        DepartmentlID = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        DepartmentName = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        updateButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Department");
        setPreferredSize(new java.awt.Dimension(452, 600));

        jPanel1.setPreferredSize(new java.awt.Dimension(0, 80));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jPanel9.setPreferredSize(new java.awt.Dimension(50, 10));
        jPanel1.add(jPanel9);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/eraser2_64.png"))); // NOI18N
        jLabel1.setText("EDIT DEPARTMENT INFO.");
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

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel5.setText("School: ");
        jPanel3.add(jLabel5);

        schoolComboBox.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        schoolComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        schoolComboBox.setPreferredSize(new java.awt.Dimension(300, 30));
        schoolComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schoolComboBoxActionPerformed(evt);
            }
        });
        jPanel3.add(schoolComboBox);

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("          Existing Department ID : ");
        jPanel3.add(jLabel2);

        existingDepartmentID.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        existingDepartmentID.setPreferredSize(new java.awt.Dimension(100, 30));
        existingDepartmentID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                existingDepartmentIDKeyPressed(evt);
            }
        });
        jPanel3.add(existingDepartmentID);

        searchButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        searchButton.setText("Search Info.");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        jPanel3.add(searchButton);

        deleteButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        deleteButton.setText("Delete Department");
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

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel7.setText("New School:");
        jPanel5.add(jLabel7);

        newSchoolComboBox.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        newSchoolComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        newSchoolComboBox.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel5.add(newSchoolComboBox);

        jPanel2.add(jPanel5);

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setText("Department ID             : ");
        jLabel4.setToolTipText("");
        jPanel7.add(jLabel4);

        DepartmentlID.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        DepartmentlID.setPreferredSize(new java.awt.Dimension(200, 30));
        DepartmentlID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartmentlIDActionPerformed(evt);
            }
        });
        jPanel7.add(DepartmentlID);

        jPanel2.add(jPanel7);

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("Department Name      : ");
        jLabel3.setToolTipText("");
        jPanel4.add(jLabel3);

        DepartmentName.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        DepartmentName.setPreferredSize(new java.awt.Dimension(200, 30));
        DepartmentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartmentNameActionPerformed(evt);
            }
        });
        jPanel4.add(DepartmentName);

        jPanel2.add(jPanel4);

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

    private void DepartmentNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartmentNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DepartmentNameActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        //ADD Button
        String department_id = DepartmentlID.getText().trim();
        String department_name = DepartmentName.getText().trim();
        //String past_id = existingDepartmentID.getText().trim();
        String new_T03_id = T03_id[newSchoolComboBox.getSelectedIndex()];
        
        if(department_id.isEmpty() || department_name.isEmpty() || existing_id.isEmpty() ||
                new_T03_id.isEmpty() || existing_T03_id.isEmpty()){
            alertGeneration(6,"Error in Data Entry!!","Data Field is Empty!","Try Again!");
            return;
        }
        PreparedStatement ps;
        try {
            ps = connect.prepareStatement("SELECT * FROM `t04_department` WHERE department_id = ? AND T03_id_fk = ?;");
            ps.setString(1, existing_id);
            ps.setString(2,existing_T03_id);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                
                alertGeneration(6,"Error in Data Entry!!","Department ID doesn't exist","Try Again!");
                DepartmentlID.setText("");
                DepartmentName.setText("");
                existingDepartmentID.setText("");
                newSchoolComboBox.setSelectedIndex(0);
                DepartmentlID.setEnabled(false);
                DepartmentName.setEnabled(false);
                updateButton.setEnabled(false);
                deleteButton.setEnabled(false);
                newSchoolComboBox.setEnabled(false);
                return;
            }
            ps = connect.prepareStatement("UPDATE `t04_department` SET `department_id` = ?, `department_name` = ?, `T03_id_fk` = ? WHERE `t04_department`.`department_id` = ? "
                    + "AND `t04_department`.`T03_id_fk` = ?;");
            ps.setString(1, department_id);
            ps.setString(2, department_name);
            ps.setString(3, new_T03_id);
            ps.setString(4, existing_id);
            ps.setString(5, existing_T03_id);
            ps.execute();
            
            
            alertGeneration(5,"Department Information Update Successful!!","","");
            DepartmentlID.setText("");
            DepartmentName.setText("");
            existingDepartmentID.setText("");
            newSchoolComboBox.setSelectedItem(0);
            DepartmentlID.setEnabled(false);
            DepartmentName.setEnabled(false);
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
            newSchoolComboBox.setEnabled(false);
            
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
        String [] tables = new String [] {"t04_department"};
        ViewTableFrame vtfrm = new ViewTableFrame(tables);
        vtfrm.passT01_id(T01_id);
        vtfrm.setLocationRelativeTo(null);     //opens the frame in the middle of the screen
        vtfrm.setVisible(true);
    }//GEN-LAST:event_viewTableButtonActionPerformed

    private void DepartmentlIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartmentlIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DepartmentlIDActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        PreparedStatement ps;
        existing_id = existingDepartmentID.getText().trim();
        
        
        if(existing_id.isEmpty()){
            alertGeneration(6,"Error in Data Entry!!","Data Field is Empty!","Try Again!");
            return;
        }
        try {
            ps = connect.prepareStatement("SELECT department_id,department_name FROM `t04_department` WHERE department_id = ? AND T03_id_fk = ?;");
            ps.setString(1, existing_id);
            int temp_index = schoolComboBox.getSelectedIndex();
            ps.setString(2, T03_id[temp_index]);
            ResultSet rs = ps.executeQuery();
            boolean notfound = true;
            while(rs.next()){
                notfound = false;
                DepartmentlID.setText(rs.getString(1));
                DepartmentlID.setEnabled(true);
                DepartmentName.setText(rs.getString(2));
                DepartmentName.setEnabled(true);
                newSchoolComboBox.setSelectedIndex(temp_index);
                newSchoolComboBox.setEnabled(true);
                
                updateButton.setEnabled(true);
                deleteButton.setEnabled(true);
                existing_T03_id = T03_id[temp_index];
            }
            if(notfound){
                alertGeneration(7,"Department ID Not Found!!","Try different ID!!","Search Again!");
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
        existing_id = existingDepartmentID.getText().trim();
        String msg = "Department ID: "+existing_id+" will be Deleted!!";
        alert2bGeneration(3,"WARNING!!!!!",msg,"Press Proceed to Delete","Proceed","Cancel","EditDepartmentFrame");

    }//GEN-LAST:event_deleteButtonActionPerformed

    private void existingDepartmentIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_existingDepartmentIDKeyPressed
        // TODO add your handling code here:
        DepartmentlID.setText("");
        DepartmentName.setText("");
        newSchoolComboBox.setSelectedIndex(0);
            
        DepartmentlID.setEnabled(false);
        DepartmentName.setEnabled(false);
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
        newSchoolComboBox.setEnabled(false);
    }//GEN-LAST:event_existingDepartmentIDKeyPressed

    private void schoolComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schoolComboBoxActionPerformed
        // TODO add your handling code here:
        DepartmentlID.setText("");
        DepartmentName.setText("");
        newSchoolComboBox.setSelectedIndex(0);
            
        DepartmentlID.setEnabled(false);
        DepartmentName.setEnabled(false);
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
        newSchoolComboBox.setEnabled(false);
    }//GEN-LAST:event_schoolComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(EditDepartmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditDepartmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditDepartmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditDepartmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditDepartmentFrame("default").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DepartmentName;
    private javax.swing.JTextField DepartmentlID;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField existingDepartmentID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JComboBox<String> newSchoolComboBox;
    private javax.swing.JComboBox<String> schoolComboBox;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton viewTableButton;
    // End of variables declaration//GEN-END:variables
}

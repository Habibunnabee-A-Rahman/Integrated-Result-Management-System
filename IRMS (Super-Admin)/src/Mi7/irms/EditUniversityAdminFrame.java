/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Mi7.irms;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author himal
 */
public class EditUniversityAdminFrame extends javax.swing.JFrame {

    /**
     * Creates new form EnterUniversity
     */
    Connection connect;
    Statement stmt;
    String existing_id="";
    String [] universityList;
    
    void reConnection(){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
    }
    String encryptPass(String password){
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] passByte = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< passByte.length ;i++){ 
                sb.append(Integer.toString((passByte[i] & 0xff) + 0x100, 16).substring(1));         //Encrypting default password
            }  
            password = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        
        
        return password;
    }
    void alert2bGeneration(int c,String msg1,String msg2,String msg3,String btext1,String btext2,String parent){
        
        
        this.setEnabled(false);
        AlertFrame2B a2frm = new AlertFrame2B(c,msg1,msg2,msg3,btext1,btext2);
        a2frm.passEditUniversityAdminFrame(this, parent);
        a2frm.setLocationRelativeTo(null);
        a2frm.setAlwaysOnTop(true);
        a2frm.setVisible(true);         
        
    }
    
    void alertGeneration(int c,String msg1,String msg2,String msg3){
        
        
        this.setEnabled(false);
        AlertFrame afrm = new AlertFrame(c,msg1,msg2,msg3);
        afrm.passEditUniversityAdminFrame(this,"edituniversityadminframe");
        
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        afrm.setLocation(x+w/4, y+h/4);
        
        afrm.setAlwaysOnTop(true);
        afrm.setVisible(true);
    }
    
    void resetPassword(String password,String confirm_password){
        
        String admin_id = existingAdminID.getText().trim();
        if(password.isEmpty()||confirm_password.isEmpty() || admin_id.isEmpty()){
            alertGeneration(6,"Error in Data Entry!!","Data Field is Empty!","Try Again!");
            return;
        }
        
        if(!password.equals(confirm_password)){
            alertGeneration(2,"Error in Password!!","Password & Confirm Password Do Not Match!","Try Again!");
            return;
        }
        try{
            PreparedStatement ps;
            ps = connect.prepareStatement("UPDATE `admin` SET `password` = ? WHERE `admin`.`ID` = ?;");
            ps.setString(1, this.encryptPass(password));
            ps.setString(2, admin_id);
            ps.execute();
            alertGeneration(5,"Admin Password Update Successful!!","","");
            jPasswordField1.setText("");
            jPasswordField2.setText("");
            
        }catch (SQLException ex) {
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
            
            //System.out.println("ERROR!! :  "+code);
        }
        
        
    }
    
    
    
    
    
    void deleteAdmin(String delete_id){
        PreparedStatement ps;
        try {
            
            ps = connect.prepareStatement("SELECT * FROM `admin` WHERE ID = ?;");
            ps.setString(1, delete_id);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                
                alertGeneration(6,"Error in Data Entry!!","Admin ID doesn't exist","Try Again!");
                adminID.setText("");
                existingAdminID.setText("");
                
                adminID.setEnabled(false);
                universityComboBox.setEnabled(false);
                updateButton.setEnabled(false);
                deleteButton.setEnabled(false);
                resetPasswordButton.setEnabled(false);
                jPasswordField1.setEnabled(false);
                jPasswordField2.setEnabled(false);
                jPasswordField1.setText("");
                jPasswordField2.setText("");
                
                return;
            }
            
            ps = connect.prepareStatement("DELETE FROM `admin` WHERE `admin`.`ID` = ?;");
            ps.setString(1, delete_id);
            ps.execute();
            
            
            alertGeneration(5,"Admin Delete Successful!!","","");
            adminID.setText("");
            existingAdminID.setText("");
            
            
            adminID.setEnabled(false);
            universityComboBox.setEnabled(false);
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
            resetPasswordButton.setEnabled(false);
            jPasswordField1.setEnabled(false);
            jPasswordField2.setEnabled(false);
            jPasswordField1.setText("");
            jPasswordField2.setText("");
            
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
    
    public EditUniversityAdminFrame() {
        initComponents();
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            PreparedStatement ps = connect.prepareStatement("SELECT university_id, acronym FROM `t01_university` WHERE university_id != \"UV-0000\"", 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Allows scrolling in the ResultSet & Ensures the ResultSet is read-only
            ResultSet rs = ps.executeQuery();
            //ResultSetMetaData rsmd = rs.getMetaData();
            rs.last();
            int row_count = rs.getRow();
            this.universityList = new String [row_count];
            rs.first();
            for(int i=0;i<row_count;i++,rs.next()){
                universityList[i] = rs.getString(1)+" - "+rs.getString(2);
            }
            universityComboBox.setModel(new DefaultComboBoxModel(universityList));
            
            universityComboBox.setEnabled(false);
            adminID.setEnabled(false);
            resetPasswordButton.setEnabled(false);
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
            jPasswordField1.setEnabled(false);
            jPasswordField2.setEnabled(false);
            jPasswordField1.setText("");
            jPasswordField2.setText("");
        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            alert2bGeneration(2,"Server Connection Failed!!",msg,"Please,Retry Connection or Quit!","Retry","Quit","EditUniversityAdminFrame");
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
        jPanel12 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        viewTableButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        existingAdminID = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        universityComboBox = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        adminID = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        updateButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPanel16 = new javax.swing.JPanel();
        resetPasswordButton = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Admin");
        setPreferredSize(new java.awt.Dimension(533, 640));
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(0, 80));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 5));

        jPanel12.setPreferredSize(new java.awt.Dimension(90, 20));
        jPanel1.add(jPanel12);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit_admin_64.png"))); // NOI18N
        jLabel1.setText("EDIT UNIVERSITY ADMIN");
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

        jPanel2.setLayout(new java.awt.GridLayout(7, 1));

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("Existing Admin ID  : ");
        jPanel3.add(jLabel2);

        existingAdminID.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        existingAdminID.setPreferredSize(new java.awt.Dimension(200, 30));
        existingAdminID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                existingAdminIDActionPerformed(evt);
            }
        });
        existingAdminID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                existingAdminIDKeyPressed(evt);
            }
        });
        jPanel3.add(existingAdminID);

        searchButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        searchButton.setText("Search Info.");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        jPanel3.add(searchButton);

        deleteButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        deleteButton.setText("Delete Admin");
        deleteButton.setPreferredSize(new java.awt.Dimension(120, 23));
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

        universityComboBox.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        universityComboBox.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel7.add(universityComboBox);

        jPanel2.add(jPanel7);

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("Admin ID                   : ");
        jLabel3.setToolTipText("");
        jPanel4.add(jLabel3);

        adminID.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        adminID.setPreferredSize(new java.awt.Dimension(200, 30));
        adminID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminIDActionPerformed(evt);
            }
        });
        jPanel4.add(adminID);

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

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel10.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setText("Reset Password : ");
        jPanel10.add(jLabel5);

        jPanel18.setPreferredSize(new java.awt.Dimension(390, 5));
        jPanel10.add(jPanel18);

        jSeparator2.setForeground(new java.awt.Color(0, 51, 153));
        jSeparator2.setPreferredSize(new java.awt.Dimension(500, 10));
        jPanel10.add(jSeparator2);

        jPanel5.add(jPanel10, java.awt.BorderLayout.NORTH);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));
        jPanel9.add(jPanel17);

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel7.setText("New Password        :");
        jPanel9.add(jLabel7);

        jPasswordField1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPasswordField1.setPreferredSize(new java.awt.Dimension(180, 30));
        jPanel9.add(jPasswordField1);

        jCheckBox1.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        jCheckBox1.setText("Show");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel9.add(jCheckBox1);

        jPanel13.setPreferredSize(new java.awt.Dimension(150, 10));
        jPanel9.add(jPanel13);

        jPanel5.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel5);

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));
        jPanel11.add(jPanel15);

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel8.setText("Confirm Password  :");
        jPanel11.add(jLabel8);

        jPasswordField2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPasswordField2.setPreferredSize(new java.awt.Dimension(180, 30));
        jPanel11.add(jPasswordField2);
        jPanel11.add(jPanel16);

        resetPasswordButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        resetPasswordButton.setText("Reset Password");
        resetPasswordButton.setPreferredSize(new java.awt.Dimension(140, 30));
        resetPasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetPasswordButtonActionPerformed(evt);
            }
        });
        jPanel11.add(resetPasswordButton);
        jPanel11.add(jPanel14);

        jPanel2.add(jPanel11);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adminIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminIDActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        //ADD Button
        String admin_id = adminID.getText().trim();
        String [] uni_id_temp = universityList[universityComboBox.getSelectedIndex()].split(" ", 2);
        String uni_id = uni_id_temp[0];
        String past_id = existingAdminID.getText().trim();
        if(uni_id.isEmpty() || admin_id.isEmpty() || past_id.isEmpty()){
            alertGeneration(6,"Error in Data Entry!!","Data Field is Empty!","Try Again!");
            return;
        }
        PreparedStatement ps;
        try {
            
            ps = connect.prepareStatement("SELECT * FROM `admin` WHERE ID = ?;");
            ps.setString(1, past_id);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                
                alertGeneration(6,"Error in Data Entry!!","Admin ID doesn't exist","Try Again!");
                adminID.setText("");
                existingAdminID.setText("");
                
                adminID.setEnabled(false);
                universityComboBox.setEnabled(false);
                updateButton.setEnabled(false);
                deleteButton.setEnabled(false);
                resetPasswordButton.setEnabled(false);
                jPasswordField1.setEnabled(false);
                jPasswordField2.setEnabled(false);
                jPasswordField1.setText("");
                jPasswordField2.setText("");
                return;
            }
            
            ps = connect.prepareStatement("UPDATE `admin` SET `ID` = ?, `university_id` = ? WHERE `admin`.`ID` = ?;");
            ps.setString(1, admin_id);
            ps.setString(2, uni_id);
            ps.setString(3, past_id);
            
            ps.execute();
            
            
            alertGeneration(5,"Admin Information Update Successful!!","","");
            
            adminID.setText("");
            existingAdminID.setText("");
            adminID.setEnabled(false);
            universityComboBox.setEnabled(false);
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
            resetPasswordButton.setEnabled(false);
            jPasswordField1.setEnabled(false);
            jPasswordField2.setEnabled(false);
            jPasswordField1.setText("");
            jPasswordField2.setText("");
            
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
        String [] tables = new String [] {"admin"};
        ViewTableFrame vtfrm = new ViewTableFrame(tables);
        vtfrm.setLocationRelativeTo(null);     //opens the frame in the middle of the screen
        vtfrm.setVisible(true);
    }//GEN-LAST:event_viewTableButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        PreparedStatement ps;
        existing_id = existingAdminID.getText().trim();
        if(existing_id.isEmpty()){
            alertGeneration(6,"Error in Data Entry!!","Data Field is Empty!","Try Again!");
            return;
        }
        try {
            ps = connect.prepareStatement("SELECT ID,university_id FROM `admin` WHERE ID = ? AND university_id!=\"UV-0000\"");
            ps.setString(1, existing_id);
            ResultSet rs = ps.executeQuery();
            boolean notfound = true;
            while(rs.next()){
                notfound = false;
 
                String temp_uni_id = rs.getString(2);
                for(int i = 0;i<universityList.length;i++){
                    String [] temp = universityList[i].split(" ", 2);
                    if(temp_uni_id.equals(temp[0])){
                        universityComboBox.setSelectedIndex(i);
                        universityComboBox.setEnabled(true);
                        break;
                    }
                }
                adminID.setText(rs.getString(1));
                adminID.setEnabled(true);
                
                resetPasswordButton.setEnabled(true);
                updateButton.setEnabled(true);
                deleteButton.setEnabled(true);
                jPasswordField1.setEnabled(true);
                jPasswordField2.setEnabled(true);
                
            }
            if(notfound){
                alertGeneration(7,"Admin ID Not Found!!","Try different ID!!","Search Again!");
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
        //String checkTable = "";
        
        
        
        
        existing_id = existingAdminID.getText().trim();
        String msg = "Admin ID: "+existing_id+" will be Deleted!!";
        alert2bGeneration(3,"WARNING!!!!!",msg,"Press Proceed to Delete","Proceed","Cancel","EditUniversityAdminFrame");

    }//GEN-LAST:event_deleteButtonActionPerformed

    private void existingAdminIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_existingAdminIDKeyPressed
        // TODO add your handling code here:
        adminID.setText("");
        
        adminID.setEnabled(false);
        universityComboBox.setEnabled(false);
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
        resetPasswordButton.setEnabled(false);
        jPasswordField1.setEnabled(false);
        jPasswordField2.setEnabled(false);
        jPasswordField1.setText("");
        jPasswordField2.setText("");
    }//GEN-LAST:event_existingAdminIDKeyPressed

    private void resetPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetPasswordButtonActionPerformed
        // TODO add your handling code here:
        String password = jPasswordField1.getText();
        String confirm_password = jPasswordField2.getText();
        this.resetPassword(password, confirm_password);
        
    }//GEN-LAST:event_resetPasswordButtonActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.isSelected()){
            jPasswordField1.setEchoChar((char) 0);
        }else{
            jPasswordField1.setEchoChar('\u2022');
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void existingAdminIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_existingAdminIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_existingAdminIDActionPerformed

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
            java.util.logging.Logger.getLogger(EditUniversityAdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditUniversityAdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditUniversityAdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditUniversityAdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new EditUniversityAdminFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adminID;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField existingAdminID;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton resetPasswordButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox<String> universityComboBox;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton viewTableButton;
    // End of variables declaration//GEN-END:variables
}

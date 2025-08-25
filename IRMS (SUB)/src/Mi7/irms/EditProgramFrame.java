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
public class EditProgramFrame extends javax.swing.JFrame {

    /**
     * Creates new form EnterUniversity
     */
    Connection connect;
    Statement stmt;
    String existing_id="";
    String T01_id = "";
    String [] T03_id;
    String existing_T04_id = "";
    String [] newT04_id;
    
    String [] T04_id;
    
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
        a2frm.passEditProgramFrame(this, parent);
        a2frm.setLocationRelativeTo(null);
        a2frm.setAlwaysOnTop(true);
        a2frm.setVisible(true);         
        
    }
    
    void alertGeneration(int c,String msg1,String msg2,String msg3){
        
        
        this.setEnabled(false);
        AlertFrame afrm = new AlertFrame(c,msg1,msg2,msg3);
        afrm.passEditProgramFrame(this,"editprogramframe");
        
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
            
            ps = connect.prepareStatement("SELECT * FROM `t05_program` WHERE program_id = ? AND T04_id_fk = ?;");
            ps.setString(1, delete_id);
            ps.setString(2, existing_T04_id);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()){
                
                alertGeneration(6,"Error in Data Entry!!","Program ID doesn't exist","Try Again!");
                programID.setText("");
                programName.setText("");
                newSchoolComboBox.setSelectedIndex(0);
                newDepartmentComboBox.setSelectedIndex(0);
                existingProgramID.setText("");
                programID.setEnabled(false);
                programName.setEnabled(false);
                updateButton.setEnabled(false);
                deleteButton.setEnabled(false);
                newSchoolComboBox.setEnabled(false);
                newDepartmentComboBox.setEnabled(false);
                return;
            }
            
            ps = connect.prepareStatement("DELETE FROM `t05_program` WHERE `t05_program`.`program_id` = ? AND `t05_program`.`T04_id_fk` = ?;");
            ps.setString(1, delete_id);
            ps.setString(2, existing_T04_id);
            ps.execute();
            
            
            alertGeneration(5,"Program Delete Successful!!","","");
            programID.setText("");
            programName.setText("");
            existingProgramID.setText("");
            newSchoolComboBox.setSelectedIndex(0);
            newDepartmentComboBox.setSelectedIndex(0);
            programID.setEnabled(false);
            programName.setEnabled(false);
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
            newSchoolComboBox.setEnabled(false);
            newDepartmentComboBox.setEnabled(false);
            
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
    
    public EditProgramFrame(String T01_id) {
        initComponents();
        this.T01_id = T01_id;
        int combo_len = 0;
        schoolComboBox.setEnabled(false);
        departmentComboBox.setEnabled(false);
        existingProgramID.setEnabled(false);
        existingProgramID.setText("");
        searchButton.setEnabled(false);
        newSchoolComboBox.setEnabled(false);
        newDepartmentComboBox.setEnabled(false);
        programID.setEnabled(false);
        programID.setText("");
        programName.setEnabled(false);
        programName.setText("");
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
        try{
            PreparedStatement ps;
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            
            ps = connect.prepareStatement("SELECT COUNT(*) FROM `t03_school` WHERE T01_id_fk = ?;");
            ps.setString(1, T01_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                combo_len = rs.getInt(1);
            }
            if(combo_len == 0){
                String [] tempx = {"None!"};
                schoolComboBox.setModel(new DefaultComboBoxModel(tempx));
                return;
            }
            
            String [] temp = new String [combo_len];
            T03_id = new String [combo_len];
            
            ps = connect.prepareStatement("SELECT school_id,school_name,T03_id FROM `t03_school` WHERE T01_id_fk = ?;");
            ps.setString(1, T01_id);
            rs = ps.executeQuery();
            rs.next();
            
            for(int i=0;i<combo_len;i++,rs.next()){
                temp [i] = rs.getString(1) + "--" + rs.getString(2);
                T03_id [i]=rs.getString(3);
            }
            schoolComboBox.setModel(new DefaultComboBoxModel(temp));
            schoolComboBox.setEnabled(true);
            schoolComboBox.setSelectedIndex(0);
            newSchoolComboBox.setModel(new DefaultComboBoxModel(temp));
            
            
        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            alert2bGeneration(2,"Server Connection Failed!!",msg,"Please,Retry Connection or Quit!","Retry","Quit","EditProgramFrame");
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
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        departmentComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        existingProgramID = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        newSchoolComboBox = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        newDepartmentComboBox = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        programID = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        programName = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        updateButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Program");
        setPreferredSize(new java.awt.Dimension(529, 606));

        jPanel1.setPreferredSize(new java.awt.Dimension(0, 80));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jPanel9.setPreferredSize(new java.awt.Dimension(50, 10));
        jPanel1.add(jPanel9);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/eraser3_64.png"))); // NOI18N
        jLabel1.setText("EDIT PROGRAM INFO.");
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
        schoolComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        schoolComboBox.setPreferredSize(new java.awt.Dimension(150, 30));
        schoolComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schoolComboBoxActionPerformed(evt);
            }
        });
        jPanel3.add(schoolComboBox);
        jPanel3.add(jPanel11);

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel8.setText("Department:");
        jPanel3.add(jLabel8);

        departmentComboBox.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        departmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        departmentComboBox.setPreferredSize(new java.awt.Dimension(150, 30));
        departmentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentComboBoxActionPerformed(evt);
            }
        });
        jPanel3.add(departmentComboBox);

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("          Existing Program ID : ");
        jPanel3.add(jLabel2);

        existingProgramID.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        existingProgramID.setPreferredSize(new java.awt.Dimension(100, 30));
        existingProgramID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                existingProgramIDActionPerformed(evt);
            }
        });
        existingProgramID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                existingProgramIDKeyPressed(evt);
            }
        });
        jPanel3.add(existingProgramID);

        searchButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        searchButton.setText("Search Info.");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        jPanel3.add(searchButton);

        deleteButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        deleteButton.setText("Delete Program");
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
        newSchoolComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        newSchoolComboBox.setPreferredSize(new java.awt.Dimension(130, 30));
        newSchoolComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSchoolComboBoxActionPerformed(evt);
            }
        });
        jPanel5.add(newSchoolComboBox);

        jPanel10.setPreferredSize(new java.awt.Dimension(20, 10));
        jPanel5.add(jPanel10);

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel9.setText("New Department");
        jPanel5.add(jLabel9);

        newDepartmentComboBox.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        newDepartmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        newDepartmentComboBox.setPreferredSize(new java.awt.Dimension(130, 30));
        newDepartmentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newDepartmentComboBoxActionPerformed(evt);
            }
        });
        jPanel5.add(newDepartmentComboBox);

        jPanel2.add(jPanel5);

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setText("Program ID             : ");
        jLabel4.setToolTipText("");
        jPanel7.add(jLabel4);

        programID.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        programID.setPreferredSize(new java.awt.Dimension(200, 30));
        programID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programIDActionPerformed(evt);
            }
        });
        jPanel7.add(programID);

        jPanel2.add(jPanel7);

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("Program Name      : ");
        jLabel3.setToolTipText("");
        jPanel4.add(jLabel3);

        programName.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        programName.setPreferredSize(new java.awt.Dimension(200, 30));
        programName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programNameActionPerformed(evt);
            }
        });
        jPanel4.add(programName);

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

    private void programNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_programNameActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        //ADD Button
        String check_temp = (String) newDepartmentComboBox.getSelectedItem();
        if(check_temp.equals("None!")){
            alertGeneration(6,"Error in Data Entry!!","Data Field is Empty!","Try Again!");
            return;
        }
        
        String program_id = programID.getText().trim();
        String program_name = programName.getText().trim();
        //String past_id = existingDepartmentID.getText().trim();
        String set_T04_id = newT04_id[newDepartmentComboBox.getSelectedIndex()];
        
        if(program_id.isEmpty() || program_name.isEmpty() || existing_id.isEmpty() ||
                set_T04_id.isEmpty() || existing_T04_id.isEmpty() || set_T04_id.equals("None!")){
            alertGeneration(6,"Error in Data Entry!!","Data Field is Empty!","Try Again!");
            return;
        }
        PreparedStatement ps;
        try {
            ps = connect.prepareStatement("SELECT * FROM `t05_program` WHERE program_id = ? AND T04_id_fk = ?;");
            ps.setString(1, existing_id);
            ps.setString(2,existing_T04_id);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                
                alertGeneration(6,"Error in Data Entry!!","Program ID doesn't exist","Try Again!");
                programID.setText("");
                programName.setText("");
                existingProgramID.setText("");
                newSchoolComboBox.setSelectedIndex(0);
                newDepartmentComboBox.setSelectedIndex(0);
                programID.setEnabled(false);
                programName.setEnabled(false);
                updateButton.setEnabled(false);
                deleteButton.setEnabled(false);
                newSchoolComboBox.setEnabled(false);
                newDepartmentComboBox.setEnabled(false);
                return;
            }
            ps = connect.prepareStatement("UPDATE `t05_program` SET `program_id` = ?, `program_name` = ?, `T04_id_fk` = ? WHERE `t05_program`.`program_id` = ? "
                    + "AND `t05_program`.`T04_id_fk` = ?;");
            ps.setString(1, program_id);
            ps.setString(2, program_name);
            ps.setString(3, set_T04_id);
            ps.setString(4, existing_id);
            ps.setString(5, existing_T04_id);
            ps.execute();
            
            
            alertGeneration(5,"Department Information Update Successful!!","","");
            programID.setText("");
            programName.setText("");
            existingProgramID.setText("");
            newSchoolComboBox.setSelectedIndex(0);
            newDepartmentComboBox.setSelectedIndex(0);
            programID.setEnabled(false);
            programName.setEnabled(false);
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
            newSchoolComboBox.setEnabled(false);
            newDepartmentComboBox.setEnabled(false);
            schoolComboBox.setSelectedIndex(0);
            
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
        String [] tables = new String [] {"t05_program"};
        ViewTableFrame vtfrm = new ViewTableFrame(tables);
        vtfrm.passT01_id(T01_id);
        vtfrm.setLocationRelativeTo(null);     //opens the frame in the middle of the screen
        vtfrm.setVisible(true);
    }//GEN-LAST:event_viewTableButtonActionPerformed

    private void programIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_programIDActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        String temp1 = (String) departmentComboBox.getSelectedItem();
        if(temp1.equals("None!")){
            return;
        }
        
        PreparedStatement ps;
        existing_id = existingProgramID.getText().trim();
        
        
        if(existing_id.isEmpty()){
            alertGeneration(6,"Error in Data Entry!!","Data Field is Empty!","Try Again!");
            return;
        }
        try {
            ps = connect.prepareStatement("SELECT program_id,program_name FROM `t05_program` WHERE program_id = ? AND T04_id_fk = ?;");
            ps.setString(1, existing_id);
            int temp_index = departmentComboBox.getSelectedIndex();
            ps.setString(2, T04_id[temp_index]);
            ResultSet rs = ps.executeQuery();
            boolean notfound = true;
            while(rs.next()){
                notfound = false;
                programID.setText(rs.getString(1));
                programID.setEnabled(true);
                programName.setText(rs.getString(2));
                programName.setEnabled(true);
                newSchoolComboBox.setSelectedIndex(schoolComboBox.getSelectedIndex());
                newSchoolComboBox.setEnabled(true);
                newDepartmentComboBox.setSelectedIndex(temp_index);
                newDepartmentComboBox.setEnabled(true);
                updateButton.setEnabled(true);
                deleteButton.setEnabled(true);
                existing_T04_id = T04_id[temp_index];
            }
            if(notfound){
                alertGeneration(7,"Program ID Not Found!!","Try different ID!!","Search Again!");
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
        existing_id = existingProgramID.getText().trim();
        String msg = "Program ID: "+existing_id+" will be Deleted!!";
        alert2bGeneration(3,"WARNING!!!!!",msg,"Press Proceed to Delete","Proceed","Cancel","EditProgramFrame");

    }//GEN-LAST:event_deleteButtonActionPerformed

    private void existingProgramIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_existingProgramIDKeyPressed
        // TODO add your handling code here:
        newSchoolComboBox.setEnabled(false);
        newDepartmentComboBox.setEnabled(false);
       
        programID.setEnabled(false);
        programName.setEnabled(false);
        programID.setText("");
        programName.setText("");
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
        
    }//GEN-LAST:event_existingProgramIDKeyPressed

    private void schoolComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schoolComboBoxActionPerformed
        // TODO add your handling code here:
        departmentComboBox.setEnabled(false);
        existingProgramID.setEnabled(false);
        existingProgramID.setText("");
        searchButton.setEnabled(false);
        newSchoolComboBox.setEnabled(false);
        newDepartmentComboBox.setEnabled(false);
        programID.setEnabled(false);
        programID.setText("");
        programName.setEnabled(false);
        programName.setText("");
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
        
        String temp1 = (String) schoolComboBox.getSelectedItem();
        if(temp1.equals("None!")){
            return;
        }
        
        int index = schoolComboBox.getSelectedIndex();
        int combo_len = 0;
        
        try {
            PreparedStatement ps = connect.prepareStatement("SELECT COUNT(*) FROM `t04_department` WHERE T03_id_fk = ?;");
            ps.setString(1, T03_id[index]);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                combo_len = rs.getInt(1);
            }
            if(combo_len==0){
                String [] temp2 = {"None!"};
                departmentComboBox.setModel(new DefaultComboBoxModel(temp2));
                return;
            }
            T04_id = new String [combo_len];
            String [] temp = new String [combo_len];
            
            ps = connect.prepareStatement("SELECT department_id,department_name,T04_id FROM `t04_department` WHERE T03_id_fk = ?;");
            ps.setString(1, T03_id[index]);
            rs = ps.executeQuery();
            
            rs.next();
            for(int i=0;i<combo_len;i++,rs.next()){
                temp[i] = rs.getString(1)+"--"+rs.getString(2);
                T04_id[i]=rs.getString(3);
            }
            departmentComboBox.setModel(new DefaultComboBoxModel(temp));
            departmentComboBox.setEnabled(true);
            departmentComboBox.setSelectedIndex(0);
            
            
        } catch (SQLException e) {
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            alert2bGeneration(2,"Server Connection Failed!!",msg,"Please,Retry Connection or Quit!","Retry","Quit","EditProgramFrame");
            //System.out.println(e);
            
        }
    }//GEN-LAST:event_schoolComboBoxActionPerformed

    private void departmentComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentComboBoxActionPerformed
        // TODO add your handling code here:
        String temp = (String) departmentComboBox.getSelectedItem();
        if(temp.equals("None!")){
            return;
        }
        existingProgramID.setEnabled(true);
        existingProgramID.setText("");
        searchButton.setEnabled(true);
        
    }//GEN-LAST:event_departmentComboBoxActionPerformed

    private void newDepartmentComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDepartmentComboBoxActionPerformed
        // TODO add your handling code here:
        String temp = (String) newDepartmentComboBox.getSelectedItem();
        if(temp.equals("None!")){
            return;
        }
        programID.setEnabled(true);
        programName.setEnabled(true);
        updateButton.setEnabled(true);
        
    }//GEN-LAST:event_newDepartmentComboBoxActionPerformed

    private void newSchoolComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSchoolComboBoxActionPerformed
        // TODO add your handling code here:
        
        
        newDepartmentComboBox.setEnabled(false);
        updateButton.setEnabled(false);
        programID.setEnabled(false);
        programName.setEnabled(false);
        
        
        String temp1 = (String) newSchoolComboBox.getSelectedItem();
        if(temp1.equals("None!")){
            return;
        }
        
        int index = newSchoolComboBox.getSelectedIndex();
        int combo_len = 0;
        
        try {
            PreparedStatement ps = connect.prepareStatement("SELECT COUNT(*) FROM `t04_department` WHERE T03_id_fk = ?;");
            ps.setString(1, T03_id[index]);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                combo_len = rs.getInt(1);
            }
            if(combo_len==0){
                String [] temp2 = {"None!"};
                newDepartmentComboBox.setModel(new DefaultComboBoxModel(temp2));
                return;
            }
            newT04_id = new String [combo_len];
            String [] temp = new String [combo_len];
            
            ps = connect.prepareStatement("SELECT department_id,department_name,T04_id FROM `t04_department` WHERE T03_id_fk = ?;");
            ps.setString(1, T03_id[index]);
            rs = ps.executeQuery();
            
            rs.next();
            for(int i=0;i<combo_len;i++,rs.next()){
                temp[i] = rs.getString(1)+"--"+rs.getString(2);
                newT04_id[i]=rs.getString(3);
            }
            newDepartmentComboBox.setModel(new DefaultComboBoxModel(temp));
            newDepartmentComboBox.setEnabled(true);
            newDepartmentComboBox.setSelectedIndex(0);
            
            
        } catch (SQLException e) {
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            alert2bGeneration(2,"Server Connection Failed!!",msg,"Please,Retry Connection or Quit!","Retry","Quit","EditProgramFrame");
            //System.out.println(e);
            
        }
    }//GEN-LAST:event_newSchoolComboBoxActionPerformed

    private void existingProgramIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_existingProgramIDActionPerformed
        // TODO add your handling code here:
        /*newSchoolComboBox.setEnabled(false);
        newDepartmentComboBox.setEnabled(false);
        programID.setEnabled(false);
        programName.setEnabled(false);
        programID.setText("");
        programName.setText("");
        updateButton.setEnabled(false);*/
    }//GEN-LAST:event_existingProgramIDActionPerformed

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
            java.util.logging.Logger.getLogger(EditProgramFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditProgramFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditProgramFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditProgramFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new EditProgramFrame("default").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> departmentComboBox;
    private javax.swing.JTextField existingProgramID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> newDepartmentComboBox;
    private javax.swing.JComboBox<String> newSchoolComboBox;
    private javax.swing.JTextField programID;
    private javax.swing.JTextField programName;
    private javax.swing.JComboBox<String> schoolComboBox;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton viewTableButton;
    // End of variables declaration//GEN-END:variables
}

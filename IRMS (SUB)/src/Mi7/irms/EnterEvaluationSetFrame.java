/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Mi7.irms;


import java.awt.Dimension;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 *
 * @author himal
 */
public class EnterEvaluationSetFrame extends javax.swing.JFrame {

    /**
     * Creates new form EnterUniversity
     */
    Connection connect;
    Statement stmt;
    
    //String uni_id="";
    String [] T03_id = new String [100];
    String idT06 = "";
    String idT01 = "";
    String syllabus_id = "";
    boolean syllabus_status = true;
    String [] T08_id = {"None!"};
    String [] T07_id = {"None!"};
    String [] [] prev_t12info;
    int delete_row_index = -1;
    void reConnection(){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            connect.setAutoCommit(true);
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
    }
    void setComboBox(DefaultComboBoxModel combomodel1,DefaultComboBoxModel combomodel2){
        defaultSet.setModel(combomodel1);
        defaultSet.setEnabled(true);
        evaluationSet.setModel(combomodel2);
        evaluationSet.setEnabled(true);
    }
    void alert2bGeneration(int c,String msg1,String msg2,String msg3,String btext1,String btext2,String parent){
        
        
        this.setEnabled(false);
        AlertFrame2B a2frm = new AlertFrame2B(c,msg1,msg2,msg3,btext1,btext2);
        a2frm.passEnterEvaluationSetFrame(this, parent);
        a2frm.setLocationRelativeTo(null);
        a2frm.setAlwaysOnTop(true);
        a2frm.setVisible(true);         
        
    }
    
    void alertGeneration(int c,String msg1,String msg2,String msg3){
        
        
        this.setEnabled(false);
        AlertFrame afrm = new AlertFrame(c,msg1,msg2,msg3);
        afrm.passEnterEvaluationSetFrame(this,"enterevaluationsetframe");
        
        
        afrm.setLocationRelativeTo(null);
        
        afrm.setAlwaysOnTop(true);
        afrm.setVisible(true);
    }
    
    public EnterEvaluationSetFrame(String idT06,String idT01) {
        initComponents();
        this.idT06 = idT06;
        this.idT01 = idT01;
        addButton.setEnabled(false);
        saveButton.setEnabled(false);
        deleteButton.setEnabled(false);
        courseComboBox.setEnabled(false);
        evaluationSet.setEnabled(false);
        defaultSet.setEnabled(false);
        jTable.setShowGrid(true);
        syllabus_status = true;
        delete_row_index = -1;
        T08_id = new String[1];
        T08_id[0] = "None!";
        T07_id = new String[1];
        T07_id[0] = "None!";
        prev_t12info = new String[1][4];
        JTableHeader header = jTable.getTableHeader();
        header.setReorderingAllowed(false);
        for(int i=0;i<4;i++){
            prev_t12info[0][i] = "None!";
        }
        try{
            
            PreparedStatement ps;
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            connect.setAutoCommit(true);
            ps = connect.prepareStatement("SELECT syllabus_id FROM `t06_syllabus` WHERE T06_id = ?");
            ps.setString(1, idT06);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                this.syllabus_id = rs.getString(1);
            }
            jLabel1.setText("EVALUATION SET OF: "+syllabus_id);
            
            ps = connect.prepareStatement("SELECT * FROM `t06_syllabus` WHERE T06_id = ? AND ( syllabus_status IS NULL OR syllabus_status = 0);" );
            ps.setString(1, idT06);
            rs = ps.executeQuery();
            while(rs.next()){
                syllabus_status = false;
            }
            
            
            
            
            if(syllabus_status){
                alertGeneration(1,"Warning!!","Syllabus is already Finalized!","Syllabus can not be Changed!");
            }else{
                saveButton.setEnabled(true);
                addButton.setEnabled(true);
            }
            
            //EvaluationSet comboBoxes
            ps = connect.prepareStatement("SELECT T08_id,evaluation_set_id FROM `t08_evaluation_set` WHERE T01_id_fk = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT01);
            rs = ps.executeQuery();
            
            rs.last();
            int combo_len = rs.getRow();
            rs.beforeFirst();
            if(combo_len>0){
                T08_id = new String[combo_len];
                String [] temp = new String[combo_len];
                int i=0;
                while (rs.next()) {
                    temp[i] = rs.getString(2);
                    T08_id [i] = rs.getString(1);
                    i++;
                }
                this.setComboBox(new DefaultComboBoxModel(temp),new DefaultComboBoxModel(temp));
            }
           
            //course comboBox
            ps = connect.prepareStatement("SELECT T07_id,course_id FROM `t07_course` WHERE T06_id_fk = ? ORDER BY course_id",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); 
            ps.setString(1, idT06);
            rs = ps.executeQuery();
            
            combo_len=0;
            rs.last();
            combo_len = rs.getRow();
            rs.beforeFirst();
            if(combo_len>0){
                T07_id = new String[combo_len];
                String [] temp = new String[combo_len];
                int i=0;
                while (rs.next()) {
                    temp[i] = rs.getString(2);
                    T07_id [i] = rs.getString(1);
                    i++;
                }
                courseComboBox.setModel(new DefaultComboBoxModel(temp));
                courseComboBox.setEnabled(true);
            }
            
            //table
            String [] [] temp = new String [T07_id.length][3];
            temp[0][0] = "None!";
            int temp_current_length = 0;
            for(int i=0;i<T07_id.length;i++){
               
               ps = connect.prepareStatement("SELECT T12_id,T08_id_fk FROM `t12_unique_course_evaluation` WHERE T07_id_fk = ?");
               ps.setString(1, T07_id[i]);
               rs = ps.executeQuery();
               while(rs.next()){
                   temp[temp_current_length][0] = rs.getString(1);
                   temp[temp_current_length][1] = T07_id[i];
                   temp[temp_current_length][2] = rs.getString(2);
                   temp_current_length++;
                   String temp_T08id = rs.getString(2);
                   for (int j = 0; j < T08_id.length; j++) {
                       if (temp_T08id.equals(T08_id[j])) {
                           String temp_course_id = courseComboBox.getItemAt(i);
                           String temp_evaluation_set = evaluationSet.getItemAt(j);
                           DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
                           
                           Object obj[] ={temp_course_id,temp_evaluation_set};
                           dModel.addRow(obj);
                           
                           jTable.setModel(dModel);
                           jTable.revalidate();
                           jTable.repaint();
                           break;
                       }
                   }
               }
               
            }
            if (!temp[0][0].equals("None!")) {
                prev_t12info = new String[temp_current_length][4];
                for (int i = 0; i < prev_t12info.length; i++) {
                    prev_t12info[i][0] = temp[i][0];
                    prev_t12info[i][1] = temp[i][1];
                    prev_t12info[i][2] = temp[i][2];
                    prev_t12info[i][3] = "0";
                }
            }
            courseComboBox.setSelectedIndex(0);
            
            
            //set DefaultEvaluation
            ps = connect.prepareStatement("SELECT t08_evaluation_set.evaluation_set_id "
                    + "FROM `t06_syllabus` JOIN t08_evaluation_set "
                    + "ON t06_syllabus.default_T08_id_fk = t08_evaluation_set.T08_id where T06_id = ?");
            ps.setString(1, idT06);
            rs = ps.executeQuery();
            if(rs.next()){
                String temp_default_eva = rs.getString(1);
                defaultSet.setSelectedItem(temp_default_eva);
            }
            
        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            alert2bGeneration(2,"Server Connection Failed!!",msg,"Please,Retry Connection or Quit!","Retry","Quit","enterevaluationsetframe");
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        defaultSet = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        courseComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        evaluationSet = new javax.swing.JComboBox<>();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Evaluation Set");
        setPreferredSize(new java.awt.Dimension(665, 610));

        jPanel1.setPreferredSize(new java.awt.Dimension(234, 100));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/syllabusSettings_64.png"))); // NOI18N
        jLabel1.setText("EVALUATION SET OF #DEFAULT");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(10, 70));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("Default Set:");
        jPanel5.add(jLabel2);

        defaultSet.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        defaultSet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        defaultSet.setPreferredSize(new java.awt.Dimension(150, 30));
        defaultSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultSetActionPerformed(evt);
            }
        });
        jPanel5.add(defaultSet);

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel6.setPreferredSize(new java.awt.Dimension(160, 100));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jButton1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jButton1.setText("Evaluation Settings");
        jButton1.setPreferredSize(new java.awt.Dimension(140, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);

        jPanel3.add(jPanel6, java.awt.BorderLayout.EAST);

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel9.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jSeparator1.setForeground(new java.awt.Color(0, 51, 153));
        jSeparator1.setPreferredSize(new java.awt.Dimension(500, 10));
        jPanel9.add(jSeparator1, java.awt.BorderLayout.NORTH);

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("Set Uniqe Evaluation Set for Courses");
        jLabel6.setToolTipText("");
        jPanel10.add(jLabel6);

        jPanel9.add(jPanel10, java.awt.BorderLayout.PAGE_END);

        jPanel4.add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel7.setPreferredSize(new java.awt.Dimension(200, 100));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 40));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("Course:");
        jPanel7.add(jLabel3);

        courseComboBox.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        courseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        courseComboBox.setPreferredSize(new java.awt.Dimension(120, 30));
        courseComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseComboBoxActionPerformed(evt);
            }
        });
        jPanel7.add(courseComboBox);

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setText("E. SET: ");
        jPanel7.add(jLabel4);

        evaluationSet.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        evaluationSet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        evaluationSet.setPreferredSize(new java.awt.Dimension(120, 30));
        evaluationSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evaluationSetActionPerformed(evt);
            }
        });
        jPanel7.add(evaluationSet);

        addButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        addButton.setText("ADD");
        addButton.setPreferredSize(new java.awt.Dimension(120, 30));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel7.add(addButton);

        deleteButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        deleteButton.setText("DELETE");
        deleteButton.setPreferredSize(new java.awt.Dimension(120, 30));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jPanel7.add(deleteButton);

        jPanel4.add(jPanel7, java.awt.BorderLayout.WEST);

        jPanel8.setLayout(new java.awt.BorderLayout(10, 10));

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        jTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course", "Evaluation Set"
            }
        ));
        jTable.setEnabled(false);
        jScrollPane1.setViewportView(jTable);

        jPanel12.add(jScrollPane1);

        jPanel8.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        jSeparator2.setForeground(new java.awt.Color(0, 51, 153));
        jSeparator2.setPreferredSize(new java.awt.Dimension(500, 10));
        jPanel2.add(jSeparator2, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel11.setPreferredSize(new java.awt.Dimension(100, 70));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        saveButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.setPreferredSize(new java.awt.Dimension(120, 30));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jPanel11.add(saveButton);

        getContentPane().add(jPanel11, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setEnabled(false);
        EvaluationSetFrame evfrm = new EvaluationSetFrame(idT06,idT01);
        evfrm.setLocationRelativeTo(null);
        evfrm.passEnterEvaluationSetFrame(this);
        evfrm.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        if(courseComboBox.getItemAt(0).equals("None!")){
            ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
            JOptionPane.showMessageDialog(this, "No Course Exists!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            courseComboBox.setEnabled(false);
            return;
        }
        if(evaluationSet.getItemAt(0).equals("None!")){
            ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
            JOptionPane.showMessageDialog(this, "No Evaluation Set Exists!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            evaluationSet.setEnabled(false);
            return;
        }
        Object input_row[] = {courseComboBox.getSelectedItem(), evaluationSet.getSelectedItem()};
        DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
        dModel.addRow(input_row);

        jTable.setModel(dModel);
        jTable.revalidate();
        jTable.repaint();
        courseComboBox.setSelectedIndex(courseComboBox.getSelectedIndex());
        
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        if(courseComboBox.getItemAt(0).equals("None!")){
            ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
            JOptionPane.showMessageDialog(this, "No Course Exists!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            courseComboBox.setEnabled(false);
            return;
        }
        if(evaluationSet.getItemAt(0).equals("None!")){
            ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
            JOptionPane.showMessageDialog(this, "No Evaluation Set Exists!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            evaluationSet.setEnabled(false);
            return;
        }
        
        if(delete_row_index>-1){
            DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
            dModel.removeRow(delete_row_index);
            
            jTable.setModel(dModel);
            jTable.revalidate();
            jTable.repaint();
        }
        courseComboBox.setSelectedIndex(courseComboBox.getSelectedIndex());

        
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void courseComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseComboBoxActionPerformed
        // TODO add your handling code here:
        
        String temp_course_id = courseComboBox.getSelectedItem().toString();
        boolean flag = true;
        for(int i=0;i<jTable.getRowCount();i++){
            if(temp_course_id.equals(jTable.getValueAt(i, 0).toString())){
                evaluationSet.setSelectedItem(jTable.getValueAt(i, 1));
                addButton.setEnabled(false);
                deleteButton.setEnabled(true);
                evaluationSet.setEnabled(false);
                flag = false;
                delete_row_index = i;
                break;
            }
        }
        if(flag){
            addButton.setEnabled(true);
            deleteButton.setEnabled(false);
            evaluationSet.setEnabled(true);
            delete_row_index = -1;
        }
    }//GEN-LAST:event_courseComboBoxActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        int row_count = jTable.getRowCount();
        PreparedStatement ps;
        try {
            connect.setAutoCommit(false);
            for(int i=0;i<row_count;i++){
                for(int j=0;j<prev_t12info.length;j++){
                    DefaultComboBoxModel dComboModel = (DefaultComboBoxModel) courseComboBox.getModel();
                    int index_T07 = dComboModel.getIndexOf(jTable.getValueAt(i, 0));
                    dComboModel = (DefaultComboBoxModel) evaluationSet.getModel();
                    int index_T08 = dComboModel.getIndexOf(jTable.getValueAt(i, 1));
                    if(T07_id[index_T07].equals(prev_t12info[j][1])){
                        if(T08_id[index_T08].equals(prev_t12info[j][2])){
                            prev_t12info[j][3] ="1";
                            break;
                        }else{
                            //update
                            ps = connect.prepareStatement("UPDATE t12_unique_course_evaluation SET T08_id_fk = ? WHERE T12_id = ?");
                            ps.setString(1, T08_id[index_T08]);
                            ps.setString(2, prev_t12info[j][0]);
                            ps.executeUpdate();
                            prev_t12info[j][3] ="1";
                            ps = connect.prepareStatement("UPDATE t08_evaluation_set SET editable = 1 WHERE T08_id = ?");
                            ps.setString(1, T08_id[index_T08]);
                            ps.executeUpdate();
                            break;
                        }
                    }else if(j == prev_t12info.length-1){
                        ps = connect.prepareStatement("INSERT INTO `t12_unique_course_evaluation`(`T07_id_fk`, `T08_id_fk`) VALUES (?,?)");
                        ps.setString(1, T07_id[index_T07]);
                        ps.setString(2, T08_id[index_T08]);
                        ps.executeUpdate();
                        ps = connect.prepareStatement("UPDATE t08_evaluation_set SET editable = 1 WHERE T08_id = ?");
                        ps.setString(1, T08_id[index_T08]);
                        ps.executeUpdate();
                    }
                }
                
            }
            
            //delete data that is not present in new data
            for(int i=0;!prev_t12info[0][0].equals("None!") &&i<prev_t12info.length;i++){
                if(prev_t12info[i][3].equals("0")){
                    ps = connect.prepareStatement("DELETE FROM `t12_unique_course_evaluation` WHERE T12_id = ?");
                    ps.setString(1, prev_t12info[i][0]);
                    ps.executeUpdate();
                }
            }
            
            
            //insert default evaluationSet
            ps = connect.prepareStatement("UPDATE `t06_syllabus` SET `default_T08_id_fk`= ? WHERE T06_id = ?");
            
            ps.setString(1, T08_id[defaultSet.getSelectedIndex()]);
            ps.setString(2, idT06);
            ps.executeUpdate();
            ps = connect.prepareStatement("UPDATE t08_evaluation_set SET editable = 1 WHERE T08_id = ?");
            ps.setString(1, T08_id[defaultSet.getSelectedIndex()]);
            ps.executeUpdate();
            connect.commit();
            connect.setAutoCommit(true);
            ImageIcon success = new ImageIcon(getClass().getResource("/icon/success_64.png"));
            JOptionPane.showMessageDialog(this, "Evaluation Set Entry Successful!", "Success!", JOptionPane.ERROR_MESSAGE, success);
            this.dispose();
            /*
            for (int i = 0; i < row_count; i++) {
                
               
                
                
                if ((!T12_id[0].equals("None!")) && T12_id.length > i) {
                    //update
                    PreparedStatement ps = connect.prepareStatement("UPDATE t12_unique_course_evaluation SET T07_id_fk = ?,T08_id_fk = ? WHERE T12_id = ?");
                    DefaultComboBoxModel dComboModel = (DefaultComboBoxModel) courseComboBox.getModel();
                    int temp_index = dComboModel.getIndexOf(jTable.getValueAt(i, 0));
                    ps.setString(1, T07_id[temp_index]);
                    
                    dComboModel = (DefaultComboBoxModel) evaluationSet.getModel();
                    temp_index = dComboModel.getIndexOf(jTable.getValueAt(i, 1));
                    ps.setString(2, T08_id[temp_index]);
                    
                    ps.setString(3, T12_id[i]);
                    ps.executeUpdate();
                    
                } else {
                    //Insert
                    PreparedStatement ps = connect.prepareStatement("INSERT INTO `t12_unique_course_evaluation`(`T07_id_fk`, `T08_id_fk`) VALUES (?,?)");
                    DefaultComboBoxModel dComboModel = (DefaultComboBoxModel) courseComboBox.getModel();
                    int temp_index = dComboModel.getIndexOf(jTable.getValueAt(i, 0));
                    ps.setString(1, T07_id[temp_index]);
                    
                    dComboModel = (DefaultComboBoxModel) evaluationSet.getModel();
                    temp_index = dComboModel.getIndexOf(jTable.getValueAt(i, 1));
                    ps.setString(2, T08_id[temp_index]);
                    
                    ps.executeUpdate();
                       
                }
                
            }
            if (T12_id.length > row_count) {
                for(int i=row_count;i<T12_id.length;i++){
                    PreparedStatement ps = connect.prepareStatement("DELETE FROM `t12_unique_course_evaluation` WHERE T12_id = ?");
                    ps.setString(1, T12_id[i]);
                    ps.executeUpdate();
                }
            }
            
            PreparedStatement ps = connect.prepareStatement("UPDATE `t06_syllabus` SET `default_T08_id_fk`= ? WHERE T06_id = ?");
            
            ps.setString(1, T08_id[defaultSet.getSelectedIndex()]);
            ps.setString(2, idT06);
            ps.executeUpdate();
            
            ImageIcon success = new ImageIcon(getClass().getResource("/icon/success_64.png"));
            JOptionPane.showMessageDialog(this, "Evaluation Set Entry Successful!", "Success!", JOptionPane.ERROR_MESSAGE, success);
            
            */
        }catch (SQLException ex) {
            try {
                connect.rollback();
                connect.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(EnterEvaluationSetFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
            Logger.getLogger(EvaluationSetFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(EvaluationSetFrame.class.getName()).log(Level.SEVERE, null, ex);
            int code = ex.getErrorCode();
            String msg = "Error Code: " + code;
            String[] options = {"OK"};
            ImageIcon icon_server_error = new ImageIcon(getClass().getResource("/icon/server_error_2_64.png"));
            ImageIcon icon_data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));

            if (code == 0) {
                this.reConnection();
                int select = JOptionPane.showOptionDialog(this, "Error Server Connection!!\n" + msg + "\nTry Again!", "Connection Failed!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_server_error, options, options[0]);
                if (select == JOptionPane.CLOSED_OPTION || select == 0) {
                    this.reConnection();
                }
            } else {
                JOptionPane.showOptionDialog(this, "Error in Data Entry!!\n" + msg + "\nTry Again!", "Data Entry Failed!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_data_error, options, options[0]);
            }
        }
        
    }//GEN-LAST:event_saveButtonActionPerformed

    private void defaultSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defaultSetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_defaultSetActionPerformed

    private void evaluationSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evaluationSetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_evaluationSetActionPerformed

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
            java.util.logging.Logger.getLogger(EnterEvaluationSetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnterEvaluationSetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnterEvaluationSetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnterEvaluationSetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EnterEvaluationSetFrame("default","default").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JComboBox<String> courseComboBox;
    private javax.swing.JComboBox<String> defaultSet;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> evaluationSet;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}

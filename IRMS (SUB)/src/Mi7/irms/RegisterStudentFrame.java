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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
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
public class RegisterStudentFrame extends javax.swing.JFrame {

    /**
     * Creates new form EnterUniversity
     */
    Connection connect;
    Statement stmt;
    
    //String uni_id="";
    String [] T03_id = new String [100];
    String idT06 = "";
    String idT01 = "";
    String idT14 = "";
    String syllabus_id = "";
    String [] T17_id = {"None!"};
    String [] T15_id = {"None!"};
    String [] prev_T21_id;
    int delete_row_index = -1;
    String [] T20_id;
    OfferedCourseFrame ofcfrm;
    
    
    void passOfferedCourseFrame(OfferedCourseFrame ofcfrm){
        this.ofcfrm = ofcfrm;
    }
    
    void reConnection(){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            connect.setAutoCommit(true);
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
    }
    
    
    
    
    
    public RegisterStudentFrame(String idT06,String idT01,String idT14) {
        initComponents();
        this.idT06 = idT06;
        this.idT01 = idT01;
        this.idT14 = idT14;
        //System.out.println(idT01 +" " +idT06+" " + idT14);
        
        countTextField.setEditable(false);
        
        batchComboBox.setEnabled(false);
        courseComboBox.setEnabled(false);
        studentComboBox.setEnabled(false);
        registerButton.setEnabled(false);
        deleteButton.setEnabled(false);
        saveButton.setEnabled(false);
        
        jTable.setEnabled(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        
        JTableHeader header = jTable.getTableHeader();
        header.setReorderingAllowed(false);
        jTable.setShowGrid(true);
        
        
        delete_row_index = -1;
        
        T17_id = new String[1];
        T17_id[0] = "None!";
        T20_id = new String [1];
        T20_id[0]= "None!";
        T15_id = new String [1];
        T15_id[0]= "None!";
        
        prev_T21_id = new String[1];
        prev_T21_id[0] = "None!";
        
        
        
        
        try{
            
            PreparedStatement ps;
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            connect.setAutoCommit(true);
            ps = connect.prepareStatement("SELECT t15_offered_course.T15_id,t07_course.course_id FROM `t15_offered_course` "
                    + "JOIN t07_course ON t15_offered_course.T07_id_fk = t07_course.T07_id "
                    + "WHERE t15_offered_course.T14_id_fk=? ORDER BY t07_course.course_id ASC",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT14);
            ResultSet rs = ps.executeQuery();
            rs.last();
            int course_count = rs.getRow();
            rs.beforeFirst();
            
            if(course_count>0){
                T15_id = new String [course_count];
                String [] temp = new String [ course_count];
                for(int i=0;rs.next() && i<T15_id.length;i++){
                    T15_id[i] = rs.getString(1);
                    temp[i] = rs.getString(2);
                }
                courseComboBox.setModel(new DefaultComboBoxModel(temp));
                
            }else{
                ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                JOptionPane.showMessageDialog(this, "No Offered Course Exists", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            
            ps = connect.prepareStatement("SELECT t17_batch.T17_id,t17_batch.batch_id,t17_batch.batch_name FROM `t17_batch` "
                    + "JOIN t14_semester ON t17_batch.T14_id_fk=t14_semester.T14_id "
                    + "WHERE t14_semester.T06_id_fk = ? ORDER BY t17_batch.batch_id ASC",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT06);
            rs = ps.executeQuery();
            rs.last();
            int batch_count = rs.getRow();
            rs.beforeFirst();
            if(batch_count>0){
                T17_id = new String [batch_count];
                String [] temp = new String [batch_count];
                for(int i=0;rs.next()&&i<T17_id.length;i++){
                    T17_id[i] = rs.getString(1);
                    temp[i] = rs.getString(2)+"--"+rs.getString(3);
                    
                }
                batchComboBox.setModel(new DefaultComboBoxModel(temp));
                batchComboBox.setEnabled(true);
                batchComboBox.setSelectedIndex(0);
            }else{
                ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                JOptionPane.showMessageDialog(this, "No Batch Exists", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            
            
            
                
                
        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            String [] options = {"Retry","Quit"};
            ImageIcon icon_server_retry = new ImageIcon(getClass().getResource("/icon/server_retry_64.png"));
            int select = JOptionPane.showOptionDialog(null, "Server Connection Failed!!\n"+msg+"\nPlease,Retry Connection or Quit!", "Connection Failed!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_server_retry, options, options[0]);
            if(select==0){
                RegisterStudentFrame rtfrm = new RegisterStudentFrame(this.idT06,this.idT01,this.idT14);
                rtfrm.setLocationRelativeTo(null);
                rtfrm.setVisible(true);
                this.dispose();
            }else{
                this.dispose();
            }
            //alert2bGeneration(2,"Server Connection Failed!!",msg,"Please,Retry Connection or Quit!","Retry","Quit","entercourseinfoFrame");
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
        batchComboBox = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        countTextField = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        studentComboBox = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        courseComboBox = new javax.swing.JComboBox<>();
        deleteButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Register Student");
        setPreferredSize(new java.awt.Dimension(600, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(234, 80));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/offeredCourse_64.png"))); // NOI18N
        jLabel1.setText("REGISTER STUDENT");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(10, 50));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("Batch:");
        jPanel5.add(jLabel2);

        batchComboBox.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        batchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        batchComboBox.setPreferredSize(new java.awt.Dimension(180, 30));
        batchComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchComboBoxActionPerformed(evt);
            }
        });
        jPanel5.add(batchComboBox);

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel9.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jSeparator1.setForeground(new java.awt.Color(0, 51, 153));
        jSeparator1.setPreferredSize(new java.awt.Dimension(500, 5));
        jPanel9.add(jSeparator1, java.awt.BorderLayout.NORTH);

        jPanel14.setPreferredSize(new java.awt.Dimension(150, 35));
        jPanel10.add(jPanel14);

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("Register Offered Courses to Student");
        jLabel6.setToolTipText("");
        jPanel10.add(jLabel6);

        jPanel6.setPreferredSize(new java.awt.Dimension(150, 35));

        jPanel15.setPreferredSize(new java.awt.Dimension(35, 10));
        jPanel6.add(jPanel15);

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel5.setText("Count:");
        jPanel6.add(jLabel5);

        countTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        countTextField.setPreferredSize(new java.awt.Dimension(40, 23));
        countTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countTextFieldActionPerformed(evt);
            }
        });
        jPanel6.add(countTextField);

        jPanel10.add(jPanel6);

        jPanel9.add(jPanel10, java.awt.BorderLayout.PAGE_END);

        jPanel4.add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel7.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 40));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setText("Student ID: ");
        jPanel7.add(jLabel4);

        studentComboBox.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        studentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        studentComboBox.setPreferredSize(new java.awt.Dimension(200, 30));
        studentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentComboBoxActionPerformed(evt);
            }
        });
        jPanel7.add(studentComboBox);

        jSeparator3.setForeground(new java.awt.Color(0, 51, 153));
        jSeparator3.setPreferredSize(new java.awt.Dimension(500, 10));
        jPanel7.add(jSeparator3);

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("Offered Course:");
        jPanel7.add(jLabel3);

        courseComboBox.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        courseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        courseComboBox.setPreferredSize(new java.awt.Dimension(150, 30));
        courseComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseComboBoxActionPerformed(evt);
            }
        });
        jPanel7.add(courseComboBox);

        deleteButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        deleteButton.setText("DELETE");
        deleteButton.setPreferredSize(new java.awt.Dimension(120, 30));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jPanel7.add(deleteButton);

        registerButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        registerButton.setText("REGISTER");
        registerButton.setPreferredSize(new java.awt.Dimension(120, 30));
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        jPanel7.add(registerButton);

        jPanel4.add(jPanel7, java.awt.BorderLayout.WEST);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Register Course"
            }
        ));
        jScrollPane1.setViewportView(jTable);

        jPanel8.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        jSeparator2.setForeground(new java.awt.Color(0, 51, 153));
        jSeparator2.setPreferredSize(new java.awt.Dimension(500, 10));
        jPanel2.add(jSeparator2, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel11.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

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

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        // TODO add your handling code here:
        if(T15_id[0].equals("None!")){
            ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
            JOptionPane.showMessageDialog(this, "No Course Exists!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            courseComboBox.setEnabled(false);
            batchComboBox.setEnabled(false);
            studentComboBox.setEnabled(false);
            saveButton.setEnabled(false);
            deleteButton.setEnabled(false);
            registerButton.setEnabled(false);
            return;
        }
        if(T20_id[0].equals("None!")){
            ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
            JOptionPane.showMessageDialog(this, "No Student Exists!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            courseComboBox.setEnabled(false);
            studentComboBox.setEnabled(false);
            saveButton.setEnabled(false);
            deleteButton.setEnabled(false);
            registerButton.setEnabled(false);
            return;
        }
        Object input_row[] = {courseComboBox.getSelectedItem()};
        DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
        dModel.addRow(input_row);
        
        
        
        jTable.setModel(dModel);
        jTable.revalidate();
        jTable.repaint();
        courseComboBox.setSelectedIndex(courseComboBox.getSelectedIndex());
        int count = jTable.getRowCount();
        countTextField.setText(String.valueOf(count));
        
    }//GEN-LAST:event_registerButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        if(T15_id[0].equals("None!")){
            ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
            JOptionPane.showMessageDialog(this, "No Course Exists!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            courseComboBox.setEnabled(false);
            batchComboBox.setEnabled(false);
            studentComboBox.setEnabled(false);
            saveButton.setEnabled(false);
            deleteButton.setEnabled(false);
            registerButton.setEnabled(false);
            return;
        }
        if(T20_id[0].equals("None!")){
            ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
            JOptionPane.showMessageDialog(this, "No Student Exists!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            courseComboBox.setEnabled(false);
            studentComboBox.setEnabled(false);
            saveButton.setEnabled(false);
            deleteButton.setEnabled(false);
            registerButton.setEnabled(false);
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
        int count = jTable.getRowCount();
        countTextField.setText(String.valueOf(count));
        
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void courseComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseComboBoxActionPerformed
        // TODO add your handling code here:
        
        if(T20_id[0].equals("None!")){
            studentComboBox.setEnabled(false);
            registerButton.setEnabled(false);
            deleteButton.setEnabled(false);
            saveButton.setEnabled(false);
            return;
        }
        if(T15_id[0].equals("None!")){
            batchComboBox.setEnabled(false);
            studentComboBox.setEnabled(false);
            courseComboBox.setEnabled(false);
            registerButton.setEnabled(false);
            deleteButton.setEnabled(false);
            saveButton.setEnabled(false);
            return;
        }
        if(T17_id[0].equals("None!")){
            batchComboBox.setEnabled(false);
            registerButton.setEnabled(false);
            deleteButton.setEnabled(false);
            saveButton.setEnabled(false);
            return;
        }
        String temp_course_id = courseComboBox.getSelectedItem().toString();
        boolean flag = true;
        
        for(int i=0;i<jTable.getRowCount();i++){
            if(temp_course_id.equals(jTable.getValueAt(i, 0).toString())){
                
                registerButton.setEnabled(false);
                deleteButton.setEnabled(true);
                
                flag = false;
                delete_row_index = i;
                break;
            }
        }
        if(flag){
            registerButton.setEnabled(true);
            deleteButton.setEnabled(false);
            
            delete_row_index = -1;
        }
        
    }//GEN-LAST:event_courseComboBoxActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        int row_count = jTable.getRowCount();
        PreparedStatement ps;
        int index_student = studentComboBox.getSelectedIndex();
        try {
            connect.setAutoCommit(false);
            
            //Delete previous T21 data
            ps = connect.prepareStatement("DELETE `t21_student_course_registration` FROM `t21_student_course_registration` "
                    + "JOIN t15_offered_course ON t21_student_course_registration.T15_id_fk = t15_offered_course.T15_id "
                    + "WHERE t15_offered_course.T14_id_fk = ? AND t21_student_course_registration.T20_id_fk=?");
            ps.setString(1, idT14);
            ps.setString(2, T20_id[index_student]);
            ps.executeUpdate();
            
            
            
            for(int i=0;i<row_count;i++){
                DefaultComboBoxModel dComboModel = (DefaultComboBoxModel) courseComboBox.getModel();
                int index_T15 = dComboModel.getIndexOf(jTable.getValueAt(i, 0));
                
                //System.out.println(prev_T15info.length);
                if(i<prev_T21_id.length && !prev_T21_id[0].equals("None!")){
                    ps = connect.prepareStatement("INSERT INTO `t21_student_course_registration`"
                            + "(`T21_id`, `T15_id_fk`, `T20_id_fk`) VALUES (?,?,?)");
                    ps.setString(1, prev_T21_id[i]);
                    ps.setString(2, T15_id[index_T15]);
                    ps.setString(3, T20_id[index_student]);
                    if(ps.executeUpdate()<=0){
                        throw new SQLException("Insert Error!");
                    }
                }else{
                    ps = connect.prepareStatement("INSERT INTO `t21_student_course_registration`"
                            + "(`T15_id_fk`, `T20_id_fk`) VALUES (?,?)");
                    ps.setString(1, T15_id[index_T15]);
                    ps.setString(2, T20_id[index_student]);
                    if(ps.executeUpdate()<=0){
                        throw new SQLException("Insert Error!");
                    }
                }
                
            }
            
            //Set Student status to 1
            ps = connect.prepareStatement("UPDATE `t20_student` SET `student_status`=1 WHERE T20_id = ?");
            ps.setString(1, T20_id[index_student]);
            if (ps.executeUpdate() <= 0) {
                throw new SQLException("Update Error!");
            }
            
            
            
            //check semester status
            ps = connect.prepareStatement("SELECT semester_status FROM `t14_semester` WHERE T14_id = ?");
            ps.setString(1, idT14);
            ResultSet rs = ps.executeQuery();
            if(rs.next() && rs.getInt(1)==0){
                //good
            }else{
                throw new SQLException("Update Error!");
            }
            
            
            connect.commit();
            connect.setAutoCommit(true);
            
            ImageIcon success = new ImageIcon(getClass().getResource("/icon/success_64.png"));
            JOptionPane.showMessageDialog(this, "Student Register Successful!", "Success!", JOptionPane.ERROR_MESSAGE, success);
            
            
        }catch (SQLException ex) {
            try {
                connect.rollback();
                connect.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(OfferedCourseFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(EndSemesterFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(EndSemesterFrame.class.getName()).log(Level.SEVERE, null, ex);
            
            ImageIcon icon_server_error = new ImageIcon(getClass().getResource("/icon/server_error_2_64.png"));
            ImageIcon icon_data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));

            
            int code = ex.getErrorCode();
            String error = ex.getMessage();
            String msg = "Error Code: " + code;
            String[] options = {"OK"};
            if (error.equals("Delete Error!") || error.equals("Update Error!") || error.equals("Insert Error!") ) {
                JOptionPane.showMessageDialog(this, "Error: "+error, "DataBase Error!", JOptionPane.ERROR_MESSAGE, icon_data_error);

            } else if (code == 0) {
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

    private void studentComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentComboBoxActionPerformed
        // TODO add your handling code here:
        registerButton.setEnabled(false);
        deleteButton.setEnabled(false);
        //saveButton.setEnabled(false);
        DefaultTableModel dModelx = (DefaultTableModel) jTable.getModel();
        dModelx.setRowCount(0);
        jTable.setModel(dModelx);
        jTable.revalidate();
        jTable.repaint();
        int countx = jTable.getRowCount();
        countTextField.setText(String.valueOf(countx));
        
        prev_T21_id = new String[1];
        prev_T21_id[0] = "None!";
        if(T20_id[0].equals("None!")){
            
            ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
            JOptionPane.showMessageDialog(this, "No Student Exists", "Error!", JOptionPane.ERROR_MESSAGE, error);
            studentComboBox.setEnabled(false);
            
            return;
        }
        int student_index = studentComboBox.getSelectedIndex();
        try{
            PreparedStatement ps = connect.prepareStatement("SELECT "
                    + "t21_student_course_registration.T21_id,t21_student_course_registration.T15_id_fk "
                    + "FROM `t21_student_course_registration` JOIN t15_offered_course "
                    + "ON t15_offered_course.T15_id=t21_student_course_registration.T15_id_fk "
                    + "WHERE t15_offered_course.T14_id_fk = ? AND t21_student_course_registration.T20_id_fk = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT14);
            ps.setString(2, T20_id[student_index]);
            ResultSet rs = ps.executeQuery();
            rs.last();
            int prev_count = rs.getRow();
            rs.beforeFirst();
            
            if(prev_count>0){
                prev_T21_id = new String [prev_count];
                DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
                dModel.setRowCount(0);
                for(int i=0;rs.next() && i<prev_T21_id.length;i++){
                    prev_T21_id[i] = rs.getString(1);
                    String temp = rs.getString(2);
                    for(int j=0;j<T15_id.length;j++){
                        if(T15_id[j].equals(temp)){
                            String [] temp_row ={courseComboBox.getItemAt(j)};
                            dModel.addRow(temp_row);
                            break;
                        }
                    }
                }
                
                jTable.setModel(dModel);
                jTable.revalidate();
                jTable.repaint();
                int count = jTable.getRowCount();
                countTextField.setText(String.valueOf(count));
            }else{
                DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
                dModel.setRowCount(0);
                jTable.setModel(dModel);
                jTable.revalidate();
                jTable.repaint();
                int count = jTable.getRowCount();
                countTextField.setText(String.valueOf(count));
            }
            courseComboBox.setSelectedIndex(0);
            
            
        }catch (SQLException ex) {
            Logger.getLogger(RegisterStudentFrame.class.getName()).log(Level.SEVERE, null, ex);
            ImageIcon icon_server_error = new ImageIcon(getClass().getResource("/icon/server_error_2_64.png"));
            ImageIcon icon_data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));

            
            int code = ex.getErrorCode();
            String error = ex.getMessage();
            String msg = "Error Code: " + code;
            String[] options = {"OK"};
            if (error.equals("Delete Error!") || error.equals("Update Error!") || error.equals("Insert Error!") ) {
                JOptionPane.showMessageDialog(this, "Error: "+error, "DataBase Error!", JOptionPane.ERROR_MESSAGE, icon_data_error);

            } else if (code == 0) {
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
        
        
    }//GEN-LAST:event_studentComboBoxActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        ofcfrm.setEnabled(true);
        ofcfrm.setAlwaysOnTop(true);
        ofcfrm.setAlwaysOnTop(false);
    }//GEN-LAST:event_formWindowClosed

    private void countTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_countTextFieldActionPerformed

    private void batchComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchComboBoxActionPerformed
        // TODO add your handling code here:
        
        studentComboBox.setEnabled(false);
        registerButton.setEnabled(false);
        deleteButton.setEnabled(false);
        saveButton.setEnabled(false);
        T20_id = new String[1];
        T20_id[0] = "None!";

        prev_T21_id = new String[1];
        prev_T21_id[0] = "None!";
        if(T17_id[0].equals("None!")){
            
            ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
            JOptionPane.showMessageDialog(this, "No Batch Exists", "Error!", JOptionPane.ERROR_MESSAGE, error);
            batchComboBox.setEnabled(false);
            return;
        }
        int index_batch = batchComboBox.getSelectedIndex();
        
        try {
            PreparedStatement ps = connect.prepareStatement("SELECT T20_id,student_id FROM `t20_student`"
                    + " WHERE T17_id_fk=? AND student_status<2 ORDER BY student_id ASC",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, T17_id[index_batch]);
            ResultSet rs = ps.executeQuery();
            rs.last();
            int student_count = rs.getRow();
            rs.beforeFirst();
            if(student_count>0){
                T20_id = new String [student_count];
                String[] temp = new String [student_count];
                for(int i=0;rs.next() && i<T20_id.length;i++){
                    T20_id[i] = rs.getString(1);
                    temp[i] = rs.getString(2);
                }
                studentComboBox.setModel(new DefaultComboBoxModel(temp));
                studentComboBox.setEnabled(true);
                studentComboBox.setSelectedIndex(0);
                if(!T15_id[0].equals("None!")){
                    courseComboBox.setEnabled(true);
                    courseComboBox.setSelectedIndex(0);
                }
                
                ps = connect.prepareStatement("SELECT * FROM `t14_semester` WHERE T14_id = ? AND semester_status<1");
                ps.setString(1, idT14);
                rs = ps.executeQuery();
                if(rs.next()){
                    saveButton.setEnabled(true);
                    
                }else{
                    saveButton.setEnabled(false);
                    courseComboBox.setEnabled(false);
                    registerButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                    ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                    JOptionPane.showMessageDialog(this, "Semester is already Rigid/Ended", "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
                
                
            }else{
                T20_id = new String[1];
                T20_id[0] = "None!";
                String [] tempx = {"None!"};
                studentComboBox.setModel(new DefaultComboBoxModel(tempx));
                ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                JOptionPane.showMessageDialog(this, "No Student Exists", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(RegisterStudentFrame.class.getName()).log(Level.SEVERE, null, ex);
            ImageIcon icon_server_error = new ImageIcon(getClass().getResource("/icon/server_error_2_64.png"));
            ImageIcon icon_data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));

            
            int code = ex.getErrorCode();
            String error = ex.getMessage();
            String msg = "Error Code: " + code;
            String[] options = {"OK"};
            if (error.equals("Delete Error!") || error.equals("Update Error!") || error.equals("Insert Error!") ) {
                JOptionPane.showMessageDialog(this, "Error: "+error, "DataBase Error!", JOptionPane.ERROR_MESSAGE, icon_data_error);

            } else if (code == 0) {
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
    }//GEN-LAST:event_batchComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(RegisterStudentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterStudentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterStudentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterStudentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new RegisterStudentFrame("default","default","default").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> batchComboBox;
    private javax.swing.JTextField countTextField;
    private javax.swing.JComboBox<String> courseComboBox;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
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
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> studentComboBox;
    // End of variables declaration//GEN-END:variables
}

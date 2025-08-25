/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Mi7.irms;


import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
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
public class CustomCourseOutcomeFrame extends javax.swing.JFrame {

    /**
     * Creates new form EnterUniversity
     */
    Connection connect;
    Statement stmt;

    //String uni_id="";
    String [] T03_id = new String [100];
    String idT06 = "";
    String idT01 = "";
    String idT08 = "";
    String idT16 = "";
    String syllabus_id = "";
    //String [] [] T07_info;
    PreparedStatement ps;
    String [] prev_T10_id;
    String [] prev_T13_id;
    String [] T09_id;
    String [] T16_id;
    String [] T14_id;
    String [] T15_id;
    String idT07;
    boolean set_exists = false;
    //int evaluation_count = 0;
    DefaultTableModel default_model;
    EnterEvaluationSetFrame eevsfrm;
    String [] columnNames;
    boolean constructor_generated = false;
    boolean course_status;
    
    int syb_status = 0;
    String token = "";
    String id = "";
    String uni_id = "";
    int profile = -1;
    int job_id = -1;
    MainFrame mfrm;
    boolean save_status;
    boolean delete_status;
    
    void passMainFrame(MainFrame mfrm) {
        this.mfrm = mfrm;
    }
    void reConnection(){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            connect.setAutoCommit(true);

        }catch(SQLException e){
            System.out.println(e);
        }

    }
    String encryptPass(char[] passwordPlain) {
        String encryptedPassword = null;

        try {
            // Convert char[] to byte[] using (UTF-8)
            byte[] passwordBytes = new String(passwordPlain).getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordBytes);

            byte[] hashBytes = md.digest();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < hashBytes.length; i++) {
                sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            encryptedPassword = sb.toString();

            // Clear the password bytes immediately
            Arrays.fill(passwordBytes, (byte) 0);
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            System.out.println(e);
        } finally {
            // Clear the original Plain Password for security
            Arrays.fill(passwordPlain, '\0');
        }

        return encryptedPassword;
    }
    
    
    void tableSettings(String [] columnNames){
        DefaultTableModel oldModel = (DefaultTableModel) jTable.getModel();
        int rowCount = oldModel.getRowCount();
        int columnCount = oldModel.getColumnCount();

        // Copy column names
        


        // Copy data from the existing table
        Object[][] data = new Object[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                data[row][col] = oldModel.getValueAt(row, col);
            }
        }

        //Create new model with editability control
        DefaultTableModel newModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return column != 0;
            }
        };
        
        //center align text in jTable

        jTable.setModel(newModel);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 1; i < jTable.getColumnCount(); i++) {
            jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JTableHeader header = jTable.getTableHeader();
        header.setReorderingAllowed(false);
        jTable.setShowGrid(true);
    }

    void showNormalTable(PreparedStatement ps) throws SQLException{

        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();

        int column_count = rsmd.getColumnCount();

        String[] column_names = new String[column_count];

        for (int i = 0, j = 0; i < column_count; i++) {

            column_names[j] = rsmd.getColumnName(i + 1);
            j++;
        }

        model.setColumnIdentifiers(column_names);

        while (rs.next()) {
            Object[] row = new Object[column_count];
            for (int i = 0, j = 0; i < column_count; i++) {

                row[j] = rs.getObject(i + 1);  //adding each row one by one
                j++;
            }
            model.addRow(row);
        }

        jTable.setModel(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);                                     //centering column names
        for (int i = 0; i < column_count; i++) {
            jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        


    }
    

    public CustomCourseOutcomeFrame(String idT01,String idT06, String token) {
        initComponents();
        constructor_generated = false;
        this.idT06 = idT06;
        this.idT01 = idT01;
        
        this.token = token;
        idT07 = "None!";
        columnNames = new String[1];
        columnNames[0] = "None!";
        T09_id = new String [1];
        T09_id[0] = "None!";
        T16_id = new String [1];
        T16_id[0] = "None!";
        T15_id = new String [1];
        T15_id[0] = "None!";
        prev_T10_id = new String [1];
        prev_T10_id [0] = "None!";
        prev_T13_id = new String [1];
        prev_T13_id [0] = "None!";
        
        
        
        cloCount.setEditable(false);
        cloCount.setEnabled(true);
        teacherComboBox.setEnabled(false);
        semesterComboBox.setEnabled(false);
        offeredCourseCombo.setEnabled(false);
        selectedCourseField.setEditable(false);
        saveButton.setEnabled(false);
        
        String [] tempToken = token.split(" ");
        this.id = tempToken[0];
        this.uni_id = tempToken[1];
        this.profile = Integer.parseInt(tempToken[2]);



        try{

            //PreparedStatement ps;
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            ResultSet rs;
            job_id = -1;
            if (profile ==0){
                ps = connect.prepareStatement("SELECT T02_id_fk , T16_id FROM `t16_employee` WHERE employee_id = ?  AND T01_id_fk = ?");
                ps.setString(1, id);
                ps.setString(2, idT01);
                rs = ps.executeQuery();
                while(rs.next()){
                    if(rs.getInt(1)==1){
                        job_id =1;
                    }else if(rs.getInt(1)==2){
                        job_id=2;
                        String [] tempTeacher = new String [1];
                        tempTeacher[0] = id;
                        T16_id = new String [1];
                        T16_id[0] = rs.getString(2);
                        teacherComboBox.setModel(new DefaultComboBoxModel(tempTeacher));
                        teacherComboBox.setEnabled(true);
                        teacherComboBox.setSelectedIndex(0);
                    }
                }
            }
            if (profile == 2 || job_id == 1) {
                
                ps = connect.prepareStatement("SELECT DISTINCT xyz.T16_id,xyz.employee_id,xyz.employee_name "
                        + "FROM (SELECT t16_employee.T16_id,t16_employee.employee_id,t16_employee.employee_name,t15_offered_course.T14_id_fk "
                        + "FROM t16_employee JOIN t15_offered_course ON "
                        + "t16_employee.T16_id = t15_offered_course.T16_id_fk) AS xyz "
                        + "JOIN t14_semester ON t14_semester.T14_id = xyz.T14_id_fk "
                        + "WHERE t14_semester.T06_id_fk=? ",
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ps.setString(1, idT06);
                rs = ps.executeQuery();
                rs.last();
                int count = rs.getRow();
                rs.beforeFirst();
                
                if (count > 0) {
                    T16_id = new String[count];
                    String[] comboArray = new String[count];
                    for (int i = 0; rs.next() && i < T16_id.length; i++) {

                        T16_id[i] = rs.getString(1);
                        String[] temp_name = rs.getString(3).trim().split(" ");
                        comboArray[i] = rs.getString(2) + "--" + temp_name[0];
                        

                    }

                    teacherComboBox.setModel(new DefaultComboBoxModel(comboArray));
                    teacherComboBox.setEnabled(true);
                    teacherComboBox.setSelectedIndex(0);
                }
            }
            
            
            
            constructor_generated = true;
            
            
            
            
            


        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            String [] options = {"Retry","Quit"};
            ImageIcon icon_server_retry = new ImageIcon(getClass().getResource("/icon/server_retry_64.png"));
            int select = JOptionPane.showOptionDialog(this, "Server Connection Failed!!\n"+msg+"\nPlease,Retry Connection or Quit!", "Connection Failed!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_server_retry, options, options[0]);
            if(select==0){
                CustomCourseOutcomeFrame evfrm = new CustomCourseOutcomeFrame(this.idT06,this.idT01,this.token);
                evfrm.setLocationRelativeTo(null);
                evfrm.setVisible(true);
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
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        semesterComboBox = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        offeredCourseCombo = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        teacherComboBox = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        selectedCourseField = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        removeButton = new javax.swing.JButton();
        cloCount = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jCheckBox = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        deleteButton = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Custom CLO");
        setMinimumSize(new java.awt.Dimension(550, 570));
        setPreferredSize(new java.awt.Dimension(700, 680));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(234, 100));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/customCLO_64.png"))); // NOI18N
        jLabel1.setText("CUSTOM COURSE LEVEL OUTCOME");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel10.setPreferredSize(new java.awt.Dimension(270, 40));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel5.setText("Semester:");
        jPanel10.add(jLabel5);

        semesterComboBox.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        semesterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        semesterComboBox.setPreferredSize(new java.awt.Dimension(150, 30));
        semesterComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semesterComboBoxActionPerformed(evt);
            }
        });
        jPanel10.add(semesterComboBox);

        jPanel5.add(jPanel10, java.awt.BorderLayout.WEST);

        jPanel11.setPreferredSize(new java.awt.Dimension(260, 45));

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel6.setText("Offered Course:");
        jPanel11.add(jLabel6);

        offeredCourseCombo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        offeredCourseCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        offeredCourseCombo.setPreferredSize(new java.awt.Dimension(150, 30));
        offeredCourseCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offeredCourseComboActionPerformed(evt);
            }
        });
        jPanel11.add(offeredCourseCombo);

        jPanel5.add(jPanel11, java.awt.BorderLayout.EAST);

        jPanel14.setPreferredSize(new java.awt.Dimension(100, 50));

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel8.setText("Teacher");
        jPanel14.add(jLabel8);

        teacherComboBox.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        teacherComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        teacherComboBox.setPreferredSize(new java.awt.Dimension(180, 30));
        teacherComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherComboBoxActionPerformed(evt);
            }
        });
        jPanel14.add(teacherComboBox);

        jPanel5.add(jPanel14, java.awt.BorderLayout.NORTH);

        jPanel2.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(10, 50));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setText("Selected Course:");
        jPanel3.add(jLabel4);

        selectedCourseField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        selectedCourseField.setFocusable(false);
        selectedCourseField.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel3.add(selectedCourseField);

        jPanel7.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel3.add(jPanel7);

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("Number of CLO: ");
        jPanel3.add(jLabel2);

        removeButton.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        removeButton.setText("-");
        removeButton.setPreferredSize(new java.awt.Dimension(30, 30));
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        jPanel3.add(removeButton);

        cloCount.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cloCount.setText("0");
        cloCount.setFocusable(false);
        cloCount.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel3.add(cloCount);

        addButton.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        addButton.setText("+");
        addButton.setPreferredSize(new java.awt.Dimension(30, 30));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel3.add(addButton);

        jPanel4.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("***Give \"X\" or any value to Connect");
        jPanel9.add(jLabel3);

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(205, 0, 0));
        jLabel7.setText("(Default CLO-PLO)");
        jPanel9.add(jLabel7);

        jPanel8.add(jPanel9, java.awt.BorderLayout.NORTH);

        jTable.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable);

        jPanel8.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel6.setPreferredSize(new java.awt.Dimension(10, 70));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 20));

        jCheckBox.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jCheckBox.setText("Custom CLO-PLO");
        jCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckBox);

        jPanel13.setPreferredSize(new java.awt.Dimension(70, 10));
        jPanel6.add(jPanel13);

        deleteButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        deleteButton.setText("DELETE");
        deleteButton.setPreferredSize(new java.awt.Dimension(130, 30));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jPanel6.add(deleteButton);

        jPanel12.setPreferredSize(new java.awt.Dimension(100, 10));
        jPanel6.add(jPanel12);

        saveButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.setPreferredSize(new java.awt.Dimension(130, 30));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jPanel6.add(saveButton);

        jPanel2.add(jPanel6, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        
        boolean row_empty = true;
        boolean col_empty = true;
        int row_count = jTable.getRowCount();
        int col_count = jTable.getColumnCount();
        int t15Index = offeredCourseCombo.getSelectedIndex();
        for(int i=0;i<row_count;i++){
            row_empty = true;
            for(int j=1;j<col_count;j++){
                if(jTable.getValueAt(i, j) !=null && !jTable.getValueAt(i, j).toString().trim().isBlank()){
                    row_empty = false;
                    break;
                }
            }
            if (row_empty) {
                ImageIcon data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));
                JOptionPane.showMessageDialog(this, "Row is Empty!", "Data Error!", JOptionPane.ERROR_MESSAGE, data_error);
                return;
            }
        }
        
        for(int i=0;i<col_count;i++){
            
            col_empty = true;
            for(int j=1;j<row_count;j++){
                if(jTable.getValueAt(j, i) !=null && !jTable.getValueAt(j, i).toString().trim().isBlank()){
                    col_empty = false;
                    break;
                }
            }
            if (col_empty) {
                ImageIcon data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));
                JOptionPane.showMessageDialog(this, "Column Empty, PLO is Missing!", "Data Error!", JOptionPane.ERROR_MESSAGE, data_error);
                return;
            }
        }
        
        if(idT07.equals("None!") || T15_id[0].equals("None!")){
            ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
            JOptionPane.showMessageDialog(this, "Major Error in DataBase!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            return;
        }
        
        
        try{
            //password protected
            String checkTable = "";
            switch (profile) {
                case 0:
                    checkTable = "employee_login";
                    break;
                case 2:
                    checkTable = "admin";
                    break;
                default:
                    checkTable = "";
                    break;
            }
            ImageIcon errorx = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
            JPasswordField passwordField = new JPasswordField();
            ImageIcon enter_pass = new ImageIcon(getClass().getResource("/icon/password_enter_64.png"));
            int option = JOptionPane.showConfirmDialog(
                    null,
                    passwordField,
                    "Enter your password",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, enter_pass
            );

            if (option == JOptionPane.OK_OPTION) {
                char[] passwordPlain = passwordField.getPassword();
                String passwordEncrypt = this.encryptPass(passwordPlain);
                Arrays.fill(passwordPlain, '\0');
                if (!checkTable.trim().isBlank()) {
                    if (profile == 2) {
                        ps = connect.prepareStatement("SELECT * FROM `" + checkTable + "` WHERE ID = ? AND password = ?");
                        ps.setString(1, id);
                        ps.setString(2, passwordEncrypt);

                    } else {
                        ps = connect.prepareStatement("SELECT * FROM `" + checkTable + "` WHERE ID = ? AND password = ? AND university_id = ?");
                        ps.setString(1, id);
                        ps.setString(2, passwordEncrypt);
                        ps.setString(3, uni_id);
                    }

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {

                    } else {
                        JOptionPane.showMessageDialog(this, "Password Didn't Match!", "Error!", JOptionPane.ERROR_MESSAGE, errorx);
                        return;
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Major error in Integrity of Token!", "Error!", JOptionPane.ERROR_MESSAGE, errorx);
                    return;
                }

            } else {
                ImageIcon warning = new ImageIcon(getClass().getResource("/icon/warning_64.png"));
                JOptionPane.showMessageDialog(this, "Need to Enter Password!", "Warning!", JOptionPane.ERROR_MESSAGE, warning);
                return;

            }
            
            
            connect.setAutoCommit(false);
            
            
            
            ps = connect.prepareStatement("DELETE FROM `t10_course_clo` WHERE T07_id_fk = ? AND T15_id_fk= ?");
            ps.setString(1, idT07);
            ps.setString(2, T15_id[t15Index]);
            ps.executeUpdate();
            
            
            
            for(int i=0,k=0;i<row_count;i++){
                //inserting CLO
                String current_idT10=null;
                if((!prev_T10_id[0].equals("None!")) && prev_T10_id.length>i){
                    ps = connect.prepareStatement("INSERT INTO `t10_course_clo`(`T10_id`, `T07_id_fk`, `clo_number`, `T15_id_fk`) VALUES (? ,? , ?, ?)");
                    ps.setString(1, prev_T10_id[i]);
                    ps.setString(2, idT07);
                    ps.setString(3, String.valueOf(i+1));
                    ps.setString(4, T15_id[t15Index]);
                    ps.executeUpdate();
                    current_idT10 = prev_T10_id[i];
                }else{
                    ps = connect.prepareStatement("INSERT INTO `t10_course_clo`(`T07_id_fk`, `clo_number`, `T15_id_fk`) VALUES (? , ?, ?)");
                    ps.setString(1, idT07);
                    ps.setString(2, String.valueOf(i+1));
                    ps.setString(3, T15_id[t15Index]);
                    ps.executeUpdate();
                    
                    //getting current T10id
                    ps = connect.prepareStatement("SELECT T10_id FROM `t10_course_clo` WHERE T07_id_fk = ? AND clo_number = ? AND T15_id_fk = ?");
                    ps.setString(1, idT07);
                    ps.setString(2, String.valueOf(i+1));
                    ps.setString(3, T15_id[t15Index]);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        current_idT10 = rs.getString(1);
                    }else{
                        throw new SQLException ("Major Error getting current idT10!");
                    }
                    
                }
                //connecting CLO and PLO
                for(int j=1;j<col_count;j++){
                    if(jTable.getValueAt(i, j) !=null && !jTable.getValueAt(i, j).toString().trim().isBlank()){
                        if ((!prev_T13_id[0].equals("None!")) && prev_T13_id.length > k) {
                            ps = connect.prepareStatement("INSERT INTO `t13_clo_plo_connect`(`T13_id`, `T10_id_fk`, `T09_id_fk`)"
                                    + " VALUES (?,?,?)");
                            ps.setString(1, prev_T13_id[k]);
                            ps.setString(2, current_idT10);
                            ps.setString(3, T09_id[j-1]);
                            ps.executeUpdate();
                            k++;
                        }else{
                            ps = connect.prepareStatement("INSERT INTO `t13_clo_plo_connect`(`T10_id_fk`, `T09_id_fk`)"
                                    + " VALUES (?,?)");
                            ps.setString(1, current_idT10);
                            ps.setString(2, T09_id[j-1]);
                            ps.executeUpdate();
                        }
                    }
                    
                }
                
                
            
            }
            
            
            
            
            connect.commit();
            connect.setAutoCommit(true);
            ImageIcon success = new ImageIcon(getClass().getResource("/icon/success_64.png"));
            JOptionPane.showMessageDialog(this, "Custom CLO to PLO connect Success!", "Success!", JOptionPane.ERROR_MESSAGE, success);
            
            saveButton.setEnabled(false);
            deleteButton.setEnabled(false);
            selectedCourseField.setText("");
            cloCount.setText("0");
            DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
            dModel.setRowCount(0);
            jTable.setModel(dModel);
            jTable.revalidate();
            jTable.repaint();
            jCheckBox.setSelected(false);
            offeredCourseCombo.setSelectedIndex(t15Index);
            
            
            
            
        }catch (SQLException ex) {
            try {
                connect.rollback();
                connect.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(CustomCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(CustomCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(CustomCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            int code = ex.getErrorCode();
            String msg = "Error Code: "+code;
            String [] options = {"OK"};
            ImageIcon icon_server_error = new ImageIcon(getClass().getResource("/icon/server_error_2_64.png"));
            ImageIcon icon_data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));

            if(code==0){
                this.reConnection();
                int select = JOptionPane.showOptionDialog(this, "Error Server Connection!!\n"+msg+"\nTry Again!", "Connection Failed!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_server_error, options, options[0]);
                if (select == JOptionPane.CLOSED_OPTION || select == 0){
                    this.reConnection();
                }
            }else{
                JOptionPane.showOptionDialog(this, "Error in Data Entry!!\n"+msg+"\nTry Again!", "Data Entry Failed!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_data_error, options, options[0]);
            }
        }
        
        

        
    }//GEN-LAST:event_saveButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        mfrm.setEnabled(true);
        mfrm.setAlwaysOnTop(true);
        mfrm.setAlwaysOnTop(false);

    }//GEN-LAST:event_formWindowClosed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
        int row_count = Integer.parseInt(cloCount.getText());
        if(row_count>1){
            row_count--;
            DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
            dModel.removeRow(jTable.getRowCount()-1);
            jTable.setModel(dModel);
            //makeEditable();
            jTable.revalidate();
            jTable.repaint();
            cloCount.setText(String.valueOf(row_count));
        }
    }//GEN-LAST:event_removeButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        int row_count = Integer.parseInt(cloCount.getText());
        int col_count = jTable.getColumnCount();
        row_count++;

        DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
        Object [] temp = new Object [col_count];
        temp [0] = (Object) "CLO"+String.valueOf(row_count);
        for(int i=1;i<col_count;i++){
            temp[i] = (Object)"";
        }
        dModel.addRow(temp);
        jTable.setModel(dModel);
        //makeEditable();
        jTable.revalidate();
        jTable.repaint();
        cloCount.setText(String.valueOf(row_count));
    }//GEN-LAST:event_addButtonActionPerformed

    private void semesterComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semesterComboBoxActionPerformed
        // TODO add your handling code here:
        
        String [] offered_none = {"None!"};
        offeredCourseCombo.setEnabled(false);
        offeredCourseCombo.setModel(new DefaultComboBoxModel(offered_none));
        
        if(semesterComboBox.getItemCount()==0){
            semesterComboBox.setEnabled(false);
            return;
        }
        if(semesterComboBox.getSelectedItem().toString().equals("None!")){
            semesterComboBox.setEnabled(false);
            return;
        }
        
        int t14_index = semesterComboBox.getSelectedIndex();
        int t16_index = teacherComboBox.getSelectedIndex();
        T15_id = new String [1];
        T15_id[0]= "None!";
        try {
            ps = connect.prepareStatement("SELECT t15_offered_course.T15_id,t07_course.course_id "
                    + "FROM `t15_offered_course`JOIN t07_course ON t15_offered_course.T07_id_fk = t07_course.T07_id "
                    + "WHERE t15_offered_course.T14_id_fk = ? AND t15_offered_course.T16_id_fk = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, T14_id[t14_index]);
            ps.setString(2, T16_id[t16_index]);
            ResultSet rs = ps.executeQuery();
            rs.last();
            int temp_count = rs.getRow();
            rs.beforeFirst();

            if (temp_count > 0) {
                T15_id = new String[temp_count];
                String[] temp = new String[temp_count];
                for (int i = 0; rs.next() && i < T15_id.length; i++) {
                    T15_id[i] = rs.getString(1);
                    temp[i] = rs.getString(2);
                }
                offeredCourseCombo.setModel(new DefaultComboBoxModel(temp));
                offeredCourseCombo.setEnabled(true);
                offeredCourseCombo.setSelectedIndex(0);

            } else {
                ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                JOptionPane.showMessageDialog(this, "Course Doesn't Exist", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;

            }
        } catch (SQLException ex) {
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
            
    }//GEN-LAST:event_semesterComboBoxActionPerformed

    private void offeredCourseComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offeredCourseComboActionPerformed
        // TODO add your handling code here:
        saveButton.setEnabled(false);
        deleteButton.setEnabled(false);
        
        if(offeredCourseCombo.getItemCount()==0){
            offeredCourseCombo.setEnabled(false);
            return;
        }
        if(offeredCourseCombo.getSelectedItem().toString().equals("None!") || T15_id[0].equals("None!")){
            offeredCourseCombo.setEnabled(false);
            return;
        }
        
        String course_id = offeredCourseCombo.getSelectedItem().toString();
        int t15Index = offeredCourseCombo.getSelectedIndex();
        int t14Index = semesterComboBox.getSelectedIndex();
        idT07 = "None!";
        save_status = false;
        delete_status = false;
        T09_id = new String [1];
        T09_id[0] = "None!";
        prev_T10_id = new String [1];
        prev_T10_id[0] = "None!";
        prev_T13_id = new String [1];
        prev_T13_id[0] = "None!";
        boolean default_status;
        idT07 = "None!";
        //String [] table_clo;
        try{
            
            // get T07id
            ps = connect.prepareStatement("SELECT T07_id_fk FROM `t15_offered_course` WHERE T15_id = ?");
            ps.setString(1, T15_id[t15Index] );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                idT07 = rs.getString(1);
            }else{
                ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                JOptionPane.showMessageDialog(this, "Major Error in DataBase!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            //get PLOs for the course set by the syllabus
            String []columnNames = {"None!"};
            ps = connect.prepareStatement("SELECT DISTINCT t09_syllabus_plo.T09_id,t09_syllabus_plo.plo_id "
                    + "FROM (SELECT t13_clo_plo_connect.T09_id_fk FROM t13_clo_plo_connect JOIN t10_course_clo ON t13_clo_plo_connect.T10_id_fk = t10_course_clo.T10_id"
                    + " WHERE t10_course_clo.T07_id_fk = ? AND t10_course_clo.T15_id_fk = 0) AS plo_connect JOIN t09_syllabus_plo ON plo_connect.T09_id_fk = t09_syllabus_plo.T09_id "
                    + "ORDER BY t09_syllabus_plo.plo_id ASC",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT07);
            rs = ps.executeQuery();
            rs.last();
            int column_count = rs.getRow()+1;
            rs.beforeFirst();
            if(column_count>1){
                columnNames = new String [column_count];
                columnNames[0] = "X";
                T09_id = new String[column_count-1];
                for(int i=0;rs.next() && i<T09_id.length;i++){
                    T09_id[i] = rs.getString(1);
                    columnNames[i+1]= "PLO"+rs.getString(2);
                }
                tableSettings(columnNames);
                
            }else{
                ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                JOptionPane.showMessageDialog(this, "Major Error in DataBase!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            
            
            
            //Custom CLO
            ps = connect.prepareStatement("SELECT clo_table.T10_id,clo_table.clo_number, plo_connect.T09_id_fk, plo_connect.T13_id"
                    + " FROM (SELECT T10_id,clo_number FROM `t10_course_clo` WHERE T07_id_fk = ? AND T15_id_fk = ?) AS clo_table"
                    + " LEFT JOIN t13_clo_plo_connect AS plo_connect ON clo_table.T10_id = plo_connect.T10_id_fk"
                    + " ORDER BY clo_number ASC",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT07);
            ps.setString(2, T15_id[t15Index]);
            rs = ps.executeQuery();
            rs.last();
            int T10_length = rs.getRow();
            rs.beforeFirst();
            if (T10_length == 0) {
                //Default CLO
                ps = connect.prepareStatement("SELECT clo_table.T10_id,clo_table.clo_number, plo_connect.T09_id_fk, plo_connect.T13_id"
                        + " FROM (SELECT T10_id,clo_number FROM `t10_course_clo` WHERE T07_id_fk = ? AND T15_id_fk = 0) AS clo_table"
                        + " LEFT JOIN t13_clo_plo_connect AS plo_connect ON clo_table.T10_id = plo_connect.T10_id_fk"
                        + " ORDER BY clo_number ASC",
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ps.setString(1, idT07);
                rs = ps.executeQuery();
                rs.last();
                T10_length = rs.getRow();
                rs.beforeFirst();
                default_status = true;
                jLabel7.setText("Default CLO-PLO");
            }else{
                default_status = false;
                jLabel7.setText("Custom CLO-PLO");
            }
            
            if(T10_length>0){
                String [] temp_T10_id = new String [T10_length];
                String [] temp_T13_id = new String [T10_length];
                int temp_T10length = 0;
                int temp_T13length = 0;
                //table_clo = new String [T10_length];
                Object [][] dataTable = new String [T10_length][jTable.getColumnCount()];
                for (int i=0,flag=0;rs.next();   ){
                    //System.out.println("CLO"+rs.getString(2).trim());
                    if(flag!=0){
                        if(!temp_T10_id[i].equals(rs.getString(1))){
                            i++;
                            temp_T10length++;
                            temp_T10_id[i] = rs.getString(1);
                            dataTable[i][0] = "CLO"+rs.getString(2).trim();
                            //System.out.println("CLO"+rs.getString(2).trim());
                            //table_clo[i] = "CLO"+rs.getString(2).trim();
                        }
                    }else{
                        temp_T10_id[i] = rs.getString(1);
                        dataTable[i][0] = "CLO"+rs.getString(2).trim();
                        flag++;
                        temp_T10length++;
                        //table_clo[i] = "CLO"+rs.getString(2).trim();
                    }
                    
                    if(!rs.getString(3).trim().isBlank()){
                        String tempT09 = rs.getString(3);
                        for(int j=0;j<T09_id.length;j++){
                            if(T09_id[j].equals(tempT09)){
                                dataTable [i][j+1] = "X";
                                break;
                            }
                        }
                    }
                    if(!rs.getString(4).trim().isBlank()){
                        if(temp_T13length>0){
                            if(!temp_T13_id[temp_T13length-1].equals(rs.getString(4))){
                                temp_T13_id[temp_T13length] = rs.getString(4);
                                temp_T13length++;
                            }
                        }else{
                            temp_T13_id[temp_T13length] = rs.getString(4);
                            temp_T13length++;
                        }
                    }
                    
                    
                }
                if(temp_T10length>0){
                    prev_T10_id = new String[temp_T10length];
                    for(int i=0;i<prev_T10_id.length;i++){
                        prev_T10_id[i] = temp_T10_id[i];
                    }
                }
                if(temp_T13length>0){
                    prev_T13_id = new String[temp_T13length];
                    for(int i=0;i<prev_T13_id.length;i++){
                        prev_T13_id[i] = temp_T13_id[i];
                    }
                }
                
                
                
                DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
                dModel.setRowCount(0);
                for(int i=0;i<prev_T10_id.length;i++){
                    dModel.addRow(dataTable[i]);
                }
                jTable.setModel(dModel);
                jTable.revalidate();
                jTable.repaint();
                cloCount.setText(String.valueOf(prev_T10_id.length));
                selectedCourseField.setText(course_id);
                
                
                if(profile==2){
                    save_status = true;
                    if(!default_status){
                        delete_status = true;
                        jCheckBox.setSelected(true);
                        for (ActionListener al : jCheckBox.getActionListeners()) {
                            al.actionPerformed(new ActionEvent(jCheckBox, ActionEvent.ACTION_PERFORMED, jCheckBox.getActionCommand()));
                        }
                    }else{
                        prev_T10_id = new String[1];
                        prev_T10_id[0] = "None!";
                        prev_T13_id = new String[1];
                        prev_T13_id[0] = "None!";
                        
                        delete_status = false;
                        jCheckBox.setSelected(false);
                        for (ActionListener al : jCheckBox.getActionListeners()) {
                            al.actionPerformed(new ActionEvent(jCheckBox, ActionEvent.ACTION_PERFORMED, jCheckBox.getActionCommand()));
                        }
                    }
                }else{
                    ps = connect.prepareStatement("SELECT semester_status FROM `t14_semester` WHERE T14_id = ?");
                    ps.setString(1, T14_id[t14Index]);
                    rs = ps.executeQuery();
                    rs.next();
                    if(rs.getInt(1)==0){
                        save_status = true;
                        if(!default_status){
                            delete_status = true;
                            jCheckBox.setSelected(true);
                            for (ActionListener al : jCheckBox.getActionListeners()) {
                                al.actionPerformed(new ActionEvent(jCheckBox, ActionEvent.ACTION_PERFORMED, jCheckBox.getActionCommand()));
                            }
                        }else{
                            prev_T10_id = new String[1];
                            prev_T10_id[0] = "None!";
                            prev_T13_id = new String[1];
                            prev_T13_id[0] = "None!";
                            
                            delete_status = false;
                            jCheckBox.setSelected(false);
                            for (ActionListener al : jCheckBox.getActionListeners()) {
                                al.actionPerformed(new ActionEvent(jCheckBox, ActionEvent.ACTION_PERFORMED, jCheckBox.getActionCommand()));
                            }
                        }
                    }else{
                        save_status = false;
                        if(!default_status){
                            delete_status = false;
                            jCheckBox.setSelected(true);
                            for (ActionListener al : jCheckBox.getActionListeners()) {
                                al.actionPerformed(new ActionEvent(jCheckBox, ActionEvent.ACTION_PERFORMED, jCheckBox.getActionCommand()));
                            }
                        }else{
                            prev_T10_id = new String[1];
                            prev_T10_id[0] = "None!";
                            prev_T13_id = new String[1];
                            prev_T13_id[0] = "None!";
                            
                            delete_status = false;
                            jCheckBox.setSelected(false);
                            for (ActionListener al : jCheckBox.getActionListeners()) {
                                al.actionPerformed(new ActionEvent(jCheckBox, ActionEvent.ACTION_PERFORMED, jCheckBox.getActionCommand()));
                            }
                        }
                    }
                }
                
                
            }else{
                ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                JOptionPane.showMessageDialog(this, "Major Error in DataBase!\nShould Delete Syllabus and Start New!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                selectedCourseField.setText(course_id);
                saveButton.setEnabled(false);
                return;
            }
            
            
            
            
        }catch (SQLException ex) {
            
            Logger.getLogger(CustomCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(CustomCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
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
                JOptionPane.showOptionDialog(this, "Error in Data Retrieval!!\n" + msg + "\nTry Again!", "Data Retrieval Failed!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_data_error, options, options[0]);
            }
        }
        
        
    }//GEN-LAST:event_offeredCourseComboActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        boolean row_empty = true;
        
        if(idT07.equals("None!") || T15_id[0].equals("None!")){
            ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
            JOptionPane.showMessageDialog(this, "Major Error in DataBase!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            return;
        }
        
        int t15Index = offeredCourseCombo.getSelectedIndex();
        try{
            //password protected
            String checkTable = "";
            switch (profile) {
                case 0:
                    checkTable = "employee_login";
                    break;
                case 2:
                    checkTable = "admin";
                    break;
                default:
                    checkTable = "";
                    break;
            }
            ImageIcon errorx = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
            JPasswordField passwordField = new JPasswordField();
            ImageIcon enter_pass = new ImageIcon(getClass().getResource("/icon/password_enter_64.png"));
            int option = JOptionPane.showConfirmDialog(
                    null,
                    passwordField,
                    "Enter your password",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, enter_pass
            );

            if (option == JOptionPane.OK_OPTION) {
                char[] passwordPlain = passwordField.getPassword();
                String passwordEncrypt = this.encryptPass(passwordPlain);
                Arrays.fill(passwordPlain, '\0');
                if (!checkTable.trim().isBlank()) {
                    if (profile == 2) {
                        ps = connect.prepareStatement("SELECT * FROM `" + checkTable + "` WHERE ID = ? AND password = ?");
                        ps.setString(1, id);
                        ps.setString(2, passwordEncrypt);

                    } else {
                        ps = connect.prepareStatement("SELECT * FROM `" + checkTable + "` WHERE ID = ? AND password = ? AND university_id = ?");
                        ps.setString(1, id);
                        ps.setString(2, passwordEncrypt);
                        ps.setString(3, uni_id);
                    }

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {

                    } else {
                        JOptionPane.showMessageDialog(this, "Password Didn't Match!", "Error!", JOptionPane.ERROR_MESSAGE, errorx);
                        return;
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Major error in Integrity of Token!", "Error!", JOptionPane.ERROR_MESSAGE, errorx);
                    return;
                }

            } else {
                ImageIcon warning = new ImageIcon(getClass().getResource("/icon/warning_64.png"));
                JOptionPane.showMessageDialog(this, "Need to Enter Password!", "Warning!", JOptionPane.ERROR_MESSAGE, warning);
                return;

            }
            
            
            
            
            
            
            connect.setAutoCommit(false);
            
            
            
            ps = connect.prepareStatement("DELETE FROM `t10_course_clo` WHERE T07_id_fk = ? AND T15_id_fk= ?");
            ps.setString(1, idT07);
            ps.setString(2, T15_id[t15Index]);
            if(ps.executeUpdate()<=0){
                ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                JOptionPane.showMessageDialog(this, "No Data to Delete!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            }else{
                ImageIcon success = new ImageIcon(getClass().getResource("/icon/success_64.png"));
                JOptionPane.showMessageDialog(this, "Custom CLO to PLO Delete Successful!", "Success!", JOptionPane.ERROR_MESSAGE, success);
            }
            
            
            
            
            
            
            connect.commit();
            connect.setAutoCommit(true);
            
            saveButton.setEnabled(false);
            deleteButton.setEnabled(false);
            selectedCourseField.setText("");
            cloCount.setText("0");
            DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
            dModel.setRowCount(0);
            jTable.setModel(dModel);
            jTable.revalidate();
            jTable.repaint();
            jCheckBox.setSelected(false);
            offeredCourseCombo.setSelectedIndex(t15Index);
            
            
            
        }catch (SQLException ex) {
            try {
                connect.rollback();
                connect.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(CustomCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(CustomCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(CustomCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            int code = ex.getErrorCode();
            String msg = "Error Code: "+code;
            String [] options = {"OK"};
            ImageIcon icon_server_error = new ImageIcon(getClass().getResource("/icon/server_error_2_64.png"));
            ImageIcon icon_data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));

            if(code==0){
                this.reConnection();
                int select = JOptionPane.showOptionDialog(this, "Error Server Connection!!\n"+msg+"\nTry Again!", "Connection Failed!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_server_error, options, options[0]);
                if (select == JOptionPane.CLOSED_OPTION || select == 0){
                    this.reConnection();
                }
            }else{
                JOptionPane.showOptionDialog(this, "Error in Data Entry!!\n"+msg+"\nTry Again!", "Data Entry Failed!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_data_error, options, options[0]);
            }
        }
        
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void teacherComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherComboBoxActionPerformed
        // TODO add your handling code here:
        T14_id = new String [1];
        T14_id[0] = "None!";
        String [] semester_none = {"None!"};
        semesterComboBox.setEnabled(false);
        semesterComboBox.setModel(new DefaultComboBoxModel(semester_none));
        if(T16_id[0].equals("None!")){
            teacherComboBox.setEnabled(false);
            semesterComboBox.setEnabled(false);
            idT16="None!";
            return;
        }
        
        int index = teacherComboBox.getSelectedIndex();
        
        try {
            
            if (profile == 0 || job_id == 2){
                ps = connect.prepareStatement("SELECT DISTINCT t14_semester.T14_id,t14_semester.semester_id,t14_semester.semester_name"
                    + " FROM (SELECT t16_employee.T16_id,t16_employee.employee_id,t16_employee.employee_name,t15_offered_course.T14_id_fk"
                    + " FROM t16_employee JOIN t15_offered_course ON t16_employee.T16_id = t15_offered_course.T16_id_fk "
                    + "WHERE t16_employee.T16_id = ? ) AS xyz JOIN t14_semester ON t14_semester.T14_id = xyz.T14_id_fk "
                    + "WHERE t14_semester.T06_id_fk=? AND t14_semester.semester_status<2",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            }else{
                ps = connect.prepareStatement("SELECT DISTINCT t14_semester.T14_id,t14_semester.semester_id,t14_semester.semester_name"
                    + " FROM (SELECT t16_employee.T16_id,t16_employee.employee_id,t16_employee.employee_name,t15_offered_course.T14_id_fk"
                    + " FROM t16_employee JOIN t15_offered_course ON t16_employee.T16_id = t15_offered_course.T16_id_fk "
                    + "WHERE t16_employee.T16_id = ? ) AS xyz JOIN t14_semester ON t14_semester.T14_id = xyz.T14_id_fk "
                    + "WHERE t14_semester.T06_id_fk=? ",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            }
            
            
            ps.setString(1, T16_id[index]);
            ps.setString(2, idT06);
            ResultSet rs = ps.executeQuery();
            
            rs.last();
            int count = rs.getRow();
            rs.beforeFirst();
            if (count > 0) {
                
                T14_id = new String[count];
                String [] comboArray = new String [count];
                for (int i = 0; rs.next() && i < T14_id.length; i++) {

                    T14_id[i] = rs.getString(1);
                    String[] temp_name = rs.getString(3).trim().split(" ");
                    comboArray[i] = rs.getString(2) + "--" + temp_name[0];

                }
                
                
                semesterComboBox.setModel(new DefaultComboBoxModel(comboArray));
                semesterComboBox.setEnabled(true);
                semesterComboBox.setSelectedIndex(0);
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(EvaluationSetFrame.class.getName()).log(Level.SEVERE, null, ex);
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
    }//GEN-LAST:event_teacherComboBoxActionPerformed

    private void jCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActionPerformed
        // TODO add your handling code here:
        boolean selected = jCheckBox.isSelected();
        //System.out.println("FIRE");
        if(selected){
            saveButton.setEnabled(save_status);
            deleteButton.setEnabled(delete_status);
        }else{
            saveButton.setEnabled(false);
            deleteButton.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBoxActionPerformed

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
            java.util.logging.Logger.getLogger(CustomCourseOutcomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomCourseOutcomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomCourseOutcomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomCourseOutcomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new CustomCourseOutcomeFrame("default","default","default").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField cloCount;
    private javax.swing.JButton deleteButton;
    private javax.swing.JCheckBox jCheckBox;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JComboBox<String> offeredCourseCombo;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField selectedCourseField;
    private javax.swing.JComboBox<String> semesterComboBox;
    private javax.swing.JComboBox<String> teacherComboBox;
    // End of variables declaration//GEN-END:variables
}

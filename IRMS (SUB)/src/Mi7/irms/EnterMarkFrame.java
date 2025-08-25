/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Mi7.irms;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
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
//customTableModel
 class CustomTableModel extends DefaultTableModel {
    private final Set<Point> nonEditableCells = new HashSet<>(); // keeps track of cell editable status

    public CustomTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 0){
            return false;
        }
        if(column == 1){
            return true;
        }
        return !nonEditableCells.contains(new Point(row, column));
    }

    public void setRowNonEditable(int row) {
        for (int col = 2; col < getColumnCount(); col++) {
            nonEditableCells.add(new Point(row, col));
        }
    }

    public void setRowEditable(int row) {
        for (int col = 2; col < getColumnCount(); col++) {
            nonEditableCells.remove(new Point(row, col));
        }
    }
}


public class EnterMarkFrame extends javax.swing.JFrame {

    /**
     * Creates new form EnterUniversity
     */
    Connection connect;
    
    
    //String uni_id="";
    String [] T03_id = new String [100];
    String idT06 = "";
    String idT01 = "";
    String idT14 = "";
    String idT16 = "";
    String syllabus_id = "";
    String [] [] T18_info = {{"None!"}};
    String [] T15_id = {"None!"};
    String [] prev_T22_id = {"None!"};
    String [] [] T19_info = {{"None!"}};
    String [] T20_id = {"None!"};
    String token = "";
    String id = "";
    String uni_id = "";
    int profile = -1;
    String [] columnNames;
    
    
    MarkDistributionFrame mdisfrm;
    
    
    
    void passMarkDistributionFrame(MarkDistributionFrame mdisfrm){
        this.mdisfrm = mdisfrm;
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
    
    
    
    
    public EnterMarkFrame(String idT06,String idT01,String idT14,String idT16,String token) {
        initComponents();
        this.idT06 = idT06;
        this.idT01 = idT01;
        this.idT14 = idT14;
        this.idT16 = idT16;
        this.token = token;
        //System.out.println(idT01 +" " +idT06+" " + idT14);
        
        jLabel5.setVisible(false);
        jLabel4.setVisible(false);
        jTable.setEnabled(false);
        JTableHeader header = jTable.getTableHeader();
        header.setReorderingAllowed(false);
        jTable.setShowGrid(true);
        
        
        
        

        jTable.revalidate();
        jTable.repaint();
        
        
        
        
        T15_id = new String[1];
        T15_id[0] = "None!";
        T18_info = new String [1][4];
        for(int i=0;i<4;i++){
            T18_info[0][i] = "None!";
        }
        T19_info = new String [1][4];
        for(int i=0;i<4;i++){
            T19_info[0][i] = "None!";
        }
        T15_id = new String [1];
        T15_id[0]= "None!";
        T20_id = new String [1];
        T20_id[0]= "None!";
        prev_T22_id = new String[1];
        prev_T22_id [0] = "None!";
        
        
        String [] tempToken = token.split(" ");
        id = tempToken[0];
        uni_id = tempToken[1];
        profile = Integer.parseInt(tempToken[2]);
        
        
        
        courseComboBox.setEnabled(false);
        examComboBox.setEnabled(false);
        jTable.setEnabled(false);
        finalizeButton.setEnabled(false);
        saveButton.setEnabled(false);
        
        
        totalMark.setEditable(false);
        
        
        
        
        try{
            
            PreparedStatement ps;
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            connect.setAutoCommit(true);
            
            ps = connect.prepareStatement("SELECT employee_id FROM `t16_employee` WHERE T16_id = ?");
            ps.setString(1, idT16);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                jLabel1.setText("ENTER MARK FOR: "+rs.getString(1));
            }
            
            //course
            ps = connect.prepareStatement("SELECT t15_offered_course.T15_id,t07_course.course_id "
                    + "FROM `t15_offered_course`JOIN t07_course ON t15_offered_course.T07_id_fk = t07_course.T07_id "
                    + "WHERE t15_offered_course.T14_id_fk = ? AND t15_offered_course.T16_id_fk = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT14);
            ps.setString(2, idT16);
            rs = ps.executeQuery();
            rs.last();
            int temp_count = rs.getRow();
            rs.beforeFirst();
            
            if(temp_count>0){
                T15_id = new String [temp_count];
                String [] temp = new String [temp_count];
                for(int i=0;rs.next() && i<T15_id.length;i++){
                    T15_id[i]= rs.getString(1);
                    temp[i] = rs.getString(2);
                }
                courseComboBox.setModel(new DefaultComboBoxModel(temp));
                courseComboBox.setEnabled(true);
                courseComboBox.setSelectedIndex(0);
                
            }else{
                ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                JOptionPane.showMessageDialog(this, "Course Doesn't Exist", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
        
            }
            
            
            
            
            
            
        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            String [] options = {"Retry","Quit"};
            ImageIcon icon_server_retry = new ImageIcon(getClass().getResource("/icon/server_retry_64.png"));
            int select = JOptionPane.showOptionDialog(null, "Server Connection Failed!!\n"+msg+"\nPlease,Retry Connection or Quit!", "Connection Failed!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_server_retry, options, options[0]);
            if(select==0){
                EnterMarkFrame rtfrm = new EnterMarkFrame(this.idT06,this.idT01,this.idT14,this.idT16,this.token);
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
        courseComboBox = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        examComboBox = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        totalMark = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        finalizeButton = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Enter University");
        setPreferredSize(new java.awt.Dimension(900, 625));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(234, 80));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mark_distribution_64.png"))); // NOI18N
        jLabel1.setText("ENTER MARK FOR");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(10, 50));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("Course:");
        jPanel5.add(jLabel2);

        courseComboBox.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        courseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        courseComboBox.setPreferredSize(new java.awt.Dimension(150, 30));
        courseComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseComboBoxActionPerformed(evt);
            }
        });
        jPanel5.add(courseComboBox);

        jPanel16.setPreferredSize(new java.awt.Dimension(60, 10));
        jPanel5.add(jPanel16);

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel7.setText("EXAM:");
        jPanel5.add(jLabel7);

        examComboBox.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        examComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        examComboBox.setPreferredSize(new java.awt.Dimension(150, 30));
        examComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examComboBoxActionPerformed(evt);
            }
        });
        jPanel5.add(examComboBox);

        jPanel6.setPreferredSize(new java.awt.Dimension(5, 10));
        jPanel5.add(jPanel6);

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("Mark: ");
        jPanel5.add(jLabel3);

        totalMark.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        totalMark.setFocusable(false);
        totalMark.setPreferredSize(new java.awt.Dimension(50, 30));
        totalMark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalMarkActionPerformed(evt);
            }
        });
        jPanel5.add(totalMark);

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(220, 0, 0));
        jLabel4.setText("FINALIZED");
        jPanel5.add(jLabel4);

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel9.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jSeparator1.setForeground(new java.awt.Color(0, 51, 153));
        jSeparator1.setPreferredSize(new java.awt.Dimension(500, 5));
        jPanel9.add(jSeparator1, java.awt.BorderLayout.NORTH);

        jPanel10.setPreferredSize(new java.awt.Dimension(80, 20));

        jPanel17.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel10.add(jPanel17);

        jPanel9.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel8.setLayout(new java.awt.BorderLayout(10, 10));

        jTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable);

        jPanel8.add(jScrollPane1, java.awt.BorderLayout.PAGE_START);

        jPanel4.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        jSeparator2.setForeground(new java.awt.Color(0, 51, 153));
        jSeparator2.setPreferredSize(new java.awt.Dimension(500, 10));
        jPanel2.add(jSeparator2, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel11.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        finalizeButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        finalizeButton.setText("FINALIZE");
        finalizeButton.setPreferredSize(new java.awt.Dimension(120, 30));
        finalizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizeButtonActionPerformed(evt);
            }
        });
        jPanel11.add(finalizeButton);

        jPanel7.setPreferredSize(new java.awt.Dimension(150, 30));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(220, 0, 0));
        jLabel5.setText("FINALIZED");
        jPanel7.add(jLabel5);

        jPanel11.add(jPanel7);

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

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        int row_count = jTable.getRowCount();
        int exam_index = examComboBox.getSelectedIndex();
        ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
        
        
        if (row_count == 0) {
            JOptionPane.showMessageDialog(this, "Table is Empty", "Error!", JOptionPane.ERROR_MESSAGE, error);
            return;
        }
        if(T19_info[0][0].equals("None!")){
            JOptionPane.showMessageDialog(this, "No Questions!!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            return;
        }
        String[][] table_info = new String [row_count][T19_info.length];
        for(int i=0;i<table_info.length;i++){
            for(int j=0;j<table_info[i].length;j++){
                table_info[i][j] = "";
            }
        }
        
        //taking table data to 2D double array checking for invalid data
        for(int i=0;i<table_info.length;i++){
            for(int j=0;j<table_info[i].length;j++){
                if(jTable.getValueAt(i, j+2)==null){
                    table_info[i][j] = "";
                    continue;
                }
                table_info[i][j] = jTable.getValueAt(i, j+2).toString().trim();
            }
        }
        
 
        
        //taking checking for invalid data
        for (int i = 0; i < row_count; i++) {
            
            for (int j = 0; j < table_info[i].length; j++) {
                if(table_info[i][j].trim().isBlank()){
                    continue;
                }
                try {
                    if(table_info[i][j].equals("ABSENT")){
                        continue;
                    }
                    double temp_number = Double.parseDouble(table_info[i][j]);
                    if(temp_number > Double.parseDouble(T19_info[j][3]) || temp_number<0){
                        JOptionPane.showMessageDialog(this, "Marks > Total Mark for: " + jTable.getValueAt(i, 0).toString()+" at "+columnNames[j+2],
                            "Error!", JOptionPane.ERROR_MESSAGE, error);
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Input Format Error for: " + jTable.getValueAt(i, 0).toString()+" at "+columnNames[j+2],
                            "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
            }
            
        }
        
        
        

        //inserting data to table
        try {
            connect.setAutoCommit(false);
            PreparedStatement ps;
            //deleteing previous T22
            ps = connect.prepareStatement("DELETE t22_marks_obtained FROM `t22_marks_obtained` "
                    + "JOIN t19_question ON t22_marks_obtained.T19_id_fk=t19_question.T19_id "
                    + "WHERE t19_question.T18_id_fk = ?");
            
            ps.setString(1, T18_info[exam_index][0]);
            ps.executeUpdate();
            
            int temp_count = 0;
            for(int i=0;i<table_info.length;i++){
                for(int j=0;j<table_info[i].length;j++){
                    if(table_info[i][j].isBlank()){
                        continue;
                    }
                    if(temp_count<prev_T22_id.length && !prev_T22_id[0].equals("None!")){
                        ps = connect.prepareStatement("INSERT INTO `t22_marks_obtained`(`T22_id`, `T19_id_fk`, `T20_id_fk`, `marks`) "
                                + "VALUES (?,?,?,?)");
                        ps.setString(1, prev_T22_id[temp_count]);
                        ps.setString(2, T19_info[j][0]);
                        ps.setString(3, T20_id[i]);
                        if(table_info[i][j].equals("ABSENT")){
                            ps.setString(4, "-1");
                        }else{
                            ps.setString(4, table_info[i][j]);
                        }
                        
                        if(ps.executeUpdate()<=0){
                            throw new SQLException("Update Error!"); 
                        }
                        temp_count++;
                    }else{
                        ps = connect.prepareStatement("INSERT INTO `t22_marks_obtained`(`T19_id_fk`, `T20_id_fk`, `marks`) "
                                + "VALUES (?,?,?)");
                        
                        ps.setString(1, T19_info[j][0]);
                        ps.setString(2, T20_id[i]);
                        if(table_info[i][j].equals("ABSENT")){
                            ps.setString(3, "-1");
                        }else{
                            ps.setString(3, table_info[i][j]);
                        }
                        
                        if(ps.executeUpdate()<=0){
                            throw new SQLException("Update Error!"); 
                        }
                        
                    }
                }
            }
            
            
            
            ///Checking user status to make sure it can be updated
            if (!T18_info[0][0].equals("None!")) {
                if (profile == 0) {
                    ps = connect.prepareStatement("SELECT T02_id_fk FROM `t16_employee` WHERE employee_id = ? AND T01_id_fk = ?");
                    ps.setString(1, id);
                    ps.setString(2, idT01);
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    int job = Integer.parseInt(rs.getString(1));

                    if (job == 1) {
                        //employee-admin
                        ps = connect.prepareStatement("SELECT semester_status FROM `t14_semester` WHERE T14_id = ?");
                        ps.setString(1, idT14);
                        rs = ps.executeQuery();
                        rs.next();
                        int semester_status = rs.getInt(1);
                        if (semester_status == 2) {
                            throw new SQLException("Update Error!"); 
                        }
                    } else if (job == 2) {
                        //employee-teacher
                        ps = connect.prepareStatement("SELECT exam_status FROM `t18_exam` WHERE T18_id = ?");
                        ps.setString(1, T18_info[exam_index][0]);
                        rs = ps.executeQuery();
                        rs.next();
                        int exam_status = rs.getInt(1);
                        if (exam_status > 0) {
                            throw new SQLException("Update Error!");
                        }
                    }

                } 
            }
            
            
            
            connect.commit();
            connect.setAutoCommit(true);
            
            
            ImageIcon success = new ImageIcon(getClass().getResource("/icon/success_64.png"));
            JOptionPane.showMessageDialog(this, "Entry Successful!", "Success!", JOptionPane.ERROR_MESSAGE, success);
            examComboBox.setSelectedIndex(exam_index);
            
        } catch (SQLException ex) {
            try {
                connect.rollback();
                connect.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(OfferedCourseFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(EndSemesterFrame.class.getName()).log(Level.SEVERE, null, ex);
            //Logger.getLogger(EndSemesterFrame.class.getName()).log(Level.SEVERE, null, ex);

            ImageIcon icon_server_error = new ImageIcon(getClass().getResource("/icon/server_error_2_64.png"));
            ImageIcon icon_data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));

            int code = ex.getErrorCode();
            String errorM = ex.getMessage();
            String msg = "Error Code: " + code;
            String[] options = {"OK"};
            if (errorM.equals("Delete Error!") || errorM.equals("Update Error!") || errorM.equals("Insert Error!")) {
                JOptionPane.showMessageDialog(this, "Error: " + errorM, "DataBase Error!", JOptionPane.ERROR_MESSAGE, icon_data_error);

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

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        mdisfrm.setEnabled(true);
        mdisfrm.setAlwaysOnTop(true);
        mdisfrm.setAlwaysOnTop(false);
    }//GEN-LAST:event_formWindowClosed

    private void courseComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseComboBoxActionPerformed
        // TODO add your handling code here:
        int course_index = courseComboBox.getSelectedIndex();
        examComboBox.setEnabled(false);
        saveButton.setEnabled(false);
        finalizeButton.setEnabled(false);
        jTable.setEnabled(false);
        
        T18_info = new String[1][4];
        for (int i = 0; i < 4; i++) {
            T18_info[0][i] = "None!";
        }
       try{
           PreparedStatement ps = connect.prepareStatement("SELECT t18_exam.T18_id,t11_evaluation.evaluation_name,t11_evaluation.marks,t18_exam.exam_status "
                   + "FROM `t18_exam` JOIN t11_evaluation ON t18_exam.T11_id_fk = t11_evaluation.T11_id "
                   + "WHERE t18_exam.T15_id_fk = ? ORDER BY t11_evaluation.marks ASC",
                   ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
           ps.setString(1, T15_id[course_index]);
           ResultSet rs = ps.executeQuery();
           rs.last();
           int exam_length = rs.getRow();
           rs.beforeFirst();
           if(exam_length>0){
               T18_info = new String [exam_length][4];
               String[] temp = new String [exam_length];
               
               for(int i=0;rs.next() && i< T18_info.length;i++){
                   T18_info[i][0] = rs.getString(1);
                   T18_info[i][1] = rs.getString(2);
                   T18_info[i][2] = rs.getString(3);
                   T18_info[i][3] = rs.getString(4);
                   temp[i] = rs.getString(2);
               }
               
               
               
               
               
               
               examComboBox.setModel(new DefaultComboBoxModel(temp));
               examComboBox.setEnabled(true);
               examComboBox.setSelectedIndex(0);
               
           }else{
               String [] temp = {"None!"};
               examComboBox.setModel(new DefaultComboBoxModel(temp));
               examComboBox.setEnabled(false);
           }
           
           
           
       }catch (SQLException ex) {

            
            Logger.getLogger(EndSemesterFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(EndSemesterFrame.class.getName()).log(Level.SEVERE, null, ex);
            
            ImageIcon icon_server_error = new ImageIcon(getClass().getResource("/icon/server_error_2_64.png"));
            ImageIcon icon_data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));

            
            int code = ex.getErrorCode();
            String error_msg = ex.getMessage();
            String msg = "Error Code: " + code;
            String[] options = {"OK"};
            if (error_msg.equals("Delete Error!") || error_msg.equals("Update Error!") || error_msg.equals("Insert Error!") ) {
                JOptionPane.showMessageDialog(this, "Error: "+error_msg, "DataBase Error!", JOptionPane.ERROR_MESSAGE, icon_data_error);

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
    }//GEN-LAST:event_courseComboBoxActionPerformed

    private void examComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examComboBoxActionPerformed
        // TODO add your handling code here:
        boolean finalize = false;
        jTable.setEnabled(false);
        jLabel5.setVisible(false);
        jLabel4.setVisible(false);
        saveButton.setEnabled(false);
        finalizeButton.setEnabled(false);
        prev_T22_id = new String [1];
        prev_T22_id[0] = "None!";
        T19_info = new String [1][4];
        for(int i=0;i<4;i++){
            T19_info[0][i] = "None!";
        }
        T20_id = new String [1];
        T20_id[0]= "None!";
        columnNames = new String [1];
        columnNames[0]= "None!";
        if(T18_info[0][0].equals("None!")){
            String [] temp_none = {"None!"};
            DefaultTableModel dModel = (DefaultTableModel)jTable.getModel();
            dModel.setRowCount(0);
            jTable.setModel(dModel);
            jTable.revalidate();
            jTable.repaint();
            examComboBox.setModel(new DefaultComboBoxModel(temp_none));
            saveButton.setEnabled(false);
            examComboBox.setEnabled(false);            
            return;
        }
        int exam_index = examComboBox.getSelectedIndex();
        int course_index = courseComboBox.getSelectedIndex();
        totalMark.setText(T18_info[exam_index][2]);
        try{
            //which profile gets what access
            PreparedStatement ps;
            ResultSet rs;
            //Show Finalized
            ps = connect.prepareStatement("SELECT exam_status FROM `t18_exam`"
                    + " WHERE T18_id=? AND exam_status =1");
            ps.setString(1,T18_info[exam_index][0] );
            rs = ps.executeQuery();
            if(rs.next()){
                jLabel5.setVisible(true);
                jLabel4.setVisible(true);
                finalize = true;
            }else{
                jLabel5.setVisible(false);
                jLabel4.setVisible(false);
                finalize = false;
            }
            
            
            if(!T18_info[0][0].equals("None!")){
                if(profile==0){
                    ps  = connect.prepareStatement("SELECT T02_id_fk FROM `t16_employee` WHERE employee_id = ? AND T01_id_fk = ?");
                    ps.setString(1,id);
                    ps.setString(2, idT01);
                    rs = ps.executeQuery();
                    rs.next();
                    int job = Integer.parseInt(rs.getString(1));
                    //System.out.println(job+"JOB");
                    if(job == 1){
                        //employee-admin
                        ps = connect.prepareStatement("SELECT semester_status FROM `t14_semester` WHERE T14_id = ?");
                        ps.setString(1, idT14);
                        rs = ps.executeQuery();
                        rs.next();
                        int semester_status = rs.getInt(1);
                        if(semester_status==1){
                            jTable.setEnabled(true);
                            saveButton.setEnabled(true);
                            finalizeButton.setEnabled(true);
                        }else if(semester_status==2){
                            jTable.setEnabled(false);
                            saveButton.setEnabled(false);
                            finalizeButton.setEnabled(false);
                        }
                    }else if (job==2){
                        //employee-teacher
                        int exam_status = Integer.parseInt(T18_info[exam_index][3]);
                        if(exam_status==0){
                            jTable.setEnabled(true);
                            saveButton.setEnabled(true);
                            finalizeButton.setEnabled(true);
                        }else if(exam_status>0){
                            jTable.setEnabled(false);
                            saveButton.setEnabled(false);
                            finalizeButton.setEnabled(false);
                        }
                    }
                    
                }else if (profile == 2) {
                    jTable.setEnabled(true);
                    saveButton.setEnabled(true);
                    finalizeButton.setEnabled(true);
                }
            }
            
            if(finalize){
                finalizeButton.setEnabled(!finalize);
            }else{
                finalizeButton.setEnabled(!finalize);
            }
            
            
            
            //Questions Info
             ps = connect.prepareStatement("SELECT T19_id, question_no,sub_question_no ,question_mark FROM `t19_question` "
                    + "WHERE T18_id_fk = ? ORDER BY question_no ASC, sub_question_no ASC",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, T18_info[exam_index][0]);
            rs = ps.executeQuery();
            rs.last();
            int t19_length = rs.getRow();
            //System.out.println(t19_length);
            rs.beforeFirst();
            if(t19_length>0){
                
                T19_info = new String [t19_length][4];
                columnNames = new String [t19_length+2];
                columnNames[0] = "ID/QUESTION";
                columnNames[1] = "Present";
                for(int i=0;rs.next() && i<T19_info.length;i++){
                    T19_info[i][0] = rs.getString(1);//T19_id
                    T19_info[i][1] = rs.getString(2);//ques_no
                    T19_info[i][2] = rs.getString(3);//Sub_ques_No
                    T19_info[i][3] = rs.getString(4);//ques_marks
                    columnNames[i+2] = T19_info[i][1] +"/("+T19_info[i][2]+")- ("+T19_info[i][3]+")"; //Ex: 1/a(5)
                }
                
                
                //student Info for the course
                ps = connect.prepareStatement("SELECT t21_student_course_registration.T20_id_fk, t20_student.student_id"
                        + " FROM `t21_student_course_registration` JOIN t20_student ON t21_student_course_registration.T20_id_fk=t20_student.T20_id "
                        + "WHERE t21_student_course_registration.T15_id_fk = ? ORDER BY t20_student.student_id ASC",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ps.setString(1, T15_id[course_index]);
                rs = ps.executeQuery();
                rs.last();
                int student_length = rs.getRow();
                rs.beforeFirst();
                String [] studentID = {"None!"};
                if(student_length>0){
                    T20_id = new String [student_length];
                    studentID = new String [student_length];
                    for(int i=0;rs.next() && i<student_length;i++){
                        T20_id[i] = rs.getString(1);
                        studentID [i] = rs.getString(2);
                    }
                    
                }else{
                    DefaultTableModel dModel = (DefaultTableModel)jTable.getModel();
                    dModel.setRowCount(0);
                    jTable.setModel(dModel);
                    jTable.repaint();
                    jTable.revalidate();
                    ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                    JOptionPane.showMessageDialog(this, "No Student Registered", "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
                
                
                
                //previous/saved student marks
                ps = connect.prepareStatement("SELECT "
                        + "t22_marks_obtained.T22_id, t22_marks_obtained.T19_id_fk, t22_marks_obtained.T20_id_fk,t22_marks_obtained.marks "
                        + "FROM `t22_marks_obtained` JOIN t19_question ON t22_marks_obtained.T19_id_fk=t19_question.T19_id "
                        + "WHERE t19_question.T18_id_fk=? ORDER BY t22_marks_obtained.T20_id_fk ASC"
                        ,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ps.setString(1, T18_info[exam_index][0]);
                rs = ps.executeQuery();
                rs.last();
                int t22_length = rs.getRow();
                rs.beforeFirst();
                
                //Creating the table 
                Object[][] data_table = new Object[studentID.length][columnNames.length];
                for(int i=0;i<data_table.length;i++){
                    for(int j=0;j<data_table[i].length;j++){
                        data_table[i][j] ="";
                    }
                }
                
                for(int i=0;i<studentID.length;i++){
                    data_table[i][0] = studentID[i];
                    data_table[i][1] = "YES";
                }
                
                //setting previous data to table
                if(t22_length>0){
                    prev_T22_id = new String [t22_length];
                    for(int i=0;rs.next()&& i<prev_T22_id.length;i++){
                        prev_T22_id[i] = rs.getString(1);
                        String data_T20id = rs.getString(3);
                        for(int j=0;j<T20_id.length;j++){
                            if(data_T20id.equals(T20_id[j])){
                                String data_T19id = rs.getString(2);
                                for(int k=0;k<T19_info.length;k++){
                                    if(data_T19id.equals(T19_info[k][0])){
                                        if(rs.getString(4).equals("-1.0")){
                                            data_table[j][k+2] = "ABSENT";
                                            data_table[j][1] = "NO";
                                            break;
                                        }
                                        data_table[j][k+2] = rs.getString(4);
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                
                //table settings
                CustomTableModel cModel = new CustomTableModel(data_table, columnNames);
                jTable.setModel(cModel);

                
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                for (int i = 1; i < jTable.getColumnCount(); i++) {
                    jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }
                String [] present_options = {"YES","NO"};
                JComboBox<String> comboBox1 = new JComboBox<>(present_options);
                TableColumn col2 = jTable.getColumnModel().getColumn(1);
                col2.setCellEditor(new DefaultCellEditor(comboBox1));
                
                
                for(int i=0;i<jTable.getRowCount();i++){
                    if(jTable.getValueAt(i, 1).equals("NO")){
                        cModel.setRowNonEditable(i);
                    }
                }
                comboBox1.addActionListener(e -> {
                    int row = jTable.getEditingRow();
                    if (row >= 0) {
                        String value = (String) comboBox1.getSelectedItem();
                        if ("NO".equals(value)) {
                            for (int i = 2; i < jTable.getColumnCount(); i++) {
                                cModel.setValueAt("ABSENT", row, i);
                            }
                            cModel.setRowNonEditable(row);
                        } else {
                            for (int i = 2; i < jTable.getColumnCount(); i++) {
                                cModel.setValueAt("", row, i);
                            }
                            cModel.setRowEditable(row);
                        }
                    }
                });
                
                //setting column width
                TableColumnModel columnModel = jTable.getColumnModel();
                columnModel.getColumn(0).setPreferredWidth(75); // Column 0
                columnModel.getColumn(1).setPreferredWidth(20); // Column 1
                for(int i=2;i<columnNames.length;i++){
                    columnModel.getColumn(i).setPreferredWidth(10);
                }
                
                jTable.revalidate();
                jTable.repaint();
                
                
                
                
            }else{
                DefaultTableModel dModel = (DefaultTableModel)jTable.getModel();
                dModel.setRowCount(0);
                jTable.setModel(dModel);
                jTable.repaint();
                jTable.revalidate();
                ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                JOptionPane.showMessageDialog(this, "No Question SET Exist", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
                
            }
            
            
            
            
            
        }catch (SQLException ex) {

            
            //Logger.getLogger(EndSemesterFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(EndSemesterFrame.class.getName()).log(Level.SEVERE, null, ex);
            
            ImageIcon icon_server_error = new ImageIcon(getClass().getResource("/icon/server_error_2_64.png"));
            ImageIcon icon_data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));

            
            int code = ex.getErrorCode();
            String error_msg = ex.getMessage();
            String msg = "Error Code: " + code;
            String[] options = {"OK"};
            if (error_msg.equals("Delete Error!") || error_msg.equals("Update Error!") || error_msg.equals("Insert Error!") ) {
                JOptionPane.showMessageDialog(this, "Error: "+error_msg, "DataBase Error!", JOptionPane.ERROR_MESSAGE, icon_data_error);

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
        
    }//GEN-LAST:event_examComboBoxActionPerformed

    private void totalMarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalMarkActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_totalMarkActionPerformed

    private void finalizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizeButtonActionPerformed
        // TODO add your handling code here:
        
        
        
        
        
        for(int i=0;i<jTable.getRowCount();i++){
            for(int j=0;j<jTable.getColumnCount();j++){
                if(jTable.getValueAt(i, j)==null){
                    ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                    JOptionPane.showMessageDialog(this, "Missing Data at Row: "+(i+1)+"Column: "+(j+1), "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }else if(jTable.getValueAt(i, j).toString().trim().isBlank()){
                    ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                    JOptionPane.showMessageDialog(this, "Missing Data at Row: "+(i+1)+"Column: "+(j+1), "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
            }
        }
        
        
        //SAVE BUTTON code + finalize code
        int row_count = jTable.getRowCount();
        int exam_index = examComboBox.getSelectedIndex();
        ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
        
        
        if (row_count == 0) {
            JOptionPane.showMessageDialog(this, "Table is Empty", "Error!", JOptionPane.ERROR_MESSAGE, error);
            return;
        }
        if(T19_info[0][0].equals("None!")){
            JOptionPane.showMessageDialog(this, "No Questions!!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            return;
        }
        String[][] table_info = new String [row_count][T19_info.length];
        for(int i=0;i<table_info.length;i++){
            for(int j=0;j<table_info[i].length;j++){
                table_info[i][j] = "";
            }
        }
        
        //taking table data to 2D double array checking for invalid data
        for(int i=0;i<table_info.length;i++){
            for(int j=0;j<table_info[i].length;j++){
                if(jTable.getValueAt(i, j+2)==null){
                    table_info[i][j] = "";
                    continue;
                }
                table_info[i][j] = jTable.getValueAt(i, j+2).toString().trim();
            }
        }
        
 
        
        //taking checking for invalid data
        for (int i = 0; i < row_count; i++) {
            
            for (int j = 0; j < table_info[i].length; j++) {
                if(table_info[i][j].trim().isBlank()){
                    continue;
                }
                try {
                    if(table_info[i][j].equals("ABSENT")){
                        continue;
                    }
                    double temp_number = Double.parseDouble(table_info[i][j]);
                    if(temp_number > Double.parseDouble(T19_info[j][3]) || temp_number<0){
                        JOptionPane.showMessageDialog(this, "Marks > Total Mark for: " + jTable.getValueAt(i, 0).toString()+" at "+columnNames[j+2],
                            "Error!", JOptionPane.ERROR_MESSAGE, error);
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Input Format Error for: " + jTable.getValueAt(i, 0).toString()+" at "+columnNames[j+2],
                            "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
            }
            
        }
        
        
        

        //inserting data to table
        try {
            connect.setAutoCommit(false);
            PreparedStatement ps;
            //Password Protect
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
                        //ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                        JOptionPane.showMessageDialog(this, "Password Didn't Match!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                        return;
                    }

                } else {
                    //ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                    JOptionPane.showMessageDialog(this, "Major error in Integrity of Token!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }

            } else {
                ImageIcon warning = new ImageIcon(getClass().getResource("/icon/warning_64.png"));
                JOptionPane.showMessageDialog(this, "Need to Enter Password!", "Warning!", JOptionPane.ERROR_MESSAGE, warning);
                return;

            }
            
            
            
            
            
            
            
            //deleteing previous T22
            ps = connect.prepareStatement("DELETE t22_marks_obtained FROM `t22_marks_obtained` "
                    + "JOIN t19_question ON t22_marks_obtained.T19_id_fk=t19_question.T19_id "
                    + "WHERE t19_question.T18_id_fk = ?");
            
            ps.setString(1, T18_info[exam_index][0]);
            ps.executeUpdate();
            
            int temp_count = 0;
            for(int i=0;i<table_info.length;i++){
                for(int j=0;j<table_info[i].length;j++){
                    if(table_info[i][j].isBlank()){
                        continue;
                    }
                    if(temp_count<prev_T22_id.length && !prev_T22_id[0].equals("None!")){
                        ps = connect.prepareStatement("INSERT INTO `t22_marks_obtained`(`T22_id`, `T19_id_fk`, `T20_id_fk`, `marks`) "
                                + "VALUES (?,?,?,?)");
                        ps.setString(1, prev_T22_id[temp_count]);
                        ps.setString(2, T19_info[j][0]);
                        ps.setString(3, T20_id[i]);
                        if(table_info[i][j].equals("ABSENT")){
                            ps.setString(4, "-1");
                        }else{
                            ps.setString(4, table_info[i][j]);
                        }
                        
                        if(ps.executeUpdate()<=0){
                            throw new SQLException("Update Error!"); 
                        }
                        temp_count++;
                    }else{
                        ps = connect.prepareStatement("INSERT INTO `t22_marks_obtained`(`T19_id_fk`, `T20_id_fk`, `marks`) "
                                + "VALUES (?,?,?)");
                        
                        ps.setString(1, T19_info[j][0]);
                        ps.setString(2, T20_id[i]);
                        if(table_info[i][j].equals("ABSENT")){
                            ps.setString(3, "-1");
                        }else{
                            ps.setString(3, table_info[i][j]);
                        }
                        
                        if(ps.executeUpdate()<=0){
                            throw new SQLException("Update Error!"); 
                        }
                        
                    }
                }
            }
            
            
            
            ///Checking user status to make sure it can be updated
            if (!T18_info[0][0].equals("None!")) {
                if (profile == 0) {
                    ps = connect.prepareStatement("SELECT T02_id_fk FROM `t16_employee` WHERE employee_id = ? AND T01_id_fk = ?");
                    ps.setString(1, id);
                    ps.setString(2, idT01);
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    int job = Integer.parseInt(rs.getString(1));

                    if (job == 1) {
                        //employee-admin
                        ps = connect.prepareStatement("SELECT semester_status FROM `t14_semester` WHERE T14_id = ?");
                        ps.setString(1, idT14);
                        rs = ps.executeQuery();
                        rs.next();
                        int semester_status = rs.getInt(1);
                        if (semester_status == 2) {
                            throw new SQLException("Update Error!"); 
                        }
                    } else if (job == 2) {
                        //employee-teacher
                        ps = connect.prepareStatement("SELECT exam_status FROM `t18_exam` WHERE T18_id = ?");
                        ps.setString(1, T18_info[exam_index][0]);
                        rs = ps.executeQuery();
                        rs.next();
                        int exam_status = rs.getInt(1);
                        if (exam_status > 0) {
                            throw new SQLException("Update Error!");
                        }
                    }

                } 
            }
            
            //Finalize the Exam
            ps = connect.prepareStatement("UPDATE `t18_exam` SET `exam_status`= 1 WHERE exam_status = 0 AND T18_id = ?");
            ps.setString(1, T18_info[exam_index][0]);
            if(ps.executeUpdate()<=0){
                examComboBox.setSelectedIndex(exam_index);
                throw new SQLException("Update Error!");
            }
            
            
            connect.commit();
            connect.setAutoCommit(true);
            
            
            ImageIcon success = new ImageIcon(getClass().getResource("/icon/success_64.png"));
            JOptionPane.showMessageDialog(this, "Exam Finalized!", "Success!", JOptionPane.ERROR_MESSAGE, success);
            examComboBox.setSelectedIndex(exam_index);
            
        } catch (SQLException ex) {
            try {
                connect.rollback();
                connect.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(OfferedCourseFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(EndSemesterFrame.class.getName()).log(Level.SEVERE, null, ex);
            //Logger.getLogger(EndSemesterFrame.class.getName()).log(Level.SEVERE, null, ex);

            ImageIcon icon_server_error = new ImageIcon(getClass().getResource("/icon/server_error_2_64.png"));
            ImageIcon icon_data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));

            int code = ex.getErrorCode();
            String errorM = ex.getMessage();
            String msg = "Error Code: " + code;
            String[] options = {"OK"};
            if (errorM.equals("Delete Error!") || errorM.equals("Update Error!") || errorM.equals("Insert Error!")) {
                JOptionPane.showMessageDialog(this, "Error: " + errorM, "DataBase Error!", JOptionPane.ERROR_MESSAGE, icon_data_error);

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
    }//GEN-LAST:event_finalizeButtonActionPerformed

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
            java.util.logging.Logger.getLogger(EnterMarkFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnterMarkFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnterMarkFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnterMarkFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EnterMarkFrame("default","default","default","default","default").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> courseComboBox;
    private javax.swing.JComboBox<String> examComboBox;
    private javax.swing.JButton finalizeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
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
    private javax.swing.JTextField totalMark;
    // End of variables declaration//GEN-END:variables
}

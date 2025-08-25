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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
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
public class CreateMarkDistributionFrame extends javax.swing.JFrame {

    /**
     * Creates new form EnterUniversity
     */
    Connection connect;
    
    //JComboBox<String> comboBox3;
    //String uni_id="";
    String [] T03_id = new String [100];
    String idT06 = "";
    String idT01 = "";
    String idT14 = "";
    String idT16 = "";
    String syllabus_id = "";
    String [] [] T18_info = {{"None!"}};
    String [] T15_id = {"None!"};
    String [] prev_T19_id = {"None!"};
    String [] T10_id = {"None!"};
    String [] T09_id = {"None!"};
    String token = "";
    String id = "";
    String uni_id = "";
    int profile = -1;
    final Map<Point, TableCellEditor> cellEditors = new HashMap<>();
    
    int delete_row_index = -1;
    MarkDistributionFrame mdisfrm;
    boolean save_status = false;
    
    
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
    
    
    
    
    
    public CreateMarkDistributionFrame(String idT06,String idT01,String idT14,String idT16,String token) {
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
        
        String [] mainQuestionOptions = {"1"};
        JComboBox<String> comboBox1 = new JComboBox<>(mainQuestionOptions);
        TableColumn col1 = jTable.getColumnModel().getColumn(0);
        col1.setCellEditor(new DefaultCellEditor(comboBox1));
        
        String [] subQuestionOptions = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","za","zb","zc","zd"};
        JComboBox<String> comboBox2 = new JComboBox<>(subQuestionOptions);
        TableColumn col2 = jTable.getColumnModel().getColumn(1);
        col2.setCellEditor(new DefaultCellEditor(comboBox2));
        
        String [] cloOptions = {"None!"};
        JComboBox<String> comboBox3 = new JComboBox<>(cloOptions);
        TableColumn col3 = jTable.getColumnModel().getColumn(3);
        col3.setCellEditor(new DefaultCellEditor(comboBox3));
        
        

        jTable.revalidate();
        jTable.repaint();
        
        
        
        
        T15_id = new String[1];
        T15_id[0] = "None!";
        T18_info = new String [1][4];
        for(int i=0;i<4;i++){
            T18_info[0][i] = "None!";
        }
        T15_id = new String [1];
        T15_id[0]= "None!";
        T10_id = new String [1];
        T10_id[0]= "None!";
        T09_id = new String [1];
        T09_id[0]= "None!";
        prev_T19_id = new String[1];
        prev_T19_id [0] = "None!";
        
        
        String [] tempToken = token.split(" ");
        id = tempToken[0];
        uni_id = tempToken[1];
        profile = Integer.parseInt(tempToken[2]);
        mainQuestionCount.setText("1");
        totalQuestionCount.setText("0");
        
        save_status = false;
        courseComboBox.setEnabled(false);
        examComboBox.setEnabled(false);
        jTable.setEnabled(false);
        saveButton.setEnabled(false);
        mainQuestionCount.setEditable(false);
        totalQuestionCount.setEditable(false);
        totalMark.setEditable(false);
        
        
        
        
        try{
            
            PreparedStatement ps;
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            connect.setAutoCommit(true);
            
            ps = connect.prepareStatement("SELECT employee_id FROM `t16_employee` WHERE T16_id = ?");
            ps.setString(1, idT16);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                jLabel1.setText("CREATE MARK DISTRIBUTION FOR: "+rs.getString(1));
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
            ps = connect.prepareStatement("SELECT T09_id FROM `t09_syllabus_plo` "
                    + "WHERE T06_id_fk = ?  ORDER BY plo_id ASC",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT06);
            rs = ps.executeQuery();
            rs.last();
            int temp_count2 = rs.getRow();
            rs.beforeFirst();
            if(temp_count2>0){
                T09_id = new String[temp_count2];
                for(int i=0;rs.next() && i<temp_count2;i++){
                    T09_id[i]= rs.getString(1);
                    
                }
            }else{
                ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                JOptionPane.showMessageDialog(this, "Major Error: Problem in PLO Information!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            
            
            
            
            
        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            String [] options = {"Retry","Quit"};
            ImageIcon icon_server_retry = new ImageIcon(getClass().getResource("/icon/server_retry_64.png"));
            int select = JOptionPane.showOptionDialog(null, "Server Connection Failed!!\n"+msg+"\nPlease,Retry Connection or Quit!", "Connection Failed!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_server_retry, options, options[0]);
            if(select==0){
                CreateMarkDistributionFrame rtfrm = new CreateMarkDistributionFrame(this.idT06,this.idT01,this.idT14,this.idT16,this.token);
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
        jLabel8 = new javax.swing.JLabel();
        removeButton = new javax.swing.JButton();
        mainQuestionCount = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        removeButton2 = new javax.swing.JButton();
        totalQuestionCount = new javax.swing.JTextField();
        addButton2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new JTable(){
            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                Point cell = new Point(row, column);
                if (cellEditors.containsKey(cell)) {
                    return cellEditors.get(cell);
                }
                return super.getCellEditor(row, column);
            }
        }
        ;
        jSeparator2 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Mark Distribution");
        setPreferredSize(new java.awt.Dimension(705, 622));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(234, 80));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mark_distribution_64.png"))); // NOI18N
        jLabel1.setText("CREATE MARK DISTRIBUTION FOR");
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

        jPanel9.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jSeparator1.setForeground(new java.awt.Color(0, 51, 153));
        jSeparator1.setPreferredSize(new java.awt.Dimension(500, 5));
        jPanel9.add(jSeparator1, java.awt.BorderLayout.NORTH);

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel8.setText("Total Main Question:");
        jPanel10.add(jLabel8);

        removeButton.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        removeButton.setText("-");
        removeButton.setPreferredSize(new java.awt.Dimension(30, 30));
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        jPanel10.add(removeButton);

        mainQuestionCount.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        mainQuestionCount.setText("1");
        mainQuestionCount.setFocusable(false);
        mainQuestionCount.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel10.add(mainQuestionCount);

        addButton.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        addButton.setText("+");
        addButton.setPreferredSize(new java.awt.Dimension(30, 30));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel10.add(addButton);

        jPanel17.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel10.add(jPanel17);

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel9.setText("Total (sub) Question:");
        jPanel10.add(jLabel9);

        removeButton2.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        removeButton2.setText("-");
        removeButton2.setPreferredSize(new java.awt.Dimension(30, 30));
        removeButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButton2ActionPerformed(evt);
            }
        });
        jPanel10.add(removeButton2);

        totalQuestionCount.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        totalQuestionCount.setText("0");
        totalQuestionCount.setFocusable(false);
        totalQuestionCount.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel10.add(totalQuestionCount);

        addButton2.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        addButton2.setText("+");
        addButton2.setPreferredSize(new java.awt.Dimension(30, 30));
        addButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButton2ActionPerformed(evt);
            }
        });
        jPanel10.add(addButton2);

        jPanel9.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel8.setLayout(new java.awt.BorderLayout(10, 10));

        jTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Main Question", "Sub Question", "Mark", "CLO", "PLO"
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

        saveButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.setPreferredSize(new java.awt.Dimension(120, 30));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jPanel11.add(saveButton);

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(220, 0, 0));
        jLabel5.setText("FINALIZED");
        jPanel11.add(jLabel5);

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
        String[][] table_info = new String [row_count][5];
        //checking if there is any empty cell in table and taking table input to an array
        for(int i=0;i<row_count;i++){
            if (jTable.getValueAt(i, 0) == null) {
                JOptionPane.showMessageDialog(this, "Table Input is missing at Row:"+(i+1)+", Column:1", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;

            }
            table_info[i][0] = jTable.getValueAt(i, 0).toString().trim();
            if(table_info[i][0].isBlank() ){
                JOptionPane.showMessageDialog(this, "Table Input is missing at Row:"+(i+1)+", Column:1", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            if(jTable.getValueAt(i, 1) == null ){
                JOptionPane.showMessageDialog(this, "Table Input is missing at Row:"+(i+1)+", Column:2", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            table_info[i][1] = jTable.getValueAt(i, 1).toString().trim();
            if(table_info[i][1].isBlank() ){
                JOptionPane.showMessageDialog(this, "Table Input is missing at Row:"+(i+1)+", Column:2", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            if(jTable.getValueAt(i, 2) == null ){
                JOptionPane.showMessageDialog(this, "Table Input is missing at Row:"+(i+1)+", Column:3", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            table_info[i][2] = jTable.getValueAt(i, 2).toString().trim();
            if (table_info[i][2].isBlank()) {
                JOptionPane.showMessageDialog(this, "Table Input is missing at Row:"+(i+1)+", Column:3", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }

            if (jTable.getValueAt(i, 3) == null) {
                JOptionPane.showMessageDialog(this, "Table Input is missing at Row:"+(i+1)+", Column:4", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            table_info[i][3] = jTable.getValueAt(i, 3).toString().trim();
            if (table_info[i][3].isBlank() ){
                JOptionPane.showMessageDialog(this, "Table Input is missing at Row:"+(i+1)+", Column:4", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            if (jTable.getValueAt(i, 4) == null) {
                JOptionPane.showMessageDialog(this, "Table Input is missing at Row:"+(i+1)+", Column:5", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            table_info[i][4] = jTable.getValueAt(i, 4).toString().trim();
            if (table_info[i][4].isBlank() || table_info[i][4].equals("None!") ){
                JOptionPane.showMessageDialog(this, "Table Input is missing at Row:"+(i+1)+", Column:5", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
        }
        
        
        //checking for missing Main Question
        int mainQcount = Integer.parseInt(mainQuestionCount.getText().trim());
        boolean Qcount_flag = false;
        for(int i=1;i<=mainQcount;i++){
            for(int j=0;j<table_info.length;j++){
                if(table_info[j][0].equals(String.valueOf(i))){
                    break;
                }else if(j==table_info.length-1){
                    Qcount_flag = true;
                }
            }
            if(Qcount_flag){
                JOptionPane.showMessageDialog(this, "Main Question number \""+String.valueOf(i)+"\" is Missing!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
        }
        
        //checking for duplicate question number
        for(int i=0;i<row_count;i++){
            String temp1_qSet = table_info[i][0]+"/"+table_info[i][1];
            for(int j=i+1;j<row_count;j++){
                String temp2_qSet = table_info[j][0]+"/"+table_info[j][1];
                if (temp1_qSet.equals(temp2_qSet)) {
                    JOptionPane.showMessageDialog(this, "There are multiple \""+temp1_qSet+"\" Questions!!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
            }
        }
        
        //checking if there is any invalid value for Marks
        for (int i = 0; i < row_count; i++) {
            try {
                Double.parseDouble(table_info[i][2]);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Marks input Error at Row: " + (i + 1), "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
        }
        
        //checking if Total Mark is valid
        double totalMarks = Double.parseDouble(totalMark.getText().trim());
        double sum_mark =0;
        for(int i=0;i<row_count;i++){
            sum_mark+=Double.parseDouble(table_info[i][2]);
        }
        if(totalMarks!=sum_mark){
            JOptionPane.showMessageDialog(this, "Sum of Question Marks is not eqaul to Exam Total Mark!!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            return;
        }
        

        //inserting data to table
        try {
            connect.setAutoCommit(false);
            PreparedStatement ps;
            //deleteing previous T19
            ps = connect.prepareStatement("DELETE FROM `t19_question` WHERE T18_id_fk = ?");
            ps.setString(1, T18_info[exam_index][0]);
            ps.executeUpdate();
            
            //Inserting
            for (int i = 0; i < table_info.length; i++) {
                if (i < prev_T19_id.length && !prev_T19_id[0].equals("None!")) {
                    ps = connect.prepareStatement("INSERT INTO `t19_question`"
                            + "(`T19_id`, `T18_id_fk`, `question_no`, `sub_question_no`, `question_mark`, `T10_id_fk`, `T09_id_fk`) "
                            + "VALUES (?,?,?,?,?,?,?)");
                    ps.setString(1, prev_T19_id[i]);
                    ps.setString(2, T18_info[exam_index][0]);
                    ps.setString(3, table_info[i][0]);
                    ps.setString(4, table_info[i][1]);
                    ps.setString(5, table_info[i][2]);
                    ps.setString(6, T10_id[Integer.parseInt(table_info[i][3]) - 1]);
                    ps.setString(7, T09_id[Integer.parseInt(table_info[i][4]) - 1]);
                    if (ps.executeUpdate() <= 0) {
                        throw new SQLException("Update Error!");
                    }

                }else{
                   ps = connect.prepareStatement("INSERT INTO `t19_question`"
                            + "(`T18_id_fk`, `question_no`, `sub_question_no`, `question_mark`, `T10_id_fk`, `T09_id_fk`) "
                            + "VALUES (?,?,?,?,?,?)");
                    ps.setString(1, T18_info[exam_index][0]);
                    ps.setString(2, table_info[i][0]);
                    ps.setString(3, table_info[i][1]);
                    ps.setString(4, table_info[i][2]);
                    ps.setString(5, T10_id[Integer.parseInt(table_info[i][3]) - 1]);
                    ps.setString(6, T09_id[Integer.parseInt(table_info[i][4]) - 1]);
                    if (ps.executeUpdate() <= 0) {
                        throw new SQLException("Update Error!");
                    } 
                }

            }
            
            ///Checking status to make sure it can be updated
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
                JOptionPane.showMessageDialog(this, "Error: " + error, "DataBase Error!", JOptionPane.ERROR_MESSAGE, icon_data_error);

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

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
        int main_count = Integer.parseInt(mainQuestionCount.getText());
        if(main_count>1){
            main_count--;
            String[] mainQuestionOptions = new String[main_count];
            for (int i = 0; i < main_count; i++) {
                mainQuestionOptions [i] = String.valueOf(i+1);
            }
            JComboBox<String> comboBox1 = new JComboBox<>(mainQuestionOptions);
            TableColumn col1 = jTable.getColumnModel().getColumn(0);
            col1.setCellEditor(new DefaultCellEditor(comboBox1));
            mainQuestionCount.setText(String.valueOf(main_count));

            //check if any options exists that is not valid any more
            DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
            for (int i = 0; i < dModel.getRowCount(); i++) {
                Object value = dModel.getValueAt(i, 0);
                
                boolean found = false;
                for (String option : mainQuestionOptions) {
                    if (option.equals(value)) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    dModel.setValueAt(mainQuestionOptions[0], i, 0); 
                }
            }
            
            if(main_count==1){
                removeButton.setEnabled(false);
            }
            jTable.setModel(dModel);
            jTable.revalidate();
            jTable.repaint();
            
        }else{
            removeButton.setEnabled(false);
        }
    }//GEN-LAST:event_removeButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        int main_count = Integer.parseInt(mainQuestionCount.getText());
        if(main_count==1){
            removeButton.setEnabled(true);
        }
        main_count++;
        String[] mainQuestionOptions = new String[main_count];
        for (int i = 0; i < main_count; i++) {
            mainQuestionOptions[i] = String.valueOf(i + 1);
        }
        JComboBox<String> comboBox1 = new JComboBox<>(mainQuestionOptions);
        TableColumn col1 = jTable.getColumnModel().getColumn(0);
        col1.setCellEditor(new DefaultCellEditor(comboBox1));
        mainQuestionCount.setText(String.valueOf(main_count));
        
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButton2ActionPerformed
        // TODO add your handling code here:
        int question_count = Integer.parseInt(totalQuestionCount.getText());
        question_count--;
        if(question_count>-1){
            
            DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
            dModel.setRowCount(question_count);
            jTable.setModel(dModel);
            jTable.revalidate();
            jTable.repaint();
            if(question_count == 0){
                removeButton2.setEnabled(false);
            }
        }else{
            removeButton2.setEnabled(false);
        }
        totalQuestionCount.setText(String.valueOf(question_count));
    }//GEN-LAST:event_removeButton2ActionPerformed

    private void addButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButton2ActionPerformed
        // TODO add your handling code here:
        int question_count = Integer.parseInt(totalQuestionCount.getText());
        question_count++;
        DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
        dModel.setRowCount(question_count);
        jTable.setModel(dModel);
        jTable.revalidate();
        jTable.repaint();
        if (question_count > 0) {
            removeButton2.setEnabled(true);
        }
        totalQuestionCount.setText(String.valueOf(question_count));
    }//GEN-LAST:event_addButton2ActionPerformed

    private void courseComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseComboBoxActionPerformed
        // TODO add your handling code here:
        
        int course_index = courseComboBox.getSelectedIndex();
        examComboBox.setEnabled(false);
        
        saveButton.setEnabled(false);
        jTable.setEnabled(false);
        T10_id = new String[1];
        T10_id[0] = "None!";
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
               
               //custom CLO search
               ps = connect.prepareStatement("SELECT t10_course_clo.T10_id FROM `t15_offered_course` "
                       + "JOIN t10_course_clo ON t10_course_clo.T07_id_fk = t15_offered_course.T07_id_fk "
                       + "WHERE t15_offered_course.T15_id = ? AND t10_course_clo.T15_id_fk = ? "
                       + "ORDER BY t10_course_clo.clo_number ASC",
                       ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
               ps.setString(1, T15_id[course_index]);
               ps.setString(2, T15_id[course_index]);
               rs = ps.executeQuery();
               rs.last();
               int t10_length = 0;
               t10_length = rs.getRow();
               rs.beforeFirst();
               
               if(t10_length==0){
                   //default CLO
                   ps = connect.prepareStatement("SELECT t10_course_clo.T10_id FROM `t15_offered_course` "
                           + "JOIN t10_course_clo ON t10_course_clo.T07_id_fk = t15_offered_course.T07_id_fk "
                           + "WHERE t15_offered_course.T15_id = ? AND t10_course_clo.T15_id_fk = 0 "
                           + "ORDER BY t10_course_clo.clo_number ASC",
                           ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                   ps.setString(1, T15_id[course_index]);
                   rs = ps.executeQuery();
                   rs.last();
                   t10_length = rs.getRow();
                   rs.beforeFirst();
               }
               
               
               T10_id = new String [t10_length];
               String[] cloOptions = new String[t10_length];
               for(int i=0;rs.next() && i<T10_id.length;i++){
                   T10_id [i] = rs.getString(1);
                   cloOptions[i] = String.valueOf(i+1);
               }
               
               //CLO comboBox
               JComboBox<String> comboBox4 = new JComboBox<>(cloOptions);
               TableColumn col4 = jTable.getColumnModel().getColumn(3);
               col4.setCellEditor(new DefaultCellEditor(comboBox4));
               
               //changes the PLO comboBox based on CLO
               comboBox4.addActionListener(e -> {
                   int row = jTable.getEditingRow();
                   if (row >= 0) {
                       int t10_index2 = Integer.parseInt((String) comboBox4.getSelectedItem()) - 1;
                       try {
                           PreparedStatement psx = connect.prepareStatement("SELECT t09_syllabus_plo.plo_id "
                                   + "FROM `t13_clo_plo_connect` JOIN t09_syllabus_plo ON t13_clo_plo_connect.T09_id_fk = t09_syllabus_plo.T09_id "
                                   + "WHERE t13_clo_plo_connect.T10_id_fk = ?",
                                   ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                           psx.setString(1, T10_id[t10_index2]);
                           ResultSet rsx = psx.executeQuery();
                           String[] plo_items = {"None!"};
                           rsx.last();
                           int plo_length = rsx.getRow();
                           rsx.beforeFirst();
                           if (plo_length > 0) {
                               plo_items = new String[plo_length];
                               for (int i = 0; rsx.next() && i < plo_length; i++) {
                                   plo_items[i] = rsx.getString(1).trim();
                               }
                           }

                           // Create a combo box with fetched items
                           JComboBox<String> detailsCombo = new JComboBox<>(plo_items);
                           cellEditors.put(new Point(row, 4), new DefaultCellEditor(detailsCombo));
                           detailsCombo.setSelectedIndex(0);
                           jTable.setValueAt(detailsCombo.getSelectedItem(), row, 4);
                           jTable.revalidate();
                           jTable.repaint();

                       } catch (SQLException ex) {
                           Logger.getLogger(CreateMarkDistributionFrame.class.getName()).log(Level.SEVERE, null, ex);
                       }

                   }
               });

               
               
               
               jTable.revalidate();
               jTable.repaint();
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
            
        }catch (NumberFormatException e) {
            
            Logger.getLogger(EndSemesterFrame.class.getName()).log(Level.SEVERE, null, e);

        }
    }//GEN-LAST:event_courseComboBoxActionPerformed

    private void examComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examComboBoxActionPerformed
        // TODO add your handling code here:
        jLabel5.setVisible(false);
        jLabel4.setVisible(false);
        if (jTable.isEditing()) {
            jTable.getCellEditor().stopCellEditing();
        }
        jTable.clearSelection();
        cellEditors.clear();
        jTable.setEnabled(false);
        saveButton.setEnabled(false);
        prev_T19_id = new String [1];
        prev_T19_id[0] = "None!";
        if(T18_info[0][0].equals("None!")){
            saveButton.setEnabled(false);
            examComboBox.setEnabled(false);            
            return;
        }
        int exam_index = examComboBox.getSelectedIndex();
        totalMark.setText(T18_info[exam_index][2]);
        try{
            //Show Finalized
            PreparedStatement ps = connect.prepareStatement("SELECT exam_status FROM `t18_exam`"
                    + " WHERE T18_id=? AND exam_status =1");
            ps.setString(1,T18_info[exam_index][0] );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                jLabel5.setVisible(true);
                jLabel4.setVisible(true);
            }else{
                jLabel5.setVisible(false);
                jLabel4.setVisible(false);
            }
            
            
            ps = connect.prepareStatement("SELECT "
                    + "table1.T19_id,table1.question_no,table1.sub_question_no,table1.question_mark,"
                    + "table1.clo_number,t09_syllabus_plo.plo_id"
                    + " FROM (SELECT"
                    + " t19_question.T19_id,t19_question.question_no,t19_question.sub_question_no,t19_question.question_mark,t10_course_clo.clo_number,t19_question.T09_id_fk"
                    + " FROM `t19_question` JOIN t10_course_clo ON t19_question.T10_id_fk = t10_course_clo.T10_id"
                    + " WHERE T18_id_fk = ? ORDER BY t19_question.question_no ASC, t19_question.sub_question_no ASC)"
                    + " AS table1 JOIN t09_syllabus_plo ON table1.T09_id_fk = t09_syllabus_plo.T09_id "
                    + "ORDER BY table1.question_no ASC, table1.sub_question_no ASC",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, T18_info[exam_index][0]);
            rs = ps.executeQuery();
            rs.last();
            int t19_length = rs.getRow();
            rs.beforeFirst();
            if(t19_length>0){
                
                String [][] table_info = new String [t19_length][5];
                prev_T19_id = new String [t19_length];
                for(int i=0;rs.next() && i<t19_length;i++){
                    prev_T19_id [i] = rs.getString(1);
                    table_info [i][0] = rs.getString(2);
                    table_info [i][1] = rs.getString(3);
                    table_info [i][2] = rs.getString(4);
                    table_info [i][3] = rs.getString(5);
                    //setting the PLO comboBox according to CLO
                    PreparedStatement psx = connect.prepareStatement("SELECT t09_syllabus_plo.plo_id "
                            + "FROM `t13_clo_plo_connect` JOIN t09_syllabus_plo ON t13_clo_plo_connect.T09_id_fk = t09_syllabus_plo.T09_id "
                            + "WHERE t13_clo_plo_connect.T10_id_fk = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    psx.setString(1, T10_id[Integer.parseInt(table_info [i][3].trim())-1]);
                    ResultSet rsx = psx.executeQuery();
                    rsx.last();
                    String[] plo_items = {"None!"};
                    int plo_length = rsx.getRow();
                    rsx.beforeFirst();
                    if(plo_length>0){
                        plo_items = new String [plo_length];
                        for(int j=0;rsx.next() && j<plo_length;j++){
                            plo_items[j] = rsx.getString(1);
                        }
                        
                    }
                    JComboBox<String> comboBox5 = new JComboBox<>(plo_items);
                    TableColumn col5 = jTable.getColumnModel().getColumn(4);
                    col5.setCellEditor(new DefaultCellEditor(comboBox5));

                           
                    
                    
                    
                    table_info [i][4] = rs.getString(6);
                }
                
                //options for Main Question ComboBox and count of Main questions
                String [] temp_options = new String [table_info.length];
                int options_count =0;
                
                for(int i=0;i<table_info.length;i++){
                    boolean exists = false;
                    for(int j=0;j<options_count;j++){
                        if(temp_options[j].equals(table_info[i][0])){
                            exists = true;
                            break;
                        }
                    }
                    if(!exists){
                        temp_options[options_count]=table_info[i][0];
                        options_count++;
                    }
                }
                
                String[] mainQuestionOptions = new String [options_count];
                for(int i=0;i<options_count;i++){
                    mainQuestionOptions [i] = temp_options[i];
                }
                JComboBox<String> comboBox1 = new JComboBox<>(mainQuestionOptions);
                TableColumn col1 = jTable.getColumnModel().getColumn(0);
                col1.setCellEditor(new DefaultCellEditor(comboBox1));
                mainQuestionCount.setText(String.valueOf(options_count));
                
                
                
                //set totalQuestionCount
                totalQuestionCount.setText(String.valueOf(table_info.length));
                
                
                //set Data to Table
                DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
                dModel.setRowCount(0);
                for(int i=0;i<table_info.length;i++){
                    dModel.addRow(table_info[i]);
                }
                jTable.setModel(dModel);
                
                
                
                
                
                
                jTable.revalidate();
                jTable.repaint();
                

                
            }else{
                DefaultTableModel dModel = (DefaultTableModel)jTable.getModel();
                dModel.setRowCount(0);
                
                String[] mainQuestionOptions = {"1"};
                JComboBox<String> comboBox1 = new JComboBox<>(mainQuestionOptions);
                TableColumn col1 = jTable.getColumnModel().getColumn(0);
                col1.setCellEditor(new DefaultCellEditor(comboBox1));
                
                String [] defaultPLO = {"None!"};
                JComboBox<String> comboBox5 = new JComboBox<>(defaultPLO);
                TableColumn col5 = jTable.getColumnModel().getColumn(4);
                col5.setCellEditor(new DefaultCellEditor(comboBox5));
                
                
                jTable.setModel(dModel);
                jTable.repaint();
                jTable.revalidate();
                mainQuestionCount.setText("1");
                totalQuestionCount.setText("0");
            }
            
            
            //which profile gets what access
            if(!T18_info[0][0].equals("None!")){
                if(profile==0){
                    ps  = connect.prepareStatement("SELECT T02_id_fk FROM `t16_employee` WHERE employee_id = ? AND T01_id_fk = ?");
                    ps.setString(1,id);
                    ps.setString(2, idT01);
                    rs = ps.executeQuery();
                    rs.next();
                    int job = Integer.parseInt(rs.getString(1));
                    
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
                        }else if(semester_status==2){
                            jTable.setEnabled(false);
                            saveButton.setEnabled(false);
                        }
                    }else if (job==2){
                        //employee-teacher
                        int exam_status = Integer.parseInt(T18_info[exam_index][3]);
                        if(exam_status==0){
                            jTable.setEnabled(true);
                            saveButton.setEnabled(true);
                        }else if(exam_status>0){
                            jTable.setEnabled(false);
                            saveButton.setEnabled(false);
                        }
                    }
                    
                }else if (profile == 2) {
                    jTable.setEnabled(true);
                    saveButton.setEnabled(true);
                }
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
            
        }catch (NumberFormatException e) {
            
            Logger.getLogger(EndSemesterFrame.class.getName()).log(Level.SEVERE, null, e);

        }
        
    }//GEN-LAST:event_examComboBoxActionPerformed

    private void totalMarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalMarkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalMarkActionPerformed

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
            java.util.logging.Logger.getLogger(CreateMarkDistributionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateMarkDistributionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateMarkDistributionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateMarkDistributionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new CreateMarkDistributionFrame("default","default","default","default","default").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton addButton2;
    private javax.swing.JComboBox<String> courseComboBox;
    private javax.swing.JComboBox<String> examComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField mainQuestionCount;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton removeButton2;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField totalMark;
    private javax.swing.JTextField totalQuestionCount;
    // End of variables declaration//GEN-END:variables
}

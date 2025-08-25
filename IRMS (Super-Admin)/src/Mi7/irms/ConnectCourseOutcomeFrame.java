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
public class ConnectCourseOutcomeFrame extends javax.swing.JFrame {

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
    String syllabus_id = "";
    String [] [] T07_info;
    PreparedStatement ps;
    String [] prev_T10_id;
    String [] prev_T13_id;
    String [] T09_id;
    boolean set_exists = false;
    //int evaluation_count = 0;
    //DefaultTableModel default_model;
    EnterEvaluationSetFrame eevsfrm;
    String [] columnNames;
    boolean constructor_generated = false;
    boolean course_status;
    boolean connected;
    int syb_status = 0;
    void reConnection(){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            connect.setAutoCommit(true);

        }catch(SQLException e){
            System.out.println(e);
        }

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
    /*void alert2bGeneration(int c,String msg1,String msg2,String msg3,String btext1,String btext2,String parent){


        this.setEnabled(false);
        AlertFrame2B a2frm = new AlertFrame2B(c,msg1,msg2,msg3,btext1,btext2);
        a2frm.passEnterCourseInfoFrame(this, parent);
        a2frm.setLocationRelativeTo(null);
        a2frm.setAlwaysOnTop(true);
        a2frm.setVisible(true);

    }*/

    /*void alertGeneration(int c,String msg1,String msg2,String msg3){


        this.setEnabled(false);
        AlertFrame afrm = new AlertFrame(c,msg1,msg2,msg3);
        afrm.passEnterCourseInfoFrame(this,"entercourseinfoframe");

        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        afrm.setLocation(x+w/4, y+h/4);

        afrm.setAlwaysOnTop(true);
        afrm.setVisible(true);
    }*/

    public ConnectCourseOutcomeFrame(String idT06, String idT01) {
        initComponents();
        constructor_generated = false;
        this.idT06 = idT06;
        this.idT01 = idT01;
        T07_info = new String [1][2];
        T07_info[0][0] = "None!";
        T07_info[0][1] = "None!";
        columnNames = new String[1];
        columnNames[0] = "None!";
        T09_id = new String [1];
        T09_id[0] = "None!";
        prev_T10_id = new String [1];
        prev_T10_id [0] = "None!";
        prev_T13_id = new String [1];
        prev_T13_id [0] = "None!";
        connected = false;
        
        
        cloCount.setEditable(false);
        cloCount.setEnabled(true);
        disconnectedCourseCombo.setEnabled(false);
        connectedCourseCombo.setEnabled(false);
        selectedCourseField.setEditable(false);
        saveButton.setEnabled(false);




        try{

            //PreparedStatement ps;
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            ps = connect.prepareStatement("SELECT syllabus_id FROM `t06_syllabus` WHERE T06_id = ?");
            ps.setString(1, idT06);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                this.syllabus_id = rs.getString(1);
            }
            jLabel1.setText("CONNECT COURSE OUTCOME OF: "+syllabus_id);


            ps = connect.prepareStatement("SELECT T07_id,course_id,course_status FROM `t07_course` WHERE "
                    + "T06_id_fk = ? ORDER BY course_id",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT06);
            rs = ps.executeQuery();
            rs.last();
            int course_count = rs.getRow();
            rs.beforeFirst();
            if(course_count>0){
                
                T07_info = new String [course_count][2];
                for(int i=0;rs.next() && i<T07_info.length;i++){
                    T07_info[i][0] = rs.getString(1);
                    T07_info[i][1] = rs.getString(2);
                    if(rs.getInt(3)>0){
                        if(connectedCourseCombo.getItemAt(0).equals("None!")){
                            connectedCourseCombo.removeAllItems();
                            connectedCourseCombo.setEnabled(true);
                        }
                        connectedCourseCombo.addItem(T07_info[i][1]);
                    }else{
                       if(disconnectedCourseCombo.getItemAt(0).equals("None!")){
                            disconnectedCourseCombo.removeAllItems();
                            disconnectedCourseCombo.setEnabled(true);
                        }
                        disconnectedCourseCombo.addItem(T07_info[i][1]); 
                    }
                }
            }
            
            //setTableColumnTitles
            ps = connect.prepareStatement("SELECT T09_id,plo_id FROM `t09_syllabus_plo` WHERE T06_id_fk = ? ORDER BY plo_id"
                    ,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT06);
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
                disconnectedCourseCombo.setEnabled(false);
                connectedCourseCombo.setEnabled(false);
                selectedCourseField.setEnabled(false);
                saveButton.setEnabled(false);
                removeButton.setEnabled(false);
                addButton.setEnabled(false);
            }
            constructor_generated = true;
            
            
            ps = connect.prepareStatement("SELECT syllabus_status FROM `t06_syllabus` WHERE T06_id =?");
            ps.setString(1, idT06);
            rs = ps.executeQuery();
            syb_status = 0;
            if(rs.next()){
                syb_status = rs.getInt(1);
            }
            
            if(syb_status>0){
                ImageIcon warning = new ImageIcon(getClass().getResource("/icon/warning_64.png"));
                JOptionPane.showMessageDialog(this, """
                                                    WARNING!
                                                    Syllabus is Already Finalized!!
                                                    Syllabus can not be Changed!""", "Warning!", JOptionPane.ERROR_MESSAGE, warning);
                saveButton.setEnabled(false);
                removeButton.setEnabled(false);
                addButton.setEnabled(false);
                jTable.setEnabled(false);
            }


        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            String [] options = {"Retry","Quit"};
            ImageIcon icon_server_retry = new ImageIcon(getClass().getResource("/icon/server_retry_64.png"));
            int select = JOptionPane.showOptionDialog(this, "Server Connection Failed!!\n"+msg+"\nPlease,Retry Connection or Quit!", "Connection Failed!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_server_retry, options, options[0]);
            if(select==0){
                ConnectCourseOutcomeFrame evfrm = new ConnectCourseOutcomeFrame(this.idT06,this.idT01);
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
        disconnectedCourseCombo = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        connectedCourseCombo = new javax.swing.JComboBox<>();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Connect CLO");
        setMinimumSize(new java.awt.Dimension(550, 570));
        setPreferredSize(new java.awt.Dimension(560, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(234, 100));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/syllabusSettings_64.png"))); // NOI18N
        jLabel1.setText("CONNECT COURSE OUTCOME OF #DEFAULT");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel10.setPreferredSize(new java.awt.Dimension(270, 40));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel5.setText("Disconnected Course:");
        jPanel10.add(jLabel5);

        disconnectedCourseCombo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        disconnectedCourseCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        disconnectedCourseCombo.setPreferredSize(new java.awt.Dimension(130, 30));
        disconnectedCourseCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectedCourseComboActionPerformed(evt);
            }
        });
        jPanel10.add(disconnectedCourseCombo);

        jPanel5.add(jPanel10, java.awt.BorderLayout.WEST);

        jPanel11.setPreferredSize(new java.awt.Dimension(260, 45));

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel6.setText("Connected Course:");
        jPanel11.add(jLabel6);

        connectedCourseCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        connectedCourseCombo.setPreferredSize(new java.awt.Dimension(130, 30));
        connectedCourseCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectedCourseComboActionPerformed(evt);
            }
        });
        jPanel11.add(connectedCourseCombo);

        jPanel5.add(jPanel11, java.awt.BorderLayout.EAST);

        jPanel2.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(10, 50));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setText("Selected Course:");
        jPanel3.add(jLabel4);

        selectedCourseField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        selectedCourseField.setFocusable(false);
        selectedCourseField.setPreferredSize(new java.awt.Dimension(120, 30));
        jPanel3.add(selectedCourseField);

        jPanel7.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel3.add(jPanel7);

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
        cloCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cloCountActionPerformed(evt);
            }
        });
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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("***Give \"X\" or any value to Connect");
        jPanel9.add(jLabel3);

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

        saveButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.setPreferredSize(new java.awt.Dimension(120, 30));
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
        int row_count = jTable.getRowCount();
        int col_count = jTable.getColumnCount();
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
        
        
        try{
            connect.setAutoCommit(false);
            
            String course_id = selectedCourseField.getText().trim();
            String current_idT07 = null;
            for(int i=0;i<T07_info.length;i++){
                if(T07_info[i][1].equals(course_id)){
                    current_idT07 = T07_info[i][0];
                    break;
                }
            }
            ps = connect.prepareStatement("DELETE FROM `t10_course_clo` WHERE T07_id_fk = ?");
            ps.setString(1, current_idT07);
            ps.executeUpdate();
            
            
            
            for(int i=0,k=0;i<row_count;i++){
                //inserting CLO
                String current_idT10=null;
                if((!prev_T10_id[0].equals("None!")) && prev_T10_id.length>i){
                    ps = connect.prepareStatement("INSERT INTO `t10_course_clo`(`T10_id`, `T07_id_fk`, `clo_number`) VALUES (? ,? , ?)");
                    ps.setString(1, prev_T10_id[i]);
                    ps.setString(2, current_idT07);
                    ps.setString(3, String.valueOf(i+1));
                    ps.executeUpdate();
                    current_idT10 = prev_T10_id[i];
                }else{
                    ps = connect.prepareStatement("INSERT INTO `t10_course_clo`(`T07_id_fk`, `clo_number`) VALUES (? , ?)");
                    ps.setString(1, current_idT07);
                    ps.setString(2, String.valueOf(i+1));
                    ps.executeUpdate();
                    
                    //getting current T10id
                    ps = connect.prepareStatement("SELECT T10_id FROM `t10_course_clo` WHERE T07_id_fk = ? AND clo_number = ?");
                    ps.setString(1, current_idT07);
                    ps.setString(2, String.valueOf(i+1));
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        current_idT10 = rs.getString(1);
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
            
            if (!connected) {
                //course status to 1
                ps = connect.prepareStatement("UPDATE `t07_course` SET `course_status`= 1 WHERE T07_id = ?");
                ps.setString(1, current_idT07);
                ps.executeUpdate();
                
                if(disconnectedCourseCombo.getItemCount()==1){
                    String [] temp1 = {"None!"};
                    disconnectedCourseCombo.setModel(new DefaultComboBoxModel(temp1));
                    disconnectedCourseCombo.setEnabled(false);
                }else{
                    disconnectedCourseCombo.removeItem((Object) course_id);
                }
                
                
                if(connectedCourseCombo.getItemAt(0).equals("None!")){
                    connectedCourseCombo.removeAllItems();
                }
                connectedCourseCombo.addItem(course_id);

                int item_count = connectedCourseCombo.getItemCount();
                String[] temp = new String[item_count];
                for (int i = 0; i < item_count; i++) {
                    temp [i] = connectedCourseCombo.getItemAt(i);
                }
                Arrays.sort(temp,String.CASE_INSENSITIVE_ORDER);
                connectedCourseCombo.setModel(new DefaultComboBoxModel(temp));
                connectedCourseCombo.setEnabled(true);
                

            }
            
            
            connect.commit();
            connect.setAutoCommit(true);
            ImageIcon success = new ImageIcon(getClass().getResource("/icon/success_64.png"));
            JOptionPane.showMessageDialog(this, "CLO to PLO connect Success!", "Success!", JOptionPane.ERROR_MESSAGE, success);
            
            saveButton.setEnabled(false);
            selectedCourseField.setText("");
            cloCount.setText("0");
            DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
            dModel.setRowCount(0);
            jTable.setModel(dModel);
            jTable.revalidate();
            jTable.repaint();
            disconnectedCourseCombo.setSelectedIndex(0);
            
            
            
        }catch (SQLException ex) {
            try {
                connect.rollback();
                connect.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        
        /*
        try{
            //delete previous values
            int row_count = jTable.getRowCount();
            connect.setAutoCommit(false);
            ps = connect.prepareStatement("DELETE FROM `t09_syllabus_plo` WHERE T06_id_fk = ?");
            ps.setString(1, idT06);
            ps.executeUpdate();

            for(int i=0;i<row_count;i++){
                if(T09_id.length>i && (!T09_id[0].equals("None!"))){
                    ps = connect.prepareStatement("INSERT INTO `t09_syllabus_plo`(`T09_id`, `plo_id`, `T06_id_fk`, `plo_name`) VALUES (?,?,?,?)");
                    ps.setString(1, T09_id[i]);
                    ps.setString(2, jTable.getValueAt(i, 0).toString().trim());
                    ps.setString(3, idT06);
                    ps.setString(4, jTable.getValueAt(i, 1).toString().trim());
                    ps.executeUpdate();
                }else{
                    ps = connect.prepareStatement("INSERT INTO `t09_syllabus_plo`(`plo_id`, `T06_id_fk`, `plo_name`) VALUES (?,?,?)");

                    ps.setString(1, jTable.getValueAt(i, 0).toString().trim());
                    ps.setString(2, idT06);
                    ps.setString(3, jTable.getValueAt(i, 1).toString().trim());
                    ps.executeUpdate();
                }
            }



            connect.commit();
            connect.setAutoCommit(true);

            ImageIcon success = new ImageIcon(getClass().getResource("/icon/success_64.png"));
            JOptionPane.showMessageDialog(this, "Syllabus PLO Entry Successful!", "Success!", JOptionPane.ERROR_MESSAGE, success);
            this.dispose();

        }catch (SQLException ex) {
            try {
                connect.rollback();
                connect.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
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


        
        /*
        try{
            //delete previous values
            int row_count = jTable.getRowCount();
            connect.setAutoCommit(false);
            ps = connect.prepareStatement("DELETE FROM `t09_syllabus_plo` WHERE T06_id_fk = ?");
            ps.setString(1, idT06);
            ps.executeUpdate();

            for(int i=0;i<row_count;i++){
                if(T09_id.length>i && (!T09_id[0].equals("None!"))){
                    ps = connect.prepareStatement("INSERT INTO `t09_syllabus_plo`(`T09_id`, `plo_id`, `T06_id_fk`, `plo_name`) VALUES (?,?,?,?)");
                    ps.setString(1, T09_id[i]);
                    ps.setString(2, jTable.getValueAt(i, 0).toString().trim());
                    ps.setString(3, idT06);
                    ps.setString(4, jTable.getValueAt(i, 1).toString().trim());
                    ps.executeUpdate();
                }else{
                    ps = connect.prepareStatement("INSERT INTO `t09_syllabus_plo`(`plo_id`, `T06_id_fk`, `plo_name`) VALUES (?,?,?)");

                    ps.setString(1, jTable.getValueAt(i, 0).toString().trim());
                    ps.setString(2, idT06);
                    ps.setString(3, jTable.getValueAt(i, 1).toString().trim());
                    ps.executeUpdate();
                }
            }



            connect.commit();
            connect.setAutoCommit(true);

            ImageIcon success = new ImageIcon(getClass().getResource("/icon/success_64.png"));
            JOptionPane.showMessageDialog(this, "Syllabus PLO Entry Successful!", "Success!", JOptionPane.ERROR_MESSAGE, success);
            this.dispose();

        }catch (SQLException ex) {
            try {
                connect.rollback();
                connect.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
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


        
        /*
        try{
            //delete previous values
            int row_count = jTable.getRowCount();
            connect.setAutoCommit(false);
            ps = connect.prepareStatement("DELETE FROM `t09_syllabus_plo` WHERE T06_id_fk = ?");
            ps.setString(1, idT06);
            ps.executeUpdate();

            for(int i=0;i<row_count;i++){
                if(T09_id.length>i && (!T09_id[0].equals("None!"))){
                    ps = connect.prepareStatement("INSERT INTO `t09_syllabus_plo`(`T09_id`, `plo_id`, `T06_id_fk`, `plo_name`) VALUES (?,?,?,?)");
                    ps.setString(1, T09_id[i]);
                    ps.setString(2, jTable.getValueAt(i, 0).toString().trim());
                    ps.setString(3, idT06);
                    ps.setString(4, jTable.getValueAt(i, 1).toString().trim());
                    ps.executeUpdate();
                }else{
                    ps = connect.prepareStatement("INSERT INTO `t09_syllabus_plo`(`plo_id`, `T06_id_fk`, `plo_name`) VALUES (?,?,?)");

                    ps.setString(1, jTable.getValueAt(i, 0).toString().trim());
                    ps.setString(2, idT06);
                    ps.setString(3, jTable.getValueAt(i, 1).toString().trim());
                    ps.executeUpdate();
                }
            }



            connect.commit();
            connect.setAutoCommit(true);

            ImageIcon success = new ImageIcon(getClass().getResource("/icon/success_64.png"));
            JOptionPane.showMessageDialog(this, "Syllabus PLO Entry Successful!", "Success!", JOptionPane.ERROR_MESSAGE, success);
            this.dispose();

        }catch (SQLException ex) {
            try {
                connect.rollback();
                connect.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
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


        
        /*
        try{
            //delete previous values
            int row_count = jTable.getRowCount();
            connect.setAutoCommit(false);
            ps = connect.prepareStatement("DELETE FROM `t09_syllabus_plo` WHERE T06_id_fk = ?");
            ps.setString(1, idT06);
            ps.executeUpdate();

            for(int i=0;i<row_count;i++){
                if(T09_id.length>i && (!T09_id[0].equals("None!"))){
                    ps = connect.prepareStatement("INSERT INTO `t09_syllabus_plo`(`T09_id`, `plo_id`, `T06_id_fk`, `plo_name`) VALUES (?,?,?,?)");
                    ps.setString(1, T09_id[i]);
                    ps.setString(2, jTable.getValueAt(i, 0).toString().trim());
                    ps.setString(3, idT06);
                    ps.setString(4, jTable.getValueAt(i, 1).toString().trim());
                    ps.executeUpdate();
                }else{
                    ps = connect.prepareStatement("INSERT INTO `t09_syllabus_plo`(`plo_id`, `T06_id_fk`, `plo_name`) VALUES (?,?,?)");

                    ps.setString(1, jTable.getValueAt(i, 0).toString().trim());
                    ps.setString(2, idT06);
                    ps.setString(3, jTable.getValueAt(i, 1).toString().trim());
                    ps.executeUpdate();
                }
            }



            connect.commit();
            connect.setAutoCommit(true);

            ImageIcon success = new ImageIcon(getClass().getResource("/icon/success_64.png"));
            JOptionPane.showMessageDialog(this, "Syllabus PLO Entry Successful!", "Success!", JOptionPane.ERROR_MESSAGE, success);
            this.dispose();

        }catch (SQLException ex) {
            try {
                connect.rollback();
                connect.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
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


        */

        
    }//GEN-LAST:event_saveButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:


    }//GEN-LAST:event_formWindowClosed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
        int row_count = Integer.parseInt(cloCount.getText());
        if(row_count>1){
            //System.out.println("removeButton Fire");
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

    private void disconnectedCourseComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectedCourseComboActionPerformed
        // TODO add your handling code here:
        if(!constructor_generated){
            return;
        }
        if(disconnectedCourseCombo.getItemCount()==0){
            disconnectedCourseCombo.setEnabled(false);
            return;
        }
        if(disconnectedCourseCombo.getSelectedItem().toString().equals("None!")){
            disconnectedCourseCombo.setEnabled(false);
            return;
        }
        
        
        DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
        int col_count = dModel.getColumnCount();
        while(dModel.getRowCount()>0){
            dModel.removeRow(0);
        }
        Object [] temp = new Object [col_count];
        temp [0] = (Object) "CLO1";
        for(int i=1;i<col_count;i++){
            temp[i] = (Object)"";
        }
        dModel.addRow(temp);
        jTable.setModel(dModel);
        jTable.revalidate();
        jTable.repaint();
        cloCount.setText("1");
        
        course_status = false;
        selectedCourseField.setText(disconnectedCourseCombo.getSelectedItem().toString().trim());
        saveButton.setEnabled(true);
        prev_T10_id = new String [1];
        prev_T10_id[0] = "None!";
        prev_T13_id = new String [1];
        prev_T13_id[0] = "None!";
        connected = false;
    }//GEN-LAST:event_disconnectedCourseComboActionPerformed

    private void connectedCourseComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectedCourseComboActionPerformed
        // TODO add your handling code here:
        if(!constructor_generated){
            return;
        }
        if(connectedCourseCombo.getItemCount()==0){
            connectedCourseCombo.setEnabled(false);
            return;
        }
        if(connectedCourseCombo.getSelectedItem().toString().equals("None!")){
            connectedCourseCombo.setEnabled(false);
            return;
        }
        
        String course_id = connectedCourseCombo.getSelectedItem().toString();
        String idT07 = null;
        prev_T10_id[0] = "None!";
        prev_T13_id[0] = "None!";
        //String [] table_clo;
        try{
            for(int i=0;i<T07_info.length;i++){
                if(T07_info[i][1].equals(course_id)){
                    idT07 = T07_info[i][0];
                    break;
                }
            }
            ps = connect.prepareStatement("SELECT clo_table.T10_id,clo_table.clo_number, plo_connect.T09_id_fk, plo_connect.T13_id"
                    + " FROM (SELECT T10_id,clo_number FROM `t10_course_clo` WHERE T07_id_fk = ?) AS clo_table"
                    + " LEFT JOIN t13_clo_plo_connect AS plo_connect ON clo_table.T10_id = plo_connect.T10_id_fk"
                    + " ORDER BY clo_number ASC",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT07);
            ResultSet rs = ps.executeQuery();
            rs.last();
            int T10_length = rs.getRow();
            rs.beforeFirst();
            
            
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
                if(syb_status>0){
                    saveButton.setEnabled(false);
                }else{
                    saveButton.setEnabled(true);
                }
                
                connected = true;
            }else{
                ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
                JOptionPane.showMessageDialog(this, "Major Error in DataBase!\nShould Delete Syllabus and Start New!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                selectedCourseField.setText(course_id);
                saveButton.setEnabled(false);
                return;
            }
            
            
            
            
        }catch (SQLException ex) {
            
            Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        
        
    }//GEN-LAST:event_connectedCourseComboActionPerformed

    private void cloCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cloCountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cloCountActionPerformed

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
            java.util.logging.Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new ConnectCourseOutcomeFrame("default","default").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField cloCount;
    private javax.swing.JComboBox<String> connectedCourseCombo;
    private javax.swing.JComboBox<String> disconnectedCourseCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField selectedCourseField;
    // End of variables declaration//GEN-END:variables
}

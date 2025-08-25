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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class EnterPLOFrame extends javax.swing.JFrame {

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
    boolean syllabus_status = true;
    PreparedStatement ps;
    String [] T11_id;
    String [] T09_id;
    boolean set_exists = false;
    //int evaluation_count = 0;
    DefaultTableModel default_model;
    EnterEvaluationSetFrame eevsfrm;
    
    void passEnterEvaluationSetFrame(EnterEvaluationSetFrame eevsfrm){
        this.eevsfrm = eevsfrm;
    }
    
    void reConnection(){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            connect.setAutoCommit(true);
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
    }
    void makeEditable(){
        DefaultTableModel oldModel = (DefaultTableModel) jTable.getModel();
        int rowCount = oldModel.getRowCount();
        int columnCount = oldModel.getColumnCount();

        // Copy column names
        String[] columnNames = {"PLO","PLO NAME"};
        

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
                
                return column == 1;
            }
        };


        jTable.setModel(newModel);
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

        jTable.setShowGrid(true);
        
            
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
    
    public EnterPLOFrame(String idT06, String idT01) {
        initComponents();
        this.idT06 = idT06;
        this.idT01 = idT01;
        String[] columnNames = {"PLO", "PLO NAME"};
        Object[][] data = new Object[0][0]; // 4 rows, 2 columns
        default_model = new DefaultTableModel(data, columnNames);
        
        //make only PLO NAME editable
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                
                return column == 1; // only column 1 is editable
            }
        };
        jTable.setModel(model);
        
        JTableHeader header = jTable.getTableHeader();
        header.setReorderingAllowed(false);
        jTable.setShowGrid(true);
        ploNumber.setEditable(false);
        ploNumber.setEnabled(true);
        T09_id = new String [1];
        T09_id[0] = "None!";
        
        
        
        try{
            
            //PreparedStatement ps;
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            ps = connect.prepareStatement("SELECT syllabus_id FROM `t06_syllabus` WHERE T06_id = ?");
            ps.setString(1, idT06);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                this.syllabus_id = rs.getString(1);
            }
            jLabel1.setText("PLO SETUP OF: "+syllabus_id);
            
            ps = connect.prepareStatement("SELECT plo_id AS PLO,plo_name AS \"PLO NAME\" FROM `t09_syllabus_plo` WHERE T06_id_fk = ? ORDER BY plo_id ASC");
            ps.setString(1, idT06);
            showNormalTable(ps);
            makeEditable();
            
            ps = connect.prepareStatement("SELECT T09_id FROM `t09_syllabus_plo` WHERE T06_id_fk = ? ORDER BY plo_id ASC");
            ps.setString(1, idT06);
            rs = ps.executeQuery();
            if(jTable.getRowCount()>0){
                T09_id = new String [jTable.getRowCount()];
                int i=0;
                while(rs.next() && i<T09_id.length){
                    T09_id[i] = rs.getString(1);
                    i++;
                }
            }
            ploNumber.setText(String.valueOf(jTable.getRowCount()));
            
            ps = connect.prepareStatement("SELECT syllabus_status FROM `t06_syllabus` WHERE T06_id =?");
            ps.setString(1, idT06);
            rs = ps.executeQuery();
            int status = 0;
            if(rs.next()){
                status = rs.getInt(1);
            }
            if(status>0){
                ImageIcon warning = new ImageIcon(getClass().getResource("/icon/warning_64.png"));
                JOptionPane.showMessageDialog(this, """
                                                    WARNING!
                                                    Syllabus is Already Finalized!!
                                                    Syllabus can not be Changed!""", "Warning!", JOptionPane.ERROR_MESSAGE, warning);
                saveButton.setEnabled(false);
                jButton1.setEnabled(false);
                jButton2.setEnabled(false);
            }
            
        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            String [] options = {"Retry","Quit"};
            ImageIcon icon_server_retry = new ImageIcon(getClass().getResource("/icon/server_retry_64.png"));
            int select = JOptionPane.showOptionDialog(this, "Server Connection Failed!!\n"+msg+"\nPlease,Retry Connection or Quit!", "Connection Failed!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_server_retry, options, options[0]);
            if(select==0){
                EnterPLOFrame evfrm = new EnterPLOFrame(this.idT06,this.idT01);
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
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        ploNumber = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PLO Setup");
        setPreferredSize(new java.awt.Dimension(542, 560));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(234, 100));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/syllabusSettings_64.png"))); // NOI18N
        jLabel1.setText("PLO SETUP OF #DEFAULT");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("***CAUTION: Editing PLO setup will efftect Course,CLO and PLO connections");
        jPanel5.add(jLabel3);

        jPanel2.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(10, 60));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("Number of PLO: ");
        jPanel3.add(jLabel2);

        jButton1.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jButton1.setText("-");
        jButton1.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        ploNumber.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        ploNumber.setText("0");
        ploNumber.setFocusable(false);
        ploNumber.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel3.add(ploNumber);

        jButton2.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jButton2.setText("+");
        jButton2.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);

        jPanel4.add(jPanel3, java.awt.BorderLayout.NORTH);

        jTable.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PLO", "PLO NAME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable);

        jPanel8.add(jScrollPane1);

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
        try{
            //delete previous values
            int row_count = jTable.getRowCount();
            
            for(int i=0;i<row_count;i++){
                if(jTable.getValueAt(i, 1).toString().trim().isBlank()){
                    ImageIcon data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));
                    JOptionPane.showMessageDialog(this, "Data Field in Table is Empty!", "Data Error!", JOptionPane.ERROR_MESSAGE, data_error);
                    return;
                }
            }
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
            
            //setting course status to 0
            ps = connect.prepareStatement("UPDATE `t07_course` SET `course_status`= 0 WHERE T06_id_fk = ?");
            ps.setString(1, idT06);
            ps.executeUpdate();
            
            //deleteing CLOs from courses
            ps = connect.prepareStatement("DELETE t10_course_clo FROM `t10_course_clo` JOIN t07_course ON T07_id_fk = t07_course.T07_id "
                    + "WHERE t07_course.T06_id_fk = ?");
            ps.setString(1, idT06);
            ps.executeUpdate();
            
            
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
                Logger.getLogger(EnterPLOFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(EnterPLOFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(EnterPLOFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        if(evaluationCount.getText().isBlank()){
            ImageIcon data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));
            JOptionPane.showMessageDialog(this, "Evaluation Count is Empty", "Data Error!", JOptionPane.ERROR_MESSAGE, data_error);
            return;
            
        }
        if(totalMarks.getText().isBlank()){
            ImageIcon data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));
            JOptionPane.showMessageDialog(this, "Total Marks is Empty", "Data Error!", JOptionPane.ERROR_MESSAGE, data_error);
            return;
        }
        if(evaluationSetName.getText().isBlank()){
            ImageIcon data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));
            JOptionPane.showMessageDialog(this, "Evaluation Set Name is Empty", "Data Error!", JOptionPane.ERROR_MESSAGE, data_error);
            return;
            
        }
        int evaluation_count = Integer.parseInt(evaluationCount.getText());
        double total_marks = Double.parseDouble(totalMarks.getText());
        int row_count = jTable.getRowCount();
        double [] marks = new double [row_count];
        String [] evaluation_name = new String [row_count];

        double sum_mark =0;
        
        try{
            connect.setAutoCommit(false);
            for(int i=0;i<row_count;i++){
                marks[i] = Double.parseDouble(jTable.getValueAt(i, 1).toString().trim());
            }
            for(int i=0;i<marks.length;i++){
                sum_mark+=marks[i];
            }
            if(sum_mark!=total_marks){
                ImageIcon data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));
                JOptionPane.showMessageDialog(this, "Total Mark doesn't match with the Evaluation marks", "Data Error!", JOptionPane.ERROR_MESSAGE,data_error);
                return;
            }
            
            for(int i=0;i<row_count;i++){
                evaluation_name[i] = jTable.getValueAt(i, 0).toString().trim();
                if(evaluation_name[i].isBlank()){
                    ImageIcon data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));
                    JOptionPane.showMessageDialog(this, "Evaluation Name is Empty!!", "Data Error!", JOptionPane.ERROR_MESSAGE,data_error);
                    return;
                }
            }
            for(int i=0;i<evaluation_name.length;i++){
                for(int j=i+1 ; j<evaluation_name.length;j++){
                    if(evaluation_name[i].equals(evaluation_name[j])){
                        ImageIcon data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));
                        JOptionPane.showMessageDialog(this, "Duplicate Evaluation Name!!", "Data Error!", JOptionPane.ERROR_MESSAGE, data_error);
                        return;
                    }
                }
            }
            if(!set_exists){
                ps = connect.prepareStatement("INSERT INTO `t08_evaluation_set` (`evaluation_set_id`, `T01_id_fk`, `total_marks`) VALUES (?,?,?)");
                ps.setString(1, evaluationSetName.getText().trim());
                ps.setString(2, idT01);
                ps.setString(3, String.valueOf(total_marks));
                ps.executeUpdate();
                
                ps =connect.prepareStatement("SELECT T08_id FROM `t08_evaluation_set` WHERE "
                    + "T01_id_fk = ? AND evaluation_set_id = ?");
                ps.setString(1, idT01);
                ps.setString(2, evaluationSetName.getText().trim());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    idT08 = rs.getString(1);
                    //System.out.println("T08: "+idT08);
                }
            }else{
                ps = connect.prepareStatement("UPDATE t08_evaluation_set SET total_marks = ? WHERE T08_id=?");
                ps.setDouble(1, total_marks);
                ps.setString(2, idT08);
                ps.executeUpdate();
            }
            
            
            
            
            for(int i=0;i<row_count;i++){
                
                ps= connect.prepareStatement("SELECT * FROM `t11_evaluation` WHERE evaluation_name = ? AND T08_id_fk = ?");
                ps.setString(1, evaluation_name[i]);
                ps.setString(2, idT08);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    ps = connect.prepareStatement("UPDATE `t11_evaluation` SET `evaluation_name`= ? WHERE T11_id = ?");
                    ps.setString(1, "default"+i);
                    ps.setString(2, rs.getString("T11_id"));
                    ps.executeUpdate();
                }
                
                if((!T11_id[0].equals("-1")) && T11_id.length>i){
                    //Update
                    ps = connect.prepareStatement("UPDATE t11_evaluation SET evaluation_name = ?, marks = ? WHERE T11_id = ?");
                    ps.setString(1, evaluation_name[i]);
                    ps.setDouble(2, marks[i]);
                    ps.setString(3, T11_id[i]);
                    ps.executeUpdate();
                }else{
                    //Insert
                    ps = connect.prepareStatement("INSERT INTO `t11_evaluation`(`T08_id_fk`, `evaluation_name`, `marks`) "
                            + "VALUES (?,?,?)");
                    ps.setString(1, idT08);
                    ps.setString(2, evaluation_name[i]);
                    ps.setDouble(3, marks[i]);
                    ps.executeUpdate();
                }
            }
            if (T11_id.length > row_count) {
                for(int i=row_count;i<T11_id.length;i++){
                    ps = connect.prepareStatement("DELETE FROM `t11_evaluation` WHERE T11_id = ?");
                    ps.setString(1, T11_id[i]);
                    ps.executeUpdate();
                }
            }
            jTable.setModel(default_model);
            evaluationSetName.setText("");
            evaluationCount.setText("4");
            totalMarks.setText("100");
            
            ps = connect.prepareStatement("SELECT T08_id,evaluation_set_id FROM `t08_evaluation_set` WHERE T01_id_fk = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT01);
            ResultSet rs = ps.executeQuery();
            
            rs.last();
            int combo_len = rs.getRow();
            rs.beforeFirst();
            if(combo_len>0){
                eevsfrm.T08_id = new String[combo_len];
                String [] temp = new String[combo_len];
                int i=0;
                while (rs.next()) {
                    temp[i] = rs.getString(2);
                    eevsfrm.T08_id [i] = rs.getString(1);
                    i++;
                }
                eevsfrm.setComboBox(new DefaultComboBoxModel(temp),new DefaultComboBoxModel(temp));
            }
            connect.commit();
            connect.setAutoCommit(true);
            
            ImageIcon success = new ImageIcon(getClass().getResource("/icon/success_64.png"));
            JOptionPane.showMessageDialog(this, "Evaluation Set Entry Successful!", "Success!", JOptionPane.ERROR_MESSAGE, success);
            
            
        }catch(NumberFormatException e1){
            ImageIcon data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));
            JOptionPane.showMessageDialog(this, "Error in Input Data!!", "Data Error!", JOptionPane.ERROR_MESSAGE, data_error);
            //System.out.println(e1);
            
        }catch(NullPointerException e2){
            ImageIcon data_error = new ImageIcon(getClass().getResource("/icon/error_data_64.png"));
            JOptionPane.showMessageDialog(this, "Error in Input Data!!", "Data Error!", JOptionPane.ERROR_MESSAGE, data_error);
            //System.out.println(e2);
        } catch (SQLException ex) {
            try {
                connect.rollback();
                connect.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(EnterPLOFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(EnterPLOFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(EnterPLOFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        }*/
    }//GEN-LAST:event_saveButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_formWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int row_count = Integer.parseInt(ploNumber.getText());
        if(row_count>0){
            row_count--;
            DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
            dModel.removeRow(jTable.getRowCount()-1);
            jTable.setModel(dModel);
            //makeEditable();
            jTable.revalidate();
            jTable.repaint();
            ploNumber.setText(String.valueOf(row_count));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int row_count = Integer.parseInt(ploNumber.getText());
        row_count++;
        
        DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
        Object [] temp = new Object [2];
        temp [0] = (Object) String.valueOf(row_count);
        temp [1] = (Object)"";
        dModel.addRow(temp);
        jTable.setModel(dModel);
        //makeEditable();
        jTable.revalidate();
        jTable.repaint();
        ploNumber.setText(String.valueOf(row_count));
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(EnterPLOFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnterPLOFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnterPLOFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnterPLOFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EnterPLOFrame("default","default").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField ploNumber;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}

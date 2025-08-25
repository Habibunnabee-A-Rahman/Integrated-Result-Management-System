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
public class EvaluationSetFrame extends javax.swing.JFrame {

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
    
    public EvaluationSetFrame(String idT06, String idT01) {
        initComponents();
        
        String[] columnNames = {"Evaluation Name", "Marks"};
        Object[][] data = new Object[4][2]; // 4 rows, 2 columns
        default_model = new DefaultTableModel(data, columnNames);
        jTable.setEnabled(false);
        jTable.setShowGrid(true);
        JTableHeader header = jTable.getTableHeader();
        header.setReorderingAllowed(false);
        
        T11_id = new String [2];
        T11_id [0] = "-1";
        set_exists = false;
        this.idT06 = idT06;
        this.idT01 = idT01;
        syllabus_status = true;
        saveButton.setEnabled(false);
        totalMarks.setEnabled(false);
        evaluationCount.setEnabled(false);
        //default_model= (DefaultTableModel) jTable.getModel();
        NumberFormat doubleFormat = new DecimalFormat("#0.00"); // Allows decimals
        doubleFormat.setGroupingUsed(false); // No commas

        // NumberFormatter for double values
        NumberFormatter doubleFormatter = new NumberFormatter(doubleFormat);
        doubleFormatter.setAllowsInvalid(false); // Prevents invalid input
        //doubleFormatter.setCommitsOnValidEdit(true); // Updates the field immediately PROBLEM losses FOCUS
        doubleFormatter.setMinimum(0.01); // Set minimum value
        
        
        //NumberFormatter for integer values
        NumberFormat integerFormat = new DecimalFormat("#0"); // Allows integer
        integerFormat.setGroupingUsed(false); // No commas

        NumberFormatter integerFormatter = new NumberFormatter(integerFormat);
        integerFormatter.setAllowsInvalid(false); // Prevents invalid input
        //integerFormatter.setCommitsOnValidEdit(true); // Updates the field immediately PROBLEM losses FOCUS
        integerFormatter.setMinimum(1);
        

 
        totalMarks.setFormatterFactory(new DefaultFormatterFactory(doubleFormatter));
        evaluationCount.setFormatterFactory(new DefaultFormatterFactory(integerFormatter));
        
        try{
            
            //PreparedStatement ps;
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            
            
            
            
        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            String [] options = {"Retry","Quit"};
            ImageIcon icon_server_retry = new ImageIcon(getClass().getResource("/icon/server_retry_64.png"));
            int select = JOptionPane.showOptionDialog(this, "Server Connection Failed!!\n"+msg+"\nPlease,Retry Connection or Quit!", "Connection Failed!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_server_retry, options, options[0]);
            if(select==0){
                EvaluationSetFrame evfrm = new EvaluationSetFrame(this.idT06,this.idT01);
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
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        evaluationSetName = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        totalMarks = new javax.swing.JFormattedTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        evaluationCount = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Evaluation Set");
        setPreferredSize(new java.awt.Dimension(540, 512));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(234, 100));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/syllabusSettings_64.png"))); // NOI18N
        jLabel1.setText("EVALUATION SET");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(10, 60));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("Evalution Set Name:");
        jPanel3.add(jLabel2);

        evaluationSetName.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        evaluationSetName.setPreferredSize(new java.awt.Dimension(180, 30));
        evaluationSetName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evaluationSetNameActionPerformed(evt);
            }
        });
        evaluationSetName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                evaluationSetNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                evaluationSetNameKeyReleased(evt);
            }
        });
        jPanel3.add(evaluationSetName);

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(10, 60));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("Total Marks:");
        jPanel5.add(jLabel3);

        totalMarks.setPreferredSize(new java.awt.Dimension(80, 30));
        totalMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalMarksActionPerformed(evt);
            }
        });
        jPanel5.add(totalMarks);

        jPanel7.setPreferredSize(new java.awt.Dimension(50, 10));
        jPanel5.add(jPanel7);

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setText("Evaluation Count:");
        jPanel5.add(jLabel4);

        evaluationCount.setPreferredSize(new java.awt.Dimension(80, 30));
        evaluationCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evaluationCountActionPerformed(evt);
            }
        });
        jPanel5.add(evaluationCount);

        jPanel4.add(jPanel5, java.awt.BorderLayout.NORTH);

        jTable.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Evaluation Name", "Marks"
            }
        ));
        jTable.setEnabled(false);
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

    private void evaluationSetNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_evaluationSetNameKeyPressed
        
    }//GEN-LAST:event_evaluationSetNameKeyPressed

    private void evaluationCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evaluationCountActionPerformed
        // TODO add your handling code here:
        if(evaluationCount.getText().isBlank()){
            return;
        }
        
        int evaluation_count = Integer.parseInt(evaluationCount.getText());
        //System.out.println(evaluation_count);
        if(evaluation_count>jTable.getRowCount()){
            DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
            while(dModel.getRowCount()<evaluation_count){
                dModel.addRow(new Object[dModel.getColumnCount()]);
            }
            jTable.setModel(dModel);
            jTable.revalidate();
            jTable.repaint();
        }else if(evaluation_count<jTable.getRowCount()){
            DefaultTableModel dModel = (DefaultTableModel) jTable.getModel();
            while(dModel.getRowCount()>evaluation_count){
                dModel.removeRow(dModel.getRowCount()-1);
            }
            jTable.setModel(dModel);
            jTable.revalidate();
            jTable.repaint();
        }
        
        
    }//GEN-LAST:event_evaluationCountActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        
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
            
            totalMarks.setText("100");
            evaluationCount.setText("4");
            evaluationSetName.setText("");
            String[] columnNames = {"Evaluation Name", "Marks"};
            Object[][] data = new Object[4][2]; // 4 rows, 2 columns
            default_model = new DefaultTableModel(data, columnNames);
            jTable.setModel(default_model);
            jTable.setEnabled(false);
            jTable.setShowGrid(true);
            JTableHeader header = jTable.getTableHeader();
            header.setReorderingAllowed(false);
            
            ImageIcon success = new ImageIcon(getClass().getResource("/icon/success_64.png"));
            JOptionPane.showMessageDialog(this, "Evaluation Set Entry Successful!", "Success!", JOptionPane.ERROR_MESSAGE, success);
            //this.dispose();
            
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
                Logger.getLogger(EvaluationSetFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(EvaluationSetFrame.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(EvaluationSetFrame.class.getName()).log(Level.SEVERE, null, ex);
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

    private void totalMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalMarksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalMarksActionPerformed

    private void evaluationSetNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evaluationSetNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_evaluationSetNameActionPerformed

    private void evaluationSetNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_evaluationSetNameKeyReleased
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            //String temp_T08id = "";
            connect.setAutoCommit(true);
            T11_id [0] = "-1";
            set_exists = false;
            ps =connect.prepareStatement("SELECT * FROM `t08_evaluation_set` WHERE "
                    + "T01_id_fk = ? AND evaluation_set_id = ?");
            ps.setString(1, idT01);
            ps.setString(2, evaluationSetName.getText().trim());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                set_exists = true;
                idT08 = rs.getString("T08_id");
                int editable = rs.getInt("editable");  // int can't have null value. It will have "0" value if you put null in it
                double t_mark = rs.getDouble("total_marks");
                if(t_mark>0){
                    totalMarks.setText(String.valueOf(t_mark));
                }
                if(editable>0){
                    jTable.setEnabled(false);
                    saveButton.setEnabled(false);
                    //deleteButton.setEnabled(false);
                    totalMarks.setEnabled(false);
                    evaluationCount.setEnabled(false);
                    
                }else{
                    jTable.setEnabled(true);
                    saveButton.setEnabled(true);
                    //deleteButton.setEnabled(true);
                    totalMarks.setEnabled(true);
                    evaluationCount.setEnabled(true);
                }
                ps = connect.prepareStatement("SELECT evaluation_name,marks FROM `t11_evaluation` WHERE T08_id_fk= ? ORDER BY marks ASC");
                ps.setString(1, rs.getString("T08_id"));
                showNormalTable(ps);
                //default_model= (DefaultTableModel) jTable.getModel();
                
                int row_count = jTable.getRowCount();
                if(row_count>0){
                    evaluationCount.setText(String.valueOf(row_count));
                    T11_id = new String[row_count];
                    ps = connect.prepareStatement("SELECT T11_id FROM `t11_evaluation` WHERE T08_id_fk= ? ORDER BY marks ASC");
                    ps.setString(1, idT08);
                    rs = ps.executeQuery();
                    for(int i=0;rs.next() && i<T11_id.length ;i++){
                        T11_id [i] = rs.getString(1);
                        
                    }
                }
                
            }else{
                jTable.setModel(default_model);
                jTable.setEnabled(true);
                saveButton.setEnabled(true);
                totalMarks.setEnabled(true);
                evaluationCount.setEnabled(true);
                evaluationCount.setText(String.valueOf(jTable.getRowCount()));
                totalMarks.setText("100");
            }
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationSetFrame.class.getName()).log(Level.SEVERE, null, ex);
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
    }//GEN-LAST:event_evaluationSetNameKeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        eevsfrm.setEnabled(true);
        eevsfrm.setAlwaysOnTop(true);
        eevsfrm.setAlwaysOnTop(false);
        
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(EvaluationSetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EvaluationSetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EvaluationSetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EvaluationSetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new EvaluationSetFrame("default","default").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField evaluationCount;
    private javax.swing.JTextField evaluationSetName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JButton saveButton;
    private javax.swing.JFormattedTextField totalMarks;
    // End of variables declaration//GEN-END:variables
}

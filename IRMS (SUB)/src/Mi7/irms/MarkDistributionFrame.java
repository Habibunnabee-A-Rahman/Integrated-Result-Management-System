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
public class MarkDistributionFrame extends javax.swing.JFrame {

    /**
     * Creates new form EnterUniversity
     */
    Connection connect;
    PreparedStatement ps;
    
    //String uni_id="";
    String [] T03_id = new String [100];
    String [] T14_id;
    String [] T16_id;
    String idT06 = "";
    String idT01 = "";
    String syllabus_id = "";
    boolean syllabus_status = true;
    String idT14="None!";
    String idT16="None!";
    MainFrame mfrm;
    String token = "";
    String id="";
    String uni_id="";
    int profile = -1;
    int job_id = -1;
    void reConnection(){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            connect.setAutoCommit(true);
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
    }
    void passMainFrame(MainFrame mfrm){
        this.mfrm = mfrm;
    }
    
    
    
    public MarkDistributionFrame(String idT06,String idT01,String token) {
        initComponents();
        this.idT06 = idT06;
        this.idT01 = idT01;
        this.token = token;
        T14_id = new String[1];
        T14_id[0] = "None!";
        T16_id = new String[1];
        T16_id[0] = "None!";
        enterButton.setEnabled(false);
        createButton.setEnabled(false);
        semesterComboBox.setEnabled(false);
        teacherComboBox.setEnabled(false);
        idT14 ="None!";
        idT16 = "None!";
        String [] tempToken = token.split(" ");
        this.id = tempToken[0];
        this.uni_id = tempToken[1];
        this.profile = Integer.parseInt(tempToken[2]);
        try{
            
            //PreparedStatement ps;
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            ps = connect.prepareStatement("SELECT syllabus_id FROM `t06_syllabus` WHERE T06_id = ?");
            ps.setString(1, idT06);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                this.syllabus_id = rs.getString(1);
            }
            jLabel1.setText("MARK DISTRIBUTION FOR: "+syllabus_id);
            
            
            job_id = -1;
            //teacherComboBox
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
                    + "WHERE t14_semester.T06_id_fk=? AND t14_semester.semester_status>0",
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
            
            
 
            
            
            //teacherComboBox
            /*
            ps = connect.prepareStatement("SELECT DISTINCT xyz.T16_id,xyz.employee_id,xyz.employee_name "
                    + "FROM (SELECT t16_employee.T16_id,t16_employee.employee_id,t16_employee.employee_name,t15_offered_course.T14_id_fk "
                    + "FROM t16_employee JOIN t15_offered_course ON "
                    + "t16_employee.T16_id = t15_offered_course.T16_id_fk) AS xyz "
                    + "JOIN t14_semester ON t14_semester.T14_id = xyz.T14_id_fk "
                    + "WHERE t14_semester.T06_id_fk=? AND t14_semester.semester_status>0",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT06);
            rs = ps.executeQuery();
            rs.last();
            int count = rs.getRow();
            rs.beforeFirst();
            if (count > 0) {
                
                T16_id = new String[count];
                String [] comboArray = new String [count];
                for (int i = 0; rs.next() && i < T16_id.length; i++) {

                    T16_id[i] = rs.getString(1);
                    String[] temp_name = rs.getString(3).trim().split(" ");
                    comboArray[i] = rs.getString(2) + "--" + temp_name[0];

                }
                
                
                teacherComboBox.setModel(new DefaultComboBoxModel(comboArray));
                teacherComboBox.setEnabled(true);
                teacherComboBox.setSelectedIndex(0);
            }
            */

            
        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            String [] options = {"Retry","Quit"};
            ImageIcon icon_server_retry = new ImageIcon(getClass().getResource("/icon/server_retry_64.png"));
            int select = JOptionPane.showOptionDialog(null, "Server Connection Failed!!\n"+msg+"\nPlease,Retry Connection or Quit!", "Connection Failed!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_server_retry, options, options[0]);
            if(select==0){
                MarkDistributionFrame eocfrm = new MarkDistributionFrame(this.idT06,this.idT01,this.token);
                eocfrm.setLocationRelativeTo(null);
                eocfrm.setVisible(true);
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
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        teacherComboBox = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        semesterComboBox = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        createButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        enterButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mark Distribution");
        setPreferredSize(new java.awt.Dimension(431, 508));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(234, 100));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mark_distribution_64.png"))); // NOI18N
        jLabel1.setText("MARK DISTRIBUTION FOR #DEFAULT");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.GridLayout(4, 1));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setText("Select Teacher:  ");
        jPanel8.add(jLabel3);

        teacherComboBox.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        teacherComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        teacherComboBox.setPreferredSize(new java.awt.Dimension(180, 40));
        teacherComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherComboBoxActionPerformed(evt);
            }
        });
        jPanel8.add(teacherComboBox);

        jPanel2.add(jPanel8);

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setText("Select Semester:");
        jPanel4.add(jLabel2);

        semesterComboBox.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        semesterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        semesterComboBox.setPreferredSize(new java.awt.Dimension(180, 40));
        semesterComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semesterComboBoxActionPerformed(evt);
            }
        });
        jPanel4.add(semesterComboBox);

        jPanel2.add(jPanel4);

        createButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        createButton.setText("Create Mark Distribution");
        createButton.setPreferredSize(new java.awt.Dimension(300, 40));
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });
        jPanel3.add(createButton);

        jPanel2.add(jPanel3);

        enterButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        enterButton.setText("Enter Mark");
        enterButton.setPreferredSize(new java.awt.Dimension(300, 40));
        enterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterButtonActionPerformed(evt);
            }
        });
        jPanel6.add(enterButton);

        jPanel2.add(jPanel6);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterButtonActionPerformed
        
       if(T14_id[0].equals("None!")){
            enterButton.setEnabled(false);
            return;
        }
        EnterMarkFrame etrmrkfrm = new EnterMarkFrame(idT06,idT01,
                T14_id[semesterComboBox.getSelectedIndex()],T16_id[teacherComboBox.getSelectedIndex()],token);
        etrmrkfrm.setLocationRelativeTo(null);
        etrmrkfrm.passMarkDistributionFrame(this);
        etrmrkfrm.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_enterButtonActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        // TODO add your handling code here:
        if(T14_id[0].equals("None!")){
            createButton.setEnabled(false);
            return;
        }
        CreateMarkDistributionFrame crtmdisfrm = new CreateMarkDistributionFrame(idT06,idT01,
                T14_id[semesterComboBox.getSelectedIndex()],T16_id[teacherComboBox.getSelectedIndex()],token);
        crtmdisfrm.setLocationRelativeTo(null);
        crtmdisfrm.passMarkDistributionFrame(this);
        crtmdisfrm.setVisible(true);
        this.setEnabled(false);
        
        
    }//GEN-LAST:event_createButtonActionPerformed

    private void semesterComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semesterComboBoxActionPerformed
        // TODO add your handling code here:
        if(T14_id[0].equals("None!")){
            
            semesterComboBox.setEnabled(false);
            idT16="None!";
            idT14 = "None!";
            createButton.setEnabled(false);
            enterButton.setEnabled(false);
            return;
        }else{
            createButton.setEnabled(true);
            enterButton.setEnabled(true);
        }
    }//GEN-LAST:event_semesterComboBoxActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        mfrm.setEnabled(true);
        mfrm.setAlwaysOnTop(true);
        mfrm.setAlwaysOnTop(false);
    }//GEN-LAST:event_formWindowClosed

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
            idT14 = "None!";
            createButton.setEnabled(false);
            enterButton.setEnabled(false);
            return;
        }
        
        int index = teacherComboBox.getSelectedIndex();
        
        try {
            ps = connect.prepareStatement("SELECT DISTINCT t14_semester.T14_id,t14_semester.semester_id,t14_semester.semester_name"
                    + " FROM (SELECT t16_employee.T16_id,t16_employee.employee_id,t16_employee.employee_name,t15_offered_course.T14_id_fk"
                    + " FROM t16_employee JOIN t15_offered_course ON t16_employee.T16_id = t15_offered_course.T16_id_fk "
                    + "WHERE t16_employee.T16_id = ? ) AS xyz JOIN t14_semester ON t14_semester.T14_id = xyz.T14_id_fk "
                    + "WHERE t14_semester.T06_id_fk=? AND t14_semester.semester_status>0",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
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
        
    }//GEN-LAST:event_teacherComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(MarkDistributionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MarkDistributionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MarkDistributionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MarkDistributionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new MarkDistributionFrame("default","default","default").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createButton;
    private javax.swing.JButton enterButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JComboBox<String> semesterComboBox;
    private javax.swing.JComboBox<String> teacherComboBox;
    // End of variables declaration//GEN-END:variables
}

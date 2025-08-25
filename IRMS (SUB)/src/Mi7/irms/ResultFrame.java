/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Mi7.irms;

import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleInfo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STTableType;
import java.awt.CardLayout;
import java.awt.Dimension;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
//import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
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
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFTableStyleInfo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumns;
import java.awt.Color;



/**
 *
 * @author himal
 */
public class ResultFrame extends javax.swing.JFrame {

    /**
     * Creates new form EnterUniversity
     */
    Connection connect;
    PreparedStatement ps;
    
    //String uni_id="";
    String [] T03_id = new String [100];
    String [] T14_id;
    String [] T15_id;
    String [] T17_id;
    String [] T20_id;
    String idT06 = "";
    String idT01 = "";
    String syllabus_id = "";
    boolean syllabus_status = true;
    String idT14="None!";
    MainFrame mfrm;
    CardLayout resultLayout = new CardLayout();
    String grade1 = "H";
    String grade2 = "M";
    String grade3 = "L";
    String grade4 = "F";
    double score1 = 80.0;
    double score2 = 60.0;
    double score3 = 40.0;
    
    
    
    
    
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
    
    
    
    public ResultFrame(String idT06,String idT01) {
        initComponents();
        this.idT06 = idT06;
        this.idT01 = idT01;
        grade1 = "H";
        grade2 = "M";
        grade3 = "L";
        grade4 = "F";
        score1 = 80.0;
        score2 = 60.0;
        score3 = 40.0;
        T14_id = new String[1];
        T14_id[0] = "None!";
        T15_id = new String [1];
        T15_id[0] = "None!";
        T17_id = new String [1];
        T17_id[0] = "None!";
        T20_id = new String [1];
        T20_id[0] = "None!";
        resultLayout = (CardLayout)(resultCards.getLayout());
        
        
        try{
            
            //PreparedStatement ps;
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            resultComboBox.setSelectedIndex(0);
            
            
            
            
            
        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            String [] options = {"Retry","Quit"};
            ImageIcon icon_server_retry = new ImageIcon(getClass().getResource("/icon/server_retry_64.png"));
            int select = JOptionPane.showOptionDialog(null, "Server Connection Failed!!\n"+msg+"\nPlease,Retry Connection or Quit!", "Connection Failed!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icon_server_retry, options, options[0]);
            if(select==0){
                ResultFrame eocfrm = new ResultFrame(this.idT06,this.idT01);
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
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        resultComboBox = new javax.swing.JComboBox<>();
        resultCards = new javax.swing.JPanel();
        coursePanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        semesterComboBox = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        courseComboBox = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        generateButtonCourse1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        generateButtonCourse = new javax.swing.JButton();
        studentPanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        batchComboBox = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        studentComboBox = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        generateButtonStudent = new javax.swing.JButton();
        batchPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        batchComboBox2 = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        generateButtonBatch = new javax.swing.JButton();
        gradePLOPanel = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        scr1TextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        grd1TextField = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        scr2TextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        grd2TextField = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        scr3TextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        grd3TextField = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        grd4TextField = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Result & Analysis");
        setPreferredSize(new java.awt.Dimension(422, 498));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(234, 100));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/result-analysis_64.png"))); // NOI18N
        jLabel1.setText("RESULT & ANALYSIS");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(265, 70));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setText("Result Type");
        jPanel4.add(jLabel2);

        resultComboBox.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        resultComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Course-Wise", "Student-Wise", "Batch-Wise" }));
        resultComboBox.setPreferredSize(new java.awt.Dimension(180, 40));
        resultComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultComboBoxActionPerformed(evt);
            }
        });
        jPanel4.add(resultComboBox);

        jPanel2.add(jPanel4, java.awt.BorderLayout.NORTH);

        resultCards.setLayout(new java.awt.CardLayout());

        coursePanel.setLayout(new java.awt.GridLayout(4, 0));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setText("Semester: ");
        jPanel5.add(jLabel3);

        semesterComboBox.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        semesterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        semesterComboBox.setPreferredSize(new java.awt.Dimension(220, 40));
        semesterComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semesterComboBoxActionPerformed(evt);
            }
        });
        jPanel5.add(semesterComboBox);

        coursePanel.add(jPanel5);

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setText("Course: ");
        jPanel6.add(jLabel4);

        courseComboBox.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        courseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        courseComboBox.setPreferredSize(new java.awt.Dimension(180, 40));
        courseComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseComboBoxActionPerformed(evt);
            }
        });
        jPanel6.add(courseComboBox);

        coursePanel.add(jPanel6);

        generateButtonCourse1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        generateButtonCourse1.setText("Set PLO Grade");
        generateButtonCourse1.setPreferredSize(new java.awt.Dimension(200, 40));
        generateButtonCourse1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonCourse1ActionPerformed(evt);
            }
        });
        jPanel12.add(generateButtonCourse1);

        coursePanel.add(jPanel12);

        generateButtonCourse.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        generateButtonCourse.setText("Generate Result");
        generateButtonCourse.setPreferredSize(new java.awt.Dimension(200, 40));
        generateButtonCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonCourseActionPerformed(evt);
            }
        });
        jPanel7.add(generateButtonCourse);

        coursePanel.add(jPanel7);

        resultCards.add(coursePanel, "courseCard");

        studentPanel.setLayout(new java.awt.GridLayout(3, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("BatchID: ");
        jPanel8.add(jLabel5);

        batchComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        batchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        batchComboBox.setPreferredSize(new java.awt.Dimension(220, 40));
        batchComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchComboBoxActionPerformed(evt);
            }
        });
        jPanel8.add(batchComboBox);

        studentPanel.add(jPanel8);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Student: ");
        jPanel9.add(jLabel6);

        studentComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        studentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        studentComboBox.setPreferredSize(new java.awt.Dimension(180, 40));
        studentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentComboBoxActionPerformed(evt);
            }
        });
        jPanel9.add(studentComboBox);

        studentPanel.add(jPanel9);

        generateButtonStudent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        generateButtonStudent.setText("Generate Result");
        generateButtonStudent.setPreferredSize(new java.awt.Dimension(200, 40));
        generateButtonStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonStudentActionPerformed(evt);
            }
        });
        jPanel10.add(generateButtonStudent);

        studentPanel.add(jPanel10);

        resultCards.add(studentPanel, "studentCard");

        batchPanel.setLayout(new java.awt.GridLayout(2, 0));

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("BatchID: ");
        jPanel3.add(jLabel7);

        batchComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        batchComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None!" }));
        batchComboBox2.setPreferredSize(new java.awt.Dimension(220, 40));
        batchComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchComboBox2ActionPerformed(evt);
            }
        });
        jPanel3.add(batchComboBox2);

        batchPanel.add(jPanel3);

        generateButtonBatch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        generateButtonBatch.setText("Generate Result");
        generateButtonBatch.setPreferredSize(new java.awt.Dimension(200, 40));
        generateButtonBatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonBatchActionPerformed(evt);
            }
        });
        jPanel11.add(generateButtonBatch);

        batchPanel.add(jPanel11);

        resultCards.add(batchPanel, "batchCard");

        gradePLOPanel.setLayout(new java.awt.GridLayout(6, 1));

        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Value");
        jPanel13.add(jLabel10);

        jPanel21.setBackground(new java.awt.Color(201, 0, 0));
        jPanel21.setForeground(new java.awt.Color(193, 0, 0));
        jPanel21.setPreferredSize(new java.awt.Dimension(10, 30));
        jPanel13.add(jPanel21);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 153));
        jLabel8.setText("***Set Grade High to Low");
        jPanel13.add(jLabel8);

        jPanel20.setBackground(new java.awt.Color(201, 0, 0));
        jPanel20.setForeground(new java.awt.Color(193, 0, 0));
        jPanel20.setPreferredSize(new java.awt.Dimension(10, 30));
        jPanel13.add(jPanel20);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Grade");
        jPanel13.add(jLabel11);

        gradePLOPanel.add(jPanel13);

        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        scr1TextField.setPreferredSize(new java.awt.Dimension(50, 25));
        scr1TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scr1TextFieldActionPerformed(evt);
            }
        });
        jPanel14.add(scr1TextField);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel9.setText(">= Set to");
        jPanel14.add(jLabel9);

        grd1TextField.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel14.add(grd1TextField);

        gradePLOPanel.add(jPanel14);

        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        scr2TextField.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel15.add(scr2TextField);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel12.setText(">= Set to");
        jPanel15.add(jLabel12);

        grd2TextField.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel15.add(grd2TextField);

        gradePLOPanel.add(jPanel15);

        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        scr3TextField.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel16.add(scr3TextField);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel13.setText(">= Set to");
        jPanel16.add(jLabel13);

        grd3TextField.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel16.add(grd3TextField);

        gradePLOPanel.add(jPanel16);

        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel15.setText("OtherWise");
        jPanel17.add(jLabel15);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel14.setText("Set to  ");
        jPanel17.add(jLabel14);

        grd4TextField.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel17.add(grd4TextField);

        gradePLOPanel.add(jPanel17);

        jButton2.setText("BACK");
        jButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton2);

        jPanel19.setPreferredSize(new java.awt.Dimension(40, 11));
        jPanel18.add(jPanel19);

        jButton1.setText("SAVE");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton1);

        gradePLOPanel.add(jPanel18);

        resultCards.add(gradePLOPanel, "gradeCard");

        jPanel2.add(resultCards, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resultComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultComboBoxActionPerformed
        // TODO add your handling code here:
        T14_id = new String[1];
        T14_id[0] = "None!";
        T15_id = new String [1];
        T15_id[0] = "None!";
        T17_id = new String [1];
        T17_id[0] = "None!";
        T20_id = new String [1];
        T20_id[0] = "None!";
        ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
        int result_index = resultComboBox.getSelectedIndex();
        String [] temp_nonex = {"None!"};
        semesterComboBox.setModel(new DefaultComboBoxModel(temp_nonex));
        courseComboBox.setModel(new DefaultComboBoxModel(temp_nonex));
        semesterComboBox.setEnabled(false);
        courseComboBox.setEnabled(false);
        batchComboBox.setModel(new DefaultComboBoxModel(temp_nonex));
        batchComboBox2.setModel(new DefaultComboBoxModel(temp_nonex));
        studentComboBox.setModel(new DefaultComboBoxModel(temp_nonex));
        batchComboBox.setEnabled(false);
        batchComboBox2.setEnabled(false);
        studentComboBox.setEnabled(false);
        
        
        T14_id = new String [1];
        T14_id [0] = "None!";
        T15_id = new String [1];
        T15_id [0] = "None!";
        T17_id = new String [1];
        T17_id [0] = "None!";
        if(result_index == 0){
            //Course-Wise
            resultLayout.show(resultCards, "courseCard");
            try {
                ps = connect.prepareStatement("SELECT T14_id,semester_id,semester_name FROM `t14_semester` "
                        + "WHERE T06_id_fk = ? AND semester_status>0 ORDER BY semester_id ASC",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ps.setString(1,idT06);
                ResultSet rs = ps.executeQuery();
                rs.last();
                int count_semester = 0;
                count_semester = rs.getRow();
                rs.beforeFirst();
                if(count_semester>0){
                    T14_id = new String [count_semester];
                    String [] temp_semester = new String [count_semester];
                    for(int i=0;rs.next()&& i<count_semester;i++){
                        T14_id [i] = rs.getString(1);
                        temp_semester[i] = rs.getString(2)+"--"+rs.getString(3);
                    }
                    semesterComboBox.setModel(new DefaultComboBoxModel(temp_semester));
                    semesterComboBox.setEnabled(true);
                    semesterComboBox.setSelectedIndex(0);
                    
                }else{
                    String [] temp_none = {"None!"};
                    semesterComboBox.setModel(new DefaultComboBoxModel(temp_none));
                    courseComboBox.setModel(new DefaultComboBoxModel(temp_none));
                    semesterComboBox.setEnabled(false);
                    courseComboBox.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "No Semester Exists for the Syllabus", "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
                
            } catch (SQLException ex) {
                
                Logger.getLogger(ResultFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        }else if(result_index == 1){
            //Student-Wise
            resultLayout.show(resultCards, "studentCard");
            try {
                ps = connect.prepareStatement("SELECT t17_batch.T17_id,t17_batch.batch_id,t17_batch.batch_name "
                        + "FROM `t14_semester` "
                        + "JOIN t17_batch ON t17_batch.T14_id_fk = t14_semester.T14_id"
                        + " WHERE t14_semester.T06_id_fk = ? ORDER BY t17_batch.batch_id ASC",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ps.setString(1,idT06);
                ResultSet rs = ps.executeQuery();
                rs.last();
                int count_batch = 0;
                count_batch = rs.getRow();
                rs.beforeFirst();
                if(count_batch>0){
                    T17_id = new String [count_batch];
                    String [] temp_batch = new String [count_batch];
                    for(int i=0;rs.next()&& i<count_batch;i++){
                        T17_id [i] = rs.getString(1);
                        temp_batch[i] = rs.getString(2)+"--"+rs.getString(3);
                    }
                    batchComboBox.setModel(new DefaultComboBoxModel(temp_batch));
                    batchComboBox.setEnabled(true);
                    batchComboBox.setSelectedIndex(0);
                    
                }else{
                    String [] temp_none = {"None!"};
                    batchComboBox.setModel(new DefaultComboBoxModel(temp_none));
                    studentComboBox.setModel(new DefaultComboBoxModel(temp_none));
                    batchComboBox.setEnabled(false);
                    studentComboBox.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "No Batch Exists for the Syllabus", "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
                
            } catch (SQLException ex) {
                
                Logger.getLogger(ResultFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        }else if(result_index == 2){
            //Student-Wise
            resultLayout.show(resultCards, "batchCard");
            try {
                ps = connect.prepareStatement("SELECT t17_batch.T17_id,t17_batch.batch_id,t17_batch.batch_name "
                        + "FROM `t14_semester` "
                        + "JOIN t17_batch ON t17_batch.T14_id_fk = t14_semester.T14_id"
                        + " WHERE t14_semester.T06_id_fk = ? ORDER BY t17_batch.batch_id ASC",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ps.setString(1,idT06);
                ResultSet rs = ps.executeQuery();
                rs.last();
                int count_batch = 0;
                count_batch = rs.getRow();
                rs.beforeFirst();
                if(count_batch>0){
                    T17_id = new String [count_batch];
                    String [] temp_batch = new String [count_batch];
                    for(int i=0;rs.next()&& i<count_batch;i++){
                        T17_id [i] = rs.getString(1);
                        temp_batch[i] = rs.getString(2)+"--"+rs.getString(3);
                    }
                    batchComboBox2.setModel(new DefaultComboBoxModel(temp_batch));
                    batchComboBox2.setEnabled(true);
                    batchComboBox2.setSelectedIndex(0);
                    
                }else{
                    String [] temp_none = {"None!"};
                    batchComboBox2.setModel(new DefaultComboBoxModel(temp_none));
                    batchComboBox2.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "No Batch Exists for the Syllabus", "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
                
            } catch (SQLException ex) {
                
                Logger.getLogger(ResultFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        }
        
        
    }//GEN-LAST:event_resultComboBoxActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        mfrm.setEnabled(true);
        mfrm.setAlwaysOnTop(true);
        mfrm.setAlwaysOnTop(false);
    }//GEN-LAST:event_formWindowClosed

    private void semesterComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semesterComboBoxActionPerformed
        // TODO add your handling code here:
        ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
        T15_id = new String [1];
        T15_id [0] = "None!";
        if(T14_id[0].equals("None!") ){
            String[] temp_none = {"None!"};
            semesterComboBox.setModel(new DefaultComboBoxModel(temp_none));
            return;
        }
        int t14_index = semesterComboBox.getSelectedIndex();
        try {
                ps = connect.prepareStatement("SELECT t07_course.course_id,t15_offered_course.T15_id FROM `t15_offered_course` "
                        + "JOIN t07_course ON t15_offered_course.T07_id_fk = t07_course.T07_id "
                        + "WHERE t15_offered_course.T14_id_fk = ? "
                        + "ORDER BY t07_course.course_id ASC",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ps.setString(1,T14_id[t14_index]);
                ResultSet rs = ps.executeQuery();
                rs.last();
                int count_course = 0;
                count_course = rs.getRow();
                rs.beforeFirst();
                if(count_course>0){
                    T15_id = new String [count_course];
                    String [] temp_course = new String [count_course];
                    for(int i=0;rs.next()&& i<count_course;i++){
                        T15_id [i] = rs.getString(2);
                        temp_course[i] = rs.getString(1);
                    }
                    courseComboBox.setModel(new DefaultComboBoxModel(temp_course));
                    courseComboBox.setEnabled(true);
                    courseComboBox.setSelectedIndex(0);
                    
                }else{
                    String [] temp_none = {"None!"};
                    
                    courseComboBox.setModel(new DefaultComboBoxModel(temp_none));
                    courseComboBox.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "No Offered Course Exists for the Semester", "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
                
            } catch (SQLException ex) {
                

                Logger.getLogger(ResultFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        
        
    }//GEN-LAST:event_semesterComboBoxActionPerformed

    private void generateButtonCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonCourseActionPerformed
        // TODO add your handling code here:
        ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));  
        if(courseComboBox.getSelectedItem().equals("None!")){
            JOptionPane.showMessageDialog(this, "No Course Selected!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            return;
        }
        String [][] T20_info = new String [1][1] ;
        T20_info [0][0] = "None!";
        
        String [][] T10_info = new String [1][1] ;
        T10_info [0][0] = "None!";
        
        String [][] T09_info = new String [1][1] ;
        T09_info [0][0] = "None!";
        
        String [][] T18_info = new String [1][1] ;
        T18_info [0][0] = "None!";
        
        String idT07 = "None!";
        double courseCredit =0.0;
        int t15Index = courseComboBox.getSelectedIndex();
        int t14Index = semesterComboBox.getSelectedIndex();
        
        try{
            connect.setAutoCommit(false);
            //Student in Offered Course
            ps = connect.prepareStatement("SELECT t21_student_course_registration.T20_id_fk, t20_student.student_id"
                    + " FROM `t21_student_course_registration` "
                    + "JOIN t20_student ON t21_student_course_registration.T20_id_fk=t20_student.T20_id"
                    + " WHERE t21_student_course_registration.T15_id_fk = ? "
                    + "ORDER BY t20_student.student_id ASC",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, T15_id[t15Index]);
            ResultSet rs = ps.executeQuery();
            rs.last();
            int student_count =0;
            student_count = rs.getRow();
            rs.beforeFirst();
            if(student_count>0){
                T20_info = new String [student_count][2];
                for(int i=0;rs.next() && i<student_count;i++){
                    T20_info[i][0] = rs.getString(1);
                    T20_info[i][1] = rs.getString(2);
                }
            }else{
                JOptionPane.showMessageDialog(this, "No Student Registered in this Course!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            
            //idT07 for Offered Course
            ps = connect.prepareStatement("SELECT T07_id_fk FROM `t15_offered_course` WHERE T15_id = ?");
            ps.setString(1, T15_id[t15Index]);
            rs = ps.executeQuery();
            if(rs.next()){
                idT07 = rs.getString(1);
            }else{
                JOptionPane.showMessageDialog(this, "Problem in Fethcing Course ID info!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            
            //Course CLO info
            //Custom?
            ps = connect.prepareStatement("SELECT T10_id,clo_number FROM `t10_course_clo`"
                    + " WHERE T07_id_fk = ? AND T15_id_fk = ? ORDER BY clo_number ASC",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT07);
            ps.setString(2, T15_id[t15Index]);
            rs = ps.executeQuery();
            rs.last();
            int clo_count =0;
            clo_count = rs.getRow();
            rs.beforeFirst();
            
            if(clo_count==0){
                //Default CLO
                ps = connect.prepareStatement("SELECT T10_id,clo_number FROM `t10_course_clo`"
                    + " WHERE T07_id_fk = ? AND T15_id_fk = 0 ORDER BY clo_number ASC",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ps.setString(1, idT07);
                rs = ps.executeQuery();
                rs.last();
                clo_count = rs.getRow();
                rs.beforeFirst();
            }
            
            if(clo_count>0){
                T10_info = new String[clo_count][2];
                for(int i=0;rs.next() && i<clo_count;i++){
                    T10_info[i][0] = rs.getString(1);
                    T10_info[i][1] = "CLO-"+rs.getString(2);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Problem Course CLO info!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            
            //Course PLO
            ps = connect.prepareStatement("SELECT DISTINCT "
                    + "t09_syllabus_plo.T09_id,t09_syllabus_plo.plo_id "
                    + "FROM (SELECT t13_clo_plo_connect.T09_id_fk FROM t13_clo_plo_connect "
                    + "JOIN t10_course_clo"
                    + " ON t13_clo_plo_connect.T10_id_fk = t10_course_clo.T10_id"
                    + " WHERE t10_course_clo.T07_id_fk = ?"
                    + " AND t10_course_clo.T15_id_fk = 0)"
                    + " AS plo_connect JOIN t09_syllabus_plo ON "
                    + "plo_connect.T09_id_fk = t09_syllabus_plo.T09_id "
                    + "ORDER BY t09_syllabus_plo.plo_id ASC"
                    ,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT07);
            rs = ps.executeQuery();
            rs.last();
            int plo_count =0;
            plo_count = rs.getRow();
            rs.beforeFirst();
            if(plo_count>0){
                T09_info = new String [plo_count][2];
                for(int i=0;rs.next()&& i<plo_count; i++){
                    T09_info[i][0] = rs.getString(1);
                    T09_info[i][1] = "PLO-"+rs.getString(2);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Problem Course PLO info!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            
            //Exams for the course with exam status
            ps = connect.prepareStatement("SELECT "
                    + "t18_exam.T18_id,t11_evaluation.evaluation_name,"
                    + "t11_evaluation.marks,t18_exam.exam_status"
                    + " FROM `t18_exam` JOIN t11_evaluation ON "
                    + "t18_exam.T11_id_fk = t11_evaluation.T11_id "
                    + "WHERE t18_exam.T15_id_fk = ? ORDER BY t11_evaluation.marks ASC",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, T15_id[t15Index]);
            rs = ps.executeQuery();
            rs.last();
            int exam_count =0;
            exam_count = rs.getRow();
            rs.beforeFirst();
            if(exam_count>0){
                T18_info = new String[exam_count][3];
                for(int i=0;rs.next()&& i <exam_count;i++){
                    T18_info[i][0] = rs.getString(1);
                    T18_info[i][1] = rs.getString(2)+" ("+rs.getString(3)+")";
                    T18_info[i][2] = rs.getString(4); //exam_status
                }
            }else{
                JOptionPane.showMessageDialog(this, "No Exam info!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            
            
            //Arrays to keep data
            Double [][][] ploData = new Double [student_count][plo_count][2];
            for(int i=0;i<ploData.length;i++){
                for(int j=0;j<ploData[i].length;j++){
                    for(int k=0;k<ploData[i][j].length;k++){
                        ploData[i][j][k] = 0.0;
                    }
                }
            }
            Double [][][] cloData = new Double [student_count][clo_count][2];
            for(int i=0;i<cloData.length;i++){
                for(int j=0;j<cloData[i].length;j++){
                    for(int k=0;k<cloData[i][j].length;k++){
                        cloData[i][j][k] = 0.0;
                    }
                }
            }
            Double [][][] examData = new Double[student_count][exam_count][2]; //examData with absent flag
            for(int i=0;i<examData.length;i++){
                for(int j=0;j<examData[i].length;j++){
                    for(int k=0;k<examData[i][j].length;k++){
                        
                        examData[i][j][k] = 0.0;
                    }
                }
            }
            String [][] courseInfo = new String [12][2];
            for(int i=0;i<courseInfo.length;i++){
                for(int j=0;j<courseInfo[i].length;j++){
                   courseInfo[i][j] = "-";
                }
            }
            Double [][] finalExamData = new Double [student_count][2];
            for(int i=0;i<finalExamData.length;i++){
                for(int j=0;j<finalExamData[i].length;j++){
                   finalExamData[i][j] = 0.0;
                }
            }
            double finalTotalMarks = 0;
            boolean allFinalized = true;
            
            
            
            
            
            //Calculating all result data
            
            for(int i=0;i<exam_count;i++){
                if(T18_info[i][2].equals("0")){
                    continue;
                }
                for(int j=0;j<student_count;j++){
                    
                    ps = connect.prepareStatement("SELECT "
                            + "t19_question.question_mark,t19_question.T10_id_fk,"
                            + "t19_question.T09_id_fk,t22_marks_obtained.marks"
                            + " FROM `t22_marks_obtained` JOIN t19_question ON "
                            + "t19_question.T19_id = t22_marks_obtained.T19_id_fk "
                            + "WHERE t19_question.T18_id_fk = ? AND "
                            + "t22_marks_obtained.T20_id_fk = ?");
                    ps.setString(1, T18_info[i][0]);
                    ps.setString(2, T20_info[j][0]);
                    rs = ps.executeQuery();
                    while(rs.next()){
                        //clo data entry
                        if(rs.getDouble(4)<0){
                            examData[j][i][1] = 1.0;
                            break;
                        }
                        for(int k=0;k<T10_info.length;k++){
                            if(T10_info[k][0].equals(rs.getString(2))){
                                cloData[j][k][0] += rs.getDouble(4);
                                cloData[j][k][1] += rs.getDouble(1);
                            }
                        }
                        //plo data entry
                        for(int k=0;k<T09_info.length;k++){
                            if(T09_info[k][0].equals(rs.getString(3))){
                                ploData[j][k][0] += rs.getDouble(4);
                                ploData[j][k][1] += rs.getDouble(1);
                            }
                        }
                        //exam data entry
                        examData[j][i][0] += rs.getDouble(4);
                    }
                }
            }
            
            //Calculating Final Number and GPA
            ps = connect.prepareStatement("SELECT t08_evaluation_set.total_marks FROM t08_evaluation_set"
                    + " JOIN (SELECT * FROM `t18_exam` "
                    + "JOIN t11_evaluation ON t18_exam.T11_id_fk = t11_evaluation.T11_id"
                    + " WHERE t18_exam.T18_id = ?) as xyz ON t08_evaluation_set.T08_id = xyz.T08_id_fk");
            ps.setString(1, T18_info[0][0]);
            rs = ps.executeQuery();
            if(rs.next()){
                finalTotalMarks = rs.getDouble(1);
            }else{
                JOptionPane.showMessageDialog(this, "Problem getting Total Marks!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            
            for(int i=0;i<T18_info.length;i++){
                if(T18_info[i][2].equals("0")){
                    allFinalized = false;
                    break;
                }
            }
            //totalmarks
            for(int i=0;i<student_count;i++){
                for(int j=0;j<exam_count;j++){
                    if(examData[i][j][1]==0){
                        finalExamData[i][0] += examData[i][j][0];
                    }
                }
            }
            
            //GPA
            for(int i=0;i<student_count;i++){
                boolean absent_flag = false;
                for(int j=0;j<exam_count;j++){
                    if(examData[i][j][1]==1){
                        absent_flag=true;
                    }
                }
                if(absent_flag){
                    finalExamData[i][1] = -1.0;
                }else{
                    double mark = (finalExamData[i][0]/finalTotalMarks)*100;
                    if(mark>=80){
                        finalExamData[i][1] = 4.00;
                    }else if(mark>=75){
                        finalExamData[i][1] = 3.75;
                    }else if(mark>=70){
                        finalExamData[i][1] = 3.50;
                    }else if(mark>=65){
                        finalExamData[i][1] = 3.25;
                    }else if(mark>=60){
                        finalExamData[i][1] = 3.00;
                    }else if(mark>=55){
                        finalExamData[i][1] = 2.75;
                    }else if(mark>=50){
                        finalExamData[i][1] = 2.50;
                    }else if(mark>=45){
                        finalExamData[i][1] = 2.25;
                    }else if(mark>=40){
                        finalExamData[i][1] = 2.00;
                    }else if(mark<40){
                        finalExamData[i][1] = 0.00;
                    }
                }
            }
            
            
            
            
            //Getting Course Info data
            ps = connect.prepareStatement("SELECT "
                    + "university_name,school_name,department_name,program_name,syllabus_name,course_id,course_name,course_credit "
                    + "FROM t01_university JOIN (SELECT * FROM t03_school JOIN "
                    + "(SELECT * FROM t04_department JOIN (SELECT * FROM t05_program JOIN "
                    + "(SELECT * FROM t07_course JOIN "
                    + "t06_syllabus ON t07_course.T06_id_fk = t06_syllabus.T06_id WHERE t07_course.T07_id = ?) AS syllabus "
                    + "ON t05_program.T05_id = syllabus.T05_id_fk) AS program ON t04_department.T04_id = program.T04_id_fk) AS department "
                    + "ON t03_school.T03_id = department.T03_id_fk)"
                    + " AS school ON t01_university.T01_id = school.T01_id_fk ");
            ps.setString(1, idT07);
            rs = ps.executeQuery();
            if(rs.next()){
                courseInfo[0][0]="University";
                courseInfo[0][1]=rs.getString(1);
                courseInfo[1][0]="School";
                courseInfo[1][1]=rs.getString(2);
                courseInfo[2][0]="Department";
                courseInfo[2][1]=rs.getString(3);
                courseInfo[3][0]="Program";
                courseInfo[3][1]=rs.getString(4);
                courseInfo[4][0]="Syllabus";
                courseInfo[4][1]=rs.getString(5);
                courseInfo[5][0]="Course Code";
                courseInfo[5][1]=rs.getString(6);
                courseInfo[6][0]="Course Name";
                courseInfo[6][1]=rs.getString(7);
                courseInfo[7][0]="Credit:";
                courseInfo[7][1]=String.format("%.2f", rs.getDouble(8));
                courseCredit = rs.getDouble(8);
            }else{
                JOptionPane.showMessageDialog(this, "No Course info!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            ps = connect.prepareStatement("SELECT semester_name,start_date,end_date,employee_name,employee_id FROM t14_semester"
                    + " JOIN (SELECT * FROM `t15_offered_course` JOIN t16_employee ON t15_offered_course.T16_id_fk=t16_employee.T16_id"
                    + " WHERE t15_offered_course.T15_id=?) AS employee"
                    + " ON t14_semester.T14_id = employee.T14_id_fk");
            ps.setString(1, T15_id[t15Index]);
            rs = ps.executeQuery();
            if(rs.next()){
                courseInfo[8][0]="Semester";
                courseInfo[8][1]=rs.getString(1);
                courseInfo[9][0]="Start Date";
                if(rs.getString(2)!=null){
                    if(!rs.getString(2).trim().isBlank()){
                        courseInfo[9][1]=rs.getString(2);
                    }
                }
                courseInfo[10][0]="End Date";
                if(rs.getString(3)!=null){
                    if(!rs.getString(3).trim().isBlank()){
                        courseInfo[10][1]=rs.getString(3);
                    }
                }
                courseInfo[11][0]="Teacher";
                courseInfo[11][1]=rs.getString(4)+" ("+rs.getString(5)+")";
                
                
            }else{
                JOptionPane.showMessageDialog(this, "No Course info!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            
            
            //debug
            /*
            for(int i=0;i<ploData.length;i++){
                for(int j=0;j<ploData[i].length;j++){
                    for(int k=0;k<ploData[i][j].length;k++){
                        System.out.print(ploData[i][j][k]+" ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
            
            for (int i = 0; i < cloData.length; i++) {
                for (int j = 0; j < cloData[i].length; j++) {
                    for (int k = 0; k < cloData[i][j].length; k++) {
                        System.out.print(cloData[i][j][k] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
                
            
            
            for(int i=0;i<examData.length;i++){
                for(int j=0;j<examData[i].length;j++){
                    for(int k=0;k<examData[i][j].length;k++){
                        System.out.print(examData[i][j][k] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
            
            for(int i=0;i<courseInfo.length;i++){
                for(int j=0;j<courseInfo[i].length;j++){
                   System.out.print(courseInfo[i][j] + " ");
                }
                System.out.println();
            }
            */
            
            
            //Creating Excel
            //PLO
            //Course Info
            XSSFWorkbook wb = new XSSFWorkbook();
            
            XSSFSheet sht1 = wb.createSheet("PLO");
            
            String[] infoHeaders = {"Course Info.", "Value"};
            XSSFRow infoHeaderRow = sht1.createRow(0);
            for (int i = 0; i < infoHeaders.length; i++) {
                infoHeaderRow.createCell(i).setCellValue(infoHeaders[i]);
            }
            
            for(int i=0;i<courseInfo.length;i++){
                XSSFRow infoRow = sht1.createRow(i+1);
                for(int j=0;j<courseInfo[i].length;j++){
                     infoRow.createCell(j).setCellValue(courseInfo[i][j]);     
                }
            }
            
            //creating table

            AreaReference infoRef = new AreaReference(
                    new CellReference(0, 0), //1st cell
                    new CellReference(courseInfo.length, infoHeaders.length-1), // last cell
                    SpreadsheetVersion.EXCEL2007
            );
            XSSFTable infoTable = sht1.createTable(infoRef);
            infoTable.setName("Course Information");
            
            
            //table style
            CTTable ctInfoTable = infoTable.getCTTable();
            CTTableStyleInfo styleInfo = ctInfoTable.addNewTableStyleInfo();
            styleInfo.setName("TableStyleMedium7");
            styleInfo.setShowColumnStripes(false);
            styleInfo.setShowRowStripes(true);

            infoTable.setStyleName("TableStyleMedium7");
  
            
            
            //PLO Table for sht1
            String[] ploHeaders = new String [plo_count+1];
            ploHeaders[0] = "Student ID";
            for(int i=0;i<plo_count;i++){
                ploHeaders[i+1] = T09_info[i][1];
                //System.out.println(ploHeaders[i+1]);
            }
            
            XSSFRow ploHeaderRow = sht1.createRow(courseInfo.length+6);
            for (int i = 0; i < ploHeaders.length; i++) {
                ploHeaderRow.createCell(i+1).setCellValue(ploHeaders[i]);
            }
            
            //Set PLO Table Tag
            XSSFCellStyle stylePloTag = wb.createCellStyle();

            XSSFColor tagColor = new XSSFColor(Color.decode("#FF8989"), null); // Hex -> Color
            stylePloTag.setFillForegroundColor(tagColor);
            stylePloTag.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            XSSFRow ploTagRow = sht1.createRow(courseInfo.length+5);
            Cell ploTagCell = ploTagRow.createCell(1);
            Font fontBold = wb.createFont();
            fontBold.setBold(true);
            stylePloTag.setFont(fontBold);
            ploTagCell.setCellValue("PLO Information");
            ploTagCell.setCellStyle(stylePloTag);
            
            //Set PLO Table Horizontal Border
            for(int i=0;i<plo_count;i++){
                XSSFCellStyle styleBorder = wb.createCellStyle();
                XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                styleBorder.setFillForegroundColor(borderColor);
                styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell tempCell = ploTagRow.createCell(2+i);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
                tempCell = ploTagRow.createCell(0);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
            }
            
            
            //PLO Table Info
            for(int i=0;i<ploData.length;i++){
                XSSFRow ploRow = sht1.createRow(courseInfo.length+6+1+i);
                ploRow.createCell(1).setCellValue(T20_info[i][1]);
                //setting Border Color
                XSSFCellStyle styleBorder = wb.createCellStyle();
                XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                styleBorder.setFillForegroundColor(borderColor);
                styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell tempCell = ploRow.createCell(0);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
                
                boolean flag = false;
                for (int k = 0; k < examData[i].length; k++) {
                    if (examData[i][k][1] == 1) {
                        flag = true;
                        break;
                    }
                }
                for(int j=0;j<ploData[i].length;j++){
                    if(flag){
                        ploRow.createCell(j+2).setCellValue("Absent");
                    }else{
                        if(ploData[i][j][1]==0){
                            ploRow.createCell(j+2).setCellValue("-");
                            continue;
                        }
                        String temp = String.format("%.2f",(ploData[i][j][0]/ploData[i][j][1])*100)+"%";
                        ploRow.createCell(j+2).setCellValue(temp);
                    }
                         
                }
            }
            
            //creating table

            AreaReference ploRef = new AreaReference(
                    new CellReference(courseInfo.length+6, 1), //1st cell
                    new CellReference(courseInfo.length+6+ploData.length, ploHeaders.length), // last cell
                    SpreadsheetVersion.EXCEL2007
            );
            XSSFTable ploTable = sht1.createTable(ploRef);
            ploTable.setName("PLO Information");
            
            
            //table style
            CTTable ctPloTable = ploTable.getCTTable();
            CTTableStyleInfo stylePlo = ctPloTable.addNewTableStyleInfo();
            stylePlo.setName("TableStyleMedium7");
            stylePlo.setShowColumnStripes(false);
            stylePlo.setShowRowStripes(true);

            ploTable.setStyleName("TableStyleMedium7");
            
            
            
            for (int i = 0; i < ploHeaders.length; i++) {
                sht1.autoSizeColumn(i);                   //resize column width
            }
            
            
            
            //PLO Grading 
            
            //course Info
            XSSFSheet sht1x = wb.createSheet("PLO Grading");
            
            
            XSSFRow infoHeaderRow1x = sht1x.createRow(0);
            for (int i = 0; i < infoHeaders.length; i++) {
                infoHeaderRow1x.createCell(i).setCellValue(infoHeaders[i]);
            }
            
            for(int i=0;i<courseInfo.length;i++){
                XSSFRow infoRow = sht1x.createRow(i+1);
                for(int j=0;j<courseInfo[i].length;j++){
                     infoRow.createCell(j).setCellValue(courseInfo[i][j]);     
                }
            }
            
            //creating table

            AreaReference infoRef1x = new AreaReference(
                    new CellReference(0, 0), //1st cell
                    new CellReference(courseInfo.length, infoHeaders.length-1), // last cell
                    SpreadsheetVersion.EXCEL2007
            );
            XSSFTable infoTable1x = sht1x.createTable(infoRef1x);
            infoTable1x.setName("Course Information");
            
            
            //table style
            CTTable ctInfoTable1x = infoTable1x.getCTTable();
            CTTableStyleInfo styleInfo1x = ctInfoTable1x.addNewTableStyleInfo();
            styleInfo1x.setName("TableStyleMedium7");
            styleInfo1x.setShowColumnStripes(false);
            styleInfo1x.setShowRowStripes(true);

            infoTable1x.setStyleName("TableStyleMedium7");
  
            
            
            //PLO Table for sht1
            String[] ploHeaders1x = new String [plo_count+1];
            ploHeaders1x[0] = "Student ID";
            for(int i=0;i<plo_count;i++){
                ploHeaders1x[i+1] = T09_info[i][1];
                //System.out.println(ploHeaders[i+1]);
            }
            
            XSSFRow ploHeaderRow1x = sht1x.createRow(courseInfo.length+6);
            for (int i = 0; i < ploHeaders1x.length; i++) {
                ploHeaderRow1x.createCell(i+1).setCellValue(ploHeaders1x[i]);
            }
            
            //Set PLO Table Tag
            XSSFCellStyle stylePloTag1x = wb.createCellStyle();

            //XSSFColor tagColor = new XSSFColor(Color.decode("#FF8989"), null); // Hex -> Color
            stylePloTag1x.setFillForegroundColor(tagColor);
            stylePloTag1x.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            XSSFRow ploTagRow1x = sht1x.createRow(courseInfo.length+5);
            Cell ploTagCell1x = ploTagRow1x.createCell(1);
            //Font fontBold = wb.createFont();
            //fontBold.setBold(true);
            stylePloTag1x.setFont(fontBold);
            ploTagCell1x.setCellValue("PLO Grade Information");
            ploTagCell1x.setCellStyle(stylePloTag1x);
            
            //Set PLO Table Horizontal Border
            for(int i=0;i<plo_count;i++){
                XSSFCellStyle styleBorder = wb.createCellStyle();
                XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                styleBorder.setFillForegroundColor(borderColor);
                styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell tempCell = ploTagRow1x.createCell(2+i);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
                tempCell = ploTagRow1x.createCell(0);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
            }
            
            
            //PLO Table Info
            double grade1Count = 0;
            double grade2Count = 0;
            double grade3Count = 0;
            double grade4Count = 0;
            for(int i=0;i<ploData.length;i++){
                XSSFRow ploRow1x = sht1x.createRow(courseInfo.length+6+1+i);
                ploRow1x.createCell(1).setCellValue(T20_info[i][1]);
                
                //setting Border Color
                XSSFCellStyle styleBorder = wb.createCellStyle();
                XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                styleBorder.setFillForegroundColor(borderColor);
                styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell tempCell = ploRow1x.createCell(0);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
                
                boolean flag = false;
                for (int k = 0; k < examData[i].length; k++) {
                    if (examData[i][k][1] == 1) {
                        flag = true;
                        break;
                    }
                }
                for(int j=0;j<ploData[i].length;j++){
                    if(flag){
                        ploRow1x.createCell(j+2).setCellValue("Absent");
                    }else{
                        if(ploData[i][j][1]==0){
                            ploRow1x.createCell(j+2).setCellValue("-");
                            continue;
                        }
                        String temp = "";
                        double score = (ploData[i][j][0]/ploData[i][j][1])*100;
                        if(score>=score1){
                            temp = grade1;
                            grade1Count++;
                        }else if(score>=score2){
                            temp = grade2;
                            grade2Count++;
                        }else if(score>=score3){
                            temp = grade3;
                            grade3Count++;
                        }else{
                            temp = grade4;
                            grade4Count++;
                        }
                        ploRow1x.createCell(j+2).setCellValue(temp);
                    }
                         
                }
            }
            
            //creating table

            AreaReference ploRef1x = new AreaReference(
                    new CellReference(courseInfo.length+6, 1), //1st cell
                    new CellReference(courseInfo.length+6+ploData.length, ploHeaders1x.length), // last cell
                    SpreadsheetVersion.EXCEL2007
            );
            XSSFTable ploTable1x = sht1x.createTable(ploRef1x);
            ploTable1x.setName("PLO Grade Information");
            
            
            //table style
            CTTable ctPloTable1x = ploTable1x.getCTTable();
            CTTableStyleInfo stylePlo1x = ctPloTable1x.addNewTableStyleInfo();
            stylePlo1x.setName("TableStyleMedium7");
            stylePlo1x.setShowColumnStripes(false);
            stylePlo1x.setShowRowStripes(true);

            ploTable1x.setStyleName("TableStyleMedium7");
            
            
            
            
            
            
            
            
            //Creating Table for Grade Summary
            
            String[] gradingHeaders = new String [2];
            gradingHeaders[0] = "Grade";
            gradingHeaders[1] = "Percentage";
            
            
            XSSFRow gradingHeadersRow = sht1x.createRow(courseInfo.length+6+ploData.length+6);
            for (int i = 0; i < gradingHeaders.length; i++) {
                gradingHeadersRow.createCell(2+i).setCellValue(gradingHeaders[i]);
            }
            
            //Set PLO Table Tag
            XSSFCellStyle styleGradeTag = wb.createCellStyle();

            //XSSFColor tagColor = new XSSFColor(Color.decode("#FF8989"), null); // Hex -> Color
            styleGradeTag.setFillForegroundColor(tagColor);
            styleGradeTag.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            XSSFRow gradeTagRow = sht1x.createRow(courseInfo.length+6+ploData.length+6-1);
            Cell gradeTagCell = gradeTagRow.createCell(2);
            //Font fontBold = wb.createFont();
            //fontBold.setBold(true);
            styleGradeTag.setFont(fontBold);
            gradeTagCell.setCellValue("Grade Summary");
            gradeTagCell.setCellStyle(stylePloTag1x);
            
            
            
            
            
            
            for(int i=0;i<1;i++){ // unnecessary for
                XSSFRow gradeRow1 = sht1x.createRow(courseInfo.length+6+ploData.length+6+1);
                double sum = grade1Count+grade2Count+grade3Count+grade4Count;
                gradeRow1.createCell(2).setCellValue(grade1);
                if(sum>0){
                    double percentage = (grade1Count/sum)*100;
                    gradeRow1.createCell(3).setCellValue(String.format("%.2f", percentage)+"%");
                }else{
                    gradeRow1.createCell(3).setCellValue("-");
                }
                
                XSSFRow gradeRow2 = sht1x.createRow(courseInfo.length+6+ploData.length+6+2);
                gradeRow2.createCell(2).setCellValue(grade2);
                if(sum>0){
                    double percentage = (grade2Count/sum)*100;
                    gradeRow2.createCell(3).setCellValue(String.format("%.2f", percentage)+"%");
                }else{
                    gradeRow2.createCell(3).setCellValue("-");
                }
                
                XSSFRow gradeRow3 = sht1x.createRow(courseInfo.length+6+ploData.length+6+3);
                gradeRow3.createCell(2).setCellValue(grade3);
                if(sum>0){
                    double percentage = (grade3Count/sum)*100;
                    gradeRow3.createCell(3).setCellValue(String.format("%.2f", percentage)+"%");
                }else{
                    gradeRow3.createCell(3).setCellValue("-");
                }
                
                XSSFRow gradeRow4 = sht1x.createRow(courseInfo.length+6+ploData.length+6+4);
                gradeRow4.createCell(2).setCellValue(grade4);
                if(sum>0){
                    double percentage = (grade4Count/sum)*100;
                    gradeRow4.createCell(3).setCellValue(String.format("%.2f", percentage)+"%");
                }else{
                    gradeRow4.createCell(3).setCellValue("-");
                }
                
                
                
            }
            
            //creating table

            AreaReference gradeRef = new AreaReference(
                    new CellReference(courseInfo.length+6+ploData.length+6, 2), //1st cell
                    new CellReference(courseInfo.length+6+ploData.length+6+4, 3), // last cell
                    SpreadsheetVersion.EXCEL2007
            );
            XSSFTable gradeTable = sht1x.createTable(gradeRef);
            gradeTable.setName("Grade Summary");
            
            
            //table style
            CTTable ctGradeTable = gradeTable.getCTTable();
            CTTableStyleInfo styleGrade = ctGradeTable.addNewTableStyleInfo();
            styleGrade.setName("TableStyleMedium7");
            styleGrade.setShowColumnStripes(false);
            styleGrade.setShowRowStripes(true);

            gradeTable.setStyleName("TableStyleMedium7");
            
            
            
            
            
            
            
            
            for (int i = 0; i < 1+plo_count+5; i++) {
                sht1x.autoSizeColumn(i);                   //resize column width
            }
            
            
            
            
            
            
            
            
            //CLO SHEET
            //courseInfo
            XSSFSheet sht2 = wb.createSheet("CLO");
            
            XSSFRow infoHeaderRow2 = sht2.createRow(0);
            for (int i = 0; i < infoHeaders.length; i++) {
                infoHeaderRow2.createCell(i).setCellValue(infoHeaders[i]);
            }
            
            for(int i=0;i<courseInfo.length;i++){
                XSSFRow infoRow = sht2.createRow(i+1);
                for(int j=0;j<courseInfo[i].length;j++){
                     infoRow.createCell(j).setCellValue(courseInfo[i][j]);     
                }
            }
            
            //creating table

            AreaReference infoRef2 = new AreaReference(
                    new CellReference(0, 0), //1st cell
                    new CellReference(courseInfo.length, infoHeaders.length-1), // last cell
                    SpreadsheetVersion.EXCEL2007
            );
            XSSFTable infoTable2 = sht2.createTable(infoRef2);
            infoTable2.setName("Course Information");
            
            
            //table style
            CTTable ctInfo2Table = infoTable2.getCTTable();
            CTTableStyleInfo styleInfo2 = ctInfo2Table.addNewTableStyleInfo();
            styleInfo2.setName("TableStyleMedium7");
            styleInfo2.setShowColumnStripes(false);
            styleInfo2.setShowRowStripes(true);

            infoTable2.setStyleName("TableStyleMedium7");
            
            
            
            //CLO INFO
            String[] cloHeaders = new String [clo_count+1];
            cloHeaders[0] = "Student ID";
            for(int i=0;i<clo_count;i++){
                cloHeaders[i+1] = T10_info[i][1];
                //System.out.println(ploHeaders[i+1]);
            }
            
            XSSFRow cloHeaderRow = sht2.createRow(courseInfo.length+6);
            for (int i = 0; i < cloHeaders.length; i++) {
                cloHeaderRow.createCell(i+1).setCellValue(cloHeaders[i]);
            }
            
            //Set CLO Table Tag
            XSSFCellStyle styleCloTag = wb.createCellStyle();

            //XSSFColor tagColor = new XSSFColor(Color.decode("#FF8989"), null); // Hex -> Color
            styleCloTag.setFillForegroundColor(tagColor);
            styleCloTag.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            XSSFRow cloTagRow = sht2.createRow(courseInfo.length+5);
            Cell cloTagCell = cloTagRow.createCell(1);
            //Font fontBold = wb.createFont();
            //fontBold.setBold(true);
            styleCloTag.setFont(fontBold);
            cloTagCell.setCellValue("CLO Information");
            cloTagCell.setCellStyle(styleCloTag);
            
            //Set CLO Table Horizontal Border
            for(int i=0;i<clo_count;i++){
                XSSFCellStyle styleBorder = wb.createCellStyle();
                XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                styleBorder.setFillForegroundColor(borderColor);
                styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell tempCell = cloTagRow.createCell(2+i);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
                tempCell = cloTagRow.createCell(0);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
            }
            
            
            //CLO Table Info
            for(int i=0;i<cloData.length;i++){
                XSSFRow cloRow = sht2.createRow(courseInfo.length+6+1+i);
                cloRow.createCell(1).setCellValue(T20_info[i][1]);
                //setting Border Color
                XSSFCellStyle styleBorder = wb.createCellStyle();
                XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                styleBorder.setFillForegroundColor(borderColor);
                styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell tempCell = cloRow.createCell(0);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
                
                boolean flag = false;
                for (int k = 0; k < examData[i].length; k++) {
                    if (examData[i][k][1] == 1) {
                        flag = true;
                        break;
                    }
                }
                for(int j=0;j<cloData[i].length;j++){
                    if(flag){
                        cloRow.createCell(j+2).setCellValue("Absent");
                    }else{
                        if(cloData[i][j][1]==0){
                            cloRow.createCell(j+2).setCellValue("-");
                            continue;
                        }
                        String temp = String.format("%.2f",(cloData[i][j][0]/cloData[i][j][1])*100)+"%";
                        cloRow.createCell(j+2).setCellValue(temp);
                    }
                         
                }
            }
            
            //creating table

            AreaReference cloRef = new AreaReference(
                    new CellReference(courseInfo.length+6, 1), //1st cell
                    new CellReference(courseInfo.length+6+ploData.length, cloHeaders.length), // last cell
                    SpreadsheetVersion.EXCEL2007
            );
            XSSFTable cloTable = sht2.createTable(cloRef);
            cloTable.setName("CLO Information");
            
            
            //table style
            CTTable ctCloTable = cloTable.getCTTable();
            CTTableStyleInfo styleClo = ctCloTable.addNewTableStyleInfo();
            styleClo.setName("TableStyleMedium7");
            styleClo.setShowColumnStripes(false);
            styleClo.setShowRowStripes(true);

            cloTable.setStyleName("TableStyleMedium7");
            
            
            
            for (int i = 0; i < cloHeaders.length; i++) {
                sht2.autoSizeColumn(i);                   //resize column width
            }
            
            
            
            //Result Exam Data
            //Course Info
            XSSFSheet sht3 = wb.createSheet("GPA & Marks");
            
            XSSFRow infoHeaderRow3 = sht3.createRow(0);
            for (int i = 0; i < infoHeaders.length; i++) {
                infoHeaderRow3.createCell(i).setCellValue(infoHeaders[i]);
            }
            
            for(int i=0;i<courseInfo.length;i++){
                XSSFRow infoRow3 = sht3.createRow(i+1);
                for(int j=0;j<courseInfo[i].length;j++){
                     infoRow3.createCell(j).setCellValue(courseInfo[i][j]);     
                }
            }
            
            //creating table

            AreaReference infoRef3 = new AreaReference(
                    new CellReference(0, 0), //1st cell
                    new CellReference(courseInfo.length, infoHeaders.length-1), // last cell
                    SpreadsheetVersion.EXCEL2007
            );
            XSSFTable infoTable3 = sht3.createTable(infoRef3);
            infoTable3.setName("Course Information");
            
            
            //table style
            CTTable ctInfoTable3 = infoTable3.getCTTable();
            CTTableStyleInfo styleInfo3 = ctInfoTable3.addNewTableStyleInfo();
            styleInfo3.setName("TableStyleMedium7");
            styleInfo3.setShowColumnStripes(false);
            styleInfo3.setShowRowStripes(true);

            infoTable3.setStyleName("TableStyleMedium7");
  
            
            
            //Result&Mark Table for sht3
            String[] examHeaders = new String [exam_count+1+3];
            examHeaders[0] = "Student ID";
            for(int i=0;i<exam_count;i++){
                examHeaders[i+1] = T18_info[i][1];
                //System.out.println(ploHeaders[i+1]);
            }
            examHeaders[exam_count+1] = "Total ("+finalTotalMarks+")";
            examHeaders[exam_count+2] = "Grade";
            examHeaders[exam_count+3] = "GPA";
            
            XSSFRow examHeaderRow = sht3.createRow(courseInfo.length+6);
            for (int i = 0; i < examHeaders.length; i++) {
                examHeaderRow.createCell(i+1).setCellValue(examHeaders[i]);
            }
            
            //Set exam Table Tag
            XSSFCellStyle styleExamTag = wb.createCellStyle();

            //XSSFColor tagColor = new XSSFColor(Color.decode("#FF8989"), null); // Hex -> Color
            styleExamTag.setFillForegroundColor(tagColor);
            styleExamTag.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            XSSFRow examTagRow = sht3.createRow(courseInfo.length+5);
            Cell examTagCell = examTagRow.createCell(1);
            //Font fontBold = wb.createFont();
            //fontBold.setBold(true);
            styleExamTag.setFont(fontBold);
            examTagCell.setCellValue("Exam & GPA Information");
            examTagCell.setCellStyle(styleExamTag);
            
            //Set Exam Table Horizontal Border
            for(int i=0;i<exam_count+3;i++){
                XSSFCellStyle styleBorder = wb.createCellStyle();
                XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                styleBorder.setFillForegroundColor(borderColor);
                styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell tempCell = examTagRow.createCell(2+i);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
                tempCell = examTagRow.createCell(0);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
            }
            
            
            //Exam Table Info
            for(int i=0;i<examData.length;i++){
                XSSFRow examRow = sht3.createRow(courseInfo.length+6+1+i);
                examRow.createCell(1).setCellValue(T20_info[i][1]);
                //setting Border Color
                XSSFCellStyle styleBorder = wb.createCellStyle();
                XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                styleBorder.setFillForegroundColor(borderColor);
                styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell tempCell = examRow.createCell(0);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
                
                CellStyle centerStyle = wb.createCellStyle();
                centerStyle.setAlignment(HorizontalAlignment.CENTER);
                
                boolean absentFlag = false;
                for(int j=0;j<examData[i].length;j++){
                    
                    Cell examCell = examRow.createCell(j+2);
                    examCell.setCellStyle(centerStyle);
                    
                    if (examData[i][j][1] == 1) {
                        examCell.setCellValue("Absent");
                        absentFlag = true;
                    }else{
                        if(T18_info[j][2].equals("0")){
                            examCell.setCellValue("-");
                            
                        }else{
                            examCell.setCellValue(examData[i][j][0]);
                        }
                        
                    }
                    
                    if(j==examData[i].length-1){
                        Cell examTotal = examRow.createCell(j+2+1);
                        examTotal.setCellStyle(centerStyle);
                        examTotal.setCellValue(finalExamData[i][0]);
                        
                        if(courseCredit == 0){
                            Cell examGrade = examRow.createCell(j + 2 + 2);
                            examGrade.setCellStyle(centerStyle);
                            if (absentFlag) {
                                examGrade.setCellValue("I");
                            } else if (!allFinalized) {
                                examGrade.setCellValue("-");
                            } else {
                                if (finalExamData[i][1] == 4) {
                                    examGrade.setCellValue("E");
                                } else if (finalExamData[i][1]>=3) {
                                    examGrade.setCellValue("S");
                                } else if (finalExamData[i][1]>=2) {
                                    examGrade.setCellValue("P");
                                }else if (finalExamData[i][1]<2) {
                                    examGrade.setCellValue("F");
                                }
                            }

                            Cell examGPA = examRow.createCell(j + 2 + 3);
                            examGPA.setCellStyle(centerStyle);
                            if (absentFlag) {
                                examGPA.setCellValue("I");
                            } else if (!allFinalized) {
                                examGPA.setCellValue("-");
                            } else {
                                examGPA.setCellValue("-");
                            }
                        }else {
                            Cell examGrade = examRow.createCell(j + 2 + 2);
                            examGrade.setCellStyle(centerStyle);
                            if (absentFlag) {
                                examGrade.setCellValue("I");
                            } else if (!allFinalized) {
                                examGrade.setCellValue("-");
                            } else {
                                if (finalExamData[i][1] == 4) {
                                    examGrade.setCellValue("A+");
                                } else if (finalExamData[i][1] == 3.75) {
                                    examGrade.setCellValue("A");
                                } else if (finalExamData[i][1] == 3.50) {
                                    examGrade.setCellValue("A-");
                                } else if (finalExamData[i][1] == 3.25) {
                                    examGrade.setCellValue("B+");
                                } else if (finalExamData[i][1] == 3.00) {
                                    examGrade.setCellValue("B");
                                } else if (finalExamData[i][1] == 2.75) {
                                    examGrade.setCellValue("B-");
                                } else if (finalExamData[i][1] == 2.50) {
                                    examGrade.setCellValue("C+");
                                } else if (finalExamData[i][1] == 2.25) {
                                    examGrade.setCellValue("C");
                                } else if (finalExamData[i][1] == 2.00) {
                                    examGrade.setCellValue("D");
                                } else if (finalExamData[i][1] == 0.00) {
                                    examGrade.setCellValue("F");
                                }
                            }

                            Cell examGPA = examRow.createCell(j + 2 + 3);
                            examGPA.setCellStyle(centerStyle);
                            if (absentFlag) {
                                examGPA.setCellValue("I");
                            } else if (!allFinalized) {
                                examGPA.setCellValue("-");
                            } else {
                                examGPA.setCellValue(String.format("%.2f", finalExamData[i][1]));
                            }
                        }

                    }
                         
                }
            }
            
            //creating table

            AreaReference examRef = new AreaReference(
                    new CellReference(courseInfo.length+6, 1), //1st cell
                    new CellReference(courseInfo.length+6+examData.length, examHeaders.length), // last cell
                    SpreadsheetVersion.EXCEL2007
            );
            XSSFTable examTable = sht3.createTable(examRef);
            examTable.setName("Exam & Grade Information");
            
            
            //table style
            CTTable ctExamTable = examTable.getCTTable();
            CTTableStyleInfo styleExam = ctExamTable.addNewTableStyleInfo();
            styleExam.setName("TableStyleMedium7");
            styleExam.setShowColumnStripes(false);
            styleExam.setShowRowStripes(true);

            examTable.setStyleName("TableStyleMedium7");
            
            
            
            for (int i = 0; i < examHeaders.length; i++) {
                sht3.autoSizeColumn(i);                   //resize column width
            }
            
            
            
            
            
 
            //File
            JFileChooser file_chooser = new JFileChooser();
            file_chooser.setDialogTitle("Save Result File");
            file_chooser.setSelectedFile(new File(courseInfo[5][1]+"_Result.xlsx"));
            
            int select = file_chooser.showSaveDialog(null);
            if(select == JFileChooser.APPROVE_OPTION){
                File save_file = file_chooser.getSelectedFile();
                if(!save_file.getAbsolutePath().endsWith(".xlsx")){
                    save_file = new File(save_file.getAbsolutePath()+".xlsx");
                }
                FileOutputStream fos = new FileOutputStream(save_file);
                wb.write(fos);
                wb.close();
                fos.close();
                JOptionPane.showMessageDialog(this, courseInfo[5][1]+" result file saved to: " + save_file.getAbsolutePath());

                
            }
            
            
            
            
            
        }catch (SQLException ex) {
            try {
                connect.rollback();
                connect.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(ConnectCourseOutcomeFrame.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ResultFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (IOException ex) {
            ImageIcon error_page = new ImageIcon(getClass().getResource("/icon/error-page_64.png"));
            String[] options = {"OK"};
            Logger.getLogger(OfferedCourseFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showOptionDialog(this, """
                                               Error in saving File!!!!
                                               Something went wrong in importing xlsx
                                               Try Again!""", "Data Fetch Failed!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, error_page, options, options[0]);
            
            

        }
    }//GEN-LAST:event_generateButtonCourseActionPerformed

    private void courseComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_courseComboBoxActionPerformed

    private void batchComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchComboBoxActionPerformed
        // TODO add your handling code here:
        ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
        T20_id = new String [1];
        T20_id [0] = "None!";
        if(T17_id[0].equals("None!") ){
            String[] temp_none = {"None!"};
            batchComboBox.setModel(new DefaultComboBoxModel(temp_none));
            return;
        }
        int t17_index = batchComboBox.getSelectedIndex();
        try {
                ps = connect.prepareStatement("SELECT T20_id,student_id FROM `t20_student` "
                        + "WHERE T17_id_fk=? ORDER BY student_id ASC",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ps.setString(1,T17_id[t17_index]);
                ResultSet rs = ps.executeQuery();
                rs.last();
                int count_student = 0;
                count_student = rs.getRow();
                rs.beforeFirst();
                if(count_student>0){
                    T20_id = new String [count_student];
                    String [] temp_student = new String [count_student];
                    for(int i=0;rs.next()&& i<count_student;i++){
                        T20_id [i] = rs.getString(1);
                        temp_student[i] = rs.getString(2);
                    }
                    studentComboBox.setModel(new DefaultComboBoxModel(temp_student));
                    studentComboBox.setEnabled(true);
                    studentComboBox.setSelectedIndex(0);
                    
                }else{
                    String [] temp_none = {"None!"};
                    
                    studentComboBox.setModel(new DefaultComboBoxModel(temp_none));
                    studentComboBox.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "No Student Exists for the Batch", "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
                
            } catch (SQLException ex) {
                

                Logger.getLogger(ResultFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        
    }//GEN-LAST:event_batchComboBoxActionPerformed

    private void studentComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentComboBoxActionPerformed

    private void generateButtonStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonStudentActionPerformed
        // TODO add your handling code here:
        ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
        if(studentComboBox.getSelectedItem().equals("None!")){
            JOptionPane.showMessageDialog(this, "No Student Selected!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            return;
        }
        
        String[] [] T09_info = new String [1][1];
        T09_info[0][0] = "None!";
        String[][] T07_info = new String[1][1];
        T07_info [0][0] = "Nonde!";
        int t20Index = studentComboBox.getSelectedIndex();
        try{
            PreparedStatement ps;
            ResultSet rs;
            ps = connect.prepareStatement("SELECT T09_id,plo_id FROM `t09_syllabus_plo` WHERE T06_id_fk = ? ORDER BY plo_id",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT06);
            rs = ps.executeQuery();
            rs.last();
            int plo_count = 0;
            double CGPA = 0.00;
            double completedCredit = 0.00;
            int completedCourse =0;
            plo_count = rs.getRow();
            rs.beforeFirst();
            if(plo_count>0){
                T09_info = new String [plo_count][2];
                for(int i=0;rs.next() && i<plo_count;i++){
                    T09_info[i][0] = rs.getString(1);
                    T09_info[i][1] = "PLO-"+rs.getString(2);
                }
            }else{
                JOptionPane.showMessageDialog(this, "No PLO in Syllabus!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            ps = connect.prepareStatement("SELECT T07_id,course_id,course_credit FROM `t07_course` WHERE T06_id_fk = ? ORDER BY course_id ASC",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, idT06);
            rs = ps.executeQuery();
            int totalCourse_count = 0;
            double totalCreditCount = 0;
            rs.last();
            totalCourse_count = rs.getRow();
            rs.beforeFirst();
            if(totalCourse_count>0){
                T07_info = new String [totalCourse_count][3];
                for(int i=0;rs.next() && i<totalCourse_count;i++){
                    T07_info[i][0]=rs.getString(1);//T07_id
                    T07_info[i][1]=rs.getString(2);//course_id
                    T07_info[i][2]=rs.getString(3);//course_credit
                    totalCreditCount += rs.getDouble(3);
                    
                }
            }else{
                JOptionPane.showMessageDialog(this, "No Course in Syllabus!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            String tempPLOTable[][] = new String [totalCourse_count][plo_count+1];
            for(int i=0;i<tempPLOTable.length;i++){
                for(int j=0;j<tempPLOTable[i].length;j++){
                    tempPLOTable[i][j] = "None!";
                }
            }
            String tempResultTable[][] = new String [totalCourse_count][5];
            for(int i=0;i<tempResultTable.length;i++){
                for(int j=0;j<tempResultTable[i].length;j++){
                    tempResultTable[i][j] = "None!";
                }
            }
            double [][]finalPlo = new double [plo_count][2];
            for(int i=0;i<finalPlo.length;i++){
                for(int j=0;j<finalPlo[i].length;j++){
                    finalPlo[i][j] = 0.0;
                }
            }
            
            //Calculating PLO & Result Data
            //int courseCompleted =0;
            for(int i=0;i<totalCourse_count;i++){
                ps = connect.prepareStatement("SELECT course.T15_id, t14_semester.semester_name FROM "
                        + "(SELECT t15_offered_course.T15_id,t15_offered_course.T14_id_fk FROM `t15_offered_course` "
                        + "JOIN t21_student_course_registration ON t15_offered_course.T15_id = t21_student_course_registration.T15_id_fk "
                        + "WHERE t21_student_course_registration.T20_id_fk =? AND t15_offered_course.T07_id_fk =?) as course"
                        + " JOIN t14_semester ON course.T14_id_fk = t14_semester.T14_id",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ps.setString(1, T20_id[t20Index]);
                ps.setString(2, T07_info[i][0]);
                rs = ps.executeQuery();
                rs.last();
                int sameTaken = 0; // how many same course taken
                sameTaken = rs.getRow();
                rs.beforeFirst();
                Double [] totalPlosCourse = new Double[plo_count];
                for(int j=0;j<plo_count;j++){
                    totalPlosCourse[j] = 0.0;
                }
                if(sameTaken>0){
                    
                    String [][] sameRegister = new String [sameTaken][2];
                    boolean takeTotalPLO = true;
                    for(int j=0;rs.next()&& j<sameTaken;j++){
                        sameRegister[j][0] = rs.getString(1); //same course register
                        sameRegister[j][1] = rs.getString(2); //semester name
                    }
                    
                    for (int j = 0; j < sameTaken; j++) {
                        ps = connect.prepareStatement("SELECT t18_exam.T18_id,t11_evaluation.evaluation_name,t11_evaluation.marks,t18_exam.exam_status"
                                + " FROM `t18_exam` JOIN t11_evaluation ON t18_exam.T11_id_fk = t11_evaluation.T11_id "
                                + "WHERE t18_exam.T15_id_fk = ? ORDER BY t11_evaluation.marks ASC",
                                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        ps.setString(1, sameRegister[j][0]);
                        rs = ps.executeQuery();
                        rs.last();
                        int exam_count = 0;
                        exam_count = rs.getRow();
                        rs.beforeFirst();
                        String [] []T18_info;
                        
                        if (exam_count > 0) {
                            T18_info = new String[exam_count][2];
                            double finalTotalMarks = 0;
                            for (int k = 0; rs.next() && k < exam_count; k++) {
                                T18_info[k][0] = rs.getString(1);
                                T18_info[k][1] = rs.getString(4); //exam_status
                            }
                            ps = connect.prepareStatement("SELECT t08_evaluation_set.total_marks FROM t08_evaluation_set"
                                    + " JOIN (SELECT * FROM `t18_exam` "
                                    + "JOIN t11_evaluation ON t18_exam.T11_id_fk = t11_evaluation.T11_id"
                                    + " WHERE t18_exam.T18_id = ?) as xyz ON t08_evaluation_set.T08_id = xyz.T08_id_fk");
                            ps.setString(1, T18_info[0][0]);
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                finalTotalMarks = rs.getDouble(1);
                            } else {
                                JOptionPane.showMessageDialog(this, "Problem getting Total Marks!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                                return;
                            }
                            //check if any exam is not finalized
                            boolean allFinalized = true;
                            for(int k=0;k<T18_info.length;k++){
                                if(T18_info[k][1].equals("0")){
                                    allFinalized=false;
                                    break;
                                }
                            }
                            if (!allFinalized) {
                                continue;
                            }
                            
                            
                            //variables for collecting temporary PLO and Mark data
                            Double[][] ploData = new Double[plo_count][2];
                            for (int k = 0; k < ploData.length; k++) {
                                for (int ix = 0; ix < ploData[k].length; ix++) {
                                    ploData[k][ix] = 0.0;
                                }
                            }
                            double finalExamMarks = 0;
                            
                            for(int k=0;k<exam_count;k++){
                                boolean absent = false;
                                ps = connect.prepareStatement("SELECT "
                                        + "t19_question.question_mark,t19_question.T09_id_fk,t22_marks_obtained.marks"
                                        + " FROM `t22_marks_obtained` JOIN t19_question ON t19_question.T19_id = t22_marks_obtained.T19_id_fk "
                                        + "WHERE t19_question.T18_id_fk = ? AND t22_marks_obtained.T20_id_fk = ?");
                                ps.setString(1, T18_info[k][0]);
                                ps.setString(2, T20_id[t20Index]);
                                rs = ps.executeQuery();
                                while(rs.next()){
                                    if (rs.getDouble(3) < 0) {
                                        absent =true;
                                        break;
                                    }
                                    for (int ix = 0; ix < T09_info.length; ix++) {
                                        if (T09_info[ix][0].equals(rs.getString(2))) {
                                            ploData[ix][0] += rs.getDouble(3);
                                            ploData[ix][1] += rs.getDouble(1);
                                            break;
                                        }
                                    }
                                    finalExamMarks += rs.getDouble(3);
                                    
                                }
                                if(absent){
                                    if(tempPLOTable[i][0].equals("None!")){
                                        tempPLOTable[i][0] = T07_info[i][1];
                                        for(int ix = 0;ix<plo_count;ix++){
                                            tempPLOTable[i][ix+1] = "Absent";
                                        }
                                        tempResultTable[i][0] = T07_info[i][1];
                                        tempResultTable[i][1] = T07_info[i][2];//credit
                                        tempResultTable[i][2] = sameRegister[j][1];//semester
                                        tempResultTable[i][3] = "I";//Grade
                                        tempResultTable[i][4] = "I";//GPA
                                        
                                    }
                                    break;
                                }else if(k==exam_count-1){
                                    double mark = (finalExamMarks/finalTotalMarks)*100;
                                    double gpa = 0;
                                    if(takeTotalPLO){
                                        takeTotalPLO = false;
                                        for(int ix=0;ix<plo_count;ix++){
                                            finalPlo[ix][1] += ploData[ix][1];
                                            totalPlosCourse [ix] = ploData[ix][1];
                                        }
                                    }
                                    if (mark >= 80) {
                                        gpa = 4.00;
                                    } else if (mark >= 75) {
                                        gpa = 3.75;
                                    } else if (mark >= 70) {
                                        gpa = 3.50;
                                    } else if (mark >= 65) {
                                        gpa = 3.25;
                                    } else if (mark >= 60) {
                                        gpa = 3.00;
                                    } else if (mark >= 55) {
                                        gpa = 2.75;
                                    } else if (mark >= 50) {
                                        gpa = 2.50;
                                    } else if (mark >= 45) {
                                        gpa = 2.25;
                                    } else if (mark >= 40) {
                                        gpa = 2.00;
                                    } else if (mark < 40) {
                                        gpa = 0.00;
                                    }
                                    if(tempResultTable[i][4].equals("I")){
                                        
                                    }else if(tempResultTable[i][4].equals("None!")){
                                        tempPLOTable[i][0] = T07_info[i][1];
                                        for(int ix = 0;ix<plo_count;ix++){
                                            if(ploData[ix][1]==0){
                                                tempPLOTable[i][ix+1] = "-";
                                            }else{
                                                double tempPLOpercent = (ploData[ix][0]/ploData[ix][1])*100;
                                                tempPLOTable[i][ix+1] = String.format("%.2f",tempPLOpercent );
                                            }
                                        }
                                        tempResultTable[i][0] = T07_info[i][1];//course ID
                                        tempResultTable[i][1] = T07_info[i][2];// course Credit
                                        tempResultTable[i][2] = sameRegister[j][1];// semester registered
                                        
                                        double courseCredit = Double.parseDouble(T07_info[i][2]);
                                        if (courseCredit == 0) {
                                            if (gpa == 4) {
                                                tempResultTable[i][3] = "E";
                                            } else if (gpa >= 3) {
                                                tempResultTable[i][3] = "S";
                                            } else if (gpa >= 2) {
                                                tempResultTable[i][3] = "P";
                                            } else if (gpa < 2) {
                                                tempResultTable[i][3] = "F";
                                            }
                                            
                                            tempResultTable[i][4] = String.format("%.2f",gpa);// need to replace it while generating table
                                        } else {
                                            if (gpa == 4) {
                                                tempResultTable[i][3] = "A+";
                                            } else if (gpa == 3.75) {
                                                tempResultTable[i][3] = "A";
                                            } else if (gpa == 3.50) {
                                                tempResultTable[i][3] = "A-";
                                            } else if (gpa == 3.25) {
                                                tempResultTable[i][3] = "B+";
                                            } else if (gpa == 3.00) {
                                                tempResultTable[i][3] = "B";
                                            } else if (gpa == 2.75) {
                                                tempResultTable[i][3] = "B-";
                                            } else if (gpa == 2.50) {
                                                tempResultTable[i][3] = "C+";
                                            } else if (gpa == 2.25) {
                                                tempResultTable[i][3] = "C";
                                            } else if (gpa == 2.00) {
                                                tempResultTable[i][3] = "D";
                                            } else if (gpa == 0.00) {
                                                tempResultTable[i][3] = "F";
                                            }
                                            tempResultTable[i][4] = String.format("%.2f",gpa);
                                        }
                                    }else{
                                        //if gpa is better in this course
                                        double beforeGPA = Double.parseDouble(tempResultTable[i][4]);
                                        if (beforeGPA < gpa) {
                                            tempPLOTable[i][0] = T07_info[i][1];
                                            for (int ix = 0; ix < plo_count; ix++) {
                                                if (ploData[ix][1] == 0) {
                                                    tempPLOTable[i][ix + 1] = "-";
                                                } else {
                                                    double tempPLOpercent = (ploData[ix][0] / ploData[ix][1]) * 100;
                                                    tempPLOTable[i][ix + 1] = String.format("%.2f", tempPLOpercent);
                                                }
                                            }
                                            tempResultTable[i][0] = T07_info[i][1];//course ID
                                            tempResultTable[i][1] = T07_info[i][2];// course Credit
                                            tempResultTable[i][2] = sameRegister[j][1];// semester registered

                                            double courseCredit = Double.parseDouble(T07_info[i][2]);
                                            if (courseCredit == 0) {
                                                if (gpa == 4) {
                                                    tempResultTable[i][3] = "E";
                                                } else if (gpa >= 3) {
                                                    tempResultTable[i][3] = "S";
                                                } else if (gpa >= 2) {
                                                    tempResultTable[i][3] = "P";
                                                } else if (gpa < 2) {
                                                    tempResultTable[i][3] = "F";
                                                }
                                                tempResultTable[i][4] = String.format("%.2f", gpa);// need to replace it while generating table
                                            } else {
                                                if (gpa == 4) {
                                                    tempResultTable[i][3] = "A+";
                                                } else if (gpa == 3.75) {
                                                    tempResultTable[i][3] = "A";
                                                } else if (gpa == 3.50) {
                                                    tempResultTable[i][3] = "A-";
                                                } else if (gpa == 3.25) {
                                                    tempResultTable[i][3] = "B+";
                                                } else if (gpa == 3.00) {
                                                    tempResultTable[i][3] = "B";
                                                } else if (gpa == 2.75) {
                                                    tempResultTable[i][3] = "B-";
                                                } else if (gpa == 2.50) {
                                                    tempResultTable[i][3] = "C+";
                                                } else if (gpa == 2.25) {
                                                    tempResultTable[i][3] = "C";
                                                } else if (gpa == 2.00) {
                                                    tempResultTable[i][3] = "D";
                                                } else if (gpa == 0.00) {
                                                    tempResultTable[i][3] = "F";
                                                }
                                                tempResultTable[i][4] = String.format("%.2f", gpa);
                                            }
                                        }
                                        
                                    }
                                    
                                }
                            }
                            
                            
                        } else {
                            JOptionPane.showMessageDialog(this, "No Exam info!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                            return;
                        }
                    }
                    
                    
                    
                }else{
                    tempPLOTable[i][0] = "None!";
                    tempResultTable[i][0] = "None!";
                }
                if (!tempPLOTable[i][0].equals("None!")) {
                    for (int j = 0; j < plo_count; j++) {
                        if (!tempPLOTable[i][j + 1].equals("-")
                                && !tempPLOTable[i][j + 1].equals("Absent")) {
                            double tempPLOx = (Double.parseDouble(tempPLOTable[i][j + 1]) / 100) * totalPlosCourse[j];
                            //System.out.println(tempPLOx+"Position PLO:"+(j+1));
                            finalPlo[j][0] += tempPLOx;
                        }
                    }
                }
                
            }
            
            //Taking converting tempory data to Actual
            int totalRegCourse = 0;
            for(int i=0;i<tempPLOTable.length;i++){
                if(!tempPLOTable[i][0].equals("None!")){
                    totalRegCourse++;
                    
                }
            }
            String [][] ploTable = new String [totalRegCourse][plo_count+1];
            String [][] resultTable = new String [totalRegCourse][5];
            for(int i=0,j=0;i<tempPLOTable.length && j<totalRegCourse;i++){
                if(!tempPLOTable[i][0].equals("None!")){
                    for(int k=0;k<tempPLOTable[i].length;k++){
                        ploTable [j][k] = tempPLOTable[i][k];
                    }
                    for(int k=0;k<tempResultTable[i].length;k++){
                        resultTable[j][k] = tempResultTable[i][k];
                    }
                    j++;
                }
            }
            
            //overall PLO
            String [] overallPlo = new String [plo_count+1];
            overallPlo [0] = "Overall";
            for(int j=0;j<plo_count;j++){
                if(finalPlo[j][1]==0){
                    overallPlo[j+1] = "-";
                    continue;
                }
                double finalCount = (finalPlo[j][0]/finalPlo[j][1])*100;
                overallPlo[j+1] = String.format("%.2f", finalCount);
            }
            
            //CGPA and Credit Completed
            //System.out.println(totalRegCourse);
            for(int i=0;i<resultTable.length;i++){
                if(resultTable[i][3].equals("I")||resultTable[i][3].equals("F")){
                    
                }else{
                    CGPA += Double.parseDouble(resultTable[i][4]) * Double.parseDouble(resultTable[i][1]);
                    completedCredit += Double.parseDouble(resultTable[i][1]);
                    completedCourse++;
                }
                
                
                if(i==resultTable.length-1){
                    if(completedCredit==0){
                        CGPA = -1;
                    }else{
                        CGPA = CGPA/completedCredit;
                    }
                }
            }
            
            
            
            //Student Info
            String[][] studentInfo = new String [11][2];
            ps = connect.prepareStatement("SELECT "
                    + "university_name,school_name,department_name,program_name,syllabus_name "
                    + "FROM t01_university JOIN "
                    + "(SELECT * FROM t03_school JOIN "
                    + "(SELECT * FROM t04_department JOIN "
                    + "(SELECT * FROM `t06_syllabus` JOIN t05_program ON t06_syllabus.T05_id_fk=t05_program.T05_id WHERE T06_id=?) "
                    + "AS program ON t04_department.T04_id = program.T04_id_fk) AS department ON t03_school.T03_id = department.T03_id_fk) "
                    + "AS school ON t01_university.T01_id = school.T01_id_fk");
            ps.setString(1, idT06);
            rs = ps.executeQuery();
            if(rs.next()){
                studentInfo [0][0] = "University";
                studentInfo[0][1]=rs.getString(1);
                studentInfo[1][0]="School";
                studentInfo[1][1]=rs.getString(2);
                studentInfo[2][0]="Department";
                studentInfo[2][1]=rs.getString(3);
                studentInfo[3][0]="Program";
                studentInfo[3][1]=rs.getString(4);
                studentInfo[4][0]="Syllabus";
                studentInfo[4][1]=rs.getString(5);
                
            }else{
                JOptionPane.showMessageDialog(this, "Problem in Student info!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            ps = connect.prepareStatement("SELECT "
                    + "student_id,student_name,t17_batch.batch_name,t17_batch.batch_id "
                    + "FROM `t20_student` JOIN t17_batch ON t20_student.T17_id_fk=t17_batch.T17_id "
                    + "WHERE t20_student.T20_id = ?");
            ps.setString(1, T20_id[t20Index]);
            rs = ps.executeQuery();
            if(rs.next()){
                studentInfo[5][0]="Student ID";
                studentInfo[5][1]=rs.getString(1);
                studentInfo[6][0]="Student Name";
                studentInfo[6][1]=rs.getString(2);
                studentInfo[7][0]="Batch";
                studentInfo[7][1]=rs.getString(3)+" ("+rs.getString(4)+")";
            }else{
                JOptionPane.showMessageDialog(this, "Problem in Student info!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return; 
            }
            
            if(CGPA==-1){
                studentInfo[8][0]="CGPA";
                studentInfo[8][1]="-";
            }else{
                studentInfo[8][0]="CGPA";
                studentInfo[8][1]=String.format("%.2f", CGPA);
            }
            studentInfo[9][0]="Credit Completed";
            studentInfo[9][1]=String.format("%.2f", completedCredit)+"   -- (Out of: #"+String.format("%.2f", totalCreditCount)+")";
            
            studentInfo[10][0]="Course Completed";
            studentInfo[10][1]=String.valueOf(completedCourse)+"   -- (Out of: #"+totalCourse_count+")";
            
            //Creating Excel
            //For both sheet
            //Student Info
            XSSFWorkbook wb = new XSSFWorkbook();
            
            XSSFSheet sht1 = wb.createSheet("PLO");
            XSSFSheet sht2 = wb.createSheet("Result");
            
            String[] infoHeaders = {"Student Info.", "Value"};
            XSSFRow infoHeaderRow1 = sht1.createRow(0);
            XSSFRow infoHeaderRow2 = sht2.createRow(0);
            for (int i = 0; i < infoHeaders.length; i++) {
                infoHeaderRow1.createCell(i).setCellValue(infoHeaders[i]);
                infoHeaderRow2.createCell(i).setCellValue(infoHeaders[i]);
            }
            
            for(int i=0;i<studentInfo.length;i++){
                XSSFRow infoRow1 = sht1.createRow(i+1);
                XSSFRow infoRow2 = sht2.createRow(i+1);
                for(int j=0;j<studentInfo[i].length;j++){
                     infoRow1.createCell(j).setCellValue(studentInfo[i][j]);
                     infoRow2.createCell(j).setCellValue(studentInfo[i][j]);
                }
            }
            
            //creating table

            AreaReference infoRef = new AreaReference(
                    new CellReference(0, 0), //1st cell
                    new CellReference(studentInfo.length, infoHeaders.length-1), // last cell
                    SpreadsheetVersion.EXCEL2007
            );
            
            XSSFTable infoTable1 = sht1.createTable(infoRef);
            XSSFTable infoTable2 = sht2.createTable(infoRef);
            infoTable1.setName("Student Information");
            infoTable2.setName("Student Information");
            
            
            //table style
            CTTable ctInfoTable1 = infoTable1.getCTTable();
            CTTable ctInfoTable2 = infoTable2.getCTTable();
            CTTableStyleInfo styleInfo1 = ctInfoTable1.addNewTableStyleInfo();
            CTTableStyleInfo styleInfo2 = ctInfoTable2.addNewTableStyleInfo();
            styleInfo1.setName("TableStyleMedium7");
            styleInfo2.setName("TableStyleMedium7");
            styleInfo1.setShowColumnStripes(false);
            styleInfo2.setShowColumnStripes(false);
            styleInfo1.setShowRowStripes(true);
            styleInfo2.setShowRowStripes(true);

            infoTable1.setStyleName("TableStyleMedium7");
            infoTable2.setStyleName("TableStyleMedium7");
  
            
            //PLO Table for Sheet1
            String[] ploHeaders = new String [plo_count+1];
            ploHeaders[0] = "Course ID";
            for(int i=0;i<plo_count;i++){
                ploHeaders[i+1] = T09_info[i][1];
                //System.out.println(ploHeaders[i+1]);
            }
            XSSFRow ploHeaderRow = sht1.createRow(studentInfo.length+6);
            for (int i = 0; i < ploHeaders.length; i++) {
                ploHeaderRow.createCell(i+1).setCellValue(ploHeaders[i]);
            }
            
            //Set PLO Table Tag
            XSSFCellStyle stylePloTag = wb.createCellStyle();

            XSSFColor tagColor = new XSSFColor(Color.decode("#FF8989"), null); // Hex -> Color
            stylePloTag.setFillForegroundColor(tagColor);
            stylePloTag.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            XSSFRow ploTagRow = sht1.createRow(studentInfo.length+5);
            Cell ploTagCell = ploTagRow.createCell(1);
            Font fontBold = wb.createFont();
            fontBold.setBold(true);
            stylePloTag.setFont(fontBold);
            ploTagCell.setCellValue("PLO Information");
            ploTagCell.setCellStyle(stylePloTag);
            
            //Set PLO Table Horizontal Border
            for(int i=0;i<plo_count;i++){
                XSSFCellStyle styleBorder = wb.createCellStyle();
                XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                styleBorder.setFillForegroundColor(borderColor);
                styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell tempCell = ploTagRow.createCell(2+i);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
                tempCell = ploTagRow.createCell(0);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
            }
            
            
            //PLO Table Info
            for(int i=0;i<ploTable.length;i++){
                XSSFRow ploRow = sht1.createRow(studentInfo.length+6+1+i);
                ploRow.createCell(1).setCellValue(ploTable[i][0]);
                //setting Border Color
                XSSFCellStyle styleBorder = wb.createCellStyle();
                XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                styleBorder.setFillForegroundColor(borderColor);
                styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell tempCell = ploRow.createCell(0);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
                
                for(int j=1;j<ploTable[i].length;j++){
                    if(ploTable[i][j].equals("-")||ploTable[i][j].equals("Absent")){
                        ploRow.createCell(j+1).setCellValue(ploTable[i][j]);
                    }else{
                        ploRow.createCell(j+1).setCellValue(ploTable[i][j]+"%");
                    }
                }
                if(i==ploTable.length-1){
                    ploRow = sht1.createRow(studentInfo.length+6+1+i+1);
                    ploRow.createCell(1).setCellValue(overallPlo[0]);
                    for(int j=1;j<overallPlo.length;j++){
                        if(overallPlo.equals("-")){
                            ploRow.createCell(j+1).setCellValue(overallPlo[j]);
                        }else{
                            ploRow.createCell(j+1).setCellValue(overallPlo[j]+"%");
                        }
                    }
                }
            }
            
            //creating table

            AreaReference ploRef = new AreaReference(
                    new CellReference(studentInfo.length+6, 1), //1st cell
                    new CellReference(studentInfo.length+6+ploTable.length+1, ploHeaders.length), // last cell
                    SpreadsheetVersion.EXCEL2007
            );
            XSSFTable ploTableShow = sht1.createTable(ploRef);
            ploTableShow.setName("PLO Information");
            
            
            //table style
            CTTable ctPloTable = ploTableShow.getCTTable();
            CTTableStyleInfo stylePlo = ctPloTable.addNewTableStyleInfo();
            stylePlo.setName("TableStyleMedium7");
            stylePlo.setShowColumnStripes(false);
            stylePlo.setShowRowStripes(true);

            ploTableShow.setStyleName("TableStyleMedium7");
            
            
            
            for (int i = 0; i < ploHeaders.length; i++) {
                sht1.autoSizeColumn(i);                   //resize column width
            }
            
            
            
            //Result&Mark Table for sht2
            String[] examHeaders = new String [5];
            examHeaders[0] = "Course ID";
            examHeaders[1] = "Credit";
            examHeaders[2] = "Semester";
            examHeaders[3] = "Grade";
            examHeaders[4] = "GPA";
            
            XSSFRow examHeaderRow = sht2.createRow(studentInfo.length+6);
            for (int i = 0; i < examHeaders.length; i++) {
                examHeaderRow.createCell(i+1).setCellValue(examHeaders[i]);
            }
            
            //Set exam Table Tag
            XSSFCellStyle styleExamTag = wb.createCellStyle();

            //XSSFColor tagColor = new XSSFColor(Color.decode("#FF8989"), null); // Hex -> Color
            styleExamTag.setFillForegroundColor(tagColor);
            styleExamTag.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            XSSFRow examTagRow = sht2.createRow(studentInfo.length+5);
            Cell examTagCell = examTagRow.createCell(1);
            //Font fontBold = wb.createFont();
            //fontBold.setBold(true);
            styleExamTag.setFont(fontBold);
            examTagCell.setCellValue("GPA Information");
            examTagCell.setCellStyle(styleExamTag);
            
            
            //Set Exam Table Horizontal Border
            for(int i=0;i<examHeaders.length-1;i++){
                XSSFCellStyle styleBorder = wb.createCellStyle();
                XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                styleBorder.setFillForegroundColor(borderColor);
                styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell tempCell = examTagRow.createCell(2+i);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
                tempCell = examTagRow.createCell(0);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
            }
            
            //Exam Table Info
            for(int i=0;i<resultTable.length;i++){
                XSSFRow resultRow = sht2.createRow(studentInfo.length+6+1+i);
                resultRow.createCell(1).setCellValue(resultTable[i][0]);
                //setting Border Color
                XSSFCellStyle styleBorder = wb.createCellStyle();
                XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                styleBorder.setFillForegroundColor(borderColor);
                styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell tempCell = resultRow.createCell(0);
                tempCell.setCellValue("");
                tempCell.setCellStyle(styleBorder);
                
                CellStyle centerStyle = wb.createCellStyle();
                centerStyle.setAlignment(HorizontalAlignment.CENTER);
                
                resultRow.createCell(2).setCellValue(resultTable[i][1]);
                resultRow.createCell(3).setCellValue(resultTable[i][2]);
                resultRow.createCell(4).setCellValue(resultTable[i][3]);
                
                if(Double.parseDouble(resultTable[i][1])==0){
                    resultRow.createCell(5).setCellValue("-");
                }else{
                    resultRow.createCell(5).setCellValue(resultTable[i][4]);
                }
           
                
            }
            
            
            //creating Table
            AreaReference resultRef = new AreaReference(
                    new CellReference(studentInfo.length+6, 1), //1st cell
                    new CellReference(studentInfo.length+6+resultTable.length, examHeaders.length), // last cell
                    SpreadsheetVersion.EXCEL2007
            );
            XSSFTable resultTableCreate = sht2.createTable(resultRef);
            resultTableCreate.setName("Exam & Grade Information");
            
            
            //table style
            CTTable ctResultTable = resultTableCreate.getCTTable();
            CTTableStyleInfo styleResult = ctResultTable.addNewTableStyleInfo();
            styleResult.setName("TableStyleMedium7");
            styleResult.setShowColumnStripes(false);
            styleResult.setShowRowStripes(true);

            resultTableCreate.setStyleName("TableStyleMedium7");
            
            
            
            for (int i = 0; i < examHeaders.length; i++) {
                sht2.autoSizeColumn(i);                   //resize column width
            }
            
            
            
            
            
            //File
            JFileChooser file_chooser = new JFileChooser();
            file_chooser.setDialogTitle("Save Result File");
            file_chooser.setSelectedFile(new File(studentInfo[5][1]+"_Result.xlsx"));
            
            int select = file_chooser.showSaveDialog(null);
            if(select == JFileChooser.APPROVE_OPTION){
                File save_file = file_chooser.getSelectedFile();
                if(!save_file.getAbsolutePath().endsWith(".xlsx")){
                    save_file = new File(save_file.getAbsolutePath()+".xlsx");
                }
                FileOutputStream fos = new FileOutputStream(save_file);
                wb.write(fos);
                wb.close();
                fos.close();
                JOptionPane.showMessageDialog(this, studentInfo[5][1]+" result file saved to: " + save_file.getAbsolutePath());

                
            }
            
        }catch (SQLException ex) {
                
                
                Logger.getLogger(ResultFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NumberFormatException e) {
            Logger.getLogger(ResultFrame.class.getName()).log(Level.SEVERE, null, e);
           
            JOptionPane.showMessageDialog(this, "Number Format Error!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            
        }catch (IOException ex) {
            
            ImageIcon error_page = new ImageIcon(getClass().getResource("/icon/error-page_64.png"));
            String[] options = {"OK"};
            Logger.getLogger(OfferedCourseFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showOptionDialog(this, """
                                               Error in saving File!!!!
                                               Something went wrong in importing xlsx
                                               Try Again!""", "Data Fetch Failed!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, error_page, options, options[0]);
            
            

        }
        
        
        
    }//GEN-LAST:event_generateButtonStudentActionPerformed

    private void batchComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_batchComboBox2ActionPerformed

    private void generateButtonBatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonBatchActionPerformed
        // TODO add your handling code here:
        ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));
        String[][] T20_info = new String [1][1];
        T20_info[0][0] = "None!";
        int t17Index = batchComboBox2.getSelectedIndex();
        ResultSet rs;
        
        try{
            ps = connect.prepareStatement("SELECT T20_id,student_id FROM `t20_student` WHERE T17_id_fk = ? AND student_status>0",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, T17_id[t17Index]);
            rs = ps.executeQuery();
            rs.last();
            int student_count = 0;
            student_count = rs.getRow();
            rs.beforeFirst();
            if(student_count>0){
                T20_info = new String [student_count][2];
                for(int i=0;rs.next() && i<student_count;i++){
                    T20_info[i][0] = rs.getString(1);
                    T20_info[i][1] = rs.getString(2);
                }
                
                
                
                //Getting Basic Info about PLO
                String[] [] T09_info = new String [1][1];
                T09_info[0][0] = "None!";
                String[][] T07_info = new String[1][1];
                T07_info [0][0] = "None!";
                ps = connect.prepareStatement("SELECT T09_id,plo_id FROM `t09_syllabus_plo` WHERE T06_id_fk = ? ORDER BY plo_id",
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ps.setString(1, idT06);
                rs = ps.executeQuery();
                rs.last();
                int plo_count = 0;
                plo_count = rs.getRow();
                rs.beforeFirst();
                if (plo_count > 0) {
                    T09_info = new String[plo_count][2];
                    for (int i = 0; rs.next() && i < plo_count; i++) {
                        T09_info[i][0] = rs.getString(1);
                        T09_info[i][1] = "PLO-" + rs.getString(2);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No PLO in Syllabus!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
                
                ps = connect.prepareStatement("SELECT T07_id,course_id,course_credit FROM `t07_course` WHERE T06_id_fk = ? ORDER BY course_id ASC",
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ps.setString(1, idT06);
                rs = ps.executeQuery();
                int totalCourse_count = 0;
                double totalCreditCount = 0;
                rs.last();
                totalCourse_count = rs.getRow();
                rs.beforeFirst();
                if (totalCourse_count > 0) {
                    T07_info = new String[totalCourse_count][3];
                    for (int i = 0; rs.next() && i < totalCourse_count; i++) {
                        T07_info[i][0] = rs.getString(1);//T07_id
                        T07_info[i][1] = rs.getString(2);//course_id
                        T07_info[i][2] = rs.getString(3);//course_credit
                        totalCreditCount += rs.getDouble(3);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No Course in Syllabus!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
                

                
                // calculating PLO and Result
                String [][] batchPLOInfo = new String[student_count][plo_count+1];
                for (int i = 0; i < batchPLOInfo.length; i++) {
                    for (int j = 0; j < batchPLOInfo[i].length; j++) {
                        batchPLOInfo[i][j] = "-";
                    }
                }
                String [][]batchResultInfo = new String [student_count][4];
                for (int i = 0; i < batchResultInfo.length; i++) {
                    for (int j = 0; j < batchResultInfo[i].length; j++) {
                        batchResultInfo[i][j] = "-";
                    }
                }
                Double [][] batchOverallPLO = new Double [plo_count][2];
                for (int i = 0; i < batchOverallPLO.length; i++) {
                    for (int j = 0; j < batchOverallPLO[i].length; j++) {
                        batchOverallPLO[i][j] = 0.0;
                    }
                }
                
                for (int ixx = 0; ixx < T20_info.length; ixx++) {
                    
                    double CGPA = 0.0;
                    int completedCourse =0;
                    double completedCredit =0.0;
                    String tempPLOTable[][] = new String[totalCourse_count][plo_count + 1];
                    for (int i = 0; i < tempPLOTable.length; i++) {
                        for (int j = 0; j < tempPLOTable[i].length; j++) {
                            tempPLOTable[i][j] = "None!";
                        }
                    }
                    String tempResultTable[][] = new String[totalCourse_count][5];
                    for (int i = 0; i < tempResultTable.length; i++) {
                        for (int j = 0; j < tempResultTable[i].length; j++) {
                            tempResultTable[i][j] = "None!";
                        }
                    }
                    double[][] finalPlo = new double[plo_count][2];
                    for (int i = 0; i < finalPlo.length; i++) {
                        for (int j = 0; j < finalPlo[i].length; j++) {
                            finalPlo[i][j] = 0.0;
                        }
                    }

                    //Calculating PLO & Result Data for indivudual student
                    for (int i = 0; i < totalCourse_count; i++) {
                        ps = connect.prepareStatement("SELECT course.T15_id, t14_semester.semester_name FROM "
                                + "(SELECT t15_offered_course.T15_id,t15_offered_course.T14_id_fk FROM `t15_offered_course` "
                                + "JOIN t21_student_course_registration ON t15_offered_course.T15_id = t21_student_course_registration.T15_id_fk "
                                + "WHERE t21_student_course_registration.T20_id_fk =? AND t15_offered_course.T07_id_fk =?) as course"
                                + " JOIN t14_semester ON course.T14_id_fk = t14_semester.T14_id",
                                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        ps.setString(1, T20_info[ixx][0]);
                        ps.setString(2, T07_info[i][0]);
                        rs = ps.executeQuery();
                        rs.last();
                        int sameTaken = 0; // how many same course taken
                        sameTaken = rs.getRow();
                        rs.beforeFirst();
                        Double[] totalPlosCourse = new Double[plo_count];
                        for (int j = 0; j < plo_count; j++) {
                            totalPlosCourse[j] = 0.0;
                        }
                        if (sameTaken > 0) {

                            String[][] sameRegister = new String[sameTaken][2];
                            boolean takeTotalPLO = true;
                            for (int j = 0; rs.next() && j < sameTaken; j++) {
                                sameRegister[j][0] = rs.getString(1); //same course register
                                sameRegister[j][1] = rs.getString(2); //semester name
                            }

                            for (int j = 0; j < sameTaken; j++) {
                                ps = connect.prepareStatement("SELECT t18_exam.T18_id,t11_evaluation.evaluation_name,t11_evaluation.marks,t18_exam.exam_status"
                                        + " FROM `t18_exam` JOIN t11_evaluation ON t18_exam.T11_id_fk = t11_evaluation.T11_id "
                                        + "WHERE t18_exam.T15_id_fk = ? ORDER BY t11_evaluation.marks ASC",
                                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                ps.setString(1, sameRegister[j][0]);
                                rs = ps.executeQuery();
                                rs.last();
                                int exam_count = 0;
                                exam_count = rs.getRow();
                                rs.beforeFirst();
                                String[][] T18_info;

                                if (exam_count > 0) {
                                    T18_info = new String[exam_count][2];
                                    double finalTotalMarks = 0;
                                    for (int k = 0; rs.next() && k < exam_count; k++) {
                                        T18_info[k][0] = rs.getString(1);
                                        T18_info[k][1] = rs.getString(4); //exam_status
                                    }
                                    ps = connect.prepareStatement("SELECT t08_evaluation_set.total_marks FROM t08_evaluation_set"
                                            + " JOIN (SELECT * FROM `t18_exam` "
                                            + "JOIN t11_evaluation ON t18_exam.T11_id_fk = t11_evaluation.T11_id"
                                            + " WHERE t18_exam.T18_id = ?) as xyz ON t08_evaluation_set.T08_id = xyz.T08_id_fk");
                                    ps.setString(1, T18_info[0][0]);
                                    rs = ps.executeQuery();
                                    if (rs.next()) {
                                        finalTotalMarks = rs.getDouble(1);
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Problem getting Total Marks!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                                        return;
                                    }
                                    //check if any exam is not finalized
                                    boolean allFinalized = true;
                                    for (int k = 0; k < T18_info.length; k++) {
                                        if (T18_info[k][1].equals("0")) {
                                            allFinalized = false;
                                            break;
                                        }
                                    }
                                    if (!allFinalized) {
                                        continue;
                                    }

                                    //variables for collecting temporary PLO and Mark data
                                    Double[][] ploData = new Double[plo_count][2];
                                    for (int k = 0; k < ploData.length; k++) {
                                        for (int ix = 0; ix < ploData[k].length; ix++) {
                                            ploData[k][ix] = 0.0;
                                        }
                                    }
                                    double finalExamMarks = 0;

                                    for (int k = 0; k < exam_count; k++) {
                                        boolean absent = false;
                                        ps = connect.prepareStatement("SELECT "
                                                + "t19_question.question_mark,t19_question.T09_id_fk,t22_marks_obtained.marks"
                                                + " FROM `t22_marks_obtained` JOIN t19_question ON t19_question.T19_id = t22_marks_obtained.T19_id_fk "
                                                + "WHERE t19_question.T18_id_fk = ? AND t22_marks_obtained.T20_id_fk = ?");
                                        ps.setString(1, T18_info[k][0]);
                                        ps.setString(2, T20_info[ixx][0]);
                                        rs = ps.executeQuery();
                                        while (rs.next()) {
                                            if (rs.getDouble(3) < 0) {
                                                absent = true;
                                                break;
                                            }
                                            for (int ix = 0; ix < T09_info.length; ix++) {
                                                if (T09_info[ix][0].equals(rs.getString(2))) {
                                                    ploData[ix][0] += rs.getDouble(3);
                                                    ploData[ix][1] += rs.getDouble(1);
                                                    break;
                                                }
                                            }
                                            finalExamMarks += rs.getDouble(3);

                                        }
                                        if (absent) {
                                            if (tempPLOTable[i][0].equals("None!")) {
                                                tempPLOTable[i][0] = T07_info[i][1];
                                                for (int ix = 0; ix < plo_count; ix++) {
                                                    tempPLOTable[i][ix + 1] = "Absent";
                                                }
                                                tempResultTable[i][0] = T07_info[i][1];
                                                tempResultTable[i][1] = T07_info[i][2];//credit
                                                tempResultTable[i][2] = sameRegister[j][1];//semester
                                                tempResultTable[i][3] = "I";//Grade
                                                tempResultTable[i][4] = "I";//GPA

                                            }
                                            break;
                                        } else if (k == exam_count - 1) {
                                            double mark = (finalExamMarks / finalTotalMarks) * 100;
                                            double gpa = 0;
                                            if (takeTotalPLO) {
                                                takeTotalPLO = false;
                                                for (int ix = 0; ix < plo_count; ix++) {
                                                    finalPlo[ix][1] += ploData[ix][1];
                                                    totalPlosCourse[ix] = ploData[ix][1];
                                                }
                                            }
                                            if (mark >= 80) {
                                                gpa = 4.00;
                                            } else if (mark >= 75) {
                                                gpa = 3.75;
                                            } else if (mark >= 70) {
                                                gpa = 3.50;
                                            } else if (mark >= 65) {
                                                gpa = 3.25;
                                            } else if (mark >= 60) {
                                                gpa = 3.00;
                                            } else if (mark >= 55) {
                                                gpa = 2.75;
                                            } else if (mark >= 50) {
                                                gpa = 2.50;
                                            } else if (mark >= 45) {
                                                gpa = 2.25;
                                            } else if (mark >= 40) {
                                                gpa = 2.00;
                                            } else if (mark < 40) {
                                                gpa = 0.00;
                                            }
                                            if (tempResultTable[i][4].equals("I")) {

                                            } else if (tempResultTable[i][4].equals("None!")) {
                                                tempPLOTable[i][0] = T07_info[i][1];
                                                for (int ix = 0; ix < plo_count; ix++) {
                                                    if (ploData[ix][1] == 0) {
                                                        tempPLOTable[i][ix + 1] = "-";
                                                    } else {
                                                        double tempPLOpercent = (ploData[ix][0] / ploData[ix][1]) * 100;
                                                        tempPLOTable[i][ix + 1] = String.format("%.2f", tempPLOpercent);
                                                    }
                                                }
                                                tempResultTable[i][0] = T07_info[i][1];//course ID
                                                tempResultTable[i][1] = T07_info[i][2];// course Credit
                                                tempResultTable[i][2] = sameRegister[j][1];// semester registered

                                                double courseCredit = Double.parseDouble(T07_info[i][2]);
                                                if (courseCredit == 0) {
                                                    if (gpa == 4) {
                                                        tempResultTable[i][3] = "E";
                                                    } else if (gpa >= 3) {
                                                        tempResultTable[i][3] = "S";
                                                    } else if (gpa >= 2) {
                                                        tempResultTable[i][3] = "P";
                                                    } else if (gpa < 2) {
                                                        tempResultTable[i][3] = "F";
                                                    }

                                                    tempResultTable[i][4] = String.format("%.2f", gpa);// need to replace it while generating table
                                                } else {
                                                    if (gpa == 4) {
                                                        tempResultTable[i][3] = "A+";
                                                    } else if (gpa == 3.75) {
                                                        tempResultTable[i][3] = "A";
                                                    } else if (gpa == 3.50) {
                                                        tempResultTable[i][3] = "A-";
                                                    } else if (gpa == 3.25) {
                                                        tempResultTable[i][3] = "B+";
                                                    } else if (gpa == 3.00) {
                                                        tempResultTable[i][3] = "B";
                                                    } else if (gpa == 2.75) {
                                                        tempResultTable[i][3] = "B-";
                                                    } else if (gpa == 2.50) {
                                                        tempResultTable[i][3] = "C+";
                                                    } else if (gpa == 2.25) {
                                                        tempResultTable[i][3] = "C";
                                                    } else if (gpa == 2.00) {
                                                        tempResultTable[i][3] = "D";
                                                    } else if (gpa == 0.00) {
                                                        tempResultTable[i][3] = "F";
                                                    }
                                                    tempResultTable[i][4] = String.format("%.2f", gpa);
                                                }
                                            } else {
                                                //if gpa is better in this course
                                                double beforeGPA = Double.parseDouble(tempResultTable[i][4]);
                                                if (beforeGPA < gpa) {
                                                    tempPLOTable[i][0] = T07_info[i][1];
                                                    for (int ix = 0; ix < plo_count; ix++) {
                                                        if (ploData[ix][1] == 0) {
                                                            tempPLOTable[i][ix + 1] = "-";
                                                        } else {
                                                            double tempPLOpercent = (ploData[ix][0] / ploData[ix][1]) * 100;
                                                            tempPLOTable[i][ix + 1] = String.format("%.2f", tempPLOpercent);
                                                        }
                                                    }
                                                    tempResultTable[i][0] = T07_info[i][1];//course ID
                                                    tempResultTable[i][1] = T07_info[i][2];// course Credit
                                                    tempResultTable[i][2] = sameRegister[j][1];// semester registered

                                                    double courseCredit = Double.parseDouble(T07_info[i][2]);
                                                    if (courseCredit == 0) {
                                                        if (gpa == 4) {
                                                            tempResultTable[i][3] = "E";
                                                        } else if (gpa >= 3) {
                                                            tempResultTable[i][3] = "S";
                                                        } else if (gpa >= 2) {
                                                            tempResultTable[i][3] = "P";
                                                        } else if (gpa < 2) {
                                                            tempResultTable[i][3] = "F";
                                                        }
                                                        tempResultTable[i][4] = String.format("%.2f", gpa);// need to replace it while generating table
                                                    } else {
                                                        if (gpa == 4) {
                                                            tempResultTable[i][3] = "A+";
                                                        } else if (gpa == 3.75) {
                                                            tempResultTable[i][3] = "A";
                                                        } else if (gpa == 3.50) {
                                                            tempResultTable[i][3] = "A-";
                                                        } else if (gpa == 3.25) {
                                                            tempResultTable[i][3] = "B+";
                                                        } else if (gpa == 3.00) {
                                                            tempResultTable[i][3] = "B";
                                                        } else if (gpa == 2.75) {
                                                            tempResultTable[i][3] = "B-";
                                                        } else if (gpa == 2.50) {
                                                            tempResultTable[i][3] = "C+";
                                                        } else if (gpa == 2.25) {
                                                            tempResultTable[i][3] = "C";
                                                        } else if (gpa == 2.00) {
                                                            tempResultTable[i][3] = "D";
                                                        } else if (gpa == 0.00) {
                                                            tempResultTable[i][3] = "F";
                                                        }
                                                        tempResultTable[i][4] = String.format("%.2f", gpa);
                                                    }
                                                }

                                            }

                                        }
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(this, "No Exam info!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                                    return;
                                }
                            }

                        } else {
                            tempPLOTable[i][0] = "None!";
                            tempResultTable[i][0] = "None!";
                        }
                        if (!tempPLOTable[i][0].equals("None!")) {
                            for (int j = 0; j < plo_count; j++) {
                                if (!tempPLOTable[i][j + 1].equals("-")
                                        && !tempPLOTable[i][j + 1].equals("Absent")) {
                                    double tempPLOx = (Double.parseDouble(tempPLOTable[i][j + 1]) / 100) * totalPlosCourse[j];
                                    //System.out.println(tempPLOx+"Position PLO:"+(j+1));
                                    finalPlo[j][0] += tempPLOx;
                                }
                            }
                        }

                    }//student normal PLO finish
                    
                    int totalRegCourse = 0;
                    for (int i = 0; i < tempPLOTable.length; i++) {
                        if (!tempPLOTable[i][0].equals("None!")) {
                            totalRegCourse++;

                        }
                    }
                    String[][] ploTable = new String[totalRegCourse][plo_count + 1];
                    String[][] resultTable = new String[totalRegCourse][5];
                    for (int i = 0, j = 0; i < tempPLOTable.length && j < totalRegCourse; i++) {
                        if (!tempPLOTable[i][0].equals("None!")) {
                            for (int k = 0; k < tempPLOTable[i].length; k++) {
                                ploTable[j][k] = tempPLOTable[i][k];
                            }
                            for (int k = 0; k < tempResultTable[i].length; k++) {
                                resultTable[j][k] = tempResultTable[i][k];
                            }
                            j++;
                        }
                    }
                    //overall PLO
                    batchPLOInfo[ixx][0] = T20_info[ixx][1];
                    for (int j = 0; j < plo_count; j++) {
                        if (finalPlo[j][1] == 0) {
                            batchPLOInfo[ixx][j+1] = "-";
                            continue;
                        }
                        double finalCount = (finalPlo[j][0] / finalPlo[j][1]) * 100;
                        batchOverallPLO [j][0] += finalPlo[j][0];
                        batchOverallPLO [j][1] += finalPlo[j][1];
                        batchPLOInfo[ixx][j+1] = String.format("%.2f", finalCount);
                    }
                    
                    
                    //CGPA and Credit Completed
                    System.out.println(totalRegCourse);
                    for (int i = 0; i < resultTable.length; i++) {
                        if (resultTable[i][3].equals("I") || resultTable[i][3].equals("F")) {
                            
                        }else{
                            CGPA += Double.parseDouble(resultTable[i][4]) * Double.parseDouble(resultTable[i][1]);
                            completedCredit += Double.parseDouble(resultTable[i][1]);
                            completedCourse++;
                        }

                        
                        if (i == resultTable.length - 1) {
                            if (completedCredit == 0) {
                                CGPA = -1;
                            } else {
                                CGPA = CGPA / completedCredit;
                            }
                        }
                    }
                    
                    batchResultInfo[ixx][0] = T20_info[ixx][1];
                    batchResultInfo[ixx][1] = String.valueOf(completedCourse);
                    batchResultInfo[ixx][2] = String.format("%.2f",completedCredit );                    
                    if(CGPA!=-1){
                        batchResultInfo[ixx][3] = String.format("%.2f",CGPA);
                    }else{
                        batchResultInfo[ixx][3] = "-";
                    }
                    

                }//student wise overall PLO finish
                
                //Final Calculations
                String []finalOverall = new String [plo_count+1] ;
                finalOverall[0] = "Overall";
                for(int i=0;i<plo_count;i++){
                    if(batchOverallPLO[i][1]<=0){
                        finalOverall[i+1] ="-";
                        continue;
                    }
                    finalOverall[i+1] = String.format("%.2f",(batchOverallPLO[i][0] / batchOverallPLO[i][1]) * 100);
                }
                
                double averageCGPA=0.0;
                double tempTotalCredit = 0.0;
                for(int i=0;i<student_count;i++){
                    if(batchResultInfo[i][3].equals("-")){
                        continue;
                    }
                    if(Double.parseDouble(batchResultInfo[i][2])>0){
                        averageCGPA += Double.parseDouble(batchResultInfo[i][3])*Double.parseDouble(batchResultInfo[i][2]);
                        tempTotalCredit += Double.parseDouble(batchResultInfo[i][2]);
                    }     
                }
                if(tempTotalCredit<=0){
                    averageCGPA = -1;
                }else{
                    averageCGPA = averageCGPA/tempTotalCredit;
                }
                
                
                //Batch Info
                String[][] batchInfo = new String[10][2];
                ps = connect.prepareStatement("SELECT "
                        + "university_name,school_name,department_name,program_name,syllabus_name "
                        + "FROM t01_university JOIN "
                        + "(SELECT * FROM t03_school JOIN "
                        + "(SELECT * FROM t04_department JOIN "
                        + "(SELECT * FROM `t06_syllabus` JOIN t05_program ON t06_syllabus.T05_id_fk=t05_program.T05_id WHERE T06_id=?) "
                        + "AS program ON t04_department.T04_id = program.T04_id_fk) AS department ON t03_school.T03_id = department.T03_id_fk) "
                        + "AS school ON t01_university.T01_id = school.T01_id_fk");
                ps.setString(1, idT06);
                rs = ps.executeQuery();
                if (rs.next()) {
                    batchInfo[0][0] = "University";
                    batchInfo[0][1] = rs.getString(1);
                    batchInfo[1][0] = "School";
                    batchInfo[1][1] = rs.getString(2);
                    batchInfo[2][0] = "Department";
                    batchInfo[2][1] = rs.getString(3);
                    batchInfo[3][0] = "Program";
                    batchInfo[3][1] = rs.getString(4);
                    batchInfo[4][0] = "Syllabus";
                    batchInfo[4][1] = rs.getString(5);

                } else {
                    JOptionPane.showMessageDialog(this, "Problem in Batch info!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
                
                ps = connect.prepareStatement("SELECT "
                        + "batch_name,batch_id,t14_semester.semester_name FROM `t17_batch`"
                        + " JOIN t14_semester ON t17_batch.T14_id_fk = t14_semester.T14_id"
                        + " WHERE T17_id = ?");
                ps.setString(1, T17_id[t17Index]);
                rs = ps.executeQuery();
                if(rs.next()){
                    batchInfo[5][0] = "Batch";
                    batchInfo[5][1] = rs.getString(1)+" ("+rs.getString(2)+")";
                    batchInfo[6][0] = "Starting Semester";
                    batchInfo[6][1] = rs.getString(3);
                }else{
                    JOptionPane.showMessageDialog(this, "Problem in Batch info!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
                batchInfo[7][0] = "Total Credit";
                batchInfo[7][1] = String.format("%.2f", totalCreditCount);
                batchInfo[8][0] = "Total Course";
                batchInfo[8][1] = String.valueOf(totalCourse_count);
                batchInfo[9][0] = "Average CGPA";
                if(averageCGPA<0){
                    batchInfo[9][1] = "-";
                }else{
                    batchInfo[9][1] = String.format("%.2f", averageCGPA);
                }
                
                //Creating Excel
                //For both sheet
                //Batch Info
                XSSFWorkbook wb = new XSSFWorkbook();

                XSSFSheet sht1 = wb.createSheet("Batch-PLO");
                XSSFSheet sht2 = wb.createSheet("Batch-Result");

                String[] infoHeaders = {"Batch Info.", "Value"};
                XSSFRow infoHeaderRow1 = sht1.createRow(0);
                XSSFRow infoHeaderRow2 = sht2.createRow(0);
                for (int i = 0; i < infoHeaders.length; i++) {
                    infoHeaderRow1.createCell(i).setCellValue(infoHeaders[i]);
                    infoHeaderRow2.createCell(i).setCellValue(infoHeaders[i]);
                }

                for (int i = 0; i < batchInfo.length; i++) {
                    XSSFRow infoRow1 = sht1.createRow(i + 1);
                    XSSFRow infoRow2 = sht2.createRow(i + 1);
                    for (int j = 0; j < batchInfo[i].length; j++) {
                        infoRow1.createCell(j).setCellValue(batchInfo[i][j]);
                        infoRow2.createCell(j).setCellValue(batchInfo[i][j]);
                    }
                }
                
                
                
                //creating table
                AreaReference infoRef = new AreaReference(
                        new CellReference(0, 0), //1st cell
                        new CellReference(batchInfo.length, infoHeaders.length - 1), // last cell
                        SpreadsheetVersion.EXCEL2007
                );

                XSSFTable infoTable1 = sht1.createTable(infoRef);
                XSSFTable infoTable2 = sht2.createTable(infoRef);
                infoTable1.setName("Batch Information");
                infoTable2.setName("Batch Information");

                //table style
                CTTable ctInfoTable1 = infoTable1.getCTTable();
                CTTable ctInfoTable2 = infoTable2.getCTTable();
                CTTableStyleInfo styleInfo1 = ctInfoTable1.addNewTableStyleInfo();
                CTTableStyleInfo styleInfo2 = ctInfoTable2.addNewTableStyleInfo();
                styleInfo1.setName("TableStyleMedium7");
                styleInfo2.setName("TableStyleMedium7");
                styleInfo1.setShowColumnStripes(false);
                styleInfo2.setShowColumnStripes(false);
                styleInfo1.setShowRowStripes(true);
                styleInfo2.setShowRowStripes(true);

                infoTable1.setStyleName("TableStyleMedium7");
                infoTable2.setStyleName("TableStyleMedium7");
                
                
                
                //PLO Table for Sheet1
                String[] ploHeaders = new String[plo_count + 1];
                ploHeaders[0] = "Student ID";
                for (int i = 0; i < plo_count; i++) {
                    ploHeaders[i + 1] = T09_info[i][1];
                    //System.out.println(ploHeaders[i+1]);
                }
                XSSFRow ploHeaderRow = sht1.createRow(batchInfo.length + 6);
                for (int i = 0; i < ploHeaders.length; i++) {
                    ploHeaderRow.createCell(i + 1).setCellValue(ploHeaders[i]);
                }
                
                //Set PLO Table Tag
                XSSFCellStyle stylePloTag = wb.createCellStyle();

                XSSFColor tagColor = new XSSFColor(Color.decode("#FF8989"), null); // Hex -> Color
                stylePloTag.setFillForegroundColor(tagColor);
                stylePloTag.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                XSSFRow ploTagRow = sht1.createRow(batchInfo.length + 5);
                Cell ploTagCell = ploTagRow.createCell(1);
                Font fontBold = wb.createFont();
                fontBold.setBold(true);
                stylePloTag.setFont(fontBold);
                ploTagCell.setCellValue("PLO Information");
                ploTagCell.setCellStyle(stylePloTag);
                
                //Set PLO Table Horizontal Border
                for (int i = 0; i < plo_count; i++) {
                    XSSFCellStyle styleBorder = wb.createCellStyle();
                    XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                    styleBorder.setFillForegroundColor(borderColor);
                    styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    Cell tempCell = ploTagRow.createCell(2 + i);
                    tempCell.setCellValue("");
                    tempCell.setCellStyle(styleBorder);
                    tempCell = ploTagRow.createCell(0);
                    tempCell.setCellValue("");
                    tempCell.setCellStyle(styleBorder);
                }
                
                //PLO Table Info
                for (int i = 0; i < batchPLOInfo.length; i++) {
                    XSSFRow ploRow = sht1.createRow(batchInfo.length + 6 + 1 + i);
                    ploRow.createCell(1).setCellValue(batchPLOInfo[i][0]);
                    //setting Border Color
                    XSSFCellStyle styleBorder = wb.createCellStyle();
                    XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                    styleBorder.setFillForegroundColor(borderColor);
                    styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    Cell tempCell = ploRow.createCell(0);
                    tempCell.setCellValue("");
                    tempCell.setCellStyle(styleBorder);

                    for (int j = 1; j < batchPLOInfo[i].length; j++) {
                        if (batchPLOInfo[i][j].equals("-") || batchPLOInfo[i][j].equals("Absent")) {
                            ploRow.createCell(j + 1).setCellValue(batchPLOInfo[i][j]);
                        } else {
                            ploRow.createCell(j + 1).setCellValue(batchPLOInfo[i][j] + "%");
                        }
                    }
                    if (i == batchPLOInfo.length - 1) {
                        ploRow = sht1.createRow(batchInfo.length + 6 + 1 + i + 1);
                        ploRow.createCell(1).setCellValue(finalOverall[0]);
                        for (int j = 1; j < finalOverall.length; j++) {
                            if (finalOverall.equals("-")) {
                                ploRow.createCell(j + 1).setCellValue(finalOverall[j]);
                            } else {
                                ploRow.createCell(j + 1).setCellValue(finalOverall[j] + "%");
                            }
                        }
                    }
                }
                
                
                
                //creating table
                
                AreaReference ploRef = new AreaReference(
                        new CellReference(batchInfo.length + 6, 1), //1st cell
                        new CellReference(batchInfo.length + 6 + batchPLOInfo.length + 1, ploHeaders.length), // last cell
                        SpreadsheetVersion.EXCEL2007
                );
                XSSFTable ploTableShow = sht1.createTable(ploRef);
                ploTableShow.setName("PLO Information");

                //table style
                CTTable ctPloTable = ploTableShow.getCTTable();
                CTTableStyleInfo stylePlo = ctPloTable.addNewTableStyleInfo();
                stylePlo.setName("TableStyleMedium7");
                stylePlo.setShowColumnStripes(false);
                stylePlo.setShowRowStripes(true);

                ploTableShow.setStyleName("TableStyleMedium7");

                for (int i = 0; i < ploHeaders.length; i++) {
                    sht1.autoSizeColumn(i);                   //resize column width
                }
                
                
                //Result&Mark Table for sht2
                String[] examHeaders = new String[4];
                examHeaders[0] = "Student ID";
                examHeaders[1] = "Course Completed";
                examHeaders[2] = "Credits Completed";
                examHeaders[3] = "CGPA";

                XSSFRow examHeaderRow = sht2.createRow(batchInfo.length + 6);
                for (int i = 0; i < examHeaders.length; i++) {
                    examHeaderRow.createCell(i + 1).setCellValue(examHeaders[i]);
                }
            
                //Set exam Table Tag
                XSSFCellStyle styleExamTag = wb.createCellStyle();

                //XSSFColor tagColor = new XSSFColor(Color.decode("#FF8989"), null); // Hex -> Color
                styleExamTag.setFillForegroundColor(tagColor);
                styleExamTag.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                XSSFRow examTagRow = sht2.createRow(batchInfo.length + 5);
                Cell examTagCell = examTagRow.createCell(1);
                //Font fontBold = wb.createFont();
                //fontBold.setBold(true);
                styleExamTag.setFont(fontBold);
                examTagCell.setCellValue("CGPA Information");
                examTagCell.setCellStyle(styleExamTag);
            
                //Set Exam Table Horizontal Border
                for (int i = 0; i < examHeaders.length - 1; i++) {
                    XSSFCellStyle styleBorder = wb.createCellStyle();
                    XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                    styleBorder.setFillForegroundColor(borderColor);
                    styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    Cell tempCell = examTagRow.createCell(2 + i);
                    tempCell.setCellValue("");
                    tempCell.setCellStyle(styleBorder);
                    tempCell = examTagRow.createCell(0);
                    tempCell.setCellValue("");
                    tempCell.setCellStyle(styleBorder);
                }
                
                
                //Exam Table Info
                for (int i = 0; i < batchResultInfo.length; i++) {
                    XSSFRow resultRow = sht2.createRow(batchInfo.length + 6 + 1 + i);
                    resultRow.createCell(1).setCellValue(batchResultInfo[i][0]);
                    //setting Border Color
                    XSSFCellStyle styleBorder = wb.createCellStyle();
                    XSSFColor borderColor = new XSSFColor(Color.decode("#EE0000"), null); // Hex -> Color
                    styleBorder.setFillForegroundColor(borderColor);
                    styleBorder.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    Cell tempCell = resultRow.createCell(0);
                    tempCell.setCellValue("");
                    tempCell.setCellStyle(styleBorder);

                    CellStyle centerStyle = wb.createCellStyle();
                    centerStyle.setAlignment(HorizontalAlignment.CENTER);

                    resultRow.createCell(2).setCellValue(batchResultInfo[i][1]);
                    resultRow.createCell(3).setCellValue(batchResultInfo[i][2]);
                    resultRow.createCell(4).setCellValue(batchResultInfo[i][3]);


                }
                
                
                //creating Table
                AreaReference resultRef = new AreaReference(
                        new CellReference(batchInfo.length + 6, 1), //1st cell
                        new CellReference(batchInfo.length + 6 + batchResultInfo.length, examHeaders.length), // last cell
                        SpreadsheetVersion.EXCEL2007
                );
                XSSFTable resultTableCreate = sht2.createTable(resultRef);
                resultTableCreate.setName("Result & CGPA Information");

                //table style
                CTTable ctResultTable = resultTableCreate.getCTTable();
                CTTableStyleInfo styleResult = ctResultTable.addNewTableStyleInfo();
                styleResult.setName("TableStyleMedium7");
                styleResult.setShowColumnStripes(false);
                styleResult.setShowRowStripes(true);

                resultTableCreate.setStyleName("TableStyleMedium7");

                for (int i = 0; i < examHeaders.length; i++) {
                    sht2.autoSizeColumn(i);                   //resize column width
                }
                
                
                //File
                JFileChooser file_chooser = new JFileChooser();
                file_chooser.setDialogTitle("Save Result File");
                file_chooser.setSelectedFile(new File(batchInfo[5][1] + "_Result.xlsx"));

                int select = file_chooser.showSaveDialog(null);
                if (select == JFileChooser.APPROVE_OPTION) {
                    File save_file = file_chooser.getSelectedFile();
                    if (!save_file.getAbsolutePath().endsWith(".xlsx")) {
                        save_file = new File(save_file.getAbsolutePath() + ".xlsx");
                    }
                    FileOutputStream fos = new FileOutputStream(save_file);
                    wb.write(fos);
                    wb.close();
                    fos.close();
                    JOptionPane.showMessageDialog(this, batchInfo[5][1] + " result file saved to: " + save_file.getAbsolutePath());

                }
                

            } else {
                JOptionPane.showMessageDialog(this, "No Student Exists for the Batch", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
        }catch (SQLException ex) {
                
                
                Logger.getLogger(ResultFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NumberFormatException e) {
            Logger.getLogger(ResultFrame.class.getName()).log(Level.SEVERE, null, e);
           
            JOptionPane.showMessageDialog(this, "Number Format Error!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            
        }catch (IOException ex) {
            
            ImageIcon error_page = new ImageIcon(getClass().getResource("/icon/error-page_64.png"));
            String[] options = {"OK"};
            Logger.getLogger(OfferedCourseFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showOptionDialog(this, """
                                               Error in saving File!!!!
                                               Something went wrong in importing xlsx
                                               Try Again!""", "Data Fetch Failed!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, error_page, options, options[0]);
            
            

        }
    }//GEN-LAST:event_generateButtonBatchActionPerformed

    private void generateButtonCourse1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonCourse1ActionPerformed
        // TODO add your handling code here:
        resultComboBox.setEnabled(false);
        resultLayout.show(resultCards, "gradeCard");
        scr1TextField.setText(String.valueOf(score1));
        grd1TextField.setText(grade1);
        scr2TextField.setText(String.valueOf(score2));
        grd2TextField.setText(grade2);
        scr3TextField.setText(String.valueOf(score3));
        grd3TextField.setText(grade3);
        grd4TextField.setText(grade4);
        
        
    }//GEN-LAST:event_generateButtonCourse1ActionPerformed

    private void scr1TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scr1TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scr1TextFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ImageIcon error = new ImageIcon(getClass().getResource("/icon/error_2_64.png"));

        try{
            if(scr1TextField.getText().isBlank() || scr2TextField.getText().isBlank() || scr3TextField.getText().isBlank() ||
                    grd1TextField.getText().isBlank() || grd2TextField.getText().isBlank() || grd3TextField.getText().isBlank() || 
                    grd4TextField.getText().isBlank() ){
                JOptionPane.showMessageDialog(this, "Missing Input!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            double temp1 = Double.parseDouble(scr1TextField.getText());
            double temp2 = Double.parseDouble(scr2TextField.getText());
            double temp3 = Double.parseDouble(scr3TextField.getText());
            
            if(temp1>temp2 && temp1>temp3 && temp1<=100){
                if(temp2>temp3){
                    
                }else{
                    JOptionPane.showMessageDialog(this, "Grade Score is not Correct!!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                    return;
                }
                
            }else{
                JOptionPane.showMessageDialog(this, "Grade Score is not Correct!!", "Error!", JOptionPane.ERROR_MESSAGE, error);
                return;
            }
            
            score1 = temp1;
            score2 = temp2;
            score3 = temp3;
            grade1 = grd1TextField.getText().trim();
            grade2 = grd2TextField.getText().trim();
            grade3 = grd3TextField.getText().trim();
            grade4 = grd4TextField.getText().trim();
            
            
            
        }catch (NumberFormatException e) {
            Logger.getLogger(ResultFrame.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Format Error!", "Error!", JOptionPane.ERROR_MESSAGE, error);
            return;
        }
        //JOptionPane.showMessageDialog(this, "Format Error!", "Error!", JOptionPane.ERROR_MESSAGE, error);
        resultLayout.show(resultCards, "courseCard");
        resultComboBox.setEnabled(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        resultLayout.show(resultCards, "courseCard");
        resultComboBox.setEnabled(true);
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
            java.util.logging.Logger.getLogger(ResultFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResultFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResultFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResultFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ResultFrame("default","default").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> batchComboBox;
    private javax.swing.JComboBox<String> batchComboBox2;
    private javax.swing.JPanel batchPanel;
    private javax.swing.JComboBox<String> courseComboBox;
    private javax.swing.JPanel coursePanel;
    private javax.swing.JButton generateButtonBatch;
    private javax.swing.JButton generateButtonCourse;
    private javax.swing.JButton generateButtonCourse1;
    private javax.swing.JButton generateButtonStudent;
    private javax.swing.JPanel gradePLOPanel;
    private javax.swing.JTextField grd1TextField;
    private javax.swing.JTextField grd2TextField;
    private javax.swing.JTextField grd3TextField;
    private javax.swing.JTextField grd4TextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel resultCards;
    private javax.swing.JComboBox<String> resultComboBox;
    private javax.swing.JTextField scr1TextField;
    private javax.swing.JTextField scr2TextField;
    private javax.swing.JTextField scr3TextField;
    private javax.swing.JComboBox<String> semesterComboBox;
    private javax.swing.JComboBox<String> studentComboBox;
    private javax.swing.JPanel studentPanel;
    // End of variables declaration//GEN-END:variables
}

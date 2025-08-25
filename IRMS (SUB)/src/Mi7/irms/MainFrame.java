/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Mi7.irms;


import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.*;
import java.sql.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


/**
 *
 * @author himal
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    boolean generated_once = false;
    boolean log_out_pressed = false;
    CardLayout userLayout = new CardLayout();
    Color pressed = new Color(102, 192, 140);
    Color notPressed = new Color(237,230,249);
    String id;
    String uni_id;
    String idT16;
    int profile;
    String role;
    String token;
    Connection connect;
    //Statement stmt;
    boolean theme;
    boolean lecturerProfile;
    boolean generated = false;
    CardLayout cardLayout2 = new CardLayout();
    PreparedStatement ps;
    String selectedCourse;
    String T01_id = "";
    boolean themePressed = false;
    
    /*void changePassGeneration(){
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        
        ChangePassFrame cpfrm = new ChangePassFrame(id,this);
        
        cpfrm.setLocation(x+w/4, y+h/4-50);
        this.setEnabled(false);
        cpfrm.setVisible(true);
    }
            
    void clickjButton1(){
        jButton1.doClick();
    }        
            
    */
    void reConnection(){
  
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            generated_once = true;
        }catch(SQLException ex2){
            
            System.out.println(ex2);
        }
    }
    void alert2bGeneration(int c,String msg1,String msg2,String msg3,String btext1,String btext2,String parent,boolean top){
        
        
        this.setEnabled(false);
        AlertFrame2B a2frm = new AlertFrame2B(c,msg1,msg2,msg3,btext1,btext2);
        a2frm.passMainFrame(this, parent);
        a2frm.setLocationRelativeTo(null);
        a2frm.setAlwaysOnTop(top);
        a2frm.setVisible(true);         
        
    }
    /*
    
    void warning2bGeneration(int c,String msg1,String msg2,String msg3,String btext1,String btext2,String parent){
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        
        this.setEnabled(false);
        AlertFrame2B wfrm = new AlertFrame2B(c,msg1,msg2,msg3,btext1,btext2);
        wfrm.passMainFrame(this, parent);
        wfrm.setLocation(x+w/4, y+h/4);
        
        wfrm.setVisible(true);         
        
    }
    void errorGeneration(int c,String msg1,String msg2,String msg3){
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;

        ErrorFrame efrm = new ErrorFrame(c,msg1,msg2,msg3);
        
        efrm.setLocation(x+w/4, y+h/4);
        efrm.setVisible(true);
    }*/
    
    void viewTableMethod(String [] tables){
        ViewTableFrame vtfrm = new ViewTableFrame(tables);
        vtfrm.setLocationRelativeTo(null);     //opens the frame in the middle of the screen
        vtfrm.setVisible(true);
    }
    
    public MainFrame(String token,boolean theme) {
        initComponents();
        themeCheckBox1.setSelected(theme);
        themePressed = false;
        try{
            jScrollPane1.getVerticalScrollBar().setUnitIncrement(10);
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            this.token = token;
            String [] tempToken = token.split(" ");
            id = tempToken[0];
            uni_id = tempToken[1];
            profile = Integer.parseInt(tempToken[2]);
            idT16 = "None!";
            PreparedStatement ps;
            ps = connect.prepareStatement("SELECT T01_id FROM `t01_university` WHERE university_id = ?;");
            ps.setString(1,uni_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                T01_id = rs.getString(1);
            }
            
            if(profile == 2){
                if(uni_id.equals("UV-0000")){
                    String [] temp_comboBox = new String[]{"Super Admin"};
                    ProfileComboBox.setModel(new DefaultComboBoxModel(temp_comboBox));
                    generated = true;
                    

                }else{
                    String [] temp_comboBox = new String[]{"Admin "+uni_id};
                    ProfileComboBox.setModel(new DefaultComboBoxModel(temp_comboBox));
                    generated = true;
                    
                }
                
            }else if (profile == 0){
                ps = connect.prepareStatement("SELECT T02_id_fk,T16_id FROM `t16_employee` "
                        + "WHERE employee_id = ? AND T01_id_fk = ?");
                ps.setString(1, id);
                ps.setString(2, T01_id);
                rs = ps.executeQuery();
                String[] temp_comboBox = {"No_Role! ERROR"};
                while(rs.next()){
                    if(rs.getInt(1)==1){
                        temp_comboBox[0] = "Admin "+id;
                    }else if(rs.getInt(1)==2){
                        idT16 = rs.getString(2);
                        temp_comboBox[0] = "Teacher "+id;
                    } 
                }
                ProfileComboBox.setModel(new DefaultComboBoxModel(temp_comboBox));
                generated = true;
            }
            
        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            alert2bGeneration(2,"Server Connection Failed!!",msg,"Please,Retry Connection or Quit!","Retry","Quit","MainFrame",true);
            System.out.println(e);
        }
        
        
        userLayout = (CardLayout)(UserCards.getLayout());
        ProfileButton.doClick(1);
        ProfileComboBox.setSelectedIndex(0);
        generated_once = true;
        
        /*lecturerProfile = false;
        this.theme=theme;
        themeCheckBox.setSelected(theme);
        cardLayout = (CardLayout)(ButtonCards.getLayout());
        cardLayout2 = (CardLayout)(UserCards.getLayout());
        ProfileButton.doClick(1);
        jButton7.setEnabled(false); //produce result button
        this.id=id;
        int temp=0;
        String [] tempItem = new String [10];
        jLabel9.setVisible(false);
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums_database","root","");
            stmt = connect.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT J.job_name FROM employees E JOIN jobs J ON (j.job_id=e.primary_job_id AND e.employee_id=\""+id+"\")");
            while(rs.next()){
                tempItem[temp]=rs.getString(1);
                temp++;
            }
            
            rs = stmt.executeQuery("SELECT J.job_name FROM add_responsibility A JOIN jobs J ON (A.job_id=J.job_id AND A.employee_id=\""+ id + "\")");
            while(rs.next()){
                tempItem[temp]=rs.getString(1);
                temp++;
            }
            
        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            warning2bGeneration(2,"Server Connection Failed!!",msg,"Please,Retry Connection or Quit!","Retry","Quit","MainFrame",true);
            System.out.println(e);
        }
        
        
        
        String [] item = new String [temp];
        for(int i=0;i<temp;i++){
            item[i]=tempItem[i];
            if(item[i].equals("Lecturer")){
                lecturerProfile = true;
            }
        }
        
        
        ProfileComboBox.removeAllItems();
        ProfileComboBox.setModel(new DefaultComboBoxModel(item));
        generated = true;
        ProfileComboBox.setSelectedIndex(0);
        if(lecturerProfile){
            try {
                temp =0;
                ps = connect.prepareStatement("SELECT course_id FROM `assign_course` WHERE employee_id = ?");
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                String [] tempCourse = new String [100];
                while(rs.next()){
                    tempCourse[temp]=rs.getString(1);
                    temp++;
                }
                String [] course = new String [temp+1];
                course [0] = "Select Course!";
                for(int i=1;i<=temp;i++){
                    course[i]=tempCourse[i-1];
                }
                jComboBox1.removeAllItems();
                jComboBox1.setModel(new DefaultComboBoxModel(course));
                jComboBox1.setSelectedIndex(0);
                jButton1.doClick();
                
            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        ProfileButton = new javax.swing.JButton();
        LogoutButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        SlideLeftButton = new javax.swing.JButton();
        ButtonCards = new javax.swing.JPanel();
        ProfilePnl = new javax.swing.JPanel();
        ProfileComboBox = new javax.swing.JComboBox<>();
        ChangePassButton1 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        themeCheckBox1 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        UserCards = new javax.swing.JPanel();
        AdminPnl = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel29 = new javax.swing.JPanel();
        addSchoolButton = new javax.swing.JButton();
        editSchoolButton = new javax.swing.JButton();
        addDepartmentButton = new javax.swing.JButton();
        editDepartmentButton = new javax.swing.JButton();
        addProgramButton = new javax.swing.JButton();
        editProgramButton = new javax.swing.JButton();
        addSyllabusButton = new javax.swing.JButton();
        editSyllabusButton = new javax.swing.JButton();
        syllabusSettingsButton = new javax.swing.JButton();
        semesterButton = new javax.swing.JButton();
        batchButton = new javax.swing.JButton();
        studentButton = new javax.swing.JButton();
        employeeButton = new javax.swing.JButton();
        offeredCourseButton = new javax.swing.JButton();
        customCLOButton = new javax.swing.JButton();
        markDistributionButton = new javax.swing.JButton();
        resultButton = new javax.swing.JButton();
        irmsButton = new javax.swing.JButton();
        AdminSuperPnl = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        TeacherPnl = new javax.swing.JPanel();
        customCLOButtonTeacher = new javax.swing.JButton();
        markDistributionButtonTeacher = new javax.swing.JButton();
        NoRole = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IRMS");
        setPreferredSize(new java.awt.Dimension(940, 680));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(238, 106, 113));
        jPanel5.setPreferredSize(new java.awt.Dimension(900, 30));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 940, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setBackground(new java.awt.Color(0, 0, 0));
        jSplitPane1.setDividerLocation(400);
        jSplitPane1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(85, 110));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(179, 224, 198));
        jPanel3.setPreferredSize(new java.awt.Dimension(85, 650));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 7));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(2, 2, 2));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/main_logo64.png"))); // NOI18N
        jLabel1.setText("IRMS");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel1);

        jPanel6.setBackground(new java.awt.Color(179, 224, 198));
        jPanel6.setPreferredSize(new java.awt.Dimension(75, 40));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel6);

        ProfileButton.setBackground(new java.awt.Color(179, 224, 198));
        ProfileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/profileselect2_64.png"))); // NOI18N
        ProfileButton.setToolTipText("button");
        ProfileButton.setBorderPainted(false);
        ProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfileButtonActionPerformed(evt);
            }
        });
        jPanel3.add(ProfileButton);

        LogoutButton.setBackground(new java.awt.Color(179, 224, 198));
        LogoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/log-out64_1.png"))); // NOI18N
        LogoutButton.setBorderPainted(false);
        LogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutButtonActionPerformed(evt);
            }
        });
        jPanel3.add(LogoutButton);

        jPanel1.add(jPanel3, java.awt.BorderLayout.WEST);

        jPanel4.setBackground(new java.awt.Color(243, 238, 251));
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 650));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(243, 238, 251));
        jPanel7.setPreferredSize(new java.awt.Dimension(315, 167));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(230, 245, 236));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 70));
        jPanel10.add(jLabel2);

        jPanel7.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel12.setBackground(new java.awt.Color(230, 245, 236));
        jPanel12.setPreferredSize(new java.awt.Dimension(60, 167));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel13.setBackground(new java.awt.Color(230, 245, 236));
        jPanel13.setPreferredSize(new java.awt.Dimension(60, 50));

        SlideLeftButton.setBackground(new java.awt.Color(230, 245, 236));
        SlideLeftButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/left-arrow_32.png"))); // NOI18N
        SlideLeftButton.setBorderPainted(false);
        SlideLeftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SlideLeftButtonActionPerformed(evt);
            }
        });
        jPanel13.add(SlideLeftButton);

        jPanel12.add(jPanel13, java.awt.BorderLayout.NORTH);

        jPanel7.add(jPanel12, java.awt.BorderLayout.EAST);

        jPanel4.add(jPanel7, java.awt.BorderLayout.NORTH);

        ButtonCards.setPreferredSize(new java.awt.Dimension(300, 453));
        ButtonCards.setLayout(new java.awt.CardLayout());

        ProfilePnl.setBackground(new java.awt.Color(230, 245, 236));
        ProfilePnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        ProfileComboBox.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        ProfileComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Role!" }));
        ProfileComboBox.setMaximumSize(new java.awt.Dimension(250, 32));
        ProfileComboBox.setMinimumSize(new java.awt.Dimension(250, 32));
        ProfileComboBox.setPreferredSize(new java.awt.Dimension(250, 32));
        ProfileComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfileComboBoxActionPerformed(evt);
            }
        });
        ProfilePnl.add(ProfileComboBox);

        ChangePassButton1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        ChangePassButton1.setText("Change Password");
        ChangePassButton1.setMaximumSize(new java.awt.Dimension(210, 32));
        ChangePassButton1.setMinimumSize(new java.awt.Dimension(210, 32));
        ChangePassButton1.setPreferredSize(new java.awt.Dimension(210, 32));
        ChangePassButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangePassButton1ActionPerformed(evt);
            }
        });
        ProfilePnl.add(ChangePassButton1);

        jPanel17.setBackground(new java.awt.Color(230, 245, 236));
        jPanel17.setPreferredSize(new java.awt.Dimension(210, 80));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(1, 1, 1));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sun_32.png"))); // NOI18N
        jLabel4.setText("Light");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel17.add(jLabel4);

        themeCheckBox1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        themeCheckBox1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/toggle-button_off_64.png"))); // NOI18N
        themeCheckBox1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/toggle-button_on_64.png"))); // NOI18N
        themeCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themeCheckBox1ActionPerformed(evt);
            }
        });
        jPanel17.add(themeCheckBox1);

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(1, 1, 1));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/night_32.png"))); // NOI18N
        jLabel5.setText("Dark");
        jPanel17.add(jLabel5);

        ProfilePnl.add(jPanel17);

        jLabel6.setBackground(new java.awt.Color(255, 237, 0));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/SUB-only-logo_256.png"))); // NOI18N
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ProfilePnl.add(jLabel6);

        ButtonCards.add(ProfilePnl, "ProfileCard");

        jPanel4.add(ButtonCards, java.awt.BorderLayout.WEST);

        jPanel9.setBackground(new java.awt.Color(230, 245, 236));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel2.setMinimumSize(new java.awt.Dimension(200, 200));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel8.setMaximumSize(new java.awt.Dimension(495, 100));
        jPanel8.setMinimumSize(new java.awt.Dimension(495, 100));
        jPanel8.setPreferredSize(new java.awt.Dimension(508, 120));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel22.setBackground(new java.awt.Color(230, 245, 236));
        jPanel22.setPreferredSize(new java.awt.Dimension(145, 106));
        jPanel22.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 10));

        jButton14.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/data_table_32.png"))); // NOI18N
        jButton14.setText("View Tables");
        jButton14.setPreferredSize(new java.awt.Dimension(134, 45));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel22.add(jButton14);

        jPanel8.add(jPanel22, java.awt.BorderLayout.EAST);

        jPanel20.setBackground(new java.awt.Color(230, 245, 236));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/course_64.png"))); // NOI18N
        jLabel9.setText("Selected Course: No Course Selected");
        jPanel20.add(jLabel9);

        jPanel8.add(jPanel20, java.awt.BorderLayout.CENTER);

        jPanel28.setBackground(new java.awt.Color(230, 245, 236));
        jPanel28.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 535, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel28, java.awt.BorderLayout.SOUTH);

        jPanel2.add(jPanel8, java.awt.BorderLayout.NORTH);

        UserCards.setPreferredSize(new java.awt.Dimension(450, 550));
        UserCards.setLayout(new java.awt.CardLayout());

        AdminPnl.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 237, 0));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel29.setBackground(new java.awt.Color(255, 237, 0));
        jPanel29.setLayout(new java.awt.GridLayout(9, 2, 1, 1));

        addSchoolButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        addSchoolButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addschool_64.png"))); // NOI18N
        addSchoolButton.setText("Add School");
        addSchoolButton.setPreferredSize(new java.awt.Dimension(220, 80));
        addSchoolButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSchoolButtonActionPerformed(evt);
            }
        });
        jPanel29.add(addSchoolButton);

        editSchoolButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        editSchoolButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/eraser_64.png"))); // NOI18N
        editSchoolButton.setText("Edit School");
        editSchoolButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSchoolButtonActionPerformed(evt);
            }
        });
        jPanel29.add(editSchoolButton);

        addDepartmentButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        addDepartmentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/addDepartment_64.png"))); // NOI18N
        addDepartmentButton.setText("Add Department");
        addDepartmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDepartmentButtonActionPerformed(evt);
            }
        });
        jPanel29.add(addDepartmentButton);

        editDepartmentButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        editDepartmentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/eraser2_64.png"))); // NOI18N
        editDepartmentButton.setText("Edit Department");
        editDepartmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editDepartmentButtonActionPerformed(evt);
            }
        });
        jPanel29.add(editDepartmentButton);

        addProgramButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        addProgramButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/program_64.png"))); // NOI18N
        addProgramButton.setText("Add Program");
        addProgramButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProgramButtonActionPerformed(evt);
            }
        });
        jPanel29.add(addProgramButton);

        editProgramButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        editProgramButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/eraser3_64.png"))); // NOI18N
        editProgramButton.setText("Edit Program");
        editProgramButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProgramButtonActionPerformed(evt);
            }
        });
        jPanel29.add(editProgramButton);

        addSyllabusButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        addSyllabusButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/syllabus_64.png"))); // NOI18N
        addSyllabusButton.setText("Add Syllabus");
        addSyllabusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSyllabusButtonActionPerformed(evt);
            }
        });
        jPanel29.add(addSyllabusButton);

        editSyllabusButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        editSyllabusButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/eraser_syllabus_64.png"))); // NOI18N
        editSyllabusButton.setText("Edit Syllabus");
        editSyllabusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSyllabusButtonActionPerformed(evt);
            }
        });
        jPanel29.add(editSyllabusButton);

        syllabusSettingsButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        syllabusSettingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/syllabusSettings_64.png"))); // NOI18N
        syllabusSettingsButton.setText("Syllabus Settings");
        syllabusSettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                syllabusSettingsButtonActionPerformed(evt);
            }
        });
        jPanel29.add(syllabusSettingsButton);

        semesterButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        semesterButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/semester_64.png"))); // NOI18N
        semesterButton.setText("Semester");
        semesterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semesterButtonActionPerformed(evt);
            }
        });
        jPanel29.add(semesterButton);

        batchButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        batchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/batch_64.png"))); // NOI18N
        batchButton.setText("Batch           ");
        batchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchButtonActionPerformed(evt);
            }
        });
        jPanel29.add(batchButton);

        studentButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        studentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/student_64.png"))); // NOI18N
        studentButton.setText("Student");
        studentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentButtonActionPerformed(evt);
            }
        });
        jPanel29.add(studentButton);

        employeeButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        employeeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/teacher_64.png"))); // NOI18N
        employeeButton.setText("Employee");
        employeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeButtonActionPerformed(evt);
            }
        });
        jPanel29.add(employeeButton);

        offeredCourseButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        offeredCourseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/offeredCourse_64.png"))); // NOI18N
        offeredCourseButton.setText("Offered Course");
        offeredCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offeredCourseButtonActionPerformed(evt);
            }
        });
        jPanel29.add(offeredCourseButton);

        customCLOButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        customCLOButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/customCLO_64.png"))); // NOI18N
        customCLOButton.setText("Custom CLO");
        customCLOButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customCLOButtonActionPerformed(evt);
            }
        });
        jPanel29.add(customCLOButton);

        markDistributionButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        markDistributionButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mark_distribution_64.png"))); // NOI18N
        markDistributionButton.setText("Mark Distribution");
        markDistributionButton.setPreferredSize(new java.awt.Dimension(220, 80));
        markDistributionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markDistributionButtonActionPerformed(evt);
            }
        });
        jPanel29.add(markDistributionButton);

        resultButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        resultButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/result-analysis_64.png"))); // NOI18N
        resultButton.setText("Result & Analysis");
        resultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultButtonActionPerformed(evt);
            }
        });
        jPanel29.add(resultButton);

        irmsButton.setBackground(new java.awt.Color(243, 238, 251));
        irmsButton.setFont(new java.awt.Font("SansSerif", 2, 14)); // NOI18N
        irmsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/main_logo64.png"))); // NOI18N
        irmsButton.setText("IRMS");
        irmsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irmsButtonActionPerformed(evt);
            }
        });
        jPanel29.add(irmsButton);

        jScrollPane1.setViewportView(jPanel29);

        AdminPnl.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        UserCards.add(AdminPnl, "AdminCard");

        AdminSuperPnl.setLayout(new java.awt.GridLayout(2, 1));

        jPanel14.setBackground(new java.awt.Color(230, 245, 236));
        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 20));

        jButton10.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add_university_64.png"))); // NOI18N
        jButton10.setText("Enter New University");
        jButton10.setPreferredSize(new java.awt.Dimension(250, 80));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton10);

        jButton11.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit_university_64.png"))); // NOI18N
        jButton11.setText("Edit University Info");
        jButton11.setPreferredSize(new java.awt.Dimension(250, 80));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton11);

        AdminSuperPnl.add(jPanel14);

        jPanel21.setBackground(new java.awt.Color(230, 245, 236));
        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 20));

        jButton12.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add_admin_64.png"))); // NOI18N
        jButton12.setText("Enter University Admin");
        jButton12.setPreferredSize(new java.awt.Dimension(250, 80));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton12);

        jButton13.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit_admin_2_64.png"))); // NOI18N
        jButton13.setText("Edit University Admin");
        jButton13.setPreferredSize(new java.awt.Dimension(250, 80));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton13);

        AdminSuperPnl.add(jPanel21);

        UserCards.add(AdminSuperPnl, "SuperAdminCard");

        TeacherPnl.setBackground(new java.awt.Color(230, 245, 236));
        TeacherPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 50));

        customCLOButtonTeacher.setBackground(new java.awt.Color(255, 253, 230));
        customCLOButtonTeacher.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        customCLOButtonTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/customCLO_64.png"))); // NOI18N
        customCLOButtonTeacher.setText("Custom CLO");
        customCLOButtonTeacher.setPreferredSize(new java.awt.Dimension(300, 100));
        customCLOButtonTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customCLOButtonTeacherActionPerformed(evt);
            }
        });
        TeacherPnl.add(customCLOButtonTeacher);

        markDistributionButtonTeacher.setBackground(new java.awt.Color(255, 253, 230));
        markDistributionButtonTeacher.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        markDistributionButtonTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mark_distribution_64.png"))); // NOI18N
        markDistributionButtonTeacher.setText("Mark Distribution");
        markDistributionButtonTeacher.setPreferredSize(new java.awt.Dimension(300, 100));
        markDistributionButtonTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markDistributionButtonTeacherActionPerformed(evt);
            }
        });
        TeacherPnl.add(markDistributionButtonTeacher);

        UserCards.add(TeacherPnl, "TeacherCard");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/error_64.png"))); // NOI18N
        jLabel7.setText("ERROR!!!!!");
        NoRole.add(jLabel7);

        UserCards.add(NoRole, "NoRoleCard");

        jPanel2.add(UserCards, java.awt.BorderLayout.CENTER);

        jPanel11.setBackground(new java.awt.Color(230, 245, 236));
        jPanel11.setPreferredSize(new java.awt.Dimension(30, 550));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel11, java.awt.BorderLayout.WEST);

        jPanel19.setBackground(new java.awt.Color(230, 245, 236));
        jPanel19.setPreferredSize(new java.awt.Dimension(40, 40));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 535, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel19, java.awt.BorderLayout.SOUTH);

        jPanel18.setBackground(new java.awt.Color(230, 245, 236));
        jPanel18.setPreferredSize(new java.awt.Dimension(30, 550));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel18, java.awt.BorderLayout.EAST);

        jSplitPane1.setRightComponent(jPanel2);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfileButtonActionPerformed
        // TODO add your handling code here:
        ProfileButton.setBackground(pressed);
        
        //cardLayout.show(ButtonCards, "ProfileCard");
        jSplitPane1.setDividerLocation(400);
    }//GEN-LAST:event_ProfileButtonActionPerformed

    private void SlideLeftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SlideLeftButtonActionPerformed
        // TODO add your handling code here:
        jSplitPane1.setDividerLocation(85);
 
    }//GEN-LAST:event_SlideLeftButtonActionPerformed

    private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutButtonActionPerformed
        // TODO add your handling code here:
        log_out_pressed = true;
        for (Window win : Window.getWindows()){
            if(win instanceof JFrame){
                win.dispose();                      //closes all opened windows
            }
        }
        LoginFrame lfrm = new LoginFrame();
        lfrm.setVisible(true);
        //dispose();
    }//GEN-LAST:event_LogoutButtonActionPerformed

    private void ProfileComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfileComboBoxActionPerformed
        // TODO add your handling code here:
        if(generated){
            String [] user = ProfileComboBox.getSelectedItem().toString().split(" ", 2);
            if(user[0].equals("Super")){
                jButton14.setVisible(true);
                jLabel9.setText("   "+id);
                jLabel2.setIcon(new ImageIcon(getClass().getResource("/icon/superadmin_64.png")));
                jLabel9.setIcon(new ImageIcon(getClass().getResource("/icon/superadmin_64.png")));
                userLayout.show(UserCards, "SuperAdminCard");
            }else if(user[0].equals("Admin")){
                jButton14.setVisible(false);
                jLabel9.setText("   "+id);
                if(profile==0){
                    jLabel2.setIcon(new ImageIcon(getClass().getResource("/icon/admin_64.png")));
                    jLabel9.setIcon(new ImageIcon(getClass().getResource("/icon/admin_64.png")));
                }else if(profile==2){
                    jLabel2.setIcon(new ImageIcon(getClass().getResource("/icon/superadmin_64.png")));
                    jLabel9.setIcon(new ImageIcon(getClass().getResource("/icon/superadmin_64.png")));
                }
                
                userLayout.show(UserCards, "AdminCard");
            }else if(user[0].equals("Teacher")){
                jButton14.setVisible(false);
                jLabel9.setText("   "+id);
                jLabel2.setIcon(new ImageIcon(getClass().getResource("/icon/teacher_64.png")));
                jLabel9.setIcon(new ImageIcon(getClass().getResource("/icon/teacher_64.png")));
                userLayout.show(UserCards, "TeacherCard");
            }else if(user[0].equals("No_Role!")){
                jButton14.setVisible(false);
                jLabel9.setText(" ERROR!!!  ");
                jLabel9.setIcon(new ImageIcon(getClass().getResource("/icon/error_64.png")));
                userLayout.show(UserCards, "NoRoleCard");
            }
            
        }
        /*if(generated){
            profile = ProfileComboBox.getSelectedItem().toString();
            if(profile.equals("Lecturer")){
                jLabel9.setVisible(true);
                cardLayout2.show(UserCards, "TeacherCard");
            }else if(profile.equals("Executive of Office of Register")){
                jLabel9.setVisible(false);
                cardLayout2.show(UserCards, "RegExOfficerCard");
            }else if(profile.equals("Executive of Office of VC")){
                jLabel9.setVisible(false);
                cardLayout2.show(UserCards, "VCExOfficerCard");
            }else if(profile.equals("Executive of Office of CSE")){
                jLabel9.setVisible(false);
                cardLayout2.show(UserCards, "CSEExOfficerCard");
            }else{
                jLabel9.setVisible(false);
                cardLayout2.show(UserCards, "NoRoleCard");
            }
            
            //System.out.println(profile);
        }*/
        
        //System.out.println("action!");
    }//GEN-LAST:event_ProfileComboBoxActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        //Enter University button
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        
          
        EnterUniversityFrame eufrm = new EnterUniversityFrame();
        eufrm.setLocation(x+w/4, y+h/4);
        eufrm.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        if(uni_id.equals("UV-0000") && profile==2){
            String [] tables = new String [] {"t01_university","admin"};
            viewTableMethod(tables);
            System.out.println("1");
        }
               
    }//GEN-LAST:event_jButton14ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        if(log_out_pressed == false && themePressed == false){
            System.exit(0);
        }
        themePressed = false;
    }//GEN-LAST:event_formWindowClosed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        
          
        EnterUniversityAdminFrame euafrm = new EnterUniversityAdminFrame();
        euafrm.setLocation(x+w/4, y+h/4);
        euafrm.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        
          
        EditUniversityInfoFrame euifrm = new EditUniversityInfoFrame();
        euifrm.setLocation(x+w/4, y+h/4);
        euifrm.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        
          
        EditUniversityAdminFrame eua2frm = new EditUniversityAdminFrame();
        eua2frm.setLocation(x+w/4, y+h/4);
        eua2frm.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void addSchoolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSchoolButtonActionPerformed
        // TODO add your handling code here:
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        
          
        AddSchoolFrame asfrm = new AddSchoolFrame(uni_id);
        asfrm.setLocation(x+w/4, y+h/4);
        asfrm.setVisible(true);
        
    }//GEN-LAST:event_addSchoolButtonActionPerformed

    private void editSchoolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSchoolButtonActionPerformed
        // TODO add your handling code here:
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        
          
        EditSchoolFrame esfrm = new EditSchoolFrame(uni_id);
        esfrm.setLocation(x+w/4, y+h/4);
        esfrm.setVisible(true);
    }//GEN-LAST:event_editSchoolButtonActionPerformed

    private void addDepartmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDepartmentButtonActionPerformed
        // TODO add your handling code here:
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        
          
        AddDepartmentFrame adfrm = new AddDepartmentFrame(uni_id);
        adfrm.setLocation(x+w/4, y+h/4);
        adfrm.setVisible(true);
    }//GEN-LAST:event_addDepartmentButtonActionPerformed

    private void editDepartmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDepartmentButtonActionPerformed
        // TODO add your handling code here:
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        
          
        EditDepartmentFrame edfrm = new EditDepartmentFrame(T01_id);
        edfrm.setLocation(x+w/4, y+h/4);
        edfrm.setVisible(true);
    }//GEN-LAST:event_editDepartmentButtonActionPerformed

    private void addProgramButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProgramButtonActionPerformed
        // TODO add your handling code here:
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        
          
        AddProgramFrame apfrm = new AddProgramFrame(T01_id);
        apfrm.setLocation(x+w/4, y+h/4);
        apfrm.setVisible(true);
    }//GEN-LAST:event_addProgramButtonActionPerformed

    private void editProgramButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editProgramButtonActionPerformed
        // TODO add your handling code here:
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        
          
        EditProgramFrame epfrm = new EditProgramFrame(T01_id);
        epfrm.setLocation(x+w/4, y+h/4);
        epfrm.setVisible(true);
    }//GEN-LAST:event_editProgramButtonActionPerformed

    private void addSyllabusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSyllabusButtonActionPerformed
        // TODO add your handling code here:
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;
        
          
        AddSyllabusFrame asbfrm = new AddSyllabusFrame(T01_id);
        asbfrm.setLocation(x+w/4, y+h/4);
        asbfrm.setVisible(true);
    }//GEN-LAST:event_addSyllabusButtonActionPerformed

    private void editSyllabusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSyllabusButtonActionPerformed
        // TODO add your handling code here:
        
        
          
        EditSyllabusFrame esbfrm = new EditSyllabusFrame(T01_id);
        esbfrm.setLocationRelativeTo(null);
        esbfrm.setVisible(true);
    }//GEN-LAST:event_editSyllabusButtonActionPerformed

    private void syllabusSettingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_syllabusSettingsButtonActionPerformed
        // TODO add your handling code here:
        SelectSyllabusFrame ssbfrm = new SelectSyllabusFrame(T01_id,1,token);
        ssbfrm.setLocationRelativeTo(null);
        ssbfrm.setVisible(true);
        ssbfrm.passMainFrame(this);
        this.setEnabled(false);
    }//GEN-LAST:event_syllabusSettingsButtonActionPerformed

    private void semesterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semesterButtonActionPerformed
        // TODO add your handling code here:
        SelectSyllabusFrame ssbfrm = new SelectSyllabusFrame(T01_id,2,token);
        ssbfrm.setLocationRelativeTo(null);
        ssbfrm.setVisible(true);
        ssbfrm.passMainFrame(this);
        this.setEnabled(false);
    }//GEN-LAST:event_semesterButtonActionPerformed

    private void offeredCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offeredCourseButtonActionPerformed
        // TODO add your handling code here:
        SelectSyllabusFrame ssbfrm = new SelectSyllabusFrame(T01_id,3,token);
        ssbfrm.setLocationRelativeTo(null);
        ssbfrm.setVisible(true);
        ssbfrm.passMainFrame(this);
        this.setEnabled(false);
    }//GEN-LAST:event_offeredCourseButtonActionPerformed

    private void employeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeButtonActionPerformed
        // TODO add your handling code here:
        UpdateEmployeeFrame uempfrm = new UpdateEmployeeFrame(T01_id,token);
        uempfrm.setLocationRelativeTo(null);
        uempfrm.passMainFrmae(this);
        uempfrm.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_employeeButtonActionPerformed

    private void batchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchButtonActionPerformed
        // TODO add your handling code here:
        SelectSyllabusFrame ssbfrm = new SelectSyllabusFrame(T01_id,4,token);
        ssbfrm.setLocationRelativeTo(null);
        ssbfrm.setVisible(true);
        ssbfrm.passMainFrame(this);
        this.setEnabled(false);
    }//GEN-LAST:event_batchButtonActionPerformed

    private void markDistributionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_markDistributionButtonActionPerformed
        // TODO add your handling code here:
        SelectSyllabusFrame ssbfrm = new SelectSyllabusFrame(T01_id,5,token);
        ssbfrm.setLocationRelativeTo(null);
        ssbfrm.setVisible(true);
        ssbfrm.passMainFrame(this);
        this.setEnabled(false);
    }//GEN-LAST:event_markDistributionButtonActionPerformed

    private void studentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentButtonActionPerformed
        // TODO add your handling code here:
        SelectSyllabusFrame ssbfrm = new SelectSyllabusFrame(T01_id,6,token);
        ssbfrm.setLocationRelativeTo(null);
        ssbfrm.setVisible(true);
        ssbfrm.passMainFrame(this);
        this.setEnabled(false);
    }//GEN-LAST:event_studentButtonActionPerformed

    private void customCLOButtonTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customCLOButtonTeacherActionPerformed
        // TODO add your handling code here:
        SelectSyllabusFrame ssbfrm = new SelectSyllabusFrame(T01_id,7,token);
        ssbfrm.setLocationRelativeTo(null);
        ssbfrm.setVisible(true);
        //ssbfrm.passidT16(idT16);
        ssbfrm.passMainFrame(this);
        this.setEnabled(false);
    }//GEN-LAST:event_customCLOButtonTeacherActionPerformed

    private void customCLOButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customCLOButtonActionPerformed
        // TODO add your handling code here:
        SelectSyllabusFrame ssbfrm = new SelectSyllabusFrame(T01_id,7,token);
        ssbfrm.setLocationRelativeTo(null);
        ssbfrm.setVisible(true);
        ssbfrm.passMainFrame(this);
        this.setEnabled(false);
    }//GEN-LAST:event_customCLOButtonActionPerformed

    private void resultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultButtonActionPerformed
        // TODO add your handling code here:
        SelectSyllabusFrame ssbfrm = new SelectSyllabusFrame(T01_id,8,token);
        ssbfrm.setLocationRelativeTo(null);
        ssbfrm.setVisible(true);
        ssbfrm.passMainFrame(this);
        this.setEnabled(false);
    }//GEN-LAST:event_resultButtonActionPerformed

    private void ChangePassButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangePassButton1ActionPerformed
        // TODO add your handling code here:
        ChangePassFrame cpfrm = new ChangePassFrame(this);
        cpfrm.setLocationRelativeTo(null);
        this.setEnabled(false);
        cpfrm.setVisible(true);
    }//GEN-LAST:event_ChangePassButton1ActionPerformed

    private void themeCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themeCheckBox1ActionPerformed
        // TODO add your handling code here:
        
        if(themeCheckBox1.isSelected()){
            try{
                //System.out.println("Selected");
                UIManager.setLookAndFeel(new FlatDarkLaf());
                theme = true;
                //warning2bGeneration(1,"Need Restart for theme to take Full Effect!","","","Restart","Cancel","MainFrame");
                ImageIcon restart = new ImageIcon(getClass().getResource("/icon/reload_64.png"));
                Object[] options = {"Restart", "Cancel"};

                int result = JOptionPane.showOptionDialog(
                        null,
                        "Need Restart for theme to take Full Effect!",
                        "Confirm Restart",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        restart,
                        options,
                        options[0] // 
                );

                if (result == 0) {
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    //System.out.println(theme);
                    MainFrame mfrm2 = new MainFrame(this.token,theme);
                    mfrm2.setLocationRelativeTo(null);
                    mfrm2.setVisible(true);
                    themePressed = true;
                    this.dispose();
                   
                } else {
                    
                }
                

            } catch (Exception e) {
                System.out.println(e);
            }

        }else{
            try{
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
                theme = false;
                //System.out.println("Not Selected");
                ImageIcon restart = new ImageIcon(getClass().getResource("/icon/reload_64.png"));
                Object[] options = {"Restart", "Cancel"};

                int result = JOptionPane.showOptionDialog(
                        null,
                        "Need Restart for theme to take Full Effect!",
                        "Confirm Restart",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        restart,
                        options,
                        options[0] // 
                );

                if (result == 0) {
                    UIManager.setLookAndFeel(new FlatIntelliJLaf());
                    //System.out.println(theme);
                    MainFrame mfrm2 = new MainFrame(this.token,theme);
                    mfrm2.setLocationRelativeTo(null);
                    mfrm2.setVisible(true);
                    themePressed = true;
                    this.dispose();
                   
                } else {
                    
                }

            }catch(Exception e){
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_themeCheckBox1ActionPerformed

    private void markDistributionButtonTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_markDistributionButtonTeacherActionPerformed
        // TODO add your handling code here:
        SelectSyllabusFrame ssbfrm = new SelectSyllabusFrame(T01_id,5,token);
        ssbfrm.setLocationRelativeTo(null);
        ssbfrm.setVisible(true);
        ssbfrm.passMainFrame(this);
        this.setEnabled(false);
    }//GEN-LAST:event_markDistributionButtonTeacherActionPerformed

    private void irmsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irmsButtonActionPerformed
        // TODO add your handling code here:
        IRMSinfoFrame irmsFrame = new IRMSinfoFrame();
        irmsFrame.setLocationRelativeTo(null);
        irmsFrame.setVisible(true);
    }//GEN-LAST:event_irmsButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
             
            public void run() {
                new MainFrame("default",false).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdminPnl;
    private javax.swing.JPanel AdminSuperPnl;
    private javax.swing.JPanel ButtonCards;
    private javax.swing.JButton ChangePassButton1;
    private javax.swing.JButton LogoutButton;
    private javax.swing.JPanel NoRole;
    private javax.swing.JButton ProfileButton;
    private javax.swing.JComboBox<String> ProfileComboBox;
    private javax.swing.JPanel ProfilePnl;
    private javax.swing.JButton SlideLeftButton;
    private javax.swing.JPanel TeacherPnl;
    private javax.swing.JPanel UserCards;
    private javax.swing.JButton addDepartmentButton;
    private javax.swing.JButton addProgramButton;
    private javax.swing.JButton addSchoolButton;
    private javax.swing.JButton addSyllabusButton;
    private javax.swing.JButton batchButton;
    private javax.swing.JButton customCLOButton;
    private javax.swing.JButton customCLOButtonTeacher;
    private javax.swing.JButton editDepartmentButton;
    private javax.swing.JButton editProgramButton;
    private javax.swing.JButton editSchoolButton;
    private javax.swing.JButton editSyllabusButton;
    private javax.swing.JButton employeeButton;
    private javax.swing.JButton irmsButton;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton markDistributionButton;
    private javax.swing.JButton markDistributionButtonTeacher;
    private javax.swing.JButton offeredCourseButton;
    private javax.swing.JButton resultButton;
    private javax.swing.JButton semesterButton;
    private javax.swing.JButton studentButton;
    private javax.swing.JButton syllabusSettingsButton;
    private javax.swing.JCheckBox themeCheckBox1;
    // End of variables declaration//GEN-END:variables
}

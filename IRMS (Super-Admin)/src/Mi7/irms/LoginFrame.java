/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Mi7.irms;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author himal
 */
public class LoginFrame extends javax.swing.JFrame {

    /**
     * Creates new form LoginFrame
     */
    Connection connect;
    PreparedStatement ps;
    File saveDir;
    String uni_id_const;
    boolean generated_once = false;
    
    void alert2bGeneration(int c,String msg1,String msg2,String msg3,String btext1,String btext2,String parent,boolean top){
        
        
        this.setEnabled(false);
        AlertFrame2B a2frm = new AlertFrame2B(c,msg1,msg2,msg3,btext1,btext2);
        a2frm.passLoginFrame(this, parent);
        a2frm.setLocationRelativeTo(null);
        a2frm.setAlwaysOnTop(top);
        a2frm.setVisible(true);
        
            
        
    }
    
    void alertGeneration(int c,String msg1,String msg2,String msg3){
        int x = getLocationOnScreen().x;   // location of frame
        int y=getLocationOnScreen().y;
        int w=getSize().width;           // size of frame
        int h=getSize().height;

        this.setEnabled(false);
        AlertFrame afrm = new AlertFrame(c,msg1,msg2,msg3);
        afrm.passLoginFrame(this, "LoginFrame");
        afrm.setLocation(x+w/4, y+h/4);
        afrm.setVisible(true);
    }
    
    
    
    
    void reConnection(){
  
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            generated_once = true;
            //ps=connect.createStatement();
        }catch(SQLException ex2){
            if(!generated_once){
               int code = ex2.getErrorCode();
               String msg = "Error Code: "+code;
               alert2bGeneration(2,"Server Connection Failed!!",msg,"Please,Retry Connection or Quit!","Retry","Quit","LoginFrame",true); 
            }
            System.out.println(ex2);
        }
    }
    
    String encryptPass(char[] passwordPlain) {
        String passwordEncrypt = null;

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

            passwordEncrypt = sb.toString();

            // Clear the password bytes immediately
            Arrays.fill(passwordBytes, (byte) 0);
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            System.out.println(e);
        } finally {
            // Clear the original Plain Password for security
            Arrays.fill(passwordPlain, '\0');
        }

        return passwordEncrypt;
    }
    
    /*String encryptPass(String password){
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] passByte = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< passByte.length ;i++){ 
                sb.append(Integer.toString((passByte[i] & 0xff) + 0x100, 16).substring(1));         //Encrypting default password
            }  
            password = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        
        
        return password;
    }*/
    
    public LoginFrame()  {
        initComponents();
        uni_id_const = "UV-0001";
        //idText.setBorder(new LineBorder(Color.RED,5,true));
        try{

            //Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/irms_db","irms_main","Mi7*sub-Pro-IRMS");
            generated_once = true;
            //stmt=connect.createStatement();
            
        }catch(SQLException e){
            int code = e.getErrorCode();
            String msg = "Error Code: "+code;
            alert2bGeneration(2,"Server Connection Failed!!",msg,"Please,Retry Connection or Quit!","Retry","Quit","LoginFrame",true);
   
        }
        
        
        try{
           
           saveDir=new File("account.txt");
           //System.out.println("Success!");
           
           if(saveDir.exists()&& saveDir.length()!=0L){
               FileReader fr = new FileReader(saveDir);
               Scanner scan = new Scanner(fr);              //scan save file to get the ID
               String temp[]= scan.nextLine().split(" ");
               scan.close();
               if(temp.length!=3){
                   return;
               }
               String id = temp[0].trim();
               String temp_profile = temp[2].trim();
               
               if(temp_profile.equals("0")){
                   temp_profile = "(emp.)";
               }else if(temp_profile.equals("1")){
                   temp_profile = "(stu.)";
               }else if(temp_profile.equals("2")){
                   temp_profile = "(admin)";
               }else{
                   temp_profile = "(ERROR!)";
               }
               
               if(!id.isEmpty() && id!="None!"){
                   saved_ID.setText(" "+id+" "+temp_profile+" ");      //set text of Saved account ID and enable
                   saveLoginButton.setEnabled(true);
                   removeAccountButton.setEnabled(true);
                   
               }
           }
           
        }catch(Exception e){
            alertGeneration(4,"Something went wrong with File read/write!!","","");
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
        java.awt.GridBagConstraints gridBagConstraints;

        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        saved_ID = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        saveAccountId = new javax.swing.JLabel();
        saveLoginButton = new javax.swing.JButton();
        removeAccountButton = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        profileChoose = new javax.swing.JComboBox<>();
        profileImg = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        idText = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        passwordText = new javax.swing.JPasswordField();
        jPanel6 = new javax.swing.JPanel();
        checkSave = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IRMS");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(100, 100));
        setPreferredSize(new java.awt.Dimension(906, 680));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(375, 620));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(375, 300));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setPreferredSize(new java.awt.Dimension(375, 70));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel15);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setPreferredSize(new java.awt.Dimension(375, 131));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setPreferredSize(new java.awt.Dimension(130, 80));
        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 30));

        saved_ID.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        saved_ID.setText("None!");
        saved_ID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(221, 197, 245), 2, true));
        jPanel17.add(saved_ID);

        jPanel14.add(jPanel17);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new java.awt.BorderLayout(0, 3));

        saveAccountId.setBackground(new java.awt.Color(255, 255, 255));
        saveAccountId.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        saveAccountId.setText("Saved Account");
        jPanel16.add(saveAccountId, java.awt.BorderLayout.SOUTH);

        saveLoginButton.setFont(new java.awt.Font("SansSerif", 0, 5)); // NOI18N
        saveLoginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/account64.png"))); // NOI18N
        saveLoginButton.setBorderPainted(false);
        saveLoginButton.setEnabled(false);
        saveLoginButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveLoginButton.setPreferredSize(new java.awt.Dimension(75, 85));
        saveLoginButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveLoginButtonActionPerformed(evt);
            }
        });
        jPanel16.add(saveLoginButton, java.awt.BorderLayout.NORTH);

        jPanel14.add(jPanel16);

        removeAccountButton.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        removeAccountButton.setText("Remove");
        removeAccountButton.setEnabled(false);
        removeAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAccountButtonActionPerformed(evt);
            }
        });
        jPanel14.add(removeAccountButton);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setPreferredSize(new java.awt.Dimension(25, 80));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        jPanel14.add(jPanel20);

        jPanel4.add(jPanel14);

        jPanel1.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(375, 300));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 7));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setPreferredSize(new java.awt.Dimension(250, 62));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setPreferredSize(new java.awt.Dimension(170, 50));
        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel6.setText("Profile");
        jPanel19.add(jLabel6);

        profileChoose.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        profileChoose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Teacher/Employee", "Super-Admin" }));
        profileChoose.setPreferredSize(new java.awt.Dimension(170, 30));
        profileChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileChooseActionPerformed(evt);
            }
        });
        jPanel19.add(profileChoose);

        jPanel18.add(jPanel19, java.awt.BorderLayout.WEST);

        profileImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/teacher_64.png"))); // NOI18N
        jPanel18.add(profileImg, java.awt.BorderLayout.EAST);

        jPanel5.add(jPanel18);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(250, 57));
        jPanel7.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setText("User ID");
        jPanel7.add(jLabel2, java.awt.BorderLayout.NORTH);

        idText.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        idText.setPreferredSize(new java.awt.Dimension(72, 26));
        jPanel7.add(idText, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(250, 57));
        jPanel8.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setText("Password");
        jPanel8.add(jLabel3, java.awt.BorderLayout.NORTH);

        passwordText.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        passwordText.setPreferredSize(new java.awt.Dimension(72, 26));
        jPanel8.add(passwordText, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel8);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(250, 30));
        jPanel6.setLayout(new java.awt.BorderLayout());

        checkSave.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        checkSave.setText("Save Account");
        jPanel6.add(checkSave, java.awt.BorderLayout.EAST);

        jPanel5.add(jPanel6);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setMinimumSize(new java.awt.Dimension(250, 50));
        jPanel9.setPreferredSize(new java.awt.Dimension(250, 34));
        jPanel9.setLayout(new java.awt.BorderLayout());

        loginButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        jPanel9.add(loginButton, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel9);

        jPanel1.add(jPanel5);

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setBackground(new java.awt.Color(221, 197, 245));
        jPanel2.setPreferredSize(new java.awt.Dimension(1155, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1266, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(525, 620));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(780, 310));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setPreferredSize(new java.awt.Dimension(1000000, 40));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000000, 1000000)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel13);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setPreferredSize(new java.awt.Dimension(525, 195));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/main0logo128.png"))); // NOI18N
        jLabel4.setText("IRMS");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 200;
        jPanel12.add(jLabel4, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        jLabel1.setText(" Welcome Back!");
        jLabel1.setPreferredSize(new java.awt.Dimension(170, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 7;
        jPanel12.add(jLabel1, gridBagConstraints);

        jPanel10.add(jPanel12);

        jPanel3.add(jPanel10, java.awt.BorderLayout.NORTH);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setPreferredSize(new java.awt.Dimension(780, 310));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/university256.png"))); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel11.add(jLabel5);

        jPanel3.add(jPanel11, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        String id = idText.getText();
        char [] passwordPlain = passwordText.getPassword();
        String passwordEncrypt = encryptPass(passwordPlain);
        Arrays.fill(passwordPlain, '\0');
        PreparedStatement ps;
        String checkTable="";
        String uni_id = uni_id_const;
        int profile = profileChoose.getSelectedIndex();
        if(profile ==1){
            profile = 2;
        }
        /*String regex = "(?i)[\\w\\W]*admin[\\w\\W]*";
        if(id.matches(regex)){
        checkTable = "admin";
        }else{
        return;//logintable
        }*/
        switch (profile) {
            case 0:
                checkTable = "employee_login";
                break;
            case 1:
                checkTable = "student_login";
                break;
            case 2:
                checkTable = "admin";
                break;
            default:
            
                break;
        }
        
        try{
            if(profile == 2){
                ps = connect.prepareStatement("SELECT * FROM `"+checkTable+"` WHERE ID = ? AND password = ?");
                ps.setString(1, id);
                ps.setString(2, passwordEncrypt);
                
            }else{
                ps = connect.prepareStatement("SELECT * FROM `"+checkTable+"` WHERE ID = ? AND password = ? AND university_id = ?");
                ps.setString(1, id);
                ps.setString(2, passwordEncrypt);
                ps.setString(3, uni_id);
            }
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                //dispose();
                if(profile ==2){
                    uni_id = rs.getString("university_id");
                }
                if(checkSave.isSelected()){
                    try{
                        String saveInfo = id+" "+passwordEncrypt+" "+profile+"\n";
                        FileWriter fw = new FileWriter(saveDir);
                        BufferedWriter bw = new BufferedWriter(fw);   //save account info
                        bw.write(saveInfo);
                        bw.close();
                    }catch(IOException ex1){
                        alertGeneration(4,"Something went wrong with File read/write!!","","");
                        System.out.println(ex1);
                    }
                }
                String token = id +" "+uni_id+" "+profile;
                MainFrame mfrm = new MainFrame(token,false);
                mfrm.setVisible(true);
                dispose();
                System.out.println("success "+profile);
            }else{
                //System.out.println("unsuccess");
                alertGeneration(2,"Password or ID didn't match!!","Try Again!","");
                return;
            }
        }catch(SQLException e){
            
            int code = e.getErrorCode();
            System.out.println(e);
            String msg = "Error code: "+code;
            alertGeneration(3,"Error in Server Connection!!",msg,"Try Again!");
            
            reConnection();
            return;
            
        }
        
        
    }//GEN-LAST:event_loginButtonActionPerformed

    private void saveLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveLoginButtonActionPerformed
        // TODO add your handling code here:
        if(saveDir.exists()){
            try{
                String [] temp;
                if(saveDir.length()==0L){
                    
                    alertGeneration( 1,"Something went Wrong with save account!!","Try removing it!","");
                    return;
                }
                
                FileReader fr = new FileReader(saveDir);
                Scanner scan = new Scanner(fr);
                temp = scan.nextLine().split(" ");    //read account info from file
                scan.close();
                
                
                if(temp.length!=3){
                    alertGeneration( 1,"Something went Wrong with save account!!","Try removing it!","");
                    return;
                }
                
                String id=temp[0];
                String password=temp[1];
                int profile = Integer.parseInt(temp[2]);
                String checkTable = "";
                String uni_id = uni_id_const ;
                PreparedStatement ps;
                switch (profile) {
                    case 0:
                        checkTable = "employee_login";
                        break;
                    case 1:
                        checkTable = "student_login";
                        break;
                    case 2:
                        checkTable = "admin";
                        break;
                    default:
            
                        break;
                }
                /*String regex = "(?i)[\\w\\W]*admin[\\w\\W]*";
             
                
                if(id.matches(regex)){
                    checkTable = "admin";
                }else{
                    return;//logintable
                }*/
                if(profile == 2){
                    ps = connect.prepareStatement("SELECT * FROM `"+checkTable+"` WHERE ID = ? AND password = ?");
                    ps.setString(1, id);
                    ps.setString(2, password);
                
                }else{
                    ps = connect.prepareStatement("SELECT * FROM `"+checkTable+"` WHERE ID = ? AND password = ? AND university_id = ?");
                    ps.setString(1, id);
                    ps.setString(2, password);
                    ps.setString(3, uni_id);
                }
                
                ResultSet rs = ps.executeQuery();   
                if(rs.next()){
                    if(profile ==2){
                        uni_id = rs.getString("university_id");
                    }
                    String token = id +" "+uni_id+" "+profile;
                    MainFrame mfrm = new MainFrame(token,false);
                    mfrm.setVisible(true);
                    dispose();
                    System.out.println("success "+profile);
                    
                }else{
                    alertGeneration( 1,"Something went Wrong with save account!!","Try removing it!","");
                    return;
                    
                }
            }catch(SQLException e){
                int code = e.getErrorCode();
                String msg = "Error Code: "+code;
                alertGeneration(3,"Error in Server Connection!!",msg,"Try Again!");
                reConnection();
                
                //System.out.println(e);
            }catch(IOException e){
                alertGeneration(4,"Something went wrong with File read/write!!","","");
                //System.out.println(e);
            }
        }
    }//GEN-LAST:event_saveLoginButtonActionPerformed

    private void removeAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAccountButtonActionPerformed
       // TODO add your handling code here:
        try{
            FileWriter fw = new FileWriter(saveDir);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
            bw.close();
            removeAccountButton.setEnabled(false);
            saveLoginButton.setEnabled(false);       //disable remove and save buttons
            saved_ID.setText(" None! ");
        }catch(IOException e){
            alertGeneration(4,"Something went wrong with File read/write!!","","");
            System.out.println(e);
        }
        
    }//GEN-LAST:event_removeAccountButtonActionPerformed

    private void profileChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileChooseActionPerformed
        // TODO add your handling code here:
        int temp = profileChoose.getSelectedIndex();
        if(temp == 0){
            profileImg.setIcon(new ImageIcon(getClass().getResource("/icon/teacher_64.png")));
        }else if(temp == 1){
            profileImg.setIcon(new ImageIcon(getClass().getResource("/icon/superadmin_64.png")));        
        }
        //System.out.println(temp);
    }//GEN-LAST:event_profileChooseActionPerformed

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
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkSave;
    private javax.swing.JTextField idText;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JComboBox<String> profileChoose;
    private javax.swing.JLabel profileImg;
    private javax.swing.JButton removeAccountButton;
    private javax.swing.JLabel saveAccountId;
    private javax.swing.JButton saveLoginButton;
    private javax.swing.JLabel saved_ID;
    // End of variables declaration//GEN-END:variables
}

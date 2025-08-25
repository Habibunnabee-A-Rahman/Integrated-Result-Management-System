/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Mi7.irms;
import javax.swing.*;
/**
 *
 * @author himal
 */
public class AlertFrame2B extends javax.swing.JFrame {

    /**
     * Creates new form ErrorFrame
     */
    String btext1;
    String btext2;
    boolean button1_pressed = false;
    MainFrame mfrmx;
    LoginFrame lfrmx;
    EnterUniversityFrame eufrmx;
    ViewTableFrame vtfrmx;
    EnterUniversityAdminFrame euafrmx;
    EditUniversityInfoFrame euifrmx;
    EditUniversityAdminFrame eua2frmx;
    AddSchoolFrame asfrmx;
    EditSchoolFrame esfrmx;
    AddDepartmentFrame adfrmx;
    EditDepartmentFrame edfrmx;
    AddProgramFrame apfrmx;
    EditProgramFrame epfrmx;
    AddSyllabusFrame asbfrmx;
    EditSyllabusFrame esbfrmx;
    SelectSyllabusFrame ssbfrmx;
    EnterSyllabusInfoFrame esbifrmx;
    EnterCourseInfoFrame ecrifrmx;
    EnterEvaluationSetFrame eevsfrmx;
    //ChangePassFrame cpfrmx;
    //AddChangeEmployee acefrmx;
    //AddChangeStudent acsfrmx;
    String parent;

    
    void passMainFrame(MainFrame mfrmx,String parent){
        
        this.mfrmx = mfrmx;
        this.parent = parent;
        
    }
    void passLoginFrame(LoginFrame lfrmx,String parent){
        
        this.lfrmx = lfrmx;
        this.parent = parent;
        
    }
    void passEnterUniversityFrame(EnterUniversityFrame eufrmx,String parent){
        this.eufrmx = eufrmx;
        this.parent = parent;
    }
    void passViewTableFrame(ViewTableFrame vtfrmx,String parent){
        this.vtfrmx = vtfrmx;
        this.parent = parent;
    }
    void passEnterUniversityAdminFrame(EnterUniversityAdminFrame euafrmx,String parent){
        this.euafrmx = euafrmx;
        this.parent = parent;
    }
    void passEditUniversityInfoFrame(EditUniversityInfoFrame euifrmx,String parent){
        this.euifrmx = euifrmx;
        this.parent = parent;
    }
    void passEditUniversityAdminFrame(EditUniversityAdminFrame eua2frmx,String parent){
        this.eua2frmx = eua2frmx;
        this.parent = parent;
    }
    void passAddSchoolFrame(AddSchoolFrame asfrmx,String parent){
        this.asfrmx = asfrmx;
        this.parent = parent;
    }
    void passEditSchoolFrame(EditSchoolFrame esfrmx, String parent){
        this.esfrmx = esfrmx;
        this.parent = parent;
    }
    void passAddDepartmentFrame(AddDepartmentFrame adfrmx, String parent){
        this.adfrmx = adfrmx;
        this.parent = parent;
    }
    void passEditDepartmentFrame(EditDepartmentFrame edfrmx, String parent){
        this.edfrmx = edfrmx;
        this.parent = parent;
    }
    void passAddProgramFrame(AddProgramFrame apfrmx, String parent){
        this.apfrmx = apfrmx;
        this.parent = parent;
    }
    void passEditProgramFrame(EditProgramFrame epfrmx,String parent){
        this.epfrmx = epfrmx;
        this.parent = parent;
    }
    void passAddSyllabusFrame(AddSyllabusFrame asbfrmx,String parent){
        this.asbfrmx = asbfrmx;
        this.parent = parent;
    }
    void passEditSyllabusFrame(EditSyllabusFrame esbfrmx,String parent){
        this.esbfrmx = esbfrmx;
        this.parent = parent;
    }
    void passSelectSyllabusFrame(SelectSyllabusFrame ssbfrmx,String parent){
        this.ssbfrmx = ssbfrmx;
        this.parent = parent;
    }
    void passEnterSyllabusInfoFrame(EnterSyllabusInfoFrame esbifrmx,String parent){
        this.esbifrmx = esbifrmx;
        this.parent = parent;
    }
    void passEnterCourseInfoFrame(EnterCourseInfoFrame ecrifrmx,String parent){
        this.ecrifrmx = ecrifrmx;
        this.parent = parent;
    }
    void passEnterEvaluationSetFrame(EnterEvaluationSetFrame eevsfrmx,String parent){
        this.eevsfrmx = eevsfrmx;
        this.parent = parent;
    }
    
    /*void passChangePassFrame(ChangePassFrame cpfrmx,String parent){
        
        this.cpfrmx = cpfrmx;
        this.parent = parent;
        
    }
    void passAddChangeEmployee(AddChangeEmployee acefrmx,String parent){
        this.acefrmx=acefrmx;
        this.parent = parent;
    }
    void passAddChangeStudent(AddChangeStudent acsfrmx,String parent){
        this.acsfrmx=acsfrmx;
        this.parent = parent;
    }*/
    
    
    public AlertFrame2B(int x,String msg1,String msg2,String msg3,String btext1,String btext2) {
        initComponents();
        
        if(x==1){
            
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/icon/reload_64.png")));
        }
        if(x==2){
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/icon/server_retry_64.png")));
        }
        if(x==3){
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/icon/warning_64.png")));
        }
        
        this.btext1 = btext1;
        this.btext2 = btext2;
        
        jLabel2.setText(msg1);
        jLabel3.setText(msg2);
        jLabel4.setText(msg3);
        jButton1.setText(btext1);
        jButton2.setText(btext2);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Error!");
        setMinimumSize(new java.awt.Dimension(417, 161));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel4.setPreferredSize(new java.awt.Dimension(470, 42));

        jButton1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton1.setText("Restart");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 26));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jPanel5.setPreferredSize(new java.awt.Dimension(130, 30));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel5);

        jButton2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton2.setText("Cancel");
        jButton2.setPreferredSize(new java.awt.Dimension(100, 26));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);

        getContentPane().add(jPanel4, java.awt.BorderLayout.SOUTH);

        jPanel1.setPreferredSize(new java.awt.Dimension(110, 124));

        jPanel6.setPreferredSize(new java.awt.Dimension(100, 20));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setPreferredSize(new java.awt.Dimension(350, 124));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel7.setPreferredSize(new java.awt.Dimension(300, 25));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel7);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setPreferredSize(new java.awt.Dimension(360, 22));
        jPanel2.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.setPreferredSize(new java.awt.Dimension(360, 22));
        jPanel2.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("jLabel2");
        jLabel4.setPreferredSize(new java.awt.Dimension(360, 22));
        jPanel2.add(jLabel4);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        
        if(btext1.toLowerCase().equals("restart") && parent.toLowerCase().equals("mainframe")){
            button1_pressed = true;
            MainFrame mfrm = new MainFrame(mfrmx.id,mfrmx.theme);
            mfrmx.dispose();
            mfrm.setVisible(true);
            System.out.println("restart");
            dispose();
        }else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("loginframe")){
            button1_pressed = true;
            lfrmx.reConnection();
            lfrmx.setEnabled(true);
            dispose();
        }else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("mainframe")){
            button1_pressed = true;
            if(!mfrmx.generated_once){
                MainFrame mfrm = new MainFrame(mfrmx.token,mfrmx.theme);
                mfrmx.log_out_pressed = true;
                mfrmx.dispose();
                mfrm.setLocationRelativeTo(null);
                mfrm.setVisible(true);
                dispose();
            }else{
                mfrmx.reConnection();
                mfrmx.setEnabled(true);
                dispose();
            }
            
        }else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("enteruniversityframe")){
            button1_pressed = true;
            EnterUniversityFrame eufrm = new EnterUniversityFrame();
            eufrmx.dispose();
            eufrm.setLocationRelativeTo(null);
            eufrm.setVisible(true);
            dispose();
        }else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("viewtableframe")){
            button1_pressed = true;
            ViewTableFrame vtfrm = new ViewTableFrame(vtfrmx.tables);
            vtfrmx.dispose();
            vtfrm.setLocationRelativeTo(null);
            vtfrm.setVisible(true);
            dispose();
        }else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("enteruniversityadminframe")){
            button1_pressed = true;
            EnterUniversityAdminFrame euafrm = new EnterUniversityAdminFrame();
            euafrmx.dispose();
            euafrm.setLocationRelativeTo(null);
            euafrm.setVisible(true);
            dispose();
        }else if(parent.toLowerCase().equals("edituniversityinfoframe")){
            button1_pressed = true;
            if(btext1.toLowerCase().equals("retry")){
                EditUniversityInfoFrame euifrm = new EditUniversityInfoFrame();
                euifrmx.dispose();
                euifrm.setLocationRelativeTo(null);
                euifrm.setVisible(true);
                dispose();
            }else if(btext1.toLowerCase().equals("proceed")){
                euifrmx.deleteUniversity(euifrmx.existing_id);
                euifrmx.setEnabled(true);
                euifrmx.setAlwaysOnTop(true);
                euifrmx.setAlwaysOnTop(false);
                dispose();
            }
            
        }else if(parent.toLowerCase().equals("edituniversityadminframe")){
            button1_pressed = true;
            if(btext1.toLowerCase().equals("retry")){
                EditUniversityAdminFrame eua2frm = new EditUniversityAdminFrame();
                eua2frmx.dispose();
                eua2frm.setLocationRelativeTo(null);
                eua2frm.setVisible(true);
                dispose();
            }else if(btext1.toLowerCase().equals("proceed")){
                eua2frmx.deleteAdmin(eua2frmx.existing_id);
                eua2frmx.setEnabled(true);
                eua2frmx.setAlwaysOnTop(true);
                eua2frmx.setAlwaysOnTop(false);
                dispose();
            }
            
        }else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("addschoolframe")){
            button1_pressed = true;
            AddSchoolFrame asfrm = new AddSchoolFrame(asfrmx.uni_id);
            asfrmx.dispose();
            asfrm.setLocationRelativeTo(null);
            asfrm.setVisible(true);
            dispose();
        }else if(parent.toLowerCase().equals("editschoolframe")){
            button1_pressed = true;
            if(btext1.toLowerCase().equals("retry")){
                EditSchoolFrame esfrm = new EditSchoolFrame(esfrmx.uni_id);
                esfrmx.dispose();
                esfrm.setLocationRelativeTo(null);
                esfrm.setVisible(true);
                dispose();
            }else if(btext1.toLowerCase().equals("proceed")){
                esfrmx.deleteSchool(esfrmx.existing_id);
                esfrmx.setEnabled(true);
                esfrmx.setAlwaysOnTop(true);
                esfrmx.setAlwaysOnTop(false);
                dispose();
            }
            
        }else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("adddepartmentframe")){
            button1_pressed = true;
            AddDepartmentFrame adfrm = new AddDepartmentFrame(adfrmx.uni_id);
            adfrmx.dispose();
            adfrm.setLocationRelativeTo(null);
            adfrm.setVisible(true);
            dispose();
        }else if(parent.toLowerCase().equals("editdepartmentframe")){
            button1_pressed = true;
            if(btext1.toLowerCase().equals("retry")){
                EditDepartmentFrame edfrm = new EditDepartmentFrame(edfrmx.T01_id);
                edfrmx.dispose();
                edfrm.setLocationRelativeTo(null);
                edfrm.setVisible(true);
                dispose();
            }else if(btext1.toLowerCase().equals("proceed")){
                edfrmx.deleteDepartment(edfrmx.existing_id);
                edfrmx.setEnabled(true);
                edfrmx.setAlwaysOnTop(true);
                edfrmx.setAlwaysOnTop(false);
                dispose();
            }
            
        }else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("addprogramframe")){
            button1_pressed = true;
            AddProgramFrame apfrm = new AddProgramFrame(apfrmx.T01_id);
            apfrmx.dispose();
            apfrm.setLocationRelativeTo(null);
            apfrm.setVisible(true);
            dispose();
        }else if(parent.toLowerCase().equals("editprogramframe")){
            button1_pressed = true;
            if(btext1.toLowerCase().equals("retry")){
                EditProgramFrame epfrm = new EditProgramFrame(epfrmx.T01_id);
                epfrmx.dispose();
                epfrm.setLocationRelativeTo(null);
                epfrm.setVisible(true);
                dispose();
            }else if(btext1.toLowerCase().equals("proceed")){
                epfrmx.deleteDepartment(epfrmx.existing_id);
                epfrmx.setEnabled(true);
                epfrmx.setAlwaysOnTop(true);
                epfrmx.setAlwaysOnTop(false);
                dispose();
            }
            
        }else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("addsyllabusframe")){
            button1_pressed = true;
            AddSyllabusFrame asbfrm = new AddSyllabusFrame(asbfrmx.T01_id);
            asbfrmx.dispose();
            asbfrm.setLocationRelativeTo(null);
            asbfrm.setVisible(true);
            dispose();
        }else if(parent.toLowerCase().equals("editsyllabusframe")){
            button1_pressed = true;
            if(btext1.toLowerCase().equals("retry")){
                EditSyllabusFrame esbfrm = new EditSyllabusFrame(esbfrmx.T01_id);
                esbfrmx.dispose();
                esbfrm.setLocationRelativeTo(null);
                esbfrm.setVisible(true);
                dispose();
            }else if(btext1.toLowerCase().equals("proceed")){
                esbfrmx.deleteDepartment(esbfrmx.existing_id);
                esbfrmx.setEnabled(true);
                esbfrmx.setAlwaysOnTop(true);
                esbfrmx.setAlwaysOnTop(false);
                dispose();
            }
            
        }else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("selectsyllabusframe")){
            button1_pressed = true;
            SelectSyllabusFrame ssbfrm = new SelectSyllabusFrame(ssbfrmx.T01_id,ssbfrmx.flag,ssbfrmx.token);
            ssbfrmx.dispose();
            ssbfrm.setLocationRelativeTo(null);
            ssbfrm.setVisible(true);
            dispose();
        }else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("entersyllabusinfoframe")){
            button1_pressed = true;
            EnterSyllabusInfoFrame esbifrm = new EnterSyllabusInfoFrame(esbifrmx.idT06,esbifrmx.idT01);
            esbifrmx.dispose();
            esbifrm.setLocationRelativeTo(null);
            esbifrm.setVisible(true);
            dispose();
        }else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("entercourseinfoframe")){
            button1_pressed = true;
            EnterCourseInfoFrame ecrifrm = new EnterCourseInfoFrame(ecrifrmx.idT06);
            ecrifrmx.dispose();
            ecrifrm.setLocationRelativeTo(null);
            ecrifrm.setVisible(true);
            dispose();
        }else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("enterevaluationsetframe")){
            button1_pressed = true;
            EnterEvaluationSetFrame eevsfrm = new EnterEvaluationSetFrame(eevsfrmx.idT06,eevsfrmx.idT01);
            eevsfrmx.dispose();
            eevsfrm.setLocationRelativeTo(null);
            eevsfrm.setVisible(true);
            dispose();
        }
        /*else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("changepassframe")){
            close = false;            
            ChangePassFrame cpfrm = new ChangePassFrame(cpfrmx.id,cpfrmx.mfrmx);

            int x = getLocationOnScreen().x;   // location of frame
            int y=getLocationOnScreen().y;
            int w=getSize().width;           // size of frame
            int h=getSize().height;
        
            cpfrm.setLocation(x+w/4, y+h/4-50);
            
            cpfrmx.dispose();
            cpfrm.setVisible(true);
                   
            dispose();

        }else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("addchangeemployee")){
            close = false;
            AddChangeEmployee acefrm = new AddChangeEmployee();
            acefrmx.dispose();
            acefrm.setVisible(true);
            dispose();
        }else if(btext1.toLowerCase().equals("retry") && parent.toLowerCase().equals("addchangestudent")){
            close = false;
            AddChangeStudent acsfrm = new AddChangeStudent();
            acsfrmx.dispose();
            acsfrm.setVisible(true);
            dispose();
        }*/
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(btext2.toLowerCase().equals("cancel") && parent.toLowerCase().equals("mainframe")){
            //mfrmx.setEnabled(true);
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("loginframe")){
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("mainframe")){
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("enteruniversityframe")){
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("viewtableframe")){
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("enteruniversityadminframe")){
            dispose();
        }else if((btext2.toLowerCase().equals("quit") || btext2.toLowerCase().equals("cancel")) && parent.toLowerCase().equals("edituniversityinfoframe")){
            dispose();
        }else if(btext2.toLowerCase().equals("cancel") && parent.toLowerCase().equals("edituniversityadminframe")){
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("edituniversityadminframe")){
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("addschoolframe")){
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("adddepartmentframe")){
            dispose();
        }else if((btext2.toLowerCase().equals("quit") || btext2.toLowerCase().equals("cancel")) && parent.toLowerCase().equals("editschoolframe")){
            dispose();
        }else if((btext2.toLowerCase().equals("quit") || btext2.toLowerCase().equals("cancel")) && parent.toLowerCase().equals("editdepartmentframe")){
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("addprogramframe")){
            dispose();
        }else if((btext2.toLowerCase().equals("quit") || btext2.toLowerCase().equals("cancel")) && parent.toLowerCase().equals("editprogramframe")){
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("addsyllabusframe")){
            dispose();
        }else if((btext2.toLowerCase().equals("quit") || btext2.toLowerCase().equals("cancel")) && parent.toLowerCase().equals("editsyllabusframe")){
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("selectsyllabusframe")){
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("entersyllabusinfoframe")){
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("entercourseinfoframe")){
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("enterevaluationsetframe")){
            dispose();
        }
        /*else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("changepassframe")){
            System.exit(0);
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("addchangeemployee")){
            System.exit(0);
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("addchangestudent")){
            System.exit(0);
        }*/
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        if( btext2.toLowerCase().equals("cancel") && parent.toLowerCase().equals("mainframe")){
            //mfrmx.setEnabled(true);
            //mfrmx.setAlwaysOnTop(true);
            //mfrmx.setAlwaysOnTop(false);
            
            
        }else if( btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("loginframe") && !button1_pressed ){
            System.exit(0);
        }else if( btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("mainframe") && !button1_pressed ){
            System.exit(0);
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("enteruniversityframe") && !button1_pressed ){
            eufrmx.dispose();
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("viewtableframe") && !button1_pressed ){
            vtfrmx.dispose();
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("enteruniversityadminframe") && !button1_pressed ){
            euafrmx.dispose();
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("edituniversityinfoframe") && !button1_pressed ){
            euifrmx.dispose();
            dispose();
        }else if(btext2.toLowerCase().equals("cancel") && parent.toLowerCase().equals("edituniversityinfoframe") && !button1_pressed ){
            euifrmx.setEnabled(true);
            euifrmx.setAlwaysOnTop(true);
            euifrmx.setAlwaysOnTop(false);
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("edituniversityadminframe") && !button1_pressed ){
            eua2frmx.dispose();
            dispose();
        }else if(btext2.toLowerCase().equals("cancel") && parent.toLowerCase().equals("edituniversityadminframe") && !button1_pressed ){
            eua2frmx.setEnabled(true);
            eua2frmx.setAlwaysOnTop(true);
            eua2frmx.setAlwaysOnTop(false);
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("addschoolframe") && !button1_pressed ){
            asfrmx.dispose();
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("editschoolframe") && !button1_pressed ){
            euifrmx.dispose();
            dispose();
        }else if(btext2.toLowerCase().equals("cancel") && parent.toLowerCase().equals("editschoolframe") && !button1_pressed ){
            esfrmx.setEnabled(true);
            esfrmx.setAlwaysOnTop(true);
            esfrmx.setAlwaysOnTop(false);
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("adddepartmentframe") && !button1_pressed ){
            adfrmx.dispose();
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("editdepartmentframe") && !button1_pressed ){
            edfrmx.dispose();
            dispose();
        }else if(btext2.toLowerCase().equals("cancel") && parent.toLowerCase().equals("editdepartmentframe") && !button1_pressed ){
            edfrmx.setEnabled(true);
            edfrmx.setAlwaysOnTop(true);
            edfrmx.setAlwaysOnTop(false);
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("addprogramframe") && !button1_pressed ){
            apfrmx.dispose();
            dispose();
        }else if(parent.toLowerCase().equals("editprogramframe") && !button1_pressed ){
            if(btext2.toLowerCase().equals("cancel")){
                epfrmx.setEnabled(true);
                epfrmx.setAlwaysOnTop(true);
                epfrmx.setAlwaysOnTop(false);
                dispose();
            }else if(btext2.toLowerCase().equals("quit")){
                epfrmx.dispose();
                dispose();
            }
            
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("addsyllabusframe") && !button1_pressed ){
            asbfrmx.dispose();
            dispose();
        }else if(parent.toLowerCase().equals("editsyllabusframe") && !button1_pressed ){
            if(btext2.toLowerCase().equals("cancel")){
                esbfrmx.setEnabled(true);
                esbfrmx.setAlwaysOnTop(true);
                esbfrmx.setAlwaysOnTop(false);
                dispose();
            }else if(btext2.toLowerCase().equals("quit")){
                esbfrmx.dispose();
                dispose();
            }
            
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("selectsyllabusframe") && !button1_pressed ){
            ssbfrmx.dispose();
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("entersyllabusinfoframe") && !button1_pressed ){
            esbifrmx.dispose();
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("entercourseinfoframe") && !button1_pressed ){
            ecrifrmx.dispose();
            dispose();
        }else if(btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("enterevaluationsetframe") && !button1_pressed ){
            eevsfrmx.dispose();
            dispose();
        }
        /*else if( btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("changepassframe") ){
            System.exit(0);
        }else if( btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("addchangeemployee") ){
            System.exit(0);
        }else if( btext2.toLowerCase().equals("quit") && parent.toLowerCase().equals("addchangestudent") ){
            System.exit(0);
        }*/
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
            java.util.logging.Logger.getLogger(AlertFrame2B.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlertFrame2B.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlertFrame2B.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlertFrame2B.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlertFrame2B(1,"default!","default!","default!","default!","default").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables
}

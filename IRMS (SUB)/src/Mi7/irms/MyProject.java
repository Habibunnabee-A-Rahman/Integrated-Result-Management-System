/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mi7.irms;
import com.formdev.flatlaf.*;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 *
 * @author himal
 */

    


public class MyProject {
    
    
    public static void main(String [] args){
        try{
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        }catch(Exception e){
            System.out.println(e);
        }
        LoginFrame frm = new LoginFrame();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                frm.setVisible(true);    
            }
        });
    
    }
}

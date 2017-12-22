/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.khoitao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author user
 */
public class JavaApplication5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        try {
//        UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
//    } catch (Exception e) {
//    }
            //setTheme(String themeName, String licenseKey, String logoString)
            com.jtattoo.plaf.smart.SmartLookAndFeel.setTheme("Gray", "INSERT YOUR LICENSE KEY HERE", "my company");
            
        try {
            // select the Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JavaApplication5.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(JavaApplication5.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JavaApplication5.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JavaApplication5.class.getName()).log(Level.SEVERE, null, ex);
        }
            
//        thuvien a=new thuvien();         
//        try {
//            UIManager.setLookAndFeel(
//                    UIManager.getCrossPlatformLookAndFeelClassName());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(thuvien.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(thuvien.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(thuvien.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(thuvien.class.getName()).log(Level.SEVERE, null, ex);
//        }
        truyen b=new truyen();
        b.setVisible(true);
//         khoitao a=new khoitao();
//          
//       try {
//            a.them();
//          a.taotable();
//        } catch (SQLException ex) {
//            Logger.getLogger(JavaApplication5.class.getName()).log(Level.SEVERE, null, ex);
//       }
    }
    
}

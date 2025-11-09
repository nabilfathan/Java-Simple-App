/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok2.proyekdppl;

/**
 *
 * @author user
 */


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

public class ProyekDppl {

    private static final Logger logger = Logger.getLogger(ProyekDppl.class.getName());

    public static void main(String[] args) {
        
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginPage frame = new LoginPage();
                frame.setLocationRelativeTo(null); 
                frame.setVisible(true);
            }
        });
    }
}
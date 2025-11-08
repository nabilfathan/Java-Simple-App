package com.kelompok2.proyekdppl;

// Import yang diperlukan untuk Look and Feel dan logging
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

public class ProyekDppl {

    // Logger untuk mencatat error jika Look and Feel gagal
    private static final Logger logger = Logger.getLogger(ProyekDppl.class.getName());

    public static void main(String[] args) {
        
        // --- Kode untuk set Look and Feel "Nimbus" ---
        // (Dipindahkan dari LoginPage.java)
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
        // --- Selesai Look and Feel ---

        
        /* Buat dan tampilkan form di thread yang benar */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginPage frame = new LoginPage();
                // setLocationRelativeTo(null) bagus untuk menengahkan jendela
                frame.setLocationRelativeTo(null); 
                frame.setVisible(true);
            }
        });
    }
}
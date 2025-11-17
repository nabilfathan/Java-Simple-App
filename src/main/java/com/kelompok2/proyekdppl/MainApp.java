package com.kelompok2.proyekdppl;
// File: MainApp.java

// File: MainApp.java (Versi Update Halaman Mahasiswa)
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

public class MainApp {
    
    // Core Swing
    private JFrame frame;
    private final CardLayout cardLayout;
    private final JPanel cardsPanel;
    private JPanel navPanelContainer;
    
    // Data
    private final DataManager dataManager;
    private User userLogin;

    // --- DEKLARASIKAN SEMUA PANEL DI SINI ---
    private PanelHomeMahasiswa panelHomeMhs;
    private PanelHomeAdmin panelHomeAdmin;
    private PanelKelolaRuangan panelKelolaRuangan;
    private PanelKelolaReservasi panelKelolaReservasi;
    
    // --- PANEL BARU MAHASISWA ---
    private PanelLihatRuangan panelLihatRuangan;
    private PanelReservasiMahasiswa panelReservasiMahasiswa;

    // --- PANEL BARU ADMIN ---
    private PanelLaporan panelLaporan;

    // Panel-panel Navigasi
    private JPanel navMahasiswa;
    private JPanel navAdmin;


    public MainApp() {
        // 1. Inisialisasi DataManager
        dataManager = new DataManager();
        
        // 2. Setup Frame Utama
        frame = new JFrame("SIRUKAN - Sistem Reservasi Ruangan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 850); 
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // 3. Panel Utama dengan CardLayout (di CENTER)
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        // 4. Buat panel-panel (kartu-kartu)
        PanelLogin panelLogin = new PanelLogin(this); 
        panelHomeMhs = new PanelHomeMahasiswa(this);
        panelHomeAdmin = new PanelHomeAdmin(this);
        panelKelolaRuangan = new PanelKelolaRuangan(this);
        panelKelolaReservasi = new PanelKelolaReservasi(this);
        
        // --- GANTI PLACEHOLDER DENGAN PANEL ASLI ---
        panelLihatRuangan = new PanelLihatRuangan(this);
        panelReservasiMahasiswa = new PanelReservasiMahasiswa(this);
        
        // Buat panel placeholder untuk halaman sisa
        panelLaporan = new PanelLaporan(this);


        // 5. Masukkan panel-panel ke CardLayout
        cardsPanel.add(panelLogin, "LOGIN");
        cardsPanel.add(panelHomeMhs, "HOME_MHS");
        cardsPanel.add(panelHomeAdmin, "HOME_ADMIN");
        cardsPanel.add(panelKelolaRuangan, "KELOLA_RUANGAN");
        cardsPanel.add(panelKelolaReservasi, "KELOLA_RESERVASI");
        cardsPanel.add(panelLaporan, "LAPORAN");
        
        // --- SAMBUNGKAN PANEL BARU ---
        cardsPanel.add(panelLihatRuangan, "LIHAT_RUANGAN_MHS");
        cardsPanel.add(panelReservasiMahasiswa, "RESERVASI_MHS");
        
        // 6. Buat Navigasi Bar (di SOUTH)
        createNavBars(); 
        navPanelContainer = new JPanel(new BorderLayout());
        frame.add(navPanelContainer, BorderLayout.SOUTH);
        navPanelContainer.setVisible(false); 

        // 7. Susun di Frame
        frame.add(cardsPanel, BorderLayout.CENTER);

        // 8. Logika Awal
        showPanel("LOGIN");
        frame.setVisible(true);
    }

    /**
     * Method helper untuk membuat 2 jenis navigasi bar
     */
    private void createNavBars() {
        // --- Navigasi Mahasiswa (Home, Ruangan, Reservasi) ---
        navMahasiswa = new JPanel(new GridLayout(1, 3));
        navMahasiswa.setBorder(new EmptyBorder(5, 0, 5, 0));
        JButton btnMhsHome = new JButton("Home");
        JButton btnMhsRuangan = new JButton("Ruangan");
        JButton btnMhsReservasi = new JButton("Reservasi");
        navMahasiswa.add(btnMhsHome);
        navMahasiswa.add(btnMhsRuangan);
        navMahasiswa.add(btnMhsReservasi);
        
        // Aksi ini sudah benar
        btnMhsHome.addActionListener(e -> showPanel("HOME_MHS"));
        btnMhsRuangan.addActionListener(e -> showPanel("LIHAT_RUANGAN_MHS"));
        btnMhsReservasi.addActionListener(e -> showPanel("RESERVASI_MHS"));

        // --- Navigasi Admin (Home, Kelola, Reservasi, Laporan) ---
        navAdmin = new JPanel(new GridLayout(1, 4));
        navAdmin.setBorder(new EmptyBorder(5, 0, 5, 0));
        JButton btnAdminHome = new JButton("Home");
        JButton btnAdminKelola = new JButton("Kelola");
        JButton btnAdminReservasi = new JButton("Reservasi");
        JButton btnAdminLaporan = new JButton("Laporan");
        navAdmin.add(btnAdminHome);
        navAdmin.add(btnAdminKelola);
        navAdmin.add(btnAdminReservasi);
        navAdmin.add(btnAdminLaporan);
        
        // Aksi ini sudah benar
        btnAdminHome.addActionListener(e -> showPanel("HOME_ADMIN"));
        btnAdminKelola.addActionListener(e -> showPanel("KELOLA_RUANGAN"));
        btnAdminReservasi.addActionListener(e -> showPanel("KELOLA_RESERVASI"));
        btnAdminLaporan.addActionListener(e -> showPanel("LAPORAN"));
    }

    /**
     * Method ini dipanggil oleh PanelLogin
     */
    public User doLogin(String nim, String password) {
        User user = dataManager.login(nim, password);
        if (user != null) {
            this.userLogin = user;
            
            navPanelContainer.removeAll();
            
            if ("Admin".equals(user.getRole())) {
                panelHomeAdmin.setUserName(user.getNama());
                navPanelContainer.add(navAdmin, BorderLayout.CENTER);
                showPanel("HOME_ADMIN");
            } else {
                panelHomeMhs.setUserName(user.getNama());
                navPanelContainer.add(navMahasiswa, BorderLayout.CENTER);
                showPanel("HOME_MHS");
            }
            
            navPanelContainer.setVisible(true);
            frame.revalidate();
            frame.repaint();
            
            return user;
        }
        return null;
    }
    
    public void showPanel(String panelName) {
        cardLayout.show(cardsPanel, panelName);
    }
    
    public DataManager getDataManager() {
        return dataManager;
    }
    
    public User getUserLogin() {
        return userLogin;
    }

    // --- MAIN METHOD ---
    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(MainApp::new);
    }
}
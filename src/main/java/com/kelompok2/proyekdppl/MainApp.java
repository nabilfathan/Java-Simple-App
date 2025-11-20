package com.kelompok2.proyekdppl;

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
    
    private JFrame frame;
    private final CardLayout cardLayout;
    private final JPanel cardsPanel;
    private JPanel navPanelContainer;

    private final DataManager dataManager;
    private User userLogin;

    private PanelHomeMahasiswa panelHomeMhs;
    private PanelHomeAdmin panelHomeAdmin;
    private PanelKelolaRuangan panelKelolaRuangan;
    private PanelKelolaReservasi panelKelolaReservasi;
    
    private PanelLihatRuangan panelLihatRuangan;
    private PanelReservasiMahasiswa panelReservasiMahasiswa;

    private PanelLaporan panelLaporan;

    
    private JPanel navMahasiswa;
    private JPanel navAdmin;


    public MainApp() {
        dataManager = new DataManager();
        
        frame = new JFrame("SIRUKAN - Sistem Reservasi Ruangan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 850); 
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        PanelLogin panelLogin = new PanelLogin(this); 
        panelHomeMhs = new PanelHomeMahasiswa(this);
        panelHomeAdmin = new PanelHomeAdmin(this);
        panelKelolaRuangan = new PanelKelolaRuangan(this);
        panelKelolaReservasi = new PanelKelolaReservasi(this);
        
        panelLihatRuangan = new PanelLihatRuangan(this);
        panelReservasiMahasiswa = new PanelReservasiMahasiswa(this);
        
        panelLaporan = new PanelLaporan(this);

        cardsPanel.add(panelLogin, "LOGIN");
        cardsPanel.add(panelHomeMhs, "HOME_MHS");
        cardsPanel.add(panelHomeAdmin, "HOME_ADMIN");
        cardsPanel.add(panelKelolaRuangan, "KELOLA_RUANGAN");
        cardsPanel.add(panelKelolaReservasi, "KELOLA_RESERVASI");
        cardsPanel.add(panelLaporan, "LAPORAN");
        
        cardsPanel.add(panelLihatRuangan, "LIHAT_RUANGAN_MHS");
        cardsPanel.add(panelReservasiMahasiswa, "RESERVASI_MHS");
        
        createNavBars(); 
        navPanelContainer = new JPanel(new BorderLayout());
        frame.add(navPanelContainer, BorderLayout.SOUTH);
        navPanelContainer.setVisible(false); 

        frame.add(cardsPanel, BorderLayout.CENTER);

        showPanel("LOGIN");
        frame.setVisible(true);
    }

    private void createNavBars() {
        navMahasiswa = new JPanel(new GridLayout(1, 3));
        navMahasiswa.setBorder(new EmptyBorder(5, 0, 5, 0));
        JButton btnMhsHome = new JButton("Home");
        JButton btnMhsRuangan = new JButton("Ruangan");
        JButton btnMhsReservasi = new JButton("Reservasi");
        navMahasiswa.add(btnMhsHome);
        navMahasiswa.add(btnMhsRuangan);
        navMahasiswa.add(btnMhsReservasi);
        
        btnMhsHome.addActionListener(e -> showPanel("HOME_MHS"));
        btnMhsRuangan.addActionListener(e -> showPanel("LIHAT_RUANGAN_MHS"));
        btnMhsReservasi.addActionListener(e -> showPanel("RESERVASI_MHS"));

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
        
        btnAdminHome.addActionListener(e -> showPanel("HOME_ADMIN"));
        btnAdminKelola.addActionListener(e -> showPanel("KELOLA_RUANGAN"));
        btnAdminReservasi.addActionListener(e -> showPanel("KELOLA_RESERVASI"));
        btnAdminLaporan.addActionListener(e -> showPanel("LAPORAN"));
    }

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

    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(MainApp::new);
    }
}
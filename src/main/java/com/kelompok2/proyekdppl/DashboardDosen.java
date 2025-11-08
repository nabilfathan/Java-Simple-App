package com.kelompok2.proyekdppl;

// Import semua yang kita butuhkan
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DashboardDosen extends javax.swing.JFrame {

    // Deklarasi semua komponen
    private JPanel mainPanel;
    private JPanel cardsPanel;
    private JPanel tablePanel;
    private JPanel navPanel;
    private JTable reservasiTable;

    /**
     * Creates new form DashboardDosen
     */
    public DashboardDosen() {
        // Panggil method yang akan kita buat
        initComponentsCustom(); 
        
        // Pengaturan dasar untuk window
        setTitle("Dashboard Dosen - SIRUKAN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600); // Samakan ukurannya dengan login
        setLocationRelativeTo(null); // Tampilkan di tengah layar
    }

    // Kita buat method init kita sendiri agar lebih rapi
    private void initComponentsCustom() {
        
        // --- 1. Main Panel (Container Utama) ---
        mainPanel = new JPanel(new BorderLayout(10, 10)); // Layout utama: BorderLayout
        mainPanel.setBackground(Color.WHITE);
        // Beri padding (jarak dari tepi)
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        
        // --- 2. Title "Good Morning..." ---
        JLabel titleLabel = new JLabel("Good Morning...");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        // Taruh di bagian ATAS (NORTH)
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        
        // --- 3. Panel Tengah (untuk Cards dan Table) ---
        JPanel centerContent = new JPanel(new BorderLayout(10, 10));
        centerContent.setBackground(Color.WHITE);

        // --- 3a. Panel untuk Cards (Total Reservasi, dll) ---
        cardsPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // 1 baris, 2 kolom, gap 10px
        cardsPanel.setBackground(Color.WHITE);
        
        // Kita panggil method helper untuk buat card
        JPanel card1 = createInfoCard("Total Reservasi", "5");
        JPanel card2 = createInfoCard("Menunggu Persetujuan", "0");
        
        cardsPanel.add(card1);
        cardsPanel.add(card2);
        
        // Taruh panel cards di bagian ATAS panel tengah
        centerContent.add(cardsPanel, BorderLayout.NORTH);

        
        // --- 3b. Panel untuk Table (Reservasi Terbaru) ---
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        // Beri judul di bordernya
        tablePanel.setBorder(BorderFactory.createTitledBorder("Reservasi Terbaru"));

        // Data untuk tabel (contoh)
        String[] columnNames = {"Tanggal", "Waktu", "Ruangan", "Status"};
        Object[][] data = {
            {"10 April 2024", "09.00-11.00", "C304", "Disetujui"},
            {"10 April 2024", "09.00-11.00", "C304", "Disetujui"},
            {"10 April 2024", "09.00-11.00", "C304", "Disetujui"},
            {"10 April 2024", "09.00-11.00", "C304", "Disetujui"},
            {"10 April 2024", "09.00-11.00", "C304", "Disetujui"}
        };
        
        // Buat model tabel (agar datanya bisa diatur)
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            // Override method ini agar user tidak bisa mengedit isi tabel
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        reservasiTable = new JTable(tableModel);
        
        // Masukkan tabel ke dalam JScrollPane (agar bisa di-scroll)
        JScrollPane scrollPane = new JScrollPane(reservasiTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Taruh panel table di bagian TENGAH panel tengah
        centerContent.add(tablePanel, BorderLayout.CENTER);
        
        
        // Taruh panel tengah (centerContent) ke mainPanel
        mainPanel.add(centerContent, BorderLayout.CENTER);

        
        // --- 4. Panel Navigasi Bawah ---
        navPanel = new JPanel(new GridLayout(1, 3, 10, 10)); // 1 baris, 3 kolom
        navPanel.setBackground(Color.WHITE);
        
        JButton homeButton = new JButton("Home");
        JButton ruanganButton = new JButton("Ruangan");
        JButton reservasiButton = new JButton("Reservasi");
        
        navPanel.add(homeButton);
        navPanel.add(ruanganButton);
        navPanel.add(reservasiButton);
        
        // Taruh panel navigasi di bagian BAWAH (SOUTH)
        mainPanel.add(navPanel, BorderLayout.SOUTH);
        
        
        // --- Terakhir ---
        // Tambahkan mainPanel ke content pane dari JFrame
        this.add(mainPanel);
    }

    
    private JPanel createInfoCard(String title, String value) {
        JPanel card = new JPanel();
        // Set layout agar komponen ditumpuk ke bawah (Y_AXIS)
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        // Beri border abu-abu simpel
        card.setBorder(BorderFactory.createEtchedBorder());
        card.setPreferredSize(new Dimension(150, 100)); // Atur ukuran

        // Label untuk Judul (cth: "Total Reservasi")
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Rata tengah

        // Label untuk Angka (cth: "5")
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 36));
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Rata tengah

        // Tambahkan komponen ke panel card
        card.add(Box.createVerticalStrut(15)); // Beri jarak kosong atas
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10)); // Beri jarak kosong tengah
        card.add(valueLabel);

        return card;
    }

    
    @SuppressWarnings("unchecked")
                 
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        
    // </editor-fold> 
}
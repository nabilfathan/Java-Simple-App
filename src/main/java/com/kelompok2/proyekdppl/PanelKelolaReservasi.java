package com.kelompok2.proyekdppl;

// File: PanelKelolaReservasi.java
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PanelKelolaReservasi extends JPanel {

    private MainApp mainApp;
    private JPanel listPanel; // Panel yang menampung kartu-kartu
    private String filterStatus = "Menunggu"; // Filter awal

    public PanelKelolaReservasi(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.decode("#f0f2f5"));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        
        // 1. Header (Judul + Filter)
        add(createHeaderPanel(), BorderLayout.NORTH);
        
        // 2. List (dibungkus JScrollPane)
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(new TitledBorder(filterStatus));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollPane, BorderLayout.CENTER);
        
        loadDataReservasi();
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
        headerPanel.setOpaque(false);
        JLabel title = new JLabel("Kelola Reservasi");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        // Panel Filter (Search + Buttons)
        JPanel filterPanel = new JPanel(new BorderLayout(10, 10));
        filterPanel.setOpaque(false);
        
        JTextField searchField = new JTextField("Cari reservasi...");
        searchField.putClientProperty("JComponent.roundRect", true);
        
        // Filter Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        buttonPanel.setOpaque(false);
        ButtonGroup filterGroup = new ButtonGroup();
        
        JToggleButton btnSemua = new JToggleButton("Semua");
        JToggleButton btnMenunggu = new JToggleButton("Menunggu", true);
        JToggleButton btnDisetujui = new JToggleButton("Disetujui");
        
        filterGroup.add(btnSemua);
        filterGroup.add(btnMenunggu);
        filterGroup.add(btnDisetujui);
        
        buttonPanel.add(btnSemua);
        buttonPanel.add(btnMenunggu);
        buttonPanel.add(btnDisetujui);
        
        // Aksi untuk filter
        btnSemua.addActionListener(e -> { filterStatus = "Semua"; loadDataReservasi(); });
        btnMenunggu.addActionListener(e -> { filterStatus = "Menunggu"; loadDataReservasi(); });
        btnDisetujui.addActionListener(e -> { filterStatus = "Disetujui"; loadDataReservasi(); });

        filterPanel.add(searchField, BorderLayout.NORTH);
        filterPanel.add(buttonPanel, BorderLayout.CENTER);
        
        headerPanel.add(title, BorderLayout.NORTH);
        headerPanel.add(filterPanel, BorderLayout.CENTER);
        
        return headerPanel;
    }
    
    private void loadDataReservasi() {
        // Bersihkan list
        listPanel.removeAll();
        
        List<Reservasi> reservasiList = mainApp.getDataManager().getAllReservasi();
        
        // Filter berdasarkan status
        List<Reservasi> filteredList = reservasiList.stream()
            .filter(r -> filterStatus.equals("Semua") || r.getStatus().equals(filterStatus))
            .collect(Collectors.toList());

        // Update judul border
        JScrollPane scrollPane = (JScrollPane) listPanel.getParent().getParent();
        scrollPane.setBorder(new TitledBorder(filterStatus + " (" + filteredList.size() + ")"));

        // Buat kartu untuk setiap item
        for (Reservasi r : filteredList) {
            listPanel.add(createReservasiCard(r));
            listPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Jarak antar kartu
        }
        
        listPanel.revalidate();
        listPanel.repaint();
    }
    
    private JPanel createReservasiCard(Reservasi r) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#cccccc"), 1, true),
            new EmptyBorder(10, 10, 10, 10)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130)); // Batasi tinggi
        
        // Cari nama user
        User u = mainApp.getDataManager().getUserByNIM(r.getNimPemesan());
        String nama = (u != null) ? u.getNama() : "User Tidak Ditemukan";
        
        // Panel Info (Kiri)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        
        JLabel namaLabel = new JLabel(nama + " (" + r.getNimPemesan() + ")");
        namaLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        
        JLabel keperluanLabel = new JLabel(r.getKeperluan());
        keperluanLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        JLabel jumlahLabel = new JLabel(r.getJumlahOrang() + " Orang");
        jumlahLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        
        infoPanel.add(namaLabel);
        infoPanel.add(keperluanLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(jumlahLabel);
        
        // Panel Waktu (Kanan)
        JPanel waktuPanel = new JPanel();
        waktuPanel.setLayout(new BoxLayout(waktuPanel, BoxLayout.Y_AXIS));
        waktuPanel.setOpaque(false);
        
        waktuPanel.add(new JLabel(r.getKodeRuang()));
        waktuPanel.add(new JLabel(r.getTanggal()));
        waktuPanel.add(new JLabel(r.getJamMulai() + " - " + r.getJamSelesai()));
        
        // Panel Tombol (Bawah)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        buttonPanel.setOpaque(false);
        JButton btnSetuju = new JButton("Setujui");
        JButton btnTolak = new JButton("Tolak");
        JButton btnDetail = new JButton("Lihat Detail");
        
        buttonPanel.add(btnSetuju);
        buttonPanel.add(btnTolak);
        buttonPanel.add(btnDetail);
        
        // Sembunyikan tombol jika status bukan "Menunggu"
        if (!r.getStatus().equals("Menunggu")) {
            btnSetuju.setVisible(false);
            btnTolak.setVisible(false);
        }
        
        // TODO: Tambahkan Aksi
        btnSetuju.addActionListener(e -> {
            // Logika setujui
            r.setStatus("Disetujui"); // (Harusnya update di JSON)
            JOptionPane.showMessageDialog(this, "Reservasi " + r.getIdReservasi() + " disetujui!");
            loadDataReservasi(); // Refresh list
        });
        
        card.add(infoPanel, BorderLayout.NORTH);
        card.add(waktuPanel, BorderLayout.EAST);
        card.add(buttonPanel, BorderLayout.SOUTH);
        
        return card;
    }
}
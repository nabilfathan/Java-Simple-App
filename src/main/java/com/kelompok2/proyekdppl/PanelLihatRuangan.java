package com.kelompok2.proyekdppl;

// File: PanelLihatRuangan.java
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PanelLihatRuangan extends JPanel {

    private MainApp mainApp;
    private JPanel resultsPanel; // Panel untuk menampilkan grid hasil

    public PanelLihatRuangan(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.decode("#f0f2f5"));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // 1. Panel Pencarian (Form)
        add(createSearchPanel(), BorderLayout.NORTH);

        // 2. Panel Hasil (Grid)
        add(createResultsPanel(), BorderLayout.CENTER);
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);
        panel.setBorder(new TitledBorder("Cari Ruangan Tersedia"));

        // Panel untuk form fields
        JPanel formFieldsPanel = new JPanel();
        formFieldsPanel.setLayout(new BoxLayout(formFieldsPanel, BoxLayout.Y_AXIS));
        formFieldsPanel.setOpaque(false);

        // Baris 1: Fakultas
        formFieldsPanel.add(new JLabel("Fakultas:"));
        formFieldsPanel.add(new JComboBox<>(new String[]{"Pilih Fakultas", "Teknik", "MIPA", "Hukum"}));
        formFieldsPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Baris 2: Tanggal & Kapasitas
        JPanel row2 = new JPanel(new GridLayout(1, 2, 10, 0));
        row2.setOpaque(false);
        row2.add(new LabeledTextField("Tanggal (dd/mm/yy):", new JTextField()));
        row2.add(new LabeledTextField("Kapasitas:", new JTextField("50")));
        formFieldsPanel.add(row2);
        formFieldsPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Baris 3: Jam
        JPanel row3 = new JPanel(new GridLayout(1, 2, 10, 0));
        row3.setOpaque(false);
        row3.add(new LabeledTextField("Jam Mulai (HH:MM):", new JTextField("08:00")));
        row3.add(new LabeledTextField("Hingga (HH:MM):", new JTextField("10:30")));
        formFieldsPanel.add(row3);
        formFieldsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Baris 4: Tipe
        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row4.setOpaque(false);
        row4.add(new JCheckBox("Kelas", true));
        row4.add(new JCheckBox("Laboratorium"));
        formFieldsPanel.add(row4);

        panel.add(formFieldsPanel, BorderLayout.CENTER);

        // Tombol Cari
        JButton cariButton = new JButton("Cari Ruangan");
        cariButton.putClientProperty("JButton.buttonType", "roundRect");
        cariButton.setBackground(Color.decode("#4285F4"));
        cariButton.setForeground(Color.WHITE);
        panel.add(cariButton, BorderLayout.SOUTH);

        // Aksi tombol
        cariButton.addActionListener(e -> {
            // Panggil method untuk update hasil
            updateSearchResults();
        });

        return panel;
    }

    private JScrollPane createResultsPanel() {
        resultsPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // 0 baris, 3 kolom
        resultsPanel.setOpaque(false);
        
        // Awalnya kosong
        resultsPanel.add(new JLabel("Silakan lakukan pencarian..."));
        
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(new TitledBorder("Hasil Pencarian"));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private void updateSearchResults() {
        // Hapus hasil lama
        resultsPanel.removeAll();
        
        // 1. Ambil data dari form (masih bohongan)
        // ...

        // 2. Ambil data dari DataManager (kita pakai semua ruangan dulu)
        List<Ruang> ruangan = mainApp.getDataManager().getAllRuangan();
        
        if (ruangan.isEmpty()) {
            resultsPanel.add(new JLabel("Tidak ada ruangan ditemukan."));
        } else {
            // 3. Tampilkan di grid (sesuai desain Lihat Ruangan 3.png)
            for (Ruang r : ruangan) {
                if (r.getStatus().equals("Aktif")) { // Hanya tampilkan yg aktif
                    resultsPanel.add(createRoomCard(r.getKodeRuang()));
                }
            }
        }
        
        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    // Helper untuk membuat 1 kartu ruangan
    private JPanel createRoomCard(String namaRuang) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        card.setPreferredSize(new Dimension(120, 120));
        
        // Placeholder untuk gambar
        JLabel imageLabel = new JLabel("Foto Ruangan", SwingConstants.CENTER);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.GRAY);
        imageLabel.setForeground(Color.WHITE);
        
        JLabel nameLabel = new JLabel(namaRuang, SwingConstants.CENTER);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        nameLabel.setOpaque(true);
        nameLabel.setBackground(new Color(255, 255, 255, 200)); // Latar semi-transparan
        
        card.add(imageLabel, BorderLayout.CENTER);
        card.add(nameLabel, BorderLayout.SOUTH);
        return card;
    }

    // Class helper kecil untuk form
    private class LabeledTextField extends JPanel {
        LabeledTextField(String label, JComponent component) {
            setLayout(new BorderLayout(0, 3));
            setOpaque(false);
            add(new JLabel(label), BorderLayout.NORTH);
            add(component, BorderLayout.CENTER);
        }
    }
}
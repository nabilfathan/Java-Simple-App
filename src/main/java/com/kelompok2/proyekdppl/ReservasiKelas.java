/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok2.proyekdppl;

/**
 *
 * @author user
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReservasiKelas extends javax.swing.JFrame {

    private JPanel mainPanel;
    private JPanel navPanel;
    private JButton homeButton, ruanganButton, reservasiButton, buatReservasiButton;
    private JComboBox<String> fakultasComboBox, kelasComboBox;
    private JTextField kapasitasField, judulKegiatanField, deskripsiField;
    private JCheckBox setujuCheckBox;

    public ReservasiKelas() {
        initComponentsCustom();
        
        setTitle("Reservasi Kelas - SIRUKAN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 750); 
        setLocationRelativeTo(null);
    }

    private void initComponentsCustom() {
        
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        //Header 
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0)); 

        JLabel titleLabel = new JLabel("SIRUKAN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102));
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(8)); 
        
        JLabel subtitleLabel = new JLabel("Reservasi Kelas");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        subtitleLabel.setForeground(Color.BLACK);
        subtitleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        headerPanel.add(subtitleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        //Konteen
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setMaximumSize(new Dimension(350, Integer.MAX_VALUE));

        //Fakultas
        JLabel fakultasLabel = new JLabel("Fakultas");
        fakultasLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fakultasLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        formPanel.add(fakultasLabel);
        formPanel.add(Box.createVerticalStrut(4)); 

        String[] fakultasOptions = {"Pilih", "Fakultas Teknik", "Fakultas Ekonomi", "Fakultas Hukum", "Fakultas Kedokteran"};
        fakultasComboBox = new JComboBox<>(fakultasOptions);
        fakultasComboBox.setMaximumSize(new Dimension(350, 35));
        fakultasComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        fakultasComboBox.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
        formPanel.add(fakultasComboBox);
        formPanel.add(Box.createVerticalStrut(12)); // Dikurangi

        //Tanggal
        JLabel tanggalLabel = new JLabel("Tanggal");
        tanggalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tanggalLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        formPanel.add(tanggalLabel);
        formPanel.add(Box.createVerticalStrut(4)); 

        JTextField tanggalField = new JTextField("dd/mm/yy");
        tanggalField.setMaximumSize(new Dimension(350, 35));
        tanggalField.setFont(new Font("Arial", Font.PLAIN, 14));
        tanggalField.setAlignmentX(JTextField.LEFT_ALIGNMENT);
        tanggalField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(tanggalField);
        formPanel.add(Box.createVerticalStrut(12)); 

        //Waktu 
        JLabel waktuLabel = new JLabel("Waktu");
        waktuLabel.setFont(new Font("Arial", Font.BOLD, 14));
        waktuLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        formPanel.add(waktuLabel);
        formPanel.add(Box.createVerticalStrut(4));

        JPanel waktuPanel = new JPanel();
        waktuPanel.setLayout(new BoxLayout(waktuPanel, BoxLayout.X_AXIS));
        waktuPanel.setBackground(Color.WHITE);
        waktuPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        waktuPanel.setMaximumSize(new Dimension(350, 35));

        String[] waktuOptions = {"07.00", "08.00", "09.00", "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00"};
        JComboBox<String> waktuMulaiComboBox = new JComboBox<>(waktuOptions);
        waktuMulaiComboBox.setPreferredSize(new Dimension(160, 35));
        waktuMulaiComboBox.setMaximumSize(new Dimension(160, 35));
        waktuMulaiComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        waktuMulaiComboBox.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
        
        JLabel hinggaLabel = new JLabel(" hingga ");
        hinggaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        hinggaLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JComboBox<String> waktuSelesaiComboBox = new JComboBox<>(waktuOptions);
        waktuSelesaiComboBox.setPreferredSize(new Dimension(160, 35));
        waktuSelesaiComboBox.setMaximumSize(new Dimension(160, 35));
        waktuSelesaiComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        waktuSelesaiComboBox.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
        waktuSelesaiComboBox.setSelectedIndex(2);

        waktuPanel.add(waktuMulaiComboBox);
        waktuPanel.add(Box.createHorizontalStrut(5)); 
        waktuPanel.add(hinggaLabel);
        waktuPanel.add(Box.createHorizontalStrut(5));
        waktuPanel.add(waktuSelesaiComboBox);
        
        formPanel.add(waktuPanel);
        formPanel.add(Box.createVerticalStrut(12)); 
        
        // Kapasitas
        JLabel kapasitasLabel = new JLabel("Kapasitas");
        kapasitasLabel.setFont(new Font("Arial", Font.BOLD, 14));
        kapasitasLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        formPanel.add(kapasitasLabel);
        formPanel.add(Box.createVerticalStrut(4)); 

        kapasitasField = new JTextField("50");
        kapasitasField.setMaximumSize(new Dimension(350, 35));
        kapasitasField.setFont(new Font("Arial", Font.PLAIN, 14));
        kapasitasField.setAlignmentX(JTextField.LEFT_ALIGNMENT);
        kapasitasField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(kapasitasField);
        formPanel.add(Box.createVerticalStrut(12)); 

        //Pilih klas
        JLabel kelasLabel = new JLabel("Pilih Kelas");
        kelasLabel.setFont(new Font("Arial", Font.BOLD, 14));
        kelasLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        formPanel.add(kelasLabel);
        formPanel.add(Box.createVerticalStrut(4));

        String[] kelasOptions = {"Pilih kelas", "C-305", "C-306", "C-307", "C-311"};
        kelasComboBox = new JComboBox<>(kelasOptions);
        kelasComboBox.setMaximumSize(new Dimension(350, 35));
        kelasComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        kelasComboBox.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
        formPanel.add(kelasComboBox);
        formPanel.add(Box.createVerticalStrut(12)); 

        //Judul kegiatan
        JLabel judulLabel = new JLabel("Judul Kegiatan");
        judulLabel.setFont(new Font("Arial", Font.BOLD, 14));
        judulLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        formPanel.add(judulLabel);
        formPanel.add(Box.createVerticalStrut(4)); 

        judulKegiatanField = new JTextField();
        judulKegiatanField.setMaximumSize(new Dimension(350, 35));
        judulKegiatanField.setFont(new Font("Arial", Font.PLAIN, 14));
        judulKegiatanField.setAlignmentX(JTextField.LEFT_ALIGNMENT);
        judulKegiatanField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(judulKegiatanField);
        formPanel.add(Box.createVerticalStrut(12)); 

        //Deskripsi
        JLabel deskripsiLabel = new JLabel("Deskripsi");
        deskripsiLabel.setFont(new Font("Arial", Font.BOLD, 14));
        deskripsiLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        formPanel.add(deskripsiLabel);
        formPanel.add(Box.createVerticalStrut(4)); 

        deskripsiField = new JTextField();
        deskripsiField.setMaximumSize(new Dimension(350, 50)); 
        deskripsiField.setFont(new Font("Arial", Font.PLAIN, 14));
        deskripsiField.setAlignmentX(JTextField.LEFT_ALIGNMENT);
        deskripsiField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(deskripsiField);
        formPanel.add(Box.createVerticalStrut(15)); 

        //Persetujuan
        setujuCheckBox = new JCheckBox("Saya menyetujui seluruh aturan reservasi");
        setujuCheckBox.setFont(new Font("Arial", Font.PLAIN, 12));
        setujuCheckBox.setBackground(Color.WHITE);
        setujuCheckBox.setAlignmentX(JCheckBox.LEFT_ALIGNMENT);
        formPanel.add(setujuCheckBox);
        formPanel.add(Box.createVerticalStrut(15)); 

        buatReservasiButton = new JButton("Buat Reservasi");
        buatReservasiButton.setFont(new Font("Arial", Font.BOLD, 16));
        buatReservasiButton.setBackground(new Color(0, 51, 102));
        buatReservasiButton.setForeground(Color.WHITE);
        buatReservasiButton.setFocusPainted(false);
        buatReservasiButton.setBorderPainted(false);
        buatReservasiButton.setPreferredSize(new Dimension(350, 45));
        buatReservasiButton.setMaximumSize(new Dimension(350, 45));
        buatReservasiButton.setAlignmentX(JButton.LEFT_ALIGNMENT);
        
        buatReservasiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (setujuCheckBox.isSelected()) {
                    javax.swing.JOptionPane.showMessageDialog(ReservasiKelas.this, 
                        "Reservasi anda telah diajukan, silakan tunggu email konfirmasi persetujuan",
                        "Reservasi Berhasil",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(ReservasiKelas.this, 
                        "Harap setujui aturan reservasi terlebih dahulu!",
                        "Peringatan",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        formPanel.add(buatReservasiButton);

        contentPanel.add(formPanel);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        navPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        navPanel.setBackground(Color.WHITE);
        navPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        homeButton = new JButton("Home");
        ruanganButton = new JButton("Ruangan");
        reservasiButton = new JButton("Reservasi");
        
        homeButton.setBackground(Color.LIGHT_GRAY);
        homeButton.setForeground(Color.BLACK);
        ruanganButton.setBackground(Color.LIGHT_GRAY);
        ruanganButton.setForeground(Color.BLACK);
        reservasiButton.setBackground(new Color(0, 51, 102));
        reservasiButton.setForeground(Color.WHITE);
        
        homeButton.setFont(new Font("Arial", Font.BOLD, 14));
        ruanganButton.setFont(new Font("Arial", Font.BOLD, 14));
        reservasiButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        homeButton.setFocusPainted(false);
        ruanganButton.setFocusPainted(false);
        reservasiButton.setFocusPainted(false);
        homeButton.setBorderPainted(false);
        ruanganButton.setBorderPainted(false);
        reservasiButton.setBorderPainted(false);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DashboardDosen().setVisible(true);
                dispose();
            }
        });
        
        ruanganButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LihatRuangan2().setVisible(true);
                dispose();
            }
        });

        navPanel.add(homeButton);
        navPanel.add(ruanganButton);
        navPanel.add(reservasiButton);
        mainPanel.add(navPanel, BorderLayout.SOUTH);
        
        this.add(mainPanel);
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
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservasiKelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReservasiKelas().setVisible(true);
            }
        });
    }                 
}
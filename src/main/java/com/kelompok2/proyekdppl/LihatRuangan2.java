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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LihatRuangan2 extends javax.swing.JFrame {

    private JPanel mainPanel;
    private JPanel navPanel;
    private JButton homeButton, ruanganButton, reservasiButton, cariButton;
    private JComboBox<String> fakultasComboBox;
    private JTextField tanggalField, kapasitasField;
    private JButton kelasButton, laboratoriumButton;

    public LihatRuangan2() {
        initComponentsCustom();
        
        setTitle("Filter Ruangan - SIRUKAN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
    }

    private void initComponentsCustom() {
        
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 20, 0));

        JLabel titleLabel = new JLabel("SIRUKAN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102));
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        
        JLabel subtitleLabel = new JLabel("Lihat Ruangan");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        subtitleLabel.setForeground(Color.BLACK);
        subtitleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        headerPanel.add(subtitleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setBackground(Color.WHITE);
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setMaximumSize(new Dimension(320, 500));

        JLabel fakultasLabel = new JLabel("Fakultas");
        fakultasLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fakultasLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        formPanel.add(fakultasLabel);
        formPanel.add(Box.createVerticalStrut(5));

        String[] fakultasOptions = {"Pilih", "Fakultas Teknik", "Fakultas Ekonomi", "Fakultas Hukum", "Fakultas Kedokteran"};
        fakultasComboBox = new JComboBox<>(fakultasOptions);
        fakultasComboBox.setMaximumSize(new Dimension(280, 35));
        fakultasComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        fakultasComboBox.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        formPanel.add(fakultasComboBox);
        formPanel.add(Box.createVerticalStrut(15));

        JLabel tanggalLabel = new JLabel("Tanggal");
        tanggalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tanggalLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        formPanel.add(tanggalLabel);
        formPanel.add(Box.createVerticalStrut(5));

        tanggalField = new JTextField("dd/mm/yy");
        tanggalField.setMaximumSize(new Dimension(280, 35));
        tanggalField.setFont(new Font("Arial", Font.PLAIN, 14));
        tanggalField.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        tanggalField.setHorizontalAlignment(JTextField.CENTER);
        tanggalField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(tanggalField);
        formPanel.add(Box.createVerticalStrut(15));

        JLabel kapasitasLabel = new JLabel("Kapasitas");
        kapasitasLabel.setFont(new Font("Arial", Font.BOLD, 14));
        kapasitasLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        formPanel.add(kapasitasLabel);
        formPanel.add(Box.createVerticalStrut(5));

        kapasitasField = new JTextField("50");
        kapasitasField.setMaximumSize(new Dimension(280, 35));
        kapasitasField.setFont(new Font("Arial", Font.PLAIN, 14));
        kapasitasField.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        kapasitasField.setHorizontalAlignment(JTextField.CENTER);
        kapasitasField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(kapasitasField);
        formPanel.add(Box.createVerticalStrut(15));

        JPanel jamLabelPanel = new JPanel(new GridLayout(1, 2, 60, 0));
        jamLabelPanel.setBackground(Color.WHITE);
        jamLabelPanel.setMaximumSize(new Dimension(280, 20));
        jamLabelPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        
        JLabel jamLabel = new JLabel("Jam");
        JLabel hinggaLabel = new JLabel("Hingga");
        jamLabel.setFont(new Font("Arial", Font.BOLD, 14));
        hinggaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        jamLabel.setHorizontalAlignment(JLabel.CENTER);
        hinggaLabel.setHorizontalAlignment(JLabel.CENTER);
        
        jamLabelPanel.add(jamLabel);
        jamLabelPanel.add(hinggaLabel);
        formPanel.add(jamLabelPanel);
        formPanel.add(Box.createVerticalStrut(5));

        JPanel jamPanel = new JPanel();
        jamPanel.setLayout(new BoxLayout(jamPanel, BoxLayout.X_AXIS));
        jamPanel.setBackground(Color.WHITE);
        jamPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        jamPanel.setMaximumSize(new Dimension(280, 35));

        String[] jamOptions = {"07.00", "08.00", "09.00", "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00"};
        JComboBox<String> jamComboBox = new JComboBox<>(jamOptions);
        jamComboBox.setPreferredSize(new Dimension(130, 35));
        jamComboBox.setMaximumSize(new Dimension(130, 35));
        jamComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JComboBox<String> hinggaComboBox = new JComboBox<>(jamOptions);
        hinggaComboBox.setPreferredSize(new Dimension(130, 35));
        hinggaComboBox.setMaximumSize(new Dimension(130, 35));
        hinggaComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        hinggaComboBox.setSelectedIndex(2);

        jamPanel.add(jamComboBox);
        jamPanel.add(Box.createHorizontalStrut(20));
        jamPanel.add(hinggaComboBox);
        
        formPanel.add(jamPanel);
        formPanel.add(Box.createVerticalStrut(15));

        JPanel typeLabelPanel = new JPanel(new GridLayout(1, 2, 0, 0));
        typeLabelPanel.setBackground(Color.WHITE);
        typeLabelPanel.setMaximumSize(new Dimension(280, 20));
        typeLabelPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        
        JLabel kelasTextLabel = new JLabel("Kelas");
        JLabel laboratoriumTextLabel = new JLabel("Laboratorium");
        kelasTextLabel.setFont(new Font("Arial", Font.BOLD, 14));
        laboratoriumTextLabel.setFont(new Font("Arial", Font.BOLD, 14));
        kelasTextLabel.setHorizontalAlignment(JLabel.CENTER);
        laboratoriumTextLabel.setHorizontalAlignment(JLabel.CENTER);
        
        kelasTextLabel.setPreferredSize(new Dimension(130, 20));
        laboratoriumTextLabel.setPreferredSize(new Dimension(130, 20));
        
        typeLabelPanel.add(kelasTextLabel);
        typeLabelPanel.add(laboratoriumTextLabel);
        formPanel.add(typeLabelPanel);
        formPanel.add(Box.createVerticalStrut(5));

        JPanel typePanel = new JPanel();
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.X_AXIS));
        typePanel.setBackground(Color.WHITE);
        typePanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        typePanel.setMaximumSize(new Dimension(280, 35));

        kelasButton = new JButton("Kelas");
        laboratoriumButton = new JButton("Laboratorium");
        
        kelasButton.setFont(new Font("Arial", Font.PLAIN, 14));
        laboratoriumButton.setFont(new Font("Arial", Font.PLAIN, 14));
       
        kelasButton.setPreferredSize(new Dimension(130, 35));
        laboratoriumButton.setPreferredSize(new Dimension(130, 35));
        kelasButton.setMinimumSize(new Dimension(130, 35));
        laboratoriumButton.setMinimumSize(new Dimension(130, 35));
        kelasButton.setMaximumSize(new Dimension(130, 35));
        laboratoriumButton.setMaximumSize(new Dimension(130, 35));
       
        kelasButton.setBackground(new Color(0, 51, 102));
        kelasButton.setForeground(Color.WHITE);
        laboratoriumButton.setBackground(Color.LIGHT_GRAY);
        laboratoriumButton.setForeground(Color.BLACK);
        
        kelasButton.setFocusPainted(false);
        laboratoriumButton.setFocusPainted(false);
        kelasButton.setBorderPainted(false);
        laboratoriumButton.setBorderPainted(false);

        kelasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kelasButton.setBackground(new Color(0, 51, 102));
                kelasButton.setForeground(Color.WHITE);
                laboratoriumButton.setBackground(Color.LIGHT_GRAY);
                laboratoriumButton.setForeground(Color.BLACK);
            }
        });
        
        laboratoriumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                laboratoriumButton.setBackground(new Color(0, 51, 102));
                laboratoriumButton.setForeground(Color.WHITE);
                kelasButton.setBackground(Color.LIGHT_GRAY);
                kelasButton.setForeground(Color.BLACK);
            }
        });

        typePanel.add(kelasButton);
        typePanel.add(Box.createHorizontalStrut(20));
        typePanel.add(laboratoriumButton);
        formPanel.add(typePanel);
        formPanel.add(Box.createVerticalStrut(25));

        cariButton = new JButton("Cari");
        cariButton.setFont(new Font("Arial", Font.BOLD, 16));
        cariButton.setBackground(new Color(0, 51, 102));
        cariButton.setForeground(Color.WHITE);
        cariButton.setFocusPainted(false);
        cariButton.setBorderPainted(false);
        cariButton.setPreferredSize(new Dimension(200, 45));
        cariButton.setMaximumSize(new Dimension(200, 45));
        cariButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        
        cariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            // Navigasi ke LihatRuangan3 (hasil pencarian)
            new LihatRuangan3().setVisible(true);
            dispose();
            }
        });
        
        formPanel.add(cariButton);
       
        formContainer.add(Box.createVerticalGlue());
        formContainer.add(formPanel);
        formContainer.add(Box.createVerticalGlue());
        
        mainPanel.add(formContainer, BorderLayout.CENTER);

        navPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        navPanel.setBackground(Color.WHITE);
        navPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        homeButton = new JButton("Home");
        ruanganButton = new JButton("Ruangan");
        reservasiButton = new JButton("Reservasi");
        
        homeButton.setBackground(Color.LIGHT_GRAY);
        homeButton.setForeground(Color.BLACK);
        ruanganButton.setBackground(new Color(0, 51, 102));
        ruanganButton.setForeground(Color.WHITE);
        reservasiButton.setBackground(Color.LIGHT_GRAY);
        reservasiButton.setForeground(Color.BLACK);
        
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
        
        reservasiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            new ReservasiRuangan().setVisible(true);
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LihatRuangan2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LihatRuangan2().setVisible(true);
            }
        });
    }                 
}

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReservasiRuangan extends javax.swing.JFrame {

    private JPanel mainPanel;
    private JPanel navPanel;
    private JButton homeButton, ruanganButton, reservasiButton;
    private JButton kelasButton, laboratoriumButton;

    public ReservasiRuangan() {
        initComponentsCustom();
        
        setTitle("Reservasi Ruangan - SIRUKAN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
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
        headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 20, 0));

        JLabel titleLabel = new JLabel("SIRUKAN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102));
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        
        JLabel subtitleLabel = new JLabel("Pilihan Reservasi");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        subtitleLabel.setForeground(Color.BLACK);
        subtitleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        headerPanel.add(subtitleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        //Konten
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        //Kelas 
        JPanel kelasSectionPanel = new JPanel();
        kelasSectionPanel.setLayout(new BoxLayout(kelasSectionPanel, BoxLayout.Y_AXIS));
        kelasSectionPanel.setBackground(Color.WHITE);
        kelasSectionPanel.setMaximumSize(new Dimension(320, 200));

        JLabel kelasLabel = new JLabel("Kelas");
        kelasLabel.setFont(new Font("Arial", Font.BOLD, 16));
        kelasLabel.setForeground(Color.BLACK);
        kelasLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        kelasSectionPanel.add(kelasLabel);
        kelasSectionPanel.add(Box.createVerticalStrut(10));

        JPanel separatorPanel1 = new JPanel();
        separatorPanel1.setBackground(Color.LIGHT_GRAY);
        separatorPanel1.setMaximumSize(new Dimension(320, 2));
        separatorPanel1.setPreferredSize(new Dimension(320, 2));
        
        kelasSectionPanel.add(separatorPanel1);
        kelasSectionPanel.add(Box.createVerticalStrut(20));

        //Kelas button
        kelasButton = new JButton("Kelas");
        kelasButton.setFont(new Font("Arial", Font.BOLD, 16));
        kelasButton.setBackground(new Color(0, 51, 102));
        kelasButton.setForeground(Color.WHITE);
        kelasButton.setFocusPainted(false);
        kelasButton.setBorderPainted(false);
        kelasButton.setPreferredSize(new Dimension(280, 200));
        kelasButton.setMaximumSize(new Dimension(280, 200));
        kelasButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        
        kelasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReservasiKelas().setVisible(true);
                dispose();
            }
        });
        
        kelasSectionPanel.add(kelasButton);
        contentPanel.add(kelasSectionPanel);
        contentPanel.add(Box.createVerticalStrut(40));

        //Laboratorium 
        JPanel laboratoriumSectionPanel = new JPanel();
        laboratoriumSectionPanel.setLayout(new BoxLayout(laboratoriumSectionPanel, BoxLayout.Y_AXIS));
        laboratoriumSectionPanel.setBackground(Color.WHITE);
        laboratoriumSectionPanel.setMaximumSize(new Dimension(320, 200));

        JLabel laboratoriumLabel = new JLabel("Laboratorium");
        laboratoriumLabel.setFont(new Font("Arial", Font.BOLD, 16));
        laboratoriumLabel.setForeground(Color.BLACK);
        laboratoriumLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        laboratoriumSectionPanel.add(laboratoriumLabel);
        laboratoriumSectionPanel.add(Box.createVerticalStrut(10));

        JPanel separatorPanel2 = new JPanel();
        separatorPanel2.setBackground(Color.LIGHT_GRAY);
        separatorPanel2.setMaximumSize(new Dimension(320, 2));
        separatorPanel2.setPreferredSize(new Dimension(320, 2));
        
        laboratoriumSectionPanel.add(separatorPanel2);
        laboratoriumSectionPanel.add(Box.createVerticalStrut(20));

        //Laboratorium Button
        laboratoriumButton = new JButton("Laboratorium");
        laboratoriumButton.setFont(new Font("Arial", Font.BOLD, 16));
        laboratoriumButton.setBackground(new Color(0, 51, 102));
        laboratoriumButton.setForeground(Color.WHITE);
        laboratoriumButton.setFocusPainted(false);
        laboratoriumButton.setBorderPainted(false);
        laboratoriumButton.setPreferredSize(new Dimension(280, 200));
        laboratoriumButton.setMaximumSize(new Dimension(280, 200));
        laboratoriumButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        
        laboratoriumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javax.swing.JOptionPane.showMessageDialog(ReservasiRuangan.this, 
                    "Memilih reservasi Laboratorium");
            }
        });
        
        laboratoriumSectionPanel.add(laboratoriumButton);
        contentPanel.add(laboratoriumSectionPanel);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        navPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        navPanel.setBackground(Color.WHITE);
        navPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
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
            java.util.logging.Logger.getLogger(ReservasiRuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReservasiRuangan().setVisible(true);
            }
        });
    }                 
}

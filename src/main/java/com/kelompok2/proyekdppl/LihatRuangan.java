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

public class LihatRuangan extends javax.swing.JFrame {

    private JPanel mainPanel;
    private JPanel navPanel;
    private JButton homeButton, ruanganButton, reservasiButton;

    public LihatRuangan() {
        initComponentsCustom();
        
        setTitle("Lihat Ruangan - SIRUKAN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
    }

    private void initComponentsCustom() {
        
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));

        JLabel titleLabel = new JLabel("SIRUKAN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102));
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        JButton lihatRuanganButton = new JButton("Lihat Ruangan");
        lihatRuanganButton.setFont(new Font("Arial", Font.BOLD, 16));
        lihatRuanganButton.setBackground(new Color(0, 51, 102));
        lihatRuanganButton.setForeground(Color.WHITE);
        lihatRuanganButton.setFocusPainted(false);
        lihatRuanganButton.setBorderPainted(false);
        lihatRuanganButton.setPreferredSize(new Dimension(400, 200));
        lihatRuanganButton.setMaximumSize(new Dimension(400, 200));
        lihatRuanganButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        
        lihatRuanganButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LihatRuangan2 lihatRuangan2 = new LihatRuangan2();
                    lihatRuangan2.setLocationRelativeTo(null);
                    lihatRuangan2.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(LihatRuangan.this, 
                        "Error: " + ex.getMessage() + "\nPastikan file LihatRuangan2.java sudah dibuat!");
                }
            }
        });
        
        contentPanel.add(lihatRuanganButton);
        contentPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        navPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        navPanel.setBackground(Color.WHITE);
        navPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        homeButton = new JButton("Home");
        ruanganButton = new JButton("Ruangan");
        reservasiButton = new JButton("Reservasi");
        
        styleNavButton(homeButton, false);
        styleNavButton(ruanganButton, true);
        styleNavButton(reservasiButton, false);
        
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDashboard();
            }
        });
        
        reservasiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javax.swing.JOptionPane.showMessageDialog(LihatRuangan.this, 
                    "Fitur Reservasi akan segera tersedia!");
            }
        });
        
        navPanel.add(homeButton);
        navPanel.add(ruanganButton);
        navPanel.add(reservasiButton);
        mainPanel.add(navPanel, BorderLayout.SOUTH);
        
        this.add(mainPanel);
    }
    
    private void styleNavButton(JButton button, boolean isActive) {
        if (isActive) {
            button.setBackground(new Color(0, 51, 102));
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(Color.LIGHT_GRAY);
            button.setForeground(Color.BLACK);
        }
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
    
    private void openDashboard() {
        DashboardDosen dashboard = new DashboardDosen();
        dashboard.setLocationRelativeTo(null);
        dashboard.setVisible(true);
        this.dispose();
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LihatRuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LihatRuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LihatRuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LihatRuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LihatRuangan().setVisible(true);
            }
        });
    }                  
}

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
import java.awt.Component;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DashboardDosen extends javax.swing.JFrame {

    private JPanel mainPanel;
    private JPanel cardsPanel;
    private JPanel tablePanel;
    private JPanel navPanel;
    private JTable reservasiTable;
    private JButton homeButton, ruanganButton, reservasiButton;

    public DashboardDosen() {
        initComponentsCustom();
        
        setTitle("Dashboard Dosen - SIRUKAN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
    }

    private void initComponentsCustom() {
        
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("SIRUKAN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel centerContent = new JPanel(new BorderLayout(10, 10));
        centerContent.setBackground(Color.WHITE);

        cardsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        cardsPanel.setBackground(Color.WHITE);
        
        JPanel card1 = createInfoCard("Total Reservasi", "5");
        JPanel card2 = createInfoCard("Menunggu Persetujuan", "0");
        
        cardsPanel.add(card1);
        cardsPanel.add(card2);
        
        centerContent.add(cardsPanel, BorderLayout.NORTH);

        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createTitledBorder("Reservasi Terbaru"));

        String[] columnNames = {"Tanggal", "Waktu", "Ruangan", "Status"};
        Object[][] data = {
            {"10 April 2024", "09.00-11.00", "C304", "Disetujui"},
            {"10 April 2024", "09.00-11.00", "C304", "Disetujui"},
            {"10 April 2024", "09.00-11.00", "C304", "Disetujui"},
            {"10 April 2024", "09.00-11.00", "C304", "Disetujui"},
            {"10 April 2024", "09.00-11.00", "C304", "Disetujui"}
        };
        
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        reservasiTable = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(reservasiTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        centerContent.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(centerContent, BorderLayout.CENTER);

        navPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        navPanel.setBackground(Color.WHITE);
        
        homeButton = new JButton("Home");
        ruanganButton = new JButton("Ruangan");
        reservasiButton = new JButton("Reservasi");
        
        styleNavButton(homeButton, true);
        styleNavButton(ruanganButton, false);
        styleNavButton(reservasiButton, false);
        
        ruanganButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRuanganPage();
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

    private JPanel createInfoCard(String title, String value) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEtchedBorder());
        card.setPreferredSize(new Dimension(150, 100));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 36));
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createVerticalStrut(15));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(valueLabel);

        return card;
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
    
    private void openRuanganPage() {
        LihatRuangan lihatRuangan = new LihatRuangan();
        lihatRuangan.setLocationRelativeTo(null);
        lihatRuangan.setVisible(true);
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
            java.util.logging.Logger.getLogger(DashboardDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardDosen().setVisible(true);
            }
        });
    }                  
}
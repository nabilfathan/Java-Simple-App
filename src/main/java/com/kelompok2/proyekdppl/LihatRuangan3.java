package com.kelompok2.proyekdppl;

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

public class LihatRuangan3 extends javax.swing.JFrame {

    private JPanel mainPanel;
    private JPanel navPanel;
    private JButton homeButton, ruanganButton, reservasiButton;

    public LihatRuangan3() {
        initComponentsCustom();
        
        setTitle("Hasil Pencarian Ruangan - SIRUKAN");
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
       
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        //Content 
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        JPanel ruanganListPanel = new JPanel();
        ruanganListPanel.setLayout(new GridLayout(2, 2, 15, 15));
        ruanganListPanel.setBackground(Color.WHITE);
        ruanganListPanel.setMaximumSize(new Dimension(320, 200));
        ruanganListPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        String[] rooms = {"C-305", "C-306", "C-307", "C-311"};
        
        for (String room : rooms) {
            JLabel roomLabel = createRoomLabel(room);
            ruanganListPanel.add(roomLabel);
        }

        contentPanel.add(ruanganListPanel);
        contentPanel.add(Box.createVerticalStrut(30));

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBackground(Color.WHITE);
        filterPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        filterPanel.setMaximumSize(new Dimension(320, 150));
        
        //Tanggal
        JPanel tanggalPanel = new JPanel(new BorderLayout());
        tanggalPanel.setBackground(Color.WHITE);
        tanggalPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        tanggalPanel.setMaximumSize(new Dimension(320, 40));

        JLabel tanggalLabel = new JLabel("Tanggal");
        tanggalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel tanggalValue = new JLabel("03/11/2025");
        tanggalValue.setFont(new Font("Arial", Font.PLAIN, 14));
        tanggalValue.setHorizontalAlignment(JLabel.RIGHT);

        tanggalPanel.add(tanggalLabel, BorderLayout.WEST);
        tanggalPanel.add(tanggalValue, BorderLayout.EAST);

        //Fakultas
        JPanel fakultasPanel = new JPanel(new BorderLayout());
        fakultasPanel.setBackground(Color.WHITE);
        fakultasPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        fakultasPanel.setMaximumSize(new Dimension(320, 40));

        JLabel fakultasLabel = new JLabel("Fakultas");
        fakultasLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel fakultasValue = new JLabel("Fakultas Teknik");
        fakultasValue.setFont(new Font("Arial", Font.PLAIN, 14));
        fakultasValue.setHorizontalAlignment(JLabel.RIGHT);

        fakultasPanel.add(fakultasLabel, BorderLayout.WEST);
        fakultasPanel.add(fakultasValue, BorderLayout.EAST);

        //Kapasitas
        JPanel kapasitasPanel = new JPanel(new BorderLayout());
        kapasitasPanel.setBackground(Color.WHITE);
        kapasitasPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        kapasitasPanel.setMaximumSize(new Dimension(320, 40));

        JLabel kapasitasLabel = new JLabel("Kapasitas");
        kapasitasLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel kapasitasValue = new JLabel("50 Orang");
        kapasitasValue.setFont(new Font("Arial", Font.PLAIN, 14));
        kapasitasValue.setHorizontalAlignment(JLabel.RIGHT);

        kapasitasPanel.add(kapasitasLabel, BorderLayout.WEST);
        kapasitasPanel.add(kapasitasValue, BorderLayout.EAST);

        //Jam
        JPanel jamPanel = new JPanel(new BorderLayout());
        jamPanel.setBackground(Color.WHITE);
        jamPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        jamPanel.setMaximumSize(new Dimension(320, 40));

        JLabel jamLabel = new JLabel("Jam");
        jamLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel jamValue = new JLabel("08.00 - 10.00");
        jamValue.setFont(new Font("Arial", Font.PLAIN, 14));
        jamValue.setHorizontalAlignment(JLabel.RIGHT);

        jamPanel.add(jamLabel, BorderLayout.WEST);
        jamPanel.add(jamValue, BorderLayout.EAST);

        filterPanel.add(tanggalPanel);
        filterPanel.add(fakultasPanel);
        filterPanel.add(kapasitasPanel);
        filterPanel.add(jamPanel);

        contentPanel.add(filterPanel);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        //Navigasi
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

    private JLabel createRoomLabel(String roomName) {
        JLabel roomLabel = new JLabel(roomName, JLabel.CENTER);
        roomLabel.setFont(new Font("Arial", Font.BOLD, 16));
        roomLabel.setBackground(new Color(0, 51, 102));
        roomLabel.setForeground(Color.WHITE);
        roomLabel.setOpaque(true); 
        roomLabel.setPreferredSize(new Dimension(80, 80));
        roomLabel.setMinimumSize(new Dimension(80, 80));
        roomLabel.setMaximumSize(new Dimension(80, 80));
        
        return roomLabel;
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
            java.util.logging.Logger.getLogger(LihatRuangan3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LihatRuangan3().setVisible(true);
            }
        });
    }                 
}
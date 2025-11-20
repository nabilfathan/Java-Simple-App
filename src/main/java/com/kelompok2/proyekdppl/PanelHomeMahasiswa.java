package com.kelompok2.proyekdppl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class PanelHomeMahasiswa extends JPanel {

    private MainApp mainApp;
    private JLabel userNameLabel;

    public PanelHomeMahasiswa(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.decode("#f0f2f5")); 
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false); 
        
        contentPanel.add(createSummaryPanel());
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15))); 
        
        contentPanel.add(createReservasiPanel());

        add(contentPanel, BorderLayout.CENTER);
    }

    public void setUserName(String name) {
        userNameLabel.setText("Good Morning, " + name + "...");
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false); 
        userNameLabel = new JLabel("Good Morning...");
        userNameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        JLabel subLabel = new JLabel("Selamat datang di SIRUKAN");
        subLabel.setForeground(Color.GRAY);
        
        panel.add(userNameLabel, BorderLayout.NORTH);
        panel.add(subLabel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10)); 
        panel.setOpaque(false);

        int totalReservasi = 5;
        int menunggu = 0;

        panel.add(createSummaryCard("Total Reservasi", String.valueOf(totalReservasi)));
        panel.add(createSummaryCard("Menunggu Persetujuan", String.valueOf(menunggu)));
        
        return panel;
    }

    private JPanel createReservasiPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#dddddd"), 1, true), 
            new EmptyBorder(15, 15, 15, 15) 
        ));
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Reservasi Terbaru");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(title, BorderLayout.NORTH);

        String[] columnNames = {"Tanggal", "Waktu", "Ruangan", "Status"};
        Object[][] data = {
            {"10 April 2024", "09.00-11.00", "C304", "Disetujui"},
            {"10 April 2024", "09.00-11.00", "C304", "Disetujui"},
            {"10 April 2024", "09.00-11.00", "C304", "Disetujui"},
            {"10 April 2024", "09.00-11.00", "C304", "Disetujui"},
        };

        JTable table = new JTable(data, columnNames);
        table.setRowHeight(25);
        table.setFillsViewportHeight(true);
        table.setEnabled(false); 

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createSummaryCard(String title, String value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#dddddd"), 1, true), 
            new EmptyBorder(15, 15, 15, 15) 
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.GRAY);
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
}
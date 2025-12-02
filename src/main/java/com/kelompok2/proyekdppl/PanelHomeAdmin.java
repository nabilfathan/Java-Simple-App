package com.kelompok2.proyekdppl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

public class PanelHomeAdmin extends JPanel {

    private MainApp mainApp;
    private JLabel userNameLabel;
    private JLabel totalReservasiLabel;
    private JLabel menungguLabel;
    private JLabel disetujuiLabel;
    private JLabel ditolakLabel;
    private JTable table;
    private DefaultTableModel tableModel;

    public PanelHomeAdmin(MainApp mainApp) {
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
        
        // Load data pertama kali
        refreshData();
        
        // AUTO REFRESH - Refresh data setiap kali panel ditampilkan
        this.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                System.out.println("Panel Admin Dashboard: Refreshing Data...");
                refreshData();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {}

            @Override
            public void ancestorMoved(AncestorEvent event) {}
        });
    }

    public void setUserName(String name) {
        userNameLabel.setText("Welcome Admin, " + name);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false); 
        userNameLabel = new JLabel("Welcome Admin...");
        userNameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        JLabel subLabel = new JLabel("Dashboard Reservasi Ruangan");
        subLabel.setForeground(Color.GRAY);
        
        panel.add(userNameLabel, BorderLayout.NORTH);
        panel.add(subLabel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 10, 10)); 
        panel.setOpaque(false);

        // Buat label yang bisa di-update
        totalReservasiLabel = new JLabel("0");
        totalReservasiLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        
        menungguLabel = new JLabel("0");
        menungguLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        
        disetujuiLabel = new JLabel("0");
        disetujuiLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        
        ditolakLabel = new JLabel("0");
        ditolakLabel.setFont(new Font("SansSerif", Font.BOLD, 36));

        panel.add(createSummaryCard("Total Reservasi", totalReservasiLabel, Color.decode("#007bff")));
        panel.add(createSummaryCard("Menunggu", menungguLabel, Color.decode("#ffc107")));
        panel.add(createSummaryCard("Disetujui", disetujuiLabel, Color.decode("#28a745")));
        panel.add(createSummaryCard("Ditolak", ditolakLabel, Color.decode("#dc3545")));
        
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

        String[] columnNames = {"NIM", "Nama", "Ruangan", "Tanggal", "Jam", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createSummaryCard(String title, JLabel valueLabel, Color accentColor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#dddddd"), 1, true), 
            new EmptyBorder(15, 15, 15, 15) 
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.GRAY);
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        valueLabel.setForeground(accentColor);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    // METHOD BARU: Refresh data dari DataManager
    private void refreshData() {
        // Ambil semua reservasi dari DataManager
        List<Reservasi> allReservasi = mainApp.getDataManager().getAllReservasi();
        
        if (allReservasi == null || allReservasi.isEmpty()) {
            totalReservasiLabel.setText("0");
            menungguLabel.setText("0");
            disetujuiLabel.setText("0");
            ditolakLabel.setText("0");
            tableModel.setRowCount(0);
            return;
        }
        
        // Hitung statistik
        int total = allReservasi.size();
        int menunggu = (int) allReservasi.stream()
            .filter(r -> r.getStatus().equalsIgnoreCase("Menunggu"))
            .count();
        int disetujui = (int) allReservasi.stream()
            .filter(r -> r.getStatus().equalsIgnoreCase("Disetujui"))
            .count();
        int ditolak = (int) allReservasi.stream()
            .filter(r -> r.getStatus().equalsIgnoreCase("Ditolak"))
            .count();
        
        // Update summary cards
        totalReservasiLabel.setText(String.valueOf(total));
        menungguLabel.setText(String.valueOf(menunggu));
        disetujuiLabel.setText(String.valueOf(disetujui));
        ditolakLabel.setText(String.valueOf(ditolak));
        
        // Update tabel dengan data terbaru (maksimal 15 data terbaru)
        tableModel.setRowCount(0);
        
        int limit = Math.min(allReservasi.size(), 15);
        for (int i = allReservasi.size() - 1; i >= allReservasi.size() - limit && i >= 0; i--) {
            Reservasi r = allReservasi.get(i);
            
            // Ambil nama user
            User u = mainApp.getDataManager().getUserByNIM(r.getNimPemesan());
            String nama = (u != null) ? u.getNama() : "-";
            
            Object[] row = {
                r.getNimPemesan(),
                nama,
                r.getKodeRuang(),
                r.getTanggal(),
                r.getJamMulai() + "-" + r.getJamSelesai(),
                r.getStatus()
            };
            tableModel.addRow(row);
        }
    }
}
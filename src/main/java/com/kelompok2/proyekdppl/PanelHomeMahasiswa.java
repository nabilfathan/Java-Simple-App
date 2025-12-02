package com.kelompok2.proyekdppl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PanelHomeMahasiswa extends JPanel {

    private MainApp mainApp;
    private JLabel userNameLabel;
    private JLabel totalReservasiLabel;
    private JLabel menungguLabel;
    private JTable table;
    private DefaultTableModel tableModel;
    private String currentUserNIM;

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
        
        // AUTO REFRESH: Setiap kali panel ini dibuka, data ditarik ulang
        this.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                refreshData();
            }
            @Override public void ancestorRemoved(AncestorEvent event) {}
            @Override public void ancestorMoved(AncestorEvent event) {}
        });
    }

    // --- PERBAIKAN UTAMA DISINI ---
    
    // 1. Method Utama: Panggil ini agar data MUNCUL (Wajib ada NIM)
    public void setStudentInfo(String name, String nim) {
        this.currentUserNIM = nim;
        userNameLabel.setText("Selamat Datang, " + name + "!");
        System.out.println("Dashboard MHS: Login sebagai " + name + " (" + nim + ")");
        refreshData();
    }

    // 2. Method Cadangan: Biar MainApp tidak error "cannot find symbol"
    // Tapi method ini TIDAK AKAN memunculkan data reservasi karena NIM-nya null
    public void setUserName(String name) {
        userNameLabel.setText("Selamat Datang, " + name + "!");
        // Kita coba cari NIM dari User Login di MainApp kalau bisa
        if (mainApp.getUserLogin() != null) {
            this.currentUserNIM = mainApp.getUserLogin().getNim();
            refreshData();
        }
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false); 
        userNameLabel = new JLabel("Selamat Datang...");
        userNameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        JLabel subLabel = new JLabel("Dashboard Mahasiswa - Status Reservasi");
        subLabel.setForeground(Color.GRAY);
        
        panel.add(userNameLabel, BorderLayout.NORTH);
        panel.add(subLabel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10)); 
        panel.setOpaque(false);

        totalReservasiLabel = new JLabel("0");
        totalReservasiLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        
        menungguLabel = new JLabel("0");
        menungguLabel.setFont(new Font("SansSerif", Font.BOLD, 36));

        panel.add(createSummaryCard("Total Reservasi Saya", totalReservasiLabel));
        panel.add(createSummaryCard("Menunggu Persetujuan", menungguLabel));
        
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

        JLabel title = new JLabel("Riwayat Reservasi Terbaru");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(title, BorderLayout.NORTH);

        String[] columnNames = {"Tanggal", "Waktu", "Ruangan", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };

        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.setFillsViewportHeight(true);
        
        // Rata Tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i=0; i<3; i++) table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createSummaryCard(String title, JLabel valueLabel) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#dddddd"), 1, true), 
            new EmptyBorder(15, 15, 15, 15) 
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.GRAY);
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        return card;
    }
    
    // METHOD REFRESH DATA (LOGIKA UTAMA)
    private void refreshData() {
        // Jaga-jaga jika NIM belum diset
        if (currentUserNIM == null || currentUserNIM.isEmpty()) {
            // Coba ambil dari MainApp jika user sudah login tapi lupa setStudentInfo
            if (mainApp != null && mainApp.getUserLogin() != null) {
                currentUserNIM = mainApp.getUserLogin().getNim();
            } else {
                System.out.println("Dashboard Kosong: NIM Mahasiswa belum diketahui.");
                return;
            }
        }
        
        // 1. Ambil Data
        List<Reservasi> allReservasi = mainApp.getDataManager().getAllReservasi();
        
        if (allReservasi == null || allReservasi.isEmpty()) {
            totalReservasiLabel.setText("0");
            menungguLabel.setText("0");
            tableModel.setRowCount(0);
            return;
        }
        
        // 2. Filter Sesuai NIM
        List<Reservasi> myReservasi = allReservasi.stream()
            .filter(r -> r.getNimPemesan() != null && r.getNimPemesan().equals(currentUserNIM))
            .collect(Collectors.toList());
        
        // 3. Update Angka
        totalReservasiLabel.setText(String.valueOf(myReservasi.size()));
        long menunggu = myReservasi.stream().filter(r -> r.getStatus().equalsIgnoreCase("Menunggu")).count();
        menungguLabel.setText(String.valueOf(menunggu));
        
        // 4. Update Tabel
        tableModel.setRowCount(0);
        for (int i = myReservasi.size() - 1; i >= 0; i--) {
            Reservasi r = myReservasi.get(i);
            tableModel.addRow(new Object[]{
                r.getTanggal(),
                r.getJamMulai() + " - " + r.getJamSelesai(),
                r.getKodeRuang(),
                r.getStatus().toUpperCase()
            });
        }
    }
}
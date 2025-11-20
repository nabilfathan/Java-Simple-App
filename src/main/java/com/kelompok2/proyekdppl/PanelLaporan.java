package com.kelompok2.proyekdppl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PanelLaporan extends JPanel {

    private MainApp mainApp;

    public PanelLaporan(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.decode("#f0f2f5"));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        add(createHeaderPanel(), BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        
        contentPanel.add(createSummaryPanel());
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
      
        contentPanel.add(createGraphPanel());
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
       
        contentPanel.add(createExportPanel());
        
        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);
        
        JLabel title = new JLabel("Laporan & Analitik");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        panel.add(title, BorderLayout.NORTH);
        
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setOpaque(false);
        filterPanel.setBorder(new TitledBorder("Filter Laporan"));

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.setOpaque(false);
        p1.add(new JLabel("Periode:"));
        p1.add(new JTextField("[1 Nov 2024 - 15 Nov 2024]", 20)); 
        
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2.setOpaque(false);
        p2.add(new JLabel("Periode:"));
        p2.add(new JComboBox<>(new String[]{"Semua ruangan", "C-301", "A-101"}));
        p2.add(new JComboBox<>(new String[]{"Semua jenis", "Kelas", "Laboratorium"}));
        p2.add(new JComboBox<>(new String[]{"Disetujui", "Ditolak", "Semua Status"}));

        filterPanel.add(p1);
        filterPanel.add(p2);
        panel.add(filterPanel, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.setOpaque(false);
        panel.setBorder(new TitledBorder("Ringkasan"));

        long total = 1245;
        long disetujui = 1237;
        long ditolak = 18;

        panel.add(createSummaryCard("Total", String.valueOf(total)));
        panel.add(createSummaryCard("Disetujui", String.valueOf(disetujui)));
        panel.add(createSummaryCard("Ditolak", String.valueOf(ditolak)));
        
        return panel;
    }

    private JPanel createGraphPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(new TitledBorder("Grafik Penggunaan Ruangan"));
        
        panel.add(createGraphBar("Kelas C-301", 75));
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(createGraphBar("Kelas C-302", 57));
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(createGraphBar("Lab A-101", 32));
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(createGraphBar("Kelas C-304", 10));
        
        return panel;
    }
    
    private JPanel createExportPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setOpaque(false);
        panel.setBorder(new TitledBorder("Ekspor Laporan"));
        
        JButton btnPdf = new JButton("PDF");
        JButton btnExcel = new JButton("Excel");
        JButton btnCsv = new JButton("CSV");
        
        panel.add(btnPdf);
        panel.add(btnExcel);
        panel.add(btnCsv);
        
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
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createGraphBar(String label, int value) {
        JPanel barPanel = new JPanel(new BorderLayout(10, 0));
        barPanel.setOpaque(false);
        
        JLabel title = new JLabel(label);
        title.setPreferredSize(new Dimension(100, 20));     
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setValue(value);
        bar.setStringPainted(true);
        bar.setString(value + "%");       
        barPanel.add(title, BorderLayout.WEST);
        barPanel.add(bar, BorderLayout.CENTER);
        return barPanel;
    }
}
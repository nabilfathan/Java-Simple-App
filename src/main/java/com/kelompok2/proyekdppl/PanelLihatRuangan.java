package com.kelompok2.proyekdppl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PanelLihatRuangan extends JPanel {

    private MainApp mainApp;
    private JPanel resultsPanel; 

    public PanelLihatRuangan(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.decode("#f0f2f5"));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        add(createSearchPanel(), BorderLayout.NORTH);

        add(createResultsPanel(), BorderLayout.CENTER);
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);
        panel.setBorder(new TitledBorder("Cari Ruangan Tersedia"));

        JPanel formFieldsPanel = new JPanel();
        formFieldsPanel.setLayout(new BoxLayout(formFieldsPanel, BoxLayout.Y_AXIS));
        formFieldsPanel.setOpaque(false);

        formFieldsPanel.add(new JLabel("Fakultas:"));
        formFieldsPanel.add(new JComboBox<>(new String[]{"Pilih Fakultas", "Teknik", "MIPA", "Hukum"}));
        formFieldsPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel row2 = new JPanel(new GridLayout(1, 2, 10, 0));
        row2.setOpaque(false);
        row2.add(new LabeledTextField("Tanggal (dd/mm/yy):", new JTextField()));
        row2.add(new LabeledTextField("Kapasitas:", new JTextField("50")));
        formFieldsPanel.add(row2);
        formFieldsPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel row3 = new JPanel(new GridLayout(1, 2, 10, 0));
        row3.setOpaque(false);
        row3.add(new LabeledTextField("Jam Mulai (HH:MM):", new JTextField("08:00")));
        row3.add(new LabeledTextField("Hingga (HH:MM):", new JTextField("10:30")));
        formFieldsPanel.add(row3);
        formFieldsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row4.setOpaque(false);
        row4.add(new JCheckBox("Kelas", true));
        row4.add(new JCheckBox("Laboratorium"));
        formFieldsPanel.add(row4);

        panel.add(formFieldsPanel, BorderLayout.CENTER);

        JButton cariButton = new JButton("Cari Ruangan");
        cariButton.putClientProperty("JButton.buttonType", "roundRect");
        cariButton.setBackground(Color.decode("#4285F4"));
        cariButton.setForeground(Color.WHITE);
        panel.add(cariButton, BorderLayout.SOUTH);

        cariButton.addActionListener(e -> {
            
            updateSearchResults();
        });

        return panel;
    }

    private JScrollPane createResultsPanel() {
        resultsPanel = new JPanel(new GridLayout(0, 3, 10, 10)); 
        resultsPanel.setOpaque(false);
        
        resultsPanel.add(new JLabel("Silakan lakukan pencarian..."));
        
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(new TitledBorder("Hasil Pencarian"));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private void updateSearchResults() {
        resultsPanel.removeAll();
       
        List<Ruang> ruangan = mainApp.getDataManager().getAllRuangan();
        
        if (ruangan.isEmpty()) {
            resultsPanel.add(new JLabel("Tidak ada ruangan ditemukan."));
        } else {
            for (Ruang r : ruangan) {
                if (r.getStatus().equals("Aktif")) { 
                    resultsPanel.add(createRoomCard(r.getKodeRuang()));
                }
            }
        }
        
        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    private JPanel createRoomCard(String namaRuang) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        card.setPreferredSize(new Dimension(120, 120));
       
        JLabel imageLabel = new JLabel("Foto Ruangan", SwingConstants.CENTER);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.GRAY);
        imageLabel.setForeground(Color.WHITE);
        
        JLabel nameLabel = new JLabel(namaRuang, SwingConstants.CENTER);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        nameLabel.setOpaque(true);
        nameLabel.setBackground(new Color(255, 255, 255, 200)); 
        
        card.add(imageLabel, BorderLayout.CENTER);
        card.add(nameLabel, BorderLayout.SOUTH);
        return card;
    }

    private class LabeledTextField extends JPanel {
        LabeledTextField(String label, JComponent component) {
            setLayout(new BorderLayout(0, 3));
            setOpaque(false);
            add(new JLabel(label), BorderLayout.NORTH);
            add(component, BorderLayout.CENTER);
        }
    }
}
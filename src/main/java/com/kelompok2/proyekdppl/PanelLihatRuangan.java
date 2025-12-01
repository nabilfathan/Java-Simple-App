package com.kelompok2.proyekdppl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PanelLihatRuangan extends JPanel {

    private MainApp mainApp;
    private JPanel resultsPanel;
    private JTextField fieldKapasitas;
    private JCheckBox checkKelas;
    private JCheckBox checkLaboratorium;
    private JComboBox<String> comboFakultas;
    private JTextField fieldTanggal;
    private JTextField fieldJamMulai;
    private JTextField fieldJamSelesai;

    public PanelLihatRuangan(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.decode("#f0f2f5"));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        add(createSearchPanel(), BorderLayout.NORTH);
        add(createResultsPanel(), BorderLayout.CENTER);
        
        // Load data ruangan saat pertama kali dibuka
        updateSearchResults();
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);
        panel.setBorder(new TitledBorder("Cari Ruangan Tersedia"));

        JPanel formFieldsPanel = new JPanel();
        formFieldsLayout(formFieldsPanel);

        panel.add(formFieldsPanel, BorderLayout.CENTER);

        JButton cariButton = createSearchButton();
        panel.add(cariButton, BorderLayout.SOUTH);

        return panel;
    }

    private void formFieldsLayout(JPanel formFieldsPanel) {
        formFieldsPanel.setLayout(new BoxLayout(formFieldsPanel, BoxLayout.Y_AXIS));
        formFieldsPanel.setOpaque(false);

        // Combo fakultas
        formFieldsPanel.add(new JLabel("Fakultas:"));
        comboFakultas = new JComboBox<>(new String[]{"Semua Fakultas", "Teknik", "MIPA", "Hukum"});
        formFieldsPanel.add(comboFakultas);
        formFieldsPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel row2 = new JPanel(new GridLayout(1, 2, 10, 0));
        row2.setOpaque(false);
        fieldTanggal = new JTextField();
        row2.add(new LabeledTextField("Tanggal (dd/mm/yy):", fieldTanggal));
        fieldKapasitas = new JTextField();
        fieldKapasitas.setToolTipText("Masukkan kapasitas minimum yang diinginkan");
        row2.add(new LabeledTextField("Kapasitas Minimum:", fieldKapasitas));
        formFieldsPanel.add(row2);
        formFieldsPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel row3 = new JPanel(new GridLayout(1, 2, 10, 0));
        row3.setOpaque(false);
        fieldJamMulai = new JTextField("08:00");
        fieldJamSelesai = new JTextField("10:30");
        row3.add(new LabeledTextField("Jam Mulai (HH:MM):", fieldJamMulai));
        row3.add(new LabeledTextField("Hingga (HH:MM):", fieldJamSelesai));
        formFieldsPanel.add(row3);
        formFieldsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row4.setOpaque(false);
        checkKelas = new JCheckBox("Kelas", true);
        checkLaboratorium = new JCheckBox("Laboratorium", true);
        row4.add(checkKelas);
        row4.add(checkLaboratorium);
        formFieldsPanel.add(row4);
    }

    private JButton createSearchButton() {
        JButton cariButton = new JButton("Cari Ruangan");
        cariButton.putClientProperty("JButton.buttonType", "roundRect");
        cariButton.setBackground(Color.decode("#4285F4"));
        cariButton.setForeground(Color.WHITE);
        
        cariButton.addActionListener(e -> {
            // Validasi sebelum melakukan pencarian
            if (!validateSearchCriteria()) {
                return;
            }
            updateSearchResults();
        });
        
        return cariButton;
    }

    // METHOD BARU: Validasi kriteria pencarian - MODIFIKASI
    private boolean validateSearchCriteria() {
        List<String> errors = new ArrayList<>();
        
        // Cek setiap field yang wajib diisi
        if (fieldTanggal.getText().trim().isEmpty()) {
            errors.add("• Tanggal harus diisi");
        }
        
        if (fieldKapasitas.getText().trim().isEmpty()) {
            errors.add("• Kapasitas minimum harus diisi");
        }
        
        if (fieldJamMulai.getText().trim().isEmpty()) {
            errors.add("• Jam mulai harus diisi");
        }
        
        if (fieldJamSelesai.getText().trim().isEmpty()) {
            errors.add("• Jam selesai harus diisi");
        }
        
        // Cek apakah setidaknya satu jenis ruangan dipilih
        if (!checkKelas.isSelected() && !checkLaboratorium.isSelected()) {
            errors.add("• Pilih setidaknya satu jenis ruangan (Kelas atau Laboratorium)");
        }
        
        // Jika ada error, tampilkan semua sekaligus
        if (!errors.isEmpty()) {
            String errorMessage = "Mohon lengkapi field berikut:\n\n" +
                String.join("\n", errors) +
                "\n\nSemua field harus diisi untuk melakukan pencarian.";
            
            JOptionPane.showMessageDialog(this, 
                errorMessage,
                "Field Pencarian Tidak Lengkap",
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Validasi format tanggal
        String tanggal = fieldTanggal.getText().trim();
        if (!isValidDateFormat(tanggal)) {
            JOptionPane.showMessageDialog(this, 
                "Format tanggal tidak valid. Gunakan format dd/mm/yy (contoh: 25/11/25)",
                "Format Tanggal Salah",
                JOptionPane.ERROR_MESSAGE);
            fieldTanggal.requestFocus();
            return false;
        }
        
        // Validasi format jam
        String jamMulai = fieldJamMulai.getText().trim();
        String jamSelesai = fieldJamSelesai.getText().trim();
        
        if (!isValidTimeFormat(jamMulai)) {
            JOptionPane.showMessageDialog(this, 
                "Format jam mulai tidak valid. Gunakan format HH:MM (contoh: 08:00)",
                "Format Jam Salah",
                JOptionPane.ERROR_MESSAGE);
            fieldJamMulai.requestFocus();
            return false;
        }
        
        if (!isValidTimeFormat(jamSelesai)) {
            JOptionPane.showMessageDialog(this, 
                "Format jam selesai tidak valid. Gunakan format HH:MM (contoh: 10:30)",
                "Format Jam Salah",
                JOptionPane.ERROR_MESSAGE);
            fieldJamSelesai.requestFocus();
            return false;
        }
        
        // Validasi jam selesai harus setelah jam mulai
        if (!isEndTimeAfterStartTime(jamMulai, jamSelesai)) {
            JOptionPane.showMessageDialog(this, 
                "Jam selesai harus setelah jam mulai",
                "Jam Tidak Valid",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validasi kapasitas
        try {
            int kapasitas = Integer.parseInt(fieldKapasitas.getText().trim());
            if (kapasitas <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "Kapasitas harus lebih dari 0!", 
                    "Kapasitas Tidak Valid", 
                    JOptionPane.ERROR_MESSAGE);
                fieldKapasitas.requestFocus();
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Kapasitas harus berupa angka!", 
                "Format Kapasitas Salah", 
                JOptionPane.ERROR_MESSAGE);
            fieldKapasitas.requestFocus();
            return false;
        }
        
        return true;
    }
    
    // METHOD BARU: Validasi format tanggal
    private boolean isValidDateFormat(String date) {
        // Format sederhana: dd/mm/yy
        return date.matches("^\\d{1,2}/\\d{1,2}/\\d{2,4}$");
    }
    
    // METHOD BARU: Validasi format waktu
    private boolean isValidTimeFormat(String time) {
        // Format: HH:MM
        return time.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$");
    }
    
    // METHOD BARU: Validasi jam selesai setelah jam mulai
    private boolean isEndTimeAfterStartTime(String startTime, String endTime) {
        try {
            int startMinutes = convertTimeToMinutes(startTime);
            int endMinutes = convertTimeToMinutes(endTime);
            return endMinutes > startMinutes;
        } catch (Exception e) {
            return false;
        }
    }
    
    // METHOD BARU: Konversi waktu ke menit
    private int convertTimeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    private JScrollPane createResultsPanel() {
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(0, 4, 8, 8));
        resultsPanel.setOpaque(false);
        resultsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(new TitledBorder("Hasil Pencarian"));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private void updateSearchResults() {
        resultsPanel.removeAll();
       
        List<Ruang> semuaRuangan = mainApp.getDataManager().getAllRuangan();
        List<Ruang> ruanganFiltered = filterRuangan(semuaRuangan);
        
        if (ruanganFiltered.isEmpty()) {
            JLabel labelTidakAda = new JLabel("Tidak ada ruangan yang ditemukan dengan kriteria yang diminta.");
            labelTidakAda.setHorizontalAlignment(SwingConstants.CENTER);
            labelTidakAda.setForeground(Color.RED);
            labelTidakAda.setFont(new Font("SansSerif", Font.BOLD, 14));
            resultsPanel.add(labelTidakAda);
        } else {
            for (Ruang ruang : ruanganFiltered) {
                resultsPanel.add(createRoomCard(ruang));
            }
        }
        
        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    private List<Ruang> filterRuangan(List<Ruang> semuaRuangan) {
        List<Ruang> hasil = new ArrayList<>();
        String kapasitasText = fieldKapasitas.getText().trim();
        
        try {
            int kapasitasMin = kapasitasText.isEmpty() ? 0 : Integer.parseInt(kapasitasText);
            
            for (Ruang ruang : semuaRuangan) {
                // Filter status aktif
                if (!"Aktif".equals(ruang.getStatus())) {
                    continue;
                }
                
                // Filter berdasarkan kapasitas
                if (kapasitasMin > 0 && ruang.getKapasitas() < kapasitasMin) {
                    continue;
                }
                
                // Filter berdasarkan jenis ruangan
                boolean isKelas = "Kelas".equals(ruang.getJenis());
                boolean isLaboratorium = "Laboratorium".equals(ruang.getJenis());
                
                if ((isKelas && !checkKelas.isSelected()) || 
                    (isLaboratorium && !checkLaboratorium.isSelected())) {
                    continue;
                }
                
                hasil.add(ruang);
            }
            
            // Jika mencari kapasitas 3000+ dan tidak ditemukan, tampilkan pesan
            if (kapasitasMin >= 3000 && hasil.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Tidak ada ruangan dengan kapasitas " + kapasitasMin + " orang atau lebih.", 
                    "Ruangan Tidak Ditemukan", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            // Exception ini seharusnya tidak terjadi karena sudah divalidasi sebelumnya
            JOptionPane.showMessageDialog(this, 
                "Kapasitas harus berupa angka!", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        
        return hasil;
    }

    private JPanel createRoomCard(Ruang ruang) {
        JPanel card = new JPanel(new BorderLayout(4, 4));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#cccccc"), 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(150, 120));
        
        // Header dengan kode ruangan dan nama
        JPanel headerPanel = createHeaderPanel(ruang);
        
        // Detail ruangan - menggunakan GridLayout yang lebih compact
        JPanel detailPanel = createDetailPanel(ruang);
        
        // Status tersedia di bagian bawah
        JPanel footerPanel = createFooterPanel();
        
        card.add(headerPanel, BorderLayout.NORTH);
        card.add(detailPanel, BorderLayout.CENTER);
        card.add(footerPanel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private JPanel createHeaderPanel(Ruang ruang) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        
        JLabel kodeLabel = new JLabel(ruang.getKodeRuang());
        kodeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        kodeLabel.setForeground(Color.decode("#2c3e50"));
        
        JLabel namaLabel = new JLabel(shortenText(ruang.getNamaRuang(), 12));
        namaLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        namaLabel.setForeground(Color.decode("#7f8c8d"));
        
        headerPanel.add(kodeLabel, BorderLayout.WEST);
        headerPanel.add(namaLabel, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    private JPanel createDetailPanel(Ruang ruang) {
        JPanel detailPanel = new JPanel(new GridLayout(4, 1, 2, 2));
        detailPanel.setOpaque(false);
        detailPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        
        // Baris 1: Kapasitas
        JLabel kapasitasLabel = createDetailLabel("Kapasitas: " + ruang.getKapasitas() + " org", Font.BOLD, 11);
        
        // Baris 2: Jenis
        JLabel jenisLabel = createDetailLabel("Jenis: " + ruang.getJenis(), Font.PLAIN, 10);
        
        // Baris 3: Fasilitas (sedikit disingkat)
        String fasilitasText = buildReadableFacilitiesText(ruang);
        JLabel fasilitasLabel = createDetailLabel(fasilitasText, Font.PLAIN, 9);
        
        // Baris 4: Status dan Lantai
        JPanel statusLantaiPanel = new JPanel(new GridLayout(1, 2, 3, 0));
        statusLantaiPanel.setOpaque(false);
        JLabel statusLabel = createDetailLabel("Aktif", Font.BOLD, 9);
        statusLabel.setForeground(Color.decode("#27ae60"));
        JLabel lantaiLabel = createDetailLabel("Lantai " + getLantaiFromKode(ruang.getKodeRuang()), Font.PLAIN, 9);
        
        statusLantaiPanel.add(statusLabel);
        statusLantaiPanel.add(lantaiLabel);
        
        detailPanel.add(kapasitasLabel);
        detailPanel.add(jenisLabel);
        detailPanel.add(fasilitasLabel);
        detailPanel.add(statusLantaiPanel);
        
        return detailPanel;
    }
    
    private String buildReadableFacilitiesText(Ruang ruang) {
        StringBuilder fasilitas = new StringBuilder("Fasilitas: ");
        Ruang.Fasilitas fasil = ruang.getFasilitas();
        if (fasil != null) {
            List<String> fasilList = new ArrayList<>();
            if (fasil.isProyektor()) fasilList.add("Proyektor");
            if (fasil.isAc()) fasilList.add("AC");
            if (fasil.isTv()) fasilList.add("TV");
            
            if (!fasilList.isEmpty()) {
                if (fasilList.size() > 2) {
                    fasilitas.append(fasilList.get(0)).append(",").append(fasilList.get(1)).append("...");
                } else {
                    fasilitas.append(String.join(",", fasilList));
                }
            } else {
                fasilitas.append("-");
            }
        } else {
            fasilitas.append("-");
        }
        
        return fasilitas.toString();
    }
    
    private String shortenText(String text, int maxLength) {
        if (text == null) return "";
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 2) + "..";
    }
    
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setOpaque(false);
        
        JLabel statusLabel = new JLabel("TERSEDIA");
        statusLabel.setForeground(Color.decode("#27ae60"));
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        footerPanel.add(statusLabel, BorderLayout.EAST);
        
        return footerPanel;
    }
    
    private String getLantaiFromKode(String kodeRuang) {
        // Ekstrak lantai dari kode ruangan (misal: A-101 -> Lantai 1)
        if (kodeRuang != null && kodeRuang.length() >= 4) {
            char lantaiChar = kodeRuang.charAt(kodeRuang.length() - 3);
            if (Character.isDigit(lantaiChar)) {
                return String.valueOf(lantaiChar);
            }
        }
        return "1";
    }
    
    private JLabel createDetailLabel(String text, int fontStyle, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", fontStyle, fontSize));
        label.setForeground(Color.decode("#555555"));
        return label;
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
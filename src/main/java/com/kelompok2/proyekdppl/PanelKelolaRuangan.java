package com.kelompok2.proyekdppl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelKelolaRuangan extends JPanel {

    private MainApp mainApp;
    private JTable tableRuangan;
    private DefaultTableModel tableModel;
    
    // Form Components
    private JTextField fieldKode, fieldNama, fieldKapasitas;
    private JComboBox<String> comboJenis, comboStatus;
    private JCheckBox checkProyektor, checkAC, checkTV;
    
    // Mode Form: "EDIT" atau "TAMBAH"
    private boolean isEditMode = false;

    public PanelKelolaRuangan(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.decode("#f0f2f5"));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Header 
        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
        headerPanel.setOpaque(false);
        JLabel title = new JLabel("Kelola Data Ruangan");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        // Tombol Tambah Baru
        JButton btnTambahBaru = new JButton("+ Tambah Ruangan");
        btnTambahBaru.setBackground(Color.decode("#28a745"));
        btnTambahBaru.setForeground(Color.WHITE);
        btnTambahBaru.setFocusPainted(false);
        btnTambahBaru.addActionListener(e -> siapkanFormTambah());
        
        headerPanel.add(title, BorderLayout.WEST);
        headerPanel.add(btnTambahBaru, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // Konten (Split Pane: Atas Tabel, Bawah Form)
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.5); 
        splitPane.setBorder(null);
        splitPane.setOpaque(false);

        splitPane.setTopComponent(createTablePanel());
        splitPane.setBottomComponent(createEditFormPanel());

        add(splitPane, BorderLayout.CENTER);
        
        loadDataRuangan(); 
    }

    private JScrollPane createTablePanel() {
        String[] columnNames = {"Kode", "Nama", "Kapasitas", "Jenis", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tableRuangan = new JTable(tableModel);
        tableRuangan.setRowHeight(25);
        tableRuangan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tableRuangan.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tableRuangan.getSelectedRow();
                if (selectedRow != -1) {
                    populateEditForm(selectedRow);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableRuangan);
        scrollPane.setBorder(new TitledBorder("Daftar Ruangan (Klik untuk Edit)"));
        return scrollPane;
    }

    private JPanel createEditFormPanel() {
        JPanel formPanel = new JPanel(new BorderLayout(10, 10));
        formPanel.setBorder(new TitledBorder("Form Ruangan"));
        formPanel.setBackground(Color.WHITE);

        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2, 10, 10)); 
        fieldsPanel.setOpaque(false);
        fieldsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        fieldsPanel.add(new JLabel("Kode Ruangan (Unik):"));
        fieldKode = new JTextField();
        fieldsPanel.add(fieldKode);
        
        fieldsPanel.add(new JLabel("Nama Ruangan:"));
        fieldNama = new JTextField();
        fieldsPanel.add(fieldNama);
        
        fieldsPanel.add(new JLabel("Kapasitas:"));
        fieldKapasitas = new JTextField();
        fieldsPanel.add(fieldKapasitas);
        
        fieldsPanel.add(new JLabel("Jenis:"));
        comboJenis = new JComboBox<>(new String[]{"Kelas", "Laboratorium"});
        fieldsPanel.add(comboJenis);
        
        fieldsPanel.add(new JLabel("Status:"));
        comboStatus = new JComboBox<>(new String[]{"Aktif", "Tidak Aktif"});
        fieldsPanel.add(comboStatus);
        
        // Fasilitas
        JPanel fasilitasPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fasilitasPanel.setOpaque(false);
        checkProyektor = new JCheckBox("Proyektor");
        checkAC = new JCheckBox("AC");
        checkTV = new JCheckBox("TV");
        fasilitasPanel.add(checkProyektor);
        fasilitasPanel.add(checkAC);
        fasilitasPanel.add(checkTV);
        
        fieldsPanel.add(new JLabel("Fasilitas:"));
        fieldsPanel.add(fasilitasPanel);

        formPanel.add(fieldsPanel, BorderLayout.CENTER);
        
        // Tombol Aksi
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        
        JButton btnClear = new JButton("Reset Form");
        btnClear.addActionListener(e -> siapkanFormTambah());
        
        JButton simpanButton = new JButton("Simpan Data");
        simpanButton.putClientProperty("JButton.buttonType", "roundRect");
        simpanButton.setBackground(Color.decode("#4285F4"));
        simpanButton.setForeground(Color.WHITE);
        simpanButton.addActionListener(e -> prosesSimpan());
        
        btnPanel.add(btnClear);
        btnPanel.add(simpanButton);
        
        formPanel.add(btnPanel, BorderLayout.SOUTH);
        return formPanel;
    }
    
    private void loadDataRuangan() {
        tableModel.setRowCount(0);
        List<Ruang> ruanganList = mainApp.getDataManager().getAllRuangan();
        if(ruanganList != null) {
            for (Ruang r : ruanganList) {
                tableModel.addRow(new Object[]{
                    r.getKodeRuang(), r.getNamaRuang(), r.getKapasitas(), r.getJenis(), r.getStatus()
                });
            }
        }
    }
    
    // Mode Tambah Baru
    private void siapkanFormTambah() {
        isEditMode = false;
        tableRuangan.clearSelection();
        
        fieldKode.setText("");
        fieldKode.setEditable(true); // Kode bisa diedit kalau baru
        fieldKode.setBackground(Color.WHITE);
        
        fieldNama.setText("");
        fieldKapasitas.setText("");
        comboJenis.setSelectedIndex(0);
        comboStatus.setSelectedIndex(0);
        checkProyektor.setSelected(false);
        checkAC.setSelected(false);
        checkTV.setSelected(false);
    }
    
    // Mode Edit
    private void populateEditForm(int selectedRow) {
        isEditMode = true;
        String kode = (String) tableModel.getValueAt(selectedRow, 0);
        Ruang r = mainApp.getDataManager().getRuanganByKode(kode);
        
        if (r == null) return;
        
        fieldKode.setText(r.getKodeRuang());
        fieldKode.setEditable(false); // Kode TIDAK bisa diedit
        fieldKode.setBackground(new Color(230, 230, 230));
        
        fieldNama.setText(r.getNamaRuang());
        fieldKapasitas.setText(String.valueOf(r.getKapasitas()));
        comboJenis.setSelectedItem(r.getJenis());
        comboStatus.setSelectedItem(r.getStatus());
       
        // Cek Fasilitas Null Safety
        if(r.getFasilitas() != null) {
            checkProyektor.setSelected(r.getFasilitas().isProyektor());
            checkAC.setSelected(r.getFasilitas().isAc());
            checkTV.setSelected(r.getFasilitas().isTv());
        } else {
            checkProyektor.setSelected(false);
            checkAC.setSelected(false);
            checkTV.setSelected(false);
        }
    }
    
    private void prosesSimpan() {
        // Validasi Dasar
        if (fieldKode.getText().trim().isEmpty() || fieldNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kode dan Nama Ruangan wajib diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int kapasitas;
        try {
            kapasitas = Integer.parseInt(fieldKapasitas.getText().trim());
            if (kapasitas <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Kapasitas harus angka > 0", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Buat Object Fasilitas
        Ruang.Fasilitas fas = new Ruang.Fasilitas(
            checkAC.isSelected(), 
            checkProyektor.isSelected(), 
            checkTV.isSelected()
        );
        
        // Buat Object Ruangan Baru
        Ruang rBaru = new Ruang(
            fieldKode.getText().trim(),
            fieldNama.getText().trim(),
            kapasitas,
            (String) comboJenis.getSelectedItem(),
            (String) comboStatus.getSelectedItem(),
            fas
        );
        
        if (isEditMode) {
            // --- LOGIKA UPDATE ---
            if (mainApp.getDataManager().updateRuangan(rBaru)) {
                JOptionPane.showMessageDialog(this, "Data Berhasil Diupdate!");
                loadDataRuangan();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal Update!");
            }
        } else {
            // --- LOGIKA TAMBAH BARU ---
            // Cek Kode Unik dulu
            if (mainApp.getDataManager().getRuanganByKode(rBaru.getKodeRuang()) != null) {
                JOptionPane.showMessageDialog(this, "Kode Ruangan sudah ada! Gunakan kode lain.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            mainApp.getDataManager().addRuangan(rBaru);
            JOptionPane.showMessageDialog(this, "Ruangan Baru Berhasil Ditambahkan!");
            loadDataRuangan();
            siapkanFormTambah(); // Reset form lagi
        }
    }
}
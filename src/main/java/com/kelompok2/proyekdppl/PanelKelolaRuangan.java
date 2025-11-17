package com.kelompok2.proyekdppl;

// File: PanelKelolaRuangan.java
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
    
    // Form Edit
    private JTextField fieldKode, fieldNama, fieldKapasitas;
    private JComboBox<String> comboJenis, comboStatus;
    private JCheckBox checkProyektor, checkAC, checkTV;

    public PanelKelolaRuangan(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.decode("#f0f2f5"));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // 1. Header (Judul + Search)
        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
        headerPanel.setOpaque(false);
        JLabel title = new JLabel("Kelola Data Ruangan");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        JTextField searchField = new JTextField("Cari ruangan...");
        searchField.putClientProperty("JComponent.roundRect", true);
        
        headerPanel.add(title, BorderLayout.NORTH);
        headerPanel.add(searchField, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // 2. Konten (Tabel dan Form)
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.6); // 60% untuk tabel, 40% untuk form
        splitPane.setBorder(null);
        splitPane.setOpaque(false);

        // 2a. Panel Tabel
        splitPane.setTopComponent(createTablePanel());
        
        // 2b. Panel Form Edit
        splitPane.setBottomComponent(createEditFormPanel());

        add(splitPane, BorderLayout.CENTER);
        
        loadDataRuangan(); // Panggil method untuk isi data
    }

    private JScrollPane createTablePanel() {
        String[] columnNames = {"Kode", "Nama", "Kapasitas", "Jenis", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Buat tabel read-only
            }
        };
        tableRuangan = new JTable(tableModel);
        tableRuangan.setRowHeight(25);
        tableRuangan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Tambahkan listener: Saat baris di-klik, isi data ke form
        tableRuangan.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tableRuangan.getSelectedRow();
                if (selectedRow != -1) {
                    populateEditForm(selectedRow);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableRuangan);
        scrollPane.setBorder(new TitledBorder("Daftar Ruangan"));
        return scrollPane;
    }

    private JPanel createEditFormPanel() {
        JPanel formPanel = new JPanel(new BorderLayout(10, 10));
        formPanel.setBorder(new TitledBorder("Edit Ruangan Terpilih"));
        formPanel.setBackground(Color.WHITE);

        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // 0 baris, 2 kolom
        fieldsPanel.setOpaque(false);
        fieldsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        fieldsPanel.add(new JLabel("Kode ruangan:"));
        fieldKode = new JTextField();
        fieldKode.setEditable(false); // Kode ruang biasanya tidak bisa diedit
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
        
        JButton simpanButton = new JButton("Simpan Perubahan");
        simpanButton.putClientProperty("JButton.buttonType", "roundRect");
        simpanButton.setBackground(Color.decode("#4285F4"));
        simpanButton.setForeground(Color.WHITE);
        
        // Nanti tambahkan ActionListener untuk simpan
        simpanButton.addActionListener(e -> simpanPerubahan());
        
        formPanel.add(simpanButton, BorderLayout.SOUTH);
        return formPanel;
    }
    
    private void loadDataRuangan() {
        // Hapus data lama
        tableModel.setRowCount(0);
        
        List<Ruang> ruanganList = mainApp.getDataManager().getAllRuangan();
        for (Ruang r : ruanganList) {
            tableModel.addRow(new Object[]{
                r.getKodeRuang(),
                r.getNamaRuang(),
                r.getKapasitas(),
                r.getJenis(),
                r.getStatus()
            });
        }
    }
    
    private void populateEditForm(int selectedRow) {
        String kode = (String) tableModel.getValueAt(selectedRow, 0);
        
        // Cari object Ruang asli dari DataManager
        Ruang r = mainApp.getDataManager().getAllRuangan().stream()
            .filter(ruang -> ruang.getKodeRuang().equals(kode))
            .findFirst().orElse(null);
            
        if (r == null) return;
        
        fieldKode.setText(r.getKodeRuang());
        fieldNama.setText(r.getNamaRuang());
        fieldKapasitas.setText(String.valueOf(r.getKapasitas()));
        comboJenis.setSelectedItem(r.getJenis());
        comboStatus.setSelectedItem(r.getStatus());
        
        // Set Fasilitas
        checkProyektor.setSelected(r.getFasilitas().isProyektor());
        checkAC.setSelected(r.getFasilitas().isAc());
        checkTV.setSelected(r.getFasilitas().isTv());
    }
    
    private void simpanPerubahan() {
        // Logika untuk simpan (Tulis ke JSON)
        // Ini lebih kompleks, untuk sekarang kita tampilkan pesan
        JOptionPane.showMessageDialog(this, 
            "Perubahan untuk " + fieldKode.getText() + " disimpan (WIP)!", 
            "Simpan", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // Idealnya:
        // 1. Ambil data dari form
        // 2. Update object Ruang di list DataManager
        // 3. Panggil method DataManager.saveRuanganToJson()
        // 4. Panggil loadDataRuangan() untuk refresh tabel
    }
}
package com.kelompok2.proyekdppl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class PanelKelolaReservasi extends JPanel {

    private MainApp mainApp;
    private JPanel listPanel; 
    private String filterStatus = "Menunggu";

    public PanelKelolaReservasi(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.decode("#f0f2f5"));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        
        add(createHeaderPanel(), BorderLayout.NORTH);
        
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(new TitledBorder(filterStatus));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollPane, BorderLayout.CENTER);
        
        loadDataReservasi();

        this.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                loadDataReservasi();
            }
            @Override public void ancestorRemoved(AncestorEvent event) {}
            @Override public void ancestorMoved(AncestorEvent event) {}
        });
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
        headerPanel.setOpaque(false);
        
        // --- JUDUL & TOMBOL TAMBAH ---
        JPanel topRow = new JPanel(new BorderLayout());
        topRow.setOpaque(false);
        
        JLabel title = new JLabel("Kelola Reservasi");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        JButton btnTambah = new JButton("+ Buat Baru");
        btnTambah.setBackground(Color.decode("#007BFF"));
        btnTambah.setForeground(Color.WHITE);
        btnTambah.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnTambah.setFocusPainted(false);
        
        // Listener Tombol Tambah
        btnTambah.addActionListener(e -> showDialogTambah());
        
        topRow.add(title, BorderLayout.WEST);
        topRow.add(btnTambah, BorderLayout.EAST);
        
        // --- FILTER ---
        JPanel filterPanel = new JPanel(new BorderLayout(10, 10));
        filterPanel.setOpaque(false);
        
        JTextField searchField = new JTextField("Cari reservasi...");
        searchField.putClientProperty("JComponent.roundRect", true);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        buttonPanel.setOpaque(false);
        ButtonGroup filterGroup = new ButtonGroup();
        
        JToggleButton btnSemua = new JToggleButton("Semua");
        JToggleButton btnMenunggu = new JToggleButton("Menunggu", true);
        JToggleButton btnDisetujui = new JToggleButton("Disetujui");
        
        filterGroup.add(btnSemua);
        filterGroup.add(btnMenunggu);
        filterGroup.add(btnDisetujui);
        
        buttonPanel.add(btnSemua);
        buttonPanel.add(btnMenunggu);
        buttonPanel.add(btnDisetujui);
        
        btnSemua.addActionListener(e -> { filterStatus = "Semua"; loadDataReservasi(); });
        btnMenunggu.addActionListener(e -> { filterStatus = "Menunggu"; loadDataReservasi(); });
        btnDisetujui.addActionListener(e -> { filterStatus = "Disetujui"; loadDataReservasi(); });

        filterPanel.add(searchField, BorderLayout.NORTH);
        filterPanel.add(buttonPanel, BorderLayout.CENTER);
        
        headerPanel.add(topRow, BorderLayout.NORTH); // Ubah disini biar tombol tambah di atas
        headerPanel.add(filterPanel, BorderLayout.CENTER);
        
        return headerPanel;
    }
    
    // --- FITUR BARU: DIALOG TAMBAH RESERVASI ---
    private void showDialogTambah() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Buat Reservasi Manual", true);
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        
        JPanel form = new JPanel(new GridLayout(7, 2, 10, 10));
        form.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JTextField txtNIM = new JTextField();
        JComboBox<String> cmbRuangan = new JComboBox<>();
        JTextField txtTanggal = new JTextField("YYYY-MM-DD");
        JTextField txtMulai = new JTextField("08:00");
        JTextField txtSelesai = new JTextField("10:00");
        JTextField txtKeperluan = new JTextField("Kegiatan Akademik");
        
        // Isi Combo Ruangan
        List<Ruang> listR = mainApp.getDataManager().getAllRuangan();
        for(Ruang r : listR) {
            cmbRuangan.addItem(r.getKodeRuang() + " (" + r.getNamaRuang() + ")");
        }
        
        form.add(new JLabel("NIM Mahasiswa:"));
        form.add(txtNIM);
        form.add(new JLabel("Pilih Ruangan:"));
        form.add(cmbRuangan);
        form.add(new JLabel("Tanggal:"));
        form.add(txtTanggal);
        form.add(new JLabel("Jam Mulai:"));
        form.add(txtMulai);
        form.add(new JLabel("Jam Selesai:"));
        form.add(txtSelesai);
        form.add(new JLabel("Keperluan:"));
        form.add(txtKeperluan);
        
        JButton btnSimpan = new JButton("Simpan Reservasi");
        btnSimpan.setBackground(Color.decode("#28a745"));
        btnSimpan.setForeground(Color.WHITE);
        
        btnSimpan.addActionListener(e -> {
            if(txtNIM.getText().isEmpty() || txtTanggal.getText().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Data tidak lengkap!");
                return;
            }
            
            String selectedRuangFull = (String) cmbRuangan.getSelectedItem();
            String kodeRuang = selectedRuangFull.split(" ")[0];
            
            Reservasi rBaru = new Reservasi(
                "RES-ADM-" + System.currentTimeMillis(),
                txtNIM.getText(),
                kodeRuang,
                txtTanggal.getText(),
                txtMulai.getText(),
                txtSelesai.getText(),
                txtKeperluan.getText() + " (Via Admin)",
                "Disetujui" // ADMIN POWER: Langsung disetujui!
            );
            
            mainApp.getDataManager().addReservasi(rBaru);
            JOptionPane.showMessageDialog(dialog, "Berhasil ditambahkan!");
            loadDataReservasi();
            dialog.dispose();
        });
        
        dialog.add(new JLabel("  Form Input Admin"), BorderLayout.NORTH);
        dialog.add(form, BorderLayout.CENTER);
        dialog.add(btnSimpan, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    private void loadDataReservasi() {
        listPanel.removeAll();
        List<Reservasi> reservasiList = mainApp.getDataManager().getAllReservasi();
        
        if (reservasiList == null || reservasiList.isEmpty()) {
            listPanel.add(new JLabel("Belum ada data reservasi."));
        } else {
            List<Reservasi> filteredList = reservasiList.stream()
                .filter(r -> filterStatus.equals("Semua") || r.getStatus().equalsIgnoreCase(filterStatus))
                .collect(Collectors.toList());

            if(listPanel.getParent() != null && listPanel.getParent().getParent() instanceof JScrollPane) {
                 JScrollPane scrollPane = (JScrollPane) listPanel.getParent().getParent();
                 scrollPane.setBorder(new TitledBorder(filterStatus + " (" + filteredList.size() + ")"));
            }

            for (Reservasi r : filteredList) {
                listPanel.add(createReservasiCard(r));
                listPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
            }
        }
        listPanel.revalidate();
        listPanel.repaint();
    }
    
    private JPanel createReservasiCard(Reservasi r) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#cccccc"), 1, true),
            new EmptyBorder(10, 10, 10, 10)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130)); 
        
        User u = mainApp.getDataManager().getUserByNIM(r.getNimPemesan());
        String nama = (u != null) ? u.getNama() : "NIM: " + r.getNimPemesan();
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        
        infoPanel.add(new JLabel("<html><b>" + nama + "</b></html>"));
        infoPanel.add(new JLabel(r.getKeperluan()));
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        JPanel waktuPanel = new JPanel();
        waktuPanel.setLayout(new BoxLayout(waktuPanel, BoxLayout.Y_AXIS));
        waktuPanel.setOpaque(false);
        
        waktuPanel.add(new JLabel("Ruang: " + r.getKodeRuang()));
        waktuPanel.add(new JLabel("Tgl: " + r.getTanggal()));
        waktuPanel.add(new JLabel(r.getJamMulai() + " - " + r.getJamSelesai()));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        buttonPanel.setOpaque(false);
        JButton btnSetuju = new JButton("Setujui");
        JButton btnTolak = new JButton("Tolak");
        
        btnSetuju.setBackground(new Color(40, 167, 69)); btnSetuju.setForeground(Color.WHITE);
        btnTolak.setBackground(new Color(220, 53, 69));  btnTolak.setForeground(Color.WHITE);
        
        buttonPanel.add(btnSetuju);
        buttonPanel.add(btnTolak);
        
        if (!r.getStatus().equalsIgnoreCase("Menunggu")) {
            btnSetuju.setVisible(false);
            btnTolak.setVisible(false);
            JLabel lblStatus = new JLabel(r.getStatus().toUpperCase());
            lblStatus.setFont(new Font("SansSerif", Font.BOLD, 12));
            lblStatus.setForeground(r.getStatus().equalsIgnoreCase("Disetujui") ? new Color(40, 167, 69) : Color.RED);
            buttonPanel.add(lblStatus);
        }
        
        btnSetuju.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Setujui?", "Konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if(mainApp.getDataManager().updateStatusReservasi(r.getIdReservasi(), "Disetujui")) {
                    JOptionPane.showMessageDialog(this, "Berhasil!");
                    loadDataReservasi();
                }
            }
        });
        
        btnTolak.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Tolak?", "Konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if(mainApp.getDataManager().updateStatusReservasi(r.getIdReservasi(), "Ditolak")) {
                    JOptionPane.showMessageDialog(this, "Ditolak.");
                    loadDataReservasi();
                }
            }
        });

        card.add(infoPanel, BorderLayout.NORTH);
        card.add(waktuPanel, BorderLayout.EAST);
        card.add(buttonPanel, BorderLayout.SOUTH);
        
        return card;
    }
}
package com.kelompok2.proyekdppl;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PanelReservasiMahasiswa extends JPanel {

    private MainApp mainApp;
    private CardLayout cardLayout;
    private PanelReservasiForm formPanel; 
    
    // TAMBAHAN: Variabel untuk menyimpan NIM mahasiswa yang login
    private String currentUserNIM = "";

    public PanelReservasiMahasiswa(MainApp mainApp) {
        this.mainApp = mainApp;
        
        cardLayout = new CardLayout();
        setLayout(cardLayout); 

        JPanel pilihanPanel = createPilihanPanel();
        formPanel = new PanelReservasiForm(); 

        add(pilihanPanel, "PILIHAN");
        add(formPanel, "FORM");
        
        showSubPanel("PILIHAN");
    }
    
    // Method ini WAJIB dipanggil dari MainApp saat login berhasil
    // Agar reservasi tersimpan atas nama mahasiswa yang benar
    public void setUserNIM(String nim) {
        this.currentUserNIM = nim;
    }

    private JPanel createPilihanPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10)); 
        panel.setBackground(Color.decode("#f0f2f5"));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JLabel title = new JLabel("Pilihan Reservasi");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
       
        JPanel wrapper = new JPanel(new BorderLayout(10, 10));
        wrapper.setOpaque(false);
        wrapper.add(title, BorderLayout.NORTH);
        wrapper.add(panel, BorderLayout.CENTER);

        JButton btnKelas = new JButton("Kelas");
        btnKelas.setFont(new Font("SansSerif", Font.BOLD, 28));
              
        JButton btnLab = new JButton("Laboratorium");
        btnLab.setFont(new Font("SansSerif", Font.BOLD, 28));

        panel.add(btnKelas);
        panel.add(btnLab);
      
        btnKelas.addActionListener(e -> {
            formPanel.setJenisReservasi("Kelas"); 
            showSubPanel("FORM");
        });
        
        btnLab.addActionListener(e -> {
            formPanel.setJenisReservasi("Laboratorium"); 
            showSubPanel("FORM"); 
        });

        return wrapper;
    }
    
    private void showSubPanel(String panelName) {
        cardLayout.show(this, panelName);
    }
    
    private class PanelReservasiForm extends JPanel {
        
        private JComboBox<String> comboFakultas;
        private JComboBox<String> comboKelas;
        private JTextField fieldTanggal, fieldMulai, fieldSelesai, fieldKapasitas, fieldJudul;
        private JTextArea areaDeskripsi;
        private JCheckBox checkSetuju;
        private JLabel formTitle;

        PanelReservasiForm() {
            setLayout(new BorderLayout(10, 10));
            setBackground(Color.decode("#f0f2f5"));
            setBorder(new EmptyBorder(15, 15, 15, 15));
            
            formTitle = new JLabel("Reservasi Kelas"); 
            formTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
            add(formTitle, BorderLayout.NORTH);
            
            JPanel formPanel = new JPanel();
            formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
            formPanel.setOpaque(false);
            formPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
           
            comboFakultas = new JComboBox<>(new String[]{"Pilih Fakultas", "Teknik", "MIPA"});
            fieldTanggal = new JTextField();
            fieldMulai = new JTextField();
            fieldSelesai = new JTextField();
            fieldKapasitas = new JTextField();
            comboKelas = new JComboBox<>(); 
            fieldJudul = new JTextField();
            areaDeskripsi = new JTextArea(4, 30);
            checkSetuju = new JCheckBox("Saya menyetujui seluruh aturan reservasi");
            
            formPanel.add(new LabeledTextField("Fakultas", comboFakultas));
            
            JPanel panelWaktu = new JPanel(new GridLayout(1, 3, 5, 0));
            panelWaktu.setOpaque(false);
            panelWaktu.add(new LabeledTextField("Tanggal (YYYY-MM-DD)", fieldTanggal));
            panelWaktu.add(new LabeledTextField("Waktu Mulai (HH:MM)", fieldMulai));
            panelWaktu.add(new LabeledTextField("Waktu Selesai (HH:MM)", fieldSelesai));
            formPanel.add(panelWaktu);
            
            JPanel panelDetail = new JPanel(new GridLayout(1, 2, 10, 0));
            panelDetail.setOpaque(false);
            panelDetail.add(new LabeledTextField("Kapasitas", fieldKapasitas));
            panelDetail.add(new LabeledTextField("Pilih Kelas", comboKelas));
            formPanel.add(panelDetail);
            
            formPanel.add(new LabeledTextField("Judul Kegiatan", fieldJudul));
            formPanel.add(new LabeledTextField("Deskripsi", new JScrollPane(areaDeskripsi)));
            formPanel.add(checkSetuju);
            
            add(formPanel, BorderLayout.CENTER);
            
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            buttonPanel.setOpaque(false);
            JButton btnReservasi = new JButton("Buat Reservasi");
            btnReservasi.putClientProperty("JButton.buttonType", "roundRect");
            btnReservasi.setBackground(Color.decode("#4285F4"));
            btnReservasi.setForeground(Color.WHITE);
            
            JButton btnKembali = new JButton("Kembali");
            
            buttonPanel.add(btnKembali);
            buttonPanel.add(btnReservasi);
            add(buttonPanel, BorderLayout.SOUTH);
            
            btnKembali.addActionListener(e -> showSubPanel("PILIHAN"));
            
            btnReservasi.addActionListener(e -> {
                List<String> errors = new ArrayList<>();
                
                // VALIDASI DASAR
                if (!checkSetuju.isSelected()) errors.add("• Anda harus menyetujui aturan reservasi");
                if (fieldJudul.getText().trim().isEmpty()) errors.add("• Judul kegiatan tidak boleh kosong");
                if (areaDeskripsi.getText().trim().isEmpty()) errors.add("• Deskripsi kegiatan tidak boleh kosong");
                if (comboFakultas.getSelectedIndex() == 0) errors.add("• Silakan pilih fakultas");
                if (comboKelas.getSelectedItem() == null || comboKelas.getSelectedIndex() == -1) errors.add("• Silakan pilih ruangan");
                if (fieldTanggal.getText().trim().isEmpty()) errors.add("• Tanggal tidak boleh kosong");
                
                // Cek Kapasitas
                try {
                    int kap = Integer.parseInt(fieldKapasitas.getText().trim());
                    if (kap <= 0) errors.add("• Kapasitas harus > 0");
                } catch (NumberFormatException ex) {
                    errors.add("• Kapasitas harus angka");
                }
                
                if (!errors.isEmpty()) {
                    JOptionPane.showMessageDialog(this, String.join("\n", errors), "Data Tidak Lengkap", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // ==========================================
                // BAGIAN PENTING: PROSES SIMPAN KE DATA MANAGER
                // ==========================================
               try {
                    // 1. Ambil Kode Ruangan
                    String selectedRuang = (String) comboKelas.getSelectedItem();
                    String kodeRuang = selectedRuang.split(" ")[0]; 
                    
                    // 2. Gabungkan Keperluan
                    String keperluanFull = fieldJudul.getText() + " (" + areaDeskripsi.getText() + ")";
                    
                    // 3. DEBUG: Cek NIM
                    if(currentUserNIM == null || currentUserNIM.isEmpty()) {
                        currentUserNIM = "UNKNOWN"; // Fallback biar gak error
                    }

                    // 4. BUAT OBJECT RESERVASI (GUNAKAN 8 PARAMETER)
                    // Perhatikan: fieldMulai dan fieldSelesai dipisah!
                    Reservasi rBaru = new Reservasi(
                        "RES-" + System.currentTimeMillis(), // 1. ID
                        currentUserNIM,                      // 2. NIM
                        kodeRuang,                           // 3. Ruang
                        fieldTanggal.getText(),              // 4. Tanggal
                        fieldMulai.getText(),                // 5. Jam Mulai
                        fieldSelesai.getText(),              // 6. Jam Selesai
                        keperluanFull,                       // 7. Keperluan
                        "Menunggu"                           // 8. Status
                    );
                    
                    // 5. SIMPAN KE DATABASE
                    mainApp.getDataManager().addReservasi(rBaru);
                    
                    // 6. Sukses
                    JOptionPane.showMessageDialog(this, "Reservasi berhasil diajukan!\nMenunggu persetujuan Admin.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    
                    resetForm();
                    mainApp.showPanel("HOME_MHS"); 
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Gagal menyimpan: " + ex.getMessage());
                }
            });
        }
        
        private void resetForm() {
            fieldJudul.setText("");
            areaDeskripsi.setText("");
            fieldTanggal.setText("");
            fieldMulai.setText("");
            fieldSelesai.setText("");
            checkSetuju.setSelected(false);
        }
        
        public void setJenisReservasi(String jenis) {
            formTitle.setText("Reservasi " + jenis);
            
            comboKelas.removeAllItems();
            List<Ruang> ruanganTersedia = mainApp.getDataManager().getAllRuangan().stream()
                .filter(r -> r.getJenis().equalsIgnoreCase(jenis) && r.getStatus().equalsIgnoreCase("Aktif"))
                .collect(Collectors.toList());
                
            for (Ruang r : ruanganTersedia) {
                comboKelas.addItem(r.getKodeRuang() + " (" + r.getNamaRuang() + ")");
            }
        }
        
        private class LabeledTextField extends JPanel {
            LabeledTextField(String label, JComponent component) {
                setLayout(new BorderLayout(0, 3));
                setOpaque(false);
                setBorder(new EmptyBorder(0, 0, 10, 0));
                add(new JLabel(label), BorderLayout.NORTH);
                add(component, BorderLayout.CENTER);
            }
        }
    }
}
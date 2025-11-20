package com.kelompok2.proyekdppl;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
    private JPanel cardsPanel; 
    
    private PanelReservasiForm formPanel; 

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
            panelWaktu.add(new LabeledTextField("Tanggal", fieldTanggal));
            panelWaktu.add(new LabeledTextField("Waktu Mulai", fieldMulai));
            panelWaktu.add(new LabeledTextField("Waktu Selesai", fieldSelesai));
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
               
                if (!checkSetuju.isSelected()) {
                    JOptionPane.showMessageDialog(this, "Anda harus menyetujui aturan reservasi.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                              
                JOptionPane.showMessageDialog(this, "Reservasi berhasil dibuat dan menunggu persetujuan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                mainApp.showPanel("HOME_MHS"); 
            });
        }
        
        public void setJenisReservasi(String jenis) {
            formTitle.setText("Reservasi " + jenis);
            
            comboKelas.removeAllItems();
            List<Ruang> ruanganTersedia = mainApp.getDataManager().getAllRuangan().stream()
                .filter(r -> r.getJenis().equals(jenis) && r.getStatus().equals("Aktif"))
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
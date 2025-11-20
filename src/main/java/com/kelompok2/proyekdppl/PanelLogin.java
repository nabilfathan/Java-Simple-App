package com.kelompok2.proyekdppl;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelLogin extends JPanel {
    
    private JTextField nimField;
    private JPasswordField passField;
    private JButton loginButton;
    private MainApp mainApp; 

    public PanelLogin(MainApp mainApp) {
        this.mainApp = mainApp;
        
        setLayout(new GridBagLayout());
        setBackground(Color.decode("#f0f2f5")); 
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;

        JPanel loginCard = new JPanel(new GridBagLayout());
        loginCard.setBackground(Color.WHITE);
        loginCard.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        //Judul 
        JLabel titleLabel = new JLabel("SIRUKAN");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginCard.add(titleLabel, gbc);

        gbc.gridy++;
        JLabel subTitleLabel = new JLabel("sistem reservasi ruang kelas dan laboratorium");
        subTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subTitleLabel.setForeground(Color.GRAY);
        subTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.insets = new Insets(0, 20, 20, 20);
        loginCard.add(subTitleLabel, gbc);
        
        gbc.insets = new Insets(10, 20, 10, 20); 

        //NIM
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START; 
        JLabel nimLabel = new JLabel("NIM (Nomor Induk Mahasiswa)");
        loginCard.add(nimLabel, gbc);

        gbc.gridy++;
        nimField = new JTextField(25);
        nimField.putClientProperty("JComponent.roundRect", true);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginCard.add(nimField, gbc);

        //Password
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        JLabel passLabel = new JLabel("Password");
        loginCard.add(passLabel, gbc);

        gbc.gridy++;
        passField = new JPasswordField(25);
        passField.putClientProperty("JComponent.roundRect", true);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginCard.add(passField, gbc);

        //Login
        gbc.gridy++;
        gbc.insets = new Insets(20, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginButton = new JButton("Login");
        loginButton.putClientProperty("JButton.buttonType", "roundRect");
        loginButton.setBackground(Color.decode("#4285F4")); 
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginCard.add(loginButton, gbc);
      
        gbc.gridy++;
        JLabel helpLabel = new JLabel("Butuh bantuan? Hubungi kami");
        helpLabel.setForeground(Color.GRAY);
        helpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginCard.add(helpLabel, gbc);

        add(loginCard, new GridBagConstraints()); 
     
        loginButton.addActionListener(this::onLogin);
    }

    private void onLogin(ActionEvent e) {
        String nim = nimField.getText();
        String password = new String(passField.getPassword());

        User user = mainApp.doLogin(nim, password);

        if (user != null) {      
            if ("Admin".equals(user.getRole())) {
                mainApp.showPanel("ADMIN_HOME");
            } else {
                mainApp.showPanel("MAHASISWA_HOME");
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "NIM atau Password salah!", 
                "Login Gagal", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
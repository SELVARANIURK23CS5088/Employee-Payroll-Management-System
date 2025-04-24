package com.yourcompany.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final JTextField    txtUsername = new JTextField(20);
    private final JPasswordField txtPassword = new JPasswordField(20);
    private final JButton       btnLogin    = new JButton("Login");

    public LoginFrame() {
        super("Login");
        // Nimbus L&F (unchanged) …
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // 1) Use GridBagLayout on the background to center our card
        JPanel background = new JPanel(new GridBagLayout());
        background.setBackground(new Color(33, 150, 243));  // mid-blue
        setContentPane(background);

        // 2) The “card” itself (two-tone)
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(420, 460));
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(0, 0, 0, 0));

        // add card to background, centered
        background.add(card, new GridBagConstraints());

        // 3) Header portion (dark blue)
        JPanel header = new JPanel(new GridBagLayout());
        header.setPreferredSize(new Dimension(420, 120));
        header.setBackground(new Color(25, 118, 210));
        card.add(header, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("Welcome");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblTag = new JLabel("Sign in to your account");
        lblTag.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblTag.setForeground(Color.WHITE);

        GridBagConstraints hgbc = new GridBagConstraints();
        hgbc.insets = new Insets(5, 0, 5, 0);
        header.add(lblTitle, hgbc);
        hgbc.gridy = 1;
        header.add(lblTag, hgbc);

        // 4) Form portion (white)
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(new EmptyBorder(20, 20, 20, 20));
        card.add(form, BorderLayout.CENTER);

        // 5) Fonts & colors
        Font labelFont  = new Font("Segoe UI", Font.BOLD, 18);
        Font fieldFont  = new Font("Segoe UI", Font.PLAIN, 16);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 18);
        Color primary   = new Color(3, 169, 244);
        Color darkGray  = new Color(33, 33, 33);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(labelFont);
        lblUser.setForeground(darkGray);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(labelFont);
        lblPass.setForeground(darkGray);

        txtUsername.setFont(fieldFont);
        txtPassword.setFont(fieldFont);

        btnLogin.setFont(buttonFont);
        btnLogin.setBackground(primary);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        // Give the button a fixed size
        btnLogin.setPreferredSize(new Dimension(200, 45));

        // 6) Layout constraints for the form
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 5, 10);
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        // Username label
        gbc.gridx   = 0;
        gbc.gridy   = 0;
        gbc.weightx = 0.3;
        gbc.anchor  = GridBagConstraints.EAST;
        form.add(lblUser, gbc);

        // Username field
        gbc.gridx   = 1;
        gbc.weightx = 0.7;
        gbc.anchor  = GridBagConstraints.WEST;
        form.add(txtUsername, gbc);

        // Password label
        gbc.gridx   = 0;
        gbc.gridy   = 1;
        gbc.weightx = 0.3;
        gbc.anchor  = GridBagConstraints.EAST;
        form.add(lblPass, gbc);

        // Password field
        gbc.gridx   = 1;
        gbc.weightx = 0.7;
        gbc.anchor  = GridBagConstraints.WEST;
        form.add(txtPassword, gbc);

        // Login button (spans both columns)
        gbc.gridx      = 0;
        gbc.gridy      = 2;
        gbc.gridwidth  = 2;
        gbc.weightx    = 1.0;
        gbc.insets     = new Insets(30, 10, 10, 10);
        gbc.anchor     = GridBagConstraints.CENTER;
        form.add(btnLogin, gbc);

        // 7) Hook up your authenticate() unchanged
        btnLogin.addActionListener(e -> authenticate());
    }

    private void authenticate() {
        String user = txtUsername.getText().trim();
        String pass = new String(txtPassword.getPassword());
        if ("admin".equals(user) && "admin".equals(pass)) {
            new MainFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Invalid username or password.",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new LoginFrame().setVisible(true)
        );
    }
}




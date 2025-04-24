package com.yourcompany.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Employee Payroll Management System");

        // 1) Nimbus Look & Feel
        try {
            for (UIManager.LookAndFeelInfo info : 
                    UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        // 2) Full-screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // 3) Gradient background panel
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int w = getWidth(), h = getHeight();
                // from deep navy to indigo
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(10, 25, 74),
                    0, h, new Color(25, 32, 108)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, w, h);
            }
        };
        background.setLayout(new GridBagLayout()); // to center the card
        setContentPane(background);

        // 4) The central card
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(40, 60, 40, 60));
        background.add(card, new GridBagConstraints());

        // 5) Header label
        JLabel lblTitle = new JLabel("Employee Payroll Management");
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblTitle.setForeground(new Color(33, 33, 33));
        card.add(lblTitle);

        card.add(Box.createRigidArea(new Dimension(0, 30)));

        // 6) Buttons panel
        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.setLayout(new GridLayout(3, 1, 20, 20));

        Font btnFont = new Font("Segoe UI", Font.BOLD, 20);
        Color accent   = new Color(3, 169, 244);

        JButton btnAdd    = new JButton("Add Employee");
        JButton btnCalc   = new JButton("Calculate Payroll");
        JButton btnLogout = new JButton("Logout");

        for (JButton btn : new JButton[]{btnAdd, btnCalc, btnLogout}) {
            btn.setFont(btnFont);
            btn.setBackground(accent);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(260, 50));
            btnPanel.add(btn);
        }

        card.add(btnPanel);

        // 7) Wire up actions (unchanged logic)
        btnAdd.addActionListener(e -> {
            new AddEmployeeFrame().setVisible(true);
            dispose();
        });
        btnCalc.addActionListener(e -> {
            new CalculatePayrollFrame().setVisible(true);
            dispose();
        });
        btnLogout.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new MainFrame().setVisible(true)
        );
    }
}


package com.yourcompany.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalculatePayrollFrame extends JFrame {
    private final JTextField txtEmpId = new JTextField(20);
    private final JTextField txtBasic = new JTextField(20);
    private final JLabel     lblNet   = new JLabel("Net Salary: 0.00");

    public CalculatePayrollFrame() {
        super("Calculate Payroll");

        // 1) Nimbus L&F
        try {
            for (UIManager.LookAndFeelInfo info :
                    UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        // 2) Full‐screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // 3) Gradient background
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int w = getWidth(), h = getHeight();
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(10, 25, 74),
                    0, h, new Color(25, 32, 108)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, w, h);
            }
        };
        background.setLayout(new GridBagLayout());  // center the card
        setContentPane(background);

        // 4) White card
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(500, 350));
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(0, 0, 0, 0));
        background.add(card, new GridBagConstraints());

        // 5) Header band
        JPanel header = new JPanel(new GridBagLayout());
        header.setPreferredSize(new Dimension(500, 80));
        header.setBackground(new Color(21, 101, 192));
        JLabel lblTitle = new JLabel("CALCULATE PAYROLL");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        header.add(lblTitle);
        card.add(header, BorderLayout.NORTH);

        // 6) Form area
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(new EmptyBorder(20, 40, 20, 40));
        card.add(form, BorderLayout.CENTER);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Color fgColor   = new Color(33, 33, 33);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 0, 12, 10);
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        // Employee ID
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblId = new JLabel("Employee ID:");
        lblId.setFont(labelFont);
        lblId.setForeground(fgColor);
        form.add(lblId, gbc);

        gbc.gridx = 1;
        txtEmpId.setFont(fieldFont);
        form.add(txtEmpId, gbc);

        // Basic Salary
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lblBasic = new JLabel("Basic Salary:");
        lblBasic.setFont(labelFont);
        lblBasic.setForeground(fgColor);
        form.add(lblBasic, gbc);

        gbc.gridx = 1;
        txtBasic.setFont(fieldFont);
        form.add(txtBasic, gbc);

        // Net Salary display
        gbc.gridx      = 0;
        gbc.gridy      = 2;
        gbc.gridwidth  = 2;
        gbc.insets     = new Insets(15, 0, 15, 0);
        lblNet.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNet.setForeground(new Color(3, 169, 244));
        lblNet.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(lblNet, gbc);

        // 7) Button bar
        JPanel btnBar = new JPanel(new GridLayout(1, 2, 30, 0));
        btnBar.setOpaque(false);
        gbc.gridy      = 3;
        gbc.insets     = new Insets(10, 0, 0, 0);
        form.add(btnBar, gbc);

        JButton btnCalc = new JButton("Calculate");
        JButton btnBack = new JButton("Back");

        Font btnFont = new Font("Segoe UI", Font.BOLD, 16);
        Color accent  = new Color(3, 169, 244);

        for (JButton btn : new JButton[]{btnCalc, btnBack}) {
            btn.setFont(btnFont);
            btn.setBackground(accent);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(160, 40));
            btnBar.add(btn);
        }

        // 8) Wire up actions
        btnCalc.addActionListener(e -> calculate());
        btnBack.addActionListener(e -> {
            new MainFrame().setVisible(true);
            dispose();
        });
    }

    private void calculate() {
        try {
            double basic = Double.parseDouble(txtBasic.getText().trim());
            double hra   = basic * 0.30;
            double pf    = basic * 0.12;
            double net   = basic + hra - pf;
            lblNet.setText("Net Salary: " + String.format("%.2f", net));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Please enter valid numbers.",
                "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new CalculatePayrollFrame().setVisible(true)
        );
    }
}



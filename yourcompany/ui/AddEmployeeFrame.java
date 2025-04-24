package com.yourcompany.ui;

import com.yourcompany.db.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddEmployeeFrame extends JFrame {
    private final JTextField    txtFirst  = new JTextField(20);
    private final JTextField    txtLast   = new JTextField(20);
    private final JTextField    txtEmail  = new JTextField(20);
    private final JTextField    txtPhone  = new JTextField(20);
    private final JComboBox<String> cbGender = new JComboBox<>(
        new String[]{"Select","Male","Female","Other"});
    private final JTextField    txtDept   = new JTextField(20);
    private final JTextField    txtSalary = new JTextField(20);

    public AddEmployeeFrame() {
        super("Enter Employee Details");

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

        // 2) Full-screen + base setup
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
                    0, 0, new Color(25, 118, 210),
                    0, h, new Color(33, 150, 243)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, w, h);
            }
        };
        background.setLayout(new GridBagLayout());  // centers the card
        setContentPane(background);

        // 4) White “card” panel
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(600, 650));
        card.setBackground(Color.WHITE);
        background.add(card, new GridBagConstraints());

        // 5) Header band
        JPanel header = new JPanel(new GridBagLayout());
        header.setPreferredSize(new Dimension(600, 100));
        header.setBackground(new Color(21, 101, 192));
        JLabel lblTitle = new JLabel("ENTER EMPLOYEE DETAILS");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        header.add(lblTitle);
        card.add(header, BorderLayout.NORTH);

        // 6) Form area
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(new EmptyBorder(20, 40, 20, 40));
        form.setBackground(Color.WHITE);
        card.add(form, BorderLayout.CENTER);

        // fonts + colors
        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Color fgColor  = new Color(33, 33, 33);

        String[] labels = {
            "First Name:", "Last Name:", "Email:",
            "Phone Number:", "Gender:", "Department:", "Salary:"
        };
        JComponent[] inputs = {
            txtFirst, txtLast, txtEmail,
            txtPhone, cbGender, txtDept, txtSalary
        };

        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.anchor   = GridBagConstraints.EAST;
        gbcLabel.insets   = new Insets(12, 0, 12, 10);
        gbcLabel.gridx    = 0;

        GridBagConstraints gbcField = new GridBagConstraints();
        gbcField.fill     = GridBagConstraints.HORIZONTAL;
        gbcField.weightx  = 1.0;
        gbcField.insets   = new Insets(12, 0, 12, 0);
        gbcField.gridx    = 1;

        for (int i = 0; i < labels.length; i++) {
            // Label in column 0
            gbcLabel.gridy = i;
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(labelFont);
            lbl.setForeground(fgColor);
            form.add(lbl, gbcLabel);

            // Field in column 1
            gbcField.gridy = i;
            JComponent comp = inputs[i];
            comp.setFont(fieldFont);
            form.add(comp, gbcField);
        }

        // 7) Button bar
        JPanel btnBar = new JPanel(new GridLayout(1, 2, 40, 0));
        btnBar.setBorder(new EmptyBorder(30, 40, 30, 40));
        btnBar.setBackground(Color.WHITE);
        card.add(btnBar, BorderLayout.SOUTH);

        JButton btnAdd  = new JButton("Add Employee");
        JButton btnBack = new JButton("Back");
        Font    btnFont = new Font("Segoe UI", Font.BOLD, 16);
        Color   primary = new Color(3, 169, 244);

        for (JButton btn : new JButton[]{btnAdd, btnBack}) {
            btn.setFont(btnFont);
            btn.setBackground(primary);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(200, 45));
            btnBar.add(btn);
        }

        // 8) Wiring your existing actions
        btnAdd .addActionListener(e -> insertEmployee());
        btnBack.addActionListener(e -> {
            new MainFrame().setVisible(true);
            dispose();
        });
    }

    private void insertEmployee() {
        String sql = "INSERT INTO employee"
                   + "(first_name,last_name,email,phone,gender,department,salary)"
                   + " VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, txtFirst .getText().trim());
            pst.setString(2, txtLast  .getText().trim());
            pst.setString(3, txtEmail .getText().trim());
            pst.setString(4, txtPhone .getText().trim());
            pst.setString(5, cbGender.getSelectedItem().toString());
            pst.setString(6, txtDept  .getText().trim());
            pst.setDouble(7, Double.parseDouble(txtSalary.getText().trim()));

            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this,
                    "Employee added successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Insert failed—no rows affected.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this,
                "Salary must be a valid number.",
                "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Database error: " + ex.getMessage(),
                "DB Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        txtFirst .setText("");
        txtLast  .setText("");
        txtEmail .setText("");
        txtPhone .setText("");
        cbGender .setSelectedIndex(0);
        txtDept  .setText("");
        txtSalary.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new AddEmployeeFrame().setVisible(true)
        );
    }
}

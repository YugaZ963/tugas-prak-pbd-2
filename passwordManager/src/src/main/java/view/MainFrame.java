/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import src.main.java.controller.PasswordManagerController;
import src.main.java.model.PasswordEntry;
import src.main.java.model.User;

public class MainFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtMasterPassword;
    private PasswordManagerController controller;

    public MainFrame(PasswordManagerController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Password Manager");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel lblUsername = new JLabel("Username:");
        JLabel lblMasterPassword = new JLabel("Master Password:");

        txtUsername = new JTextField();
        txtMasterPassword = new JPasswordField();

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new LoginAction());

        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblMasterPassword);
        panel.add(txtMasterPassword);
        panel.add(new JLabel());
        panel.add(btnLogin);

        add(panel);
        setVisible(true);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = txtUsername.getText();
            String masterPassword = new String(txtMasterPassword.getPassword());

            User user = controller.authenticate(username, masterPassword);
            if (user != null) {
                JOptionPane.showMessageDialog(MainFrame.this, "Login successful!");
                openPasswordManager(user);
            } else {
                JOptionPane.showMessageDialog(MainFrame.this, "Invalid credentials!");
            }
        }

        private void openPasswordManager(User user) {
            JFrame frame = new JFrame("Password Manager for " + user.getUsername());
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            DefaultListModel<String> listModel = new DefaultListModel<>();
            JList<String> passwordList = new JList<>(listModel);

            JButton btnAdd = new JButton("Add");
            JButton btnEdit = new JButton("Edit");
            JButton btnDelete = new JButton("Delete");

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JScrollPane(passwordList), BorderLayout.CENTER);

            JPanel buttonsPanel = new JPanel();
            buttonsPanel.add(btnAdd);
            buttonsPanel.add(btnEdit);
            buttonsPanel.add(btnDelete);

            panel.add(buttonsPanel, BorderLayout.SOUTH);
            frame.add(panel);

            // Load password entries
            List<PasswordEntry> entries = controller.getPasswordEntries(user.getId());
            for (PasswordEntry entry : entries) {
                listModel.addElement(entry.getSite() + " - " + entry.getLogin());
            }

            // Add Action Listener
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleAddPassword(user, listModel);
                }
            });

            frame.setVisible(true);
        }
    }

    private void handleAddPassword(User user, DefaultListModel<String> listModel) {
        JTextField txtSite = new JTextField();
        JTextField txtLogin = new JTextField();
        JPasswordField txtPassword = new JPasswordField();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Site:"));
        panel.add(txtSite);
        panel.add(new JLabel("Login:"));
        panel.add(txtLogin);
        panel.add(new JLabel("Password:"));
        panel.add(txtPassword);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Password", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String site = txtSite.getText();
            String login = txtLogin.getText();
            String password = new String(txtPassword.getPassword());

            if (!site.isEmpty() && !login.isEmpty() && !password.isEmpty()) {
                PasswordEntry newEntry = new PasswordEntry(site, login, password, user.getId());
                controller.addPasswordEntry(newEntry);
                listModel.addElement(site + " - " + login);
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required!");
            }
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log.reg.pkg1;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AccountForm extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JTextField namaLengkapField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton buatAkunButton;

    public AccountForm(String username) {
        setTitle("Buat Akun");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null); // Gunakan layout null untuk pengaturan manual

        // Label dan Text Field untuk Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 20, 80, 20);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 20, 200, 20);
        add(usernameField);

        // Label dan Text Field untuk Nama Lengkap
        JLabel namaLengkapLabel = new JLabel("Nama Lengkap:");
        namaLengkapLabel.setBounds(20, 50, 120, 20);
        add(namaLengkapLabel);

        namaLengkapField = new JTextField();
        namaLengkapField.setBounds(140, 50, 200, 20);
        add(namaLengkapField);

        // Label dan Text Field untuk Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 80, 80, 20);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 80, 200, 20);
        add(emailField);

        // Label dan Password Field untuk Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 110, 80, 20);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 110, 200, 20);
        add(passwordField);

        // Tombol Buat Akun
        buatAkunButton = new JButton("Buat Akun");
        buatAkunButton.setBounds(120, 140, 100, 25);
        buatAkunButton.addActionListener(this);
        add(buatAkunButton);

        pack();
        setVisible(true);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buatAkunButton) {
            String username = usernameField.getText();
            String namaLengkap = namaLengkapField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            // Validasi Data Akun (opsional)
            // (Tambahkan kode validasi di sini)

            // Simpan Data Akun ke Database
            boolean akunTersimpan = saveAccountToDatabase(username, namaLengkap, email, password);

            if (akunTersimpan) {
                JOptionPane.showMessageDialog(this, "Akun berhasil dibuat!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                // Reset Formulir (opsional)
                usernameField.setText("");
                namaLengkapField.setText("");
                emailField.setText("");
                passwordField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal membuat akun!", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean saveAccountToDatabase(String username, String namaLengkap, String email, String password) {
        // Buat koneksi ke database dan lakukan query untuk menyimpan data akun
        // (Ganti dengan kode koneksi dan query Anda)
        Connection connection = KoneksiDatabase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tabel_pengguna (username, nama_lengkap, email, password) VALUES (?, ?, ?, ?)");
            statement.setString(1, username);
            statement.setString(2, namaLengkap);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            KoneksiDatabase.closeConnection();
        }
    }

    public static void main(String[] args) {
        
    }
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahasiswaform2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class MahasiswaForm2 extends JFrame {
    private JTextField txtName, txtNpm, txtKelas;
    private JComboBox<String> cmbGender, cmbProdi;
    private JLabel lblImage;
    private JTextArea txtArea;
    private File selectedImageFile;
    private JTable table;
    private DefaultTableModel tableModel;

    public MahasiswaForm2() {
        setTitle("Student Form");
        setSize(800, 600);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2));

        panel.add(new JLabel("Nama:"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("NPM:"));
        txtNpm = new JTextField();
        panel.add(txtNpm);

        panel.add(new JLabel("Jenis Kelamin:"));
        cmbGender = new JComboBox<>(new String[] { "Laki-laki", "Perempuan" });
        panel.add(cmbGender);

        panel.add(new JLabel("Prodi:"));
        cmbProdi = new JComboBox<>(new String[] { "Teknik Informatika", "Sistem Informasi", "Teknik Elektro" });
        panel.add(cmbProdi);

        panel.add(new JLabel("Kelas:"));
        txtKelas = new JTextField();
        panel.add(txtKelas);

        panel.add(new JLabel("Foto:"));
        lblImage = new JLabel();
        lblImage.setPreferredSize(new Dimension(100, 100));
        panel.add(lblImage);

        JButton btnBrowse = new JButton("Browse");
        btnBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "png"));
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    selectedImageFile = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(new ImageIcon(selectedImageFile.getAbsolutePath()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                    lblImage.setIcon(icon);
                }
            }
        });
        panel.add(btnBrowse);

        add(panel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[] { "Nama", "NPM", "Jenis Kelamin", "Prodi", "Kelas", "Foto" }, 0);
        table = new JTable(tableModel) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 5 ? ImageIcon.class : String.class;
            }
        };
        table.setRowHeight(100);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });
        add(btnSave, BorderLayout.SOUTH);
    }

    private void saveData() {
        String name = txtName.getText();
        String npm = txtNpm.getText();
        String gender = cmbGender.getSelectedItem().toString();
        String prodi = cmbProdi.getSelectedItem().toString();
        String kelas = txtKelas.getText();
        byte[] imageBytes = null;
        
        if (selectedImageFile != null) {
            try {
                imageBytes = java.nio.file.Files.readAllBytes(selectedImageFile.toPath());
            } catch (IOException ex) {
                Logger.getLogger(MahasiswaForm2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        saveToDatabase(name, npm, gender, prodi, kelas, imageBytes);

        tableModel.addRow(new Object[] {
            name, npm, gender, prodi, kelas,
            new ImageIcon(new ImageIcon(imageBytes).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))
        });
    }

    private void saveToDatabase(String name, String npm, String gender, String prodi, String kelas, byte[] imageBytes) {
        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO mahasiswa (nama, npm, jenis_kelamin, prodi, kelas, image) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, npm);
            pst.setString(3, gender);
            pst.setString(4, prodi);
            pst.setString(5, kelas);
            pst.setBytes(6, imageBytes);
            pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MahasiswaForm2().setVisible(true);
            }
        });
    }
}


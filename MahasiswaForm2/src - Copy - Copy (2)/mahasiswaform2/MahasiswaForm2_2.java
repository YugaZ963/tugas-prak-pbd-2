/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahasiswaform2;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MahasiswaForm2_2 extends JFrame {
    private JTextField txtName, txtNpm, txtKelas;
    private JComboBox<String> cmbGender, cmbProdi;
    private JLabel lblImage;
    private File selectedImageFile;
    private JTable table;
    private DefaultTableModel tableModel;

    public MahasiswaForm2_2() {
        setTitle("Student Form");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Dapatkan content pane dari JFrame
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
// [41,209,86]
        // Atur warna background dari content pane sesuai dengan nilai RGB
        int red = 41;
        int green = 209;
        int blue = 86;
        Color customColor = new Color(red, green, blue);
        contentPane.setBackground(customColor);

        // Atur layout manager (opsional)
        contentPane.setLayout(null); // Menggunakan layout absolute

        JLabel lblJudul = new JLabel("Data Mahasiswa");
        lblJudul.setBounds(0, 0, 100, 25);
        add(lblJudul);
        
        JLabel lblName = new JLabel("Nama:");
        lblName.setBounds(20, 20, 100, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(130, 20, 200, 25);
        add(txtName);

        JLabel lblNpm = new JLabel("NPM:");
        lblNpm.setBounds(20, 60, 100, 25);
        add(lblNpm);

        txtNpm = new JTextField();
        txtNpm.setBounds(130, 60, 200, 25);
        add(txtNpm);

        JLabel lblGender = new JLabel("Jenis Kelamin:");
        lblGender.setBounds(20, 100, 100, 25);
        add(lblGender);

        cmbGender = new JComboBox<>(new String[] { "Laki-laki", "Perempuan" });
        cmbGender.setBounds(130, 100, 200, 25);
        add(cmbGender);

        JLabel lblProdi = new JLabel("Prodi:");
        lblProdi.setBounds(20, 140, 100, 25);
        add(lblProdi);

        cmbProdi = new JComboBox<>(new String[] { "Teknik Informatika", "Teknik Industri", "Teknik Elektro", "Teknik Sipil", "Arsitektur" });
        cmbProdi.setBounds(130, 140, 200, 25);
        add(cmbProdi);

        JLabel lblKelas = new JLabel("Kelas:");
        lblKelas.setBounds(20, 180, 100, 25);
        add(lblKelas);

        txtKelas = new JTextField();
        txtKelas.setBounds(130, 180, 200, 25);
        add(txtKelas);

        JLabel lblPhoto = new JLabel("Foto:");
        lblPhoto.setBounds(20, 220, 100, 25);
        add(lblPhoto);

        lblImage = new JLabel();
        lblImage.setBounds(130, 220, 100, 100);
        lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(lblImage);

        JButton btnBrowse = new JButton("Browse");
        btnBrowse.setBounds(250, 220, 80, 25);
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
        add(btnBrowse);

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(20, 340, 80, 25);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });
        add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(120, 340, 80, 25);
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData();
            }
        });
        add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(220, 340, 80, 25);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });
        add(btnDelete);

        tableModel = new DefaultTableModel(new String[] { "Nama", "NPM", "Jenis Kelamin", "Prodi", "Kelas", "Foto" }, 0);
        table = new JTable(tableModel) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 5 ? ImageIcon.class : String.class;
            }
        };
        table.setRowHeight(100);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(350, 20, 400, 350);
        add(scrollPane);

        loadData();
    }

    private void saveData() {
        String name = txtName.getText();
        String npm = txtNpm.getText();
        String gender = (String) cmbGender.getSelectedItem();
        String prodi = (String) cmbProdi.getSelectedItem();
        String kelas = txtKelas.getText();
        byte[] imageBytes = null;

        if(txtName.getText().equals("") || txtNpm.getText().equals("")
                    || cmbGender.getSelectedItem().equals("") || cmbProdi.getSelectedItem().equals("")
                    || txtKelas.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Data tidak Boleh Kosong !!", "Validasi Data",JOptionPane.INFORMATION_MESSAGE);
                return;
        }
                
        if (selectedImageFile != null) {
            try {
                imageBytes = Files.readAllBytes(selectedImageFile.toPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

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
            JOptionPane.showMessageDialog(null,"Data berhasil di simpan");  //notifikasi bahwa data berhasil disimpan
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        tableModel.addRow(new Object[] {
            name, npm, gender, prodi, kelas,
            new ImageIcon(new ImageIcon(imageBytes).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))
        });
    }
    
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        txtNpm.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
        txtName.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
        cmbGender.setSelectedItem(table.getValueAt(table.getSelectedRow(), 2).toString());
        cmbProdi.setSelectedItem(table.getValueAt(table.getSelectedRow(), 3).toString());
        txtKelas.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
        btnSave.setEnabled(false);
        btnEdit.setEnabled(true);
        btnExit.setEnabled(true);
        btnDelete.setEnabled(true);
    }

    private void updateData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update");
            return;
        }

        String name = txtName.getText();
        String npm = txtNpm.getText();
        String gender = (String) cmbGender.getSelectedItem();
        String prodi = (String) cmbProdi.getSelectedItem();
        String kelas = txtKelas.getText();
        byte[] imageBytes = null;

        if (selectedImageFile != null) {
            try {
                imageBytes = Files.readAllBytes(selectedImageFile.toPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        String id = tableModel.getValueAt(selectedRow, 1).toString();

        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "UPDATE mahasiswa SET nama=?, npm=?, jenis_kelamin=?, prodi=?, kelas=?, image=? WHERE npm=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, npm);
            pst.setString(3, gender);
            pst.setString(4, prodi);
            pst.setString(5, kelas);
            pst.setBytes(6, imageBytes);
            pst.setString(7, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Data berhasil di Ubah");  //notifikasi bahwa data berhasil disimpan
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        tableModel.setValueAt(name, selectedRow, 0);
        tableModel.setValueAt(npm, selectedRow, 1);
        tableModel.setValueAt(gender, selectedRow, 2);
        tableModel.setValueAt(prodi, selectedRow, 3);
        tableModel.setValueAt(kelas, selectedRow, 4);
                tableModel.setValueAt(new ImageIcon(new ImageIcon(imageBytes).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)), selectedRow, 5);
    }

    private void deleteData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete");
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 1).toString();
        JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM mahasiswa WHERE npm=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Data berhasil di Hapus");  //notifikasi bahwa data berhasil disimpan
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        tableModel.removeRow(selectedRow);
    }

    private void loadData() {
        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM mahasiswa";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String name = rs.getString("nama");
                String npm = rs.getString("npm");
                String gender = rs.getString("jenis_kelamin");
                String prodi = rs.getString("prodi");
                String kelas = rs.getString("kelas");
                byte[] photoBytes = rs.getBytes("image");
                ImageIcon icon = new ImageIcon(new ImageIcon(photoBytes).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));

                tableModel.addRow(new Object[]{name, npm, gender, prodi, kelas, icon});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MahasiswaForm2_2().setVisible(true);
            }
        });
    }
    
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnDelete;
}



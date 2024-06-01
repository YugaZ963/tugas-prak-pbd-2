import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MahasiswaForm extends JFrame {

    private JTextField txtNama, txtNpm, txtKelas;
    private JComboBox<String> cbJenisKelamin, cbProdi;
    private JLabel lblFoto;
    private JFileChooser fileChooser;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnSave, btnUpdate, btnDelete, btnChooseFoto;

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public MahasiswaForm() {
        initComponents();
        Connect();
        loadData();
    }

    private void initComponents() {
        setTitle("Form Data Mahasiswa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        
        panel.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panel.add(txtNama);
        
        panel.add(new JLabel("NPM:"));
        txtNpm = new JTextField();
        panel.add(txtNpm);
        
        panel.add(new JLabel("Jenis Kelamin:"));
        cbJenisKelamin = new JComboBox<>(new String[] {"Laki-laki", "Perempuan"});
        panel.add(cbJenisKelamin);
        
        panel.add(new JLabel("Prodi:"));
        cbProdi = new JComboBox<>(new String[] {"Teknik Informatika", "Sistem Informasi", "Teknik Elektro"});
        panel.add(cbProdi);
        
        panel.add(new JLabel("Kelas:"));
        txtKelas = new JTextField();
        panel.add(txtKelas);
        
        panel.add(new JLabel("Foto:"));
        lblFoto = new JLabel();
        lblFoto.setPreferredSize(new Dimension(100, 100));
        lblFoto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(lblFoto);
        
        btnChooseFoto = new JButton("Pilih Foto");
        panel.add(btnChooseFoto);
        
        btnSave = new JButton("Simpan");
        panel.add(btnSave);
        
        btnUpdate = new JButton("Update");
        panel.add(btnUpdate);
        
        btnDelete = new JButton("Hapus");
        panel.add(btnDelete);
        
        add(panel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[] {"ID", "Nama", "NPM", "Jenis Kelamin", "Prodi", "Kelas", "Foto"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        fileChooser = new JFileChooser();
        
        btnChooseFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFoto();
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                fillFormFromTable();
            }
        });
    }

    private void chooseFoto() {
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            lblFoto.setIcon(new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        }
    }

    private void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/mahasiswa_db", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MahasiswaForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadData() {
        try {
            pst = con.prepareStatement("SELECT * FROM mahasiswa");
            rs = pst.executeQuery();
            tableModel.setRowCount(0);
            while (rs.next()) {
                Object[] row = new Object[7];
                row[0] = rs.getInt("id");
                row[1] = rs.getString("nama");
                row[2] = rs.getString("npm");
                row[3] = rs.getString("jenis_kelamin");
                row[4] = rs.getString("prodi");
                row[5] = rs.getString("kelas");
                row[6] = new ImageIcon(new ImageIcon(rs.getBytes("foto")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MahasiswaForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveData() {
        try {
            String nama = txtNama.getText();
            String npm = txtNpm.getText();
            String jenisKelamin = cbJenisKelamin.getSelectedItem().toString();
            String prodi = cbProdi.getSelectedItem().toString();
            String kelas = txtKelas.getText();

            FileInputStream fis = null;
            if (fileChooser.getSelectedFile() != null) {
                fis = new FileInputStream(fileChooser.getSelectedFile());
            }

            pst = con.prepareStatement("INSERT INTO mahasiswa(nama, npm, jenis_kelamin, prodi, kelas, foto) VALUES(?, ?, ?, ?, ?, ?)");
            pst.setString(1, nama);
            pst.setString(2, npm);
            pst.setString(3, jenisKelamin);
            pst.setString(4, prodi);
            pst.setString(5, kelas);
            if (fis != null) {
                pst.setBinaryStream(6, fis, (int) fileChooser.getSelectedFile().length());
            } else {
                pst.setNull(6, Types.BLOB);
            }

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
            loadData();
        } catch (Exception ex) {
            Logger.getLogger(MahasiswaForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            try {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                String nama = txtNama.getText();
                String npm = txtNpm.getText();
                String jenisKelamin = cbJenisKelamin.getSelectedItem().toString();
                String prodi = cbProdi.getSelectedItem().toString();
                String kelas = txtKelas.getText();

                FileInputStream fis = null;
                if (fileChooser.getSelectedFile() != null) {
                    fis = new FileInputStream(fileChooser.getSelectedFile());
                }

                pst = con.prepareStatement("UPDATE mahasiswa SET nama=?, npm=?, jenis_kelamin=?, prodi=?, kelas=?, foto=? WHERE id=?");
                pst.setString(1, nama);
                pst.setString(2, npm);
                pst.setString(3, jenisKelamin);
                pst.setString(4, prodi);
                pst.setString(5, kelas);
                if (fis != null) {
                    pst.setBinaryStream(6, fis, (int) fileChooser.getSelectedFile().length());
                } else {
                    pst.setNull(6, Types.BLOB);
                }
                pst.setInt(7, id);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
                loadData();
            } catch (Exception ex) {
                Logger.getLogger(MahasiswaForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan diupdate");
        }
    }

    private void deleteData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            try {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                pst = con.prepareStatement("DELETE FROM mahasiswa WHERE id=?");
                pst.setInt(1, id);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
                loadData();
            } catch (SQLException ex) {
                Logger.getLogger(MahasiswaForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus");
        }
    }

    private void fillFormFromTable() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            txtNama.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtNpm.setText(tableModel.getValueAt(selectedRow, 2).toString());
            cbJenisKelamin.setSelectedItem(tableModel.getValueAt(selectedRow, 3).toString());
            cbProdi.setSelectedItem(tableModel.getValueAt(selectedRow, 4).toString());
            txtKelas.setText(tableModel.getValueAt(selectedRow, 5).toString());
            lblFoto.setIcon((ImageIcon) tableModel.getValueAt(selectedRow, 6));
        }
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MahasiswaForm().setVisible(true);
            }
        });
    }
}

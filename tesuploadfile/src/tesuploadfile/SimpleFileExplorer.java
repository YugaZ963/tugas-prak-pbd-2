/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesuploadfile;
// Mengimpor paket-paket yang diperlukan untuk aplikasi
import javax.swing.*;  // Mengimpor semua kelas dalam javax.swing untuk antarmuka pengguna
import javax.swing.border.EmptyBorder;  // Mengimpor EmptyBorder untuk memberikan padding pada panel
import java.awt.*;  // Mengimpor semua kelas dalam java.awt untuk komponen GUI dan tata letak
import java.awt.event.ActionEvent;  // Mengimpor kelas ActionEvent untuk menangani aksi pada komponen
import java.awt.event.ActionListener;  // Mengimpor interface ActionListener untuk mendengarkan aksi pengguna
import java.io.File;  // Mengimpor kelas File untuk mengelola file dan direktori
import java.io.IOException;  // Mengimpor kelas IOException untuk menangani kesalahan I/O
import java.nio.file.Files;  // Mengimpor kelas Files untuk operasi file dengan NIO
import java.nio.file.Path;  // Mengimpor kelas Path untuk representasi jalur file
import java.nio.file.StandardCopyOption;  // Mengimpor StandardCopyOption untuk menentukan opsi penyalinan file
import java.text.SimpleDateFormat;  // Mengimpor SimpleDateFormat untuk memformat tanggal
import java.util.Date;  // Mengimpor kelas Date untuk mengelola tanggal dan waktu

/*
Deskripsi Fungsionalitas yang Diimpor:
javax.swing*:

JFrame: Membuat frame utama aplikasi.
JPanel: Mengelola panel untuk menempatkan komponen GUI.
JButton: Membuat tombol yang dapat diklik oleh pengguna.
JLabel: Menampilkan teks atau gambar di aplikasi.
JFileChooser: Menampilkan dialog untuk memilih file atau direktori.
JOptionPane: Menampilkan dialog pesan kepada pengguna.
JScrollPane: Menyediakan area bergulir untuk panel.
javax.swing.border.EmptyBorder:

EmptyBorder: Memberikan padding pada panel untuk menjaga jarak antar komponen.
java.awt*:

BorderLayout: Mengatur komponen di lima area (Utara, Selatan, Timur, Barat, Tengah).
GridLayout: Menyusun komponen dalam grid dengan baris dan kolom.
FlowLayout: Mengatur komponen dalam satu baris, berurutan dari kiri ke kanan.
Dimension: Menentukan ukuran komponen.
Color: Menentukan warna untuk komponen GUI.
java.awt.event*:

ActionEvent: Mewakili aksi yang terjadi pada komponen GUI (seperti klik tombol).
ActionListener: Mendengarkan dan menangani aksi yang dipicu oleh pengguna.
java.io*:

File: Mengelola operasi file dan direktori seperti membaca, menulis, dan memodifikasi.
java.nio.file*:

Files: Menyediakan metode statis untuk operasi file seperti menyalin, memindahkan, dan menghapus.
Path: Mewakili jalur file atau direktori dalam sistem file.
StandardCopyOption: Menentukan opsi penyalinan file seperti mengganti file yang ada.
java.text.SimpleDateFormat:

SimpleDateFormat: Memformat dan memparsing tanggal dalam pola yang ditentukan (misalnya, "dd-MM-yyyy").
java.util.Date:

Date: Mewakili tanggal dan waktu saat ini atau spesifik.
*/

/**
 *
 * @author WIN11
 */
public class SimpleFileExplorer { // Baris ini mendeklarasikan kelas publik bernama SimpleFileExplorer. Kelas publik dapat diakses dari kelas lain dalam proyek Anda.

    private JFrame frame; // Variabel ini akan menyimpan referensi ke objek JFrame, yang merepresentasikan jendela utama dari aplikasi file explorer. 
    private JPanel filePanel; // Variabel ini akan menyimpan referensi ke objek JPanel, yang kemungkinan besar merupakan panel konten utama di mana informasi file akan ditampilkan.
    private File currentDirectory; // Variabel ini akan menyimpan objek File yang mewakili direktori yang saat ini dipilih dalam file explorer. 
    private JTextField tfFilePath; // Variabel ini akan menyimpan referensi ke objek JTextField, yang dapat digunakan untuk menampilkan jalur file saat ini.
    private JLabel lblFileName; // Variabel ini akan menyimpan referensi ke objek JLabel, yang mungkin digunakan untuk menampilkan nama file yang dipilih.
    private JLabel lblFileSize; // Variabel ini akan menyimpan referensi ke objek JLabel, kemungkinan digunakan untuk menampilkan ukuran file yang dipilih.
    private JLabel lblFileLastModified; // Variabel ini akan menyimpan referensi ke objek JLabel, kemungkinan digunakan untuk menampilkan tanggal modifikasi terakhir dari file yang dipilih.
    
    private File selectedFile; // Variabel ini akan menyimpan referensi ke objek File yang mewakili file yang saat ini dipilih di dalam file explorer.

    public static void main(String[] args) { 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleFileExplorer().initialize();
            }
        });
    }

    public void initialize() {
        frame = new JFrame("Simple File Explorer");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        int xCenter = (screenSize.width - frame.getWidth()) / 2;
        int yCenter = (screenSize.height - frame.getHeight()) / 2;

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel untuk menampilkan file dalam grid layout
        filePanel = new JPanel(new GridLayout(0, 3, 10, 10));
        filePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        frame.setLocation(xCenter, yCenter);

        // Atur warna background dari content pane sesuai dengan nilai RGB
        int red = 230;
        int green = 240;
        int blue = 254;
        
        int red2 = 240;
        int green2 = 255;
        int blue2 = 240;
        Color mainBg = new Color(red, green, blue);
        Color seccondBg = new Color(red2, green2, blue2);
        
        JLabel lblSelectFile = new JLabel("Select File:");
        tfFilePath = new JTextField(30);
        JButton btnBrowseFile = new JButton("Browse");

        JScrollPane scrollPane = new JScrollPane(filePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton btnBrowse = new JButton("Browse Folder");
        btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browseFolder();
            }
        });
        
        btnBrowseFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browseFile();
            }
        });
        
        JButton btnUpload = new JButton("Upload");
        btnUpload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uploadFile2();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnBrowse);
        buttonPanel.add(btnUpload);
        buttonPanel.add(lblSelectFile);
        buttonPanel.add(tfFilePath);
        buttonPanel.add(btnBrowseFile);
        buttonPanel.setBackground(seccondBg);
        filePanel.setBackground(mainBg);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void browseFolder() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentDirectory = fileChooser.getSelectedFile();
            displayFiles(currentDirectory);
        }
    }
    
    private void browseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            displayFileInfo(selectedFile);
        }
    }
    
    private void displayFileInfo(File file) {
        String fileName = file.getName();
    }

    private void displayFiles(File directory) {
//        int red = 230;
//        int green = 240;
//        int blue = 254;
//        Color mainBg = new Color(red, green, blue);
        
        // Clear existing panels
        filePanel.removeAll();

        // List files in the selected directory
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                JPanel panel = new JPanel(new BorderLayout());
                panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                panel.setPreferredSize(new Dimension(180, 120));

                JLabel lblFileName = new JLabel(file.getName());
                lblFileName.setHorizontalAlignment(JLabel.CENTER);
                
                panel.add(lblFileName, BorderLayout.CENTER);
//                panel.setBackground(mainBg);s

//                JButton btnUpload = new JButton("Upload");
//                btnUpload.addActionListener(new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        // Implement upload logic here (placeholder)
//                        uploadFile(file);
//                    }
//                });

                filePanel.add(panel);
            }
        }

        // Refresh UI
        filePanel.revalidate();
        filePanel.repaint();
    }
    private void uploadFile(File file) {
        // Implementasi logika unggah file
        // Misalnya, menampilkan pesan dialog bahwa file telah diunggah
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Simulasi proses unggah yang berjalan selama 2 detik
                Thread.sleep(2000);
                return null;
            }

            @Override
            protected void done() {
                JOptionPane.showMessageDialog(frame, "File uploaded successfully: " + file.getName());
            }
        };

        worker.execute();
    }
    
    private void uploadFile2() {
        if (selectedFile != null) {
            // Example destination folder (replace with your desired location)
            String destinationFolder = "C:/Users/WIN11/OneDrive/Dokumen/NetBeansProjects/tesuploadfile/src/tesuploadfile/uploads/";

            // Create destination directory if it doesn't exist
            File destDir = new File(destinationFolder);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            try {
                // Copy selected file to destination folder
                Path sourcePath = selectedFile.toPath();
                Path destPath = new File(destinationFolder + selectedFile.getName()).toPath();
                Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);

                JOptionPane.showMessageDialog(frame, "File uploaded successfully to: " + destPath.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Failed to upload file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a file to upload.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

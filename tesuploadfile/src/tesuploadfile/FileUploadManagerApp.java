/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesuploadfile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author WIN11
 */
public class FileUploadManagerApp {

    private JFrame frame;
    private JTextField tfFilePath;
    private JLabel lblFileName;
    private JLabel lblFileSize;
    private JLabel lblFileLastModified;

    private File selectedFile;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FileUploadManagerApp().initialize();
            }
        });
    }

    public void initialize() {
        frame = new JFrame("File Upload Manager");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel lblSelectFile = new JLabel("Select File:");
        panel.add(lblSelectFile, constraints);

        tfFilePath = new JTextField(30);
        constraints.gridx = 1;
        panel.add(tfFilePath, constraints);

        JButton btnBrowse = new JButton("Browse");
        constraints.gridx = 2;
        btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browseFile();
            }
        });
        panel.add(btnBrowse, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.WEST;
        lblFileName = new JLabel("File Name: ");
        panel.add(lblFileName, constraints);

        constraints.gridy = 2;
        lblFileSize = new JLabel("File Size: ");
        panel.add(lblFileSize, constraints);

        constraints.gridy = 3;
        lblFileLastModified = new JLabel("Last Modified: ");
        panel.add(lblFileLastModified, constraints);

        JButton btnUpload = new JButton("Upload");
        constraints.gridy = 4;
        btnUpload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uploadFile();
            }
        });
        panel.add(btnUpload, constraints);

        frame.add(panel);
        frame.setVisible(true);
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
        long fileSize = file.length();
        Date lastModified = new Date(file.lastModified());

        lblFileName.setText("File Name: " + fileName);
        lblFileSize.setText("File Size: " + fileSize + " bytes");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        lblFileLastModified.setText("Last Modified: " + dateFormat.format(lastModified));
    }

    private void uploadFile() {
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

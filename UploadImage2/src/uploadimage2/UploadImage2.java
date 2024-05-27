package uploadimage2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
/**
 *
 * @author WIN11
 */
public class UploadImage2 extends JFrame{

    private JLabel labelImage;
    private JTextField textFieldPath;
    private JButton buttonBrowse;
    private JButton buttonSave;
    private File selectedFile;

    public UploadImage2() {
        setTitle("Image Uploader");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        labelImage = new JLabel();
        labelImage.setHorizontalAlignment(JLabel.CENTER);
        textFieldPath = new JTextField(20);
        textFieldPath.setEditable(false);
        buttonBrowse = new JButton("Browse");
        buttonSave = new JButton("Save");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(labelImage, BorderLayout.CENTER);

        JPanel panelBottom = new JPanel();
        panelBottom.add(textFieldPath);
        panelBottom.add(buttonBrowse);
        panelBottom.add(buttonSave);
        panel.add(panelBottom, BorderLayout.SOUTH);

        add(panel);

        buttonBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseImage();
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveImage();
                } catch (SQLException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png") || filename.endsWith(".gif");
                }
            }

            @Override
            public String getDescription() {
                return "Image Files (*.jpg, *.jpeg, *.png, *.gif)";
            }
        });

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            textFieldPath.setText(selectedFile.getAbsolutePath());
            labelImage.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
        }
    }

    private void saveImage() throws SQLException, IOException {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Please select an image first");
            return;
        }

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uploadimage-java2", "root", "");
        String sql = "INSERT INTO images (image) VALUES (?)";
        PreparedStatement pst = con.prepareStatement(sql);
        FileInputStream fis = new FileInputStream(selectedFile);
        pst.setBinaryStream(1, fis, (int) selectedFile.length());
        int result = pst.executeUpdate();

        if (result == 1) {
            JOptionPane.showMessageDialog(this, "Image uploaded successfully");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to upload image");
        }

        fis.close();
        pst.close();
        con.close();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UploadImage2().setVisible(true);
        });
    }
}

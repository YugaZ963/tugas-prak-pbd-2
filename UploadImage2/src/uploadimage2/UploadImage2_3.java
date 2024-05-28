/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uploadimage2;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author WIN11
 */
public class UploadImage2_3 extends JFrame {

    private JLabel lblImage;
    private JTextArea txtPath;
    private JButton btnChoose, btnSave, btnLoad;
    private File selectedFile;
    private Connection con;

    public UploadImage2_3() {
        initComponents();
        connectDatabase();
    }

    private void initComponents() {
        setTitle("Image Uploader");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        lblImage = new JLabel();
        lblImage.setBounds(10, 10, 200, 200);
        add(lblImage);

        txtPath = new JTextArea();
        txtPath.setLineWrap(true);
        txtPath.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtPath);
        scrollPane.setBounds(220, 10, 350, 100);
        add(scrollPane);

        btnChoose = new JButton("Choose Image");
        btnChoose.setBounds(220, 120, 150, 30);
        add(btnChoose);

        btnSave = new JButton("Save Image");
        btnSave.setBounds(380, 120, 150, 30);
        add(btnSave);
        
        btnLoad = new JButton("Load Image");
        btnLoad.setBounds(220, 160, 150, 30);
        add(btnLoad);

        btnChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseImage();
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveImage();
            }
        });

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadImage();
            }
        });

        setVisible(true);
    }

    private void connectDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uploadimage-java2", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            txtPath.setText(selectedFile.getAbsolutePath());

            try {
                Image img = ImageIO.read(selectedFile);
                Image scaledImg = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
                lblImage.setIcon(new ImageIcon(scaledImg));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveImage() {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "No image selected!");
            return;
        }

        try (FileInputStream fis = new FileInputStream(selectedFile)) {
            String query = "INSERT INTO images (image, imagePath) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(2, selectedFile.getAbsolutePath());
            pst.setBinaryStream(1, fis, (int) selectedFile.length());

            int rowAffected = pst.executeUpdate();
            if (rowAffected > 0) {
                JOptionPane.showMessageDialog(this, "Image saved successfully!");
            }
        } catch (SQLException | FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadImage() {
        try {
            String query = "SELECT imagePath, image FROM images ORDER BY id DESC LIMIT 1";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String imagePath = rs.getString("imagePath");
                txtPath.setText(imagePath);
                byte[] imgBytes = rs.getBytes("image");
                
                ImageIcon imageIcon = new ImageIcon(imgBytes);
                Image img = imageIcon.getImage();
                Image scaledImg = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
                lblImage.setIcon(new ImageIcon(scaledImg));
            } else {
                JOptionPane.showMessageDialog(this, "No image found in the database!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UploadImage2_3();
    }
}

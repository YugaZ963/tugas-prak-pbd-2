/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userform;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author WIN11
 */
public class UserForm extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    byte[] user_image;
    
    ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
        JLabel jLabel1 = new javax.swing.JLabel();
        JTextField txtName = new javax.swing.JTextField();
        JLabel jLabel2 = new javax.swing.JLabel();
        JPasswordField txtPassword = new javax.swing.JPasswordField();
        JLabel jLabel3 = new javax.swing.JLabel();
        JRadioButton rbMale = new javax.swing.JRadioButton();
        JRadioButton rbFemale = new javax.swing.JRadioButton();
        JLabel jLabel4 = new javax.swing.JLabel();
        JComboBox<Object> cmbCountry = new javax.swing.JComboBox<>();
        JButton btnChooseImage = new javax.swing.JButton();
        JLabel lblImage = new javax.swing.JLabel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTextArea txtAreaPath = new javax.swing.JTextArea();
        JButton btnAdd = new javax.swing.JButton();
        JButton btnUpdate = new javax.swing.JButton();
        JButton btnDelete = new javax.swing.JButton();
        JButton btnShow = new javax.swing.JButton();

    public UserForm() {
        initComponents();
        Connect();
        loadUserData();
    }

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/userdb_java_tes", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Name:");

        jLabel2.setText("Password:");

        jLabel3.setText("Gender:");

        buttonGroup1.add(rbMale);
        rbMale.setText("Male");

        buttonGroup1.add(rbFemale);
        rbFemale.setText("Female");

        jLabel4.setText("Country:");

        cmbCountry.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USA", "India", "UK", "Australia" }));

        btnChooseImage.setText("Choose Image");
        btnChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImageActionPerformed(evt);
            }
        });

        txtAreaPath.setColumns(20);
        txtAreaPath.setRows(5);
        txtAreaPath.setLineWrap(true);
        jScrollPane1.setViewportView(txtAreaPath);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.setSize(30,30);

        btnDelete.setText("Delete");

        btnShow.setText("Show");
        btnShow.setSize(30,30);
        
        jScrollPane2 = new javax.swing.JScrollPane();
userTable = new javax.swing.JTable();

userTable.setModel(new javax.swing.table.DefaultTableModel(
    new Object [][] {},
    new String [] {"ID", "Name", "Gender", "Country", "Image"}
));
jScrollPane2.setViewportView(userTable);



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtName)
                    .addComponent(txtPassword)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbMale)
                        .addGap(18, 18, 18)
                        .addComponent(rbFemale))
                    .addComponent(cmbCountry, 0, 200, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnChooseImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnShow))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChooseImage))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(rbMale)
                            .addComponent(rbFemale))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmbCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnUpdate))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete)
                            .addComponent(btnShow))
                        .addGap(30, 30, 30))))
                
                
        );
        
        

        pack();
    }

    private void btnChooseImageActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        
        if (file != null) {
            String path = file.getAbsolutePath();
            txtAreaPath.setText(path);

            ImageIcon imageIcon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
            lblImage.setIcon(imageIcon);
            
            try {
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
                user_image = bos.toByteArray();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String name = txtName.getText();
            String password = new String(txtPassword.getPassword());
            String gender = rbMale.isSelected() ? "Male" : "Female";
            String country = cmbCountry.getSelectedItem().toString();

            pst = con.prepareStatement("INSERT INTO users(name, password, gender, country, image) VALUES(?,?,?,?,?)");
            pst.setString(1, name);
            pst.setString(2, password);
            pst.setString(3, gender);
            pst.setString(4, country);
            pst.setBytes(5, user_image);

            int k = pst.executeUpdate();
            
            if (k == 1) {
                JOptionPane.showMessageDialog(this, "Record Added Successfully");
                txtName.setText("");
                txtPassword.setText("");
                buttonGroup1.clearSelection();
                cmbCountry.setSelectedIndex(-1);
                lblImage.setIcon(null);
                txtAreaPath.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Record Failed to Save");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
    try {
        String name = txtName.getText();
        String password = new String(txtPassword.getPassword());
        String gender = rbMale.isSelected() ? "Male" : "Female";
        String country = cmbCountry.getSelectedItem().toString();
        int id = getSelectedUserId();

        if (id == -1) {
            return; // No user selected, exit the method
        }

        pst = con.prepareStatement("UPDATE users SET name=?, password=?, gender=?, country=?, image=? WHERE id=?");
        pst.setString(1, name);
        pst.setString(2, password);
        pst.setString(3, gender);
        pst.setString(4, country);
        pst.setBytes(5, user_image);
        pst.setInt(6, id);

        int k = pst.executeUpdate();

        if (k == 1) {
            JOptionPane.showMessageDialog(this, "Record Updated Successfully");
            loadUserData();
        } else {
            JOptionPane.showMessageDialog(this, "Record Update Failed");
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
    }
}

private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
    try {
        int id = getSelectedUserId();

        if (id == -1) {
            return; // No user selected, exit the method
        }

        pst = con.prepareStatement("DELETE FROM users WHERE id=?");
        pst.setInt(1, id);

        int k = pst.executeUpdate();

        if (k == 1) {
            JOptionPane.showMessageDialog(this, "Record Deleted Successfully");
            loadUserData();
        } else {
            JOptionPane.showMessageDialog(this, "Record Deletion Failed");
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {
    try {
        pst = con.prepareStatement("SELECT * FROM users");
        rs = pst.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            String country = rs.getString("country");
            // Process the image bytes if needed
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    private void loadUserData() {
    try {
        pst = con.prepareStatement("SELECT * FROM users");
        rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            String country = rs.getString("country");
            byte[] image = rs.getBytes("image");

            Object[] row = {id, name, gender, country, image};
            model.addRow(row);
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
    }
}

private int getSelectedUserId() {
    int selectedRow = userTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a user.");
        return -1;
    }
    return (int) userTable.getValueAt(selectedRow, 0);
}
    

    private javax.swing.JTable userTable;
private javax.swing.JScrollPane jScrollPane2;


    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserForm().setVisible(true);
            }
        });
    }
    
}

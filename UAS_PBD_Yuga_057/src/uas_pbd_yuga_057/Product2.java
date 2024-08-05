/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_pbd_yuga_057;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class Product2 extends javax.swing.JFrame {

    /**
     * Creates new form Product
     */
    public Product2() {
        initComponents();
        LoadProductNo(); // Memuat nomor produk dari database ke komponen JComboBox
        Fetch();
        customizeTable();
        setLocationRelativeTo(null); // Tambahkan ini untuk menempatkan frame di tengah
        setIconImage(new ImageIcon(getClass().getResource("/uas_pbd_yuga_057/images/logo.jpeg")).getImage());
    }
    Connection con; // Deklarasi variabel untuk koneksi database
    PreparedStatement pst; // Deklarasi variabel untuk statement SQL
    ResultSet rs; // Deklarasi variabel untuk hasil kueri SQL
    private File selectedFile;
    private String filePath;

    
    
    
    
    // Metode untuk memuat nomor produk dari database ke komponen JComboBox
    public void LoadProductNo(){
        try {
            con = (Connection) Config.configDB();
            pst = con.prepareStatement("SELECT id FROM product"); // Membuat pernyataan SQL untuk memilih nomor produk dari tabel 'product'
            rs = pst.executeQuery(); // Mengeksekusi pernyataan SQL dan mendapatkan hasilnya
            txtId.removeAllItems(); // Menghapus semua item dari komponen JComboBox
            while(rs.next()){
                txtId.addItem(rs.getString(1)); // Menambahkan nomor produk ke dalam komponen JComboBox
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product2.class.getName()).log(Level.SEVERE, null, ex); // Menangani kesalahan jika terjadi kesalahan dalam eksekusi kueri SQL
        } catch (Exception ex) {
            Logger.getLogger(Product2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void customizeTable() {
        // Pastikan bahwa kolom ke-4 adalah kolom untuk gambar
        jTable1.getColumnModel().getColumn(5).setCellRenderer(new ImageRenderer());
        jTable1.setRowHeight(100); // set tinggi baris sesuai ukuran gambar
    }
    
    private void searchProductById() {
    try {
        // Mengambil ID produk yang dipilih dari JComboBox dan menyimpannya dalam variabel pid
        String pid = txtId.getSelectedItem().toString();

        // Membuat pernyataan SQL untuk memilih data produk berdasarkan ID dari tabel 'product'
        con = (Connection) Config.configDB();
        pst = con.prepareStatement("SELECT * FROM product WHERE id=?");
        
        // Mengatur nilai parameter pertama (?) dengan nilai dari variabel pid
        pst.setString(1, pid);
        
        // Mengeksekusi pernyataan SQL dan mendapatkan hasilnya
        rs = pst.executeQuery();

        // Memeriksa apakah hasil kueri mengandung baris data
        if (rs.next()) {
            // Mengatur item yang dipilih dalam JComboBox
            txtId.setSelectedItem(pid);

            // Jika ada data, mengatur nilai JTextField txtName, txtSize, txtPrice, dan txtQty dengan nilai dari hasil kueri
            txtName.setText(rs.getString(2)); // Mengatur nilai txtName dengan nilai dari kolom kedua (nama produk)
            txtSize.setText(rs.getString(3)); // Mengatur nilai txtSize dengan nilai dari kolom ketiga (ukuran produk)
            txtPrice.setText(rs.getString(4)); // Mengatur nilai txtPrice dengan nilai dari kolom keempat (harga produk)
            txtQty.setText(rs.getString(5)); // Mengatur nilai txtQty dengan nilai dari kolom kelima (jumlah produk)

            // Ambil data gambar dari database
            byte[] imgBytes = rs.getBytes("image");
            if (imgBytes != null && imgBytes.length > 0) {
                // Konversi byte array ke BufferedImage
                InputStream in = new ByteArrayInputStream(imgBytes);
                BufferedImage bufferedImage = ImageIO.read(in);

                if (bufferedImage != null) {
                    // Tampilkan gambar pada JLabel
                    ImageIcon icon = new ImageIcon(bufferedImage);
                    Image img = icon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(img));

                    // Tambahkan imageLabel ke frame atau panel sesuai desain Anda
                    if (imageLabel.getParent() == null) {
                        jPanel1.add(imageLabel);
                    }
                    jPanel1.revalidate();
                    jPanel1.repaint();
                } else {
                    // Menampilkan pesan jika gambar tidak dapat dimuat
                    JOptionPane.showMessageDialog(this, "Error loading image");
                }
            } else {
                // Jika tidak ada gambar, hapus ikon dari JLabel
                imageLabel.setIcon(null);
            }
        } else {
            // Jika tidak ada data yang cocok dengan ID yang dipilih, menampilkan pesan dialog bahwa tidak ada data yang ditemukan
            JOptionPane.showMessageDialog(this, "No Record Found");
        }
    } catch (SQLException ex) {
        // Menangani kesalahan jika terjadi kesalahan dalam eksekusi kueri SQL
        Logger.getLogger(Product2.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        // Menangani kesalahan dalam proses input/output
        Logger.getLogger(Product2.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        // Menangani kesalahan umum
        Logger.getLogger(Product2.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        // Menutup resource untuk mencegah kebocoran resource
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Product2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

    
    private void Fetch() { // Deklarasi method Fetch.
    try { // Mulai blok try untuk menangani eksekusi yang mungkin menghasilkan kesalahan.
        int g; // Deklarasi variabel g untuk menyimpan jumlah kolom dari ResultSet.
        
        // Membuat koneksi ke database
        con = (Connection) Config.configDB();
        
        // Membuat statement SQL untuk mengambil semua data dari tabel 'product'
        pst = con.prepareStatement("SELECT * FROM product");
        
        // Mengeksekusi query dan menyimpan hasilnya dalam ResultSet
        rs = pst.executeQuery();
        
        // Mendapatkan metadata dari ResultSet untuk mengetahui jumlah kolom
        ResultSetMetaData rss = rs.getMetaData();
        g = rss.getColumnCount();
        
        // Mengambil model dari jTable1 dan mengosongkan data yang ada
        DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
        df.setRowCount(0);
        
        // Looping melalui setiap baris hasil query
        while (rs.next()) {
            // Membuat vector untuk menyimpan data setiap baris
            Vector v2 = new Vector();
            
            // Looping melalui setiap kolom dalam baris
            for (int a = 1; a <= g; a++) {
                // Menambahkan nilai dari setiap kolom ke dalam vector
                v2.add(rs.getString("id")); // Menambahkan nilai kolom 'id'
                v2.add(rs.getString("name")); // Menambahkan nilai kolom 'name'
                v2.add(rs.getString("size")); // Menambahkan nilai kolom 'size'
                v2.add(rs.getString("price")); // Menambahkan nilai kolom 'price'
                v2.add(rs.getString("qty")); // Menambahkan nilai kolom 'qty'
                
                // Mengambil data gambar dari kolom 'image'
                byte[] imgBytes = rs.getBytes("image");
                
                // Memeriksa apakah ada gambar yang diambil
                if (imgBytes != null) {
                    // Membuat ImageIcon dari byte array gambar
                    ImageIcon imageIcon = new ImageIcon(imgBytes);
                    
                    // Mengatur ukuran gambar
                    Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    
                    // Menambahkan gambar yang telah diatur ukurannya ke dalam vector
                    v2.add(new ImageIcon(scaledImage));
                } else {
                    // Jika tidak ada gambar, tambahkan nilai null ke dalam vector
                    v2.add(null);
                }
            }
            
            // Menambahkan vector yang mewakili baris ke model tabel
            df.addRow(v2);
        }
    } catch (SQLException ex) {
        // Menangani kesalahan SQL dan mencatat pesan kesalahan
        Logger.getLogger(Product2.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        // Menangani kesalahan umum dan mencatat pesan kesalahan
        Logger.getLogger(Product2.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        Welcome = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSize = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtId = new javax.swing.JComboBox<>();
        btnSearchData = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSelectImage = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Background = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RAVAZKA Shop");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Title.setBackground(new java.awt.Color(255, 255, 255));
        Title.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Title.setText("Stock Barang di Toko RAVAZKA");
        Title.setOpaque(true);
        jPanel1.add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        Welcome.setBackground(new java.awt.Color(255, 255, 255));
        Welcome.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Welcome.setText("Temukan Ribuan Seragam Sekolah Berkualitas di Satu Tempat!");
        Welcome.setOpaque(true);
        jPanel1.add(Welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Product Size");
        jLabel6.setOpaque(true);
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Product Name");
        jLabel2.setOpaque(true);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Product Price");
        jLabel3.setOpaque(true);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, 20));

        imageLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        imageLabel.setOpaque(true);
        jPanel1.add(imageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 220, 240));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Image");
        jLabel8.setOpaque(true);
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 150, -1, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Product Image");
        jLabel7.setOpaque(true);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, -1, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Product Qty");
        jLabel4.setOpaque(true);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, 20));
        jPanel1.add(txtSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 380, -1));
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 380, -1));
        jPanel1.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 380, -1));
        jPanel1.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 380, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Product Id");
        jLabel5.setOpaque(true);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 340, -1, 20));

        txtId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 340, -1, -1));

        btnSearchData.setText("Search");
        btnSearchData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchDataActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearchData, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 400, -1, -1));

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, -1, -1));

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 400, -1, -1));

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 400, -1, -1));

        btnSelectImage.setText("Search");
        btnSelectImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectImageActionPerformed(evt);
            }
        });
        jPanel1.add(btnSelectImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 380, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Product Name", "Size", "Price", "Quantity", "Image"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 1000, 290));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uas_pbd_yuga_057/images/gudang.jpg"))); // NOI18N
        jPanel1.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 740));

        jMenu1.setText("Home");

        jMenuItem2.setText("Home");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Product");

        jMenuItem1.setText("Product");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Transaction");

        jMenuItem5.setText("Sales history ");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Account");

        jMenuItem6.setText("Profile");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem3.setText("Log out");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDataActionPerformed
       searchProductById();
    }//GEN-LAST:event_btnSearchDataActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            String pname = txtName.getText(); // Mengambil teks dari JTextField txtName dan menyimpannya dalam variabel pname
            String size = txtSize.getText(); // Mengambil teks dari JTextField txtSize dan menyimpannya dalam variabel size
            String price = txtPrice.getText(); // Mengambil teks dari JTextField txtPrice dan menyimpannya dalam variabel price
            String qty = txtQty.getText(); // Mengambil teks dari JTextField txtQty dan menyimpannya dalam variabel qty
            
            if (selectedFile == null) { // kondisi untuk ketika kita belum pilih gambar
        JOptionPane.showMessageDialog(this, "Please select an image.", "Error", JOptionPane.ERROR_MESSAGE); // pesan notifikasi
        return;
    }

            con = (Connection) Config.configDB();
            pst = con.prepareStatement("INSERT INTO product (name, size, price, qty, image) VALUES(?,?,?,?,?)"); // Membuat pernyataan SQL untuk memasukkan data ke tabel 'product'
            pst.setString(1, pname); // Mengatur nilai parameter pertama (?) dengan nilai dari variabel pname
            pst.setString(2, size); // Mengatur nilai parameter kedua (?) dengan nilai dari variabel size
            pst.setString(3, price); // Mengatur nilai parameter ketiga (?) dengan nilai dari variabel price
            pst.setString(4, qty); // Mengatur nilai parameter keempat (?) dengan nilai dari variabel qty
            
            // Membaca file gambar dan mengkonversinya ke byte array
            // Membuat objek FileInputStream untuk membaca file yang dipilih.
            // 'selectedFile' adalah objek File yang mengacu pada file gambar yang dipilih.
            FileInputStream fis = new FileInputStream(selectedFile);

            // Mengatur parameter kelima dari pernyataan SQL (pst) sebagai aliran biner dari file.
            // Metode 'setBinaryStream' digunakan untuk menyimpan file biner (misalnya, gambar) dalam basis data.
            // Parameter pertama (5) adalah indeks parameter dalam pernyataan SQL (parameter kelima).
            // Parameter kedua (fis) adalah aliran input biner dari file yang akan disimpan.
            // Parameter ketiga ((int) selectedFile.length()) adalah panjang file dalam byte.
            pst.setBinaryStream(5, fis, (int) selectedFile.length());

            int k = pst.executeUpdate(); // Mengeksekusi pernyataan SQL untuk memasukkan data ke dalam tabel dan mendapatkan jumlah baris yang terpengaruh

            if(k == 1){
                JOptionPane.showMessageDialog(this, "Record Added Successfully"); // Menampilkan pesan dialog jika data berhasil dimasukkan
                txtName.setText(""); // Mengosongkan JTextField txtName
                txtSize.setText(""); // Mengosongkan JTextField txtSize
                txtPrice.setText(""); // Mengosongkan JTextField txtPrice
                txtQty.setText(""); // Mengosongkan JTextField txtQty
                imageLabel.setIcon(null);
                LoadProductNo(); // Memuat nomor produk dari database ke komponen JComboBox
                Fetch(); // Memuat data produk dari database ke tabel
            }else{
                JOptionPane.showMessageDialog(this, "Record Failed to save"); // Menampilkan pesan dialog jika data gagal disimpan
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product2.class.getName()).log(Level.SEVERE, null, ex); // Menangani kesalahan jika terjadi kesalahan dalam eksekusi kueri SQL
        } catch (Exception ex) {
            Logger.getLogger(Product2.class.getName()).log(Level.SEVERE, null, ex); // Menangani kesalahan jika terjadi kesalahan
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            // Mengambil nilai teks dari JTextField txtName, txtPrice, dan txtQty serta ID produk dari JComboBox txtId
            String pname = txtName.getText(); // Mengambil teks dari JTextField txtName dan menyimpannya dalam variabel pname
            String size = txtSize.getText();
            String price = txtPrice.getText(); // Mengambil teks dari JTextField txtPrice dan menyimpannya dalam variabel price
            String qty = txtQty.getText(); // Mengambil teks dari JTextField txtQty dan menyimpannya dalam variabel qty
            String pid = txtId.getSelectedItem().toString(); // Mengambil ID produk yang dipilih
            
            if (selectedFile == null) {
                JOptionPane.showMessageDialog(this, "Please select an image.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            con = (Connection) Config.configDB();
            // Membuat pernyataan SQL untuk melakukan pembaruan data produk berdasarkan ID
            pst = con.prepareStatement("UPDATE product SET name=?, size=?, price=?, qty=?, image=? WHERE id=?");

            // Mengatur nilai parameter (?) pada pernyataan SQL dengan nilai dari variabel pname, price, qty, dan pid
            pst.setString(1, pname); // Mengatur nama produk
            pst.setString(2, size); // Mengatur nama produk
            pst.setString(3, price); // Mengatur harga produk
            pst.setString(4, qty); // Mengatur jumlah produk
            
            // Membaca file gambar dan mengkonversinya ke byte array
            FileInputStream fis = new FileInputStream(selectedFile);
            pst.setBinaryStream(5, fis, (int) selectedFile.length());
            
            pst.setString(6, pid); // Mengatur ID produk

            // Mengeksekusi pernyataan SQL untuk melakukan pembaruan data dalam tabel 'product' dan mendapatkan jumlah baris yang terpengaruh
            int k = pst.executeUpdate();
            if(k==1){
                // Jika pembaruan berhasil, menampilkan pesan dialog bahwa data berhasil diperbarui
                JOptionPane.showMessageDialog(this, "Record Update Successfully");
                // Mengosongkan nilai JTextField txtName, txtPrice, dan txtQty serta mengatur fokus kembali ke txtName
                txtName.setText("");
                txtSize.setText("");
                txtPrice.setText("");
                txtQty.setText("");
                imageLabel.setIcon(null);
                txtName.requestFocus();
                // Memuat ulang nomor produk ke dalam JComboBox txtId
                LoadProductNo(); // Memuat nomor produk dari database ke komponen JComboBox
                Fetch();
            }else{
                // Jika pembaruan gagal, menampilkan pesan dialog bahwa pembaruan data gagal
                JOptionPane.showMessageDialog(this, "Record Update Failed");
            }
        } catch (SQLException ex) {
            // Menangani kesalahan jika terjadi kesalahan dalam eksekusi kueri SQL
            Logger.getLogger(Product2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Product2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:

        try {
            con = (Connection) Config.configDB();
            String pid = txtId.getSelectedItem().toString();
            pst = con.prepareStatement("DELETE FROM product WHERE id=?");
            pst.setString(1, pid);

            int k = pst.executeUpdate();
            if(k==1){
                // Jika pembaruan berhasil, menampilkan pesan dialog bahwa data berhasil diperbarui
                JOptionPane.showMessageDialog(this, "Record Delete Successfully");
                // Mengosongkan nilai JTextField txtName, txtPrice, dan txtQty serta mengatur fokus kembali ke txtName
                txtName.setText("");
                txtPrice.setText("");
                txtQty.setText("");
                txtName.requestFocus();
                // Memuat ulang nomor produk ke dalam JComboBox txtId
                LoadProductNo(); // Memuat nomor produk dari database ke komponen JComboBox
                Fetch();
            }else{
                // Jika pembaruan gagal, menampilkan pesan dialog bahwa pembaruan data gagal
                JOptionPane.showMessageDialog(this, "Record Delete Failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Product2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        // Buat instance dari SecondFrame
        AdminFrame adminFrame = new AdminFrame();

        // Tampilkan frame kedua
        adminFrame.setVisible(true);

        // Tutup MainFrame
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        // Buat instance dari SecondFrame
        Product2 productFrame = new Product2();

        // Tampilkan frame kedua
        productFrame.setVisible(true);

        // Tutup MainFrame
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        setLocationRelativeTo(null);
    }//GEN-LAST:event_formComponentShown

    private void btnSelectImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectImageActionPerformed
        // TODO add your handling code here:
        // Membuat instance dari JFileChooser
        JFileChooser fileChooser = new JFileChooser();  

        // Mengatur JFileChooser hanya bisa memilih file (bukan direktori)
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);  

        // Menonaktifkan filter "Terima Semua File"
        fileChooser.setAcceptAllFileFilterUsed(false);  

        // Mengatur filter untuk hanya menerima file gambar dengan ekstensi jpg, png, dan jpeg
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "jpeg"));  

        // Menampilkan dialog pemilihan file dan menyimpan hasilnya dalam variabel result
        int result = fileChooser.showOpenDialog(this);  

        // Memeriksa apakah pengguna menyetujui pemilihan file
        if (result == JFileChooser.APPROVE_OPTION) {  

            // Mendapatkan file yang dipilih oleh pengguna
            selectedFile = fileChooser.getSelectedFile();  

            // Mendapatkan path absolut dari file yang dipilih
            filePath = selectedFile.getAbsolutePath();  

            // Menampilkan gambar pada JLabel
            // Membuat ImageIcon dari file yang dipilih
            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());  

            // Mengubah ukuran gambar agar sesuai dengan ukuran JLabel menggunakan pengaturan SCALE_SMOOTH
            Image img = icon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);  

            // Menampilkan gambar yang telah diubah ukurannya pada JLabel
            imageLabel.setIcon(new ImageIcon(img));  
        }

    }//GEN-LAST:event_btnSelectImageActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        SalesHistoryFrame salesHistoryFrame = new SalesHistoryFrame();
        salesHistoryFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        UserSession.userId = 0;

        // Buka kembali form login
        LoginForm loginFrame = new LoginForm();
        loginFrame.setVisible(true);
        this.dispose(); // Menutup form utama
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        // Buat instance dari TopUpDialog
        AccountProfileDialog accountProfileDialog = new AccountProfileDialog(this, true);

        // Tampilkan dialog
        accountProfileDialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Product2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Product2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Product2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Product2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Product2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel Welcome;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearchData;
    private javax.swing.JButton btnSelectImage;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSize;
    // End of variables declaration//GEN-END:variables
}

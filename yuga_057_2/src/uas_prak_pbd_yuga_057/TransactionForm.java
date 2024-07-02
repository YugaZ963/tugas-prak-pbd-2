/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_prak_pbd_yuga_057;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WIN11
 */
public class TransactionForm extends javax.swing.JFrame {

    /**
     * Creates new form Transaction
     */
    public TransactionForm() {
        initComponents();
        LoadProductNo(); // Memuat nomor produk dari database ke komponen JComboBox
        Fetch();
        customizeTable();
        loadUserBalance();
        setLocationRelativeTo(null); // Tambahkan ini untuk menempatkan frame di tengah
        setIconImage(new ImageIcon(getClass().getResource("/uas_prak_pbd_yuga_057/images/logo.jpeg")).getImage());
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
        pst.setString(1, pid); // Mengatur nilai parameter pertama (?) dengan nilai dari variabel pid
        rs = pst.executeQuery(); // Mengeksekusi pernyataan SQL dan mendapatkan hasilnya

        // Memeriksa apakah hasil kueri mengandung baris data
        if (rs.next()) {
            // Mengatur item yang dipilih dalam JComboBox
            txtId.setSelectedItem(pid);

            // Jika ada data, mengatur nilai JTextField txtName, txtPrice, dan txtQty dengan nilai dari hasil kueri
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
                    JOptionPane.showMessageDialog(this, "Error loading image");
                }
            } else {
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
    
    private void updateTotalPrice() {
    String selectedProduct = (String) txtId.getSelectedItem();
    if (selectedProduct == null || selectedProduct.isEmpty()) return;

    int productId = Integer.parseInt(selectedProduct.split(" - ")[0]);
    int quantity;
    try {
        quantity = Integer.parseInt(txtQtyBuy.getText());
    } catch (NumberFormatException e) {
        lblTotalPrice.setText("Total: ");
        return;
    }

    try {
        con = Config.configDB();
        PreparedStatement pstProduct = con.prepareStatement("SELECT price FROM product WHERE id=?");
        pstProduct.setInt(1, productId);
        ResultSet rsProduct = pstProduct.executeQuery();
        if (rsProduct.next()) {
            double price = rsProduct.getDouble("price");
            double totalPrice = price * quantity;
            lblTotalPrice.setText("Total : " + totalPrice);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }   catch (Exception ex) {
            Logger.getLogger(TransactionForm.class.getName()).log(Level.SEVERE, null, ex);
        }
}

private void loadUserBalance() {
    try {
        con = Config.configDB();
        PreparedStatement pstUser = con.prepareStatement("SELECT money FROM akun WHERE id=?");
        int userId = UserSession.userId; // Asumsikan ID pengguna adalah 1 untuk contoh ini
        pstUser.setInt(1, userId);
        ResultSet rsUser = pstUser.executeQuery();
        if (rsUser.next()) {
            int money = rsUser.getInt("money");
            lblMoney.setText("Money: " + money);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }   catch (Exception ex) {
            Logger.getLogger(TransactionForm.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    private void confirmPurchase() {
    String selectedProduct = (String) txtId.getSelectedItem();
    int productId = Integer.parseInt(selectedProduct.split(" - ")[0]);
    int quantity = Integer.parseInt(txtQtyBuy.getText());

    try {
        con = (Connection) Config.configDB();
        con.setAutoCommit(false);

        // Mengambil data produk
        PreparedStatement pstProduct = con.prepareStatement("SELECT price, qty FROM product WHERE id=?");
        pstProduct.setInt(1, productId);
        ResultSet rsProduct = pstProduct.executeQuery();
        if (!rsProduct.next()) {
            JOptionPane.showMessageDialog(this, "Produk tidak ditemukan");
            return;
        }

        int price = rsProduct.getInt("price");
        int availableQuantity = rsProduct.getInt("qty");
        int totalPrice = price * quantity;
        
        

        // Memeriksa stok
        if (quantity > availableQuantity) {
            JOptionPane.showMessageDialog(this, "Stok tidak mencukupi");
            return;
        }

        // Mengambil data pembeli
        PreparedStatement pstUser = con.prepareStatement("SELECT money FROM akun WHERE id=?");
        // Asumsikan ID pengguna adalah 1 untuk contoh ini
        int userId = UserSession.userId;
        pstUser.setInt(1, userId);
        ResultSet rsUser = pstUser.executeQuery();
        if (!rsUser.next()) {
            JOptionPane.showMessageDialog(this, "Pengguna tidak ditemukan");
            return;
        }

        int money = rsUser.getInt("money");

        // Memeriksa saldo
        if (totalPrice > money) {
            JOptionPane.showMessageDialog(this, "Saldo tidak mencukupi");
            return;
        }

        // Mengurangi stok dan saldo
        PreparedStatement pstUpdateProduct = con.prepareStatement("UPDATE product SET qty=? WHERE id=?");
        pstUpdateProduct.setInt(1, availableQuantity - quantity);
        pstUpdateProduct.setInt(2, productId);
        pstUpdateProduct.executeUpdate();

        PreparedStatement pstUpdateUser = con.prepareStatement("UPDATE akun SET money=? WHERE id=?");
        pstUpdateUser.setDouble(1, money - totalPrice);
        pstUpdateUser.setInt(2, userId);
        pstUpdateUser.executeUpdate();

        // Mencatat transaksi
        PreparedStatement pstTransaction = con.prepareStatement("INSERT INTO transactions (user_id, product_id, quantity, total_price) VALUES (?, ?, ?, ?)");
        pstTransaction.setInt(1, userId);
        pstTransaction.setInt(2, productId);
        pstTransaction.setInt(3, quantity);
        pstTransaction.setDouble(4, totalPrice);
        pstTransaction.executeUpdate();
        int moneyNow = money - totalPrice; 
        lblMoney.setText("Money : " + moneyNow);
        con.commit();
        JOptionPane.showMessageDialog(this, "Pembelian berhasil");
    } catch (SQLException e) {
        e.printStackTrace();
        try {
            con.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }   catch (Exception ex) {
            Logger.getLogger(TransactionForm.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    
    
    private void Fetch(){
        try {
            int g;
            con = (Connection) Config.configDB();
            pst = con.prepareStatement("SELECT * FROM product");
            rs = pst.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();
            g = rss.getColumnCount();
            
            DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
            df.setRowCount(0);
            while(rs.next()){
                Vector v2 = new Vector();
                for(int a=1; a<=g; a++){
                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("name"));
                    v2.add(rs.getString("size"));
                    v2.add(rs.getString("price"));
                    v2.add(rs.getString("qty"));
                    byte[] imgBytes = rs.getBytes("image");
                    
                if (imgBytes != null) {
                    ImageIcon imageIcon = new ImageIcon(imgBytes);
                    Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    v2.add(new ImageIcon(scaledImage));
                } else {
                    v2.add(null);
                }
                }
                df.addRow(v2);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(Product2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
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

        jDialog1 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        Title1 = new javax.swing.JLabel();
        Welcome2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTopUp = new javax.swing.JTextField();
        btnTopUp = new javax.swing.JButton();
        Background1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Title = new javax.swing.JLabel();
        Welcome1 = new javax.swing.JLabel();
        Welcome = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTotalPrice = new javax.swing.JLabel();
        lblMoney = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSize = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnSelectImage = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        imageLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        txtId = new javax.swing.JComboBox<>();
        txtMoney = new javax.swing.JTextField();
        btnBuy = new javax.swing.JButton();
        txtQtyBuy = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Background = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jDialog1.setModal(true);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Title1.setBackground(new java.awt.Color(255, 255, 255));
        Title1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Title1.setText("Form Top Up Pembayaran");
        Title1.setOpaque(true);
        jPanel2.add(Title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, -1));

        Welcome2.setBackground(new java.awt.Color(255, 255, 255));
        Welcome2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Welcome2.setText("Pilih Produk yang akan dibeli");
        Welcome2.setOpaque(true);
        jPanel2.add(Welcome2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Amount of money");
        jLabel9.setOpaque(true);
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, 20));

        txtTopUp.setEditable(false);
        jPanel2.add(txtTopUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 230, -1));

        btnTopUp.setText("Top Up");
        btnTopUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTopUpActionPerformed(evt);
            }
        });
        jPanel2.add(btnTopUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 180, -1));

        Background1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uas_prak_pbd_yuga_057/images/gambarToko-1.jpg"))); // NOI18N
        jPanel2.add(Background1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 380));

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RAVAZKA Shop");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setEditingColumn(0);
        jTable1.setEditingRow(0);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 1330, 390));

        Title.setBackground(new java.awt.Color(255, 255, 255));
        Title.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Title.setText("Form Pembelian Produk");
        Title.setOpaque(true);
        jPanel1.add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        Welcome1.setBackground(new java.awt.Color(255, 255, 255));
        Welcome1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Welcome1.setText("Pilih Produk yang akan dibeli");
        Welcome1.setOpaque(true);
        jPanel1.add(Welcome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, -1, -1));

        Welcome.setBackground(new java.awt.Color(255, 255, 255));
        Welcome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Welcome.setText("List - list Produk dari Toko RAVAZKA");
        Welcome.setOpaque(true);
        jPanel1.add(Welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 320, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Quantity : ");
        jLabel10.setOpaque(true);
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, -1, 20));

        lblTotalPrice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalPrice.setText("Total : ");
        lblTotalPrice.setOpaque(true);
        jPanel1.add(lblTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 240, 170, 20));

        lblMoney.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMoney.setText("Your Money : ");
        lblMoney.setOpaque(true);
        jPanel1.add(lblMoney, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, -1, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Product Name");
        jLabel2.setOpaque(true);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 20));

        txtName.setEditable(false);
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 380, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Product Size");
        jLabel6.setOpaque(true);
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 20));

        txtSize.setEditable(false);
        jPanel1.add(txtSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 380, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Product Price");
        jLabel3.setOpaque(true);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 20));

        txtPrice.setEditable(false);
        jPanel1.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 380, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Product Qty");
        jLabel4.setOpaque(true);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 20));

        txtQty.setEditable(false);
        jPanel1.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 380, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Product Image");
        jLabel7.setOpaque(true);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, 20));

        btnSelectImage.setText("Search");
        btnSelectImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectImageActionPerformed(evt);
            }
        });
        jPanel1.add(btnSelectImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 180, -1));

        jButton7.setText("Save");
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 180, -1));

        imageLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        imageLabel.setOpaque(true);
        jPanel1.add(imageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 60, 220, 240));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Image");
        jLabel8.setOpaque(true);
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 30, -1, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Product Id");
        jLabel5.setOpaque(true);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 200, -1, 20));

        jButton5.setText("Search");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 310, -1, -1));

        txtId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 200, -1, -1));
        jPanel1.add(txtMoney, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 200, 200, -1));

        btnBuy.setText("BUY");
        btnBuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuyActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, 120, -1));
        jPanel1.add(txtQtyBuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 160, 200, -1));

        jButton1.setText("Count");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 240, -1, -1));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uas_prak_pbd_yuga_057/images/gambarToko-1.jpg"))); // NOI18N
        jPanel1.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        menuBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenu1.setText("Home ");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Home");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        menuBar.add(jMenu1);

        jMenu2.setText("Transaction");

        jMenuItem2.setText("Buy");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem4.setText("Top Up");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        menuBar.add(jMenu2);

        jMenu3.setText("Account");

        jMenuItem3.setText("Log out");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        menuBar.add(jMenu3);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        // Buat instance dari SecondFrame
        MainFrame057 mainFrame = new MainFrame057();

        // Tampilkan frame kedua
        mainFrame.setVisible(true);

        // Tutup MainFrame
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        TransactionForm transaction = new TransactionForm();
    
    // Tampilkan frame kedua
    transaction.setVisible(true);
    
    // Tutup MainFrame
    this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnSelectImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectImageActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "jpeg"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
            // Tampilkan gambar pada JLabel
            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        }
    }//GEN-LAST:event_btnSelectImageActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        searchProductById();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnBuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuyActionPerformed
        // TODO add your handling code here:
        confirmPurchase();
    }//GEN-LAST:event_btnBuyActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String selectedProduct = (String) txtId.getSelectedItem();
        int productId = Integer.parseInt(selectedProduct.split(" - ")[0]);
        int quantity = Integer.parseInt(txtQtyBuy.getText());
        try {
        con = (Connection) Config.configDB();
        con.setAutoCommit(false);

        // Mengambil data produk
        PreparedStatement pstProduct = con.prepareStatement("SELECT price, qty FROM product WHERE id=?");
        pstProduct.setInt(1, productId);
        ResultSet rsProduct = pstProduct.executeQuery();
        if (!rsProduct.next()) {
            JOptionPane.showMessageDialog(this, "Produk tidak ditemukan");
            return;
        }

        int price = rsProduct.getInt("price");
        int availableQuantity = rsProduct.getInt("qty");
        int totalPrice = price * quantity;
        
        
        lblTotalPrice.setText("Total : " + totalPrice);
        
        }catch (SQLException e) {
        e.printStackTrace();
        try {
            con.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }   catch (Exception ex) {
            Logger.getLogger(TransactionForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        UserSession.userId = 0;

        // Buka kembali form login
        LoginForm loginFrame = new LoginForm();
        loginFrame.setVisible(true);
        this.dispose(); // Menutup form utama
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        TopUpDialog topUpForm = new TopUpDialog(this, true);
        topUpForm.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnTopUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTopUpActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnTopUpActionPerformed

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
            java.util.logging.Logger.getLogger(TransactionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransactionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransactionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransactionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Background1;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel Title1;
    private javax.swing.JLabel Welcome;
    private javax.swing.JLabel Welcome1;
    private javax.swing.JLabel Welcome2;
    private javax.swing.JButton btnBuy;
    private javax.swing.JButton btnSelectImage;
    private javax.swing.JButton btnTopUp;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblMoney;
    private javax.swing.JLabel lblTotalPrice;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JComboBox<String> txtId;
    private javax.swing.JTextField txtMoney;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtQtyBuy;
    private javax.swing.JTextField txtSize;
    private javax.swing.JTextField txtTopUp;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package data.mahasiswa;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class App extends javax.swing.JFrame {

    /**
     * Creates new form Data
     */
    public App() {
        setTitle("Data Mahasiswa");
        initComponents();
        tabel();
    }
    private void tabel()  {
    DefaultTableModel tb = new DefaultTableModel();
    // Memberi nama pada setiap kolom tabel
    tb.addColumn("NPM");
    tb.addColumn("Nama");
    tb.addColumn("Jenis Kelamin");
    tb.addColumn("Jurusan");
    tb.addColumn("Kelas");
    
    jTable1.setModel(tb);
    try {
          java.sql.Connection conn = (Connection)konfigurasi.configDB();     //memanggil koneksi yang terdapat pada class konfigurasi
            java.sql.Statement pstmt = conn.createStatement();
            java.sql.ResultSet rs;   // interface untuk menampung data hasil query.
        System.out.println("Database ditemukan");
        pstmt = conn.createStatement();    // Membuat objek statement
        // Mengambil data dari database
         rs = pstmt.executeQuery("select *from mahasiswa"); // eksekusi query dan simpan hasilnya di obj ResultSet
        
        while (rs.next())
        {
            // Mengambil data dari database berdasarkan nama kolom pada tabel
            // Lalu di tampilkan ke dalam JTable
            tb.addRow(new Object[] {
                //getString digunakan untuk mengambil kolom dengan tipe data string. 
                rs.getString (3),   
                rs.getString (2),
                rs.getString (4),
                rs.getString (5),
                rs.getString (6),
            });
        }
    }catch ( SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtkelas = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cmbjurusan = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        btnsimpan = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cmbjenkel = new javax.swing.JComboBox<>();
        txtnpm = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(41, 209, 86));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Mahasiswa Teknik");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NPM");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama");

        txtnama.setBackground(new java.awt.Color(41, 209, 86));
        txtnama.setForeground(new java.awt.Color(255, 255, 255));
        txtnama.setBorder(null);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jenis Kelamin");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Jurusan");

        txtkelas.setBackground(new java.awt.Color(41, 209, 86));
        txtkelas.setForeground(new java.awt.Color(255, 255, 255));
        txtkelas.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Kelas");

        cmbjurusan.setBackground(new java.awt.Color(240, 240, 240));
        cmbjurusan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbjurusan.setForeground(new java.awt.Color(255, 255, 255));
        cmbjurusan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "Teknik Informatika", "Teknik Elektro", "Teknik Industri", "Teknik Sipil", "Arsitektur" }));
        cmbjurusan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbjurusanActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));

        btnsimpan.setBackground(new java.awt.Color(11, 94, 215));
        btnsimpan.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnsimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnedit.setBackground(new java.awt.Color(255, 153, 0));
        btnedit.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnedit.setForeground(new java.awt.Color(255, 255, 255));
        btnedit.setText("Edit");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        btndelete.setBackground(new java.awt.Color(255, 51, 51));
        btndelete.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btndelete.setForeground(new java.awt.Color(255, 255, 255));
        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnexit.setBackground(new java.awt.Color(153, 0, 153));
        btnexit.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnexit.setForeground(new java.awt.Color(255, 255, 255));
        btnexit.setText("Exit");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        jTable1.setBackground(new java.awt.Color(35, 35, 35));
        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        cmbjenkel.setBackground(new java.awt.Color(240, 240, 240));
        cmbjenkel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbjenkel.setForeground(new java.awt.Color(255, 255, 255));
        cmbjenkel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "Laki - Laki", "Perempuan" }));
        cmbjenkel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbjenkelActionPerformed(evt);
            }
        });

        txtnpm.setBackground(new java.awt.Color(41, 209, 86));
        txtnpm.setForeground(new java.awt.Color(255, 255, 255));
        txtnpm.setBorder(null);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Image : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(btnsimpan)
                        .addGap(18, 18, 18)
                        .addComponent(btnedit)
                        .addGap(18, 18, 18)
                        .addComponent(btndelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnexit))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                    .addGap(37, 37, 37)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtkelas)
                                        .addComponent(cmbjurusan, 0, 236, Short.MAX_VALUE)
                                        .addComponent(jSeparator3)
                                        .addComponent(jSeparator1)
                                        .addComponent(txtnama)
                                        .addComponent(jSeparator2)
                                        .addComponent(cmbjenkel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtnpm))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnpm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(8, 8, 8)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbjenkel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbjurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(txtkelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsimpan)
                    .addComponent(btnedit)
                    .addComponent(btndelete)
                    .addComponent(btnexit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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

    private void cmbjurusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbjurusanActionPerformed
        // TODO add your handling code here:
         // untuk mengambil nilai dari ComboBox  berdasarkan list item dengan perintah getSelectedItem() ke data String.
        if( cmbjurusan.getSelectedItem()=="Teknik Informatika"){
            // kondisi data yang diambil dari jComboBox berdasarkan list item, menggunakan operator pemberi nilai sama dengan (==),
        }else if( cmbjurusan.getSelectedItem()=="Teknik Elektro"){
            // kondisi data yang diambil dari jComboBox berdasarkan list item, menggunakan operator pemberi nilai sama dengan (==),
        }else if( cmbjurusan.getSelectedItem()=="Teknik Industri"){
            // kondisi data yang diambil dari jComboBox berdasarkan list item, menggunakan operator pemberi nilai sama dengan (==),
        }else if( cmbjurusan.getSelectedItem()=="Teknik Sipil"){
            // kondisi data yang diambil dari jComboBox berdasarkan list item, menggunakan operator pemberi nilai sama dengan (==),
        }else if( cmbjurusan.getSelectedItem()=="Arsitektur"){
            // kondisi data yang diambil dari jComboBox berdasarkan list item, menggunakan operator pemberi nilai sama dengan (==),
        }
    }//GEN-LAST:event_cmbjurusanActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        //memanggil variable yang sudah dibuat pada class mahasiswa
        Mahasiswa  data = new Mahasiswa();
        // ambil input dari user
        data.setNpm(txtnpm.getText());
        data.setNama(txtnama.getText());
        data.setJenis_kelamin((String)cmbjenkel.getSelectedItem());
        data.setJurusan((String)cmbjurusan.getSelectedItem());
        data.setKelas(txtkelas.getText());
        
         
        
        try{
            
            if(txtnama.getText().equals("") || txtnpm.getText().equals("")
                    || cmbjenkel.getSelectedItem().equals("") || cmbjurusan.getSelectedItem().equals("")
                    || txtkelas.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Data tidak Boleh Kosong !!", "Validasi Data",JOptionPane.INFORMATION_MESSAGE);
                return;
                
        }else{
            String sql = "insert into mahasiswa (npm, nama, jenis_kelamin, jurusan, kelas) values ('"+(txtnpm.getText())+"','"+(txtnama.getText())
                    +"','"+((String)cmbjenkel.getSelectedItem())+"','"+((String)cmbjurusan.getSelectedItem())+"','"+(txtkelas.getText())+"')";       
            java.sql.Connection conn = (Connection)konfigurasi.configDB();   //memanggil koneksi yang sudah dibuat pada class konfigurasi untuk menjalankan query 
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();     //untuk menjalankan eksekusi query
            pstmt.close();
            txtnpm.setText("");
            txtnama.setText("");
            cmbjenkel.setSelectedIndex(0);
            cmbjurusan.setSelectedIndex(0);
            txtkelas.setText("");
            tabel();     //memanggil fungsi tabel dan merefresh ketika selesai menjalankan query
            JOptionPane.showMessageDialog(null,"Data berhasil di simpan");  //notifikasi bahwa data berhasil disimpan
            }
            // query simpan
            
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnexitActionPerformed

    private void cmbjenkelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbjenkelActionPerformed
        // TODO add your handling code here:
        // untuk mengambil nilai dari ComboBox  berdasarkan list item dengan perintah getSelectedItem() ke data String.
        if( cmbjenkel.getSelectedItem()=="Laki - Laki"){
            // kondisi data yang diambil dari jComboBox berdasarkan list item, menggunakan operator pemberi nilai sama dengan (==),
        }else if( cmbjenkel.getSelectedItem()=="Perempuan"){
            // kondisi data yang diambil dari jComboBox berdasarkan list item, menggunakan operator pemberi nilai sama dengan (==),
        }
    }//GEN-LAST:event_cmbjenkelActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        //memanggil variable yang sudah dibuat pada class mahasiswa
        Mahasiswa  data = new Mahasiswa();
        // ambil input dari user
        data.setNpm(txtnpm.getText());
        data.setNama(txtnama.getText());
        data.setJenis_kelamin((String)cmbjenkel.getSelectedItem());
        data.setJurusan((String)cmbjurusan.getSelectedItem());
        data.setKelas(txtkelas.getText());
        
         
        
        try{
            // query update
            String sql = "update mahasiswa SET nama = '"+(txtnama.getText())+"', jenis_kelamin = '"+((String)cmbjenkel.getSelectedItem())+"', jurusan = '"
                    +((String)cmbjurusan.getSelectedItem())+"', kelas = '"+(txtkelas.getText())+"'  WHERE npm = '"+(txtnpm.getText())+"'";   
            java.sql.Connection conn = (Connection)konfigurasi.configDB();       //memanggil koneksi yang sudah dibuat pada class konfigurasi untuk menjalankan query 
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();    //untuk menjalankan eksekusi query
            pstmt.close();
            txtnpm.setText("");
            txtnama.setText("");
            cmbjenkel.setSelectedIndex(0);
            cmbjurusan.setSelectedIndex(0);
            txtkelas.setText("");
            tabel();         //memanggil fungsi tabel dan merefresh ketika selesai menjalankan query
            JOptionPane.showMessageDialog(null,"Data berhasil di ubah");  //notifikasi bahwa data berhasil diubah
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btneditActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        //memanggil variable yang sudah dibuat pada class mahasiswa
        Mahasiswa  data = new Mahasiswa();
        // ambil input dari user
        data.setNpm(txtnpm.getText());
        data.setNama(txtnama.getText());
        data.setJenis_kelamin((String)cmbjenkel.getSelectedItem());
        data.setJurusan((String)cmbjurusan.getSelectedItem());
        data.setKelas(txtkelas.getText());
       //menampilkan notifikasi apakah data akan di delete lalu menampilkan pilihan yes atau no
         int ok = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
         if (ok == 0) {
             //query delete
            String sql = "DELETE FROM mahasiswa WHERE npm='" + (txtnpm.getText().trim()) + "'";
         try{                  
            java.sql.Connection conn = (Connection)konfigurasi.configDB();    //memanggil koneksi yang sudah dibuat pada class konfigurasi untuk menjalankan query 
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus", "Hapus Data", JOptionPane.INFORMATION_MESSAGE);  //notifikasi bahwa data berhasil dihapus]
            pstmt.execute();     //untuk menjalankan eksekusi query
            //merefresh inputan pada saat selesai menghapus data 
            pstmt.close();
            txtnpm.setText("");
            txtnama.setText("");
            cmbjenkel.setSelectedIndex(0);
            cmbjurusan.setSelectedIndex(0);
            txtkelas.setText("");
            tabel();         //memanggil fungsi tabel dan merefresh ketika selesai menjalankan query
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
         }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        txtnpm.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        txtnama.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
        cmbjenkel.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
        cmbjurusan.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
        txtkelas.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString());
        btnsimpan.setEnabled(false);
        btnedit.setEnabled(true);
        btnexit.setEnabled(true);
        btndelete.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox<String> cmbjenkel;
    private javax.swing.JComboBox<String> cmbjurusan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtkelas;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnpm;
    // End of variables declaration//GEN-END:variables
}

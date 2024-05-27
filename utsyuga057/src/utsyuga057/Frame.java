/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utsyuga057;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author pc301-07
 */
public class Frame extends javax.swing.JFrame {

    /**
     * Creates new form Frame
     */
    public Frame() {
        initComponents();
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        namaKonsumenTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        kodeKonsumenCB = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jumlahBarangTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        uangBayarTxt = new javax.swing.JTextField();
        kodeBarangCB = new javax.swing.JComboBox<>();
        uangKembalianTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        totalHargaTxt = new javax.swing.JTextField();
        btnUangBayar = new javax.swing.JButton();
        btnDiskon = new javax.swing.JButton();
        btnTotal = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        kodeKonsumenTxt = new javax.swing.JTextField();
        kodeBarangTtxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Warung Serba Guna");

        jLabel2.setText("Nama Konsumen");

        namaKonsumenTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaKonsumenTxtActionPerformed(evt);
            }
        });

        jLabel3.setText("Kode Konsumen");

        kodeKonsumenCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "m057", "n057"}));
        kodeKonsumenCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeKonsumenCBActionPerformed(evt);
            }
        });

        jLabel4.setText("Kode Barang");

        jLabel5.setText("Jumlah Barang ");

        jumlahBarangTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahBarangTxtActionPerformed(evt);
            }
        });

        jLabel6.setText("Uang Bayar");

        uangBayarTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uangBayarTxtActionPerformed(evt);
            }
        });

        kodeBarangCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "b000", "b001"}));
        kodeBarangCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeBarangCBActionPerformed(evt);
            }
        });

        uangKembalianTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uangKembalianTxtActionPerformed(evt);
            }
        });

        jLabel7.setText("Uang Kembalian");

        jLabel8.setText("Total Harga");

        totalHargaTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalHargaTxtActionPerformed(evt);
            }
        });

        btnUangBayar.setText("BUY");
        btnUangBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUangBayarActionPerformed(evt);
            }
        });

        btnDiskon.setText("DISCOUNT");

        btnTotal.setText("TOTAL");
        btnTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalActionPerformed(evt);
            }
        });

        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        kodeKonsumenTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeKonsumenTxtActionPerformed(evt);
            }
        });

        kodeBarangTtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeBarangTtxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(141, 141, 141)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namaKonsumenTxt)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(kodeKonsumenCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(kodeKonsumenTxt))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(144, 144, 144)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(uangKembalianTxt)
                            .addComponent(totalHargaTxt)
                            .addComponent(uangBayarTxt)
                            .addComponent(jumlahBarangTxt)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(kodeBarangCB, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(kodeBarangTtxt))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnUangBayar)
                        .addGap(18, 18, 18)
                        .addComponent(btnDiskon)
                        .addGap(26, 26, 26)
                        .addComponent(btnTotal)
                        .addGap(38, 38, 38)
                        .addComponent(btnDelete)
                        .addGap(0, 167, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(namaKonsumenTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(kodeKonsumenCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(kodeKonsumenTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(kodeBarangCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(kodeBarangTtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jumlahBarangTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(uangBayarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(totalHargaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(uangKembalianTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUangBayar)
                    .addComponent(btnDiskon)
                    .addComponent(btnTotal)
                    .addComponent(btnDelete))
                .addContainerGap(85, Short.MAX_VALUE))
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

    private void kodeKonsumenCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeKonsumenCBActionPerformed
    String kodeKonsumen = kodeKonsumenCB.getSelectedItem().toString() ;
// Periksa j ika kode konsumen adalah "M082"
if (kodeKonsumen.equalsIgnoreCase("m057") ){
    kodeKonsumenTxt.setText("Member") ;
}else {
    // Jika bukan "M082"
    kodeKonsumenTxt.setText("Non—Member");
}

    }//GEN-LAST:event_kodeKonsumenCBActionPerformed

    private void kodeBarangCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeBarangCBActionPerformed
    String KodeBarang = kodeBarangCB.getSelectedItem().toString();
        int jumlahBarang = Integer.parseInt(kodeBarangTtxt.getText());
        int hargaBarang = 0;
        if (KodeBarang.equalsIgnoreCase("b000")) {
            kodeBarangTtxt.setText("Pensil");
            hargaBarang = 5000;
        }else if (KodeBarang.equalsIgnoreCase("b001")){
            hargaBarang = 10000;
            kodeBarangTtxt.setText("Buku");
        }
    }//GEN-LAST:event_kodeBarangCBActionPerformed

    private void namaKonsumenTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaKonsumenTxtActionPerformed
    String namaKonsumen = namaKonsumenTxt.getText();
    }//GEN-LAST:event_namaKonsumenTxtActionPerformed

    private void jumlahBarangTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahBarangTxtActionPerformed
    String jumlahBarang = jumlahBarangTxt.getText();
    }//GEN-LAST:event_jumlahBarangTxtActionPerformed

    private void uangBayarTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uangBayarTxtActionPerformed
    double uangBayar = Double.parseDouble(uangBayarTxt.getText());
    }//GEN-LAST:event_uangBayarTxtActionPerformed

    private void totalHargaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalHargaTxtActionPerformed
        double uangBayar = Double.parseDouble(totalHargaTxt.getText());
    }//GEN-LAST:event_totalHargaTxtActionPerformed

    private void uangKembalianTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uangKembalianTxtActionPerformed
        double uangBayar = Double.parseDouble(uangKembalianTxt.getText());
    }//GEN-LAST:event_uangKembalianTxtActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        namaKonsumenTxt.setText("");
        jumlahBarangTxt.setText("");
        totalHargaTxt.setText("");
        uangKembalianTxt.setText("");
        uangBayarTxt.setText("");
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalActionPerformed
        double totalHarga = Double.parseDouble(uangBayarTxt.getText()) * Double.parseDouble(jumlahBarangTxt.getText());
        totalHargaTxt.setText(String.valueOf(totalHarga));
    }//GEN-LAST:event_btnTotalActionPerformed

    private void btnUangBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUangBayarActionPerformed
        double uangBayar = Double.parseDouble(uangBayarTxt.getText());
    }//GEN-LAST:event_btnUangBayarActionPerformed

    private void kodeKonsumenTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeKonsumenTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeKonsumenTxtActionPerformed

    private void kodeBarangTtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeBarangTtxtActionPerformed
        
        
    }//GEN-LAST:event_kodeBarangTtxtActionPerformed

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
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDiskon;
    private javax.swing.JButton btnTotal;
    private javax.swing.JButton btnUangBayar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jumlahBarangTxt;
    private javax.swing.JComboBox<String> kodeBarangCB;
    private javax.swing.JTextField kodeBarangTtxt;
    private javax.swing.JComboBox<String> kodeKonsumenCB;
    private javax.swing.JTextField kodeKonsumenTxt;
    private javax.swing.JTextField namaKonsumenTxt;
    private javax.swing.JTextField totalHargaTxt;
    private javax.swing.JTextField uangBayarTxt;
    private javax.swing.JTextField uangKembalianTxt;
    // End of variables declaration//GEN-END:variables
}

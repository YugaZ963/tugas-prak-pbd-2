/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ubahpw extends javax.swing.JFrame {
    private JPasswordField currentPasswordField;
    private JPasswordField newPasswordField;
    private JButton changePasswordButton;

    public ubahpw() {
        initComponents();
    }

    /**
     * 
     */
    private void initComponents() {
        currentPasswordField = new JPasswordField();
        newPasswordField = new JPasswordField();
        changePasswordButton = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ubah Password");

        changePasswordButton.setText("Ubah Password");
        changePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                handleChangePassword(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(currentPasswordField)
                                        .addComponent(newPasswordField)
                                        .addComponent(changePasswordButton, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(50, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(currentPasswordField, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(newPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(changePasswordButton)
                                .addContainerGap(30, Short.MAX_VALUE)));

        pack();
    }

    private void handleChangePassword(ActionEvent evt) {
        char[] currentPassword = currentPasswordField.getPassword();
        char[] newPassword = newPasswordField.getPassword();

        // Tambahkan logika untuk mengelola perubahan password di sini
        // Misalnya, validasi password lama, menyimpan password baru, dll.

        // Contoh sederhana hanya menampilkan pesan
        JOptionPane.showMessageDialog(this, "Password berhasil diubah.");

        // Hapus nilai yang sudah dimasukkan untuk keamanan
        currentPasswordField.setText("");
        newPasswordField.setText("");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UbahPassword().setVisible(true);
            }
        });
    }
}

// <editor-fold defaultstate="collapsed" desc="Generated
// Code">//GEN-BEGIN:initComponents
// </editor-fold>//GEN-END:initComponents

/**
 * @param args the command line arguments
 */
// public static void main(String args[]) {
// /* Set the Nimbus look and feel */
// //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code
// (optional) ">
// /* If Nimbus (introduced in Java SE 6) is not available, stay with the
// default look and feel.
// * For details see
// http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
// */
// try {
// for (javax.swing.UIManager.LookAndFeelInfo info :
// javax.swing.UIManager.getInstalledLookAndFeels()) {
// if ("Nimbus".equals(info.getName())) {
// javax.swing.UIManager.setLookAndFeel(info.getClassName());
// break;
// }
// }
// } catch (ClassNotFoundException ex) {
// java.util.logging.Logger.getLogger(ubahpw.class.getName()).log(java.util.logging.Level.SEVERE,
// null, ex);
// } catch (InstantiationException ex) {
// java.util.logging.Logger.getLogger(ubahpw.class.getName()).log(java.util.logging.Level.SEVERE,
// null, ex);
// } catch (IllegalAccessException ex) {
// java.util.logging.Logger.getLogger(ubahpw.class.getName()).log(java.util.logging.Level.SEVERE,
// null, ex);
// } catch (javax.swing.UnsupportedLookAndFeelException ex) {
// java.util.logging.Logger.getLogger(ubahpw.class.getName()).log(java.util.logging.Level.SEVERE,
// null, ex);
// }
// //</editor-fold>
//
// /* Create and display the form */
// java.awt.EventQueue.invokeLater(new Runnable() {
// public void run() {
// new ubahpw().setVisible(true);
// }
// });
// }

// Variables declaration - do not modify//GEN-BEGIN:variables
// End of variables declaration//GEN-END:variables

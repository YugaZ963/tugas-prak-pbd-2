/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_pbd_yuga_057;
// Mengimpor pustaka yang diperlukan untuk membuat antarmuka pengguna dan tabel
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;

// Mendefinisikan kelas 'ImageRenderer' yang memperluas 'DefaultTableCellRenderer'
public class ImageRenderer extends DefaultTableCellRenderer {

    // Mengoverride metode 'getTableCellRendererComponent' untuk mengatur bagaimana sel tabel dirender
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        // Jika nilai sel adalah sebuah 'ImageIcon', maka atur ikon pada sel tersebut
        if (value instanceof ImageIcon) {
            setIcon((ImageIcon) value);
        } else {
            // Jika tidak, kosongkan ikon pada sel tersebut
            setIcon(null);
        }
        // Mengembalikan komponen ini sebagai representasi visual dari sel
        return this;
    }
}



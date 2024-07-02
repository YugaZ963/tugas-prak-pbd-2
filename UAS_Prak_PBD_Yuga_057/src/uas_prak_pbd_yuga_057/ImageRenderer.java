/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_prak_pbd_yuga_057;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;

public class ImageRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        if (value instanceof ImageIcon) {
            setIcon((ImageIcon) value);
        } else {
            setIcon(null);
        }
        return this;
    }
}


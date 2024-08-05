/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_prak_pbd_yuga_057;
import javax.swing.table.DefaultTableModel;

public class NonEditableTableModel extends DefaultTableModel {
    public NonEditableTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    NonEditableTableModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Semua sel tidak dapat diedit
    }
}


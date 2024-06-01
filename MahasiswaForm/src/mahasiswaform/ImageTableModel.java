/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahasiswaform;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class ImageTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Name", "Image"};
    private final ArrayList<ImageData> list;

    public ImageTableModel(ArrayList<ImageData> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ImageData imgData = list.get(rowIndex);
        switch (columnIndex) {
            case 0: return imgData.getId();
            case 1: return imgData.getName();
            case 2: return imgData.getImageIcon();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 2) {
            return ImageIcon.class;
        }
        return String.class;
    }
}

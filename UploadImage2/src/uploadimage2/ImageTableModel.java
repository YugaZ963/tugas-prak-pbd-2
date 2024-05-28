/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uploadimage2;
import javax.swing.table.AbstractTableModel;
import javax.swing.ImageIcon;
import java.util.List;
/**
 *
 * @author WIN11
 */
public class ImageTableModel extends AbstractTableModel {

    private final String[] columnNames = {"ID", "Name", "Image"};
    private final List<Object[]> data;

    public ImageTableModel(List<Object[]> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data.get(row)[col];
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        if (col == 2) {
            return ImageIcon.class;
        }
        return String.class;
    }
}

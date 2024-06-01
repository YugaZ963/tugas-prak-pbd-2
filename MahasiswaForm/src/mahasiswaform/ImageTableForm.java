/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahasiswaform;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ImageTableForm extends JFrame {
    private JTable table;
    private ImageTableModel tableModel;

    public ImageTableForm() {
        setTitle("Image Table");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageDAO dao = new ImageDAO();
        ArrayList<ImageData> list = dao.getImageList();
        tableModel = new ImageTableModel(list);
        table = new JTable(tableModel);
        table.setRowHeight(100);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ImageTableForm().setVisible(true);
            }
        });
    }
}

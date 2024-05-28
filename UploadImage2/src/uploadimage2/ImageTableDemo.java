/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uploadimage2;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author WIN11
 */
public class ImageTableDemo extends JFrame {

    private JTable table;
    private Connection con;

    public ImageTableDemo() {
        connectDatabase();
        initComponents();
    }

    private void connectDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uploadimage-java2", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        setTitle("Image Table Demo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Object[]> data = loadImageData();
        ImageTableModel model = new ImageTableModel(data);
        table = new JTable(model);
        table.setRowHeight(100);
        table.getColumnModel().getColumn(2).setCellRenderer(new ImageCellRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private List<Object[]> loadImageData() {
        List<Object[]> data = new ArrayList<>();
        try {
            String query = "SELECT id, name, image FROM images";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                byte[] imgBytes = rs.getBytes("image");

                ImageIcon imageIcon = new ImageIcon(imgBytes);
                data.add(new Object[]{id, name, imageIcon});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ImageTableDemo().setVisible(true);
        });
    }
}

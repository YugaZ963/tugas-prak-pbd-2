/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahasiswaform;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class ImageDAO {
    public ArrayList<ImageData> getImageList() {
        ArrayList<ImageData> list = new ArrayList<>();
        String query = "SELECT id, name, image FROM images";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                byte[] imageBytes = rs.getBytes("image");

                ImageIcon imageIcon = null;
                if (imageBytes != null) {
                    Image image = new ImageIcon(imageBytes).getImage();
                    Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(scaledImage);
                }

                ImageData imgData = new ImageData(id, name, imageIcon);
                list.add(imgData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}



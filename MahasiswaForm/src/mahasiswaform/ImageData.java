/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahasiswaform;
import javax.swing.ImageIcon;

public class ImageData {
    private int id;
    private String name;
    private ImageIcon imageIcon;

    public ImageData(int id, String name, ImageIcon imageIcon) {
        this.id = id;
        this.name = name;
        this.imageIcon = imageIcon;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
}

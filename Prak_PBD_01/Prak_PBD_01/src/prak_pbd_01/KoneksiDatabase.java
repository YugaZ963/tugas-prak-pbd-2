/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prak_pbd_01;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author WIN11
 */
public class KoneksiDatabase {
    Connection con;
    public static Connection koneksi(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prakpbd-form-mhsw", "root","");
            return con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
}

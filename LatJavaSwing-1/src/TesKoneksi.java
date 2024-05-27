/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author WIN11
 */
public class TesKoneksi {
       private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            String url = "jdbc:mysql://localhost:3306/your_database_name"; // Ganti dengan informasi database Anda
            String user = "your_username"; // Ganti dengan username database Anda
            String password = "your_password"; // Ganti dengan password database Anda
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log.reg.pkg1;
import java.sql.*;


/**
 *
 * @author WIN11
 */
public class KoneksiDatabase {
    
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Ganti dengan informasi database Anda
                String url = "jdbc:mysql://localhost:3306/logreg-java";
                String username = "root";
                String password = "";

                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Koneksi database berhasil dibuat!");
            } catch (SQLException e) {
                System.err.println("Gagal terhubung ke database: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Koneksi database telah ditutup.");
            } catch (SQLException e) {
                System.err.println("Gagal menutup koneksi database: " + e.getMessage());
            }
        }
    }
}

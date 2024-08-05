/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_pbd_yuga_057;
// Mengimpor kelas Driver dari com.mysql.cj.jdbc untuk registrasi driver MySQL
import com.mysql.cj.jdbc.Driver;

// Mengimpor kelas-kelas yang diperlukan untuk koneksi database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    // Mendeklarasikan variabel static untuk menyimpan objek Connection
    private static Connection mysqlconfig;

    // Method static untuk mengkonfigurasi dan mendapatkan koneksi database
    public static Connection configDB() throws Exception {
        try {
            // Menyimpan URL koneksi database ke dalam variabel
            String url = "jdbc:mysql://localhost:3306/uas-pbd";
            
            // Menyimpan username MySQL ke dalam variabel
            String user = "root";
            
            // Menyimpan password MySQL ke dalam variabel
            String password = "";
            
            // Mendaftarkan driver MySQL
            DriverManager.registerDriver(new Driver());
            
            // Mendapatkan koneksi ke database menggunakan URL, username, dan password
            mysqlconfig = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            // Menangani kesalahan koneksi dan mencetak pesan kesalahan
            System.err.println("koneksi gagal" + e.getMessage());
        }
        
        // Mengembalikan objek Connection
        return mysqlconfig;
    }
}


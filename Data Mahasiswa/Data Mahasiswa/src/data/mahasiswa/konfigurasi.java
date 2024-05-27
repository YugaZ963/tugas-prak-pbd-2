/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.mahasiswa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class konfigurasi {
     private static Connection MysqlConfig;
    
    public static Connection configDB() throws SQLException{
        try{
            //objek yang diperlukan untuk mengelola database
            Connection con;     //interface yang menyediakan method untuk menghubungi database;
            Statement pstmt;    //inteface untuk mengeksekusi query;
            ResultSet rs;             // interface untuk menampung data hasil query.
            String url = "jdbc:mysql://localhost:3306/data_mahasiswa";
            String user = "root";
            String pass = "";
            
            MysqlConfig = DriverManager.getConnection(url,user,pass);   // Membuat koneksi ke database
        } catch (SQLException e){
            System.out.println("koneksi database gagal!" + e.getMessage());
        }
        return MysqlConfig;
    }
}

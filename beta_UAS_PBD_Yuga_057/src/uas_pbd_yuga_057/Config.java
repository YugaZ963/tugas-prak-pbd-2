/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_prak_pbd_yuga_057;
import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    private static Connection mysqlconfig;
    public static Connection configDB() throws Exception{
        try {
            String url = "jdbc:mysql://localhost:3306/uas-prak-pbd";
            String user = "root";
            String password = "";
            DriverManager.registerDriver(new Driver());
            mysqlconfig = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.err.println("koneksi gagal" + e.getMessage());
        }
        return mysqlconfig;
    }
}

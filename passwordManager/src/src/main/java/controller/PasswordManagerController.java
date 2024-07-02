/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.main.java.controller;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import src.main.java.model.PasswordEntry;
import src.main.java.model.User;
import src.main.java.utils.DatabaseConnection;

public class PasswordManagerController {
    public User authenticate(String username, String password) {
        return null;
        // Implementasi autentikasi
    }

    public List<PasswordEntry> getPasswordEntries(int userId) {
        List<PasswordEntry> entries = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM password_entries WHERE user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PasswordEntry entry = new PasswordEntry(
                        rs.getString("site"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getInt("user_id")
                );
                entries.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public void addPasswordEntry(PasswordEntry entry) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO password_entries (site, login, password, user_id) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, entry.getSite());
            pstmt.setString(2, entry.getLogin());
            pstmt.setString(3, entry.getPassword());
            pstmt.setInt(4, entry.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


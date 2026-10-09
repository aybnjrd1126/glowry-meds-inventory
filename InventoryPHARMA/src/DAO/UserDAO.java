/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;





public class UserDAO {
    
    public String login(String username, String password) {
        String sql = "SELECT role FROM users WHERE username = ? AND password = ? AND active = 1";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getString("role");
        }
        return null;

    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
    }
    
    public boolean registerUser(String username, String password, String role) {
    String sql = "INSERT INTO users (username, password, role, active) VALUES (?, ?, ?, 1)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.setString(3, role);

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean usernameExists(String username) {
    String sql = "SELECT 1 FROM users WHERE username = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        return rs.next();

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public List<Object[]> getAllStaffUsers() {
    List<Object[]> list = new ArrayList<>();
    String sql = "SELECT username, role, active FROM users WHERE role = 'Staff'";

    try (Connection conn = DBConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            String status = rs.getInt("active") == 1 ? "Active" : "Inactive";
            list.add(new Object[]{ rs.getString("username"), rs.getString("role"), status });
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}

public boolean setUserActiveStatus(String username, boolean active) {
    String sql = "UPDATE users SET active = ? WHERE username = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, active ? 1 : 0);
        stmt.setString(2, username);

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
}

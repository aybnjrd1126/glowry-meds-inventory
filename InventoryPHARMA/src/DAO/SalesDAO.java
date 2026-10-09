/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import db.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @author jawed
 */
public class SalesDAO {
    
    public double getTotalSalesToday() {
    String sql = "SELECT SUM(total) AS totalSales FROM sales_of_the_day WHERE sale_date = CURDATE()";
    double totalSales = 0;

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
            totalSales = rs.getDouble("totalSales");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return totalSales;
}
    
    public boolean recordSale(double total, double payment, double balance) {
    String sql = "INSERT INTO sales_of_the_day (total, payment, balance, sale_date) VALUES (?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setDouble(1, total);
        stmt.setDouble(2, payment);
        stmt.setDouble(3, balance);
        stmt.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
}

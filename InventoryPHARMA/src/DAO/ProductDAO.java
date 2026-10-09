package DAO;

import Model.Product;
import java.sql.Statement;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public boolean addProduct(Product p) {
        String sql = "INSERT INTO products (code, prodname, qty, price, category, expiry_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getCode());
            stmt.setString(2, p.getProdname());
            stmt.setInt(3, p.getQty());
            stmt.setDouble(4, p.getPrice());
            stmt.setString(5, p.getCategory());
            
            if (p.getExpiryDate() == null) {
                stmt.setNull(6, java.sql.Types.DATE);
            } else{
                stmt.setDate(6, java.sql.Date.valueOf(p.getExpiryDate()));
            }

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> getAllProducts() {
        List<Product> list  = new ArrayList<>();
        String sql = "SELECT code, prodname, qty, price, category, expiry_date FROM products WHERE archived = 0";
        

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product p = new Product(
                    rs.getString("code"),
                    rs.getString("prodname"),
                    rs.getInt("qty"),
                    rs.getDouble("price"),
                    rs.getString("category"),
                    rs.getString("expiry_date")
                );
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    
    public List<Product> getProductsByCategory(String category) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT code, prodname, qty, price, category, expiry_date FROM products WHERE category = ? AND archived = 0";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product p = new Product(
                    rs.getString("code"),
                    rs.getString("prodname"),
                    rs.getInt("qty"),
                    rs.getDouble("price"),
                    rs.getString("category"),
                    rs.getString("expiry_date")
                );
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean productExists(String code) {
        String sql = "SELECT 1 FROM products WHERE code = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateQuantity(String code, int addedQty) {
        String sql = "UPDATE products SET qty = qty + ? WHERE code = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, addedQty);
            stmt.setString(2, code);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(Product p) {
    String sql = "UPDATE products SET prodname = ?, qty = ?, price = ?, category = ?, expiry_date = ? WHERE code = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, p.getProdname());
        stmt.setInt(2, p.getQty());
        stmt.setDouble(3, p.getPrice());
        stmt.setString(4, p.getCategory());

        if (p.getExpiryDate() == null) {
            stmt.setNull(5, java.sql.Types.DATE);
        } else {
            stmt.setDate(5, java.sql.Date.valueOf(p.getExpiryDate()));
        }

        stmt.setString(6, p.getCode());

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean archiveProduct(String code) {
    String sql = "UPDATE products SET archived = 1 WHERE code = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, code);
        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public Product getProductByCode(String code) {
        String sql = "SELECT * FROM products WHERE code = ? AND archived = 0";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(
                    rs.getString("code"),
                    rs.getString("prodname"),
                    rs.getInt("qty"),
                    rs.getDouble("price"),
                    rs.getString("category"),
                    rs.getString("expiry_date")
                );
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean reduceStock(String code, int soldQty) {
        String sql = "UPDATE products SET qty = qty - ? WHERE code = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, soldQty);
            stmt.setString(2, code);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int archiveExpiredProducts() {
    String sql = "UPDATE products SET archived = 1 WHERE expiry_date < CURDATE() AND archived = 0";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        return stmt.executeUpdate(); // number of products archived

    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
    }

}
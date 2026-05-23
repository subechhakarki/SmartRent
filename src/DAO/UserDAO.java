/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Model.User;
import database.mysqlConnection;
import java.sql.*;
/**
 *
 * @author Subechha Karki
 */
public class UserDAO {

    public boolean insertRenter(User user) {
        mysqlConnection db = new mysqlConnection();
        Connection conn = db.openConnection();
        String sql = "INSERT INTO users (full_name, email, password_hash, role, user_status, phone) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash());
            ps.setString(4, "RENTER");
            ps.setString(5, "ACTIVE");
            ps.setNull(6, java.sql.Types.VARCHAR);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("insertRenter error: " + e.getMessage());
            return false;
        } finally {
            db.closeConnection(conn);
        }
    }

    // Member B adds insertOwner() 
    // Member C adds getUserByEmail() below
    // Member D adds lockout methods below
    // Member E adds admin methods below
}
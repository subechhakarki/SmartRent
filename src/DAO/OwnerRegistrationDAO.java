/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import database.mySQLConnection;
import Model.OwnerRegistration;
import java.sql.*;
public class OwnerRegistrationDAO {
    
    public void createOwner(OwnerRegistration owner) {
        mySQLConnection db_conn = new mySQLConnection();
        Connection conn = db_conn.openConnection();
        if (conn == null) return;
        
        String insertUser = "INSERT INTO users (full_name, email, phone, password_hash, role, user_status) VALUES (?, ?, ?, ?, 'PROPERTY_OWNER', 'PENDING')";
        String insertOwner = "INSERT INTO property_owners (owner_id, approval_status) VALUES (?, 'PENDING')";
        
        try {
            conn.setAutoCommit(false);
            int newUserId = -1;
            
            try (PreparedStatement stmt1 = conn.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)) {
                stmt1.setString(1, owner.getFullName());
                stmt1.setString(2, owner.getEmail());
                stmt1.setString(3, owner.getPhoneNumber());
                
                // Securely hash the password using BCrypt
                String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(owner.getPassword(), org.mindrot.jbcrypt.BCrypt.gensalt());
                stmt1.setString(4, hashedPassword);
                stmt1.executeUpdate();
                
                ResultSet rs = stmt1.getGeneratedKeys();
                if (rs.next()) {
                    newUserId = rs.getInt(1);
                }
            }
            
            if (newUserId != -1) {
                try (PreparedStatement stmt2 = conn.prepareStatement(insertOwner)) {
                    stmt2.setInt(1, newUserId);
                    stmt2.executeUpdate();
                }
            }
            
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            db_conn.closeConnection(conn);
        }
    }
    
    public boolean checkOwner(OwnerRegistration owner) {
        mySQLConnection db_conn = new mySQLConnection();
        Connection conn = db_conn.openConnection();
        if (conn == null) return false;
        
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, owner.getEmail());
            ResultSet result = pstmt.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            System.out.print(ex);
        } finally {
            db_conn.closeConnection(conn);
        }
        return false;
    }
    
}
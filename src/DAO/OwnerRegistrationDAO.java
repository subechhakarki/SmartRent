/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import database.mysqlConnection;
import Model.OwnerRegistration;
import java.sql.*;
public class OwnerRegistrationDAO {
    mysqlConnection mysql = new mysqlConnection();
    
    public void createOwner(OwnerRegistration owner){
        Connection conn = mysql.openConnection();
        String sql = "insert into owners(full_name, email, phone_number, address, business_name, registration_number, business_address, business_email, password) values (?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, owner.getFullName());
            pstm.setString(2, owner.getEmail());
            pstm.setString(3, owner.getPhoneNumber());
            pstm.setString(4, owner.getAddress());
            pstm.setString(5, owner.getBusinessName());
            pstm.setString(6, owner.getRegistrationNumber());
            pstm.setString(7, owner.getBusinessAddress());
            pstm.setString(8, owner.getBusinessEmail());
            pstm.setString(9, owner.getPassword());
            pstm.executeUpdate();
        }catch(Exception e){
            System.out.print(e);
        }finally{
            mysql.closeConnection(conn);
        }
    }
    
    public boolean checkOwner(OwnerRegistration owner){
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM owners where email = ? or registration_number = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, owner.getEmail());
            pstmt.setString(2, owner.getRegistrationNumber());
            ResultSet result = pstmt.executeQuery();
            return result.next();
        } catch (SQLException ex) {
           System.out.print(ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    
}
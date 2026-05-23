/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author ASUS
 */

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class OwnerDBConnection {
 
    private Connection connection;
 
    public Connection openConnection() {
        try {
            String username = "root";
            String password = "1234";
            String database = "smartrent";
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/" + database,
                    username,
                    password
            );
            if (connection == null) {
                System.out.println("No connection");
            } else {
                System.out.println("Connected");
            }
            return connection;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
 
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mySQLConnection implements Db {

    private Connection connection;
    private static Connection staticConnection = null;

    public static Connection getConnection() {
        try {
            if (staticConnection == null || staticConnection.isClosed()) {
                staticConnection = new mySQLConnection().openConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staticConnection;
    }

    @Override
    public Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "root";
            String password = "subu123";
            String database = "SmartRent"; 

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + database,
                    username,
                    password
            );

            if (connection == null) {
                System.out.println("No connection");
            } else {
                System.out.println("Connected");
            }

            return connection;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection close");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try {
            Statement stmp = conn.createStatement();
            ResultSet result = stmp.executeQuery(query);
            return result;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        try {
            Statement stmp = conn.createStatement();
            return stmp.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        }
    }
}

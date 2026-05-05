package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mysqlConnection implements Db {

    private Connection connection;

    @Override
    public Connection openConnection() {
        try {
            String username = "root";
            String password = "Subechha@1";
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

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void closeConnection(Connection conn) {
        try{
                    if(conn != null && !conn.isClosed() ){

                        conn.close();

                        System.out.println("Connection close");

                    }



                }catch(SQLException e){

                    System.out.println(e);

                }

            }
    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try{

           Statement stmp = conn.createStatement();

           ResultSet result = stmp.executeQuery(query);

           return result;

       

       }catch (SQLException e){

           System.out.println(e);

           return null;
       }
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
              try{

          Statement stmp = conn.createStatement();

          return stmp.executeUpdate(query);

          

      }catch(SQLException e){

          System.out.println(e);

          return -1;

      }

    }

}
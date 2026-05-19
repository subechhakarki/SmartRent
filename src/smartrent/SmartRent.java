/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package smartrent;

import database.Db;
import database.mysqlConnection;

public class SmartRent {
    public static void main(String[] args) {
        
        // Test database connection
        Db database = new mysqlConnection();
        database.openConnection();
        
        // Generate admin hash - copy output, paste into schema.sql, then remove this line
        System.out.println(PasswordUtil.hashPassword("Admin@1234"));
    }
}
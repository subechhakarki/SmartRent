/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package smartrent;

import database.Db;
import database.mySQLConnection;

import Controller.MainFrame;

public class SmartRent {
    public static void main(String[] args) {
        // Test database connection
        Db database = new mySQLConnection();
        database.openConnection();
        

        // Launch the app
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
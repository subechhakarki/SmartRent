/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Subechha Karki
 */
public class Renter extends User {
    
    private String employmentStatus;
    private double monthlyIncome;
    
    public Renter() { 
        super(); 
    }

    public Renter(String fullName, String email, String passwordHash) {
        super(fullName, email, passwordHash, "RENTER", "ACTIVE", null);
    }
    
    public String getEmploymentStatus() { return employmentStatus; }
    public double getMonthlyIncome() { return monthlyIncome; }
    public void setEmploymentStatus(String s) { this.employmentStatus = s; }
    public void setMonthlyIncome(double d) { this.monthlyIncome = d; }
}
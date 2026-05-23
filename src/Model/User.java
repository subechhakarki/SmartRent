/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.Timestamp;

/**
 *
 * @author Subechha Karki
 */


public class User {
    private int userId;
    private String fullName;
    private String email;
    private String passwordHash;
    private String role;
    private String userStatus;
    private String phone;
    private Timestamp createdAt;
    private int loginAttempts;
    private Timestamp lockedUntil;

    public User() {}

    public User(String fullName, String email, String passwordHash,
                String role, String userStatus, String phone) {
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.userStatus = userStatus;
        this.phone = phone;
    }
    

    public int getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }
    public String getUserStatus() { return userStatus; }
    public String getPhone() { return phone; }
    public Timestamp getCreatedAt() { return createdAt; }
    public int getLoginAttempts() { return loginAttempts; }
    public Timestamp getLockedUntil() { return lockedUntil; }

    public void setUserId(int userId) { this.userId = userId; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setRole(String role) { this.role = role; }
    public void setUserStatus(String userStatus) { this.userStatus = userStatus; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public void setLoginAttempts(int loginAttempts) { this.loginAttempts = loginAttempts; }
    public void setLockedUntil(Timestamp lockedUntil) { this.lockedUntil = lockedUntil; }
}

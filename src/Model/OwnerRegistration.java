/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


 
public class OwnerRegistration {
    private int owner_id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String businessName;
    private String registrationNumber;
    private String businessAddress;
    private String businessEmail;
    private String password;
    private String confirmPassword;
 
    public int getOwnerId() {
        return owner_id;
    }
    public void setOwnerId(int owner_id) {
        this.owner_id = owner_id;
    }
 
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
 
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
 
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getBusinessName() {
        return businessName;
    }
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
 
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
 
    public String getBusinessAddress() {
        return businessAddress;
    }
    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }
 
    public String getBusinessEmail() {
        return businessEmail;
    }
    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }
 
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
 
    public OwnerRegistration(String fullName, String email, String phoneNumber,
                                   String address, String businessName,
                                   String registrationNumber, String businessAddress,
                                   String businessEmail, String password,
                                   String confirmPassword) {
        this.fullName           = fullName;
        this.email              = email;
        this.phoneNumber        = phoneNumber;
        this.address            = address;
        this.businessName       = businessName;
        this.registrationNumber = registrationNumber;
        this.businessAddress    = businessAddress;
        this.businessEmail      = businessEmail;
        this.password           = password;
        this.confirmPassword    = confirmPassword;
    }
}
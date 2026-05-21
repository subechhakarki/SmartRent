/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DAO.UserDAO;
import Model.User;
import smartrent.PasswordUtil;
import smartrent.ValidationUtil;
/**
 *
 * @author Subechha Karki
 */
public class AuthController {

    private final UserDAO userDAO = new UserDAO();

    // Member A - registerRenter
    public String registerRenter(String fullName, String email,
                                  String password, String confirmPassword) {
        
        if (!ValidationUtil.isNotEmpty(fullName)) 
            return "Full name is required.";
        if (!ValidationUtil.isValidEmail(email)) 
            return "Invalid email address.";
        if (!ValidationUtil.isValidPassword(password)) 
            return "Password must be 8+ characters with letters and numbers.";
        if (!password.equals(confirmPassword)) 
            return "Passwords do not match.";

        String hash = PasswordUtil.hashPassword(password);
        User user = new User(fullName, email, hash, "RENTER", "ACTIVE", null);

        boolean success = userDAO.insertRenter(user);
        return success ? "SUCCESS" : "Registration failed. Email may already be in use.";
    }

    // Member B adds registerOwner() below
    // Member C adds login() below
    // Member D adds lockout check below
}

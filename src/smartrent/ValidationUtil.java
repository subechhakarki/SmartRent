/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartrent;

/**
 *
 * @author Subechha Karki
 */

public class ValidationUtil {

    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return phone.matches("^[0-9+\\-\\s]{7,15}$");
    }

    public static boolean isValidPassword(String password) {
        if (password == null) return false;
        return password.length() >= 8 
            && password.matches(".*[A-Za-z].*") 
            && password.matches(".*[0-9].*");
    }
}
package Controller;

import DAO.UserDAO;
import Model.User;
import smartrent.SessionService;
import smartrent.ValidationUtil;
import view.*;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.util.Date;

public class AuthController {
    
    private final UserDAO userDAO;

    public AuthController() {
        this.userDAO = new UserDAO();
    }

    public void login(LoginView view) {
        String email = view.getTxtEmail().getText();
        String password = new String(view.getTxtPassword().getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter email and password.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String result = login(email, password);
        if (result.startsWith("SUCCESS")) {
            String role = SessionService.getInstance().getCurrentUser().getRole();
            if ("RENTER".equals(role)) {
                new RenterDashboardView().setVisible(true);
            } else if ("PROPERTY_OWNER".equals(role)) {
                new OwnerDashboardView().setVisible(true);
            } else if ("SUPER_ADMIN".equals(role)) {
                new AdminDashboardView().setVisible(true);
            }
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, result, "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void registerRenter(RenterRegistrationView view) {
        String fullName = view.getTxtName().getText();
        String email = view.getTxtEmail().getText();
        String phone = view.getTxtPhone().getText();
        String password = new String(view.getTxtPassword().getPassword());
        String confirmPassword = new String(view.getTxtConfirmPassword().getPassword());
        String employmentStatus = view.getTxtEmploymentStatus().getText();
        double monthlyIncome = 0.0;
        try {
            monthlyIncome = Double.parseDouble(view.getTxtMonthlyIncome().getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Monthly income must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(view, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String result = registerRenter(fullName, email, phone, password, employmentStatus, monthlyIncome);
        if ("SUCCESS".equals(result)) {
            JOptionPane.showMessageDialog(view, "Registration Successful! Please log in.");
            new LoginView().setVisible(true);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, result, "Registration Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void registerOwner(PropertyOwnerRegistrationView view) {
        String fullName = view.getTxtName().getText();
        String email = view.getTxtEmail().getText();
        String phone = view.getTxtPhone().getText();
        String password = new String(view.getTxtPassword().getPassword());
        String confirmPassword = new String(view.getTxtConfirmPassword().getPassword());

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(view, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String result = registerOwner(fullName, email, phone, password);
        if ("SUCCESS".equals(result)) {
            JOptionPane.showMessageDialog(view, "Registration Successful! Your account is pending admin approval.");
            new LoginView().setVisible(true);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, result, "Registration Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showRenterRegistration(LoginView view) {
        new RenterRegistrationView().setVisible(true);
        view.dispose();
    }

    public void showOwnerRegistration(LoginView view) {
        new PropertyOwnerRegistrationView().setVisible(true);
        view.dispose();
    }

    public void showLoginView(JFrame currentView) {
        new LoginView().setVisible(true);
        currentView.dispose();
    }

    public String login(String email, String password) {
        if (!ValidationUtil.isNotEmpty(email) || !ValidationUtil.isNotEmpty(password)) {
            return "Please enter both email and password.";
        }

        User user = userDAO.getUserByEmail(email);
        if (user == null) {
            return "Invalid email or password.";
        }

        // Check Lockout
        if (user.getLockedUntil() != null && user.getLockedUntil().after(new Date())) {
            long remainingMinutes = (user.getLockedUntil().getTime() - System.currentTimeMillis()) / 60000;
            return "Account locked. Try again in " + remainingMinutes + " minutes.";
        }

        // Check password (using BCrypt.checkpw with a plain text fallback)
        boolean passwordMatch = false;
        try {
            passwordMatch = org.mindrot.jbcrypt.BCrypt.checkpw(password, user.getPassword());
        } catch (Exception e) {
            // fallback to plain-text equality for legacy/unhashed passwords
            passwordMatch = user.getPassword().equals(password);
        }

        if (!passwordMatch) {
            userDAO.incrementLoginAttempts(user.getUserId(), user.getLoginAttempts());
            int attemptsLeft = 4 - user.getLoginAttempts();
            if (attemptsLeft <= 0) {
                 return "Account locked due to too many failed attempts.";
            }
            return "Invalid email or password. " + attemptsLeft + " attempts left.";
        }

        // Check User Status
        if ("PENDING".equals(user.getUserStatus())) {
            return "Your account is pending admin approval.";
        }
        if ("REJECTED".equals(user.getUserStatus())) {
            String note = userDAO.getOwnerRejectionNote(user.getUserId());
            if (note != null && !note.trim().isEmpty()) {
                return "Your account application was rejected. Reason: " + note;
            }
            return "Your account application was rejected.";
        }
        if ("SUSPENDED".equals(user.getUserStatus())) {
            return "Your account has been suspended by an administrator.";
        }

        // Success
        userDAO.resetLoginAttempts(user.getUserId());
        SessionService.getInstance().login(user);
        return "SUCCESS:" + user.getRole();
    }

    
    public String registerRenter(String fullName, String email, String password, String confirmPassword) {
        return registerRenter(fullName, email, "", password, "", 0.0);
    }

    public String registerRenter(String fullName, String email, String phone, String password, String employmentStatus, double monthlyIncome) {
        if (!ValidationUtil.isNotEmpty(fullName) || !ValidationUtil.isNotEmpty(email) || !ValidationUtil.isNotEmpty(password)) {
            return "Please fill in all required fields.";
        }
        if (!ValidationUtil.isValidEmail(email)) {
            return "Invalid email format.";
        }
        if (!ValidationUtil.isValidPassword(password)) {
            return "Password must be at least 8 characters.";
        }
        if (userDAO.isEmailExists(email)) {
            return "Email already exists.";
        }

        String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
        User user = new User(fullName, email, phone, hashedPassword, "RENTER", "ACTIVE");
        boolean success = userDAO.createRenter(user, employmentStatus, monthlyIncome);
        
        return success ? "SUCCESS" : "Registration failed due to a system error.";
    }

    public String registerOwner(String fullName, String email, String phone, String password) {
        if (!ValidationUtil.isNotEmpty(fullName) || !ValidationUtil.isNotEmpty(email) || !ValidationUtil.isNotEmpty(password)) {
            return "Please fill in all required fields.";
        }
        if (!ValidationUtil.isValidEmail(email)) {
            return "Invalid email format.";
        }
        if (!ValidationUtil.isValidPassword(password)) {
            return "Password must be at least 8 characters.";
        }
        if (userDAO.isEmailExists(email)) {
            return "Email already exists.";
        }

        String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
        User user = new User(fullName, email, phone, hashedPassword, "PROPERTY_OWNER", "PENDING");
        boolean success = userDAO.createOwner(user);
        
        return success ? "SUCCESS" : "Registration failed due to a system error.";
    }
}


package DAO;

import database.DatabaseConnection;
import Model.User;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UserDAO {

    private static List<User> mockUsers = null;

    private static synchronized void initMockUsers() {
        if (mockUsers == null) {
            mockUsers = new ArrayList<>();
            
            User u1 = new User();
            u1.setUserId(1);
            u1.setFullName("John Smith");
            u1.setEmail("john.smith@email.com");
            u1.setPhone("9876543210");
            u1.setPassword(org.mindrot.jbcrypt.BCrypt.hashpw("owner123", org.mindrot.jbcrypt.BCrypt.gensalt()));
            u1.setRole("PROPERTY_OWNER");
            u1.setUserStatus("ACTIVE");
            mockUsers.add(u1);
            
            User u2 = new User();
            u2.setUserId(2);
            u2.setFullName("Emily Brown");
            u2.setEmail("emily.brown@email.com");
            u2.setPhone("9876543211");
            u2.setPassword(org.mindrot.jbcrypt.BCrypt.hashpw("renter123", org.mindrot.jbcrypt.BCrypt.gensalt()));
            u2.setRole("RENTER");
            u2.setUserStatus("ACTIVE");
            mockUsers.add(u2);
            
            User u3 = new User();
            u3.setUserId(3);
            u3.setFullName("Michael Wilson");
            u3.setEmail("michael.wilson@email.com");
            u3.setPhone("9876543212");
            u3.setPassword(org.mindrot.jbcrypt.BCrypt.hashpw("owner123", org.mindrot.jbcrypt.BCrypt.gensalt()));
            u3.setRole("PROPERTY_OWNER");
            u3.setUserStatus("ACTIVE");
            mockUsers.add(u3);
            
            User u4 = new User();
            u4.setUserId(4);
            u4.setFullName("Sarah Johnson");
            u4.setEmail("sarah.johnson@email.com");
            u4.setPhone("9876543213");
            u4.setPassword(org.mindrot.jbcrypt.BCrypt.hashpw("renter123", org.mindrot.jbcrypt.BCrypt.gensalt()));
            u4.setRole("RENTER");
            u4.setUserStatus("ACTIVE");
            mockUsers.add(u4);
            
            User u5 = new User();
            u5.setUserId(5);
            u5.setFullName("David Lee");
            u5.setEmail("david.lee@email.com");
            u5.setPhone("9876543214");
            u5.setPassword(org.mindrot.jbcrypt.BCrypt.hashpw("renter123", org.mindrot.jbcrypt.BCrypt.gensalt()));
            u5.setRole("RENTER");
            u5.setUserStatus("DEACTIVATED");
            mockUsers.add(u5);
 
            User u6 = new User();
            u6.setUserId(6);
            u6.setFullName("John Owner");
            u6.setEmail("owner@smartrent.com");
            u6.setPhone("1111111111");
            u6.setPassword(org.mindrot.jbcrypt.BCrypt.hashpw("owner123", org.mindrot.jbcrypt.BCrypt.gensalt()));
            u6.setRole("PROPERTY_OWNER");
            u6.setUserStatus("ACTIVE");
            mockUsers.add(u6);
 
            User u7 = new User();
            u7.setUserId(7);
            u7.setFullName("Jane Renter");
            u7.setEmail("renter@smartrent.com");
            u7.setPhone("2222222222");
            u7.setPassword(org.mindrot.jbcrypt.BCrypt.hashpw("renter123", org.mindrot.jbcrypt.BCrypt.gensalt()));
            u7.setRole("RENTER");
            u7.setUserStatus("ACTIVE");
            mockUsers.add(u7);
        }
    }

    public boolean isEmailExists(String email) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            return "admin@smartrent.com".equalsIgnoreCase(email) || 
                   "renter@smartrent.com".equalsIgnoreCase(email) || 
                   "owner@smartrent.com".equalsIgnoreCase(email);
        }
        String query = "SELECT 1 FROM users WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserById(int userId) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            initMockUsers();
            for (User u : mockUsers) {
                if (u.getUserId() == userId) return u;
            }
            if (userId == 999) {
                User u = new User();
                u.setUserId(999);
                u.setFullName("System Admin");
                u.setEmail("admin@smartrent.com");
                u.setPassword("admin123");
                u.setRole("SUPER_ADMIN");
                u.setUserStatus("ACTIVE");
                return u;
            }
            return null;
        }
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmail(String email) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            initMockUsers();
            for (User u : mockUsers) {
                if (u.getEmail().equalsIgnoreCase(email)) return u;
            }
            if ("admin@smartrent.com".equalsIgnoreCase(email)) {
                User u = new User();
                u.setUserId(999);
                u.setFullName("System Admin");
                u.setEmail("admin@smartrent.com");
                u.setPassword("admin123");
                u.setRole("SUPER_ADMIN");
                u.setUserStatus("ACTIVE");
                return u;
            }
            return null;
        }
        String query = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password_hash"));
                user.setRole(rs.getString("role"));
                user.setUserStatus(rs.getString("user_status"));
                user.setLoginAttempts(rs.getInt("login_attempts"));
                java.sql.Timestamp locked = rs.getTimestamp("locked_until");
                if (locked != null) {
                    user.setLockedUntil(new java.util.Date(locked.getTime()));
                }
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void incrementLoginAttempts(int userId, int currentAttempts) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return;
        String query = "UPDATE users SET login_attempts = ?, locked_until = ? WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            int newAttempts = currentAttempts + 1;
            stmt.setInt(1, newAttempts);
            if (newAttempts >= 5) {
                // Lock for 15 minutes
                long lockTime = System.currentTimeMillis() + (15 * 60 * 1000);
                stmt.setTimestamp(2, new java.sql.Timestamp(lockTime));
            } else {
                stmt.setNull(2, Types.TIMESTAMP);
            }
            stmt.setInt(3, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetLoginAttempts(int userId) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return;
        String query = "UPDATE users SET login_attempts = 0, locked_until = NULL WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean rejectOwnerRegistration(int userId) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return true;
        // For now, let's delete the pending user from DB.
        String query = "DELETE FROM users WHERE user_id = ? AND user_status = 'PENDING'";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            initMockUsers();
            return new ArrayList<>(mockUsers);
        }
        String query = "SELECT * FROM users WHERE role != 'SUPER_ADMIN'";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteUser(int userId) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            initMockUsers();
            return mockUsers.removeIf(u -> u.getUserId() == userId);
        }
        String query = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(int userId, String fullName, String email, String role, String status) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            initMockUsers();
            for (User u : mockUsers) {
                if (u.getUserId() == userId) {
                    u.setFullName(fullName);
                    u.setEmail(email);
                    u.setRole(role);
                    u.setUserStatus(status);
                    return true;
                }
            }
            return false;
        }
        String query = "UPDATE users SET full_name = ?, email = ?, role = ?, user_status = ? WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setString(3, role);
            stmt.setString(4, status);
            stmt.setInt(5, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createRenter(User user, String employmentStatus, double monthlyIncome) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return true;
        String insertUser = "INSERT INTO users (full_name, email, phone, password_hash, role, user_status) VALUES (?, ?, ?, ?, 'RENTER', 'ACTIVE')";
        String insertRenter = "INSERT INTO renters (renter_id, employment_status, monthly_income) VALUES (?, ?, ?)";
        try {
            conn.setAutoCommit(false);
            
            int newUserId = -1;
            try (PreparedStatement stmt1 = conn.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)) {
                stmt1.setString(1, user.getFullName());
                stmt1.setString(2, user.getEmail());
                stmt1.setString(3, user.getPhone());
                stmt1.setString(4, user.getPassword()); // Should be hashed
                stmt1.executeUpdate();
                
                ResultSet rs = stmt1.getGeneratedKeys();
                if (rs.next()) {
                    newUserId = rs.getInt(1);
                }
            }

            if (newUserId != -1) {
                try (PreparedStatement stmt2 = conn.prepareStatement(insertRenter)) {
                    stmt2.setInt(1, newUserId);
                    stmt2.setString(2, employmentStatus);
                    stmt2.setDouble(3, monthlyIncome);
                    stmt2.executeUpdate();
                }
            }
            
            conn.commit();
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean createOwner(User user) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return true;
        String insertUser = "INSERT INTO users (full_name, email, phone, password_hash, role, user_status) VALUES (?, ?, ?, ?, 'PROPERTY_OWNER', 'PENDING')";
        String insertOwner = "INSERT INTO property_owners (owner_id, approval_status) VALUES (?, 'PENDING')";
        try {
            conn.setAutoCommit(false);
            
            int newUserId = -1;
            try (PreparedStatement stmt1 = conn.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)) {
                stmt1.setString(1, user.getFullName());
                stmt1.setString(2, user.getEmail());
                stmt1.setString(3, user.getPhone());
                stmt1.setString(4, user.getPassword());
                stmt1.executeUpdate();
                
                ResultSet rs = stmt1.getGeneratedKeys();
                if (rs.next()) {
                    newUserId = rs.getInt(1);
                }
            }

            if (newUserId != -1) {
                try (PreparedStatement stmt2 = conn.prepareStatement(insertOwner)) {
                    stmt2.setInt(1, newUserId);
                    stmt2.executeUpdate();
                }
            }
            
            conn.commit();
            return true;
        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); }
        }
        return false;
    }

    public java.util.List<User> getPendingOwners() {
        java.util.List<User> list = new java.util.ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            // Return a dummy pending owner for testing
            User u = new User();
            u.setUserId(2);
            u.setFullName("Demo Pending Owner");
            u.setEmail("owner@smartrent.com");
            u.setPhone("9876543210");
            u.setRole("PROPERTY_OWNER");
            u.setUserStatus("PENDING");
            list.add(u);
            return list;
        }
        String query = "SELECT u.* FROM users u JOIN property_owners p ON u.user_id = p.owner_id WHERE p.approval_status = 'PENDING'";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));
                java.sql.Timestamp created = rs.getTimestamp("created_at");
                if (created != null) user.setCreatedAt(new java.util.Date(created.getTime()));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateOwnerApproval(int userId, String status, String note) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return true;
        String updateUser = "UPDATE users SET user_status = ? WHERE user_id = ?";
        String updateOwner = "UPDATE property_owners SET approval_status = ?, approval_note = ? WHERE owner_id = ?";
        
        String userStatus = status.equals("APPROVED") ? "ACTIVE" : "REJECTED";
        try {
            conn.setAutoCommit(false);
            
            try (PreparedStatement stmt1 = conn.prepareStatement(updateUser)) {
                stmt1.setString(1, userStatus);
                stmt1.setInt(2, userId);
                stmt1.executeUpdate();
            }
            try (PreparedStatement stmt2 = conn.prepareStatement(updateOwner)) {
                stmt2.setString(1, status);
                stmt2.setString(2, note);
                stmt2.setInt(3, userId);
                stmt2.executeUpdate();
            }
            
            conn.commit();
            return true;
        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); }
        }
        return false;
    }

    public int[] getPlatformStats() {
        int[] stats = new int[4]; // [Total Users, Active Users, Pending Approvals, Deactivated/Suspended]
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            return new int[]{10, 8, 1, 1}; // Dummy stats
        }
        String totalQuery = "SELECT COUNT(*) FROM users WHERE role != 'SUPER_ADMIN'";
        String activeQuery = "SELECT COUNT(*) FROM users WHERE user_status = 'ACTIVE' AND role != 'SUPER_ADMIN'";
        String pendingQuery = "SELECT COUNT(*) FROM property_owners WHERE approval_status = 'PENDING'";
        String deactivatedQuery = "SELECT COUNT(*) FROM users WHERE user_status IN ('SUSPENDED', 'REJECTED')";
        
        try {
            try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(totalQuery)) { if(rs.next()) stats[0] = rs.getInt(1); }
            try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(activeQuery)) { if(rs.next()) stats[1] = rs.getInt(1); }
            try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(pendingQuery)) { if(rs.next()) stats[2] = rs.getInt(1); }
            try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(deactivatedQuery)) { if(rs.next()) stats[3] = rs.getInt(1); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }

    public boolean updateUserStatus(int userId, String status) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return true;
        String query = "UPDATE users SET user_status = ? WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int[] getUserRoleCounts() {
        int[] counts = new int[2]; // [Owner Count, Renter Count]
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            initMockUsers();
            for (User u : mockUsers) {
                if ("PROPERTY_OWNER".equals(u.getRole())) {
                    counts[0]++;
                } else if ("RENTER".equals(u.getRole())) {
                    counts[1]++;
                }
            }
            return counts;
        }
        String query = "SELECT role, COUNT(*) FROM users GROUP BY role";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String role = rs.getString(1);
                int count = rs.getInt(2);
                if ("PROPERTY_OWNER".equalsIgnoreCase(role)) {
                    counts[0] = count;
                } else if ("RENTER".equalsIgnoreCase(role)) {
                    counts[1] = count;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counts;
    }

    public String getOwnerRejectionNote(int userId) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            return "Application does not meet platform requirements.";
        }
        String query = "SELECT approval_note FROM property_owners WHERE owner_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String note = rs.getString("approval_note");
                return note != null ? note : "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFullName(rs.getString("full_name"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setRole(rs.getString("role"));
        user.setUserStatus(rs.getString("user_status"));
        java.sql.Timestamp created = rs.getTimestamp("created_at");
        if (created != null) {
            user.setCreatedAt(new java.util.Date(created.getTime()));
        }
        return user;
    }
}

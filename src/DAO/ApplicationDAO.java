package DAO;

import database.mySQLConnection;
import Model.RentalApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ApplicationDAO {

    public String createApplication(RentalApplication app) {
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) return "Failed to submit application: database connection offline.";
        String query = "INSERT INTO rental_applications (renter_id, property_id, move_in_date, cover_message, app_status) VALUES (?, ?, ?, ?, 'SUBMITTED')";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, app.getRenterId());
            stmt.setInt(2, app.getPropertyId());
            stmt.setDate(3, new java.sql.Date(app.getMoveInDate().getTime()));
            stmt.setString(4, app.getCoverMessage());
            stmt.executeUpdate();
            return "SUCCESS";
        } catch (SQLException e) {
            // Check for UNIQUE constraint violation
            if (e.getMessage().contains("UNIQUE constraint failed") || e.getMessage().contains("Duplicate entry")) {
                return "You have already applied for this property.";
            }
            e.printStackTrace();
            return "Failed to submit application due to a system error.";
        }
    }

    public java.util.List<RentalApplication> getApplicationsByOwner(int ownerId) {
        java.util.List<RentalApplication> list = new java.util.ArrayList<>();
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) {
            return list;
        }
        String query = "SELECT a.*, u.full_name as renter_name, p.title as property_title " +
                       "FROM rental_applications a " +
                       "JOIN users u ON a.renter_id = u.user_id " +
                       "JOIN properties p ON a.property_id = p.property_id " +
                       "WHERE p.owner_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ownerId);
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RentalApplication app = new RentalApplication();
                app.setApplicationId(rs.getInt("application_id"));
                app.setRenterId(rs.getInt("renter_id"));
                app.setPropertyId(rs.getInt("property_id"));
                app.setMoveInDate(rs.getDate("move_in_date"));
                app.setCoverMessage(rs.getString("cover_message"));
                app.setAppStatus(rs.getString("app_status"));
                app.setRejectionNote(rs.getString("rejection_note"));
                app.setCreatedAt(rs.getTimestamp("created_at"));
                app.setRenterName(rs.getString("renter_name"));
                app.setPropertyTitle(rs.getString("property_title"));
                list.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public java.util.List<RentalApplication> getApplicationsByRenter(int renterId) {
        java.util.List<RentalApplication> list = new java.util.ArrayList<>();
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) {
            return list;
        }
        String query = "SELECT a.*, p.title as property_title, p.owner_id as owner_id, u.full_name as owner_name " +
                       "FROM rental_applications a " +
                       "JOIN properties p ON a.property_id = p.property_id " +
                       "JOIN users u ON p.owner_id = u.user_id " +
                       "WHERE a.renter_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, renterId);
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RentalApplication app = new RentalApplication();
                app.setApplicationId(rs.getInt("application_id"));
                app.setRenterId(rs.getInt("renter_id"));
                app.setPropertyId(rs.getInt("property_id"));
                app.setMoveInDate(rs.getDate("move_in_date"));
                app.setCoverMessage(rs.getString("cover_message"));
                app.setAppStatus(rs.getString("app_status"));
                app.setRejectionNote(rs.getString("rejection_note"));
                app.setCreatedAt(rs.getTimestamp("created_at"));
                app.setPropertyTitle(rs.getString("property_title"));
                app.setOwnerId(rs.getInt("owner_id"));
                app.setOwnerName(rs.getString("owner_name"));
                list.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateApplicationStatus(int applicationId, String status, String rejectionNote) {
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) return false;
        String query = "UPDATE rental_applications SET app_status = ?, rejection_note = ? WHERE application_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setString(2, rejectionNote);
            stmt.setInt(3, applicationId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public RentalApplication getApplicationById(int applicationId) {
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) {
            return null;
        }
        String query = "SELECT * FROM rental_applications WHERE application_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, applicationId);
            java.sql.ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                RentalApplication app = new RentalApplication();
                app.setApplicationId(rs.getInt("application_id"));
                app.setRenterId(rs.getInt("renter_id"));
                app.setPropertyId(rs.getInt("property_id"));
                app.setMoveInDate(rs.getDate("move_in_date"));
                app.setCoverMessage(rs.getString("cover_message"));
                app.setAppStatus(rs.getString("app_status"));
                return app;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

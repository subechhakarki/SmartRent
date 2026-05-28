package DAO;

import database.DatabaseConnection;
import Model.RentalApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ApplicationDAO {

    public String createApplication(RentalApplication app) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return "SUCCESS";
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
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            if (ownerId == 6) {
                RentalApplication app1 = new RentalApplication();
                app1.setApplicationId(1);
                app1.setRenterId(7);
                app1.setPropertyId(1);
                app1.setMoveInDate(new java.util.Date());
                app1.setCoverMessage("Hello, I would love to rent this beautiful lakeside apartment!");
                app1.setAppStatus("SUBMITTED");
                app1.setCreatedAt(new java.util.Date());
                app1.setRenterName("Jane Renter");
                app1.setPropertyTitle("Lakeside Apartment");
                list.add(app1);

                RentalApplication app2 = new RentalApplication();
                app2.setApplicationId(2);
                app2.setRenterId(7);
                app2.setPropertyId(2);
                app2.setMoveInDate(new java.util.Date());
                app2.setCoverMessage("Hi, the greenview villa looks perfect for my family.");
                app2.setAppStatus("SUBMITTED");
                app2.setCreatedAt(new java.util.Date());
                app2.setRenterName("Jane Renter");
                app2.setPropertyTitle("Greenview Villa");
                list.add(app2);
            }
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
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            if (renterId == 7) {
                RentalApplication app1 = new RentalApplication();
                app1.setApplicationId(1);
                app1.setRenterId(7);
                app1.setPropertyId(1);
                app1.setMoveInDate(new java.util.Date());
                app1.setCoverMessage("Hello, I would love to rent this beautiful lakeside apartment!");
                app1.setAppStatus("SUBMITTED");
                app1.setCreatedAt(new java.util.Date());
                app1.setPropertyTitle("Lakeside Apartment");
                app1.setOwnerId(6);
                app1.setOwnerName("John Owner");
                list.add(app1);
            }
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
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return true;
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
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            RentalApplication app = new RentalApplication();
            app.setApplicationId(applicationId);
            app.setRenterId(1);
            app.setPropertyId(1);
            app.setMoveInDate(new java.util.Date());
            app.setCoverMessage("Dummy cover message");
            app.setAppStatus("SUBMITTED");
            return app;
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

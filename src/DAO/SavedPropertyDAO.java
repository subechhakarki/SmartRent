package DAO;

import database.DatabaseConnection;
import Model.Property;
import Model.SavedProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SavedPropertyDAO {

    public boolean saveProperty(int renterId, int propertyId) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return true;
        String query = "INSERT INTO saved_properties (renter_id, property_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, renterId);
            stmt.setInt(2, propertyId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Might be a duplicate save, ignore or return false
        }
        return false;
    }

    public boolean removeSavedProperty(int renterId, int propertyId) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return true;
        String query = "DELETE FROM saved_properties WHERE renter_id = ? AND property_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, renterId);
            stmt.setInt(2, propertyId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Property> getSavedPropertiesForRenter(int renterId) {
        List<Property> list = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return list;
        String query = "SELECT p.* FROM saved_properties s " +
                       "JOIN properties p ON s.property_id = p.property_id " +
                       "WHERE s.renter_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, renterId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Property p = new Property();
                p.setPropertyId(rs.getInt("property_id"));
                p.setOwnerId(rs.getInt("owner_id"));
                p.setTitle(rs.getString("title"));
                p.setAddress(rs.getString("address"));
                p.setPropertyType(rs.getString("property_type"));
                p.setBedrooms(rs.getInt("bedrooms"));
                p.setBathrooms(rs.getInt("bathrooms"));
                p.setMonthlyRent(rs.getDouble("monthly_rent"));
                p.setDeposit(rs.getDouble("deposit"));
                p.setPropStatus(rs.getString("prop_status"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

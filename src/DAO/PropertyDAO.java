package DAO;

import database.DatabaseConnection;
import Model.Property;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAO {

    private static List<Property> mockProperties = null;

    private static synchronized void initMockProperties() {
        if (mockProperties == null) {
            mockProperties = new ArrayList<>();
            
            Property p1 = new Property();
            p1.setPropertyId(1);
            p1.setOwnerId(6); // Match current owner id (John Owner)
            p1.setTitle("Lakeside Apartment");
            p1.setAddress("Mumbai");
            p1.setMonthlyRent(45000.0);
            p1.setDeposit(90000.0);
            p1.setPropStatus("OCCUPIED");
            p1.setPropertyType("Apartment");
            p1.setBedrooms(2);
            p1.setBathrooms(2);
            p1.setCreatedAt(new java.util.Date(System.currentTimeMillis() - 15L*24*60*60*1000));
            mockProperties.add(p1);
            
            Property p2 = new Property();
            p2.setPropertyId(2);
            p2.setOwnerId(6);
            p2.setTitle("Greenview Villa");
            p2.setAddress("Bangalore");
            p2.setMonthlyRent(75000.0);
            p2.setDeposit(150000.0);
            p2.setPropStatus("AVAILABLE");
            p2.setPropertyType("House");
            p2.setBedrooms(3);
            p2.setBathrooms(3);
            p2.setCreatedAt(new java.util.Date(System.currentTimeMillis() - 5L*24*60*60*1000));
            mockProperties.add(p2);
            
            Property p3 = new Property();
            p3.setPropertyId(3);
            p3.setOwnerId(6);
            p3.setTitle("Urban Loft");
            p3.setAddress("Delhi");
            p3.setMonthlyRent(35000.0);
            p3.setDeposit(70000.0);
            p3.setPropStatus("OCCUPIED");
            p3.setPropertyType("Studio");
            p3.setBedrooms(1);
            p3.setBathrooms(1);
            p3.setCreatedAt(new java.util.Date(System.currentTimeMillis() - 30L*24*60*60*1000));
            mockProperties.add(p3);
            
            Property p4 = new Property();
            p4.setPropertyId(4);
            p4.setOwnerId(6);
            p4.setTitle("Maplewood Residence");
            p4.setAddress("Chennai");
            p4.setMonthlyRent(55000.0);
            p4.setDeposit(110000.0);
            p4.setPropStatus("AVAILABLE");
            p4.setPropertyType("Apartment");
            p4.setBedrooms(3);
            p4.setBathrooms(2);
            p4.setCreatedAt(new java.util.Date(System.currentTimeMillis() - 20L*24*60*60*1000));
            mockProperties.add(p4);
            
            Property p5 = new Property();
            p5.setPropertyId(5);
            p5.setOwnerId(6);
            p5.setTitle("Cozy Cottage");
            p5.setAddress("Jaipur");
            p5.setMonthlyRent(30000.0);
            p5.setDeposit(60000.0);
            p5.setPropStatus("AVAILABLE");
            p5.setPropertyType("House");
            p5.setBedrooms(2);
            p5.setBathrooms(1);
            p5.setCreatedAt(new java.util.Date(System.currentTimeMillis() - 2L*24*60*60*1000));
            mockProperties.add(p5);
            
            Property p6 = new Property();
            p6.setPropertyId(6);
            p6.setOwnerId(6);
            p6.setTitle("Sunset Hills House");
            p6.setAddress("Pune");
            p6.setMonthlyRent(85000.0);
            p6.setDeposit(170000.0);
            p6.setPropStatus("AVAILABLE");
            p6.setPropertyType("House");
            p6.setBedrooms(4);
            p6.setBathrooms(3);
            p6.setCreatedAt(new java.util.Date(System.currentTimeMillis() - 40L*24*60*60*1000));
            mockProperties.add(p6);
        }
    }

    public boolean createProperty(Property property, List<String> imagePaths, int primaryImageIndex) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            initMockProperties();
            int maxId = 0;
            for (Property p : mockProperties) {
                if (p.getPropertyId() > maxId) maxId = p.getPropertyId();
            }
            property.setPropertyId(maxId + 1);
            property.setPropStatus("AVAILABLE");
            property.setCreatedAt(new java.util.Date());
            mockProperties.add(property);
            return true;
        }
        String insertProperty = "INSERT INTO properties (owner_id, title, address, property_type, bedrooms, bathrooms, monthly_rent, deposit, available_from, prop_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'AVAILABLE')";
        String insertImage = "INSERT INTO property_images (property_id, image_path, is_primary, sort_order) VALUES (?, ?, ?, ?)";
        try {
            conn.setAutoCommit(false);
            int newPropertyId = -1;

            try (PreparedStatement stmt = conn.prepareStatement(insertProperty, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, property.getOwnerId());
                stmt.setString(2, property.getTitle());
                stmt.setString(3, property.getAddress());
                stmt.setString(4, property.getPropertyType());
                stmt.setInt(5, property.getBedrooms());
                stmt.setInt(6, property.getBathrooms());
                stmt.setDouble(7, property.getMonthlyRent());
                stmt.setDouble(8, property.getDeposit());
                stmt.setDate(9, new java.sql.Date(property.getAvailableFrom().getTime()));
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    newPropertyId = rs.getInt(1);
                }
            }

            if (newPropertyId != -1 && imagePaths != null) {
                try (PreparedStatement stmtImg = conn.prepareStatement(insertImage)) {
                    for (int i = 0; i < imagePaths.size(); i++) {
                        stmtImg.setInt(1, newPropertyId);
                        stmtImg.setString(2, imagePaths.get(i));
                        stmtImg.setInt(3, i == primaryImageIndex ? 1 : 0);
                        stmtImg.setInt(4, i);
                        stmtImg.executeUpdate();
                    }
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

    public List<Property> getAvailableProperties(String location, double minPrice, double maxPrice, String bedrooms, String propertyType) {
        List<Property> list = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return list;
        StringBuilder query = new StringBuilder("SELECT p.*, (SELECT image_path FROM property_images pi WHERE pi.property_id = p.property_id AND pi.is_primary = 1 LIMIT 1) AS primary_image FROM properties p WHERE p.prop_status = 'AVAILABLE'");

        if (location != null && !location.trim().isEmpty()) {
            query.append(" AND p.address LIKE ?");
        }
        if (maxPrice > 0) {
            query.append(" AND p.monthly_rent BETWEEN ? AND ?");
        }
        if (bedrooms != null && !bedrooms.equals("Any")) {
            query.append(" AND p.bedrooms = ?");
        }
        if (propertyType != null && !propertyType.trim().isEmpty()) {
            query.append(" AND p.property_type = ?");
        }

        try (PreparedStatement stmt = conn.prepareStatement(query.toString())) {
            
            int paramIndex = 1;
            if (location != null && !location.trim().isEmpty()) {
                stmt.setString(paramIndex++, "%" + location.trim() + "%");
            }
            if (maxPrice > 0) {
                stmt.setDouble(paramIndex++, minPrice);
                stmt.setDouble(paramIndex++, maxPrice);
            }
            if (bedrooms != null && !bedrooms.equals("Any")) {
                stmt.setInt(paramIndex++, Integer.parseInt(bedrooms));
            }
            if (propertyType != null && !propertyType.trim().isEmpty()) {
                stmt.setString(paramIndex++, propertyType);
            }

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
                p.setAvgRating(rs.getDouble("avg_rating"));
                p.setPrimaryImagePath(rs.getString("primary_image"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Property getPropertyById(int propertyId) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            initMockProperties();
            for (Property p : mockProperties) {
                if (p.getPropertyId() == propertyId) return p;
            }
            return null;
        }
        String query = "SELECT * FROM properties WHERE property_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, propertyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
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
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateProperty(Property p) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            initMockProperties();
            for (int i = 0; i < mockProperties.size(); i++) {
                Property mp = mockProperties.get(i);
                if (mp.getPropertyId() == p.getPropertyId() && mp.getOwnerId() == p.getOwnerId()) {
                    mp.setTitle(p.getTitle());
                    mp.setAddress(p.getAddress());
                    mp.setPropertyType(p.getPropertyType());
                    mp.setBedrooms(p.getBedrooms());
                    mp.setBathrooms(p.getBathrooms());
                    mp.setMonthlyRent(p.getMonthlyRent());
                    mp.setDeposit(p.getDeposit());
                    return true;
                }
            }
            return false;
        }
        String query = "UPDATE properties SET title=?, address=?, property_type=?, bedrooms=?, bathrooms=?, monthly_rent=?, deposit=? WHERE property_id=? AND owner_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, p.getTitle());
            stmt.setString(2, p.getAddress());
            stmt.setString(3, p.getPropertyType());
            stmt.setInt(4, p.getBedrooms());
            stmt.setInt(5, p.getBathrooms());
            stmt.setDouble(6, p.getMonthlyRent());
            stmt.setDouble(7, p.getDeposit());
            stmt.setInt(8, p.getPropertyId());
            stmt.setInt(9, p.getOwnerId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProperty(int propertyId, int ownerId) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            initMockProperties();
            for (int i = 0; i < mockProperties.size(); i++) {
                Property mp = mockProperties.get(i);
                if (mp.getPropertyId() == propertyId && mp.getOwnerId() == ownerId) {
                    mockProperties.remove(i);
                    return true;
                }
            }
            return false;
        }
        String query = "DELETE FROM properties WHERE property_id=? AND owner_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, propertyId);
            stmt.setInt(2, ownerId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasActiveApplications(int propertyId) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;
        String query = "SELECT 1 FROM rental_applications WHERE property_id=? AND app_status NOT IN ('REJECTED', 'WITHDRAWN')";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, propertyId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Property> getAllProperties() {
        List<Property> list = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            Property p = getPropertyById(1);
            if (p != null) list.add(p);
            return list;
        }
        String query = "SELECT * FROM properties";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
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

    public boolean updatePropertyStatus(int propertyId, String status) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return true;
        String query = "UPDATE properties SET prop_status = ? WHERE property_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, propertyId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Property> getOwnerProperties(int ownerId) {
        List<Property> list = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            initMockProperties();
            for (Property p : mockProperties) {
                if (p.getOwnerId() == ownerId) {
                    list.add(p);
                }
            }
            return list;
        }
        String query = "SELECT * FROM properties WHERE owner_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ownerId);
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

    public List<String> getPropertyImages(int propertyId) {
        List<String> images = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            // Mock fallback: return primary image if available
            initMockProperties();
            for (Property p : mockProperties) {
                if (p.getPropertyId() == propertyId && p.getPrimaryImagePath() != null) {
                    images.add(p.getPrimaryImagePath());
                }
            }
            return images;
        }
        String query = "SELECT image_path FROM property_images WHERE property_id = ? ORDER BY sort_order";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, propertyId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                images.add(rs.getString("image_path"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }
}

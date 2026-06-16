package DAO;

import database.mySQLConnection;
import Model.Property;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAO {

    public boolean createProperty(Property property, List<String> imagePaths, int primaryImageIndex) {
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) {
            return false;
        }
        String insertProperty = "INSERT INTO properties (owner_id, title, address, property_type, bedrooms, bathrooms, monthly_rent, deposit, available_from, prop_status, image_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'AVAILABLE', ?)";
        try {
            String combinedImagePath = "";
            if (imagePaths != null && !imagePaths.isEmpty()) {
                List<String> orderedPaths = new ArrayList<>();
                String primary = (primaryImageIndex >= 0 && primaryImageIndex < imagePaths.size()) ? imagePaths.get(primaryImageIndex) : imagePaths.get(0);
                orderedPaths.add(primary);
                for (String path : imagePaths) {
                    if (!path.equals(primary)) {
                        orderedPaths.add(path);
                    }
                }
                combinedImagePath = String.join(",", orderedPaths);
            }

            try (PreparedStatement stmt = conn.prepareStatement(insertProperty)) {
                stmt.setInt(1, property.getOwnerId());
                stmt.setString(2, property.getTitle());
                stmt.setString(3, property.getAddress());
                stmt.setString(4, property.getPropertyType());
                stmt.setInt(5, property.getBedrooms());
                stmt.setInt(6, property.getBathrooms());
                stmt.setDouble(7, property.getMonthlyRent());
                stmt.setDouble(8, property.getDeposit());
                stmt.setDate(9, new java.sql.Date(property.getAvailableFrom().getTime()));
                stmt.setString(10, combinedImagePath);
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Property> getAvailableProperties(String location, double minPrice, double maxPrice, String bedrooms, String propertyType) {
        List<Property> list = new ArrayList<>();
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) return list;
        StringBuilder query = new StringBuilder("SELECT p.* FROM properties p WHERE p.prop_status = 'AVAILABLE'");

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
                p.setPrimaryImagePath(getPrimaryFromDbPath(rs.getString("image_path")));
                java.sql.Timestamp ts = rs.getTimestamp("created_at");
                if (ts != null) {
                    p.setCreatedAt(new java.util.Date(ts.getTime()));
                }
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Property getPropertyById(int propertyId) {
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) {
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
                p.setPrimaryImagePath(getPrimaryFromDbPath(rs.getString("image_path")));
                p.setAvgRating(rs.getDouble("avg_rating"));
                java.sql.Timestamp ts = rs.getTimestamp("created_at");
                if (ts != null) {
                    p.setCreatedAt(new java.util.Date(ts.getTime()));
                }
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateProperty(Property p) {
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) {
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
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) {
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
        Connection conn = mySQLConnection.getConnection();
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
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) {
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
                p.setPrimaryImagePath(getPrimaryFromDbPath(rs.getString("image_path")));
                p.setAvgRating(rs.getDouble("avg_rating"));
                java.sql.Timestamp ts = rs.getTimestamp("created_at");
                if (ts != null) {
                    p.setCreatedAt(new java.util.Date(ts.getTime()));
                }
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updatePropertyStatus(int propertyId, String status) {
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) return false;
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
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) {
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
                p.setPrimaryImagePath(getPrimaryFromDbPath(rs.getString("image_path")));
                p.setAvgRating(rs.getDouble("avg_rating"));
                java.sql.Timestamp ts = rs.getTimestamp("created_at");
                if (ts != null) {
                    p.setCreatedAt(new java.util.Date(ts.getTime()));
                }
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getPropertyImages(int propertyId) {
        List<String> images = new ArrayList<>();
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) return images;
        String query = "SELECT image_path FROM properties WHERE property_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, propertyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String dbPath = rs.getString("image_path");
                if (dbPath != null && !dbPath.trim().isEmpty()) {
                    if (dbPath.contains(",")) {
                        for (String path : dbPath.split(",")) {
                            if (!path.trim().isEmpty()) {
                                images.add(path.trim());
                            }
                        }
                    } else {
                        images.add(dbPath.trim());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    private String getPrimaryFromDbPath(String dbPath) {
        if (dbPath != null && dbPath.contains(",")) {
            return dbPath.split(",")[0].trim();
        }
        return dbPath != null ? dbPath.trim() : null;
    }

    public boolean updateAverageRating(int propertyId, double avgRating) {
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) return false;
        String query = "UPDATE properties SET avg_rating = ? WHERE property_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, avgRating);
            stmt.setInt(2, propertyId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

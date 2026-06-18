package DAO;

import database.mySQLConnection;
import Model.Rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO {

    public boolean addRating(Rating rating) {
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) return false;
        boolean originalAutoCommit = true;
        try {
            originalAutoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            
            String query = "INSERT INTO property_ratings (property_id, renter_id, rating, review_comment) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, rating.getPropertyId());
                stmt.setInt(2, rating.getRenterId());
                stmt.setInt(3, rating.getScore());
                stmt.setString(4, rating.getReviewText());
                boolean success = stmt.executeUpdate() > 0;
                if (success) {
                    double avg = getAverageRating(rating.getPropertyId());
                    String updatePropQuery = "UPDATE properties SET avg_rating = ? WHERE property_id = ?";
                    try (PreparedStatement updatePropStmt = conn.prepareStatement(updatePropQuery)) {
                        updatePropStmt.setDouble(1, avg);
                        updatePropStmt.setInt(2, rating.getPropertyId());
                        updatePropStmt.executeUpdate();
                    }
                }
                conn.commit();
                return success;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                conn.setAutoCommit(originalAutoCommit);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Rating> getRatingsForProperty(int propertyId) {
        List<Rating> list = new ArrayList<>();
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) return list;
        String query = "SELECT r.*, u.full_name as renter_name, p.title as property_title " +
                       "FROM property_ratings r " +
                       "JOIN users u ON r.renter_id = u.user_id " +
                       "JOIN properties p ON r.property_id = p.property_id " +
                       "WHERE r.property_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, propertyId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Rating r = new Rating();
                r.setRatingId(rs.getInt("rating_id"));
                r.setPropertyId(rs.getInt("property_id"));
                r.setRenterId(rs.getInt("renter_id"));
                r.setScore(rs.getInt("rating"));
                r.setReviewText(rs.getString("review_comment"));
                r.setCreatedAt(rs.getTimestamp("created_at"));
                r.setRenterName(rs.getString("renter_name"));
                r.setPropertyTitle(rs.getString("property_title"));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public double getAverageRating(int propertyId) {
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) return 0.0;
        String query = "SELECT AVG(rating) as avg_score FROM property_ratings WHERE property_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, propertyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("avg_score");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public Rating getRatingByRenterAndProperty(int renterId, int propertyId) {
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) return null;
        String query = "SELECT * FROM property_ratings WHERE renter_id = ? AND property_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, renterId);
            stmt.setInt(2, propertyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Rating r = new Rating();
                r.setRatingId(rs.getInt("rating_id"));
                r.setPropertyId(rs.getInt("property_id"));
                r.setRenterId(rs.getInt("renter_id"));
                r.setScore(rs.getInt("rating"));
                r.setReviewText(rs.getString("review_comment"));
                r.setCreatedAt(rs.getTimestamp("created_at"));
                return r;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveOrUpdateRating(Rating rating) {
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) return false;
        boolean originalAutoCommit = true;
        try {
            originalAutoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            
            String checkQuery = "SELECT rating_id FROM property_ratings WHERE property_id = ? AND renter_id = ?";
            boolean success = false;
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, rating.getPropertyId());
                checkStmt.setInt(2, rating.getRenterId());
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        // Update
                        String updateQuery = "UPDATE property_ratings SET rating = ?, review_comment = ? WHERE property_id = ? AND renter_id = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                            updateStmt.setInt(1, rating.getScore());
                            updateStmt.setString(2, rating.getReviewText());
                            updateStmt.setInt(3, rating.getPropertyId());
                            updateStmt.setInt(4, rating.getRenterId());
                            success = updateStmt.executeUpdate() > 0;
                        }
                    } else {
                        // Insert
                        String insertQuery = "INSERT INTO property_ratings (property_id, renter_id, rating, review_comment) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                            insertStmt.setInt(1, rating.getPropertyId());
                            insertStmt.setInt(2, rating.getRenterId());
                            insertStmt.setInt(3, rating.getScore());
                            insertStmt.setString(4, rating.getReviewText());
                            success = insertStmt.executeUpdate() > 0;
                        }
                    }
                }
            }
            if (success) {
                // Update properties average rating in the same transaction
                double avg = getAverageRating(rating.getPropertyId());
                String updatePropQuery = "UPDATE properties SET avg_rating = ? WHERE property_id = ?";
                try (PreparedStatement updatePropStmt = conn.prepareStatement(updatePropQuery)) {
                    updatePropStmt.setDouble(1, avg);
                    updatePropStmt.setInt(2, rating.getPropertyId());
                    updatePropStmt.executeUpdate();
                }
            }
            conn.commit();
            return success;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(originalAutoCommit);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

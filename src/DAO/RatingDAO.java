package DAO;

import database.DatabaseConnection;
import Model.Rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO {

    public boolean addRating(Rating rating) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return true;
        String query = "INSERT INTO property_ratings (property_id, renter_id, score, review_text) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, rating.getPropertyId());
            stmt.setInt(2, rating.getRenterId());
            stmt.setInt(3, rating.getScore());
            stmt.setString(4, rating.getReviewText());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Rating> getRatingsForProperty(int propertyId) {
        List<Rating> list = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
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
                r.setScore(rs.getInt("score"));
                r.setReviewText(rs.getString("review_text"));
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
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return 4.5;
        String query = "SELECT AVG(score) as avg_score FROM property_ratings WHERE property_id = ?";
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
}

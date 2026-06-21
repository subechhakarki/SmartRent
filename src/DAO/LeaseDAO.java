package DAO;

import database.mySQLConnection;
import Model.Lease;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaseDAO {

    public boolean createLease(Lease lease) {
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) return false;
        String query = "INSERT INTO leases (application_id, property_id, renter_id, owner_id, monthly_rent, deposit, start_date, end_date, terms, status) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, lease.getApplicationId());
            stmt.setInt(2, lease.getPropertyId());
            stmt.setInt(3, lease.getRenterId());
            stmt.setInt(4, lease.getOwnerId());
            stmt.setDouble(5, lease.getMonthlyRent());
            stmt.setDouble(6, lease.getDeposit());
            stmt.setDate(7, new java.sql.Date(lease.getStartDate().getTime()));
            stmt.setDate(8, new java.sql.Date(lease.getEndDate().getTime()));
            stmt.setString(9, lease.getTerms());
            stmt.setString(10, lease.getStatus());
            
            boolean success = stmt.executeUpdate() > 0;
            if (success) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        lease.setLeaseId(rs.getInt(1));
                    }
                }
            }
            return success;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Lease> getLeasesByOwner(int ownerId) {
        List<Lease> list = new ArrayList<>();
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) {
            return list;
        }
        String query = "SELECT l.*, p.title as property_title, u.full_name as renter_name " +
                       "FROM leases l " +
                       "JOIN properties p ON l.property_id = p.property_id " +
                       "JOIN users u ON l.renter_id = u.user_id " +
                       "WHERE l.owner_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ownerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToLease(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Lease> getLeasesByRenter(int renterId) {
        List<Lease> list = new ArrayList<>();
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) {
            return list;
        }
        String query = "SELECT l.*, p.title as property_title, u.full_name as owner_name " +
                       "FROM leases l " +
                       "JOIN properties p ON l.property_id = p.property_id " +
                       "JOIN users u ON l.owner_id = u.user_id " +
                       "WHERE l.renter_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, renterId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToLease(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Lease mapResultSetToLease(ResultSet rs) throws SQLException {
        Lease l = new Lease();
        l.setLeaseId(rs.getInt("lease_id"));
        l.setApplicationId(rs.getInt("application_id"));
        l.setPropertyId(rs.getInt("property_id"));
        l.setRenterId(rs.getInt("renter_id"));
        l.setOwnerId(rs.getInt("owner_id"));
        l.setMonthlyRent(rs.getDouble("monthly_rent"));
        l.setDeposit(rs.getDouble("deposit"));
        l.setStartDate(rs.getDate("start_date"));
        l.setEndDate(rs.getDate("end_date"));
        l.setTerms(rs.getString("terms"));
        l.setStatus(rs.getString("status"));
        l.setCreatedAt(rs.getTimestamp("created_at"));
        
        try {
            l.setPropertyTitle(rs.getString("property_title"));
        } catch (SQLException e) { }
        try {
            l.setRenterName(rs.getString("renter_name"));
        } catch (SQLException e) { }
        try {
            l.setOwnerName(rs.getString("owner_name"));
        } catch (SQLException e) { }
        
        return l;
    }
}

package DAO;

import database.mySQLConnection;
import Model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    public boolean sendMessage(Message message) {
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) return true;
        String query = "INSERT INTO messages (sender_id, receiver_id, content) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, message.getSenderId());
            stmt.setInt(2, message.getReceiverId());
            stmt.setString(3, message.getContent());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Message> getConversation(int user1, int user2) {
        List<Message> list = new ArrayList<>();
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) return list;
        String query = "SELECT m.*, u1.full_name as sender_name, u2.full_name as receiver_name " +
                       "FROM messages m " +
                       "JOIN users u1 ON m.sender_id = u1.user_id " +
                       "JOIN users u2 ON m.receiver_id = u2.user_id " +
                       "WHERE (m.sender_id = ? AND m.receiver_id = ?) OR (m.sender_id = ? AND m.receiver_id = ?) " +
                       "ORDER BY m.sent_at ASC";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, user1);
            stmt.setInt(2, user2);
            stmt.setInt(3, user2);
            stmt.setInt(4, user1);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Message m = new Message();
                m.setMessageId(rs.getInt("message_id"));
                m.setSenderId(rs.getInt("sender_id"));
                m.setReceiverId(rs.getInt("receiver_id"));
                m.setContent(rs.getString("content"));
                m.setRead(rs.getInt("is_read") == 1);
                m.setSentAt(rs.getTimestamp("sent_at"));
                m.setSenderName(rs.getString("sender_name"));
                m.setReceiverName(rs.getString("receiver_name"));
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean markMessagesAsRead(int receiverId, int senderId) {
        Connection conn = mySQLConnection.getConnection();
        if (conn == null) return true;
        String query = "UPDATE messages SET is_read = 1 WHERE receiver_id = ? AND sender_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, receiverId);
            stmt.setInt(2, senderId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

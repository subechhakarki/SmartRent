package Controller;

import DAO.MessageDAO;
import Model.Message;
import Controller.SessionService;
import Model.User;
import view.*;

import java.util.List;

public class MessageController {

    private MessageDAO messageDAO;

    public MessageController() {
        this.messageDAO = new MessageDAO();
    }

    public List<Message> getConversation(int otherUserId) {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null) return new java.util.ArrayList<>();
        
        // Mark messages as read since we are viewing them
        messageDAO.markMessagesAsRead(currentUser.getUserId(), otherUserId);
        
        return messageDAO.getConversation(currentUser.getUserId(), otherUserId);
    }

    public boolean sendMessage(int receiverId, String content) {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null || content == null || content.trim().isEmpty()) {
            return false;
        }
        
        Message m = new Message();
        m.setSenderId(currentUser.getUserId());
        m.setReceiverId(receiverId);
        m.setContent(content);
        
        return messageDAO.sendMessage(m);
    }

    public void initMessagingView(MessagingView view, int otherUserId, String otherUserName) {
        view.lblChatWith.setText("Chat with " + otherUserName);
        loadHistory(view, otherUserId);
    }

    public void loadHistory(MessagingView view, int otherUserId) {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null) return;
        
        List<Message> history = getConversation(otherUserId);
        StringBuilder sb = new StringBuilder();
        
        for (Message m : history) {
            String sender = m.getSenderId() == currentUser.getUserId() ? "You" : m.getSenderName();
            String status = "";
            if (m.getSenderId() == currentUser.getUserId()) {
                status = m.isRead() ? " [Read]" : " [Unread]";
            }
            sb.append(sender).append(" (").append(m.getSentAt().toString().substring(11, 16)).append(")").append(status).append(":\n");
            sb.append(m.getContent()).append("\n\n");
        }
        
        view.txtHistory.setText(sb.toString());
        view.txtHistory.setCaretPosition(view.txtHistory.getDocument().getLength());
    }

    public void sendMessage(MessagingView view, int otherUserId) {
        String content = view.txtNewMessage.getText();
        if (content.trim().isEmpty()) return;
        
        if (sendMessage(otherUserId, content)) {
            view.txtNewMessage.setText("");
            loadHistory(view, otherUserId);
        } else {
            javax.swing.JOptionPane.showMessageDialog(view, "Failed to send message.");
        }
    }
}

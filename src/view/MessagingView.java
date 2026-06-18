package view;

import Controller.MessageController;
import Model.Message;
import Model.User;
import Controller.SessionService;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MessagingView extends javax.swing.JFrame {

    private MessageController messageController;
    private int otherUserId;
    private String otherUserName;

    public MessagingView(int otherUserId, String otherUserName) {
        this.otherUserId = otherUserId;
        this.otherUserName = otherUserName;
        initComponents();
        messageController = new MessageController();
        messageController.initMessagingView(this, otherUserId, otherUserName);
        btnSend.addActionListener(e -> messageController.sendMessage(this, otherUserId));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblChatWith = new javax.swing.JLabel();
        scrollPaneHistory = new javax.swing.JScrollPane();
        txtHistory = new javax.swing.JTextArea();
        txtNewMessage = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SmartRent - Messages");
        setPreferredSize(new java.awt.Dimension(600, 600));
        getContentPane().setLayout(null);

        lblChatWith.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblChatWith.setText("Chat with User");
        getContentPane().add(lblChatWith);
        lblChatWith.setBounds(20, 20, 400, 30);

        txtHistory.setEditable(false);
        txtHistory.setColumns(20);
        txtHistory.setRows(5);
        txtHistory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        scrollPaneHistory.setViewportView(txtHistory);

        getContentPane().add(scrollPaneHistory);
        scrollPaneHistory.setBounds(20, 60, 540, 400);

        txtNewMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(txtNewMessage);
        txtNewMessage.setBounds(20, 480, 420, 40);

        btnSend.setText("Send");
        btnSend.setBackground(new java.awt.Color(30, 92, 240));
        btnSend.setForeground(new java.awt.Color(255, 255, 255));
        btnSend.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(btnSend);
        btnSend.setBounds(460, 480, 100, 40);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        messageController.sendMessage(this, otherUserId);
    }//GEN-LAST:event_btnSendActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnSend;
    public javax.swing.JLabel lblChatWith;
    public javax.swing.JScrollPane scrollPaneHistory;
    public javax.swing.JTextArea txtHistory;
    public javax.swing.JTextField txtNewMessage;
    // End of variables declaration//GEN-END:variables
}

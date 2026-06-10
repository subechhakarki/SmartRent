package view;

import Controller.AdminController;
import Model.User;
import smartrent.SessionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

public class UserManagementView extends javax.swing.JFrame {

    private AdminController adminController;
    private final javax.swing.JButton btnPrev = new javax.swing.JButton("Previous");
    private final javax.swing.JButton btnNext = new javax.swing.JButton("Next");
    private final javax.swing.JPanel pnlPageNumbers = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

    public UserManagementView() {
        initComponents();
        adminController = new AdminController();
        btnNavDashboard.addActionListener(e -> {
            System.out.println("Admin UserManagement -> Dashboard clicked");
            adminController.navigateToDashboard(this);
        });
        btnNavLogout.addActionListener(e -> {
            System.out.println("Admin UserManagement -> Logout clicked");
            adminController.logout(this);
        });
        cmbRoleFilter.addActionListener(this::cmbRoleFilterActionPerformed);
        adminController.initUserManagementView(this);
    }

    public javax.swing.JTextField getTxtSearch() { return txtSearch; }
    public javax.swing.JPanel getPnlCard() { return pnlCard; }
    public javax.swing.JPanel getPnlPagination() { return pnlPagination; }
    public javax.swing.JButton getBtnPrev() { return btnPrev; }
    public javax.swing.JPanel getPnlPageNumbers() { return pnlPageNumbers; }
    public javax.swing.JButton getBtnNext() { return btnNext; }
    public javax.swing.JScrollPane getScrollTable() { return scrollTable; }
    public javax.swing.JComboBox getCmbRoleFilter() { return cmbRoleFilter; }
    public javax.swing.JPanel getPnlTableBody() { return pnlTableBody; }
    public javax.swing.JLabel getLblEntriesSummary() { return lblEntriesSummary; }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSidebar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogoSubtitle = new javax.swing.JLabel();
        btnNavDashboard = new javax.swing.JButton();
        btnNavUserManagement = new javax.swing.JButton();
        btnNavLogout = new javax.swing.JButton();
        lblHeaderTitle = new javax.swing.JLabel();
        pnlCard = new javax.swing.JPanel();
        cmbRoleFilter = new javax.swing.JComboBox();
        txtSearch = new javax.swing.JTextField();
        pnlTableHeader = new javax.swing.JPanel();
        lblHName = new javax.swing.JLabel();
        lblHRole = new javax.swing.JLabel();
        lblHEmail = new javax.swing.JLabel();
        lblHStatus = new javax.swing.JLabel();
        lblHActions = new javax.swing.JLabel();
        scrollTable = new javax.swing.JScrollPane();
        pnlTableBody = new javax.swing.JPanel();
        lblEntriesSummary = new javax.swing.JLabel();
        pnlPagination = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartRent - User Management");
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setResizable(false);
        getContentPane().setLayout(null);

        pnlSidebar.setBackground(new java.awt.Color(31, 97, 109));
        pnlSidebar.setLayout(null);

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("SmartRent");
        pnlSidebar.add(lblLogo);
        lblLogo.setBounds(20, 20, 180, 30);

        lblLogoSubtitle.setFont(new java.awt.Font("Segoe UI", 2, 9)); // NOI18N
        lblLogoSubtitle.setForeground(new java.awt.Color(180, 210, 215));
        lblLogoSubtitle.setText("The rental experience you actually deserve");
        pnlSidebar.add(lblLogoSubtitle);
        lblLogoSubtitle.setBounds(20, 48, 190, 15);

        btnNavDashboard.setBackground(new java.awt.Color(31, 97, 109));
        btnNavDashboard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnNavDashboard.setText("  ⊞  Dashboard");
        btnNavDashboard.setBorderPainted(false);
        btnNavDashboard.setContentAreaFilled(false);
        btnNavDashboard.setFocusPainted(false);
        btnNavDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlSidebar.add(btnNavDashboard);
        btnNavDashboard.setBounds(0, 80, 220, 40);

        btnNavUserManagement.setBackground(new java.awt.Color(44, 122, 135));
        btnNavUserManagement.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavUserManagement.setForeground(new java.awt.Color(255, 255, 255));
        btnNavUserManagement.setText("  👤  User Management");
        btnNavUserManagement.setBorderPainted(false);
        btnNavUserManagement.setFocusPainted(false);
        btnNavUserManagement.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlSidebar.add(btnNavUserManagement);
        btnNavUserManagement.setBounds(0, 120, 220, 40);

        btnNavLogout.setBackground(new java.awt.Color(31, 97, 109));
        btnNavLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLogout.setText("  🚪  Logout");
        btnNavLogout.setBorderPainted(false);
        btnNavLogout.setContentAreaFilled(false);
        btnNavLogout.setFocusPainted(false);
        btnNavLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlSidebar.add(btnNavLogout);
        btnNavLogout.setBounds(0, 160, 220, 40);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 220, 768);

        lblHeaderTitle.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblHeaderTitle.setForeground(new java.awt.Color(45, 55, 72));
        lblHeaderTitle.setText("User Management");
        getContentPane().add(lblHeaderTitle);
        lblHeaderTitle.setBounds(250, 20, 400, 40);

        pnlCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard.setLayout(null);

        cmbRoleFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All Roles", "Owner", "Renter" }));
        cmbRoleFilter.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlCard.add(cmbRoleFilter);
        cmbRoleFilter.setBounds(380, 20, 150, 35);

        txtSearch.setText("Search users...");
        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(128, 128, 128));
        pnlCard.add(txtSearch);
        txtSearch.setBounds(550, 20, 205, 35);

        pnlTableHeader.setBackground(new java.awt.Color(247, 250, 252));
        pnlTableHeader.setLayout(null);

        lblHName.setText("User Name");
        lblHName.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblHName.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblHName);
        lblHName.setBounds(15, 10, 150, 20);

        lblHRole.setText("Role");
        lblHRole.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblHRole.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblHRole);
        lblHRole.setBounds(175, 10, 80, 20);

        lblHEmail.setText("Email");
        lblHEmail.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblHEmail.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblHEmail);
        lblHEmail.setBounds(265, 10, 210, 20);

        lblHStatus.setText("Status");
        lblHStatus.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblHStatus.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblHStatus);
        lblHStatus.setBounds(485, 10, 90, 20);

        lblHActions.setText("Actions");
        lblHActions.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblHActions.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblHActions);
        lblHActions.setBounds(585, 10, 140, 20);

        pnlCard.add(pnlTableHeader);
        pnlTableHeader.setBounds(15, 75, 740, 40);

        scrollTable.setBorder(null);

        pnlTableBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlTableBody.setLayout(new javax.swing.BoxLayout(pnlTableBody, javax.swing.BoxLayout.Y_AXIS));
        scrollTable.setViewportView(pnlTableBody);

        pnlCard.add(scrollTable);
        scrollTable.setBounds(15, 115, 740, 450);

        lblEntriesSummary.setText("Showing 0 to 0 of 0 entries");
        lblEntriesSummary.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblEntriesSummary.setForeground(new java.awt.Color(113, 128, 150));
        pnlCard.add(lblEntriesSummary);
        lblEntriesSummary.setBounds(15, 585, 300, 30);

        pnlPagination.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard.add(pnlPagination);
        pnlPagination.setBounds(355, 580, 400, 40);

        getContentPane().add(pnlCard);
        pnlCard.setBounds(230, 70, 770, 640);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNavDashboardActionPerformed(java.awt.event.ActionEvent evt) {
        adminController.navigateToDashboard(this);
    }

    private void btnNavLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        adminController.logout(this);
    }

    private void cmbRoleFilterActionPerformed(java.awt.event.ActionEvent evt) {
        adminController.filterChanged(this);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNavDashboard;
    private javax.swing.JButton btnNavLogout;
    private javax.swing.JButton btnNavUserManagement;
    private javax.swing.JComboBox cmbRoleFilter;
    private javax.swing.JLabel lblEntriesSummary;
    private javax.swing.JLabel lblHActions;
    private javax.swing.JLabel lblHEmail;
    private javax.swing.JLabel lblHName;
    private javax.swing.JLabel lblHRole;
    private javax.swing.JLabel lblHStatus;
    private javax.swing.JLabel lblHeaderTitle;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoSubtitle;
    private javax.swing.JPanel pnlCard;
    private javax.swing.JPanel pnlPagination;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlTableBody;
    private javax.swing.JPanel pnlTableHeader;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

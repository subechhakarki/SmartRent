package view;

import Controller.AdminController;
import Model.User;
import Controller.SessionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

public class UserManagementView extends javax.swing.JFrame {

    private AdminController adminController;
    public final javax.swing.JButton btnPrev = new javax.swing.JButton("Previous");
    public final javax.swing.JButton btnNext = new javax.swing.JButton("Next");
    public final javax.swing.JPanel pnlPageNumbers = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

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



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSidebar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogo.setBackground(new java.awt.Color(209, 232, 237));
        lblLogo.setOpaque(true);
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
        setPreferredSize(new java.awt.Dimension(1100, 800));
        setResizable(false);
        getContentPane().setLayout(null);

        pnlSidebar.setBackground(new java.awt.Color(60, 110, 113));
        pnlSidebar.setLayout(null);

        lblLogo.setBackground(new java.awt.Color(209, 232, 237));
        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(60, 110, 113));
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/SmartRentLogo_Banner.png"))); // NOI18N
        lblLogo.setOpaque(true);
        pnlSidebar.add(lblLogo);
        lblLogo.setBounds(0, 0, 200, 80);

        btnNavDashboard.setBackground(new java.awt.Color(60, 110, 113));
        btnNavDashboard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnNavDashboard.setText("Dashboard");
        btnNavDashboard.setBorderPainted(false);
        btnNavDashboard.setFocusPainted(false);
        btnNavDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavDashboard.setMargin(new java.awt.Insets(2, 20, 2, 14));
        pnlSidebar.add(btnNavDashboard);
        btnNavDashboard.setBounds(0, 100, 200, 40);

        btnNavUserManagement.setBackground(new java.awt.Color(80, 128, 128));
        btnNavUserManagement.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavUserManagement.setForeground(new java.awt.Color(255, 255, 255));
        btnNavUserManagement.setText("User Management");
        btnNavUserManagement.setBorderPainted(false);
        btnNavUserManagement.setFocusPainted(false);
        btnNavUserManagement.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavUserManagement.setMargin(new java.awt.Insets(2, 20, 2, 14));
        pnlSidebar.add(btnNavUserManagement);
        btnNavUserManagement.setBounds(0, 140, 200, 40);

        btnNavLogout.setBackground(new java.awt.Color(60, 110, 113));
        btnNavLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLogout.setText("Logout");
        btnNavLogout.setBorderPainted(false);
        btnNavLogout.setFocusPainted(false);
        btnNavLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavLogout.setMargin(new java.awt.Insets(2, 20, 2, 14));
        pnlSidebar.add(btnNavLogout);
        btnNavLogout.setBounds(0, 200, 200, 40);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 200, 800);

        lblHeaderTitle.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblHeaderTitle.setForeground(new java.awt.Color(45, 55, 72));
        lblHeaderTitle.setText("User Management");
        getContentPane().add(lblHeaderTitle);
        lblHeaderTitle.setBounds(230, 20, 400, 40);

        pnlCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard.setLayout(null);

        cmbRoleFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All Roles", "Owner", "Renter" }));
        cmbRoleFilter.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlCard.add(cmbRoleFilter);
        cmbRoleFilter.setBounds(450, 20, 150, 35);

        txtSearch.setText("Search users...");
        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(128, 128, 128));
        pnlCard.add(txtSearch);
        txtSearch.setBounds(620, 20, 210, 35);

        pnlTableHeader.setBackground(new java.awt.Color(247, 250, 252));
        pnlTableHeader.setLayout(null);

        lblHName.setText("User Name");
        lblHName.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblHName.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblHName);
        lblHName.setBounds(15, 10, 180, 20);

        lblHRole.setText("Role");
        lblHRole.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblHRole.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblHRole);
        lblHRole.setBounds(210, 10, 80, 20);

        lblHEmail.setText("Email");
        lblHEmail.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblHEmail.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblHEmail);
        lblHEmail.setBounds(300, 10, 230, 20);

        lblHStatus.setText("Status");
        lblHStatus.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblHStatus.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblHStatus);
        lblHStatus.setBounds(545, 10, 95, 20);

        lblHActions.setText("Actions");
        lblHActions.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblHActions.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblHActions);
        lblHActions.setBounds(650, 10, 150, 20);

        pnlCard.add(pnlTableHeader);
        pnlTableHeader.setBounds(15, 75, 815, 40);

        scrollTable.setBorder(null);

        pnlTableBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlTableBody.setLayout(new javax.swing.BoxLayout(pnlTableBody, javax.swing.BoxLayout.Y_AXIS));
        scrollTable.setViewportView(pnlTableBody);

        pnlCard.add(scrollTable);
        scrollTable.setBounds(15, 115, 815, 450);

        lblEntriesSummary.setText("Showing 0 to 0 of 0 entries");
        lblEntriesSummary.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblEntriesSummary.setForeground(new java.awt.Color(113, 128, 150));
        pnlCard.add(lblEntriesSummary);
        lblEntriesSummary.setBounds(15, 605, 300, 30);

        pnlPagination.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard.add(pnlPagination);
        pnlPagination.setBounds(430, 600, 400, 40);

        getContentPane().add(pnlCard);
        pnlCard.setBounds(230, 80, 845, 660);

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
    public javax.swing.JButton btnNavDashboard;
    public javax.swing.JButton btnNavLogout;
    public javax.swing.JButton btnNavUserManagement;
    public javax.swing.JComboBox cmbRoleFilter;
    public javax.swing.JLabel lblEntriesSummary;
    public javax.swing.JLabel lblHActions;
    public javax.swing.JLabel lblHEmail;
    public javax.swing.JLabel lblHName;
    public javax.swing.JLabel lblHRole;
    public javax.swing.JLabel lblHStatus;
    public javax.swing.JLabel lblHeaderTitle;
    public javax.swing.JLabel lblLogo;
    public javax.swing.JPanel pnlCard;
    public javax.swing.JPanel pnlPagination;
    public javax.swing.JPanel pnlSidebar;
    public javax.swing.JPanel pnlTableBody;
    public javax.swing.JPanel pnlTableHeader;
    public javax.swing.JScrollPane scrollTable;
    public javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

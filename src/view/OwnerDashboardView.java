package view;

import Controller.ApplicationController;

public class OwnerDashboardView extends javax.swing.JFrame {

    private ApplicationController applicationController;

    public OwnerDashboardView() {
        initComponents();
        applicationController = new ApplicationController();
        applicationController.initOwnerDashboard(this);
    }

    // Getters for controller usage
    public javax.swing.JScrollPane getScrollPane() { return scrollPane; }
    public javax.swing.JLabel getLblWelcome() { return lblWelcome; }
    public javax.swing.JLabel getLblStatProperties() { return lblStatProperties; }
    public javax.swing.JLabel getLblStatApplications() { return lblStatApplications; }
    public javax.swing.JLabel getLblStatLeases() { return lblStatLeases; }
    public javax.swing.JPanel getPnlTableBody() { return pnlTableBody; }
    public javax.swing.JButton getBtnAddProperty() { return btnAddProperty; }
    public javax.swing.JButton getBtnNavDashboard() { return btnNavDashboard; }
    public javax.swing.JButton getBtnNavMyProperties() { return btnNavMyProperties; }
    public javax.swing.JButton getBtnNavLeaseManagement() { return btnNavLeaseManagement; }
    public javax.swing.JButton getBtnNavLogout() { return btnNavLogout; }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSidebar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        btnNavDashboard = new javax.swing.JButton();
        btnNavMyProperties = new javax.swing.JButton();
        btnNavLeaseManagement = new javax.swing.JButton();
        btnNavLogout = new javax.swing.JButton();
        pnlHeader = new javax.swing.JPanel();
        lblDashboardHeader = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        pnlStats = new javax.swing.JPanel();
        pnlTotalProps = new javax.swing.JPanel();
        lblStatProperties = new javax.swing.JLabel();
        pnlPendingApps = new javax.swing.JPanel();
        lblStatApplications = new javax.swing.JLabel();
        pnlPendingApprovals = new javax.swing.JPanel();
        lblStatLeases = new javax.swing.JLabel();
        btnAddProperty = new javax.swing.JButton();
        lblTableTitle = new javax.swing.JLabel();
        pnlTableHeader = new javax.swing.JPanel();
        lblColUser = new javax.swing.JLabel();
        lblColRole = new javax.swing.JLabel();
        lblColEmail = new javax.swing.JLabel();
        lblColStatus = new javax.swing.JLabel();
        lblColActions = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        pnlTableBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartRent - Owner Dashboard");
        setBackground(new java.awt.Color(245, 245, 245));
        setPreferredSize(new java.awt.Dimension(1024, 768));
        getContentPane().setLayout(null);

        pnlSidebar.setBackground(new java.awt.Color(60, 110, 113));
        pnlSidebar.setLayout(null);

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("SmartRent");
        pnlSidebar.add(lblLogo);
        lblLogo.setBounds(20, 20, 160, 40);

        btnNavDashboard.setBackground(new java.awt.Color(80, 128, 128));
        btnNavDashboard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnNavDashboard.setText("Dashboard");
        btnNavDashboard.setBorderPainted(false);
        btnNavDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavDashboard.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavDashboard.addActionListener(this::btnNavDashboardActionPerformed);
        pnlSidebar.add(btnNavDashboard);
        btnNavDashboard.setBounds(0, 80, 200, 40);

        btnNavMyProperties.setBackground(new java.awt.Color(60, 110, 113));
        btnNavMyProperties.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavMyProperties.setForeground(new java.awt.Color(255, 255, 255));
        btnNavMyProperties.setText("My Properties");
        btnNavMyProperties.setBorderPainted(false);
        btnNavMyProperties.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavMyProperties.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavMyProperties.addActionListener(this::btnNavMyPropertiesActionPerformed);
        pnlSidebar.add(btnNavMyProperties);
        btnNavMyProperties.setBounds(0, 120, 200, 40);

        btnNavLeaseManagement.setBackground(new java.awt.Color(60, 110, 113));
        btnNavLeaseManagement.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLeaseManagement.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLeaseManagement.setText("Lease Management");
        btnNavLeaseManagement.setBorderPainted(false);
        btnNavLeaseManagement.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavLeaseManagement.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavLeaseManagement.addActionListener(this::btnNavLeaseManagementActionPerformed);
        pnlSidebar.add(btnNavLeaseManagement);
        btnNavLeaseManagement.setBounds(0, 160, 200, 40);

        btnNavLogout.setBackground(new java.awt.Color(60, 110, 113));
        btnNavLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLogout.setText("Logout");
        btnNavLogout.setBorderPainted(false);
        btnNavLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavLogout.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavLogout.addActionListener(this::btnNavLogoutActionPerformed);
        pnlSidebar.add(btnNavLogout);
        btnNavLogout.setBounds(0, 220, 200, 40);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 200, 768);

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setLayout(null);

        lblDashboardHeader.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblDashboardHeader.setText("Owner Dashboard");
        pnlHeader.add(lblDashboardHeader);
        lblDashboardHeader.setBounds(30, 15, 300, 40);

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblWelcome.setForeground(new java.awt.Color(102, 102, 102));
        lblWelcome.setText("Welcome, Owner");
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnlHeader.add(lblWelcome);
        lblWelcome.setBounds(580, 15, 200, 40);

        getContentPane().add(pnlHeader);
        pnlHeader.setBounds(200, 0, 824, 70);

        pnlStats.setBackground(new java.awt.Color(245, 245, 245));
        pnlStats.setOpaque(false);
        pnlStats.setLayout(null);

        pnlTotalProps.setBackground(new java.awt.Color(30, 121, 222));
        pnlTotalProps.setLayout(null);

        lblStatProperties.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStatProperties.setForeground(new java.awt.Color(255, 255, 255));
        lblStatProperties.setText("<html>Total Properties<br><font size='5'>0</font> Properties</html>");
        pnlTotalProps.add(lblStatProperties);
        lblStatProperties.setBounds(60, 10, 150, 50);

        pnlStats.add(pnlTotalProps);
        pnlTotalProps.setBounds(30, 20, 220, 70);

        pnlPendingApps.setBackground(new java.awt.Color(243, 112, 33));
        pnlPendingApps.setLayout(null);

        lblStatApplications.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStatApplications.setForeground(new java.awt.Color(255, 255, 255));
        lblStatApplications.setText("<html>Pending Applications<br><font size='5'>0</font> Applications</html>");
        pnlPendingApps.add(lblStatApplications);
        lblStatApplications.setBounds(60, 10, 150, 50);

        pnlStats.add(pnlPendingApps);
        pnlPendingApps.setBounds(270, 20, 220, 70);

        pnlPendingApprovals.setBackground(new java.awt.Color(64, 160, 69));
        pnlPendingApprovals.setLayout(null);

        lblStatLeases.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStatLeases.setForeground(new java.awt.Color(255, 255, 255));
        lblStatLeases.setText("<html>Pending Approvals<br><font size='5'>0</font> Pending</html>");
        pnlPendingApprovals.add(lblStatLeases);
        lblStatLeases.setBounds(60, 10, 150, 50);

        pnlStats.add(pnlPendingApprovals);
        pnlPendingApprovals.setBounds(510, 20, 220, 70);

        btnAddProperty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddProperty.setText("+");
        btnAddProperty.setForeground(new java.awt.Color(30, 121, 222));
        btnAddProperty.addActionListener(this::btnAddPropertyActionPerformed);
        pnlStats.add(btnAddProperty);
        btnAddProperty.setBounds(760, 40, 30, 30);

        getContentPane().add(pnlStats);
        pnlStats.setBounds(200, 70, 824, 120);

        lblTableTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTableTitle.setText("Rental Applications");
        getContentPane().add(lblTableTitle);
        lblTableTitle.setBounds(230, 200, 300, 30);

        pnlTableHeader.setBackground(new java.awt.Color(243, 247, 250));
        pnlTableHeader.setLayout(null);

        lblColUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColUser.setForeground(new java.awt.Color(96, 128, 160));
        lblColUser.setText("User Name");
        pnlTableHeader.add(lblColUser);
        lblColUser.setBounds(20, 10, 120, 20);

        lblColRole.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColRole.setForeground(new java.awt.Color(96, 128, 160));
        lblColRole.setText("Property");
        pnlTableHeader.add(lblColRole);
        lblColRole.setBounds(150, 10, 140, 20);

        lblColEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColEmail.setForeground(new java.awt.Color(96, 128, 160));
        lblColEmail.setText("Email");
        pnlTableHeader.add(lblColEmail);
        lblColEmail.setBounds(300, 10, 160, 20);

        lblColStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColStatus.setForeground(new java.awt.Color(96, 128, 160));
        lblColStatus.setText("Status");
        pnlTableHeader.add(lblColStatus);
        lblColStatus.setBounds(470, 10, 70, 20);

        lblColActions.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColActions.setForeground(new java.awt.Color(96, 128, 160));
        lblColActions.setText("Actions");
        pnlTableHeader.add(lblColActions);
        lblColActions.setBounds(550, 10, 190, 20);

        getContentPane().add(pnlTableHeader);
        pnlTableHeader.setBounds(230, 240, 770, 40);

        scrollPane.setBorder(null);

        pnlTableBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlTableBody.setLayout(new javax.swing.BoxLayout(pnlTableBody, javax.swing.BoxLayout.Y_AXIS));
        scrollPane.setViewportView(pnlTableBody);

        getContentPane().add(scrollPane);
        scrollPane.setBounds(230, 280, 770, 450);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNavLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        applicationController.logout(this);
    }

    private void btnAddPropertyActionPerformed(java.awt.event.ActionEvent evt) {
        applicationController.addProperty(this);
    }

    private void btnNavMyPropertiesActionPerformed(java.awt.event.ActionEvent evt) {
        applicationController.navigateToMyProperties(this);
    }

    private void btnNavDashboardActionPerformed(java.awt.event.ActionEvent evt) {
        applicationController.initOwnerDashboard(this);
    }

    private void btnNavLeaseManagementActionPerformed(java.awt.event.ActionEvent evt) {
        applicationController.navigateToLeaseManagement(this);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProperty;
    private javax.swing.JButton btnNavDashboard;
    private javax.swing.JButton btnNavLeaseManagement;
    private javax.swing.JButton btnNavLogout;
    private javax.swing.JButton btnNavMyProperties;
    private javax.swing.JLabel lblColActions;
    private javax.swing.JLabel lblColEmail;
    private javax.swing.JLabel lblColRole;
    private javax.swing.JLabel lblColStatus;
    private javax.swing.JLabel lblColUser;
    private javax.swing.JLabel lblDashboardHeader;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblStatApplications;
    private javax.swing.JLabel lblStatLeases;
    private javax.swing.JLabel lblStatProperties;
    private javax.swing.JLabel lblTableTitle;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlPendingApprovals;
    private javax.swing.JPanel pnlPendingApps;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlStats;
    private javax.swing.JPanel pnlTableBody;
    private javax.swing.JPanel pnlTableHeader;
    private javax.swing.JPanel pnlTotalProps;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}

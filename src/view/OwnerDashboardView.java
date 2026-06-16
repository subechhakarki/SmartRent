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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSidebar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogo.setBackground(new java.awt.Color(209, 232, 237));
        lblLogo.setOpaque(true);
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
        setPreferredSize(new java.awt.Dimension(1280, 800));
        getContentPane().setLayout(null);

        pnlSidebar.setBackground(new java.awt.Color(60, 110, 113));
        pnlSidebar.setLayout(null);

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("");
        pnlSidebar.add(lblLogo);
        lblLogo.setBounds(0, 0, 200, 80);

        btnNavDashboard.setBackground(new java.awt.Color(80, 128, 128));
        btnNavDashboard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnNavDashboard.setText("Dashboard");
        btnNavDashboard.setBorderPainted(false);
        btnNavDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavDashboard.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavDashboard.addActionListener(this::btnNavDashboardActionPerformed);
        pnlSidebar.add(btnNavDashboard);
        btnNavDashboard.setBounds(0, 100, 200, 40);

        btnNavMyProperties.setBackground(new java.awt.Color(60, 110, 113));
        btnNavMyProperties.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavMyProperties.setForeground(new java.awt.Color(255, 255, 255));
        btnNavMyProperties.setText("My Properties");
        btnNavMyProperties.setBorderPainted(false);
        btnNavMyProperties.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavMyProperties.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavMyProperties.addActionListener(this::btnNavMyPropertiesActionPerformed);
        pnlSidebar.add(btnNavMyProperties);
        btnNavMyProperties.setBounds(0, 140, 200, 40);

        btnNavLeaseManagement.setBackground(new java.awt.Color(60, 110, 113));
        btnNavLeaseManagement.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLeaseManagement.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLeaseManagement.setText("Lease Management");
        btnNavLeaseManagement.setBorderPainted(false);
        btnNavLeaseManagement.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavLeaseManagement.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavLeaseManagement.addActionListener(this::btnNavLeaseManagementActionPerformed);
        pnlSidebar.add(btnNavLeaseManagement);
        btnNavLeaseManagement.setBounds(0, 180, 200, 40);

        btnNavLogout.setBackground(new java.awt.Color(60, 110, 113));
        btnNavLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLogout.setText("Logout");
        btnNavLogout.setBorderPainted(false);
        btnNavLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavLogout.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavLogout.addActionListener(this::btnNavLogoutActionPerformed);
        pnlSidebar.add(btnNavLogout);
        btnNavLogout.setBounds(0, 240, 200, 40);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 200, 800);

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
        lblWelcome.setBounds(800, 15, 200, 40);

        getContentPane().add(pnlHeader);
        pnlHeader.setBounds(200, 0, 1080, 70);

        pnlStats.setBackground(new java.awt.Color(245, 245, 245));
        pnlStats.setOpaque(false);
        pnlStats.setLayout(null);

        pnlTotalProps.setBackground(new java.awt.Color(30, 121, 222));
        pnlTotalProps.setLayout(null);

        lblStatProperties.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStatProperties.setForeground(new java.awt.Color(255, 255, 255));
        lblStatProperties.setText("<html>Total Properties<br><font size='5'>0</font> Properties</html>");
        pnlTotalProps.add(lblStatProperties);
        lblStatProperties.setBounds(20, 10, 260, 50);

        pnlStats.add(pnlTotalProps);
        pnlTotalProps.setBounds(30, 20, 300, 70);

        pnlPendingApps.setBackground(new java.awt.Color(243, 112, 33));
        pnlPendingApps.setLayout(null);

        lblStatApplications.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStatApplications.setForeground(new java.awt.Color(255, 255, 255));
        lblStatApplications.setText("<html>Pending Applications<br><font size='5'>0</font> Applications</html>");
        pnlPendingApps.add(lblStatApplications);
        lblStatApplications.setBounds(20, 10, 260, 50);

        pnlStats.add(pnlPendingApps);
        pnlPendingApps.setBounds(370, 20, 300, 70);

        pnlPendingApprovals.setBackground(new java.awt.Color(64, 160, 69));
        pnlPendingApprovals.setLayout(null);

        lblStatLeases.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStatLeases.setForeground(new java.awt.Color(255, 255, 255));
        lblStatLeases.setText("<html>Pending Approvals<br><font size='5'>0</font> Pending</html>");
        pnlPendingApprovals.add(lblStatLeases);
        lblStatLeases.setBounds(20, 10, 260, 50);

        pnlStats.add(pnlPendingApprovals);
        pnlPendingApprovals.setBounds(710, 20, 300, 70);

        btnAddProperty.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAddProperty.setText("+");
        btnAddProperty.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnAddProperty.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnAddProperty.setForeground(new java.awt.Color(30, 121, 222));
        btnAddProperty.addActionListener(this::btnAddPropertyActionPerformed);
        pnlStats.add(btnAddProperty);
        btnAddProperty.setBounds(1020, 35, 40, 40);

        getContentPane().add(pnlStats);
        pnlStats.setBounds(200, 70, 1080, 120);

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
        lblColUser.setBounds(20, 10, 180, 20);

        lblColRole.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColRole.setForeground(new java.awt.Color(96, 128, 160));
        lblColRole.setText("Property");
        pnlTableHeader.add(lblColRole);
        lblColRole.setBounds(220, 10, 180, 20);

        lblColEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColEmail.setForeground(new java.awt.Color(96, 128, 160));
        lblColEmail.setText("Email");
        pnlTableHeader.add(lblColEmail);
        lblColEmail.setBounds(420, 10, 200, 20);

        lblColStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColStatus.setForeground(new java.awt.Color(96, 128, 160));
        lblColStatus.setText("Status");
        pnlTableHeader.add(lblColStatus);
        lblColStatus.setBounds(640, 10, 100, 20);

        lblColActions.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColActions.setForeground(new java.awt.Color(96, 128, 160));
        lblColActions.setText("Actions");
        pnlTableHeader.add(lblColActions);
        lblColActions.setBounds(760, 10, 220, 20);

        getContentPane().add(pnlTableHeader);
        pnlTableHeader.setBounds(230, 240, 1020, 40);

        scrollPane.setBorder(null);

        pnlTableBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlTableBody.setLayout(new javax.swing.BoxLayout(pnlTableBody, javax.swing.BoxLayout.Y_AXIS));
        scrollPane.setViewportView(pnlTableBody);

        getContentPane().add(scrollPane);
        scrollPane.setBounds(230, 280, 1020, 450);

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
    public javax.swing.JButton btnAddProperty;
    public javax.swing.JButton btnNavDashboard;
    public javax.swing.JButton btnNavLeaseManagement;
    public javax.swing.JButton btnNavLogout;
    public javax.swing.JButton btnNavMyProperties;
    public javax.swing.JLabel lblColActions;
    public javax.swing.JLabel lblColEmail;
    public javax.swing.JLabel lblColRole;
    public javax.swing.JLabel lblColStatus;
    public javax.swing.JLabel lblColUser;
    public javax.swing.JLabel lblDashboardHeader;
    public javax.swing.JLabel lblLogo;
    public javax.swing.JLabel lblStatApplications;
    public javax.swing.JLabel lblStatLeases;
    public javax.swing.JLabel lblStatProperties;
    public javax.swing.JLabel lblTableTitle;
    public javax.swing.JLabel lblWelcome;
    public javax.swing.JPanel pnlHeader;
    public javax.swing.JPanel pnlPendingApprovals;
    public javax.swing.JPanel pnlPendingApps;
    public javax.swing.JPanel pnlSidebar;
    public javax.swing.JPanel pnlStats;
    public javax.swing.JPanel pnlTableBody;
    public javax.swing.JPanel pnlTableHeader;
    public javax.swing.JPanel pnlTotalProps;
    public javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}

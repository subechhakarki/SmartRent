package view;

import Controller.AdminController;

public class AdminDashboardView extends javax.swing.JFrame {

    private AdminController adminController;

    public AdminDashboardView() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color(245, 245, 245));
        adminController = new AdminController();
        adminController.initDashboard(this);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlSidebar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnUserManagement = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        pnlCard1 = new javax.swing.JPanel();
        lblCard1Title = new javax.swing.JLabel();
        lblCard1Value = new javax.swing.JLabel();
        pnlCard2 = new javax.swing.JPanel();
        lblCard2Title = new javax.swing.JLabel();
        lblCard2Value = new javax.swing.JLabel();
        pnlCard3 = new javax.swing.JPanel();
        lblCard3Title = new javax.swing.JLabel();
        lblCard3Value = new javax.swing.JLabel();
        pnlCard4 = new javax.swing.JPanel();
        lblCard4Title = new javax.swing.JLabel();
        lblCard4Value = new javax.swing.JLabel();
        pnlTable1 = new javax.swing.JPanel();
        lblTable1Title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblApplications = new javax.swing.JTable();
        pnlTable2 = new javax.swing.JPanel();
        lblTable2Title = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProperties = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartRent - Admin Dashboard");
        setBackground(new java.awt.Color(245, 245, 245));
        setPreferredSize(new java.awt.Dimension(1100, 800));
        getContentPane().setLayout(null);

        pnlSidebar.setBackground(new java.awt.Color(60, 110, 113));
        pnlSidebar.setLayout(null);

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("SmartRent");
        pnlSidebar.add(lblLogo);
        lblLogo.setBounds(20, 20, 160, 40);

        btnDashboard.setBackground(new java.awt.Color(60, 110, 113));
        btnDashboard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorderPainted(false);
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboard.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnDashboard.addActionListener(this::btnDashboardActionPerformed);
        pnlSidebar.add(btnDashboard);
        btnDashboard.setBounds(0, 80, 200, 40);

        btnUserManagement.setBackground(new java.awt.Color(60, 110, 113));
        btnUserManagement.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUserManagement.setForeground(new java.awt.Color(255, 255, 255));
        btnUserManagement.setText("User Management");
        btnUserManagement.setBorderPainted(false);
        btnUserManagement.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUserManagement.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnUserManagement.addActionListener(this::btnUserManagementActionPerformed);
        pnlSidebar.add(btnUserManagement);
        btnUserManagement.setBounds(0, 120, 200, 40);

        btnLogout.setBackground(new java.awt.Color(60, 110, 113));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.setBorderPainted(false);
        btnLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLogout.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnLogout.addActionListener(this::btnLogoutActionPerformed);
        pnlSidebar.add(btnLogout);
        btnLogout.setBounds(0, 160, 200, 40);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 200, 800);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setText("Dashboard");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(230, 20, 300, 40);

        pnlCard1.setBackground(new java.awt.Color(24, 107, 251));
        pnlCard1.setLayout(null);

        lblCard1Title.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCard1Title.setForeground(new java.awt.Color(255, 255, 255));
        lblCard1Title.setText("Total Users");
        pnlCard1.add(lblCard1Title);
        lblCard1Title.setBounds(70, 20, 120, 20);

        lblCard1Value.setForeground(new java.awt.Color(255, 255, 255));
        lblCard1Value.setText("120 Users");
        pnlCard1.add(lblCard1Value);
        lblCard1Value.setBounds(70, 40, 120, 20);

        getContentPane().add(pnlCard1);
        pnlCard1.setBounds(230, 80, 200, 80);

        pnlCard2.setBackground(new java.awt.Color(34, 166, 76));
        pnlCard2.setLayout(null);

        lblCard2Title.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCard2Title.setForeground(new java.awt.Color(255, 255, 255));
        lblCard2Title.setText("Active Users");
        pnlCard2.add(lblCard2Title);
        lblCard2Title.setBounds(70, 20, 120, 20);

        lblCard2Value.setForeground(new java.awt.Color(255, 255, 255));
        lblCard2Value.setText("95 Active");
        pnlCard2.add(lblCard2Value);
        lblCard2Value.setBounds(70, 40, 120, 20);

        getContentPane().add(pnlCard2);
        pnlCard2.setBounds(445, 80, 200, 80);

        pnlCard3.setBackground(new java.awt.Color(235, 138, 28));
        pnlCard3.setLayout(null);

        lblCard3Title.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCard3Title.setForeground(new java.awt.Color(255, 255, 255));
        lblCard3Title.setText("Pending Approvals");
        pnlCard3.add(lblCard3Title);
        lblCard3Title.setBounds(60, 20, 130, 20);

        lblCard3Value.setForeground(new java.awt.Color(255, 255, 255));
        lblCard3Value.setText("8 Pending");
        pnlCard3.add(lblCard3Value);
        lblCard3Value.setBounds(60, 40, 130, 20);

        getContentPane().add(pnlCard3);
        pnlCard3.setBounds(660, 80, 200, 80);

        pnlCard4.setBackground(new java.awt.Color(204, 57, 51));
        pnlCard4.setLayout(null);

        lblCard4Title.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCard4Title.setForeground(new java.awt.Color(255, 255, 255));
        lblCard4Title.setText("Deactivated Accounts");
        pnlCard4.add(lblCard4Title);
        lblCard4Title.setBounds(50, 20, 150, 20);

        lblCard4Value.setForeground(new java.awt.Color(255, 255, 255));
        lblCard4Value.setText("6 Deactivated");
        pnlCard4.add(lblCard4Value);
        lblCard4Value.setBounds(50, 40, 150, 20);

        getContentPane().add(pnlCard4);
        pnlCard4.setBounds(875, 80, 200, 80);

        pnlTable1.setBackground(new java.awt.Color(255, 255, 255));
        pnlTable1.setLayout(null);

        lblTable1Title.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTable1Title.setText("Owner Applications");
        pnlTable1.add(lblTable1Title);
        lblTable1Title.setBounds(20, 10, 300, 30);

        tblApplications.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Owner Name", "Email", "Phone", "Registered Date", "Status", "Actions"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblApplications.setRowHeight(40);
        tblApplications.setShowGrid(false);
        jScrollPane1.setViewportView(tblApplications);

        pnlTable1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 50, 805, 190);

        getContentPane().add(pnlTable1);
        pnlTable1.setBounds(230, 190, 845, 260);

        pnlTable2.setBackground(new java.awt.Color(255, 255, 255));
        pnlTable2.setLayout(null);

        lblTable2Title.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTable2Title.setText("Property Listings Overview");
        pnlTable2.add(lblTable2Title);
        lblTable2Title.setBounds(20, 10, 300, 30);

        tblProperties.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Property Title", "Owner", "Status", "Date Added", "Actions"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProperties.setRowHeight(40);
        tblProperties.setShowGrid(false);
        jScrollPane2.setViewportView(tblProperties);

        pnlTable2.add(jScrollPane2);
        jScrollPane2.setBounds(20, 50, 805, 190);

        getContentPane().add(pnlTable2);
        pnlTable2.setBounds(230, 480, 845, 260);

        pack();
        setLocationRelativeTo(null);
    }

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {
        adminController.navigateToDashboard(this);
    }

    private void btnUserManagementActionPerformed(java.awt.event.ActionEvent evt) {
        adminController.navigateToUserManagement(this);
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        adminController.logout(this);
    }

    public javax.swing.JLabel getLblCard1Value() { return lblCard1Value; }
    public javax.swing.JLabel getLblCard2Value() { return lblCard2Value; }
    public javax.swing.JLabel getLblCard3Value() { return lblCard3Value; }
    public javax.swing.JLabel getLblCard4Value() { return lblCard4Value; }
    public javax.swing.JTable getTblApplications() { return tblApplications; }
    public javax.swing.JTable getTblProperties() { return tblProperties; }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboardView().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnUserManagement;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCard1Title;
    private javax.swing.JLabel lblCard1Value;
    private javax.swing.JLabel lblCard2Title;
    private javax.swing.JLabel lblCard2Value;
    private javax.swing.JLabel lblCard3Title;
    private javax.swing.JLabel lblCard3Value;
    private javax.swing.JLabel lblCard4Title;
    private javax.swing.JLabel lblCard4Value;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblTable1Title;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlCard1;
    private javax.swing.JPanel pnlCard2;
    private javax.swing.JPanel pnlCard3;
    private javax.swing.JPanel pnlCard4;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlTable1;
    private javax.swing.JPanel pnlTable2;
    private javax.swing.JTable tblApplications;
    private javax.swing.JTable tblProperties;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTable2Title;
    // End of variables declaration
}

package view;

import Controller.PropertyController;

public class MyPropertiesView extends javax.swing.JFrame {

    private PropertyController propertyController;

    public MyPropertiesView() {
        initComponents();
        propertyController = new PropertyController();
        propertyController.initMyProperties(this);
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
        lblHeaderTitle = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        pnlCard = new javax.swing.JPanel();
        lblMyPropertiesTitle = new javax.swing.JLabel();
        btnAddProperty = new javax.swing.JButton();
        pnlTableHeader = new javax.swing.JPanel();
        lblColTitle = new javax.swing.JLabel();
        lblColLocation = new javax.swing.JLabel();
        lblColStatus = new javax.swing.JLabel();
        lblColActions = new javax.swing.JLabel();
        lblColDate = new javax.swing.JLabel();
        scrollTable = new javax.swing.JScrollPane();
        pnlTableBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartRent - My Properties");
        setPreferredSize(new java.awt.Dimension(1280, 800));
        setResizable(false);
        getContentPane().setLayout(null);

        pnlSidebar.setBackground(new java.awt.Color(60, 110, 113));
        pnlSidebar.setLayout(null);

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("");
        pnlSidebar.add(lblLogo);
        lblLogo.setBounds(0, 0, 200, 80);

        btnNavDashboard.setBackground(new java.awt.Color(60, 110, 113));
        btnNavDashboard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnNavDashboard.setText("Dashboard");
        btnNavDashboard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255, 100)));
        btnNavDashboard.setBorderPainted(true);
        btnNavDashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNavDashboard.addActionListener(this::btnNavDashboardActionPerformed);
        pnlSidebar.add(btnNavDashboard);
        btnNavDashboard.setBounds(0, 100, 200, 40);

        btnNavMyProperties.setBackground(new java.awt.Color(80, 128, 128));
        btnNavMyProperties.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavMyProperties.setForeground(new java.awt.Color(255, 255, 255));
        btnNavMyProperties.setText("My Properties");
        btnNavMyProperties.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255, 100)));
        btnNavMyProperties.setBorderPainted(true);
        btnNavMyProperties.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlSidebar.add(btnNavMyProperties);
        btnNavMyProperties.setBounds(0, 140, 200, 40);

        btnNavLeaseManagement.setBackground(new java.awt.Color(60, 110, 113));
        btnNavLeaseManagement.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLeaseManagement.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLeaseManagement.setText("Lease Management");
        btnNavLeaseManagement.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255, 100)));
        btnNavLeaseManagement.setBorderPainted(true);
        btnNavLeaseManagement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNavLeaseManagement.addActionListener(this::btnNavLeaseManagementActionPerformed);
        pnlSidebar.add(btnNavLeaseManagement);
        btnNavLeaseManagement.setBounds(0, 180, 200, 40);

        btnNavLogout.setBackground(new java.awt.Color(60, 110, 113));
        btnNavLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLogout.setText("Logout");
        btnNavLogout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255, 100)));
        btnNavLogout.setBorderPainted(true);
        btnNavLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNavLogout.addActionListener(this::btnNavLogoutActionPerformed);
        pnlSidebar.add(btnNavLogout);
        btnNavLogout.setBounds(0, 240, 200, 40);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 200, 800);

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setLayout(null);

        lblHeaderTitle.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblHeaderTitle.setForeground(new java.awt.Color(45, 55, 72));
        lblHeaderTitle.setText("My Properties");
        pnlHeader.add(lblHeaderTitle);
        lblHeaderTitle.setBounds(30, 15, 300, 40);

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblWelcome.setForeground(new java.awt.Color(102, 102, 102));
        lblWelcome.setText("Welcome, Owner");
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnlHeader.add(lblWelcome);
        lblWelcome.setBounds(800, 15, 200, 40);

        getContentPane().add(pnlHeader);
        pnlHeader.setBounds(200, 0, 1080, 70);

        pnlCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard.setLayout(null);

        lblMyPropertiesTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblMyPropertiesTitle.setForeground(new java.awt.Color(45, 55, 72));
        lblMyPropertiesTitle.setText("My Properties");
        pnlCard.add(lblMyPropertiesTitle);
        lblMyPropertiesTitle.setBounds(15, 20, 200, 30);

        btnAddProperty.setBackground(new java.awt.Color(56, 161, 105));
        btnAddProperty.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAddProperty.setForeground(new java.awt.Color(255, 255, 255));
        btnAddProperty.setText("+");
        btnAddProperty.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnAddProperty.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnAddProperty.setBorderPainted(false);
        btnAddProperty.setFocusPainted(false);
        btnAddProperty.addActionListener(this::btnAddPropertyActionPerformed);
        pnlCard.add(btnAddProperty);
        btnAddProperty.setBounds(960, 15, 40, 40);

        pnlTableHeader.setBackground(new java.awt.Color(247, 250, 252));
        pnlTableHeader.setLayout(null);

        lblColTitle.setText("Property Title");
        lblColTitle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColTitle.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblColTitle);
        lblColTitle.setBounds(15, 10, 280, 20);

        lblColLocation.setText("Location");
        lblColLocation.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColLocation.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblColLocation);
        lblColLocation.setBounds(310, 10, 200, 20);

        lblColStatus.setText("Status");
        lblColStatus.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColStatus.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblColStatus);
        lblColStatus.setBounds(530, 10, 100, 20);

        lblColActions.setText("Actions");
        lblColActions.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColActions.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblColActions);
        lblColActions.setBounds(650, 10, 180, 20);

        lblColDate.setText("Date Listed");
        lblColDate.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColDate.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblColDate);
        lblColDate.setBounds(850, 10, 120, 20);

        pnlCard.add(pnlTableHeader);
        pnlTableHeader.setBounds(20, 80, 980, 40);

        scrollTable.setBorder(null);

        pnlTableBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlTableBody.setLayout(new javax.swing.BoxLayout(pnlTableBody, javax.swing.BoxLayout.Y_AXIS));
        scrollTable.setViewportView(pnlTableBody);

        pnlCard.add(scrollTable);
        scrollTable.setBounds(20, 120, 980, 500);

        getContentPane().add(pnlCard);
        pnlCard.setBounds(230, 80, 1020, 660);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNavDashboardActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.navigateToDashboard(this);
    }

    private void btnNavLeaseManagementActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.navigateToLeaseManagement(this);
    }

    private void btnNavLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.logout(this);
    }

    private void btnAddPropertyActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.addProperty(this);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAddProperty;
    public javax.swing.JButton btnNavDashboard;
    public javax.swing.JButton btnNavLeaseManagement;
    public javax.swing.JButton btnNavLogout;
    public javax.swing.JButton btnNavMyProperties;
    public javax.swing.JLabel lblColActions;
    public javax.swing.JLabel lblColDate;
    public javax.swing.JLabel lblColLocation;
    public javax.swing.JLabel lblColStatus;
    public javax.swing.JLabel lblColTitle;
    public javax.swing.JLabel lblHeaderTitle;
    public javax.swing.JLabel lblLogo;
    public javax.swing.JLabel lblMyPropertiesTitle;
    public javax.swing.JLabel lblWelcome;
    public javax.swing.JPanel pnlCard;
    public javax.swing.JPanel pnlHeader;
    public javax.swing.JPanel pnlSidebar;
    public javax.swing.JPanel pnlTableBody;
    public javax.swing.JPanel pnlTableHeader;
    public javax.swing.JScrollPane scrollTable;
    // End of variables declaration//GEN-END:variables
}

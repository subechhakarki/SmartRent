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
    public javax.swing.JLabel getLblWelcome() { return lblWelcome; }
    public javax.swing.JPanel getPnlTableBody() { return pnlTableBody; }
    public javax.swing.JButton getBtnAddProperty() { return btnAddProperty; }
    public javax.swing.JButton getBtnNavDashboard() { return btnNavDashboard; }
    public javax.swing.JButton getBtnNavMyProperties() { return btnNavMyProperties; }
    public javax.swing.JButton getBtnNavLeaseManagement() { return btnNavLeaseManagement; }
    public javax.swing.JButton getBtnNavLogout() { return btnNavLogout; }
    public javax.swing.JScrollPane getScrollTable() { return scrollTable; }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSidebar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        btnNavDashboard = new javax.swing.JButton();
        btnNavMyProperties = new javax.swing.JButton();
        btnNavLeaseManagement = new javax.swing.JButton();
        sepSidebar = new javax.swing.JSeparator();
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
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setResizable(false);
        getContentPane().setLayout(null);

        pnlSidebar.setBackground(new java.awt.Color(60, 110, 113));
        pnlSidebar.setLayout(null);

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("SmartRent");
        pnlSidebar.add(lblLogo);
        lblLogo.setBounds(10, 25, 180, 40);

        btnNavDashboard.setBackground(new java.awt.Color(60, 110, 113));
        btnNavDashboard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnNavDashboard.setText("Dashboard");
        btnNavDashboard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255, 100)));
        btnNavDashboard.setBorderPainted(true);
        btnNavDashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNavDashboard.addActionListener(this::btnNavDashboardActionPerformed);
        pnlSidebar.add(btnNavDashboard);
        btnNavDashboard.setBounds(0, 90, 200, 40);

        btnNavMyProperties.setBackground(new java.awt.Color(80, 128, 128));
        btnNavMyProperties.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavMyProperties.setForeground(new java.awt.Color(255, 255, 255));
        btnNavMyProperties.setText("My Properties");
        btnNavMyProperties.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255, 100)));
        btnNavMyProperties.setBorderPainted(true);
        btnNavMyProperties.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlSidebar.add(btnNavMyProperties);
        btnNavMyProperties.setBounds(0, 130, 200, 40);

        btnNavLeaseManagement.setBackground(new java.awt.Color(60, 110, 113));
        btnNavLeaseManagement.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLeaseManagement.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLeaseManagement.setText("Lease Management");
        btnNavLeaseManagement.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255, 100)));
        btnNavLeaseManagement.setBorderPainted(true);
        btnNavLeaseManagement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNavLeaseManagement.addActionListener(this::btnNavLeaseManagementActionPerformed);
        pnlSidebar.add(btnNavLeaseManagement);
        btnNavLeaseManagement.setBounds(0, 170, 200, 40);

        sepSidebar.setForeground(new java.awt.Color(255, 255, 255));
        pnlSidebar.add(sepSidebar);
        sepSidebar.setBounds(10, 230, 180, 10);

        btnNavLogout.setBackground(new java.awt.Color(60, 110, 113));
        btnNavLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLogout.setText("Logout");
        btnNavLogout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255, 100)));
        btnNavLogout.setBorderPainted(true);
        btnNavLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNavLogout.addActionListener(this::btnNavLogoutActionPerformed);
        pnlSidebar.add(btnNavLogout);
        btnNavLogout.setBounds(0, 245, 200, 40);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 200, 768);

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
        lblWelcome.setBounds(580, 15, 200, 40);

        getContentPane().add(pnlHeader);
        pnlHeader.setBounds(200, 0, 824, 70);

        pnlCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard.setLayout(null);

        lblMyPropertiesTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblMyPropertiesTitle.setForeground(new java.awt.Color(45, 55, 72));
        lblMyPropertiesTitle.setText("My Properties");
        pnlCard.add(lblMyPropertiesTitle);
        lblMyPropertiesTitle.setBounds(15, 20, 200, 30);

        btnAddProperty.setBackground(new java.awt.Color(56, 161, 105));
        btnAddProperty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddProperty.setForeground(new java.awt.Color(255, 255, 255));
        btnAddProperty.setText("+ Add Property");
        btnAddProperty.setBorderPainted(false);
        btnAddProperty.setFocusPainted(false);
        btnAddProperty.addActionListener(this::btnAddPropertyActionPerformed);
        pnlCard.add(btnAddProperty);
        btnAddProperty.setBounds(620, 20, 150, 40);

        pnlTableHeader.setBackground(new java.awt.Color(247, 250, 252));
        pnlTableHeader.setLayout(null);

        lblColTitle.setText("Property Title");
        lblColTitle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColTitle.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblColTitle);
        lblColTitle.setBounds(15, 10, 180, 20);

        lblColLocation.setText("Location");
        lblColLocation.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColLocation.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblColLocation);
        lblColLocation.setBounds(205, 10, 130, 20);

        lblColStatus.setText("Status");
        lblColStatus.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColStatus.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblColStatus);
        lblColStatus.setBounds(345, 10, 90, 20);

        lblColActions.setText("Actions");
        lblColActions.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColActions.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblColActions);
        lblColActions.setBounds(450, 10, 150, 20);

        lblColDate.setText("Date Listed");
        lblColDate.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColDate.setForeground(new java.awt.Color(74, 85, 104));
        pnlTableHeader.add(lblColDate);
        lblColDate.setBounds(615, 10, 120, 20);

        pnlCard.add(pnlTableHeader);
        pnlTableHeader.setBounds(15, 80, 760, 40);

        scrollTable.setBorder(null);

        pnlTableBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlTableBody.setLayout(new javax.swing.BoxLayout(pnlTableBody, javax.swing.BoxLayout.Y_AXIS));
        scrollTable.setViewportView(pnlTableBody);

        pnlCard.add(scrollTable);
        scrollTable.setBounds(15, 120, 760, 480);

        getContentPane().add(pnlCard);
        pnlCard.setBounds(215, 80, 790, 670);

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
    private javax.swing.JButton btnAddProperty;
    private javax.swing.JButton btnNavDashboard;
    private javax.swing.JButton btnNavLeaseManagement;
    private javax.swing.JButton btnNavLogout;
    private javax.swing.JButton btnNavMyProperties;
    private javax.swing.JLabel lblColActions;
    private javax.swing.JLabel lblColDate;
    private javax.swing.JLabel lblColLocation;
    private javax.swing.JLabel lblColStatus;
    private javax.swing.JLabel lblColTitle;
    private javax.swing.JLabel lblHeaderTitle;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMyPropertiesTitle;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel pnlCard;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlTableBody;
    private javax.swing.JPanel pnlTableHeader;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JSeparator sepSidebar;
    // End of variables declaration//GEN-END:variables
}

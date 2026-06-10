package view;

import Controller.PropertyController;
import Model.Property;
import Model.User;
import smartrent.SessionService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

public class RenterDashboardView extends javax.swing.JFrame {

    private PropertyController propertyController;
    private final PropertyCardRenter pnlCard1 = new PropertyCardRenter();
    private final PropertyCardRenter pnlCard2 = new PropertyCardRenter();
    private final PropertyCardRenter pnlCard3 = new PropertyCardRenter();
    private final PropertyCardRenter pnlCard4 = new PropertyCardRenter();
    private final PropertyCardRenter pnlCard5 = new PropertyCardRenter();
    private final PropertyCardRenter pnlCard6 = new PropertyCardRenter();

    public RenterDashboardView() {
        initComponents();
        pnlPropertiesGrid.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 20));
        pnlPropertiesGrid.add(pnlCard1);
        pnlPropertiesGrid.add(pnlCard2);
        pnlPropertiesGrid.add(pnlCard3);
        pnlPropertiesGrid.add(pnlCard4);
        pnlPropertiesGrid.add(pnlCard5);
        pnlPropertiesGrid.add(pnlCard6);
        setupPlaceholders();
        propertyController = new PropertyController();
        propertyController.initRenterDashboard(this);
    }

    private void setupPlaceholders() {
        addPlaceholder(txtLocation, "Location");
        addPlaceholder(txtMaxPrice, "Max Price");
    }

    private void addPlaceholder(JTextField field, String placeholder) {
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                }
            }
        });
    }

    public PropertyCardRenter getPnlCard1() { return pnlCard1; }
    public PropertyCardRenter getPnlCard2() { return pnlCard2; }
    public PropertyCardRenter getPnlCard3() { return pnlCard3; }
    public PropertyCardRenter getPnlCard4() { return pnlCard4; }
    public PropertyCardRenter getPnlCard5() { return pnlCard5; }
    public PropertyCardRenter getPnlCard6() { return pnlCard6; }

    public javax.swing.JPanel getPnlPropertiesGrid() { return pnlPropertiesGrid; }
    public javax.swing.JScrollPane getScrollPaneProperties() { return scrollPaneProperties; }
    public javax.swing.JPanel getPnlSidebar() { return pnlSidebar; }
    public javax.swing.JLabel getLblLogo() { return lblLogo; }
    public javax.swing.JButton getBtnNavDashboard() { return btnNavDashboard; }
    public javax.swing.JButton getBtnNavMyApplications() { return btnNavMyApplications; }
    public javax.swing.JButton getBtnNavPropertyRatings() { return btnNavPropertyRatings; }
    public javax.swing.JButton getBtnNavSavedProperties() { return btnNavSavedProperties; }
    public javax.swing.JButton getBtnNavLogout() { return btnNavLogout; }
    public javax.swing.JLabel getLblDashboardHeader() { return lblDashboardHeader; }
    public javax.swing.JLabel getLblWelcome() { return lblWelcome; }
    public javax.swing.JPanel getPnlFilters() { return pnlFilters; }
    public javax.swing.JTextField getTxtLocation() { return txtLocation; }
    public javax.swing.JTextField getTxtMaxPrice() { return txtMaxPrice; }
    public javax.swing.JComboBox getCmbBedrooms() { return cmbBedrooms; }
    public javax.swing.JComboBox getCmbPropertyType() { return cmbPropertyType; }
    public javax.swing.JButton getBtnSearch() { return btnSearch; }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSidebar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        btnNavDashboard = new javax.swing.JButton();
        btnNavMyApplications = new javax.swing.JButton();
        btnNavPropertyRatings = new javax.swing.JButton();
        btnNavSavedProperties = new javax.swing.JButton();
        btnNavLogout = new javax.swing.JButton();
        lblDashboardHeader = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        pnlFilters = new javax.swing.JPanel();
        txtLocation = new javax.swing.JTextField();
        txtMaxPrice = new javax.swing.JTextField();
        cmbBedrooms = new javax.swing.JComboBox();
        cmbPropertyType = new javax.swing.JComboBox();
        btnSearch = new javax.swing.JButton();
        scrollPaneProperties = new javax.swing.JScrollPane();
        pnlPropertiesGrid = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartRent - Renter Dashboard");
        setBackground(new java.awt.Color(245, 247, 250));
        setPreferredSize(new java.awt.Dimension(1280, 800));
        getContentPane().setLayout(null);

        pnlSidebar.setBackground(new java.awt.Color(60, 110, 113));
        pnlSidebar.setLayout(null);

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("<html><font size='5'>🏠</font> SmartRent</html>");
        pnlSidebar.add(lblLogo);
        lblLogo.setBounds(20, 20, 180, 40);

        btnNavDashboard.setBackground(new java.awt.Color(80, 128, 128));
        btnNavDashboard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnNavDashboard.setText("⊞  Dashboard");
        btnNavDashboard.setBorderPainted(false);
        btnNavDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavDashboard.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavDashboard.addActionListener(this::btnNavDashboardActionPerformed);
        pnlSidebar.add(btnNavDashboard);
        btnNavDashboard.setBounds(0, 80, 220, 40);

        btnNavMyApplications.setBackground(new java.awt.Color(60, 110, 113));
        btnNavMyApplications.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavMyApplications.setForeground(new java.awt.Color(255, 255, 255));
        btnNavMyApplications.setText("📄  My Applications");
        btnNavMyApplications.setBorderPainted(false);
        btnNavMyApplications.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavMyApplications.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavMyApplications.addActionListener(this::btnNavMyApplicationsActionPerformed);
        pnlSidebar.add(btnNavMyApplications);
        btnNavMyApplications.setBounds(0, 120, 220, 40);

        btnNavPropertyRatings.setBackground(new java.awt.Color(60, 110, 113));
        btnNavPropertyRatings.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavPropertyRatings.setForeground(new java.awt.Color(255, 255, 255));
        btnNavPropertyRatings.setText("★  Property Ratings");
        btnNavPropertyRatings.setBorderPainted(false);
        btnNavPropertyRatings.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavPropertyRatings.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavPropertyRatings.addActionListener(this::btnNavPropertyRatingsActionPerformed);
        pnlSidebar.add(btnNavPropertyRatings);
        btnNavPropertyRatings.setBounds(0, 160, 220, 40);

        btnNavSavedProperties.setBackground(new java.awt.Color(60, 110, 113));
        btnNavSavedProperties.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavSavedProperties.setForeground(new java.awt.Color(255, 255, 255));
        btnNavSavedProperties.setText("♡  Saved Properties");
        btnNavSavedProperties.setBorderPainted(false);
        btnNavSavedProperties.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavSavedProperties.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavSavedProperties.addActionListener(this::btnNavSavedPropertiesActionPerformed);
        pnlSidebar.add(btnNavSavedProperties);
        btnNavSavedProperties.setBounds(0, 200, 220, 40);

        btnNavLogout.setText("🚪  Logout");
        btnNavLogout.setBackground(new java.awt.Color(60, 110, 113));
        btnNavLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLogout.setBorderPainted(false);
        btnNavLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavLogout.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavLogout.addActionListener(this::btnNavLogoutActionPerformed);
        pnlSidebar.add(btnNavLogout);
        btnNavLogout.setBounds(0, 260, 220, 40);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 220, 800);

        lblDashboardHeader.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblDashboardHeader.setText("Renter Dashboard");
        getContentPane().add(lblDashboardHeader);
        lblDashboardHeader.setBounds(250, 20, 200, 40);

        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblWelcome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblWelcome.setText("Welcome, User");
        getContentPane().add(lblWelcome);
        lblWelcome.setBounds(1030, 20, 200, 40);

        pnlFilters.setBackground(new java.awt.Color(245, 247, 250));
        pnlFilters.setLayout(null);

        txtLocation.setText("Location");
        pnlFilters.add(txtLocation);
        txtLocation.setBounds(0, 15, 150, 35);

        txtMaxPrice.setText("Max Price");
        pnlFilters.add(txtMaxPrice);
        txtMaxPrice.setBounds(170, 15, 150, 35);

        cmbBedrooms.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Any", "1", "2", "3", "4+" }));
        pnlFilters.add(cmbBedrooms);
        cmbBedrooms.setBounds(340, 15, 100, 35);

        cmbPropertyType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Any", "Apartment", "House", "Studio" }));
        pnlFilters.add(cmbPropertyType);
        cmbPropertyType.setBounds(460, 15, 120, 35);

        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearch.setText("Search...");
        btnSearch.setBackground(new java.awt.Color(30, 92, 240));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        pnlFilters.add(btnSearch);
        btnSearch.setBounds(850, 15, 150, 35);

        getContentPane().add(pnlFilters);
        pnlFilters.setBounds(250, 70, 1000, 60);

        scrollPaneProperties.setBorder(null);

        pnlPropertiesGrid.setBackground(new java.awt.Color(245, 247, 250));
        scrollPaneProperties.setViewportView(pnlPropertiesGrid);

        getContentPane().add(scrollPaneProperties);
        scrollPaneProperties.setBounds(250, 140, 1000, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNavDashboardActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.navigateToDashboard(this);
    }

    private void btnNavLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.logout(this);
    }

    private void btnNavMyApplicationsActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.navigateToMyApplications(this);
    }

    private void btnNavPropertyRatingsActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.navigateToPropertyRatings(this);
    }

    private void btnNavSavedPropertiesActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.navigateToSavedProperties(this);
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.loadRenterProperties(this);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNavDashboard;
    private javax.swing.JButton btnNavLogout;
    private javax.swing.JButton btnNavMyApplications;
    private javax.swing.JButton btnNavPropertyRatings;
    private javax.swing.JButton btnNavSavedProperties;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cmbBedrooms;
    private javax.swing.JComboBox cmbPropertyType;
    private javax.swing.JLabel lblDashboardHeader;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel pnlFilters;
    private javax.swing.JPanel pnlPropertiesGrid;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JScrollPane scrollPaneProperties;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtMaxPrice;
    // End of variables declaration//GEN-END:variables
}

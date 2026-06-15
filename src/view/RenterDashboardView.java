package view;

import Controller.PropertyController;
import Model.Property;
import Model.User;
import Controller.SessionService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

public class RenterDashboardView extends javax.swing.JFrame {

    private PropertyController propertyController;
    public final PropertyCardRenter pnlCard1 = new PropertyCardRenter();
    public final PropertyCardRenter pnlCard2 = new PropertyCardRenter();
    public final PropertyCardRenter pnlCard3 = new PropertyCardRenter();
    public final PropertyCardRenter pnlCard4 = new PropertyCardRenter();
    public final PropertyCardRenter pnlCard5 = new PropertyCardRenter();
    public final PropertyCardRenter pnlCard6 = new PropertyCardRenter();

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
        
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        cmbBedrooms.addActionListener(e -> propertyController.loadRenterProperties(this));
        cmbPropertyType.addActionListener(e -> propertyController.loadRenterProperties(this));
        txtLocation.addActionListener(e -> propertyController.loadRenterProperties(this));
        txtMaxPrice.addActionListener(e -> propertyController.loadRenterProperties(this));
        
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

    public PropertyCardRenter getPnlCard5() { return pnlCard5; }
    public PropertyCardRenter getPnlCard6() { return pnlCard6; }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSidebar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogo.setBackground(new java.awt.Color(209, 232, 237));
        lblLogo.setOpaque(true);
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

        lblLogo.setBackground(new java.awt.Color(209, 232, 237));
        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(60, 110, 113));
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/SmartRentLogo_Banner.png"))); // NOI18N
        lblLogo.setOpaque(true);
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

        btnNavMyApplications.setBackground(new java.awt.Color(60, 110, 113));
        btnNavMyApplications.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavMyApplications.setForeground(new java.awt.Color(255, 255, 255));
        btnNavMyApplications.setText("My Applications");
        btnNavMyApplications.setBorderPainted(false);
        btnNavMyApplications.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavMyApplications.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavMyApplications.addActionListener(this::btnNavMyApplicationsActionPerformed);
        pnlSidebar.add(btnNavMyApplications);
        btnNavMyApplications.setBounds(0, 140, 200, 40);

        btnNavPropertyRatings.setBackground(new java.awt.Color(60, 110, 113));
        btnNavPropertyRatings.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavPropertyRatings.setForeground(new java.awt.Color(255, 255, 255));
        btnNavPropertyRatings.setText("Property Ratings");
        btnNavPropertyRatings.setBorderPainted(false);
        btnNavPropertyRatings.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavPropertyRatings.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavPropertyRatings.addActionListener(this::btnNavPropertyRatingsActionPerformed);
        pnlSidebar.add(btnNavPropertyRatings);
        btnNavPropertyRatings.setBounds(0, 180, 200, 40);

        btnNavSavedProperties.setBackground(new java.awt.Color(60, 110, 113));
        btnNavSavedProperties.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavSavedProperties.setForeground(new java.awt.Color(255, 255, 255));
        btnNavSavedProperties.setText("Saved Properties");
        btnNavSavedProperties.setBorderPainted(false);
        btnNavSavedProperties.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavSavedProperties.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavSavedProperties.addActionListener(this::btnNavSavedPropertiesActionPerformed);
        pnlSidebar.add(btnNavSavedProperties);
        btnNavSavedProperties.setBounds(0, 220, 200, 40);

        btnNavLogout.setBackground(new java.awt.Color(60, 110, 113));
        btnNavLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLogout.setText("Logout");
        btnNavLogout.setBorderPainted(false);
        btnNavLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavLogout.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavLogout.addActionListener(this::btnNavLogoutActionPerformed);
        pnlSidebar.add(btnNavLogout);
        btnNavLogout.setBounds(0, 280, 200, 40);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 200, 800);

        lblDashboardHeader.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblDashboardHeader.setText("Renter Dashboard");
        getContentPane().add(lblDashboardHeader);
        lblDashboardHeader.setBounds(230, 20, 200, 40);

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblWelcome.setText("Welcome, User");
        getContentPane().add(lblWelcome);
        lblWelcome.setBounds(1050, 20, 200, 40);

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

        btnSearch.setBackground(new java.awt.Color(30, 92, 240));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search...");
        pnlFilters.add(btnSearch);
        btnSearch.setBounds(850, 15, 150, 35);

        getContentPane().add(pnlFilters);
        pnlFilters.setBounds(230, 70, 1020, 60);

        scrollPaneProperties.setBorder(null);

        pnlPropertiesGrid.setBackground(new java.awt.Color(245, 247, 250));
        pnlPropertiesGrid.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 20));
        scrollPaneProperties.setViewportView(pnlPropertiesGrid);

        getContentPane().add(scrollPaneProperties);
        scrollPaneProperties.setBounds(230, 140, 1020, 600);

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
    public javax.swing.JButton btnNavDashboard;
    public javax.swing.JButton btnNavLogout;
    public javax.swing.JButton btnNavMyApplications;
    public javax.swing.JButton btnNavPropertyRatings;
    public javax.swing.JButton btnNavSavedProperties;
    public javax.swing.JButton btnSearch;
    public javax.swing.JComboBox cmbBedrooms;
    public javax.swing.JComboBox cmbPropertyType;
    public javax.swing.JLabel lblDashboardHeader;
    public javax.swing.JLabel lblLogo;
    public javax.swing.JLabel lblWelcome;
    public javax.swing.JPanel pnlFilters;
    public javax.swing.JPanel pnlPropertiesGrid;
    public javax.swing.JPanel pnlSidebar;
    public javax.swing.JScrollPane scrollPaneProperties;
    public javax.swing.JTextField txtLocation;
    public javax.swing.JTextField txtMaxPrice;
    // End of variables declaration//GEN-END:variables
}

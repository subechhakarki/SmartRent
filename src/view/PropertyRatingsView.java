package view;

import Controller.PropertyController;
import Model.Rating;
import Model.User;
import Controller.SessionService;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PropertyRatingsView extends javax.swing.JFrame {

    private PropertyController propertyController;
    private int propertyId = -1;
    private String propertyTitle = "";

    public PropertyRatingsView() {
        this(-1, "Select a Property from Dashboard");
    }

    public PropertyRatingsView(int propertyId, String propertyTitle) {
        this.propertyId = propertyId;
        this.propertyTitle = propertyTitle;
        initComponents();
        propertyController = new PropertyController();
        propertyController.initRatingsView(this, propertyId, propertyTitle);
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRatingsList = new javax.swing.JPanel();
        pnlSidebar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogo.setBackground(new java.awt.Color(209, 232, 237));
        lblLogo.setOpaque(true);
        btnNavDashboard = new javax.swing.JButton();
        btnNavMyApplications = new javax.swing.JButton();
        btnNavPropertyRatings = new javax.swing.JButton();
        btnNavSavedProperties = new javax.swing.JButton();
        btnNavLogout = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblAvgRating = new javax.swing.JLabel();
        scrollPaneRatings = new javax.swing.JScrollPane();
        tblRatings = new javax.swing.JTable();
        lblAddRating = new javax.swing.JLabel();
        cmbScore = new javax.swing.JComboBox();
        txtReview = new javax.swing.JTextField();
        btnSubmitReview = new javax.swing.JButton();

        pnlRatingsList.setBackground(new java.awt.Color(245, 247, 250));
        pnlRatingsList.setLayout(new javax.swing.BoxLayout(pnlRatingsList, javax.swing.BoxLayout.Y_AXIS));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartRent - Property Ratings");
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

        btnNavDashboard.setBackground(new java.awt.Color(60, 110, 113));
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

        btnNavPropertyRatings.setBackground(new java.awt.Color(80, 128, 128));
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

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setText("Property Ratings");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(230, 20, 600, 30);

        lblAvgRating.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblAvgRating.setText("Average Rating: 0.0 / 5");
        getContentPane().add(lblAvgRating);
        lblAvgRating.setBounds(230, 60, 400, 20);

        scrollPaneRatings.setBorder(null);

        tblRatings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Property", "Rating", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPaneRatings.setViewportView(tblRatings);

        getContentPane().add(scrollPaneRatings);
        scrollPaneRatings.setBounds(230, 100, 1000, 600);

        lblAddRating.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAddRating.setText("Leave a Review");
        getContentPane().add(lblAddRating);
        lblAddRating.setBounds(230, 580, 200, 20);

        cmbScore.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5 - Excellent", "4 - Good", "3 - Average", "2 - Poor", "1 - Terrible" }));
        getContentPane().add(cmbScore);
        cmbScore.setBounds(230, 610, 150, 40);

        txtReview.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtReview.setText("Write your review here...");
        getContentPane().add(txtReview);
        txtReview.setBounds(400, 610, 450, 40);

        btnSubmitReview.setBackground(new java.awt.Color(34, 180, 50));
        btnSubmitReview.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmitReview.setText("Submit");
        btnSubmitReview.addActionListener(this::btnSubmitReviewActionPerformed);
        getContentPane().add(btnSubmitReview);
        btnSubmitReview.setBounds(870, 610, 100, 40);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNavDashboardActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.navigateToDashboard(this);
    }

    private void btnNavMyApplicationsActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.navigateToMyApplications(this);
    }

    private void btnNavPropertyRatingsActionPerformed(java.awt.event.ActionEvent evt) {
        // Already on this page
    }

    private void btnNavSavedPropertiesActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.navigateToSavedProperties(this);
    }

    private void btnNavLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.logout(this);
    }

    private void btnSubmitReviewActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.submitRating(this, propertyId);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnNavDashboard;
    public javax.swing.JButton btnNavLogout;
    public javax.swing.JButton btnNavMyApplications;
    public javax.swing.JButton btnNavPropertyRatings;
    public javax.swing.JButton btnNavSavedProperties;
    public javax.swing.JButton btnSubmitReview;
    public javax.swing.JComboBox cmbScore;
    public javax.swing.JLabel lblAddRating;
    public javax.swing.JLabel lblAvgRating;
    public javax.swing.JLabel lblLogo;
    public javax.swing.JLabel lblTitle;
    public javax.swing.JPanel pnlRatingsList;
    public javax.swing.JPanel pnlSidebar;
    public javax.swing.JScrollPane scrollPaneRatings;
    public javax.swing.JTable tblRatings;
    public javax.swing.JTextField txtReview;
    // End of variables declaration//GEN-END:variables
}

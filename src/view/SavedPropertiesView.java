package view;

import Controller.SavedPropertyController;
import Model.Property;
import Model.User;
import Controller.SessionService;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.List;
import javax.swing.*;

public class SavedPropertiesView extends javax.swing.JFrame {

    private SavedPropertyController savedPropertyController;
    public final PropertyCardSaved pnlCard1 = new PropertyCardSaved();
    public final PropertyCardSaved pnlCard2 = new PropertyCardSaved();
    public final PropertyCardSaved pnlCard3 = new PropertyCardSaved();
    public final PropertyCardSaved pnlCard4 = new PropertyCardSaved();
    public final PropertyCardSaved pnlCard5 = new PropertyCardSaved();
    public final PropertyCardSaved pnlCard6 = new PropertyCardSaved();

    public SavedPropertiesView() {
        initComponents();
        pnlGrid.add(pnlCard1);
        pnlGrid.add(pnlCard2);
        pnlGrid.add(pnlCard3);
        pnlGrid.add(pnlCard4);
        pnlGrid.add(pnlCard5);
        pnlGrid.add(pnlCard6);
        savedPropertyController = new SavedPropertyController();
        savedPropertyController.initSavedPropertiesView(this);
    }

    public PropertyCardSaved getPnlCard5() { return pnlCard5; }
    public PropertyCardSaved getPnlCard6() { return pnlCard6; }


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
        scrollPaneApps = new javax.swing.JScrollPane();
        pnlGrid = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SmartRent - Saved Properties");
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

        btnNavSavedProperties.setBackground(new java.awt.Color(80, 128, 128));
        btnNavSavedProperties.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavSavedProperties.setForeground(new java.awt.Color(255, 255, 255));
        btnNavSavedProperties.setText("Saved Properties");
        btnNavSavedProperties.setBorderPainted(false);
        btnNavSavedProperties.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavSavedProperties.setMargin(new java.awt.Insets(2, 20, 2, 14));
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

        lblDashboardHeader.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDashboardHeader.setText("Saved Properties");
        getContentPane().add(lblDashboardHeader);
        lblDashboardHeader.setBounds(230, 20, 250, 40);

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblWelcome.setText("Welcome, User");
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(lblWelcome);
        lblWelcome.setBounds(1050, 20, 200, 40);

        scrollPaneApps.setBorder(null);
        scrollPaneApps.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        pnlGrid.setBackground(new java.awt.Color(245, 247, 250));
        pnlGrid.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 20));
        scrollPaneApps.setViewportView(pnlGrid);

        getContentPane().add(scrollPaneApps);
        scrollPaneApps.setBounds(230, 80, 1020, 660);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNavDashboardActionPerformed(java.awt.event.ActionEvent evt) {
        savedPropertyController.navigateToDashboard(this);
    }

    private void btnNavMyApplicationsActionPerformed(java.awt.event.ActionEvent evt) {
        savedPropertyController.navigateToMyApplications(this);
    }

    private void btnNavPropertyRatingsActionPerformed(java.awt.event.ActionEvent evt) {
        savedPropertyController.navigateToPropertyRatings(this);
    }

    private void btnNavLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        savedPropertyController.logout(this);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnNavDashboard;
    public javax.swing.JButton btnNavLogout;
    public javax.swing.JButton btnNavMyApplications;
    public javax.swing.JButton btnNavPropertyRatings;
    public javax.swing.JButton btnNavSavedProperties;
    public javax.swing.JLabel lblDashboardHeader;
    public javax.swing.JLabel lblLogo;
    public javax.swing.JLabel lblWelcome;
    public javax.swing.JPanel pnlGrid;
    public javax.swing.JPanel pnlSidebar;
    public javax.swing.JScrollPane scrollPaneApps;
    // End of variables declaration//GEN-END:variables
}

package view;

import Controller.SavedPropertyController;
import Model.Property;
import Model.User;
import smartrent.SessionService;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.List;
import javax.swing.*;

public class SavedPropertiesView extends javax.swing.JFrame {

    private SavedPropertyController savedPropertyController;
    private final PropertyCardSaved pnlCard1 = new PropertyCardSaved();
    private final PropertyCardSaved pnlCard2 = new PropertyCardSaved();
    private final PropertyCardSaved pnlCard3 = new PropertyCardSaved();
    private final PropertyCardSaved pnlCard4 = new PropertyCardSaved();
    private final PropertyCardSaved pnlCard5 = new PropertyCardSaved();
    private final PropertyCardSaved pnlCard6 = new PropertyCardSaved();

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

    public PropertyCardSaved getPnlCard1() { return pnlCard1; }
    public PropertyCardSaved getPnlCard2() { return pnlCard2; }
    public PropertyCardSaved getPnlCard3() { return pnlCard3; }
    public PropertyCardSaved getPnlCard4() { return pnlCard4; }
    public PropertyCardSaved getPnlCard5() { return pnlCard5; }
    public PropertyCardSaved getPnlCard6() { return pnlCard6; }

    public javax.swing.JPanel getPnlGrid() { return pnlGrid; }
    public javax.swing.JScrollPane getScrollPaneApps() { return scrollPaneApps; }
    public javax.swing.JPanel getPnlSidebar() { return pnlSidebar; }
    public javax.swing.JLabel getLblLogo() { return lblLogo; }
    public javax.swing.JButton getBtnNavDashboard() { return btnNavDashboard; }
    public javax.swing.JButton getBtnNavMyApplications() { return btnNavMyApplications; }
    public javax.swing.JButton getBtnNavPropertyRatings() { return btnNavPropertyRatings; }
    public javax.swing.JButton getBtnNavSavedProperties() { return btnNavSavedProperties; }
    public javax.swing.JButton getBtnNavLogout() { return btnNavLogout; }
    public javax.swing.JLabel getLblDashboardHeader() { return lblDashboardHeader; }
    public javax.swing.JLabel getLblWelcome() { return lblWelcome; }

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
        scrollPaneApps = new javax.swing.JScrollPane();
        pnlGrid = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SmartRent - Saved Properties");
        setBackground(new java.awt.Color(245, 247, 250));
        setPreferredSize(new java.awt.Dimension(1280, 800));
        getContentPane().setLayout(null);

        pnlSidebar.setBackground(new java.awt.Color(60, 110, 113));
        pnlSidebar.setLayout(null);

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("SmartRent");
        pnlSidebar.add(lblLogo);
        lblLogo.setBounds(20, 20, 180, 40);

        btnNavDashboard.setText("Dashboard");
        btnNavDashboard.setBackground(new java.awt.Color(60, 110, 113));
        btnNavDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnNavDashboard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavDashboard.addActionListener(this::btnNavDashboardActionPerformed);
        pnlSidebar.add(btnNavDashboard);
        btnNavDashboard.setBounds(0, 80, 220, 40);

        btnNavMyApplications.setText("My Applications");
        btnNavMyApplications.setBackground(new java.awt.Color(60, 110, 113));
        btnNavMyApplications.setForeground(new java.awt.Color(255, 255, 255));
        btnNavMyApplications.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavMyApplications.addActionListener(this::btnNavMyApplicationsActionPerformed);
        pnlSidebar.add(btnNavMyApplications);
        btnNavMyApplications.setBounds(0, 120, 220, 40);

        btnNavPropertyRatings.setText("Property Ratings");
        btnNavPropertyRatings.setBackground(new java.awt.Color(60, 110, 113));
        btnNavPropertyRatings.setForeground(new java.awt.Color(255, 255, 255));
        btnNavPropertyRatings.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavPropertyRatings.addActionListener(this::btnNavPropertyRatingsActionPerformed);
        pnlSidebar.add(btnNavPropertyRatings);
        btnNavPropertyRatings.setBounds(0, 160, 220, 40);

        btnNavSavedProperties.setText("Saved Properties");
        btnNavSavedProperties.setBackground(new java.awt.Color(80, 128, 128));
        btnNavSavedProperties.setForeground(new java.awt.Color(255, 255, 255));
        btnNavSavedProperties.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pnlSidebar.add(btnNavSavedProperties);
        btnNavSavedProperties.setBounds(0, 200, 220, 40);

        btnNavLogout.setText("Logout");
        btnNavLogout.setBackground(new java.awt.Color(60, 110, 113));
        btnNavLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLogout.addActionListener(this::btnNavLogoutActionPerformed);
        pnlSidebar.add(btnNavLogout);
        btnNavLogout.setBounds(0, 260, 220, 40);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 220, 800);

        lblDashboardHeader.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDashboardHeader.setText("Saved Properties");
        getContentPane().add(lblDashboardHeader);
        lblDashboardHeader.setBounds(250, 20, 250, 40);

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblWelcome.setText("Welcome, User");
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(lblWelcome);
        lblWelcome.setBounds(1030, 20, 200, 40);

        scrollPaneApps.setBorder(null);
        scrollPaneApps.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        pnlGrid.setBackground(new java.awt.Color(245, 247, 250));
        pnlGrid.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 20));
        scrollPaneApps.setViewportView(pnlGrid);

        getContentPane().add(scrollPaneApps);
        scrollPaneApps.setBounds(250, 80, 1000, 660);

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
    private javax.swing.JButton btnNavDashboard;
    private javax.swing.JButton btnNavLogout;
    private javax.swing.JButton btnNavMyApplications;
    private javax.swing.JButton btnNavPropertyRatings;
    private javax.swing.JButton btnNavSavedProperties;
    private javax.swing.JLabel lblDashboardHeader;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel pnlGrid;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JScrollPane scrollPaneApps;
    // End of variables declaration//GEN-END:variables
}

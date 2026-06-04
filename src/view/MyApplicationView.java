package view;

import Controller.ApplicationController;
import Model.RentalApplication;
import smartrent.SessionService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MyApplicationView extends javax.swing.JFrame {

    private ApplicationController applicationController;

    public MyApplicationView() {
        initComponents();
        applicationController = new ApplicationController();
        applicationController.initMyApplicationsView(this);
    }

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
        scrollPaneApps = new javax.swing.JScrollPane();
        pnlAppsList = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartRent - My Applications");
        setBackground(new java.awt.Color(245, 247, 250));
        setPreferredSize(new java.awt.Dimension(1024, 768));
        getContentPane().setLayout(null);

        pnlSidebar.setBackground(new java.awt.Color(60, 110, 113));
        pnlSidebar.setLayout(null);

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("SmartRent");
        pnlSidebar.add(lblLogo);
        lblLogo.setBounds(20, 20, 160, 40);

        btnNavDashboard.setBackground(new java.awt.Color(60, 110, 113));
        btnNavDashboard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnNavDashboard.setText("Dashboard");
        btnNavDashboard.setBorderPainted(false);
        btnNavDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavDashboard.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavDashboard.addActionListener(this::btnNavDashboardActionPerformed);
        pnlSidebar.add(btnNavDashboard);
        btnNavDashboard.setBounds(0, 80, 200, 40);

        btnNavMyApplications.setBackground(new java.awt.Color(80, 128, 128));
        btnNavMyApplications.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavMyApplications.setForeground(new java.awt.Color(255, 255, 255));
        btnNavMyApplications.setText("My Applications");
        btnNavMyApplications.setBorderPainted(false);
        btnNavMyApplications.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavMyApplications.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavMyApplications.addActionListener(this::btnNavMyApplicationsActionPerformed);
        pnlSidebar.add(btnNavMyApplications);
        btnNavMyApplications.setBounds(0, 120, 200, 40);

        btnNavPropertyRatings.setBackground(new java.awt.Color(60, 110, 113));
        btnNavPropertyRatings.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavPropertyRatings.setForeground(new java.awt.Color(255, 255, 255));
        btnNavPropertyRatings.setText("Property Ratings");
        btnNavPropertyRatings.setBorderPainted(false);
        btnNavPropertyRatings.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavPropertyRatings.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavPropertyRatings.addActionListener(this::btnNavPropertyRatingsActionPerformed);
        pnlSidebar.add(btnNavPropertyRatings);
        btnNavPropertyRatings.setBounds(0, 160, 200, 40);

        btnNavSavedProperties.setText("Saved Properties");
        btnNavSavedProperties.setBackground(new java.awt.Color(60, 110, 113));
        btnNavSavedProperties.setForeground(new java.awt.Color(255, 255, 255));
        btnNavSavedProperties.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavSavedProperties.setBorderPainted(false);
        btnNavSavedProperties.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavSavedProperties.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavSavedProperties.addActionListener(this::btnNavSavedPropertiesActionPerformed);
        pnlSidebar.add(btnNavSavedProperties);
        btnNavSavedProperties.setBounds(0, 200, 200, 40);

        btnNavLogout.setText("Logout");
        btnNavLogout.setBackground(new java.awt.Color(60, 110, 113));
        btnNavLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLogout.setBorderPainted(false);
        btnNavLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavLogout.setMargin(new java.awt.Insets(2, 20, 2, 14));
        btnNavLogout.addActionListener(this::btnNavLogoutActionPerformed);
        pnlSidebar.add(btnNavLogout);
        btnNavLogout.setBounds(0, 260, 200, 40);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 200, 768);

        lblDashboardHeader.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDashboardHeader.setText("My Applications");
        getContentPane().add(lblDashboardHeader);
        lblDashboardHeader.setBounds(230, 20, 250, 40);

        scrollPaneApps.setBorder(null);

        pnlAppsList.setBackground(new java.awt.Color(245, 247, 250));
        pnlAppsList.setLayout(new javax.swing.BoxLayout(pnlAppsList, javax.swing.BoxLayout.Y_AXIS));
        scrollPaneApps.setViewportView(pnlAppsList);

        getContentPane().add(scrollPaneApps);
        scrollPaneApps.setBounds(230, 80, 750, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNavLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavLogoutActionPerformed
        applicationController.logout(this);
    }//GEN-LAST:event_btnNavLogoutActionPerformed

    private void btnNavDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavDashboardActionPerformed
        applicationController.navigateToDashboard(this);
    }//GEN-LAST:event_btnNavDashboardActionPerformed

    private void btnNavPropertyRatingsActionPerformed(java.awt.event.ActionEvent evt) {
        applicationController.navigateToPropertyRatings(this);
    }

    private void btnNavSavedPropertiesActionPerformed(java.awt.event.ActionEvent evt) {
        applicationController.navigateToSavedProperties(this);
    }

    private void btnNavMyApplicationsActionPerformed(java.awt.event.ActionEvent evt) {
        // Already on this page
    }

    public javax.swing.JScrollPane getScrollPaneApps() { return scrollPaneApps; }
    public javax.swing.JPanel getPnlAppsList() { return pnlAppsList; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNavDashboard;
    private javax.swing.JButton btnNavLogout;
    private javax.swing.JButton btnNavMyApplications;
    private javax.swing.JButton btnNavPropertyRatings;
    private javax.swing.JButton btnNavSavedProperties;
    private javax.swing.JLabel lblDashboardHeader;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPanel pnlAppsList;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JScrollPane scrollPaneApps;
    // End of variables declaration//GEN-END:variables
}

package view;

import Controller.ApplicationController;
import Model.RentalApplication;
import Controller.SessionService;
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
        lblLogo.setBackground(new java.awt.Color(209, 232, 237));
        lblLogo.setOpaque(true);
        btnNavDashboard = new javax.swing.JButton();
        btnNavMyApplications = new javax.swing.JButton();
        btnNavPropertyRatings = new javax.swing.JButton();
        btnNavSavedProperties = new javax.swing.JButton();
        btnNavLogout = new javax.swing.JButton();
        lblDashboardHeader = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        pnlHeader = new javax.swing.JPanel();
        lblColProp = new javax.swing.JLabel();
        lblColDate = new javax.swing.JLabel();
        lblColStatus = new javax.swing.JLabel();
        lblColActions = new javax.swing.JLabel();
        scrollPaneApps = new javax.swing.JScrollPane();
        pnlAppsList = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartRent - My Applications");
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

        btnNavMyApplications.setBackground(new java.awt.Color(80, 128, 128));
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

        lblDashboardHeader.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDashboardHeader.setText("My Applications");
        getContentPane().add(lblDashboardHeader);
        lblDashboardHeader.setBounds(230, 20, 250, 40);

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblWelcome.setText("Welcome, User");
        getContentPane().add(lblWelcome);
        lblWelcome.setBounds(1050, 20, 200, 40);

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setLayout(null);

        lblColProp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColProp.setForeground(new java.awt.Color(120, 130, 140));
        lblColProp.setText("Property Name");
        pnlHeader.add(lblColProp);
        lblColProp.setBounds(30, 12, 250, 20);

        lblColDate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColDate.setForeground(new java.awt.Color(120, 130, 140));
        lblColDate.setText("Submission Date");
        pnlHeader.add(lblColDate);
        lblColDate.setBounds(330, 12, 150, 20);

        lblColStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColStatus.setForeground(new java.awt.Color(120, 130, 140));
        lblColStatus.setText("Status");
        pnlHeader.add(lblColStatus);
        lblColStatus.setBounds(510, 12, 150, 20);

        lblColActions.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColActions.setForeground(new java.awt.Color(120, 130, 140));
        lblColActions.setText("Actions");
        pnlHeader.add(lblColActions);
        lblColActions.setBounds(790, 12, 150, 20);

        getContentPane().add(pnlHeader);
        pnlHeader.setBounds(230, 80, 1000, 45);

        scrollPaneApps.setBorder(null);

        pnlAppsList.setBackground(new java.awt.Color(255, 255, 255));
        pnlAppsList.setLayout(new javax.swing.BoxLayout(pnlAppsList, javax.swing.BoxLayout.Y_AXIS));
        scrollPaneApps.setViewportView(pnlAppsList);

        getContentPane().add(scrollPaneApps);
        scrollPaneApps.setBounds(230, 125, 1000, 600);

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnNavDashboard;
    public javax.swing.JButton btnNavLogout;
    public javax.swing.JButton btnNavMyApplications;
    public javax.swing.JButton btnNavPropertyRatings;
    public javax.swing.JButton btnNavSavedProperties;
    public javax.swing.JLabel lblColActions;
    public javax.swing.JLabel lblColDate;
    public javax.swing.JLabel lblColProp;
    public javax.swing.JLabel lblColStatus;
    public javax.swing.JLabel lblDashboardHeader;
    public javax.swing.JLabel lblLogo;
    public javax.swing.JLabel lblWelcome;
    public javax.swing.JPanel pnlAppsList;
    public javax.swing.JPanel pnlHeader;
    public javax.swing.JPanel pnlSidebar;
    public javax.swing.JScrollPane scrollPaneApps;
    // End of variables declaration//GEN-END:variables
}

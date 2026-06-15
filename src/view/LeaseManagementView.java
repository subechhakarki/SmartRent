package view;

import Controller.LeaseController;
import Model.Lease;
import Model.User;
import Controller.SessionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

public class LeaseManagementView extends javax.swing.JFrame {

    private LeaseController leaseController;
    public javax.swing.JButton btnPrev;
    public javax.swing.JButton btnNext;
    public javax.swing.JPanel pnlPageNumbers;

    public LeaseManagementView() {
        initComponents();
        
        btnPrev = new javax.swing.JButton("Previous");
        btnNext = new javax.swing.JButton("Next");
        pnlPageNumbers = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        pnlPageNumbers.setBackground(java.awt.Color.WHITE);

        leaseController = new LeaseController();
        leaseController.initLeaseManagementView(this);
    }

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
        pnlActiveLeases = new javax.swing.JPanel();
        lblActiveLeases = new javax.swing.JLabel();
        pnlExpiredLeases = new javax.swing.JPanel();
        lblExpiredLeases = new javax.swing.JLabel();
        pnlTerminatedLeases = new javax.swing.JPanel();
        lblTerminatedLeases = new javax.swing.JLabel();
        pnlCard = new javax.swing.JPanel();
        lblLeaseManagementTitle = new javax.swing.JLabel();
        pnlTableHeader = new javax.swing.JPanel();
        lblColTenant = new javax.swing.JLabel();
        lblColProperty = new javax.swing.JLabel();
        lblColStartDate = new javax.swing.JLabel();
        lblColEndDate = new javax.swing.JLabel();
        lblColRentAmount = new javax.swing.JLabel();
        lblColStatus = new javax.swing.JLabel();
        lblColActions = new javax.swing.JLabel();
        scrollTable = new javax.swing.JScrollPane();
        pnlTableBody = new javax.swing.JPanel();
        lblEntriesSummary = new javax.swing.JLabel();
        pnlPagination = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartRent - Lease Management");
        setResizable(false);
        getContentPane().setLayout(null);

        pnlSidebar.setBackground(new java.awt.Color(60, 110, 113));
        pnlSidebar.setLayout(null);

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("");
        pnlSidebar.add(lblLogo);
        lblLogo.setBounds(0, 0, 200, 80);

        btnNavDashboard.setBackground(new java.awt.Color(60, 110, 113));
        btnNavDashboard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnNavDashboard.setText("Dashboard");
        btnNavDashboard.addActionListener(this::btnNavDashboardActionPerformed);
        btnNavDashboard.setBorderPainted(false);
        btnNavDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavDashboard.setMargin(new java.awt.Insets(2, 20, 2, 14));
        pnlSidebar.add(btnNavDashboard);
        btnNavDashboard.setBounds(0, 100, 200, 40);

        btnNavMyProperties.setBackground(new java.awt.Color(60, 110, 113));
        btnNavMyProperties.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavMyProperties.setForeground(new java.awt.Color(255, 255, 255));
        btnNavMyProperties.setText("My Properties");
        btnNavMyProperties.addActionListener(this::btnNavMyPropertiesActionPerformed);
        btnNavMyProperties.setBorderPainted(false);
        btnNavMyProperties.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavMyProperties.setMargin(new java.awt.Insets(2, 20, 2, 14));
        pnlSidebar.add(btnNavMyProperties);
        btnNavMyProperties.setBounds(0, 140, 200, 40);

        btnNavLeaseManagement.setBackground(new java.awt.Color(80, 128, 128));
        btnNavLeaseManagement.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLeaseManagement.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLeaseManagement.setText("Lease Management");
        btnNavLeaseManagement.addActionListener(this::btnNavLeaseManagementActionPerformed);
        btnNavLeaseManagement.setBorderPainted(false);
        btnNavLeaseManagement.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavLeaseManagement.setMargin(new java.awt.Insets(2, 20, 2, 14));
        pnlSidebar.add(btnNavLeaseManagement);
        btnNavLeaseManagement.setBounds(0, 180, 200, 40);

        btnNavLogout.setBackground(new java.awt.Color(60, 110, 113));
        btnNavLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLogout.setText("Logout");
        btnNavLogout.addActionListener(this::btnNavLogoutActionPerformed);
        btnNavLogout.setBorderPainted(false);
        btnNavLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavLogout.setMargin(new java.awt.Insets(2, 20, 2, 14));
        pnlSidebar.add(btnNavLogout);
        btnNavLogout.setBounds(0, 240, 200, 40);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 200, 800);

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setLayout(null);

        lblHeaderTitle.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblHeaderTitle.setForeground(new java.awt.Color(45, 55, 72));
        lblHeaderTitle.setText("Lease Management");
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

        pnlActiveLeases.setBackground(new java.awt.Color(43, 108, 176));
        pnlActiveLeases.setLayout(null);

        lblActiveLeases.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblActiveLeases.setForeground(new java.awt.Color(255, 255, 255));
        lblActiveLeases.setText("Active Leases: 0");
        pnlActiveLeases.add(lblActiveLeases);
        lblActiveLeases.setBounds(10, 10, 200, 50);

        getContentPane().add(pnlActiveLeases);
        pnlActiveLeases.setBounds(230, 90, 220, 70);

        pnlExpiredLeases.setBackground(new java.awt.Color(229, 62, 62));
        pnlExpiredLeases.setLayout(null);

        lblExpiredLeases.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblExpiredLeases.setForeground(new java.awt.Color(255, 255, 255));
        lblExpiredLeases.setText("Expired Leases: 0");
        pnlExpiredLeases.add(lblExpiredLeases);
        lblExpiredLeases.setBounds(10, 10, 200, 50);

        getContentPane().add(pnlExpiredLeases);
        pnlExpiredLeases.setBounds(470, 90, 220, 70);

        pnlTerminatedLeases.setBackground(new java.awt.Color(56, 161, 105));
        pnlTerminatedLeases.setLayout(null);

        lblTerminatedLeases.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTerminatedLeases.setForeground(new java.awt.Color(255, 255, 255));
        lblTerminatedLeases.setText("Terminated Leases: 0");
        pnlTerminatedLeases.add(lblTerminatedLeases);
        lblTerminatedLeases.setBounds(10, 10, 200, 50);

        getContentPane().add(pnlTerminatedLeases);
        pnlTerminatedLeases.setBounds(710, 90, 220, 70);

        pnlCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard.setLayout(null);

        lblLeaseManagementTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblLeaseManagementTitle.setForeground(new java.awt.Color(45, 55, 72));
        lblLeaseManagementTitle.setText("Lease Management");
        pnlCard.add(lblLeaseManagementTitle);
        lblLeaseManagementTitle.setBounds(20, 20, 300, 30);

        pnlTableHeader.setBackground(new java.awt.Color(247, 250, 252));
        pnlTableHeader.setLayout(null);

        lblColTenant.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColTenant.setForeground(new java.awt.Color(74, 85, 104));
        lblColTenant.setText("Tenant");
        pnlTableHeader.add(lblColTenant);
        lblColTenant.setBounds(20, 10, 160, 20);

        lblColProperty.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColProperty.setForeground(new java.awt.Color(74, 85, 104));
        lblColProperty.setText("Property");
        pnlTableHeader.add(lblColProperty);
        lblColProperty.setBounds(180, 10, 180, 20);

        lblColStartDate.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColStartDate.setForeground(new java.awt.Color(74, 85, 104));
        lblColStartDate.setText("Start Date");
        pnlTableHeader.add(lblColStartDate);
        lblColStartDate.setBounds(360, 10, 110, 20);

        lblColEndDate.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColEndDate.setForeground(new java.awt.Color(74, 85, 104));
        lblColEndDate.setText("End Date");
        pnlTableHeader.add(lblColEndDate);
        lblColEndDate.setBounds(470, 10, 110, 20);

        lblColRentAmount.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColRentAmount.setForeground(new java.awt.Color(74, 85, 104));
        lblColRentAmount.setText("Rent Amount");
        pnlTableHeader.add(lblColRentAmount);
        lblColRentAmount.setBounds(580, 10, 120, 20);

        lblColStatus.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColStatus.setForeground(new java.awt.Color(74, 85, 104));
        lblColStatus.setText("Status");
        pnlTableHeader.add(lblColStatus);
        lblColStatus.setBounds(700, 10, 100, 20);

        lblColActions.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblColActions.setForeground(new java.awt.Color(74, 85, 104));
        lblColActions.setText("Actions");
        pnlTableHeader.add(lblColActions);
        lblColActions.setBounds(800, 10, 140, 20);

        pnlCard.add(pnlTableHeader);
        pnlTableHeader.setBounds(20, 80, 960, 40);

        scrollTable.setBorder(null);

        pnlTableBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlTableBody.setLayout(new javax.swing.BoxLayout(pnlTableBody, javax.swing.BoxLayout.Y_AXIS));
        scrollTable.setViewportView(pnlTableBody);

        pnlCard.add(scrollTable);
        scrollTable.setBounds(20, 120, 960, 370);

        lblEntriesSummary.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblEntriesSummary.setForeground(new java.awt.Color(113, 128, 150));
        lblEntriesSummary.setText("Showing 0 to 0 of 0 entries");
        pnlCard.add(lblEntriesSummary);
        lblEntriesSummary.setBounds(20, 505, 300, 30);

        pnlPagination.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlPaginationLayout = new javax.swing.GroupLayout(pnlPagination);
        pnlPagination.setLayout(pnlPaginationLayout);
        pnlPaginationLayout.setHorizontalGroup(
            pnlPaginationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        pnlPaginationLayout.setVerticalGroup(
            pnlPaginationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        pnlCard.add(pnlPagination);
        pnlPagination.setBounds(580, 500, 400, 40);

        getContentPane().add(pnlCard);
        pnlCard.setBounds(230, 180, 1020, 560);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNavDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavDashboardActionPerformed
        leaseController.navigateToDashboard(this);
    }//GEN-LAST:event_btnNavDashboardActionPerformed

    private void btnNavMyPropertiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavMyPropertiesActionPerformed
        leaseController.navigateToMyProperties(this);
    }//GEN-LAST:event_btnNavMyPropertiesActionPerformed

    private void btnNavLeaseManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavLeaseManagementActionPerformed
        // Already on Lease Management
    }//GEN-LAST:event_btnNavLeaseManagementActionPerformed

    private void btnNavLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavLogoutActionPerformed
        leaseController.logout(this);
    }//GEN-LAST:event_btnNavLogoutActionPerformed




    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnNavDashboard;
    public javax.swing.JButton btnNavLeaseManagement;
    public javax.swing.JButton btnNavLogout;
    public javax.swing.JButton btnNavMyProperties;
    public javax.swing.JLabel lblActiveLeases;
    public javax.swing.JLabel lblColActions;
    public javax.swing.JLabel lblColEndDate;
    public javax.swing.JLabel lblColProperty;
    public javax.swing.JLabel lblColRentAmount;
    public javax.swing.JLabel lblColStartDate;
    public javax.swing.JLabel lblColStatus;
    public javax.swing.JLabel lblColTenant;
    public javax.swing.JLabel lblEntriesSummary;
    public javax.swing.JLabel lblExpiredLeases;
    public javax.swing.JLabel lblHeaderTitle;
    public javax.swing.JLabel lblLeaseManagementTitle;
    public javax.swing.JLabel lblLogo;
    public javax.swing.JLabel lblTerminatedLeases;
    public javax.swing.JLabel lblWelcome;
    public javax.swing.JPanel pnlActiveLeases;
    public javax.swing.JPanel pnlCard;
    public javax.swing.JPanel pnlExpiredLeases;
    public javax.swing.JPanel pnlHeader;
    public javax.swing.JPanel pnlPagination;
    public javax.swing.JPanel pnlSidebar;
    public javax.swing.JPanel pnlTableBody;
    public javax.swing.JPanel pnlTableHeader;
    public javax.swing.JPanel pnlTerminatedLeases;
    public javax.swing.JScrollPane scrollTable;
    // End of variables declaration//GEN-END:variables
}

package view;

import Controller.ApplicationController;
import Model.Property;
import Model.User;
import smartrent.SessionService;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicationFormView extends javax.swing.JFrame {

    private ApplicationController applicationController;
    private int propertyId;
    private ApplicationFormStep2View step2View;

    public int getPropertyId() { return propertyId; }
    public ApplicationFormStep2View getStep2View() { return step2View; }
    public void setStep2View(ApplicationFormStep2View step2View) { this.step2View = step2View; }

    public ApplicationFormView(int propertyId) {
        this.propertyId = propertyId;
        initComponents();
        applicationController = new ApplicationController();
        applicationController.initApplicationForm(this, propertyId);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (step2View != null) {
                    step2View.dispose();
                }
            }
        });
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
        lblHeader = new javax.swing.JLabel();
        lblSteps = new javax.swing.JLabel();
        pnlContent = new javax.swing.JPanel();
        pnlPropertyCard = new javax.swing.JPanel();
        lblPropImage = new javax.swing.JLabel();
        lblPropTitle = new javax.swing.JLabel();
        lblPropLocation = new javax.swing.JLabel();
        lblPropPrice = new javax.swing.JLabel();
        lblPropDetails = new javax.swing.JLabel();
        lblFullName = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        lblDOB = new javax.swing.JLabel();
        txtDOB = new javax.swing.JTextField();
        lblPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        lblMoveInDate = new javax.swing.JLabel();
        txtMoveInDate = new javax.swing.JTextField();
        lblLeaseDuration = new javax.swing.JLabel();
        txtLeaseDuration = new javax.swing.JTextField();
        btnNext = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SmartRent - Submit Application");
        setBackground(new java.awt.Color(245, 247, 250));
        setPreferredSize(new java.awt.Dimension(1024, 768));
        getContentPane().setLayout(null);

        lblHeader.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblHeader.setText("Rental Application Form");
        getContentPane().add(lblHeader);
        lblHeader.setBounds(135, 20, 400, 40);

        lblSteps.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSteps.setText("<html><font color='#1E5CF0'><b>1. Personal Details</b></font> &gt; 2. Employment &amp; Income </html>");
        getContentPane().add(lblSteps);
        lblSteps.setBounds(135, 70, 600, 30);

        pnlContent.setBackground(new java.awt.Color(255, 255, 255));
        pnlContent.setLayout(null);

        pnlPropertyCard.setBackground(new java.awt.Color(243, 246, 248));
        pnlPropertyCard.setLayout(null);

        lblPropImage.setText("Image");
        lblPropImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPropImage.setBackground(new java.awt.Color(210, 210, 210));
        lblPropImage.setOpaque(true);
        pnlPropertyCard.add(lblPropImage);
        lblPropImage.setBounds(10, 10, 150, 100);

        lblPropTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPropTitle.setText("Property Title");
        pnlPropertyCard.add(lblPropTitle);
        lblPropTitle.setBounds(170, 10, 300, 25);

        lblPropLocation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPropLocation.setForeground(new java.awt.Color(102, 102, 102));
        lblPropLocation.setText("Location");
        pnlPropertyCard.add(lblPropLocation);
        lblPropLocation.setBounds(170, 40, 200, 20);

        lblPropPrice.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblPropPrice.setText("Rs. 0/mo");
        pnlPropertyCard.add(lblPropPrice);
        lblPropPrice.setBounds(170, 70, 200, 25);

        lblPropDetails.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPropDetails.setForeground(new java.awt.Color(102, 102, 102));
        lblPropDetails.setText("Details");
        pnlPropertyCard.add(lblPropDetails);
        lblPropDetails.setBounds(380, 40, 200, 20);

        pnlContent.add(pnlPropertyCard);
        pnlPropertyCard.setBounds(20, 20, 710, 120);

        lblFullName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFullName.setText("Full Name");
        pnlContent.add(lblFullName);
        lblFullName.setBounds(20, 150, 340, 20);
        pnlContent.add(txtFullName);
        txtFullName.setBounds(20, 175, 340, 35);

        lblDOB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDOB.setText("Date of Birth");
        pnlContent.add(lblDOB);
        lblDOB.setBounds(380, 150, 340, 20);
        pnlContent.add(txtDOB);
        txtDOB.setBounds(380, 175, 340, 35);

        lblPhone.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPhone.setText("Phone Number");
        pnlContent.add(lblPhone);
        lblPhone.setBounds(20, 220, 340, 20);
        pnlContent.add(txtPhone);
        txtPhone.setBounds(20, 245, 340, 35);

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmail.setText("Email Address");
        pnlContent.add(lblEmail);
        lblEmail.setBounds(380, 220, 340, 20);
        pnlContent.add(txtEmail);
        txtEmail.setBounds(380, 245, 340, 35);

        lblAddress.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAddress.setText("Current Address");
        pnlContent.add(lblAddress);
        lblAddress.setBounds(20, 290, 700, 20);
        pnlContent.add(txtAddress);
        txtAddress.setBounds(20, 315, 700, 35);

        lblMoveInDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMoveInDate.setText("Desired Move-in Date");
        pnlContent.add(lblMoveInDate);
        lblMoveInDate.setBounds(20, 360, 340, 20);
        pnlContent.add(txtMoveInDate);
        txtMoveInDate.setBounds(20, 385, 340, 35);

        lblLeaseDuration.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblLeaseDuration.setText("Preferred Lease Duration (months)");
        pnlContent.add(lblLeaseDuration);
        lblLeaseDuration.setBounds(380, 360, 340, 20);
        pnlContent.add(txtLeaseDuration);
        txtLeaseDuration.setBounds(380, 385, 340, 35);

        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNext.setText("Next");
        btnNext.setBackground(new java.awt.Color(30, 92, 240));
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.addActionListener(this::btnNextActionPerformed);
        pnlContent.add(btnNext);
        btnNext.setBounds(20, 440, 120, 40);

        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.setBackground(new java.awt.Color(210, 210, 210));
        btnCancel.setForeground(new java.awt.Color(51, 51, 51));
        btnCancel.addActionListener(this::btnCancelActionPerformed);
        pnlContent.add(btnCancel);
        btnCancel.setBounds(150, 440, 120, 40);

        getContentPane().add(pnlContent);
        pnlContent.setBounds(135, 110, 750, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        applicationController.nextStep(this, propertyId);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        if (step2View != null) {
            step2View.dispose();
        }
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnNavDashboardActionPerformed(java.awt.event.ActionEvent evt) {
        applicationController.navigateToDashboard(this);
    }
    
    private void btnNavMyApplicationsActionPerformed(java.awt.event.ActionEvent evt) {
        applicationController.navigateToMyApplications(this);
    }
    
    private void btnNavPropertyRatingsActionPerformed(java.awt.event.ActionEvent evt) {
        applicationController.navigateToPropertyRatings(this);
    }
    
    private void btnNavSavedPropertiesActionPerformed(java.awt.event.ActionEvent evt) {
        applicationController.navigateToSavedProperties(this);
    }
    
    private void btnNavLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        applicationController.logout(this);
    }

    public javax.swing.JTextField getTxtFullName() { return txtFullName; }
    public javax.swing.JTextField getTxtDOB() { return txtDOB; }
    public javax.swing.JTextField getTxtPhone() { return txtPhone; }
    public javax.swing.JTextField getTxtEmail() { return txtEmail; }
    public javax.swing.JTextField getTxtAddress() { return txtAddress; }
    public javax.swing.JTextField getTxtMoveInDate() { return txtMoveInDate; }
    public javax.swing.JTextField getTxtLeaseDuration() { return txtLeaseDuration; }

    public javax.swing.JLabel getLblPropTitle() { return lblPropTitle; }
    public javax.swing.JLabel getLblPropLocation() { return lblPropLocation; }
    public javax.swing.JLabel getLblPropPrice() { return lblPropPrice; }
    public javax.swing.JLabel getLblPropDetails() { return lblPropDetails; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnNavDashboard;
    private javax.swing.JButton btnNavLogout;
    private javax.swing.JButton btnNavMyApplications;
    private javax.swing.JButton btnNavPropertyRatings;
    private javax.swing.JButton btnNavSavedProperties;
    private javax.swing.JButton btnNext;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblLeaseDuration;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMoveInDate;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblPropDetails;
    private javax.swing.JLabel lblPropImage;
    private javax.swing.JLabel lblPropLocation;
    private javax.swing.JLabel lblPropPrice;
    private javax.swing.JLabel lblPropTitle;
    private javax.swing.JLabel lblSteps;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlPropertyCard;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtLeaseDuration;
    private javax.swing.JTextField txtMoveInDate;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}

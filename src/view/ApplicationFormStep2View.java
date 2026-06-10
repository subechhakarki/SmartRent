package view;

import Controller.ApplicationController;
import Model.Property;
import smartrent.SessionService;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ApplicationFormStep2View extends javax.swing.JFrame {

    private ApplicationController applicationController;
    private int propertyId;
    private ApplicationFormView step1View;

    public ApplicationFormView getStep1View() { return step1View; }

    public ApplicationFormStep2View(ApplicationFormView step1View) {
        this.step1View = step1View;
        this.propertyId = step1View.getPropertyId();
        initComponents();
        applicationController = new ApplicationController();
        applicationController.initStep2(this, propertyId);
        btnBrowse.addActionListener(e -> applicationController.browseProof(this));
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (step1View != null && step1View.isVisible() == false) {
                    step1View.dispose();
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
        lblEmployer = new javax.swing.JLabel();
        txtEmployer = new javax.swing.JTextField();
        lblJobTitle = new javax.swing.JLabel();
        txtJobTitle = new javax.swing.JTextField();
        lblStartDate = new javax.swing.JLabel();
        txtStartDate = new javax.swing.JTextField();
        lblEmployerPhone = new javax.swing.JLabel();
        txtEmployerPhone = new javax.swing.JTextField();
        lblSupervisorName = new javax.swing.JLabel();
        txtSupervisorName = new javax.swing.JTextField();
        lblSupervisorEmail = new javax.swing.JLabel();
        txtSupervisorEmail = new javax.swing.JTextField();
        lblIncome = new javax.swing.JLabel();
        txtIncome = new javax.swing.JTextField();
        lblOtherIncome = new javax.swing.JLabel();
        txtOtherIncome = new javax.swing.JTextField();
        lblProof = new javax.swing.JLabel();
        txtProof = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        lblProofHint = new javax.swing.JLabel();
        lblYears = new javax.swing.JLabel();
        scrollPaneYears = new javax.swing.JScrollPane();
        txtYears = new javax.swing.JTextArea();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SmartRent - Submit Application Step 2");
        setBackground(new java.awt.Color(245, 247, 250));
        setPreferredSize(new java.awt.Dimension(1024, 768));
        getContentPane().setLayout(null);

        lblHeader.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblHeader.setText("Rental Application Form");
        getContentPane().add(lblHeader);
        lblHeader.setBounds(135, 20, 400, 40);

        lblSteps.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSteps.setText("<html>1. Personal Details &gt; <font color='#1E5CF0'><b>2. Employment &amp; Income</b></font> &gt; 3. Reference &amp; Documents</html>");
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

        lblEmployer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmployer.setText("Current Employer");
        pnlContent.add(lblEmployer);
        lblEmployer.setBounds(20, 150, 220, 20);
        pnlContent.add(txtEmployer);
        txtEmployer.setBounds(20, 175, 220, 35);

        lblJobTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblJobTitle.setText("Job Title");
        pnlContent.add(lblJobTitle);
        lblJobTitle.setBounds(260, 150, 220, 20);
        pnlContent.add(txtJobTitle);
        txtJobTitle.setBounds(260, 175, 220, 35);

        lblStartDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStartDate.setText("Start Date");
        pnlContent.add(lblStartDate);
        lblStartDate.setBounds(500, 150, 220, 20);
        pnlContent.add(txtStartDate);
        txtStartDate.setBounds(500, 175, 220, 35);

        lblEmployerPhone.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmployerPhone.setText("Employer Phone");
        pnlContent.add(lblEmployerPhone);
        lblEmployerPhone.setBounds(20, 220, 220, 20);
        pnlContent.add(txtEmployerPhone);
        txtEmployerPhone.setBounds(20, 245, 220, 35);

        lblSupervisorName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSupervisorName.setText("Supervisor Name");
        pnlContent.add(lblSupervisorName);
        lblSupervisorName.setBounds(260, 220, 220, 20);
        pnlContent.add(txtSupervisorName);
        txtSupervisorName.setBounds(260, 245, 220, 35);

        lblSupervisorEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSupervisorEmail.setText("Supervisor Email");
        pnlContent.add(lblSupervisorEmail);
        lblSupervisorEmail.setBounds(500, 220, 220, 20);
        pnlContent.add(txtSupervisorEmail);
        txtSupervisorEmail.setBounds(500, 245, 220, 35);

        lblIncome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblIncome.setText("Gross Monthly Income");
        pnlContent.add(lblIncome);
        lblIncome.setBounds(20, 290, 220, 20);
        pnlContent.add(txtIncome);
        txtIncome.setBounds(20, 315, 220, 35);

        lblOtherIncome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOtherIncome.setText("Other Sources of Income (e.g., side hustle)");
        pnlContent.add(lblOtherIncome);
        lblOtherIncome.setBounds(260, 290, 460, 20);
        pnlContent.add(txtOtherIncome);
        txtOtherIncome.setBounds(260, 315, 460, 35);

        lblProof.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblProof.setText("Proof of Income");
        pnlContent.add(lblProof);
        lblProof.setBounds(20, 360, 200, 20);
        pnlContent.add(txtProof);
        txtProof.setBounds(20, 385, 160, 35);

        btnBrowse.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBrowse.setText("Browse");
        btnBrowse.setBackground(new java.awt.Color(210, 210, 210));
        btnBrowse.setForeground(new java.awt.Color(51, 51, 51));
        pnlContent.add(btnBrowse);
        btnBrowse.setBounds(180, 385, 90, 35);

        lblProofHint.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblProofHint.setForeground(new java.awt.Color(102, 102, 102));
        lblProofHint.setText("Paystubs, Bank Statements");
        pnlContent.add(lblProofHint);
        lblProofHint.setBounds(20, 425, 250, 20);

        lblYears.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblYears.setText("Years in Current Field");
        pnlContent.add(lblYears);
        lblYears.setBounds(300, 360, 420, 20);

        txtYears.setColumns(20);
        txtYears.setRows(5);
        scrollPaneYears.setViewportView(txtYears);

        pnlContent.add(scrollPaneYears);
        scrollPaneYears.setBounds(300, 385, 420, 80);

        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack.setText("Back");
        btnBack.setBackground(new java.awt.Color(210, 210, 210));
        btnBack.setForeground(new java.awt.Color(51, 51, 51));
        btnBack.addActionListener(this::btnBackActionPerformed);
        pnlContent.add(btnBack);
        btnBack.setBounds(20, 520, 120, 40);

        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNext.setText("Next");
        btnNext.setBackground(new java.awt.Color(46, 134, 210));
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.addActionListener(this::btnNextActionPerformed);
        pnlContent.add(btnNext);
        btnNext.setBounds(390, 520, 120, 40);

        btnSubmit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSubmit.setText("Submit Application");
        btnSubmit.setBackground(new java.awt.Color(46, 134, 210));
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.addActionListener(this::btnSubmitActionPerformed);
        pnlContent.add(btnSubmit);
        btnSubmit.setBounds(520, 520, 200, 40);

        getContentPane().add(pnlContent);
        pnlContent.setBounds(135, 110, 750, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        applicationController.goBackToStep1(this, propertyId);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        JOptionPane.showMessageDialog(this, "Step 3 (Reference & Documents) coming soon!");
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        applicationController.submitApplicationFromStep2(this);
    }//GEN-LAST:event_btnSubmitActionPerformed

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

    public javax.swing.JTextField getTxtEmployer() { return txtEmployer; }
    public javax.swing.JTextField getTxtEmployerPhone() { return txtEmployerPhone; }
    public javax.swing.JTextField getTxtIncome() { return txtIncome; }
    public javax.swing.JTextField getTxtJobTitle() { return txtJobTitle; }
    public javax.swing.JTextField getTxtOtherIncome() { return txtOtherIncome; }
    public javax.swing.JTextField getTxtProof() { return txtProof; }
    public javax.swing.JTextField getTxtStartDate() { return txtStartDate; }
    public javax.swing.JTextField getTxtSupervisorEmail() { return txtSupervisorEmail; }
    public javax.swing.JTextField getTxtSupervisorName() { return txtSupervisorName; }
    public javax.swing.JTextArea getTxtYears() { return txtYears; }

    public javax.swing.JLabel getLblPropTitle() { return lblPropTitle; }
    public javax.swing.JLabel getLblPropLocation() { return lblPropLocation; }
    public javax.swing.JLabel getLblPropPrice() { return lblPropPrice; }
    public javax.swing.JLabel getLblPropDetails() { return lblPropDetails; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnNavDashboard;
    private javax.swing.JButton btnNavLogout;
    private javax.swing.JButton btnNavMyApplications;
    private javax.swing.JButton btnNavPropertyRatings;
    private javax.swing.JButton btnNavSavedProperties;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel lblEmployer;
    private javax.swing.JLabel lblEmployerPhone;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblIncome;
    private javax.swing.JLabel lblJobTitle;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblOtherIncome;
    private javax.swing.JLabel lblProof;
    private javax.swing.JLabel lblProofHint;
    private javax.swing.JLabel lblPropDetails;
    private javax.swing.JLabel lblPropImage;
    private javax.swing.JLabel lblPropLocation;
    private javax.swing.JLabel lblPropPrice;
    private javax.swing.JLabel lblPropTitle;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblSteps;
    private javax.swing.JLabel lblSupervisorEmail;
    private javax.swing.JLabel lblSupervisorName;
    private javax.swing.JLabel lblYears;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlPropertyCard;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JScrollPane scrollPaneYears;
    private javax.swing.JTextField txtEmployer;
    private javax.swing.JTextField txtEmployerPhone;
    private javax.swing.JTextField txtIncome;
    private javax.swing.JTextField txtJobTitle;
    private javax.swing.JTextField txtOtherIncome;
    private javax.swing.JTextField txtProof;
    private javax.swing.JTextField txtStartDate;
    private javax.swing.JTextField txtSupervisorEmail;
    private javax.swing.JTextField txtSupervisorName;
    private javax.swing.JTextArea txtYears;
    // End of variables declaration//GEN-END:variables
}

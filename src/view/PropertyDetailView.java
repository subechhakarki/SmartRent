package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

public class PropertyDetailView extends javax.swing.JFrame {

    public PropertyDetailView() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color(245, 247, 250));
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
        btnBack = new javax.swing.JButton();
        pnlImageContainer = new javax.swing.JPanel();
        lblMainImage = new javax.swing.JLabel();
        btnPrevImage = new javax.swing.JButton();
        btnNextImage = new javax.swing.JButton();
        lblImageCounter = new javax.swing.JLabel();
        thumbnail1 = new javax.swing.JLabel();
        thumbnail2 = new javax.swing.JLabel();
        thumbnail3 = new javax.swing.JLabel();
        thumbnail4 = new javax.swing.JLabel();
        pnlInfo = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblPropertyType = new javax.swing.JLabel();
        sep1 = new javax.swing.JSeparator();
        lblBedIcon = new javax.swing.JLabel();
        lblBedrooms = new javax.swing.JLabel();
        lblBathIcon = new javax.swing.JLabel();
        lblBathrooms = new javax.swing.JLabel();
        lblRent = new javax.swing.JLabel();
        lblDeposit = new javax.swing.JLabel();
        lblAvailableFrom = new javax.swing.JLabel();
        sep2 = new javax.swing.JSeparator();
        lblStatus = new javax.swing.JLabel();
        lblRating = new javax.swing.JLabel();
        sep3 = new javax.swing.JSeparator();
        btnApply = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        pnlExtraInfo = new javax.swing.JPanel();
        lblDescHeader = new javax.swing.JLabel();
        lblDescText = new javax.swing.JLabel();
        lblFeaturesHeader = new javax.swing.JLabel();
        lblFeatures = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartRent - Property Details");
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

        btnNavDashboard.setBackground(new java.awt.Color(60, 110, 113));
        btnNavDashboard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnNavDashboard.setText("Dashboard");
        btnNavDashboard.setBorderPainted(false);
        btnNavDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavDashboard.setMargin(new java.awt.Insets(2, 20, 2, 14));
        pnlSidebar.add(btnNavDashboard);
        btnNavDashboard.setBounds(0, 80, 220, 40);

        btnNavMyApplications.setBackground(new java.awt.Color(60, 110, 113));
        btnNavMyApplications.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavMyApplications.setForeground(new java.awt.Color(255, 255, 255));
        btnNavMyApplications.setText("My Applications");
        btnNavMyApplications.setBorderPainted(false);
        btnNavMyApplications.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavMyApplications.setMargin(new java.awt.Insets(2, 20, 2, 14));
        pnlSidebar.add(btnNavMyApplications);
        btnNavMyApplications.setBounds(0, 120, 220, 40);

        btnNavPropertyRatings.setBackground(new java.awt.Color(60, 110, 113));
        btnNavPropertyRatings.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavPropertyRatings.setForeground(new java.awt.Color(255, 255, 255));
        btnNavPropertyRatings.setText("Property Ratings");
        btnNavPropertyRatings.setBorderPainted(false);
        btnNavPropertyRatings.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavPropertyRatings.setMargin(new java.awt.Insets(2, 20, 2, 14));
        pnlSidebar.add(btnNavPropertyRatings);
        btnNavPropertyRatings.setBounds(0, 160, 220, 40);

        btnNavSavedProperties.setBackground(new java.awt.Color(60, 110, 113));
        btnNavSavedProperties.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavSavedProperties.setForeground(new java.awt.Color(255, 255, 255));
        btnNavSavedProperties.setText("Saved Properties");
        btnNavSavedProperties.setBorderPainted(false);
        btnNavSavedProperties.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavSavedProperties.setMargin(new java.awt.Insets(2, 20, 2, 14));
        pnlSidebar.add(btnNavSavedProperties);
        btnNavSavedProperties.setBounds(0, 200, 220, 40);

        btnNavLogout.setBackground(new java.awt.Color(60, 110, 113));
        btnNavLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLogout.setText("Logout");
        btnNavLogout.setBorderPainted(false);
        btnNavLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavLogout.setMargin(new java.awt.Insets(2, 20, 2, 14));
        pnlSidebar.add(btnNavLogout);
        btnNavLogout.setBounds(0, 260, 220, 40);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 220, 800);

        btnBack.setBackground(new java.awt.Color(245, 247, 250));
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBack.setForeground(new java.awt.Color(30, 92, 240));
        btnBack.setText("<- Back to Properties");
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        getContentPane().add(btnBack);
        btnBack.setBounds(240, 15, 200, 35);

        pnlImageContainer.setBackground(new java.awt.Color(30, 40, 55));
        pnlImageContainer.setLayout(null);

        lblMainImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMainImage.setText("<html><div style='text-align:center;color:white;padding-top:140px;font-size:16px;'>No Image Available</div></html>");
        pnlImageContainer.add(lblMainImage);
        lblMainImage.setBounds(0, 0, 600, 350);

        btnPrevImage.setBackground(new java.awt.Color(0, 0, 0));
        btnPrevImage.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPrevImage.setForeground(new java.awt.Color(255, 255, 255));
        btnPrevImage.setText("<");
        btnPrevImage.setBorderPainted(false);
        btnPrevImage.setFocusPainted(false);
        pnlImageContainer.add(btnPrevImage);
        btnPrevImage.setBounds(5, 150, 45, 50);

        btnNextImage.setBackground(new java.awt.Color(0, 0, 0));
        btnNextImage.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNextImage.setForeground(new java.awt.Color(255, 255, 255));
        btnNextImage.setText(">");
        btnNextImage.setBorderPainted(false);
        btnNextImage.setFocusPainted(false);
        pnlImageContainer.add(btnNextImage);
        btnNextImage.setBounds(550, 150, 45, 50);

        getContentPane().add(pnlImageContainer);
        pnlImageContainer.setBounds(250, 60, 600, 350);

        lblImageCounter.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblImageCounter.setForeground(new java.awt.Color(120, 120, 120));
        lblImageCounter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImageCounter.setText("0 / 0");
        getContentPane().add(lblImageCounter);
        lblImageCounter.setBounds(480, 415, 100, 25);

        thumbnail1.setBackground(new java.awt.Color(60, 60, 70));
        thumbnail1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        thumbnail1.setForeground(new java.awt.Color(255, 255, 255));
        thumbnail1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        thumbnail1.setText("Thumb");
        thumbnail1.setOpaque(true);
        getContentPane().add(thumbnail1);
        thumbnail1.setBounds(255, 420, 130, 75);

        thumbnail2.setBackground(new java.awt.Color(60, 60, 70));
        thumbnail2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        thumbnail2.setForeground(new java.awt.Color(255, 255, 255));
        thumbnail2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        thumbnail2.setText("Thumb");
        thumbnail2.setOpaque(true);
        getContentPane().add(thumbnail2);
        thumbnail2.setBounds(400, 420, 130, 75);

        thumbnail3.setBackground(new java.awt.Color(60, 60, 70));
        thumbnail3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        thumbnail3.setForeground(new java.awt.Color(255, 255, 255));
        thumbnail3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        thumbnail3.setText("Thumb");
        thumbnail3.setOpaque(true);
        getContentPane().add(thumbnail3);
        thumbnail3.setBounds(545, 420, 130, 75);

        thumbnail4.setBackground(new java.awt.Color(60, 60, 70));
        thumbnail4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        thumbnail4.setForeground(new java.awt.Color(255, 255, 255));
        thumbnail4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        thumbnail4.setText("Thumb");
        thumbnail4.setOpaque(true);
        getContentPane().add(thumbnail4);
        thumbnail4.setBounds(690, 420, 130, 75);

        pnlInfo.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfo.setLayout(null);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(33, 37, 41));
        lblTitle.setText("Property Title");
        pnlInfo.add(lblTitle);
        lblTitle.setBounds(20, 15, 340, 30);

        lblAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAddress.setForeground(new java.awt.Color(100, 100, 100));
        lblAddress.setText("Address");
        pnlInfo.add(lblAddress);
        lblAddress.setBounds(20, 50, 340, 22);

        lblPropertyType.setBackground(new java.awt.Color(60, 110, 113));
        lblPropertyType.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPropertyType.setForeground(new java.awt.Color(255, 255, 255));
        lblPropertyType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPropertyType.setText("Apartment");
        lblPropertyType.setOpaque(true);
        pnlInfo.add(lblPropertyType);
        lblPropertyType.setBounds(20, 82, 100, 26);
        pnlInfo.add(sep1);
        sep1.setBounds(20, 118, 340, 2);

        lblBedIcon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBedIcon.setText("Bed");
        pnlInfo.add(lblBedIcon);
        lblBedIcon.setBounds(20, 130, 25, 22);

        lblBedrooms.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBedrooms.setForeground(new java.awt.Color(74, 85, 104));
        lblBedrooms.setText("3 Bedrooms");
        pnlInfo.add(lblBedrooms);
        lblBedrooms.setBounds(45, 130, 140, 22);

        lblBathIcon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBathIcon.setText("Bath");
        pnlInfo.add(lblBathIcon);
        lblBathIcon.setBounds(195, 130, 25, 22);

        lblBathrooms.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBathrooms.setForeground(new java.awt.Color(74, 85, 104));
        lblBathrooms.setText("2 Bathrooms");
        pnlInfo.add(lblBathrooms);
        lblBathrooms.setBounds(220, 130, 140, 22);

        lblRent.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblRent.setForeground(new java.awt.Color(30, 92, 240));
        lblRent.setText("Rs. 45,000 / month");
        pnlInfo.add(lblRent);
        lblRent.setBounds(20, 168, 340, 35);

        lblDeposit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDeposit.setForeground(new java.awt.Color(100, 100, 100));
        lblDeposit.setText("Deposit: Rs. 90,000");
        pnlInfo.add(lblDeposit);
        lblDeposit.setBounds(20, 205, 340, 22);

        lblAvailableFrom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAvailableFrom.setForeground(new java.awt.Color(100, 100, 100));
        lblAvailableFrom.setText("Available from: Now");
        pnlInfo.add(lblAvailableFrom);
        lblAvailableFrom.setBounds(20, 235, 340, 22);
        pnlInfo.add(sep2);
        sep2.setBounds(20, 267, 340, 2);

        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(56, 161, 105));
        lblStatus.setText("AVAILABLE");
        pnlInfo.add(lblStatus);
        lblStatus.setBounds(20, 280, 150, 22);

        lblRating.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblRating.setForeground(new java.awt.Color(243, 156, 18));
        lblRating.setText("Rating");
        pnlInfo.add(lblRating);
        lblRating.setBounds(20, 308, 340, 22);
        pnlInfo.add(sep3);
        sep3.setBounds(20, 340, 340, 2);

        btnApply.setBackground(new java.awt.Color(30, 92, 240));
        btnApply.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnApply.setForeground(new java.awt.Color(255, 255, 255));
        btnApply.setText("Apply Now");
        btnApply.setBorderPainted(false);
        btnApply.setFocusPainted(false);
        pnlInfo.add(btnApply);
        btnApply.setBounds(20, 360, 165, 45);

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnSave.setForeground(new java.awt.Color(30, 92, 240));
        btnSave.setText("Save Property");
        btnSave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30, 92, 240), 2));
        btnSave.setFocusPainted(false);
        pnlInfo.add(btnSave);
        btnSave.setBounds(195, 360, 165, 45);

        getContentPane().add(pnlInfo);
        pnlInfo.setBounds(870, 60, 380, 435);

        pnlExtraInfo.setBackground(new java.awt.Color(255, 255, 255));
        pnlExtraInfo.setLayout(null);

        lblDescHeader.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDescHeader.setForeground(new java.awt.Color(33, 37, 41));
        lblDescHeader.setText("Property Description");
        pnlExtraInfo.add(lblDescHeader);
        lblDescHeader.setBounds(20, 15, 300, 28);

        lblDescText.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDescText.setText("<html><p style='color:#6c757d;font-size:12px;'>This property is listed on SmartRent. Contact the owner through the application process to learn more about this property, schedule a visit, and discuss rental terms.</p></html>");
        pnlExtraInfo.add(lblDescText);
        lblDescText.setBounds(20, 50, 960, 60);

        lblFeaturesHeader.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblFeaturesHeader.setForeground(new java.awt.Color(33, 37, 41));
        lblFeaturesHeader.setText("Key Features");
        pnlExtraInfo.add(lblFeaturesHeader);
        lblFeaturesHeader.setBounds(20, 115, 200, 25);

        lblFeatures.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblFeatures.setForeground(new java.awt.Color(56, 161, 105));
        lblFeatures.setText("<html>✓ Verified Listing &nbsp;&nbsp;&nbsp; ✓ 24/7 Security &nbsp;&nbsp;&nbsp; ✓ Parking Available &nbsp;&nbsp;&nbsp; ✓ Pet Friendly</html>");
        pnlExtraInfo.add(lblFeatures);
        lblFeatures.setBounds(20, 145, 960, 25);

        getContentPane().add(pnlExtraInfo);
        pnlExtraInfo.setBounds(250, 510, 1000, 220);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // ===== GETTERS =====
    public JButton getBtnNavDashboard() { return btnNavDashboard; }
    public JButton getBtnNavMyApplications() { return btnNavMyApplications; }
    public JButton getBtnNavPropertyRatings() { return btnNavPropertyRatings; }
    public JButton getBtnNavSavedProperties() { return btnNavSavedProperties; }
    public JButton getBtnNavLogout() { return btnNavLogout; }
    public JLabel getLblMainImage() { return lblMainImage; }
    public JButton getBtnPrevImage() { return btnPrevImage; }
    public JButton getBtnNextImage() { return btnNextImage; }
    public JLabel getLblImageCounter() { return lblImageCounter; }
    public JLabel getThumbnail1() { return thumbnail1; }
    public JLabel getThumbnail2() { return thumbnail2; }
    public JLabel getThumbnail3() { return thumbnail3; }
    public JLabel getThumbnail4() { return thumbnail4; }
    public JLabel getLblTitle() { return lblTitle; }
    public JLabel getLblAddress() { return lblAddress; }
    public JLabel getLblPropertyType() { return lblPropertyType; }
    public JLabel getLblBedrooms() { return lblBedrooms; }
    public JLabel getLblBathrooms() { return lblBathrooms; }
    public JLabel getLblRent() { return lblRent; }
    public JLabel getLblDeposit() { return lblDeposit; }
    public JLabel getLblAvailableFrom() { return lblAvailableFrom; }
    public JLabel getLblStatus() { return lblStatus; }
    public JLabel getLblRating() { return lblRating; }
    public JButton getBtnApply() { return btnApply; }
    public JButton getBtnSave() { return btnSave; }
    public JButton getBtnBack() { return btnBack; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnNavDashboard;
    private javax.swing.JButton btnNavLogout;
    private javax.swing.JButton btnNavMyApplications;
    private javax.swing.JButton btnNavPropertyRatings;
    private javax.swing.JButton btnNavSavedProperties;
    private javax.swing.JButton btnNextImage;
    private javax.swing.JButton btnPrevImage;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblAvailableFrom;
    private javax.swing.JLabel lblBathIcon;
    private javax.swing.JLabel lblBathrooms;
    private javax.swing.JLabel lblBedIcon;
    private javax.swing.JLabel lblBedrooms;
    private javax.swing.JLabel lblDeposit;
    private javax.swing.JLabel lblDescHeader;
    private javax.swing.JLabel lblDescText;
    private javax.swing.JLabel lblFeatures;
    private javax.swing.JLabel lblFeaturesHeader;
    private javax.swing.JLabel lblImageCounter;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMainImage;
    private javax.swing.JLabel lblPropertyType;
    private javax.swing.JLabel lblRating;
    private javax.swing.JLabel lblRent;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlExtraInfo;
    private javax.swing.JPanel pnlImageContainer;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JSeparator sep1;
    private javax.swing.JSeparator sep2;
    private javax.swing.JSeparator sep3;
    private javax.swing.JLabel thumbnail1;
    private javax.swing.JLabel thumbnail2;
    private javax.swing.JLabel thumbnail3;
    private javax.swing.JLabel thumbnail4;
    // End of variables declaration//GEN-END:variables
}

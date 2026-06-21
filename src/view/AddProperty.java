package view;

import Controller.PropertyController;
import java.util.ArrayList;
import java.util.List;

public class AddProperty extends javax.swing.JFrame {

    private PropertyController propertyController;
    private List<String> selectedImagePaths;
    private int loadedPropertyId = -1;

    public AddProperty() {
        initComponents();
        propertyController = new PropertyController();
        selectedImagePaths = new ArrayList<>();
        propertyController.initAddProperty(this, loadedPropertyId);
    }

    public AddProperty(int propertyId) {
        initComponents();
        propertyController = new PropertyController();
        selectedImagePaths = new ArrayList<>();
        this.loadedPropertyId = propertyId;
        propertyController.initAddProperty(this, loadedPropertyId);
    }

    // Getters and Setters for passive state
    public int getLoadedPropertyId() { return loadedPropertyId; }
    public void setLoadedPropertyId(int id) { this.loadedPropertyId = id; }
    public List<String> getSelectedImagePaths() { return selectedImagePaths; }

    // Getters for swing components
    


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
        pnlCard = new javax.swing.JPanel();
        lblAddPropertyTitle = new javax.swing.JLabel();
        pnlPropertyDetailsHeader = new javax.swing.JPanel();
        lblPropertyDetails = new javax.swing.JLabel();
        lblPropTitleLabel = new javax.swing.JLabel();
        txtPropTitle = new javax.swing.JTextField();
        lblLocationLabel = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        lblBedroomsLabel = new javax.swing.JLabel();
        txtBedrooms = new javax.swing.JTextField();
        lblBathroomsLabel = new javax.swing.JLabel();
        txtBathrooms = new javax.swing.JTextField();
        lblRentLabel = new javax.swing.JLabel();
        txtRent = new javax.swing.JTextField();
        lblAvailableFromLabel = new javax.swing.JLabel();
        txtAvailableFrom = new javax.swing.JTextField();
        lblDepositLabel = new javax.swing.JLabel();
        txtDeposit = new javax.swing.JTextField();
        lblPropertyTypeLabel = new javax.swing.JLabel();
        cmbPropertyType = new javax.swing.JComboBox();
        lblUploadPhotos = new javax.swing.JLabel();
        btnUploadImages = new javax.swing.JButton();
        lblImagesStatus = new javax.swing.JLabel();
        pnlImagesPreview = new javax.swing.JPanel();
        pnlSlot1 = new javax.swing.JPanel();
        lblPreview1 = new javax.swing.JLabel();
        btnRemove1 = new javax.swing.JButton();
        pnlSlot2 = new javax.swing.JPanel();
        lblPreview2 = new javax.swing.JLabel();
        btnRemove2 = new javax.swing.JButton();
        pnlSlot3 = new javax.swing.JPanel();
        lblPreview3 = new javax.swing.JLabel();
        btnRemove3 = new javax.swing.JButton();
        pnlSlot4 = new javax.swing.JPanel();
        lblPreview4 = new javax.swing.JLabel();
        btnRemove4 = new javax.swing.JButton();
        pnlSlot5 = new javax.swing.JPanel();
        lblPreview5 = new javax.swing.JLabel();
        btnRemove5 = new javax.swing.JButton();
        lblDescription = new javax.swing.JLabel();
        scrollDesc = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SmartRent - Add Property");
        setPreferredSize(new java.awt.Dimension(1280, 800));
        setResizable(false);
        getContentPane().setLayout(null);

        pnlSidebar.setBackground(new java.awt.Color(31, 97, 109));
        pnlSidebar.setLayout(null);

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("");
        pnlSidebar.add(lblLogo);
        lblLogo.setBounds(0, 0, 200, 80);


        btnNavDashboard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnNavDashboard.setText("  ⊞  Dashboard");
        btnNavDashboard.setBorderPainted(false);
        btnNavDashboard.setContentAreaFilled(false);
        btnNavDashboard.setFocusPainted(false);
        btnNavDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavDashboard.addActionListener(this::btnNavDashboardActionPerformed);
        pnlSidebar.add(btnNavDashboard);
        btnNavDashboard.setBounds(0, 100, 200, 40);

        btnNavMyProperties.setBackground(new java.awt.Color(44, 122, 135));
        btnNavMyProperties.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavMyProperties.setForeground(new java.awt.Color(255, 255, 255));
        btnNavMyProperties.setText("  🏢  My Properties");
        btnNavMyProperties.setBorderPainted(false);
        btnNavMyProperties.setFocusPainted(false);
        btnNavMyProperties.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavMyProperties.addActionListener(this::btnNavMyPropertiesActionPerformed);
        pnlSidebar.add(btnNavMyProperties);
        btnNavMyProperties.setBounds(0, 140, 200, 40);

        btnNavLeaseManagement.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLeaseManagement.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLeaseManagement.setText("  📄  Lease Management");
        btnNavLeaseManagement.setBorderPainted(false);
        btnNavLeaseManagement.setContentAreaFilled(false);
        btnNavLeaseManagement.setFocusPainted(false);
        btnNavLeaseManagement.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavLeaseManagement.addActionListener(this::btnNavLeaseManagementActionPerformed);
        pnlSidebar.add(btnNavLeaseManagement);
        btnNavLeaseManagement.setBounds(0, 180, 200, 40);

        btnNavLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNavLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnNavLogout.setText("  🚪  Logout");
        btnNavLogout.setBorderPainted(false);
        btnNavLogout.setContentAreaFilled(false);
        btnNavLogout.setFocusPainted(false);
        btnNavLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavLogout.addActionListener(this::btnNavLogoutActionPerformed);
        pnlSidebar.add(btnNavLogout);
        btnNavLogout.setBounds(0, 240, 200, 40);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 200, 800);

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setLayout(null);

        lblHeaderTitle.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblHeaderTitle.setForeground(new java.awt.Color(45, 55, 72));
        lblHeaderTitle.setText("My Properties");
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

        pnlCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard.setLayout(null);

        lblAddPropertyTitle.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblAddPropertyTitle.setForeground(new java.awt.Color(45, 55, 72));
        lblAddPropertyTitle.setText("Add Property");
        pnlCard.add(lblAddPropertyTitle);
        lblAddPropertyTitle.setBounds(30, 20, 300, 30);

        pnlPropertyDetailsHeader.setBackground(new java.awt.Color(243, 247, 250));
        pnlPropertyDetailsHeader.setLayout(null);

        lblPropertyDetails.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPropertyDetails.setForeground(new java.awt.Color(74, 85, 104));
        lblPropertyDetails.setText("Property Details");
        pnlPropertyDetailsHeader.add(lblPropertyDetails);
        lblPropertyDetails.setBounds(20, 5, 200, 20);

        pnlCard.add(pnlPropertyDetailsHeader);
        pnlPropertyDetailsHeader.setBounds(30, 60, 940, 30);

        lblPropTitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPropTitleLabel.setText("Property Title:");
        pnlCard.add(lblPropTitleLabel);
        lblPropTitleLabel.setBounds(30, 105, 120, 25);

        txtPropTitle.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        pnlCard.add(txtPropTitle);
        txtPropTitle.setBounds(160, 100, 310, 35);

        lblLocationLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblLocationLabel.setText("Location:");
        pnlCard.add(lblLocationLabel);
        lblLocationLabel.setBounds(510, 105, 100, 25);

        txtAddress.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        pnlCard.add(txtAddress);
        txtAddress.setBounds(620, 100, 350, 35);

        lblBedroomsLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblBedroomsLabel.setText("Bedrooms:");
        pnlCard.add(lblBedroomsLabel);
        lblBedroomsLabel.setBounds(30, 155, 120, 25);

        txtBedrooms.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        pnlCard.add(txtBedrooms);
        txtBedrooms.setBounds(160, 150, 310, 35);

        lblBathroomsLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblBathroomsLabel.setText("Bathrooms:");
        pnlCard.add(lblBathroomsLabel);
        lblBathroomsLabel.setBounds(510, 155, 100, 25);

        txtBathrooms.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        pnlCard.add(txtBathrooms);
        txtBathrooms.setBounds(620, 150, 350, 35);

        lblRentLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblRentLabel.setText("Rental Price (per mo):");
        pnlCard.add(lblRentLabel);
        lblRentLabel.setBounds(30, 205, 130, 25);

        txtRent.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        pnlCard.add(txtRent);
        txtRent.setBounds(160, 200, 310, 35);

        lblAvailableFromLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblAvailableFromLabel.setText("Available From:");
        pnlCard.add(lblAvailableFromLabel);
        lblAvailableFromLabel.setBounds(510, 205, 100, 25);

        txtAvailableFrom.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        pnlCard.add(txtAvailableFrom);
        txtAvailableFrom.setBounds(620, 200, 350, 35);

        lblDepositLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDepositLabel.setText("Deposit Amount (Rs.):");
        pnlCard.add(lblDepositLabel);
        lblDepositLabel.setBounds(30, 255, 130, 25);

        txtDeposit.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        pnlCard.add(txtDeposit);
        txtDeposit.setBounds(160, 250, 310, 35);

        lblPropertyTypeLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPropertyTypeLabel.setText("Property Type:");
        pnlCard.add(lblPropertyTypeLabel);
        lblPropertyTypeLabel.setBounds(510, 255, 100, 25);

        cmbPropertyType.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbPropertyType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "", "" }));
        pnlCard.add(cmbPropertyType);
        cmbPropertyType.setBounds(620, 250, 350, 35);

        lblUploadPhotos.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblUploadPhotos.setText("Upload Photos:");
        pnlCard.add(lblUploadPhotos);
        lblUploadPhotos.setBounds(30, 300, 200, 25);

        btnUploadImages.setBackground(new java.awt.Color(43, 108, 176));
        btnUploadImages.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnUploadImages.setForeground(new java.awt.Color(255, 255, 255));
        btnUploadImages.setText("Upload Images");
        btnUploadImages.setBorderPainted(false);
        btnUploadImages.setFocusPainted(false);
        btnUploadImages.addActionListener(this::btnUploadImagesActionPerformed);
        pnlCard.add(btnUploadImages);
        btnUploadImages.setBounds(30, 330, 140, 35);

        lblImagesStatus.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblImagesStatus.setForeground(new java.awt.Color(128, 128, 128));
        lblImagesStatus.setText("0 Images Selected");
        pnlCard.add(lblImagesStatus);
        lblImagesStatus.setBounds(30, 370, 140, 20);

        pnlImagesPreview.setBackground(new java.awt.Color(255, 255, 255));
        pnlImagesPreview.setLayout(null);

        pnlSlot1.setBackground(new java.awt.Color(255, 255, 255));
        pnlSlot1.setLayout(null);

        lblPreview1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPreview1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlSlot1.add(lblPreview1);
        lblPreview1.setBounds(0, 0, 140, 80);

        btnRemove1.setBackground(new java.awt.Color(229, 62, 62));
        btnRemove1.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnRemove1.setForeground(new java.awt.Color(255, 255, 255));
        btnRemove1.setText("Remove");
        btnRemove1.setBorderPainted(false);
        btnRemove1.setFocusPainted(false);
        btnRemove1.addActionListener(e -> btnRemoveActionPerformed(0));
        pnlSlot1.add(btnRemove1);
        btnRemove1.setBounds(0, 85, 140, 25);

        pnlImagesPreview.add(pnlSlot1);
        pnlSlot1.setBounds(0, 0, 140, 110);

        pnlSlot2.setBackground(new java.awt.Color(255, 255, 255));
        pnlSlot2.setLayout(null);

        lblPreview2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPreview2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlSlot2.add(lblPreview2);
        lblPreview2.setBounds(0, 0, 140, 80);

        btnRemove2.setBackground(new java.awt.Color(229, 62, 62));
        btnRemove2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnRemove2.setForeground(new java.awt.Color(255, 255, 255));
        btnRemove2.setText("Remove");
        btnRemove2.setBorderPainted(false);
        btnRemove2.setFocusPainted(false);
        btnRemove2.addActionListener(e -> btnRemoveActionPerformed(1));
        pnlSlot2.add(btnRemove2);
        btnRemove2.setBounds(0, 85, 140, 25);

        pnlImagesPreview.add(pnlSlot2);
        pnlSlot2.setBounds(155, 0, 140, 110);

        pnlSlot3.setBackground(new java.awt.Color(255, 255, 255));
        pnlSlot3.setLayout(null);

        lblPreview3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPreview3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlSlot3.add(lblPreview3);
        lblPreview3.setBounds(0, 0, 140, 80);

        btnRemove3.setBackground(new java.awt.Color(229, 62, 62));
        btnRemove3.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnRemove3.setForeground(new java.awt.Color(255, 255, 255));
        btnRemove3.setText("Remove");
        btnRemove3.setBorderPainted(false);
        btnRemove3.setFocusPainted(false);
        btnRemove3.addActionListener(e -> btnRemoveActionPerformed(2));
        pnlSlot3.add(btnRemove3);
        btnRemove3.setBounds(0, 85, 140, 25);

        pnlImagesPreview.add(pnlSlot3);
        pnlSlot3.setBounds(310, 0, 140, 110);

        pnlSlot4.setBackground(new java.awt.Color(255, 255, 255));
        pnlSlot4.setLayout(null);

        lblPreview4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPreview4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlSlot4.add(lblPreview4);
        lblPreview4.setBounds(0, 0, 140, 80);

        btnRemove4.setBackground(new java.awt.Color(229, 62, 62));
        btnRemove4.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnRemove4.setForeground(new java.awt.Color(255, 255, 255));
        btnRemove4.setText("Remove");
        btnRemove4.setBorderPainted(false);
        btnRemove4.setFocusPainted(false);
        btnRemove4.addActionListener(e -> btnRemoveActionPerformed(3));
        pnlSlot4.add(btnRemove4);
        btnRemove4.setBounds(0, 85, 140, 25);

        pnlImagesPreview.add(pnlSlot4);
        pnlSlot4.setBounds(465, 0, 140, 110);

        pnlSlot5.setBackground(new java.awt.Color(255, 255, 255));
        pnlSlot5.setLayout(null);

        lblPreview5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPreview5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlSlot5.add(lblPreview5);
        lblPreview5.setBounds(0, 0, 140, 80);

        btnRemove5.setBackground(new java.awt.Color(229, 62, 62));
        btnRemove5.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnRemove5.setForeground(new java.awt.Color(255, 255, 255));
        btnRemove5.setText("Remove");
        btnRemove5.setBorderPainted(false);
        btnRemove5.setFocusPainted(false);
        btnRemove5.addActionListener(e -> btnRemoveActionPerformed(4));
        pnlSlot5.add(btnRemove5);
        btnRemove5.setBounds(0, 85, 140, 25);

        pnlImagesPreview.add(pnlSlot5);
        pnlSlot5.setBounds(620, 0, 140, 110);

        pnlCard.add(pnlImagesPreview);
        pnlImagesPreview.setBounds(200, 310, 770, 120);

        lblDescription.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblDescription.setText("Description:");
        pnlCard.add(lblDescription);
        lblDescription.setBounds(30, 440, 200, 25);

        scrollDesc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDescription.setRows(3);
        scrollDesc.setViewportView(txtDescription);

        pnlCard.add(scrollDesc);
        scrollDesc.setBounds(30, 470, 940, 90);

        btnSave.setBackground(new java.awt.Color(56, 161, 105));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Save Property");
        btnSave.setBorderPainted(false);
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(this::btnSaveActionPerformed);
        pnlCard.add(btnSave);
        btnSave.setBounds(30, 580, 150, 40);

        btnCancel.setBackground(new java.awt.Color(229, 62, 62));
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel");
        btnCancel.setBorderPainted(false);
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this::btnCancelActionPerformed);
        pnlCard.add(btnCancel);
        btnCancel.setBounds(200, 580, 110, 40);

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        pnlCard.add(lblError);
        lblError.setBounds(340, 580, 450, 40);

        getContentPane().add(pnlCard);
        pnlCard.setBounds(230, 90, 1020, 660);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNavDashboardActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.navigateToDashboard(this);
    }

    private void btnNavMyPropertiesActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.navigateToMyProperties(this);
    }

    private void btnNavLeaseManagementActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.navigateToLeaseManagement(this);
    }

    private void btnNavLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.logout(this);
    }

    private void btnUploadImagesActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.uploadImages(this);
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.navigateToMyProperties(this);
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.saveProperty(this, loadedPropertyId);
    }

    private void btnRemove1ActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.removeImage(this, 0);
    }

    private void btnRemove2ActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.removeImage(this, 1);
    }

    private void btnRemove3ActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.removeImage(this, 2);
    }

    private void btnRemove4ActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.removeImage(this, 3);
    }

    private void btnRemove5ActionPerformed(java.awt.event.ActionEvent evt) {
        propertyController.removeImage(this, 4);
    }

    private void btnRemoveActionPerformed(int index) {
        propertyController.removeImage(this, index);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCancel;
    public javax.swing.JButton btnNavDashboard;
    public javax.swing.JButton btnNavLeaseManagement;
    public javax.swing.JButton btnNavLogout;
    public javax.swing.JButton btnNavMyProperties;
    public javax.swing.JButton btnRemove1;
    public javax.swing.JButton btnRemove2;
    public javax.swing.JButton btnRemove3;
    public javax.swing.JButton btnRemove4;
    public javax.swing.JButton btnRemove5;
    public javax.swing.JButton btnSave;
    public javax.swing.JButton btnUploadImages;
    public javax.swing.JComboBox cmbPropertyType;
    public javax.swing.JLabel lblAddPropertyTitle;
    public javax.swing.JLabel lblAvailableFromLabel;
    public javax.swing.JLabel lblBathroomsLabel;
    public javax.swing.JLabel lblBedroomsLabel;
    public javax.swing.JLabel lblDepositLabel;
    public javax.swing.JLabel lblDescription;
    public javax.swing.JLabel lblError;
    public javax.swing.JLabel lblHeaderTitle;
    public javax.swing.JLabel lblImagesStatus;
    public javax.swing.JLabel lblLocationLabel;
    public javax.swing.JLabel lblLogo;
    public javax.swing.JLabel lblPreview1;
    public javax.swing.JLabel lblPreview2;
    public javax.swing.JLabel lblPreview3;
    public javax.swing.JLabel lblPreview4;
    public javax.swing.JLabel lblPreview5;
    public javax.swing.JLabel lblPropTitleLabel;
    public javax.swing.JLabel lblPropertyDetails;
    public javax.swing.JLabel lblPropertyTypeLabel;
    public javax.swing.JLabel lblRentLabel;
    public javax.swing.JLabel lblUploadPhotos;
    public javax.swing.JLabel lblWelcome;
    public javax.swing.JPanel pnlCard;
    public javax.swing.JPanel pnlHeader;
    public javax.swing.JPanel pnlImagesPreview;
    public javax.swing.JPanel pnlPropertyDetailsHeader;
    public javax.swing.JPanel pnlSidebar;
    public javax.swing.JPanel pnlSlot1;
    public javax.swing.JPanel pnlSlot2;
    public javax.swing.JPanel pnlSlot3;
    public javax.swing.JPanel pnlSlot4;
    public javax.swing.JPanel pnlSlot5;
    public javax.swing.JScrollPane scrollDesc;
    public javax.swing.JTextField txtAddress;
    public javax.swing.JTextField txtAvailableFrom;
    public javax.swing.JTextField txtBathrooms;
    public javax.swing.JTextField txtBedrooms;
    public javax.swing.JTextField txtDeposit;
    public javax.swing.JTextArea txtDescription;
    public javax.swing.JTextField txtPropTitle;
    public javax.swing.JTextField txtRent;
    // End of variables declaration//GEN-END:variables
}

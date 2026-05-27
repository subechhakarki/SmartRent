package Controller;

import DAO.PropertyDAO;
import Model.Property;
import smartrent.SessionService;
import Model.User;

import java.awt.*;
import javax.swing.*;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import view.*;

public class PropertyController {
    
    private PropertyDAO propertyDAO;

    public PropertyController() {
        this.propertyDAO = new PropertyDAO();
    }

    public List<Property> searchAvailableProperties(String location, double minPrice, double maxPrice, String bedrooms, String propertyType) {
        return propertyDAO.getAvailableProperties(location, minPrice, maxPrice, bedrooms, propertyType);
    }

    public String addProperty(String title, String address, String propertyType, String bedroomsStr, String bathroomsStr, String rentStr, String depositStr, Date availableFrom, List<String> imagePaths, int primaryImageIndex) {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null || !"PROPERTY_OWNER".equals(currentUser.getRole())) {
            return "Unauthorized access.";
        }

        if (title == null || title.trim().isEmpty() || address == null || address.trim().isEmpty()) {
            return "Title and address are required.";
        }

        int bedrooms;
        int bathrooms;
        double rent;
        double deposit;
        
        try {
            bedrooms = Integer.parseInt(bedroomsStr);
            bathrooms = Integer.parseInt(bathroomsStr);
            rent = Double.parseDouble(rentStr);
            deposit = Double.parseDouble(depositStr);
            
            if (rent <= 0) return "Rent must be greater than zero.";
        } catch (NumberFormatException e) {
            return "Please enter valid numbers for bedrooms, bathrooms, rent, and deposit.";
        }

        Property p = new Property();
        p.setOwnerId(currentUser.getUserId());
        p.setTitle(title);
        p.setAddress(address);
        p.setPropertyType(propertyType);
        p.setBedrooms(bedrooms);
        p.setBathrooms(bathrooms);
        p.setMonthlyRent(rent);
        p.setDeposit(deposit);
        p.setAvailableFrom(availableFrom != null ? availableFrom : new Date());

        // Process and compress images using Thumbnailator
        java.io.File uploadDir = new java.io.File("uploaded_images");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        java.util.List<String> processedPaths = new java.util.ArrayList<>();
        if (imagePaths != null && !imagePaths.isEmpty()) {
            long timestamp = System.currentTimeMillis();
            for (int i = 0; i < imagePaths.size(); i++) {
                String originalPath = imagePaths.get(i);
                String targetPath = "uploaded_images/prop_" + timestamp + "_" + i + ".jpg";
                try {
                    net.coobird.thumbnailator.Thumbnails.of(new java.io.File(originalPath))
                            .size(320, 240)
                            .outputFormat("jpg")
                            .outputQuality(0.85)
                            .toFile(new java.io.File(targetPath));
                    processedPaths.add(targetPath);
                } catch (java.io.IOException e) {
                    System.err.println("Failed to resize image " + originalPath + ": " + e.getMessage());
                    // Fallback to original path if processing fails
                    processedPaths.add(originalPath);
                }
            }
        }

        boolean success = propertyDAO.createProperty(p, processedPaths, primaryImageIndex);
        return success ? "SUCCESS" : "Failed to add property due to system error.";
    }

    public Property getPropertyById(int propertyId) {
        return propertyDAO.getPropertyById(propertyId);
    }

    public String updateProperty(int propertyId, String title, String address, String propertyType, String bedroomsStr, String bathroomsStr, String rentStr, String depositStr) {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null || !"PROPERTY_OWNER".equals(currentUser.getRole())) {
            return "Unauthorized access.";
        }

        if (title == null || title.trim().isEmpty() || address == null || address.trim().isEmpty()) {
            return "Title and address are required.";
        }

        int bedrooms;
        int bathrooms;
        double rent;
        double deposit;
        
        try {
            bedrooms = Integer.parseInt(bedroomsStr);
            bathrooms = Integer.parseInt(bathroomsStr);
            rent = Double.parseDouble(rentStr);
            deposit = Double.parseDouble(depositStr);
            
            if (rent <= 0) return "Rent must be greater than zero.";
        } catch (NumberFormatException e) {
            return "Please enter valid numbers for bedrooms, bathrooms, rent, and deposit.";
        }

        Property p = new Property();
        p.setPropertyId(propertyId);
        p.setOwnerId(currentUser.getUserId());
        p.setTitle(title);
        p.setAddress(address);
        p.setPropertyType(propertyType);
        p.setBedrooms(bedrooms);
        p.setBathrooms(bathrooms);
        p.setMonthlyRent(rent);
        p.setDeposit(deposit);

        boolean success = propertyDAO.updateProperty(p);
        return success ? "SUCCESS" : "Failed to update property.";
    }

    public String deleteProperty(int propertyId) {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null || !"PROPERTY_OWNER".equals(currentUser.getRole())) {
            return "Unauthorized access.";
        }

        if (propertyDAO.hasActiveApplications(propertyId)) {
            return "Cannot delete property. There are active applications.";
        }

        boolean success = propertyDAO.deleteProperty(propertyId, currentUser.getUserId());
        return success ? "SUCCESS" : "Failed to delete property.";
    }

    public String addRating(int propertyId, int score, String reviewText) {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null || !"RENTER".equals(currentUser.getRole())) {
            return "Only renters can leave ratings.";
        }
        
        if (score < 1 || score > 5) {
            return "Score must be between 1 and 5.";
        }
        
        Model.Rating r = new Model.Rating();
        r.setPropertyId(propertyId);
        r.setRenterId(currentUser.getUserId());
        r.setScore(score);
        r.setReviewText(reviewText);
        
        DAO.RatingDAO ratingDAO = new DAO.RatingDAO();
        boolean success = ratingDAO.addRating(r);
        return success ? "SUCCESS" : "Failed to add rating (you may have already rated this property).";
    }

    public java.util.List<Model.Rating> getPropertyRatings(int propertyId) {
        return new DAO.RatingDAO().getRatingsForProperty(propertyId);
    }

    public double getAverageRating(int propertyId) {
        return new DAO.RatingDAO().getAverageRating(propertyId);
    }

    public List<Property> getOwnerProperties() {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null || !"PROPERTY_OWNER".equals(currentUser.getRole())) {
            return new java.util.ArrayList<>();
        }
        return propertyDAO.getOwnerProperties(currentUser.getUserId());
    }

    public void initRenterDashboard(view.RenterDashboardView view) {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser != null) {
            view.getLblWelcome().setText("Welcome, " + currentUser.getFullName().split(" ")[0]);
        }
        
        view.getScrollPaneProperties().getVerticalScrollBar().setUnitIncrement(16);
        view.getScrollPaneProperties().setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        view.setTitle("SmartRent - Renter Dashboard v2");
        view.setSize(1280, 800);
        view.setPreferredSize(new Dimension(1280, 800));
        
        view.getPnlSidebar().setBounds(0, 0, 220, 800);
        
        view.getLblLogo().setText("<html><font size='5'>\ud83c\udfe0</font> SmartRent</html>");
        view.getLblLogo().setBounds(20, 20, 180, 40);
        
        view.getBtnNavDashboard().setText("\u229e  Dashboard");
        view.getBtnNavDashboard().setBounds(0, 80, 220, 40);
        view.getBtnNavDashboard().setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        view.getBtnNavDashboard().setMargin(new java.awt.Insets(2, 20, 2, 14));
        
        view.getBtnNavMyApplications().setText("\ud83d\udcc4  My Applications");
        view.getBtnNavMyApplications().setBounds(0, 120, 220, 40);
        view.getBtnNavMyApplications().setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        view.getBtnNavMyApplications().setMargin(new java.awt.Insets(2, 20, 2, 14));
        
        view.getBtnNavPropertyRatings().setText("\u2605  Property Ratings");
        view.getBtnNavPropertyRatings().setBounds(0, 160, 220, 40);
        view.getBtnNavPropertyRatings().setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        view.getBtnNavPropertyRatings().setMargin(new java.awt.Insets(2, 20, 2, 14));
        
        view.getBtnNavSavedProperties().setText("\u2661  Saved Properties");
        view.getBtnNavSavedProperties().setBounds(0, 200, 220, 40);
        view.getBtnNavSavedProperties().setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        view.getBtnNavSavedProperties().setMargin(new java.awt.Insets(2, 20, 2, 14));
        
        view.getBtnNavLogout().setText("\ud83d\udeaa  Logout");
        view.getBtnNavLogout().setBounds(0, 260, 220, 40);
        view.getBtnNavLogout().setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        view.getBtnNavLogout().setMargin(new java.awt.Insets(2, 20, 2, 14));
        
        view.getLblDashboardHeader().setBounds(250, 20, 200, 40);
        view.getLblWelcome().setBounds(1030, 20, 200, 40);
        view.getLblWelcome().setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        
        view.getPnlFilters().setBounds(250, 70, 1000, 60);
        view.getPnlFilters().setBackground(new java.awt.Color(245, 247, 250));
        
        view.getTxtLocation().setBounds(0, 15, 150, 35);
        view.getTxtMaxPrice().setBounds(170, 15, 150, 35);
        view.getCmbBedrooms().setBounds(340, 15, 100, 35);
        view.getCmbPropertyType().setBounds(460, 15, 120, 35);
        
        view.getBtnSearch().setBounds(850, 15, 150, 35);
        view.getBtnSearch().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        
        view.getScrollPaneProperties().setBounds(250, 140, 1000, 600);
        
        loadRenterProperties(view);
    }

    public void loadRenterProperties(view.RenterDashboardView view) {
        String location = view.getTxtLocation().getText().equals("Location") ? "" : view.getTxtLocation().getText();
        
        double maxPrice = 0;
        try {
            if (!view.getTxtMaxPrice().getText().equals("Max Price")) {
                maxPrice = Double.parseDouble(view.getTxtMaxPrice().getText());
            }
        } catch (NumberFormatException e) {
            // ignore
        }
        
        String bedrooms = view.getCmbBedrooms().getSelectedItem().toString();
        if (bedrooms.equals("4+")) bedrooms = "4";
        
        String propertyType = view.getCmbPropertyType().getSelectedItem().toString();
        if (propertyType.equals("Any")) propertyType = "";

        List<Property> properties = new java.util.ArrayList<>();
        try {
            properties = searchAvailableProperties(location, 0, maxPrice, bedrooms, propertyType);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        if (properties == null || properties.isEmpty()) {
            properties = new java.util.ArrayList<>();
            Property d1 = new Property(); d1.setPropertyId(1); d1.setTitle("Lakeside Apartment"); d1.setAddress("Mumbai"); d1.setMonthlyRent(45000.0); d1.setAvgRating(4.5); properties.add(d1);
            Property d2 = new Property(); d2.setPropertyId(2); d2.setTitle("Greenview Villa"); d2.setAddress("Bangalore"); d2.setMonthlyRent(60000.0); d2.setAvgRating(4.8); properties.add(d2);
            Property d3 = new Property(); d3.setPropertyId(3); d3.setTitle("Urban Loft"); d3.setAddress("Delhi"); d3.setMonthlyRent(38000.0); d3.setAvgRating(3.9); properties.add(d3);
            Property d4 = new Property(); d4.setPropertyId(4); d4.setTitle("Sunset Hills House"); d4.setAddress("Pune"); d4.setMonthlyRent(55000.0); d4.setAvgRating(4.2); properties.add(d4);
            Property d5 = new Property(); d5.setPropertyId(5); d5.setTitle("Palmview House"); d5.setAddress("Goa"); d5.setMonthlyRent(32000.0); d5.setAvgRating(4.0); properties.add(d5);
            Property d6 = new Property(); d6.setPropertyId(6); d6.setTitle("Cityscape Studio"); d6.setAddress("Hyderabad"); d6.setMonthlyRent(25000.0); d6.setAvgRating(3.7); properties.add(d6);
            Property d7 = new Property(); d7.setPropertyId(7); d7.setTitle("Maplewood Residence"); d7.setAddress("Chennai"); d7.setMonthlyRent(70000.0); d7.setAvgRating(4.6); properties.add(d7);
            Property d8 = new Property(); d8.setPropertyId(8); d8.setTitle("Cozy Cottage"); d8.setAddress("Jaipur"); d8.setMonthlyRent(28000.0); d8.setAvgRating(4.1); properties.add(d8);
        }
        
        view.getPnlCard1().setVisible(false);
        view.getPnlCard2().setVisible(false);
        view.getPnlCard3().setVisible(false);
        view.getPnlCard4().setVisible(false);
        view.getPnlCard5().setVisible(false);
        view.getPnlCard6().setVisible(false);
        
        int count = Math.min(properties.size(), 6);
        for (int i = 0; i < count; i++) {
            Property p = properties.get(i);
            if (i == 0) configureCard(view, view.getPnlCard1(), p);
            else if (i == 1) configureCard(view, view.getPnlCard2(), p);
            else if (i == 2) configureCard(view, view.getPnlCard3(), p);
            else if (i == 3) configureCard(view, view.getPnlCard4(), p);
            else if (i == 4) configureCard(view, view.getPnlCard5(), p);
            else if (i == 5) configureCard(view, view.getPnlCard6(), p);
        }
        
        int containerWidth = view.getPnlPropertiesGrid().getWidth();
        if (containerWidth <= 0) {
            containerWidth = 980;
        }
        int cardWidth = 220;
        int gap = 20;
        int cols = (containerWidth - gap) / (cardWidth + gap);
        if (cols <= 0) cols = 1;
        
        int rows = (int) Math.ceil(count / (double) cols);
        int prefHeight = rows * (280 + gap) + gap;
        view.getPnlPropertiesGrid().setPreferredSize(new Dimension(containerWidth, Math.max(prefHeight, 600)));
        
        view.getPnlPropertiesGrid().revalidate();
        view.getPnlPropertiesGrid().repaint();
    }

    private void configureCard(view.RenterDashboardView view, view.PropertyCardRenter card, Property p) {
        card.setPropertyData(
            p,
            e -> {
                SavedPropertyController savedPropCtrl = new SavedPropertyController();
                if (savedPropCtrl.saveProperty(p.getPropertyId())) {
                    JOptionPane.showMessageDialog(view, "Property saved!");
                }
            },
            e -> {
                new view.ApplicationFormView(p.getPropertyId()).setVisible(true);
            }
        );
        card.setVisible(true);
        card.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getSource() == card) {
                    openPropertyDetail(view, p.getPropertyId());
                }
            }
        });
    }

    public void navigateToDashboard(RenterDashboardView view) {
        loadRenterProperties(view);
    }

    public void navigateToMyApplications(RenterDashboardView view) {
        new MyApplicationView().setVisible(true);
        view.dispose();
    }

    public void navigateToPropertyRatings(RenterDashboardView view) {
        new PropertyRatingsView().setVisible(true);
        view.dispose();
    }

    public void navigateToSavedProperties(RenterDashboardView view) {
        new SavedPropertiesView().setVisible(true);
        view.dispose();
    }

    public void logout(RenterDashboardView view) {
        SessionService.getInstance().logout();
        new LoginView().setVisible(true);
        view.dispose();
    }

    // PropertyRatingsView management
    public void initRatingsView(PropertyRatingsView view, int propertyId, String propertyTitle) {
        view.getLblTitle().setText("Ratings for: " + propertyTitle);
        view.getPnlRatingsList().setLayout(new javax.swing.BoxLayout(view.getPnlRatingsList(), javax.swing.BoxLayout.Y_AXIS));
        
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (propertyId == -1 || currentUser == null || !"RENTER".equals(currentUser.getRole())) {
            view.getLblAddRating().setVisible(false);
            view.getCmbScore().setVisible(false);
            view.getTxtReview().setVisible(false);
            view.getBtnSubmitReview().setVisible(false);
        }
        
        if (propertyId != -1) {
            loadRatings(view, propertyId);
        } else {
            view.getLblAvgRating().setText("Go to the Dashboard to select a property.");
            view.getPnlRatingsList().removeAll();
            view.getPnlRatingsList().revalidate();
            view.getPnlRatingsList().repaint();
        }
        
        view.setSize(1280, 800);
        view.setPreferredSize(new Dimension(1280, 800));
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.revalidate();
        view.repaint();
    }

    public void loadRatings(PropertyRatingsView view, int propertyId) {
        double avg = getAverageRating(propertyId);
        view.getLblAvgRating().setText(String.format("Average Rating: %.1f / 5", avg));
        
        view.getPnlRatingsList().removeAll();
        List<Model.Rating> ratings = getPropertyRatings(propertyId);
        
        if (ratings.isEmpty()) {
            JLabel emptyLbl = new JLabel("No ratings yet for this property.");
            emptyLbl.setFont(new java.awt.Font("Segoe UI", java.awt.Font.ITALIC, 14));
            view.getPnlRatingsList().add(emptyLbl);
        }
        
        for (Model.Rating r : ratings) {
            JPanel card = new JPanel();
            card.setLayout(null);
            card.setPreferredSize(new Dimension(500, 80));
            card.setMaximumSize(new Dimension(500, 80));
            card.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
            
            JLabel lblName = new JLabel(r.getRenterName() + " (" + r.getScore() + "/5)");
            lblName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
            lblName.setBounds(10, 10, 300, 20);
            card.add(lblName);
            
            JLabel lblDate = new JLabel(r.getCreatedAt().toString().substring(0, 10));
            lblDate.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 10));
            lblDate.setForeground(java.awt.Color.GRAY);
            lblDate.setBounds(400, 10, 80, 20);
            card.add(lblDate);
            
            JLabel lblReview = new JLabel("<html>" + r.getReviewText() + "</html>");
            lblReview.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
            lblReview.setBounds(10, 35, 480, 40);
            card.add(lblReview);
            
            view.getPnlRatingsList().add(card);
            view.getPnlRatingsList().add(Box.createRigidArea(new Dimension(0, 10)));
        }
        view.getPnlRatingsList().revalidate();
        view.getPnlRatingsList().repaint();
    }

    public void submitRating(PropertyRatingsView view, int propertyId) {
        if (propertyId == -1) return;
        
        int scoreIndex = view.getCmbScore().getSelectedIndex();
        int score = 5 - scoreIndex; // 0=5, 1=4, 2=3, 3=2, 4=1
        String review = view.getTxtReview().getText();
        
        if (review.equals("Write your review here...") || review.trim().isEmpty()) {
            review = "";
        }
        
        String result = addRating(propertyId, score, review);
        if (result.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(view, "Thank you for your review!");
            view.getTxtReview().setText("Write your review here...");
            loadRatings(view, propertyId);
        } else {
            JOptionPane.showMessageDialog(view, result);
        }
    }

    public void navigateToDashboard(PropertyRatingsView view) {
        new RenterDashboardView().setVisible(true);
        view.dispose();
    }

    public void navigateToMyApplications(PropertyRatingsView view) {
        new MyApplicationView().setVisible(true);
        view.dispose();
    }

    public void navigateToSavedProperties(PropertyRatingsView view) {
        new SavedPropertiesView().setVisible(true);
        view.dispose();
    }

    public void logout(PropertyRatingsView view) {
        SessionService.getInstance().logout();
        new LoginView().setVisible(true);
        view.dispose();
    }

    // MyPropertiesView management
    public void initMyProperties(MyPropertiesView view) {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser != null) {
            view.getLblWelcome().setText("Welcome, " + currentUser.getFullName().split(" ")[0]);
        }
        
        loadMyProperties(view);
        
        view.setSize(1024, 768);
        view.setResizable(false);
        view.setLocationRelativeTo(null);
    }

    public void loadMyProperties(MyPropertiesView view) {
        view.getPnlTableBody().removeAll();
        List<Property> properties = getOwnerProperties();
        
        for (Property p : properties) {
            JPanel row = new JPanel();
            row.setLayout(null);
            row.setPreferredSize(new Dimension(740, 60));
            row.setMaximumSize(new Dimension(740, 60));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);
            row.setBackground(Color.WHITE);
            row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(240, 240, 240)));

            JLabel lblTitleText = new JLabel(p.getTitle());
            lblTitleText.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblTitleText.setForeground(new Color(43, 108, 176));
            lblTitleText.setBounds(15, 15, 180, 30);
            row.add(lblTitleText);

            JLabel lblLoc = new JLabel(p.getAddress());
            lblLoc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lblLoc.setForeground(new Color(74, 85, 104));
            lblLoc.setBounds(205, 15, 130, 30);
            row.add(lblLoc);

            String status = p.getPropStatus() != null ? p.getPropStatus() : "AVAILABLE";
            boolean isAvailable = "AVAILABLE".equalsIgnoreCase(status);
            JLabel lblStatusBadge = new JLabel(isAvailable ? "Available" : "Occupied");
            lblStatusBadge.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblStatusBadge.setHorizontalAlignment(SwingConstants.CENTER);
            lblStatusBadge.setOpaque(true);
            if (isAvailable) {
                lblStatusBadge.setBackground(new Color(56, 161, 105));
                lblStatusBadge.setForeground(Color.WHITE);
            } else {
                lblStatusBadge.setBackground(new Color(221, 107, 32));
                lblStatusBadge.setForeground(Color.WHITE);
            }
            lblStatusBadge.setBounds(345, 15, 90, 30);
            row.add(lblStatusBadge);

            JButton btnEdit = new JButton("Edit");
            btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 12));
            btnEdit.setBackground(new Color(43, 108, 176));
            btnEdit.setForeground(Color.WHITE);
            btnEdit.setFocusPainted(false);
            btnEdit.setBounds(450, 15, 70, 30);
            btnEdit.addActionListener(e -> {
                new AddProperty(p.getPropertyId()).setVisible(true);
                view.dispose();
            });
            row.add(btnEdit);

            JButton btnDelete = new JButton("Delete");
            btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 12));
            btnDelete.setBackground(new Color(229, 62, 62));
            btnDelete.setForeground(Color.WHITE);
            btnDelete.setFocusPainted(false);
            btnDelete.setBounds(525, 15, 75, 30);
            btnDelete.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(view, 
                        "Are you sure you want to delete property: " + p.getTitle() + "?", 
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String result = deleteProperty(p.getPropertyId());
                    if ("SUCCESS".equals(result)) {
                        JOptionPane.showMessageDialog(view, "Property deleted successfully.");
                        loadMyProperties(view);
                    } else {
                        JOptionPane.showMessageDialog(view, result, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            row.add(btnDelete);

            String dateStr = new SimpleDateFormat("MM/dd/yyyy").format(p.getCreatedAt() != null ? p.getCreatedAt() : new java.util.Date());
            JLabel lblDateText = new JLabel(dateStr);
            lblDateText.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lblDateText.setForeground(new Color(113, 128, 150));
            lblDateText.setBounds(615, 15, 120, 30);
            row.add(lblDateText);

            view.getPnlTableBody().add(row);
        }

        view.getPnlTableBody().revalidate();
        view.getPnlTableBody().repaint();
    }

    public void addProperty(MyPropertiesView view) {
        new AddProperty().setVisible(true);
        view.dispose();
    }

    public void navigateToDashboard(MyPropertiesView view) {
        new OwnerDashboardView().setVisible(true);
        view.dispose();
    }

    public void navigateToLeaseManagement(MyPropertiesView view) {
        new LeaseManagementView().setVisible(true);
        view.dispose();
    }

    public void logout(MyPropertiesView view) {
        SessionService.getInstance().logout();
        new LoginView().setVisible(true);
        view.dispose();
    }

    // AddProperty view management
    public void initAddProperty(AddProperty view, int loadedPropertyId) {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser != null) {
            view.getLblWelcome().setText("Welcome, " + currentUser.getFullName().split(" ")[0]);
        }
        
        view.getCmbPropertyType().setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Apartment", "House", "Studio" }));

        refreshImagePreviews(view);
        
        view.getTxtAvailableFrom().setText(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
        
        if (loadedPropertyId != -1) {
            loadPropertyData(view, loadedPropertyId);
            setupDeleteButton(view, loadedPropertyId);
        }

        view.setSize(1280, 800);
        view.setResizable(false);
        view.setLocationRelativeTo(null);
    }

    private void setupDeleteButton(AddProperty view, int loadedPropertyId) {
        JButton btnDelete = new JButton("Delete Property");
        btnDelete.setBackground(new java.awt.Color(229, 62, 62));
        btnDelete.setForeground(java.awt.Color.WHITE);
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnDelete.setBounds(820, 580, 150, 40);
        btnDelete.setBorderPainted(false);
        btnDelete.setFocusPainted(false);
        btnDelete.addActionListener(e -> deleteProperty(view, loadedPropertyId));
        view.getPnlCard().add(btnDelete);
        view.getPnlCard().revalidate();
        view.getPnlCard().repaint();
    }

    public void loadPropertyData(AddProperty view, int loadedPropertyId) {
        Property p = getPropertyById(loadedPropertyId);
        if (p != null) {
            view.getLblAddPropertyTitle().setText("Edit Property");
            view.getTxtPropTitle().setText(p.getTitle());
            view.getTxtAddress().setText(p.getAddress());
            view.getCmbPropertyType().setSelectedItem(p.getPropertyType());
            view.getTxtBedrooms().setText(String.valueOf(p.getBedrooms()));
            view.getTxtBathrooms().setText(String.valueOf(p.getBathrooms()));
            view.getTxtRent().setText(String.valueOf(p.getMonthlyRent()));
            view.getTxtDeposit().setText(String.valueOf(p.getDeposit()));
            
            String dateStr = new SimpleDateFormat("MM/dd/yyyy").format(p.getAvailableFrom() != null ? p.getAvailableFrom() : new Date());
            view.getTxtAvailableFrom().setText(dateStr);
            
            view.getBtnSave().setText("Update Property");
            
            if (p.getPrimaryImagePath() != null && !p.getPrimaryImagePath().isEmpty()) {
                view.getSelectedImagePaths().clear();
                view.getSelectedImagePaths().add(p.getPrimaryImagePath());
            }
            refreshImagePreviews(view);
        }
    }

    public void deleteProperty(AddProperty view, int loadedPropertyId) {
        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this property?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String result = deleteProperty(loadedPropertyId);
            if (result.equals("SUCCESS")) {
                JOptionPane.showMessageDialog(view, "Property deleted successfully.");
                new MyPropertiesView().setVisible(true);
                view.dispose();
            } else {
                view.getLblError().setText(result);
            }
        }
    }

    public void uploadImages(AddProperty view) {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        int result = chooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File[] files = chooser.getSelectedFiles();
            int count = view.getSelectedImagePaths().size();
            for (java.io.File f : files) {
                if (count >= 5) break;
                view.getSelectedImagePaths().add(f.getAbsolutePath());
                count++;
            }
            refreshImagePreviews(view);
        }
    }

    public void removeImage(AddProperty view, int index) {
        if (index >= 0 && index < view.getSelectedImagePaths().size()) {
            view.getSelectedImagePaths().remove(index);
            refreshImagePreviews(view);
        }
    }

    public void saveProperty(AddProperty view, int loadedPropertyId) {
        String title = view.getTxtPropTitle().getText().trim();
        String address = view.getTxtAddress().getText().trim();
        String type = view.getCmbPropertyType().getSelectedItem().toString();
        String bed = view.getTxtBedrooms().getText().trim();
        String bath = view.getTxtBathrooms().getText().trim();
        String rent = view.getTxtRent().getText().trim();
        String deposit = view.getTxtDeposit().getText().trim();
        String availStr = view.getTxtAvailableFrom().getText().trim();

        if (title.isEmpty() || address.isEmpty() || bed.isEmpty() || bath.isEmpty() || rent.isEmpty() || deposit.isEmpty()) {
            view.getLblError().setText("Please fill out all required fields.");
            return;
        }

        Date availDate = new Date();
        if (!availStr.isEmpty()) {
            try {
                availDate = new SimpleDateFormat("MM/dd/yyyy").parse(availStr);
            } catch (java.text.ParseException e) {
                // Keep default
            }
        }

        String result;
        if (loadedPropertyId == -1) {
            result = addProperty(title, address, type, bed, bath, rent, deposit, availDate, view.getSelectedImagePaths(), 0);
        } else {
            result = updateProperty(loadedPropertyId, title, address, type, bed, bath, rent, deposit);
        }
        
        if (result.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(view, loadedPropertyId == -1 ? "Property added successfully!" : "Property updated successfully!");
            new MyPropertiesView().setVisible(true);
            view.dispose();
        } else {
            view.getLblError().setText(result);
        }
    }

    public void refreshImagePreviews(AddProperty view) {
        view.getPnlSlot1().setVisible(false);
        view.getPnlSlot2().setVisible(false);
        view.getPnlSlot3().setVisible(false);
        view.getPnlSlot4().setVisible(false);
        view.getPnlSlot5().setVisible(false);

        int count = view.getSelectedImagePaths().size();
        view.getLblImagesStatus().setText(count + " Images Selected");

        if (count > 0) {
            setupSlot(view.getPnlSlot1(), view.getLblPreview1(), view.getSelectedImagePaths().get(0));
            view.getPnlSlot1().setVisible(true);
        }
        if (count > 1) {
            setupSlot(view.getPnlSlot2(), view.getLblPreview2(), view.getSelectedImagePaths().get(1));
            view.getPnlSlot2().setVisible(true);
        }
        if (count > 2) {
            setupSlot(view.getPnlSlot3(), view.getLblPreview3(), view.getSelectedImagePaths().get(2));
            view.getPnlSlot3().setVisible(true);
        }
        if (count > 3) {
            setupSlot(view.getPnlSlot4(), view.getLblPreview4(), view.getSelectedImagePaths().get(3));
            view.getPnlSlot4().setVisible(true);
        }
        if (count > 4) {
            setupSlot(view.getPnlSlot5(), view.getLblPreview5(), view.getSelectedImagePaths().get(4));
            view.getPnlSlot5().setVisible(true);
        }
        
        view.getPnlImagesPreview().revalidate();
        view.getPnlImagesPreview().repaint();
    }

    private void setupSlot(JPanel slot, JLabel label, String path) {
        try {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(140, 80, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
            label.setText("");
        } catch (Exception e) {
            label.setIcon(null);
            label.setText("No Image");
        }
    }

    public void navigateToDashboard(AddProperty view) {
        new OwnerDashboardView().setVisible(true);
        view.dispose();
    }

    public void navigateToMyProperties(AddProperty view) {
        new MyPropertiesView().setVisible(true);
        view.dispose();
    }

    public void navigateToLeaseManagement(AddProperty view) {
        new LeaseManagementView().setVisible(true);
        view.dispose();
    }

    public void logout(AddProperty view) {
        SessionService.getInstance().logout();
        new LoginView().setVisible(true);
        view.dispose();
    }

    // ===== PropertyDetailView management =====

    public void openPropertyDetail(javax.swing.JFrame parentView, int propertyId) {
        PropertyDetailView detailView = new PropertyDetailView();
        initPropertyDetail(detailView, propertyId);
        detailView.setVisible(true);
        parentView.dispose();
    }

    public void initPropertyDetail(PropertyDetailView view, int propertyId) {
        view.setSize(1280, 800);
        view.setResizable(false);
        view.setLocationRelativeTo(null);

        Property p = getPropertyById(propertyId);
        if (p == null) {
            javax.swing.JOptionPane.showMessageDialog(view, "Property not found.");
            return;
        }

        // Set property info
        view.getLblTitle().setText(p.getTitle());
        view.getLblAddress().setText("\uD83D\uDCCD " + p.getAddress());
        view.getLblPropertyType().setText(p.getPropertyType() != null ? p.getPropertyType() : "N/A");
        view.getLblBedrooms().setText(p.getBedrooms() + " Bedrooms");
        view.getLblBathrooms().setText(p.getBathrooms() + " Bathrooms");
        view.getLblRent().setText("Rs. " + String.format("%,.0f", p.getMonthlyRent()) + " / month");
        view.getLblDeposit().setText("Deposit: Rs. " + String.format("%,.0f", p.getDeposit()));
        view.getLblAvailableFrom().setText("Available from: " + (p.getAvailableFrom() != null ? new java.text.SimpleDateFormat("MMM dd, yyyy").format(p.getAvailableFrom()) : "Now"));
        view.getLblStatus().setText(p.getPropStatus() != null ? p.getPropStatus() : "AVAILABLE");
        view.getLblRating().setText(p.getAvgRating() >= 4.5 ? "\u2605\u2605\u2605\u2605\u2605 " + p.getAvgRating() : "\u2605\u2605\u2605\u2605\u2606 " + p.getAvgRating());

        // Load images
        List<String> images = propertyDAO.getPropertyImages(propertyId);
        if (images.isEmpty() && p.getPrimaryImagePath() != null) {
            images.add(p.getPrimaryImagePath());
        }

        final List<String> imageList = images;
        final int[] currentIndex = {0};

        // Show first image
        if (!imageList.isEmpty()) {
            showImage(view, imageList, 0);
            view.getLblImageCounter().setText("1 / " + imageList.size());
        }

        // Set up thumbnails
        JLabel[] thumbs = {view.getThumbnail1(), view.getThumbnail2(), view.getThumbnail3(), view.getThumbnail4()};
        for (int i = 0; i < thumbs.length; i++) {
            if (i < imageList.size()) {
                final int idx = i;
                loadThumbnail(thumbs[i], imageList.get(i));
                thumbs[i].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                for (java.awt.event.MouseListener ml : thumbs[i].getMouseListeners()) {
                    thumbs[i].removeMouseListener(ml);
                }
                thumbs[i].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        currentIndex[0] = idx;
                        showImage(view, imageList, idx);
                        view.getLblImageCounter().setText((idx + 1) + " / " + imageList.size());
                    }
                });
                thumbs[i].setVisible(true);
            } else {
                thumbs[i].setVisible(false);
            }
        }

        // Arrow buttons
        view.getBtnPrevImage().addActionListener(e -> {
            if (currentIndex[0] > 0) {
                currentIndex[0]--;
                showImage(view, imageList, currentIndex[0]);
                view.getLblImageCounter().setText((currentIndex[0] + 1) + " / " + imageList.size());
            }
        });
        view.getBtnNextImage().addActionListener(e -> {
            if (currentIndex[0] < imageList.size() - 1) {
                currentIndex[0]++;
                showImage(view, imageList, currentIndex[0]);
                view.getLblImageCounter().setText((currentIndex[0] + 1) + " / " + imageList.size());
            }
        });

        // Action buttons
        view.getBtnApply().addActionListener(e -> {
            new view.ApplicationFormView(propertyId).setVisible(true);
            view.dispose();
        });
        view.getBtnSave().addActionListener(e -> {
            SavedPropertyController savedCtrl = new SavedPropertyController();
            if (savedCtrl.saveProperty(propertyId)) {
                javax.swing.JOptionPane.showMessageDialog(view, "Property saved!");
            }
        });
        view.getBtnBack().addActionListener(e -> {
            new RenterDashboardView().setVisible(true);
            view.dispose();
        });

        // Sidebar navigation
        view.getBtnNavDashboard().addActionListener(e -> { new RenterDashboardView().setVisible(true); view.dispose(); });
        view.getBtnNavMyApplications().addActionListener(e -> { new MyApplicationView().setVisible(true); view.dispose(); });
        view.getBtnNavPropertyRatings().addActionListener(e -> { new PropertyRatingsView().setVisible(true); view.dispose(); });
        view.getBtnNavSavedProperties().addActionListener(e -> { new SavedPropertiesView().setVisible(true); view.dispose(); });
        view.getBtnNavLogout().addActionListener(e -> { SessionService.getInstance().logout(); new LoginView().setVisible(true); view.dispose(); });
    }

    private void showImage(PropertyDetailView view, List<String> images, int index) {
        if (index >= 0 && index < images.size()) {
            String path = images.get(index);
            java.io.File file = new java.io.File(path);
            if (file.exists()) {
                javax.swing.ImageIcon icon = new javax.swing.ImageIcon(path);
                java.awt.Image img = icon.getImage().getScaledInstance(580, 340, java.awt.Image.SCALE_SMOOTH);
                view.getLblMainImage().setIcon(new javax.swing.ImageIcon(img));
                view.getLblMainImage().setText("");
            } else {
                view.getLblMainImage().setIcon(null);
                view.getLblMainImage().setText("<html><div style='text-align:center;color:white;padding-top:140px;font-size:16px;'>No Image Available</div></html>");
            }
        }
    }

    private void loadThumbnail(JLabel lbl, String path) {
        java.io.File file = new java.io.File(path);
        if (file.exists()) {
            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(path);
            java.awt.Image img = icon.getImage().getScaledInstance(120, 70, java.awt.Image.SCALE_SMOOTH);
            lbl.setIcon(new javax.swing.ImageIcon(img));
            lbl.setText("");
        }
    }
}

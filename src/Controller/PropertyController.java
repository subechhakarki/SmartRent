package Controller;

import DAO.PropertyDAO;
import Model.Property;
import Controller.SessionService;
import Model.User;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableModel;
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
        java.io.File projectRoot = getProjectRoot();
        java.io.File uploadDir = new java.io.File(projectRoot, "uploaded_images");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        java.util.List<String> processedPaths = new java.util.ArrayList<>();
        if (imagePaths != null && !imagePaths.isEmpty()) {
            long timestamp = System.currentTimeMillis();
            for (int i = 0; i < imagePaths.size(); i++) {
                String originalPath = imagePaths.get(i);
                String relativePath = "uploaded_images/prop_" + timestamp + "_" + i + ".jpg";
                java.io.File targetFile = new java.io.File(projectRoot, relativePath);
                try {
                    net.coobird.thumbnailator.Thumbnails.of(new java.io.File(originalPath))
                            .size(320, 240)
                            .outputFormat("jpg")
                            .outputQuality(0.85)
                            .toFile(targetFile);
                    processedPaths.add(relativePath);
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

    public String updateProperty(int propertyId, String title, String address, String propertyType, String bedroomsStr, String bathroomsStr, String rentStr, String depositStr, Date availableFrom, List<String> imagePaths, int primaryImageIndex) {
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

        // Process images
        java.io.File projectRoot = getProjectRoot();
        java.io.File uploadDir = new java.io.File(projectRoot, "uploaded_images");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        java.util.List<String> processedPaths = new java.util.ArrayList<>();
        if (imagePaths != null && !imagePaths.isEmpty()) {
            long timestamp = System.currentTimeMillis();
            for (int i = 0; i < imagePaths.size(); i++) {
                String originalPath = imagePaths.get(i);
                // If it is already a relative path inside uploaded_images, keep it as is
                if (originalPath.startsWith("uploaded_images/") || originalPath.startsWith("uploaded_images\\")) {
                    processedPaths.add(originalPath);
                } else {
                    String relativePath = "uploaded_images/prop_" + timestamp + "_" + i + ".jpg";
                    java.io.File targetFile = new java.io.File(projectRoot, relativePath);
                    try {
                        net.coobird.thumbnailator.Thumbnails.of(new java.io.File(originalPath))
                                .size(320, 240)
                                .outputFormat("jpg")
                                .outputQuality(0.85)
                                .toFile(targetFile);
                        processedPaths.add(relativePath);
                    } catch (java.io.IOException e) {
                        System.err.println("Failed to resize image " + originalPath + ": " + e.getMessage());
                        processedPaths.add(originalPath);
                    }
                }
            }
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
        p.setAvailableFrom(availableFrom != null ? availableFrom : new Date());

        // Composing comma-separated image paths
        String combinedImagePath = "";
        if (!processedPaths.isEmpty()) {
            List<String> orderedPaths = new java.util.ArrayList<>();
            String primary = (primaryImageIndex >= 0 && primaryImageIndex < processedPaths.size()) ? processedPaths.get(primaryImageIndex) : processedPaths.get(0);
            orderedPaths.add(primary);
            for (String path : processedPaths) {
                if (!path.equals(primary)) {
                    orderedPaths.add(path);
                }
            }
            combinedImagePath = String.join(",", orderedPaths);
        }
        p.setPrimaryImagePath(combinedImagePath);

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

    public static String getStarHtml(double rating) {
        int rounded = (int) Math.round(rating);
        StringBuilder sb = new StringBuilder("<html><font face='Segoe UI Symbol' color='#F39C12'>");
        for (int i = 0; i < 5; i++) {
            if (i < rounded) {
                sb.append("★");
            } else {
                sb.append("</font><font face='Segoe UI Symbol' color='#D1D5DB'>★</font><font face='Segoe UI Symbol' color='#F39C12'>");
            }
        }
        sb.append("</font> <font color='gray'>(").append(String.format("%.1f", rating)).append(")</font></html>");
        return sb.toString();
    }

    public static String getStarHtmlForScore(int score) {
        StringBuilder sb = new StringBuilder("<html><font face='Segoe UI Symbol' color='#F39C12'>");
        for (int i = 0; i < 5; i++) {
            if (i < score) {
                sb.append("★");
            } else {
                sb.append("</font><font face='Segoe UI Symbol' color='#D1D5DB'>★</font><font face='Segoe UI Symbol' color='#F39C12'>");
            }
        }
        sb.append("</font></html>");
        return sb.toString();
    }

    public List<Property> getOwnerProperties() {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null || !"PROPERTY_OWNER".equals(currentUser.getRole())) {
            return new java.util.ArrayList<>();
        }
        return propertyDAO.getOwnerProperties(currentUser.getUserId());
    }

    public void initRenterDashboard(view.RenterDashboardView view) {
        view.scrollPaneProperties.getVerticalScrollBar().setUnitIncrement(16);
        view.scrollPaneProperties.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        view.setTitle("SmartRent - Renter Dashboard");
        view.setSize(1280, 800);
        view.setPreferredSize(new Dimension(1280, 800));
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        
        // Centralized Sidebar Styling
        LogoLoader.styleRenterSidebar(
                view,
                view.pnlSidebar,
                view.lblLogo,
                view.btnNavDashboard,
                view.btnNavMyApplications,
                view.btnNavPropertyRatings,
                view.btnNavSavedProperties,
                view.btnNavLogout,
                "dashboard"
        );
        
        try {
            User currentUser = SessionService.getInstance().getCurrentUser();
            if (currentUser != null) {
                view.lblWelcome.setText("Welcome, " + currentUser.getFullName().split(" ")[0]);
            }
        } catch (Exception e) { /* ignore */ }
        
        loadRenterProperties(view);
    }

    public void loadRenterProperties(view.RenterDashboardView view) {
        String location = view.txtLocation.getText().equals("Location") ? "" : view.txtLocation.getText();
        
        double maxPrice = 0;
        try {
            if (!view.txtMaxPrice.getText().equals("Max Price")) {
                maxPrice = Double.parseDouble(view.txtMaxPrice.getText());
            }
        } catch (NumberFormatException e) {
            // ignore
        }
        
        String bedrooms = view.cmbBedrooms.getSelectedItem().toString();
        if (bedrooms.equals("4+")) bedrooms = "4";
        
        String propertyType = view.cmbPropertyType.getSelectedItem().toString();
        if (propertyType.equals("Any")) propertyType = "";

        boolean hasFilters = !location.isEmpty() 
            || (maxPrice > 0) 
            || (!bedrooms.equals("Any")) 
            || (!propertyType.isEmpty() && !propertyType.equals("Any"));

        List<Property> properties = new java.util.ArrayList<>();
        try {
            properties = searchAvailableProperties(location, 0, maxPrice, bedrooms, propertyType);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        if (properties == null || properties.isEmpty()) {
            if (hasFilters) {
                properties = new java.util.ArrayList<>();
                JOptionPane.showMessageDialog(view, "No properties found matching your search criteria.", "No Results", JOptionPane.INFORMATION_MESSAGE);
            } else {
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
        }
        
        view.pnlCard1.setVisible(false);
        view.pnlCard2.setVisible(false);
        view.pnlCard3.setVisible(false);
        view.pnlCard4.setVisible(false);
        view.getPnlCard5().setVisible(false);
        view.getPnlCard6().setVisible(false);
        
        int count = Math.min(properties.size(), 6);
        for (int i = 0; i < count; i++) {
            Property p = properties.get(i);
            if (i == 0) configureCard(view, view.pnlCard1, p);
            else if (i == 1) configureCard(view, view.pnlCard2, p);
            else if (i == 2) configureCard(view, view.pnlCard3, p);
            else if (i == 3) configureCard(view, view.pnlCard4, p);
            else if (i == 4) configureCard(view, view.getPnlCard5(), p);
            else if (i == 5) configureCard(view, view.getPnlCard6(), p);
        }
        
        int containerWidth = view.pnlPropertiesGrid.getWidth();
        if (containerWidth <= 0) {
            containerWidth = 980;
        }
        int cardWidth = 220;
        int gap = 20;
        int cols = (containerWidth - gap) / (cardWidth + gap);
        if (cols <= 0) cols = 1;
        
        int rows = (int) Math.ceil(count / (double) cols);
        int prefHeight = rows * (280 + gap) + gap;
        view.pnlPropertiesGrid.setPreferredSize(new Dimension(containerWidth, Math.max(prefHeight, 600)));
        
        view.pnlPropertiesGrid.revalidate();
        view.pnlPropertiesGrid.repaint();
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
        User currentUser = SessionService.getInstance().getCurrentUser();
        
        // Centralized Sidebar Styling
        LogoLoader.styleRenterSidebar(
                view,
                view.pnlSidebar,
                view.lblLogo,
                view.btnNavDashboard,
                view.btnNavMyApplications,
                view.btnNavPropertyRatings,
                view.btnNavSavedProperties,
                view.btnNavLogout,
                "ratings"
        );
        
        if (currentUser != null && "RENTER".equals(currentUser.getRole())) {
            view.lblTitle.setText("Places You've Stayed");
            view.lblAvgRating.setText("History of all the places you've stayed in");
            
            // Hide review form
            view.lblAddRating.setVisible(false);
            view.cmbScore.setVisible(false);
            view.txtReview.setVisible(false);
            view.btnSubmitReview.setVisible(false);
            
            // Expand scroll pane
            view.scrollPaneRatings.setBounds(230, 100, 1000, 600);
            
            // Load stay history/leases
            DAO.LeaseDAO leaseDAO = new DAO.LeaseDAO();
            List<Model.Lease> leases = leaseDAO.getLeasesByRenter(currentUser.getUserId());
            String[] columns = {"Property", "Rating", "Action"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 2;
                }
            };
            
            DAO.RatingDAO ratingDAO = new DAO.RatingDAO();
            for (Model.Lease lease : leases) {
                Model.Rating r = ratingDAO.getRatingByRenterAndProperty(currentUser.getUserId(), lease.getPropertyId());
                String ratingStr = (r != null) ? getStarHtmlForScore(r.getScore()) : "Unrated";
                model.addRow(new Object[]{lease.getPropertyTitle(), ratingStr, "Rate"});
            }
            
            JTable table = view.tblRatings;
            table.setModel(model);
            table.setRowHeight(40);
            table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
            
            table.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
            table.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
                Model.Lease selectedLease = leases.get(row);
                openRateDialog(view, selectedLease.getPropertyId(), selectedLease.getPropertyTitle(), currentUser.getUserId(), () -> {
                    // Refresh table ratings
                    model.setRowCount(0);
                    for (Model.Lease l : leases) {
                        Model.Rating ratingVal = ratingDAO.getRatingByRenterAndProperty(currentUser.getUserId(), l.getPropertyId());
                        String rStr = (ratingVal != null) ? getStarHtmlForScore(ratingVal.getScore()) : "Unrated";
                        model.addRow(new Object[]{l.getPropertyTitle(), rStr, "Rate"});
                    }
                });
            }));
            
            view.scrollPaneRatings.setViewportView(table);
        } else {
            // Fallback for non-renters
            view.lblTitle.setText("Ratings for: " + propertyTitle);
            view.scrollPaneRatings.setViewportView(view.pnlRatingsList);
            view.pnlRatingsList.setLayout(new javax.swing.BoxLayout(view.pnlRatingsList, javax.swing.BoxLayout.Y_AXIS));
            view.scrollPaneRatings.setBounds(230, 100, 750, 450);
            
            view.lblAddRating.setVisible(false);
            view.cmbScore.setVisible(false);
            view.txtReview.setVisible(false);
            view.btnSubmitReview.setVisible(false);
            
            if (propertyId != -1) {
                loadRatings(view, propertyId);
            } else {
                view.lblAvgRating.setText("Go to the Dashboard to select a property.");
                view.pnlRatingsList.removeAll();
                view.pnlRatingsList.revalidate();
                view.pnlRatingsList.repaint();
            }
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
        view.lblAvgRating.setText(getStarHtml(avg));
        
        view.pnlRatingsList.removeAll();
        List<Model.Rating> ratings = getPropertyRatings(propertyId);
        
        if (ratings.isEmpty()) {
            JLabel emptyLbl = new JLabel("No ratings yet for this property.");
            emptyLbl.setFont(new java.awt.Font("Segoe UI", java.awt.Font.ITALIC, 14));
            view.pnlRatingsList.add(emptyLbl);
        }
        
        for (Model.Rating r : ratings) {
            JPanel card = new JPanel();
            card.setLayout(null);
            card.setPreferredSize(new Dimension(500, 80));
            card.setMaximumSize(new Dimension(500, 80));
            card.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
            
            JLabel lblName = new JLabel("<html><b>" + r.getRenterName() + "</b> &nbsp;&nbsp;" + getStarHtmlForScore(r.getScore()) + "</html>");
            lblName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
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
            
            view.pnlRatingsList.add(card);
            view.pnlRatingsList.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        view.pnlRatingsList.revalidate();
        view.pnlRatingsList.repaint();
    }

    public void submitRating(PropertyRatingsView view, int propertyId) {
        if (propertyId == -1) return;
        
        int scoreIndex = view.cmbScore.getSelectedIndex();
        int score = 5 - scoreIndex; // 0=5, 1=4, 2=3, 3=2, 4=1
        String review = view.txtReview.getText();
        
        if (review.equals("Write your review here...") || review.trim().isEmpty()) {
            review = "";
        }
        
        String result = addRating(propertyId, score, review);
        if (result.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(view, "Thank you for your review!");
            view.txtReview.setText("Write your review here...");
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
        LogoLoader.styleOwnerSidebar(view, view.pnlSidebar, view.lblLogo, view.btnNavDashboard, view.btnNavMyProperties, view.btnNavLeaseManagement, view.btnNavLogout, "properties");
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser != null) {
            view.lblWelcome.setText("Welcome, " + currentUser.getFullName().split(" ")[0]);
        }
        
        loadMyProperties(view);
        
        view.setSize(1280, 800);
        view.setResizable(false);
        view.setLocationRelativeTo(null);
    }

    public void loadMyProperties(MyPropertiesView view) {
        view.pnlTableBody.removeAll();
        List<Property> properties = getOwnerProperties();
        
        for (Property p : properties) {
            JPanel row = new JPanel();
            row.setLayout(null);
            row.setPreferredSize(new Dimension(980, 60));
            row.setMaximumSize(new Dimension(980, 60));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);
            row.setBackground(Color.WHITE);
            row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(240, 240, 240)));

            JLabel lblTitleText = new JLabel(p.getTitle());
            lblTitleText.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblTitleText.setForeground(new Color(43, 108, 176));
            lblTitleText.setBounds(15, 15, 280, 30);
            row.add(lblTitleText);

            JLabel lblLoc = new JLabel(p.getAddress());
            lblLoc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lblLoc.setForeground(new Color(74, 85, 104));
            lblLoc.setBounds(310, 15, 200, 30);
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
            lblStatusBadge.setBounds(530, 15, 100, 30);
            row.add(lblStatusBadge);

            JButton btnEdit = new JButton("Edit");
            btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 12));
            btnEdit.setBackground(new Color(43, 108, 176));
            btnEdit.setForeground(Color.WHITE);
            btnEdit.setFocusPainted(false);
            btnEdit.setBounds(650, 15, 80, 30);
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
            btnDelete.setBounds(740, 15, 80, 30);
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
            lblDateText.setBounds(850, 15, 120, 30);
            row.add(lblDateText);

            view.pnlTableBody.add(row);
        }

        view.pnlTableBody.revalidate();
        view.pnlTableBody.repaint();
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
        LogoLoader.styleOwnerSidebar(view, view.pnlSidebar, view.lblLogo, view.btnNavDashboard, view.btnNavMyProperties, view.btnNavLeaseManagement, view.btnNavLogout, "properties");
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser != null) {
            view.lblWelcome.setText("Welcome, " + currentUser.getFullName().split(" ")[0]);
        }
        
        view.cmbPropertyType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Apartment", "House", "Studio" }));

        refreshImagePreviews(view);
        
        view.txtAvailableFrom.setText(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
        
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
        view.pnlCard.add(btnDelete);
        view.pnlCard.revalidate();
        view.pnlCard.repaint();
    }

    public void loadPropertyData(AddProperty view, int loadedPropertyId) {
        Property p = getPropertyById(loadedPropertyId);
        if (p != null) {
            view.lblAddPropertyTitle.setText("Edit Property");
            view.txtPropTitle.setText(p.getTitle());
            view.txtAddress.setText(p.getAddress());
            view.cmbPropertyType.setSelectedItem(p.getPropertyType());
            view.txtBedrooms.setText(String.valueOf(p.getBedrooms()));
            view.txtBathrooms.setText(String.valueOf(p.getBathrooms()));
            view.txtRent.setText(String.valueOf(p.getMonthlyRent()));
            view.txtDeposit.setText(String.valueOf(p.getDeposit()));
            
            String dateStr = new SimpleDateFormat("MM/dd/yyyy").format(p.getAvailableFrom() != null ? p.getAvailableFrom() : new Date());
            view.txtAvailableFrom.setText(dateStr);
            
            view.btnSave.setText("Update Property");
            
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
                view.lblError.setText(result);
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
        String title = view.txtPropTitle.getText().trim();
        String address = view.txtAddress.getText().trim();
        String type = view.cmbPropertyType.getSelectedItem().toString();
        String bed = view.txtBedrooms.getText().trim();
        String bath = view.txtBathrooms.getText().trim();
        String rent = view.txtRent.getText().trim();
        String deposit = view.txtDeposit.getText().trim();
        String availStr = view.txtAvailableFrom.getText().trim();

        if (title.isEmpty() || address.isEmpty() || bed.isEmpty() || bath.isEmpty() || rent.isEmpty() || deposit.isEmpty()) {
            view.lblError.setText("Please fill out all required fields.");
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
            result = updateProperty(loadedPropertyId, title, address, type, bed, bath, rent, deposit, availDate, view.getSelectedImagePaths(), 0);
        }
        
        if (result.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(view, loadedPropertyId == -1 ? "Property added successfully!" : "Property updated successfully!");
            new MyPropertiesView().setVisible(true);
            view.dispose();
        } else {
            view.lblError.setText(result);
        }
    }

    public void refreshImagePreviews(AddProperty view) {
        view.pnlSlot1.setVisible(false);
        view.pnlSlot2.setVisible(false);
        view.pnlSlot3.setVisible(false);
        view.pnlSlot4.setVisible(false);
        view.pnlSlot5.setVisible(false);

        int count = view.getSelectedImagePaths().size();
        view.lblImagesStatus.setText(count + " Images Selected");

        if (count > 0) {
            setupSlot(view.pnlSlot1, view.lblPreview1, view.getSelectedImagePaths().get(0));
            view.pnlSlot1.setVisible(true);
        }
        if (count > 1) {
            setupSlot(view.pnlSlot2, view.lblPreview2, view.getSelectedImagePaths().get(1));
            view.pnlSlot2.setVisible(true);
        }
        if (count > 2) {
            setupSlot(view.pnlSlot3, view.lblPreview3, view.getSelectedImagePaths().get(2));
            view.pnlSlot3.setVisible(true);
        }
        if (count > 3) {
            setupSlot(view.pnlSlot4, view.lblPreview4, view.getSelectedImagePaths().get(3));
            view.pnlSlot4.setVisible(true);
        }
        if (count > 4) {
            setupSlot(view.pnlSlot5, view.lblPreview5, view.getSelectedImagePaths().get(4));
            view.pnlSlot5.setVisible(true);
        }
        
        view.pnlImagesPreview.revalidate();
        view.pnlImagesPreview.repaint();
    }

    private void setupSlot(JPanel slot, JLabel label, String path) {
        try {
            java.io.File file = resolveFile(path);
            String resolved = (file != null && file.exists()) ? file.getAbsolutePath() : path;
            ImageIcon icon = new ImageIcon(resolved);
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
        // Centralized Sidebar Styling
        LogoLoader.styleRenterSidebar(
                view,
                view.pnlSidebar,
                view.lblLogo,
                view.btnNavDashboard,
                view.btnNavMyApplications,
                view.btnNavPropertyRatings,
                view.btnNavSavedProperties,
                view.btnNavLogout,
                ""
        );
        view.setSize(1280, 800);
        view.setResizable(false);
        view.setLocationRelativeTo(null);

        Property p = getPropertyById(propertyId);
        if (p == null) {
            javax.swing.JOptionPane.showMessageDialog(view, "Property not found.");
            return;
        }

        // Set property info
        view.lblTitle.setText(p.getTitle());
        view.lblAddress.setText("\uD83D\uDCCD " + p.getAddress());
        view.lblPropertyType.setText(p.getPropertyType() != null ? p.getPropertyType() : "N/A");
        view.lblBedrooms.setText(p.getBedrooms() + " Bedrooms");
        view.lblBathrooms.setText(p.getBathrooms() + " Bathrooms");
        view.lblRent.setText("Rs. " + String.format("%,.0f", p.getMonthlyRent()) + " / month");
        view.lblDeposit.setText("Deposit: Rs. " + String.format("%,.0f", p.getDeposit()));
        view.lblAvailableFrom.setText("Available from: " + (p.getAvailableFrom() != null ? new java.text.SimpleDateFormat("MMM dd, yyyy").format(p.getAvailableFrom()) : "Now"));
        view.lblStatus.setText(p.getPropStatus() != null ? p.getPropStatus() : "AVAILABLE");
        view.lblRating.setText(getStarHtml(p.getAvgRating()));

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
            view.lblImageCounter.setText("1 / " + imageList.size());
        }

        // Set up thumbnails
        JLabel[] thumbs = {view.thumbnail1, view.thumbnail2, view.thumbnail3, view.thumbnail4};
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
                        view.lblImageCounter.setText((idx + 1) + " / " + imageList.size());
                    }
                });
                thumbs[i].setVisible(true);
            } else {
                thumbs[i].setVisible(false);
            }
        }

        // Arrow buttons
        view.btnPrevImage.addActionListener(e -> {
            if (currentIndex[0] > 0) {
                currentIndex[0]--;
                showImage(view, imageList, currentIndex[0]);
                view.lblImageCounter.setText((currentIndex[0] + 1) + " / " + imageList.size());
            }
        });
        view.btnNextImage.addActionListener(e -> {
            if (currentIndex[0] < imageList.size() - 1) {
                currentIndex[0]++;
                showImage(view, imageList, currentIndex[0]);
                view.lblImageCounter.setText((currentIndex[0] + 1) + " / " + imageList.size());
            }
        });

        // Action buttons
        view.btnApply.addActionListener(e -> {
            new view.ApplicationFormView(propertyId).setVisible(true);
            view.dispose();
        });
        view.btnSave.addActionListener(e -> {
            SavedPropertyController savedCtrl = new SavedPropertyController();
            if (savedCtrl.saveProperty(propertyId)) {
                javax.swing.JOptionPane.showMessageDialog(view, "Property saved!");
            }
        });
        view.btnBack.addActionListener(e -> {
            new RenterDashboardView().setVisible(true);
            view.dispose();
        });

        // Sidebar navigation
        view.btnNavDashboard.addActionListener(e -> { new RenterDashboardView().setVisible(true); view.dispose(); });
        view.btnNavMyApplications.addActionListener(e -> { new MyApplicationView().setVisible(true); view.dispose(); });
        view.btnNavPropertyRatings.addActionListener(e -> { new PropertyRatingsView().setVisible(true); view.dispose(); });
        view.btnNavSavedProperties.addActionListener(e -> { new SavedPropertiesView().setVisible(true); view.dispose(); });
        view.btnNavLogout.addActionListener(e -> { SessionService.getInstance().logout(); new LoginView().setVisible(true); view.dispose(); });
    }

    public static java.io.File getProjectRoot() {
        java.io.File currentDir = new java.io.File(System.getProperty("user.dir"));
        while (currentDir != null) {
            if (new java.io.File(currentDir, "build.xml").exists() || new java.io.File(currentDir, "src").exists()) {
                return currentDir;
            }
            currentDir = currentDir.getParentFile();
        }
        return new java.io.File(System.getProperty("user.dir"));
    }

    public static java.io.File resolveFile(String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        java.io.File f = new java.io.File(path);
        if (f.exists()) {
            return f;
        }
        java.io.File projectRoot = getProjectRoot();
        String rel = path;
        int idx = path.indexOf("uploaded_images");
        if (idx != -1) {
            rel = path.substring(idx);
        }
        java.io.File candidate = new java.io.File(projectRoot, rel);
        if (candidate.exists()) {
            return candidate;
        }
        java.io.File subuFallback = new java.io.File("C:\\Users\\Subechha Karki\\Documents\\NetBeansProjects\\SmartRent", rel);
        if (subuFallback.exists()) {
            return subuFallback;
        }
        return f;
    }

    private void showImage(PropertyDetailView view, List<String> images, int index) {
        if (index >= 0 && index < images.size()) {
            String path = images.get(index);
            java.io.File file = resolveFile(path);
            if (file != null && file.exists()) {
                javax.swing.ImageIcon icon = new javax.swing.ImageIcon(file.getAbsolutePath());
                java.awt.Image img = icon.getImage().getScaledInstance(580, 340, java.awt.Image.SCALE_SMOOTH);
                view.lblMainImage.setIcon(new javax.swing.ImageIcon(img));
                view.lblMainImage.setText("");
            } else {
                java.net.URL imgUrl = getClass().getResource("/Images/Gemini_Generated_Image_enyzbenyzbe.png");
                if (imgUrl != null) {
                    javax.swing.ImageIcon icon = new javax.swing.ImageIcon(imgUrl);
                    java.awt.Image img = icon.getImage().getScaledInstance(580, 340, java.awt.Image.SCALE_SMOOTH);
                    view.lblMainImage.setIcon(new javax.swing.ImageIcon(img));
                    view.lblMainImage.setText("");
                } else {
                    view.lblMainImage.setIcon(null);
                    view.lblMainImage.setText("<html><div style='text-align:center;color:white;padding-top:140px;font-size:16px;'>No Image Available</div></html>");
                }
            }
        }
    }

    private void loadThumbnail(JLabel lbl, String path) {
        java.io.File file = resolveFile(path);
        if (file != null && file.exists()) {
            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(file.getAbsolutePath());
            java.awt.Image img = icon.getImage().getScaledInstance(120, 70, java.awt.Image.SCALE_SMOOTH);
            lbl.setIcon(new javax.swing.ImageIcon(img));
            lbl.setText("");
        } else {
            java.net.URL imgUrl = getClass().getResource("/Images/Gemini_Generated_Image_enyzbenyzbe.png");
            if (imgUrl != null) {
                javax.swing.ImageIcon icon = new javax.swing.ImageIcon(imgUrl);
                java.awt.Image img = icon.getImage().getScaledInstance(120, 70, java.awt.Image.SCALE_SMOOTH);
                lbl.setIcon(new javax.swing.ImageIcon(img));
                lbl.setText("");
            }
        }
    }

    private void openRateDialog(JFrame parent, int propertyId, String propertyTitle, int renterId, Runnable onSuccess) {
        JDialog dialog = new JDialog(parent, "Rate Property", true);
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Rate Property: " + propertyTitle);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        dialog.add(lblTitle, gbc);

        JLabel lblScore = new JLabel("Rating:");
        lblScore.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        dialog.add(lblScore, gbc);

        JComboBox<String> cmbScore = new JComboBox<>(new String[] {
            "5 - Excellent", "4 - Good", "3 - Average", "2 - Poor", "1 - Terrible"
        });
        gbc.gridx = 1;
        dialog.add(cmbScore, gbc);

        JLabel lblReview = new JLabel("Review:");
        lblReview.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(lblReview, gbc);

        JTextArea txtReview = new JTextArea(4, 25);
        txtReview.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtReview.setLineWrap(true);
        txtReview.setWrapStyleWord(true);
        txtReview.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        // Fetch existing rating if any to pre-fill
        DAO.RatingDAO ratingDAO = new DAO.RatingDAO();
        Model.Rating existing = ratingDAO.getRatingByRenterAndProperty(renterId, propertyId);
        if (existing != null) {
            cmbScore.setSelectedIndex(5 - existing.getScore());
            txtReview.setText(existing.getReviewText());
        } else {
            txtReview.setText("Write your review here...");
            txtReview.setForeground(Color.GRAY);
        }

        // Add focus listener for placeholder
        txtReview.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtReview.getText().equals("Write your review here...")) {
                    txtReview.setText("");
                    txtReview.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtReview.getText().isEmpty()) {
                    txtReview.setText("Write your review here...");
                    txtReview.setForeground(Color.GRAY);
                }
            }
        });

        JScrollPane scrollReview = new JScrollPane(txtReview);
        gbc.gridx = 1;
        dialog.add(scrollReview, gbc);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlButtons.setBackground(Color.WHITE);
        JButton btnCancel = new JButton("Cancel");
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(new Color(30, 92, 240));
        btnSubmit.setForeground(Color.WHITE);

        btnCancel.addActionListener(e -> dialog.dispose());
        btnSubmit.addActionListener(e -> {
            int scoreIndex = cmbScore.getSelectedIndex();
            int score = 5 - scoreIndex;
            String review = txtReview.getText();
            if (review.equals("Write your review here...") || review.trim().isEmpty()) {
                review = "";
            }
            
            Model.Rating r = new Model.Rating();
            r.setPropertyId(propertyId);
            r.setRenterId(renterId);
            r.setScore(score);
            r.setReviewText(review);
            
            boolean success = ratingDAO.saveOrUpdateRating(r);
            if (success) {
                JOptionPane.showMessageDialog(dialog, "Thank you for your review!");
                dialog.dispose();
                onSuccess.run();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to save rating.");
            }
        });

        pnlButtons.add(btnCancel);
        pnlButtons.add(btnSubmit);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        dialog.add(pnlButtons, gbc);

        dialog.pack();
        dialog.setSize(450, 320);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "Rate" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private int selectedRow;

    public ButtonEditor(JCheckBox checkBox, java.util.function.Consumer<Integer> onAction) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> {
            fireEditingStopped();
            onAction.accept(selectedRow);
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        this.selectedRow = row;
        label = (value == null) ? "Rate" : value.toString();
        button.setText(label);
        return button;
    }

    public Object getCellEditorValue() {
        return label;
    }
}

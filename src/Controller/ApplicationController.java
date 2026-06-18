package Controller;

import DAO.ApplicationDAO;
import DAO.UserDAO;
import DAO.PropertyDAO;
import DAO.LeaseDAO;
import Model.RentalApplication;
import Model.Property;
import Model.Lease;
import Model.User;
import Controller.SessionService;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class ApplicationController {

    private ApplicationDAO applicationDAO;
    private UserDAO userDAO;

    public ApplicationController() {
        this.applicationDAO = new ApplicationDAO();
        this.userDAO = new UserDAO();
    }

    public String submitApplication(int propertyId, Date moveInDate, String coverMessage) {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null || !"RENTER".equals(currentUser.getRole())) {
            return "Unauthorized. Only renters can submit applications.";
        }
        
        if (moveInDate == null) {
            return "Please select a move-in date.";
        }
        
        if (moveInDate.before(new Date())) {
            return "Move-in date cannot be in the past.";
        }

        RentalApplication app = new RentalApplication();
        app.setRenterId(currentUser.getUserId());
        app.setPropertyId(propertyId);
        app.setMoveInDate(moveInDate);
        app.setCoverMessage(coverMessage);

        return applicationDAO.createApplication(app);
    }

    public List<RentalApplication> getOwnerApplications() {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null || !"PROPERTY_OWNER".equals(currentUser.getRole())) {
            return new java.util.ArrayList<>();
        }
        return applicationDAO.getApplicationsByOwner(currentUser.getUserId());
    }

    public List<RentalApplication> getRenterApplications() {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null || !"RENTER".equals(currentUser.getRole())) {
            return new java.util.ArrayList<>();
        }
        return applicationDAO.getApplicationsByRenter(currentUser.getUserId());
    }

    public boolean approveApplication(int applicationId) {
        RentalApplication app = applicationDAO.getApplicationById(applicationId);
        if (app == null) return false;

        PropertyDAO propertyDAO = new PropertyDAO();
        Property prop = propertyDAO.getPropertyById(app.getPropertyId());
        if (prop == null) return false;

        // Create the formal Lease
        Lease lease = new Lease();
        lease.setApplicationId(app.getApplicationId());
        lease.setPropertyId(prop.getPropertyId());
        lease.setRenterId(app.getRenterId());
        lease.setOwnerId(prop.getOwnerId());
        lease.setMonthlyRent(prop.getMonthlyRent());
        lease.setDeposit(prop.getDeposit());
        lease.setStartDate(app.getMoveInDate());
        
        // Default end date is 1 year from start date
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(app.getMoveInDate());
        cal.add(java.util.Calendar.YEAR, 1);
        lease.setEndDate(cal.getTime());
        
        lease.setTerms("Standard 1-year residential lease agreement.");
        lease.setStatus("ACTIVE");

        LeaseDAO leaseDAO = new LeaseDAO();
        if (leaseDAO.createLease(lease)) {
            // Populate lease renter/owner names and property title for PDF layout
            User renterUser = new UserDAO().getUserById(app.getRenterId());
            User ownerUser = new UserDAO().getUserById(prop.getOwnerId());
            lease.setRenterName(renterUser != null ? renterUser.getFullName() : "Renter");
            lease.setOwnerName(ownerUser != null ? ownerUser.getFullName() : "Owner");
            lease.setPropertyTitle(prop.getTitle());

            // Automatically generate a local PDF copy of the agreement
            String dest = "leases/Lease_Agreement_" + lease.getLeaseId() + ".pdf";
            LeaseController.generateLeasePDF(lease, dest);

            // Mark application as APPROVED
            applicationDAO.updateApplicationStatus(applicationId, "APPROVED", "");
            // Auto-update property status to OCCUPIED
            propertyDAO.updatePropertyStatus(prop.getPropertyId(), "OCCUPIED");
            return true;
        }
        
        return false;
    }

    public boolean rejectApplication(int applicationId, String note) {
        return applicationDAO.updateApplicationStatus(applicationId, "REJECTED", note);
    }

    // OwnerDashboardView management
    public void initOwnerDashboard(OwnerDashboardView view) {
        LogoLoader.styleOwnerSidebar(view, view.pnlSidebar, view.lblLogo, view.btnNavDashboard, view.btnNavMyProperties, view.btnNavLeaseManagement, view.btnNavLogout, "dashboard");
        view.scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser != null) {
            view.lblWelcome.setText("Welcome, " + currentUser.getFullName().split(" ")[0]);
        }
        
        loadStats(view);
        loadApplications(view);
        
        view.setSize(1280, 800);
        view.setResizable(false);
        view.setLocationRelativeTo(null);
    }

    public void loadStats(OwnerDashboardView view) {
        PropertyController propertyController = new PropertyController();
        int totalProps = propertyController.getOwnerProperties().size();
        List<RentalApplication> apps = getOwnerApplications();
        int pendingApps = 0;
        for (RentalApplication a : apps) {
            if ("SUBMITTED".equals(a.getAppStatus())) {
                pendingApps++;
            }
        }
        
        view.lblStatProperties.setText("<html>Total Properties<br><font size='5'>" + totalProps + "</font> Properties</html>");
        view.lblStatApplications.setText("<html>Pending Applications<br><font size='5'>" + pendingApps + "</font> Applications</html>");
        view.lblStatLeases.setText("<html>Pending Approvals<br><font size='5'>" + pendingApps + "</font> Pending</html>");
    }

    public void loadApplications(OwnerDashboardView view) {
        List<RentalApplication> apps = getOwnerApplications();
        view.pnlTableBody.removeAll();
        
        for (RentalApplication app : apps) {
            JPanel row = new JPanel(null);
            row.setBackground(Color.WHITE);
            row.setPreferredSize(new Dimension(980, 60));
            row.setMaximumSize(new Dimension(980, 60));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            // User Name
            JLabel lblUser = new JLabel(app.getRenterName());
            lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblUser.setBounds(20, 15, 180, 30);
            row.add(lblUser);
            
            // Property
            JLabel lblProp = new JLabel(app.getPropertyTitle());
            lblProp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lblProp.setBounds(220, 15, 180, 30);
            row.add(lblProp);
            
            // Email
            User renter = userDAO.getUserById(app.getRenterId());
            String email = renter != null ? renter.getEmail() : "N/A";
            JLabel lblEmail = new JLabel(email);
            lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lblEmail.setForeground(Color.GRAY);
            lblEmail.setBounds(420, 15, 200, 30);
            row.add(lblEmail);
            
            // Status Badge
            JLabel lblStatus = new JLabel(app.getAppStatus());
            lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 11));
            lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
            lblStatus.setForeground(Color.WHITE);
            lblStatus.setOpaque(true);
            if ("SUBMITTED".equals(app.getAppStatus())) {
                lblStatus.setBackground(new Color(243, 112, 33)); // Orange
                lblStatus.setText("Pending");
            } else if ("APPROVED".equals(app.getAppStatus())) {
                lblStatus.setBackground(new Color(64, 160, 69)); // Green
                lblStatus.setText("Approved");
            } else {
                lblStatus.setBackground(new Color(210, 50, 50)); // Red
                lblStatus.setText("Rejected");
            }
            lblStatus.setBounds(640, 15, 100, 30);
            row.add(lblStatus);
            
            // Actions
            int btnX = 760;
            
            // View Button
            JButton btnView = new JButton("View");
            btnView.setBackground(new Color(108, 117, 125));
            btnView.setForeground(Color.WHITE);
            btnView.setFont(new Font("Segoe UI", Font.BOLD, 11));
            btnView.setMargin(new Insets(2,2,2,2));
            btnView.setBounds(btnX, 15, 50, 30);
            btnView.addActionListener(e -> {
                java.util.Map<String, String> details = getApplicationDetailsMap(app);
                new ApplicationDetailsView(details).setVisible(true);
            });
            row.add(btnView);
            btnX += 55;
            
            if ("SUBMITTED".equals(app.getAppStatus())) {
                JButton btnApprove = new JButton("Approve");
                btnApprove.setBackground(new Color(30, 92, 240));
                btnApprove.setForeground(Color.WHITE);
                btnApprove.setFont(new Font("Segoe UI", Font.BOLD, 11));
                btnApprove.setMargin(new Insets(2,2,2,2));
                btnApprove.setBounds(btnX, 15, 65, 30);
                btnApprove.addActionListener(e -> {
                    if (approveApplication(app.getApplicationId())) {
                        JOptionPane.showMessageDialog(view, "Application Approved.");
                        loadApplications(view);
                        loadStats(view);
                    }
                });
                row.add(btnApprove);
                btnX += 70;
                
                JButton btnReject = new JButton("Reject");
                btnReject.setBackground(new Color(210, 50, 50));
                btnReject.setForeground(Color.WHITE);
                btnReject.setFont(new Font("Segoe UI", Font.BOLD, 11));
                btnReject.setMargin(new Insets(2,2,2,2));
                btnReject.setBounds(btnX, 15, 55, 30);
                btnReject.addActionListener(e -> {
                    String note = JOptionPane.showInputDialog(view, "Enter rejection reason:");
                    if (note != null && rejectApplication(app.getApplicationId(), note)) {
                        JOptionPane.showMessageDialog(view, "Application Rejected.");
                        loadApplications(view);
                        loadStats(view);
                    }
                });
                row.add(btnReject);
            } else {
                JButton btnEdit = new JButton("Edit");
                btnEdit.setBackground(new Color(64, 160, 69));
                btnEdit.setForeground(Color.WHITE);
                btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 11));
                btnEdit.setMargin(new Insets(2,2,2,2));
                btnEdit.setBounds(btnX, 15, 50, 30);
                btnEdit.addActionListener(e -> {
                    JOptionPane.showMessageDialog(view, "Edit action coming soon!");
                });
                row.add(btnEdit);
            }
            
            row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
            view.pnlTableBody.add(row);
        }
        
        view.pnlTableBody.revalidate();
        view.pnlTableBody.repaint();
    }

    public java.util.Map<String, String> getApplicationDetailsMap(Model.RentalApplication app) {
        java.util.Map<String, String> details = new java.util.HashMap<>();
        
        Model.User renter = userDAO.getUserById(app.getRenterId());
        details.put("renterName", renter != null ? renter.getFullName() : app.getRenterName());
        details.put("renterEmail", renter != null ? renter.getEmail() : "N/A");
        details.put("renterPhone", renter != null ? renter.getPhone() : "N/A");
        
        String moveInDateStr = app.getMoveInDate() != null 
            ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(app.getMoveInDate()) 
            : "N/A";
        details.put("moveInDate", moveInDateStr);
        
        String duration = "N/A";
        String dob = "N/A";
        String employer = "N/A";
        String jobTitle = "N/A";
        String income = "N/A";

        if (app.getCoverMessage() != null) {
            String[] lines = app.getCoverMessage().split("\n");
            for (String line : lines) {
                if (line.startsWith("Lease Duration:")) {
                    duration = line.substring("Lease Duration:".length()).trim();
                } else if (line.startsWith("DOB:")) {
                    dob = line.substring("DOB:".length()).trim();
                } else if (line.startsWith("Employer:")) {
                    employer = line.substring("Employer:".length()).trim();
                } else if (line.startsWith("Job Title:")) {
                    jobTitle = line.substring("Job Title:".length()).trim();
                } else if (line.startsWith("Income:")) {
                    income = line.substring("Income:".length()).trim();
                }
            }
        }
        details.put("duration", duration);
        details.put("dob", dob);
        details.put("employer", employer);
        details.put("jobTitle", jobTitle);
        details.put("income", income);
        
        return details;
    }

    public void addProperty(OwnerDashboardView view) {
        new AddProperty().setVisible(true);
        view.dispose();
    }

    public void navigateToMyProperties(OwnerDashboardView view) {
        new MyPropertiesView().setVisible(true);
        view.dispose();
    }

    public void navigateToLeaseManagement(OwnerDashboardView view) {
        new LeaseManagementView().setVisible(true);
        view.dispose();
    }

    public void logout(OwnerDashboardView view) {
        SessionService.getInstance().logout();
        new LoginView().setVisible(true);
        view.dispose();
    }

    // ApplicationFormView & ApplicationFormStep2View methods
    public void initApplicationForm(ApplicationFormView view, int propertyId) {
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
        PropertyDAO pDao = new PropertyDAO();
        Property property = pDao.getPropertyById(propertyId);
        if (property != null) {
            view.lblPropTitle.setText(property.getTitle() != null ? property.getTitle() : "Unknown Property");
            view.lblPropLocation.setText(property.getAddress() != null ? property.getAddress() : "Location");
            view.lblPropPrice.setText("Rs. " + String.format("%.0f", property.getMonthlyRent()) + "/mo");
            view.lblPropDetails.setText(property.getBedrooms() + " Bed Property");
            
            if (property.getPrimaryImagePath() != null && !property.getPrimaryImagePath().isEmpty()) {
                java.io.File file = Controller.PropertyController.resolveFile(property.getPrimaryImagePath());
                if (file != null && file.exists()) {
                    javax.swing.ImageIcon icon = new javax.swing.ImageIcon(file.getAbsolutePath());
                    java.awt.Image img = icon.getImage().getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH);
                    view.lblPropImage.setIcon(new javax.swing.ImageIcon(img));
                    view.lblPropImage.setText("");
                } else {
                    setFallbackFormImage(view.lblPropImage, 150, 100);
                }
            } else {
                setFallbackFormImage(view.lblPropImage, 150, 100);
            }
        }
        
        User user = SessionService.getInstance().getCurrentUser();
        if (user != null) {
            view.txtFullName.setText(user.getFullName() != null ? user.getFullName() : "");
            view.txtPhone.setText(user.getPhone() != null ? user.getPhone() : "");
            view.txtEmail.setText(user.getEmail() != null ? user.getEmail() : "");
        }
    }

    public void nextStep(ApplicationFormView view, int propertyId) {
        String dateStr = view.txtMoveInDate.getText();
        
        Date moveInDate = null;
        try {
            moveInDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (java.text.ParseException ex) {
            JOptionPane.showMessageDialog(view, "Invalid Move-in Date format. Use YYYY-MM-DD.");
            return;
        }

        if (view.getStep2View() == null) {
            ApplicationFormStep2View step2 = new ApplicationFormStep2View(view);
            view.setStep2View(step2);
        }
        view.setVisible(false);
        view.getStep2View().setVisible(true);
    }

    public void initStep2(ApplicationFormStep2View view, int propertyId) {
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
        PropertyDAO pDao = new PropertyDAO();
        Property property = pDao.getPropertyById(propertyId);
        if (property != null) {
            view.lblPropTitle.setText(property.getTitle() != null ? property.getTitle() : "Unknown Property");
            view.lblPropLocation.setText(property.getAddress() != null ? property.getAddress() : "Location");
            view.lblPropPrice.setText("Rs. " + String.format("%.0f", property.getMonthlyRent()) + "/mo");
            view.lblPropDetails.setText(property.getBedrooms() + " Bed Property");
            
            if (property.getPrimaryImagePath() != null && !property.getPrimaryImagePath().isEmpty()) {
                java.io.File file = Controller.PropertyController.resolveFile(property.getPrimaryImagePath());
                if (file != null && file.exists()) {
                    javax.swing.ImageIcon icon = new javax.swing.ImageIcon(file.getAbsolutePath());
                    java.awt.Image img = icon.getImage().getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH);
                    view.lblPropImage.setIcon(new javax.swing.ImageIcon(img));
                    view.lblPropImage.setText("");
                } else {
                    setFallbackFormImage(view.lblPropImage, 150, 100);
                }
            } else {
                setFallbackFormImage(view.lblPropImage, 150, 100);
            }
        }
    }

    public void goBackToStep1(ApplicationFormStep2View view, int propertyId) {
        view.setVisible(false);
        view.getStep1View().setVisible(true);
    }

    public void submitApplicationFromStep2(ApplicationFormStep2View step2View) {
        ApplicationFormView step1View = step2View.getStep1View();
        String dateStr = step1View.txtMoveInDate.getText();
        Date moveInDate = null;
        try {
            moveInDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (java.text.ParseException ex) {
            JOptionPane.showMessageDialog(step2View, "Invalid Move-in Date format. Use YYYY-MM-DD.");
            return;
        }

        String step1Data = "Lease Duration: " + step1View.txtLeaseDuration.getText() + " months\n" +
                           "DOB: " + step1View.txtDOB.getText() + "\n";

        String extraInfo = step1Data + 
                           "Employer: " + step2View.txtEmployer.getText() + "\n" +
                           "Job Title: " + step2View.txtJobTitle.getText() + "\n" +
                           "Income: " + step2View.txtIncome.getText();

        String result = submitApplication(step1View.getPropertyId(), moveInDate, extraInfo);
        
        if (result.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(step2View, "Application submitted successfully!");
            step2View.dispose();
            step1View.dispose();
        } else {
            JOptionPane.showMessageDialog(step2View, result);
        }
    }

    public void browseProof(ApplicationFormStep2View view) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(view);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            view.txtProof.setText(selectedFile.getAbsolutePath());
        }
    }

    // Sidebar navigation helpers for Renter Applications view pages:
    public void navigateToDashboard(JFrame view) {
        new RenterDashboardView().setVisible(true);
        view.dispose();
    }

    public void navigateToMyApplications(JFrame view) {
        new MyApplicationView().setVisible(true);
        view.dispose();
    }

    public void navigateToPropertyRatings(JFrame view) {
        new PropertyRatingsView().setVisible(true);
        view.dispose();
    }

    public void navigateToSavedProperties(JFrame view) {
        new SavedPropertiesView().setVisible(true);
        view.dispose();
    }

    public void logout(JFrame view) {
        SessionService.getInstance().logout();
        new LoginView().setVisible(true);
        view.dispose();
    }

    // MyApplicationView logic
    public void initMyApplicationsView(MyApplicationView view) {
        LogoLoader.styleRenterSidebar(
                view,
                view.pnlSidebar,
                view.lblLogo,
                view.btnNavDashboard,
                view.btnNavMyApplications,
                view.btnNavPropertyRatings,
                view.btnNavSavedProperties,
                view.btnNavLogout,
                "applications"
        );

        User currentUser = SessionService.getInstance().getCurrentUser();
        String firstName = currentUser != null ? currentUser.getFullName().split(" ")[0] : "User";
        view.lblWelcome.setText("Welcome, " + firstName);

        view.pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

        loadMyApplications(view);
        
        view.setSize(1280, 800);
        view.setPreferredSize(new Dimension(1280, 800));
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.revalidate();
        view.repaint();
    }

    public void loadMyApplications(MyApplicationView view) {
        List<RentalApplication> apps = getRenterApplications();
        view.pnlAppsList.removeAll();
        
        for (RentalApplication a : apps) {
            view.pnlAppsList.add(createAppRow(view, a));
        }
        
        view.pnlAppsList.revalidate();
        view.pnlAppsList.repaint();
    }

    private JPanel createAppRow(MyApplicationView view, RentalApplication app) {
        JPanel row = new JPanel(null);
        row.setBackground(Color.WHITE);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        int height = 75;
        if ("APPROVED".equals(app.getAppStatus())) {
            height = 110;
        } else if ("REJECTED".equals(app.getAppStatus())) {
            height = 95;
        }
        
        row.setPreferredSize(new Dimension(980, height));
        row.setMinimumSize(new Dimension(980, height));
        row.setMaximumSize(new Dimension(980, height));
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(235, 238, 242)));
        
        // Property Name Column
        JLabel lblTitle = new JLabel(app.getPropertyTitle() != null ? app.getPropertyTitle() : "Unknown Property");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setForeground(new Color(30, 30, 30));
        lblTitle.setBounds(30, 15, 280, 22);
        row.add(lblTitle);
        
        String desc = app.getCoverMessage() != null ? app.getCoverMessage() : "";
        if (desc.contains("\n")) {
            desc = desc.substring(0, desc.indexOf("\n"));
        }
        if (desc.length() > 40) {
            desc = desc.substring(0, 37) + "...";
        }
        JLabel lblDesc = new JLabel(desc);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDesc.setForeground(new Color(150, 160, 175));
        lblDesc.setBounds(30, 38, 280, 18);
        row.add(lblDesc);
        
        // Submission Date Column
        String dateStr = "";
        if (app.getCreatedAt() != null) {
            dateStr = new java.text.SimpleDateFormat("MM/dd/yyyy").format(app.getCreatedAt());
        } else {
            dateStr = "N/A";
        }
        JLabel lblDate = new JLabel(dateStr);
        lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDate.setForeground(new Color(100, 110, 120));
        lblDate.setBounds(330, 18, 150, 20);
        row.add(lblDate);
        
        // Status Column
        Color badgeBg = new Color(170, 185, 200); // default gray
        Color badgeFg = Color.WHITE;
        String statusText = app.getAppStatus() != null ? app.getAppStatus() : "SUBMITTED";
        
        if ("APPROVED".equals(statusText)) {
            badgeBg = new Color(25, 165, 95); // green
        } else if ("REJECTED".equals(statusText)) {
            badgeBg = new Color(225, 50, 50); // red
        } else if ("SUBMITTED".equals(statusText)) {
            badgeBg = new Color(235, 130, 45); // orange
            statusText = "Submitted";
        } else if ("WITHDRAWN".equals(statusText)) {
            badgeBg = new Color(155, 170, 185); // gray
            statusText = "Withdrawn";
        }
        
        if ("APPROVED".equals(statusText)) statusText = "Approved";
        else if ("REJECTED".equals(statusText)) statusText = "Rejected";
        
        BadgeLabel lblStatusBadge = new BadgeLabel(statusText, badgeBg, badgeFg);
        lblStatusBadge.setBounds(510, 15, 95, 24);
        row.add(lblStatusBadge);
        
        if ("APPROVED".equals(app.getAppStatus())) {
            String moveInStr = "N/A";
            if (app.getMoveInDate() != null) {
                moveInStr = new java.text.SimpleDateFormat("MM/dd/yyyy").format(app.getMoveInDate());
            }
            User owner = userDAO.getUserById(app.getOwnerId());
            String phone = (owner != null && owner.getPhone() != null) ? owner.getPhone() : "9876543210";
            
            JLabel lblMoveIn = new JLabel("<html><b>Move-in Date:</b> <font color='#1E5DF0'>" + moveInStr + "</font></html>");
            lblMoveIn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lblMoveIn.setForeground(new Color(60, 60, 60));
            lblMoveIn.setBounds(510, 45, 250, 18);
            row.add(lblMoveIn);
            
            JLabel lblContact = new JLabel("<html><b>Owner Contact:</b> " + phone + "</html>");
            lblContact.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lblContact.setForeground(new Color(60, 60, 60));
            lblContact.setBounds(510, 65, 250, 18);
            row.add(lblContact);
        } else if ("REJECTED".equals(app.getAppStatus())) {
            String rejNote = app.getRejectionNote() != null ? app.getRejectionNote() : "Income requirements not met";
            JLabel lblNote = new JLabel("<html><b>Note:</b> " + rejNote + "</html>");
            lblNote.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lblNote.setForeground(new Color(60, 60, 60));
            lblNote.setBounds(510, 45, 250, 18);
            row.add(lblNote);
        }
        
        // Actions Column
        if ("SUBMITTED".equals(app.getAppStatus())) {
            ActionButton btnWithdraw = new ActionButton("Withdrew", new Color(15, 60, 160));
            btnWithdraw.setBounds(790, 12, 90, 30);
            btnWithdraw.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(view,
                        "Are you sure you want to withdraw this application?",
                        "Confirm Withdrawal",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (applicationDAO.updateApplicationStatus(app.getApplicationId(), "WITHDRAWN", "")) {
                        JOptionPane.showMessageDialog(view, "Application withdrawn successfully.");
                        loadMyApplications(view);
                    } else {
                        JOptionPane.showMessageDialog(view, "Failed to withdraw application.");
                    }
                }
            });
            row.add(btnWithdraw);
        }
        
        return row;
    }

    private void setFallbackFormImage(javax.swing.JLabel lbl, int w, int h) {
        java.net.URL imgUrl = getClass().getResource("/Images/Gemini_Generated_Image_enyzbenyzbe.png");
        if (imgUrl == null) {
            imgUrl = getClass().getResource("/Images/Gemini_Generated_Image_enyzbenyzbenyzbe.png");
        }
        if (imgUrl != null) {
            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(imgUrl);
            java.awt.Image img = icon.getImage().getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
            lbl.setIcon(new javax.swing.ImageIcon(img));
            lbl.setText("");
        } else {
            lbl.setIcon(null);
            lbl.setText("Image");
        }
    }

    private static class BadgeLabel extends javax.swing.JLabel {
        private java.awt.Color bgColor;
        public BadgeLabel(String text, java.awt.Color bg, java.awt.Color fg) {
            super(text);
            this.bgColor = bg;
            setForeground(fg);
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
            setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            setOpaque(false);
        }
        @Override
        protected void paintComponent(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class ActionButton extends javax.swing.JButton {
        public ActionButton(String text, java.awt.Color bg) {
            super(text);
            setBackground(bg);
            setForeground(java.awt.Color.WHITE);
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }
        @Override
        protected void paintComponent(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}

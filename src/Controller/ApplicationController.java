package Controller;

import DAO.ApplicationDAO;
import DAO.UserDAO;
import DAO.PropertyDAO;
import DAO.LeaseDAO;
import Model.RentalApplication;
import Model.Property;
import Model.Lease;
import Model.User;
import smartrent.SessionService;
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
        view.getScrollPane().getVerticalScrollBar().setUnitIncrement(16);
        
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser != null) {
            view.getLblWelcome().setText("Welcome, " + currentUser.getFullName().split(" ")[0]);
        }
        
        loadStats(view);
        loadApplications(view);
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
        
        view.getLblStatProperties().setText("<html>Total Properties<br><font size='5'>" + totalProps + "</font> Properties</html>");
        view.getLblStatApplications().setText("<html>Pending Applications<br><font size='5'>" + pendingApps + "</font> Applications</html>");
        view.getLblStatLeases().setText("<html>Pending Approvals<br><font size='5'>" + pendingApps + "</font> Pending</html>");
    }

    public void loadApplications(OwnerDashboardView view) {
        List<RentalApplication> apps = getOwnerApplications();
        view.getPnlTableBody().removeAll();
        
        for (RentalApplication app : apps) {
            JPanel row = new JPanel(null);
            row.setBackground(Color.WHITE);
            row.setPreferredSize(new Dimension(740, 60));
            row.setMaximumSize(new Dimension(740, 60));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            // User Name
            JLabel lblUser = new JLabel(app.getRenterName());
            lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblUser.setBounds(20, 15, 120, 30);
            row.add(lblUser);
            
            // Property
            JLabel lblProp = new JLabel(app.getPropertyTitle());
            lblProp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lblProp.setBounds(150, 15, 140, 30);
            row.add(lblProp);
            
            // Email
            User renter = userDAO.getUserById(app.getRenterId());
            String email = renter != null ? renter.getEmail() : "N/A";
            JLabel lblEmail = new JLabel(email);
            lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lblEmail.setForeground(Color.GRAY);
            lblEmail.setBounds(300, 15, 160, 30);
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
            lblStatus.setBounds(470, 15, 70, 30);
            row.add(lblStatus);
            
            // Actions
            int btnX = 550;
            
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
            view.getPnlTableBody().add(row);
        }
        
        view.getPnlTableBody().revalidate();
        view.getPnlTableBody().repaint();
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
        PropertyDAO pDao = new PropertyDAO();
        Property property = pDao.getPropertyById(propertyId);
        if (property != null) {
            view.getLblPropTitle().setText(property.getTitle() != null ? property.getTitle() : "Unknown Property");
            view.getLblPropLocation().setText(property.getAddress() != null ? property.getAddress() : "Location");
            view.getLblPropPrice().setText("Rs. " + String.format("%.0f", property.getMonthlyRent()) + "/mo");
            view.getLblPropDetails().setText(property.getBedrooms() + " Bed Property");
        }
        
        User user = SessionService.getInstance().getCurrentUser();
        if (user != null) {
            view.getTxtFullName().setText(user.getFullName() != null ? user.getFullName() : "");
            view.getTxtPhone().setText(user.getPhone() != null ? user.getPhone() : "");
            view.getTxtEmail().setText(user.getEmail() != null ? user.getEmail() : "");
        }
    }

    public void nextStep(ApplicationFormView view, int propertyId) {
        String dateStr = view.getTxtMoveInDate().getText();
        
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
        PropertyDAO pDao = new PropertyDAO();
        Property property = pDao.getPropertyById(propertyId);
        if (property != null) {
            view.getLblPropTitle().setText(property.getTitle() != null ? property.getTitle() : "Unknown Property");
            view.getLblPropLocation().setText(property.getAddress() != null ? property.getAddress() : "Location");
            view.getLblPropPrice().setText("Rs. " + String.format("%.0f", property.getMonthlyRent()) + "/mo");
            view.getLblPropDetails().setText(property.getBedrooms() + " Bed Property");
        }
    }

    public void goBackToStep1(ApplicationFormStep2View view, int propertyId) {
        view.setVisible(false);
        view.getStep1View().setVisible(true);
    }

    public void submitApplicationFromStep2(ApplicationFormStep2View step2View) {
        ApplicationFormView step1View = step2View.getStep1View();
        String dateStr = step1View.getTxtMoveInDate().getText();
        Date moveInDate = null;
        try {
            moveInDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (java.text.ParseException ex) {
            JOptionPane.showMessageDialog(step2View, "Invalid Move-in Date format. Use YYYY-MM-DD.");
            return;
        }

        String step1Data = "Lease Duration: " + step1View.getTxtLeaseDuration().getText() + " months\n" +
                           "DOB: " + step1View.getTxtDOB().getText() + "\n";

        String extraInfo = step1Data + 
                           "Employer: " + step2View.getTxtEmployer().getText() + "\n" +
                           "Job Title: " + step2View.getTxtJobTitle().getText() + "\n" +
                           "Income: " + step2View.getTxtIncome().getText();

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
            view.getTxtProof().setText(selectedFile.getAbsolutePath());
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
        // pnlAppsList uses BoxLayout Y_AXIS
        view.getPnlAppsList().setLayout(new BoxLayout(view.getPnlAppsList(), BoxLayout.Y_AXIS));
        
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
        view.getPnlAppsList().removeAll();
        
        for (RentalApplication a : apps) {
            view.getPnlAppsList().add(createAppCard(a));
            view.getPnlAppsList().add(Box.createRigidArea(new Dimension(0, 15))); // spacing between cards
        }
        
        view.getPnlAppsList().revalidate();
        view.getPnlAppsList().repaint();
    }

    private JPanel createAppCard(RentalApplication app) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(720, 120));
        card.setMaximumSize(new Dimension(720, 120));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true));

        // Image placeholder
        JLabel lblImage = new JLabel("Img", SwingConstants.CENTER);
        lblImage.setOpaque(true);
        lblImage.setBackground(new Color(200, 200, 200));
        lblImage.setBounds(10, 10, 100, 100);
        card.add(lblImage);

        // Title
        JLabel lblTitle = new JLabel(app.getPropertyTitle() != null ? app.getPropertyTitle() : "Unknown Property");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setBounds(130, 20, 300, 25);
        card.add(lblTitle);

        // Applied Date
        JLabel lblDate = new JLabel("Applied on: " + app.getCreatedAt().toString().substring(0, 10));
        lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDate.setForeground(Color.GRAY);
        lblDate.setBounds(130, 50, 200, 20);
        card.add(lblDate);

        // Status Badge
        JLabel lblStatus = new JLabel("Status: " + app.getAppStatus());
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
        Color statusColor = Color.GRAY;
        if (app.getAppStatus().equals("APPROVED")) statusColor = new Color(34, 180, 50);
        if (app.getAppStatus().equals("REJECTED")) statusColor = new Color(230, 51, 51);
        if (app.getAppStatus().equals("SUBMITTED")) statusColor = new Color(230, 140, 25);
        lblStatus.setForeground(statusColor);
        lblStatus.setBounds(130, 80, 200, 20);
        card.add(lblStatus);

        // Message Button
        JButton btnMessage = new JButton("Message Owner");
        btnMessage.setBackground(new Color(30, 92, 240));
        btnMessage.setForeground(Color.WHITE);
        btnMessage.setBounds(550, 20, 150, 35);
        btnMessage.addActionListener(e -> {
            new MessagingView(app.getOwnerId(), app.getOwnerName()).setVisible(true);
        });
        card.add(btnMessage);

        return card;
    }
}

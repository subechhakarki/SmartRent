package Controller;

import DAO.LeaseDAO;
import Model.Lease;
import Model.User;
import smartrent.SessionService;
import view.*;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

public class LeaseController {

    private LeaseDAO leaseDAO;
    private List<Lease> currentLeaseList;
    private int currentPage = 1;
    private final int itemsPerPage = 5;

    public LeaseController() {
        this.leaseDAO = new LeaseDAO();
    }

    public List<Lease> getMyLeases() {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null) return new java.util.ArrayList<>();
        
        if ("PROPERTY_OWNER".equals(currentUser.getRole())) {
            return leaseDAO.getLeasesByOwner(currentUser.getUserId());
        } else if ("RENTER".equals(currentUser.getRole())) {
            return leaseDAO.getLeasesByRenter(currentUser.getUserId());
        }
        
        return new java.util.ArrayList<>();
    }

    public void initLeaseManagementView(LeaseManagementView view) {
        view.getScrollTable().getVerticalScrollBar().setUnitIncrement(16);

        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser != null) {
            view.getLblWelcome().setText("Welcome, " + currentUser.getFullName().split(" ")[0]);
        }

        setupCustomComponents(view);
        setupEvents(view);
        loadLeases(view);

        view.setSize(1280, 800);
        view.setResizable(false);
        view.setLocationRelativeTo(null);
    }

    private void setupCustomComponents(LeaseManagementView view) {
        view.getPnlPagination().setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        view.getPnlPagination().removeAll();
        view.getPnlPagination().add(view.getBtnPrev());
        view.getPnlPagination().add(view.getPnlPageNumbers());
        view.getPnlPagination().add(view.getBtnNext());

        stylePaginationButton(view.getBtnPrev());
        stylePaginationButton(view.getBtnNext());

        view.getPnlCard().setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        view.getScrollTable().getViewport().setBackground(Color.WHITE);
    }

    private void stylePaginationButton(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(74, 85, 104));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        btn.setFocusPainted(false);
    }

    private void setupEvents(LeaseManagementView view) {
        view.getBtnPrev().addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                loadLeases(view);
            }
        });

        view.getBtnNext().addActionListener(e -> {
            int totalPages = (int) Math.ceil((double) currentLeaseList.size() / itemsPerPage);
            if (currentPage < totalPages) {
                currentPage++;
                loadLeases(view);
            }
        });
    }

    public void loadLeases(LeaseManagementView view) {
        currentLeaseList = getMyLeases();
        
        int activeCount = 0;
        int expiredCount = 0;
        int terminatedCount = 0;

        for (Lease l : currentLeaseList) {
            String status = l.getStatus() != null ? l.getStatus().toUpperCase() : "";
            if ("ACTIVE".equals(status)) {
                activeCount++;
            } else if ("EXPIRED".equals(status)) {
                expiredCount++;
            } else if ("TERMINATED".equals(status)) {
                terminatedCount++;
            }
        }

        view.getLblActiveLeases().setText("<html><table cellpadding='5'><tr><td><font size='6' color='white'>⌂</font></td><td><font color='white'>Active Leases</font><br><font size='5' color='white'><b>" + activeCount + " Active</b></font></td></tr></table></html>");
        view.getLblExpiredLeases().setText("<html><table cellpadding='5'><tr><td><font size='6' color='white'>⚠</font></td><td><font color='white'>Expired Leases</font><br><font size='5' color='white'><b>" + expiredCount + " Expired</b></font></td></tr></table></html>");
        view.getLblTerminatedLeases().setText("<html><table cellpadding='5'><tr><td><font size='6' color='white'>✓</font></td><td><font color='white'>Terminated Leases</font><br><font size='5' color='white'><b>" + terminatedCount + " Terminated</b></font></td></tr></table></html>");

        view.getPnlTableBody().removeAll();
        int totalLeases = currentLeaseList.size();

        int totalPages = (int) Math.ceil((double) totalLeases / itemsPerPage);
        if (currentPage > totalPages && totalPages > 0) {
            currentPage = totalPages;
        } else if (totalPages == 0) {
            currentPage = 1;
        }

        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, totalLeases);

        if (totalLeases == 0) {
            view.getLblEntriesSummary().setText("Showing 0 to 0 of 0 entries");
        } else {
            view.getLblEntriesSummary().setText("Showing " + (startIndex + 1) + " to " + endIndex + " of " + totalLeases + " entries");
        }

        for (int i = startIndex; i < endIndex; i++) {
            Lease l = currentLeaseList.get(i);
            view.getPnlTableBody().add(createRowPanel(l));
        }

        updatePaginationControls(view, totalPages);

        view.getPnlTableBody().revalidate();
        view.getPnlTableBody().repaint();
    }

    private void updatePaginationControls(LeaseManagementView view, int totalPages) {
        view.getBtnPrev().setEnabled(currentPage > 1);
        view.getBtnNext().setEnabled(currentPage < totalPages && totalPages > 1);

        view.getPnlPageNumbers().removeAll();
        for (int p = 1; p <= totalPages; p++) {
            final int pageNum = p;
            JButton btnPage = new JButton(String.valueOf(pageNum));
            btnPage.setFocusPainted(false);
            btnPage.setFont(new Font("Segoe UI", Font.BOLD, 12));
            btnPage.setMargin(new Insets(2, 6, 2, 6));

            if (pageNum == currentPage) {
                btnPage.setBackground(new Color(43, 108, 176));
                btnPage.setForeground(Color.WHITE);
                btnPage.setBorder(BorderFactory.createLineBorder(new Color(43, 108, 176), 1));
            } else {
                btnPage.setBackground(Color.WHITE);
                btnPage.setForeground(new Color(74, 85, 104));
                btnPage.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240), 1));
                btnPage.addActionListener(e -> {
                    currentPage = pageNum;
                    loadLeases(view);
                });
            }
            view.getPnlPageNumbers().add(btnPage);
        }
        view.getPnlPageNumbers().revalidate();
        view.getPnlPageNumbers().repaint();
    }

    private JPanel createRowPanel(Lease l) {
        JPanel row = new JPanel();
        row.setLayout(null);
        row.setPreferredSize(new Dimension(960, 60));
        row.setMaximumSize(new Dimension(960, 60));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setBackground(Color.WHITE);
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(240, 240, 240)));

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd/yyyy");

        JLabel lblTenant = new JLabel(l.getRenterName() != null ? l.getRenterName() : "N/A");
        lblTenant.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTenant.setForeground(new Color(45, 55, 72));
        lblTenant.setBounds(20, 15, 160, 30);
        row.add(lblTenant);

        JLabel lblProp = new JLabel(l.getPropertyTitle() != null ? l.getPropertyTitle() : "N/A");
        lblProp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblProp.setForeground(new Color(74, 85, 104));
        lblProp.setBounds(180, 15, 180, 30);
        row.add(lblProp);

        String startStr = l.getStartDate() != null ? sdf.format(l.getStartDate()) : "N/A";
        JLabel lblStart = new JLabel(startStr);
        lblStart.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblStart.setForeground(new Color(74, 85, 104));
        lblStart.setBounds(360, 15, 110, 30);
        row.add(lblStart);

        String endStr = l.getEndDate() != null ? sdf.format(l.getEndDate()) : "—";
        JLabel lblEnd = new JLabel(endStr);
        lblEnd.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblEnd.setForeground(new Color(74, 85, 104));
        lblEnd.setBounds(470, 15, 110, 30);
        row.add(lblEnd);

        String rentStr = "Rs. " + String.format("%,.0f", l.getMonthlyRent());
        JLabel lblRent = new JLabel(rentStr);
        lblRent.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblRent.setForeground(new Color(74, 85, 104));
        lblRent.setBounds(580, 15, 120, 30);
        row.add(lblRent);

        String status = l.getStatus() != null ? l.getStatus().toUpperCase() : "ACTIVE";
        JLabel lblStatus = new JLabel();
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setOpaque(true);
        if ("ACTIVE".equals(status)) {
            lblStatus.setText("Active");
            lblStatus.setBackground(new Color(43, 108, 176));
            lblStatus.setForeground(Color.WHITE);
        } else if ("EXPIRED".equals(status)) {
            lblStatus.setText("Expired");
            lblStatus.setBackground(new Color(229, 62, 62));
            lblStatus.setForeground(Color.WHITE);
        } else {
            lblStatus.setText("Terminated");
            lblStatus.setBackground(new Color(56, 161, 105));
            lblStatus.setForeground(Color.WHITE);
        }
        lblStatus.setBounds(700, 15, 90, 30);
        row.add(lblStatus);

        JButton btnView = new JButton("View Details");
        btnView.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnView.setBackground(new Color(43, 108, 176));
        btnView.setForeground(Color.WHITE);
        btnView.setFocusPainted(false);
        btnView.setBounds(800, 15, 120, 30);
        btnView.addActionListener(e -> {
            new ViewLeaseView(l).setVisible(true);
        });
        row.add(btnView);

        return row;
    }

    public void initViewLeaseView(ViewLeaseView view, Lease lease) {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("         RESIDENTIAL LEASE AGREEMENT      \n");
        sb.append("========================================\n\n");
        
        sb.append("LEASE ID: ").append(lease.getLeaseId()).append("\n");
        sb.append("STATUS:   ").append(lease.getStatus()).append("\n");
        sb.append("CREATED:  ").append(lease.getCreatedAt()).append("\n\n");
        
        sb.append("PROPERTY DETAILS\n");
        sb.append("----------------\n");
        sb.append("Title:    ").append(lease.getPropertyTitle()).append("\n");
        sb.append("Rent:     Rs. ").append(String.format("%.2f", lease.getMonthlyRent())).append(" / month\n");
        sb.append("Deposit:  Rs. ").append(String.format("%.2f", lease.getDeposit())).append("\n\n");
        
        sb.append("PARTIES\n");
        sb.append("-------\n");
        sb.append("Landlord: ").append(lease.getOwnerName()).append("\n");
        sb.append("Tenant:   ").append(lease.getRenterName()).append("\n\n");
        
        sb.append("LEASE TERM\n");
        sb.append("----------\n");
        sb.append("Start:    ").append(lease.getStartDate().toString()).append("\n");
        sb.append("End:      ").append(lease.getEndDate().toString()).append("\n\n");
        
        sb.append("TERMS & CONDITIONS\n");
        sb.append("------------------\n");
        sb.append(lease.getTerms()).append("\n\n");
        
        sb.append("========================================\n");
        sb.append("This is a digitally generated record.\n");
        
        view.getTxtLeaseDetails().setText(sb.toString());
        view.getTxtLeaseDetails().setCaretPosition(0);
    }

    public void navigateToDashboard(JFrame view) {
        new OwnerDashboardView().setVisible(true);
        view.dispose();
    }

    public void navigateToMyProperties(JFrame view) {
        new MyPropertiesView().setVisible(true);
        view.dispose();
    }

    public void logout(JFrame view) {
        SessionService.getInstance().logout();
        new LoginView().setVisible(true);
        view.dispose();
    }

    public void downloadLeasePDF(JFrame view, Lease lease) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("Lease_Agreement_" + lease.getLeaseId() + ".pdf"));
        int userSelection = fileChooser.showSaveDialog(view);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            generateLeasePDF(lease, fileToSave.getAbsolutePath());
            JOptionPane.showMessageDialog(view, "Lease PDF successfully saved to:\n" + fileToSave.getAbsolutePath());
        }
    }

    public static void generateLeasePDF(Lease lease, String destinationPath) {
        String propertyAddress = "N/A";
        try {
            Model.Property prop = new DAO.PropertyDAO().getPropertyById(lease.getPropertyId());
            if (prop != null) {
                propertyAddress = prop.getAddress();
            }
        } catch (Exception e) {
            // ignore
        }
        
        try {
            File file = new File(destinationPath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            
            com.itextpdf.kernel.pdf.PdfWriter writer = new com.itextpdf.kernel.pdf.PdfWriter(new FileOutputStream(file));
            com.itextpdf.kernel.pdf.PdfDocument pdf = new com.itextpdf.kernel.pdf.PdfDocument(writer);
            com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdf);
            
            document.add(new com.itextpdf.layout.element.Paragraph("RESIDENTIAL LEASE AGREEMENT")
                    .setFontSize(22)
                    .setBold()
                    .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER)
                    .setMarginBottom(15));
                    
            document.add(new com.itextpdf.layout.element.Paragraph("This Residential Lease Agreement is entered into and made effective as of the start date listed below, between the Landlord and Tenant specified below.")
                    .setFontSize(10)
                    .setMarginBottom(20));
                    
            com.itextpdf.layout.element.Table table = new com.itextpdf.layout.element.Table(2);
            table.addCell(new com.itextpdf.layout.element.Cell().add(new com.itextpdf.layout.element.Paragraph("Lease ID").setBold()));
            table.addCell(String.valueOf(lease.getLeaseId()));
            
            table.addCell(new com.itextpdf.layout.element.Cell().add(new com.itextpdf.layout.element.Paragraph("Landlord (Owner)").setBold()));
            table.addCell(lease.getOwnerName() != null ? lease.getOwnerName() : "N/A");
            
            table.addCell(new com.itextpdf.layout.element.Cell().add(new com.itextpdf.layout.element.Paragraph("Tenant").setBold()));
            table.addCell(lease.getRenterName() != null ? lease.getRenterName() : "N/A");
            
            table.addCell(new com.itextpdf.layout.element.Cell().add(new com.itextpdf.layout.element.Paragraph("Property Title").setBold()));
            table.addCell(lease.getPropertyTitle() != null ? lease.getPropertyTitle() : "N/A");
            
            table.addCell(new com.itextpdf.layout.element.Cell().add(new com.itextpdf.layout.element.Paragraph("Property Address").setBold()));
            table.addCell(propertyAddress);
            
            table.addCell(new com.itextpdf.layout.element.Cell().add(new com.itextpdf.layout.element.Paragraph("Monthly Rent").setBold()));
            table.addCell("Rs. " + String.format("%,.2f", lease.getMonthlyRent()));
            
            table.addCell(new com.itextpdf.layout.element.Cell().add(new com.itextpdf.layout.element.Paragraph("Security Deposit").setBold()));
            table.addCell("Rs. " + String.format("%,.2f", lease.getDeposit()));
            
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            table.addCell(new com.itextpdf.layout.element.Cell().add(new com.itextpdf.layout.element.Paragraph("Start Date").setBold()));
            table.addCell(lease.getStartDate() != null ? sdf.format(lease.getStartDate()) : "N/A");
            
            table.addCell(new com.itextpdf.layout.element.Cell().add(new com.itextpdf.layout.element.Paragraph("End Date").setBold()));
            table.addCell(lease.getEndDate() != null ? sdf.format(lease.getEndDate()) : "N/A");
            
            document.add(table);
            
            document.add(new com.itextpdf.layout.element.Paragraph("\nTERMS & CONDITIONS")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(15)
                    .setMarginBottom(5));
            document.add(new com.itextpdf.layout.element.Paragraph(lease.getTerms() != null ? lease.getTerms() : "Standard 1-year residential lease agreement.")
                    .setFontSize(9));
                    
            document.add(new com.itextpdf.layout.element.Paragraph("\n\n\nIN WITNESS WHEREOF, the Landlord and Tenant have executed this Lease Agreement as of the dates specified.")
                    .setFontSize(9)
                    .setItalic());

            com.itextpdf.layout.element.Table sigTable = new com.itextpdf.layout.element.Table(2);
            sigTable.setMarginTop(30);
            
            com.itextpdf.layout.element.Cell cellL = new com.itextpdf.layout.element.Cell().add(new com.itextpdf.layout.element.Paragraph("_______________________\nLandlord Signature"));
            cellL.setBorder(com.itextpdf.layout.borders.Border.NO_BORDER);
            
            com.itextpdf.layout.element.Cell cellT = new com.itextpdf.layout.element.Cell().add(new com.itextpdf.layout.element.Paragraph("_______________________\nTenant Signature").setTextAlignment(com.itextpdf.layout.properties.TextAlignment.RIGHT));
            cellT.setBorder(com.itextpdf.layout.borders.Border.NO_BORDER);
            
            sigTable.addCell(cellL);
            sigTable.addCell(cellT);
            document.add(sigTable);
            
            document.close();
            System.out.println("Lease PDF successfully generated at: " + destinationPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

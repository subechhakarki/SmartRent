package Controller;

import DAO.UserDAO;
import DAO.PropertyDAO;
import Model.User;
import Model.Property;
import view.*;
import smartrent.SessionService;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.List;
import java.util.ArrayList;

public class AdminController {

    private UserDAO userDAO;
    private List<User> currentUsersList;
    private int currentPage = 1;
    private final int itemsPerPage = 5;
    private List<Integer> pendingUserIds = new ArrayList<>();
    private List<Integer> propertyOwnerIds = new ArrayList<>();

    public AdminController() {
        this.userDAO = new UserDAO();
    }

    public int[] getPlatformStats() {
        return userDAO.getPlatformStats();
    }

    public List<User> getPendingOwners() {
        return userDAO.getPendingOwners();
    }

    public boolean approveOwner(int userId) {
        return userDAO.updateOwnerApproval(userId, "APPROVED", "");
    }

    public boolean rejectOwner(int userId, String note) {
        return userDAO.updateOwnerApproval(userId, "REJECTED", note);
    }

    public List<Property> getAllProperties() {
        return new PropertyDAO().getAllProperties();
    }

    public boolean suspendUser(int userId) {
        return userDAO.updateUserStatus(userId, "SUSPENDED");
    }

    public boolean reinstateUser(int userId) {
        return userDAO.updateUserStatus(userId, "ACTIVE");
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }

    public boolean updateUser(int userId, String fullName, String email, String role, String status) {
        // Map from UI role ("Owner"/"Renter") to DB role ("PROPERTY_OWNER"/"RENTER")
        String dbRole = role;
        if ("Owner".equalsIgnoreCase(role)) {
            dbRole = "PROPERTY_OWNER";
        } else if ("Renter".equalsIgnoreCase(role)) {
            dbRole = "RENTER";
        }
        return userDAO.updateUser(userId, fullName, email, dbRole, status.toUpperCase());
    }

    public List<User> getUsers(String roleFilter, String searchQuery) {
        List<User> all = getAllUsers();
        if ((roleFilter == null || roleFilter.isEmpty() || "All Roles".equalsIgnoreCase(roleFilter)) 
                && (searchQuery == null || searchQuery.trim().isEmpty())) {
            return all;
        }
        
        List<User> filtered = new java.util.ArrayList<>();
        String queryLower = searchQuery != null ? searchQuery.toLowerCase().trim() : "";
        for (User u : all) {
            boolean roleMatch = true;
            if (roleFilter != null && !roleFilter.isEmpty() && !"All Roles".equalsIgnoreCase(roleFilter)) {
                String dbRole = u.getRole();
                if ("Owner".equalsIgnoreCase(roleFilter)) {
                    roleMatch = "PROPERTY_OWNER".equalsIgnoreCase(dbRole);
                } else if ("Renter".equalsIgnoreCase(roleFilter)) {
                    roleMatch = "RENTER".equalsIgnoreCase(dbRole);
                } else {
                    roleMatch = roleFilter.equalsIgnoreCase(dbRole);
                }
            }
            
            boolean searchMatch = true;
            if (!queryLower.isEmpty()) {
                boolean nameMatch = u.getFullName() != null && u.getFullName().toLowerCase().contains(queryLower);
                boolean emailMatch = u.getEmail() != null && u.getEmail().toLowerCase().contains(queryLower);
                searchMatch = nameMatch || emailMatch;
            }
            
            if (roleMatch && searchMatch) {
                filtered.add(u);
            }
        }
        return filtered;
    }

    // Dashboard View Initialization & Loading
    public void initDashboard(AdminDashboardView view) {
        int[] stats = getPlatformStats();
        if (stats != null && stats.length >= 4) {
            view.getLblCard1Value().setText(stats[0] + " Users");
            view.getLblCard2Value().setText(stats[1] + " Active");
            view.getLblCard3Value().setText(stats[2] + " Pending");
            view.getLblCard4Value().setText(stats[3] + " Suspended");
        }
        
        loadApplicationsTable(view);
        loadPropertiesTable(view);
    }

    public void loadApplicationsTable(AdminDashboardView view) {
        List<User> pending = getPendingOwners();
        pendingUserIds.clear();
        
        DefaultTableModel model = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Owner Name", "Email", "Phone", "Registered Date", "Status", "Actions" }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };
        
        for (User u : pending) {
            pendingUserIds.add(u.getUserId());
            String regDate = u.getCreatedAt() != null ? u.getCreatedAt().toString() : "N/A";
            if (regDate.length() > 10) {
                regDate = regDate.substring(0, 10);
            }
            model.addRow(new Object[]{
                u.getFullName(), 
                u.getEmail(), 
                u.getPhone() != null ? u.getPhone() : "N/A", 
                regDate, 
                "PENDING", 
                ""
            });
        }

        view.getTblApplications().setModel(model);
        view.getTblApplications().setRowHeight(45);
        view.getTblApplications().getColumnModel().getColumn(0).setPreferredWidth(120); // Owner Name
        view.getTblApplications().getColumnModel().getColumn(1).setPreferredWidth(180); // Email
        view.getTblApplications().getColumnModel().getColumn(2).setPreferredWidth(100); // Phone
        view.getTblApplications().getColumnModel().getColumn(3).setPreferredWidth(120); // Registered Date
        view.getTblApplications().getColumnModel().getColumn(4).setPreferredWidth(100); // Status
        view.getTblApplications().getColumnModel().getColumn(5).setPreferredWidth(180); // Actions
        view.getTblApplications().getColumnModel().getColumn(5).setMinWidth(150);
        
        // Set up custom renderer and editor for Actions column
        view.getTblApplications().getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        view.getTblApplications().getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(view));
    }

    // Custom cell renderer that draws Approve/Reject buttons
    private class ButtonRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            panel.setBackground(Color.WHITE);
            
            JButton btnApprove = new JButton("Approve");
            btnApprove.setBackground(new Color(34, 166, 76));
            btnApprove.setForeground(Color.WHITE);
            btnApprove.setFont(new Font("Segoe UI", Font.BOLD, 11));
            btnApprove.setFocusPainted(false);
            btnApprove.setBorderPainted(false);
            
            JButton btnReject = new JButton("Reject");
            btnReject.setBackground(new Color(204, 57, 51));
            btnReject.setForeground(Color.WHITE);
            btnReject.setFont(new Font("Segoe UI", Font.BOLD, 11));
            btnReject.setFocusPainted(false);
            btnReject.setBorderPainted(false);
            
            panel.add(btnApprove);
            panel.add(btnReject);
            return panel;
        }
    }

    // Custom cell editor that handles Approve/Reject button clicks
    private class ButtonEditor extends DefaultCellEditor {
        private JPanel panel;
        private JButton btnApprove;
        private JButton btnReject;
        private int editingRow;
        private AdminDashboardView parentView;

        public ButtonEditor(AdminDashboardView view) {
            super(new JCheckBox());
            this.parentView = view;
            
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            panel.setBackground(Color.WHITE);
            
            btnApprove = new JButton("Approve");
            btnApprove.setBackground(new Color(34, 166, 76));
            btnApprove.setForeground(Color.WHITE);
            btnApprove.setFont(new Font("Segoe UI", Font.BOLD, 11));
            btnApprove.setFocusPainted(false);
            btnApprove.setBorderPainted(false);
            
            btnReject = new JButton("Reject");
            btnReject.setBackground(new Color(204, 57, 51));
            btnReject.setForeground(Color.WHITE);
            btnReject.setFont(new Font("Segoe UI", Font.BOLD, 11));
            btnReject.setFocusPainted(false);
            btnReject.setBorderPainted(false);
            
            panel.add(btnApprove);
            panel.add(btnReject);
            
            btnApprove.addActionListener(e -> {
                fireEditingStopped();
                if (editingRow >= 0 && editingRow < pendingUserIds.size()) {
                    int userId = pendingUserIds.get(editingRow);
                    String ownerName = (String) parentView.getTblApplications().getValueAt(editingRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(parentView, 
                        "Approve owner " + ownerName + "?", 
                        "Approve Owner", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        approveOwner(userId);
                        initDashboard(parentView);
                    }
                }
            });
            
            btnReject.addActionListener(e -> {
                fireEditingStopped();
                if (editingRow >= 0 && editingRow < pendingUserIds.size()) {
                    int userId = pendingUserIds.get(editingRow);
                    String ownerName = (String) parentView.getTblApplications().getValueAt(editingRow, 0);
                    String reason = JOptionPane.showInputDialog(parentView, 
                        "Enter rejection reason for " + ownerName + ":", 
                        "Reject Owner", JOptionPane.QUESTION_MESSAGE);
                    if (reason != null && !reason.trim().isEmpty()) {
                        rejectOwner(userId, reason);
                        initDashboard(parentView);
                    }
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            editingRow = row;
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }

    public void loadPropertiesTable(AdminDashboardView view) {
        List<Property> props = getAllProperties();
        propertyOwnerIds.clear();
        
        DefaultTableModel model = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Property Title", "Owner", "Status", "Date Added", "Actions" }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        
        for (Property p : props) {
            propertyOwnerIds.add(p.getOwnerId());
            User owner = userDAO.getUserById(p.getOwnerId());
            String ownerName = owner != null ? owner.getFullName() : "Owner ID: " + p.getOwnerId();
            
            String dateAdded = p.getCreatedAt() != null ? p.getCreatedAt().toString() : "N/A";
            if (dateAdded.length() > 10) {
                dateAdded = dateAdded.substring(0, 10);
            }
            
            String status = p.getPropStatus();
            if (status != null && !status.isEmpty()) {
                status = status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
            } else {
                status = "N/A";
            }
            
            model.addRow(new Object[]{
                p.getTitle(), 
                ownerName, 
                status, 
                dateAdded, 
                ""
            });
        }

        view.getTblProperties().setModel(model);
        view.getTblProperties().setRowHeight(45);
        view.getTblProperties().getColumnModel().getColumn(0).setPreferredWidth(200); // Property Title
        view.getTblProperties().getColumnModel().getColumn(1).setPreferredWidth(150); // Owner
        view.getTblProperties().getColumnModel().getColumn(2).setPreferredWidth(100); // Status
        view.getTblProperties().getColumnModel().getColumn(3).setPreferredWidth(120); // Date Added
        view.getTblProperties().getColumnModel().getColumn(4).setPreferredWidth(120); // Actions
        
        view.getTblProperties().getColumnModel().getColumn(4).setCellRenderer(new PropertyButtonRenderer());
        view.getTblProperties().getColumnModel().getColumn(4).setCellEditor(new PropertyButtonEditor(view));
    }

    private class PropertyButtonRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            panel.setBackground(Color.WHITE);
            
            JButton btnSuspend = new JButton("Suspend");
            btnSuspend.setBackground(new Color(204, 57, 51));
            btnSuspend.setForeground(Color.WHITE);
            btnSuspend.setFont(new Font("Segoe UI", Font.BOLD, 11));
            btnSuspend.setFocusPainted(false);
            btnSuspend.setBorderPainted(false);
            
            panel.add(btnSuspend);
            return panel;
        }
    }

    private class PropertyButtonEditor extends DefaultCellEditor {
        private JPanel panel;
        private JButton btnSuspend;
        private int editingRow;
        private AdminDashboardView parentView;

        public PropertyButtonEditor(AdminDashboardView view) {
            super(new JCheckBox());
            this.parentView = view;
            
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            panel.setBackground(Color.WHITE);
            
            btnSuspend = new JButton("Suspend");
            btnSuspend.setBackground(new Color(204, 57, 51));
            btnSuspend.setForeground(Color.WHITE);
            btnSuspend.setFont(new Font("Segoe UI", Font.BOLD, 11));
            btnSuspend.setFocusPainted(false);
            btnSuspend.setBorderPainted(false);
            
            panel.add(btnSuspend);
            
            btnSuspend.addActionListener(e -> {
                fireEditingStopped();
                if (editingRow >= 0 && editingRow < propertyOwnerIds.size()) {
                    int ownerId = propertyOwnerIds.get(editingRow);
                    User owner = userDAO.getUserById(ownerId);
                    String ownerName = owner != null ? owner.getFullName() : "Owner ID: " + ownerId;
                    
                    int confirm = JOptionPane.showConfirmDialog(parentView, 
                        "Are you sure you want to suspend owner: " + ownerName + "?\nAll properties owned by this user will also be affected.", 
                        "Confirm Suspend", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (suspendUser(ownerId)) {
                            JOptionPane.showMessageDialog(parentView, "Owner Suspended Successfully.");
                            initDashboard(parentView);
                        } else {
                            JOptionPane.showMessageDialog(parentView, "Failed to suspend owner.");
                        }
                    }
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            editingRow = row;
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }

    // UserManagementView initialization
    public void initUserManagementView(UserManagementView view) {
        setupCustomComponents(view);
        setupEvents(view);
        loadUsers(view);
        
        view.setSize(1280, 800);
        view.setResizable(false);
        view.setLocationRelativeTo(null);
    }

    private void setupCustomComponents(UserManagementView view) {
        view.getTxtSearch().setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        view.getPnlCard().setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        view.getPnlPagination().setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        view.getPnlPagination().removeAll();
        view.getPnlPagination().add(view.getBtnPrev());
        view.getPnlPagination().add(view.getPnlPageNumbers());
        view.getPnlPagination().add(view.getBtnNext());
        
        stylePaginationButton(view.getBtnPrev());
        stylePaginationButton(view.getBtnNext());
        
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

    private void setupEvents(UserManagementView view) {
        view.getTxtSearch().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if ("Search users...".equals(view.getTxtSearch().getText())) {
                    view.getTxtSearch().setText("");
                    view.getTxtSearch().setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (view.getTxtSearch().getText().trim().isEmpty()) {
                    view.getTxtSearch().setText("Search users...");
                    view.getTxtSearch().setForeground(Color.GRAY);
                }
            }
        });

        view.getTxtSearch().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { update(); }
            public void removeUpdate(DocumentEvent e) { update(); }
            public void changedUpdate(DocumentEvent e) { update(); }
            private void update() {
                currentPage = 1;
                loadUsers(view);
            }
        });

        view.getBtnPrev().addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                loadUsers(view);
            }
        });

        view.getBtnNext().addActionListener(e -> {
            int totalPages = (int) Math.ceil((double) currentUsersList.size() / itemsPerPage);
            if (currentPage < totalPages) {
                currentPage++;
                loadUsers(view);
            }
        });
    }

    public void filterChanged(UserManagementView view) {
        currentPage = 1;
        loadUsers(view);
    }

    public void loadUsers(UserManagementView view) {
        String filter = (String) view.getCmbRoleFilter().getSelectedItem();
        String search = view.getTxtSearch().getText();
        if ("Search users...".equals(search)) {
            search = "";
        }

        currentUsersList = getUsers(filter, search);
        
        view.getPnlTableBody().removeAll();
        int totalUsers = currentUsersList.size();
        
        int totalPages = (int) Math.ceil((double) totalUsers / itemsPerPage);
        if (currentPage > totalPages && totalPages > 0) {
            currentPage = totalPages;
        } else if (totalPages == 0) {
            currentPage = 1;
        }

        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, totalUsers);

        if (totalUsers == 0) {
            view.getLblEntriesSummary().setText("Showing 0 to 0 of 0 entries");
        } else {
            view.getLblEntriesSummary().setText("Showing " + (startIndex + 1) + " to " + endIndex + " of " + totalUsers + " entries");
        }

        for (int i = startIndex; i < endIndex; i++) {
            User u = currentUsersList.get(i);
            view.getPnlTableBody().add(createRowPanel(view, u));
        }

        updatePaginationControls(view, totalPages);
        
        view.getPnlTableBody().revalidate();
        view.getPnlTableBody().repaint();
    }

    private void updatePaginationControls(UserManagementView view, int totalPages) {
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
                    loadUsers(view);
                });
            }
            view.getPnlPageNumbers().add(btnPage);
        }
        view.getPnlPageNumbers().revalidate();
        view.getPnlPageNumbers().repaint();
    }

    private JPanel createRowPanel(UserManagementView view, User u) {
        JPanel row = new JPanel();
        row.setLayout(null);
        row.setPreferredSize(new Dimension(740, 60));
        row.setMaximumSize(new Dimension(740, 60));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setBackground(Color.WHITE);
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(240, 240, 240)));

        JLabel lblName = new JLabel(u.getFullName());
        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblName.setForeground(new Color(45, 55, 72));
        lblName.setBounds(15, 15, 150, 30);
        row.add(lblName);

        String roleStr = u.getRole();
        if ("PROPERTY_OWNER".equalsIgnoreCase(roleStr)) {
            roleStr = "Owner";
        } else if ("RENTER".equalsIgnoreCase(roleStr)) {
            roleStr = "Renter";
        } else if ("SUPER_ADMIN".equalsIgnoreCase(roleStr)) {
            roleStr = "Admin";
        }
        JLabel lblRole = new JLabel(roleStr);
        lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblRole.setForeground(new Color(74, 85, 104));
        lblRole.setBounds(175, 15, 80, 30);
        row.add(lblRole);

        JLabel lblEmail = new JLabel(u.getEmail());
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblEmail.setForeground(Color.GRAY);
        lblEmail.setBounds(265, 15, 210, 30);
        row.add(lblEmail);

        String statusStr = u.getUserStatus() != null ? u.getUserStatus() : "ACTIVE";
        boolean isActive = "ACTIVE".equalsIgnoreCase(statusStr);
        JLabel lblStatus = new JLabel(isActive ? "Active" : "Deactivated");
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setOpaque(true);
        if (isActive) {
            lblStatus.setBackground(new Color(34, 180, 50));
            lblStatus.setForeground(Color.WHITE);
        } else {
            lblStatus.setBackground(new Color(229, 62, 62));
            lblStatus.setForeground(Color.WHITE);
        }
        lblStatus.setBounds(485, 15, 90, 30);
        row.add(lblStatus);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEdit.setBackground(new Color(43, 108, 176));
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFocusPainted(false);
        btnEdit.setBounds(585, 15, 65, 30);
        btnEdit.addActionListener(e -> showEditDialog(view, u));
        row.add(btnEdit);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnDelete.setBackground(new Color(229, 62, 62));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFocusPainted(false);
        btnDelete.setBounds(660, 15, 70, 30);
        btnDelete.addActionListener(e -> confirmDelete(view, u));
        row.add(btnDelete);

        return row;
    }

    private void showEditDialog(UserManagementView view, User u) {
        JDialog dialog = new JDialog(view, "Edit User Information", true);
        dialog.setSize(400, 320);
        dialog.setLayout(null);
        dialog.setLocationRelativeTo(view);
        dialog.getContentPane().setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Edit User Details");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setBounds(20, 15, 300, 25);
        dialog.add(lblTitle);

        JLabel lblName = new JLabel("Full Name:");
        lblName.setBounds(20, 55, 100, 25);
        dialog.add(lblName);
        
        JTextField txtName = new JTextField(u.getFullName());
        txtName.setBounds(130, 55, 230, 25);
        dialog.add(txtName);

        JLabel lblEmail = new JLabel("Email Address:");
        lblEmail.setBounds(20, 95, 100, 25);
        dialog.add(lblEmail);
        
        JTextField txtEmail = new JTextField(u.getEmail());
        txtEmail.setBounds(130, 95, 230, 25);
        dialog.add(txtEmail);

        JLabel lblRole = new JLabel("System Role:");
        lblRole.setBounds(20, 135, 100, 25);
        dialog.add(lblRole);
        
        String roleStr = u.getRole();
        if ("PROPERTY_OWNER".equalsIgnoreCase(roleStr)) roleStr = "Owner";
        else if ("RENTER".equalsIgnoreCase(roleStr)) roleStr = "Renter";
        
        JComboBox<String> cmbRole = new JComboBox<>(new String[]{"Owner", "Renter"});
        cmbRole.setSelectedItem(roleStr);
        cmbRole.setBounds(130, 135, 230, 25);
        cmbRole.setBackground(Color.WHITE);
        dialog.add(cmbRole);

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(20, 175, 100, 25);
        dialog.add(lblStatus);
        
        String statusStr = "ACTIVE".equalsIgnoreCase(u.getUserStatus()) ? "Active" : "Deactivated";
        JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"Active", "Deactivated"});
        cmbStatus.setSelectedItem(statusStr);
        cmbStatus.setBounds(130, 175, 230, 25);
        cmbStatus.setBackground(Color.WHITE);
        dialog.add(cmbStatus);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(160, 230, 90, 30);
        btnCancel.setBackground(Color.WHITE);
        btnCancel.addActionListener(e -> dialog.dispose());
        dialog.add(btnCancel);

        JButton btnSave = new JButton("Save Changes");
        btnSave.setBounds(260, 230, 110, 30);
        btnSave.setBackground(new Color(31, 97, 109));
        btnSave.setForeground(Color.WHITE);
        btnSave.addActionListener(e -> {
            String name = txtName.getText().trim();
            String email = txtEmail.getText().trim();
            String role = (String) cmbRole.getSelectedItem();
            String status = "Active".equals(cmbStatus.getSelectedItem()) ? "ACTIVE" : "DEACTIVATED";

            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (updateUser(u.getUserId(), name, email, role, status)) {
                JOptionPane.showMessageDialog(view, "User updated successfully.");
                dialog.dispose();
                loadUsers(view);
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to update user. Email might already exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.add(btnSave);

        dialog.setVisible(true);
    }

    private void confirmDelete(UserManagementView view, User u) {
        int confirm = JOptionPane.showConfirmDialog(view,
                "Are you sure you want to delete user: " + u.getFullName() + "?\nThis action cannot be undone.",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (deleteUser(u.getUserId())) {
                JOptionPane.showMessageDialog(view, "User deleted successfully.");
                loadUsers(view);
            } else {
                JOptionPane.showMessageDialog(view, "Failed to delete user.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Sidebar navigation routes for Administrator Views:
    public void navigateToDashboard(JFrame currentView) {
        new AdminDashboardView().setVisible(true);
        currentView.dispose();
    }

    public void navigateToUserManagement(JFrame currentView) {
        new UserManagementView().setVisible(true);
        currentView.dispose();
    }

    public void logout(JFrame currentView) {
        SessionService.getInstance().logout();
        new LoginView().setVisible(true);
        currentView.dispose();
    }
}

package view;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ApplicationDetailsView extends javax.swing.JFrame {

    public ApplicationDetailsView(Map<String, String> details) {
        initComponents();
        initCustomComponents(details);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rental Application Details");
        setResizable(false);
        getContentPane().setLayout(null);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void initCustomComponents(Map<String, String> details) {
        setSize(550, 520);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblHeader = new JLabel("Rental Application Details");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblHeader.setForeground(new Color(43, 108, 176));
        lblHeader.setBounds(20, 15, 300, 30);
        add(lblHeader);

        String renterName = details.getOrDefault("renterName", "N/A");
        String renterEmail = details.getOrDefault("renterEmail", "N/A");
        String renterPhone = details.getOrDefault("renterPhone", "N/A");
        String dob = details.getOrDefault("dob", "N/A");
        String moveInDateStr = details.getOrDefault("moveInDate", "N/A");
        String duration = details.getOrDefault("duration", "N/A");
        String employer = details.getOrDefault("employer", "N/A");
        String jobTitle = details.getOrDefault("jobTitle", "N/A");
        String income = details.getOrDefault("income", "N/A");

        int y = 60;
        
        // Personal Details Panel
        JPanel pnlPersonal = new JPanel(null);
        pnlPersonal.setBackground(new Color(247, 250, 252));
        pnlPersonal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)), "Personal Details"));
        pnlPersonal.setBounds(20, y, 490, 150);
        
        addLabelValue(pnlPersonal, "Full Name:", renterName, 15, 25, 200, 20);
        addLabelValue(pnlPersonal, "Email:", renterEmail, 15, 55, 200, 20);
        addLabelValue(pnlPersonal, "Phone:", renterPhone, 15, 85, 200, 20);
        addLabelValue(pnlPersonal, "Date of Birth:", dob, 15, 115, 200, 20);
        
        add(pnlPersonal);
        y += 165;

        // Rental Preferences Panel
        JPanel pnlRental = new JPanel(null);
        pnlRental.setBackground(new Color(247, 250, 252));
        pnlRental.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)), "Rental Preferences"));
        pnlRental.setBounds(20, y, 490, 90);
        
        addLabelValue(pnlRental, "Move-in Date:", moveInDateStr, 15, 25, 200, 20);
        addLabelValue(pnlRental, "Lease Duration:", duration, 15, 55, 200, 20);
        
        add(pnlRental);
        y += 105;

        // Employment & Income Panel
        JPanel pnlEmployment = new JPanel(null);
        pnlEmployment.setBackground(new Color(247, 250, 252));
        pnlEmployment.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)), "Employment & Income"));
        pnlEmployment.setBounds(20, y, 490, 110);
        
        addLabelValue(pnlEmployment, "Employer:", employer, 15, 25, 200, 20);
        addLabelValue(pnlEmployment, "Job Title:", jobTitle, 15, 55, 200, 20);
        addLabelValue(pnlEmployment, "Monthly Income:", income, 15, 85, 200, 20);
        
        add(pnlEmployment);
        y += 120;

        JButton btnClose = new JButton("Close");
        btnClose.setBackground(new Color(43, 108, 176));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnClose.setBounds(410, y, 100, 30);
        btnClose.addActionListener(e -> dispose());
        add(btnClose);
        
        setLocationRelativeTo(null);
    }

    private void addLabelValue(JPanel panel, String labelText, String valueText, int x, int y, int width, int height) {
        JLabel lbl = new JLabel(labelText);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(new Color(74, 85, 104));
        lbl.setBounds(x, y, 110, height);
        panel.add(lbl);

        JLabel val = new JLabel(valueText);
        val.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        val.setForeground(Color.BLACK);
        val.setBounds(x + 120, y, 340, height);
        panel.add(val);
    }
}

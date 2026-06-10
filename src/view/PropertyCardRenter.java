package view;

import Model.Property;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.ActionListener;

public class PropertyCardRenter extends javax.swing.JPanel {

    private Property property;

    public PropertyCardRenter() {
        initComponents();
        this.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true));
    }

    public void setPropertyData(Property p, ActionListener onSave, ActionListener onApply) {
        this.property = p;
        lblTitle.setText(p.getTitle());
        lblLoc.setText(p.getAddress());
        
        String ratingStars = p.getAvgRating() >= 4.5 ? "★★★★★" : "★★★★☆";
        lblRating.setText("<html><font color='#F39C12'>" + ratingStars + "</font> <font color='gray'>" + p.getAvgRating() + "</font></html>");
        lblPrice.setText("Rs. " + p.getMonthlyRent() + " / mo");
        
        if (p.getPrimaryImagePath() != null && !p.getPrimaryImagePath().isEmpty()) {
            java.io.File file = new java.io.File(p.getPrimaryImagePath());
            if (file.exists()) {
                javax.swing.ImageIcon icon = new javax.swing.ImageIcon(p.getPrimaryImagePath());
                java.awt.Image img = icon.getImage().getScaledInstance(220, 130, java.awt.Image.SCALE_SMOOTH);
                lblImage.setIcon(new javax.swing.ImageIcon(img));
                lblImage.setText("");
            } else {
                lblImage.setIcon(null);
                lblImage.setText("<html><div style='text-align: center; color: white; padding-top: 40px;'>No Image</div></html>");
            }
        } else {
            lblImage.setIcon(null);
            lblImage.setText("<html><div style='text-align: center; color: white; padding-top: 40px;'>No Image</div></html>");
        }
        
        for (ActionListener al : btnSave.getActionListeners()) {
            btnSave.removeActionListener(al);
        }
        btnSave.addActionListener(onSave);
        
        for (ActionListener al : btnApply.getActionListeners()) {
            btnApply.removeActionListener(al);
        }
        btnApply.addActionListener(onApply);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSave = new javax.swing.JButton();
        lblImage = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblLoc = new javax.swing.JLabel();
        lblRating = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        btnApply = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(220, 280));
        setLayout(null);

        btnSave.setText("+");
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSave.setForeground(new java.awt.Color(30, 92, 240));
        btnSave.setMargin(new java.awt.Insets(0,0,0,0));
        add(btnSave);
        btnSave.setBounds(190, 5, 20, 20);

        lblImage.setBackground(new java.awt.Color(30, 80, 120));
        lblImage.setText("<html><div style='text-align: center; color: white; padding-top: 40px;'>No Image</div></html>");
        lblImage.setOpaque(true);
        add(lblImage);
        lblImage.setBounds(0, 0, 220, 130);

        lblTitle.setText("Lakeside Apartment");
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        add(lblTitle);
        lblTitle.setBounds(15, 140, 190, 20);

        lblLoc.setText("Mumbai");
        lblLoc.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblLoc.setForeground(new java.awt.Color(128, 128, 128));
        add(lblLoc);
        lblLoc.setBounds(15, 160, 190, 20);

        lblRating.setText("<html><font color='#F39C12'>★★★★☆</font> <font color='gray'>4.5</font></html>");
        add(lblRating);
        lblRating.setBounds(15, 185, 190, 20);

        lblPrice.setText("Rs. 45000 / mo");
        lblPrice.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        add(lblPrice);
        lblPrice.setBounds(15, 235, 110, 30);

        btnApply.setText("Apply >");
        btnApply.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnApply.setBackground(new java.awt.Color(40, 92, 240));
        btnApply.setForeground(new java.awt.Color(255, 255, 255));
        btnApply.setMargin(new java.awt.Insets(2,2,2,2));
        add(btnApply);
        btnApply.setBounds(125, 235, 80, 30);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLoc;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblRating;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}

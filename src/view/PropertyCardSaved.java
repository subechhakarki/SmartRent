package view;

import Model.Property;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.ActionListener;

public class PropertyCardSaved extends javax.swing.JPanel {

    private Property property;

    public PropertyCardSaved() {
        initComponents();
        this.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true));
    }

    public void setPropertyData(Property p, ActionListener onRemove, ActionListener onApply) {
        this.property = p;
        lblTitle.setText(p.getTitle() != null ? p.getTitle() : "Unknown");
        lblLoc.setText(p.getAddress() != null ? p.getAddress() : "");
        
        String priceText = "R.S  \u20b9" + String.format("%.0f", p.getMonthlyRent()) + "/month";
        lblPrice.setText(priceText);
        
        for (ActionListener al : btnRemove.getActionListeners()) {
            btnRemove.removeActionListener(al);
        }
        btnRemove.addActionListener(onRemove);
        
        for (ActionListener al : btnApply.getActionListeners()) {
            btnApply.removeActionListener(al);
        }
        btnApply.addActionListener(onApply);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImage = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblLoc = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        btnRemove = new javax.swing.JButton();
        btnApply = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(300, 310));
        setLayout(null);

        lblImage.setBackground(new java.awt.Color(30, 80, 120));
        lblImage.setText("<html><div style='text-align: center; color: white; padding-top: 50px;'>No Image</div></html>");
        lblImage.setOpaque(true);
        add(lblImage);
        lblImage.setBounds(0, 0, 300, 160);

        lblTitle.setText("Unknown");
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        add(lblTitle);
        lblTitle.setBounds(15, 170, 150, 25);

        lblLoc.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblLoc.setForeground(new java.awt.Color(128, 128, 128));
        add(lblLoc);
        lblLoc.setBounds(165, 173, 120, 20);

        lblPrice.setText("Rs. 0 / month");
        lblPrice.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        add(lblPrice);
        lblPrice.setBounds(15, 205, 240, 25);

        btnRemove.setText("Removed from saved");
        btnRemove.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnRemove.setBackground(new java.awt.Color(231, 76, 60));
        btnRemove.setForeground(new java.awt.Color(255, 255, 255));
        btnRemove.setMargin(new java.awt.Insets(2,2,2,2));
        add(btnRemove);
        btnRemove.setBounds(15, 255, 145, 35);

        btnApply.setText("Apply");
        btnApply.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnApply.setBackground(new java.awt.Color(46, 204, 113));
        btnApply.setForeground(new java.awt.Color(255, 255, 255));
        btnApply.setMargin(new java.awt.Insets(2,2,2,2));
        add(btnApply);
        btnApply.setBounds(170, 255, 110, 35);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnRemove;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLoc;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}

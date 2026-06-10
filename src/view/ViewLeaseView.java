package view;

import Model.Lease;
import javax.swing.*;

public class ViewLeaseView extends javax.swing.JFrame {

    private javax.swing.JButton btnDownloadPDF;

    public ViewLeaseView(Lease lease) {
        initComponents();
        
        btnDownloadPDF = new javax.swing.JButton("Download PDF");
        btnDownloadPDF.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnDownloadPDF.setBackground(new java.awt.Color(43, 108, 176));
        btnDownloadPDF.setForeground(java.awt.Color.WHITE);
        btnDownloadPDF.setFocusPainted(false);
        btnDownloadPDF.addActionListener(e -> {
            new Controller.LeaseController().downloadLeasePDF(this, lease);
        });
        getContentPane().add(btnDownloadPDF);
        btnDownloadPDF.setBounds(20, 630, 180, 30);
        
        setSize(600, 720);
        setLocationRelativeTo(null);
        
        new Controller.LeaseController().initViewLeaseView(this, lease);
    }

    public javax.swing.JTextArea getTxtLeaseDetails() { return txtLeaseDetails; }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHeader = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        txtLeaseDetails = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SmartRent - Lease Agreement");
        setPreferredSize(new java.awt.Dimension(600, 700));
        getContentPane().setLayout(null);

        lblHeader.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblHeader.setText("Formal Lease Agreement");
        getContentPane().add(lblHeader);
        lblHeader.setBounds(20, 20, 400, 40);

        txtLeaseDetails.setEditable(false);
        txtLeaseDetails.setColumns(20);
        txtLeaseDetails.setRows(5);
        txtLeaseDetails.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        txtLeaseDetails.setMargin(new java.awt.Insets(15, 15, 15, 15));
        scrollPane.setViewportView(txtLeaseDetails);

        getContentPane().add(scrollPane);
        scrollPane.setBounds(20, 70, 540, 550);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblHeader;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextArea txtLeaseDetails;
    // End of variables declaration//GEN-END:variables
}

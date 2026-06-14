package view;

import javax.swing.JOptionPane;

public class ForgotPasswordView extends javax.swing.JFrame {

    public ForgotPasswordView() {
        initComponents();
        
        // Placeholder handling for txtEmail
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtEmail.getText().equals("Enter your email address")) {
                    txtEmail.setText("");
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtEmail.getText().trim().isEmpty()) {
                    txtEmail.setText("Enter your email address");
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabelNewPassword = new javax.swing.JLabel();
        txtNewPassword = new javax.swing.JPasswordField();
        jLabelConfirmPassword = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        btnReset = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartRent - Forgot Password");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Gadugi", 1, 26)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Reset Password");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 180, -1, -1));

        jLabel3.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Enter your details to change password");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 220, -1, -1));

        jLabel9.setFont(new java.awt.Font("Gadugi", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Email Address");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 270, -1, -1));

        txtEmail.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(102, 102, 102));
        txtEmail.setText("Enter your email address");
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 300, 320, 40));

        jLabelNewPassword.setFont(new java.awt.Font("Gadugi", 1, 16)); // NOI18N
        jLabelNewPassword.setForeground(new java.awt.Color(102, 102, 102));
        jLabelNewPassword.setText("New Password");
        jPanel1.add(jLabelNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 360, -1, -1));
        jPanel1.add(txtNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 390, 320, 40));

        jLabelConfirmPassword.setFont(new java.awt.Font("Gadugi", 1, 16)); // NOI18N
        jLabelConfirmPassword.setForeground(new java.awt.Color(102, 102, 102));
        jLabelConfirmPassword.setText("Confirm Password");
        jPanel1.add(jLabelConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 450, -1, -1));
        jPanel1.add(txtConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 480, 320, 40));

        btnReset.setBackground(new java.awt.Color(0, 102, 102));
        btnReset.setFont(new java.awt.Font("Gadugi", 1, 16)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Update Password");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel1.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 550, 320, 40));

        btnBack.setForeground(new java.awt.Color(0, 102, 102));
        btnBack.setText("Back to Login");
        btnBack.setBorder(null);
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 610, 100, 25));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Screenshot 2026-06-02 212659.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 12, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1290, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        String email = txtEmail.getText().trim();
        String newPassword = new String(txtNewPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        Controller.AuthController authController = new Controller.AuthController();
        
        // Step 1: Send the OTP
        String otpResult = authController.sendPasswordResetOTP(email);
        if (otpResult.equals("SUCCESS")) {
            // Step 2: Show prompt to enter OTP
            String otp = JOptionPane.showInputDialog(
                this, 
                "A 6-digit verification code has been sent to " + email + ".\nPlease enter it below to verify your account:", 
                "Email Verification", 
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (otp == null) {
                // User cancelled the prompt
                return;
            }
            
            otp = otp.trim();
            if (otp.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Verification code cannot be empty.", "Verification Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Step 3: Verify and Reset Password
            String resetResult = authController.verifyOTPAndResetPassword(email, otp, newPassword, confirmPassword);
            if (resetResult.equals("SUCCESS")) {
                JOptionPane.showMessageDialog(this, "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new LoginView().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, resetResult, "Reset Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, otpResult, "Reset Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new LoginView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBack;
    public javax.swing.JButton btnReset;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JLabel jLabelConfirmPassword;
    public javax.swing.JLabel jLabelNewPassword;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPasswordField txtConfirmPassword;
    public javax.swing.JTextField txtEmail;
    public javax.swing.JPasswordField txtNewPassword;
    // End of variables declaration//GEN-END:variables
}

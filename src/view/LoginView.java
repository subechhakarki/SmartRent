package view;
public class LoginView extends javax.swing.JFrame {
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JButton btnLogin;
    public LoginView() {
        initComponents();
    }
    private void initComponents() {
        lblTitle = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
    }
    public void addLoginListener(java.awt.event.ActionListener l) {
        btnLogin.addActionListener(l);
    }
}

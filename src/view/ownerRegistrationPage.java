package view;
public class ownerRegistrationPage extends javax.swing.JFrame {
    private javax.swing.JLabel Head;
    private javax.swing.JLabel Email;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton CreateOwnerAccount;
    public ownerRegistrationPage() {
        initComponents();
    }
    private void initComponents() {
        Head = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        CreateOwnerAccount = new javax.swing.JButton();
    }
    public void addCreateAccountListener(java.awt.event.ActionListener l) {
        CreateOwnerAccount.addActionListener(l);
    }
}

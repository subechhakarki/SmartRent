/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author ASUS
 */
import DAO.OwnerRegistrationDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Model.OwnerRegistration;
import java.awt.HeadlessException;
import view.OwnerRegistrationPage;

/**
 *
 * @author Bibek Bidari
 */
public class OwnerRegistrationController {
    private final OwnerRegistrationDAO ownerDao = new OwnerRegistrationDAO();
    private final OwnerRegistrationPage ownerView;
    public OwnerRegistrationController(OwnerRegistrationPage ownerView) {
        this.ownerView = ownerView;
        ownerView.addCreateAccountListener(new CreateAccountListener());
    }
    public void open() {
        this.ownerView.setVisible(true);
    }
    public void close() {
        this.ownerView.dispose();
    }
    class CreateAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String fullName = ownerView.getFullNameField().getText();
                String email = ownerView.getEmailField().getText();
                String phoneNumber = ownerView.getPhoneNumberField().getText();
                String address = ownerView.getAddressField().getText();
                String businessName = ownerView.getBusinessNameField().getText();
                String registrationNumber = ownerView.getRegistrationNumberField().getText();
                String businessAddress = ownerView.getBusinessAddressField().getText();
                String businessEmail = ownerView.getBusinessEmailField().getText();
                String password = new String(ownerView.getPasswordField().getPassword());
                String confirmPassword = new String(ownerView.getConfirmPasswordField().getPassword());
                String hashedPassword = Integer.toHexString(password.hashCode());
                
                OwnerRegistration owner = new OwnerRegistration(
                fullName, email, phoneNumber, address,
                businessName, registrationNumber,
                businessAddress, businessEmail,
                hashedPassword, hashedPassword
                );
                
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(ownerView, "Passwords do not match");
                    return;
                }
                
                boolean check = ownerDao.checkOwner(owner);
                if (check) {
                    JOptionPane.showMessageDialog(ownerView, "Owner already exists");
                } else {
                    ownerDao.createOwner(owner);
                    JOptionPane.showMessageDialog(ownerView, "Account created successfully");
                    close();
                }
            } catch (HeadlessException ex) {
                System.out.println("Error adding owner: " + ex.getMessage());
            }
        }
    }
    
}

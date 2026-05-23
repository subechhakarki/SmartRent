/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartrent;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Subechha Karki
 */

public class MainFrame extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    public MainFrame() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add all panels
        mainPanel.add(new view.LandingView(this), "LANDING");
        mainPanel.add(new view.RenterRegisterView(this), "RENTER_REGISTER");

        // These will be uncommented as teammates finish their views
        // mainPanel.add(new view.LoginView(this), "LOGIN");
        // mainPanel.add(new view.OwnerRegistration(this), "OWNER_REGISTER");

        // Frame settings
        setContentPane(mainPanel);
        setTitle("SmartRent");
        setSize(1030, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Show landing page first
        showPanel("LANDING");
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
}

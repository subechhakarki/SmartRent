package Controller;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LogoLoader {
    public static void setLogo(JLabel lblLogo, int width, int height) {
        if (lblLogo == null) return;
        try {
            ImageIcon icon = null;
            // 1. Try class loader resource (for packaged JAR execution)
            URL imgUrl = LogoLoader.class.getResource("/Images/SmartRentLogo_Banner.png");
            if (imgUrl != null) {
                icon = new ImageIcon(imgUrl);
            } else {
                // 2. Try filesystem (for IDE / development directory run)
                File file = new File("src/Images/SmartRentLogo_Banner.png");
                if (file.exists()) {
                    icon = new ImageIcon(file.getAbsolutePath());
                } else {
                    file = new File("Images/SmartRentLogo_Banner.png");
                    if (file.exists()) {
                        icon = new ImageIcon(file.getAbsolutePath());
                    }
                }
            }

            if (icon != null && icon.getImage() != null) {
                Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(img));
                lblLogo.setText(""); // Remove default text
            }
        } catch (Exception e) {
            System.err.println("Failed to load logo: " + e.getMessage());
        }
    }

    public static void styleRenterSidebar(
            javax.swing.JFrame frame,
            javax.swing.JPanel pnlSidebar,
            javax.swing.JLabel lblLogo,
            javax.swing.JButton btnDash,
            javax.swing.JButton btnApps,
            javax.swing.JButton btnRatings,
            javax.swing.JButton btnSaved,
            javax.swing.JButton btnLogout,
            String activePage) {
        if (lblLogo != null) {
            setLogo(lblLogo, 200, 64);
        }
        
        java.awt.Color activeBg = new java.awt.Color(80, 128, 128);
        java.awt.Color inactiveBg = new java.awt.Color(60, 110, 113);
        
        javax.swing.JButton[] buttons = {btnDash, btnApps, btnRatings, btnSaved, btnLogout};
        String[] pages = {"dashboard", "applications", "ratings", "saved", "logout"};
        
        for (int i = 0; i < buttons.length; i++) {
            javax.swing.JButton btn = buttons[i];
            if (btn != null) {
                if (pages[i].equals(activePage)) {
                    btn.setBackground(activeBg);
                } else {
                    btn.setBackground(inactiveBg);
                }
            }
        }
    }

    public static void styleOwnerSidebar(
            javax.swing.JFrame frame,
            javax.swing.JPanel pnlSidebar,
            javax.swing.JLabel lblLogo,
            javax.swing.JButton btnDash,
            javax.swing.JButton btnProps,
            javax.swing.JButton btnLeases,
            javax.swing.JButton btnLogout,
            String activePage) {
        if (lblLogo != null) {
            setLogo(lblLogo, 200, 64);
        }
        
        java.awt.Color activeBg = new java.awt.Color(80, 128, 128);
        java.awt.Color inactiveBg = new java.awt.Color(60, 110, 113);
        
        javax.swing.JButton[] buttons = {btnDash, btnProps, btnLeases, btnLogout};
        String[] pages = {"dashboard", "properties", "leases", "logout"};
        
        for (int i = 0; i < buttons.length; i++) {
            javax.swing.JButton btn = buttons[i];
            if (btn != null) {
                if (pages[i].equals(activePage)) {
                    btn.setBackground(activeBg);
                } else {
                    btn.setBackground(inactiveBg);
                }
            }
        }
    }

    public static void styleAdminSidebar(
            javax.swing.JFrame frame,
            javax.swing.JPanel pnlSidebar,
            javax.swing.JLabel lblLogo,
            javax.swing.JButton btnDash,
            javax.swing.JButton btnUsers,
            javax.swing.JButton btnLogout,
            String activePage) {
        if (lblLogo != null) {
            setLogo(lblLogo, 200, 64);
        }
        
        java.awt.Color activeBg = new java.awt.Color(80, 128, 128);
        java.awt.Color inactiveBg = new java.awt.Color(60, 110, 113);
        
        javax.swing.JButton[] buttons = {btnDash, btnUsers, btnLogout};
        String[] pages = {"dashboard", "users", "logout"};
        
        for (int i = 0; i < buttons.length; i++) {
            javax.swing.JButton btn = buttons[i];
            if (btn != null) {
                if (pages[i].equals(activePage)) {
                    btn.setBackground(activeBg);
                } else {
                    btn.setBackground(inactiveBg);
                }
            }
        }
    }
}


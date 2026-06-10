package Controller;

import DAO.SavedPropertyDAO;
import Model.Property;
import Model.User;
import smartrent.SessionService;
import view.*;

import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.util.List;

public class SavedPropertyController {

    private SavedPropertyDAO savedPropertyDAO;

    public SavedPropertyController() {
        this.savedPropertyDAO = new SavedPropertyDAO();
    }

    public boolean saveProperty(int propertyId) {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null || !"RENTER".equals(currentUser.getRole())) {
            return false;
        }
        return savedPropertyDAO.saveProperty(currentUser.getUserId(), propertyId);
    }

    public boolean removeSavedProperty(int propertyId) {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null || !"RENTER".equals(currentUser.getRole())) {
            return false;
        }
        return savedPropertyDAO.removeSavedProperty(currentUser.getUserId(), propertyId);
    }

    public List<Property> getMySavedProperties() {
        User currentUser = SessionService.getInstance().getCurrentUser();
        if (currentUser == null || !"RENTER".equals(currentUser.getRole())) {
            return new java.util.ArrayList<>();
        }
        return savedPropertyDAO.getSavedPropertiesForRenter(currentUser.getUserId());
    }

    // Presentation logic for SavedPropertiesView
    public void initSavedPropertiesView(SavedPropertiesView view) {
        try {
            User currentUser = SessionService.getInstance().getCurrentUser();
            if (currentUser != null) {
                view.getLblWelcome().setText("Welcome, " + currentUser.getFullName().split(" ")[0]);
            }
        } catch (Exception e) { /* ignore */ }
        
        view.getScrollPaneApps().getVerticalScrollBar().setUnitIncrement(16);
        view.getPnlGrid().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 20));
        
        loadSavedProperties(view);
        
        view.setSize(1280, 800);
        view.setPreferredSize(new java.awt.Dimension(1280, 800));
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.revalidate();
        view.repaint();
    }

    public void loadSavedProperties(SavedPropertiesView view) {
        List<Property> savedProps = new java.util.ArrayList<>();
        try {
            savedProps = getMySavedProperties();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        if (savedProps == null) {
            savedProps = new java.util.ArrayList<>();
        }
        
        view.getPnlCard1().setVisible(false);
        view.getPnlCard2().setVisible(false);
        view.getPnlCard3().setVisible(false);
        view.getPnlCard4().setVisible(false);
        view.getPnlCard5().setVisible(false);
        view.getPnlCard6().setVisible(false);
        
        int count = Math.min(savedProps.size(), 6);
        for (int i = 0; i < count; i++) {
            Property p = savedProps.get(i);
            if (i == 0) configureCard(view, view.getPnlCard1(), p);
            else if (i == 1) configureCard(view, view.getPnlCard2(), p);
            else if (i == 2) configureCard(view, view.getPnlCard3(), p);
            else if (i == 3) configureCard(view, view.getPnlCard4(), p);
            else if (i == 4) configureCard(view, view.getPnlCard5(), p);
            else if (i == 5) configureCard(view, view.getPnlCard6(), p);
        }
        
        int containerWidth = view.getPnlGrid().getWidth();
        if (containerWidth <= 0) {
            containerWidth = 980;
        }
        int cardWidth = 300;
        int gap = 20;
        int cols = (containerWidth - gap) / (cardWidth + gap);
        if (cols <= 0) cols = 1;
        
        int rows = (int) Math.ceil(count / (double) cols);
        int prefHeight = rows * (310 + gap) + gap;
        view.getPnlGrid().setPreferredSize(new Dimension(containerWidth, Math.max(prefHeight, 660)));
        
        view.getPnlGrid().revalidate();
        view.getPnlGrid().repaint();
    }

    private void configureCard(SavedPropertiesView view, PropertyCardSaved card, Property p) {
        card.setPropertyData(
            p,
            e -> {
                if (removeSavedProperty(p.getPropertyId())) {
                    JOptionPane.showMessageDialog(view, "Removed from saved properties.");
                    loadSavedProperties(view);
                }
            },
            e -> {
                new ApplicationFormView(p.getPropertyId()).setVisible(true);
            }
        );
        card.setVisible(true);
    }

    public void navigateToDashboard(SavedPropertiesView view) {
        new RenterDashboardView().setVisible(true);
        view.dispose();
    }

    public void navigateToMyApplications(SavedPropertiesView view) {
        new MyApplicationView().setVisible(true);
        view.dispose();
    }

    public void navigateToPropertyRatings(SavedPropertiesView view) {
        new PropertyRatingsView().setVisible(true);
        view.dispose();
    }

    public void logout(SavedPropertiesView view) {
        SessionService.getInstance().logout();
        new LoginView().setVisible(true);
        view.dispose();
    }
}

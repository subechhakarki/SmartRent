package Model;

import java.util.Date;

public class SavedProperty {
    private int saveId;
    private int renterId;
    private int propertyId;
    private Date savedAt;
    
    // UI field
    private Property property;

    public SavedProperty() {}

    public int getSaveId() { return saveId; }
    public void setSaveId(int saveId) { this.saveId = saveId; }

    public int getRenterId() { return renterId; }
    public void setRenterId(int renterId) { this.renterId = renterId; }

    public int getPropertyId() { return propertyId; }
    public void setPropertyId(int propertyId) { this.propertyId = propertyId; }

    public Date getSavedAt() { return savedAt; }
    public void setSavedAt(Date savedAt) { this.savedAt = savedAt; }

    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }
}

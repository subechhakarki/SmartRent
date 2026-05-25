package Model;

import java.util.Date;

public class Property {
    private int propertyId;
    private int ownerId;
    private String title;
    private String address;
    private String propertyType;
    private int bedrooms;
    private int bathrooms;
    private double monthlyRent;
    private double deposit;
    private Date availableFrom;
    private String propStatus; // AVAILABLE, OCCUPIED, MAINTENANCE
    private double avgRating;
    private Date createdAt;
    
    // Additional fields for UI display
    private String primaryImagePath;

    public Property() {
    }

    // Getters and Setters
    public int getPropertyId() { return propertyId; }
    public void setPropertyId(int propertyId) { this.propertyId = propertyId; }

    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPropertyType() { return propertyType; }
    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }

    public int getBedrooms() { return bedrooms; }
    public void setBedrooms(int bedrooms) { this.bedrooms = bedrooms; }

    public int getBathrooms() { return bathrooms; }
    public void setBathrooms(int bathrooms) { this.bathrooms = bathrooms; }

    public double getMonthlyRent() { return monthlyRent; }
    public void setMonthlyRent(double monthlyRent) { this.monthlyRent = monthlyRent; }

    public double getDeposit() { return deposit; }
    public void setDeposit(double deposit) { this.deposit = deposit; }

    public Date getAvailableFrom() { return availableFrom; }
    public void setAvailableFrom(Date availableFrom) { this.availableFrom = availableFrom; }

    public String getPropStatus() { return propStatus; }
    public void setPropStatus(String propStatus) { this.propStatus = propStatus; }

    public double getAvgRating() { return avgRating; }
    public void setAvgRating(double avgRating) { this.avgRating = avgRating; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public String getPrimaryImagePath() { return primaryImagePath; }
    public void setPrimaryImagePath(String primaryImagePath) { this.primaryImagePath = primaryImagePath; }
}

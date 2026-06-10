package Model;

import java.util.Date;

public class Lease {
    private int leaseId;
    private int applicationId;
    private int propertyId;
    private int renterId;
    private int ownerId;
    private double monthlyRent;
    private double deposit;
    private Date startDate;
    private Date endDate;
    private String terms;
    private String status; // e.g., ACTIVE, TERMINATED
    private Date createdAt;
    
    // UI fields
    private String propertyTitle;
    private String renterName;
    private String ownerName;

    public Lease() {}

    public int getLeaseId() { return leaseId; }
    public void setLeaseId(int leaseId) { this.leaseId = leaseId; }

    public int getApplicationId() { return applicationId; }
    public void setApplicationId(int applicationId) { this.applicationId = applicationId; }

    public int getPropertyId() { return propertyId; }
    public void setPropertyId(int propertyId) { this.propertyId = propertyId; }

    public int getRenterId() { return renterId; }
    public void setRenterId(int renterId) { this.renterId = renterId; }

    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public double getMonthlyRent() { return monthlyRent; }
    public void setMonthlyRent(double monthlyRent) { this.monthlyRent = monthlyRent; }

    public double getDeposit() { return deposit; }
    public void setDeposit(double deposit) { this.deposit = deposit; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getTerms() { return terms; }
    public void setTerms(String terms) { this.terms = terms; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public String getPropertyTitle() { return propertyTitle; }
    public void setPropertyTitle(String propertyTitle) { this.propertyTitle = propertyTitle; }

    public String getRenterName() { return renterName; }
    public void setRenterName(String renterName) { this.renterName = renterName; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
}

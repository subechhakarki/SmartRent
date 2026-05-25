package Model;

import java.util.Date;

public class Rating {
    private int ratingId;
    private int propertyId;
    private int renterId;
    private int score;
    private String reviewText;
    private Date createdAt;
    
    // UI fields
    private String propertyTitle;
    private String renterName;

    public Rating() {}

    public int getRatingId() { return ratingId; }
    public void setRatingId(int ratingId) { this.ratingId = ratingId; }

    public int getPropertyId() { return propertyId; }
    public void setPropertyId(int propertyId) { this.propertyId = propertyId; }

    public int getRenterId() { return renterId; }
    public void setRenterId(int renterId) { this.renterId = renterId; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public String getPropertyTitle() { return propertyTitle; }
    public void setPropertyTitle(String propertyTitle) { this.propertyTitle = propertyTitle; }

    public String getRenterName() { return renterName; }
    public void setRenterName(String renterName) { this.renterName = renterName; }
}

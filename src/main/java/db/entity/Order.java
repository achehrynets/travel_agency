package db.entity;

import java.util.Comparator;

public class Order extends Entity implements Comparator<Order> {

    private User user;
    private Tour tour;
    private int statusId;
    private String status;
    private int peopleAmount;
    private float totalPrice;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPeopleAmount() {
        return peopleAmount;
    }

    public void setPeopleAmount(int peopleAmount) {
        this.peopleAmount = peopleAmount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int compare(Order o1, Order o2) {
        return o1.getStatusId() - o2.getStatusId();
    }

    public String toString() {
        return "Order{" +
                "user=" + user +
                ", tour=" + tour +
                ", statusId=" + statusId +
                ", status='" + status + '\'' +
                ", peopleAmount=" + peopleAmount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

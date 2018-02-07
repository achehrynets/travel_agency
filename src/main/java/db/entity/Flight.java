package db.entity;

import java.sql.Time;
import java.sql.Timestamp;

public class Flight extends Entity{

    private String departurePoint;
    private Timestamp departureDate;
    private Time travelTime;
    private String arrivalPoint;
    private Timestamp arrivalDate;
    private float price;
    private int transportTypeId;

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public Timestamp getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Timestamp departureDate) {
        this.departureDate = departureDate;
    }

    public Time getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Time travelTime) {
        this.travelTime = travelTime;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTransportTypeId() {
        return transportTypeId;
    }

    public void setTransportTypeId(int transportTypeId) {
        this.transportTypeId = transportTypeId;
    }

    public String toString() {
        return "Flight{" +
                "departurePoint='" + departurePoint + '\'' +
                ", departureDate=" + departureDate +
                ", travelTime=" + travelTime +
                ", arrivalPoint='" + arrivalPoint + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", price=" + price +
                ", transportTypeId=" + transportTypeId +
                '}';
    }
}

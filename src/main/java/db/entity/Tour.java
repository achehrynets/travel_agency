package db.entity;

import java.util.Comparator;
import java.util.Date;

public class Tour extends Entity implements Comparator<Tour>{

    private String name;
    private Date tourDate;
    private int amountOfDays;
    private int placeQuantity;
    private boolean hotTour;
    private float totalPrice;
    private int tourTypeId;
    private Country country;
    private Resort resort;
    private Hotel hotel;
    private Flight flight;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTourDate() {
        return tourDate;
    }

    public void setTourDate(Date tourDate) {
        this.tourDate = tourDate;
    }

    public int getAmountOfDays() {
        return amountOfDays;
    }

    public void setAmountOfDays(int amountOfDays) {
        this.amountOfDays = amountOfDays;
    }

    public int getPlaceQuantity() {
        return placeQuantity;
    }

    public void setPlaceQuantity(int placeQuantity) {
        this.placeQuantity = placeQuantity;
    }

    public boolean isHotTour() {
        return hotTour;
    }

    public void setHotTour(boolean hotTour) {
        this.hotTour = hotTour;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTourTypeId() {
        return tourTypeId;
    }

    public void setTourTypeId(int tourTypeId) {
        this.tourTypeId = tourTypeId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Resort getResort() {
        return resort;
    }

    public void setResort(Resort resort) {
        this.resort = resort;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int compare(Tour o1, Tour o2) {
        return Boolean.valueOf(o2.isHotTour()).compareTo(o1.isHotTour());
    }

    public String toString() {
        return "Tour{" +
                "name='" + name + '\'' +
                ", tourDate=" + tourDate +
                ", amountOfDays=" + amountOfDays +
                ", placeQuantity=" + placeQuantity +
                ", hotTour=" + hotTour +
                ", totalPrice=" + totalPrice +
                ", tourTypeId=" + tourTypeId +
                ", country=" + country +
                ", resort=" + resort +
                ", hotel=" + hotel +
                ", flight=" + flight +
                '}';
    }
}

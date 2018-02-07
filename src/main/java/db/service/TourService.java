package db.service;

import db.entity.*;
import exception.DatabaseException;

public interface TourService {

    void addTour(Tour tour) throws DatabaseException;

    void addTourWhenCountryExist(Tour tour) throws DatabaseException;

    void addTourWhenCountryAndResortExist(Tour tour) throws DatabaseException;

    void addTourWhenCountryAndResortAndHotelExist(Tour tour) throws DatabaseException;

}

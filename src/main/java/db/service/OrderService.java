package db.service;

import db.entity.Order;
import exception.DatabaseException;

public interface OrderService {

    void addOrderAndUpdateTour(Order order) throws DatabaseException;

}

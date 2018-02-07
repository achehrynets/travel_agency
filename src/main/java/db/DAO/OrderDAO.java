package db.DAO;

import db.entity.Order;
import exception.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {

    void addOrder(Connection connection, Order order) throws SQLException;

    List<Order> getAllUserOrdersByUserId(Long id) throws DatabaseException;

    List<Order> getAllOrders() throws DatabaseException;

    void updateOrderStatus(Long orderId, Long statusId) throws DatabaseException;

    void updateOrderPrice(Long orderId, float price) throws DatabaseException;

    List<Order> getTotalPriceOfPaidToursForClient() throws DatabaseException;
}

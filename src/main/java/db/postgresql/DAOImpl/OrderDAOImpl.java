package db.postgresql.DAOImpl;

import constant.ErrorMessages;
import constant.SQL;
import db.DAO.OrderDAO;
import db.entity.Order;
import db.entity.Status;
import db.entity.Tour;
import db.entity.User;
import db.postgresql.PostgresqlDAOFactory;
import exception.DatabaseException;

import static util.DBUtil.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    public void addOrder(Connection connection, Order order) throws SQLException {
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQL.ADD_ORDER);
            ps.setFloat(1, order.getTotalPrice());
            ps.setInt(2, order.getPeopleAmount());
            ps.setLong(3, order.getUser().getId());
            ps.setInt(4, order.getStatusId());
            ps.setLong(5, order.getTour().getId());
            ps.executeUpdate();

        } finally {
            close(ps);
        }
    }

    public List<Order> getAllUserOrdersByUserId(Long id) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.GET_ALL_USER_ORDERS_BY_USER_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(extractOrderFromResultSet(rs));
            }

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_ALL_USER_ORDERS_BY_USER_ID, ex);
        } finally {
            close(connection, ps, rs);
        }

        return orders;
    }

    public List<Order> getAllOrders() throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();

        try {
            connection = factory.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(SQL.GET_ALL_ORDERS);
            while (rs.next()) {
                orders.add(extractOrderFromResultSet(rs));
            }

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_ALL_ORDERS, ex);
        } finally {
            close(connection, st, rs);
        }

        return orders;
    }

    public List<Order> getTotalPriceOfPaidToursForClient() throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();

        try {
            connection = factory.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(SQL.GET_TOTAL_PRICE_OF_PAID_TOUR_FOR_CLIENTS);

            while (rs.next()) {
                Order order = new Order();
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                order.setUser(user);
                order.setTotalPrice(rs.getFloat("total_price"));
                order.setStatus(rs.getString("name"));
                orders.add(order);
            }

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_ALL_USER_ORDERS_BY_USER_ID, ex);
        } finally {
            close(connection, st, rs);
        }

        return orders;
    }

    public void updateOrderStatus(Long orderId, Long statusId) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.UPDATE_ORDER_STATUS_BY_ORDER_ID);
            ps.setLong(1, statusId);
            ps.setLong(2, orderId);
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_UPDATE_ORDER_STATUS, ex);
        } finally {
            close(ps);
            close(connection);
        }
    }

    public void updateOrderPrice(Long orderId, float price) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.UPDATE_ORDER_PRICE);
            ps.setFloat(1, price);
            ps.setLong(2, orderId);
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_UPDATE_ORDER_PRICE, ex);
        } finally {
            close(ps);
            close(connection);
        }
    }

    private Order extractOrderFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setPeopleAmount(rs.getInt("people_amount"));
        order.setTotalPrice(rs.getFloat("total_price"));

        User user = new User();
        user.setId(rs.getLong("users_id"));
        user.setLogin(rs.getString("users_login"));
        user.setPhone(rs.getString("users_phone"));
        user.setEmail(rs.getString("users_email"));
        user.setSurname(rs.getString("users_surname"));
        user.setName(rs.getString("users_name"));
        order.setUser(user);

        Tour tour = new Tour();
        tour.setId(rs.getLong("tours_id"));
        tour.setName(rs.getString("tours_name"));
        order.setTour(tour);

        order.setStatusId(rs.getInt("status_id"));
        order.setStatus(Status.getSatus(order).getName());
        return order;
    }

}

package db.service.postgersqlImpl;

import constant.AppConst;
import constant.ErrorMessages;
import db.DAO.OrderDAO;
import db.DAO.TourDAO;
import db.DAOFactory;
import db.entity.Order;
import db.postgresql.PostgresqlDAOFactory;
import db.service.OrderService;
import exception.DatabaseException;
import static util.DBUtil.*;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private TourDAO tourDAO;

    public OrderServiceImpl() throws DatabaseException {
        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        orderDAO = factory.orderDAO();
        tourDAO = factory.tourDAO();
    }

    public void addOrderAndUpdateTour(Order order) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;

        try {
            connection = factory.getConnection();
            orderDAO.addOrder(connection, order);
            int placeQuantity = order.getTour().getPlaceQuantity() - order.getPeopleAmount();
            tourDAO.updatePlaceQuantityRowById(connection, order.getTour().getId(), placeQuantity);
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_ADD_ORDER, ex);
        } finally {
            close(connection);
        }
    }
}

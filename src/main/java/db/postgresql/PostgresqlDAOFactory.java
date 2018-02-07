package db.postgresql;

import constant.ErrorMessages;
import constant.InfoMessages;
import db.DAO.*;
import db.DAOFactory;
import db.postgresql.DAOImpl.*;
import exception.DatabaseException;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PostgresqlDAOFactory extends DAOFactory{

    private static final Logger LOGGER = Logger.getLogger(PostgresqlDAOFactory.class);

    private static PostgresqlDAOFactory instance;
    private DataSource dataSource;

    private PostgresqlDAOFactory() throws DatabaseException {
        try {
            InitialContext context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/travelagency");
        } catch (NamingException e) {
            LOGGER.error(ErrorMessages.ERROR_CAN_NOT_OBTAIN_DATA_SOURCE, e);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_DATA_SOURCE, e);
        }
    }

    public static synchronized PostgresqlDAOFactory getInstance() throws DatabaseException {
        if (instance == null) {
            instance = new PostgresqlDAOFactory();
            LOGGER.info(InfoMessages.INFO_DATABASE_INIT);
        }
        return instance;
    }

    public Connection getConnection() throws DatabaseException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error(ErrorMessages.ERROR_CAN_NOT_OBTAIN_CONNECTION, e);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_CLOSE_CONNECTION, e);
        }
        return connection;
    }

    public UserDAO userDAO() {
        return new UserDAOImpl();
    }

    public CountryDAO countryDAO() {
        return new CountryDAOImpl();
    }

    public ResortDAO resortDAO() {
        return new ResortDAOImpl();
    }

    public HotelDAO hotelDAO() {
        return new HotelDAOImpl();
    }

    public FlightDAO flightDAO() {
        return new FlightDAOImpl();
    }

    public TourDAO tourDAO() {
        return new TourDAOImpl();
    }

    public OrderDAO orderDAO() {
        return new OrderDAOImpl();
    }
}

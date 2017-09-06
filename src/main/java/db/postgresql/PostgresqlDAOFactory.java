package db.postgresql;

import constant.InfoMessages;
import db.DAOFactory;
import db.postgresql.DAO.UserDAO;
import db.postgresql.DAOImpl.UserDAOImpl;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import constant.ErrorMessages;
import exception.DatabaseException;

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

    @Override
    public UserDAO userDAO() {
        return new UserDAOImpl();
    }
}

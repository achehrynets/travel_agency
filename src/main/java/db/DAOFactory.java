package db;

import db.mysql.MySQLDAOFactory;
import db.postgresql.DAO.UserDAO;
import db.postgresql.PostgresqlDAOFactory;
import exception.DatabaseException;

public abstract class DAOFactory {

    public abstract UserDAO userDAO();

    public static DAOFactory getDaoFactory(DBName name) throws DatabaseException {
        switch (name) {
            case POSTGRESQL: {
                return PostgresqlDAOFactory.getInstance();
            }
            case MySQL: {
                return new MySQLDAOFactory();
            }
            default: {
                return PostgresqlDAOFactory.getInstance();
            }
        }
    }

}

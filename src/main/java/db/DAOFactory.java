package db;

import db.DAO.*;
import db.mysql.MySQLDAOFactory;
import db.postgresql.PostgresqlDAOFactory;
import exception.DatabaseException;

public abstract class DAOFactory {

    public abstract UserDAO userDAO();
    public abstract CountryDAO countryDAO();
    public abstract ResortDAO resortDAO();
    public abstract HotelDAO hotelDAO();
    public abstract FlightDAO flightDAO();
    public abstract TourDAO tourDAO();
    public abstract OrderDAO orderDAO();

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

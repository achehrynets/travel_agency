package db.mysql;

import db.DAOFactory;
import db.postgresql.DAO.UserDAO;

public class MySQLDAOFactory extends DAOFactory {

    public UserDAO userDAO() {
        return null;
    }

}

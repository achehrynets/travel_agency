package db.postgresql.DAO;

import db.entity.User;
import exception.DatabaseException;

public interface UserDAO {

    User getUserByLogin(String login) throws DatabaseException;
    void registerUser(User user);

}

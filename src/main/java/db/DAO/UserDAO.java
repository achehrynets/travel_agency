package db.DAO;

import db.entity.User;
import exception.DatabaseException;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers() throws DatabaseException;
    User getUserByLogin(String login) throws DatabaseException;
    void addUser(User user) throws DatabaseException;
    void blockOrUnblockUserByIdAndParam(int id, boolean isBlock) throws DatabaseException;

}

package db.postgresql.DAOImpl;

import constant.ErrorMessages;
import constant.Fields;
import constant.SQL;
import db.entity.User;
import db.DAO.UserDAO;
import db.postgresql.PostgresqlDAOFactory;
import exception.DatabaseException;
import static util.DBUtil.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{

    /**
     *
     * Returns a user with the given login
     *
     * @param login
     * @return User entity
     * @throws DatabaseException
     */
    public User getUserByLogin(String login) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();

        User user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.GET_USER_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = extractUserFromResultSet(rs);
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_USER_BY_LOGIN, ex);
        } finally {
            close(connection, ps, rs);
        }
        return user;
    }

    /**
     *
     * Current method register new user
     *
     * @param user
     * @throws DatabaseException
     */
    public void addUser(User user) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        int k = 0;
        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.ADD_USER);
            ps.setString(++k, user.getLogin());
            ps.setString(++k, user.getPassword());
            ps.setString(++k, user.getEmail());
            ps.setString(++k, user.getSurname());
            ps.setString(++k, user.getMiddleName());
            ps.setString(++k, user.getName());
            ps.setInt(++k, user.getRoleId());
            ps.setBoolean(++k, user.isBlocked());
            ps.setString(++k, user.getPassportId());
            ps.setString(++k, user.getInternationalPassportId());
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_REGISTER_USER, ex);
        } finally {
            close(ps);
            close(connection);
        }
    }

    /**
     *
     * Returns all users
     *
     * @return list of users
     * @throws DatabaseException
     */
    public List<User> getAllUsers() throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();

        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();

        connection = factory.getConnection();
        try {
            st = connection.createStatement();
            rs = st.executeQuery(SQL.GET_ALL_USERS);
            while (rs.next()) {
                users.add(extractUserFromResultSet(rs));
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_ALL_USERS, ex);
        } finally {
            close(connection, st, rs);
        }

        return users;
    }

    /**
     *
     * Current method block or unblock user by id and 'isBlock' parameter
     * if isBlock equals 'true', user will be block
     * if isBlock equals 'false', user will be unblock
     *
     * @param id
     * @param isBlock
     * @throws DatabaseException
     */
    public void blockOrUnblockUserByIdAndParam(int id, boolean isBlock) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.UPDATE_USER_BLOCK_OR_UNBLOCK_BY_ID);
            ps.setBoolean(1, isBlock);
            ps.setInt(2, id);
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            if (isBlock) {
                throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_BLOCK_USER, ex);
            } else {
                throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_UNBLOCK_USER, ex);
            }
        } finally {
            close(ps);
            close(connection);
        }
    }

    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Fields.ENTITY_ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setEmail(rs.getString(Fields.USER_EMAIL));
        user.setSurname(rs.getString(Fields.USER_SURNAME));
        user.setMiddleName(rs.getString(Fields.USER_MIDDLE_NAME));
        user.setName(rs.getString(Fields.NAME));
        user.setPhone(rs.getString(Fields.USER_PHONE));
        user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
        user.setBlocked(rs.getBoolean(Fields.USER_BLOCKED));
        user.setPassportId(rs.getString(Fields.USER_PASSPORT_ID));
        user.setInternationalPassportId(rs.getString(Fields.USER_INTERNATIONAL_PASSPORT_ID));

        return user;
    }

}

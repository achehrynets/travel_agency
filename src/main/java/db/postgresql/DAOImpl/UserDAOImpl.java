package db.postgresql.DAOImpl;

import constant.ErrorMessages;
import constant.Fields;
import constant.SQL;
import db.entity.User;
import db.postgresql.DAO.UserDAO;
import db.postgresql.PostgresqlDAOFactory;
import exception.DatabaseException;
import static util.DBUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    @Override
    public void registerUser(User user) {

    }

    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Fields.ENTITY_ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setEmail(rs.getString(Fields.USER_EMAIL));
        user.setSurname(rs.getString(Fields.USER_SURNAME));
        user.setMiddleName(rs.getString(Fields.USER_MIDDLE_NAME));
        user.setName(rs.getString(Fields.USER_NAME));
        user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
        user.setBlocked(rs.getBoolean(Fields.USER_BLOCKED));
        user.setPassportId(rs.getString(Fields.USER_PASSPORT_ID));
        user.setInternationalPassportId(rs.getString(Fields.USER_INTERNATIONAL_PASSPORT_ID));
        System.out.println(user);
        return user;
    }

}

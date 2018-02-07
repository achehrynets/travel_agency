package db.postgresql.DAOImpl;

import constant.ErrorMessages;
import constant.SQL;
import db.DAO.ResortDAO;
import db.entity.Resort;
import db.postgresql.PostgresqlDAOFactory;
import exception.DatabaseException;
import static util.DBUtil.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResortDAOImpl implements ResortDAO{

    public List<Resort> getAllResorts() throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        List<Resort> resorts = new ArrayList<>();

        try {
            connection = factory.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(SQL.GET_ALL_RESORTS);
            while (rs.next()) {
                resorts.add(extractResortFromResultSet(rs));
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_ALL_RESORTS, ex);
        } finally {
            close(connection, st, rs);
        }

        return resorts;
    }

    public void updateResort(Resort resort) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.UPDATE_RESORT);
            ps.setString(1, resort.getName());
            ps.setString(2, resort.getDescription());
            ps.setLong(3, resort.getId());
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_UPDATE_RESORT, ex);
        } finally {
            close(ps);
            close(connection);
        }
    }

    public void addResort(Resort resort) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.ADD_RESORT);
            ps.setLong(1, resort.getCountryId());
            ps.setString(2, resort.getName());
            ps.setString(3, resort.getDescription());
            ps.setString(4, resort.getImagePath());
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_ADD_RESORT, ex);
        } finally {
            close(ps);
            close(connection);
        }
    }

    public Long addResort(Connection connection, Resort resort) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long id = -1L;
        try {
            ps = connection.prepareStatement(SQL.ADD_RESORT, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setLong(1, resort.getCountryId());
            ps.setString(2, resort.getName());
            ps.setString(3, resort.getDescription());
            ps.setString(4, resort.getImagePath());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } finally {
            close(rs);
            close(ps);
        }
        return id;
    }

    public Resort getResortByName(String name) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Resort resort = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.GET_RESORT_BY_NAME);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                resort = extractResortFromResultSet(rs);
            }

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_RESORT_BY_NAME, ex);
        }

        return resort;
    }

    public Resort getResortById(long id) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Resort resort = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.GET_RESORT_BY_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                resort = extractResortFromResultSet(rs);
            }

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_RESORT_BY_ID, ex);
        }

        return resort;
    }

    public void deleteResortById(long id) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.DELETE_RESORT_BY_ID);
            ps.setLong(1, id);
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_DELETE_RESORT, ex);
        } finally {
            close(ps);
            close(connection);
        }
    }

    private Resort extractResortFromResultSet(ResultSet rs) throws SQLException {
        Resort resort = new Resort();
        resort.setId(rs.getLong("id"));
        resort.setName(rs.getString("name"));
        resort.setDescription(rs.getString("description"));
        resort.setImagePath(rs.getString("image_path"));

        return resort;
    }
}

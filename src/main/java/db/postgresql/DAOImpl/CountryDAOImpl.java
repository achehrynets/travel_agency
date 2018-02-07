package db.postgresql.DAOImpl;

import constant.ErrorMessages;
import constant.Fields;
import constant.SQL;
import db.DAO.CountryDAO;
import db.entity.Country;
import db.postgresql.PostgresqlDAOFactory;
import exception.DatabaseException;
import static util.DBUtil.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAOImpl implements CountryDAO {

    public List<Country> getAllCountries() throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        List<Country> countries = new ArrayList<>();

        connection = factory.getConnection();
        try {
            st = connection.createStatement();
            rs = st.executeQuery(SQL.GET_ALL_COUNTRIES);
            while (rs.next()) {
                countries.add(extractCountryFromResultSet(rs));
            }
            connection.commit();
        } catch (SQLException ex) {
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_ALL_COUNTRIES, ex);
        } finally {
            close(connection, st, rs);
        }

        return countries;
    }

    public void deleteCountryById(long id) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.DELETE_COUNTRY_BY_ID);
            ps.setLong(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_DELETE_COUNTRY, ex);
        } finally {
            close(ps);
            close(connection);
        }
    }

    public Country getCountryByName(String name) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Country country = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.GET_COUNTRY_BY_NAME);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                country = extractCountryFromResultSet(rs);
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_COUNTRY_BY_NAME + name, ex);
        } finally {
            close(connection, ps, rs);
        }

        return country;
    }

    public Country getCountryById(long id) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Country country = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.GET_COUNTRY_BY_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                country = extractCountryFromResultSet(rs);
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_COUNTRY_BY_ID, ex);
        } finally {
            close(connection, ps, rs);
        }

        return country;
    }

    public void updateCountryByID(Country country) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
        connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.UPDATE_COUNTRY_BY_ID);
            ps.setString(1, country.getName());
            ps.setBoolean(2, country.isVisa());
            ps.setLong(3, country.getId());
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_UPDATE_COUNTRY + " name: " + country.getName(), ex);
        } finally {
            close(ps);
            close(connection);
        }
    }

    public void addCountry(Country country) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.ADD_COUNTRY);
            ps.setString(1, country.getName());
            ps.setBoolean(2, country.isVisa());
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_ADD_COUNTRY, ex);
        } finally {
            close(ps);
            close(connection);
        }
    }

    public Long addCountry(Connection connection, Country country) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long id = -1L;
        try {
            ps = connection.prepareStatement(SQL.ADD_COUNTRY, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, country.getName());
            ps.setBoolean(2, country.isVisa());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if(rs.next()) {
                id = rs.getLong(1);
            }
        } finally {
            close(rs);
            close(ps);
        }
        return id;
    }

    private Country extractCountryFromResultSet(ResultSet rs) throws SQLException {
        Country country = new Country();
        country.setId(rs.getInt(Fields.ENTITY_ID));
        country.setName(rs.getString(Fields.NAME));
        country.setVisa(rs.getBoolean(Fields.COUNTRY_VISA));

        return country;
    }


}

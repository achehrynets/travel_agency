package db.postgresql.DAOImpl;

import constant.ErrorMessages;
import constant.SQL;
import db.DAO.HotelDAO;
import db.entity.Country;
import db.entity.Hotel;
import db.entity.Resort;
import db.postgresql.PostgresqlDAOFactory;
import exception.DatabaseException;
import static util.DBUtil.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAOImpl implements HotelDAO {

    public List<Hotel> getAllHotels() throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        List<Hotel> hotels = new ArrayList<>();
        try {
            connection = factory.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(SQL.GET_ALL_HOTELS);
            while (rs.next()) {
                hotels.add(extractHotelFromResultSet(rs));
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_ALL_HOTELS, ex);
        } finally {
            close(connection, st, rs);
        }

        return hotels;
    }

    public Hotel getHotelByName(String name) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Hotel hotel = null;
        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.GET_HOTEL_BY_NAME);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                hotel = extractHotelFromResultSet(rs);
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_HOTEL_BY_NAME, ex);
        } finally {
            close(connection, ps, rs);
        }

        return hotel;
    }

    public void deleteHotelById(long id) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.DELETE_HOTEL_BY_ID);
            ps.setLong(1, id);
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_DELETE_HOTEL, ex);
        } finally {
            close(ps);
            close(connection);
        }

    }

    public void updateHotel(Hotel hotel) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.UPDATE_HOTEL);
            ps.setString(1, hotel.getName());
            ps.setInt(2, hotel.getStars());
            ps.setFloat(3, hotel.getPrice());
            ps.setString(4, hotel.getDescription());
            ps.setLong(5, hotel.getId());
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_UPDATE_HOTEL, ex);
        } finally {
            close(ps);
            close(connection);
        }
    }

    public void addHotel(Hotel hotel) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.ADD_HOTEL);
            ps.setString(1, hotel.getName());
            ps.setLong(2, hotel.getResort().getId());
            ps.setLong(3, hotel.getCountry().getId());
            ps.setInt(4, hotel.getStars());
            ps.setString(5, hotel.getDescription());
            ps.setFloat(6, hotel.getPrice());
            ps.setString(7, hotel.getImagePath());
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_ADD_HOTEL, ex);
        }
    }

    public Long addHotel(Connection connection, Hotel hotel) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long id = -1L;
        try {
            ps = connection.prepareStatement(SQL.ADD_HOTEL, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, hotel.getName());
            ps.setLong(2, hotel.getResort().getId());
            ps.setLong(3, hotel.getCountry().getId());
            ps.setInt(4, hotel.getStars());
            ps.setString(5, hotel.getDescription());
            ps.setFloat(6, hotel.getPrice());
            ps.setString(7, hotel.getImagePath());
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

    private Hotel extractHotelFromResultSet(ResultSet rs) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(rs.getInt("id"));
        hotel.setName(rs.getString("name"));

        Resort resort = new Resort();
        resort.setId(rs.getLong("resort_id"));
        resort.setName(rs.getString("resort_name"));
        hotel.setResort(resort);

        Country country = new Country();
        country.setId(rs.getLong("country_id"));
        country.setName(rs.getString("country_name"));
        hotel.setCountry(country);

        hotel.setStars(rs.getInt("stars"));
        hotel.setDescription(rs.getString("description"));
        hotel.setPrice(rs.getFloat("price"));
        hotel.setImagePath(rs.getString("image_path"));

        return hotel;
    }
}

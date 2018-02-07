package db.postgresql.DAOImpl;

import constant.ErrorMessages;
import constant.SQL;
import db.DAO.TourDAO;
import db.entity.*;
import db.postgresql.PostgresqlDAOFactory;
import exception.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.DBUtil.close;
import static util.DBUtil.rollback;

public class TourDAOImpl implements TourDAO {

    public void addTour(Connection connection, Tour tour) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL.ADD_TOUR);
            ps.setString(1, tour.getName());
            ps.setBoolean(2, tour.isHotTour());
            ps.setInt(3, tour.getPlaceQuantity());
            ps.setInt(4, tour.getAmountOfDays());
            ps.setDate(5, new Date(tour.getTourDate().getTime()));
            ps.setFloat(6, tour.getTotalPrice());
            ps.setInt(7, tour.getTourTypeId());
            ps.setLong(8, tour.getFlight().getId());
            ps.setLong(9, tour.getHotel().getId());
            ps.setLong(10, tour.getResort().getId());
            ps.setLong(11, tour.getCountry().getId());
            ps.executeUpdate();
        } finally {
            close(ps);
        }
    }

    public List<Tour> getAllTours() throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        List<Tour> tours = new ArrayList<>();
        try {
            connection = factory.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(SQL.GET_ALL_TOURS);
            while (rs.next()) {
                if (checkTourDate(rs)) {
                    tours.add(extractTourFromResultSet(rs));
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_ALL_TOURS, ex);
        } finally {
            close(connection, st, rs);
        }

        return tours;
    }


    public void setHotTourById(long id) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.UPDATE_HOT_TOUR_ROW_BY_ID);
            ps.setLong(1, id);
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_UPDATE_HOT_TOUR_ROW, ex);
        } finally {
            close(ps);
            close(connection);
        }
    }

    public Tour getTourById(Long id) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Tour tour = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.GET_TOUR_BY_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                tour = extractTourFromResultSet(rs);
            }

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_TOUR_BY_ID, ex);
        } finally {
            close(connection, ps, rs);
        }

        return tour;
    }

    public void deleteTourById(Long id) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.DELETE_TOUR_BY_ID);
            ps.setLong(1, id);
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_DELETE_TOUR, ex);
        } finally {
            close(ps);
            close(connection);
        }
    }

    public void updateTour(Tour tour) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.UPDATE_TOUR);
            ps.setString(1, tour.getName());
            ps.setLong(2, tour.getTourTypeId());
            ps.setDate(3, new Date(tour.getTourDate().getTime()));
            ps.setFloat(4, tour.getTotalPrice());
            ps.setInt(5, tour.getAmountOfDays());
            ps.setInt(6, tour.getPlaceQuantity());
            ps.setLong(7, tour.getId());
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_UPDATE_TOUR, ex);
        } finally {
            close(ps);
            close(connection);
        }
    }

    public void updatePlaceQuantityRowById(Connection connection, long id, int placeQuantity) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL.UPDATE_PLACE_QUANTITY_ROW_BY_ID);
            ps.setInt(1, placeQuantity);
            ps.setLong(2, id);

            ps.executeUpdate();
        } finally {
            close(ps);
        }

    }

    public List<Tour> searchTours(int tourTypeId, float price, int peopleAmount, int stars) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Tour> tours = new ArrayList<>();

        try {
            connection = factory.getConnection();
            ps = connection.prepareStatement(SQL.SEARCH_TOUR);
            ps.setInt(1, tourTypeId);
            ps.setFloat(2, price);
            ps.setInt(3, peopleAmount);
            ps.setInt(4, stars);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (checkTourDate(rs)) {
                    tours.add(extractTourFromResultSet(rs));
                }
            }

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_OBTAIN_SEARCHED_TOURS, ex);
        } finally {
            close(connection, ps, rs);
        }

        return tours;
    }

    private boolean checkTourDate(ResultSet rs) throws SQLException {
        long currentTime = new java.util.Date().getTime() - 86400000;
        long tourDate = rs.getDate("tour_date").getTime();
        if (tourDate > currentTime) {
            return true;
        }
        return false;
    }

    private Tour extractTourFromResultSet(ResultSet rs) throws SQLException {

        Tour tour = new Tour();
        tour.setId(rs.getLong("id"));
        tour.setName(rs.getString("name"));
        tour.setHotTour(rs.getBoolean("hot_tour"));
        tour.setAmountOfDays(rs.getInt("amount_of_days"));
        tour.setPlaceQuantity(rs.getInt("place_quantity"));
        tour.setTourDate(rs.getDate("tour_date"));
        tour.setTotalPrice(rs.getFloat("total_price"));
        tour.setTourTypeId(rs.getInt("tour_type_id"));

        Country country = new Country();
        country.setName(rs.getString("country_name"));
        country.setVisa(rs.getBoolean("country_visa"));
        tour.setCountry(country);

        Resort resort = new Resort();
        resort.setName(rs.getString("resort_name"));
        resort.setDescription(rs.getString("resort_description"));
        resort.setImagePath(rs.getString("resort_image_path"));
        tour.setResort(resort);

        Hotel hotel = new Hotel();
        hotel.setName(rs.getString("hotels_name"));
        hotel.setStars(rs.getInt("hotels_stars"));
        hotel.setDescription(rs.getString("hotels_description"));
        tour.setHotel(hotel);

        Flight flight = new Flight();
        flight.setDeparturePoint(rs.getString("flight_departure_point"));
        flight.setDepartureDate(rs.getTimestamp("flight_departure_date"));
        flight.setTravelTime(rs.getTime("travel_time"));
        flight.setArrivalPoint(rs.getString("flight_arrival_point"));
        flight.setArrivalDate(rs.getTimestamp("flight_arrival_date"));
        flight.setTransportTypeId(rs.getInt("transport_type_id"));
        tour.setFlight(flight);

        return tour;
    }

}

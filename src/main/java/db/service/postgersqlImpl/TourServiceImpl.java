package db.service.postgersqlImpl;

import constant.AppConst;
import constant.ErrorMessages;
import db.DAO.*;
import db.DAOFactory;
import db.entity.*;
import db.postgresql.PostgresqlDAOFactory;
import db.service.TourService;
import exception.DatabaseException;
import static util.DBUtil.*;

import java.sql.Connection;
import java.sql.SQLException;

public class TourServiceImpl implements TourService{

    private CountryDAO countryDAO;
    private ResortDAO resortDAO;
    private HotelDAO hotelDAO;
    private FlightDAO flightDAO;
    private TourDAO tourDAO;

    public TourServiceImpl() throws DatabaseException {
        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        countryDAO = factory.countryDAO();
        resortDAO = factory.resortDAO();
        hotelDAO = factory.hotelDAO();
        flightDAO = factory.flightDAO();
        tourDAO = factory.tourDAO();
    }

    public void addTour(Tour tour) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;

        connection = factory.getConnection();
        try {
            Long countryId = countryDAO.addCountry(connection, tour.getCountry());
            Country country = new Country();
            country.setId(countryId);

            tour.getResort().setCountryId(country.getId());
            Long resortId = resortDAO.addResort(connection, tour.getResort());
            Resort resort = new Resort();
            resort.setId(resortId);

            tour.getHotel().setCountry(country);
            tour.getHotel().setResort(resort);
            Long hotelId = hotelDAO.addHotel(connection, tour.getHotel());
            Hotel hotel = new Hotel();
            hotel.setId(hotelId);

            Long flightId = flightDAO.addFlight(connection, tour.getFlight());
            Flight flight = new Flight();
            flight.setId(flightId);

            float tourPrice = tour.getTotalPrice() + tour.getHotel().getPrice() + tour.getFlight().getPrice();
            tour.setTotalPrice(tourPrice);
            tour.setCountry(country);
            tour.setResort(resort);
            tour.setHotel(hotel);
            tour.setFlight(flight);
            tourDAO.addTour(connection, tour);

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_ADD_TOUR, ex);
        } finally {
            close(connection);
        }
    }

    public void addTourWhenCountryExist(Tour tour) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;

        connection = factory.getConnection();
        try {
            tour.getResort().setCountryId(tour.getCountry().getId());
            long resortId = resortDAO.addResort(connection, tour.getResort());
            Resort resort = new Resort();
            resort.setId(resortId);

            tour.getHotel().setCountry(tour.getCountry());
            tour.getHotel().setResort(resort);
            long hotelId = hotelDAO.addHotel(connection, tour.getHotel());
            Hotel hotel = new Hotel();
            hotel.setId(hotelId);

            long flightId = flightDAO.addFlight(connection, tour.getFlight());
            Flight flight = new Flight();
            flight.setId(flightId);

            float tourPrice = tour.getTotalPrice() + tour.getHotel().getPrice() + tour.getFlight().getPrice();
            tour.setTotalPrice(tourPrice);
            tour.setResort(resort);
            tour.setHotel(hotel);
            tour.setFlight(flight);
            tourDAO.addTour(connection, tour);

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_ADD_TOUR, ex);
        } finally {
            close(connection);
        }
    }

    public void addTourWhenCountryAndResortExist(Tour tour) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;

        connection = factory.getConnection();
        try {

            tour.getHotel().setCountry(tour.getCountry());
            tour.getHotel().setResort(tour.getResort());
            long hotelId = hotelDAO.addHotel(connection, tour.getHotel());
            Hotel hotel = new Hotel();
            hotel.setId(hotelId);

            long flightId = flightDAO.addFlight(connection, tour.getFlight());
            Flight flight = new Flight();
            flight.setId(flightId);

            float tourPrice = tour.getTotalPrice() + tour.getHotel().getPrice() + tour.getFlight().getPrice();
            tour.setTotalPrice(tourPrice);
            tour.setHotel(hotel);
            tour.setFlight(flight);
            tourDAO.addTour(connection, tour);

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_ADD_TOUR, ex);
        } finally {
            close(connection);
        }
    }

    public void addTourWhenCountryAndResortAndHotelExist(Tour tour) throws DatabaseException {
        PostgresqlDAOFactory factory = PostgresqlDAOFactory.getInstance();
        Connection connection = null;

        connection = factory.getConnection();
        try {
            long flightId = flightDAO.addFlight(connection, tour.getFlight());
            Flight flight = new Flight();
            flight.setId(flightId);

            float tourPrice = tour.getTotalPrice() + tour.getHotel().getPrice() + tour.getFlight().getPrice();
            tour.setTotalPrice(tourPrice);
            tour.setFlight(flight);
            tourDAO.addTour(connection, tour);

            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            throw new DatabaseException(ErrorMessages.ERROR_CAN_NOT_ADD_TOUR, ex);
        } finally {
            close(connection);
        }
    }
}

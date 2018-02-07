package db.DAO;

import db.entity.Flight;

import java.sql.Connection;
import java.sql.SQLException;

public interface FlightDAO {

    Long addFlight(Connection connection, Flight flight) throws SQLException;

}

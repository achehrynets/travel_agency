package db.postgresql.DAOImpl;

import constant.SQL;
import db.DAO.FlightDAO;
import db.entity.Flight;
import static util.DBUtil.*;
import exception.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class FlightDAOImpl implements FlightDAO {

    public Long addFlight(Connection connection, Flight flight) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long id = -1L;
        try {
            ps = connection.prepareStatement(SQL.ADD_FLIGHT, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, flight.getTransportTypeId());
            ps.setString(2, flight.getDeparturePoint());
            ps.setDate(3, new Date(flight.getDepartureDate().getTime()));
            ps.setString(4, flight.getArrivalPoint());
            ps.setDate(5, new Date(flight.getArrivalDate().getTime()));
            ps.setFloat(6, flight.getPrice());
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
}

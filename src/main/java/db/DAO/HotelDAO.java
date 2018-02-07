package db.DAO;

import db.entity.Hotel;
import exception.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface HotelDAO {

    List<Hotel> getAllHotels() throws DatabaseException;
    void deleteHotelById(long id) throws DatabaseException;
    void addHotel(Hotel hotel) throws DatabaseException;
    Long addHotel(Connection connection, Hotel hotel) throws SQLException;
    void updateHotel(Hotel hotel) throws DatabaseException;
    Hotel getHotelByName(String name) throws DatabaseException;

}

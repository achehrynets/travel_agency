package db.DAO;

import db.entity.Country;
import exception.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CountryDAO {

    List<Country> getAllCountries() throws DatabaseException;

    void deleteCountryById(long id) throws DatabaseException;

    Country getCountryById(long id) throws DatabaseException;

    Country getCountryByName(String name) throws DatabaseException;

    void updateCountryByID(Country country) throws DatabaseException;

    void addCountry(Country country) throws DatabaseException;

    Long addCountry(Connection connection, Country country) throws SQLException;

}

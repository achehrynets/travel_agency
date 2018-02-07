package db.DAO;

import db.entity.Resort;
import exception.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ResortDAO {

    List<Resort> getAllResorts() throws DatabaseException;

    void updateResort(Resort resort) throws DatabaseException;

    void addResort(Resort resort) throws DatabaseException;

    Long addResort(Connection connection, Resort resort) throws SQLException;

    void deleteResortById(long id) throws DatabaseException;

    Resort getResortById(long id) throws DatabaseException;

    Resort getResortByName(String name) throws DatabaseException;

}

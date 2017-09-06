package util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import constant.ErrorMessages;

public class DBUtil {

    private static final Logger LOGGER = Logger.getLogger(DBUtil.class);

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(ErrorMessages.ERROR_CAN_NOT_CLOSE_CONNECTION, e);
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error(ErrorMessages.ERROR_CAN_NOT_CLOSE_STATEMENT, e);
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error(ErrorMessages.ERROR_CAN_NOT_CLOSE_RESULT_SET, e);
            }
        }
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        close(resultSet);
        close(statement);
        close(connection);
    }

    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error(ErrorMessages.ERROR_CAN_NOT_ROLLBACK_TRANSACTION, e);
            }
        }
    }

}

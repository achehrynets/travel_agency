package exception;

public class DatabaseException extends ApplicationException {

    public DatabaseException() {
        super();
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}

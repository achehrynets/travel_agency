package exception;

import constant.Path;

public class DatabaseException extends ApplicationException {

    public DatabaseException() {
        super();
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
        setForward(Path.PAGE_ERROR);
    }

}

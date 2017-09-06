package exception;

public class ApplicationException extends Exception{

    private String forward;

    public ApplicationException() {}

    public ApplicationException(String message) {
        super(message);
    }
    public ApplicationException(String message, String forward) {
        super(message);
        this.forward = forward;
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getForward() {
        return forward;
    }
}

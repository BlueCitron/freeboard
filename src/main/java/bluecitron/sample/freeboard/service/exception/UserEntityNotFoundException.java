package bluecitron.sample.freeboard.service.exception;

public class UserEntityNotFoundException extends RuntimeException {

    public UserEntityNotFoundException() {
        super();
    }

    public UserEntityNotFoundException(String message) {
        super(message);
    }

    public UserEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserEntityNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UserEntityNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package bluecitron.sample.freeboard.domain.exception;

public class UserPasswordNotMatchedException extends RuntimeException {

    public UserPasswordNotMatchedException() {
        super();
    }

    public UserPasswordNotMatchedException(String message) {
        super(message);
    }

    public UserPasswordNotMatchedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserPasswordNotMatchedException(Throwable cause) {
        super(cause);
    }

    protected UserPasswordNotMatchedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

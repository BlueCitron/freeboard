package bluecitron.sample.freeboard.service.exception;

public class InvalidRequestException extends SystemException {

    String message;

    public InvalidRequestException(String message) {
        super(message);
        this.message = message;
    }
}

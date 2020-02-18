package bluecitron.sample.freeboard.service.exception;

public class UnauthorizedOperationException extends SystemException {

    public UnauthorizedOperationException() {
        super("해당 작업을 실행하기 위한 권한이 부족합니다.");
    }
}

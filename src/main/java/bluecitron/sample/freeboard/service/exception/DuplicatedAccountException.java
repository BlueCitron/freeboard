package bluecitron.sample.freeboard.service.exception;

public class DuplicatedAccountException extends SystemException {

    private String account;

    public DuplicatedAccountException(String account) {
        super(String.format("이미 존재하는 계정입니다. (account: %s)", account));
        this.account = account;
    }
}

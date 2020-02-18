package bluecitron.sample.freeboard.service.exception;

public class MemberEntityNotFoundException extends SystemException {

    private String account;

    public MemberEntityNotFoundException(String account) {
        super(String.format("Post 엔티티를 찾을 수 없습니다. (account: %s)", account));
        this.account = account;
    }
}

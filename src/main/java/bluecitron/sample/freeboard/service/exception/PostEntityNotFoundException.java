package bluecitron.sample.freeboard.service.exception;

public class PostEntityNotFoundException extends SystemException {

    private Long id;

    public PostEntityNotFoundException(Long id) {
        super(String.format("Post 엔티티를 찾을 수 없습니다. (post_id: %d)", id));
        this.id = id;
    }
}

package bluecitron.sample.freeboard.service.exception;

public class CategoryEntityNotFoundException extends SystemException {

    private Long id;

    public CategoryEntityNotFoundException(Long id) {
        super(String.format("Category 엔티티를 찾을 수 없습니다. (category_id: %s)", id));
        this.id = id;
    }
}

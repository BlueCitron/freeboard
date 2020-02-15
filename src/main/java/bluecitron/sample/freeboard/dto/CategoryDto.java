package bluecitron.sample.freeboard.dto;

import bluecitron.sample.freeboard.domain.Category;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {
    private Long id;
    private String title;
    private Integer hasPostCount;
    private Category parent;
    private List<Category> children = new ArrayList<>();
}

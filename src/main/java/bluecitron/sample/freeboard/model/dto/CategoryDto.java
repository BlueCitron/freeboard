package bluecitron.sample.freeboard.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {
    private Long id;
    private String title;
    private Integer hasPostCount;
    private CategoryDto parent;
    private List<CategoryDto> children = new ArrayList<>();
}

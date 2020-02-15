package bluecitron.sample.freeboard.dto;

import lombok.Data;

@Data
public class SearchDto {
    private Long categoryId;
    private Long postId;
    private String postTile;
    private String postWriter;
    private Integer page;
    private Integer perPage;
}

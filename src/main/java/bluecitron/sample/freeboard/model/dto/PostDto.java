package bluecitron.sample.freeboard.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private Integer viewCount;
    private CategoryDto category;
    private MemberDto writer;
    private Integer postLikes;
    private Integer postDislikes;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public PostDto(Long id, String title, String content, Integer viewCount, CategoryDto category, MemberDto writer, Integer postLikes, Integer postDislikes, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.category = category;
        this.writer = writer;
        this.postLikes = postLikes;
        this.postDislikes = postDislikes;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}

package bluecitron.sample.freeboard.dto;

import bluecitron.sample.freeboard.domain.Category;
import bluecitron.sample.freeboard.domain.Member;
import bluecitron.sample.freeboard.domain.PostDislike;
import bluecitron.sample.freeboard.domain.PostLike;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private Integer viewCount;
    private Long category;
    private Long writer;
    private Integer postLikes;
    private Integer postDislikes;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public PostDto(Long id, String title, String content, Integer viewCount
            , Long category, Long writer, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
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

    @Builder
    public PostDto(Long id, String title, String content, Integer viewCount
            , Long category, Long writer, Integer postLikes, Integer postDislikes
            , LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
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

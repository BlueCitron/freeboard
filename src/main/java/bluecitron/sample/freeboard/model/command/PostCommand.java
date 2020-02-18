package bluecitron.sample.freeboard.model.command;

import lombok.Data;

@Data
public class PostCommand {
    private Long postId;
    private String title;
    private String content;
    private Long categoryId;
    private Long writerId;

    private Integer page = 0;
    private Integer offset = 20;
}

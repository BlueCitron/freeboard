package bluecitron.sample.freeboard.domain;

import bluecitron.sample.freeboard.domain.exception.InvalidOperationException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AttributeOverride(name = "id", column = @Column(name = "post_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Post extends BaseEntity {

    @Column(length = 500)
    private String title;

    @Lob
    private String content;

    @Column(columnDefinition = "int default 0")
    private Integer viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostLike> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostDislike> postDislikes = new ArrayList<>();

    @Builder
    protected Post(String title, String content, Integer viewCount, Category category, Member writer, List<PostLike> postLikes, List<PostDislike> postDislikes) {
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.category = category;
        this.writer = writer;
        this.postLikes = postLikes;
        this.postDislikes = postDislikes;
    }

    public static Post create(Category category, String title, String content, Member writer) {
        Post post = new Post().builder()
                .category(category)
                .title(title)
                .content(content)
                .viewCount(0)
                .writer(writer)
                .postLikes(new ArrayList<PostLike>())
                .postDislikes(new ArrayList<PostDislike>())
                .build();

        writer.getPosts().add(post);
        category.increasePostCount();
        return post;
    }

    public PostLike like(Member member) throws InvalidOperationException{
        boolean isExist = this.postLikes.stream().anyMatch(postLike -> postLike.getMember() == member);
        if (isExist)
            throw new InvalidOperationException("이미 좋아요 표시한 게시글입니다.");

        PostLike postLike = new PostLike(this, member);
        this.postLikes.add(postLike);
        return postLike;
    }

    public boolean likeCancel(Member member) throws InvalidOperationException {
        boolean isDeleted = this.postLikes.removeIf(postLike -> postLike.getMember() == member);
        if (!isDeleted)
            throw new InvalidOperationException("좋아요 표시한적이 없는 게시글입니다.");

        return isDeleted;
    }

    public PostDislike dislike(Member member) throws InvalidOperationException {
        boolean isExist = this.postDislikes.stream().anyMatch(postDislike -> postDislike.getMember() == member);
        if (isExist)
            throw new InvalidOperationException("이미 싫어요 표시한 게시글입니다.");

        PostDislike postDislike = new PostDislike(this, member);
        this.postDislikes.add(postDislike);
        return postDislike;
    }

    public boolean dislikeCancel(Member member) throws InvalidOperationException {
        boolean isDeleted = this.postDislikes.removeIf(like -> like.getMember() == member);
        if (!isDeleted)
            throw new InvalidOperationException("싫어요 표시한 적이 없는 게시글입니다.");

        return isDeleted;
    }

    public Integer getLikeCount() {
        return this.postLikes.size();
    }

    public Integer getDislikeCount() {
        return this.postDislikes.size();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package bluecitron.sample.freeboard.domain;

import bluecitron.sample.freeboard.domain.exception.InvalidOperationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(length = 500)
    private String title;

    private String content;

    private Integer viewCount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostLike> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostDislike> postDislikes = new ArrayList<>();

    public Post(Category category, String title, String content, Member writer) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.writer = writer;
         this.viewCount = 0;

        category.increasePostCount();
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
}

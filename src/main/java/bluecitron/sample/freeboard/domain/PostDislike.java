package bluecitron.sample.freeboard.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AttributeOverride(name = "id", column = @Column(name = "dislike_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class PostDislike extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public PostDislike(Post post, Member member) {
        this.post = post;
        this.member = member;
    }
}

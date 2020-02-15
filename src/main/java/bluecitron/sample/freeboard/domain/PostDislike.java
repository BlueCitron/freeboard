package bluecitron.sample.freeboard.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class PostDislike extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "dislike_id")
    private Long id;

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

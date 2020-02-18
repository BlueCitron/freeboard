package bluecitron.sample.freeboard.domain;

import bluecitron.sample.freeboard.domain.exception.UserPasswordNotMatchedException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AttributeOverride(name = "id", column = @Column(name = "member_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseEntity {

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "account", nullable = false, unique = true)
    private String account;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private MemberGrade grade;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;

    @Builder
    public Member(String nickname, String account, String password, String email, MemberGrade grade, List<Post> posts) {
        this.nickname = nickname;
        this.account = account;
        this.password = password;
        this.email = email;
        this.grade = grade;
        this.posts = posts;
    }

    public static Member create(String nickname, String account, String password, String email) {
        return new Member().builder()
                .nickname(nickname)
                .account(account)
                .password(password)
                .email(email)
                .grade(MemberGrade.ROLE_NORMAL)
                .posts(new ArrayList<Post>())
                .build();
    }

    public Member verifyPassword(String password) {
        if (Objects.equals(getPassword(), password)) {
            return this;
        }
        throw new UserPasswordNotMatchedException();
    }

    public void setGrade(MemberGrade grade) {
        this.grade = grade;
    }
}

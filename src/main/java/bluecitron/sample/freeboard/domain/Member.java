package bluecitron.sample.freeboard.domain;

import bluecitron.sample.freeboard.domain.exception.UserPasswordNotMatchedException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String nickname;
    private String account;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private MemberGrade grade;

    public Member(String nickname, String account, String password, String email) {
        this.nickname = nickname;
        this.account = account;
        this.password = password;
        this.email = email;
        this.grade = MemberGrade.ROLE_NORMAL;
    }

    public Member verifyPassword(String password) {
        if (Objects.equals(getPassword(), password)) {
            return this;
        }
        throw new UserPasswordNotMatchedException();
    }
}

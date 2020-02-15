package bluecitron.sample.freeboard.dto;

import bluecitron.sample.freeboard.domain.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberDto {
    private Long id;
    private String nickname;
    private String account;
    private String password;
    private String email;

    public Member toEntity() {
        return new Member(nickname, account, password, email);
    }

    @Builder
    public MemberDto(String nickname, String account, String password, String email) {
        this.nickname = nickname;
        this.account = account;
        this.password = password;
        this.email = email;
    }
}

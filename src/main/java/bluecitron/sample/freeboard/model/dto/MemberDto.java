package bluecitron.sample.freeboard.model.dto;

import lombok.Data;

@Data
public class MemberDto {
    private Long id;
    private String nickname;
    private String account;
    private String password;
    private String email;
}

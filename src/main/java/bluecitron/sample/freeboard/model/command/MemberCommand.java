package bluecitron.sample.freeboard.model.command;

import lombok.Data;

@Data
public class MemberCommand {
    private String nickname;
    private String account;
    private String password;
    private String email;
}

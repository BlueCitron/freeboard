package bluecitron.sample.freeboard.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Controller
public class AuthorizeController {

    @GetMapping("/login")
    public String loginView() {
        return "/common/login";
    }

    @GetMapping("/logout")
    public String logoutView() {
        return "/";
    }
}

package bluecitron.sample.freeboard.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Slf4j
@RequestMapping("/admin")
@Controller
public class AdminController {

    @GetMapping("/")
    public String adminView(Principal principal) {
        log.info(principal.toString());
        principal.getName();
        return "/admin/index";
    }
}

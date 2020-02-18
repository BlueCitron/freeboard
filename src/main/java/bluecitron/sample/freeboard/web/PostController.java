package bluecitron.sample.freeboard.web;

import bluecitron.sample.freeboard.model.command.PostCommand;
import bluecitron.sample.freeboard.model.dto.PostDto;
import bluecitron.sample.freeboard.repository.MemberRepository;
import bluecitron.sample.freeboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@RequestMapping("/post")
@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String postListView(Model model, PostCommand postCommand) {
        List<PostDto> posts = postService.getPosts(postCommand);
        model.addAttribute("posts", posts);
        return "/post/index";
    }

    @GetMapping("/{id}")
    public String postView(Model model, @PathVariable Long id) {
        PostDto post = postService.getPost(id);
        model.addAttribute("post", post);
        return "/post/index";
    }

    @PostMapping("/insert")
    public String insertPost(Principal principal, PostCommand postCommand) {
        String account = principal.getName();
        postService.insert(account, postCommand);
        return "redirect:/post/";
    }

    @PostMapping("/update/{id}")
    public String updatePost(Principal principal, PostCommand postCommand) {
        String account = principal.getName();
        postService.update(account, postCommand);
        return "redirect:/post/";
    }

    @PostMapping("/delete/{id}")
    public String deletePost(Principal principal, @PathVariable Long id) {
        String account = principal.getName();
        postService.delete(account, id);
        return "redirect:/post/";
    }
}

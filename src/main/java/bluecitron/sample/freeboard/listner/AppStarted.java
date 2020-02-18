package bluecitron.sample.freeboard.listner;

import bluecitron.sample.freeboard.repository.CategoryRepository;
import bluecitron.sample.freeboard.repository.MemberRepository;
import bluecitron.sample.freeboard.repository.PostRepository;
import bluecitron.sample.freeboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class AppStarted implements ApplicationListener<ApplicationStartedEvent> {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    private final MemberService memberService;

    @Transactional
    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {

//        MemberDto memberDto = MemberDto.builder()
//                .nickname("BlueCitron")
//                .account("admin")
//                .password("admin")
//                .email("admin@admin.com")
//                .build();
//        memberService.join(memberDto);
//        Member admin = memberRepository.findByAccount("admin").get();
//        admin.setGrade(MemberGrade.ROLE_ADMIN);
//
//        Category category = new Category("sample_category");
//        categoryRepository.save(category);
//
//        Post post = new Post(category, "sample_post", "content kk", admin);
//        postRepository.save(post);
    }
}

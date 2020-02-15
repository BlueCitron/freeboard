package bluecitron.sample.freeboard.listner;

import bluecitron.sample.freeboard.domain.Member;
import bluecitron.sample.freeboard.dto.MemberDto;
import bluecitron.sample.freeboard.repository.MemberRepository;
import bluecitron.sample.freeboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class AppStarted implements ApplicationListener<ApplicationStartedEvent> {

    private final MemberService memberService;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {

        MemberDto memberDto = MemberDto.builder()
                .nickname("BlueCitron")
                .account("admin")
                .password("admin")
                .email("admin@admin.com")
                .build();
        memberService.join(memberDto);
    }
}

package bluecitron.sample.freeboard.service;

import bluecitron.sample.freeboard.domain.Member;
import bluecitron.sample.freeboard.model.command.MemberCommand;
import bluecitron.sample.freeboard.repository.MemberRepository;
import bluecitron.sample.freeboard.service.exception.DuplicatedAccountException;
import bluecitron.sample.freeboard.service.exception.MemberEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Long join(MemberCommand memberCommand) {
        String account = memberCommand.getAccount();
        Optional<Member> byAccount = memberRepository.findByAccount(account);

        if (byAccount.isPresent()) {
            throw new DuplicatedAccountException(account);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member member = Member.create(memberCommand.getNickname()
                , memberCommand.getAccount()
                , memberCommand.getPassword()
                , memberCommand.getEmail());

        return memberRepository.save(member).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Member member = memberRepository.findByAccount(account)
                .orElseThrow(() -> new MemberEntityNotFoundException(account));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getGrade().toString()));
        return new User(member.getAccount(), member.getPassword(), authorities);
    }
}

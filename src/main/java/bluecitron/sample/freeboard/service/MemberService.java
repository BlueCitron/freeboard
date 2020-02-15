package bluecitron.sample.freeboard.service;

import bluecitron.sample.freeboard.domain.Member;
import bluecitron.sample.freeboard.domain.exception.InvalidOperationException;
import bluecitron.sample.freeboard.dto.MemberDto;
import bluecitron.sample.freeboard.repository.MemberRepository;
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

    public Long join(MemberDto memberDto) {
        Optional<Member> byAccount = memberRepository.findByAccount(memberDto.getAccount());
        if (byAccount.isPresent()) {
            throw new InvalidOperationException("이미 존재하는 계정입니다. [" + memberDto.getAccount() + "]");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        Member member = memberDto.toEntity();
        return memberRepository.save(member).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Optional<Member> memberWrapper = memberRepository.findByAccount(account);

        if (memberWrapper.isPresent()) {
            Member member = memberWrapper.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(member.getGrade().toString()));
            return new User(member.getAccount(), member.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("The account is not found. [" + account +"]");
        }
    }
}

package bluecitron.sample.freeboard.config;

import bluecitron.sample.freeboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/user/myinfo").hasRole("NORMAL")
                .antMatchers("/**").permitAll()
                .and() // 로그인 설정
                .formLogin()
                .loginPage("/auth/login")   // 로그인 요청(POST)
                .defaultSuccessUrl("/")
                .permitAll()
                .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout")) // 로그아웃 요청(POST)
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling().accessDeniedPage("/user/denied");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

}

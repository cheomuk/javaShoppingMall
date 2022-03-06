package com.shop.controller.config;

import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 어댑터 클래스를 상속받으면 SpringSecurityFilterChain이 포함된다.
    // 이것으로 메소드 오버라이딩을 통해 보안 설정을 커스터마이징 할 수 있다.

    @Autowired
    MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();     // 이 해시 함수를 사용해 비밀번호를 암호화시켜 DB에 저장한다.
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {  // http 요청에 대한 보안을 설정한다.
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.formLogin()
                .loginPage("/members/login")            // 로그인 페이지 URL 설정
                .defaultSuccessUrl("/")                 // 로그인 성공 시 이동할 URL 설정
                .usernameParameter("email")             // 로그인 시 사용할 파라미터 이름으로 email을 저장
                .failureUrl("/member/login/error")     // 로그인 실패 시 이동할 URL 설정
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 URL 설정
                .logoutSuccessUrl("/");                 // 로그아웃 성공 시 이동할 URL 설정

        http.authorizeRequests()    // 시큐리티 처리에 HttpServletRequest를 이용한다.
                .mvcMatchers("/", "/members/**", "/item/**", "/images/**")
                .permitAll()        // permitAll()을 통해 모든 사용자가 인증없이 해당 경로(위 4개)에 접근할 수 있도록 설정함.
                .mvcMatchers("/admin/**").hasRole("ADMIN")  // /admin으로 시작하는 경로는 ADMIN Role만 접근 가능함.
                .anyRequest().authenticated();  // 위에 설정한 경로 외 나머지 경로는 전부 인증을 요구하도록 설정.

        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        // 인증되지 않은 사용자가 리소스에 접근했을 때 수행되는 핸들러를 등록한다.
    }

    /*
    * Spring Security에서 인증은 AuthenticationManager를 통해 이루어지며 AuthenticationManagerBuilder가
    * AuthenticationManager를 생성한다. userDetailService를 구현하고 있는 객체로 memberService를 지정해주며,
    * 비밀번호 암호화를 위해 passwordEncoder를 지정해 주었다.
    * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
        // static 디렉터리의 하위 파일은 인증을 무시하도록 설정한다.
    }
}

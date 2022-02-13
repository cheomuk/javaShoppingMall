package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 어댑터 클래스를 상속받으면 SpringSecurityFilterChain이 포함된다.
    // 이것으로 메소드 오버라이딩을 통해 보안 설정을 커스터마이징 할 수 있다.

    @Override
    protected void configure(HttpSecurity http) throws Exception {  // http 요청에 대한 보안을 설정한다.
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();     // 이 해시 함수를 사용해 비밀번호를 암호화시켜 DB에 저장한다.
    }
}

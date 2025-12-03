package com.hyunwjd.umc9th.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // Spring Security 설정 활성화
@Configuration
public class SecurityConfig {

    private final String[] allowUris = {
            "/sign-up",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll() // <-- requestMatchers(): 특정 URL 패턴에 대한 접근 건한 설정 / permitAll(): 인증 없이 접근 가능한 경로 지정
                        .requestMatchers("/admin/**").hasRole("ADMIN") // "/admin/**" 경로는 "ADMIN" 역할을 가진 사용자만 접근 가능
                        .anyRequest().authenticated() // 나머지 모든 요청은 인증된 사용자만 접근 가능
                ) // HTTP 요청에 대한 접근 제어 설정
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true) // 로그인 성공 시 리다이렉트할 URL 설정
                        .permitAll() // 모든 사용자가 로그인 페이지에 접근할 수 있도록 허용
                )  // 폼 기반 로그인 설정
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 URL 설정
                        .logoutSuccessUrl("/login?logout") // 로그아웃 성공 시 리다이렉트할 URL 설정
                        .permitAll() // 모든 사용자가 로그아웃 URL에 접근할 수 있도록 허용
                ); // 로그아웃 설정

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
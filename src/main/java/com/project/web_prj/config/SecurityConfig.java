package com.project.web_prj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // security 설정을 웹에 적용
public class SecurityConfig {

    // security 기본 설정을 처리하는 Bean
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 초기에 나오는 디폴트 로그인 화면 안뜨게 하기
        http.csrf().disable() // csrf 공격 방어토큰 자동 생성 해제
                .authorizeRequests() // 권한요청 범위 설정
                .antMatchers("/member/**") 
                .permitAll(); // /member로 시작하는 요청은 따로 권한 검증하지 말아라

        return http.build();
    }
}

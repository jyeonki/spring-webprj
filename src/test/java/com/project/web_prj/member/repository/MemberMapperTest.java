package com.project.web_prj.member.repository;

import com.project.web_prj.member.domain.Auth;
import com.project.web_prj.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired MemberMapper mapper;

    @Test
    @DisplayName("회원가입에 성공해야 한다.")
    void registerTest() {

        Member m = new Member();
        m.setAccount("abc123");
        m.setPassword("1234");
        m.setName("에이킹");
        m.setEmail("abc123@naver.com");
        m.setAuth(Auth.ADMIN);

        boolean flag = mapper.register(m);

        assertTrue(flag);
    }

    @Test
    @DisplayName("비밀번호가 암호화인코딩 되어야 한다.")
    void encodePasswordTest() {

        // 인코딩 전 비밀번호
        String rawPassword = "ddd5555";

        // 인코딩을 위한 객체 생성
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 스프링에 빈 등록해놓고 쓰면 매번 객체생성 안해도 된다.

        // 인코딩 후 비밀번호
        String encodePassword = encoder.encode(rawPassword);

        System.out.println("rawPassword = " + rawPassword);
        System.out.println("encodePassword = " + encodePassword);
    }

    @Test
    @DisplayName("회원가입에 비밀번호가 인코딩된 상태로 성공해야 한다.")
    void registerTest2() {

        Member m = new Member();
        m.setAccount("qwe321");
        m.setPassword(new BCryptPasswordEncoder().encode("4321"));
        m.setName("큐킹");
        m.setEmail("qwe321@gmail.com");
        m.setAuth(Auth.ADMIN);

        boolean flag = mapper.register(m);

        assertTrue(flag);
    }
}
package com.project.web_prj.member.repository;

import com.project.web_prj.member.domain.Auth;
import com.project.web_prj.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired MemberMapper mapper;
    @Autowired BCryptPasswordEncoder encoder;

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

    @Test
    @DisplayName("특정 계정명으로 회원을 조회해야 한다.")
    void findUserTest() {

        // given
        String account = "abc123";

        // when
        Member member = mapper.findUser(account);

        // then
        System.out.println("member = " + member);
        assertEquals("에이킹", member.getName());
    }

    @Test
    @DisplayName("특정 계정명으로 회원을 조회할 수 없어야 한다.")
    void findUserTest2() {

        // given
        String account = "abc123456";

        // when
        Member member = mapper.findUser(account);

        // then
        assertNull(member);
    }

    @Test
    @DisplayName("아이디를 중복확인 할 수 있다.")
    void checkAccountTest() {

        // given
        Map<String, Object> checkMap = new HashMap<>();
        checkMap.put("type", "account");
        checkMap.put("value", "abc123");

        // when
        int flagNumber = mapper.isDuplicate(checkMap);

        // then
        assertTrue(flagNumber == 1);
    }

    @Test
    @DisplayName("이메일을 중복확인 할 수 있다.")
    void checkEmailTest() {

        // given
        Map<String, Object> checkMap = new HashMap<>();
        checkMap.put("type", "email");
        checkMap.put("value", "abc123@naver.com");

        // when
        int flagNumber = mapper.isDuplicate(checkMap);

        // then
        assertTrue(flagNumber == 1);
    }

    @Test
    @DisplayName("로그인을 검증해야 한다.")
    void signInTest() {
    
        // 로그인 시도 계정, 패스워드
        String inputId = "abc1234";
        String inputPw = "abc1234!";

        // 1. 로그인 시도한 계정명으로 회원정보 조회
        Member foundMember = mapper.findUser(inputId);

        // 2. 회원가입 여부를 먼저 확인한다
        if (foundMember != null) {

            // 3. 패스워드를 대조한다.
            // 실제 회원의 비밀번호를 가져온다.
            String dbPw = foundMember.getPassword();

            // 4. 암호화 된 패스워드를 디코딩하여 비교
            if (encoder.matches(inputPw, dbPw)) {
                System.out.println("로그인 성공");
            } else {
                System.out.println("비밀번호가 틀렸습니다.");
            }

        } else {
            System.out.println("존재하지 않는 아이디입니다.");
        }
    }
}
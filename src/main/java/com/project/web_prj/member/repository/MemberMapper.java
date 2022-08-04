package com.project.web_prj.member.repository;

import com.project.web_prj.member.domain.Member;
import com.project.web_prj.member.dto.AutoLoginDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MemberMapper {

    // 회원가입 기능
    boolean register(Member member);

    // 중복체크 기능
    // 체크 타입 : account OR email
    // 체크값 : 중복검사 대상값
    int isDuplicate(Map<String, Object> checkMap);


    // 회원정보 조회 기능
    Member findUser(String account);
    
    // 자동로그인 쿠키정보 저장
    void saveAutoLoginValue(AutoLoginDTO dto);

    // 쿠키값(세션아이디)을 가지고 있는 회원정보 조회
    Member findMemberBySessionId(String sessionId);
}

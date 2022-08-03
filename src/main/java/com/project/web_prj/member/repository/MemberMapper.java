package com.project.web_prj.member.repository;

import com.project.web_prj.member.domain.Member;
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
}
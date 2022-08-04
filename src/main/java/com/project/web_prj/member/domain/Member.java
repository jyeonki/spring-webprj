package com.project.web_prj.member.domain;

import lombok.*;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor // @Data 를 쓰면 이렇게 일일이 안 적어도 되지만 추천하지 않는다 (ToString 충돌 가능성)
public class Member {

    private String account;
    private String password;
    private String name;
    private String email;
    private Auth auth;
    private Date regDate; // 공통컬럼 빼서 상속으로 만드는 것도 생각해보기

    private String sessionId;
    private Date limitTime;
}

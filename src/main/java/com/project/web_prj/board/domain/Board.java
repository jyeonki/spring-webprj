package com.project.web_prj.board.domain;

import lombok.*;

import java.util.Date;

@Setter @Getter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor // 6개는 보통 기본으로 설정
public class Board {

    private long boardNo; // table NUMBER(10) -> long
    private String writer;
    private String title;
    private String content;
    private long viewCnt; // table NUMBER(10) -> long
    private Date regDate; // 등록일자


}

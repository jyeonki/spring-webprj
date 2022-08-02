package com.project.web_prj.board.domain;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Setter @Getter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor // 6개는 보통 기본으로 설정
public class Board {

    // table column field
    private long boardNo; // table NUMBER(10) -> long
    private String writer;
    private String title;
    private String content;
    private long viewCnt; // table NUMBER(10) -> long
    private Date regDate; // 등록일자

    // custom data field
    private String shortTitle;   // 줄임 제목
    private String prettierDate; // 변경된 날짜포맷 문자열
    private boolean newArticle;  // 신규 게시물 여부
    private int replyCount;      // 댓글 수
    
    private List<String> fileNames; // 첨부파일들의 이름 목록
    public Board(ResultSet rs) throws SQLException {
        this.boardNo = rs.getLong("board_no");
        this.title = rs.getString("title");
        this.writer = rs.getString("writer");
        this.content = rs.getString("content");
        this.viewCnt = rs.getLong("view_cnt");
        this.regDate = rs.getTimestamp("reg_date");
    }
}

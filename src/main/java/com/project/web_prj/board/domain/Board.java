package com.project.web_prj.board.domain;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
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


    public Board(ResultSet rs) throws SQLException {
        this.boardNo = rs.getLong("board_no");
        this.title = rs.getString("title");
        this.writer = rs.getString("writer");
        this.content = rs.getString("content");
        this.viewCnt = rs.getLong("view_cnt");
        this.regDate = rs.getDate("reg_date");
    }
}

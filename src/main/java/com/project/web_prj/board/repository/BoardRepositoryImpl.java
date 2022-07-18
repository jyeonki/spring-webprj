package com.project.web_prj.board.repository;

import com.project.web_prj.board.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//("bri") // bean name
@Log4j2
@RequiredArgsConstructor // final 필드의 생성자 초기화
public class BoardRepositoryImpl implements BoardRepository{

    private final JdbcTemplate template;
    /* 이거 대신 @RequiredArgsConstructor 사용
    @Autowired
    public BoardRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }
    */

    @Override
    public boolean save(Board board) {

        log.info("save process with jdbc - {}", board); // 개발 단계에서는 debug로 하는게 맞지만 지금은 일단 info
        String sql = "INSERT INTO tbl_board (board_no, writer, title, content) VALUES (seq_tbl_board.nextval, ?, ?, ?)";

        return template.update(sql, board.getWriter(), board.getTitle(), board.getContent()) == 1;
    }

    @Override
    public List<Board> findAll() {

        String sql = "SELECT * FROM tbl_board ORDER BY board_no DESC";

        return template.query(sql, (rs, rn) -> new Board(rs));
    }

    @Override
    public Board findOne(long boardNo) {

        String sql = "SELECT * FROM tbl_board WHERE board_no = ?";

        return template.queryForObject(sql, (rs, rowNum) -> new Board(rs), boardNo);
    }

    @Override
    public boolean modify(Board board) {

        String sql = "UPDATE tbl_board SET writer = ?, title = ?, content = ? WHERE board_no = ?";

        return template.update(sql, board.getWriter(), board.getTitle(), board.getContent(), board.getBoardNo()) == 1;
    }

    @Override
    public boolean remove(long boardNo) {

        String sql = "DELETE FROM tbl_board WHERE board_no = ?";

        return template.update(sql, boardNo) == 1;
    }

    @Override
    public int getTotalCount() {

        String sql = "SELECT COUNT(*) AS cnt FROM tbl_board";

        return template.queryForObject(sql, Integer.class);
    }
}

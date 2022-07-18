package com.project.web_prj.board.repository;

import com.project.web_prj.board.domain.Board;

import java.util.List;

public interface BoardRepository {

    // 게시글 쓰기 기능
    boolean save(Board board);

    // 게시글 전체 조회
    List<Board> findAll();

    // 게시글 상세 조회
    Board findOne(long boardNo);

    // 게시글 수정
    boolean modify(Board board);

    // 게시글 삭제
    boolean remove(long boardNo);

    // 전체 게시물 수 조회
    int getTotalCount();
}

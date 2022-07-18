package com.project.web_prj.board.controller;

import com.project.web_prj.board.domain.Board;
import com.project.web_prj.board.repository.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
     * 게시물 목록 요청: /board/list: GET
     * 게시물 상세조회 요청: /board/content: GET
     * 게시글 쓰기화면 요청: /board/write: GET
     * 게시글 등록 요청: /board/write: POST
     * 게시글 삭제 요청: /board/delete: GET
     * 게시글 수정화면 요청: /board/modify: GET
     * 게시글 수정 요청: /board/modify: POST
     */

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // 게시물 목록 요청
    @GetMapping("/list")
    public String list() {

        log.info("controller request /board/list GET!");

        List<Board> boardList = boardService.findAllService();
        log.info("return data - {}",boardList);

        return "";
    }

    // 게시물 상세 조회 요청
    @GetMapping("/content/{boardNo}")
    public String content(@PathVariable Long boardNo) {

        log.info("controller request /board/content GET! - {}", boardNo);

        Board board = boardService.findOneService(boardNo);
        log.info("return data - {}", board);

        return "";
    }

    // 게시물 쓰기 화면 요청
    @GetMapping("/write")
    public String write() {

        log.info("controller request /board/write GET!");

        return "";
    }
}

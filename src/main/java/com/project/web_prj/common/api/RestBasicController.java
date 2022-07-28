package com.project.web_prj.common.api;

import com.project.web_prj.board.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// jsp 뷰포워딩을 하지 않고 클라이언트에게 JSON 데이터를 전송함
@RestController
@Log4j2
public class RestBasicController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/api/board")
    public Board board() {

        Board board = new Board();
        board.setBoardNo(10L);
        board.setContent("board~0");
        board.setTitle("하이");
        board.setWriter("jyeonki");

        return board;
    }

    @GetMapping("/api/arr")
    public String[] arr() {

        String[] foods = {"짜장면", "레몬에이드", "볶음밥"};

        return foods;
    }
    
}

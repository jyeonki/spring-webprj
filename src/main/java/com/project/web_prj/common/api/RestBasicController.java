package com.project.web_prj.common.api;

import com.project.web_prj.board.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    // post 요청처리
    @PostMapping("/api/join")
    public String joinPost(@RequestBody List<String> info) {
        // @RequestBody 안 붙이면 자동으로 @RequestParam 처리가 되어서 보내진다
        //  JSON으로 받으려면 - @RequestBody
        log.info("/api/join POST! - {}", info);

        return "POST OK";
    }
    
    // put 요청처리
    @PutMapping("/api/join")
    public String joinPut(@RequestBody Board board) {
        log.info("/api/join PUT! - {}", board);

        return "PUT OK";
    }
    
    // delete 요청처리
    @DeleteMapping("/api/join")
    public String joinDelete() {
        log.info("/api/join DELETE!");

        return "DELETE OK";
    }

    // @RestController에서 뷰포워딩하기
    @GetMapping("/hoho")
    public ModelAndView hoho() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");

        return mv;
    }

    // @ResponseBody // 리턴데이터가 뷰포워딩이 아닌 JSON으로 전달됨. (@RestController 로 작동)
}

package com.project.web_prj.reply.api;

import com.project.web_prj.common.paging.Page;
import com.project.web_prj.reply.domain.Reply;
import com.project.web_prj.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/v1/replies")
@CrossOrigin // 허용
public class ReplyApiController {

    private final ReplyService replyService; // 의존 객체 주입

    /*
        - 댓글 목록 조회요청 : /api/v1/replies - GET
        - 댓글 개별 조회요청 : /api/v1/replies/11 - GET
        - 댓글 쓰기 요청 : /api/v1/replies - POST
        - 댓글 수정 요청 : /api/v1/replies/11 - PUT
        - 댓글 삭제 요청 : /api/v1/replies/11 - DELETE
    */

    // 댓글 목록 요청
    @GetMapping("")
    public Map<String, Object> list(Long boardNo, Page page) {

        Map<String, Object> replies = replyService.getList(boardNo, page);

        return replies;
    }

    // 댓글 등록 요청
    @PostMapping("")
    public String create(@RequestBody Reply reply) {

        log.info("/api/v1/replies POST! - {}", reply);
        boolean flag = replyService.write(reply);

        return flag ? "insert-success" : "insert-fail";
    }

    // 댓글 수정 요청
    @PutMapping("/{rno}")
    public String modify(@PathVariable Long rno, @RequestBody Reply reply) {

        reply.setReplyNo(rno);
        log.info("/api/v1/replies PUT! - {}", reply);
        boolean flag = replyService.modify(reply);

        return flag ? "modify-success" : "modify-fail";
    }

    // 댓글 삭제 요청
    @DeleteMapping("/{rno}")
    public String delete(@PathVariable Long rno) {

        log.info("/api/v1/replies DELETE! - {}", rno);
        boolean flag = replyService.remove(rno);

        return flag ? "delete-success" : "delete-fail";
    }
}
package com.project.web_prj.board.service;

import com.project.web_prj.board.domain.Board;
import com.project.web_prj.board.repository.BoardRepository;
import com.project.web_prj.common.paging.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;

    // 게시물 등록 요청 중간 처리
    public boolean saveService(Board board) {
        log.info("save service start - {}", board);
        return repository.save(board);
    }

    // 게시물 전체 조회 요청 중간 처리
    public List<Board> findAllService() {
        log.info("findAll service start");
//        return repository.findAll();

        List<Board> boardList = repository.findAll();

        // 목록 중간 데이터 처리
        processConverting(boardList);

        // 글제목 줄임 처리 ctrl + alt + m
//        subStringTitle(boardList);

        // 시간 포맷팅 처리 ctrl + alt + m
//        convertDateFormat(boardList);

        return boardList;
    }

    private void processConverting(List<Board> boardList) {
        for (Board b : boardList) {
            convertDateFormat(b);
            substringTitle(b);
        }
    }

    private void convertDateFormat(Board b) {
        Date date = b.getRegDate();

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd a hh:mm"); // pattern을 쓰면 원하는 형태로 바뀐다
        // hh : 1 - 12시간 , HH : 24시간으로 표기
        b.setPrettierDate(sdf.format(date));
    }

    private void substringTitle(Board b) {
        // 만약에 글제목이 5글자 이상이라면 5글자만 보여주고 나머지는 ...처리
        String title = b.getTitle();

        if (title.length() > 5) {
            String subStr = title.substring(0, 5);
            b.setShortTitle(subStr + "...");
        } else {
            b.setShortTitle(title);
        }
    }

    /*private void convertDateFormat(List<Board> boardList) {
        for (Board b : boardList) {
            Date date = b.getRegDate();

            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd a hh:mm");// pattern을 쓰면 원하는 형태로 바뀐다
            // hh : 1 - 12시간 , HH : 24시간으로 표기

            b.setPrettierDate(sdf.format(date));
        }
    }
    private void subStringTitle(List<Board> boardList) {
        for (Board b : boardList) {
            // 만약에 글제목이 5글자 이상이라면 5글자만 보여주고 나머지는 ... 처리
            String title = b.getTitle();
            if (title.length() > 5) {
                String subStr = title.substring(0, 5);
//                b.setTitle(subStr + "...");
                b.setShortTitle(subStr + "...");
            } else {
                b.setShortTitle(title);
            }
        }
    }*/

    // 게시물 전체 조회 요청 중간 처리 with paging
    public Map<String,Object> findAllService(Page page) {
        log.info("findAll service start");
//        return repository.findAll();

        Map<String, Object> findDataMap = new HashMap<>();

        List<Board> boardList = repository.findAll(page);

        // 목록 중간 데이터 처리
        processConverting(boardList);

        // 글제목 줄임 처리 ctrl + alt + m
//        subStringTitle(boardList);

        // 시간 포맷팅 처리 ctrl + alt + m
//        convertDateFormat(boardList);

        findDataMap.put("bList", boardList);
        findDataMap.put("tc", repository.getTotalCount());

        return findDataMap;
    }

    // 게시물 상세 조회 요청 중간 처리
    @Transactional
    public Board findOneService(Long boardNo, HttpServletResponse response, HttpServletRequest request) {
        // HttpServletResponse - 쿠키를 만들어서 클라이언트에 전송하기위해 필요
        log.info("findOne service start - {}", boardNo);

        // 트랜잭션 처리 - 동시에 일어나야 한다
        // 상세보기는 뜨지 않는데 조회수가 올라가면 안된다, 반대의 경우도 마찬가지
        Board board = repository.findOne(boardNo);

        // 해당 게시물 번호에 해당하는 쿠키가 있는지 확인
        // 쿠키가 없으면 조회수를 상승시켜주고, 쿠키를 만들어서 클라이언트에 전송
        makeViewCount(boardNo, response, request);

        return board;
    }

    private void makeViewCount(Long boardNo, HttpServletResponse response, HttpServletRequest request) {

        // 쿠키를 조회 - 해당 이름의 쿠키가 있으면 쿠키가 들어오고 없으면 null이 들어옴
        Cookie foundCookie = WebUtils.getCookie(request, "b" + boardNo);

        if (foundCookie == null) {
            repository.upViewCount(boardNo);

//        new Cookie("쿠키이름", "쿠키값"); // 쿠키 생성
            Cookie cookie = new Cookie("b" + boardNo, String.valueOf(boardNo)); // 쿠키 생성
            cookie.setMaxAge(60);                                                     // 쿠키 수명 설정 (60초)
            cookie.setPath("/board/content");                                         // 쿠키 작동 범위

            response.addCookie(cookie);                                               // 클라이언트에 쿠키 전송
        }
    }

    // 게시물 삭제 요청 중간 처리
    public boolean removeService(Long boardNo) {
        log.info("remove service start - {}", boardNo);
        return repository.remove(boardNo);
    }

    // 게시물 수정 요청 중간 처리
    public boolean modifyService(Board board) {
        log.info("modify service start - {}", board);
        return repository.modify(board);
    }
}
package com.project.web_prj.board.service;

import com.project.web_prj.board.domain.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardMapperServiceTest {

    @Autowired
    BoardMapperService service;

    @Test
    @DisplayName("파일 첨부가 잘 수행되어야 한다.")
    void addFileTest() {
        Board b = new Board();
        b.setWriter("푸린이");
        b.setTitle("폼폼이 소개");
        b.setContent("나는 산리오 친구들 푸린이야");;
        b.setFileNames(Arrays.asList("폼폼푸린1.png", "폼폼푸린2.png"));

        service.saveService(b);
    }

}
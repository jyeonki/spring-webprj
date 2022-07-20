package com.project.web_prj.board.repository;

import com.project.web_prj.board.domain.Board;
import com.project.web_prj.common.paging.Page;
import com.project.web_prj.common.search.Search;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardMapperTest {

    @Autowired
    BoardMapper mapper;

    @Test
    @DisplayName("게시글 정보가 저장되어야 한다.")
    void saveTest() {

        Board board = new Board();

        mapper.save(board);
        System.out.println(board);
    }

    @Test
    @DisplayName("전체 게시물 정보가 조회되어야 한다.")
    void findAllWithPagingTest() {

        Page page = new Page(1, 20);
        mapper.findAllWithPaging(page).forEach(System.out::println);
    }

    @Test
    @DisplayName("전체 게시물 정보가 조회되어야 한다.")
    void findAllWithSearchTest() {

//        Search search = new Search(new Page(1, 10), "writer", "김코히");
//        mapper.findAllWithSearch(search).forEach(System.out::println);
    }

    @Test
    @DisplayName("게시물 정보가 조회되어야 한다.")
    void findOneTest() {

        Board board = mapper.findOne(312);
        System.out.println("board = " + board);
    }

    @Test
    @DisplayName("게시물 정보가 삭제되어야 한다.")
    void removeTest() {

        boolean flag = mapper.remove(312);

        assertTrue(flag);
    }

    @Test
    @DisplayName("전체 게시물 수가 조회되어야 한다.")
    void getTotalCountTest() {

        int totalCount = mapper.getTotalCount();

        System.out.println("totalCount = " + totalCount);
//        assertTrue(totalCount == 307);
        assertEquals(307, totalCount);
    }
}
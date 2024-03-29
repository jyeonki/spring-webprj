package com.project.web_prj.board.repository;

import com.project.web_prj.board.domain.Board;
import com.project.web_prj.board.dto.ValidateMemberDTO;
import com.project.web_prj.common.paging.Page;
import com.project.web_prj.common.search.Search;
import com.project.web_prj.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 게시글 쓰기 기능
    boolean save(Board board);

    // 게시글 전체 조회
//    List<Board> findAll(); // mybatis는 같은 이름은 지원하지 않는다 (findAll 2개 불가능)

    // 게시글 전체 조회
    List<Board> findAll();

    // 게시글 전체 조회 with paging
    List<Board> findAllWithPaging(Page page);

    // 게시글 전체 조회 with searching
    List<Board> findAllWithSearch(Search search);

    // 게시글 상세 조회
    Board findOne(long boardNo);

    // 게시글 수정
    boolean modify(Board board);

    // 게시글 삭제
    boolean remove(long boardNo);

    // 전체 게시물 수 조회
    int getTotalCount();
    int getTotalCountWithSearch(Search search); //검색할 때 검색 건수에 따라 밑에 페이징 수 조절을 위해 WHERE 절 추가


    // 조회수 상승 처리
    void upViewCount(Long boardNo);

    // 파일 첨부 기능 처리
    void addFile(String fileName);

    // 게시물에 붙어있는 첨부파일 경로명 전부 조회하기
    List<String> findFileNames(Long bno);

    // 게시물 번호로 게시글 작성자의 계정명과 권한 가져오기
    ValidateMemberDTO findMemberByBoardNo(Long boardNo);
}

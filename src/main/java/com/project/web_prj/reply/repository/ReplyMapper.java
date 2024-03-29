package com.project.web_prj.reply.repository;

import com.project.web_prj.common.paging.Page;
import com.project.web_prj.reply.domain.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    //댓글 입력
    boolean save(Reply reply);

    //댓글 수정
    boolean modify(Reply reply);

    //댓글 삭제
    boolean remove(Long replyNo);

    // 댓글 전체 삭제
    boolean removeAll(Long boardNo); // 게시물에 달린 모든 댓글 삭제

    //댓글 개별 조회
    Reply findOne(Long replyNo);

    //댓글 목록 조회
    List<Reply> findAll(@Param("boardNo") Long boardNo
            , @Param("page") Page page);
    // mybatis는 값을 여러개 보내면 인식불가능, 그래서 @Param을 적어준다

    // 댓글 수 조회
    int getReplyCount(Long boardNo);
}
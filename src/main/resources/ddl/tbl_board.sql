
-- 실무에서는 테이블 복사해서 저장하면 안됨! 이런 경로에 이런 파일 생성하면 안됨!!!!
-- 이건 집에 가서 다시 테이블 생성해야 하니까 만든거임
CREATE SEQUENCE seq_tbl_board;

DROP TABLE tbl_board;
CREATE TABLE tbl_board (
    board_no NUMBER(10),
    writer VARCHAR2(20) NOT NULL,
    title VARCHAR2(200) NOT NULL,
    content CLOB,
    view_cnt NUMBER(10) DEFAULT 0,
    reg_date DATE DEFAULT SYSDATE,
    CONSTRAINT pk_tbl_board PRIMARY KEY (board_no)
);

ALTER TABLE tbl_board ADD account VARCHAR2(50) NOT NULL;


-- 내용 삭제?
TRUNCATE TABLE tbl_reply;
DELETE FROM tbl_board;
TRUNCATE TABLE file_upload;

--Paging query

--SELECT ROWNUM, tbl_board.*
--FROM tbl_board
--WHERE ROWNUM BETWEEN 11 AND 20
--ORDER BY board_no DESC
--;
--
--SELECT *
--FROM (SELECT ROWNUM rn, v_board.*
--        FROM(
--                SELECT *
--                FROM tbl_board
--                ORDER BY board_no DESC
--                ) v_board)
--WHERE rn BETWEEN 11 AND 20
--;


-- search
--SELECT  *
--FROM (SELECT ROWNUM rn, v_board.*
--        FROM (
--                SELECT *
--                FROM tbl_board
--                WHERE title LIKE '%30%'
--                ORDER BY board_no DESC
--                ) v_board)
--WHERE rn BETWEEN 1 AND 10
--;
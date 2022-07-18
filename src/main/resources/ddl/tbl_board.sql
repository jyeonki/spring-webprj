
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
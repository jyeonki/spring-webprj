

-- 회원 관리 테이블
CREATE TABLE tbl_member (
    account VARCHAR2(50),
    password VARCHAR2(150) NOT NULL, -- 비밀번호는 평문으로 저장하면 안된다 (암호화시켜야한다)
    name VARCHAR2(50) NOT NULL,
    email VARCHAR2(100) NOT NULL UNIQUE,
    auth VARCHAR2(20) DEFAULT 'COMMON', -- 관리자는 ADMIN, 일반 COMMON
    reg_date DATE DEFAULT SYSDATE,
    CONSTRAINT pk_member PRIMARY KEY (account)
);

SELECT * FROM tbl_member;

-- 로그인 이력


CREATE SEQUENCE seq_tbl_sns;

DROP TABLE tbl_sns;

CREATE TABLE tbl_sns (
    sns_code NUMBER(5),
    sns_name VARCHAR2(10),
    CONSTRAINT pk_tbl_sns PRIMARY KEY (sns_code)
);

INSERT INTO tbl_sns VALUES (1, 'KAKAO');
INSERT INTO tbl_sns VALUES (2, 'NAVER');
INSERT INTO tbl_sns VALUES (3, 'FACEBOOK');
INSERT INTO tbl_sns VALUES (4, 'GOOGLE');

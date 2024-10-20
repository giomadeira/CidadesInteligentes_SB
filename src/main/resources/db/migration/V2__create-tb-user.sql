CREATE SEQUENCE SEQ_USER
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE TB_USER (
    USER_ID INTEGER DEFAULT SEQ_USER.nextval NOT NULL,
    NAME VARCHAR2(200) NOT NULL,
    EMAIL VARCHAR2(200) UNIQUE NOT NULL,
    PASSWORD VARCHAR2(100) NOT NULL,
    ROLE VARCHAR2(50) DEFAULT 'USER'
)
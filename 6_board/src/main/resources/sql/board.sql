-- 2019. 7 . 10.
-- 게시판 작성

-- 회원관련
DROP TABLE member;
CREATE TABLE member 
(
	userid   varchar2(20) primary key,	-- 아이디
	userpwd  varchar2(20) not null,   	-- 비밀번호
    username varchar2(30) not null,  	-- 이름
	phone    varchar2(20) not null,   	-- 전화번호
	gender   varchar2(10) default 'male', -- 성별 (male/female)
	email    varchar2(50),				-- email
	address  varchar2(100)				-- 주소
);


CREATE TABLE board
(
   boardseq number primary key,
   userid varchar2(20) references member(userid) not null,
   title varchar2(200) default '내용없음',
   content varchar2(4000),
   regdate date default sysdate,
   viewcount number default 0,
   favorite number default 0 ,           --ajax 문법을 이용할 예정
   originalfile varchar2(500),
   savedfile varchar2(500)
)

CREATE SEQUENCE board_seq

INSERT INTO BOARD VALUES
(
board_seq.nextval,
'aaa',
'날씨가 비가 많이와서 축축해요',
'그렇다구요',
sysdate,
0,
0,
null,
null
)




--댓글쓰기용 테이블

CREATE TABLE REPLYINFO(
REPLYSEQ NUMBER PRIMARY KEY,
USERID VARCHAR2(20) REFERENCES MEMBER(USERID) NOT NULL,
BOARDSEQ NUMBER REFERENCES BOARD(BOARDSEQ) NOT NULL,
CONTENT VARCHAR2(4000),
REGDATE DATE DEFAULT SYSDATE
)


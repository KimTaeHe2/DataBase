create table board( -- 테이블 생성
	bno			number(5) primary key,
	btitle		nvarchar2(30) not null,
	bcontent	nvarchar2(1000) not null,
	bwriter 	nvarchar2(10) not null,
	bdate 		date 	not null
); 

create sequence board_seq; -- 시퀸스 생성
drop table board;
insert into board (bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '고구마', '비오는데 등교하시느냐고 고생 하셨습니다.', '김태희', sysdate);
insert into board (bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '토마토', '비오는데 등교하시느냐고 고생 하셨습니다.', '김태희', sysdate);
insert into board (bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '바나나', '비오는데 등교하시느냐고 고생 하셨습니다.', '김태희', sysdate);
insert into board (bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '옥수수', '비오는데 등교하시느냐고 고생 하셨습니다.', '김태희', sysdate);
insert into board (bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '세글자', '비오는데 등교하시느냐고 고생 하셨습니다.', '김태희', sysdate);
insert into board (bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '수확물', '비오는데 등교하시느냐고 고생 하셨습니다.', '김태희', sysdate);

select * from board;

------------member 테이블 용

create table member(
	mno		number(5) primary key,
	mid		nvarchar2(10) UNIQUE,
	mpw 	nvarchar2(10) not null,
	mdate 	date		  not null
);
-- 더미데이터
insert into member (mno, mid, mpw, mdate) values (board_seq.nextval, '김태희', '1234', sysdate);
insert into member (mno, mid, mpw, mdate) values (board_seq.nextval, '이현우', '1234', sysdate);
insert into member (mno, mid, mpw, mdate) values (board_seq.nextval, '김우혁', '1234', sysdate);
insert into member (mno, mid, mpw, mdate) values (board_seq.nextval, '김정하', '1234', sysdate);

select * from member;
drop table member;






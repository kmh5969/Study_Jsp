DROP SEQUENCE MEMBERBOARDSEQ;
DROP TABLE MEMBERBOARD;

CREATE SEQUENCE MEMBERBOARDSEQ;

CREATE TABLE MEMBERBOARD(
	MYNO NUMBER NOT NULL,
	MYID VARCHAR2(500) NOT NULL,
	MYPW VARCHAR2(500) NOT NULL,
	MYNAME VARCHAR2(1000) NOT NULL,
	MYADDR VARCHAR2(4000) NOT NULL,
	MYPHONE VARCHAR2(20) NOT NULL,
	MYEMAIL VARCHAR2(100) NOT NULL,
	MYENABLED VARCHAR2(2) NOT NULL,
	MYROLE VARCHAR2(100) NOT NULL,
	CONSTRAINT MYMEMBER_PK PRIMARY KEY (MYNO),
	CONSTRAINT MYMEMBER_UQ_ID UNIQUE (MYID),
	CONSTRAINT MYMEMBER_UQ_PHONE UNIQUE (MYPHONE),
	CONSTRAINT MYMEMBER_UQ_EMAIL UNIQUE (MYEMAIL),
	CONSTRAINT MYMEMBER_CK_ENABLED CHECK (MYENABLED IN ('Y','N'))
);

INSERT INTO MEMBERBOARD
VALUES(MEMBERBOARDSEQ.NEXTVAL,'admin','admin1234','관리자','TESTADDR','TESTPHONE','TESTEMAIL','Y','ADMIN');

INSERT INTO MEMBERBOARD
VALUES(MEMBERBOARDSEQ.NEXTVAL,'test','test','유저1','TESTADDR','TESTPHONE2','TESTEMAIL2','Y','USER');

SELECT * FROM MEMBERBOARD;
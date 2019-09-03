DROP SEQUENCE MYSEQ;
DROP TABLE MYBOARD;

CREATE SEQUENCE MYSEQ;
CREATE TABLE MYBOARD(
	MYNO NUMBER PRIMARY KEY,
	MYNAME VARCHAR2(500) NOT NULL,
	MYTITLE VARCHAR2(2000) NOT NULL,
	MYCONTENT VARCHAR2(4000) NOT NULL,
	MYDATE DATE NOT NULL
);

INSERT INTO MYBOARD
VALUES(MYSEQ.NEXTVAL, '관리자', 'TEST', 'TEST1234', SYSDATE);

SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE
FROM MYBOARD
ORDER BY MYNO DESC;

DROP TABLE LOGINBOARD;
DROP SEQUENCE LOGINSEQ;

CREATE SEQUENCE LOGINSEQ;
CREATE TABLE LOGINBOARD(
	MYNO NUMBER PRIMARY KEY,
	MYID VARCHAR2(100) NOT NULL,
	MYPW VARCHAR2(100) NOT NULL,
	MYNAME VARCHAR2(100) NOT NULL
);

INSERT INTO LOGINBOARD
VALUES(LOGINSEQ.NEXTVAL, 'admin', 'admin1234', '관리자');

SELECT * FROM LOGINBOARD;
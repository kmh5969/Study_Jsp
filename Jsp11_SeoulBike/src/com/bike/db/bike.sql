DROP TABLE BIKE_TB;

CREATE TABLE BIKE_TB(
	RENT_ID VARCHAR2(100),
	ADDR_GU VARCHAR2(100),
	CONTENT_ID NUMBER PRIMARY KEY,
	CONTENT_NM VARCHAR2(4000),
	NEW_ADDR VARCHAR2(4000),
	CRADLE_COUNT NUMBER,
	LONGITUDE NUMBER,
	LATITUDE NUMBER
);

SELECT * FROM BIKE_TB;

DELETE FROM BIKE_TB;
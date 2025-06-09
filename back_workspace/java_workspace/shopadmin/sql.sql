---------------------------------------
-최상위 카테고리
---------------------------------------

CREATE TABLE topcategory(
	topcategory_id int PRIMARY KEY auto_increment
	, top_name varchar(20)
);

INSERT INTO topcategory (top_name) values('상의');
INSERT INTO topcategory (top_name) values('하의');
INSERT INTO topcategory (top_name) values('신발');
INSERT INTO topcategory (top_name) values('액세서리');


---------------------------------------
-하위 카테고리
---------------------------------------

CREATE TABLE subcategory(
	subcategory_id int PRIMARY KEY auto_increment
	, sub_name varchar(20)
	, topcategory_id int
	, CONSTRAINT fk_topcategory_subcategory FOREIGN KEY(topcategory_id)
	  REFERENCES topcategory(topcategory_id) 
);

INSERT INTO subcategory(sub_name, topcategory_id) values('티셔츠',1);
INSERT INTO subcategory(sub_name, topcategory_id) values('가디건',1);
INSERT INTO subcategory(sub_name, topcategory_id) values('점퍼',1);
INSERT INTO subcategory(sub_name, topcategory_id) values('셔츠',1);

INSERT INTO subcategory(sub_name, topcategory_id) values('치마',2);
INSERT INTO subcategory(sub_name, topcategory_id) values('청바지',2);
INSERT INTO subcategory(sub_name, topcategory_id) values('반바지',2);
INSERT INTO subcategory(sub_name, topcategory_id) values('면바지',2);

INSERT INTO subcategory(sub_name, topcategory_id) values('운동화',3);
INSERT INTO subcategory(sub_name, topcategory_id) values('구두',3);
INSERT INTO subcategory(sub_name, topcategory_id) values('슬리퍼',3);
INSERT INTO subcategory(sub_name, topcategory_id) values('샌들',3);

INSERT INTO subcategory(sub_name, topcategory_id) values('반지',4);
INSERT INTO subcategory(sub_name, topcategory_id) values('목걸이',4);
INSERT INTO subcategory(sub_name, topcategory_id) values('팔찌',4);
INSERT INTO subcategory(sub_name, topcategory_id) values('귀걸이',4);





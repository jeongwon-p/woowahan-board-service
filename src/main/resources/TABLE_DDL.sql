CREATE TABLE `article` (
  `article_id` varchar(36) NOT NULL,
  `board_id` varchar(36) NOT NULL,
  `content` blob NOT NULL,
  `crt_tm` varchar(45) NOT NULL,
  `hide_yn` varchar(1) NOT NULL,
  `chg_tm` varchar(45) NOT NULL,
  `title` varchar(200) NOT NULL,
  `user_id` varchar(200) NOT NULL,
  PRIMARY KEY (`article_id`)
)
;

CREATE TABLE `board` (
  `board_id` varchar(36) NOT NULL,
  `board_desc` varchar(2000) NOT NULL,
  `hide_yn` varchar(1) NOT NULL,
  `board_name` varchar(200) NOT NULL,
  PRIMARY KEY (`board_id`)
)
;

CREATE TABLE `comment` (
  `comment_id` varchar(36) NOT NULL,
  `article_id` varchar(36) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `crt_tm` varchar(45) DEFAULT NULL,
  `hide_yn` varchar(1) DEFAULT NULL,
  `chg_tm` varchar(45) DEFAULT NULL,
  `user_id` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
)
;

CREATE TABLE `user` (
  `email_id` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `ranking` int NOT NULL,
  `score` int DEFAULT NULL,
  `hide_yn` varchar(1) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`email_id`)
)
;

CREATE TABLE `USERTOKEN` (
  `email_id` varchar(200) NOT NULL,
  `token` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`email_id`)
)
;

insert into user (email_id, name, password, ranking, score, hide_yn) values ('admin', 'admin', 'admin', '0', '0', 'N', 'ADMIN');
INSERT INTO user (email_id, name, password, ranking, score, hide_yn, role) VALUES ('jongwon5185@naver.com', '박정원', '$2a$10$I1z3kYmQqg8iOLcM/JAqKOlZZLWWGXPy6BUiT5dBNdbI.zCGw1sFy', '0', '0', 'N', 'USER');
INSERT INTO board (board_id, board_desc, hide_yn, board_name) VALUES ('1', '공지사항 올라오는 게시판입니다.', 'N', '공지사항');
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
  PRIMARY KEY (`email_id`)
)
;

insert into user (email_id, name, password, ranking, score, hide_yn) values ('admin', 'admin', 'admin', '0', '0', 'N');
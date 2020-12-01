-- 创建数据库
CREATE DATABASE `lab` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE `lab`.`book`  (
  `ISBN` varchar(255) NOT NULL,
  `bookName` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `addTime` varchar(255) NOT NULL,
  PRIMARY KEY (`ISBN`)
);
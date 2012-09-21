/*
MySQL Data Transfer
Source Host: localhost
Source Database: saboard
Target Host: localhost
Target Database: saboard
Date: 2012-09-16 ¿ÀÈÄ 2:47:07
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for sa_board
-- ----------------------------
CREATE TABLE `sa_board` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `title` varchar(200) NOT NULL,
  `content` longtext NOT NULL,
  `reg_date` varchar(40) NOT NULL,
  `has_file` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sa_board_file
-- ----------------------------
CREATE TABLE `sa_board_file` (
  `no` int(15) NOT NULL AUTO_INCREMENT,
  `id` int(15) NOT NULL,
  `file_name` varchar(120) NOT NULL,
  `file_size` int(10) NOT NULL,
  `file_reg_date` datetime NOT NULL,
  PRIMARY KEY (`no`),
  KEY `file_no` (`id`),
  KEY `id` (`id`),
  CONSTRAINT `fk_id` FOREIGN KEY (`id`) REFERENCES `sa_board` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sa_board_reply
-- ----------------------------
CREATE TABLE `sa_board_reply` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `no` int(15) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `content` longtext NOT NULL,
  `reg_date` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `no` (`no`),
  CONSTRAINT `sa_board_reply_ibfk_1` FOREIGN KEY (`no`) REFERENCES `sa_board` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sa_member
-- ----------------------------
CREATE TABLE `sa_member` (
  `no` int(20) NOT NULL AUTO_INCREMENT,
  `id` varchar(20) NOT NULL,
  `pw` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`no`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `sa_member` VALUES ('4', 'dhrod0325', 'aaa', 'test');
INSERT INTO `sa_member` VALUES ('5', 'dhrod0325', 'aaa', 'test');
INSERT INTO `sa_member` VALUES ('6', 'dhrod0325', 'aaa', 'test');
INSERT INTO `sa_member` VALUES ('7', 'dhrod0325', 'aaa', 'test');

/*
MySQL Data Transfer
Source Host: localhost
Source Database: saboard
Target Host: localhost
Target Database: saboard
Date: 2012-09-16 ¿ÀÀü 12:53:16
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for sa_board_reply
-- ----------------------------
CREATE TABLE `sa_board_reply` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `no` int(15) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `title` varchar(200) NOT NULL,
  `content` longtext NOT NULL,
  `reg_date` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_no` (`no`),
  CONSTRAINT `fk_no` FOREIGN KEY (`no`) REFERENCES `sa_board` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `sa_board_reply` VALUES ('90', '50', 'd', 'd', 'd', 'd', 'asdasdasd', 'now()');

/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50509
 Source Host           : localhost
 Source Database       : saboard

 Target Server Type    : MySQL
 Target Server Version : 50509
 File Encoding         : utf-8

 Date: 09/13/2012 21:11:07 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `board`
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `title` varchar(200) NOT NULL,
  `content` varchar(200) NOT NULL,
  `reg_date` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `sa_member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `board`
-- ----------------------------
BEGIN;
INSERT INTO `board` VALUES ('2', 'dhrod0325', 'zzz', 'asdfasdf', '2012-09-13 17:21:18'), ('3', 'dhrod0325', 'asdf', '히하하ㅏ하하ㅏ', '2012-09-13 20:48:08'), ('4', 'dhrod0325', 'zzz', 'asdfasdf', '2012-09-13 20:52:03'), ('5', 'dhrod0325', 'asdf', '히하하ㅏ하하ㅏ', '2012-09-13 20:52:26'), ('6', 'dhrod0325', 'zzz', 'asdfasdf', '2012-09-13 20:52:52'), ('7', 'dhrod0325', 'zzz', 'asdfasdf', '2012-09-13 20:54:13'), ('8', 'dhrod0325', 'zzz', 'asdfasdf', '2012-09-13 20:55:35'), ('9', 'dhrod0325', 'zzz', 'asdfasdf', '2012-09-13 20:57:06');
COMMIT;

-- ----------------------------
--  Table structure for `sa_member`
-- ----------------------------
DROP TABLE IF EXISTS `sa_member`;
CREATE TABLE `sa_member` (
  `no` int(20) NOT NULL AUTO_INCREMENT,
  `id` varchar(20) NOT NULL,
  `pw` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`no`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sa_member`
-- ----------------------------
BEGIN;
INSERT INTO `sa_member` VALUES ('4', 'dhrod0325', 'aaa', 'test');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

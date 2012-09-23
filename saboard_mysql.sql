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

 Date: 09/22/2012 18:26:35 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sa_board`
-- ----------------------------
DROP TABLE IF EXISTS `sa_board`;
CREATE TABLE `sa_board` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `board_id` varchar(200) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `title` varchar(200) NOT NULL,
  `content` longtext NOT NULL,
  `reg_date` varchar(40) NOT NULL,
  `has_file` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `fk_board_id` (`board_id`),
  CONSTRAINT `fk_board_id` FOREIGN KEY (`board_id`) REFERENCES `sa_board_tables` (`board_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sa_board_file`
-- ----------------------------
DROP TABLE IF EXISTS `sa_board_file`;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sa_board_reply`
-- ----------------------------
DROP TABLE IF EXISTS `sa_board_reply`;
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
  KEY `id` (`no`),
  CONSTRAINT `sa_board_reply_ibfk_1` FOREIGN KEY (`no`) REFERENCES `sa_board` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=83601 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sa_board_tables`
-- ----------------------------
DROP TABLE IF EXISTS `sa_board_tables`;
CREATE TABLE `sa_board_tables` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `board_id` varchar(200) NOT NULL,
  `board_nm` varchar(200) DEFAULT NULL,
  `board_desc` varchar(200) DEFAULT NULL,
  `theme` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `board_id` (`board_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sa_member`
-- ----------------------------
DROP TABLE IF EXISTS `sa_member`;
CREATE TABLE `sa_member` (
  `no` int(20) NOT NULL AUTO_INCREMENT,
  `id` varchar(20) NOT NULL,
  `pw` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `role` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`no`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : 127.0.0.1:3306
Source Database       : tb_event

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-09-11 10:11:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_event
-- ----------------------------
DROP TABLE IF EXISTS `t_event`;
CREATE TABLE `t_event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '事件记录id',
  `name` varchar(100) NOT NULL COMMENT '事件名称',
  `time` datetime NOT NULL COMMENT '事件发生时间',
  `type` varchar(100) DEFAULT NULL COMMENT '事件发生方式',
  PRIMARY KEY (`id`),
  KEY `idex_time` (`time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COMMENT='事件记录总表';

-- ----------------------------
-- Records of t_event
-- ----------------------------
INSERT INTO `t_event` VALUES ('1', 'ssssff', '2019-09-09 18:04:34', 'update');
INSERT INTO `t_event` VALUES ('2', 'sdfsdf', '2019-09-06 10:22:07', null);
INSERT INTO `t_event` VALUES ('3', 'sdfsdf', '2019-09-06 10:25:49', null);
INSERT INTO `t_event` VALUES ('4', 'sdfsdf', '2019-09-06 15:11:39', null);
INSERT INTO `t_event` VALUES ('5', 'sdfsdf', '2019-09-06 15:11:50', null);
INSERT INTO `t_event` VALUES ('6', 'sdfsdfdddddd', '2019-09-06 15:12:23', null);
INSERT INTO `t_event` VALUES ('7', 'sdfsdf', '2019-09-06 15:35:53', null);
INSERT INTO `t_event` VALUES ('8', 'sdfsdf', '2019-09-06 15:35:53', null);
INSERT INTO `t_event` VALUES ('9', 'sdfsdf', '2019-09-06 15:35:53', null);
INSERT INTO `t_event` VALUES ('10', 'sdfsdf', '2019-09-06 15:43:57', null);
INSERT INTO `t_event` VALUES ('11', 'sdfsdf', '2019-09-06 15:43:57', null);
INSERT INTO `t_event` VALUES ('12', 'sdfsdf', '2019-09-06 15:43:57', null);
INSERT INTO `t_event` VALUES ('13', 'sdfsdf', '2019-09-06 15:45:57', null);
INSERT INTO `t_event` VALUES ('14', 'sdfsdf', '2019-09-06 15:45:58', null);
INSERT INTO `t_event` VALUES ('15', 'sdfsdf', '2019-09-06 15:45:57', null);
INSERT INTO `t_event` VALUES ('16', 'sdfsdf', '2019-09-06 15:52:39', null);
INSERT INTO `t_event` VALUES ('17', 'sdfsdf', '2019-09-06 15:52:40', null);
INSERT INTO `t_event` VALUES ('18', 'sdfsdf', '2019-09-06 15:52:40', null);
INSERT INTO `t_event` VALUES ('19', 'sdfsdf', '2019-09-06 16:00:50', null);
INSERT INTO `t_event` VALUES ('20', 'sdfsdf', '2019-09-06 16:01:07', null);
INSERT INTO `t_event` VALUES ('21', 'sdfsdf', '2019-09-06 16:01:10', null);
INSERT INTO `t_event` VALUES ('22', 'sdfsdf', '2019-09-06 16:15:34', null);
INSERT INTO `t_event` VALUES ('23', 'sdfsdf', '2019-09-06 16:15:34', null);
INSERT INTO `t_event` VALUES ('24', 'sdfsdf', '2019-09-06 16:15:41', null);
INSERT INTO `t_event` VALUES ('25', 'sdfsdf', '2019-09-06 16:17:30', null);
INSERT INTO `t_event` VALUES ('26', 'sdfsdf', '2019-09-06 16:18:33', null);
INSERT INTO `t_event` VALUES ('27', 'sdfsdf', '2019-09-06 16:18:49', null);
INSERT INTO `t_event` VALUES ('28', 'sdfsdf', '2019-09-06 16:19:19', null);
INSERT INTO `t_event` VALUES ('29', 'sdfsdf', '2019-09-06 16:19:42', null);
INSERT INTO `t_event` VALUES ('30', 'sdfsdf', '2019-09-06 17:11:17', null);
INSERT INTO `t_event` VALUES ('31', 'sdfsdf', '2019-09-06 17:11:22', null);
INSERT INTO `t_event` VALUES ('32', 'sdfsdf', '2019-09-06 17:11:23', null);
INSERT INTO `t_event` VALUES ('33', 'sdfsdf', '2019-09-06 17:16:49', null);

-- ----------------------------
-- Table structure for t_event_content
-- ----------------------------
DROP TABLE IF EXISTS `t_event_content`;
CREATE TABLE `t_event_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '事件内容记录id',
  `event_id` bigint(20) NOT NULL COMMENT '事件id',
  `data_id` bigint(20) DEFAULT NULL COMMENT '操作数据唯一标识id',
  `data_name` varchar(200) DEFAULT NULL COMMENT '操作数据标识名称',
  `key_en` varchar(100) DEFAULT NULL COMMENT '修改字段英文名',
  `key_zh` varchar(200) DEFAULT NULL COMMENT '修改字段中文名',
  `ol_value` varchar(255) DEFAULT NULL COMMENT '修改前的值',
  `ne_value` varchar(255) DEFAULT NULL COMMENT '修改后的值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='事件操作内容记录表';

-- ----------------------------
-- Records of t_event_content
-- ----------------------------
INSERT INTO `t_event_content` VALUES ('1', '1', '10', null, 'rate', '????', '1.2%', '1.4%');
INSERT INTO `t_event_content` VALUES ('2', '2', '3', null, 'money', '??', '10', '20');
INSERT INTO `t_event_content` VALUES ('3', '3', '3', null, 'sdf', 'sff', 'gd', 'as');
INSERT INTO `t_event_content` VALUES ('4', '3', '3', null, 'name', 'ff', 'sss', 'fff');
INSERT INTO `t_event_content` VALUES ('5', '3', '4', null, 'dfgsdfg', 'dsfasd', 'sdf', 'asdf');

-- ----------------------------
-- Table structure for t_event_place
-- ----------------------------
DROP TABLE IF EXISTS `t_event_place`;
CREATE TABLE `t_event_place` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '事件发生地记录id',
  `event_id` bigint(20) DEFAULT NULL COMMENT '事件id',
  `place_page_code` varchar(255) DEFAULT NULL COMMENT '操作页面代码',
  `place_page_name` varchar(255) DEFAULT NULL COMMENT '操作页面名称',
  `place_platform_code` varchar(255) DEFAULT NULL COMMENT '操作平台代码',
  `place_platform_name` varchar(255) DEFAULT NULL COMMENT '操作平台名称',
  `place_url` varchar(255) DEFAULT NULL COMMENT '操作资源URL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='事件发生地记录表';

-- ----------------------------
-- Records of t_event_place
-- ----------------------------
INSERT INTO `t_event_place` VALUES ('1', '1', null, '????', null, null, null);
INSERT INTO `t_event_place` VALUES ('2', '2', 'aasdf', 'aasdf', 'sdf', null, 'adf');
INSERT INTO `t_event_place` VALUES ('3', '3', 'aasdf', 'aasdf', 'sdf', null, 'adf');

-- ----------------------------
-- Table structure for t_event_user
-- ----------------------------
DROP TABLE IF EXISTS `t_event_user`;
CREATE TABLE `t_event_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '事件用户记录id',
  `event_id` bigint(20) DEFAULT NULL COMMENT '事件id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `user_mobile` varchar(255) DEFAULT NULL COMMENT '用户手机号',
  `user_origin` varchar(255) DEFAULT NULL COMMENT '用户来源',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='事件用户体系表';

-- ----------------------------
-- Records of t_event_user
-- ----------------------------
INSERT INTO `t_event_user` VALUES ('1', '1', '1', 'sdfs', '138277445689', null);
INSERT INTO `t_event_user` VALUES ('2', '2', '2', 'fsdf', '13824411467', 'fsdf');
INSERT INTO `t_event_user` VALUES ('3', '3', '2', 'fsdf', '13824411467', 'fsdf');
INSERT INTO `t_event_user` VALUES ('4', '4', '2', 'fsdf', '13879969854', 'fsdf');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_login
-- ----------------------------
DROP TABLE IF EXISTS `t_user_login`;
CREATE TABLE `t_user_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user_login
-- ----------------------------

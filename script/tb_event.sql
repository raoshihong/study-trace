/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : 127.0.0.1:3306
Source Database       : tb_event

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-09-19 15:06:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_agent
-- ----------------------------
DROP TABLE IF EXISTS `t_agent`;
CREATE TABLE `t_agent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机号',
  `state` varchar(255) DEFAULT NULL COMMENT '状态',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_channel`;
CREATE TABLE `t_channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) DEFAULT NULL COMMENT '渠道名称',
  `state` varchar(255) DEFAULT NULL COMMENT '状态',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间',
  `update_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='渠道';

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
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COMMENT='事件记录总表';

-- ----------------------------
-- Table structure for t_event_content
-- ----------------------------
DROP TABLE IF EXISTS `t_event_content`;
CREATE TABLE `t_event_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '事件内容记录id',
  `event_id` bigint(20) NOT NULL COMMENT '事件id',
  `data_id` bigint(20) DEFAULT NULL COMMENT '操作数据唯一标识id',
  `data_name` varchar(200) DEFAULT NULL COMMENT '操作数据标识名称',
  `data_table_name` varchar(200) DEFAULT NULL COMMENT '主表名',
  `table_name` varchar(200) DEFAULT NULL COMMENT '操作表名',
  `table_name_desc` varchar(200) DEFAULT NULL COMMENT '表名描述',
  `data_action_type` varchar(255) DEFAULT NULL COMMENT '数据操作类型update,insert,delete',
  `record_id` bigint(20) DEFAULT NULL COMMENT '操作表记录id',
  `before_body` text COMMENT '修改前的实体',
  `after_body` text COMMENT '修改后的实体',
  `key_en` varchar(100) DEFAULT NULL COMMENT '修改字段英文名',
  `key_zh` varchar(200) DEFAULT NULL COMMENT '修改字段中文名',
  `ol_value` text COMMENT '修改前的值',
  `ne_value` text COMMENT '修改后的值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8mb4 COMMENT='事件操作内容记录表';

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
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COMMENT='事件发生地记录表';

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
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COMMENT='事件用户体系表';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL COMMENT '状态',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

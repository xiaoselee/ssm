/*
Navicat MySQL Data Transfer

Source Server         : vboxMysql
Source Server Version : 50718
Source Host           : 192.168.56.101:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-07-17 18:08:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for browser
-- ----------------------------
DROP TABLE IF EXISTS `browser`;
CREATE TABLE `browser` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(10) DEFAULT '1',
  `ip` varchar(32) DEFAULT NULL COMMENT '客户端ip地址',
  `mac` varchar(50) DEFAULT NULL COMMENT '客户端mac地址',
  `url` varchar(1000) DEFAULT NULL COMMENT '浏览页面',
  `date` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of browser
-- ----------------------------
INSERT INTO `browser` VALUES ('1', '1', '192.168.0.157', 'FC:AA:14:BE:CB:60', 'http://127.0.0.1:8020/ky/counttest.html', '2017-06-16 10:51:15');
INSERT INTO `browser` VALUES ('2', '1', '192.168.0.157', 'FC:AA:14:BE:CB:60', 'http://127.0.0.1:8020/ky/counttest.html', '2017-06-16 11:07:33');
INSERT INTO `browser` VALUES ('3', '1', '192.168.0.157', 'FC:AA:14:BE:CB:60', 'http://127.0.0.1:8020/ky/counttest.html', '2017-06-16 11:07:37');
INSERT INTO `browser` VALUES ('4', '1', '192.168.0.157', 'FC:AA:14:BE:CB:60', 'http://127.0.0.1:8020/ky/counttest.html', '2017-06-16 11:08:29');
INSERT INTO `browser` VALUES ('5', '1', '192.168.0.51', 'FC:AA:14:BE:CE:0C', 'http://192.168.0.51:8020/vue/new_file.html', '2017-06-16 11:11:18');
INSERT INTO `browser` VALUES ('6', '1', '192.168.0.157', 'FC:AA:14:BE:CB:60', 'http://127.0.0.1:8020/ky/counttest.html', '2017-06-16 11:18:24');
INSERT INTO `browser` VALUES ('7', '1', '192.168.0.157', 'FC:AA:14:BE:CB:60', 'http://192.168.0.157:8020/ky/counttest.html', '2017-06-16 14:16:01');
INSERT INTO `browser` VALUES ('8', '1', '192.168.0.157', 'FC:AA:14:BE:CB:60', 'http://127.0.0.1:8020/ky/counttest.html', '2017-06-16 14:16:28');
INSERT INTO `browser` VALUES ('9', '1', '192.168.0.157', 'FC:AA:14:BE:CB:60', 'http://192.168.0.157:8020/ky/counttest.html', '2017-06-16 14:16:59');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '中文名称',
  `type` varchar(10) DEFAULT NULL COMMENT '类型：',
  `pid` varchar(50) NOT NULL DEFAULT '0' COMMENT '父级菜单id',
  `order` int(20) DEFAULT NULL COMMENT '排序',
  `icon` varchar(200) DEFAULT NULL COMMENT '图标',
  `url` varchar(200) DEFAULT NULL,
  `status` varchar(10) DEFAULT '0',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `creater` varchar(50) DEFAULT NULL,
  `reviser` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '测试0级菜单1', null, '0', '0', 'lnr-home', null, null, '2017-05-31 09:10:52', '2017-05-31 09:10:55', '', '');
INSERT INTO `menu` VALUES ('2', '系统管理', null, '0', '1', 'lnr-alarm', null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('3', '测试1级菜单1-1', null, '1', '1', null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('4', '角色管理', null, '2', '1', 'lnr-alarm', '../view/role/role.html', null, null, null, null, null);
INSERT INTO `menu` VALUES ('6', '权限管理', null, '2', '5', 'lnr-alarm', null, '0', null, null, null, null);
INSERT INTO `menu` VALUES ('7', '菜单管理', null, '2', '10', 'lnr-alarm', '../view/menu/list.html', '0', '2017-06-02 09:17:47', '2017-06-02 09:17:52', null, null);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'read', '2017-05-27 15:26:12', '2017-05-27 15:26:17');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `remarks` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '2017-05-27 15:24:14', '2017-05-27 15:24:17', null);
INSERT INTO `role` VALUES ('2', 'user', '2017-06-02 16:08:13', '2017-06-02 16:08:13', '????');
INSERT INTO `role` VALUES ('3', 'teacher', '2017-06-02 16:25:37', '2017-06-02 16:25:37', '教师');
INSERT INTO `role` VALUES ('11', 'dd', '2017-06-02 17:06:11', '2017-06-02 17:06:11', 'dfsf');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `r_id` int(50) DEFAULT NULL,
  `m_id` int(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` varchar(50) NOT NULL,
  `rid` varchar(50) NOT NULL,
  `pid` varchar(50) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1', '2017-05-27 18:38:45', '2017-05-27 18:38:49');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'candy', '111111');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(50) NOT NULL,
  `uid` varchar(50) NOT NULL,
  `rid` varchar(50) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1', '2017-05-27 15:26:53', '2017-05-27 15:26:57');

/*
 Navicat Premium Data Transfer

 Source Server         : forSSM
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : edua

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 09/12/2019 12:46:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ano` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员工号',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `pass` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '666666' COMMENT '管理员登录密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员邮箱',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员姓名',
  `logo` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/static/picture/a_logo.png' COMMENT '管理员头像',
  `intro` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '300字以内简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '1', '123456', '666666', '123@qq.com', 'mqb', '/static/picture/a_logo.png', NULL);
INSERT INTO `admin` VALUES (2, '161', '654321', '123456', NULL, 'qe', NULL, NULL);
INSERT INTO `admin` VALUES (3, '2131', '123131', '666666', 'jhkd@163.com', 'hff', NULL, 'jh1hg23');
INSERT INTO `admin` VALUES (4, '777', '12121212', '666666', '1213@qq.com', 'test', NULL, 'none');
INSERT INTO `admin` VALUES (5, '029', '98729812', '666666', '91872@172.com', 'test2', NULL, 'jsd');
INSERT INTO `admin` VALUES (6, '12231', '1213422', 'qweqd', 'adsa@12.com', 'qweq', NULL, 'wddsa');
INSERT INTO `admin` VALUES (7, '657', '435345', '666666', '34253@er.vom', 'cvbf', NULL, 'sfsd');
INSERT INTO `admin` VALUES (12, '1231', '1231231', '13123', '12312@qeq.com', 'tree', NULL, '2131');

-- ----------------------------
-- Table structure for c3p0testtable
-- ----------------------------
DROP TABLE IF EXISTS `c3p0testtable`;
CREATE TABLE `c3p0testtable`  (
  `a` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `cid` int(11) NULL DEFAULT NULL COMMENT '课程id',
  `mark` tinyint(4) NULL DEFAULT 1 COMMENT '课程性质，0：必修  1：选修',
  `score` int(11) NULL DEFAULT NULL COMMENT '课程得分',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tid`(`cid`) USING BTREE,
  INDEX `sid`(`sid`) USING BTREE,
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `class_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程报名' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NULL DEFAULT NULL COMMENT 'class id',
  `state` int(11) NULL DEFAULT NULL COMMENT '教室状态',
  `intro` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教室描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tid`(`cid`) USING BTREE,
  CONSTRAINT `classroom_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教室表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state` tinyint(4) NULL DEFAULT 1 COMMENT '系统状态，1：正常运行  2: 系统维修，关站，',
  `config` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '系统配置信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cno` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程编号',
  `tid` int(11) NULL DEFAULT NULL COMMENT '开课教师id',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '开课日期',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `intro` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '300字以内课程简介',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tid`(`tid`) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '开课信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '1912010001', 1, '2019-12-02 13:26:31', '高级语言', '这是一门计算机的高级语言课程');
INSERT INTO `course` VALUES (2, '1912010002', 3, '2019-12-02 13:27:26', '地址勘测', '这是一门土木的地址勘测课程');
INSERT INTO `course` VALUES (3, '1912111', 2, NULL, NULL, 'test,test');
INSERT INTO `course` VALUES (4, '1912111', 2, NULL, NULL, 'test,test');
INSERT INTO `course` VALUES (5, '1912111', 2, NULL, NULL, 'test,test');
INSERT INTO `course` VALUES (6, '1231321', 3, NULL, NULL, 'test,test');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state` int(11) NULL DEFAULT NULL COMMENT '公告状态',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公告信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for profession
-- ----------------------------
DROP TABLE IF EXISTS `profession`;
CREATE TABLE `profession`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业名称',
  `pno` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业编号',
  `intro` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '300字以内简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '专业表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of profession
-- ----------------------------
INSERT INTO `profession` VALUES (11, '计算机科学与技术', '36', '这是一个计算机专业');
INSERT INTO `profession` VALUES (12, '土木工程', '27', '这是一个土木工程专业');
INSERT INTO `profession` VALUES (13, '网络安全', '35', '这是网络安全专业');
INSERT INTO `profession` VALUES (14, '药材识别', '52', '这是一个药材识别专业');
INSERT INTO `profession` VALUES (15, '药材识别', '52', '这是一个药材识别专业');
INSERT INTO `profession` VALUES (16, '食品安全检测', '78', '这是一个食品安全检测专业');
INSERT INTO `profession` VALUES (17, '食品日期检测', '79', '这是一个食品日期检测专业');
INSERT INTO `profession` VALUES (18, '飞机制造', '85', '这是一个飞机制造专业');
INSERT INTO `profession` VALUES (19, '坦克制造', '86', '这是一个坦克制造专业');
INSERT INTO `profession` VALUES (21, 'test', 'test', 'test');
INSERT INTO `profession` VALUES (22, '潜水艇制造', '87', '这是一个潜水艇制造专业');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生学号',
  `pid` int(11) NULL DEFAULT NULL COMMENT '学生专业id',
  `pass` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '666666' COMMENT '学生登录密码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生邮箱',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `logo` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/static/picture/s_logo.png' COMMENT '学生头像',
  `intro` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '300字以内简介',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `profession` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '16136101', 11, '666666', '10010', '36101@qq.com', 'jsj101', '/static/picture/s_logo.png', '这是计算机的第一个学生');
INSERT INTO `student` VALUES (2, '16136102', 11, '666666', '10011', '36102@163.com', 'jsj102', '/static/picture/s_logo.png', '这是计算机的第二个学生');
INSERT INTO `student` VALUES (3, '16127101', 12, '666666', '10086', '27101@qq.com', 'tmcg101', '/static/picture/s_logo.png', '这是土木工程的第一个学生');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jno` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师工号',
  `profession` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `pass` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '666666' COMMENT '教师登录密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师邮箱',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `logo` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/static/picture/t_logo.png' COMMENT '教室头像',
  `intro` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '300字以内简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教师表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '12031501', '计算机科学与技术', '18828282828', '666666', 'test@163.com', 'tea_1', '/static/picture/t_logo.png', '这是一个2012年3月15入职的，计算机科学与技术专业的老师');
INSERT INTO `teacher` VALUES (2, '20121201', 'test_pro', '123456', 'test_pass', 'test@email.cm', 'test_name1', '/static/picture/t_logo.png', 'test_intro');
INSERT INTO `teacher` VALUES (3, '12041302', '土木工程', '17382764563', '666666', 'test@qq.com', 'tea_2', '/static/picture/t_logo.png', '这是一个土木工程的老师');

SET FOREIGN_KEY_CHECKS = 1;

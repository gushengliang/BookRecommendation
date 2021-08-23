/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : book_recommendation

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 30/06/2021 13:06:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` bigint NOT NULL,
  `username` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名（学生唯一标识符）',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '管理员姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '书本id',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书名',
  `picture` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '图片连接',
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '书本简介',
  `page_view` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '书本浏览量',
  `attention` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '关注数',
  `avg_level` decimal(2, 1) UNSIGNED NOT NULL DEFAULT 0.0 COMMENT '平均评分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (3, '数据结构', 'ds.png', '数据结构（大二）', 375, 1, 4.2);
INSERT INTO `book` VALUES (4, '算法设计与分析', 'cal.jpg', '算法设计与分析（大三）', 68, 1, 4.7);
INSERT INTO `book` VALUES (5, '计算机网络', 'net.jpg', '计算机网络（大二）', 67, 0, 0.0);
INSERT INTO `book` VALUES (6, '操作系统', 'os.jpg', '操作系统（大二）', 169, 0, 0.0);
INSERT INTO `book` VALUES (7, '数据结构（Python）', 'ds-python.jpg', '使用Python编写的数据结构教材', 2839, 1, 5.0);
INSERT INTO `book` VALUES (8, '编译原理', 'byyl.jpg', '了解程序编译的原理和过程', 251, 0, 0.0);
INSERT INTO `book` VALUES (9, '数据结构（C++）', 'ds-c++.jpg', '使用C++编写的数据结构教材', 1027, 0, 0.0);
INSERT INTO `book` VALUES (10, '设计模式', 'sjms.jpg', '设计模式', 101, 0, 0.0);
INSERT INTO `book` VALUES (11, '体系结构', 'txjg.jpg', '体系结构', 84, 0, 0.0);
INSERT INTO `book` VALUES (12, 'C语言', 'c.jpg', 'Hello World!', 1534, 0, 0.0);
INSERT INTO `book` VALUES (13, 'Python教程', 'python.jpg', 'Python教程（使用PyCharm）', 1743, 0, 0.0);
INSERT INTO `book` VALUES (14, 'C++教程', 'c++.jpg', 'C++教程（使用Visual Studio）', 2825, 1, 4.0);

-- ----------------------------
-- Table structure for book_course
-- ----------------------------
DROP TABLE IF EXISTS `book_course`;
CREATE TABLE `book_course`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `book_id` bigint UNSIGNED NOT NULL COMMENT '书本id',
  `course_id` bigint UNSIGNED NOT NULL COMMENT '课程id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book_course
-- ----------------------------
INSERT INTO `book_course` VALUES (1, 3, 4);
INSERT INTO `book_course` VALUES (2, 4, 5);
INSERT INTO `book_course` VALUES (3, 5, 2);
INSERT INTO `book_course` VALUES (4, 6, 3);
INSERT INTO `book_course` VALUES (5, 7, 4);
INSERT INTO `book_course` VALUES (6, 8, 8);
INSERT INTO `book_course` VALUES (7, 9, 4);
INSERT INTO `book_course` VALUES (8, 10, 7);
INSERT INTO `book_course` VALUES (9, 11, 7);
INSERT INTO `book_course` VALUES (10, 12, 6);
INSERT INTO `book_course` VALUES (11, 13, 6);
INSERT INTO `book_course` VALUES (12, 14, 6);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '课程名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '软件工程');
INSERT INTO `course` VALUES (2, '计算机网络');
INSERT INTO `course` VALUES (3, '操作系统');
INSERT INTO `course` VALUES (4, '数据结构');
INSERT INTO `course` VALUES (5, '算法设计与分析');
INSERT INTO `course` VALUES (6, '程序设计基础');
INSERT INTO `course` VALUES (7, '软件设计模式与体系结构');
INSERT INTO `course` VALUES (8, '编译原理');
INSERT INTO `course` VALUES (9, 'Python');
INSERT INTO `course` VALUES (10, 'C++');

-- ----------------------------
-- Table structure for evaluation
-- ----------------------------
DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE `evaluation`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `book_id` bigint UNSIGNED NOT NULL COMMENT '被评价的书本id',
  `student_id` bigint UNSIGNED NOT NULL COMMENT '评价人id',
  `level` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '评分等级（5分制）',
  `comment` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容（可以为空）',
  `status` tinyint UNSIGNED NULL DEFAULT 0 COMMENT '评论状态（已发表、已删除、已违规）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of evaluation
-- ----------------------------
INSERT INTO `evaluation` VALUES (1, 3, 1, 4, NULL, 1);
INSERT INTO `evaluation` VALUES (2, 3, 2, 3, NULL, 1);
INSERT INTO `evaluation` VALUES (3, 3, 3, 5, NULL, 1);
INSERT INTO `evaluation` VALUES (4, 4, 1, 5, NULL, 1);
INSERT INTO `evaluation` VALUES (5, 4, 2, 4, NULL, 1);
INSERT INTO `evaluation` VALUES (6, 4, 3, 5, NULL, 1);
INSERT INTO `evaluation` VALUES (7, 3, 1, 5, '啊实打实', 1);
INSERT INTO `evaluation` VALUES (8, 7, 1, 5, '666', 1);
INSERT INTO `evaluation` VALUES (9, 3, 1, 4, 'sdasdaasdasasd', 1);
INSERT INTO `evaluation` VALUES (10, 14, 1, 4, '666', 1);

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '专业名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES (1, '软件工程');
INSERT INTO `major` VALUES (2, '大数据');
INSERT INTO `major` VALUES (3, '物联网');
INSERT INTO `major` VALUES (4, '无损检测');

-- ----------------------------
-- Table structure for major_course
-- ----------------------------
DROP TABLE IF EXISTS `major_course`;
CREATE TABLE `major_course`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `major_id` bigint UNSIGNED NOT NULL COMMENT '专业id',
  `course_id` bigint UNSIGNED NOT NULL COMMENT '课程id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of major_course
-- ----------------------------
INSERT INTO `major_course` VALUES (1, 1, 1);
INSERT INTO `major_course` VALUES (2, 1, 2);
INSERT INTO `major_course` VALUES (3, 1, 3);
INSERT INTO `major_course` VALUES (4, 1, 4);
INSERT INTO `major_course` VALUES (5, 1, 5);
INSERT INTO `major_course` VALUES (6, 1, 6);
INSERT INTO `major_course` VALUES (7, 1, 7);
INSERT INTO `major_course` VALUES (8, 1, 8);
INSERT INTO `major_course` VALUES (9, 2, 1);
INSERT INTO `major_course` VALUES (10, 2, 2);
INSERT INTO `major_course` VALUES (11, 2, 3);
INSERT INTO `major_course` VALUES (12, 2, 4);
INSERT INTO `major_course` VALUES (13, 2, 9);
INSERT INTO `major_course` VALUES (14, 3, 5);
INSERT INTO `major_course` VALUES (15, 3, 6);
INSERT INTO `major_course` VALUES (16, 3, 7);
INSERT INTO `major_course` VALUES (17, 3, 8);
INSERT INTO `major_course` VALUES (18, 3, 10);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '消息题目',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '消息内容',
  `type` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '消息类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '加油读书', '软件人，软件魂，软件都是人上人。奥利给！', 0);
INSERT INTO `message` VALUES (3, '666', '牛啊牛啊', 0);
INSERT INTO `message` VALUES (4, '555', '强啊', 0);
INSERT INTO `message` VALUES (5, '33', '牛', 0);
INSERT INTO `message` VALUES (6, '《数据结构》阅读提醒', '你已经订阅了《数据结构》的阅读提醒，记得多多阅读哦~', 0);
INSERT INTO `message` VALUES (7, '《数据结构（Python）》阅读提醒', '你已经订阅了《数据结构（Python）》的阅读提醒，记得多多阅读哦~', 0);
INSERT INTO `message` VALUES (8, '666', '777', 0);
INSERT INTO `message` VALUES (9, '《算法设计与分析》阅读提醒', '你已经订阅了《算法设计与分析》的阅读提醒，记得多多阅读哦~', 0);

-- ----------------------------
-- Table structure for prohibited_word
-- ----------------------------
DROP TABLE IF EXISTS `prohibited_word`;
CREATE TABLE `prohibited_word`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `content` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '违禁词名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of prohibited_word
-- ----------------------------
INSERT INTO `prohibited_word` VALUES (1, '你妈');
INSERT INTO `prohibited_word` VALUES (2, '他妈');
INSERT INTO `prohibited_word` VALUES (3, '共产党');

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '学校名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES (1, '南昌航空大学');
INSERT INTO `school` VALUES (2, '北京大学');
INSERT INTO `school` VALUES (3, '清华大学');

-- ----------------------------
-- Table structure for school_major
-- ----------------------------
DROP TABLE IF EXISTS `school_major`;
CREATE TABLE `school_major`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `school_id` bigint UNSIGNED NOT NULL COMMENT '学校id',
  `major_id` bigint UNSIGNED NOT NULL COMMENT '专业id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of school_major
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名（学生唯一标识符）',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `school_id` bigint UNSIGNED NOT NULL COMMENT '学校id',
  `major_id` bigint UNSIGNED NOT NULL COMMENT '专业id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '18201205', '铁汁', 1, 1);
INSERT INTO `student` VALUES (2, 'bbb', 'Alex', 2, 1);

-- ----------------------------
-- Table structure for student_book
-- ----------------------------
DROP TABLE IF EXISTS `student_book`;
CREATE TABLE `student_book`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` bigint UNSIGNED NOT NULL COMMENT '学校id',
  `book_id` bigint UNSIGNED NOT NULL COMMENT '书本id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_book
-- ----------------------------
INSERT INTO `student_book` VALUES (12, 1, 4);
INSERT INTO `student_book` VALUES (14, 1, 7);
INSERT INTO `student_book` VALUES (17, 1, 3);
INSERT INTO `student_book` VALUES (18, 1, 14);

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` bigint UNSIGNED NOT NULL COMMENT '学校id',
  `course_id` bigint UNSIGNED NOT NULL COMMENT '课程id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES (31, 1, 1);
INSERT INTO `student_course` VALUES (32, 1, 8);
INSERT INTO `student_course` VALUES (33, 1, 6);

-- ----------------------------
-- Table structure for student_message
-- ----------------------------
DROP TABLE IF EXISTS `student_message`;
CREATE TABLE `student_message`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` bigint UNSIGNED NOT NULL COMMENT '学校id',
  `message_id` bigint UNSIGNED NOT NULL COMMENT '消息id',
  `is_read` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已读',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_message
-- ----------------------------
INSERT INTO `student_message` VALUES (1, 1, 1, 1);
INSERT INTO `student_message` VALUES (2, 2, 1, 0);
INSERT INTO `student_message` VALUES (5, 1, 3, 1);
INSERT INTO `student_message` VALUES (6, 2, 3, 0);
INSERT INTO `student_message` VALUES (7, 1, 4, 1);
INSERT INTO `student_message` VALUES (8, 2, 4, 0);
INSERT INTO `student_message` VALUES (9, 1, 5, 1);
INSERT INTO `student_message` VALUES (10, 2, 5, 0);
INSERT INTO `student_message` VALUES (11, 1, 6, 1);
INSERT INTO `student_message` VALUES (12, 1, 7, 1);
INSERT INTO `student_message` VALUES (13, 1, 6, 1);
INSERT INTO `student_message` VALUES (14, 1, 6, 1);
INSERT INTO `student_message` VALUES (15, 1, 8, 1);
INSERT INTO `student_message` VALUES (16, 2, 8, 0);
INSERT INTO `student_message` VALUES (17, 1, 9, 0);

-- ----------------------------
-- Table structure for unexamined_book
-- ----------------------------
DROP TABLE IF EXISTS `unexamined_book`;
CREATE TABLE `unexamined_book`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `picture` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `is_examined` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已过审',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of unexamined_book
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名（用户唯一标识符）',
  `password` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'e10adc3949ba59abbe56e057f20f883e' COMMENT '用户密码',
  `type` tinyint UNSIGNED NOT NULL COMMENT '用户类型',
  `is_active` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '用户是否已激活',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '18201205', '123ec0bcc9eb514754452ace2a0ca45a', 1, 1);
INSERT INTO `user` VALUES (2, 'geigeige', '123456', 1, 1);
INSERT INTO `user` VALUES (3, 'heiheihe', 'f379eaf3c831b04de153469d1bec345e', 1, 1);

SET FOREIGN_KEY_CHECKS = 1;

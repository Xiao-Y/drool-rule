/*
 Navicat Premium Data Transfer

 Source Server         : 119.23.27.78
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 119.23.27.78:3308
 Source Schema         : rule-engine

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 10/01/2021 18:13:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for re_rule_info
-- ----------------------------
DROP TABLE IF EXISTS `re_rule_info`;
CREATE TABLE `re_rule_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `scene_id` bigint NULL DEFAULT NULL COMMENT '场景id，一个场景对应多个规则，一个场景对应一个业务场景，一个场景对应一个kmodule',
  `rule_gen_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则生成器的名称',
  `rule_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '规则内容，既drl文件内容',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `valid_ind` bit(1) NULL DEFAULT NULL COMMENT '是否有效',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

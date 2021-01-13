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

 Date: 13/01/2021 16:28:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for re_rule_config_detail
-- ----------------------------
DROP TABLE IF EXISTS `re_rule_config_detail`;
CREATE TABLE `re_rule_config_detail`  (
  `id` bigint(0) NOT NULL,
  `rule_config_id` bigint(0) NULL DEFAULT NULL,
  `jump_point` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳点条件，1-固定，2-范围',
  `commission_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提成类型：1-固定，2-点数',
  `house_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品类型：1-住宅，2-小于150w 商铺，3-150-300w 商铺，4-300w 以上商铺',
  `start_date` datetime(0) NULL DEFAULT NULL COMMENT '考核开始时间',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '考核结束时间',
  `sales_amount` decimal(15, 2) NULL DEFAULT NULL COMMENT '销售金额',
  `num_min` int(0) NULL DEFAULT NULL COMMENT '成交套数-最小值',
  `num_max` int(0) NULL DEFAULT NULL COMMENT '成交套数-最大值',
  `fixed_amount` decimal(15, 2) NULL DEFAULT NULL COMMENT '提成固定金额',
  `commission` decimal(15, 2) NULL DEFAULT NULL COMMENT '如果是固定，放入金额; 如果是点数，放入小数',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `valid_ind` bit(1) NULL DEFAULT NULL COMMENT '是否有效',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of re_rule_config_detail
-- ----------------------------
INSERT INTO `re_rule_config_detail` VALUES (1, 1, '1', '1', '1', '2021-01-10 15:39:53', '2021-01-14 15:39:57', 10000.00, NULL, NULL, 200.00, 20.00, b'0', b'1', '2021-01-13 15:41:02', '2021-01-13 15:41:04');

SET FOREIGN_KEY_CHECKS = 1;

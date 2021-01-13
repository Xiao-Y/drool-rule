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

 Date: 13/01/2021 16:28:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for re_rule_config
-- ----------------------------
DROP TABLE IF EXISTS `re_rule_config`;
CREATE TABLE `re_rule_config`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(0) NULL DEFAULT NULL COMMENT '分组id，一个分组对应多个规则，一个分组对应一个业务分组，一个分组对应一个kmodule',
  `rule_gen_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则生成器的名称',
  `rule_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '规则内容，既drl文件内容',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `valid_ind` bit(1) NULL DEFAULT NULL COMMENT '是否有效',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of re_rule_config
-- ----------------------------
INSERT INTO `re_rule_config` VALUES (1, 361689808331870208, '', 'package DeveloperSettlementParam;\r\n\r\nimport com.github.xiaoy.droolrule.param.DeveloperSettlementParam;\r\nimport java.math.BigDecimal;\r\nimport java.math.RoundingMode;\r\nimport java.time.LocalDate;\r\nimport java.time.format.DateTimeFormatter;\r\n\r\nglobal org.slf4j.Logger logger\r\n\r\n// 处理时间的function\r\nfunction LocalDate checkDate(String et){\r\n    return LocalDate.parse(et, DateTimeFormatter.ofPattern(\"yyyy-MM-dd\"));\r\n}\r\n\r\nrule \"固定-固定-1-20\"\r\n    when\r\n        $p : DeveloperSettlementParam(\r\n            jumpPoint == \"1\"\r\n            && commissionType == \"1\"\r\n            && houseType == \"1\"\r\n            && checkDate(assessmentDate).compareTo(checkDate(\"2021-01-10\")) >= 0\r\n            && checkDate(assessmentDate).compareTo(checkDate(\"2021-01-14\")) <= 0\r\n            && salesAmount >= 10000\r\n        );\r\n    then\r\n        BigDecimal sumAmount = BigDecimal.ZERO;\r\n        sumAmount = new BigDecimal(20).add(new BigDecimal(200));\r\n        $p.getResult().add(sumAmount.setScale(2, RoundingMode.HALF_UP));\r\n        logger.info(\"固定-固定-1-20：{}\",$p);\r\nend\r\n\r\n', b'0', b'1', '2021-01-12 08:02:19', '2021-01-12 08:02:19');

SET FOREIGN_KEY_CHECKS = 1;

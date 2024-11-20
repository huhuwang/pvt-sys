SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for common_test_case_base
-- ----------------------------
DROP TABLE IF EXISTS `common_test_case_base`;
CREATE TABLE `common_test_case_base`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '描述',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '简述',
  `expected_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '期待结果',
  `category` int NULL DEFAULT NULL COMMENT '1. SIT 2.UAT 4.RT 8.PVT 16.WEB 32.IPAD',
  `priority` tinyint NULL DEFAULT NULL COMMENT '1. Urgent 2.Highest 3 High 4.low 5Lowest 6.Blocker 7.Critical 8Major 9.Minor 10.Trivial',
  `type` tinyint NULL DEFAULT NULL COMMENT '1. related to story 2. common  ',
  `row_height` int NULL DEFAULT NULL COMMENT '行高',
  `rt_flow` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '如果是RT case,对应的RT flow',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改者',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态  1.启用   2.禁用  3.完成',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for deployment
-- ----------------------------
DROP TABLE IF EXISTS `deployment`;
CREATE TABLE `deployment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `deployment_date` date NOT NULL COMMENT '发布日期',
  `deployment_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '发布名称',
  `application_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '应用名称 eg. IRECRUIT2.0',
  `template_id` int NULL DEFAULT NULL COMMENT 'RT 模板',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态  1.启用   2.禁用  3.完成',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_idx`(`create_time` ASC) USING BTREE,
  INDEX `template_idx`(`template_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for document
-- ----------------------------
DROP TABLE IF EXISTS `document`;
CREATE TABLE `document`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `document_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '文件名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '描述',
  `result_id` int NULL DEFAULT NULL COMMENT 'result id',
  `original_size` bigint NULL DEFAULT NULL COMMENT '原始尺寸',
  `scale_size` bigint NULL DEFAULT NULL COMMENT '处理之后的尺寸',
  `document_type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '文件类型',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片地址',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改者',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态  1.启用   2.禁用  3.完成',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `result_idx`(`result_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kv_constants
-- ----------------------------
DROP TABLE IF EXISTS `kv_constants`;
CREATE TABLE `kv_constants`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `key_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'key 名字',
  `key_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'key 值',
  `template_id` int NULL DEFAULT NULL COMMENT 'templeted',
  `constant_type` int NULL DEFAULT NULL COMMENT '1. flow step  2. flow data',
  `parent_id` int NULL DEFAULT NULL COMMENT '父级id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改者',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态  1.启用   2.禁用  3.完成',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_idx`(`parent_id` ASC) USING BTREE,
  INDEX `template_idx`(`template_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rt_summary_template
-- ----------------------------
DROP TABLE IF EXISTS `rt_summary_template`;
CREATE TABLE `rt_summary_template`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `template_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '模板名称',
  `flow_number` int NOT NULL COMMENT 'flow 的数量',
  `application` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '所属的app',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改者',
  `status` tinyint NULL DEFAULT NULL COMMENT '状态  1.启用   2.禁用  3.完成',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for test_case
-- ----------------------------
DROP TABLE IF EXISTS `test_case`;
CREATE TABLE `test_case`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ticket_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'ticket number',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '描述',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '简述',
  `expected_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '期待结果',
  `priority` tinyint NULL DEFAULT NULL COMMENT '1. Urgent 2.Highest 3 High 4.low 5Lowest 6.Blocker 7.Critical 8Major 9.Minor 10.Trivial',
  `type` tinyint NULL DEFAULT NULL COMMENT '1. related to story 2. common  ',
  `row_height` int NULL DEFAULT NULL COMMENT '行高',
  `base_case_from` int NULL DEFAULT NULL COMMENT '来源',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改者',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态  1.启用   2.禁用  3.完成',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ticket_no_idx`(`ticket_no` ASC) USING BTREE,
  INDEX `case_from_idx`(`base_case_from` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for test_result
-- ----------------------------
DROP TABLE IF EXISTS `test_result`;
CREATE TABLE `test_result`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `case_id` int NOT NULL COMMENT 'case id',
  `category` int NOT NULL COMMENT '1. SIT 2.UAT 4.RT 8.PVT 16.WEB 32.IPAD',
  `step` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '步骤',
  `test_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '测试数据',
  `actual_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '实际结果',
  `result` tinyint NULL DEFAULT NULL COMMENT '1. pass 2. no pass',
  `rt_flow` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '如果是RT case,对应的RT flow',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改者',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态  1.启用   2.禁用  3.完成',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `case_id_idx`(`case_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `deployment_id` int NULL DEFAULT NULL COMMENT '所属的deployment',
  `ticket_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'ticket number',
  `ticket_title` varchar(164) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'ticket 标题',
  `ticket_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '对应的url',
  `ticket_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'ticket 类型  （story/pro issue）',
  `type` tinyint NULL DEFAULT NULL COMMENT '类型  1. 手动创建  2.自动创建的common',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '修改人',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态  1.启用   2.禁用  3.完成',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `deployment_ticket_idx`(`deployment_id` ASC, `ticket_no` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

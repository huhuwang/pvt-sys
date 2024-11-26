/*
 Navicat Premium Dump SQL

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 90001 (9.0.1)
 Source Host           : localhost:3306
 Source Schema         : pvt

 Target Server Type    : MySQL
 Target Server Version : 90001 (9.0.1)
 File Encoding         : 65001

 Date: 26/11/2024 19:00:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pvt_permission
-- ----------------------------
DROP TABLE IF EXISTS `pvt_permission`;
CREATE TABLE `pvt_permission`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '注释',
  `permission_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `permission_code` varchar(46) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '权限编号',
  `permission_url` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '功能url',
  `permission_describe` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '新建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `IDX_CODE`(`permission_code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pvt_permission
-- ----------------------------
INSERT INTO `pvt_permission` VALUES (2, '新增/修改发布', 'add-deployment', '/deployment/add', '新增/修改一个发布事项', '2024-11-23 17:28:08', '2024-11-23 17:28:08');
INSERT INTO `pvt_permission` VALUES (3, '删除发布', 'delete-deployment', '/deployment/delete/{id}', '删除一个发布事项', '2024-11-23 17:28:08', '2024-11-23 17:28:08');
INSERT INTO `pvt_permission` VALUES (4, '查看发布列表', 'list-deployment', '/deployment/page', '查看发布列表', '2024-11-23 17:28:08', '2024-11-23 17:28:08');
INSERT INTO `pvt_permission` VALUES (5, '查询发布', 'query-deployment', '/deployment/query/{id}', '查看发布详情', '2024-11-23 17:28:08', '2024-11-23 17:28:08');
INSERT INTO `pvt_permission` VALUES (6, '新增/修改ticket', 'add-ticket', '/ticket/add', '新增/修改ticket', '2024-11-23 17:28:08', '2024-11-23 17:28:08');
INSERT INTO `pvt_permission` VALUES (7, '查询ticket列表', 'list-ticket', '/ticket/queryTicketByDeployment/{deploymentId}', '查询ticket列表', '2024-11-23 17:28:08', '2024-11-23 17:28:08');
INSERT INTO `pvt_permission` VALUES (8, '删除ticket', 'delete-ticket', '/ticket/delete/{id}', '删除一个ticket', '2024-11-23 17:28:08', '2024-11-23 17:28:08');
INSERT INTO `pvt_permission` VALUES (9, '查询ticket(ticket no)', 'query-ticket-no', '/ticket/query/{ticketNo}/{deploymentId}', '根据ticket number 和 发布id查询', '2024-11-23 17:28:08', '2024-11-23 17:30:33');
INSERT INTO `pvt_permission` VALUES (10, '查询ticket详情', 'query-ticket', '/ticket/query/{id}', '根据ticket id 查询', '2024-11-23 17:28:08', '2024-11-23 17:30:33');
INSERT INTO `pvt_permission` VALUES (11, '新增case', 'add-case', '/case/add', '新增测试用例', '2024-11-23 17:45:30', '2024-11-23 17:45:30');
INSERT INTO `pvt_permission` VALUES (12, '查询case列表', 'list-case', '/case/page', '查询所有的case', '2024-11-23 17:45:30', '2024-11-23 17:45:30');
INSERT INTO `pvt_permission` VALUES (13, '查看base case列表', 'list-base-case', '/case/base/page', '查询所有的基础case', '2024-11-23 17:45:30', '2024-11-23 17:45:30');
INSERT INTO `pvt_permission` VALUES (14, '新增base base', 'add-base-case', '/case/base/add', '新增一个基础case', '2024-11-23 17:54:35', '2024-11-23 17:54:35');
INSERT INTO `pvt_permission` VALUES (15, '删除基础case', 'delete-base-case', '/case/base/delete/{id}', '删除一个基础的case', '2024-11-23 17:54:35', '2024-11-23 17:54:35');
INSERT INTO `pvt_permission` VALUES (16, '查询基础case', 'query-base-case', '/case/base/query/{id}', '查询一个基础case', '2024-11-23 17:54:35', '2024-11-23 17:54:35');
INSERT INTO `pvt_permission` VALUES (17, '查询未使用的base case', 'relate-query-base-case', '/case/relate/query', '查询未关联的基础case', '2024-11-23 17:54:35', '2024-11-23 17:59:10');
INSERT INTO `pvt_permission` VALUES (18, '关联base case', 'relate-add-case', '/base/relate/add', '关联选择的基础case', '2024-11-23 17:54:35', '2024-11-23 17:58:25');
INSERT INTO `pvt_permission` VALUES (19, '查询RT模板列表', 'list-rt', '/rt/page', '查询所有的rt模板', '2024-11-23 17:54:35', '2024-11-23 17:54:35');
INSERT INTO `pvt_permission` VALUES (20, '新增RT模板', 'add-rt', '/rt/add', '新增一个RT 模板', '2024-11-23 17:54:35', '2024-11-23 17:54:35');
INSERT INTO `pvt_permission` VALUES (21, '删除RT模板', 'delete-rt', '/rt/delete/{id}', '删除一个RT模板', '2024-11-23 17:54:35', '2024-11-23 17:54:35');
INSERT INTO `pvt_permission` VALUES (22, '查询RT模板详情', 'quert-rt', '/rt/query/{id}', '根据ID 查询RT模板', '2024-11-23 17:54:35', '2024-11-23 17:54:35');
INSERT INTO `pvt_permission` VALUES (23, '复制RT模板', 'copy-rt', '/rt/copy/{id}', '根据现有的模板clone一个新模板', '2024-11-23 17:57:59', '2024-11-23 17:57:59');
INSERT INTO `pvt_permission` VALUES (24, '查询应用模板', 'query-rt-with-app', '/rt/query/with/application', '根据app查询对应的可用模板', '2024-11-23 17:57:59', '2024-11-23 17:57:59');
INSERT INTO `pvt_permission` VALUES (25, '变更模板状态', 'status-rt', '/rt/status/{id}', '变更模板状态', '2024-11-23 17:57:59', '2024-11-23 17:57:59');
INSERT INTO `pvt_permission` VALUES (26, '查询测试结果详情', 'query-result', '/result/query/{id}', '查询结果测试结果详情', '2024-11-23 18:04:56', '2024-11-23 18:04:56');
INSERT INTO `pvt_permission` VALUES (27, '新增测试结果', 'add-result', '/result/add', '新增测试结果', '2024-11-23 18:04:56', '2024-11-23 18:04:56');
INSERT INTO `pvt_permission` VALUES (28, '查询待测case', 'query-case-with-fliter', '/result/query/case', '查询所有的case供下一步使用', '2024-11-23 18:04:56', '2024-11-23 18:23:35');
INSERT INTO `pvt_permission` VALUES (29, '删除测试结果', 'delete-result', '/result/delete/{id}', '删除测试结果', '2024-11-23 18:05:23', '2024-11-23 18:05:57');
INSERT INTO `pvt_permission` VALUES (30, '上传测试图片', 'upload-evidence', '/document/upload', '（批量）上传测试截图', '2024-11-23 18:08:15', '2024-11-23 18:08:15');
INSERT INTO `pvt_permission` VALUES (31, '删除测试图片', 'delete-evidence', '/document/delete/{documentId}', '删除截图', '2024-11-23 18:08:15', '2024-11-23 18:08:15');
INSERT INTO `pvt_permission` VALUES (32, '导出SIT/UAT', 'export-sit', '/download/us', '导出SIT/UAT 测试用例到本地', '2024-11-23 18:10:52', '2024-11-23 18:10:52');
INSERT INTO `pvt_permission` VALUES (33, '导出RT', 'export-rt', '/downlaod/rt', '导出RT 测试用例到本地', '2024-11-23 18:10:52', '2024-11-23 18:10:52');
INSERT INTO `pvt_permission` VALUES (34, '导出PVT', 'export-pvt', '/download/pvt', '导出RT 测试用例到本地', '2024-11-23 18:10:52', '2024-11-23 18:10:52');
INSERT INTO `pvt_permission` VALUES (35, '查看所有用户', 'list-user', '/user/page', '查询所有的用户', '2024-11-23 18:23:35', '2024-11-23 18:23:35');
INSERT INTO `pvt_permission` VALUES (36, '新增/修改用户', 'add-user', '/user/add', '新增/修改一个用户', '2024-11-23 18:28:56', '2024-11-23 18:28:56');
INSERT INTO `pvt_permission` VALUES (37, '删除用户', 'delete-user', '/user/delete/(userId}', '删除一个用户', '2024-11-23 18:28:56', '2024-11-23 18:28:56');
INSERT INTO `pvt_permission` VALUES (38, '查询用户详情', 'query-user', '/user/query/{userId}', '查询用户', '2024-11-23 18:28:56', '2024-11-23 18:28:56');
INSERT INTO `pvt_permission` VALUES (39, '重置密码', 'reset-paw', '/user/reset/{userId}', '重置用户密码', '2024-11-23 18:28:56', '2024-11-23 18:28:56');
INSERT INTO `pvt_permission` VALUES (40, '查看所有的角色', 'list-role', '/role/list', '查询角色列表', '2024-11-23 18:54:29', '2024-11-23 18:54:29');
INSERT INTO `pvt_permission` VALUES (41, '新增/修改角色', 'add-role', '/role/add', '新增/修改角色', '2024-11-23 18:54:29', '2024-11-23 18:54:29');
INSERT INTO `pvt_permission` VALUES (42, '删除角色', 'delete-role', '/role/delet/{roleId}', '删除角色', '2024-11-23 18:54:29', '2024-11-23 18:54:29');
INSERT INTO `pvt_permission` VALUES (43, '设置权限', 'set-permission', '/role/permission/setting', '设置权限', '2024-11-23 18:54:29', '2024-11-23 18:54:29');
INSERT INTO `pvt_permission` VALUES (44, '查询权限列表', 'list-permisson', '/permission/list', '查询所有权限', '2024-11-23 19:02:17', '2024-11-23 19:02:17');
INSERT INTO `pvt_permission` VALUES (45, '新增/修改权限', 'add-permission', '/permission/add', '新增/修改权限', '2024-11-23 19:02:17', '2024-11-23 19:02:17');
INSERT INTO `pvt_permission` VALUES (46, '删除权限', 'delete-permission', '/permission/delete/{permissionId}', '删除权限', '2024-11-23 19:02:17', '2024-11-23 19:02:17');
INSERT INTO `pvt_permission` VALUES (47, '查询权限', 'query-permission-role', '/permission/query/{roleId}', '根据角色查询权限列表', '2024-11-23 22:18:43', '2024-11-23 22:18:43');

SET FOREIGN_KEY_CHECKS = 1;

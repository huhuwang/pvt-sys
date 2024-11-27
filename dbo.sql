/*
 Navicat Premium Data Transfer

 Source Server         : sqlserver
 Source Server Type    : SQL Server
 Source Server Version : 14001000
 Source Host           : localhost:1433
 Source Catalog        : PVT
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 14001000
 File Encoding         : 65001

 Date: 26/11/2024 23:11:21
*/


-- ----------------------------
-- Table structure for common_test_case_base
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[common_test_case_base]') AND type IN ('U'))
	DROP TABLE [dbo].[common_test_case_base]
GO

CREATE TABLE [dbo].[common_test_case_base] (
  [id] int  NOT NULL,
  [description] nvarchar(max) COLLATE Chinese_PRC_CI_AS  NULL,
  [summary] nvarchar(max) COLLATE Chinese_PRC_CI_AS  NULL,
  [expected_result] nvarchar(max) COLLATE Chinese_PRC_CI_AS  NULL,
  [category] int  NULL,
  [priority] tinyint  NULL,
  [type] tinyint  NULL,
  [row_height] int  NULL,
  [rt_flow] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [create_time] datetime2(0)  NULL,
  [create_user] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [update_time] datetime2(0)  NULL,
  [update_user] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [status] tinyint  NULL,
  [remark] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[common_test_case_base] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'common_test_case_base',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'common_test_case_base',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'简述',
'SCHEMA', N'dbo',
'TABLE', N'common_test_case_base',
'COLUMN', N'summary'
GO

EXEC sp_addextendedproperty
'MS_Description', N'期待结果',
'SCHEMA', N'dbo',
'TABLE', N'common_test_case_base',
'COLUMN', N'expected_result'
GO

EXEC sp_addextendedproperty
'MS_Description', N'1. SIT 2.UAT 4.RT 8.PVT 16.WEB 32.IPAD',
'SCHEMA', N'dbo',
'TABLE', N'common_test_case_base',
'COLUMN', N'category'
GO

EXEC sp_addextendedproperty
'MS_Description', N'1. Urgent 2.Highest 3 High 4.low 5Lowest 6.Blocker 7.Critical 8Major 9.Minor 10.Trivial',
'SCHEMA', N'dbo',
'TABLE', N'common_test_case_base',
'COLUMN', N'priority'
GO

EXEC sp_addextendedproperty
'MS_Description', N'1. related to story 2. common  ',
'SCHEMA', N'dbo',
'TABLE', N'common_test_case_base',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'行高',
'SCHEMA', N'dbo',
'TABLE', N'common_test_case_base',
'COLUMN', N'row_height'
GO

EXEC sp_addextendedproperty
'MS_Description', N'如果是RT case,对应的RT flow',
'SCHEMA', N'dbo',
'TABLE', N'common_test_case_base',
'COLUMN', N'rt_flow'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'common_test_case_base',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建者',
'SCHEMA', N'dbo',
'TABLE', N'common_test_case_base',
'COLUMN', N'create_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'common_test_case_base',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改者',
'SCHEMA', N'dbo',
'TABLE', N'common_test_case_base',
'COLUMN', N'update_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态  1.启用   2.禁用  3.完成',
'SCHEMA', N'dbo',
'TABLE', N'common_test_case_base',
'COLUMN', N'status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'common_test_case_base',
'COLUMN', N'remark'
GO


-- ----------------------------
-- Table structure for deployment
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[deployment]') AND type IN ('U'))
	DROP TABLE [dbo].[deployment]
GO

CREATE TABLE [dbo].[deployment] (
  [id] int  NOT NULL,
  [deployment_date] date  NOT NULL,
  [deployment_name] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [application_name] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [template_id] int  NULL,
  [status] tinyint  NULL,
  [create_time] datetime2(0)  NULL,
  [create_user] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [update_time] datetime2(0)  NULL,
  [update_user] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [remark] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[deployment] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键自增',
'SCHEMA', N'dbo',
'TABLE', N'deployment',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'发布日期',
'SCHEMA', N'dbo',
'TABLE', N'deployment',
'COLUMN', N'deployment_date'
GO

EXEC sp_addextendedproperty
'MS_Description', N'发布名称',
'SCHEMA', N'dbo',
'TABLE', N'deployment',
'COLUMN', N'deployment_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'应用名称 eg. IRECRUIT2.0',
'SCHEMA', N'dbo',
'TABLE', N'deployment',
'COLUMN', N'application_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'RT 模板',
'SCHEMA', N'dbo',
'TABLE', N'deployment',
'COLUMN', N'template_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态  1.启用   2.禁用  3.完成',
'SCHEMA', N'dbo',
'TABLE', N'deployment',
'COLUMN', N'status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'deployment',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建者',
'SCHEMA', N'dbo',
'TABLE', N'deployment',
'COLUMN', N'create_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'deployment',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改者',
'SCHEMA', N'dbo',
'TABLE', N'deployment',
'COLUMN', N'update_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'deployment',
'COLUMN', N'remark'
GO


-- ----------------------------
-- Table structure for document
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[document]') AND type IN ('U'))
	DROP TABLE [dbo].[document]
GO

CREATE TABLE [dbo].[document] (
  [id] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [document_name] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [description] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [result_id] int  NULL,
  [original_size] bigint  NULL,
  [scale_size] bigint  NULL,
  [document_type] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [url] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [create_time] datetime2(0)  NULL,
  [create_user] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [update_time] datetime2(0)  NULL,
  [update_user] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [status] tinyint  NULL,
  [remark] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[document] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'document',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件名称',
'SCHEMA', N'dbo',
'TABLE', N'document',
'COLUMN', N'document_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'document',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'result id',
'SCHEMA', N'dbo',
'TABLE', N'document',
'COLUMN', N'result_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'原始尺寸',
'SCHEMA', N'dbo',
'TABLE', N'document',
'COLUMN', N'original_size'
GO

EXEC sp_addextendedproperty
'MS_Description', N'处理之后的尺寸',
'SCHEMA', N'dbo',
'TABLE', N'document',
'COLUMN', N'scale_size'
GO

EXEC sp_addextendedproperty
'MS_Description', N'文件类型',
'SCHEMA', N'dbo',
'TABLE', N'document',
'COLUMN', N'document_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'图片地址',
'SCHEMA', N'dbo',
'TABLE', N'document',
'COLUMN', N'url'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'document',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建者',
'SCHEMA', N'dbo',
'TABLE', N'document',
'COLUMN', N'create_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'document',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改者',
'SCHEMA', N'dbo',
'TABLE', N'document',
'COLUMN', N'update_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态  1.启用   2.禁用  3.完成',
'SCHEMA', N'dbo',
'TABLE', N'document',
'COLUMN', N'status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'document',
'COLUMN', N'remark'
GO


-- ----------------------------
-- Table structure for kv_constants
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[kv_constants]') AND type IN ('U'))
	DROP TABLE [dbo].[kv_constants]
GO

CREATE TABLE [dbo].[kv_constants] (
  [id] int  NOT NULL,
  [key_name] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [key_value] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [template_id] int  NULL,
  [constant_type] int  NULL,
  [parent_id] int  NULL,
  [create_time] datetime2(0)  NULL,
  [create_user] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [update_time] datetime2(0)  NULL,
  [update_user] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [status] tinyint  NULL,
  [remark] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[kv_constants] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'kv_constants',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'key 名字',
'SCHEMA', N'dbo',
'TABLE', N'kv_constants',
'COLUMN', N'key_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'key 值',
'SCHEMA', N'dbo',
'TABLE', N'kv_constants',
'COLUMN', N'key_value'
GO

EXEC sp_addextendedproperty
'MS_Description', N'templeted',
'SCHEMA', N'dbo',
'TABLE', N'kv_constants',
'COLUMN', N'template_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'1. flow step  2. flow data',
'SCHEMA', N'dbo',
'TABLE', N'kv_constants',
'COLUMN', N'constant_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'父级id',
'SCHEMA', N'dbo',
'TABLE', N'kv_constants',
'COLUMN', N'parent_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'kv_constants',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建者',
'SCHEMA', N'dbo',
'TABLE', N'kv_constants',
'COLUMN', N'create_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'kv_constants',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改者',
'SCHEMA', N'dbo',
'TABLE', N'kv_constants',
'COLUMN', N'update_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态  1.启用   2.禁用  3.完成',
'SCHEMA', N'dbo',
'TABLE', N'kv_constants',
'COLUMN', N'status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'kv_constants',
'COLUMN', N'remark'
GO


-- ----------------------------
-- Table structure for pvt_permission
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[pvt_permission]') AND type IN ('U'))
	DROP TABLE [dbo].[pvt_permission]
GO

CREATE TABLE [dbo].[pvt_permission] (
  [id] int  NOT NULL,
  [permission_name] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [permission_code] nvarchar(46) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [permission_url] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [permission_describe] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [create_time] datetime2(0)  NULL,
  [update_time] datetime2(0)  NULL
)
GO

ALTER TABLE [dbo].[pvt_permission] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'注释',
'SCHEMA', N'dbo',
'TABLE', N'pvt_permission',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'权限名称',
'SCHEMA', N'dbo',
'TABLE', N'pvt_permission',
'COLUMN', N'permission_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'权限编号',
'SCHEMA', N'dbo',
'TABLE', N'pvt_permission',
'COLUMN', N'permission_code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'功能url',
'SCHEMA', N'dbo',
'TABLE', N'pvt_permission',
'COLUMN', N'permission_url'
GO

EXEC sp_addextendedproperty
'MS_Description', N'权限描述',
'SCHEMA', N'dbo',
'TABLE', N'pvt_permission',
'COLUMN', N'permission_describe'
GO

EXEC sp_addextendedproperty
'MS_Description', N'新建时间',
'SCHEMA', N'dbo',
'TABLE', N'pvt_permission',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'pvt_permission',
'COLUMN', N'update_time'
GO


-- ----------------------------
-- Table structure for pvt_role
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[pvt_role]') AND type IN ('U'))
	DROP TABLE [dbo].[pvt_role]
GO

CREATE TABLE [dbo].[pvt_role] (
  [id] int  NOT NULL,
  [role_name] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [role_describe] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [create_time] datetime2(0)  NULL,
  [update_time] datetime2(0)  NULL
)
GO

ALTER TABLE [dbo].[pvt_role] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'pvt_role',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色名称',
'SCHEMA', N'dbo',
'TABLE', N'pvt_role',
'COLUMN', N'role_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色描述',
'SCHEMA', N'dbo',
'TABLE', N'pvt_role',
'COLUMN', N'role_describe'
GO

EXEC sp_addextendedproperty
'MS_Description', N'新建时间',
'SCHEMA', N'dbo',
'TABLE', N'pvt_role',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'pvt_role',
'COLUMN', N'update_time'
GO


-- ----------------------------
-- Table structure for pvt_role_permission
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[pvt_role_permission]') AND type IN ('U'))
	DROP TABLE [dbo].[pvt_role_permission]
GO

CREATE TABLE [dbo].[pvt_role_permission] (
  [id] int  NOT NULL,
  [principal_id] int  NULL,
  [permission_id] int  NULL,
  [principal_type] tinyint  NULL,
  [create_time] datetime2(0)  NULL,
  [update_time] datetime2(0)  NULL
)
GO

ALTER TABLE [dbo].[pvt_role_permission] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'pvt_role_permission',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'主体id',
'SCHEMA', N'dbo',
'TABLE', N'pvt_role_permission',
'COLUMN', N'principal_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'权限id',
'SCHEMA', N'dbo',
'TABLE', N'pvt_role_permission',
'COLUMN', N'permission_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'1. 角色  2,个人  3，组织 为了方便测试这个都是1',
'SCHEMA', N'dbo',
'TABLE', N'pvt_role_permission',
'COLUMN', N'principal_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'新建时间',
'SCHEMA', N'dbo',
'TABLE', N'pvt_role_permission',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'pvt_role_permission',
'COLUMN', N'update_time'
GO


-- ----------------------------
-- Table structure for pvt_user
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[pvt_user]') AND type IN ('U'))
	DROP TABLE [dbo].[pvt_user]
GO

CREATE TABLE [dbo].[pvt_user] (
  [id] int  NOT NULL,
  [account] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [user_name] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [nick_name] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [password] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [role_id] int  NOT NULL,
  [create_user] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [create_time] datetime2(0)  NULL,
  [update_user] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [update_time] datetime2(0)  NULL
)
GO

ALTER TABLE [dbo].[pvt_user] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'pvt_user',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'账号',
'SCHEMA', N'dbo',
'TABLE', N'pvt_user',
'COLUMN', N'account'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用户名',
'SCHEMA', N'dbo',
'TABLE', N'pvt_user',
'COLUMN', N'user_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'昵称',
'SCHEMA', N'dbo',
'TABLE', N'pvt_user',
'COLUMN', N'nick_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'密码',
'SCHEMA', N'dbo',
'TABLE', N'pvt_user',
'COLUMN', N'password'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色id',
'SCHEMA', N'dbo',
'TABLE', N'pvt_user',
'COLUMN', N'role_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人id',
'SCHEMA', N'dbo',
'TABLE', N'pvt_user',
'COLUMN', N'create_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'pvt_user',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人id',
'SCHEMA', N'dbo',
'TABLE', N'pvt_user',
'COLUMN', N'update_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'pvt_user',
'COLUMN', N'update_time'
GO


-- ----------------------------
-- Table structure for rt_summary_template
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[rt_summary_template]') AND type IN ('U'))
	DROP TABLE [dbo].[rt_summary_template]
GO

CREATE TABLE [dbo].[rt_summary_template] (
  [id] int  NOT NULL,
  [template_name] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [flow_number] int  NOT NULL,
  [application] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [create_time] datetime2(0)  NULL,
  [create_user] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [update_time] datetime2(0)  NULL,
  [update_user] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [status] tinyint  NULL,
  [remark] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[rt_summary_template] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'rt_summary_template',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'模板名称',
'SCHEMA', N'dbo',
'TABLE', N'rt_summary_template',
'COLUMN', N'template_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'flow 的数量',
'SCHEMA', N'dbo',
'TABLE', N'rt_summary_template',
'COLUMN', N'flow_number'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属的app',
'SCHEMA', N'dbo',
'TABLE', N'rt_summary_template',
'COLUMN', N'application'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'rt_summary_template',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建者',
'SCHEMA', N'dbo',
'TABLE', N'rt_summary_template',
'COLUMN', N'create_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'rt_summary_template',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改者',
'SCHEMA', N'dbo',
'TABLE', N'rt_summary_template',
'COLUMN', N'update_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态  1.启用   2.禁用  3.完成',
'SCHEMA', N'dbo',
'TABLE', N'rt_summary_template',
'COLUMN', N'status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'rt_summary_template',
'COLUMN', N'remark'
GO


-- ----------------------------
-- Table structure for test_case
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[test_case]') AND type IN ('U'))
	DROP TABLE [dbo].[test_case]
GO

CREATE TABLE [dbo].[test_case] (
  [id] int  NOT NULL,
  [ticket_no] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [description] nvarchar(max) COLLATE Chinese_PRC_CI_AS  NULL,
  [summary] nvarchar(max) COLLATE Chinese_PRC_CI_AS  NULL,
  [expected_result] nvarchar(max) COLLATE Chinese_PRC_CI_AS  NULL,
  [priority] tinyint  NULL,
  [type] tinyint  NULL,
  [row_height] int  NULL,
  [base_case_from] int  NULL,
  [create_time] datetime2(0)  NULL,
  [create_user] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [update_time] datetime2(0)  NULL,
  [update_user] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [status] tinyint  NULL,
  [remark] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[test_case] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'test_case',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'ticket number',
'SCHEMA', N'dbo',
'TABLE', N'test_case',
'COLUMN', N'ticket_no'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'test_case',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'简述',
'SCHEMA', N'dbo',
'TABLE', N'test_case',
'COLUMN', N'summary'
GO

EXEC sp_addextendedproperty
'MS_Description', N'期待结果',
'SCHEMA', N'dbo',
'TABLE', N'test_case',
'COLUMN', N'expected_result'
GO

EXEC sp_addextendedproperty
'MS_Description', N'1. Urgent 2.Highest 3 High 4.low 5Lowest 6.Blocker 7.Critical 8Major 9.Minor 10.Trivial',
'SCHEMA', N'dbo',
'TABLE', N'test_case',
'COLUMN', N'priority'
GO

EXEC sp_addextendedproperty
'MS_Description', N'1. related to story 2. common  ',
'SCHEMA', N'dbo',
'TABLE', N'test_case',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'行高',
'SCHEMA', N'dbo',
'TABLE', N'test_case',
'COLUMN', N'row_height'
GO

EXEC sp_addextendedproperty
'MS_Description', N'来源',
'SCHEMA', N'dbo',
'TABLE', N'test_case',
'COLUMN', N'base_case_from'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'test_case',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建者',
'SCHEMA', N'dbo',
'TABLE', N'test_case',
'COLUMN', N'create_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'test_case',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改者',
'SCHEMA', N'dbo',
'TABLE', N'test_case',
'COLUMN', N'update_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态  1.启用   2.禁用  3.完成',
'SCHEMA', N'dbo',
'TABLE', N'test_case',
'COLUMN', N'status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'test_case',
'COLUMN', N'remark'
GO


-- ----------------------------
-- Table structure for test_result
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[test_result]') AND type IN ('U'))
	DROP TABLE [dbo].[test_result]
GO

CREATE TABLE [dbo].[test_result] (
  [id] int  NOT NULL,
  [case_id] int  NOT NULL,
  [category] int  NOT NULL,
  [step] nvarchar(max) COLLATE Chinese_PRC_CI_AS  NULL,
  [test_data] nvarchar(max) COLLATE Chinese_PRC_CI_AS  NULL,
  [actual_result] nvarchar(max) COLLATE Chinese_PRC_CI_AS  NULL,
  [result] tinyint  NULL,
  [rt_flow] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [create_time] datetime2(0)  NULL,
  [create_user] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [update_time] datetime2(0)  NULL,
  [update_user] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [status] tinyint  NULL,
  [remark] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[test_result] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'test_result',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'case id',
'SCHEMA', N'dbo',
'TABLE', N'test_result',
'COLUMN', N'case_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'1. SIT 2.UAT 4.RT 8.PVT 16.WEB 32.IPAD',
'SCHEMA', N'dbo',
'TABLE', N'test_result',
'COLUMN', N'category'
GO

EXEC sp_addextendedproperty
'MS_Description', N'步骤',
'SCHEMA', N'dbo',
'TABLE', N'test_result',
'COLUMN', N'step'
GO

EXEC sp_addextendedproperty
'MS_Description', N'测试数据',
'SCHEMA', N'dbo',
'TABLE', N'test_result',
'COLUMN', N'test_data'
GO

EXEC sp_addextendedproperty
'MS_Description', N'实际结果',
'SCHEMA', N'dbo',
'TABLE', N'test_result',
'COLUMN', N'actual_result'
GO

EXEC sp_addextendedproperty
'MS_Description', N'1. pass 2. no pass',
'SCHEMA', N'dbo',
'TABLE', N'test_result',
'COLUMN', N'result'
GO

EXEC sp_addextendedproperty
'MS_Description', N'如果是RT case,对应的RT flow',
'SCHEMA', N'dbo',
'TABLE', N'test_result',
'COLUMN', N'rt_flow'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'test_result',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建者',
'SCHEMA', N'dbo',
'TABLE', N'test_result',
'COLUMN', N'create_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'test_result',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改者',
'SCHEMA', N'dbo',
'TABLE', N'test_result',
'COLUMN', N'update_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态  1.启用   2.禁用  3.完成',
'SCHEMA', N'dbo',
'TABLE', N'test_result',
'COLUMN', N'status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'test_result',
'COLUMN', N'remark'
GO


-- ----------------------------
-- Table structure for ticket
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[ticket]') AND type IN ('U'))
	DROP TABLE [dbo].[ticket]
GO

CREATE TABLE [dbo].[ticket] (
  [id] int  NOT NULL,
  [deployment_id] int  NULL,
  [ticket_no] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [ticket_title] nvarchar(164) COLLATE Chinese_PRC_CI_AS  NULL,
  [ticket_url] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [ticket_type] nvarchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
  [type] tinyint  NULL,
  [create_time] datetime2(0)  NULL,
  [create_user] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [update_time] datetime2(0)  NULL,
  [update_user] nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
  [status] tinyint  NULL,
  [remark] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[ticket] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键id',
'SCHEMA', N'dbo',
'TABLE', N'ticket',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属的deployment',
'SCHEMA', N'dbo',
'TABLE', N'ticket',
'COLUMN', N'deployment_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'ticket number',
'SCHEMA', N'dbo',
'TABLE', N'ticket',
'COLUMN', N'ticket_no'
GO

EXEC sp_addextendedproperty
'MS_Description', N'ticket 标题',
'SCHEMA', N'dbo',
'TABLE', N'ticket',
'COLUMN', N'ticket_title'
GO

EXEC sp_addextendedproperty
'MS_Description', N'对应的url',
'SCHEMA', N'dbo',
'TABLE', N'ticket',
'COLUMN', N'ticket_url'
GO

EXEC sp_addextendedproperty
'MS_Description', N'ticket 类型  （story/pro issue）',
'SCHEMA', N'dbo',
'TABLE', N'ticket',
'COLUMN', N'ticket_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'类型  1. 手动创建  2.自动创建的common',
'SCHEMA', N'dbo',
'TABLE', N'ticket',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'ticket',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建者',
'SCHEMA', N'dbo',
'TABLE', N'ticket',
'COLUMN', N'create_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'ticket',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改人',
'SCHEMA', N'dbo',
'TABLE', N'ticket',
'COLUMN', N'update_user'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态  1.启用   2.禁用  3.完成',
'SCHEMA', N'dbo',
'TABLE', N'ticket',
'COLUMN', N'status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'ticket',
'COLUMN', N'remark'
GO


-- ----------------------------
-- Primary Key structure for table common_test_case_base
-- ----------------------------
ALTER TABLE [dbo].[common_test_case_base] ADD CONSTRAINT [PK__common_t__3213E83FB3B7AC4F] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table deployment
-- ----------------------------
CREATE NONCLUSTERED INDEX [create_idx]
ON [dbo].[deployment] (
  [create_time] ASC
)
GO

CREATE NONCLUSTERED INDEX [template_idx]
ON [dbo].[deployment] (
  [template_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table deployment
-- ----------------------------
ALTER TABLE [dbo].[deployment] ADD CONSTRAINT [PK__deployme__3213E83F82E9817C] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table document
-- ----------------------------
CREATE NONCLUSTERED INDEX [result_idx]
ON [dbo].[document] (
  [result_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table document
-- ----------------------------
ALTER TABLE [dbo].[document] ADD CONSTRAINT [PK__document__3213E83FFBE9C5DC] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table kv_constants
-- ----------------------------
CREATE NONCLUSTERED INDEX [parent_idx]
ON [dbo].[kv_constants] (
  [parent_id] ASC
)
GO

CREATE NONCLUSTERED INDEX [template_idx]
ON [dbo].[kv_constants] (
  [template_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table kv_constants
-- ----------------------------
ALTER TABLE [dbo].[kv_constants] ADD CONSTRAINT [PK__kv_const__3213E83F05B09751] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table pvt_permission
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [IDX_CODE]
ON [dbo].[pvt_permission] (
  [permission_code] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table pvt_permission
-- ----------------------------
ALTER TABLE [dbo].[pvt_permission] ADD CONSTRAINT [PK__pvt_perm__3213E83FC6413E11] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table pvt_role
-- ----------------------------
ALTER TABLE [dbo].[pvt_role] ADD CONSTRAINT [PK__pvt_role__3213E83F3DEF0ED1] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table pvt_role_permission
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [IDX_PRINCIPAL]
ON [dbo].[pvt_role_permission] (
  [principal_id] ASC,
  [permission_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table pvt_role_permission
-- ----------------------------
ALTER TABLE [dbo].[pvt_role_permission] ADD CONSTRAINT [PK__pvt_role__3213E83F506D359F] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table pvt_user
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [user_account_idx]
ON [dbo].[pvt_user] (
  [account] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table pvt_user
-- ----------------------------
ALTER TABLE [dbo].[pvt_user] ADD CONSTRAINT [PK__pvt_user__3213E83FF7FA92F5] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table rt_summary_template
-- ----------------------------
ALTER TABLE [dbo].[rt_summary_template] ADD CONSTRAINT [PK__rt_summa__3213E83F81B253BD] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table test_case
-- ----------------------------
CREATE NONCLUSTERED INDEX [ticket_no_idx]
ON [dbo].[test_case] (
  [ticket_no] ASC
)
GO

CREATE NONCLUSTERED INDEX [case_from_idx]
ON [dbo].[test_case] (
  [base_case_from] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table test_case
-- ----------------------------
ALTER TABLE [dbo].[test_case] ADD CONSTRAINT [PK__test_cas__3213E83F6F72DD63] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table test_result
-- ----------------------------
CREATE NONCLUSTERED INDEX [case_id_idx]
ON [dbo].[test_result] (
  [case_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table test_result
-- ----------------------------
ALTER TABLE [dbo].[test_result] ADD CONSTRAINT [PK__test_res__3213E83F46253FD9] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table ticket
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [deployment_ticket_idx]
ON [dbo].[ticket] (
  [deployment_id] ASC,
  [ticket_no] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table ticket
-- ----------------------------
ALTER TABLE [dbo].[ticket] ADD CONSTRAINT [PK__ticket__3213E83F25504616] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


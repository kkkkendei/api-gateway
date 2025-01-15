SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for application_interface
-- ----------------------------
DROP TABLE IF EXISTS `application_interface`;
CREATE TABLE `application_interface` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `system_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '系统标识',
  `interface_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '接口标识',
  `interface_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '接口名称',
  `interface_version` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '接口版本',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of application_interface
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for application_interface_method
-- ----------------------------
DROP TABLE IF EXISTS `application_interface_method`;
CREATE TABLE `application_interface_method` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `system_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '系统标识',
  `interface_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '接口标识',
  `method_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '方法标识',
  `method_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '方法名称',
  `parameter_type` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '参数类型；(RPC 限定单参数注册)；new String[]{"java.lang.String"}、new String[]{"cn.bugstack.gateway.rpc.dto.XReq"}',
  `uri` varchar(126) COLLATE utf8_bin DEFAULT NULL COMMENT '网关接口',
  `http_command_type` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '接口类型；GET、POST、PUT、DELETE',
  `auth` int(4) DEFAULT NULL COMMENT 'true = 1是、false = 0否',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of application_interface_method
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for application_system
-- ----------------------------
DROP TABLE IF EXISTS `application_system`;
CREATE TABLE `application_system` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `system_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '系统标识',
  `system_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '系统名称',
  `system_type` varchar(4) COLLATE utf8_bin DEFAULT NULL COMMENT '系统类型；RPC、HTTP',
  `system_registry` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '注册中心',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of application_system
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for gateway_distribution
-- ----------------------------
DROP TABLE IF EXISTS `gateway_distribution`;
CREATE TABLE `gateway_distribution` (
  `id` int(11) NOT NULL COMMENT '自增主键',
  `group_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '分组标识',
  `gateway_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '网关标识',
  `system_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '系统标识',
  `system_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '系统名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gateway_distribution
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for gateway_server
-- ----------------------------
DROP TABLE IF EXISTS `gateway_server`;
CREATE TABLE `gateway_server` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `group_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '分组标识',
  `group_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '分组名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gateway_server
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for gateway_server_detail
-- ----------------------------
DROP TABLE IF EXISTS `gateway_server_detail`;
CREATE TABLE `gateway_server_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `group_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '分组标识',
  `gateway_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '网关标识',
  `gateway_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '网关名称',
  `gateway_address` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '网关地址：127.0.0.1',
  `status` varchar(4) COLLATE utf8_bin DEFAULT NULL COMMENT '服务状态：0不可用、1可使用',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_gateway` (`gateway_id`,`gateway_address`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gateway_server_detail
-- ----------------------------
BEGIN;
COMMIT;


-- ----------------------------
-- Table structure for http_statement
-- ----------------------------
DROP TABLE IF EXISTS `http_statement`;
CREATE TABLE `http_statement` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `application` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '应用名称',
  `interface_name` varchar(256) COLLATE utf8_bin NOT NULL COMMENT '服务接口；RPC、其他',
  `method_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT ' 服务方法；RPC#method',
  `parameter_type` varchar(256) COLLATE utf8_bin NOT NULL COMMENT '参数类型(RPC 限定单参数注册)；new String[]{"java.lang.String"}、new String[]{"cn.bugstack.gateway.rpc.dto.XReq"}',
  `uri` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '网关接口',
  `http_command_type` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '接口类型；GET、POST、PUT、DELETE',
  `auth` int(4) NOT NULL DEFAULT '0' COMMENT 'true = 1是、false = 0否',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of http_statement
-- ----------------------------
BEGIN;
INSERT INTO `http_statement` VALUES (1, 'api-gateway-test', 'cn.bugstack.gateway.rpc.IActivityBooth', 'sayHi', 'java.lang.String', '/wg/activity/sayHi', 'GET', 0, '2022-10-22 15:30:00', '2022-10-22 15:30:00');
INSERT INTO `http_statement` VALUES (2, 'api-gateway-test', 'cn.bugstack.gateway.rpc.IActivityBooth', 'insert', 'cn.bugstack.gateway.rpc.dto.XReq', '/wg/activity/insert', 'POST', 1, '2022-10-22 15:30:00', '2022-10-22 15:30:00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

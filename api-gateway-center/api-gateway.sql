SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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

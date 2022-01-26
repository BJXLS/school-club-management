/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.15 : Database - easyweb-shiro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`easyweb-shiro` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `easyweb-shiro`;

/*Table structure for table `sys_dictionary` */

DROP TABLE IF EXISTS `sys_dictionary`;

CREATE TABLE `sys_dictionary` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典id',
  `dict_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典标识',
  `dict_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `sort_number` int(11) NOT NULL DEFAULT '1' COMMENT '排序号',
  `comments` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除,0否,1是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典';

/*Data for the table `sys_dictionary` */

insert  into `sys_dictionary`(`dict_id`,`dict_code`,`dict_name`,`sort_number`,`comments`,`deleted`,`create_time`,`update_time`) values (1,'sex','性别',1,'',0,'2020-03-15 13:04:39','2020-03-15 13:04:39'),(2,'organization_type','机构类型',2,'',0,'2020-03-16 00:32:36','2020-03-16 00:32:36');

/*Table structure for table `sys_dictionary_data` */

DROP TABLE IF EXISTS `sys_dictionary_data`;

CREATE TABLE `sys_dictionary_data` (
  `dict_data_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典项id',
  `dict_id` int(11) NOT NULL COMMENT '字典id',
  `dict_data_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项标识',
  `dict_data_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项名称',
  `sort_number` int(11) NOT NULL DEFAULT '1' COMMENT '排序号',
  `comments` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除,0否,1是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`dict_data_id`),
  KEY `dict_id` (`dict_id`),
  CONSTRAINT `sys_dictionary_data_ibfk_1` FOREIGN KEY (`dict_id`) REFERENCES `sys_dictionary` (`dict_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典项';

/*Data for the table `sys_dictionary_data` */

insert  into `sys_dictionary_data`(`dict_data_id`,`dict_id`,`dict_data_code`,`dict_data_name`,`sort_number`,`comments`,`deleted`,`create_time`,`update_time`) values (1,1,'male','男',1,'',0,'2020-03-15 13:07:28','2020-03-15 13:07:28'),(2,1,'female','女',2,'',0,'2020-03-15 13:07:41','2020-03-15 15:58:04'),(3,2,'company','公司',1,'',0,'2020-03-16 00:34:32','2020-03-16 00:34:32'),(4,2,'subsidiary','子公司',2,'',0,'2020-03-16 00:35:02','2020-03-16 00:35:02'),(5,2,'department','部门',3,'',0,'2020-03-16 00:35:18','2020-03-16 00:35:18'),(6,2,'group','小组',4,'',0,'2020-03-16 00:35:36','2020-03-16 00:35:36');

/*Table structure for table `sys_login_record` */

DROP TABLE IF EXISTS `sys_login_record`;

CREATE TABLE `sys_login_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `os` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作系统',
  `device` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备名',
  `browser` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浏览器类型',
  `ip` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ip地址',
  `oper_type` int(11) NOT NULL COMMENT '操作类型,0登录成功,1登录失败,2退出登录,3刷新token',
  `comments` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='登录日志';

/*Data for the table `sys_login_record` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级id,0是顶级',
  `menu_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `menu_icon` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单地址',
  `menu_type` int(11) DEFAULT '0' COMMENT '类型,0菜单,1按钮',
  `sort_number` int(11) NOT NULL DEFAULT '1' COMMENT '排序号',
  `authority` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
  `target` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '_self' COMMENT '打开位置',
  `icon_color` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图标颜色',
  `hide` int(11) NOT NULL DEFAULT '0' COMMENT '是否隐藏,0否,1是',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除,0否,1是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`parent_id`,`menu_name`,`menu_icon`,`path`,`menu_type`,`sort_number`,`authority`,`target`,`hide`,`deleted`,`create_time`,`update_time`) values (1,0,'系统管理','layui-icon layui-icon-set-sm',NULL,0,1,NULL,'_self',0,0,'2020-02-26 12:51:23','2020-03-21 18:47:00'),(2,1,'用户管理',NULL,'sys/user',0,1,'sys:user:view','_self',0,0,'2020-02-26 12:51:55','2020-03-21 18:45:26'),(3,2,'查询用户',NULL,NULL,1,1,'sys:user:list','_self',0,0,'2020-02-26 12:52:06','2020-03-21 18:45:28'),(4,2,'添加用户',NULL,NULL,1,2,'sys:user:save','_self',0,0,'2020-02-26 12:52:26','2020-03-21 18:45:29'),(5,2,'修改用户',NULL,NULL,1,3,'sys:user:update','_self',0,0,'2020-02-26 12:52:50','2020-03-21 18:45:30'),(6,2,'删除用户',NULL,NULL,1,4,'sys:user:remove','_self',0,0,'2020-02-26 12:53:13','2020-03-21 18:45:32'),(7,1,'角色管理',NULL,'sys/role',0,2,'sys:role:view','_self',0,0,'2020-03-13 13:29:08','2020-03-21 18:45:33'),(8,7,'查询角色',NULL,NULL,1,1,'sys:role:list','_self',0,0,'2020-03-13 13:30:41','2020-03-21 18:45:34'),(9,7,'添加角色',NULL,NULL,1,2,'sys:role:save','_self',0,0,'2020-03-15 13:02:07','2020-03-21 18:45:35'),(10,7,'修改角色',NULL,NULL,1,3,'sys:role:update','_self',0,0,'2020-03-15 13:02:49','2020-03-21 18:45:36'),(11,7,'删除角色',NULL,NULL,1,4,'sys:role:remove','_self',0,0,'2020-03-20 17:58:51','2020-03-21 18:45:38'),(12,1,'菜单管理',NULL,'sys/menu',0,3,'sys:menu:view','_self',0,0,'2020-03-21 01:07:13','2020-03-21 18:45:39'),(13,12,'查询菜单',NULL,NULL,1,1,'sys:menu:list','_self',0,0,'2020-03-21 16:43:30','2020-03-21 18:45:40'),(14,12,'添加菜单',NULL,NULL,1,2,'sys:menu:save','_self',0,0,'2020-03-21 16:43:54','2020-03-21 18:45:41'),(15,12,'修改菜单',NULL,NULL,1,3,'sys:menu:update','_self',0,0,'2020-03-21 18:24:17','2020-03-21 18:45:43'),(16,12,'删除菜单',NULL,NULL,1,4,'sys:menu:remove','_self',0,0,'2020-03-21 18:24:18','2020-03-21 18:45:44'),(17,1,'机构管理',NULL,'sys/organization',0,4,'sys:org:view','_self',0,0,'2020-03-21 18:24:20','2020-03-21 18:45:49'),(18,17,'查询机构',NULL,NULL,1,1,'sys:org:list','_self',0,0,'2020-03-21 18:24:21','2020-03-21 18:44:36'),(19,17,'添加机构',NULL,NULL,1,2,'sys:org:save','_self',0,0,'2020-03-21 18:24:22','2020-03-21 18:45:51'),(20,17,'修改机构',NULL,NULL,1,3,'sys:org:update','_self',0,0,'2020-03-21 18:24:24','2020-03-21 18:45:52'),(21,17,'删除机构',NULL,NULL,1,4,'sys:org:remove','_self',0,0,'2020-03-21 18:24:25','2020-03-21 18:45:54'),(22,1,'字典管理',NULL,'sys/dict',0,5,'sys:dict:view','_self',0,0,'2020-03-21 18:24:26','2020-03-21 18:45:56'),(23,22,'查询字典',NULL,NULL,1,1,'sys:dict:list','_self',0,0,'2020-03-21 18:24:27','2020-03-21 18:44:42'),(24,22,'添加字典',NULL,NULL,1,2,'sys:dict:save','_self',0,0,'2020-03-21 18:24:28','2020-03-21 18:45:59'),(25,22,'修改字典',NULL,NULL,1,3,'sys:dict:update','_self',0,0,'2020-03-21 18:24:29','2020-03-21 18:46:01'),(26,22,'删除字典',NULL,NULL,1,4,'sys:dict:remove','_self',0,0,'2020-03-21 18:24:31','2020-03-21 18:46:02'),(27,0,'日志管理','layui-icon layui-icon-list',NULL,0,2,NULL,'_self',0,0,'2020-03-21 18:24:32','2020-03-21 18:46:07'),(28,27,'登录日志',NULL,'sys/loginRecord',0,1,'sys:login_record:view','_self',0,0,'2020-03-21 18:24:33','2020-03-21 18:44:52'),(29,27,'操作日志',NULL,'sys/operRecord',0,2,'sys:oper_record:view','_self',0,0,'2020-03-21 18:24:34','2020-03-21 18:46:10'),(30,27,'数据监控',NULL,'druid',0,3,NULL,'_self',0,0,'2020-03-21 18:24:35','2020-03-22 14:46:21'),(31,0,'系统工具','layui-icon layui-icon-slider',NULL,0,3,NULL,'_self',0,0,'2020-03-21 18:24:36','2020-03-22 14:47:49'),(32,31,'文件管理',NULL,'file/manage',0,1,'sys:file:view','_self',0,0,'2020-03-21 18:24:38','2020-03-22 14:46:57'),(33,32,'查询文件',NULL,NULL,1,1,'sys:file:list','_self',0,0,'2020-03-21 18:24:39','2020-03-22 14:47:32'),(34,32,'删除文件',NULL,NULL,1,2,'sys:file:remove','_self',0,0,'2020-03-21 18:24:40','2020-03-22 14:46:54'),(35,31,'发送邮件',NULL,'sys/email',0,2,'sys:email:view','_self',0,0,'2020-03-21 18:24:41','2020-03-22 14:47:35'),(36,31,'项目生成',NULL,NULL,0,3,NULL,'_self',0,0,'2020-03-21 18:24:42','2020-03-22 14:47:36');

/*Table structure for table `sys_oper_record` */

DROP TABLE IF EXISTS `sys_oper_record`;

CREATE TABLE `sys_oper_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `model` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作模块',
  `description` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作方法',
  `url` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求地址',
  `request_method` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求方式',
  `oper_method` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '调用方法',
  `param` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求参数',
  `result` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '返回结果',
  `ip` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ip地址',
  `comments` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `spend_time` int(11) DEFAULT NULL COMMENT '请求耗时,单位毫秒',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '状态,0成功,1异常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `sys_oper_record_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='操作日志';

/*Data for the table `sys_oper_record` */

/*Table structure for table `sys_organization` */

DROP TABLE IF EXISTS `sys_organization`;

CREATE TABLE `sys_organization` (
  `organization_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '机构id',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级id,0是顶级',
  `organization_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构名称',
  `organization_full_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '机构全称',
  `organization_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '机构代码',
  `organization_type` int(11) NOT NULL COMMENT '机构类型',
  `leader_id` int(11) DEFAULT NULL COMMENT '负责人id',
  `sort_number` int(11) NOT NULL DEFAULT '1' COMMENT '排序号',
  `comments` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除,0否,1是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`organization_id`),
  KEY `leader_id` (`leader_id`),
  CONSTRAINT `sys_organization_ibfk_1` FOREIGN KEY (`leader_id`) REFERENCES `sys_user` (`user_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='组织机构';

/*Data for the table `sys_organization` */

insert  into `sys_organization`(`organization_id`,`parent_id`,`organization_name`,`organization_full_name`,`organization_type`,`leader_id`,`sort_number`,`comments`,`deleted`,`create_time`,`update_time`) values (1,0,'XXX公司','XXXXXXXXX科技有限公司',3,NULL,1,'',0,'2020-03-15 13:14:55','2020-03-21 15:12:49'),(2,1,'研发部','研发部',5,NULL,2,'',0,'2020-03-15 13:15:16','2020-03-16 00:43:09'),(3,2,'高教组','高等教育行业项目组',6,NULL,3,'',0,'2020-03-15 13:15:45','2020-03-16 00:42:49'),(4,2,'政务组','政务行业项目组',6,NULL,4,'',0,'2020-03-15 13:16:15','2020-03-16 00:42:54'),(5,2,'制造组','生产制造行业项目组',6,NULL,5,'',0,'2020-03-15 13:16:37','2020-03-21 15:13:05'),(6,2,'仿真组','虚拟仿真行业项目组',6,NULL,6,'',0,'2020-03-15 13:16:57','2020-03-16 00:43:03'),(7,1,'测试部','测试部',5,NULL,6,'',0,'2020-03-15 13:17:19','2020-03-16 00:43:14'),(8,1,'设计部','UI设计部门',5,NULL,7,'',0,'2020-03-15 13:17:56','2020-03-16 00:43:18'),(9,1,'市场部','市场部',5,NULL,8,'',0,'2020-03-15 13:18:15','2020-03-16 00:43:23');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色标识',
  `comments` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除,0否,1是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色';

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`role_code`,`comments`,`deleted`,`create_time`,`update_time`) values (1,'管理员','admin','管理员',0,'2020-02-26 15:18:37','2020-03-21 15:15:54'),(2,'普通用户','user','普通用户',0,'2020-02-26 15:18:52','2020-03-21 15:16:02'),(3,'游客','guest','游客',0,'2020-02-26 15:19:49','2020-03-21 15:16:57');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_sys_role_permission_role` (`role_id`),
  KEY `menu_id` (`menu_id`),
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE,
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色权限';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`,`create_time`,`update_time`) values (1,1,1,'2020-02-27 00:43:06','2020-03-20 17:54:04'),(2,1,2,'2020-03-20 17:59:53','2020-03-20 18:00:30'),(3,1,3,'2020-03-20 17:59:55','2020-03-20 18:00:30'),(4,1,4,'2020-03-20 17:59:57','2020-03-20 18:00:32'),(5,1,5,'2020-03-20 17:59:59','2020-03-20 18:00:33'),(6,1,6,'2020-03-20 18:00:03','2020-03-20 18:00:34'),(7,1,7,'2020-03-20 18:00:05','2020-03-20 18:00:35'),(8,1,8,'2020-03-20 18:00:08','2020-03-20 18:00:36'),(9,1,9,'2020-03-20 18:00:10','2020-03-20 18:00:37'),(10,1,10,'2020-03-20 18:00:13','2020-03-20 18:00:39'),(11,1,11,'2020-03-20 18:00:15','2020-03-20 18:00:40'),(12,1,12,'2020-03-21 01:07:50','2020-03-21 15:19:57'),(13,1,13,'2020-03-21 16:47:41','2020-03-21 16:47:41'),(14,1,14,'2020-03-21 16:47:47','2020-03-21 16:47:47'),(15,1,15,'2020-03-22 00:23:11','2020-03-22 00:23:11'),(16,1,16,'2020-03-22 00:23:13','2020-03-22 00:23:13'),(17,1,17,'2020-03-22 00:23:16','2020-03-22 00:23:16'),(18,1,18,'2020-03-22 00:23:18','2020-03-22 00:23:18'),(19,1,19,'2020-03-22 00:23:20','2020-03-22 00:23:20'),(20,1,20,'2020-03-22 00:23:22','2020-03-22 00:23:22'),(21,1,21,'2020-03-22 00:23:25','2020-03-22 00:23:25'),(22,1,22,'2020-03-22 00:23:26','2020-03-22 00:23:26'),(23,1,23,'2020-03-22 00:23:29','2020-03-22 00:23:29'),(24,1,24,'2020-03-22 00:23:31','2020-03-22 00:23:31'),(25,1,25,'2020-03-22 00:23:33','2020-03-22 00:23:33'),(26,1,26,'2020-03-22 00:23:37','2020-03-22 00:23:37'),(27,1,27,'2020-03-22 00:23:40','2020-03-22 00:23:40'),(28,1,28,'2020-03-22 00:23:42','2020-03-22 00:23:42'),(29,1,29,'2020-03-22 00:23:46','2020-03-22 00:23:46'),(30,1,30,'2020-03-22 00:23:48','2020-03-22 00:23:48'),(31,1,31,'2020-03-22 00:23:51','2020-03-22 00:23:51'),(32,1,32,'2020-03-22 00:23:54','2020-03-22 00:23:54'),(33,1,33,'2020-03-22 00:23:56','2020-03-22 00:23:56'),(34,1,34,'2020-03-22 00:24:00','2020-03-22 00:24:00'),(35,1,35,'2020-03-22 00:24:02','2020-03-22 00:24:02'),(36,1,36,'2020-03-22 00:24:06','2020-03-22 00:24:06');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `nick_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `phone` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `email_verified` int(1) NOT NULL DEFAULT '0' COMMENT '邮箱是否验证,0否,1是',
  `true_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '身份证号',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `introduction` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '个人简介',
  `organization_id` int(11) DEFAULT NULL COMMENT '机构id',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态,0正常,1冻结',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除,0否,1是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  KEY `organization_id` (`organization_id`),
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`organization_id`) REFERENCES `sys_organization` (`organization_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`username`,`password`,`nick_name`,`avatar`,`sex`,`phone`,`email`,`email_verified`,`true_name`,`id_card`,`birthday`,`introduction`,`organization_id`,`state`,`deleted`,`create_time`,`update_time`) values (1,'admin','21232f297a57a5a743894a0e4a801fc3','管理员',NULL,1,'',NULL,0,NULL,NULL,NULL,NULL,NULL,0,0,'2020-01-13 14:43:52','2020-03-21 12:49:48');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_sys_user_role` (`user_id`),
  KEY `FK_sys_user_role_role` (`role_id`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户角色';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`,`create_time`,`update_time`) values (1,1,1,'2020-03-12 23:20:08','2020-03-21 15:18:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

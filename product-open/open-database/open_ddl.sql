set names utf8;

drop database if exists `product_system_open`;
create database if not exists `product_system_open`;

USE `product_system_open`;
SET FOREIGN_KEY_CHECKS=0;

-- Open module
DROP TABLE IF EXISTS `THIRD_SUPPORT_INFO`;
CREATE TABLE `THIRD_SUPPORT_INFO`(
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ORGANIZATION` varchar(100) NOT NULL,
  `ACCESS_NAME` varchar(100),
  `ACCESS_KEY` varchar(100),
  `ACCESS_SECRET_KEY` varchar(100),
  `ACCESS_IP` varchar(20),
  `CREATE_TIME` datetime,
  `UPDATE_TIME` datetime,
  `VALID_FLAG` varchar(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

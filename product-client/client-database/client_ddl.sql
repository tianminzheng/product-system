set names utf8;

drop database if exists `product_system_client`;
create database if not exists `product_system_client`;

USE `product_system_client`;
SET FOREIGN_KEY_CHECKS=0;

-- Client module
DROP TABLE IF EXISTS `CLIENT_ORDER`;
CREATE TABLE `CLIENT_ORDER` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NOT NULL,
  `VENDOR_ID` bigint(20) NOT NULL,
  `ORDER_STATUS` varchar(20),
  `CREATE_TIME` datetime,
  `UPDATE_TIME` datetime,
  `VALID_FLAG` varchar(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `CLIENT_ORDER_ITEM`;
CREATE TABLE `CLIENT_ORDER_ITEM` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` bigint(20) NOT NULL,
  `PRODUCT_CODE` varchar(100),
  `PRODUCT_NAME` varchar(100),
  `PRODUCT_PRICE` decimal(8,2),
  `ITEM_COUNT` decimal(8,2),
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

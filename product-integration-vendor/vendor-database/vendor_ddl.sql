set names utf8;

drop database if exists `product_vendor`;
create database if not exists `product_vendor`;

USE `product_vendor`;
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `VENDOR_PRODUCT`;
CREATE TABLE `VENDOR_PRODUCT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(20) NOT NULL,
  `NAME` varchar(100),
  `DESCRIPTION` varchar(100),
  `PRICE` decimal(8,2),
  `CREATE_TIME` datetime,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

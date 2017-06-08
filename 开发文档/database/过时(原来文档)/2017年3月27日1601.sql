/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.73-community : Database - dftsms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dftsms` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `dftsms`;

/*Table structure for table `t_account_modify` */

DROP TABLE IF EXISTS `t_account_modify`;

CREATE TABLE `t_account_modify` (
  `mod_id` char(16) DEFAULT NULL,
  `mod_name` varchar(20) DEFAULT NULL,
  `mod_phone` char(11) DEFAULT NULL,
  `mod_email` varchar(40) DEFAULT NULL,
  `mod_reg_time` datetime DEFAULT NULL,
  `mod_role` char(16) DEFAULT NULL,
  `mod_time` datetime DEFAULT NULL,
  `mod_ip` char(15) DEFAULT NULL,
  `mod_ device` tinyint(1) DEFAULT NULL,
  KEY `FK_t_account` (`mod_id`),
  KEY `FK_t_account_modify_role` (`mod_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_account_modify` */

LOCK TABLES `t_account_modify` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_business` */

DROP TABLE IF EXISTS `t_business`;

CREATE TABLE `t_business` (
  `pk_bus_id` char(16) NOT NULL,
  `bus_name` varchar(20) NOT NULL,
  `bus_pass` varchar(100) NOT NULL,
  `bus_email` varchar(40) DEFAULT NULL,
  `bus_phone` char(11) NOT NULL,
  `bus_identity` char(18) NOT NULL,
  `bus_time` datetime NOT NULL,
  `fk_ picture` char(16) DEFAULT NULL,
  `bus_status` tinyint(4) NOT NULL,
  PRIMARY KEY (`pk_bus_id`),
  KEY `FK_t_business` (`fk_ picture`),
  CONSTRAINT `FK_t_business` FOREIGN KEY (`fk_ picture`) REFERENCES `t_pictures` (`pk_pic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_business` */

LOCK TABLES `t_business` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_cart` */

DROP TABLE IF EXISTS `t_cart`;

CREATE TABLE `t_cart` (
  `pk_car_id` char(16) NOT NULL,
  `fk_goods` char(16) DEFAULT NULL,
  `car_quantity` int(2) DEFAULT NULL,
  `fk_customer` char(16) DEFAULT NULL,
  PRIMARY KEY (`pk_car_id`),
  KEY `FK_t_car_goods` (`fk_goods`),
  KEY `FK_t_car-customer` (`fk_customer`),
  CONSTRAINT `FK_t_car-customer` FOREIGN KEY (`fk_customer`) REFERENCES `t_customer` (`pk_cus_id`),
  CONSTRAINT `FK_t_car_goods` FOREIGN KEY (`fk_goods`) REFERENCES `t_goods` (`pk_goo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_cart` */

LOCK TABLES `t_cart` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_cheap` */

DROP TABLE IF EXISTS `t_cheap`;

CREATE TABLE `t_cheap` (
  `pk_che_id` char(16) NOT NULL,
  `che_nam` varchar(20) DEFAULT NULL,
  `fk_store` char(16) DEFAULT NULL,
  PRIMARY KEY (`pk_che_id`),
  KEY `FK_t_cheap` (`fk_store`),
  CONSTRAINT `FK_t_cheap` FOREIGN KEY (`fk_store`) REFERENCES `t_store` (`fk_sto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_cheap` */

LOCK TABLES `t_cheap` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `pk_com_id` char(16) NOT NULL,
  `com_level` int(2) DEFAULT NULL,
  `com_content` varchar(100) DEFAULT NULL,
  `fk_customer` char(16) DEFAULT NULL,
  `fk_store` char(16) DEFAULT NULL,
  PRIMARY KEY (`pk_com_id`),
  KEY `FK_t_comment_cus` (`fk_customer`),
  KEY `FK_t_comment_store` (`fk_store`),
  CONSTRAINT `FK_t_comment_cus` FOREIGN KEY (`fk_customer`) REFERENCES `t_customer` (`pk_cus_id`),
  CONSTRAINT `FK_t_comment_store` FOREIGN KEY (`fk_store`) REFERENCES `t_store` (`fk_sto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_comment` */

LOCK TABLES `t_comment` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_customer` */

DROP TABLE IF EXISTS `t_customer`;

CREATE TABLE `t_customer` (
  `pk_cus_id` char(16) NOT NULL,
  `cus_name` varbinary(20) NOT NULL,
  `cus_pass` varchar(100) NOT NULL,
  `cus_sex` tinyint(4) NOT NULL,
  `cus_email` varchar(40) NOT NULL,
  `cus_phone` char(11) NOT NULL,
  `cus_time` datetime NOT NULL,
  `cus_pic` char(1) NOT NULL,
  `cus_status` tinyint(4) NOT NULL,
  PRIMARY KEY (`pk_cus_id`),
  KEY `FK_t_customer` (`cus_pic`),
  CONSTRAINT `FK_t_customer` FOREIGN KEY (`cus_pic`) REFERENCES `t_pictures` (`pk_pic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_customer` */

LOCK TABLES `t_customer` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_customer_role` */

DROP TABLE IF EXISTS `t_customer_role`;

CREATE TABLE `t_customer_role` (
  `fk_customer` char(16) DEFAULT NULL,
  `fk_role` char(16) DEFAULT NULL,
  KEY `FK_customer` (`fk_customer`),
  KEY `FK_role` (`fk_role`),
  CONSTRAINT `FK_customer` FOREIGN KEY (`fk_customer`) REFERENCES `t_customer` (`pk_cus_id`),
  CONSTRAINT `FK_role` FOREIGN KEY (`fk_role`) REFERENCES `t_role` (`pk_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_customer_role` */

LOCK TABLES `t_customer_role` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_goods` */

DROP TABLE IF EXISTS `t_goods`;

CREATE TABLE `t_goods` (
  `pk_goo_id` char(16) NOT NULL,
  `goo_name` varchar(30) NOT NULL,
  `goo_price` varchar(30) NOT NULL,
  `goo_currprice` varchar(30) NOT NULL,
  `fk_store` char(16) DEFAULT NULL,
  `fk_store_category` char(16) DEFAULT NULL,
  `fk_picture_small` char(16) DEFAULT NULL,
  `fk_picture_big` char(16) DEFAULT NULL,
  `goo_extend01` varchar(20) DEFAULT NULL,
  `goo_extend02` varchar(20) DEFAULT NULL,
  `goo_extend03` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`pk_goo_id`),
  KEY `FK_t_goods_store` (`fk_store`),
  KEY `FK_t_goods_categry` (`fk_store_category`),
  KEY `FK_t_goods_big` (`fk_picture_big`),
  KEY `FK_t_goods_small` (`fk_picture_small`),
  CONSTRAINT `FK_t_goods_small` FOREIGN KEY (`fk_picture_small`) REFERENCES `t_pictures` (`pk_pic_id`),
  CONSTRAINT `FK_t_goods_big` FOREIGN KEY (`fk_picture_big`) REFERENCES `t_pictures` (`pk_pic_id`),
  CONSTRAINT `FK_t_goods_categry` FOREIGN KEY (`fk_store_category`) REFERENCES `t_store_category` (`pk_cat_id`),
  CONSTRAINT `FK_t_goods_store` FOREIGN KEY (`fk_store`) REFERENCES `t_store` (`fk_sto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_goods` */

LOCK TABLES `t_goods` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_logininfo` */

DROP TABLE IF EXISTS `t_logininfo`;

CREATE TABLE `t_logininfo` (
  `cus_id` char(16) DEFAULT NULL,
  `cus_ip` char(15) NOT NULL,
  `cus_device` tinyint(1) NOT NULL,
  `cus_time` datetime NOT NULL,
  KEY `FK_t_logininfo` (`cus_id`),
  CONSTRAINT `FK_t_logininfo` FOREIGN KEY (`cus_id`) REFERENCES `t_customer` (`pk_cus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_logininfo` */

LOCK TABLES `t_logininfo` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `pk_ord_id` char(16) NOT NULL,
  `ord_time` datetime DEFAULT NULL,
  `ord_total` varbinary(20) DEFAULT NULL,
  `ord_status` tinyint(1) DEFAULT NULL,
  `ord_address` varchar(30) DEFAULT NULL,
  `fk_customer` char(16) DEFAULT NULL,
  PRIMARY KEY (`pk_ord_id`),
  KEY `FK_t_order` (`fk_customer`),
  CONSTRAINT `FK_t_order` FOREIGN KEY (`fk_customer`) REFERENCES `t_customer` (`pk_cus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

LOCK TABLES `t_order` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_pictures` */

DROP TABLE IF EXISTS `t_pictures`;

CREATE TABLE `t_pictures` (
  `pk_pic_id` char(16) NOT NULL,
  `pic_url` varchar(100) NOT NULL,
  `pic_des` varchar(20) DEFAULT NULL,
  `pic_attr` char(1) DEFAULT NULL,
  PRIMARY KEY (`pk_pic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_pictures` */

LOCK TABLES `t_pictures` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_power` */

DROP TABLE IF EXISTS `t_power`;

CREATE TABLE `t_power` (
  `pk_pow_id` char(16) NOT NULL,
  `pow_describe` varchar(50) NOT NULL,
  PRIMARY KEY (`pk_pow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_power` */

LOCK TABLES `t_power` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `pk_role_id` char(16) NOT NULL,
  `rol_describe` char(10) NOT NULL,
  PRIMARY KEY (`pk_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

LOCK TABLES `t_role` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_role_power` */

DROP TABLE IF EXISTS `t_role_power`;

CREATE TABLE `t_role_power` (
  `fk_role` char(16) DEFAULT NULL,
  `fk_power` char(16) DEFAULT NULL,
  KEY `FK_power` (`fk_power`),
  KEY `FK_t_role` (`fk_role`),
  CONSTRAINT `FK_power` FOREIGN KEY (`fk_power`) REFERENCES `t_power` (`pk_pow_id`),
  CONSTRAINT `FK_t_role` FOREIGN KEY (`fk_role`) REFERENCES `t_role` (`pk_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_power` */

LOCK TABLES `t_role_power` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_store` */

DROP TABLE IF EXISTS `t_store`;

CREATE TABLE `t_store` (
  `fk_sto_id` char(16) NOT NULL,
  `sto_name` varchar(30) NOT NULL,
  `fk_picture` char(16) DEFAULT NULL,
  `sto_status` tinyint(1) NOT NULL,
  `sto_score` char(10) NOT NULL,
  `fk_address` char(1) DEFAULT NULL,
  PRIMARY KEY (`fk_sto_id`),
  KEY `FK_t_store_pic` (`fk_picture`),
  CONSTRAINT `FK_t_store_01` FOREIGN KEY (`fk_picture`) REFERENCES `t_pictures` (`pk_pic_id`),
  CONSTRAINT `FK_t_store_bus` FOREIGN KEY (`fk_sto_id`) REFERENCES `t_business` (`pk_bus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_store` */

LOCK TABLES `t_store` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_store_category` */

DROP TABLE IF EXISTS `t_store_category`;

CREATE TABLE `t_store_category` (
  `pk_cat_id` char(16) NOT NULL,
  `cat_name` varchar(10) DEFAULT NULL,
  `cat_pid` char(16) DEFAULT NULL,
  `cat_desc` int(2) DEFAULT NULL,
  `fk_store` char(16) DEFAULT NULL,
  PRIMARY KEY (`pk_cat_id`),
  KEY `FK_t_store_category` (`fk_store`),
  CONSTRAINT `FK_t_store_category` FOREIGN KEY (`fk_store`) REFERENCES `t_store` (`fk_sto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_store_category` */

LOCK TABLES `t_store_category` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_titemorder` */

DROP TABLE IF EXISTS `t_titemorder`;

CREATE TABLE `t_titemorder` (
  `pk_ite_id` char(16) NOT NULL,
  `ite_quantity` int(3) DEFAULT NULL,
  `ite_subtotal` varchar(30) DEFAULT NULL,
  `fk_goods` char(16) DEFAULT NULL,
  `goo_name` varchar(30) DEFAULT NULL,
  `goo_currprice` varchar(30) DEFAULT NULL,
  `goo_image_l` char(16) DEFAULT NULL,
  PRIMARY KEY (`pk_ite_id`),
  KEY `FK_t_titemorder_goods` (`fk_goods`),
  CONSTRAINT `FK_t_titemorder_goods` FOREIGN KEY (`fk_goods`) REFERENCES `t_goods` (`pk_goo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_titemorder` */

LOCK TABLES `t_titemorder` WRITE;

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

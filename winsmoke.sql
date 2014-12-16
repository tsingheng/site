/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : winsmoke

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2014-12-16 10:00:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for area_entity
-- ----------------------------
DROP TABLE IF EXISTS `area_entity`;
CREATE TABLE `area_entity` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AREA` bigint(20) DEFAULT NULL,
  `ENTITY_ID` bigint(20) DEFAULT NULL,
  `SORT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_5` (`AREA`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area_entity
-- ----------------------------
INSERT INTO `area_entity` VALUES ('6', '8', '1', '1');
INSERT INTO `area_entity` VALUES ('7', '8', '2', '1');
INSERT INTO `area_entity` VALUES ('8', '8', '4', '1');
INSERT INTO `area_entity` VALUES ('9', '8', '3', '1');
INSERT INTO `area_entity` VALUES ('10', '9', '1', '1');
INSERT INTO `area_entity` VALUES ('11', '9', '2', '1');
INSERT INTO `area_entity` VALUES ('12', '9', '4', '1');
INSERT INTO `area_entity` VALUES ('13', '9', '3', '1');
INSERT INTO `area_entity` VALUES ('14', '10', '1', '1');
INSERT INTO `area_entity` VALUES ('15', '10', '2', '1');
INSERT INTO `area_entity` VALUES ('16', '10', '4', '1');
INSERT INTO `area_entity` VALUES ('17', '10', '3', '1');

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PATH` varchar(200) DEFAULT NULL,
  `ORIGINAL_NAME` varchar(200) DEFAULT NULL,
  `INSERT_TIME` datetime DEFAULT NULL,
  `CREATER` varchar(10) DEFAULT NULL,
  `CONTENT_TYPE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='系统附件';

-- ----------------------------
-- Records of attachment
-- ----------------------------
INSERT INTO `attachment` VALUES ('1', 'upload/20131218/20131218221337017.JPG', '20130824135544056.JPG', '2013-12-18 22:13:37', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('2', 'upload/20131218/20131218221337482.JPG', '20130824135544923.JPG', '2013-12-18 22:13:37', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('3', 'upload/20131218/20131218221337303.JPG', '20130824135544655.JPG', '2013-12-18 22:13:37', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('4', 'upload/20131218/20131218221337885.JPG', '20130824135544218.JPG', '2013-12-18 22:13:37', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('5', 'upload/20131218/20131218221337060.JPG', '20130824135544700.JPG', '2013-12-18 22:13:37', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('6', 'upload/20131218/20131218221611240.JPG', '20130824140516646.JPG', '2013-12-18 22:16:11', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('7', 'upload/20131218/20131218221611271.JPG', '20130824140516292.JPG', '2013-12-18 22:16:11', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('8', 'upload/20131218/20131218221611783.JPG', '20130824140516950.JPG', '2013-12-18 22:16:11', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('9', 'upload/20131218/20131218221611715.JPG', '20130824140516039.JPG', '2013-12-18 22:16:11', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('10', 'upload/20131218/20131218221611003.JPG', '20130824140516364.JPG', '2013-12-18 22:16:11', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('11', 'upload/20131218/20131218221611174.JPG', '20130824140516816.JPG', '2013-12-18 22:16:11', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('12', 'upload/20131218/20131218221611221.JPG', '20130824140516974.JPG', '2013-12-18 22:16:11', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('13', 'upload/20131218/20131218221747742.JPG', '20130625065527319.JPG', '2013-12-18 22:17:47', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('14', 'upload/20131218/20131218221747046.jpg', '20130625065352838.jpg', '2013-12-18 22:17:47', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('15', 'upload/20131218/20131218221747720.jpg', '20130625065315792.jpg', '2013-12-18 22:17:47', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('16', 'upload/20131218/20131218221747801.jpg', '20130625063630156.jpg', '2013-12-18 22:17:47', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('17', 'upload/20131218/20131218221747745.jpg', '20130625065420418.jpg', '2013-12-18 22:17:47', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('18', 'upload/20131218/20131218221747204.jpg', '20130625065406511.jpg', '2013-12-18 22:17:47', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('19', 'upload/20131218/20131218221747355.jpg', '20130625065338389.jpg', '2013-12-18 22:17:47', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('20', 'upload/20131218/20131218221919247.jpg', '20130625082647899.jpg', '2013-12-18 22:19:19', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('21', 'upload/20131218/20131218221919603.jpg', '20130625082847964.jpg', '2013-12-18 22:19:19', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('22', 'upload/20131218/20131218221919854.jpg', '20130625082847304.jpg', '2013-12-18 22:19:19', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('23', 'upload/20131218/20131218221919838.JPG', '20130625082847005.JPG', '2013-12-18 22:19:19', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('24', 'upload/20131218/20131218221919872.JPG', '20130625081326744.JPG', '2013-12-18 22:19:19', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('25', 'upload/20131218/20131218221919564.JPG', '20130625082847460.JPG', '2013-12-18 22:19:19', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('26', 'upload/20131218/20131218222042474.jpg', '20130621090848590.jpg', '2013-12-18 22:20:42', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('27', 'upload/20131218/20131218222055237.jpg', '20130621090831698.jpg', '2013-12-18 22:20:55', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('28', 'upload/20131218/20131218222115506.jpg', '20130621085152628.jpg', '2013-12-18 22:21:15', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('29', 'upload/20131218/20131218222128638.jpg', '20130621084943588.jpg', '2013-12-18 22:21:28', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('30', 'upload/20131218/20131218231820452.jpg', '2013.5.24_1.49.40_6526.jpg', '2013-12-18 23:18:20', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('31', 'upload/20131218/20131218232801680.jpg', '2013.5.24_1.50.53_1084.jpg', '2013-12-18 23:28:01', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('32', 'upload/20131218/20131218232810637.jpg', '2013.5.24_1.54.17_7061.jpg', '2013-12-18 23:28:10', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('33', 'upload/20131218/20131218232818103.jpg', '2013.5.24_2.11.14_4528.jpg', '2013-12-18 23:28:18', null, 'image/jpeg');
INSERT INTO `attachment` VALUES ('34', 'upload/20141216/20141216095421840.jpg', 'Koala.jpg', '2014-12-16 09:54:21', null, 'image/jpeg');

-- ----------------------------
-- Table structure for image_display
-- ----------------------------
DROP TABLE IF EXISTS `image_display`;
CREATE TABLE `image_display` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_CODE` varchar(50) DEFAULT NULL,
  `ATTACHMENT` bigint(20) DEFAULT NULL,
  `TITLE` varchar(100) DEFAULT NULL,
  `MEMO` varchar(200) DEFAULT NULL,
  `INSERT_TIME` datetime DEFAULT NULL,
  `CREATER` varchar(20) DEFAULT NULL,
  `SORT` int(11) DEFAULT NULL,
  `PUBLISHED` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_8` (`ATTACHMENT`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`ATTACHMENT`) REFERENCES `attachment` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image_display
-- ----------------------------
INSERT INTO `image_display` VALUES ('1', 'factory', '26', 'Production Line', null, '2013-12-18 22:20:42', null, '1', '');
INSERT INTO `image_display` VALUES ('2', 'factory', '27', 'Production Line', null, '2013-12-18 22:20:55', null, '2', '');
INSERT INTO `image_display` VALUES ('3', 'factory', '28', 'Products', null, '2013-12-18 22:21:15', null, '3', '');
INSERT INTO `image_display` VALUES ('4', 'factory', '29', 'Production Line', null, '2013-12-18 22:21:28', null, '4', '');

-- ----------------------------
-- Table structure for index_area
-- ----------------------------
DROP TABLE IF EXISTS `index_area`;
CREATE TABLE `index_area` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AREA_NAME` varchar(20) DEFAULT NULL,
  `CONTENT_TYPE` varchar(20) DEFAULT NULL,
  `VIEW_TYPE` varchar(20) DEFAULT NULL,
  `AREA_CODE` varchar(20) DEFAULT NULL,
  `MEMO` varchar(200) DEFAULT NULL,
  `ORIENTATION` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of index_area
-- ----------------------------
INSERT INTO `index_area` VALUES ('8', '图片滚动', '1', '1', 'scroll_image', '', '1');
INSERT INTO `index_area` VALUES ('9', 'Hot Product', '3', '4', 'hot_product', '', '2');
INSERT INTO `index_area` VALUES ('10', 'Top Product', '3', '1', 'top_product', '', '1');

-- ----------------------------
-- Table structure for info
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(100) DEFAULT NULL,
  `MINOR_TITLE` varchar(100) DEFAULT NULL,
  `TYPE_CODE` varchar(50) DEFAULT NULL,
  `INSERT_TIME` datetime DEFAULT NULL,
  `CREATER` varchar(20) DEFAULT NULL,
  `VIEW_TIMES` int(11) DEFAULT NULL,
  `TAGS` text,
  `SORT` int(11) DEFAULT NULL,
  `CONTENT` text,
  `PUBLISHED` bit(1) DEFAULT NULL,
  `MEMO` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES ('1', 'About Us', '', 'about', '2013-09-22 15:31:12', null, null, '', '1', '<p>\n	Shenzhen Winsmoke Technology Co., Ltd. was founded in 2009, by a unique team of researchers, developers, and business experts, having been devoting itself to an enterprise that integrated production,R&amp;D,sales and service.\n</p>\n<p>\n	<br />\n</p>\n<p>\n	We have been developing healthier and safer electronic cigarette with innovative and higher technology all the time as an experienced researcher and leader in the field of electronic cigarette.\n</p>\n<p>\n	<br />\n</p>\n<p>\n	Our team has extensive experience in product and brand&nbsp;development, manufacturing, and have been in the smoking industry for years. We, personally, tried and tested each and every product before offering them to you. Being one of the finest industry to offer electronic cigarettes to the whole world, we are obligated to bring you only the BEST!!! Winsmoke is known for its exceptionally high quality products at affordable prices, outstanding customer service, and user friendly ordering process.\n</p>\n<p>\n	<br />\n</p>\n<p>\n	Winsmoke&nbsp;has several advantages over traditional cigarettes:\n</p>\n<ul>\n	<li>\n		No ashes or flames to deal with.\n	</li>\n	<li>\n		High quality and flavorful Vapor for a Smoke-Like Feeling, without the messy ashes and residue.\n	</li>\n	<li>\n		No offensive odors that stick to linen, clothing, furniture walls or your office, house or car.\n	</li>\n	<li>\n		Save tons of money compared to highly taxed traditional cigarettes.\n	</li>\n	<li>\n		Smoke your electronic cigarette virtually anywhere, inside many restaurants, bars, airports and in many places where traditional smoking is restricted or prohibited. After all, it is just vapor and leaves no lingering effect.\n	</li>\n</ul>\n<p>\n	At Winsmoke we strive to provide our customers with friendly, knowledgeable and effective customer service.&nbsp;&nbsp;We value your opinions, comments and concerns.\n</p>\n<p>\n	So please feel free to contact us for any reason!\n</p>', '\0', '');
INSERT INTO `info` VALUES ('2', 'Contact Us', '', 'contact', '2013-09-22 15:31:31', null, null, '', '1', '<h2>\n	Shenzhen Winsmoke Technology Co., Ltd.\n</h2>\n<p>\n	<br />\n</p>\n<p>\n	<table cellspacing=\"1\" cellpadding=\"4\" class=\"tbl1 ke-zeroborder\" style=\"background-color:#E6E6E6;width:728px;color:#333333;font-family:Arial, 宋体;font-size:12px;height:30px;\">\n		<tbody>\n			<tr>\n				<th style=\"background-color:#F5F5F5;text-align:right;font-weight:normal;\">\n					<span style=\"font-size:16px;\">Company :</span> \n				</th>\n				<td style=\"background-color:#FFFFFF;\">\n					<span style=\"font-size:16px;\">Shenzhen Winsmoke Technology Co., Ltd.</span> \n				</td>\n			</tr>\n			<tr>\n				<th style=\"background-color:#F5F5F5;text-align:right;font-weight:normal;\">\n					<span style=\"font-size:16px;\">Contact :</span> \n				</th>\n				<td style=\"background-color:#FFFFFF;\">\n					<span style=\"font-size:16px;\">Mr.Peter</span><br />\n				</td>\n			</tr>\n			<tr>\n				<th style=\"background-color:#F5F5F5;text-align:right;font-weight:normal;\">\n					<span style=\"font-size:16px;\">Mobile :</span> \n				</th>\n				<td style=\"background-color:#FFFFFF;\">\n					<span style=\"font-size:16px;\">+86 18201554585</span> \n				</td>\n			</tr>\n			<tr>\n				<th style=\"background-color:#F5F5F5;text-align:right;font-weight:normal;\">\n					<span style=\"font-size:16px;\">Tel:</span> \n				</th>\n				<td style=\"background-color:#FFFFFF;\">\n					<span style=\"font-size:16px;\">+86 18201554585</span> \n				</td>\n			</tr>\n			<tr>\n				<th style=\"background-color:#F5F5F5;text-align:right;font-weight:normal;\">\n					<span style=\"font-size:16px;\">Fax :</span> \n				</th>\n				<td style=\"background-color:#FFFFFF;\">\n					<span style=\"font-size:16px;\">+86 18201554585</span> \n				</td>\n			</tr>\n			<tr>\n				<th style=\"background-color:#F5F5F5;text-align:right;font-weight:normal;\">\n					<span style=\"font-size:16px;\">Zip Code :</span> \n				</th>\n				<td style=\"background-color:#FFFFFF;\">\n					<span style=\"font-size:16px;\">518104</span> \n				</td>\n			</tr>\n			<tr>\n				<th style=\"background-color:#F5F5F5;text-align:right;font-weight:normal;\">\n					<span style=\"font-size:16px;\">Web Site:</span> \n				</th>\n				<td style=\"background-color:#FFFFFF;\">\n					<span style=\"font-size:16px;\"><a href=\"http://www.winsmoke.com/\" target=\"_blank\">www.winsmoke.com</a></span> \n				</td>\n			</tr>\n			<tr>\n				<th style=\"background-color:#F5F5F5;text-align:right;font-weight:normal;\">\n					<span style=\"font-size:16px;\">E-mail:</span> \n				</th>\n				<td style=\"background-color:#FFFFFF;\">\n					<span style=\"font-size:16px;\"><a href=\"mailto:winsmoke@163.com\" target=\"_blank\">winsmoke@163.com</a></span> \n				</td>\n			</tr>\n			<tr>\n				<th style=\"background-color:#F5F5F5;text-align:right;font-weight:normal;\">\n					<span style=\"font-size:16px;\">MSN :</span> \n				</th>\n				<td style=\"background-color:#FFFFFF;\">\n					<span style=\"font-size:16px;\"><a href=\"mailto:winsmoke100@msn.cn\" target=\"_blank\">winsmoke100@msn.cn</a></span> \n				</td>\n			</tr>\n			<tr>\n				<th style=\"background-color:#F5F5F5;text-align:right;font-weight:normal;\">\n					<span style=\"font-size:16px;\">Address :</span> \n				</th>\n				<td style=\"background-color:#FFFFFF;\">\n					<span style=\"font-size:16px;\">Shajing, Shenzhen, Guangdong, China, 518104</span> \n				</td>\n			</tr>\n		</tbody>\n	</table>\n</p>\n<p>\n	<br />\n</p>', '', '');

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOG_TIME` datetime DEFAULT NULL,
  `USER_NAME` varchar(50) DEFAULT NULL,
  `LOG_IP` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_log
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SUBJECT` varchar(200) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `CUSTOMER_NAME` varchar(100) DEFAULT NULL,
  `MSG_CONTENT` text,
  `COMPANY` varchar(200) DEFAULT NULL,
  `TEL` varchar(20) DEFAULT NULL,
  `FAX` varchar(20) DEFAULT NULL,
  `COUNTRY` varchar(200) DEFAULT NULL,
  `ADDRESS` varchar(500) DEFAULT NULL,
  `SEND_TIME` datetime DEFAULT NULL,
  `DEAL_TIME` datetime DEFAULT NULL,
  `MEMO` varchar(500) DEFAULT NULL,
  `DEALER` varchar(20) DEFAULT NULL,
  `IP` varchar(15) DEFAULT NULL,
  `DEALED` bit(1) DEFAULT b'0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for message_file
-- ----------------------------
DROP TABLE IF EXISTS `message_file`;
CREATE TABLE `message_file` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MESSAGE` bigint(20) DEFAULT NULL,
  `ATTACHMENT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_10` (`MESSAGE`),
  KEY `FK_Reference_9` (`ATTACHMENT`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`MESSAGE`) REFERENCES `message` (`ID`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`ATTACHMENT`) REFERENCES `attachment` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message_file
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PRODUCT_NAME` varchar(30) DEFAULT NULL,
  `CATEGORY` bigint(20) DEFAULT NULL,
  `DESCRIPTION` text,
  `INSERT_TIME` datetime DEFAULT NULL,
  `VIEW_TIMES` int(11) DEFAULT NULL,
  `CREATER` varchar(10) DEFAULT NULL,
  `TAGS` text,
  `SORT` int(11) DEFAULT NULL,
  `PRICE` float DEFAULT NULL,
  `MEMO` varchar(100) DEFAULT NULL,
  `PUBLISHED` bit(1) DEFAULT NULL,
  `BUY_LINK` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_1` (`CATEGORY`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`CATEGORY`) REFERENCES `pro_category` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='产品表';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', 'Metal injection bottle', '7', 'Easy to carry, can be filled with 5 ml of smoke oil<img src=\"/test/upload/20141216/20141216095345722.jpg\" alt=\"\" /><img src=\"/test/upload/20141216/20141216095346628.jpg\" alt=\"\" /><img src=\"/test/upload/20141216/20141216095329181.jpg\" alt=\"\" />', '2013-12-18 22:13:37', '0', null, '', '1', null, '', '', '');
INSERT INTO `product` VALUES ('2', 'Protank', '6', 'Protank is a detachable, at the bottom of the heater, the heater can be replaced, a handstand is a product of oil atomizer', '2013-12-18 22:16:11', '0', null, '', '1', null, '', '', '');
INSERT INTO `product` VALUES ('3', 'BCC', '5', '', '2013-12-18 22:17:47', '0', null, '', '1', null, '', '', '');
INSERT INTO `product` VALUES ('4', 'BCC', '5', '', '2013-12-18 22:19:19', '0', null, '', '2', null, '', '', '');

-- ----------------------------
-- Table structure for product_image
-- ----------------------------
DROP TABLE IF EXISTS `product_image`;
CREATE TABLE `product_image` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PRODUCT` bigint(20) DEFAULT NULL,
  `ATTACHMENT` bigint(20) DEFAULT NULL,
  `SORT` int(11) DEFAULT NULL,
  `CREATER` varchar(10) DEFAULT NULL,
  `INSERT_TIME` datetime DEFAULT NULL,
  `MEMO` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_2` (`ATTACHMENT`),
  KEY `FK_Reference_3` (`PRODUCT`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`ATTACHMENT`) REFERENCES `attachment` (`ID`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`PRODUCT`) REFERENCES `product` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_image
-- ----------------------------
INSERT INTO `product_image` VALUES ('1', '1', '1', '4', null, '2013-12-18 22:13:37', null);
INSERT INTO `product_image` VALUES ('2', '1', '2', '2', null, '2013-12-18 22:13:37', null);
INSERT INTO `product_image` VALUES ('3', '1', '3', '1', null, '2013-12-18 22:13:37', null);
INSERT INTO `product_image` VALUES ('4', '1', '4', '3', null, '2013-12-18 22:13:37', null);
INSERT INTO `product_image` VALUES ('5', '1', '5', '5', null, '2013-12-18 22:13:37', null);
INSERT INTO `product_image` VALUES ('6', '2', '6', '2', null, '2013-12-18 22:16:11', null);
INSERT INTO `product_image` VALUES ('7', '2', '7', '6', null, '2013-12-18 22:16:11', null);
INSERT INTO `product_image` VALUES ('8', '2', '8', '5', null, '2013-12-18 22:16:11', null);
INSERT INTO `product_image` VALUES ('9', '2', '9', '7', null, '2013-12-18 22:16:11', null);
INSERT INTO `product_image` VALUES ('10', '2', '10', '4', null, '2013-12-18 22:16:11', null);
INSERT INTO `product_image` VALUES ('11', '2', '11', '3', null, '2013-12-18 22:16:11', null);
INSERT INTO `product_image` VALUES ('12', '2', '12', '1', null, '2013-12-18 22:16:11', null);
INSERT INTO `product_image` VALUES ('13', '3', '13', '5', null, '2013-12-18 22:17:47', null);
INSERT INTO `product_image` VALUES ('14', '3', '14', '2', null, '2013-12-18 22:17:47', null);
INSERT INTO `product_image` VALUES ('15', '3', '15', '7', null, '2013-12-18 22:17:47', null);
INSERT INTO `product_image` VALUES ('16', '3', '16', '3', null, '2013-12-18 22:17:47', null);
INSERT INTO `product_image` VALUES ('17', '3', '17', '6', null, '2013-12-18 22:17:47', null);
INSERT INTO `product_image` VALUES ('18', '3', '18', '4', null, '2013-12-18 22:17:47', null);
INSERT INTO `product_image` VALUES ('19', '3', '19', '1', null, '2013-12-18 22:17:47', null);
INSERT INTO `product_image` VALUES ('21', '4', '21', '4', null, '2013-12-18 22:19:19', null);
INSERT INTO `product_image` VALUES ('22', '4', '22', '1', null, '2013-12-18 22:19:19', null);
INSERT INTO `product_image` VALUES ('23', '4', '23', '5', null, '2013-12-18 22:19:19', null);
INSERT INTO `product_image` VALUES ('24', '4', '24', '3', null, '2013-12-18 22:19:19', null);
INSERT INTO `product_image` VALUES ('25', '4', '25', '2', null, '2013-12-18 22:19:19', null);
INSERT INTO `product_image` VALUES ('26', '4', '34', '6', null, '2014-12-16 09:54:21', null);

-- ----------------------------
-- Table structure for pro_category
-- ----------------------------
DROP TABLE IF EXISTS `pro_category`;
CREATE TABLE `pro_category` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(30) DEFAULT NULL,
  `SORT` int(11) DEFAULT NULL,
  `MEMO` varchar(100) DEFAULT NULL,
  `INSERT_TIME` datetime DEFAULT NULL,
  `CREATER` varchar(10) DEFAULT NULL,
  `PARENT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='产品类别';

-- ----------------------------
-- Records of pro_category
-- ----------------------------
INSERT INTO `pro_category` VALUES ('0', 'ROOT', '1', ' ', '2013-12-17 23:07:41', null, null);
INSERT INTO `pro_category` VALUES ('5', 'BCC', '1', '', '2013-12-17 23:07:21', null, '0');
INSERT INTO `pro_category` VALUES ('6', 'Protank', '2', '', '2013-12-17 23:07:32', null, '0');
INSERT INTO `pro_category` VALUES ('7', 'Metal injection bottle', '3', '', '2013-12-17 23:07:41', null, '0');

-- ----------------------------
-- Table structure for pro_property
-- ----------------------------
DROP TABLE IF EXISTS `pro_property`;
CREATE TABLE `pro_property` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PRODUCT_ID` bigint(20) DEFAULT NULL,
  `PROPERTY_NAME` varchar(255) DEFAULT NULL,
  `PROPERTY_VALUE` varchar(255) DEFAULT NULL,
  `SORT` int(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pro_property
-- ----------------------------
INSERT INTO `pro_property` VALUES ('1', '1', '', '', '1');
INSERT INTO `pro_property` VALUES ('2', '2', '', '', '2');
INSERT INTO `pro_property` VALUES ('3', '3', '', '', '3');
INSERT INTO `pro_property` VALUES ('4', '4', '', '', '4');
INSERT INTO `pro_property` VALUES ('5', '3', '', '', '5');
INSERT INTO `pro_property` VALUES ('6', '1', '', '', '6');
INSERT INTO `pro_property` VALUES ('7', '2', '', '', '7');
INSERT INTO `pro_property` VALUES ('8', '4', '', '', '8');
INSERT INTO `pro_property` VALUES ('9', '1', '', '', null);
INSERT INTO `pro_property` VALUES ('10', '1', '', '', null);

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `RESOURCE_NAME` varchar(20) DEFAULT NULL,
  `RESOURCE_CODE` varchar(20) DEFAULT NULL,
  `RESOURCE_TYPE` bit(1) DEFAULT NULL,
  `RESOURCE_URI` varchar(100) DEFAULT NULL,
  `PARENT` bigint(20) DEFAULT NULL,
  `SORT` int(11) DEFAULT NULL,
  `ICON_CLS` varchar(20) DEFAULT NULL,
  `LEAF` bit(1) DEFAULT NULL,
  `MEMO` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '系统管理', 'sys', '\0', '', '0', '4', '', '\0', '系统管理模块');
INSERT INTO `resource` VALUES ('2', '资源管理', 'sys_res', '\0', '/resource.action', '1', '1', '', '', '系统资源管理');
INSERT INTO `resource` VALUES ('3', '添加资源', 'sys_res_add', '', '/resource!add.action', '2', '1', '', '\0', '添加系统资源按钮');
INSERT INTO `resource` VALUES ('13', '编辑资源', 'sys_res_edit', '', '/resource!edit.action', '2', '2', '', '\0', '');
INSERT INTO `resource` VALUES ('14', '移动顺序', 'sys_res_sort', '', '/resource!sort.action', '2', '4', '', '\0', '');
INSERT INTO `resource` VALUES ('22', '删除资源', 'sys_res_del', '', '/resource!del.action', '2', '3', '', '\0', '');
INSERT INTO `resource` VALUES ('23', '角色管理', 'sys_role', '\0', '/role.action', '1', '2', '', '', '系统角色管理');
INSERT INTO `resource` VALUES ('24', '用户管理', 'sys_user', '\0', '/user.action', '1', '3', '', '', '');
INSERT INTO `resource` VALUES ('25', '产品管理', 'pro', '\0', '', '0', '1', '', '\0', '产品管理模块');
INSERT INTO `resource` VALUES ('26', '产品系列管理', 'pro_cate', '\0', '/pro-category.action', '25', '1', '', '', '');
INSERT INTO `resource` VALUES ('27', '产品管理', 'pro_pro', '\0', '/product.action', '25', '2', '', '', '');
INSERT INTO `resource` VALUES ('28', '客户关系管理', 'cust', '\0', '', '0', '2', '', '\0', '');
INSERT INTO `resource` VALUES ('29', '客户留言管理', 'cust_msg', '\0', '/message.action', '28', '1', '', '', '');
INSERT INTO `resource` VALUES ('30', '网站内容管理', 'site', '\0', '', '0', '3', '', '\0', '');
INSERT INTO `resource` VALUES ('31', '关于我们', 'site_info_about', '\0', '/info.action?type=about', '30', '1', '', '', '');
INSERT INTO `resource` VALUES ('32', '联系我们', 'site_info_contact', '\0', '/info.action?type=contact', '30', '2', '', '', '');
INSERT INTO `resource` VALUES ('33', '新闻管理', 'site_info_news', '\0', '/info.action?type=news', '30', '3', '', '', '');
INSERT INTO `resource` VALUES ('34', '照片展示', 'site_img_factory', '\0', '/image-display.action?type=factory', '30', '4', '', '', '');
INSERT INTO `resource` VALUES ('35', '幻灯片', 'site_rotate', '\0', '/rotate-image.action', '30', '5', '', '', '');
INSERT INTO `resource` VALUES ('36', '网站属性', 'site_property', '\0', '/site-property!edit.action', '30', '6', '', '', '');
INSERT INTO `resource` VALUES ('37', '添加角色', 'sys_role_add', '', '/role!add.action', '23', '1', '', '\0', '');
INSERT INTO `resource` VALUES ('38', '编辑角色', 'sys_role_edit', '', '/role!edit.action', '23', '2', '', '\0', '');
INSERT INTO `resource` VALUES ('39', '角色授权', 'sys_role_auth', '', '/role!auth.action', '23', '3', '', '\0', '');
INSERT INTO `resource` VALUES ('40', '删除角色', 'sys_role_del', '', '/role!del.action', '23', '4', '', '\0', '');
INSERT INTO `resource` VALUES ('41', '添加用户', 'sys_user_add', '', '/user!add.action', '24', '1', '', '', '');
INSERT INTO `resource` VALUES ('42', '编辑用户', 'sys_user_edit', '', '/user!edit.action', '24', '2', '', '\0', '');
INSERT INTO `resource` VALUES ('43', '删除用户', 'sys_user_del', '', '/user!del.action', '24', '3', '', '\0', '');
INSERT INTO `resource` VALUES ('44', '添加产品系列', 'pro_cate_add', '', '/pro-category!add.action', '26', '1', '', '\0', '');
INSERT INTO `resource` VALUES ('45', '编辑产品系列', 'pro_cate_edit', '', '/pro-category!edit.action', '26', '2', '', '\0', '');
INSERT INTO `resource` VALUES ('46', '删除产品系列', 'pro_cate_del', '', '/pro-category!del.action', '26', '3', '', '\0', '');
INSERT INTO `resource` VALUES ('47', '添加产品', 'pro_pro_add', '', '/product!add.action', '27', '1', '', '\0', '');
INSERT INTO `resource` VALUES ('48', '编辑产品', 'pro_pro_edit', '', '/product!edit.action', '27', '2', '', '\0', '');
INSERT INTO `resource` VALUES ('49', '删除产品', 'pro_pro_del', '', '/product!del.action', '27', '3', '', '\0', '');
INSERT INTO `resource` VALUES ('50', '添加图片', 'pro_image_add', '', '/pro-image!add.action', '27', '4', '', '\0', '');
INSERT INTO `resource` VALUES ('51', '图片备注', 'pro_image_edit', '', '/pro-image!edit.action', '27', '5', '', '\0', '');
INSERT INTO `resource` VALUES ('52', '删除图片', 'pro_image_del', '', '/pro-image!del.action', '27', '6', '', '\0', '');
INSERT INTO `resource` VALUES ('53', '留言处理', 'cust_message_deal', '', '/message!deal.action', '29', '1', '', '\0', '');
INSERT INTO `resource` VALUES ('54', '添加文章', 'site_info_about_add', '', '/info!add.action?type=about', '31', '1', '', '\0', '');
INSERT INTO `resource` VALUES ('55', '编辑文章', 'site_info_about_edit', '', '/info!edit.action?type=about', '31', '2', '', '\0', '');
INSERT INTO `resource` VALUES ('56', '删除文章', 'site_info_about_del', '', '/info!del.action?type=about', '31', '3', '', '\0', '');
INSERT INTO `resource` VALUES ('57', '添加文章', 'site_info_con_add', '', '/info!add.action?type=contact', '32', '1', '', '\0', '');
INSERT INTO `resource` VALUES ('58', '编辑文章', 'site_info_con_edit', '', '/info!edit.action?type=contact', '32', '2', '', '\0', '');
INSERT INTO `resource` VALUES ('59', '删除文章', 'site_info_con_del', '', '/info!del.action?type=contact', '32', '3', '', '\0', '');
INSERT INTO `resource` VALUES ('60', '添加新闻', 'site_info_news_add', '', '/info!add.action?type=news', '33', '1', '', '\0', '');
INSERT INTO `resource` VALUES ('61', '编辑新闻', 'site_info_news_edit', '', '/info!edit.action?type=news', '33', '2', '', '\0', '');
INSERT INTO `resource` VALUES ('62', '删除新闻', 'site_info_news_del', '', '/info!del.action?type=news', '33', '3', '', '\0', '');
INSERT INTO `resource` VALUES ('63', '图片上传', 'site_img_fct_add', '', '/image-display!add.action?type=factory', '34', '1', '', '\0', '');
INSERT INTO `resource` VALUES ('64', '编辑图片', 'site_img_fct_edit', '', '/image-display!edit.action?type=factory', '34', '2', '', '\0', '');
INSERT INTO `resource` VALUES ('65', '删除照片', 'site_img_fct_del', '', '/image-display!del.action?type=factory', '34', '3', '', '\0', '');
INSERT INTO `resource` VALUES ('66', '上传图片', 'site_rotate_add', '', '/rotate-image!add.action', '35', '1', '', '\0', '');
INSERT INTO `resource` VALUES ('67', '编辑图片', 'site_rotate_edit', '', '/rotate-image!edit.action', '35', '2', '', '\0', '');
INSERT INTO `resource` VALUES ('68', '删除图片', 'site_rotate_del', '', '/rotate-image!del.action', '35', '3', '', '\0', '');
INSERT INTO `resource` VALUES ('69', '移动', 'site_rotate_sort', '', '/rotate-image!sort.action', '35', '4', '', '\0', '');
INSERT INTO `resource` VALUES ('70', '发布', 'site_rotate_pub', '', '/rotate-image!publish.action', '35', '5', '', '\0', '');
INSERT INTO `resource` VALUES ('71', '取消发布', 'site_rotate_can', '', '/rotate-image!cancel.action', '35', '6', '', '\0', '');
INSERT INTO `resource` VALUES ('72', '移动', 'site_img_fct_sort', '', '/image-display!sort.action?type=factory', '34', '4', '', '\0', '');
INSERT INTO `resource` VALUES ('73', '发布', 'site_img_fct_pub', '', '/image-display!publish.action?type=factory', '34', '5', '', '\0', '');
INSERT INTO `resource` VALUES ('74', '取消发布', 'site_img_fct_can', '', '/image-display!cancel.action?type=factory', '34', '6', '', '\0', '');
INSERT INTO `resource` VALUES ('75', '排序', 'site_info_news_sort', '', '/info!sort.action?type=news', '33', '4', '', '\0', '');
INSERT INTO `resource` VALUES ('76', '发布', 'site_info_news_pub', '', '/info!publish.action?type=news', '33', '5', '', '\0', '');
INSERT INTO `resource` VALUES ('77', '取消发布', 'site_info_news_can', '', '/info!cancel.action?type=news', '33', '6', '', '\0', '');
INSERT INTO `resource` VALUES ('78', '排序', 'site_info_con_sort', '', '/info!sort.action?type=contact', '32', '4', '', '\0', '');
INSERT INTO `resource` VALUES ('79', '发布', 'site_info_con_pub', '', '/info!publish.action?type=contact', '32', '5', '', '\0', '');
INSERT INTO `resource` VALUES ('80', '取消发布', 'site_info_con_can', '', '/info!cancel.action?type=contact', '32', '6', '', '\0', '');
INSERT INTO `resource` VALUES ('81', '排序', 'site_info_about_sort', '', '/info!sort.action?type=about', '31', '4', '', '\0', '');
INSERT INTO `resource` VALUES ('82', '发布', 'site_info_about_pub', '', '/info!publish.action?type=about', '31', '5', '', '\0', '');
INSERT INTO `resource` VALUES ('83', '取消发布', 'site_info_about_can', '', '/info!cancel.action?type=about', '31', '6', '', '\0', '');
INSERT INTO `resource` VALUES ('84', '移动', 'pro_cate_sort', '', '/pro-category!sort.action', '26', '4', '', '\0', '');
INSERT INTO `resource` VALUES ('85', '移动产品', 'pro_pro_sort', '', '/product!sort.action', '27', '7', '', '\0', '');
INSERT INTO `resource` VALUES ('86', '发布', 'pro_pro_pub', '', '/product!publish.action', '27', '8', '', '\0', '');
INSERT INTO `resource` VALUES ('87', '取消发布', 'pro_pro_can', '', '/product!cancel.action', '27', '9', '', '\0', '');
INSERT INTO `resource` VALUES ('88', '移动图片', 'pro_image_sort', '', '/pro-image!sort.action', '27', '10', '', '\0', '');
INSERT INTO `resource` VALUES ('89', '首页管理', 'site_inde', '\0', '/index-area.action', '30', '7', '', '', '');
INSERT INTO `resource` VALUES ('90', '首页板块', 'site_index_area', '', '/area-entity.action', '89', '1', '', '\0', '');
INSERT INTO `resource` VALUES ('91', '区域内容添加', 'site_index_area_add', '', '/area-entity!form.action', '89', '2', '', '\0', '');
INSERT INTO `resource` VALUES ('92', '区域添加', 'site_index_add', '', '/index-area!add.action', '89', '3', '', '\0', '');
INSERT INTO `resource` VALUES ('93', '编辑首页区域', 'site_index_edit', '', '/index-area!edit.action', '89', '4', '', '\0', '');
INSERT INTO `resource` VALUES ('94', '删除区域', 'site_index_del', '', '/index-area!del.action', '89', '5', '', '\0', '');
INSERT INTO `resource` VALUES ('95', '删除区域记录', 'site_inex_entity_del', '', '/area-entity!del.action', '89', '6', '', '\0', '');
INSERT INTO `resource` VALUES ('96', '添加到首页', 'site_inex_entity_add', '', '/area-entity!add.action', '89', '7', '', '\0', '');
INSERT INTO `resource` VALUES ('97', '记录排序', 'site_index_area_sort', '', '/area-entity!sort.action', '89', '8', '', '\0', '');
INSERT INTO `resource` VALUES ('98', '删除产品属性', 'pro_prop_del', '', '/product!delprop.action', '27', '11', '', '\0', '');
INSERT INTO `resource` VALUES ('99', '详情查看', 'cust_msg_detail', '', '/message!detail.action', '29', '2', '', '\0', '');
INSERT INTO `resource` VALUES ('100', '附件下载', 'cust_msg_file', '', '/message!file.action', '29', '3', '', '\0', '');
INSERT INTO `resource` VALUES ('101', '留言处理', 'cust_msg_deal', '', '/message!edit.action', '29', '4', '', '\0', '');
INSERT INTO `resource` VALUES ('102', '文件上传', 'admin_file_up', '', '/admin!upfile.action', '0', '5', '', '\0', '');

-- ----------------------------
-- Table structure for resource_role
-- ----------------------------
DROP TABLE IF EXISTS `resource_role`;
CREATE TABLE `resource_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `RESOURCE_ID` bigint(20) DEFAULT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource_role
-- ----------------------------
INSERT INTO `resource_role` VALUES ('1', '1', '1');
INSERT INTO `resource_role` VALUES ('2', '2', '1');
INSERT INTO `resource_role` VALUES ('3', '3', '1');
INSERT INTO `resource_role` VALUES ('4', '13', '1');
INSERT INTO `resource_role` VALUES ('5', '22', '1');
INSERT INTO `resource_role` VALUES ('6', '14', '1');
INSERT INTO `resource_role` VALUES ('7', '23', '1');
INSERT INTO `resource_role` VALUES ('8', '24', '1');
INSERT INTO `resource_role` VALUES ('9', '25', '1');
INSERT INTO `resource_role` VALUES ('10', '26', '1');
INSERT INTO `resource_role` VALUES ('11', '27', '1');
INSERT INTO `resource_role` VALUES ('12', '28', '1');
INSERT INTO `resource_role` VALUES ('13', '29', '1');
INSERT INTO `resource_role` VALUES ('14', '30', '1');
INSERT INTO `resource_role` VALUES ('15', '31', '1');
INSERT INTO `resource_role` VALUES ('16', '32', '1');
INSERT INTO `resource_role` VALUES ('17', '33', '1');
INSERT INTO `resource_role` VALUES ('18', '34', '1');
INSERT INTO `resource_role` VALUES ('19', '35', '1');
INSERT INTO `resource_role` VALUES ('20', '36', '1');
INSERT INTO `resource_role` VALUES ('21', '25', '2');
INSERT INTO `resource_role` VALUES ('22', '26', '2');
INSERT INTO `resource_role` VALUES ('23', '27', '2');
INSERT INTO `resource_role` VALUES ('24', '39', '1');
INSERT INTO `resource_role` VALUES ('25', '37', '1');
INSERT INTO `resource_role` VALUES ('26', '38', '1');
INSERT INTO `resource_role` VALUES ('27', '40', '1');
INSERT INTO `resource_role` VALUES ('28', '41', '1');
INSERT INTO `resource_role` VALUES ('29', '42', '1');
INSERT INTO `resource_role` VALUES ('30', '43', '1');
INSERT INTO `resource_role` VALUES ('31', '44', '1');
INSERT INTO `resource_role` VALUES ('32', '45', '1');
INSERT INTO `resource_role` VALUES ('33', '46', '1');
INSERT INTO `resource_role` VALUES ('34', '84', '1');
INSERT INTO `resource_role` VALUES ('35', '47', '1');
INSERT INTO `resource_role` VALUES ('36', '48', '1');
INSERT INTO `resource_role` VALUES ('37', '49', '1');
INSERT INTO `resource_role` VALUES ('38', '50', '1');
INSERT INTO `resource_role` VALUES ('39', '51', '1');
INSERT INTO `resource_role` VALUES ('40', '52', '1');
INSERT INTO `resource_role` VALUES ('41', '85', '1');
INSERT INTO `resource_role` VALUES ('42', '86', '1');
INSERT INTO `resource_role` VALUES ('43', '87', '1');
INSERT INTO `resource_role` VALUES ('44', '88', '1');
INSERT INTO `resource_role` VALUES ('45', '53', '1');
INSERT INTO `resource_role` VALUES ('46', '54', '1');
INSERT INTO `resource_role` VALUES ('47', '55', '1');
INSERT INTO `resource_role` VALUES ('48', '56', '1');
INSERT INTO `resource_role` VALUES ('49', '81', '1');
INSERT INTO `resource_role` VALUES ('50', '82', '1');
INSERT INTO `resource_role` VALUES ('51', '83', '1');
INSERT INTO `resource_role` VALUES ('52', '57', '1');
INSERT INTO `resource_role` VALUES ('53', '58', '1');
INSERT INTO `resource_role` VALUES ('54', '59', '1');
INSERT INTO `resource_role` VALUES ('55', '78', '1');
INSERT INTO `resource_role` VALUES ('56', '79', '1');
INSERT INTO `resource_role` VALUES ('57', '80', '1');
INSERT INTO `resource_role` VALUES ('58', '60', '1');
INSERT INTO `resource_role` VALUES ('59', '61', '1');
INSERT INTO `resource_role` VALUES ('60', '62', '1');
INSERT INTO `resource_role` VALUES ('61', '75', '1');
INSERT INTO `resource_role` VALUES ('62', '76', '1');
INSERT INTO `resource_role` VALUES ('63', '77', '1');
INSERT INTO `resource_role` VALUES ('64', '63', '1');
INSERT INTO `resource_role` VALUES ('65', '64', '1');
INSERT INTO `resource_role` VALUES ('66', '65', '1');
INSERT INTO `resource_role` VALUES ('67', '72', '1');
INSERT INTO `resource_role` VALUES ('68', '73', '1');
INSERT INTO `resource_role` VALUES ('69', '74', '1');
INSERT INTO `resource_role` VALUES ('70', '66', '1');
INSERT INTO `resource_role` VALUES ('71', '67', '1');
INSERT INTO `resource_role` VALUES ('72', '68', '1');
INSERT INTO `resource_role` VALUES ('73', '69', '1');
INSERT INTO `resource_role` VALUES ('74', '70', '1');
INSERT INTO `resource_role` VALUES ('75', '71', '1');
INSERT INTO `resource_role` VALUES ('76', '89', '1');
INSERT INTO `resource_role` VALUES ('77', '90', '1');
INSERT INTO `resource_role` VALUES ('78', '91', '1');
INSERT INTO `resource_role` VALUES ('79', '92', '1');
INSERT INTO `resource_role` VALUES ('80', '93', '1');
INSERT INTO `resource_role` VALUES ('81', '94', '1');
INSERT INTO `resource_role` VALUES ('82', '95', '1');
INSERT INTO `resource_role` VALUES ('83', '96', '1');
INSERT INTO `resource_role` VALUES ('84', '97', '1');
INSERT INTO `resource_role` VALUES ('85', '98', '1');
INSERT INTO `resource_role` VALUES ('86', '99', '1');
INSERT INTO `resource_role` VALUES ('87', '100', '1');
INSERT INTO `resource_role` VALUES ('88', '101', '1');
INSERT INTO `resource_role` VALUES ('89', '102', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(20) DEFAULT NULL,
  `MEMO` varchar(50) DEFAULT NULL,
  `INSERT_TIME` datetime DEFAULT NULL,
  `ROLE_CODE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '超级管理员,神挡杀神,佛挡杀佛', '2013-08-20 22:12:01', 'SUPER');
INSERT INTO `role` VALUES ('2', 'aaaaaa', '', '2013-08-21 22:02:58', 'FFFFFF');
INSERT INTO `role` VALUES ('3', 'FASDFA', 'DGSDFGSFSDG', '2013-08-21 22:03:33', 'FGSADFG');
INSERT INTO `role` VALUES ('4', 'tttttttt', '', '2013-08-21 22:04:08', 'tttttttttt');

-- ----------------------------
-- Table structure for rotate_image
-- ----------------------------
DROP TABLE IF EXISTS `rotate_image`;
CREATE TABLE `rotate_image` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ATTACHMENT` bigint(20) DEFAULT NULL,
  `LINK` varchar(100) DEFAULT NULL,
  `MEMO` varchar(200) DEFAULT NULL,
  `SORT` int(11) DEFAULT NULL,
  `PUBLISHED` bit(1) DEFAULT NULL,
  `TITLE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_7` (`ATTACHMENT`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`ATTACHMENT`) REFERENCES `attachment` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rotate_image
-- ----------------------------
INSERT INTO `rotate_image` VALUES ('1', '30', '', '', '1', '', '1');
INSERT INTO `rotate_image` VALUES ('2', '31', '', '', '2', '', '2');
INSERT INTO `rotate_image` VALUES ('3', '32', '', '', '3', '', '3');
INSERT INTO `rotate_image` VALUES ('4', '33', '', '', '4', '', '4');

-- ----------------------------
-- Table structure for site_log
-- ----------------------------
DROP TABLE IF EXISTS `site_log`;
CREATE TABLE `site_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IP` varchar(15) DEFAULT NULL,
  `URL` varchar(100) DEFAULT NULL,
  `REQUEST_TIME` datetime DEFAULT NULL,
  `SESSION` varchar(255) DEFAULT NULL,
  `USER_AGENT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='网站日志，记录什么时间，哪里的顾客访问哪个页面';

-- ----------------------------
-- Records of site_log
-- ----------------------------
INSERT INTO `site_log` VALUES ('1', '127.0.0.1', '/index.htm', '2013-12-20 22:52:19', '62E242D3BCA17D15848C4CDD65566F6A', 'Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko');
INSERT INTO `site_log` VALUES ('2', '127.0.0.1', '/product/p.htm', '2013-12-20 22:52:42', '62E242D3BCA17D15848C4CDD65566F6A', 'Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko');

-- ----------------------------
-- Table structure for site_property
-- ----------------------------
DROP TABLE IF EXISTS `site_property`;
CREATE TABLE `site_property` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PROPERTY_CODE` varchar(20) DEFAULT NULL,
  `PROPERTY_VALUE` text,
  `PROPERTY_NAME` text,
  `PROPERTY_GROUP` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of site_property
-- ----------------------------
INSERT INTO `site_property` VALUES ('1', 'site_title', 'Shenzhen Winsmoke Technology Co., Ltd. was founded in 2009, by a unique team of researchers, developers, and business experts, having been devoting itself to an enterprise that integrated production,R&D,sales and service.', '网站标题', null);
INSERT INTO `site_property` VALUES ('2', 'site_head', '<h2 style=\"padding-top:10px;font-size:30px;color:#000033;font-family:\'Lucida Calligraphy\', ΢���ź�, SimHei;\">\r\n	Shenzhen Winsmoke Technology Co., Ltd.\r\n</h2>', '页眉文字', null);
INSERT INTO `site_property` VALUES ('3', 'site_foot', 'CopyRight &copy; 2006-2014 Winsmoke winsmoke.com , All Rights Res', '页脚文字', null);
INSERT INTO `site_property` VALUES ('4', 'site_logo', '/upload/20131005/20131005095452857.png', '网站LOGO', '');
INSERT INTO `site_property` VALUES ('5', 'skype', 'winsmoke', 'skype', null);
INSERT INTO `site_property` VALUES ('6', 'email', 'winsmoke@163.com', 'email', null);
INSERT INTO `site_property` VALUES ('7', 'msn', 'winsmoke100@msn.com', 'msn', null);
INSERT INTO `site_property` VALUES ('8', 'site_contact', '<li>\r\n	<span class=\"name\">Contact:</span><span class=\"value\">Mr.Hunk</span> \r\n</li>\r\n<li>\r\n	<span class=\"name\">MSN:</span><span class=\"value\">winsmoke100@msn.com</span> \r\n</li>\r\n<li>\r\n	<span class=\"name\">Skype:</span><span class=\"value\">winsmoke</span> \r\n</li>\r\n<li>\r\n	<span class=\"name\">Email:</span><span class=\"value\">winsmoke@163.com</span> \r\n</li>\r\n<li>\r\n	<span class=\"name\">Mobile:</span><span class=\"value\">+86-18664330742</span> \r\n</li>', '联系方式', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(10) DEFAULT NULL,
  `PASSWORD` varchar(200) DEFAULT NULL,
  `REAL_NAME` varchar(10) DEFAULT NULL,
  `INSERT_TIME` datetime DEFAULT NULL,
  `MEMO` varchar(100) DEFAULT NULL,
  `TEL` varchar(20) DEFAULT NULL,
  `QQ` varchar(20) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `USER_ROLE` bigint(20) DEFAULT NULL,
  `FAILED_TIMES` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_6` (`USER_ROLE`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`USER_ROLE`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'songxh', 'E10ADC3949BA59ABBE56E057F20F883E', 'songxh', '2013-08-22 21:40:11', '', '15260816875', '474345424', '474345424@qq.com', '1', '0');

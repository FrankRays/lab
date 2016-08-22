/*
Navicat MySQL Data Transfer

Source Server         : 阿里云512
Source Server Version : 50544
Source Host           : 114.215.153.154:3306
Source Database       : lab

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2016-08-22 02:19:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `auth_id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_descr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', '查看公开信息');
INSERT INTO `authority` VALUES ('2', '导师发布通知');

-- ----------------------------
-- Table structure for field
-- ----------------------------
DROP TABLE IF EXISTS `field`;
CREATE TABLE `field` (
  `field_id` int(11) NOT NULL AUTO_INCREMENT,
  `field_descr` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  PRIMARY KEY (`field_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of field
-- ----------------------------
INSERT INTO `field` VALUES ('1', '大数据', '0');
INSERT INTO `field` VALUES ('2', '人工智能', '0');
INSERT INTO `field` VALUES ('3', '数据挖掘', '0');
INSERT INTO `field` VALUES ('4', '云计算', '0');
INSERT INTO `field` VALUES ('5', '服务计算', '0');
INSERT INTO `field` VALUES ('6', '啦啦啦啦', '0');

-- ----------------------------
-- Table structure for field_paper
-- ----------------------------
DROP TABLE IF EXISTS `field_paper`;
CREATE TABLE `field_paper` (
  `fp_id` int(11) NOT NULL AUTO_INCREMENT,
  `field_id` int(11) DEFAULT NULL,
  `paper_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`fp_id`),
  KEY `FK83gubfcn4bupyans4vflofrl2` (`paper_id`),
  KEY `FKg5o7fqin0fkq4jg56v2v4lb7i` (`field_id`),
  CONSTRAINT `FKg5o7fqin0fkq4jg56v2v4lb7i` FOREIGN KEY (`field_id`) REFERENCES `field` (`field_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK83gubfcn4bupyans4vflofrl2` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`paper_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of field_paper
-- ----------------------------
INSERT INTO `field_paper` VALUES ('1', '1', '2');
INSERT INTO `field_paper` VALUES ('2', '1', '17');
INSERT INTO `field_paper` VALUES ('3', '2', '18');
INSERT INTO `field_paper` VALUES ('5', '1', '144');
INSERT INTO `field_paper` VALUES ('6', '3', '144');
INSERT INTO `field_paper` VALUES ('13', '1', '141');
INSERT INTO `field_paper` VALUES ('14', '3', '141');
INSERT INTO `field_paper` VALUES ('15', '4', '141');

-- ----------------------------
-- Table structure for field_project
-- ----------------------------
DROP TABLE IF EXISTS `field_project`;
CREATE TABLE `field_project` (
  `fp_id` int(11) NOT NULL AUTO_INCREMENT,
  `field_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`fp_id`),
  KEY `FKgv42i6483w1uehwck15803qng` (`project_id`),
  KEY `FKpwy54esen1mx9esecwelueblr` (`field_id`),
  CONSTRAINT `FKpwy54esen1mx9esecwelueblr` FOREIGN KEY (`field_id`) REFERENCES `field` (`field_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKgv42i6483w1uehwck15803qng` FOREIGN KEY (`project_id`) REFERENCES `project` (`pro_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of field_project
-- ----------------------------
INSERT INTO `field_project` VALUES ('6', '3', '7');
INSERT INTO `field_project` VALUES ('7', '1', '7');
INSERT INTO `field_project` VALUES ('8', '4', '7');

-- ----------------------------
-- Table structure for field_user
-- ----------------------------
DROP TABLE IF EXISTS `field_user`;
CREATE TABLE `field_user` (
  `fu_id` int(11) NOT NULL AUTO_INCREMENT,
  `field_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`fu_id`),
  KEY `FK2srn4govd3itcrvmqv90iyavi` (`field_id`),
  KEY `FKk1jm2o8i6fce65muoo5a1nen` (`user_id`),
  CONSTRAINT `FKk1jm2o8i6fce65muoo5a1nen` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK2srn4govd3itcrvmqv90iyavi` FOREIGN KEY (`field_id`) REFERENCES `field` (`field_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of field_user
-- ----------------------------
INSERT INTO `field_user` VALUES ('1', '3', '12');
INSERT INTO `field_user` VALUES ('2', '1', '12');
INSERT INTO `field_user` VALUES ('3', '1', '13');
INSERT INTO `field_user` VALUES ('4', '3', '13');
INSERT INTO `field_user` VALUES ('5', '1', '1');
INSERT INTO `field_user` VALUES ('6', '2', '1');

-- ----------------------------
-- Table structure for group_auth
-- ----------------------------
DROP TABLE IF EXISTS `group_auth`;
CREATE TABLE `group_auth` (
  `group_auth_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL,
  `auth_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`group_auth_id`),
  KEY `FK7iv0c1ovorc2qyb7gd0weu5hk` (`group_id`),
  KEY `FKsx1gx9q7k2thvs83qo1k4xj2o` (`auth_id`),
  CONSTRAINT `FKsx1gx9q7k2thvs83qo1k4xj2o` FOREIGN KEY (`auth_id`) REFERENCES `authority` (`auth_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK7iv0c1ovorc2qyb7gd0weu5hk` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_auth
-- ----------------------------

-- ----------------------------
-- Table structure for group_user
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user` (
  `gu_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`gu_id`),
  KEY `FK6u7jb50qa69gr3505uttxm86x` (`user_id`),
  KEY `FKm4p7t99vp509n4lt2et6hqkgn` (`group_id`),
  CONSTRAINT `FKm4p7t99vp509n4lt2et6hqkgn` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK6u7jb50qa69gr3505uttxm86x` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_user
-- ----------------------------
INSERT INTO `group_user` VALUES ('1', '1', '1');
INSERT INTO `group_user` VALUES ('2', '2', '1');
INSERT INTO `group_user` VALUES ('3', '3', '2');
INSERT INTO `group_user` VALUES ('4', '4', '2');
INSERT INTO `group_user` VALUES ('5', '13', '1');

-- ----------------------------
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) DEFAULT NULL,
  `leader` int(11) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of groups
-- ----------------------------
INSERT INTO `groups` VALUES ('1', '高并发并行计算科研小组', '1');
INSERT INTO `groups` VALUES ('2', '集成芯片的效率研究小组', '3');

-- ----------------------------
-- Table structure for inform
-- ----------------------------
DROP TABLE IF EXISTS `inform`;
CREATE TABLE `inform` (
  `inform_id` int(11) NOT NULL AUTO_INCREMENT,
  `inform_type` varchar(255) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `send_date` datetime DEFAULT NULL,
  PRIMARY KEY (`inform_id`),
  KEY `FKfu3jcxm4n8sody7bgjbe1cioh` (`sender_id`),
  KEY `FK4v9590cgyndoflyvwr9dr5yya` (`receiver_id`),
  CONSTRAINT `FK4v9590cgyndoflyvwr9dr5yya` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKfu3jcxm4n8sody7bgjbe1cioh` FOREIGN KEY (`sender_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `inform_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inform_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inform
-- ----------------------------
INSERT INTO `inform` VALUES ('1', 'unread', '1', '2', 'hahahahhahahahaha', '2016-07-24 11:35:27');
INSERT INTO `inform` VALUES ('2', 'unread', '2', '1', 'jhdgsjhdbshjdbshj', '2016-07-24 11:43:43');
INSERT INTO `inform` VALUES ('3', null, '4', '2', 'you are my friend', '2016-07-24 15:01:14');
INSERT INTO `inform` VALUES ('4', 'unread', '1', '4', 'yyyyyyyyyyyyy', '2016-07-24 15:05:19');
INSERT INTO `inform` VALUES ('5', 'unread', '1', '3', 'qqqqqqqqqq', '2016-07-24 20:55:43');
INSERT INTO `inform` VALUES ('6', 'unread', '3', '2', 'aaaaaaaaaaa', '2016-07-24 20:55:52');

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `paper_id` int(11) NOT NULL AUTO_INCREMENT,
  `extra_author` varchar(255) DEFAULT NULL,
  `extra_corr_author` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `CCF_status` varchar(255) DEFAULT NULL,
  `periodical` varchar(255) DEFAULT NULL,
  `post_year` varchar(255) DEFAULT NULL,
  `vol_num` varchar(255) DEFAULT NULL,
  `issue_num` varchar(255) DEFAULT NULL,
  `start_page` int(11) DEFAULT NULL,
  `end_page` int(11) DEFAULT NULL,
  `source_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`paper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES ('2', null, null, '关于神经网络的深度学习', '期刊论文', 'SEI', 'A', null, '2016', '第5卷', '第8期', '25', '76', '');
INSERT INTO `paper` VALUES ('17', null, null, null, '会议论文', 'EI', 'B', null, '2015', null, null, null, null, '2016-07-30第2讲-递归.pptx');
INSERT INTO `paper` VALUES ('18', null, null, null, null, 'EI', null, null, '2014', null, null, null, null, null);
INSERT INTO `paper` VALUES ('124', null, null, null, null, 'SEI', null, null, '2013', null, null, null, null, null);
INSERT INTO `paper` VALUES ('138', '{\"list\":[{\"isCorr\":0,\"isExtra\":1,\"order\":1,\"name\":\"马文\"},{\"isCorr\":0,\"isExtra\":0,\"order\":3,\"name\":\"Jimmy\"},{\"isCorr\":1,\"isExtra\":1,\"order\":4,\"name\":\"马瑞呢\"}]}', null, '软件生命周期的研究', '会议论文', 'SEI', 'A', '上海学术峰会', '2016', '第26卷', '第8号', '24', '40', '2016-08-14P60525-014900.jpg');
INSERT INTO `paper` VALUES ('141', '{\"list\":[{\"isCorr\":0,\"isExtra\":1,\"order\":1,\"name\":\"Jimmy\"}]}', null, '软件生命周期的研究', '会议论文', 'SEI', 'C', '上海学术峰会', '2016', '第26卷', '第8号', '3', '9', '2016-08-21_15_48_59上海大学-蒋明-简历.pdf');
INSERT INTO `paper` VALUES ('143', null, null, '软件生命周期的研究', '会议论文', 'SEI', 'B', '上海学术峰会', '2016', '第26卷', '第8号', '3', '8', null);
INSERT INTO `paper` VALUES ('144', '{\"list\":[{\"isCorr\":1,\"isExtra\":1,\"order\":1,\"name\":\"水电费\"},{\"isCorr\":0,\"isExtra\":1,\"order\":4,\"name\":\"Jimmy\"}]}', null, '软件生命周期的研究', '会议论文', 'SEI', 'A', '上海学术峰会', '2016', '第26卷', '第8号', '3', '8', null);

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `pro_id` int(11) NOT NULL AUTO_INCREMENT,
  `pro_name` varchar(255) DEFAULT NULL,
  `extra_directors` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `pro_fee` varchar(255) DEFAULT NULL,
  `pro_type` varchar(255) DEFAULT NULL,
  `pro_level` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '古代文化研究', null, '2016-07-21 13:52:27', '2016-09-22 13:52:44', '20万', '国家类', '国家自然科学基金会');
INSERT INTO `project` VALUES ('2', '计算机视觉发展', null, '2016-08-02 13:55:10', '2017-07-30 13:55:13', '100万', '企业类', '华为软件科技发展协会');
INSERT INTO `project` VALUES ('6', 'xiao ming', '{\"list\":[{\"isCorr\":0,\"isExtra\":0,\"order\":3,\"name\":\"Jimmy\"}]}', '2015-03-21 00:00:00', '2017-08-09 00:00:00', '20w', '国家型', '国家自然基金');
INSERT INTO `project` VALUES ('7', '上海市计算机发展研究', null, '2015-03-21 00:00:00', '2017-08-10 00:00:00', '40万', '企业型', '国家自然基金');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `eng_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL,
  `user_intro` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '小明', 'jimmy', '123456', null, '研究生', null);
INSERT INTO `user` VALUES ('2', '马文', 'marvin', '666666', null, '本科生', null);
INSERT INTO `user` VALUES ('3', '莉莉', 'lily', '456123', null, '本科生', null);
INSERT INTO `user` VALUES ('4', '托马', 'toma', '666666', null, '教授', null);
INSERT INTO `user` VALUES ('5', '皮特', 'Peter', '555555', null, '副教授', null);
INSERT INTO `user` VALUES ('6', '杰克', 'Jack', '444444', null, '助教', null);
INSERT INTO `user` VALUES ('7', '刘静', 'joe', '123456', null, '本科生', '问题啊是的噶顺德容桂');
INSERT INTO `user` VALUES ('8', '张飞', 'zhangfei', '123456', null, '研究生', '问题啊是的噶顺德容桂');
INSERT INTO `user` VALUES ('9', '马云', 'horse', '123456', null, '教授', '问题啊是的噶顺德容桂');
INSERT INTO `user` VALUES ('10', '麻花藤', 'ace', '123456', null, '助教', '问题啊是的噶顺德容桂');
INSERT INTO `user` VALUES ('11', '雷军', 'thunder', '123456', null, '助教', '问题啊是的噶顺德容桂');
INSERT INTO `user` VALUES ('12', '李彦宏', 'red', '123456', null, '本科生', '问题啊是的噶顺德容桂');
INSERT INTO `user` VALUES ('13', 'Marvin0', '大神大神大神', '123456', null, '本科生', '问题啊是的噶顺德容桂');

-- ----------------------------
-- Table structure for user_paper
-- ----------------------------
DROP TABLE IF EXISTS `user_paper`;
CREATE TABLE `user_paper` (
  `up_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `paper_id` int(11) DEFAULT NULL,
  `up_order` int(11) DEFAULT NULL,
  `is_corr` int(11) DEFAULT NULL,
  PRIMARY KEY (`up_id`),
  KEY `FK2v63tj6wb6vj1ff9a0p9dsagq` (`paper_id`),
  KEY `FKtgg6blxn73fbrkpbqswx7cnp6` (`user_id`),
  CONSTRAINT `FKtgg6blxn73fbrkpbqswx7cnp6` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK2v63tj6wb6vj1ff9a0p9dsagq` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`paper_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_paper
-- ----------------------------
INSERT INTO `user_paper` VALUES ('1', '2', '2', '1', '0');
INSERT INTO `user_paper` VALUES ('2', '1', '2', '2', '0');
INSERT INTO `user_paper` VALUES ('3', '3', '18', '3', '0');
INSERT INTO `user_paper` VALUES ('4', '1', '17', '2', '1');
INSERT INTO `user_paper` VALUES ('5', '4', '124', '2', '1');
INSERT INTO `user_paper` VALUES ('38', '1', '138', '2', '1');
INSERT INTO `user_paper` VALUES ('53', '1', '144', '2', '1');
INSERT INTO `user_paper` VALUES ('54', '2', '144', '3', '0');
INSERT INTO `user_paper` VALUES ('63', '1', '141', '2', '1');
INSERT INTO `user_paper` VALUES ('64', '2', '141', '3', '0');
INSERT INTO `user_paper` VALUES ('65', '12', '141', '4', '0');

-- ----------------------------
-- Table structure for user_project
-- ----------------------------
DROP TABLE IF EXISTS `user_project`;
CREATE TABLE `user_project` (
  `up_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `pro_id` int(11) DEFAULT NULL,
  `up_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`up_id`),
  KEY `FKcexstr0pox4xl6j1mbree6meq` (`pro_id`),
  KEY `FKpw81exe7fsdl7mddqujvu91kx` (`user_id`),
  CONSTRAINT `FKcexstr0pox4xl6j1mbree6meq` FOREIGN KEY (`pro_id`) REFERENCES `project` (`pro_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKpw81exe7fsdl7mddqujvu91kx` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_project
-- ----------------------------
INSERT INTO `user_project` VALUES ('3', '2', '6', '1');
INSERT INTO `user_project` VALUES ('4', '1', '6', '2');
INSERT INTO `user_project` VALUES ('10', '2', '7', '1');
INSERT INTO `user_project` VALUES ('11', '1', '7', '2');
INSERT INTO `user_project` VALUES ('12', '12', '7', '3');

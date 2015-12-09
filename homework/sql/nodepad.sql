CREATE TABLE `nodepad` (
  `create_time` timestamp ,
  `title` varchar(10) DEFAULT NULL ,
  `content` varchar(50) DEFAULT NULL ,
  `update_time` datetime DEFAULT NULL,
  `del` varchar(100) DEFAULT 'N',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8
CREATE TABLE `nodepad` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `title` varchar(10) DEFAULT NULL COMMENT 'nodepad title',
  `content` varchar(50) DEFAULT NULL COMMENT 'nodepad content',
  `update_time` datetime DEFAULT NULL,
  `del` varchar(100) DEFAULT 'N',
  PRIMARY KEY (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 

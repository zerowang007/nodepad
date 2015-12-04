--
-- Table structure for table `nodepad`
--

DROP TABLE IF EXISTS `nodepad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nodepad` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `title` varchar(10) DEFAULT NULL COMMENT 'nodepad tile',
  `content` varchar(50) DEFAULT NULL COMMENT 'nodepad content ',
  PRIMARY KEY (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
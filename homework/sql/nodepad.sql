CREATE TABLE `nodepad` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `title` varchar(10) DEFAULT NULL COMMENT 'OPN-open, CAN-cancelled, CMP-paid and transaction completed, REJ - Rejected\n',
  `content` varchar(50) DEFAULT NULL COMMENT 'invoice no/PO no from 3rd party ',
  `update_time` date DEFAULT NULL,
  `del` varchar(100) DEFAULT 'N',
  PRIMARY KEY (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='purchase order from customer. customer may be member or  not member'

CREATE DATABASE user;
USE user;


CREATE TABLE `ITEM` (
  `ITEM_ID` int(10) NOT NULL AUTO_INCREMENT,
  `BRAND` varchar(20) NOT NULL,
  `MODEL` varchar(20) NOT NULL,
  `PRICE` double NOT NULL,
  PRIMARY KEY (`ITEM_ID`),
  UNIQUE KEY `ITEM_ITEM_ID_uindex` (`ITEM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8


CREATE TABLE `ORDER` (
  `ORDER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `DATE` date NOT NULL,
  `USER_ID` int(10) NOT NULL,
  PRIMARY KEY (`ORDER_ID`),
  KEY `ORDER_USER_ID_fk` (`USER_ID`),
  CONSTRAINT `ORDER_USER_ID_fk` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8

CREATE TABLE `ORDER_TO_ITEM` (
  `ORDER_ID` int(10) NOT NULL,
  `ITEM_ID` int(10) NOT NULL,
  PRIMARY KEY (`ORDER_ID`,`ITEM_ID`),
  KEY `ITEM_ID` (`ITEM_ID`),
  CONSTRAINT `order_to_item_ibfk_1` FOREIGN KEY (`ORDER_ID`) REFERENCES `ORDER` (`ORDER_ID`) ON UPDATE CASCADE,
  CONSTRAINT `order_to_item_ibfk_2` FOREIGN KEY (`ITEM_ID`) REFERENCES `ITEM` (`ITEM_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `USER` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `LAST_NAME` varchar(20) NOT NULL,
  `FIRST_NAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `LOGIN` varchar(20) NOT NULL,
  `ROLE` varchar(10) NOT NULL,
  `EMAIL` varchar(20) NOT NULL,
  `DATE_OF_BIRTH` date NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2131248 DEFAULT CHARSET=utf8

INSERT INTO `ITEM` (`ITEM_ID`, `BRAND`, `MODEL`, `PRICE`) 
 VALUES (1, 'asics', 'gel-kayano', 120);
 INSERT INTO `ITEM` (`ITEM_ID`, `BRAND`, `MODEL`, `PRICE`) 
 VALUES (2, 'asics', 'gel-kayano', 120);
 INSERT INTO `ITEM` (`ITEM_ID`, `BRAND`, `MODEL`, `PRICE`) 
 VALUES (3, 'asics', 'gel-kayano', 120);

 INSERT INTO `USER` (`ID`, `LAST_NAME`, `FIRST_NAME`, `PASSWORD`, `LOGIN`, `ROLE`, `EMAIL`, `DATE_OF_BIRTH`) 
 VALUES (1, 'ivanov', 'ivan', '1111', 'admin', 'iv@mail.ru', '2017-08-02');

 INSERT INTO `USER` (`ID`, `LAST_NAME`, `FIRST_NAME`, `PASSWORD`, `LOGIN`, `ROLE`, `EMAIL`, `DATE_OF_BIRTH`) 
  VALUES (2, 'petrov', 'petr', '2222', 'user', 'pe@mail.ru', '2015-04-05');

/*
SQLyog - Free MySQL GUI v4.1
Host - 5.5.19 : Database - javatest
*********************************************************************
Server version : 5.5.19
*/


/*Table structure for table `game` */

CREATE TABLE `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `choice` int(11) NOT NULL,
  `computerChoice` int(11) NOT NULL,
  `judgement` varchar(50) NOT NULL,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `game` */

insert into `game` values (1,1,1,'?????.','2012-03-06 06:49:57'),(2,2,2,'?????.','2012-03-06 06:50:00'),(3,0,1,'???? ?????.','2012-03-06 06:52:34'),(4,1,2,'???? ?????.','2012-03-06 08:19:26'),(5,0,1,'???? ?????.','2012-03-06 08:20:06'),(6,1,0,'??? ?????.','2012-03-06 08:20:13');

/*Table structure for table `simple_information` */

CREATE TABLE `simple_information` (
  `info_id` int(11) DEFAULT NULL,
  `info_content` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `simple_information` */

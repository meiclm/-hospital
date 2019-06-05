-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: child_ill_system
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cases`
--

DROP TABLE IF EXISTS `cases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cases` (
  `id` varchar(45) NOT NULL,
  `dNo` varchar(45) DEFAULT NULL,
  `pNo` varchar(45) DEFAULT NULL,
  `describe` text,
  `advice` text,
  `lookTime` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dNo_idx` (`dNo`),
  KEY `pNo_idx` (`pNo`),
  CONSTRAINT `dNo` FOREIGN KEY (`dNo`) REFERENCES `doctor` (`dusername`),
  CONSTRAINT `pNo` FOREIGN KEY (`pNo`) REFERENCES `patient` (`pusername`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cases`
--

LOCK TABLES `cases` WRITE;
/*!40000 ALTER TABLE `cases` DISABLE KEYS */;
INSERT INTO `cases` VALUES ('20190425231201','2015070801','sun123','流鼻涕，发热，嗜睡','多喝热水，不熬夜，清淡','2019-04-26 09:45'),('20190425231202','2015070802','yangyg','流行性流感病毒','打疫苗','2019-04-26 09:45'),('20190426091201','2015070801','zhaisiyuan','水痘','打疫苗','2019-04-27 09:45'),('20190521203737','2014090101','chenliu','咳嗽发烧。',NULL,'2019-05-21 10:00'),('20190521203948','2015070805','chenliu','no describe',NULL,NULL),('20190521205751','2014090103','chenliu','no describe',NULL,NULL),('20190521205924','2015070801','chenliu','no describe',NULL,NULL),('20190521210040','2015070804','chenliu','no describe',NULL,NULL),('20190521210143','2015070802','chenliu','no describe',NULL,NULL),('20190528135022','2014090103','bailin','no describe',NULL,NULL),('20190528135151','2014090102','bailin','no describe','打疫苗','2019-05-28 13:53:07'),('20190530194429','2015070801','bailin','no describe',NULL,NULL),('20190530222721','2014090101','bailin','no describe','','2019-05-30 22:44:56'),('20190531150946','2015070802','chenliu','no describe',NULL,NULL),('20190531160344','2014090101','bailin','头晕','头晕','2019-05-31 17:44:59'),('20190531160442','2014090101','bailin','no describe','多喝水','2019-05-31 17:44:27'),('20190531160717','2015070802','chenliu','no describe',NULL,NULL),('20190531161127','2014090101','yangyg','no describe','多喝热水','2019-05-31 16:13:13'),('20190605100233','2014090101','chenliu','头痛',NULL,NULL),('20190605102205','2014090101','chenliu','发烧咳嗽',NULL,NULL);
/*!40000 ALTER TABLE `cases` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-05 17:00:24

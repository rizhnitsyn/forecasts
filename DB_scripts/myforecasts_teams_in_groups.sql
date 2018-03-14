CREATE DATABASE  IF NOT EXISTS `myforecasts` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `myforecasts`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: myforecasts
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `teams_in_groups`
--

DROP TABLE IF EXISTS `teams_in_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teams_in_groups` (
  `group_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  KEY `group_id_fk_idx` (`group_id`),
  KEY `team_id_fk_idx` (`team_id`),
  CONSTRAINT `FK3q1hlc6b4m7mt98curtd3te7l` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`),
  CONSTRAINT `FKldbm2fip1xi6e4brhje4h5dd1` FOREIGN KEY (`group_id`) REFERENCES `groups_in_tournament` (`id`),
  CONSTRAINT `group_id_fk` FOREIGN KEY (`group_id`) REFERENCES `groups_in_tournament` (`id`),
  CONSTRAINT `team_id_fk` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams_in_groups`
--

LOCK TABLES `teams_in_groups` WRITE;
/*!40000 ALTER TABLE `teams_in_groups` DISABLE KEYS */;
INSERT INTO `teams_in_groups` VALUES (17,6),(20,11),(17,16),(17,24),(24,8),(24,18),(24,10),(24,15),(17,12),(21,17),(21,14),(23,14),(20,26),(20,27),(20,21),(18,22),(18,30),(18,25),(18,1),(23,2),(23,29),(23,23),(19,3),(19,31),(19,19),(19,17),(25,4),(25,5),(25,37),(25,32);
/*!40000 ALTER TABLE `teams_in_groups` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-14 23:47:47

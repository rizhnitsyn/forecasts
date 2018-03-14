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
-- Table structure for table `groups_in_tournament`
--

DROP TABLE IF EXISTS `groups_in_tournament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups_in_tournament` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tournament_id` int(11) NOT NULL,
  `match_count_between_teams` int(11) NOT NULL,
  `group_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_idx1` (`tournament_id`,`group_name`),
  KEY `groups_in_tournament_group_names_group_name_id_fk` (`group_name`),
  KEY `groups_in_tournament_tournaments_tournament_id_fk` (`tournament_id`),
  CONSTRAINT `FKdfq8xjsopclpw9wuqo1gq5a1g` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`),
  CONSTRAINT `groups_in_tournament_tournaments_tournament_id_fk` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups_in_tournament`
--

LOCK TABLES `groups_in_tournament` WRITE;
/*!40000 ALTER TABLE `groups_in_tournament` DISABLE KEYS */;
INSERT INTO `groups_in_tournament` VALUES (17,23,2,'Группа А'),(18,23,2,'Группа B'),(19,23,2,'Группа C'),(20,23,2,'Группа D'),(21,23,2,'Полуфинал'),(22,19,2,'Группа E'),(23,23,2,'Группа F'),(24,9,2,'Группа А'),(25,23,2,'Группа E');
/*!40000 ALTER TABLE `groups_in_tournament` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-14 23:47:46

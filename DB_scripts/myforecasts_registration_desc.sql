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
-- Table structure for table `registration_desc`
--

DROP TABLE IF EXISTS `registration_desc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registration_desc` (
  `tournament_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  UNIQUE KEY `UNIQUE CONSTRAINTE1` (`tournament_id`,`user_id`),
  KEY `REGISTRATION_DESC_fk1` (`user_id`),
  CONSTRAINT `FK5g68othw3pmrtygayxx84xsb7` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`),
  CONSTRAINT `FK8w3otppk7euls4fve47qvs9xn` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `REGISTRATION_DESC_fk0` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`),
  CONSTRAINT `REGISTRATION_DESC_fk1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration_desc`
--

LOCK TABLES `registration_desc` WRITE;
/*!40000 ALTER TABLE `registration_desc` DISABLE KEYS */;
INSERT INTO `registration_desc` VALUES (1,1),(7,1),(9,1),(10,1),(11,1),(12,1),(15,1),(23,1),(1,2),(1,3),(4,3),(1,4),(1,5),(1,6),(1,7),(1,8),(6,8),(7,8),(9,8),(1,9),(1,10),(2,11),(3,11),(4,11);
/*!40000 ALTER TABLE `registration_desc` ENABLE KEYS */;
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

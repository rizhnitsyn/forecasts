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
-- Table structure for table `tournaments`
--

DROP TABLE IF EXISTS `tournaments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tournaments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tournament_name` varchar(30) NOT NULL,
  `team_organizer_id` int(11) NOT NULL,
  `tournament_start_date` date NOT NULL,
  `tournament_state_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tournament_name` (`tournament_name`),
  KEY `TOURNAMENTS_fk0` (`team_organizer_id`),
  KEY `TOURNAMENTS_fk1` (`tournament_state_id`),
  CONSTRAINT `FKb1td4cbbt1pfgm8bd3skyfs3f` FOREIGN KEY (`tournament_state_id`) REFERENCES `tournament_states` (`id`),
  CONSTRAINT `FKosef54py3vv9cn5v6dl7h0dlw` FOREIGN KEY (`team_organizer_id`) REFERENCES `teams` (`id`),
  CONSTRAINT `TOURNAMENTS_fk0` FOREIGN KEY (`team_organizer_id`) REFERENCES `teams` (`id`),
  CONSTRAINT `TOURNAMENTS_fk1` FOREIGN KEY (`tournament_state_id`) REFERENCES `tournament_states` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournaments`
--

LOCK TABLES `tournaments` WRITE;
/*!40000 ALTER TABLE `tournaments` DISABLE KEYS */;
INSERT INTO `tournaments` VALUES (1,'Чемпионат Европы 2016',1,'2016-06-10',2),(2,'updated964000',2,'2018-06-10',2),(3,'808373',2,'2018-06-10',2),(4,'857167',2,'2018-06-10',2),(5,'529727',2,'2018-06-10',2),(6,'964626',2,'2018-06-10',2),(7,'Тестовый турнир12',39,'2020-08-12',2),(8,'Турнирчик',31,'2019-12-12',2),(9,'Тестовый турнир13',32,'2018-10-10',1),(10,'New tournament 15',38,'2020-08-12',1),(11,'New Tournament 16',17,'2020-10-10',1),(12,'тур12',22,'2020-08-12',2),(13,'729468',2,'2017-11-29',2),(14,'401983',2,'2017-11-30',2),(15,'Турнир в Японии',26,'2018-12-10',2),(16,'Турнир в Японии 2',21,'2018-12-10',2),(17,'Турнир в Японии 3',21,'2018-12-12',2),(18,'Турнир в Японии 4',21,'2018-12-10',2),(19,'Турнир в Японии 5',21,'2018-12-12',2),(20,'Турнир в Японии 7',21,'2018-12-12',2),(21,'Новый турнир 20',20,'2017-12-10',2),(22,'Турнир Минск',38,'2017-12-22',2),(23,'JD2 tournament',15,'2018-03-16',1);
/*!40000 ALTER TABLE `tournaments` ENABLE KEYS */;
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

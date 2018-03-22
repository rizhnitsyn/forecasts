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
-- Table structure for table `forecasts`
--

DROP TABLE IF EXISTS `forecasts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forecasts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `match_id` int(11) NOT NULL,
  `first_team_forecast` int(11) NOT NULL,
  `second_team_forecast` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index` (`match_id`,`user_id`),
  KEY `FORECASTS_fk1` (`user_id`),
  CONSTRAINT `FKg5akkj2dolt5d4gjd8wx49l48` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKgjrbbdkbcttett6tcmdyr6dl3` FOREIGN KEY (`match_id`) REFERENCES `matches` (`id`),
  CONSTRAINT `FORECASTS_fk0` FOREIGN KEY (`match_id`) REFERENCES `matches` (`id`),
  CONSTRAINT `FORECASTS_fk1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=350 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forecasts`
--

LOCK TABLES `forecasts` WRITE;
/*!40000 ALTER TABLE `forecasts` DISABLE KEYS */;
INSERT INTO `forecasts` VALUES (1,1,3,0,1),(2,2,1,2,1),(3,3,1,1,1),(4,4,2,0,1),(5,5,1,2,1),(6,6,3,1,1),(7,7,2,0,1),(8,8,2,0,1),(9,9,0,1,1),(10,10,1,1,1),(11,11,2,1,1),(12,12,1,0,1),(13,13,1,2,1),(14,14,1,2,1),(15,15,2,0,1),(16,16,2,0,1),(17,17,1,0,1),(18,18,2,1,1),(19,19,2,1,1),(20,20,1,1,1),(21,21,3,1,1),(22,22,2,1,1),(23,23,1,0,1),(24,24,1,1,1),(25,25,0,0,1),(26,26,1,2,1),(27,27,1,0,1),(28,28,1,2,1),(29,29,1,2,1),(30,30,1,3,1),(31,31,1,1,1),(32,32,1,2,1),(33,33,0,1,1),(34,34,0,2,1),(35,35,3,1,1),(36,36,1,1,1),(37,1,1,0,2),(38,2,1,2,2),(39,3,1,1,2),(40,4,1,1,2),(41,5,1,3,2),(42,6,2,2,2),(43,7,3,0,2),(44,8,1,0,2),(45,9,0,0,2),(46,10,2,2,2),(47,11,1,4,2),(48,12,3,3,2),(49,13,1,0,2),(50,14,0,2,2),(51,15,2,0,2),(52,16,2,1,2),(53,17,1,2,2),(54,18,1,2,2),(55,19,1,0,2),(56,20,1,1,2),(57,21,2,0,2),(58,22,2,2,2),(59,23,2,0,2),(60,24,1,0,2),(61,25,2,1,2),(62,26,1,1,2),(63,27,1,1,2),(64,28,1,2,2),(65,29,1,1,2),(66,30,0,3,2),(67,31,1,0,2),(68,32,0,1,2),(69,33,3,1,2),(70,34,0,2,2),(71,35,1,1,2),(72,36,0,1,2),(77,1,1,0,3),(78,2,1,3,3),(79,3,2,1,3),(80,4,1,0,3),(81,5,1,3,3),(82,6,2,1,3),(83,7,4,0,3),(84,8,1,2,3),(85,9,1,1,3),(86,10,0,1,3),(87,11,2,1,3),(88,12,3,0,3),(89,13,1,1,3),(90,14,0,0,3),(91,15,2,0,3),(92,16,3,2,3),(93,17,1,3,3),(94,18,2,3,3),(95,19,3,1,3),(96,20,2,2,3),(97,21,1,2,3),(98,22,3,0,3),(99,23,0,0,3),(100,24,2,0,3),(101,25,2,2,3),(102,26,1,2,3),(103,27,1,2,3),(104,28,1,2,3),(105,29,1,3,3),(106,30,1,4,3),(107,31,1,1,3),(108,32,2,2,3),(109,33,0,0,3),(110,34,1,3,3),(111,35,1,1,3),(112,36,0,3,3),(113,1,3,0,4),(114,2,1,2,4),(115,3,2,2,4),(116,4,2,0,4),(117,5,1,2,4),(118,6,3,0,4),(119,7,2,1,4),(120,8,1,0,4),(121,9,1,3,4),(122,10,2,0,4),(123,11,3,0,4),(124,12,4,0,4),(125,13,2,1,4),(126,14,1,1,4),(127,15,3,0,4),(128,16,2,0,4),(129,17,3,0,4),(130,18,3,1,4),(131,19,1,0,4),(132,20,2,2,4),(133,21,3,0,4),(134,22,3,0,4),(135,23,0,1,4),(136,24,1,0,4),(137,25,1,0,4),(138,26,0,1,4),(139,27,1,0,4),(140,28,0,3,4),(141,29,2,2,4),(142,30,0,5,4),(143,31,3,2,4),(144,32,1,0,4),(145,33,1,2,4),(146,34,1,4,4),(147,35,1,0,4),(148,36,0,1,4),(149,1,3,1,5),(150,2,1,1,5),(151,3,1,1,5),(152,4,2,1,5),(153,5,0,1,5),(154,6,2,2,5),(155,7,2,0,5),(156,8,3,2,5),(157,9,0,1,5),(158,10,2,2,5),(159,11,1,1,5),(160,12,2,0,5),(161,13,0,0,5),(162,14,1,1,5),(163,15,2,0,5),(164,16,2,1,5),(165,17,3,2,5),(166,18,4,1,5),(167,19,2,1,5),(168,20,1,1,5),(169,21,3,1,5),(170,22,3,1,5),(171,23,0,0,5),(172,24,2,1,5),(173,25,2,1,5),(174,26,0,2,5),(175,27,1,1,5),(176,28,0,1,5),(177,29,1,0,5),(178,30,0,1,5),(179,31,0,1,5),(180,32,3,3,5),(181,33,2,0,5),(182,34,1,3,5),(183,35,1,0,5),(184,36,1,2,5),(185,1,4,0,6),(186,2,1,2,6),(187,3,2,1,6),(188,4,3,1,6),(189,5,2,2,6),(190,6,3,1,6),(191,7,2,0,6),(192,8,2,1,6),(193,9,1,1,6),(194,10,0,1,6),(195,11,2,0,6),(196,12,1,0,6),(197,13,2,1,6),(198,14,0,2,6),(199,15,2,0,6),(200,16,3,2,6),(201,17,1,0,6),(202,18,2,1,6),(203,19,2,0,6),(204,20,1,2,6),(205,21,3,2,6),(206,22,1,0,6),(207,23,1,0,6),(208,24,1,1,6),(209,25,1,2,6),(210,26,1,2,6),(211,27,1,0,6),(212,28,0,2,6),(213,29,0,0,6),(214,30,0,3,6),(215,31,1,1,6),(216,32,1,2,6),(217,33,1,2,6),(218,34,0,2,6),(219,35,1,0,6),(220,36,0,1,6),(223,1,2,0,8),(224,2,1,1,8),(225,3,1,2,8),(226,4,1,2,8),(227,5,2,2,8),(228,6,2,1,8),(229,7,3,1,8),(230,8,2,1,8),(231,9,1,1,8),(232,10,0,0,8),(233,11,2,1,8),(234,12,2,0,8),(235,13,2,1,8),(236,14,0,1,8),(237,15,3,0,8),(238,16,3,2,8),(239,17,3,1,8),(240,18,2,1,8),(241,19,2,1,8),(242,20,1,3,8),(243,21,3,2,8),(244,22,2,0,8),(245,23,1,1,8),(246,24,0,0,8),(247,25,1,1,8),(248,26,1,1,8),(249,27,3,1,8),(250,28,1,1,8),(251,29,2,1,8),(252,30,1,4,8),(253,31,1,2,8),(254,32,1,1,8),(255,33,0,1,8),(256,34,1,3,8),(257,35,2,1,8),(258,36,1,1,8),(259,1,2,0,9),(260,2,0,1,9),(261,3,2,0,9),(262,4,1,1,9),(263,5,0,1,9),(264,6,2,0,9),(265,7,2,0,9),(266,8,2,1,9),(267,9,0,1,9),(268,10,2,1,9),(269,11,1,0,9),(270,12,3,1,9),(271,13,1,0,9),(272,14,0,2,9),(273,15,2,0,9),(274,16,2,0,9),(275,17,1,0,9),(276,18,2,1,9),(277,19,2,2,9),(278,20,0,1,9),(279,21,3,0,9),(280,22,1,0,9),(281,23,0,1,9),(282,24,2,0,9),(283,25,1,1,9),(284,26,1,2,9),(285,27,1,0,9),(286,28,0,3,9),(287,29,2,2,9),(288,30,1,4,9),(289,31,2,0,9),(290,32,2,3,9),(291,33,0,1,9),(292,34,0,2,9),(293,35,2,1,9),(294,36,2,2,9),(295,1,2,0,10),(296,2,0,2,10),(297,3,1,0,10),(298,4,1,2,10),(299,5,1,2,10),(300,6,1,1,10),(301,7,2,0,10),(302,8,2,0,10),(303,9,1,2,10),(304,10,1,1,10),(305,11,1,0,10),(306,12,2,0,10),(307,13,1,0,10),(308,14,0,2,10),(309,15,3,0,10),(310,16,2,0,10),(311,17,2,1,10),(312,18,2,0,10),(313,19,2,1,10),(314,20,1,2,10),(315,21,3,0,10),(316,22,3,1,10),(317,23,2,1,10),(318,24,2,0,10),(319,25,0,0,10),(320,26,2,2,10),(321,27,2,1,10),(322,28,0,2,10),(323,29,1,0,10),(324,30,0,2,10),(325,31,1,2,10),(326,32,2,3,10),(327,33,2,2,10),(328,34,1,3,10),(329,35,0,0,10),(330,36,0,1,10);
/*!40000 ALTER TABLE `forecasts` ENABLE KEYS */;
UNLOCK TABLES;

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
  KEY `groups_in_tournament_group_names_group_name_id_fk` (`group_name`),
  KEY `groups_in_tournament_tournaments_tournament_id_fk` (`tournament_id`),
  CONSTRAINT `FKdfq8xjsopclpw9wuqo1gq5a1g` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`),
  CONSTRAINT `groups_in_tournament_tournaments_tournament_id_fk` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups_in_tournament`
--

LOCK TABLES `groups_in_tournament` WRITE;
/*!40000 ALTER TABLE `groups_in_tournament` DISABLE KEYS */;
INSERT INTO `groups_in_tournament` VALUES (17,23,2,'Группа А'),(18,23,2,'Группа B'),(19,23,2,'Группа C'),(20,23,2,'Группа D'),(21,23,2,'Полуфинал'),(23,23,2,'Группа F'),(25,23,2,'Группа E'),(26,1,1,'Группа А'),(27,1,1,'Группа B'),(28,1,1,'Группа C'),(29,1,1,'Группа D'),(30,1,1,'Группа E'),(31,1,1,'Группа F'),(32,1,1,'1\\8 финала'),(35,1,1,'1\\8 финала'),(36,1,1,'1\\8 финала'),(37,1,1,'1\\8 финала'),(38,1,1,'1\\8 финала'),(39,1,1,'1\\8 финала'),(40,1,1,'1\\8 финала'),(41,1,1,'1\\8 финала'),(42,1,1,'1\\4 финала'),(43,1,1,'1\\4 финала'),(44,1,1,'1\\4 финала'),(45,1,1,'1\\4 финала'),(46,1,1,'1\\2 финала'),(47,1,1,'1\\2 финала'),(48,1,1,'Финал');
/*!40000 ALTER TABLE `groups_in_tournament` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_comments`
--

DROP TABLE IF EXISTS `match_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match_comments` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_txt` text NOT NULL,
  `match_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `comment_datetime` datetime NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `MATCH_COMMENTS_fk0` (`match_id`),
  KEY `MATCH_COMMENTS_fk1` (`user_id`),
  CONSTRAINT `MATCH_COMMENTS_fk0` FOREIGN KEY (`match_id`) REFERENCES `matches` (`id`),
  CONSTRAINT `MATCH_COMMENTS_fk1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_comments`
--

LOCK TABLES `match_comments` WRITE;
/*!40000 ALTER TABLE `match_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `match_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_states`
--

DROP TABLE IF EXISTS `match_states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match_states` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `match_state` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `state_name` (`match_state`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_states`
--

LOCK TABLES `match_states` WRITE;
/*!40000 ALTER TABLE `match_states` DISABLE KEYS */;
INSERT INTO `match_states` VALUES (1,'Прием прогнозов окончен'),(2,'Принимаются прогнозы на матч');
/*!40000 ALTER TABLE `match_states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matches` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_team_result` int(11) DEFAULT NULL,
  `second_team_result` int(11) DEFAULT NULL,
  `match_datetime` datetime NOT NULL,
  `match_state_id` int(11) NOT NULL,
  `first_team_id` int(11) NOT NULL,
  `second_team_id` int(11) NOT NULL,
  `tournament_id` int(11) NOT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `PLANNED_MATCHES_fk0` (`match_state_id`),
  KEY `PLANNED_MATCHES_fk2` (`first_team_id`),
  KEY `PLANNED_MATCHES_fk3` (`second_team_id`),
  KEY `PLANNED_MATCHES_fk4` (`tournament_id`),
  KEY `FKh3c3yd9efqxoh5e67p7gid3p` (`group_id`),
  CONSTRAINT `FK12qdv6a7tt8esxtu2gma054ak` FOREIGN KEY (`first_team_id`) REFERENCES `teams` (`id`),
  CONSTRAINT `FKeeniokyjgo5k6rmhjujatn27i` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`),
  CONSTRAINT `FKh3c3yd9efqxoh5e67p7gid3p` FOREIGN KEY (`group_id`) REFERENCES `groups_in_tournament` (`id`),
  CONSTRAINT `FKhpg917fgh6hls86378l46n3cb` FOREIGN KEY (`second_team_id`) REFERENCES `teams` (`id`),
  CONSTRAINT `FKkfaepnj7ioiq0juurho5r4iqa` FOREIGN KEY (`match_state_id`) REFERENCES `match_states` (`id`),
  CONSTRAINT `PLANNED_MATCHES_fk0` FOREIGN KEY (`match_state_id`) REFERENCES `match_states` (`id`),
  CONSTRAINT `PLANNED_MATCHES_fk2` FOREIGN KEY (`first_team_id`) REFERENCES `teams` (`id`),
  CONSTRAINT `PLANNED_MATCHES_fk3` FOREIGN KEY (`second_team_id`) REFERENCES `teams` (`id`),
  CONSTRAINT `PLANNED_MATCHES_fk4` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`),
  CONSTRAINT `matches_groups_in_tournament_id_fk` FOREIGN KEY (`group_id`) REFERENCES `groups_in_tournament` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matches`
--

LOCK TABLES `matches` WRITE;
/*!40000 ALTER TABLE `matches` DISABLE KEYS */;
INSERT INTO `matches` VALUES (1,2,1,'2016-06-10 17:00:00',2,1,2,1,26),(2,0,1,'2016-06-10 17:00:00',2,3,4,1,26),(3,2,1,'2016-06-11 17:00:00',2,5,6,1,27),(4,1,1,'2016-06-11 19:00:00',2,7,8,1,27),(5,0,1,'2016-06-12 17:00:00',2,9,10,1,29),(6,1,0,'2016-06-12 19:00:00',2,11,12,1,28),(7,2,0,'2016-06-12 21:00:00',2,13,14,1,28),(8,1,0,'2016-06-13 17:00:00',2,15,16,1,29),(9,1,1,'2016-06-13 19:00:00',2,17,18,1,30),(10,0,2,'2016-06-13 21:00:00',2,19,20,1,30),(11,0,2,'2016-06-14 17:00:00',2,21,22,1,31),(12,1,1,'2016-06-14 19:00:00',2,23,24,1,31),(13,1,2,'2016-06-15 17:00:00',2,8,6,1,27),(14,1,1,'2016-06-15 19:00:00',2,2,4,1,26),(15,2,0,'2016-06-15 21:00:00',2,1,3,1,26),(16,2,1,'2016-06-16 17:00:00',2,7,5,1,27),(17,0,2,'2016-06-16 19:00:00',2,14,12,1,28),(18,0,0,'2016-06-16 21:00:00',2,13,11,1,28),(19,1,0,'2016-06-17 17:00:00',2,20,18,1,30),(20,2,2,'2016-06-17 19:00:00',2,16,10,1,29),(21,3,0,'2016-06-17 21:00:00',2,15,9,1,29),(22,3,0,'2016-06-18 17:00:00',2,19,17,1,30),(23,1,1,'2016-06-18 19:00:00',2,24,22,1,31),(24,0,0,'2016-06-18 21:00:00',2,23,21,1,31),(25,0,1,'2016-06-19 17:00:00',2,2,3,1,26),(26,0,0,'2016-06-19 19:00:00',2,4,1,1,26),(27,0,3,'2016-06-20 17:00:00',2,8,5,1,27),(28,0,0,'2016-06-20 19:00:00',2,6,7,1,27),(29,0,1,'2016-06-21 17:00:00',2,14,11,1,28),(30,0,1,'2016-06-21 19:00:00',2,12,13,1,28),(31,0,2,'2016-06-21 21:00:00',2,16,9,1,29),(32,2,1,'2016-06-21 23:00:00',2,10,15,1,29),(33,2,1,'2016-06-22 17:00:00',2,24,21,1,31),(34,3,3,'2016-06-22 19:00:00',2,22,23,1,31),(35,0,1,'2016-06-22 21:00:00',2,20,17,1,30),(36,0,1,'2016-06-22 23:00:00',2,18,19,1,30),(86,1,1,'2016-06-25 19:00:00',2,4,11,1,32),(87,0,1,'2016-06-25 21:00:00',2,10,23,1,35),(88,1,0,'2016-06-26 19:00:00',2,5,12,1,36),(89,0,4,'2016-06-26 21:00:00',2,22,19,1,37),(90,3,0,'2016-06-27 19:00:00',2,13,6,1,38),(91,2,0,'2016-06-27 19:00:00',2,20,15,1,39),(92,1,2,'2016-06-27 19:00:00',2,7,24,1,40),(93,2,1,'2016-06-28 19:00:00',2,1,17,1,41),(94,1,1,'2016-06-30 19:00:00',2,11,23,1,42),(95,3,1,'2016-07-01 19:00:00',2,5,19,1,43),(96,1,1,'2016-07-02 19:00:00',2,13,20,1,44),(97,5,2,'2016-07-03 19:00:00',2,1,24,1,45),(98,2,0,'2016-07-06 19:00:00',2,23,5,1,46),(99,0,2,'2016-07-07 19:00:00',2,13,1,1,47),(100,1,0,'2016-07-10 19:00:00',2,23,1,1,48);
/*!40000 ALTER TABLE `matches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playoff_groups`
--

DROP TABLE IF EXISTS `playoff_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playoff_groups` (
  `group_id` int(11) NOT NULL,
  `is_extra_time_allowed` tinyint(1) NOT NULL,
  PRIMARY KEY (`group_id`),
  CONSTRAINT `FK43xpsgvlhprptt1ow8ia78jjw` FOREIGN KEY (`group_id`) REFERENCES `groups_in_tournament` (`id`),
  CONSTRAINT `playoff_groups_groups_in_tournament_group_id_fk` FOREIGN KEY (`group_id`) REFERENCES `groups_in_tournament` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playoff_groups`
--

LOCK TABLES `playoff_groups` WRITE;
/*!40000 ALTER TABLE `playoff_groups` DISABLE KEYS */;
INSERT INTO `playoff_groups` VALUES (21,0),(32,1),(35,1),(36,1),(37,1),(38,1),(39,1),(40,1),(41,1),(42,1),(43,0),(44,1),(45,1),(46,1),(47,1),(48,1);
/*!40000 ALTER TABLE `playoff_groups` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `registration_desc` VALUES (1,1),(7,1),(9,1),(10,1),(11,1),(12,1),(15,1),(23,1),(24,1),(1,2),(1,3),(4,3),(1,4),(1,5),(1,6),(1,7),(1,8),(6,8),(7,8),(9,8),(1,9),(1,10),(2,11),(3,11),(4,11),(9,49),(23,49);
/*!40000 ALTER TABLE `registration_desc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regular_groups`
--

DROP TABLE IF EXISTS `regular_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regular_groups` (
  `group_id` int(11) NOT NULL,
  `teams_count` int(11) NOT NULL,
  `group_out_count` int(11) NOT NULL,
  PRIMARY KEY (`group_id`),
  CONSTRAINT `FKelt97vnxmxjifjmlfum07mpb7` FOREIGN KEY (`group_id`) REFERENCES `groups_in_tournament` (`id`),
  CONSTRAINT `regular_groups_groups_in_tournament_group_id_fk` FOREIGN KEY (`group_id`) REFERENCES `groups_in_tournament` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regular_groups`
--

LOCK TABLES `regular_groups` WRITE;
/*!40000 ALTER TABLE `regular_groups` DISABLE KEYS */;
INSERT INTO `regular_groups` VALUES (17,4,2),(18,4,2),(19,4,2),(20,4,2),(23,4,2),(25,4,2),(26,4,2),(27,4,2),(28,4,2),(29,4,2),(30,4,2),(31,4,2);
/*!40000 ALTER TABLE `regular_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `teams_team_name_uindex` (`team_name`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` VALUES (34,'Австралия'),(21,'Австрия'),(3,'Албания'),(7,'Англия'),(38,'Аргентина'),(19,'Бельгия'),(25,'Бразилия'),(22,'Венгрия'),(13,'Германия'),(33,'Египет'),(26,'Иран'),(17,'Ирландия'),(24,'Исландия'),(15,'Испания'),(20,'Италия'),(39,'Колумбия'),(29,'Корея'),(32,'Коста-Рика'),(28,'Мексика'),(31,'Нигерия'),(36,'Перу'),(11,'Польша'),(23,'Португалия'),(8,'Россия'),(2,'Румыния'),(30,'Саудовская Аравия'),(12,'Сев. Ирландия'),(35,'Сербия'),(6,'Словакия'),(9,'Турция'),(14,'Украина'),(37,'Уругвай'),(5,'Уэльс'),(1,'Франция'),(10,'Хорватия'),(16,'Чехия'),(4,'Швейцария'),(18,'Швеция'),(27,'Япония');
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `teams_in_groups` VALUES (17,6),(20,11),(17,16),(17,24),(17,12),(21,17),(21,14),(23,14),(20,26),(20,27),(18,22),(18,30),(18,25),(18,1),(23,2),(23,29),(23,23),(19,3),(19,31),(19,19),(19,17),(25,4),(25,5),(25,37),(25,32),(20,18),(26,1),(26,4),(26,3),(26,2),(27,5),(27,7),(27,6),(27,8),(28,13),(28,11),(28,12),(28,14),(29,10),(29,15),(29,9),(29,16),(30,20),(30,19),(30,17),(30,18),(31,22),(31,24),(31,23),(31,21),(32,11),(32,4),(35,10),(35,23),(36,5),(36,12),(37,22),(37,19),(38,13),(38,6),(39,20),(39,15),(41,1),(41,17),(40,7),(40,24),(42,11),(42,23),(43,5),(43,19),(44,13),(44,20),(45,1),(45,24),(46,23),(46,5),(47,13),(47,1),(48,23),(48,1);
/*!40000 ALTER TABLE `teams_in_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tournament_states`
--

DROP TABLE IF EXISTS `tournament_states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tournament_states` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tournament_state` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tornament_state` (`tournament_state`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournament_states`
--

LOCK TABLES `tournament_states` WRITE;
/*!40000 ALTER TABLE `tournament_states` DISABLE KEYS */;
INSERT INTO `tournament_states` VALUES (1,'активен'),(2,'окончен');
/*!40000 ALTER TABLE `tournament_states` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournaments`
--

LOCK TABLES `tournaments` WRITE;
/*!40000 ALTER TABLE `tournaments` DISABLE KEYS */;
INSERT INTO `tournaments` VALUES (1,'Чемпионат Европы 2016',1,'2016-06-10',2),(2,'updated964000',2,'2018-06-10',2),(3,'808373',2,'2018-06-10',2),(4,'857167',2,'2018-06-10',2),(5,'529727',2,'2018-06-10',2),(6,'964626',2,'2018-06-10',2),(7,'Тестовый турнир12',39,'2020-08-12',2),(8,'Турнирчик',31,'2019-12-12',2),(9,'Тестовый турнир13',32,'2018-10-10',1),(10,'New tournament 15',38,'2020-08-12',1),(11,'New Tournament 16',17,'2020-10-10',1),(12,'тур12',22,'2020-08-12',2),(13,'729468',2,'2017-11-29',2),(14,'401983',2,'2017-11-30',2),(15,'Турнир в Японии',26,'2018-12-10',2),(16,'Турнир в Японии 2',21,'2018-12-10',2),(17,'Турнир в Японии 3',21,'2018-12-12',2),(18,'Турнир в Японии 4',21,'2018-12-10',2),(19,'Турнир в Японии 5',21,'2018-12-12',2),(20,'Турнир в Японии 7',21,'2018-12-12',2),(21,'Новый турнир 20',20,'2017-12-10',2),(22,'Турнир Минск',38,'2017-12-22',2),(23,'JD2 tournament',15,'2018-03-16',1),(24,'JD2 tournament2 ',15,'2018-03-25',1),(25,'JD2 tournament 3',34,'2018-03-31',2),(27,'JD2 tournament 4',22,'2018-03-31',1);
/*!40000 ALTER TABLE `tournaments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_states`
--

DROP TABLE IF EXISTS `user_states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_states` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_state` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_state_name_uindex` (`user_state`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_states`
--

LOCK TABLES `user_states` WRITE;
/*!40000 ALTER TABLE `user_states` DISABLE KEYS */;
INSERT INTO `user_states` VALUES (4,'ADMIN'),(3,'BLOCKED USER'),(2,'POWER_USER'),(1,'USER');
/*!40000 ALTER TABLE `user_states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) NOT NULL,
  `second_name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `user_state_id` int(11) NOT NULL,
  `login` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `version` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `users_login_uindex` (`login`),
  KEY `user_stets_fk1_idx` (`user_state_id`),
  CONSTRAINT `FKmmi11gs4jb9rlm3dbqx37qs8x` FOREIGN KEY (`user_state_id`) REFERENCES `user_states` (`id`),
  CONSTRAINT `user_stets_fk1` FOREIGN KEY (`user_state_id`) REFERENCES `user_states` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Андрей','Рижницын','ra@tut.by',4,'rizhnitsyn','$2a$10$LvhokuoUs6D4UvX43Ty32.sDoYmDWcy1n.QbkOsNZx1bQmeUb1PuS','2018-03-20 00:15:57.348000'),(2,'Сергей','Гусаков','sg@tut.by',2,'gusakov','11111','2018-03-18 15:49:05.384000'),(3,'Роман','Жук','rzh@tut.by',2,'zhuk','11111','2018-03-18 15:49:05.384000'),(4,'Никита','Щербич','nsch@tut.by',2,'scherbich','11111','2018-03-18 15:49:05.384000'),(5,'Виталий','Назарян','vn@tut.by',2,'nazaryn','11111','2018-03-18 15:49:05.384000'),(6,'Гайк','Бегларян','gb@tut.by',2,'asdsd','11111','2018-03-18 15:49:05.384000'),(7,'Руслан','Акиловский','ar@tut.by',2,'asdsdsdsd','11111','2018-03-18 15:49:05.384000'),(8,'Андрей','Иванов','ai@tut.by',2,'asdasdasdsad','11111','2018-03-18 15:49:05.384000'),(9,'Камал','Бикулов','kb@tut.by',2,'asdasdsdsdsdsd','11111','2018-03-18 15:49:05.384000'),(10,'Изабелла','Аванессова','izab@tut.by',2,'zxczxczxc','11111','2018-03-18 15:49:05.384000'),(11,'updated415002','Ivanov','test@gmail.com',2,'zxcxc','11111','2018-03-18 15:49:05.384000'),(12,'Ivan','129618','test37447@gmail.com',2,'vbxcvb','11111','2018-03-18 15:49:05.384000'),(13,'Ivan','851874','test78828@gmail.com',3,'cvbcv','11111','2018-03-18 15:49:05.384000'),(15,'Иван ','Иван ','iv@bn.by',2,'nbvn','11111','2018-03-18 15:49:05.384000'),(16,'Иваныч ','Иваныч ','ivb@bnb.by',3,'hjytrj','11111','2018-03-18 15:49:05.384000'),(17,'Петя','Петя','ivbb@bnb.by',2,'ergr','11111','2018-03-18 15:49:05.384000'),(18,'иван','Ткачев','ert@tut.by',2,'dcvbed','11111','2018-03-18 15:49:05.384000'),(19,'иван','Ткачевич','ertm@tut.by',1,'cvbg','11111','2018-03-18 15:49:05.384000'),(20,'Ivan','180264','test4180@gmail.com',1,'fgjhr','11111','2018-03-18 15:49:05.384000'),(21,'Андрей','Коньков','kon@tut.by',1,'konkov','11111','2018-03-18 15:49:05.384000'),(22,'Иван','Петро','petro@tut.by',3,'petro','11111','2018-03-18 15:49:05.384000'),(23,'Олег','Петрович','oleg@tut.by',1,'oleg','11111','2018-03-18 15:49:05.384000'),(24,'Кирилл ','Вольски','vol@tut.by',2,'volskij','11111','2018-03-18 15:49:05.384000'),(25,'Андрей','Копылов','kopi@tut.by',2,'kopilov','11111','2018-03-18 15:49:05.384000'),(26,'Андре','Мелешкевич','mele@tut.by',1,'melesh','11111','2018-03-18 15:49:05.384000'),(27,'Глеб','Маеев','makeev@tut.by',2,'ьфлуум','11111','2018-03-18 15:49:05.384000'),(39,'admin','admin','admin@tut.by',4,'admin','b0baee9d279d34fa1dfd71aadb908c3f','2018-03-18 15:49:05.384000'),(40,'asdsd','sadasd','ra@dffdf.com',1,'cvdcwecwe','d29aaa0b9cd402b4bfe2395a805f9ada','2018-03-18 15:53:29.610000'),(41,'Ivanchello','Petterson','asdasd123123@tut.by',2,'asdksadksdf','dasdjfidjfid','2018-03-18 15:49:05.384000'),(42,'User','User','user@gmail.com',2,'user','11111','2018-03-18 15:49:05.384000'),(43,'Андрей','Рижницын','enotius@gmail.com',1,'asdasdasd','123112341','2018-03-18 15:49:05.384000'),(44,'User45','иванович','qweasd@qweasd.by',1,'qweasd','$2a$10$T.SeF3y7LNRqUGaAlw/EMud67dZobkFkzRIa4lS6cKB8yrVYdLtOm','2018-03-18 15:53:38.552000'),(48,'test1','fsdfsdf','qweasdf@by.by',1,'qqqqqq','$2a$10$RFwmMTbpupgP/jJFkZ274OwwG1M3DrzpSYHf1emWlYPG.cHEEludC','2018-03-18 15:49:05.384000'),(49,'power','power','power@tut.by',1,'power','$2a$10$UkmB3FCogINF8/SLz4P01eb.l65vYEIO0KY49Rxs8TaTMeQRgrVTa','2018-03-18 16:21:52.137000');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-20 17:19:57

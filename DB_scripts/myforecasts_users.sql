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
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `users_login_uindex` (`login`),
  KEY `user_stets_fk1_idx` (`user_state_id`),
  CONSTRAINT `FKmmi11gs4jb9rlm3dbqx37qs8x` FOREIGN KEY (`user_state_id`) REFERENCES `user_states` (`id`),
  CONSTRAINT `user_stets_fk1` FOREIGN KEY (`user_state_id`) REFERENCES `user_states` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Андрей','Рижницын','ra@tut.by',4,'rizhnitsyn','$2a$10$OsWIrbsZIYo0yv6tZzv8CubsNYVtlyoe/OZaGFxG0OTk8jE14zfU2'),(2,'Сергей','Гусаков','sg@tut.by',2,'gusakov',''),(3,'Роман','Жук','rzh@tut.by',2,'zhuk',''),(4,'Никита','Щербич','nsch@tut.by',2,'scherbich',''),(5,'Виталий','Назарян','vn@tut.by',2,'nazaryn',''),(6,'Гайк','Бегларян','gb@tut.by',2,'asdsd',''),(7,'Руслан','Акиловский','ar@tut.by',2,'asdsdsdsd',''),(8,'Андрей','Иванов','ai@tut.by',2,'asdasdasdsad',''),(9,'Камал','Бикулов','kb@tut.by',2,'asdasdsdsdsdsd',''),(10,'Изабелла','Аванессова','izab@tut.by',2,'zxczxczxc',''),(11,'updated415002','Ivanov','test@gmail.com',2,'zxcxc',''),(12,'Ivan','129618','test37447@gmail.com',2,'vbxcvb',''),(13,'Ivan','851874','test78828@gmail.com',3,'cvbcv',''),(14,'Тестик','Номер1','выаыва',3,'mnmy',''),(15,'Иван ','Иван ','iv@bn.by',2,'nbvn',''),(16,'Иваныч ','Иваныч ','ivb@bnb.by',3,'hjytrj',''),(17,'Петя','Петя','ivbb@bnb.by',2,'ergr',''),(18,'иван','Ткачев','ert@tut.by',2,'dcvbed',''),(19,'иван','Ткачевич','ertm@tut.by',1,'cvbg',''),(20,'Ivan','180264','test4180@gmail.com',1,'fgjhr',''),(21,'Андрей','Коньков','kon@tut.by',1,'konkov','11111'),(22,'Иван','Петро','petro@tut.by',3,'petro','11111'),(23,'Олег','Петрович','oleg@tut.by',1,'oleg','11111'),(24,'Кирилл ','Вольски','vol@tut.by',2,'volskij','11111'),(25,'Андрей','Копылов','kopi@tut.by',2,'kopilov','11111'),(26,'Андре','Мелешкевич','mele@tut.by',1,'melesh','11111'),(27,'Глеб','Маеев','makeev@tut.by',2,'ьфлуум','11111'),(28,'sdfdsf','sdfsdfdf','@tut.nby',3,'sdfsdfsdfsdf','11111'),(29,'sdfsdf','sdfsdf','sdfsdf',3,'xcv','sfsdf'),(30,'huhuh','hgfhfh','gihuhuh',3,'vefvervrvb','11111'),(31,'huhuhwerw','hgfhfhwere','gihuhuhwer',3,'dytvjbgywer','11111ere'),(32,'sdfsdf','gawghsvb','sdfhjwrtjjdf',3,'verwvqrv','asfe4tjtj'),(33,'asdasd','asdfaasdad','sfsdfsdfsdf',3,'vgxcbbtebn','asdasdasdasd'),(34,'tyu','tyu','tyu',3,'xcvcv','tyu'),(35,'fgjfgjh','fjfgj','fgjhfgj',3,'fjfgj','af242e2abfda7079d2d56c8987013cd2'),(36,'shshs','shshsh','shshshsh',3,'btetne4tnzxv','2087d87063b08c54aa31012ed012bacb'),(37,'sdfsdfsdf','sdfsdfsdfsdf','asdasd',3,'test','b59c67bf196a4758191e42f76670ceba'),(38,'test1','kasjdaskdj','dasd',3,'test3','b0baee9d279d34fa1dfd71aadb908c3f'),(39,'admin','admin','admin@tut.by',4,'admin','b0baee9d279d34fa1dfd71aadb908c3f'),(40,'asdsd','sadasd','ra@dffdf.com',1,'cvdcwecwe','d29aaa0b9cd402b4bfe2395a805f9ada'),(41,'Ivanchello','Petterson','asdasd123123@tut.by',2,'asdksadksdf','dasdjfidjfid'),(42,'User','User','user@gmail.com',2,'user','11111'),(43,'Андрей','Рижницын','enotius@gmail.com',1,'asdasdasd','123112341'),(44,'User45','иванович','qweasd@qweasd.by',1,'qweasd','$2a$10$T.SeF3y7LNRqUGaAlw/EMud67dZobkFkzRIa4lS6cKB8yrVYdLtOm');
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

-- Dump completed on 2018-03-14 23:47:46

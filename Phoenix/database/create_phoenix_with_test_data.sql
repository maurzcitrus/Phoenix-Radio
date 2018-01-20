-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: phoenix
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `program_slot`
--

DROP TABLE IF EXISTS `program_slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program_slot` (
  `slot_id` int(11) NOT NULL AUTO_INCREMENT,
  `program_id` int(11) NOT NULL,
  `presenter_id` varchar(40) NOT NULL,
  `producer_id` varchar(40) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `updated_by` varchar(40) NOT NULL,
  PRIMARY KEY (`slot_id`),
  UNIQUE KEY `start_UNIQUE` (`start`),
  UNIQUE KEY `end_UNIQUE` (`end`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program_slot`
--

LOCK TABLES `program_slot` WRITE;
/*!40000 ALTER TABLE `program_slot` DISABLE KEYS */;
INSERT INTO `program_slot` VALUES (33,73,'justin','dogbert','2017-10-02 06:00:00','2017-10-02 10:00:00','pointyhead'),(34,74,'yasminne','dogbert','2017-10-02 10:05:00','2017-10-02 14:00:00','pointyhead'),(35,75,'jean','dogbert','2017-10-02 17:00:00','2017-10-02 20:00:00','pointyhead'),(36,76,'simone','dilbert','2017-10-02 14:05:00','2017-10-02 16:55:00','pointyhead'),(37,77,'simone','dogbert','2017-10-02 21:00:00','2017-10-02 23:55:00','pointyhead'),(93,78,'dilbert','dogbert','2017-10-04 06:00:00','2017-10-04 06:30:00','pointyhead'),(94,79,'jean','dogbert','2017-10-04 06:31:00','2017-10-04 07:00:00','pointyhead'),(95,78,'dilbert','dogbert','2017-10-04 07:01:00','2017-10-04 07:29:00','pointyhead'),(96,80,'yasminne','dogbert','2017-10-04 07:30:00','2017-10-04 08:00:00','pointyhead'),(97,78,'dilbert','dogbert','2017-10-04 08:01:00','2017-10-04 08:29:00','pointyhead'),(98,80,'justin','dogbert','2017-10-04 08:30:00','2017-10-04 09:00:00','pointyhead'),(99,66,'jean','dogbert','2017-10-04 09:30:00','2017-10-04 10:30:00','pointyhead'),(100,77,'justin','dogbert','2017-10-03 06:00:00','2017-10-03 10:00:00','pointyhead'),(101,66,'yasminne','dogbert','2017-10-03 10:05:00','2017-10-03 14:00:00','pointyhead'),(102,75,'jean','dogbert','2017-10-03 17:00:00','2017-10-03 20:00:00','pointyhead'),(103,76,'simone','dilbert','2017-10-03 14:05:00','2017-10-03 16:55:00','pointyhead'),(104,77,'simone','dogbert','2017-10-03 21:00:00','2017-10-03 23:55:00','pointyhead'),(107,73,'justin','dogbert','2017-10-05 06:00:00','2017-10-05 10:00:00','pointyhead'),(108,74,'yasminne','dogbert','2017-10-05 10:05:00','2017-10-05 14:00:00','pointyhead'),(109,76,'simone','dilbert','2017-10-05 14:05:00','2017-10-05 16:55:00','pointyhead'),(110,75,'jean','dogbert','2017-10-05 17:00:00','2017-10-05 20:00:00','pointyhead'),(111,77,'simone','dogbert','2017-10-05 21:00:00','2017-10-05 23:55:00','pointyhead'),(114,77,'justin','dogbert','2017-10-06 06:00:00','2017-10-06 10:00:00','pointyhead'),(115,66,'yasminne','dogbert','2017-10-06 10:05:00','2017-10-06 14:00:00','pointyhead'),(116,75,'jean','dogbert','2017-10-06 17:00:00','2017-10-06 20:00:00','pointyhead'),(117,76,'simone','dilbert','2017-10-06 14:05:00','2017-10-06 16:55:00','pointyhead'),(118,77,'simone','dogbert','2017-10-06 21:00:00','2017-10-06 23:55:00','pointyhead'),(121,79,'jean','dogbert','2017-10-07 06:31:00','2017-10-07 07:00:00','pointyhead'),(122,80,'yasminne','dogbert','2017-10-07 07:30:00','2017-10-07 08:00:00','pointyhead'),(123,80,'justin','dogbert','2017-10-07 08:30:00','2017-10-07 09:00:00','pointyhead'),(124,66,'jean','dogbert','2017-10-07 09:30:00','2017-10-07 10:30:00','pointyhead'),(128,73,'justin','dogbert','2017-10-16 06:00:00','2017-10-16 10:00:00','pointyhead'),(129,74,'yasminne','dogbert','2017-10-16 10:05:00','2017-10-16 14:00:00','pointyhead'),(130,75,'jean','dogbert','2017-10-16 17:00:00','2017-10-16 20:00:00','pointyhead'),(131,76,'simone','dilbert','2017-10-16 14:05:00','2017-10-16 16:55:00','pointyhead'),(132,77,'simone','dogbert','2017-10-16 21:00:00','2017-10-16 23:55:00','pointyhead'),(133,78,'dilbert','dogbert','2017-10-18 06:00:00','2017-10-18 06:30:00','pointyhead'),(134,79,'jean','dogbert','2017-10-18 06:31:00','2017-10-18 07:00:00','pointyhead'),(135,78,'dilbert','dogbert','2017-10-18 07:01:00','2017-10-18 07:29:00','pointyhead'),(136,80,'yasminne','dogbert','2017-10-18 07:30:00','2017-10-18 08:00:00','pointyhead'),(137,78,'dilbert','dogbert','2017-10-18 08:01:00','2017-10-18 08:29:00','pointyhead'),(138,80,'justin','dogbert','2017-10-18 08:30:00','2017-10-18 09:00:00','pointyhead'),(139,66,'jean','dogbert','2017-10-18 09:30:00','2017-10-18 10:30:00','pointyhead'),(140,77,'justin','dogbert','2017-10-17 06:00:00','2017-10-17 10:00:00','pointyhead'),(141,66,'yasminne','dogbert','2017-10-17 10:05:00','2017-10-17 14:00:00','pointyhead'),(142,75,'jean','dogbert','2017-10-17 17:00:00','2017-10-17 20:00:00','pointyhead'),(143,76,'simone','dilbert','2017-10-17 14:05:00','2017-10-17 16:55:00','pointyhead'),(144,77,'simone','dogbert','2017-10-17 21:00:00','2017-10-17 23:55:00','pointyhead'),(145,73,'justin','dogbert','2017-10-19 06:00:00','2017-10-19 10:00:00','pointyhead'),(146,74,'yasminne','dogbert','2017-10-19 10:05:00','2017-10-19 14:00:00','pointyhead'),(147,76,'simone','dilbert','2017-10-19 14:05:00','2017-10-19 16:55:00','pointyhead'),(148,75,'jean','dogbert','2017-10-19 17:00:00','2017-10-19 20:00:00','pointyhead'),(149,77,'simone','dogbert','2017-10-19 21:00:00','2017-10-19 23:55:00','pointyhead'),(150,77,'justin','dogbert','2017-10-20 06:00:00','2017-10-20 10:00:00','pointyhead'),(151,66,'yasminne','dogbert','2017-10-20 10:05:00','2017-10-20 14:00:00','pointyhead'),(152,75,'jean','dogbert','2017-10-20 17:00:00','2017-10-20 20:00:00','pointyhead'),(153,76,'simone','dilbert','2017-10-20 14:05:00','2017-10-20 16:55:00','pointyhead'),(154,77,'simone','dogbert','2017-10-20 21:00:00','2017-10-20 23:55:00','pointyhead'),(155,79,'jean','dogbert','2017-10-21 06:31:00','2017-10-21 07:00:00','pointyhead'),(156,80,'yasminne','dogbert','2017-10-21 07:30:00','2017-10-21 08:00:00','pointyhead'),(157,80,'justin','dogbert','2017-10-21 08:30:00','2017-10-21 09:00:00','pointyhead'),(158,66,'jean','dogbert','2017-10-21 09:30:00','2017-10-21 10:30:00','pointyhead'),(159,73,'justin','dogbert','2017-10-09 06:00:00','2017-10-09 10:00:00','pointyhead'),(160,74,'yasminne','dogbert','2017-10-09 10:05:00','2017-10-09 14:00:00','pointyhead'),(161,75,'jean','dogbert','2017-10-09 17:00:00','2017-10-09 20:00:00','pointyhead'),(162,76,'simone','dilbert','2017-10-09 14:05:00','2017-10-09 16:55:00','pointyhead'),(163,77,'simone','dogbert','2017-10-09 21:00:00','2017-10-09 23:55:00','pointyhead'),(164,78,'dilbert','dogbert','2017-10-11 06:00:00','2017-10-11 06:30:00','pointyhead'),(165,79,'jean','dogbert','2017-10-11 06:31:00','2017-10-11 07:00:00','pointyhead'),(166,78,'dilbert','dogbert','2017-10-11 07:01:00','2017-10-11 07:29:00','pointyhead'),(167,80,'yasminne','dogbert','2017-10-11 07:30:00','2017-10-11 08:00:00','pointyhead'),(168,78,'dilbert','dogbert','2017-10-11 08:01:00','2017-10-11 08:29:00','pointyhead'),(169,80,'justin','dogbert','2017-10-11 08:30:00','2017-10-11 09:00:00','pointyhead'),(170,66,'jean','dogbert','2017-10-11 09:30:00','2017-10-11 10:30:00','pointyhead'),(171,77,'justin','dogbert','2017-10-10 06:00:00','2017-10-10 10:00:00','pointyhead'),(172,66,'yasminne','dogbert','2017-10-10 10:05:00','2017-10-10 14:00:00','pointyhead'),(173,75,'jean','dogbert','2017-10-10 17:00:00','2017-10-10 20:00:00','pointyhead'),(174,76,'simone','dilbert','2017-10-10 14:05:00','2017-10-10 16:55:00','pointyhead'),(175,77,'simone','dogbert','2017-10-10 21:00:00','2017-10-10 23:55:00','pointyhead'),(176,73,'justin','dogbert','2017-10-12 06:00:00','2017-10-12 10:00:00','pointyhead'),(177,74,'yasminne','dogbert','2017-10-12 10:05:00','2017-10-12 14:00:00','pointyhead'),(178,76,'simone','dilbert','2017-10-12 14:05:00','2017-10-12 16:55:00','pointyhead'),(179,75,'jean','dogbert','2017-10-12 17:00:00','2017-10-12 20:00:00','pointyhead'),(180,77,'simone','dogbert','2017-10-12 21:00:00','2017-10-12 23:55:00','pointyhead'),(181,77,'justin','dogbert','2017-10-13 06:00:00','2017-10-13 10:00:00','pointyhead'),(182,66,'yasminne','dogbert','2017-10-13 10:05:00','2017-10-13 14:00:00','pointyhead'),(183,75,'jean','dogbert','2017-10-13 17:00:00','2017-10-13 20:00:00','pointyhead'),(184,76,'simone','dilbert','2017-10-13 14:05:00','2017-10-13 16:55:00','pointyhead'),(185,77,'simone','dogbert','2017-10-13 21:00:00','2017-10-13 23:55:00','pointyhead'),(186,79,'jean','dogbert','2017-10-14 06:31:00','2017-10-14 07:00:00','pointyhead'),(187,80,'yasminne','dogbert','2017-10-14 07:30:00','2017-10-14 08:00:00','pointyhead'),(188,80,'justin','dogbert','2017-10-14 08:30:00','2017-10-14 09:00:00','pointyhead'),(189,66,'jean','dogbert','2017-10-14 09:30:00','2017-10-14 10:30:00','pointyhead');
/*!40000 ALTER TABLE `program_slot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `radio_program`
--

DROP TABLE IF EXISTS `radio_program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `radio_program` (
  `program_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `typical_duration` int(11) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`program_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `radio_program`
--

LOCK TABLES `radio_program` WRITE;
/*!40000 ALTER TABLE `radio_program` DISABLE KEYS */;
INSERT INTO `radio_program` VALUES (65,'80\'s Rock','80\'s Rock songs',1,'dilbert'),(66,'Blues','Blues songs',30,'dilbert'),(73,'Muttons in the morning','Explore the lighter side of Singapore with you',240,'pointyhead'),(74,'Lunchbreak with Yasminne','Various topics with Yasminne Cheng',120,'pointyhead'),(75,'Cartunes','Ride out of the office with the best mix of music',180,'pointyhead'),(76,'Afternoons ','Classic love songs with fresh take on modern dating',180,'pointyhead'),(77,'Love Songs','The all new Love Songs with Elias and Simone',240,'pointyhead'),(78,'News Headlines','News, Business and Sports Headlines and Analysis',30,'pointyhead'),(79,'Global View','A review of local and global news paper',30,'pointyhead'),(80,'Talkback','Your vies on the hot issues of today',30,'pointyhead');
/*!40000 ALTER TABLE `radio_program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` varchar(15) NOT NULL,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_id_UNIQUE` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('admin','System Administrator'),('manager','Station Manager'),('presenter','Radio Program Presenter'),('producer','Program Producer');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(40) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('catbert','catbert','catbert, the hr'),('dilbert','dilbert','dilbert, the hero'),('dogbert','dogbert','dogbert, the CEO'),('federer','federer','Roger Federer'),('jean','jean','Jean Danker'),('justin','justin','Justin Ang'),('pointyhead','pointyhead','pointyhead, the manager'),('simone','simone','Simone Heng'),('wally','wally','wally, the bludger'),('yasminne','yasminne','Yasminne Cheng');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` varchar(40) NOT NULL,
  `role_id` varchar(15) NOT NULL,
  `updated_by` varchar(40) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_role_id_idx` (`role_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('catbert','admin','system'),('catbert','manager','system'),('dilbert','presenter','system'),('dilbert','producer','system'),('dogbert','admin','system'),('dogbert','producer','system'),('federer','admin','system'),('federer','manager','system'),('jean','presenter','system'),('justin','presenter','system'),('pointyhead','manager','system'),('simone','presenter','system'),('wally','producer','system'),('yasminne','presenter','system');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'phoenix'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-04 18:55:01

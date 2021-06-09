-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: coin
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `link`
--

DROP TABLE IF EXISTS `link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `link` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sourceId` bigint NOT NULL,
  `targetId` bigint NOT NULL,
  `graphId` bigint NOT NULL,
  `relationName` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isFullLine` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`,`graphId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link`
--

LOCK TABLES `link` WRITE;
/*!40000 ALTER TABLE `link` DISABLE KEYS */;
INSERT INTO `link` VALUES (1,1,2,2,'actor_bio','Actor_Info','Actor_Info',1),(2,1,3,2,'actor_foreName','Actor_Info','Actor_Info',1),(3,1,4,2,'actor_nationality','Actor_Info','Actor_Info',1),(4,1,5,2,'actor_constellation','Actor_Info','Actor_Info',1),(5,1,6,2,'actor_birthPlace','Actor_Info','Actor_Info',1),(6,1,7,2,'actor_birthDay','Actor_Info','Actor_Info',1),(7,1,8,2,'actor_repWorks','Actor_Info','Actor_Info',1),(8,1,9,2,'actor_achiem','Actor_Info','Actor_Info',1),(9,1,10,2,'actor_brokerage','Actor_Info','Actor_Info',1),(10,1,11,2,'Actor_Movie','Actor_Movie','Actor_Movie',1),(11,11,12,2,'movie_bio','Movie_Info','Movie_Info',1),(12,11,13,2,'movie_foreName','Movie_Info','Movie_Info',1),(13,11,14,2,'movie_prodTime','Movie_Info','Movie_Info',1),(14,11,15,2,'movie_prodCompany','Movie_Info','Movie_Info',1),(15,11,16,2,'movie_director','Movie_Info','Movie_Info',1),(16,11,17,2,'movie_screenwriter','Movie_Info','Movie_Info',1),(17,11,18,2,'movie_genre','Movie_Info','Movie_Info',1),(18,11,19,2,'movie_star','Movie_Info','Movie_Info',1),(19,11,20,2,'movie_length','Movie_Info','Movie_Info',1),(20,11,21,2,'movie_rekeaseTime','Movie_Info','Movie_Info',1),(21,11,22,2,'movie_language','Movie_Info','Movie_Info',1),(22,11,23,2,'movie_achiem','Movie_Info','Movie_Info',1);
/*!40000 ALTER TABLE `link` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-09 20:51:52

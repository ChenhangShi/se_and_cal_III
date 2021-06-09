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
-- Table structure for table `entity`
--

DROP TABLE IF EXISTS `entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `graphId` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `shape` varchar(255) NOT NULL,
  `description` longtext,
  `x` varchar(255) NOT NULL,
  `y` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`graphId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entity`
--

LOCK TABLES `entity` WRITE;
/*!40000 ALTER TABLE `entity` DISABLE KEYS */;
INSERT INTO `entity` VALUES (1,2,'actor_chName','Actor','rectangle','张家辉','60.51391326061193','74.4627272212206'),(2,2,'actor_bio','Actor_Bio','circle','张家辉（修改－），祖籍广东番禺，香港著名艺人。在超过80套电影及电视剧集的演出中，无论是硬汉、好男人、奸角或是幽默角色，都是张家辉的拿手好戏。2003年12月8日与关咏荷在澳大利亚结婚。2004年出演杜琪峰导演的《大事件》。2009年凭借《证人》获第46届台湾电影金马奖最佳男主角奖。2012年，在《大追捕》中饰“王远阳”，获得第49届金马奖最佳男主角提名、第32届香港电影金像奖最佳男主角提名。2013年，出演《激战》获第33届香港电影金像奖最佳男主角、第16届上海国际电影节最佳男演员奖、第10届华鼎奖中国最佳电影男演员奖。2016年凭影片《陀地驱魔人》获第三十五届香港电影金像奖最佳男主角、新晋导演双项提名。编辑摘要','50.28403756031011','81.19375822718928'),(3,2,'actor_foreName','Actor_ForeName','circle','Nicky 、Nick Cheung (Cheung Ka Fai, Nick)','66.45420390429295','82.7149345473483'),(4,2,'actor_nationality','Actor_Nationality','circle','中国','65.39412198190185','94.7291289606254'),(5,2,'actor_constellation','Actor_Constellation','circle','射手座','96.46818238159241','16.343993936195155'),(6,2,'actor_birthPlace','Actor_Birthplace','circle','中国香港','96.99051874970307','67.15069682826618'),(7,2,'actor_birthDay','Actor_BirthDay','circle','1967年12月2日','99.79831127810141','11.269278834965956'),(8,2,'actor_repWorks','Actor_RepWorks','circle','扫毒激战 线人 大追捕 财神客栈 证人 赌侠1999','41.31488191841246','96.10901034383123'),(9,2,'actor_achiem','Actor_Achiem','circle','第33届香港电影金像奖最佳男主角 第28届香港电影金像奖最佳男主角 第46届台湾电影金马奖最佳男主角','69.3820410809388','35.51805483460353'),(10,2,'actor_brokerage','Actor_Brokerage','circle','亚视和无线电视','83.03942768154454','41.0481234041543'),(11,2,'movie_chName','Movie','triangle','大追捕','166.2051012142079','182.35540013645027'),(12,2,'movie_bio','Movie_Bio','circle','大追捕修改，周显扬导演的一部电影。《大追捕》讲述了一个警察与凶犯博命相争的故事，张家辉扮演的王远阳与相隔20余年的两桩命案有关，任达华出演的落魄警官则负责寻找证据将他缉拿归案。主讲了二十年前因奸杀少女依芸入狱，重犯王远阳（张家辉饰）在狱中历经凌辱，变成性格怪异、人见人惧的哑巴杀手。刚刚出狱的他，又卷入了一场惨绝人寰的命案之中：中英混血指挥家徐翰林被发现抛尸海边荒野，容貌尽毁遍体鳞伤，而徐翰林正是少女依芸的父亲。外号怒汉的警探林正忠（任达华饰）一心查出真相，出狱后一直跟踪徐翰林幼女徐雪（文咏珊饰）的王远阳，自然成为最大的嫌疑人，一场全港大追捕全面展开，然而，真相才刚刚开始被揭开。编辑摘要','33.37709631547501','161.0517852681705'),(13,2,'movie_foreName','Movie_ForeName','circle','Night Fall','162.33864950461333','4.828923048917111'),(14,2,'movie_prodTime','Movie_ProdTime','circle','None','70.73708569551391','91.45373656700806'),(15,2,'movie_prodCompany','Movie_ProdCompany','circle','银都机构有限公司、万诱引力电影公司','193.39416616520305','154.12358666819216'),(16,2,'movie_director','Movie_Director','circle','周显扬','164.87686698695762','183.87674820619563'),(17,2,'movie_screenwriter','Movie_ScreenWriter','circle','周显扬，杜致朗','116.42337555839453','107.19547061245136'),(18,2,'movie_genre','Movie_Genre','circle','剧情 动作 惊悚','129.95491503392535','116.27864168016448'),(19,2,'movie_star','Movie_Star','circle','张家辉，文咏珊','25.193619370832042','45.54439519538312'),(20,2,'movie_length','Movie_Length','circle','None','18.504891051749194','112.7104062229071'),(21,2,'movie_rekeaseTime','Movie_RekeaseTime','circle','2012年3月15日','149.8106304076489','87.4228586761614'),(22,2,'movie_language','Movie_Language','circle','None','195.7221047889166','54.12324344615689'),(23,2,'movie_achiem','Movie_Achiem','circle','None','194.98897797187985','116.03665013657971');
/*!40000 ALTER TABLE `entity` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-09 20:51:49

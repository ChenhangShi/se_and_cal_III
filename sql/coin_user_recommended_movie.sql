set names utf8mb4;

DROP TABLE IF EXISTS `user_recommended_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_recommended_movie` (
                                  `userId` int NOT NULL,
                                  `movieId` int NOT NULL ,
                                  `movieName` varchar(255) NOT NULL,
                                  PRIMARY KEY (`userId`,`movieId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

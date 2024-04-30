-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (x86_64)
--
-- Host: localhost    Database: capstone
-- ------------------------------------------------------
-- Server version	8.2.0

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
-- Table structure for table `Schedule`
--

DROP TABLE IF EXISTS `Schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Schedule` (
  `service_id` int DEFAULT NULL,
  `day_of_week` varchar(10) DEFAULT NULL,
  `open_time` time DEFAULT NULL,
  `close_time` time DEFAULT NULL,
  KEY `service_id` (`service_id`),
  CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`service_id`) REFERENCES `Services` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Schedule`
--

LOCK TABLES `Schedule` WRITE;
/*!40000 ALTER TABLE `Schedule` DISABLE KEYS */;
INSERT INTO `Schedule` VALUES (5,'Monday','11:00:00','13:00:00'),(5,'Tuesday','11:00:00','13:00:00'),(5,'Thursday','11:00:00','13:00:00'),(5,'Friday','11:00:00','13:00:00'),(7,'Monday','08:00:00','12:00:00'),(7,'Tuesday','08:00:00','12:00:00'),(7,'Wednesday','08:00:00','11:00:00'),(7,'Thursday','08:00:00','12:00:00'),(7,'Friday','08:00:00','12:00:00'),(8,'Monday','09:00:00','12:00:00'),(8,'Tuesday','09:00:00','12:00:00'),(8,'Wednesday','09:00:00','12:00:00'),(8,'Thursday','09:00:00','12:00:00'),(8,'Friday','09:00:00','12:00:00'),(9,'Monday','08:00:00','17:00:00'),(9,'Tuesday','08:00:00','17:00:00'),(9,'Wednesday','08:00:00','17:00:00'),(9,'Thursday','08:00:00','17:00:00'),(9,'Friday','08:00:00','17:00:00'),(10,'Monday','09:00:00','17:00:00'),(10,'Tuesday','09:00:00','17:00:00'),(10,'Wednesday','09:00:00','17:00:00'),(10,'Thursday','09:00:00','17:00:00'),(10,'Friday','09:00:00','17:00:00'),(11,'Monday','09:00:00','17:00:00'),(11,'Tuesday','09:00:00','17:00:00'),(11,'Wednesday','09:00:00','17:00:00'),(11,'Thursday','09:00:00','17:00:00'),(11,'Friday','09:00:00','17:00:00');
/*!40000 ALTER TABLE `Schedule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-20  5:08:43

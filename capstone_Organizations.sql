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
-- Table structure for table `Organizations`
--

DROP TABLE IF EXISTS `Organizations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Organizations` (
  `org_id` int NOT NULL AUTO_INCREMENT,
  `org_name` varchar(50) DEFAULT NULL,
  `description` varchar(2100) DEFAULT NULL,
  `phone_number` varchar(500) DEFAULT NULL,
  `website` varchar(2100) DEFAULT NULL,
  `email` varchar(500) DEFAULT NULL,
  `fax` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `longitude` decimal(10,0) DEFAULT NULL,
  `latitude` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Organizations`
--

LOCK TABLES `Organizations` WRITE;
/*!40000 ALTER TABLE `Organizations` DISABLE KEYS */;
INSERT INTO `Organizations` VALUES (1,'12Baskets','Our mission is to build community and promote economic justice.\nDismantling stereotypes, reducing fear, and empowering hope through telling stories across socio-economic difference.\nWe envision a beloved community that celebrates mutuality, abundance, and justice.','(828)-216-2331','https://www.ashevillepovertyinitiative.org/12baskets','contact@ashevillepovertyinitiative.org',NULL,'610 Haywood Rd, West Asheville',NULL,NULL),(2,'Homeward Bound','Homeward Bound is an organization dedicated to preventing and ending homelessness in Asheville through permanent housing and support.','(828)-258-1695','https://homewardboundwnc.org/about/','ED@HomewardBoundWNC.org',NULL,'P.O. Box 1166, Asheville, NC. 28802',NULL,NULL),(3,'Literacy Together','Literacy together transforms lives through the power of literacy by offering tutoring for kids and adults in basic literacy as well as offering ESOL tutoring','(828)-254-3442','https://lit-together.org','info@lit-together.org',NULL,'31 College Place, Suite B-221, Asheville, NC 28801',NULL,NULL),(4,'Our VOICE','Our VOICE offers short term therapy to support survivors of sexual violence of all ages and identities. They take a person-centered approach following their values of believing all survivors, promoting dignity, supporting individual agency, shifting paradigms, and creating a culture of care and honesty.','(828)-252-0562 for office, (828)-255-7576 for crisis line','https://www.ourvoicenc.org/','https://www.ourvoicenc.org/contact/ (no listed email)',NULL,'35 Woodfin St, Asheville, NC 28801',NULL,NULL),(5,'Eleanor Health','Eleanor Health is an organization dedicated to whole-person care that helps anyone concerned about their mental health or affected by addiction to live an amazing life. They are passionate about transforming the quality and accessibility of addiction and mental health services, and they offer in-person and virtual care.','(781)-487-1070','https://www.eleanorhealth.com/north-carolina/asheville#','gethelp@eleanorhealth.com',NULL,'39 McDowell St, Asheville, NC 28801',NULL,NULL);
/*!40000 ALTER TABLE `Organizations` ENABLE KEYS */;
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

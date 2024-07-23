-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 172.16.0.104    Database: academic_management
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
-- Table structure for table `lecture`
--

DROP TABLE IF EXISTS `lecture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture` (
  `no` int NOT NULL DEFAULT '1',
  `code` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `grade` int DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `credit` int DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `professor` varchar(45) DEFAULT NULL,
  `week` varchar(3) DEFAULT NULL,
  `star_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `room` varchar(20) DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `report` text,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='강의 정보 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture`
--

LOCK TABLES `lecture` WRITE;
/*!40000 ALTER TABLE `lecture` DISABLE KEYS */;
INSERT INTO `lecture` VALUES (1,1001,'데이터통신',2,1,3,'전공','차무식','월요일','09:00:00','11:00:00','101',20,'안녕하세요 데이터통신수업입니다'),(2,1002,'컴퓨터프로그래밍',1,2,3,'전공','강인구','화요일','15:00:00','16:30:00','101',20,'프로그래밍수업입니다'),(3,1003,'컴퓨터의 개념 및 실습',1,1,2,'교양','전요환','수요일','16:00:00','18:00:00','101',30,'컴퓨터에 대해 알아보아요'),(4,1004,'반도체 공학',2,1,3,'전공','최창호','목요일','13:00:00','15:00:00','102',20,'반도체공학 어렵네요'),(5,1005,'공학설계 입문',3,1,3,'전공','변기태','금요일','09:00:00','10:00:00','102',20,'공학설계 어렵지 않습니다'),(6,1006,'융합전자연구',4,2,3,'전공','박응수','월요일','09:00:00','12:00:00','102',30,'융합전자에 대해 연구해봅시다'),(7,1007,'반응공학',1,2,3,'전공','오승훈','수요일','09:00:00','11:00:00','103',20,'반응공학수업입니다'),(8,1008,'물리화학',1,1,3,'전공','양정팔','목요일','09:00:00','10:30:00','103',30,'물리화학수업입니다'),(9,1009,'화공 유체역학',3,2,3,'전공','최유진','화요일','13:30:00','15:30:00','103',20,'화공 유체역학 수업입니다'),(10,1010,'동아시아사',1,1,3,'전공','고애신','월요일','13:00:00','14:00:00','201',20,'동아시아수업 재밌겠네요'),(11,1011,'한국사입문',1,1,3,'전공','구동매','수요일','10:30:00','12:00:00','201',30,'한국인이면 한국사를 배워야지'),(12,1012,'한국 근대사',2,2,3,'전공','김희성','금요일','10:00:00','12:00:00','201',30,'한국근대사에 대해 알아봅시다'),(13,1013,'고전문학연습',1,1,3,'전공','임관수','목요일','10:00:00','11:00:00','202',20,'고전문학 연습합시다'),(14,1014,'국어정서법',1,1,3,'전공','김판서','수요일','09:30:00','10:30:00','202',20,'국어정서 수업입니다'),(15,1015,'국문학개론',1,2,3,'전공','이완익','월요일','10:00:00','11:00:00','202',20,'국문학개론 재밌습니다'),(16,1016,'중세영문학',3,1,3,'전공','송우석','화요일','13:00:00','14:00:00','203',30,'중세영문학 수업입니다'),(17,1017,'영어발달사',1,2,3,'전공','서도철','금요일','10:30:00','12:30:00','203',20,'영어가 어떻게 발달하는지 알아봅시다'),(18,1018,'영어학입문',1,1,2,'교양','조태오','목요일','15:00:00','16:00:00','203',20,'영문학에 대해 입문해봅시다'),(19,1019,'외교정책론',4,1,3,'전공','최상무','월요일','16:00:00','17:30:00','301',20,'외교정책론입니다'),(20,1020,'국제정치경제론',1,1,3,'전공','정청','수요일','10:00:00','11:00:00','301',30,'국제정치를 배워봅시다'),(21,1021,'한국정치론',4,2,3,'전공','이중구','화요일','09:00:00','10:00:00','301',20,'한국정치부터 알아야죠'),(22,1022,'사회복지학개론',1,1,3,'전공','변재욱','금요일','09:30:00','10:30:00','302',30,'사회복지학개론수업입니다'),(23,1023,'사회복지행정론',4,2,3,'전공','한치원','목요일','16:00:00','18:00:00','302',20,'사회복지 행정론에대해 알아봅시다'),(24,1024,'현대사회심리',3,1,3,'전공','진도준','수요일','15:00:00','16:00:00','302',20,'현대 사회의 심리가 뭘까요'),(25,1025,'방송의 이해',1,2,3,'전공','진양철','화요일','10:00:00','11:30:00','303',20,'방송을 이해해봅시다'),(26,1026,'한국언론사',1,1,3,'전공','성덕선','금요일','13:00:00','15:00:00','303',20,'한국언론사 수업입니다'),(27,1027,'광고의 이해',2,2,3,'전공','성선우','월요일','17:00:00','18:00:00','303',20,'광고를 이해해봅시다');
/*!40000 ALTER TABLE `lecture` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-23 19:35:06

-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: erp
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `advice`
--
drop database if exists erp;
create database erp;
use erp;
DROP TABLE IF EXISTS `advice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advice` (
  `star` int(11) NOT NULL,
  `time` mediumtext NOT NULL,
  `adviceIndex` int(11) NOT NULL,
  `comment` varchar(1000) NOT NULL,
  `picture` varchar(1000) NOT NULL,
  `task_id` bigint(20) NOT NULL,
  PRIMARY KEY (`adviceIndex`,`task_id`),
  KEY `task_id` (`task_id`),
  CONSTRAINT `advice_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advice`
--

LOCK TABLES `advice` WRITE;
/*!40000 ALTER TABLE `advice` DISABLE KEYS */;
/*!40000 ALTER TABLE `advice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advice_2`
--

DROP TABLE IF EXISTS `advice_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advice_2` (
  `time` mediumtext NOT NULL,
  `adviceIndex` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `comment` varchar(1000) NOT NULL,
  `task_id` bigint(20) NOT NULL,
  PRIMARY KEY (`adviceIndex`,`task_id`),
  KEY `task_id` (`task_id`),
  CONSTRAINT `advice_2_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advice_2`
--

LOCK TABLES `advice_2` WRITE;
/*!40000 ALTER TABLE `advice_2` DISABLE KEYS */;
/*!40000 ALTER TABLE `advice_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `depart`
--

DROP TABLE IF EXISTS `depart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `depart` (
  `department_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(100) NOT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `depart`
--

LOCK TABLES `depart` WRITE;
/*!40000 ALTER TABLE `depart` DISABLE KEYS */;
INSERT INTO `depart` VALUES (1,'部门A'),(2,'部门B'),(3,'部门C'),(4,'部门D'),(5,'部门A_1'),(6,'bumnenA');
/*!40000 ALTER TABLE `depart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `depart_departclass`
--

DROP TABLE IF EXISTS `depart_departclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `depart_departclass` (
  `department_id` bigint(20) NOT NULL,
  `departClass_id` bigint(20) NOT NULL,
  KEY `department_id` (`department_id`),
  KEY `departClass_id` (`departClass_id`),
  CONSTRAINT `depart_departclass_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `depart` (`department_id`) ON DELETE CASCADE,
  CONSTRAINT `depart_departclass_ibfk_2` FOREIGN KEY (`departClass_id`) REFERENCES `departclass` (`departClass_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `depart_departclass`
--

LOCK TABLES `depart_departclass` WRITE;
/*!40000 ALTER TABLE `depart_departclass` DISABLE KEYS */;
INSERT INTO `depart_departclass` VALUES (1,1),(2,2),(3,3),(4,1),(5,1),(5,2),(5,3),(5,4),(6,1),(6,2),(6,3);
/*!40000 ALTER TABLE `depart_departclass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departclass`
--

DROP TABLE IF EXISTS `departclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departclass` (
  `departClass_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `departClass_name` varchar(100) NOT NULL,
  PRIMARY KEY (`departClass_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departclass`
--

LOCK TABLES `departclass` WRITE;
/*!40000 ALTER TABLE `departclass` DISABLE KEYS */;
INSERT INTO `departclass` VALUES (1,'X类部门'),(2,'Y类部门'),(3,'Z类部门'),(4,'阿达');
/*!40000 ALTER TABLE `departclass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `time` mediumtext NOT NULL,
  `reportIndex` int(11) NOT NULL,
  `comment` varchar(1000) NOT NULL,
  `picture` varchar(1000) NOT NULL,
  `task_id` bigint(20) NOT NULL,
  PRIMARY KEY (`reportIndex`,`task_id`),
  KEY `task_id` (`task_id`),
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stuff_0`
--

DROP TABLE IF EXISTS `stuff_0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stuff_0` (
  `account` varchar(100) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `telNum` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stuff_0`
--

LOCK TABLES `stuff_0` WRITE;
/*!40000 ALTER TABLE `stuff_0` DISABLE KEYS */;
INSERT INTO `stuff_0` VALUES ('gk1','123','','');
/*!40000 ALTER TABLE `stuff_0` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stuff_1`
--

DROP TABLE IF EXISTS `stuff_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stuff_1` (
  `account` varchar(100) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `telNum` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stuff_1`
--

LOCK TABLES `stuff_1` WRITE;
/*!40000 ALTER TABLE `stuff_1` DISABLE KEYS */;
INSERT INTO `stuff_1` VALUES ('gk2','123','',''),('gk2_1','123','1212','1');
/*!40000 ALTER TABLE `stuff_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stuff_1_depart`
--

DROP TABLE IF EXISTS `stuff_1_depart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stuff_1_depart` (
  `account` varchar(100) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  KEY `account` (`account`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `stuff_1_depart_ibfk_1` FOREIGN KEY (`account`) REFERENCES `stuff_1` (`account`) ON DELETE CASCADE,
  CONSTRAINT `stuff_1_depart_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `depart` (`department_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stuff_1_depart`
--

LOCK TABLES `stuff_1_depart` WRITE;
/*!40000 ALTER TABLE `stuff_1_depart` DISABLE KEYS */;
INSERT INTO `stuff_1_depart` VALUES ('gk2',4),('gk2',1),('gk2',2),('gk2_1',1),('gk2_1',2),('gk2_1',3),('gk2_1',4);
/*!40000 ALTER TABLE `stuff_1_depart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stuff_2`
--

DROP TABLE IF EXISTS `stuff_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stuff_2` (
  `account` varchar(100) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `type` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `telNum` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stuff_2`
--

LOCK TABLES `stuff_2` WRITE;
/*!40000 ALTER TABLE `stuff_2` DISABLE KEYS */;
INSERT INTO `stuff_2` VALUES ('gk1sada','123',1,'sad','1888888888'),('gk5','123',0,'李辉',''),('gk5_1','123',0,'lihui','ceshi'),('gk6','123',0,'',''),('gk7','123',0,'',''),('gk8','123',1,'',''),('gk9','123',0,'','');
/*!40000 ALTER TABLE `stuff_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stuff_depart`
--

DROP TABLE IF EXISTS `stuff_depart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stuff_depart` (
  `account` varchar(100) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  KEY `account` (`account`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `stuff_depart_ibfk_1` FOREIGN KEY (`account`) REFERENCES `stuff_2` (`account`) ON DELETE CASCADE,
  CONSTRAINT `stuff_depart_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `depart` (`department_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stuff_depart`
--

LOCK TABLES `stuff_depart` WRITE;
/*!40000 ALTER TABLE `stuff_depart` DISABLE KEYS */;
INSERT INTO `stuff_depart` VALUES ('gk5',4),('gk6',2),('gk7',1),('gk9',3),('gk8',1),('gk8',2),('gk8',4),('gk1sada',2),('gk1sada',3),('gk1sada',4),('gk5_1',1),('gk5_1',2),('gk5_1',3),('gk5_1',4);
/*!40000 ALTER TABLE `stuff_depart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `task_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(100) NOT NULL,
  `startTime` mediumtext NOT NULL,
  `endTime` mediumtext NOT NULL,
  `updateEndTime` mediumtext NOT NULL,
  `chairMan` varchar(40) NOT NULL,
  `type` int(11) NOT NULL,
  `place` varchar(40) DEFAULT NULL,
  `financing` bigint(20) NOT NULL,
  `goal` varchar(300) NOT NULL,
  `report_type` int(11) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  `picture` varchar(1000) NOT NULL,
  PRIMARY KEY (`task_id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `depart` (`department_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-14 22:05:35

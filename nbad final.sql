-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: form
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (701018499);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admindetails`
--

DROP TABLE IF EXISTS `admindetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admindetails` (
  `id` int(30) NOT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admindetails`
--

LOCK TABLES `admindetails` WRITE;
/*!40000 ALTER TABLE `admindetails` DISABLE KEYS */;
INSERT INTO `admindetails` VALUES (701018499,'ahmed','dewan','ahne@gmail.com','e1adc3949ba59abbe56e057f2f883e');
/*!40000 ALTER TABLE `admindetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `building`
--

DROP TABLE IF EXISTS `building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `building` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `buildingname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building`
--

LOCK TABLES `building` WRITE;
/*!40000 ALTER TABLE `building` DISABLE KEYS */;
INSERT INTO `building` VALUES (1,'Woodward hall'),(2,'Bio Informatics'),(3,'Smith'),(4,'CHHS');
/*!40000 ALTER TABLE `building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `course_id` int(30) NOT NULL AUTO_INCREMENT,
  `course_number` int(50) DEFAULT NULL,
  `course_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (2,6114,'Algorithms and Data Structures'),(3,6162,'Knowledge Discovery in Databases'),(4,5180,'Mobile Applicaion Development'),(5,5160,'Survey of Programming Languages'),(6,6120,'System Integration'),(7,1241,'Calculus1'),(8,1242,'Calculus2'),(9,1105,'Finite Math'),(10,1100,'Algebra'),(11,3122,'Structural Analysis'),(12,2104,'Surveying and Site Design'),(13,3111,'Hydraulics and Hydrology'),(14,1241,'Transportation Engineering'),(16,1561,'Signals and Systems'),(17,1754,'Logic Systems'),(18,1855,'Operating Systems'),(19,6196,'Cloud Computing'),(20,6109,'Visual Analytics'),(25,5886,'Game Design');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dayschedule`
--

DROP TABLE IF EXISTS `dayschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dayschedule` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `dayvalue` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dayschedule`
--

LOCK TABLES `dayschedule` WRITE;
/*!40000 ALTER TABLE `dayschedule` DISABLE KEYS */;
INSERT INTO `dayschedule` VALUES (1,'Monday'),(2,'Tuesday'),(3,'Wednesday'),(4,'Thursday'),(5,'Friday');
/*!40000 ALTER TABLE `dayschedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `dept_id` varchar(50) DEFAULT NULL,
  `dept_name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('ITCS','Computer Science'),('MTM','Mathematics'),('CEST','Civil Engineering'),('ECET','Electrical Engineering');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departmentcourse`
--

DROP TABLE IF EXISTS `departmentcourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departmentcourse` (
  `course` int(10) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departmentcourse`
--

LOCK TABLES `departmentcourse` WRITE;
/*!40000 ALTER TABLE `departmentcourse` DISABLE KEYS */;
INSERT INTO `departmentcourse` VALUES (1,'ITCS'),(2,'ITCS'),(3,'ITCS'),(4,'ITCS'),(5,'ITCS'),(6,'ITCS'),(7,'MTM'),(8,'MTM'),(9,'MTM'),(10,'MTM'),(11,'CEST'),(12,'CEST'),(13,'CEST'),(14,'CEST'),(16,'ECET'),(17,'ECET'),(18,'ECET'),(19,'ITCS'),(20,'ITCS'),(21,'MTM'),(22,'ECET'),(0,'base'),(24,'ECET'),(25,'ITCS');
/*!40000 ALTER TABLE `departmentcourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture_type`
--

DROP TABLE IF EXISTS `lecture_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lecture_type` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `lecturetype` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture_type`
--

LOCK TABLES `lecture_type` WRITE;
/*!40000 ALTER TABLE `lecture_type` DISABLE KEYS */;
INSERT INTO `lecture_type` VALUES (1,'Online'),(2,'lecture');
/*!40000 ALTER TABLE `lecture_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `user` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `fullName` varchar(100) NOT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('Admin','passw0rd','Jon Smith'),('Ted','hello123','<script> alert(\"malicious data in database\");</script>');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `roomnum` int(10) DEFAULT NULL,
  `capacity` int(10) DEFAULT NULL,
  `building` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,130,60,'1'),(2,135,45,'1'),(3,225,50,'2'),(4,245,60,'2'),(5,345,60,'3'),(6,355,50,'3'),(7,455,50,'4'),(8,465,60,'4');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section` (
  `section_id` int(10) NOT NULL AUTO_INCREMENT,
  `section_num` int(10) DEFAULT NULL,
  `section_limit` int(5) DEFAULT NULL,
  `year` int(5) DEFAULT NULL,
  `course` int(10) DEFAULT NULL,
  `semester` varchar(10) DEFAULT NULL,
  `room` int(10) DEFAULT NULL,
  `lecture_type` int(3) DEFAULT NULL,
  PRIMARY KEY (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (6,3545,50,2018,1,'S2018',1,2),(7,3555,40,2018,2,'S2018',2,2),(8,3565,60,2018,3,'S2018',1,1),(9,3575,30,2018,4,'S2018',2,2),(13,3900,40,2018,5,'S2018',2,1),(14,4220,30,2018,7,'S2018',3,1),(15,4250,50,2018,8,'S2018',4,1),(16,4350,40,2018,9,'S2018',3,1),(18,5200,55,2018,11,'S2018',5,1),(19,5250,50,2018,12,'S2018',6,2),(20,5350,45,2018,13,'S2018',5,2),(21,5400,40,2018,14,'S2018',6,2),(22,6200,55,2018,16,'S2018',7,2),(23,6250,50,2018,17,'S2018',8,2),(25,8462,40,2018,13,'FS2018',4,2),(26,4120,40,2018,2,'FS2018',1,2),(27,8240,50,2018,20,'FS2018',3,2),(28,8585,40,2018,19,'FS2018',7,2),(29,6550,40,2018,2,'FS2018',3,2),(30,6580,40,2018,6,'FS2018',2,2),(31,6510,40,2018,3,'FS2018',1,2),(32,5550,50,2018,4,'SS2018',4,2),(33,6580,30,2018,5,'SS2018',5,2),(34,6510,40,2018,2,'SS2018',3,2),(35,2550,50,2018,7,'SS2018',4,2),(36,2580,30,2018,9,'SS2018',5,2),(37,3550,50,2018,8,'FS2018',4,2),(38,3580,30,2018,10,'FS2018',5,2),(39,4550,50,2018,7,'S2018',4,2),(40,5580,30,2018,9,'SS2018',5,2),(41,4650,50,2018,8,'S2018',4,2),(42,4680,30,2018,10,'S2018',5,2);
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sectiondaytime`
--

DROP TABLE IF EXISTS `sectiondaytime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sectiondaytime` (
  `section` int(10) DEFAULT NULL,
  `dayid` int(10) DEFAULT NULL,
  `timeid` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sectiondaytime`
--

LOCK TABLES `sectiondaytime` WRITE;
/*!40000 ALTER TABLE `sectiondaytime` DISABLE KEYS */;
INSERT INTO `sectiondaytime` VALUES (6,1,2),(7,2,3),(8,3,1),(9,4,2),(12,4,1),(13,3,3),(14,2,2),(16,2,3),(15,3,1),(17,4,1),(18,4,1),(19,4,2),(20,3,1),(21,2,3),(22,3,1),(23,3,2),(24,5,1),(25,3,2),(26,3,1),(27,4,2),(28,5,1),(29,1,2),(30,1,3),(31,2,2),(32,2,2),(33,1,3),(34,4,2),(35,3,2),(36,1,3),(37,4,2),(38,1,3),(39,4,2),(40,1,3),(41,1,2),(42,2,3);
/*!40000 ALTER TABLE `sectiondaytime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semester` (
  `id` varchar(30) DEFAULT NULL,
  `semsester` varchar(20) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES ('FS2018','First Summer 2018','2018-05-20','2018-06-25'),('SS2018','Second Summer 2018','2018-07-10','2018-08-15'),('S2018','Fall 2018','2018-08-20','2018-12-12');
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(30) NOT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (80101799,'Nara','Hari','narahari.hara@gmail.com','e1adc3949ba59abbe56e057f2f883e'),(801018499,'Narahari','Battala','nbatta@uncc.edu','e1adc3949ba59abbe56e057f2f883e');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentdetails`
--

DROP TABLE IF EXISTS `studentdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentdetails` (
  `id` int(20) NOT NULL,
  `department` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentdetails`
--

LOCK TABLES `studentdetails` WRITE;
/*!40000 ALTER TABLE `studentdetails` DISABLE KEYS */;
INSERT INTO `studentdetails` VALUES (80101799,'ITCS'),(801018499,'ITCS');
/*!40000 ALTER TABLE `studentdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentsection`
--

DROP TABLE IF EXISTS `studentsection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentsection` (
  `student` int(10) NOT NULL,
  `section` int(10) NOT NULL,
  PRIMARY KEY (`student`,`section`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentsection`
--

LOCK TABLES `studentsection` WRITE;
/*!40000 ALTER TABLE `studentsection` DISABLE KEYS */;
/*!40000 ALTER TABLE `studentsection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `teacher_id` int(10) NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'Harini','Ramaprasad'),(2,'Srinivas','Akella'),(3,'Minwoo','Jake'),(4,'Ali','Sever'),(5,'Wilson','Dale'),(6,'Shakib','Mohammed'),(7,'Ahmed','Dewan'),(8,'Pamela','Thompson'),(9,'Allison','Walling'),(10,'Andrew','Bhat'),(11,'Madelyn','Williams'),(12,'Anthony','Fernandes'),(13,'Erika','Weber'),(14,'Mie','Sun'),(15,'Marlin','Kane'),(16,'James','Milind'),(17,'Shiva','Kumar'),(18,'Marcell','Carnifal'),(19,'Arun','Ravindran');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachersection`
--

DROP TABLE IF EXISTS `teachersection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teachersection` (
  `teacher` int(10) NOT NULL,
  `section` int(10) NOT NULL,
  PRIMARY KEY (`teacher`,`section`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachersection`
--

LOCK TABLES `teachersection` WRITE;
/*!40000 ALTER TABLE `teachersection` DISABLE KEYS */;
INSERT INTO `teachersection` VALUES (1,6),(1,33),(2,7),(2,32),(3,25),(4,13),(4,29),(5,12),(5,34),(6,9),(6,26),(6,30),(7,31),(8,8),(9,14),(10,16),(11,15),(11,37),(12,17),(12,35),(12,39),(13,21),(13,36),(13,40),(14,20),(14,27),(14,38),(15,19),(15,41),(16,18),(16,28),(17,22),(17,42),(18,23),(19,24);
/*!40000 ALTER TABLE `teachersection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timeschedule`
--

DROP TABLE IF EXISTS `timeschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timeschedule` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `starttime` varchar(30) DEFAULT NULL,
  `endtime` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timeschedule`
--

LOCK TABLES `timeschedule` WRITE;
/*!40000 ALTER TABLE `timeschedule` DISABLE KEYS */;
INSERT INTO `timeschedule` VALUES (1,'2:30 p.m','5:00 p.m'),(2,'9:30 a.m','12:15 p.m'),(3,'5:30 p.m','8:45 p.m');
/*!40000 ALTER TABLE `timeschedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'form'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-29 12:49:58

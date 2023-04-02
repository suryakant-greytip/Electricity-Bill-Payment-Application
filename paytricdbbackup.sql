-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: paytric
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `adminUserName` varchar(10) NOT NULL,
  `adminEmail` varchar(30) DEFAULT NULL,
  `adminPassword` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`adminUserName`),
  UNIQUE KEY `adminEmail` (`adminEmail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES ('admin','admin@gmail.com','admin');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `consumerId` varchar(20) NOT NULL,
  `billId` varchar(20) NOT NULL,
  `previous_reading` double(8,2) NOT NULL,
  `current_reading` double(8,2) NOT NULL,
  `units_consumed` double(8,2) NOT NULL,
  `unit_rate` int DEFAULT '10',
  `total_amount` double(8,2) DEFAULT NULL,
  `tax` double(3,1) DEFAULT '2.5',
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `billing_date` date DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `is_paid` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `billId` (`billId`),
  KEY `fk_bill_consumer` (`consumerId`),
  CONSTRAINT `fk_bill_consumer` FOREIGN KEY (`consumerId`) REFERENCES `consumers` (`consumerId`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1000,'C100','B1000',50.00,90.00,40.00,10,410.00,2.5,'2022-01-01','2022-01-30','2022-01-31','2022-02-10',1),(1001,'C100','B1001',90.00,130.00,40.00,10,410.00,2.5,'2022-02-01','2022-03-01','2022-03-02','2022-03-12',1),(1002,'C101','B1002',0.00,34.00,34.00,10,348.50,2.5,'2023-01-01','2023-01-30','2023-04-02','2023-04-12',0),(1003,'C101','B1003',30.00,60.00,30.00,10,307.50,2.5,'2023-03-01','2023-04-01','2023-04-03','2023-04-13',0),(1004,'C100','B1004',600.00,650.00,50.00,10,512.50,2.5,'2023-01-01','2023-01-30','2023-04-03','2023-04-13',1);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_transaction_history`
--

DROP TABLE IF EXISTS `bill_transaction_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_transaction_history` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `consumerId` varchar(20) NOT NULL,
  `billId` varchar(20) NOT NULL,
  `amount_paid` double(8,2) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_method` varchar(20) DEFAULT NULL,
  `transactionId` varchar(20) NOT NULL,
  PRIMARY KEY (`transactionId`),
  KEY `consumerId` (`consumerId`),
  KEY `Id` (`Id`),
  CONSTRAINT `bill_transaction_history_ibfk_1` FOREIGN KEY (`consumerId`) REFERENCES `consumers` (`consumerId`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_transaction_history`
--

LOCK TABLES `bill_transaction_history` WRITE;
/*!40000 ALTER TABLE `bill_transaction_history` DISABLE KEYS */;
INSERT INTO `bill_transaction_history` VALUES (1001,'C100','B1000',410.00,'2023-04-01','BHIM UPI','T1000'),(1002,'C100','B1001',410.00,'2023-04-03','Debit Card','T1002'),(1003,'C100','B1004',512.50,'2023-04-03','Internet Banking','T1003');
/*!40000 ALTER TABLE `bill_transaction_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaints`
--

DROP TABLE IF EXISTS `complaints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaints` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `complaintId` varchar(10) NOT NULL,
  `consumerId` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `complaintType` varchar(50) NOT NULL,
  `complaintDescription` varchar(200) NOT NULL,
  `assignedTo` varchar(20) DEFAULT 'admin',
  `status` tinyint(1) DEFAULT '0',
  `resolvedDate` date DEFAULT NULL,
  `resolvedTime` time DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `complaintId` (`complaintId`),
  KEY `fk_comp_cons` (`consumerId`),
  CONSTRAINT `fk_comp_cons` FOREIGN KEY (`consumerId`) REFERENCES `consumers` (`consumerId`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaints`
--

LOCK TABLES `complaints` WRITE;
/*!40000 ALTER TABLE `complaints` DISABLE KEYS */;
INSERT INTO `complaints` VALUES (1000,'COMP1000','C100','2023-04-02','04:35:46','Meter issues','Meter is not working properly it is running so fast.','admin',0,NULL,NULL),(1001,'COMP1001','C100','2023-04-02','05:02:45','Power outages or interruptions','There is no consistency in power supply, Power cutoff is frequently seen.','admin',1,'2023-04-02','05:59:17'),(1002,'COMP1002','C103','2023-04-03','00:31:11','Voltage or frequency fluctuations','Voltage is fluctuating frequently and there is inconsistency in Electricity.','admin',0,NULL,NULL),(1003,'COMP1003','C100','2023-04-03','03:09:19','Electrical safety concerns','Electrical Safety is weak in our locality there are some bare wires','admin',0,NULL,NULL);
/*!40000 ALTER TABLE `complaints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumers`
--

DROP TABLE IF EXISTS `consumers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumers` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `consumerId` varchar(20) NOT NULL,
  `consumerUserName` varchar(25) NOT NULL,
  `consumerEmail` varchar(30) DEFAULT NULL,
  `consumerPassword` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `securityQuestion` varchar(50) NOT NULL,
  `securityAnswer` varchar(20) NOT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `firstName` varchar(25) NOT NULL,
  `lastName` varchar(25) NOT NULL,
  `address` varchar(15) DEFAULT NULL,
  `MobileNo` char(10) DEFAULT NULL,
  PRIMARY KEY (`consumerId`),
  UNIQUE KEY `consumerUserName` (`consumerUserName`),
  UNIQUE KEY `consumerEmail` (`consumerEmail`),
  KEY `Id` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumers`
--

LOCK TABLES `consumers` WRITE;
/*!40000 ALTER TABLE `consumers` DISABLE KEYS */;
INSERT INTO `consumers` VALUES (100,'C100','Surya9011','singhsurya@gmail.com','Surya@555','what is your favourite game','cricket',0,'Suryakant','Singh','Pune(MH)','7744837409'),(101,'C101','Abhi9028','Abhi9028@gmail.com','Abhi@123','What is your High School name?','GEMHS',1,'Abhishek','Gupta','Pune','7890987659'),(102,'C102','Istiyak11','Istiyak11@gmail.com','Istiyak@123','What is your favourite Game','Cricket',1,'Istiyak','Shaikh','Pune','8987987987'),(103,'C103','Sunny6789','sunny6789@gmail.com','Sunny@6789','What is your favourite Game','Cricket',1,'Sunny','Jaiswal','Pune','8979879889');
/*!40000 ALTER TABLE `consumers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-03  3:14:11

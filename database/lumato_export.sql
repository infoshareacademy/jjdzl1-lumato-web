CREATE DATABASE  IF NOT EXISTS `lumato` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `lumato`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lumato
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cars` (
  `idcars` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(45) DEFAULT NULL,
  `brand` varchar(45) DEFAULT NULL,
  `productionyear` int(11) DEFAULT NULL,
  `iduser` int(11) NOT NULL,
  PRIMARY KEY (`idcars`),
  KEY `fk_cars_users_idx` (`iduser`),
  CONSTRAINT `fk_cars_users` FOREIGN KEY (`iduser`) REFERENCES `users` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,'Astra','Opel',2001,1),(2,'VW','Gulf',2003,2),(3,'Porsze','Kajen',2016,3),(4,'Skoda','Oktawia',2012,1),(5,'Audi','S7',2018,3),(6,'Hammer','H3',2008,3);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extracosts`
--

DROP TABLE IF EXISTS `extracosts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `extracosts` (
  `idextraCosts` int(11) NOT NULL AUTO_INCREMENT,
  `cost` decimal(7,2) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `idcars` int(11) NOT NULL,
  PRIMARY KEY (`idextraCosts`,`idcars`),
  KEY `fk_extraCosts_cars1_idx` (`idcars`),
  CONSTRAINT `fk_extraCosts_cars1` FOREIGN KEY (`idcars`) REFERENCES `cars` (`idcars`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extracosts`
--

LOCK TABLES `extracosts` WRITE;
/*!40000 ALTER TABLE `extracosts` DISABLE KEYS */;
/*!40000 ALTER TABLE `extracosts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuelcosts`
--

DROP TABLE IF EXISTS `fuelcosts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fuelcosts` (
  `idfuelcost` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `priceperliter` decimal(4,2) DEFAULT NULL,
  `amountoffuel` decimal(4,1) DEFAULT NULL,
  `currentmileage` int(11) DEFAULT NULL,
  `typeoffuel` varchar(45) DEFAULT NULL,
  `idcar` int(11) NOT NULL,
  PRIMARY KEY (`idfuelcost`),
  KEY `fk_fuelCosts_cars1_idx` (`idcar`),
  CONSTRAINT `fk_fuelCosts_cars1` FOREIGN KEY (`idcar`) REFERENCES `cars` (`idcars`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuelcosts`
--

LOCK TABLES `fuelcosts` WRITE;
/*!40000 ALTER TABLE `fuelcosts` DISABLE KEYS */;
INSERT INTO `fuelcosts` VALUES (1,'2012-01-22',4.88,15.0,10110,'Diesel',1),(2,'2012-01-01',4.52,50.0,10000,'Diesel',1),(3,'2012-01-10',4.25,25.0,120846,'Petrol',3),(4,'2012-01-25',4.88,17.0,10710,'Diesel',1),(5,'2012-01-30',5.02,22.6,1,'Petrol',2),(6,'2012-02-02',5.13,33.6,786,'Petrol',2);
/*!40000 ALTER TABLE `fuelcosts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'anna@wp.pl','password1','Anna','Kowalska'),(2,'joe@gmail.com','password2','Joe','Doe'),(3,'johns@pornhub.com','password3','John','Smith'),(4,'ak@gmail.com','password4','Bob','Tomcat');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-12 14:00:29

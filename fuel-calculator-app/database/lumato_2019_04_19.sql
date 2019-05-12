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
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `fuel_type` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `production_year` int(11) DEFAULT NULL,
  `reg_plate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (50,'CAR1','Petrol',3,'1',2016,'KR1111'),(52,'CAR2','Diesel',3,'2',2016,'KSU 33668'),(53,'CAR3','Diesel',3,'3',1998,'KSY 1236'),(54,'CAR4','Petrol',3,'4',1999,'TEST');
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extracost`
--

DROP TABLE IF EXISTS `extracost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `extracost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cost` decimal(7,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `cost_date` date DEFAULT NULL,
  `car_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`car_id`),
  KEY `fk_extracosts_cars1_idx` (`car_id`),
  KEY `users_id_idx` (`user_id`),
  CONSTRAINT `car_id` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extracost`
--

LOCK TABLES `extracost` WRITE;
/*!40000 ALTER TABLE `extracost` DISABLE KEYS */;
INSERT INTO `extracost` VALUES (8,10000.00,'Szpachlowanie','2013-03-03',50,3),(9,500.00,'Dupa','2013-03-03',50,3),(10,150.00,'Mycie Siusiaka','2013-03-03',52,3),(11,1125.00,'tjuning zawieszenia','2019-03-03',54,3);
/*!40000 ALTER TABLE `extracost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuelcost`
--

DROP TABLE IF EXISTS `fuelcost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fuelcost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount_of_fuel` double DEFAULT NULL,
  `current_mileage` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `type_of_fuel` varchar(45) DEFAULT NULL,
  `price_per_liter` double DEFAULT NULL,
  `car_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`car_id`,`user_id`),
  KEY `fk_fuelcosts_cars1_idx` (`car_id`,`user_id`),
  KEY `users_id_idx` (`user_id`),
  CONSTRAINT `cars_id` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`),
  CONSTRAINT `users_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuelcost`
--

LOCK TABLES `fuelcost` WRITE;
/*!40000 ALTER TABLE `fuelcost` DISABLE KEYS */;
INSERT INTO `fuelcost` VALUES (15,23,1000,'2013-03-03 00:01:44','Petrol',5.25,50,3),(16,55,10000000,'2016-05-14 00:02:03','Diesel',4.89,52,3),(17,36,50000,'2013-03-03 00:02:26','Diesel',4.99,52,3);
/*!40000 ALTER TABLE `fuelcost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `confirm_password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,NULL,'tomek.starzak@gmail.com','Tomek','','1000:12665a30ea22e71a55837a4ca10682f8:b981d70d7dcafcf2bc6bf7a78ffad6c5147ebc9d13db02a8c54f2228603b6551c24b3689b8e013b6d01387c40961e7a74c68113bb4a21e04779875a647fecd84'),(5,'1111','akaras.@o2.pl','Aneta','Karad','1000:41fe8389439d56720afa7c6eb997d07d:66377fa8f8d79adac84a7a4c15d2fc2b299f46461bcdfa74402d4e53ec86fcb71d2e81f7c080946efe0ab2b59636dfcc5eba5c50935ff240b0da1372de364508');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-19 14:02:28

CREATE DATABASE  IF NOT EXISTS `superherosightings`;
USE `superherosightings`;

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `latitude`
--

DROP TABLE IF EXISTS `latitude`;
CREATE TABLE `latitude` (
  `latitude_id` int NOT NULL,
  `degrees` int NOT NULL,
  `direction` varchar(5) DEFAULT NULL,
  `minutes` int NOT NULL,
  `seconds` double DEFAULT NULL,
  PRIMARY KEY (`latitude_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `location_id` int NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `latitude_id` int DEFAULT NULL,
  `longitude_id` int DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  KEY `FKeh1glf0c0i9bofyomxo246yhi` (`latitude_id`),
  KEY `FKd14mgww8o22lmw96rtdufs79u` (`longitude_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `longitude`
--

DROP TABLE IF EXISTS `longitude`;
CREATE TABLE `longitude` (
  `longitude_id` int NOT NULL,
  `degrees` int NOT NULL,
  `direction` varchar(5) DEFAULT NULL,
  `minutes` int NOT NULL,
  `seconds` double DEFAULT NULL,
  PRIMARY KEY (`longitude_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;

CREATE TABLE `organization` (
  `organization_id` int NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email_address` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`organization_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `superhero`
--

DROP TABLE IF EXISTS `superhero`;

CREATE TABLE `superhero` (
  `superhero_id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `superpower` varchar(255) DEFAULT NULL,
  `superpower_id` int DEFAULT NULL,
  PRIMARY KEY (`superhero_id`),
  KEY `FKatpgbrrq03ft4isijoqsn4un1` (`superpower_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `superhero_location`
--

DROP TABLE IF EXISTS `superhero_location`;
CREATE TABLE `superhero_location` (
  `location_id` int NOT NULL,
  `superhero_id` int NOT NULL,
  `sighting_date` date DEFAULT NULL,
  PRIMARY KEY (`location_id`,`superhero_id`),
  KEY `FKa1rot2amsi90tu4t0obtbta2v` (`superhero_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `superhero_organization`
--

DROP TABLE IF EXISTS `superhero_organization`;

CREATE TABLE `superhero_organization` (
  `organization_id` int NOT NULL,
  `superhero_id` int NOT NULL,
  PRIMARY KEY (`organization_id`,`superhero_id`),
  KEY `FKc4qccney1ahy8p05c9phfmfxa` (`superhero_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `superpower`
--

DROP TABLE IF EXISTS `superpower`;

CREATE TABLE `superpower` (
  `superpower_id` int NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`superpower_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE DATABASE  IF NOT EXISTS `eventos` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `eventos`;
-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: eventos
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `idevento` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `quantIngressos` int NOT NULL,
  `tipoEvento` varchar(45) NOT NULL,
  `precoIngresso` double NOT NULL,
  `horario` datetime NOT NULL,
  `local` int NOT NULL,
  PRIMARY KEY (`idevento`),
  UNIQUE KEY `idevento_UNIQUE` (`idevento`),
  KEY `idlocal_idx` (`local`),
  CONSTRAINT `fk_local` FOREIGN KEY (`local`) REFERENCES `local` (`idlocal`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (1,'Show Little Mix',500,'Show',800,'2021-06-30 20:00:00',1),(4,'POOA',636,'Curso',100,'2021-07-01 19:00:00',2),(6,'Exposicao de Artes',100,'Mostra Cultural',250,'2021-06-26 19:00:00',1),(7,'Minha mae e uma peca',636,'Teatro',0,'2021-07-03 20:00:00',2);
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingresso`
--

DROP TABLE IF EXISTS `ingresso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingresso` (
  `idingresso` int NOT NULL AUTO_INCREMENT,
  `tipoEntrada` varchar(45) NOT NULL,
  `numero` int NOT NULL,
  `evento` int NOT NULL,
  PRIMARY KEY (`idingresso`),
  UNIQUE KEY `idingresso_UNIQUE` (`idingresso`),
  KEY `idevento_idx` (`evento`),
  CONSTRAINT `fk_evento` FOREIGN KEY (`evento`) REFERENCES `evento` (`idevento`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingresso`
--

LOCK TABLES `ingresso` WRITE;
/*!40000 ALTER TABLE `ingresso` DISABLE KEYS */;
INSERT INTO `ingresso` VALUES (1,'inteira',3,1),(2,'inteira',13,1),(4,'inteira',4,1),(5,'inteira',11,1),(6,'inteira',10,1),(7,'inteira',8,1),(8,'inteira',1,1),(9,'inteira',7,1),(11,'inteira',5,1),(12,'inteira',12,1),(13,'inteira',2,1),(14,'inteira',6,1),(15,'inteira',9,1),(16,'doacao',585,7),(17,'doacao',584,7),(18,'doacao',578,7),(19,'doacao',582,7),(20,'doacao',583,7),(21,'doacao',586,7),(22,'doacao',579,7),(23,'doacao',577,7),(24,'doacao',581,7),(25,'doacao',580,7),(26,'meia',3,4),(27,'meia',2,4),(30,'meia',1,4),(31,'meia',513,4),(32,'meia',510,4),(33,'meia',514,4),(34,'meia',515,4),(35,'meia',511,4),(36,'meia',516,4),(37,'meia',512,4),(38,'meia',508,4),(39,'meia',509,4),(40,'meia',507,4),(42,'inteira',280,4),(43,'inteira',281,4),(64,'inteira',6,6),(65,'inteira',7,6),(66,'inteira',3,6),(67,'inteira',5,6),(68,'inteira',1,6),(69,'inteira',2,6),(70,'inteira',4,6);
/*!40000 ALTER TABLE `ingresso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `local`
--

DROP TABLE IF EXISTS `local`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `local` (
  `idlocal` int NOT NULL AUTO_INCREMENT,
  `capacidadeMax` int NOT NULL,
  `aluguel` double NOT NULL,
  `limpeza` double NOT NULL,
  `luz` double NOT NULL,
  `agua` double NOT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`idlocal`),
  UNIQUE KEY `idlocal_UNIQUE` (`idlocal`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `local`
--

LOCK TABLES `local` WRITE;
/*!40000 ALTER TABLE `local` DISABLE KEYS */;
INSERT INTO `local` VALUES (1,500,6000,1000,500,300,'Salão'),(2,636,8000,2000,500,300,'Anfiteatro');
/*!40000 ALTER TABLE `local` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `adm` enum('SIM','NÃO') NOT NULL DEFAULT 'NÃO',
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `idusuario_UNIQUE` (`idusuario`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','admin','SIM'),(2,'vendedor','123','NÃO');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-11 20:51:39

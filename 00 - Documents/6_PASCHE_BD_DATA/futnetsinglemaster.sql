-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jun 05, 2023 at 12:47 PM
-- Server version: 5.7.39
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `futnetsinglemaster`
--
CREATE DATABASE IF NOT EXISTS `futnetsinglemaster` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `futnetsinglemaster`;

-- --------------------------------------------------------

--
-- Table structure for table `equipe`
--

DROP TABLE IF EXISTS `equipe`;
CREATE TABLE `equipe` (
  `id_equipe` int(11) NOT NULL,
  `nom_equipe` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `equipe`
--

INSERT INTO `equipe` (`id_equipe`, `nom_equipe`) VALUES
(1, 'FC Barcelone'),
(2, 'Arsenal FC'),
(3, 'Manchester United'),
(4, 'Real Madrid'),
(5, 'Liverpool FC'),
(6, 'AC Milan'),
(7, 'Inter Milan'),
(8, 'Manchester City'),
(9, 'Paris-St-Germain'),
(10, 'Juventus'),
(11, 'Dortmund');

-- --------------------------------------------------------

--
-- Table structure for table `rencontre`
--

DROP TABLE IF EXISTS `rencontre`;
CREATE TABLE `rencontre` (
  `id_rencontre` int(11) NOT NULL,
  `valide` tinyint(4) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rencontre`
--

INSERT INTO `rencontre` (`id_rencontre`, `valide`, `date`) VALUES
(13, 1, '2023-06-05'),
(14, 0, '2023-06-20'),
(15, 1, '2023-06-01'),
(16, 1, '2023-04-13'),
(17, 1, '2023-06-18'),
(20, 1, '2023-06-06'),
(21, 0, '2023-06-13'),
(22, 1, '2023-03-15'),
(23, 1, '2023-06-24'),
(24, 1, '2023-06-10'),
(25, 0, '2023-06-08'),
(26, 0, '2023-06-15'),
(27, 0, '2023-06-07'),
(28, 1, '2023-06-23'),
(29, 1, '2023-07-02'),
(30, 1, '2023-05-30'),
(31, 1, '2023-03-03'),
(32, 0, '2023-06-13'),
(33, 0, '2023-06-14'),
(34, 0, '2023-06-30'),
(35, 0, '2023-06-12'),
(36, 1, '2023-06-04'),
(37, 0, '2023-03-16'),
(38, 0, '2023-05-31'),
(39, 0, '2023-06-07'),
(40, 0, '2023-07-02'),
(41, 0, '2023-06-07'),
(42, 0, '2023-06-03'),
(43, 0, '2023-02-16');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id_role` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  `niveau` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id_role`, `role`, `niveau`) VALUES
(1, 'ADMIN', 10),
(2, 'JOUEUR', 5);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE `utilisateur` (
  `id_utilisateur` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `mail` varchar(150) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `id_role` int(11) NOT NULL,
  `id_equipe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `nom`, `prenom`, `mail`, `username`, `password`, `id_role`, `id_equipe`) VALUES
(1, 'Pasche', 'Killian', 'killian.pasche7@gmail.com', 'PascheK26052023212304', '$2a$10$pJhmxf8fhjhjULg.zEdoteX2d27zmyK/RwENjAAe20EXaXHCfaV2e', 1, 2),
(28, 'Yakin', 'Murat', 'hedix75863@onlcool.com', 'YakinM02062023154526', '$2a$10$CKmbg2POKvS4N/UWcvi/QeOBFLWs4Zl6XJe1y8mnB363YvTcvn6vS', 2, 2),
(29, 'Deschamps', 'Didier', 'hedix75863@onlcool.com', 'DeschampsD02062023154544', '$2a$10$YhAeJxXmxp2iOAbVyFud6ekbfZ44kqtDWK3wNcS9dV9emyWP2He6O', 2, 10),
(30, 'Mbappé', 'Kylian', 'hedix75863@onlcool.com', 'MbappéK02062023154602', '$2a$10$sFRwNi1Oe6PSm41j5j5DE.GPH4xbCYteUHBiMHod7IanCj00t/jKu', 2, 9),
(31, 'Benzema', 'Karim', 'hedix75863@onlcool.com', 'BenzemaK02062023154619', '$2a$10$YZVIIqcVT..e7uqAPekcNuw70Hh8/ZuQMsLNCOMRCa75mFVaK6dsa', 2, 4),
(32, 'Messi', 'Lionel', 'hedix75863@onlcool.com', 'MessiL02062023154638', '$2a$10$vzClv55nFdMxr/9sXqOUYOgJjSjweLqQ/x9drYYlmHWcYedvPl76C', 2, 1),
(33, 'Agüero', 'Sergio', 'hedix75863@onlcool.com', 'AgüeroS02062023154710', '$2a$10$Wms8.iCl3UTaPdInZlco4OcK/JpjHCYU2R4ekFhJ26z3F8TQnPKEG', 2, 8),
(34, 'De Bruyn', 'Kevin', 'hedix75863@onlcool.com', 'De BruynK02062023154731', '$2a$10$PSlCFdHlk4UTVQudKGwDOeS7jyE2m3Y5BSTb8gBhRG1y4W4a49tzq', 2, 6);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur_rencontre`
--

DROP TABLE IF EXISTS `utilisateur_rencontre`;
CREATE TABLE `utilisateur_rencontre` (
  `id_utilisateur` int(11) NOT NULL,
  `id_rencontre` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `est_joueur1` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `utilisateur_rencontre`
--

INSERT INTO `utilisateur_rencontre` (`id_utilisateur`, `id_rencontre`, `score`, `est_joueur1`) VALUES
(1, 13, 15, 1),
(1, 14, 15, 1),
(1, 15, 9, 1),
(1, 16, 12, 1),
(1, 17, 2, 1),
(1, 20, 9, 0),
(1, 27, 15, 0),
(1, 30, 2, 0),
(1, 32, 1, 0),
(1, 34, 3, 0),
(1, 35, 1, 0),
(1, 36, 9, 0),
(1, 37, 2, 0),
(1, 38, 12, 0),
(1, 39, 14, 0),
(1, 40, 15, 0),
(1, 41, 14, 0),
(1, 42, 15, 1),
(1, 43, 5, 1),
(28, 13, 2, 0),
(28, 20, 15, 1),
(28, 21, 13, 1),
(28, 22, 15, 1),
(28, 23, 9, 1),
(28, 24, 1, 1),
(28, 25, 15, 1),
(28, 26, 9, 1),
(28, 33, 5, 0),
(29, 14, 6, 0),
(29, 21, 15, 0),
(30, 15, 15, 0),
(30, 22, 1, 0),
(30, 27, 2, 1),
(30, 28, 13, 1),
(30, 29, 3, 1),
(30, 42, 2, 0),
(31, 16, 15, 0),
(31, 23, 15, 0),
(31, 28, 15, 0),
(31, 30, 15, 1),
(31, 31, 3, 1),
(31, 43, 15, 0),
(32, 17, 15, 0),
(32, 24, 15, 0),
(32, 29, 15, 0),
(32, 31, 15, 0),
(32, 32, 15, 1),
(32, 33, 15, 1),
(32, 34, 15, 1),
(32, 35, 15, 1),
(32, 36, 15, 1),
(32, 37, 15, 1),
(32, 38, 15, 1),
(32, 39, 15, 1),
(32, 40, 12, 1),
(32, 41, 15, 1),
(33, 25, 12, 0),
(34, 26, 15, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `equipe`
--
ALTER TABLE `equipe`
  ADD PRIMARY KEY (`id_equipe`);

--
-- Indexes for table `rencontre`
--
ALTER TABLE `rencontre`
  ADD PRIMARY KEY (`id_rencontre`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`),
  ADD UNIQUE KEY `niveau_UNIQUE` (`niveau`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_utilisateur`),
  ADD KEY `fk_Utilisateur_Role_idx` (`id_role`),
  ADD KEY `fk_Utilisateur_Equipe1_idx` (`id_equipe`);

--
-- Indexes for table `utilisateur_rencontre`
--
ALTER TABLE `utilisateur_rencontre`
  ADD PRIMARY KEY (`id_utilisateur`,`id_rencontre`),
  ADD KEY `fk_Utilisateur_has_Rencontre_Rencontre1_idx` (`id_rencontre`),
  ADD KEY `fk_Utilisateur_has_Rencontre_Utilisateur1_idx` (`id_utilisateur`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `equipe`
--
ALTER TABLE `equipe`
  MODIFY `id_equipe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `rencontre`
--
ALTER TABLE `rencontre`
  MODIFY `id_rencontre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id_role` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_utilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `fk_Utilisateur_Equipe1` FOREIGN KEY (`id_equipe`) REFERENCES `equipe` (`id_equipe`),
  ADD CONSTRAINT `fk_Utilisateur_Role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);

--
-- Constraints for table `utilisateur_rencontre`
--
ALTER TABLE `utilisateur_rencontre`
  ADD CONSTRAINT `fk_Utilisateur_has_Rencontre_Rencontre1` FOREIGN KEY (`id_rencontre`) REFERENCES `rencontre` (`id_rencontre`),
  ADD CONSTRAINT `fk_Utilisateur_has_Rencontre_Utilisateur1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

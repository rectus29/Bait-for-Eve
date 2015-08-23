-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 24 Août 2015 à 00:46
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `eve_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `config`
--

CREATE TABLE IF NOT EXISTS `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ke` longtext NOT NULL,
  `val` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `history`
--

CREATE TABLE IF NOT EXISTS `history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action` longtext NOT NULL,
  `date` datetime NOT NULL,
  `details` longtext NOT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `object_id` bigint(20) NOT NULL,
  `object_type` longtext NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK373FE494741A5F90` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `permission`
--

CREATE TABLE IF NOT EXISTS `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codeString` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`id`, `description`, `name`, `weight`) VALUES
(1, 'admin', 'admin', 1000);

-- --------------------------------------------------------

--
-- Structure de la table `role_permission`
--

CREATE TABLE IF NOT EXISTS `role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permissions_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permissions_id`),
  KEY `FKBD40D538CEEF9BB0` (`role_id`),
  KEY `FKBD40D538229AA9BB` (`permissions_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `lastLogin` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `restoreSession` varchar(255) DEFAULT NULL,
  `restoreSessionDate` datetime DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6A68E08CEEF9BB0` (`role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `created`, `email`, `lastLogin`, `password`, `restoreSession`, `restoreSessionDate`, `salt`, `state`, `username`, `role_id`) VALUES
(1, '2015-08-24 00:00:00', 'admin@alexandrebernard.Fr', '2015-08-24 00:00:00', '2pTblAzn+o+S0sFR46qTR4KVKgVZx7T/lPATHhDTU8c=', NULL, NULL, 'XU5Pp4Lz+mSiUkciqOUXD3XocslsvE/iK0eOze2A0C77WE9idlq9emqQOuO7y2T+arRT84Hku5cpFwGRAqgJy8aCTndMAtzR6QshNyfI61lHu5ec0Msj8121nTt91CYvltCWf3OCid3/8AQg+fNh89QXbSjeZvwRKhLWlb41A3A=', 1, 'admin', 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `FK373FE494741A5F90` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `role_permission`
--
ALTER TABLE `role_permission`
  ADD CONSTRAINT `FKBD40D538229AA9BB` FOREIGN KEY (`permissions_id`) REFERENCES `permission` (`id`),
  ADD CONSTRAINT `FKBD40D538CEEF9BB0` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK6A68E08CEEF9BB0` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

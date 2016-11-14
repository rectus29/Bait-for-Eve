-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 11 Avril 2016 à 18:21
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
-- Structure de la table `alliance`
--

CREATE TABLE IF NOT EXISTS `alliance` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `description` longtext,
  `name` varchar(255) NOT NULL,
  `shortName` varchar(255) NOT NULL,
  `startDate` datetime DEFAULT NULL,
  `url` longtext,
  `creatorCrestCharacter_id` bigint(20) DEFAULT NULL,
  `executorCorporation_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6BB3C8371CC7677B` (`creatorCrestCharacter_id`),
  KEY `FK6BB3C837C0BAC9A0` (`executorCorporation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `avatar`
--

CREATE TABLE IF NOT EXISTS `avatar` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `birthDay` datetime DEFAULT NULL,
  `location` tinyblob,
  `name` varchar(255) NOT NULL,
  `ship` tinyblob,
  `apiKey_id` bigint(20) DEFAULT NULL,
  `corporation_id` bigint(20) DEFAULT NULL,
  `dateOfBirth` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAC32C15971D07A73` (`apiKey_id`),
  KEY `FKAC32C159A4EE3E0D` (`corporation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `avatar`
--

INSERT INTO `avatar` (`id`, `created`, `updated`, `birthDay`, `location`, `name`, `ship`, `apiKey_id`, `corporation_id`, `dateOfBirth`) VALUES
(92562210, '2016-04-06 16:51:04', '2016-04-06 16:51:04', '2016-03-25 16:24:10', NULL, 'Karr Aulmais', NULL, 1, 592707514, '2012-10-26 17:44:15'),
(94070769, '2016-04-06 16:51:05', '2016-04-06 16:51:05', '2016-03-25 16:24:16', NULL, 'nakar Simalia', NULL, 1, 98276479, '2013-11-29 11:25:48'),
(95282441, '2016-04-06 16:51:06', '2016-04-06 16:51:06', '2016-03-25 16:24:16', NULL, 'Karrtik Aulmais', NULL, 1, 592707514, '2015-01-10 17:00:26');

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE IF NOT EXISTS `categorie` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `name` varchar(65356) NOT NULL,
  `published` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `config`
--

CREATE TABLE IF NOT EXISTS `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ke` longtext NOT NULL,
  `val` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `config`
--

INSERT INTO `config` (`id`, `ke`, `val`) VALUES
(1, 'maintenance_mod', '0'),
(2, 'defaultLocalization', 'fr'),
(4, 'graphColor', '#7cb5ec, #434348, #90ed7d, #f7a35c, #8085e9, #f15c80, #e4d354,#2b908f, #f45b5b, #91e8e1'),
(5, 'admin_msg', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `constellation`
--

CREATE TABLE IF NOT EXISTS `constellation` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `name` longtext NOT NULL,
  `position_id` bigint(20) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK83C775D3ADF3AC07` (`region_id`),
  KEY `FK83C775D3AA9604A7` (`position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `corporation`
--

CREATE TABLE IF NOT EXISTS `corporation` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `isNPC` bit(1) DEFAULT NULL,
  `name` longtext,
  `alliance_id` bigint(20) DEFAULT NULL,
  `description` longtext,
  `memberCount` int(11) DEFAULT NULL,
  `shares` int(11) DEFAULT NULL,
  `taxRate` double DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `url` longtext,
  `CeoCharacter_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB12FA008605A01E7` (`alliance_id`),
  KEY `FKB12FA0084BC6A04E` (`CeoCharacter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `corporation`
--

INSERT INTO `corporation` (`id`, `created`, `updated`, `isNPC`, `name`, `alliance_id`, `description`, `memberCount`, `shares`, `taxRate`, `ticker`, `url`, `CeoCharacter_id`) VALUES
(98276479, '2016-03-23 14:52:48', '2016-03-23 14:52:48', b'0', 'Aulmais Industry And Manufacturing Corporation', NULL, 'Enter a description of your corporation here.', 1, 1, 5, 'AULM', 'http://', NULL),
(592707514, '2016-03-25 16:24:12', '2016-03-25 16:24:12', b'0', 'Astromechanica Maxima', NULL, '<font size="12" color="#bfffffff">- Astromechanica Maxima -<br><br>Astromechanica Maxima recrute ! Plus de Fun intantanné plus de Carnaval et plus de Farmers Chinois dans la galaxie ça t''intéresse ? Alors rejoins vite notre chan débile remplis d''alcooliques et de gros losers :<br><br>Public Channel : </font><font size="12" color="#fff7931e"><a href="joinChannel:2137476962//None//None">Maxima</a></font><font size="12" color="#bfffffff"> <br>Contacts Diplomatiques : chan </font><font size="12" color="#fff7931e"><a href="joinChannel:-26211165//None//None">[A-F]</a></font><font size="12" color="#bfffffff">, ou </font><font size="12" color="#ffffa600"><loc><a href="showinfo:1380//1365782727">Barmy Failure</a></loc></font><font size="12" color="#bfffffff"> directement.<br><br>- Astromechanica, le fun est à un Warp de Toi ! -<br><br>- Astromechanicha, Sponsor officiel du carnaval de Rio since 1857 -</font>', 99, 99, 10, 'A-M', 'http://am.federatis.fr', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `crestcharacter`
--

CREATE TABLE IF NOT EXISTS `crestcharacter` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `isNPC` bit(1) DEFAULT NULL,
  `name` longtext,
  `corporation_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK89736652A4EE3E0D` (`corporation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `dogma`
--

CREATE TABLE IF NOT EXISTS `dogma` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `value` bigint(20) DEFAULT NULL,
  `attribute_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5B533D0F439CD87` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `effect`
--

CREATE TABLE IF NOT EXISTS `effect` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `description` longtext,
  `disallowAutoRepeat` bit(1) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `effectCategory` bigint(20) DEFAULT NULL,
  `electronicChance` bit(1) DEFAULT NULL,
  `isAssistance` bit(1) DEFAULT NULL,
  `isOffensive` bit(1) DEFAULT NULL,
  `isWarpSafe` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `postExpression` bigint(20) DEFAULT NULL,
  `preExpression` bigint(20) DEFAULT NULL,
  `published` bit(1) DEFAULT NULL,
  `rangeChance` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `group_type`
--

CREATE TABLE IF NOT EXISTS `group_type` (
  `group_id` bigint(20) NOT NULL,
  `typeList_id` bigint(20) NOT NULL,
  UNIQUE KEY `typeList_id` (`typeList_id`),
  KEY `FK4C7188FAFA0A3D29` (`typeList_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  KEY `FK373FE49475E73F13` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `news`
--

CREATE TABLE IF NOT EXISTS `news` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `publishDate` datetime DEFAULT NULL,
  `shortText` longtext,
  `text` longtext NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK338AD375E73F13` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `permission`
--

CREATE TABLE IF NOT EXISTS `permission` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `codeString` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `planet`
--

CREATE TABLE IF NOT EXISTS `planet` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `name` longtext NOT NULL,
  `position_id` bigint(20) DEFAULT NULL,
  `solarSystem_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC53E71981066792D` (`solarSystem_id`),
  KEY `FKC53E7198F439CD87` (`type_id`),
  KEY `FKC53E7198AA9604A7` (`position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `position`
--

CREATE TABLE IF NOT EXISTS `position` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `x` double DEFAULT NULL,
  `y` double DEFAULT NULL,
  `z` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `region`
--

CREATE TABLE IF NOT EXISTS `region` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `description` longtext,
  `name` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isAdmin` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`id`, `created`, `updated`, `description`, `isAdmin`, `name`, `weight`) VALUES
(1, '2016-03-18 00:00:00', '2016-03-18 00:00:00', 'admin', b'1', 'admin', 1000);

-- --------------------------------------------------------

--
-- Structure de la table `role_permission`
--

CREATE TABLE IF NOT EXISTS `role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permissions_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permissions_id`),
  KEY `FKBD40D5383AD49AFE` (`permissions_id`),
  KEY `FKBD40D538D0BC7B33` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `solarsystem`
--

CREATE TABLE IF NOT EXISTS `solarsystem` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `name` longtext NOT NULL,
  `securityClass` varchar(255) DEFAULT NULL,
  `securityStatus` double DEFAULT NULL,
  `constellation_id` bigint(20) DEFAULT NULL,
  `position_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK11680330FC195D6D` (`constellation_id`),
  KEY `FK11680330AA9604A7` (`position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `solarsystem_sovereignty`
--

CREATE TABLE IF NOT EXISTS `solarsystem_sovereignty` (
  `solarSystems_id` bigint(20) NOT NULL,
  `sovereigntyList_id` bigint(20) NOT NULL,
  KEY `FKB4C74C48BE862DCF` (`sovereigntyList_id`),
  KEY `FKB4C74C48C3E2BC3A` (`solarSystems_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `sovereignty`
--

CREATE TABLE IF NOT EXISTS `sovereignty` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `store_cart`
--

CREATE TABLE IF NOT EXISTS `store_cart` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK36EB9FBE75E73F13` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `store_cart_product`
--

CREATE TABLE IF NOT EXISTS `store_cart_product` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `quantity` bigint(20) DEFAULT NULL,
  `cart_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK21C4756E9FAF82F3` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `store_order`
--

CREATE TABLE IF NOT EXISTS `store_order` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `paymentRequest` mediumtext,
  `paymentResponse` mediumtext,
  `paymentToken` mediumtext,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA738F67075E73F13` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `store_order_item`
--

CREATE TABLE IF NOT EXISTS `store_order_item` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `productPrice` decimal(12,3) DEFAULT NULL,
  `productTax` decimal(12,3) DEFAULT NULL,
  `productname` varchar(255) DEFAULT NULL,
  `quantity` bigint(20) DEFAULT NULL,
  `referenceOrder_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA87BC382A5538D2C` (`referenceOrder_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

CREATE TABLE IF NOT EXISTS `type` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `capacity` bigint(20) DEFAULT NULL,
  `description` longtext,
  `mass` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `portionSize` bigint(20) DEFAULT NULL,
  `published` bit(1) DEFAULT NULL,
  `radius` bigint(20) DEFAULT NULL,
  `volume` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `lastLogin` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `restoreSession` varchar(255) DEFAULT NULL,
  `restoreSessionDate` datetime DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `userAuthentificationType` int(11) DEFAULT NULL,
  `userName` varchar(255) NOT NULL,
  `mainCharacter_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6A68E083C1F63BA` (`mainCharacter_id`),
  KEY `FK6A68E08D0BC7B33` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `created`, `updated`, `email`, `lastLogin`, `password`, `restoreSession`, `restoreSessionDate`, `salt`, `state`, `userAuthentificationType`, `userName`, `mainCharacter_id`, `role_id`) VALUES
(1, '2015-08-24 00:00:00', '2015-08-24 00:00:00', 'admin@alexandrebernard.Fr', '2016-04-11 16:04:55', '2pTblAzn+o+S0sFR46qTR4KVKgVZx7T/lPATHhDTU8c=', NULL, NULL, 'XU5Pp4Lz+mSiUkciqOUXD3XocslsvE/iK0eOze2A0C77WE9idlq9emqQOuO7y2T+arRT84Hku5cpFwGRAqgJy8aCTndMAtzR6QshNyfI61lHu5ec0Msj8121nTt91CYvltCWf3OCid3/8AQg+fNh89QXbSjeZvwRKhLWlb41A3A=', 1, 0, 'admin', 92562210, 1);

-- --------------------------------------------------------

--
-- Structure de la table `xmlapikey`
--

CREATE TABLE IF NOT EXISTS `xmlapikey` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `keyId` varchar(255) NOT NULL,
  `verificationCode` longtext NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `logonCount` bigint(20) DEFAULT NULL,
  `logonMinutes` bigint(20) DEFAULT NULL,
  `paidUntil` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB7F6D8DC75E73F13` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `xmlapikey`
--

INSERT INTO `xmlapikey` (`id`, `created`, `updated`, `keyId`, `verificationCode`, `user_id`, `createDate`, `logonCount`, `logonMinutes`, `paidUntil`, `state`) VALUES
(1, '2016-03-18 14:19:55', '2016-04-06 16:51:06', '2729727', 'XDoj3rjX7wgsnXvQZZaBW1R6Y4pAkmEIjn5pHP6CBPZfhLvoBxyKPK4TUjdvuhyM', 1, '2012-10-25 13:24:42', 1404, 218044, '2016-03-27 13:08:23', 0);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `alliance`
--
ALTER TABLE `alliance`
  ADD CONSTRAINT `FK6BB3C8371CC7677B` FOREIGN KEY (`creatorCrestCharacter_id`) REFERENCES `crestcharacter` (`id`),
  ADD CONSTRAINT `FK6BB3C837C0BAC9A0` FOREIGN KEY (`executorCorporation_id`) REFERENCES `corporation` (`id`);

--
-- Contraintes pour la table `avatar`
--
ALTER TABLE `avatar`
  ADD CONSTRAINT `FKAC32C15971D07A73` FOREIGN KEY (`apiKey_id`) REFERENCES `xmlapikey` (`id`),
  ADD CONSTRAINT `FKAC32C159A4EE3E0D` FOREIGN KEY (`corporation_id`) REFERENCES `corporation` (`id`);

--
-- Contraintes pour la table `constellation`
--
ALTER TABLE `constellation`
  ADD CONSTRAINT `FK83C775D3AA9604A7` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`),
  ADD CONSTRAINT `FK83C775D3ADF3AC07` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`);

--
-- Contraintes pour la table `corporation`
--
ALTER TABLE `corporation`
  ADD CONSTRAINT `FKB12FA0084BC6A04E` FOREIGN KEY (`CeoCharacter_id`) REFERENCES `avatar` (`id`),
  ADD CONSTRAINT `FKB12FA008605A01E7` FOREIGN KEY (`alliance_id`) REFERENCES `alliance` (`id`);

--
-- Contraintes pour la table `crestcharacter`
--
ALTER TABLE `crestcharacter`
  ADD CONSTRAINT `FK89736652A4EE3E0D` FOREIGN KEY (`corporation_id`) REFERENCES `corporation` (`id`);

--
-- Contraintes pour la table `dogma`
--
ALTER TABLE `dogma`
  ADD CONSTRAINT `FK5B533D0F439CD87` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`);

--
-- Contraintes pour la table `group_type`
--
ALTER TABLE `group_type`
  ADD CONSTRAINT `FK4C7188FAFA0A3D29` FOREIGN KEY (`typeList_id`) REFERENCES `type` (`id`);

--
-- Contraintes pour la table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `FK373FE49475E73F13` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `news`
--
ALTER TABLE `news`
  ADD CONSTRAINT `FK338AD375E73F13` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `planet`
--
ALTER TABLE `planet`
  ADD CONSTRAINT `FKC53E71981066792D` FOREIGN KEY (`solarSystem_id`) REFERENCES `solarsystem` (`id`),
  ADD CONSTRAINT `FKC53E7198AA9604A7` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`),
  ADD CONSTRAINT `FKC53E7198F439CD87` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`);

--
-- Contraintes pour la table `role_permission`
--
ALTER TABLE `role_permission`
  ADD CONSTRAINT `FKBD40D5383AD49AFE` FOREIGN KEY (`permissions_id`) REFERENCES `permission` (`id`);

--
-- Contraintes pour la table `solarsystem`
--
ALTER TABLE `solarsystem`
  ADD CONSTRAINT `FK11680330AA9604A7` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`),
  ADD CONSTRAINT `FK11680330FC195D6D` FOREIGN KEY (`constellation_id`) REFERENCES `constellation` (`id`);

--
-- Contraintes pour la table `solarsystem_sovereignty`
--
ALTER TABLE `solarsystem_sovereignty`
  ADD CONSTRAINT `FKB4C74C48BE862DCF` FOREIGN KEY (`sovereigntyList_id`) REFERENCES `sovereignty` (`id`),
  ADD CONSTRAINT `FKB4C74C48C3E2BC3A` FOREIGN KEY (`solarSystems_id`) REFERENCES `solarsystem` (`id`);

--
-- Contraintes pour la table `store_cart`
--
ALTER TABLE `store_cart`
  ADD CONSTRAINT `FK36EB9FBE75E73F13` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `store_cart_product`
--
ALTER TABLE `store_cart_product`
  ADD CONSTRAINT `FK21C4756E9FAF82F3` FOREIGN KEY (`cart_id`) REFERENCES `store_cart` (`id`);

--
-- Contraintes pour la table `store_order`
--
ALTER TABLE `store_order`
  ADD CONSTRAINT `FKA738F67075E73F13` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `store_order_item`
--
ALTER TABLE `store_order_item`
  ADD CONSTRAINT `FKA87BC382A5538D2C` FOREIGN KEY (`referenceOrder_id`) REFERENCES `store_order` (`id`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK6A68E083C1F63BA` FOREIGN KEY (`mainCharacter_id`) REFERENCES `avatar` (`id`);

--
-- Contraintes pour la table `xmlapikey`
--
ALTER TABLE `xmlapikey`
  ADD CONSTRAINT `FKB7F6D8DC75E73F13` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

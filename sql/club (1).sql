-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 31 mai 2019 à 19:30
-- Version du serveur :  10.1.38-MariaDB
-- Version de PHP :  7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `club`
--
create database if not exists club;
-- --------------------------------------------------------

--
-- Structure de la table `adherents`
--

CREATE TABLE `adherents` (
  `id` int(11) NOT NULL,
  `firstname` varchar(46) NOT NULL,
  `lastname` varchar(46) NOT NULL,
  `email` varchar(46) NOT NULL,
  `filiere` varchar(46) NOT NULL,
  `absence` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `adherents`
--

INSERT INTO `adherents` (`id`, `firstname`, `lastname`, `email`, `filiere`, `absence`) VALUES
(1, 'zakaria', 'elamim', 'zakariaelamim@mail.com', '2ITE', 0),
(2, 'houda', 'el makhchoune', 'houda@mail.com', '2ITE', 15),
(3, 'morad', 'mahjour', 'morad@mail.com', 'ISIC', 3);

-- --------------------------------------------------------

--
-- Structure de la table `charges`
--

CREATE TABLE `charges` (
  `id` int(11) NOT NULL,
  `source` varchar(40) NOT NULL,
  `date` varchar(40) NOT NULL,
  `montant` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `charges`
--

INSERT INTO `charges` (`id`, `source`, `date`, `montant`) VALUES
(14, 'transport', '2019-04-04', 350),
(15, 'matériels', '2019-04-28', 1500);

-- --------------------------------------------------------

--
-- Structure de la table `events`
--

CREATE TABLE `events` (
  `id` int(11) NOT NULL,
  `name` varchar(46) NOT NULL,
  `date` varchar(46) NOT NULL,
  `descriptif` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `events`
--

INSERT INTO `events` (`id`, `name`, `date`, `descriptif`) VALUES
(13, 'Hackathon', '2019-05-26', 'un événement où un groupe de développeurs volontaires se réunissent pour faire de la programmation informatique collaborative, sur plusieurs jours'),
(14, 'Formation photoshop ', '2019-06-14', 'une formation assurée par monsieur X pour les étudiant de Y'),
(15, 'Don du sang ', '2019-07-11', 'l\'evenement est en collaboration avec l\'association santé maroc..');

-- --------------------------------------------------------

--
-- Structure de la table `produits`
--

CREATE TABLE `produits` (
  `id` int(11) NOT NULL,
  `source` varchar(40) NOT NULL,
  `date` varchar(40) NOT NULL,
  `montant` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produits`
--

INSERT INTO `produits` (`id`, `source`, `date`, `montant`) VALUES
(20, 'Sponsor(CIH)', '2019-04-20', 5000),
(21, 'Ensaj', '2019-05-02', 1500),
(22, 'cotisation', '2019-05-16', 3000),
(23, 'caisse', '2019-01-01', 10000);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(64) DEFAULT NULL,
  `poste` varchar(64) NOT NULL,
  `password` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `username`, `poste`, `password`) VALUES
(1, 'Doe', 'president', 'azerty123'),
(18, 'Arcoffen', 'vice president', ''),
(44, 'Midnight', 'secretaire', ''),
(45, 'zakaria', 'tresorier', '');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adherents`
--
ALTER TABLE `adherents`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `charges`
--
ALTER TABLE `charges`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `produits`
--
ALTER TABLE `produits`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `adherents`
--
ALTER TABLE `adherents`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `charges`
--
ALTER TABLE `charges`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `events`
--
ALTER TABLE `events`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `produits`
--
ALTER TABLE `produits`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

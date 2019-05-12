-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: gondor-mysql
-- Czas generowania: 28 Lut 2019, 14:49
-- Wersja serwera: 8.0.15
-- Wersja PHP: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `lumato`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `cars`
--

CREATE TABLE `cars` (
  `idcars` int(11) NOT NULL,
  `model` varchar(45) DEFAULT NULL,
  `brand` varchar(45) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `iduser` int(11) NOT NULL,
  `fuelType` varchar(45) DEFAULT NULL,
  `regplate` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `cars`
--

INSERT INTO `cars` (`idcars`, `model`, `brand`, `year`, `iduser`, `fuelType`, `regplate`) VALUES
(28, 'Siwik', 'Chonda', 1999, 67, 'Petrol', 'LU1231B'),
(29, 'Ręczna', 'Drezyna', 1887, 67, 'P+LPG', 'ARIZONA'),
(31, 'Czerwone', 'Ferari', 2003, 67, 'Diesel', 'DW0102X'),
(32, '125p', 'Fiat', 1978, 68, 'Petrol', 'UU2013D'),
(33, 'AstraXD', 'Opel', 2002, 69, 'Diesel', 'XD20139W');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `extracosts`
--

CREATE TABLE `extracosts` (
  `idextraCosts` int(11) NOT NULL,
  `cost` decimal(7,2) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `idcars` int(11) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `fuelcosts`
--

CREATE TABLE `fuelcosts` (
  `idfuelcost` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `priceperliter` decimal(4,2) DEFAULT NULL,
  `amountoffuel` decimal(4,1) DEFAULT NULL,
  `currentmileage` int(11) DEFAULT NULL,
  `typeoffuel` varchar(45) DEFAULT NULL,
  `idcar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `fuelcosts`
--

INSERT INTO `fuelcosts` (`idfuelcost`, `date`, `priceperliter`, `amountoffuel`, `currentmileage`, `typeoffuel`, `idcar`) VALUES
(8, '2019-02-06 08:20:00', '12.73', '3.2', 12490, 'P+LPG', 28),
(9, '2019-02-13 07:31:14', '12.32', '3.1', 12880, 'P+LPG', 28),
(10, '2019-02-13 07:23:09', '12.73', '7.2', 30192, 'Diesel', 31),
(11, '2019-02-13 07:23:09', '12.73', '7.2', 30192, 'Diesel', 31);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `iduser` int(11) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`iduser`, `email`, `password`, `firstname`, `lastname`) VALUES
(67, 'anna@wp.pl', 'aaa', 'Anna', 'Wanna'),
(68, 'arnold@classic.us', 'biceps', 'Arnold', 'Szwarc'),
(69, 'john@doe.com', 'eldo', 'Johnn', 'Doeoe');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`idcars`),
  ADD KEY `fk_cars_users_idx` (`iduser`);

--
-- Indeksy dla tabeli `extracosts`
--
ALTER TABLE `extracosts`
  ADD PRIMARY KEY (`idextraCosts`,`idcars`),
  ADD KEY `fk_extraCosts_cars1_idx` (`idcars`);

--
-- Indeksy dla tabeli `fuelcosts`
--
ALTER TABLE `fuelcosts`
  ADD PRIMARY KEY (`idfuelcost`),
  ADD KEY `fk_fuelCosts_cars1_idx` (`idcar`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`iduser`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `cars`
--
ALTER TABLE `cars`
  MODIFY `idcars` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT dla tabeli `extracosts`
--
ALTER TABLE `extracosts`
  MODIFY `idextraCosts` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `fuelcosts`
--
ALTER TABLE `fuelcosts`
  MODIFY `idfuelcost` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `cars`
--
ALTER TABLE `cars`
  ADD CONSTRAINT `fk_cars_users` FOREIGN KEY (`iduser`) REFERENCES `users` (`iduser`);

--
-- Ograniczenia dla tabeli `extracosts`
--
ALTER TABLE `extracosts`
  ADD CONSTRAINT `fk_extraCosts_cars1` FOREIGN KEY (`idcars`) REFERENCES `cars` (`idcars`);

--
-- Ograniczenia dla tabeli `fuelcosts`
--
ALTER TABLE `fuelcosts`
  ADD CONSTRAINT `fk_fuelCosts_cars1` FOREIGN KEY (`idcar`) REFERENCES `cars` (`idcars`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

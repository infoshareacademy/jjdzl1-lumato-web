-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: gondor-mysql
-- Czas generowania: 06 Maj 2019, 11:37
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
-- Struktura tabeli dla tabeli `car`
--

CREATE TABLE `car` (
  `id` int(11) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `fuel_type` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `production_year` int(11) DEFAULT NULL,
  `reg_plate` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `car`
--

INSERT INTO `car` (`id`, `brand`, `fuel_type`, `user_id`, `model`, `production_year`, `reg_plate`) VALUES
(50, 'CAR1', 'Petrol', 3, '1', 2016, 'KR1111'),
(52, 'CAR2', 'Diesel', 3, '2', 2016, 'KSU 33668'),
(53, 'CAR3', 'Diesel', 3, '3', 1998, 'KSY 1236'),
(54, 'CAR4', 'Petrol', 3, '4', 1999, 'TEST'),
(64, 'EESRDHJB', 'Petrol', 3, 'ESRGHVBM', 2000, 'DFXCGVHBJ'),
(65, 'CHONDA', 'Petrol', 7, 'SIWIK', 1999, '1231213');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `extracost`
--

CREATE TABLE `extracost` (
  `id` int(11) NOT NULL,
  `cost` decimal(7,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `cost_date` date DEFAULT NULL,
  `car_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `extracost`
--

INSERT INTO `extracost` (`id`, `cost`, `description`, `cost_date`, `car_id`, `user_id`) VALUES
(8, '10000.00', 'Szpachlowanie', '2013-03-03', 50, 3),
(9, '500.00', 'Dupa', '2013-03-03', 50, 3),
(10, '150.00', 'Mycie Siusiaka', '2013-03-03', 52, 3),
(11, '1125.00', 'tjuning zawieszenia', '2019-03-03', 54, 3),
(12, '50.00', 'dildo', '2019-04-29', 65, 7),
(13, '30.00', 'pink steering wheel', '2019-03-28', 65, 7);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `fuelcost`
--

CREATE TABLE `fuelcost` (
  `id` int(11) NOT NULL,
  `amount_of_fuel` double DEFAULT NULL,
  `current_mileage` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `type_of_fuel` varchar(45) DEFAULT NULL,
  `price_per_liter` double DEFAULT NULL,
  `car_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `fuelcost`
--

INSERT INTO `fuelcost` (`id`, `amount_of_fuel`, `current_mileage`, `date`, `type_of_fuel`, `price_per_liter`, `car_id`, `user_id`) VALUES
(15, 23, 1000, '2013-03-03 00:01:44', 'Petrol', 5.25, 50, 3),
(16, 55, 10000000, '2016-05-14 00:02:03', 'Diesel', 4.89, 52, 3),
(17, 36, 50000, '2013-03-03 00:02:26', 'Diesel', 4.99, 52, 3),
(18, 12.5, 12000, '2019-05-03 18:28:34', 'Petrol', 3.33, 65, 7),
(19, 10, 11300, '2019-05-02 18:28:58', 'Petrol', 3.75, 65, 7),
(20, 7.2, 10900, '2019-05-01 18:29:26', 'Petrol', 3.66, 65, 7);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `token`
--

CREATE TABLE `token` (
  `user_id` int(11) DEFAULT NULL,
  `user_token` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `token`
--

INSERT INTO `token` (`user_id`, `user_token`) VALUES
(0, 'admintoken'),
(7, 'wHktc7qyUewRcpAPpKG1BVtoRajf8YjzO8rDMIPscNpxcBMfUM');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `confirm_password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`id`, `confirm_password`, `email`, `first_name`, `last_name`, `password`) VALUES
(3, NULL, 'tomek.starzak@gmail.com', 'Tomek', '', '1000:12665a30ea22e71a55837a4ca10682f8:b981d70d7dcafcf2bc6bf7a78ffad6c5147ebc9d13db02a8c54f2228603b6551c24b3689b8e013b6d01387c40961e7a74c68113bb4a21e04779875a647fecd84'),
(5, '1111', 'akaras.@o2.pl', 'Aneta', 'Karad', '1000:41fe8389439d56720afa7c6eb997d07d:66377fa8f8d79adac84a7a4c15d2fc2b299f46461bcdfa74402d4e53ec86fcb71d2e81f7c080946efe0ab2b59636dfcc5eba5c50935ff240b0da1372de364508'),
(6, 'aaaa', 'email1', 'sdfvd', 'dsf', '1000:4ae6f997af25e11e955f721c5b12e71f:9c2516345c478417f8852fd8ac4b91e093df21099f353849570c710f198c9e418c307b63645225b751cf1317c1516c205d671de6f9e1d7181d815718852f398f'),
(7, NULL, 'anna@wp.pl', 'anna', 'wanna', '1000:bd6111e3ced68c3348197f93dd227707:63b5613306a46eb61f3a7f7df7aaa01f6c1b21d24bf0f88b86c96e89cce1898d3068c95c13cffaf07e15b61e15d7cf31c6e83a20be98cc09cc30a606a0700a49'),
(9, 'aZ4!aZ4!', 'anna@op.pl', 'Adam', 'Ondra', '1000:a79e819db6f1db582d3c5f4b788b07f1:fa6554bb6d50d41f1c8a81cd0b2bc550bd2b42ae873ffe89c00d470f39752cec03372a0d221c042051998eadc5d64f4ce04c597eb1e581e027898eaba79d0730');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeksy dla tabeli `extracost`
--
ALTER TABLE `extracost`
  ADD PRIMARY KEY (`id`,`car_id`),
  ADD KEY `fk_extracosts_cars1_idx` (`car_id`),
  ADD KEY `users_id_idx` (`user_id`);

--
-- Indeksy dla tabeli `fuelcost`
--
ALTER TABLE `fuelcost`
  ADD PRIMARY KEY (`id`,`car_id`,`user_id`),
  ADD KEY `fk_fuelcosts_cars1_idx` (`car_id`,`user_id`),
  ADD KEY `users_id_idx` (`user_id`);

--
-- Indeksy dla tabeli `token`
--
ALTER TABLE `token`
  ADD UNIQUE KEY `unique_user_id` (`user_id`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `car`
--
ALTER TABLE `car`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT dla tabeli `extracost`
--
ALTER TABLE `extracost`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT dla tabeli `fuelcost`
--
ALTER TABLE `fuelcost`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ograniczenia dla tabeli `extracost`
--
ALTER TABLE `extracost`
  ADD CONSTRAINT `car_id` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`);

--
-- Ograniczenia dla tabeli `fuelcost`
--
ALTER TABLE `fuelcost`
  ADD CONSTRAINT `cars_id` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`),
  ADD CONSTRAINT `users_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

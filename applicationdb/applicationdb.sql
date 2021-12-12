-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 12, 2021 at 06:10 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `applicationdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
  `id` int(11) NOT NULL,
  `full_name` varchar(250) NOT NULL,
  `age` varchar(250) NOT NULL,
  `phone_number` varchar(250) NOT NULL,
  `address` varchar(250) NOT NULL,
  `doctor_name` varchar(250) NOT NULL,
  `reasons_for_assessment` varchar(250) NOT NULL,
  `medicines_reffered` varchar(250) NOT NULL,
  `family_medical_history` varchar(250) NOT NULL,
  `past_medical_history` varchar(250) NOT NULL,
  `examinations_findings` varchar(250) NOT NULL,
  `signature` varchar(250) NOT NULL,
  `date` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reports`
--

INSERT INTO `reports` (`id`, `full_name`, `age`, `phone_number`, `address`, `doctor_name`, `reasons_for_assessment`, `medicines_reffered`, `family_medical_history`, `past_medical_history`, `examinations_findings`, `signature`, `date`) VALUES
(1, 'Rawad rassamny', '21', '+961 70/973158', 'deirqubel', 'khaled richani', 'headaches', 'pain killers', 'non', 'non', 'good condition', 'khaled', '2021-12-01'),
(2, 'testt', '39', 'testt', 'testt', 'testt', 'testt', 'testt', 'testt', 'testt', 'testt', '147538da338b770b61e592afc92b1ee6', '12/09/2021'),
(3, 'testttt', '100', 'testtt', 'testtt', 'tstt', 'tsttt', 'tsttt', 'tsttt', 'tsttt', 'testttt', 'be1f7aaa0a308531fee45b21aa640f4d', '12/10/2021'),
(4, 'rawadrawd', '40', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', '098f6bcd4621d373cade4e832627b4f6', '1/11/2020'),
(8, 'testting', '22', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', '098f6bcd4621d373cade4e832627b4f6', '1/11/2020'),
(9, 'testing23', '59', 'testing', 'testing', 'testing', 'tsting', 'tst', 'tst', 'tst', 'tst', '9301c2a72c0f099d0313099f1cd54799', '1/11/2020');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `Full_name` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `Full_name`, `email`, `password`) VALUES
(1, 'rawad rassamny', 'rawadrassamny@gmail.com', '58a3121824f23d7c48611e11b5022a0c'),
(2, 'testing', 'test@gmail.com', 'cc03e747a6afbbcbf8be7668acfebee5'),
(3, 'rawad', 'rawad321@gmail.com', 'db98637f8d2ba4773f5bb0e5e6011fba'),
(4, 'testt', 'testing123@gmail.com', '202cb962ac59075b964b07152d234b70'),
(5, 'rassamny', 'rassamny@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b'),
(6, 'rawad h.rassamny', 'tst@gmail.com', '25f9e794323b453885f5181f1b624d0b');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

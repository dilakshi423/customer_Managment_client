-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: May 14, 2022 at 03:26 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electricitypower`
--

-- --------------------------------------------------------

--
-- Table structure for table `customermanage`
--

CREATE TABLE `customermanage` (
  `cusID` int(6) NOT NULL,
  `cusName` varchar(200) NOT NULL,
  `cusAddress` varchar(200) NOT NULL,
  `cusNIC` varchar(200) NOT NULL,
  `cusEmail` varchar(200) NOT NULL,
  `cusPno` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customermanage`
--

INSERT INTO `customermanage` (`cusID`, `cusName`, `cusAddress`, `cusNIC`, `cusEmail`, `cusPno`) VALUES
(3, 'Ramya', 'malaberoad%252Ccolombo', '945678949V', 'imesha40gmail.com', '0709897890'),
(6, 'Ruchira', '456,Gatebuwana road,piliyandala', '914567892V', 'ruchiralama@gmail.com', '0718680528'),
(13, 'viraji', 'malabe road colombo', '943567456V', 'vlamahewagmail.com', '0705674562'),
(14, 'dilakshi lamahewa', '7/113 A,edipokunagoda', '996143678V', 'it20075330@my.sliit.lk', '0704003184');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customermanage`
--
ALTER TABLE `customermanage`
  ADD PRIMARY KEY (`cusID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customermanage`
--
ALTER TABLE `customermanage`
  MODIFY `cusID` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2019 at 03:21 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `automobile`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `employee_id` varchar(10) NOT NULL,
  `in_date` varchar(10) DEFAULT NULL,
  `in_time` varchar(12) DEFAULT NULL,
  `out_date` varchar(10) DEFAULT NULL,
  `out_time` varchar(12) DEFAULT NULL,
  `worked_hours` varchar(6) DEFAULT NULL,
  `active_status` char(2) NOT NULL DEFAULT 'y'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`employee_id`, `in_date`, `in_time`, `out_date`, `out_time`, `worked_hours`, `active_status`) VALUES
('2', '2019-11-25', '14:07:45.879', '2019-11-25', '19:46:00.709', '5:38', 'n'),
('1', '2019-11-25', '14:24:41.259', '2019-11-25', '19:46:55.917', '5:22', 'n');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_id` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` bigint(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `salary` bigint(10) DEFAULT NULL,
  `active_status` varchar(2) NOT NULL DEFAULT 'y'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employee_id`, `name`, `address`, `phone`, `email`, `salary`, `active_status`) VALUES
('1', 'Jaggu', 'abc', 8103974125, 'jag@gmail.com', 10258, 'y'),
('2', 'him', 'abc', 9003987711, 'him@gmail.com', 9210, 'y'),
('3', 'Kajal', 'abc', 1532145698, 'kaj@gmail.com', 152463, 'y'),
('4', 'Nihal', 'bas', 1234567890, 'nih@gmail.com', 14625, 'y'),
('5', 'Harry', 'sjhdbf', 4561237890, 'har@gmail.com', 12345, 'y'),
('6', 'sabari', 'asd', 1532141546, 'sab@gmail.com', 75421, 'y'),
('7', 'Susmitha', 'susanb', 4561238216, 'sus@gmail.com', 41256, 'y'),
('8', 'Sus', 'acx', 7546245698, 'sus@mitha.com', 42153, 'y');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `employee_id` varchar(10) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`employee_id`, `password`) VALUES
('1', '1'),
('2', '2'),
('3', '3'),
('4', '4'),
('5', '5'),
('6', '6'),
('7', '7'),
('8', '8');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD KEY `FK1` (`employee_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD KEY `FK2` (`employee_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `FK1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`);

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `FK2` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

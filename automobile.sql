-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 23, 2019 at 02:21 PM
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
  `employee_id` int(11) NOT NULL,
  `t_date` varchar(10) DEFAULT NULL,
  `in_time` varchar(10) DEFAULT NULL,
  `out_time` varchar(10) DEFAULT NULL,
  `worked_hours` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`employee_id`, `t_date`, `in_time`, `out_time`, `worked_hours`) VALUES
(1, '2019-11-22', '17:48:22.8', '16:09:34', NULL),
(2, '2019-11-22', '17:59:10.4', '12:35:29.2', NULL),
(4, '2019-11-22', '18:13:12.3', '12:22:44.3', NULL),
(3, '2019-11-22', '18:17:07.9', '12:36:28.3', NULL),
(2, '0000-00-00', '00:00:01.0', '12:35:29.2', '1'),
(5, '2019-11-22', '18:26:30.5', '12:05:22.0', NULL),
(7, '2019-11-22', '18:30:39.6', '12:27:18.2', NULL),
(6, '2019-11-22', '18:31:30.0', '18:31:40.7', NULL),
(8, '2019-11-23', '15:56:20.0', '16:28:08', '0:31'),
(1, '2019-11-23', '16:07:03', '16:25:33', NULL),
(2, '2019-11-23', '16:19:34', '16:25:29', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(10) NOT NULL,
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
(1, 'Jaggu', 'abc', 8103974125, 'jag@gmail.com', 10258, 'y'),
(2, 'him', 'abc', 9003987711, 'him@gmail.com', 9210, 'y'),
(3, 'Kajal', 'abc', 1532145698, 'kaj@gmail.com', 152463, 'y'),
(4, 'Nihal', 'bas', 1234567890, 'nih@gmail.com', 14625, 'y'),
(5, 'Harry', 'sjhdbf', 4561237890, 'har@gmail.com', 12345, 'y'),
(6, 'sabari', 'asd', 1532141546, 'sab@gmail.com', 75421, 'y'),
(7, 'Susmitha', 'susanb', 4561238216, 'sus@gmail.com', 41256, 'y'),
(8, 'Sus', 'acx', 7546245698, 'sus@mitha.com', 42153, 'y');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `employee_id` int(10) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`employee_id`, `password`) VALUES
(1, '1'),
(2, '2'),
(3, '3'),
(4, '4'),
(5, '5'),
(6, '6'),
(7, '7'),
(8, '8');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `t_date` (`t_date`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD UNIQUE KEY `employee_id` (`employee_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`);

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

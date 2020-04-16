-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 16, 2020 at 08:01 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `healthcare`
--

-- --------------------------------------------------------

--
-- Table structure for table `appoinment`
--

CREATE TABLE `appoinment` (
  `AID` int(11) NOT NULL,
  `PName` varchar(200) NOT NULL,
  `DName` varchar(200) NOT NULL,
  `RoomNo` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Time` date NOT NULL,
  `ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `docappoinments`
--

CREATE TABLE `docappoinments` (
  `DocID` int(11) NOT NULL COMMENT 'Primary Key',
  `AID` int(11) NOT NULL COMMENT 'Primary Key',
  `DocPhone` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `docappoinments`
--

INSERT INTO `docappoinments` (`DocID`, `AID`, `DocPhone`) VALUES
(123, 111, 77575242);

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `DocID` int(11) NOT NULL,
  `DocName` varchar(200) NOT NULL,
  `DocNIC` varchar(10) NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `ReqNo` int(11) NOT NULL,
  `Specialized` varchar(200) NOT NULL,
  `Email` varchar(200) NOT NULL,
  `DocCharges` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`DocID`, `DocName`, `DocNIC`, `Gender`, `ReqNo`, `Specialized`, `Email`, `DocCharges`) VALUES
(1, 'Namala Rajapaksha', '894561234V', 'M', 58764, 'skin', 'namal.r@gmail.com', '200000.00');

-- --------------------------------------------------------

--
-- Table structure for table `docworkhost`
--

CREATE TABLE `docworkhost` (
  `DocID` int(11) NOT NULL COMMENT 'Primary Key',
  `HosID` int(11) NOT NULL COMMENT 'Primary Key',
  `HosPhone` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `docworkhost`
--

INSERT INTO `docworkhost` (`DocID`, `HosID`, `HosPhone`) VALUES
(123, 222, 771597534),
(456, 333, 771597534),
(555, 333, 771597534);

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `HosID` int(11) NOT NULL,
  `HosName` varchar(200) NOT NULL,
  `HosCity` varchar(200) NOT NULL,
  `Rooms` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `hospitalpayment`
--

CREATE TABLE `hospitalpayment` (
  `PayID` int(11) NOT NULL COMMENT 'Primary Key',
  `HosID` int(11) NOT NULL COMMENT 'Primary key'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `ID` int(11) NOT NULL,
  `Name` varchar(200) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `Age` int(11) NOT NULL,
  `NIC` varchar(20) NOT NULL,
  `Phone` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `PayID` int(11) NOT NULL,
  `DocCharge` decimal(10,2) NOT NULL,
  `HosCharge` decimal(10,2) NOT NULL,
  `AppoCharge` decimal(10,2) NOT NULL,
  `Total` decimal(10,2) NOT NULL,
  `PayType` varchar(20) NOT NULL,
  `CardNo` int(11) NOT NULL,
  `CardExpiryDate` date NOT NULL,
  `Card_CVNo` int(11) NOT NULL,
  `AID` int(11) NOT NULL,
  `DocID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appoinment`
--
ALTER TABLE `appoinment`
  ADD PRIMARY KEY (`AID`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`DocID`);

--
-- Indexes for table `hospital`
--
ALTER TABLE `hospital`
  ADD PRIMARY KEY (`HosID`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`PayID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appoinment`
--
ALTER TABLE `appoinment`
  MODIFY `AID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `DocID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `hospital`
--
ALTER TABLE `hospital`
  MODIFY `HosID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `PayID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

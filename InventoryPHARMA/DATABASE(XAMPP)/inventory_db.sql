-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 09, 2026 at 04:44 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventory_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `code` varchar(50) NOT NULL,
  `prodname` varchar(100) NOT NULL,
  `qty` int(11) NOT NULL,
  `price` double NOT NULL,
  `category` varchar(50) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT 0,
  `expiry_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `code`, `prodname`, `qty`, `price`, `category`, `archived`, `expiry_date`) VALUES
(1, 'C01', 'Cetirizine', 0, 2, 'Tablets', 1, NULL),
(3, 'P01', 'Paracetamol', 81, 6, 'Tablets', 0, '2027-02-04'),
(6, 'L02', 'Losartan-100mg', 1, 6, 'Tablets', 1, NULL),
(7, 'L03', 'Lagundi', 180, 4, 'Syrups', 0, NULL),
(11, 'N01', 'Neozep', 80, 8, 'Tablets', 0, NULL),
(12, 'C03', 'Cefalixin', 50, 7, 'Tablets', 0, NULL),
(13, 'A01', 'Ascorbic Acid', 20, 5, 'Tablets', 0, '2026-09-30');

-- --------------------------------------------------------

--
-- Table structure for table `sales_of_the_day`
--

CREATE TABLE `sales_of_the_day` (
  `id` int(11) NOT NULL,
  `total` double NOT NULL,
  `payment` double NOT NULL,
  `balance` double NOT NULL,
  `sale_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sales_of_the_day`
--

INSERT INTO `sales_of_the_day` (`id`, `total`, `payment`, `balance`, `sale_date`) VALUES
(1, 148, 200, 52, '2026-09-13'),
(2, 264, 500, 236, '2026-09-13'),
(3, 122, 200, 78, '2026-09-14'),
(4, 80, 100, 20, '2026-09-14'),
(5, 148, 200, 52, '2026-09-16'),
(6, 72, 100, 28, '2026-09-16'),
(7, 114, 200, 86, '2026-09-16'),
(8, 60, 100, 40, '2026-09-16'),
(9, 120, 200, 80, '2026-09-16'),
(10, 96, 100, 4, '2026-09-16'),
(11, 60, 100, 40, '2026-09-22'),
(12, 60, 100, 40, '2026-09-22');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(20) NOT NULL,
  `active` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`, `active`) VALUES
(1, 'admin1', 'admin123', 'Admin', 1),
(2, 'staff1', 'staff123', 'Staff', 1),
(3, 'staff2', 'staff234', 'Staff', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sales_of_the_day`
--
ALTER TABLE `sales_of_the_day`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `sales_of_the_day`
--
ALTER TABLE `sales_of_the_day`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

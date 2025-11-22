-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2025 at 08:25 PM
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
-- Database: `pos`
--

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `invId` int(11) NOT NULL,
  `invName` varchar(50) NOT NULL,
  `qty` int(11) NOT NULL,
  `lowStokeThreshold` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`invId`, `invName`, `qty`, `lowStokeThreshold`) VALUES
(1, 'Espresso Beans', 12, 10),
(2, 'Milk', 40, 10),
(3, 'Foam Ingredients', 40, 10),
(4, 'Americano brewed ', 45, 10),
(5, 'Green Tea Leaves', 30, 10),
(6, 'Chai Mix', 25, 10),
(7, 'Croissants', 60, 15),
(8, 'Muffins', 50, 15),
(9, 'Bagels & Cream Cheese', 35, 10),
(10, 'BLT Ingredients', 25, 10),
(11, 'Cheese & Bread', 25, 10),
(12, 'Chicken Wrap Ingredients', 20, 10),
(13, 'Berry Mix', 20, 10),
(14, 'Mango Mix', 20, 10),
(15, 'Protein Shake Mix', 15, 10),
(16, 'Brewed Coffee', 45, 15),
(17, 'Instant Coffee', 80, 20),
(18, 'Black Tea leaves', 45, 10),
(19, 'Oolong Tea leaves', 45, 15),
(20, 'Dried Peach Pieces', 20, 10),
(21, 'Dried Strawberries', 25, 15),
(22, 'Strawberry Mix', 12, 5),
(23, 'Mango Mix', 15, 5);

-- --------------------------------------------------------

--
-- Table structure for table `menu_item`
--

CREATE TABLE `menu_item` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `costPrice` double NOT NULL,
  `inv_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu_item`
--

INSERT INTO `menu_item` (`id`, `name`, `price`, `costPrice`, `inv_id`) VALUES
(1, 'Espresso', 3, 1.8, 1),
(2, 'Latte', 4.5, 2, 2),
(3, 'Cappuccino', 4.25, 1.85, 3),
(4, 'Ice Americano', 3.5, 1.9, 4),
(5, 'Green Tea', 3, 1.5, 5),
(6, 'Chai Latte', 4, 2, 6),
(7, 'Croissant', 2.75, 0.5, 7),
(8, 'Muffin', 2.5, 1.95, 8),
(9, 'Bagel with Cream Cheese', 3.25, 2.25, 9),
(10, 'BLT Sandwich', 6.5, 4.25, 10),
(11, 'Grilled Cheese', 5.5, 3.5, 11),
(12, 'Chicken Wrap', 7, 4.8, 12),
(13, 'Berry Smoothie', 5.75, 3.75, 13),
(14, 'Mango Smoothie', 5.75, 3.75, 14),
(15, 'Protein Shake', 6, 4, 15),
(16, 'Greek Frappe', 4, 1.25, 17),
(17, 'Ice Coffee', 2.99, 1.25, 16),
(18, 'Black Tea', 1.5, 0.5, 18),
(19, 'Oolong Tea', 3, 0.5, 19),
(20, 'Peach Tea', 2.5, 0.5, 20),
(21, 'Strawberry Tea', 2.5, 0.5, 21),
(22, 'Strawberry Smoothies', 5.75, 2.3, 22);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `payment_ID` int(11) NOT NULL,
  `order_ID` int(11) NOT NULL,
  `method_payment` enum('CASH','CREDIT','DEBIT') DEFAULT NULL,
  `tips` decimal(10,2) DEFAULT NULL CHECK (`tips` >= 0),
  `payment_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`payment_ID`, `order_ID`, `method_payment`, `tips`, `payment_date`) VALUES
(28, 42, 'CASH', 2.50, '2025-11-21 15:02:48'),
(30, 44, 'CASH', 0.30, '2025-11-21 15:06:39'),
(31, 45, 'CASH', 1.15, '2025-11-21 15:08:25'),
(32, 46, 'CASH', 0.00, '2025-11-21 15:12:13'),
(33, 47, 'CASH', 0.00, '2025-11-21 15:14:30'),
(34, 48, 'CASH', 0.00, '2025-11-21 15:15:30'),
(35, 49, 'CASH', 5.95, '2025-11-21 15:17:31'),
(36, 50, 'CASH', 0.00, '2025-11-21 15:19:10'),
(37, 51, 'CASH', 1.25, '2025-11-21 15:21:39'),
(38, 52, 'CASH', 1.25, '2025-11-21 15:26:11'),
(39, 53, 'CASH', 2.29, '2025-11-21 15:28:45'),
(40, 54, 'CASH', 0.01, '2025-11-21 15:31:43'),
(41, 55, 'CASH', 0.75, '2025-11-21 15:35:48'),
(42, 56, 'CASH', 0.00, '2025-11-21 15:36:54'),
(43, 57, 'CASH', 1.58, '2025-11-21 15:38:38'),
(44, 58, 'CASH', 0.00, '2025-11-21 15:39:44'),
(45, 59, 'CASH', 0.00, '2025-11-21 15:41:35'),
(46, 60, 'CASH', 67.00, '2025-11-21 15:53:22'),
(47, 61, 'CASH', 0.58, '2025-11-21 15:54:44'),
(48, 62, 'CASH', 1.09, '2025-11-21 15:55:30'),
(49, 63, 'CASH', 2.05, '2025-11-21 15:57:07'),
(50, 64, 'CASH', 0.00, '2025-11-21 15:57:56');

-- --------------------------------------------------------

--
-- Table structure for table `sale_order`
--

CREATE TABLE `sale_order` (
  `order_id` int(11) NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT '''OPEN''',
  `created_at` varchar(60) NOT NULL DEFAULT current_timestamp(),
  `finalized_at` varchar(60) DEFAULT NULL,
  `subtotal` double(12,2) NOT NULL DEFAULT 0.00,
  `tax_total` double(12,2) NOT NULL DEFAULT 0.00,
  `total` double(12,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sale_order`
--

INSERT INTO `sale_order` (`order_id`, `status`, `created_at`, `finalized_at`, `subtotal`, `tax_total`, `total`) VALUES
(2, 'OPEN', '2025-11-17 22:11:32', NULL, 11.75, 0.00, 0.00),
(37, '\'OPEN\'', '2025-11-21 14:44:44', NULL, 8.75, 0.00, 0.00),
(39, '\'OPEN\'', '2025-11-21 14:46:17', NULL, 10.50, 0.00, 0.00),
(40, '\'OPEN\'', '2025-11-21 14:48:44', NULL, 5.74, 0.00, 0.00),
(41, '\'OPEN\'', '2025-11-21 14:51:05', NULL, 7.50, 0.00, 0.00),
(42, '\'OPEN\'', '2025-11-21 15:02:40', NULL, 12.50, 0.00, 0.00),
(43, '\'OPEN\'', '2025-11-21 15:04:25', NULL, 7.00, 0.00, 0.00),
(44, '\'OPEN\'', '2025-11-21 15:06:30', NULL, 3.00, 0.00, 0.00),
(45, 'CLOSED', '2025-11-21 15:08:03', '2025-11-21T15:08:26.893442500', 5.75, 0.00, 0.00),
(46, 'CLOSED', '2025-11-21 15:12:08', '2025-11-21T15:12:14.640095300', 2.50, 0.00, 0.00),
(47, '\'OPEN\'', '2025-11-21 15:14:24', NULL, 3.00, 0.00, 0.00),
(48, 'CLOSED', '2025-11-21 15:15:23', '2025-11-21T15:15:31.666457500', 4.00, 0.60, 0.00),
(49, 'CLOSED', '2025-11-21 15:17:10', '2025-11-21T15:17:33.261880300', 29.75, 4.46, 40.16),
(50, 'CLOSED', '2025-11-21 15:19:00', '2025-11-21T15:19:11.925153700', 5.50, 0.82, 6.33),
(51, 'CLOSED', '2025-11-21 15:21:29', '2025-11-21T15:21:41.004836700', 12.50, 1.88, 0.00),
(52, '\'OPEN\'', '2025-11-21 15:25:46', NULL, 12.50, 0.00, 0.00),
(53, 'CLOSED', '2025-11-21 15:28:18', '2025-11-21T15:28:47.467068300', 15.25, 2.29, 0.00),
(54, 'CLOSED', '2025-11-21 15:31:00', '2025-11-21T15:31:48.476955700', 46.00, 6.90, 0.00),
(55, 'CLOSED', '2025-11-21 15:35:35', '2025-11-21T15:35:50.237312600', 7.50, 1.12, 1.00),
(56, 'CLOSED', '2025-11-21 15:36:39', '2025-11-21T15:36:55.650861300', 10.50, 1.57, 12.07),
(57, 'CLOSED', '2025-11-21 15:38:04', '2025-11-21T15:38:39.910176600', 15.75, 2.36, 19.69),
(58, 'CLOSED', '2025-11-21 15:39:31', '2025-11-21T15:39:46.045208800', 15.00, 2.25, 17.25),
(59, 'CLOSED', '2025-11-21 15:41:07', '2025-11-21T15:41:37.270491700', 20.75, 3.11, 23.86),
(60, 'CLOSED', '2025-11-21 15:52:47', '2025-11-21T15:53:24.490991100', 10.75, 1.61, 79.36),
(61, 'CLOSED', '2025-11-21 15:54:24', '2025-11-21T15:54:46.268817400', 5.75, 0.86, 7.19),
(62, 'CLOSED', '2025-11-21 15:55:21', '2025-11-21T15:55:31.396863600', 7.25, 1.09, 9.43),
(63, 'CLOSED', '2025-11-21 15:56:58', '2025-11-21T15:57:08.620202200', 10.25, 1.54, 13.84),
(64, 'CLOSED', '2025-11-21 15:57:45', '2025-11-21T15:57:57.855362300', 13.75, 2.06, 15.81);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`invId`);

--
-- Indexes for table `menu_item`
--
ALTER TABLE `menu_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `inventory_FK` (`inv_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_ID`),
  ADD KEY `FK_payment_saleOrder_orderID` (`order_ID`);

--
-- Indexes for table `sale_order`
--
ALTER TABLE `sale_order`
  ADD PRIMARY KEY (`order_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `invId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `menu_item`
--
ALTER TABLE `menu_item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `sale_order`
--
ALTER TABLE `sale_order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `menu_item`
--
ALTER TABLE `menu_item`
  ADD CONSTRAINT `inventory_FK` FOREIGN KEY (`inv_id`) REFERENCES `inventory` (`invId`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `FK_payment_saleOrder_orderID` FOREIGN KEY (`order_ID`) REFERENCES `sale_order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

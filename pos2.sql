-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 18, 2025 at 04:25 AM
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
  `itemCode` int(11) NOT NULL,
  `invName` varchar(50) NOT NULL,
  `qty` int(11) NOT NULL,
  `lowStokeThreeshold` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`invId`, `itemCode`, `invName`, `qty`, `lowStokeThreeshold`) VALUES
(1, 100, 'Espresso Beans', 50, 10),
(2, 101, 'Milk', 40, 10),
(3, 102, 'Foam Ingredients', 40, 10),
(4, 103, 'Americano brewed ', 45, 10),
(5, 104, 'Green Tea Leaves', 30, 10),
(6, 105, 'Chai Mix', 25, 10),
(7, 106, 'Croissants', 60, 15),
(8, 107, 'Muffins', 50, 15),
(9, 108, 'Bagels & Cream Cheese', 35, 10),
(10, 109, 'BLT Ingredients', 25, 10),
(11, 110, 'Cheese & Bread', 25, 10),
(12, 111, 'Chicken Wrap Ingredients', 20, 10),
(13, 112, 'Berry Mix', 20, 10),
(14, 114, 'Mango Mix', 20, 10),
(15, 115, 'Protein Shake Mix', 15, 10),
(16, 116, 'Brewed Coffee', 45, 15),
(17, 117, 'Instant Coffee', 80, 20),
(18, 118, 'Black Tea leaves', 45, 10),
(19, 119, 'Oolong Tea leaves', 45, 15),
(20, 120, 'Dried Peach Pieces', 20, 10),
(21, 121, 'Dried Strawberries', 25, 15),
(22, 122, 'Strawberry Mix', 12, 5),
(23, 123, 'Mango Mix', 15, 5);

-- --------------------------------------------------------

--
-- Table structure for table `menu_item`
--

CREATE TABLE `menu_item` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `costPrice` int(11) NOT NULL,
  `inv_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu_item`
--

INSERT INTO `menu_item` (`id`, `name`, `price`, `costPrice`, `inv_id`) VALUES
(1, 'Espresso', 3, 0, 1),
(2, 'Latte', 4.5, 0, 2),
(3, 'Cappuccino', 4.25, 0, 3),
(4, 'Ice Americano', 3.5, 0, 4),
(5, 'Green Tea', 3, 0, 5),
(6, 'Chai Latte', 4, 0, 6),
(7, 'Croissant', 2.75, 0, 7),
(8, 'Muffin', 2.5, 0, 8),
(9, 'Bagel with Cream Cheese', 3.25, 0, 9),
(10, 'BLT Sandwich', 6.5, 0, 10),
(11, 'Grilled Cheese', 5.5, 0, 11),
(12, 'Chicken Wrap', 7, 0, 12),
(13, 'Berry Smoothie', 5.75, 0, 13),
(14, 'Mango Smoothie', 5.75, 0, 14),
(15, 'Protein Shake', 6, 0, 15),
(16, 'Greek Frappe', 4, 0, 17),
(17, 'Ice Coffee', 2.99, 0, 16),
(18, 'Black Tea', 1.5, 0, 18),
(19, 'Oolong Tea', 3, 0, 19),
(20, 'Peach Tea', 2.5, 0, 20),
(21, 'Strawberry Tea', 2.5, 0, 21),
(22, 'Strawberry Smoothies', 5.75, 0, 22);

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

-- --------------------------------------------------------

--
-- Table structure for table `sale_order`
--

CREATE TABLE `sale_order` (
  `order_id` int(11) NOT NULL,
  `status` text NOT NULL DEFAULT 'OPEN',
  `created_at` text NOT NULL DEFAULT current_timestamp(),
  `finalized_at` text DEFAULT NULL,
  `subtotal` double(12,2) NOT NULL DEFAULT 0.00,
  `tax_total` double(12,2) NOT NULL DEFAULT 0.00,
  `discount` double(12,2) NOT NULL DEFAULT 0.00,
  `total` double(12,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sale_order`
--

INSERT INTO `sale_order` (`order_id`, `status`, `created_at`, `finalized_at`, `subtotal`, `tax_total`, `discount`, `total`) VALUES
(2, 'OPEN', '2025-11-17 22:11:32', NULL, 11.75, 0.00, 0.00, 0.00);

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
  MODIFY `payment_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sale_order`
--
ALTER TABLE `sale_order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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

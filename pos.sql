-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 28, 2025 at 12:29 AM
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
(1, 'Espresso Beans', 10, 10),
(2, 'Milk', 10, 10),
(3, 'Foam Ingredients', 17, 10),
(4, 'Americano brewed ', 69, 10),
(5, 'Green Tea Leaves', 14, 10),
(6, 'Chai Mix', 25, 10),
(7, 'Croissants', 60, 15),
(8, 'Muffins', 50, 15),
(9, 'Bagels & Cream Cheese', 35, 10),
(10, 'BLT Ingredients', 25, 10),
(11, 'Cheese & Bread', 25, 10),
(12, 'Chicken Wrap Ingredients', 20, 10),
(13, 'Berry Mix', 19, 10),
(14, 'Mango Mix', 20, 10),
(15, 'Protein Shake Mix', 40, 10),
(16, 'Brewed Coffee', 44, 15),
(17, 'Instant Coffee', 79, 20),
(18, 'Black Tea leaves', 44, 10),
(19, 'Oolong Tea leaves', 45, 15),
(20, 'Dried Peach Pieces', 20, 10),
(21, 'Dried Strawberries', 25, 15),
(22, 'Strawberry Mix', 11, 5);

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
(56, 77, 'CASH', 0.00, '2025-11-25 21:13:13'),
(57, 78, 'CASH', 0.00, '2025-11-25 22:32:55'),
(58, 79, 'CASH', 0.83, '2025-11-25 22:34:32'),
(59, 80, 'CASH', 0.00, '2025-11-25 22:50:42'),
(61, 82, 'CASH', 0.00, '2025-11-27 10:22:55'),
(62, 92, 'CASH', 0.00, '2025-11-27 15:24:03'),
(63, 93, 'CASH', 0.00, '2025-11-27 15:28:27');

-- --------------------------------------------------------

--
-- Table structure for table `popular_items`
--

CREATE TABLE `popular_items` (
  `popular_items_id` int(3) NOT NULL,
  `popular_items_name` varchar(40) NOT NULL,
  `popular_items_quantity` int(10) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `popular_items`
--

INSERT INTO `popular_items` (`popular_items_id`, `popular_items_name`, `popular_items_quantity`) VALUES
(1, 'Espresso', 19),
(2, 'Latte', 5),
(3, 'Cappuccino', 9),
(4, 'Ice Americano', 4),
(5, 'Green Tea', 2),
(6, 'Chai Latte', 0),
(7, 'Croissant', 1),
(8, 'Muffin', 122),
(9, 'Bagel with Cream Cheese', 0),
(10, 'BLT Sandwich', 10),
(11, 'Grilled Cheese', 0),
(12, 'Chicken Wrap', 14),
(13, 'Berry Smoothie', 3),
(14, 'Mango Smoothie', 0),
(15, 'Protein Shake', 0),
(16, 'Greek Frappe', 3),
(17, 'Ice Coffee', 1),
(18, 'Black Tea', 12),
(19, 'Oolong Tea', 0),
(20, 'Peach Tea', 0),
(21, 'Strawberry Tea', 0),
(22, 'Strawberry Smoothie', 5);

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
  `total` double(12,2) NOT NULL DEFAULT 0.00,
  `totalCostPrice` double(10,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sale_order`
--

INSERT INTO `sale_order` (`order_id`, `status`, `created_at`, `finalized_at`, `subtotal`, `tax_total`, `total`, `totalCostPrice`) VALUES
(76, '\'OPEN\'', '2025-11-25 16:56:21', NULL, 4.25, 0.00, 0.00, 1.85),
(77, 'CLOSED', '2025-11-25 21:13:05', '2025-11-25T21:13:14.089569700', 7.50, 1.12, 8.62, 3.15),
(78, 'CLOSED', '2025-11-25 22:32:41', '2025-11-25T22:32:55.506451700', 9.75, 1.46, 11.21, 3.25),
(79, 'CLOSED', '2025-11-25 22:34:27', '2025-11-25T22:34:32.611639100', 8.25, 1.24, 10.31, 5.70),
(80, 'CLOSED', '2025-11-25 22:50:39', '2025-11-25T22:50:42.689845100', 8.25, 1.24, 9.49, 5.70),
(82, 'CLOSED', '2025-11-27 10:22:50', '2025-11-27T10:22:56.018794600', 0.00, 0.00, 0.00, 0.00),
(83, '\'OPEN\'', '2025-11-27 13:38:12', NULL, 3.00, 0.00, 0.00, 1.80),
(84, '\'OPEN\'', '2025-11-27 13:38:58', NULL, 3.00, 0.00, 0.00, 1.80),
(85, '\'OPEN\'', '2025-11-27 13:42:06', NULL, 3.00, 0.00, 0.00, 1.80),
(86, '\'OPEN\'', '2025-11-27 14:15:12', NULL, 3.00, 0.00, 0.00, 1.80),
(87, '\'OPEN\'', '2025-11-27 14:22:22', NULL, 3.00, 0.00, 0.00, 1.80),
(88, '\'OPEN\'', '2025-11-27 14:27:47', NULL, 3.00, 0.00, 0.00, 1.80),
(89, '\'OPEN\'', '2025-11-27 14:27:53', NULL, 3.00, 0.00, 0.00, 1.80),
(90, '\'OPEN\'', '2025-11-27 14:28:15', NULL, 3.00, 0.00, 0.00, 1.80),
(91, '\'OPEN\'', '2025-11-27 14:28:20', NULL, 3.00, 0.00, 0.00, 1.80),
(92, 'CLOSED', '2025-11-27 15:22:13', '2025-11-27T15:24:03.616811200', 33.00, 4.95, 37.95, 19.80),
(93, 'CLOSED', '2025-11-27 15:27:34', '2025-11-27T15:28:27.801050900', 26.49, 3.97, 30.46, 12.45);

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
-- Indexes for table `popular_items`
--
ALTER TABLE `popular_items`
  ADD PRIMARY KEY (`popular_items_id`);

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
  MODIFY `payment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT for table `sale_order`
--
ALTER TABLE `sale_order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=96;

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

--
-- Constraints for table `popular_items`
--
ALTER TABLE `popular_items`
  ADD CONSTRAINT `FK_popularItems_menuItems_popularItemsID` FOREIGN KEY (`popular_items_id`) REFERENCES `menu_item` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

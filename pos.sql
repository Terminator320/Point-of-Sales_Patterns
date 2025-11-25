-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2025 at 11:48 PM
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
(22, 'Strawberry Mix', 12, 5);

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
(1, 'Espresso', 8),
(2, 'Latte', 5),
(3, 'Cappuccino', 9),
(4, 'Ice Americano', 2),
(5, 'Green Tea', 0),
(6, 'Chai Latte', 0),
(7, 'Croissant', 0),
(8, 'Muffin', 0),
(9, 'Bagel with Cream Cheese', 0),
(10, 'BLT Sandwich', 10),
(11, 'Grilled Cheese', 0),
(12, 'Chicken Wrap', 0),
(13, 'Berry Smoothie', 0),
(14, 'Mango Smoothie', 0),
(15, 'Protein Shake', 0),
(16, 'Greek Frappe', 0),
(17, 'Ice Coffee', 0),
(18, 'Black Tea', 0),
(19, 'Oolong Tea', 0),
(20, 'Peach Tea', 0),
(21, 'Strawberry Tea', 0),
(22, 'Strawberry Smoothie', 3);

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `sale_Id` int(11) NOT NULL,
  `revenue` decimal(10,2) NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `sale_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`sale_Id`, `revenue`, `cost`, `sale_date`) VALUES
(1, 140.00, 155.00, '2025-02-01 09:15:00'),
(2, 165.00, 95.00, '2025-02-01 12:50:00'),
(3, 200.00, 135.00, '2025-02-01 19:10:00'),
(4, 125.00, 140.00, '2025-02-02 08:45:00'),
(5, 155.00, 80.00, '2025-02-02 13:25:00'),
(6, 210.00, 150.00, '2025-02-02 20:00:00'),
(7, 150.00, 165.00, '2025-02-03 09:40:00'),
(8, 135.00, 75.00, '2025-02-03 14:30:00'),
(9, 195.00, 130.00, '2025-02-03 19:20:00'),
(10, 110.00, 135.00, '2025-02-04 08:55:00'),
(11, 160.00, 90.00, '2025-02-04 12:10:00'),
(12, 205.00, 140.00, '2025-02-04 18:45:00'),
(13, 100.00, 150.00, '2025-02-05 09:00:00'),
(14, 140.00, 85.00, '2025-02-05 13:35:00'),
(15, 220.00, 160.00, '2025-02-05 20:20:00'),
(16, 155.00, 175.00, '2025-02-06 10:05:00'),
(17, 150.00, 82.00, '2025-02-06 14:40:00'),
(18, 230.00, 165.00, '2025-02-06 19:55:00'),
(19, 120.00, 135.00, '2025-02-07 09:25:00'),
(20, 165.00, 90.00, '2025-02-07 12:55:00'),
(21, 200.00, 145.00, '2025-02-07 18:30:00'),
(22, 145.00, 160.00, '2025-02-08 08:50:00'),
(23, 135.00, 70.00, '2025-02-08 12:20:00'),
(24, 210.00, 155.00, '2025-02-08 19:35:00'),
(25, 130.00, 155.00, '2025-02-09 09:35:00'),
(26, 150.00, 85.00, '2025-02-09 14:10:00'),
(27, 220.00, 140.00, '2025-02-09 20:05:00'),
(28, 110.00, 140.00, '2025-02-10 10:00:00'),
(29, 170.00, 95.00, '2025-02-10 13:45:00'),
(30, 200.00, 130.00, '2025-02-10 19:15:00'),
(31, 145.00, 165.00, '2025-02-11 09:20:00'),
(32, 155.00, 90.00, '2025-02-11 12:40:00'),
(33, 210.00, 135.00, '2025-02-11 18:50:00'),
(34, 100.00, 135.00, '2025-02-12 08:40:00'),
(35, 160.00, 85.00, '2025-02-12 13:00:00'),
(36, 225.00, 150.00, '2025-02-12 20:10:00'),
(37, 135.00, 155.00, '2025-02-13 09:50:00'),
(38, 150.00, 78.00, '2025-02-13 12:55:00'),
(39, 240.00, 165.00, '2025-02-13 18:25:00'),
(40, 155.00, 170.00, '2025-02-14 10:30:00'),
(41, 165.00, 92.00, '2025-02-14 13:45:00'),
(42, 220.00, 150.00, '2025-02-14 20:55:00'),
(43, 125.00, 150.00, '2025-02-15 09:10:00'),
(44, 170.00, 95.00, '2025-02-15 12:35:00'),
(45, 235.00, 160.00, '2025-02-15 19:50:00');

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
(76, '\'OPEN\'', '2025-11-25 16:56:21', NULL, 4.25, 0.00, 0.00, 1.85);

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
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`sale_Id`);

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
  MODIFY `payment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `sale_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `sale_order`
--
ALTER TABLE `sale_order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

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

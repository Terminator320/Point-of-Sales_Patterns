-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 13, 2025 at 12:59 AM
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
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `category_name`) VALUES
(1, 'Coffee'),
(3, 'Pastry'),
(4, 'Sandwich'),
(5, 'Smoothie'),
(2, 'Tea');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `invId` int(11) NOT NULL,
  `invName` varchar(50) NOT NULL,
  `qty` int(11) NOT NULL,
  `lowStokeThreeshold` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`invId`, `invName`, `qty`, `lowStokeThreeshold`) VALUES
(1, 'Espresso Beans', 50, 10),
(2, 'Milk', 40, 10),
(3, 'Foam Ingredients', 40, 10),
(4, 'Hot Water', 45, 10),
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
(15, 'Protein Shake Mix', 15, 10);

-- --------------------------------------------------------

--
-- Table structure for table `menu_item`
--

CREATE TABLE `menu_item` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `category_id` int(11) NOT NULL,
  `inv_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu_item`
--

INSERT INTO `menu_item` (`id`, `name`, `price`, `category_id`, `inv_id`) VALUES
(1, 'Espresso', 3, 1, 1),
(2, 'Latte', 4.5, 1, 2),
(3, 'Cappuccino', 4.25, 1, 3),
(4, 'Americano', 3.5, 1, 4),
(5, 'Green Tea', 3, 2, 5),
(6, 'Chai Latte', 4, 2, 6),
(7, 'Croissant', 2.75, 3, 7),
(8, 'Muffin', 2.5, 3, 8),
(9, 'Bagel with Cream Cheese', 3.25, 3, 9),
(10, 'BLT Sandwich', 6.5, 4, 10),
(11, 'Grilled Cheese', 5.5, 4, 11),
(12, 'Chicken Wrap', 7, 4, 12),
(13, 'Berry Smoothie', 5.75, 5, 13),
(14, 'Mango Smoothie', 5.75, 5, 14),
(15, 'Protein Shake', 6, 5, 15);

-- --------------------------------------------------------

--
-- Table structure for table `order_item`
--

CREATE TABLE `order_item` (
  `order_item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `unit_price_cents` int(11) NOT NULL,
  `line_subtotal_cents` int(11) NOT NULL,
  `line_tax_cents` int(11) NOT NULL,
  `line_total_cents` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
-- Table structure for table `refund`
--

CREATE TABLE `refund` (
  `refund_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `refund_method` enum('CASH','CREDIT','DEBIT') DEFAULT NULL,
  `reason` text DEFAULT NULL,
  `refund_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sale_order`
--

CREATE TABLE `sale_order` (
  `order_id` int(11) NOT NULL,
  `status` text NOT NULL DEFAULT 'NEW',
  `created_at` text NOT NULL DEFAULT current_timestamp(),
  `finalized_at` text DEFAULT NULL,
  `subtotal_cents` int(11) NOT NULL DEFAULT 0,
  `tax_total_cents` int(11) NOT NULL DEFAULT 0,
  `discount_cents` int(11) NOT NULL DEFAULT 0,
  `total_cents` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`),
  ADD UNIQUE KEY `category_name` (`category_name`);

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
  ADD KEY `category_FK` (`category_id`),
  ADD KEY `inventory_FK` (`inv_id`);

--
-- Indexes for table `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`order_item_id`),
  ADD UNIQUE KEY `order_id` (`order_id`,`menu_id`),
  ADD KEY `FK_orderItem_menuItem_ID` (`menu_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_ID`),
  ADD KEY `FK_payment_saleOrder_orderID` (`order_ID`);

--
-- Indexes for table `refund`
--
ALTER TABLE `refund`
  ADD PRIMARY KEY (`refund_id`),
  ADD KEY `FK_refund_saleOrder_orderID` (`order_id`);

--
-- Indexes for table `sale_order`
--
ALTER TABLE `sale_order`
  ADD PRIMARY KEY (`order_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `invId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `menu_item`
--
ALTER TABLE `menu_item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `order_item`
--
ALTER TABLE `order_item`
  MODIFY `order_item_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `refund`
--
ALTER TABLE `refund`
  MODIFY `refund_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sale_order`
--
ALTER TABLE `sale_order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `menu_item`
--
ALTER TABLE `menu_item`
  ADD CONSTRAINT `category_FK` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  ADD CONSTRAINT `inventory_FK` FOREIGN KEY (`inv_id`) REFERENCES `inventory` (`invId`);

--
-- Constraints for table `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `FK_orderItem_menuItem_ID` FOREIGN KEY (`menu_id`) REFERENCES `menu_item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_orderItem_saleOrder_orderID` FOREIGN KEY (`order_id`) REFERENCES `sale_order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `FK_payment_saleOrder_orderID` FOREIGN KEY (`order_ID`) REFERENCES `sale_order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `refund`
--
ALTER TABLE `refund`
  ADD CONSTRAINT `FK_refund_saleOrder_orderID` FOREIGN KEY (`order_id`) REFERENCES `sale_order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

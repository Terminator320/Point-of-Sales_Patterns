-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 06, 2025 at 07:21 AM
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
  `lowStockThreshold` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`invId`, `invName`, `qty`, `lowStockThreshold`) VALUES
(1, 'Espresso Beans', 2, 10),
(2, 'Milk', 9, 10),
(3, 'Foam Ingredients', 25, 10),
(4, 'Americano brewed ', 50, 10),
(5, 'Green Tea Leaves', 15, 10),
(6, 'Chai Mix', 25, 10),
(7, 'Croissants', 50, 15),
(8, 'Muffins', 50, 15),
(9, 'Bagels & Cream Cheese', 35, 10),
(10, 'BLT Ingredients', 25, 10),
(11, 'Cheese & Bread', 25, 10),
(12, 'Chicken Wrap Ingredients', 20, 10),
(13, 'Berry Mix', 20, 10),
(14, 'Mango Mix', 20, 10),
(15, 'Protein Shake Mix', 40, 10),
(16, 'Brewed Coffee', 45, 15),
(17, 'Instant Coffee', 80, 20),
(18, 'Black Tea leaves', 45, 10),
(19, 'Oolong Tea leaves', 45, 15),
(20, 'Dried Peach Pieces', 20, 10),
(21, 'Dried Strawberries', 25, 15),
(22, 'Strawberry Mix', 15, 5);

-- --------------------------------------------------------

--
-- Table structure for table `menu_item`
--

CREATE TABLE `menu_item` (
  `menuItem_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `costPrice` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu_item`
--

INSERT INTO `menu_item` (`menuItem_id`, `name`, `price`, `costPrice`) VALUES
(1, 'Espresso', 3, 1.8),
(2, 'Latte', 4.5, 2),
(3, 'Cappuccino', 4.25, 1.85),
(4, 'Ice Americano', 3.5, 1.9),
(5, 'Green Tea', 3, 1.5),
(6, 'Chai Latte', 4, 2),
(7, 'Croissant', 2.75, 0.5),
(8, 'Muffin', 2.5, 1.95),
(9, 'Bagel with Cream Cheese', 3.25, 2.25),
(10, 'BLT Sandwich', 6.5, 4.25),
(11, 'Grilled Cheese', 5.5, 3.5),
(12, 'Chicken Wrap', 7, 4.8),
(13, 'Berry Smoothie', 5.75, 3.75),
(14, 'Mango Smoothie', 5.75, 3.75),
(15, 'Protein Shake', 6, 4),
(16, 'Greek Frappe', 4, 1.25),
(17, 'Ice Coffee', 2.99, 1.25),
(18, 'Black Tea', 1.5, 0.5),
(19, 'Oolong Tea', 3, 0.5),
(20, 'Peach Tea', 2.5, 0.5),
(21, 'Strawberry Tea', 2.5, 0.5),
(22, 'Strawberry Smoothies', 5.75, 2.3);

-- --------------------------------------------------------

--
-- Table structure for table `menu_item_ingredient`
--

CREATE TABLE `menu_item_ingredient` (
  `menu_item_id` int(11) NOT NULL,
  `inv_id` int(11) NOT NULL,
  `quantity_used` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu_item_ingredient`
--

INSERT INTO `menu_item_ingredient` (`menu_item_id`, `inv_id`, `quantity_used`) VALUES
(1, 1, 1),
(2, 1, 1),
(2, 2, 1),
(2, 3, 1),
(3, 1, 1),
(3, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 2, 1),
(6, 6, 1),
(7, 7, 1),
(8, 8, 1),
(9, 9, 1),
(10, 10, 1),
(11, 11, 1),
(12, 12, 1),
(13, 13, 1),
(14, 14, 1),
(15, 15, 1),
(16, 2, 1),
(16, 17, 1),
(17, 2, 1),
(17, 16, 1),
(18, 18, 1),
(19, 19, 1),
(20, 20, 1),
(21, 21, 1),
(22, 22, 1);

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
(63, 93, 'CASH', 0.00, '2025-11-27 15:28:27'),
(64, 96, 'CREDIT', 4.35, '2025-12-01 13:54:13'),
(65, 100, 'CASH', 0.00, '2025-12-01 15:36:45'),
(66, 103, 'CASH', 0.60, '2025-12-01 18:20:18'),
(67, 107, 'DEBIT', 3.10, '2025-12-03 08:46:08'),
(74, 121, 'CASH', 0.00, '2025-12-05 00:22:26'),
(75, 122, 'CASH', 2.15, '2025-12-05 00:33:14'),
(76, 125, 'CASH', 0.00, '2025-12-05 00:36:36'),
(77, 155, 'CASH', 2.48, '2025-12-05 17:34:35'),
(78, 169, 'CASH', 0.00, '2025-12-05 18:42:40'),
(79, 170, 'CASH', 4.57, '2025-12-05 18:46:32'),
(80, 171, 'CASH', 0.45, '2025-12-05 18:47:00'),
(81, 172, 'CASH', 0.00, '2025-12-05 20:59:59'),
(82, 173, 'CASH', 0.00, '2025-12-05 21:02:33'),
(83, 179, 'CASH', 4.01, '2025-12-05 21:22:32');

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
(1, 'Espresso', 23),
(2, 'Latte', 8),
(3, 'Cappuccino', 12),
(4, 'Ice Americano', 12),
(5, 'Green Tea', 2),
(6, 'Chai Latte', 1),
(7, 'Croissant', 2),
(8, 'Muffin', 122),
(9, 'Bagel with Cream Cheese', 0),
(10, 'BLT Sandwich', 10),
(11, 'Grilled Cheese', 0),
(12, 'Chicken Wrap', 14),
(13, 'Berry Smoothie', 4),
(14, 'Mango Smoothie', 1),
(15, 'Protein Shake', 1),
(16, 'Greek Frappe', 3),
(17, 'Ice Coffee', 4),
(18, 'Black Tea', 12),
(19, 'Oolong Tea', 0),
(20, 'Peach Tea', 0),
(21, 'Strawberry Tea', 0),
(22, 'Strawberry Smoothie', 7);

-- --------------------------------------------------------

--
-- Table structure for table `sales_order_items`
--

CREATE TABLE `sales_order_items` (
  `sales_order_items_id` int(11) NOT NULL,
  `menu_item_id` int(11) NOT NULL,
  `sales_order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sales_order_items`
--

INSERT INTO `sales_order_items` (`sales_order_items_id`, `menu_item_id`, `sales_order_id`) VALUES
(119, 1, 135),
(120, 2, 135),
(121, 3, 135),
(143, 1, 143),
(144, 2, 143),
(145, 4, 143),
(155, 4, 145),
(156, 4, 145),
(157, 4, 145),
(158, 4, 145),
(159, 4, 145),
(160, 4, 145),
(161, 4, 146),
(162, 4, 146),
(163, 4, 146),
(164, 4, 146),
(165, 4, 146),
(166, 4, 146),
(167, 4, 147),
(168, 4, 147),
(169, 4, 147),
(170, 4, 147),
(171, 4, 147),
(172, 4, 147),
(184, 4, 150),
(185, 4, 150),
(186, 4, 150),
(187, 4, 150),
(188, 4, 150),
(189, 4, 150),
(190, 4, 150),
(214, 2, 155),
(215, 3, 155),
(216, 3, 155),
(217, 4, 155),
(238, 16, 159),
(239, 4, 159),
(267, 1, 166),
(268, 17, 166),
(269, 17, 166),
(270, 4, 166),
(271, 4, 166),
(272, 4, 166),
(273, 4, 166),
(290, 16, 169),
(291, 17, 169),
(292, 17, 169),
(293, 17, 169),
(294, 4, 169),
(295, 4, 169),
(296, 4, 169),
(297, 16, 170),
(298, 17, 170),
(299, 17, 170),
(300, 17, 170),
(301, 4, 170),
(302, 4, 170),
(303, 4, 170),
(304, 4, 170),
(305, 4, 170),
(306, 1, 171),
(307, 18, 172),
(308, 18, 172),
(309, 18, 172),
(310, 18, 172),
(311, 18, 172),
(312, 18, 172),
(313, 18, 172),
(314, 18, 172),
(315, 18, 172),
(316, 18, 172),
(317, 17, 173),
(318, 17, 173),
(319, 4, 173),
(320, 17, 174),
(321, 17, 174),
(322, 17, 174),
(323, 4, 174),
(324, 4, 174),
(325, 17, 175),
(326, 17, 175),
(327, 17, 175),
(328, 4, 175),
(329, 4, 175),
(338, 19, 178),
(339, 7, 178),
(340, 8, 178),
(341, 4, 179),
(342, 4, 179),
(343, 4, 179),
(344, 4, 179),
(345, 4, 179),
(346, 4, 179),
(347, 22, 179);

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
(93, 'CLOSED', '2025-11-27 15:27:34', '2025-11-27T15:28:27.801050900', 26.49, 3.97, 30.46, 12.45),
(96, 'CLOSED', '2025-12-01 13:53:35', '2025-12-01T13:54:13.720769300', 28.99, 4.35, 37.69, 17.15),
(100, 'CLOSED', '2025-12-01 15:36:37', '2025-12-01T15:36:45.460218500', 3.00, 0.45, 3.45, 1.80),
(102, '\'OPEN\'', '2025-12-01 17:51:33', NULL, 0.00, 0.00, 0.00, 1.85),
(103, 'CLOSED', '2025-12-01 18:20:09', '2025-12-01T18:20:18.493029400', 3.00, 0.45, 4.05, 1.80),
(107, 'CLOSED', '2025-12-03 08:45:00', '2025-12-03T08:46:08.284004900', 15.49, 2.32, 20.91, 7.50),
(109, '\'OPEN\'', '2025-12-04 23:57:54', NULL, 0.00, 0.00, 0.00, 0.00),
(110, '\'OPEN\'', '2025-12-04 23:59:23', NULL, 0.00, 0.00, 0.00, 0.00),
(111, '\'OPEN\'', '2025-12-05 00:00:37', NULL, 0.00, 0.00, 0.00, 0.00),
(112, '\'OPEN\'', '2025-12-05 00:02:03', NULL, 0.00, 0.00, 0.00, 0.00),
(113, '\'OPEN\'', '2025-12-05 00:03:28', NULL, 11.75, 0.00, 0.00, 5.65),
(114, '\'OPEN\'', '2025-12-05 00:04:23', NULL, 11.75, 0.00, 0.00, 5.65),
(118, '\'OPEN\'', '2025-12-05 00:13:32', NULL, 11.75, 0.00, 0.00, 5.65),
(120, '\'OPEN\'', '2025-12-05 00:16:21', NULL, 11.75, 0.00, 0.00, 5.65),
(121, '\'OPEN\'', '2025-12-05 00:22:22', NULL, 11.75, 0.00, 0.00, 5.65),
(122, 'CLOSED', '2025-12-05 00:33:02', '2025-12-05T00:33:14.685784900', 10.74, 1.61, 14.50, 5.00),
(123, '\'OPEN\'', '2025-12-05 00:34:09', NULL, 0.00, 0.00, 0.00, 0.00),
(124, '\'OPEN\'', '2025-12-05 00:35:25', NULL, 0.00, 0.00, 0.00, 0.00),
(125, 'CLOSED', '2025-12-05 00:36:32', '2025-12-05T00:36:36.612236', 23.25, 3.49, 26.74, 11.70),
(126, '\'OPEN\'', '2025-12-05 16:22:57', NULL, 0.00, 0.00, 0.00, 0.00),
(127, '\'OPEN\'', '2025-12-05 16:23:00', NULL, 0.00, 0.00, 0.00, 0.00),
(128, '\'OPEN\'', '2025-12-05 16:26:54', NULL, 0.00, 0.00, 0.00, 0.00),
(129, '\'OPEN\'', '2025-12-05 16:31:09', NULL, 0.00, 0.00, 0.00, 0.00),
(130, '\'OPEN\'', '2025-12-05 16:35:14', NULL, 0.00, 0.00, 0.00, 0.00),
(131, '\'OPEN\'', '2025-12-05 16:37:05', NULL, 0.00, 0.00, 0.00, 0.00),
(132, '\'OPEN\'', '2025-12-05 16:41:18', NULL, 0.00, 0.00, 0.00, 0.00),
(135, '\'OPEN\'', '2025-12-05 16:44:00', NULL, 0.00, 0.00, 0.00, 0.00),
(143, '\'OPEN\'', '2025-12-05 16:59:54', NULL, 22.00, 0.00, 0.00, 0.00),
(145, '\'OPEN\'', '2025-12-05 17:13:43', NULL, 0.00, 0.00, 0.00, 0.00),
(146, '\'OPEN\'', '2025-12-05 17:13:44', NULL, 0.00, 0.00, 0.00, 0.00),
(147, '\'OPEN\'', '2025-12-05 17:13:46', NULL, 0.00, 0.00, 0.00, 0.00),
(150, '\'OPEN\'', '2025-12-05 17:29:36', NULL, 0.00, 0.00, 0.00, 0.00),
(155, 'CLOSED', '2025-12-05 17:34:21', '2025-12-05T17:34:35.362196800', 16.50, 2.48, 21.45, 0.00),
(159, '\'OPEN\'', '2025-12-05 18:16:40', NULL, 0.00, 0.00, 0.00, 0.00),
(166, '\'OPEN\'', '2025-12-05 18:37:18', NULL, 0.00, 0.00, 0.00, 0.00),
(169, 'CLOSED', '2025-12-05 18:42:35', '2025-12-05T18:42:40.990808600', 23.47, 3.52, 26.99, 0.00),
(170, 'CLOSED', '2025-12-05 18:46:24', '2025-12-05T18:46:32.421270700', 30.47, 4.57, 39.61, 14.50),
(171, 'CLOSED', '2025-12-05 18:46:55', '2025-12-05T18:47:00.820655400', 3.00, 0.45, 3.90, 1.80),
(172, 'CLOSED', '2025-12-05 20:59:42', '2025-12-05T20:59:59.125163', 15.00, 2.25, 17.25, 5.00),
(173, 'CLOSED', '2025-12-05 21:02:29', '2025-12-05T21:02:33.949397300', 9.48, 1.42, 10.90, 4.40),
(174, '\'OPEN\'', '2025-12-05 21:04:39', NULL, 15.97, 0.00, 0.00, 7.55),
(175, '\'OPEN\'', '2025-12-05 21:05:55', NULL, 15.97, 0.00, 0.00, 7.55),
(178, '\'OPEN\'', '2025-12-05 21:15:25', NULL, 0.00, 0.00, 0.00, 0.00),
(179, 'CLOSED', '2025-12-05 21:21:31', '2025-12-05T21:22:32.233503400', 26.75, 4.01, 34.77, 13.70);

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
  ADD PRIMARY KEY (`menuItem_id`);

--
-- Indexes for table `menu_item_ingredient`
--
ALTER TABLE `menu_item_ingredient`
  ADD PRIMARY KEY (`menu_item_id`,`inv_id`),
  ADD KEY `fk_menu_ingredient_inv` (`inv_id`) USING BTREE;

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
-- Indexes for table `sales_order_items`
--
ALTER TABLE `sales_order_items`
  ADD PRIMARY KEY (`sales_order_items_id`),
  ADD KEY `FK_menuItem_salesOrderItems_menuId` (`menu_item_id`),
  ADD KEY `FK_saleOrder_salesOrderItems_orderId` (`sales_order_id`);

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
  MODIFY `menuItem_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT for table `sales_order_items`
--
ALTER TABLE `sales_order_items`
  MODIFY `sales_order_items_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=348;

--
-- AUTO_INCREMENT for table `sale_order`
--
ALTER TABLE `sale_order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=180;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `menu_item_ingredient`
--
ALTER TABLE `menu_item_ingredient`
  ADD CONSTRAINT `fk_menu_ingredient_inv` FOREIGN KEY (`inv_id`) REFERENCES `inventory` (`invId`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_menu_ingredient_menuItem` FOREIGN KEY (`menu_item_id`) REFERENCES `menu_item` (`menuItem_id`) ON DELETE CASCADE;

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `FK_payment_saleOrder_orderID` FOREIGN KEY (`order_ID`) REFERENCES `sale_order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `popular_items`
--
ALTER TABLE `popular_items`
  ADD CONSTRAINT `FK_popularItems_menuItems_popularItemsID` FOREIGN KEY (`popular_items_id`) REFERENCES `menu_item` (`menuItem_id`) ON DELETE CASCADE;

--
-- Constraints for table `sales_order_items`
--
ALTER TABLE `sales_order_items`
  ADD CONSTRAINT `FK_menuItem_salesOrderItems_menuId` FOREIGN KEY (`menu_item_id`) REFERENCES `menu_item` (`menuItem_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_saleOrder_salesOrderItems_orderId` FOREIGN KEY (`sales_order_id`) REFERENCES `sale_order` (`order_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

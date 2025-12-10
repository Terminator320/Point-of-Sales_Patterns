-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2025 at 06:27 AM
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
(1, 'Espresso Beans', 4, 10),
(2, 'Milk', 0, 10),
(3, 'Foam Ingredients', 8, 10),
(4, 'Americano brewed ', 14, 10),
(5, 'Green Tea Leaves', 14, 10),
(6, 'Chai Mix', 14, 10),
(7, 'Croissants', 14, 15),
(8, 'Muffins', 14, 15),
(9, 'Cream Cheese', 13, 10),
(11, 'Yellow Cheese', 14, 10),
(13, 'Berry Mix', 13, 10),
(14, 'Mango Mix', 14, 10),
(15, 'Protein Shake Mix', 14, 10),
(16, 'Brewed Coffee', 14, 15),
(17, 'Instant Coffee', 15, 15),
(18, 'Black Tea leaves', 14, 10),
(19, 'Oolong Tea leaves', 114, 15),
(20, 'Dried Peach Pieces', 14, 10),
(21, 'Dried Strawberries', 14, 15),
(22, 'Strawberry Mix', 9, 10),
(24, 'Bread Slice', 11, 10),
(25, 'Bacon Slice', 13, 10),
(26, 'Lettuce Leaf', 14, 10),
(27, 'Tomato Slice', 15, 10),
(28, 'Mayonnaise', 15, 5),
(29, 'Tortilla Wrap', 14, 10),
(30, 'Grilled Chicken', 13, 10),
(31, 'Shredded Cheese', 12, 10),
(32, 'Wrap Sauce', 13, 5),
(33, 'Bagel', 14, 15);

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
(22, 'Strawberry Smoothie', 5.75, 2.3);

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
(9, 9, 2),
(9, 33, 1),
(10, 24, 2),
(10, 25, 2),
(10, 26, 1),
(11, 11, 1),
(11, 24, 2),
(12, 29, 1),
(12, 30, 2),
(12, 31, 3),
(12, 32, 2),
(13, 2, 1),
(13, 13, 1),
(14, 2, 1),
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
(22, 2, 1),
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
(96, 235, 'CREDIT', 2.35, '2025-12-09 22:11:21'),
(97, 236, 'DEBIT', 14.20, '2025-12-09 22:15:57'),
(98, 237, 'CREDIT', 0.30, '2025-12-09 22:18:19'),
(99, 238, 'DEBIT', 0.00, '2025-12-09 22:19:33'),
(100, 241, 'DEBIT', 2.02, '2025-12-09 22:29:03'),
(101, 242, 'CREDIT', 14.32, '2025-12-09 23:20:56');

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
(1, 'Espresso', 4),
(2, 'Latte', 4),
(3, 'Cappuccino', 3),
(4, 'Ice Americano', 1),
(5, 'Green Tea', 1),
(6, 'Chai Latte', 1),
(7, 'Croissant', 1),
(8, 'Muffin', 16),
(9, 'Bagel with Cream Cheese', 1),
(10, 'BLT Sandwich', 1),
(11, 'Grilled Cheese', 1),
(12, 'Chicken Wrap', 1),
(13, 'Berry Smoothie', 2),
(14, 'Mango Smoothie', 1),
(15, 'Protein Shake', 1),
(16, 'Greek Frappe', 0),
(17, 'Ice Coffee', 3),
(18, 'Black Tea', 1),
(19, 'Oolong Tea', 3),
(20, 'Peach Tea', 1),
(21, 'Strawberry Tea', 1),
(22, 'Strawberry Smoothie', 6);

-- --------------------------------------------------------

--
-- Table structure for table `sales_order_items`
--

CREATE TABLE `sales_order_items` (
  `sales_order_items_id` int(11) NOT NULL,
  `menu_item_id` int(11) NOT NULL,
  `sales_order_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sales_order_items`
--

INSERT INTO `sales_order_items` (`sales_order_items_id`, `menu_item_id`, `sales_order_id`, `quantity`) VALUES
(546, 1, 235, 1),
(547, 2, 235, 1),
(548, 3, 235, 1),
(549, 17, 236, 1),
(550, 2, 236, 1),
(551, 19, 236, 1),
(552, 22, 236, 4),
(553, 8, 236, 15),
(554, 1, 237, 1),
(555, 13, 238, 1),
(557, 1, 240, 1),
(558, 1, 241, 1),
(559, 17, 241, 1),
(560, 2, 241, 1),
(561, 19, 241, 1),
(562, 1, 242, 1),
(563, 2, 242, 1),
(564, 3, 242, 2),
(565, 4, 242, 1),
(566, 5, 242, 1),
(567, 6, 242, 1),
(568, 7, 242, 1),
(569, 8, 242, 1),
(570, 9, 242, 1),
(571, 10, 242, 1),
(572, 11, 242, 1),
(573, 12, 242, 1),
(574, 13, 242, 1),
(575, 14, 242, 1),
(576, 15, 242, 1),
(577, 17, 242, 1),
(578, 18, 242, 1),
(579, 19, 242, 1),
(580, 20, 242, 1),
(581, 21, 242, 1),
(582, 22, 242, 2);

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
(235, 'CLOSED', '2025-12-09 22:05:30', '2025-12-09T22:11:22.068915200', 11.75, 1.76, 15.86, 11.75),
(236, 'CLOSED', '2025-12-09 22:13:23', '2025-12-09T22:15:57.689185600', 70.99, 10.65, 95.84, 70.99),
(237, 'CLOSED', '2025-12-09 22:17:51', '2025-12-09T22:18:19.379132300', 3.00, 0.45, 3.75, 3.00),
(238, 'CLOSED', '2025-12-09 22:19:13', '2025-12-09T22:19:33.608103', 5.75, 0.86, 6.61, 5.75),
(240, '\'OPEN\'', '2025-12-09 22:23:48', NULL, 0.00, 0.00, 0.00, 0.00),
(241, 'CLOSED', '2025-12-09 22:25:57', '2025-12-09T22:29:03.471710', 13.49, 2.02, 17.54, 13.49),
(242, 'CLOSED', '2025-12-09 23:19:12', '2025-12-09T23:20:56.886282100', 95.49, 14.32, 124.14, 95.49);

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
  ADD KEY `fk_menu_ingredient_inv` (`inv_id`) USING BTREE,
  ADD KEY `idx_mii_menuitem` (`menu_item_id`);

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
  MODIFY `invId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `menu_item`
--
ALTER TABLE `menu_item`
  MODIFY `menuItem_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT for table `sales_order_items`
--
ALTER TABLE `sales_order_items`
  MODIFY `sales_order_items_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=583;

--
-- AUTO_INCREMENT for table `sale_order`
--
ALTER TABLE `sale_order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=243;

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

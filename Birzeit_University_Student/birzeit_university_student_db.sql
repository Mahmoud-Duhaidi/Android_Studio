-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 10, 2024 at 10:24 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `birzeit_university_student_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `cafeterianame`
--

CREATE TABLE `cafeterianame` (
  `cafeteriaId` int(11) NOT NULL,
  `cafeteriaName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cafeterianame`
--

INSERT INTO `cafeterianame` (`cafeteriaId`, `cafeteriaName`) VALUES
(1, 'College of Science cafeteria'),
(2, 'College of Business and Economics cafeteria'),
(3, 'Almahata'),
(4, 'Tob shawirma'),
(5, 'Nutella Shop'),
(6, 'Snaks'),
(7, 'College of Arts cafeteria');

-- --------------------------------------------------------

--
-- Table structure for table `major`
--

CREATE TABLE `major` (
  `major_name` varchar(255) NOT NULL,
  `major_cost_per_hour` double NOT NULL,
  `major_image` varchar(255) NOT NULL,
  `faculiteis` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `major`
--

INSERT INTO `major` (`major_name`, `major_cost_per_hour`, `major_image`, `faculiteis`) VALUES
('Art-Bachelor Design', 95, 'art_bachelor_design', 'Art-Bachelor'),
('Art-Bachelor Interior Design', 95, 'art_bachelor_interiordesign', 'Art-Bachelor'),
('Business Economics-Bachelor Accounting', 50, 'businesseconomics_bachelor_accounting', 'BusinessEconomics-Bachelor'),
('Business Economics-Bachelor Economics', 50, 'businesseconomics_bachelor_economics', 'BusinessEconomics-Bachelor'),
('Business Economics-Bachelor Finance', 50, 'businesseconomics_bachelor_finance', 'BusinessEconomics-Bachelor'),
('Business Economics-Bachelor_BusinessEconomics', 50, 'businesseconomics_bachelor_businesseconomics', 'BusinessEconomics-Bachelor'),
('Education-Bachelor English Language', 50, 'education_bachelor_englishlanguage', 'Education-Bachelor'),
('Education-Bachelor Sport', 42, 'education_bachelor_sport', 'Education-Bachelor'),
('Engineering-Technology-Bachelor Computer Engineering', 50, 'engineeringtechnology_bachelor_computerengineering', 'Engineering-Technology-Bachelor'),
('Engineering-Technology-Bachelor Computer Science', 45, 'engineeringtechnology_bachelor_computerscience', 'Engineering-Technology-Bachelor'),
('Engineering-Technology-Bachelor Information Security', 55, 'engineeringtechnology_bachelor_information_security', 'Engineering-Technology-Bachelor'),
('law-Bachelor public administration', 45, 'law_bachelor_public_administration', 'law-Bachelor'),
('Literature-Bachelor Geoinformatics', 50, 'literature_bachelor_geoinformatics', 'Literature-Bachelor'),
('Nursing-Bachelor Nursing', 50, 'nursing_bachelor_nursing', 'Nursing-Bachelor'),
('Nursing-Bachelor Pharmacist', 85, 'nursing_bachelor_pharmacist', 'Nursing-Bachelor'),
('Science-Bachelor Physics', 40, 'science_bachelor_physics', 'Science-Bachelor');

-- --------------------------------------------------------

--
-- Table structure for table `orderitem`
--

CREATE TABLE `orderitem` (
  `productId` int(11) NOT NULL,
  `stud_id` int(11) NOT NULL,
  `qun` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orderitem`
--

INSERT INTO `orderitem` (`productId`, `stud_id`, `qun`) VALUES
(1, 1240002, 1),
(2, 1240002, 1),
(3, 1240002, 1),
(28, 1240002, 1),
(35, 1240003, 1),
(37, 1240003, 1),
(95, 1240001, 2),
(96, 1240001, 8),
(184, 1240002, 5),
(185, 1240002, 2),
(200, 1240002, 1),
(250, 1240002, 1);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productId` int(11) NOT NULL,
  `cafeteriaId` int(11) DEFAULT NULL,
  `productName` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `size` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productId`, `cafeteriaId`, `productName`, `category`, `size`, `price`) VALUES
(1, 1, 'Shawarma shrak', 'Hot sandwiches', 'medium', 12.00),
(2, 1, 'Normal shawarma', 'Hot sandwiches', 'medium', 10.00),
(3, 1, 'Mashab Chicken (Kmaj)', 'Hot sandwiches', 'medium', 8.00),
(4, 1, 'Mashab chicken (baguette)', 'Hot sandwiches', 'medium', 10.00),
(5, 1, 'Chicken breast steak (baguette)', 'Hot sandwiches', 'medium', 10.00),
(6, 1, 'Schnitzel chicken breast (baguette)', 'Hot sandwiches', 'medium', 10.00),
(7, 1, 'Normal schnitzel (baguette)', 'Hot sandwiches', 'medium', 6.00),
(8, 1, 'Hot dog', 'Hot sandwiches', 'medium', 6.00),
(9, 1, 'Hot dog + yellow cheese', 'Hot sandwiches', 'medium', 7.00),
(10, 1, 'White cheese toast', 'Hot sandwiches', 'medium', 5.00),
(11, 1, 'Potato sandwich', 'Hot sandwiches', 'medium', 4.00),
(12, 1, 'Mortadella with labneh', 'Cold sandwiches', 'medium', 4.00),
(13, 1, 'Turkey pastramih', 'Cold sandwiches', 'medium', 5.00),
(14, 1, 'Pastrami with yellow cheese', 'Cold sandwiches', 'medium', 6.00),
(15, 1, 'Pastrami with labneh', 'Cold sandwiches', 'medium', 6.00),
(16, 1, 'White cheese', 'Pastries', 'medium', 4.00),
(17, 1, 'Yellow cheese', 'Pastries', 'medium', 4.00),
(18, 1, 'White cheese with zaetar', 'Pastries', 'medium', 4.00),
(19, 1, 'spinach', 'Pastries', 'medium', 4.00),
(20, 1, 'Pizza', 'Pastries', 'medium', 4.00),
(21, 1, 'Egg quris', 'Pastries', 'medium', 4.00),
(22, 1, 'Egg quris with cheese', 'Pastries', 'medium', 5.00),
(23, 1, 'Egg quris with hot dog', 'Pastries', 'medium', 5.00),
(24, 1, 'Tomato safiha', 'Pastries', 'medium', 4.00),
(25, 1, 'Tahini safiha', 'Pastries', 'medium', 4.00),
(26, 1, 'Zaetar manakish', 'Pastries', 'medium', 3.00),
(27, 1, 'Mineral water', 'Cold drinks', 'medium', 2.00),
(28, 1, 'Coca-Cola, Cappy, Fanta, Sprite', 'Cold drinks', 'medium', 2.50),
(29, 1, 'XL', 'Cold drinks', 'medium', 4.00),
(30, 1, 'Milk up, fruit up', 'Cold drinks', 'medium', 3.00),
(31, 1, 'Ice Coffee', 'Cold drinks', 'medium', 3.50),
(32, 1, 'Tea, Arabic coffee,zahurat', 'Hot drinks', 'medium', 2.00),
(33, 1, 'Nescafe', 'Hot drinks', 'medium', 2.00),
(34, 1, 'Cappuccino', 'Hot drinks', 'medium', 2.00),
(35, 2, 'Hot dog', 'Hot sandwiches', 'medium', 5.00),
(36, 2, 'Falafel - Shrak', 'Hot sandwiches', 'medium', 5.00),
(37, 2, 'Mixed pastries', 'Hot sandwiches', 'medium', 5.00),
(38, 2, 'White sauce', 'Hot sandwiches', 'medium', 12.00),
(39, 2, 'Chinese mashab', 'Hot sandwiches', 'medium', 10.00),
(40, 2, 'Italian mashab', 'Hot sandwiches', 'medium', 12.00),
(41, 2, 'Schnitzel', 'Hot sandwiches', 'medium', 7.00),
(42, 2, 'Zinger', 'Hot sandwiches', 'medium', 7.00),
(43, 2, 'Potatoes - can', 'Hot sandwiches', 'medium', 5.00),
(44, 2, 'Hamburger', 'Hot sandwiches', 'medium', 10.00),
(45, 2, 'Shawarma', 'Hot sandwiches', 'medium', 12.00),
(46, 2, 'Mortadella with labneh', 'Cold sandwiches', 'medium', 4.00),
(47, 2, 'Pastrami', 'Cold sandwiches', 'medium', 5.00),
(48, 2, 'Habash', 'Cold sandwiches', 'medium', 5.00),
(49, 2, 'Roast beef', 'Cold sandwiches', 'medium', 6.00),
(50, 2, 'Salami', 'Cold sandwiches', 'medium', 6.00),
(51, 2, 'Almarai fat cheese or puck', 'Cold sandwiches', 'medium', 5.00),
(52, 2, 'White cheese', 'Cold sandwiches', 'medium', 5.00),
(53, 2, 'Labana', 'Cold sandwiches', 'medium', 4.00),
(54, 2, 'Hummus', 'Cold sandwiches', 'medium', 4.00),
(55, 2, 'Tuna', 'Cold sandwiches', 'medium', 6.00),
(56, 2, 'Mixed', 'Cold sandwiches', 'medium', 8.00),
(57, 2, 'Salad box', 'Cold sandwiches', 'medium', 5.00),
(58, 2, 'Mineral water', 'Cold drinks', 'medium', 2.00),
(59, 2, 'Coca-Cola, Cappy, Fanta, Sprite', 'Cold drinks', 'medium', 2.50),
(60, 2, 'XL', 'Cold drinks', 'medium', 4.00),
(61, 2, 'Milk up, fruit up', 'Cold drinks', 'medium', 3.00),
(62, 2, 'Ice Coffee', 'Cold drinks', 'medium', 5.00),
(63, 2, 'Slash', 'Cold drinks', 'medium', 5.00),
(64, 2, 'Talin cup', 'Hot drinks', 'medium', 3.00),
(65, 7, 'Fajita Sandwich (Veal Fillet)', 'Sandwiches and Pastries', 'medium', 14.00),
(66, 7, 'Spicy chicken zinger sandwich', 'Sandwiches and Pastries', 'medium', 12.00),
(67, 7, 'Chicken finjar sandwich', 'Sandwiches and Pastries', 'medium', 12.00),
(68, 7, 'Thai chicken sandwich', 'Sandwiches and Pastries', 'medium', 12.00),
(69, 7, 'Shish tawouk sandwich', 'Sandwiches and Pastries', 'medium', 12.00),
(70, 7, 'Normal schnitzel sandwich', 'Sandwiches and Pastries', 'medium', 7.00),
(71, 7, 'Schnitzel sandwich aishkal', 'Sandwiches and Pastries', 'medium', 7.00),
(72, 7, 'Hot dog sandwich', 'Sandwiches and Pastries', 'medium', 7.00),
(73, 7, 'Mexican chicken sandwich', 'Sandwiches and Pastries', 'medium', 12.00),
(74, 7, 'Chicken grill sandwich', 'Sandwiches and Pastries', 'medium', 12.00),
(75, 7, 'Chicken sandwich with barbecue sauce', 'Sandwiches and Pastries', 'medium', 12.00),
(76, 7, 'Grilled chicken sandwich with cheese', 'Sandwiches and Pastries', 'medium', 12.00),
(77, 7, 'Hamburger sandwich', 'Sandwiches and Pastries', 'medium', 12.00),
(78, 7, 'Cheeseburger sandwich', 'Sandwiches and Pastries', 'medium', 14.00),
(79, 7, 'Chicken burger sandwich', 'Sandwiches and Pastries', 'medium', 8.00),
(80, 7, 'Puck cheese sandwich with olives', 'Sandwiches and Pastries', 'medium', 7.00),
(81, 7, 'Labneh sandwich with walnuts', 'Sandwiches and Pastries', 'medium', 7.00),
(82, 7, 'Italian salami sandwich', 'Sandwiches and Pastries', 'medium', 8.00),
(83, 7, 'Turkey sandwich', 'Sandwiches and Pastries', 'medium', 8.00),
(84, 7, 'Tuna sandwich', 'Sandwiches and Pastries', 'medium', 8.00),
(85, 7, 'BBQ turkey sandwich', 'Sandwiches and Pastries', 'medium', 8.00),
(86, 7, 'Roast beef sandwich', 'Sandwiches and Pastries', 'medium', 8.00),
(87, 7, 'Bulgarian cheese sandwich', 'Sandwiches and Pastries', 'medium', 8.00),
(88, 7, 'White cheese sandwich', 'Sandwiches and Pastries', 'medium', 8.00),
(89, 7, 'Bulgarian labneh and BBQ sandwich', 'Sandwiches and Pastries', 'medium', 14.00),
(90, 7, 'Potatoes box', 'Sandwiches and Pastries', 'medium', 7.00),
(91, 7, 'Meal fanujariz', 'Sandwiches and Pastries', 'medium', 15.00),
(92, 7, 'Meal (fanujariz - potatoes - salad)', 'Sandwiches and Pastries', 'medium', 20.00),
(93, 7, 'Large salad box', 'Sandwiches and Pastries', 'medium', 12.00),
(94, 7, 'Pastries (white cheese/yellow cheese/eggs/zaiter/meat/spinach)', 'Sandwiches and Pastries', 'medium', 7.00),
(95, 7, 'Pizza a piece', 'Sandwiches and Pastries', 'medium', 7.00),
(96, 7, 'Pizza', 'Sandwiches and Pastries', 'small', 35.00),
(97, 7, 'Pizza', 'Sandwiches and Pastries', 'medium', 45.00),
(98, 7, 'Pizza', 'Sandwiches and Pastries', 'large', 55.00),
(99, 7, 'Mineral water', 'Drinks', 'medium', 2.00),
(100, 7, 'coca cola', 'Drinks', 'medium', 3.00),
(101, 7, 'juice', 'Drinks', 'medium', 3.00),
(102, 7, 'Cappy juice', 'Drinks', 'medium', 3.00),
(103, 7, 'XL', 'Drinks', 'medium', 5.00),
(104, 7, 'Fayrouz juice', 'Drinks', 'medium', 5.00),
(105, 7, 'coffee', 'Drinks', 'medium', 2.00),
(106, 7, 'tea', 'Drinks', 'medium', 2.00),
(107, 7, 'Nescafe', 'Drinks', 'medium', 3.00),
(108, 7, 'zahawrat', 'Drinks', 'medium', 2.00),
(109, 7, 'babunj', 'Drinks', 'medium', 2.00),
(110, 7, 'Claire', 'Sweets', 'medium', 3.00),
(111, 7, 'A piece of crepe', 'Sweets', 'medium', 12.00),
(112, 7, 'Sweet pastries', 'Sweets', 'medium', 4.00),
(113, 7, 'Donuts', 'Sweets', 'medium', 5.00),
(114, 7, 'Waffles', 'Sweets', 'medium', 12.00),
(115, 7, 'A piece of fruit', 'Sweets', 'medium', 1.50),
(116, 3, 'Espresso', 'Espresso', 'medium', 8.00),
(117, 3, 'Macchiato', 'Espresso', 'medium', 10.00),
(118, 3, 'Konbana', 'Espresso', 'medium', 12.00),
(119, 3, 'Americano', 'Coffee drinks', 'medium', 6.00),
(120, 3, 'Americano', 'Coffee drinks', 'large', 8.00),
(121, 3, 'cappuccino', 'Coffee drinks', 'large', 13.00),
(122, 3, 'cappuccino', 'Coffee drinks', 'medium', 10.00),
(123, 3, 'Latte', 'Coffee drinks', 'medium', 10.00),
(124, 3, 'Latte', 'Coffee drinks', 'large', 13.00),
(125, 3, 'Cortado', 'Coffee drinks', 'medium', 10.00),
(126, 3, 'Flat white', 'Coffee drinks', 'medium', 10.00),
(127, 3, 'Mocha', 'Coffee drinks', 'medium', 12.00),
(128, 3, 'Mocha', 'Coffee drinks', 'large', 14.00),
(129, 3, 'Lavender latte', 'Flavored latte', 'large', 14.00),
(130, 3, 'Lavender latte', 'Flavored latte', 'medium', 12.00),
(131, 3, 'Lotus', 'Flavored latte', 'medium', 12.00),
(132, 3, 'Lotus', 'Flavored latte', 'large', 14.00),
(133, 3, 'Caramel', 'Flavored latte', 'large', 14.00),
(134, 3, 'Caramel', 'Flavored latte', 'medium', 12.00),
(135, 3, 'Hazelnut', 'Flavored latte', 'medium', 12.00),
(136, 3, 'Hazelnut', 'Flavored latte', 'large', 14.00),
(137, 3, 'Vanilla', 'Flavored latte', 'large', 14.00),
(138, 3, 'Vanilla', 'Flavored latte', 'medium', 12.00),
(139, 3, 'herbal tea', 'Tea', 'medium', 8.00),
(140, 3, 'Black tea', 'Tea', 'medium', 5.00),
(141, 3, 'Caffeine-free tea', 'Tea', 'medium', 5.00),
(142, 3, 'Green tea', 'Tea', 'medium', 5.00),
(143, 3, 'Tea latte', 'Other', 'medium', 13.00),
(144, 3, 'hot chocolate', 'Other', 'medium', 10.00),
(145, 3, 'hot chocolate', 'Other', 'large', 12.00),
(146, 3, 'Hot mint chocolate', 'Other', 'large', 13.00),
(147, 3, 'Hot mint chocolate', 'Other', 'medium', 11.00),
(148, 3, 'French vanilla', 'Other', 'medium', 12.00),
(149, 3, 'French hazelnut', 'Other', 'medium', 12.00),
(150, 3, 'Lotus milk', 'Other', 'medium', 12.00),
(151, 3, 'Dates and banana', 'Smoothie', 'medium', 14.00),
(152, 3, 'Strawberry and banana', 'Smoothie', 'medium', 14.00),
(153, 3, 'Mango and orange', 'Smoothie', 'medium', 14.00),
(154, 3, 'biriz mshkkla', 'Smoothie', 'medium', 14.00),
(155, 3, 'orange', 'fresh juice', 'medium', 12.00),
(156, 3, 'Carrot', 'fresh juice', 'medium', 12.00),
(157, 3, 'Orange and carrots', 'fresh juice', 'medium', 12.00),
(158, 3, 'Red juice', 'fresh juice', 'medium', 16.00),
(159, 3, 'Lemonade with mint', 'fresh juice', 'medium', 10.00),
(160, 3, 'Pink lemonade', 'fresh juice', 'medium', 11.00),
(161, 3, 'Ice Americano', 'Iced drinks', 'medium', 7.00),
(162, 3, 'Ice Americano', 'Iced drinks', 'large', 9.00),
(163, 3, 'Ice cappuccino', 'Iced drinks', 'large', 13.00),
(164, 3, 'Ice cappuccino', 'Iced drinks', 'medium', 10.00),
(165, 3, 'Ice latte', 'Iced drinks', 'medium', 10.00),
(166, 3, 'Ice latte', 'Iced drinks', 'large', 13.00),
(167, 3, 'Iced coffee', 'Iced drinks', 'medium', 12.00),
(168, 3, 'Iced coffee', 'Iced drinks', 'large', 14.00),
(169, 3, 'Diet iced coffee', 'Iced drinks', 'large', 14.00),
(170, 3, 'Diet iced coffee', 'Iced drinks', 'medium', 12.00),
(171, 3, 'Iced chocolate', 'Iced drinks', 'medium', 10.00),
(172, 3, 'Iced chocolate', 'Iced drinks', 'large', 13.00),
(173, 3, 'Iced vanilla', 'Iced drinks', 'large', 13.00),
(174, 3, 'Iced vanilla', 'Iced drinks', 'medium', 10.00),
(175, 3, 'Lavender ice tea', 'Smoothie', 'medium', 10.00),
(176, 3, 'Ice tea lemon and mint', 'Smoothie', 'medium', 11.00),
(177, 3, 'Cola', 'Soft drinks', 'medium', 3.00),
(178, 3, 'Diet cola', 'Soft drinks', 'medium', 3.00),
(179, 3, 'Zero', 'Soft drinks', 'medium', 3.00),
(180, 3, 'Sprite', 'Soft drinks', 'medium', 3.00),
(181, 3, 'Sprite diet', 'Soft drinks', 'medium', 3.00),
(182, 3, 'Fanta', 'Soft drinks', 'medium', 3.00),
(183, 3, 'Soda water', 'Soft drinks', 'medium', 7.00),
(184, 3, 'water', 'Soft drinks', 'small', 2.00),
(185, 3, 'Laban Up', 'Soft drinks', 'medium', 4.00),
(186, 3, 'Blueberry', 'Italian soda', 'medium', 10.00),
(187, 3, 'mes flora', 'Italian soda', 'medium', 10.00),
(188, 3, 'Curacao', 'Italian soda', 'medium', 10.00),
(189, 3, 'Grenadine', 'Italian soda', 'medium', 10.00),
(190, 3, 'Chocolate chip biscuits', 'Food', 'medium', 5.00),
(191, 3, 'Oat biscuits', 'Food', 'medium', 6.00),
(192, 3, 'Orange and vanilla cake', 'Food', 'medium', 6.00),
(193, 3, 'Chocolate cake', 'Food', 'medium', 8.00),
(194, 3, 'Brownie', 'Food', 'medium', 7.00),
(195, 3, 'Granola & milk', 'Food', 'medium', 14.00),
(196, 3, 'Caesar sandwich', 'Food', 'medium', 17.00),
(197, 3, 'Italian sandwich', 'Food', 'medium', 16.00),
(198, 3, 'Pizza', 'Food', 'medium', 15.00),
(199, 3, 'Greek salad', 'Food', 'medium', 15.00),
(200, 3, 'Italian pasta salad', 'Food', 'medium', 18.00),
(201, 3, 'Nawaem', 'Food', 'medium', 1.00),
(202, 3, 'Tortilla with white cheese', 'Food', 'medium', 5.00),
(203, 3, 'Cheese and mortadella tortilla', 'Food', 'medium', 5.00),
(204, 3, 'Cheese borex', 'Food', 'medium', 2.00),
(205, 3, 'Spinach borex', 'Food', 'medium', 2.00),
(206, 3, 'Potato borex', 'Food', 'medium', 2.00),
(207, 3, 'Nachos', 'Food', 'medium', 12.00),
(208, 3, 'Nachos extra', 'Food', 'medium', 15.00),
(209, 3, 'Lentil soup', 'Food', 'medium', 6.00),
(210, 3, 'Soup of the day', 'Food', 'medium', 8.00),
(211, 3, 'Fried potatoes', 'Food', 'medium', 6.00),
(212, 3, 'French fries with cheese', 'Food', 'medium', 8.00),
(213, 3, 'Roasted potatoes with butter and cream', 'Food', 'medium', 10.00),
(214, 3, 'Grilled potatoes with chicken and mushroom sauce', 'Food', 'medium', 8.00),
(215, 3, 'Vegetable teriyaki rice', 'Food', 'medium', 13.00),
(216, 3, 'Teriyaki rice, vegetables and chicken', 'Food', 'medium', 15.00),
(217, 3, 'Vegetable teriyaki noodles', 'Food', 'medium', 13.00),
(218, 3, 'Teriyaki noodles chicken vegetables', 'Food', 'medium', 15.00),
(219, 3, 'Lasagna', 'Food', 'medium', 16.00),
(220, 3, 'Todays meal', 'Food', 'medium', 17.00),
(221, 4, 'Shawarma shrak', 'Food', 'medium', 15.00),
(222, 4, 'Shawarma baguette', 'Food', 'small', 15.00),
(223, 4, 'Arabic shawarma meal', 'Food', 'medium', 20.00),
(224, 4, 'Pashka', 'Food', 'medium', 27.00),
(225, 4, 'Shawarma plate', 'Food', 'medium', 18.00),
(226, 4, 'potato', 'Food', 'medium', 6.00),
(227, 4, 'potato with chesees', 'Food', 'medium', 8.00),
(228, 4, 'Cola', 'Drinks', 'medium', 3.00),
(229, 4, 'juice', 'Drinks', 'medium', 3.00),
(230, 4, 'Water', 'Drinks', 'small', 2.00),
(231, 4, 'XL', 'Drinks', 'medium', 4.00),
(232, 5, 'Waffle piece', 'Sweets', 'medium', 6.00),
(233, 5, 'Two waffle pieces with ice cream', 'Sweets', 'medium', 14.00),
(234, 5, 'Nutella pizza', 'Sweets', 'medium', 15.00),
(235, 5, 'Triple waffle', 'Sweets', 'medium', 20.00),
(236, 5, 'Nutella pancake', 'Sweets', 'medium', 10.00),
(237, 5, 'Pancake cup', 'Sweets', 'small', 15.00),
(238, 5, 'Mixed balls 12 pieces', 'Sweets', 'small', 10.00),
(239, 5, 'Mixed balls 24 pieces', 'Sweets', 'small', 20.00),
(240, 5, 'Lotus cake', 'Sweets', 'medium', 8.00),
(241, 5, 'Oreo cheesecake', 'Sweets', 'medium', 8.00),
(242, 5, 'Nutella souffle', 'Sweets', 'medium', 10.00),
(243, 5, 'Nutella brownie', 'Sweets', 'medium', 10.00),
(244, 5, 'Tricolada', 'Sweets', 'medium', 8.00),
(245, 5, 'Nutella mousse', 'Sweets', 'medium', 8.00),
(246, 5, 'Nutella cheesecake', 'Sweets', 'medium', 8.00),
(247, 5, 'crepe', 'Sweets', 'medium', 8.00),
(248, 5, 'Crepeout', 'Sweets', 'medium', 10.00),
(249, 5, 'Fettuccine', 'Sweets', 'medium', 15.00),
(250, 6, 'MIX FRIES ( ZIKZAK + FRENCH + STEAK)', 'Food', 'medium', 6.00),
(251, 6, 'MIX FRIES ( ZIKZAK + FRENCH + STEAK)', 'Food', 'large', 10.00),
(252, 6, 'CRISPY FRIES', 'Food', 'medium', 8.00),
(253, 6, 'CRISPY FRIES', 'Food', 'large', 12.00),
(254, 6, 'SWEAT FRIES', 'Food', 'medium', 12.00),
(255, 6, 'SWEAT FRIES', 'Food', 'large', 19.00),
(256, 6, 'PUFFS FRIES', 'Food', 'large', 14.00),
(257, 6, 'CURLY FRIES', 'Food', 'medium', 8.00),
(258, 6, 'CURLY FRIES', 'Food', 'large', 12.00),
(259, 6, 'WEDGES FRIES', 'Food', 'medium', 11.00),
(260, 6, 'WEDGES FRIES', 'Food', 'large', 15.00),
(261, 6, 'CRISSCUT FRIES', 'Food', 'large', 14.00),
(262, 6, 'ONION RINGS', 'Food', 'large', 15.00),
(263, 6, 'CHICKEN STROGANOFF FRIES', 'Food', 'medium', 16.00),
(264, 6, 'CHICKEN PESTO FRIES', 'Food', 'medium', 16.00),
(265, 6, 'BONLESS CHICKEN FRIES', 'Food', 'medium', 16.00),
(266, 6, 'ZINGER FRIES', 'Food', 'medium', 16.00),
(267, 6, 'DYNAMITE CHICKEN FRIES', 'Food', 'medium', 16.00),
(268, 6, 'BURGER FRIES', 'Food', 'medium', 18.00),
(269, 6, 'TRIPLR CHEEZE FRIES', 'Food', 'medium', 15.00),
(270, 6, 'HOT DOG', 'Food', 'medium', 10.00);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `stud_id` int(11) NOT NULL,
  `stud_name` varchar(255) NOT NULL,
  `stud_email` varchar(255) NOT NULL,
  `stud_password` varchar(255) NOT NULL,
  `stud_major` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`stud_id`, `stud_name`, `stud_email`, `stud_password`, `stud_major`) VALUES
(1240000, 'yousef sharbi', 'yousef@student.birzeit.edu', '123456', 'Engineering-Technology-Bachelor Computer Science'),
(1240001, 'Anas', 'Anas@student.birzeit.edu', '123456', 'Engineering-Technology-Bachelor Computer Science'),
(1240002, 'salah123', 'salah123@student.birzeit.edu', '123456789', 'Nursing-Bachelor Nursing'),
(1240003, 'mahmoud', 'mahmoud@student.birzeit.edu', '123456', 'Art-Bachelor Design');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `task_id` int(11) NOT NULL,
  `task_title` varchar(255) NOT NULL,
  `task_description` varchar(255) NOT NULL,
  `task_date` varchar(255) NOT NULL,
  `task_time` varchar(255) NOT NULL,
  `task_status` varchar(255) NOT NULL,
  `stud_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`task_id`, `task_title`, `task_description`, `task_date`, `task_time`, `task_status`, `stud_id`) VALUES
(0, 'AI quiz1', 'BFS and DFS', '2024 / Jan / 8', '03:00 AM  ', 'Done', 1240000),
(4, 'hgiugiu', 'hkbkjb', '2026 / Apr / 3', '06:03  PM', 'Due', 1240000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cafeterianame`
--
ALTER TABLE `cafeterianame`
  ADD PRIMARY KEY (`cafeteriaId`);

--
-- Indexes for table `major`
--
ALTER TABLE `major`
  ADD PRIMARY KEY (`major_name`);

--
-- Indexes for table `orderitem`
--
ALTER TABLE `orderitem`
  ADD PRIMARY KEY (`productId`,`stud_id`),
  ADD KEY `stud_id` (`stud_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productId`),
  ADD KEY `cafeteriaId` (`cafeteriaId`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`stud_id`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`task_id`),
  ADD KEY `task_id` (`stud_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cafeterianame`
--
ALTER TABLE `cafeterianame`
  MODIFY `cafeteriaId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `productId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=271;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `stud_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1240005;

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`cafeteriaId`) REFERENCES `cafeterianame` (`cafeteriaId`);

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_id` FOREIGN KEY (`stud_id`) REFERENCES `student` (`stud_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Φιλοξενητής: 127.0.0.1
-- Χρόνος δημιουργίας: 10 Ιουλ 2021 στις 22:19:19
-- Έκδοση διακομιστή: 10.4.14-MariaDB
-- Έκδοση PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Βάση δεδομένων: `sofia4`
--

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `asset`
--

CREATE TABLE `asset` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `asset_entity_id` bigint(20) DEFAULT NULL,
  `entity_id` bigint(20) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `description2` varchar(500) DEFAULT NULL,
  `asset_repository_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `asset`
--

INSERT INTO `asset` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `name`, `asset_entity_id`, `entity_id`, `description`, `description2`, `asset_repository_id`) VALUES
(41, NULL, '2021-03-22 14:46:10', NULL, '2021-03-22 14:46:10', NULL, 1, 'asset code1', 'asset name', NULL, NULL, NULL, NULL, NULL),
(42, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, 3, 'asset code test 42', 'asset name test 42', NULL, NULL, NULL, NULL, NULL),
(43, NULL, '2021-04-01 06:39:37', NULL, '2021-04-01 06:39:37', NULL, NULL, 'asset code test', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(44, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, NULL, 'asset code test', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(45, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, 2, 'asset code test', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(46, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, NULL, 'asset code test', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(47, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, NULL, 'asset code test', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(48, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, NULL, 'asset code test1', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(49, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, NULL, 'asset code test45', 'asset name test56', NULL, NULL, NULL, NULL, NULL),
(50, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, NULL, 'asset code test', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(51, 8, '2021-04-02 10:49:16', NULL, '2021-04-02 10:49:16', NULL, NULL, 'klkjk', 'll', NULL, NULL, NULL, NULL, NULL),
(52, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, NULL, 'asset code test', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(53, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, NULL, 'asset code test', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(54, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, NULL, 'asset code test', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(55, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, NULL, 'asset code test', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(56, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, NULL, 'asset code test', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(57, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, 1, 'asset code test', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(58, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, NULL, 'asset code test', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(59, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, NULL, 'asset code test', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(60, NULL, '2021-03-22 18:28:39', NULL, '2021-03-22 18:28:39', NULL, NULL, 'asset code test', 'asset name test', NULL, NULL, NULL, NULL, NULL),
(61, NULL, '2021-04-14 06:56:21', NULL, '2021-04-14 06:56:21', NULL, 1, 'New Test', 'New Test', NULL, NULL, NULL, NULL, NULL),
(62, NULL, '2021-04-16 08:18:18', NULL, '2021-04-16 08:18:18', NULL, NULL, 'test', 'test', NULL, NULL, NULL, NULL, NULL),
(63, NULL, '2021-04-16 11:26:39', NULL, '2021-04-16 11:26:39', NULL, NULL, 'Hey', 'Hello', NULL, NULL, NULL, NULL, NULL),
(64, NULL, '2021-04-16 11:38:14', NULL, '2021-04-16 11:38:14', NULL, NULL, 'τεστ2α', 'τεστ2α', NULL, NULL, NULL, NULL, NULL),
(65, NULL, '2021-04-16 11:38:14', NULL, '2021-04-16 11:38:14', NULL, NULL, 'τεστ2β', 'τεστ2β', NULL, NULL, NULL, NULL, NULL),
(66, NULL, '2021-04-20 12:14:05', NULL, '2021-04-20 12:14:05', NULL, 3, 'test', 'test', NULL, NULL, NULL, NULL, NULL),
(67, NULL, '2021-07-02 07:03:32', NULL, '2021-07-02 07:03:32', NULL, NULL, NULL, NULL, NULL, 14, NULL, NULL, 89),
(68, NULL, '2021-07-02 14:39:44', NULL, '2021-07-02 14:39:44', NULL, NULL, 'assd', 'ddd', NULL, 15, 'ssss', NULL, 89);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `assets_entity`
--

CREATE TABLE `assets_entity` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `asset_category`
--

CREATE TABLE `asset_category` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `asset_category`
--

INSERT INTO `asset_category` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `name`, `description`) VALUES
(1, NULL, NULL, NULL, NULL, NULL, NULL, 'aas', 'Application/Anti-Virus / Security ', 'Application/Anti-Virus / Security'),
(2, NULL, NULL, NULL, NULL, NULL, NULL, 'app-bck', 'Application/Back-up', 'Application/Back-up'),
(3, NULL, NULL, NULL, NULL, NULL, NULL, 'app-bus', 'Application/Business', 'Application/Business'),
(4, NULL, NULL, NULL, NULL, NULL, NULL, 'app-db-mgnt', 'Application/Database Management', 'Application/Database Management'),
(5, NULL, NULL, NULL, NULL, NULL, NULL, 'app-devtools', 'Application/Development Tools', 'Application/Development Tools'),
(6, NULL, NULL, NULL, NULL, NULL, NULL, 'app-ent', 'Application/Entertainment', 'Application/Entertainment'),
(7, NULL, NULL, NULL, NULL, NULL, NULL, 'app-graph', 'Application/Graphics', 'Application/Graphics');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `asset_repository`
--

CREATE TABLE `asset_repository` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` datetime NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `business_value` int(11) DEFAULT NULL,
  `asset_category_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `asset_repository`
--

INSERT INTO `asset_repository` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `name`, `description`, `business_value`, `asset_category_id`) VALUES
(89, NULL, '2021-06-25 18:59:06', NULL, '2021-06-25 18:59:06', NULL, NULL, 'ASSET CODE1', 'ASSET NAME1', NULL, 1, NULL),
(92, NULL, '2021-07-06 15:20:43', NULL, '2021-07-06 15:20:43', NULL, NULL, 'ASSET CODE7', 'ASSET NAME7', NULL, NULL, NULL),
(93, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'ASSET CODE1', 'ASSET NAME1', NULL, NULL, NULL),
(94, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'ASSET CODE7', 'ASSET NAME7', NULL, NULL, NULL),
(95, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'ASSET CODE1', 'ASSET NAME1', NULL, NULL, NULL),
(96, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'ASSET CODE7', 'ASSET NAME7', NULL, NULL, NULL),
(97, NULL, '2021-07-08 17:51:53', NULL, '2021-07-08 17:51:53', NULL, NULL, 'Test1122', 'Test', NULL, NULL, NULL),
(98, NULL, '2021-07-09 11:23:23', NULL, '2021-07-09 11:23:23', NULL, NULL, 'Testtest', 'Hello', NULL, NULL, 7),
(99, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'ASSET CODE1', 'ASSET NAME1', NULL, NULL, NULL),
(100, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'ASSET CODE7', 'ASSET NAME7', NULL, NULL, NULL),
(101, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'ASSET CODE1', 'ASSET NAME1', NULL, NULL, NULL),
(102, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'ASSET CODE7', 'ASSET NAME7', NULL, NULL, NULL),
(103, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'ASSET CODE1', 'ASSET NAME1', NULL, NULL, NULL),
(104, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'ASSET CODE7', 'ASSET NAME7', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `chart`
--

CREATE TABLE `chart` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `chart_json` text DEFAULT NULL,
  `execute_periodically` bit(1) DEFAULT NULL,
  `execution_interval` int(11) DEFAULT NULL,
  `horizontal_axe` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `options_json` text DEFAULT NULL,
  `query` text DEFAULT NULL,
  `refresh_button` bit(1) DEFAULT NULL,
  `second_title` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `chart`
--

INSERT INTO `chart` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `chart_json`, `execute_periodically`, `execution_interval`, `horizontal_axe`, `icon`, `options_json`, `query`, `refresh_button`, `second_title`, `title`) VALUES
(1, NULL, '2021-05-07 18:07:27', NULL, '2021-05-20 09:08:43', NULL, 36, NULL, b'1', 5, 'C5', 'fa-chart-area', '{ \n  \"type\": \"line\", \n  \"hover\": false, \n  \"data\": [], \n  \"options\": { \n    \"legend\": { \n	 \"display\": false, \n	 \"position\": \"top\"\n    } \n  } \n} ', 'SELECT \n0 as C1, \n0 as C2, \n0 as C3, \n0 as C5\n\nUNION ALL  \nSELECT\n19 as C1, \n5 as C2, \n5 as C3, \n \'500k\' as C5\n\nUNION ALL  \nSELECT\n15 as C1, \n1 as C2, \n18 as C3, \n1 as C5\n\nUNION ALL  \nSELECT\n20 as C1, \n12 as C2, \n11 as C3, \n1.5 as C5\n\nUNION ALL  \nSELECT\n30 as C1, \n20 as C2, \n50 as C3, \n2 as C5\n \nUNION ALL  \nSELECT\n40 as C1, \n27 as C2, \n28 as C3, \n2.5 as C5\n\nUNION ALL  \nSELECT\n40 as C1, \n30 as C2, \n32 as C3,  \n3 as C5\n\nUNION ALL  \nSELECT\n50 as C1, \n34 as C2, \n10 as C3,  \n3.5 as C5\n\nUNION ALL  \nSELECT\n25 as C1, \n42 as C2, \n40 as C3, \n4 as C5\n\nUNION ALL  \nSELECT\n30 as C1, \n45 as C2, \n50 as C3, \n4.5 as C5\n\nUNION ALL  \nSELECT\n50 as C1, \n55 as C2, \n55 as C3, \n5 as C5\n\nUNION ALL  \nSELECT\n70 as C1, \n63 as C2, \n60 as C3, \n5.5 as C5', b'1', 'Exceedance / Expected Loss', 'Loss Exceedance Curve'),
(2, NULL, '2021-05-17 04:46:47', NULL, '2021-05-20 09:08:47', NULL, 14, NULL, NULL, 0, 'C5', 'fa-chart-area', '{ \n  \"type\": \"line\", \n  \"data\": [], \n  \"options\": { \n    \"responsive\": true, \n          \"interaction\": { \n            \"intersect\": false, \n            \"axis\": \"C1\"\n          },\n    \"plugins\": {\n      \"title\": {\n        \"display\": true,\n        \"text\": \"Step Interpolation\"\n      }\n    }\n  } \n} ', 'SELECT \n0 as C1, \n0 as C2, \n2 as C3, \n0 as C5\n\nUNION ALL  \nSELECT\n19 as C1, \n5 as C2, \n25 as C3, \n \'500k\' as C5\n\nUNION ALL  \nSELECT\n15 as C1, \n1 as C2, \n18 as C3, \n1 as C5\n\nUNION ALL  \nSELECT\n20 as C1, \n12 as C2, \n11 as C3, \n16.5 as C5\n\nUNION ALL  \nSELECT\n30 as C1, \n20 as C2, \n50 as C3, \n2 as C5\n \nUNION ALL  \nSELECT\n40 as C1, \n27 as C2, \n28 as C3, \n2.5 as C5\n\nUNION ALL  \nSELECT\n40 as C1, \n30 as C2, \n32 as C3,  \n3 as C5\n\nUNION ALL  \nSELECT\n50 as C1, \n34 as C2, \n10 as C3,  \n3.5 as C5\n\nUNION ALL  \nSELECT\n25 as C1, \n42 as C2, \n40 as C3, \n4 as C5\n\nUNION ALL  \nSELECT\n30 as C1, \n45 as C2, \n50 as C3, \n4.5 as C5\n\nUNION ALL  \nSELECT\n50 as C1, \n55 as C2, \n55 as C3, \n5 as C5\n\nUNION ALL  \nSELECT\n70 as C1, \n63 as C2, \n60 as C3, \n5.5 as C5', b'1', 'Risk / Hour Of Day', 'Daily risk profile'),
(3, NULL, '2021-05-19 12:43:20', NULL, '2021-05-20 09:08:50', NULL, 6, NULL, NULL, 0, 'C5', 'fa-chart-area', '{\n  \"type\": \"pie\",\n  \"data\": [],\n  \"options\": {\n    \"responsive\": true,\n    \"plugins\": {\n      \"legend\": {\n        \"position\": \"top\"\n      },\n      \"title\": {\n        \"display\": true,\n        \"text\": \"Chart.js Pie Chart\"\n      }\n    }\n  }\n}', 'SELECT \n0 as C1, \n0 as C2, \n2 as C3, \n0 as C5\n\nUNION ALL  \nSELECT\n19 as C1, \n5 as C2, \n25 as C3, \n \'500k\' as C5\n\nUNION ALL  \nSELECT\n15 as C1, \n1 as C2, \n18 as C3, \n1 as C5\n\nUNION ALL  \nSELECT\n20 as C1, \n12 as C2, \n11 as C3, \n16.5 as C5\n\nUNION ALL  \nSELECT\n30 as C1, \n20 as C2, \n50 as C3, \n2 as C5\n \nUNION ALL  \nSELECT\n40 as C1, \n27 as C2, \n28 as C3, \n2.5 as C5\n\nUNION ALL  \nSELECT\n40 as C1, \n30 as C2, \n32 as C3,  \n3 as C5\n\nUNION ALL  \nSELECT\n50 as C1, \n34 as C2, \n10 as C3,  \n3.5 as C5\n\nUNION ALL  \nSELECT\n25 as C1, \n42 as C2, \n40 as C3, \n4 as C5\n\nUNION ALL  \nSELECT\n30 as C1, \n45 as C2, \n50 as C3, \n4.5 as C5\n\nUNION ALL  \nSELECT\n50 as C1, \n55 as C2, \n55 as C3, \n5 as C5\n\nUNION ALL  \nSELECT\n70 as C1, \n63 as C2, \n60 as C3, \n5.5 as C5', b'1', 'Risk / Hour Of Day', 'Daily risk profile 2'),
(4, NULL, '2021-05-19 19:58:40', NULL, '2021-05-20 09:04:40', NULL, 10, NULL, NULL, 0, 'C2', 'fa-chart-pie', '{\n  \"type\": \"pie\",\n  \"data\": [],\n  \"options\": {\n    \"responsive\": true,\n    \"plugins\": {\n      \"legend\": {\n        \"position\": \"top\"\n      },\n      \"title\": {\n        \"display\": true,\n        \"text\": \"Chart.js Pie Chart\"\n      }\n    }\n  }\n}', 'SELECT \n10 as C1, \n\'Detected Threats\' as C2\n\nUNION ALL  \nSELECT \n22 as C1, \n\'Undetected Threats\' as C2\n\nUNION ALL  \nSELECT \n44 as C1, \n\'Expected Threats\' as C2\n\nUNION ALL  \nSELECT \n5 as C1, \n\'Restored Threats\' as C2\n\nUNION ALL  \nSELECT \n15 as C1, \n\'Deleted Threats\' as C2', b'1', 'Pie of Threat Categories', 'Threats by Category');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `chart_field`
--

CREATE TABLE `chart_field` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `chart_json` text DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `chart_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `chart_field`
--

INSERT INTO `chart_field` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `chart_json`, `description`, `name`, `size`, `type`, `chart_id`) VALUES
(18, NULL, '2021-05-10 04:41:09', NULL, '2021-05-20 09:08:43', NULL, 12, '{\n  \"data\": [],\n  \"fill\": false,\n  \"borderColor\": \"#fbc658\",\n  \"backgroundColor\": \"transparent\",\n  \"pointBorderColor\": \"#fbc658\",\n  \"pointRadius\": 4,\n  \"pointHoverRadius\": 14,\n  \"pointBorderWidth\": 8\n}', 'C1', 'C1', 2, 'int', 1),
(19, NULL, '2021-05-10 04:41:09', NULL, '2021-05-20 09:08:43', NULL, 12, '{\n  \"data\": [],\n  \"fill\": true,\n  \"borderColor\": \"#51CACF\",\n  \"backgroundColor\": \"transparent\",\n  \"pointBorderColor\": \"#51CACF\",\n  \"pointRadius\": 4,\n  \"pointHoverRadius\": 14,\n  \"pointBorderWidth\": 8\n}', 'C2', 'C2', 2, 'int', 1),
(20, NULL, '2021-05-10 04:41:09', NULL, '2021-05-20 09:08:43', NULL, 12, '{\n  \"data\": [],\n  \"fill\": false,\n  \"borderColor\": \"#f17e5d\",\n  \"backgroundColor\": \"transparent\",\n  \"pointBorderColor\": \"#f17e5d\",\n  \"pointRadius\": 4,\n  \"pointHoverRadius\": 14,\n  \"pointBorderWidth\": 8\n}', 'C3', 'C3', 2, 'int', 1),
(21, NULL, '2021-05-10 04:41:09', NULL, '2021-05-20 09:08:43', NULL, 12, '{\n  \"labels\": [],\n  \"datasets\": [] \n}', 'C5', 'C5', 4, 'varchar', 1),
(22, NULL, '2021-05-17 04:46:47', NULL, '2021-05-20 09:08:47', NULL, 14, '{\n  \"label\": \"Dataset3\",\n  \"data\": [],\n  \"fill\": true,\n  \"stepped\": true,\n  \"borderColor\": \"#51CACF\",\n  \"backgroundColor\": \"#51cbce29\"\n}', 'C1', 'C1', 2, 'int', 2),
(23, NULL, '2021-05-17 04:46:47', NULL, '2021-05-20 09:08:47', NULL, 14, '{\n  \"label\": \"Dataset1\",\n  \"data\": [],\n  \"fill\": false,\n  \"stepped\": true,\n  \"borderColor\": \"#f17e5d\"\n}', 'C2', 'C2', 2, 'int', 2),
(24, NULL, '2021-05-17 04:46:47', NULL, '2021-05-20 09:08:47', NULL, 14, '{\n  \"label\": \"Dataset2\",\n  \"data\": [],\n  \"fill\": true,\n  \"borderColor\": \"#fbc658\",\n  \"backgroundColor\": \"#fbc6584d\"\n}', 'C3', 'C3', 2, 'int', 2),
(25, NULL, '2021-05-17 04:46:47', NULL, '2021-05-20 09:08:47', NULL, 14, '{\n  \"labels\": [],\n  \"datasets\": [] \n}', 'C5', 'C5', 4, 'varchar', 2),
(26, NULL, '2021-05-19 12:43:20', NULL, '2021-05-20 09:08:50', NULL, 6, '{\n  \"label\": \"Dataset1\",\n  \"data\": [],\n  \"backgroundColor\": \"#fbc658\"\n}', 'C1', 'C1', 2, 'int', 3),
(27, NULL, '2021-05-19 12:43:20', NULL, '2021-05-20 09:08:50', NULL, 6, '{\n  \"label\": \"Dataset2\",\n  \"data\": [],\n  \"backgroundColor\": \"#51cbce29\"\n}', 'C2', 'C2', 2, 'int', 3),
(28, NULL, '2021-05-19 12:43:20', NULL, '2021-05-20 09:08:50', NULL, 6, '{\n  \"label\": \"Dataset3\",\n  \"data\": [],\n  \"backgroundColor\": \"#fbc658\"\n}', 'C3', 'C3', 2, 'int', 3),
(29, NULL, '2021-05-19 12:43:20', NULL, '2021-05-20 09:08:50', NULL, 6, '{\n  \"labels\": [],\n  \"datasets\": [] \n}', 'C5', 'C5', 5, 'varchar', 3),
(30, NULL, '2021-05-19 19:58:40', NULL, '2021-05-20 09:04:40', NULL, 10, '{\n  \"label\": \"Dataset1\",\n  \"data\": [],\n  \"backgroundColor\": [\"#fbc658\",\"#51CACF\",\"#2597da\",\"#f17e5d\",\"#66615b\"]\n}', 'C1', 'C1', 2, 'int', 4),
(31, NULL, '2021-05-19 19:58:40', NULL, '2021-05-20 09:04:40', NULL, 10, '{\n  \"labels\": [],\n  \"datasets\": [] \n}', 'C2', 'C2', 6, 'varchar', 4);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `component`
--

CREATE TABLE `component` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `component`
--

INSERT INTO `component` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `description`, `name`) VALUES
(1, NULL, '2021-03-06 00:26:29', NULL, '2021-05-06 12:06:07', NULL, 30, NULL, 'asset'),
(2, NULL, '2021-03-09 15:46:05', NULL, '2021-05-06 12:05:50', NULL, 2, NULL, 'User with menu name'),
(3, NULL, '2021-04-15 14:11:57', NULL, '2021-05-24 23:18:30', NULL, 18, NULL, 'entity'),
(4, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', NULL, 4, NULL, 'Asset Repository'),
(5, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', NULL, 0, NULL, 'Interface Repository'),
(6, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', NULL, 1, NULL, 'Vulnerability Repository'),
(7, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', NULL, 0, NULL, 'Threat Repository'),
(8, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', NULL, 5, NULL, 'Entity Component'),
(9, NULL, '2021-07-08 14:17:56', NULL, '2021-07-08 14:18:23', NULL, 1, NULL, 'Asset Category');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `component_persist_entity`
--

CREATE TABLE `component_persist_entity` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `allow_retrieve` bit(1) DEFAULT NULL,
  `allow_save` bit(1) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `delete_type` varchar(255) DEFAULT NULL,
  `multi_data_line` bit(1) DEFAULT NULL,
  `selector` varchar(255) DEFAULT NULL,
  `persist_entity_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `component_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `component_persist_entity`
--

INSERT INTO `component_persist_entity` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `allow_retrieve`, `allow_save`, `code`, `delete_type`, `multi_data_line`, `selector`, `persist_entity_id`, `parent_id`, `component_id`) VALUES
(1, NULL, '2021-03-06 00:26:29', NULL, '2021-05-06 12:06:07', 1, 45, b'1', b'1', 'asset', 'delete', NULL, NULL, 1, NULL, 1),
(2, NULL, '2021-03-06 00:26:29', NULL, '2021-05-06 12:06:07', 2, 30, b'1', b'1', 'interface', 'delete', NULL, '[INNER JOIN] interface.asset_id = asset.id ', 2, NULL, 1),
(3, NULL, '2021-03-06 00:26:30', NULL, '2021-05-06 12:06:07', 3, 30, b'1', b'1', 'vulnerability', 'delete', NULL, '[LEFT OUTER JOIN] vulnerability.interface_id = interface.id ', 5, NULL, 1),
(5, NULL, '2021-03-09 15:56:00', NULL, '2021-05-06 12:05:50', 1, 2, b'1', b'1', 'users_view', 'nothing', NULL, NULL, 7, NULL, 2),
(9, NULL, '2021-04-06 08:09:24', NULL, '2021-05-06 12:06:07', 4, 24, b'0', NULL, 'user', 'delete', NULL, '[LEFT OUTER JOIN] user.id = asset.version', 8, NULL, 1),
(11, NULL, '2021-04-07 14:01:55', NULL, '2021-05-06 12:06:07', 6, 12, NULL, NULL, 'user_2', NULL, NULL, NULL, 8, NULL, NULL),
(12, NULL, '2021-04-08 14:43:32', NULL, '2021-05-06 12:06:07', 6, 9, NULL, NULL, 'user_1', NULL, NULL, NULL, 8, NULL, NULL),
(13, NULL, '2021-04-15 14:11:57', NULL, '2021-05-24 23:18:30', 1, 19, b'1', b'1', 'entity', 'delete', NULL, NULL, 9, NULL, 3),
(16, NULL, '2021-04-19 11:42:25', NULL, '2021-05-06 12:06:07', 5, 4, b'1', b'0', 'user_3', 'delete', NULL, '[LEFT OUTER JOIN] asset.created_by = user_3.id ', 8, 1, NULL),
(23, NULL, '2021-04-21 07:19:24', NULL, '2021-05-24 23:18:30', 2, 13, b'1', b'1', 'entity_asset_assignments', 'delete', b'1', '[LEFT OUTER JOIN] entity.id = entity_asset_assignments.entity_id', 11, NULL, 3),
(24, NULL, '2021-04-21 07:19:24', NULL, '2021-05-24 23:18:30', 4, 15, b'1', NULL, 'asset', 'clearJoin', NULL, '[LEFT OUTER JOIN] entity_asset_assignments.asset_id = asset.id', 1, 23, NULL),
(25, NULL, '2021-04-23 09:20:16', NULL, '2021-05-24 23:18:30', 3, 9, b'1', NULL, 'user', 'clearJoin', NULL, '[LEFT OUTER JOIN] entity.user_id = user.id', 8, 13, NULL),
(26, NULL, '2021-05-24 23:18:30', NULL, '2021-05-24 23:18:30', 5, 1, b'1', b'1', 'interface', 'delete', b'1', NULL, 2, 24, NULL),
(27, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 1, 6, b'1', b'1', 'assetRepository', 'delete', NULL, NULL, 15, NULL, 4),
(28, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 2, 4, b'1', b'1', 'interfaceRepository', 'delete', b'1', NULL, 14, 27, NULL),
(29, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 3, 4, b'1', b'1', 'vulnerabilityRepository', 'delete', b'1', NULL, 13, 28, NULL),
(30, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 4, 6, b'1', b'1', 'threatRepository', 'delete', b'1', NULL, 12, 29, NULL),
(31, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 1, 0, b'1', b'1', 'interface_repository', 'delete', NULL, NULL, 14, NULL, 5),
(32, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 1, 2, b'1', NULL, 'asset_repository', 'clearJoin', NULL, NULL, 15, 31, NULL),
(33, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 2, 0, b'1', b'1', 'vulnerability_repository', 'delete', b'1', NULL, 13, 31, NULL),
(34, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 3, 2, b'1', b'1', 'threat_repository', 'delete', b'1', NULL, 12, 33, NULL),
(35, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 1, 1, b'1', b'1', 'vulnerability_repository', 'delete', NULL, NULL, 13, NULL, 6),
(36, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 1, 1, b'1', NULL, 'interface_repository', 'clearJoin', NULL, NULL, 14, 35, NULL),
(37, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 2, 3, b'1', b'1', 'threat_repository', 'delete', b'1', NULL, 12, 35, NULL),
(38, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 1, 2, b'1', b'1', 'threat_repository', 'delete', NULL, NULL, 12, NULL, 7),
(39, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 2, 0, b'1', NULL, 'vulnerability_repository', 'clearJoin', NULL, NULL, 13, NULL, 7),
(40, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 1, 5, b'1', b'1', 'entity', 'delete', NULL, NULL, 9, NULL, 8),
(41, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 3, 5, b'1', NULL, 'user', 'nothing', NULL, NULL, 8, 40, NULL),
(42, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 2, 5, b'1', b'1', 'asset', 'delete', b'1', NULL, 1, NULL, 8),
(43, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 4, 7, b'1', NULL, 'asset_repository', 'nothing', b'0', NULL, 15, 42, NULL),
(44, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 5, 5, b'1', b'1', 'interface', 'delete', b'1', NULL, 2, 42, NULL),
(45, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 6, 5, b'1', b'1', 'vulnerability', 'delete', b'1', NULL, 5, 44, NULL),
(46, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 7, 5, b'1', b'1', 'threat', 'delete', b'1', NULL, 3, 45, NULL),
(47, NULL, '2021-07-08 14:17:56', NULL, '2021-07-08 14:18:23', 1, 1, b'1', b'1', 'asset_category', 'delete', NULL, NULL, 16, NULL, 9),
(48, NULL, '2021-07-08 14:49:18', NULL, '2021-07-08 14:49:18', 5, 1, b'1', b'0', 'asset_category', 'nothing', NULL, NULL, 16, 27, NULL);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `component_persist_entity_field`
--

CREATE TABLE `component_persist_entity_field` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `default_value` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `editor` varchar(255) DEFAULT NULL,
  `locate_statement` varchar(255) DEFAULT NULL,
  `save_statement` varchar(255) DEFAULT NULL,
  `join_persist_entity_id` bigint(20) DEFAULT NULL,
  `persist_entity_field_id` bigint(20) DEFAULT NULL,
  `component_persist_entity_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `component_persist_entity_field`
--

INSERT INTO `component_persist_entity_field` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `default_value`, `description`, `editor`, `locate_statement`, `save_statement`, `join_persist_entity_id`, `persist_entity_field_id`, `component_persist_entity_id`) VALUES
(1, NULL, '2021-03-05 22:26:29', NULL, '2021-05-06 09:06:07', 1, 30, NULL, '', '', '#SELECTIONID', NULL, NULL, 1, 1),
(2, NULL, '2021-03-05 22:26:29', NULL, '2021-05-06 09:06:07', 2, 30, NULL, '', '', NULL, NULL, NULL, 2, 1),
(3, NULL, '2021-03-05 22:26:29', NULL, '2021-05-06 09:06:07', 3, 30, NULL, '', '', NULL, NULL, NULL, 3, 1),
(4, NULL, '2021-03-05 22:26:29', NULL, '2021-05-06 09:06:07', 4, 30, NULL, '', '', NULL, NULL, NULL, 4, 1),
(5, NULL, '2021-03-05 22:26:29', NULL, '2021-05-06 09:06:07', 5, 30, NULL, '', '', NULL, NULL, NULL, 5, 1),
(6, NULL, '2021-03-05 22:26:29', NULL, '2021-05-06 09:06:07', 6, 30, NULL, '', '', NULL, NULL, NULL, 6, 1),
(7, NULL, '2021-03-05 22:26:29', NULL, '2021-05-06 09:06:07', 7, 30, NULL, '', '', NULL, NULL, 11, 7, 1),
(8, NULL, '2021-03-05 22:26:29', NULL, '2021-05-06 09:06:07', 8, 30, NULL, '', '', NULL, NULL, NULL, 8, 1),
(9, NULL, '2021-03-05 22:26:29', NULL, '2021-05-06 09:06:07', 9, 30, NULL, '', '', NULL, NULL, NULL, 9, 1),
(10, NULL, '2021-03-05 22:26:29', NULL, '2021-05-06 09:06:07', 1, 30, NULL, '', '', NULL, '', NULL, 10, 2),
(11, NULL, '2021-03-05 22:26:29', NULL, '2021-05-06 09:06:07', 2, 30, NULL, '', '', NULL, NULL, NULL, 11, 2),
(12, NULL, '2021-03-05 22:26:29', NULL, '2021-05-06 09:06:07', 3, 30, NULL, '', '', NULL, NULL, NULL, 12, 2),
(13, NULL, '2021-03-05 22:26:29', NULL, '2021-05-06 09:06:07', 4, 30, NULL, '', '', NULL, NULL, NULL, 13, 2),
(14, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 5, 30, NULL, '', '', NULL, NULL, NULL, 14, 2),
(15, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 6, 30, NULL, '', '', NULL, NULL, 12, 15, 2),
(16, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 7, 30, NULL, '', '', NULL, NULL, NULL, 16, 2),
(17, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 8, 30, NULL, '', '', NULL, NULL, NULL, 17, 2),
(18, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 9, 30, NULL, '', '', NULL, NULL, NULL, 18, 2),
(19, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 10, 30, NULL, '', '', '#asset.id', '#asset.id', NULL, 19, 2),
(20, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 1, 30, NULL, '', '', NULL, NULL, NULL, 32, 3),
(21, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 2, 30, NULL, '', '', NULL, NULL, NULL, 33, 3),
(22, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 3, 30, NULL, '', '', NULL, NULL, NULL, 34, 3),
(23, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 4, 30, NULL, '', '', NULL, NULL, NULL, 35, 3),
(24, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 5, 30, NULL, '', '', NULL, NULL, NULL, 36, 3),
(25, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 6, 30, NULL, '', '', NULL, NULL, NULL, 37, 3),
(26, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 7, 30, NULL, '', '', NULL, NULL, NULL, 38, 3),
(27, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 8, 30, NULL, '', '', NULL, NULL, NULL, 39, 3),
(28, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 9, 30, NULL, '', '', NULL, NULL, NULL, 40, 3),
(29, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 10, 30, NULL, '', '', NULL, NULL, NULL, 41, 3),
(30, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 11, 30, NULL, '', '', NULL, NULL, NULL, 42, 3),
(31, NULL, '2021-03-05 22:26:30', NULL, '2021-05-06 09:06:07', 12, 30, NULL, '', '', '#interface.id', '#interface.id', NULL, 43, 3),
(43, NULL, '2021-03-09 13:56:00', NULL, '2021-05-06 09:05:50', 1, 1, NULL, '', '', NULL, NULL, NULL, 46, 5),
(44, NULL, '2021-03-09 13:56:00', NULL, '2021-05-06 09:05:50', 2, 1, NULL, '', '', NULL, NULL, NULL, 47, 5),
(45, NULL, '2021-03-09 13:56:00', NULL, '2021-05-06 09:05:50', 3, 1, NULL, '', '', NULL, NULL, NULL, 48, 5),
(46, NULL, '2021-03-09 13:56:00', NULL, '2021-05-06 09:05:50', 4, 1, NULL, '', '', NULL, NULL, NULL, 49, 5),
(47, NULL, '2021-03-09 13:56:00', NULL, '2021-05-06 09:05:50', 5, 1, NULL, '', '', NULL, NULL, NULL, 50, 5),
(48, NULL, '2021-03-09 13:56:00', NULL, '2021-05-06 09:05:50', 6, 1, NULL, '', '', NULL, NULL, NULL, 51, 5),
(49, NULL, '2021-03-09 13:56:00', NULL, '2021-05-06 09:05:50', 7, 1, NULL, '', '', NULL, NULL, NULL, 52, 5),
(50, NULL, '2021-03-09 13:56:00', NULL, '2021-05-06 09:05:50', 8, 1, NULL, '', '', NULL, NULL, NULL, 53, 5),
(51, NULL, '2021-03-09 13:56:00', NULL, '2021-05-06 09:05:50', 9, 1, NULL, '', '', NULL, NULL, NULL, 54, 5),
(52, NULL, '2021-03-09 13:56:00', NULL, '2021-05-06 09:05:50', 10, 1, NULL, '', '', NULL, NULL, NULL, 55, 5),
(53, NULL, '2021-03-09 13:56:00', NULL, '2021-05-06 09:05:50', 11, 1, NULL, '', '', NULL, NULL, NULL, 56, 5),
(54, NULL, '2021-03-09 13:56:00', NULL, '2021-05-06 09:05:50', 12, 1, NULL, '', '', NULL, NULL, NULL, 57, 5),
(55, NULL, '2021-03-09 13:56:00', NULL, '2021-05-06 09:05:50', 13, 1, NULL, '', '', NULL, NULL, NULL, 58, 5),
(56, NULL, '2021-03-09 13:56:00', NULL, '2021-05-06 09:05:50', 14, 1, NULL, '', '', NULL, NULL, NULL, 59, 5),
(90, NULL, '2021-04-06 05:09:24', NULL, '2021-05-06 09:06:07', 1, 23, NULL, NULL, NULL, '#asset.version', NULL, NULL, 62, 9),
(91, NULL, '2021-04-06 05:09:24', NULL, '2021-05-06 09:06:07', 2, 23, NULL, NULL, NULL, NULL, NULL, NULL, 63, 9),
(92, NULL, '2021-04-06 05:09:24', NULL, '2021-05-06 09:06:07', 3, 23, NULL, NULL, NULL, NULL, NULL, NULL, 64, 9),
(93, NULL, '2021-04-06 05:09:24', NULL, '2021-05-06 09:06:07', 4, 23, NULL, NULL, NULL, NULL, NULL, NULL, 65, 9),
(94, NULL, '2021-04-06 05:09:24', NULL, '2021-05-06 09:06:07', 5, 23, NULL, NULL, NULL, NULL, NULL, NULL, 66, 9),
(95, NULL, '2021-04-06 05:09:24', NULL, '2021-05-06 09:06:07', 6, 23, NULL, NULL, NULL, NULL, NULL, NULL, 67, 9),
(96, NULL, '2021-04-06 05:09:24', NULL, '2021-05-06 09:06:07', 7, 23, NULL, NULL, NULL, NULL, NULL, NULL, 68, 9),
(97, NULL, '2021-04-06 05:09:24', NULL, '2021-05-06 09:06:07', 8, 23, NULL, NULL, NULL, NULL, NULL, NULL, 69, 9),
(98, NULL, '2021-04-06 05:09:24', NULL, '2021-05-06 09:06:07', 9, 23, NULL, NULL, NULL, NULL, NULL, NULL, 70, 9),
(99, NULL, '2021-04-06 05:09:24', NULL, '2021-05-06 09:06:07', 10, 23, NULL, NULL, NULL, NULL, NULL, NULL, 71, 9),
(100, NULL, '2021-04-06 05:09:24', NULL, '2021-05-06 09:06:07', 11, 23, NULL, NULL, NULL, NULL, NULL, NULL, 72, 9),
(101, NULL, '2021-04-06 05:09:24', NULL, '2021-05-06 09:06:07', 12, 23, NULL, NULL, NULL, NULL, NULL, NULL, 73, 9),
(102, NULL, '2021-04-06 05:09:24', NULL, '2021-05-06 09:06:07', 13, 23, NULL, NULL, NULL, NULL, NULL, NULL, 74, 9),
(116, NULL, '2021-04-07 11:01:55', NULL, '2021-05-06 09:06:07', 1, 11, NULL, NULL, NULL, '#asset.version', NULL, NULL, 62, 11),
(117, NULL, '2021-04-07 11:01:55', NULL, '2021-05-06 09:06:07', 2, 11, NULL, NULL, NULL, NULL, NULL, NULL, 63, 11),
(118, NULL, '2021-04-07 11:01:55', NULL, '2021-05-06 09:06:07', 3, 11, NULL, NULL, NULL, NULL, NULL, NULL, 64, 11),
(119, NULL, '2021-04-07 11:01:55', NULL, '2021-05-06 09:06:07', 4, 11, NULL, NULL, NULL, NULL, NULL, NULL, 65, 11),
(120, NULL, '2021-04-07 11:01:55', NULL, '2021-05-06 09:06:07', 5, 11, NULL, NULL, NULL, NULL, NULL, NULL, 66, 11),
(121, NULL, '2021-04-07 11:01:55', NULL, '2021-05-06 09:06:07', 6, 11, NULL, NULL, NULL, NULL, NULL, NULL, 67, 11),
(122, NULL, '2021-04-07 11:01:55', NULL, '2021-05-06 09:06:07', 7, 11, NULL, NULL, NULL, NULL, NULL, NULL, 68, 11),
(123, NULL, '2021-04-07 11:01:55', NULL, '2021-05-06 09:06:07', 8, 11, NULL, NULL, NULL, NULL, NULL, NULL, 69, 11),
(124, NULL, '2021-04-07 11:01:55', NULL, '2021-05-06 09:06:07', 9, 11, NULL, NULL, NULL, NULL, NULL, NULL, 70, 11),
(125, NULL, '2021-04-07 11:01:55', NULL, '2021-05-06 09:06:07', 10, 11, NULL, NULL, NULL, NULL, NULL, NULL, 71, 11),
(126, NULL, '2021-04-07 11:01:55', NULL, '2021-05-06 09:06:07', 11, 11, NULL, NULL, NULL, NULL, NULL, NULL, 72, 11),
(127, NULL, '2021-04-07 11:01:55', NULL, '2021-05-06 09:06:07', 12, 11, NULL, NULL, NULL, NULL, NULL, NULL, 73, 11),
(128, NULL, '2021-04-07 11:01:55', NULL, '2021-05-06 09:06:07', 13, 11, NULL, NULL, NULL, NULL, NULL, NULL, 74, 11),
(129, NULL, '2021-04-08 11:43:32', NULL, '2021-05-06 09:06:07', 1, 8, NULL, NULL, NULL, '#interface.short_order', NULL, NULL, 62, 12),
(130, NULL, '2021-04-08 11:43:32', NULL, '2021-05-06 09:06:07', 2, 8, NULL, NULL, NULL, NULL, NULL, NULL, 63, 12),
(131, NULL, '2021-04-08 11:43:32', NULL, '2021-05-06 09:06:07', 3, 8, NULL, NULL, NULL, NULL, NULL, NULL, 64, 12),
(132, NULL, '2021-04-08 11:43:32', NULL, '2021-05-06 09:06:07', 4, 8, NULL, NULL, NULL, NULL, NULL, NULL, 65, 12),
(133, NULL, '2021-04-08 11:43:32', NULL, '2021-05-06 09:06:07', 5, 8, NULL, NULL, NULL, NULL, NULL, NULL, 66, 12),
(134, NULL, '2021-04-08 11:43:32', NULL, '2021-05-06 09:06:07', 6, 8, NULL, NULL, NULL, NULL, NULL, NULL, 67, 12),
(135, NULL, '2021-04-08 11:43:32', NULL, '2021-05-06 09:06:07', 7, 8, NULL, NULL, NULL, NULL, NULL, NULL, 68, 12),
(136, NULL, '2021-04-08 11:43:32', NULL, '2021-05-06 09:06:07', 8, 8, NULL, NULL, NULL, NULL, NULL, NULL, 69, 12),
(137, NULL, '2021-04-08 11:43:32', NULL, '2021-05-06 09:06:07', 9, 8, NULL, NULL, NULL, NULL, NULL, NULL, 70, 12),
(138, NULL, '2021-04-08 11:43:32', NULL, '2021-05-06 09:06:07', 10, 8, NULL, NULL, NULL, NULL, NULL, NULL, 71, 12),
(139, NULL, '2021-04-08 11:43:32', NULL, '2021-05-06 09:06:07', 11, 8, NULL, NULL, NULL, NULL, NULL, NULL, 72, 12),
(140, NULL, '2021-04-08 11:43:32', NULL, '2021-05-06 09:06:07', 12, 8, NULL, NULL, NULL, NULL, NULL, NULL, 73, 12),
(141, NULL, '2021-04-08 11:43:32', NULL, '2021-05-06 09:06:07', 13, 8, NULL, NULL, NULL, NULL, NULL, NULL, 74, 12),
(208, NULL, '2021-04-15 09:55:48', NULL, '2021-05-06 09:06:07', NULL, 6, NULL, NULL, NULL, NULL, NULL, NULL, 81, 1),
(210, NULL, '2021-04-15 11:11:57', NULL, '2021-05-24 20:18:30', 1, 18, NULL, NULL, NULL, '#SELECTIONID', NULL, NULL, 75, 13),
(211, NULL, '2021-04-15 11:11:57', NULL, '2021-05-24 20:18:30', 2, 18, NULL, NULL, NULL, NULL, NULL, NULL, 76, 13),
(212, NULL, '2021-04-15 11:11:57', NULL, '2021-05-24 20:18:30', 3, 18, NULL, NULL, NULL, NULL, NULL, NULL, 77, 13),
(213, NULL, '2021-04-15 11:11:57', NULL, '2021-05-24 20:18:30', 4, 18, NULL, NULL, NULL, NULL, NULL, NULL, 78, 13),
(214, NULL, '2021-04-15 11:11:57', NULL, '2021-05-24 20:18:30', 5, 18, NULL, NULL, NULL, NULL, NULL, NULL, 79, 13),
(215, NULL, '2021-04-15 11:11:57', NULL, '2021-05-24 20:18:30', 6, 18, NULL, NULL, NULL, NULL, NULL, NULL, 80, 13),
(226, NULL, '2021-04-15 11:12:27', NULL, '2021-05-24 20:18:30', NULL, 18, NULL, NULL, NULL, NULL, NULL, NULL, 85, 13),
(237, NULL, '2021-04-19 08:42:25', NULL, '2021-05-06 09:06:07', 1, 3, NULL, NULL, NULL, '#asset.created_by', '#asset.created_by', NULL, 62, 16),
(238, NULL, '2021-04-19 08:42:25', NULL, '2021-05-06 09:06:07', 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, 63, 16),
(239, NULL, '2021-04-19 08:42:25', NULL, '2021-05-06 09:06:07', 3, 3, NULL, NULL, NULL, NULL, NULL, NULL, 64, 16),
(240, NULL, '2021-04-19 08:42:25', NULL, '2021-05-06 09:06:07', 4, 3, NULL, NULL, NULL, NULL, NULL, NULL, 65, 16),
(241, NULL, '2021-04-19 08:42:25', NULL, '2021-05-06 09:06:07', 5, 3, NULL, NULL, NULL, NULL, NULL, NULL, 66, 16),
(242, NULL, '2021-04-19 08:42:25', NULL, '2021-05-06 09:06:07', 6, 3, NULL, NULL, NULL, NULL, NULL, NULL, 67, 16),
(243, NULL, '2021-04-19 08:42:25', NULL, '2021-05-06 09:06:07', 7, 3, NULL, NULL, NULL, NULL, NULL, NULL, 68, 16),
(244, NULL, '2021-04-19 08:42:25', NULL, '2021-05-06 09:06:07', 8, 3, NULL, NULL, NULL, NULL, NULL, NULL, 69, 16),
(245, NULL, '2021-04-19 08:42:25', NULL, '2021-05-06 09:06:07', 9, 3, NULL, NULL, NULL, NULL, NULL, NULL, 70, 16),
(246, NULL, '2021-04-19 08:42:25', NULL, '2021-05-06 09:06:07', 10, 3, NULL, NULL, NULL, NULL, NULL, NULL, 71, 16),
(247, NULL, '2021-04-19 08:42:25', NULL, '2021-05-06 09:06:07', 11, 3, NULL, NULL, NULL, NULL, NULL, NULL, 72, 16),
(248, NULL, '2021-04-19 08:42:25', NULL, '2021-05-06 09:06:07', 12, 3, NULL, NULL, NULL, NULL, NULL, NULL, 73, 16),
(249, NULL, '2021-04-19 08:42:25', NULL, '2021-05-06 09:06:07', 13, 3, NULL, NULL, NULL, NULL, NULL, NULL, 74, 16),
(286, NULL, '2021-04-21 04:19:24', NULL, '2021-05-24 20:18:30', 1, 12, NULL, NULL, NULL, NULL, NULL, NULL, 86, 23),
(287, NULL, '2021-04-21 04:19:24', NULL, '2021-05-24 20:18:30', 2, 12, NULL, NULL, '', '#entity.id', '#entity.id', NULL, 87, 23),
(288, NULL, '2021-04-21 04:19:24', NULL, '2021-05-24 20:18:30', 3, 12, NULL, NULL, NULL, NULL, NULL, NULL, 88, 23),
(289, NULL, '2021-04-21 04:19:24', NULL, '2021-05-24 20:18:30', 4, 12, NULL, NULL, NULL, NULL, NULL, NULL, 89, 23),
(290, NULL, '2021-04-21 04:19:24', NULL, '2021-05-24 20:18:30', 1, 12, NULL, NULL, NULL, '#entity_asset_assignments.asset_id', '#entity_asset_assignments.asset_id', NULL, 1, 24),
(291, NULL, '2021-04-21 04:19:24', NULL, '2021-05-24 20:18:30', 2, 12, NULL, NULL, NULL, NULL, NULL, NULL, 2, 24),
(292, NULL, '2021-04-21 04:19:24', NULL, '2021-05-24 20:18:30', 3, 12, NULL, NULL, NULL, NULL, NULL, NULL, 3, 24),
(293, NULL, '2021-04-21 04:19:24', NULL, '2021-05-24 20:18:30', 4, 12, NULL, NULL, NULL, NULL, NULL, NULL, 4, 24),
(294, NULL, '2021-04-21 04:19:24', NULL, '2021-05-24 20:18:30', 5, 12, NULL, NULL, NULL, NULL, NULL, NULL, 5, 24),
(295, NULL, '2021-04-21 04:19:24', NULL, '2021-05-24 20:18:30', 6, 12, NULL, NULL, NULL, NULL, NULL, NULL, 6, 24),
(296, NULL, '2021-04-21 04:19:24', NULL, '2021-05-24 20:18:30', 7, 12, NULL, NULL, NULL, NULL, NULL, NULL, 7, 24),
(297, NULL, '2021-04-21 04:19:24', NULL, '2021-05-24 20:18:30', 8, 12, NULL, NULL, NULL, NULL, NULL, NULL, 8, 24),
(298, NULL, '2021-04-21 04:19:24', NULL, '2021-05-24 20:18:30', 9, 12, NULL, NULL, NULL, NULL, NULL, NULL, 9, 24),
(299, NULL, '2021-04-21 04:19:24', NULL, '2021-05-24 20:18:30', 10, 12, NULL, NULL, NULL, NULL, NULL, NULL, 81, 24),
(300, NULL, '2021-04-23 06:20:16', NULL, '2021-05-24 20:18:30', 1, 8, NULL, NULL, NULL, '#entity.user_id', '#entity.user_id', NULL, 62, 25),
(301, NULL, '2021-04-23 06:20:16', NULL, '2021-05-24 20:18:30', 2, 8, NULL, NULL, NULL, NULL, NULL, NULL, 63, 25),
(302, NULL, '2021-04-23 06:20:16', NULL, '2021-05-24 20:18:30', 3, 8, NULL, NULL, NULL, NULL, NULL, NULL, 64, 25),
(303, NULL, '2021-04-23 06:20:16', NULL, '2021-05-24 20:18:30', 4, 8, NULL, NULL, NULL, NULL, NULL, NULL, 65, 25),
(304, NULL, '2021-04-23 06:20:16', NULL, '2021-05-24 20:18:30', 5, 8, NULL, NULL, NULL, NULL, NULL, NULL, 66, 25),
(305, NULL, '2021-04-23 06:20:16', NULL, '2021-05-24 20:18:30', 6, 8, NULL, NULL, NULL, NULL, NULL, NULL, 67, 25),
(306, NULL, '2021-04-23 06:20:16', NULL, '2021-05-24 20:18:30', 7, 8, NULL, NULL, NULL, NULL, NULL, NULL, 68, 25),
(307, NULL, '2021-04-23 06:20:16', NULL, '2021-05-24 20:18:30', 8, 8, NULL, NULL, NULL, NULL, NULL, NULL, 69, 25),
(308, NULL, '2021-04-23 06:20:16', NULL, '2021-05-24 20:18:30', 9, 8, NULL, NULL, NULL, NULL, NULL, NULL, 70, 25),
(309, NULL, '2021-04-23 06:20:16', NULL, '2021-05-24 20:18:30', 10, 8, NULL, NULL, NULL, NULL, NULL, NULL, 71, 25),
(310, NULL, '2021-04-23 06:20:16', NULL, '2021-05-24 20:18:30', 11, 8, NULL, NULL, NULL, NULL, NULL, NULL, 72, 25),
(311, NULL, '2021-04-23 06:20:16', NULL, '2021-05-24 20:18:30', 12, 8, NULL, NULL, NULL, NULL, NULL, NULL, 73, 25),
(312, NULL, '2021-04-23 06:20:16', NULL, '2021-05-24 20:18:30', 13, 8, NULL, NULL, NULL, NULL, NULL, NULL, 74, 25),
(313, NULL, '2021-05-24 20:18:30', NULL, '2021-05-24 20:18:30', 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 10, 26),
(314, NULL, '2021-05-24 20:18:30', NULL, '2021-05-24 20:18:30', 2, 0, NULL, NULL, NULL, NULL, NULL, NULL, 11, 26),
(315, NULL, '2021-05-24 20:18:30', NULL, '2021-05-24 20:18:30', 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, 12, 26),
(316, NULL, '2021-05-24 20:18:30', NULL, '2021-05-24 20:18:30', 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, 13, 26),
(317, NULL, '2021-05-24 20:18:30', NULL, '2021-05-24 20:18:30', 5, 0, NULL, NULL, NULL, NULL, NULL, NULL, 14, 26),
(318, NULL, '2021-05-24 20:18:30', NULL, '2021-05-24 20:18:30', 6, 0, NULL, NULL, NULL, NULL, NULL, NULL, 15, 26),
(319, NULL, '2021-05-24 20:18:30', NULL, '2021-05-24 20:18:30', 7, 0, NULL, NULL, NULL, NULL, NULL, NULL, 16, 26),
(320, NULL, '2021-05-24 20:18:30', NULL, '2021-05-24 20:18:30', 8, 0, NULL, NULL, NULL, NULL, NULL, NULL, 17, 26),
(321, NULL, '2021-05-24 20:18:30', NULL, '2021-05-24 20:18:30', 9, 0, NULL, NULL, NULL, NULL, NULL, NULL, 18, 26),
(322, NULL, '2021-05-24 20:18:30', NULL, '2021-05-24 20:18:30', 10, 0, NULL, NULL, NULL, '#asset.id', '#asset.id', NULL, 19, 26),
(323, NULL, '2021-05-24 20:18:30', NULL, '2021-05-24 20:18:30', 11, 0, NULL, NULL, NULL, NULL, NULL, NULL, 61, 26),
(324, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 1, 4, NULL, NULL, NULL, '#SELECTIONID', NULL, NULL, 124, 27),
(325, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 2, 4, NULL, NULL, NULL, NULL, NULL, NULL, 125, 27),
(326, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 3, 4, NULL, NULL, NULL, NULL, NULL, NULL, 126, 27),
(327, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 4, 4, NULL, NULL, NULL, NULL, NULL, NULL, 127, 27),
(328, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 5, 4, NULL, NULL, NULL, NULL, NULL, NULL, 128, 27),
(329, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 6, 4, NULL, NULL, NULL, NULL, NULL, NULL, 129, 27),
(330, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 7, 4, NULL, NULL, NULL, NULL, NULL, NULL, 130, 27),
(331, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 8, 4, NULL, NULL, NULL, NULL, NULL, NULL, 131, 27),
(332, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 9, 4, NULL, NULL, NULL, NULL, NULL, NULL, 132, 27),
(333, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 10, 4, NULL, NULL, NULL, NULL, NULL, NULL, 133, 27),
(334, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 1, 4, NULL, NULL, NULL, NULL, NULL, NULL, 113, 28),
(335, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 2, 4, NULL, NULL, NULL, NULL, NULL, NULL, 114, 28),
(336, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 3, 4, NULL, NULL, NULL, NULL, NULL, NULL, 115, 28),
(337, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 4, 4, NULL, NULL, NULL, NULL, NULL, NULL, 116, 28),
(338, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 5, 4, NULL, NULL, NULL, NULL, NULL, NULL, 117, 28),
(339, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 6, 4, NULL, NULL, NULL, NULL, NULL, NULL, 118, 28),
(340, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 7, 4, NULL, NULL, NULL, NULL, NULL, NULL, 119, 28),
(341, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 8, 4, NULL, NULL, NULL, NULL, NULL, NULL, 120, 28),
(342, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 9, 4, NULL, NULL, NULL, NULL, NULL, NULL, 121, 28),
(343, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 10, 4, NULL, NULL, NULL, '#assetRepository.id', '#assetRepository.id', NULL, 122, 28),
(344, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 11, 4, NULL, NULL, NULL, NULL, NULL, NULL, 123, 28),
(345, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 1, 4, NULL, NULL, NULL, NULL, NULL, NULL, 101, 29),
(346, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 2, 4, NULL, NULL, NULL, NULL, NULL, NULL, 102, 29),
(347, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 3, 4, NULL, NULL, NULL, NULL, NULL, NULL, 103, 29),
(348, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 4, 4, NULL, NULL, NULL, NULL, NULL, NULL, 104, 29),
(349, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 5, 4, NULL, NULL, NULL, NULL, NULL, NULL, 105, 29),
(350, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 6, 4, NULL, NULL, NULL, NULL, NULL, NULL, 106, 29),
(351, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 7, 4, NULL, NULL, NULL, NULL, NULL, NULL, 107, 29),
(352, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 8, 4, NULL, NULL, NULL, NULL, NULL, NULL, 108, 29),
(353, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 9, 4, NULL, NULL, NULL, NULL, NULL, NULL, 109, 29),
(354, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 10, 4, NULL, NULL, NULL, NULL, NULL, NULL, 110, 29),
(355, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 11, 4, NULL, NULL, NULL, NULL, NULL, NULL, 111, 29),
(356, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 12, 4, NULL, NULL, NULL, '#interfaceRepository.id', '#interfaceRepository.id', NULL, 112, 29),
(357, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 1, 4, NULL, NULL, NULL, NULL, NULL, NULL, 90, 30),
(358, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 2, 4, NULL, NULL, NULL, NULL, NULL, NULL, 91, 30),
(359, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 3, 4, NULL, NULL, NULL, NULL, NULL, NULL, 92, 30),
(360, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 4, 4, NULL, NULL, NULL, NULL, NULL, NULL, 93, 30),
(361, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 5, 4, NULL, NULL, NULL, NULL, NULL, NULL, 94, 30),
(362, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 6, 4, NULL, NULL, NULL, NULL, NULL, NULL, 95, 30),
(363, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 7, 4, NULL, NULL, NULL, NULL, NULL, NULL, 96, 30),
(364, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 8, 4, NULL, NULL, NULL, NULL, NULL, NULL, 97, 30),
(365, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 9, 4, NULL, NULL, NULL, NULL, NULL, NULL, 98, 30),
(366, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 10, 4, NULL, NULL, NULL, NULL, NULL, NULL, 99, 30),
(367, NULL, '2021-06-16 07:58:42', NULL, '2021-07-08 14:49:18', 11, 4, NULL, NULL, NULL, '#vulnerabilityRepository.id', '#vulnerabilityRepository.id', NULL, 100, 30),
(368, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 1, 0, NULL, NULL, NULL, '#SELECTIONID', NULL, NULL, 113, 31),
(369, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 2, 0, NULL, NULL, NULL, NULL, NULL, NULL, 114, 31),
(370, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, 115, 31),
(371, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, 116, 31),
(372, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 5, 0, NULL, NULL, NULL, NULL, NULL, NULL, 117, 31),
(373, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 6, 0, NULL, NULL, NULL, NULL, NULL, NULL, 118, 31),
(374, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 7, 0, NULL, NULL, NULL, NULL, NULL, NULL, 119, 31),
(375, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 8, 0, NULL, NULL, NULL, NULL, NULL, NULL, 120, 31),
(376, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 9, 0, NULL, NULL, NULL, NULL, NULL, NULL, 121, 31),
(377, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 10, 0, NULL, NULL, NULL, NULL, NULL, NULL, 122, 31),
(378, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 11, 0, NULL, NULL, NULL, NULL, NULL, NULL, 123, 31),
(379, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 1, 0, NULL, NULL, '#interface_repository.asset_repository_id', '#interface_repository.asset_repository_id', NULL, NULL, 124, 32),
(380, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 2, 0, NULL, NULL, NULL, NULL, NULL, NULL, 125, 32),
(381, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, 126, 32),
(382, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, 127, 32),
(383, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 5, 0, NULL, NULL, NULL, NULL, NULL, NULL, 128, 32),
(384, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 6, 0, NULL, NULL, NULL, NULL, NULL, NULL, 129, 32),
(385, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 7, 0, NULL, NULL, NULL, NULL, NULL, NULL, 130, 32),
(386, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 8, 0, NULL, NULL, NULL, NULL, NULL, NULL, 131, 32),
(387, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 9, 0, NULL, NULL, NULL, NULL, NULL, NULL, 132, 32),
(388, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 10, 0, NULL, NULL, NULL, NULL, NULL, NULL, 133, 32),
(389, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 101, 33),
(390, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 2, 0, NULL, NULL, NULL, NULL, NULL, NULL, 102, 33),
(391, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, 103, 33),
(392, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, 104, 33),
(393, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 5, 0, NULL, NULL, NULL, NULL, NULL, NULL, 105, 33),
(394, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 6, 0, NULL, NULL, NULL, NULL, NULL, NULL, 106, 33),
(395, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 7, 0, NULL, NULL, NULL, NULL, NULL, NULL, 107, 33),
(396, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 8, 0, NULL, NULL, NULL, NULL, NULL, NULL, 108, 33),
(397, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 9, 0, NULL, NULL, NULL, NULL, NULL, NULL, 109, 33),
(398, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 10, 0, NULL, NULL, NULL, NULL, NULL, NULL, 110, 33),
(399, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 11, 0, NULL, NULL, NULL, NULL, NULL, NULL, 111, 33),
(400, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 12, 0, NULL, NULL, NULL, '#interface_repository.id', '#interface_repository.id', NULL, 112, 33),
(401, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 90, 34),
(402, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 2, 0, NULL, NULL, NULL, NULL, NULL, NULL, 91, 34),
(403, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, 92, 34),
(404, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, 93, 34),
(405, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 5, 0, NULL, NULL, NULL, NULL, NULL, NULL, 94, 34),
(406, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 6, 0, NULL, NULL, NULL, NULL, NULL, NULL, 95, 34),
(407, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 7, 0, NULL, NULL, NULL, NULL, NULL, NULL, 96, 34),
(408, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 8, 0, NULL, NULL, NULL, NULL, NULL, NULL, 97, 34),
(409, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 9, 0, NULL, NULL, NULL, NULL, NULL, NULL, 98, 34),
(410, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 10, 0, NULL, NULL, NULL, NULL, NULL, NULL, 99, 34),
(411, NULL, '2021-06-23 09:31:57', NULL, '2021-06-23 09:31:57', 11, 0, NULL, NULL, NULL, '#vulnerability_repository.id', '#vulnerability_repository.id', NULL, 100, 34),
(412, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 1, 1, NULL, NULL, NULL, '#SELECTIONID', NULL, NULL, 101, 35),
(413, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 102, 35),
(414, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 3, 1, NULL, NULL, NULL, NULL, NULL, NULL, 103, 35),
(415, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 4, 1, NULL, NULL, NULL, NULL, NULL, NULL, 104, 35),
(416, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 5, 1, NULL, NULL, NULL, NULL, NULL, NULL, 105, 35),
(417, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 6, 1, NULL, NULL, NULL, NULL, NULL, NULL, 106, 35),
(418, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 7, 1, NULL, NULL, NULL, NULL, NULL, NULL, 107, 35),
(419, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 8, 1, NULL, NULL, NULL, NULL, NULL, NULL, 108, 35),
(420, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 9, 1, NULL, NULL, NULL, NULL, NULL, NULL, 109, 35),
(421, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 10, 1, NULL, NULL, NULL, NULL, NULL, NULL, 110, 35),
(422, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 11, 1, NULL, NULL, NULL, NULL, NULL, NULL, 111, 35),
(423, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 12, 1, NULL, NULL, NULL, NULL, NULL, NULL, 112, 35),
(424, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 1, 1, NULL, NULL, '', '#vulnerability_repository.interface_repository_id', '#vulnerability_repository.interface_repository_id', NULL, 113, 36),
(425, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 114, 36),
(426, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 3, 1, NULL, NULL, NULL, NULL, NULL, NULL, 115, 36),
(427, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 4, 1, NULL, NULL, NULL, NULL, NULL, NULL, 116, 36),
(428, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 5, 1, NULL, NULL, NULL, NULL, NULL, NULL, 117, 36),
(429, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 6, 1, NULL, NULL, NULL, NULL, NULL, NULL, 118, 36),
(430, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 7, 1, NULL, NULL, NULL, NULL, NULL, NULL, 119, 36),
(431, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 8, 1, NULL, NULL, NULL, NULL, NULL, NULL, 120, 36),
(432, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 9, 1, NULL, NULL, NULL, NULL, NULL, NULL, 121, 36),
(433, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 10, 1, NULL, NULL, NULL, NULL, NULL, NULL, 122, 36),
(434, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 11, 1, NULL, NULL, NULL, NULL, NULL, NULL, 123, 36),
(435, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, 90, 37),
(436, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 91, 37),
(437, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 3, 1, NULL, NULL, NULL, NULL, NULL, NULL, 92, 37),
(438, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 4, 1, NULL, NULL, NULL, NULL, NULL, NULL, 93, 37),
(439, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 5, 1, NULL, NULL, NULL, NULL, NULL, NULL, 94, 37),
(440, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 6, 1, NULL, NULL, NULL, NULL, NULL, NULL, 95, 37),
(441, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 7, 1, NULL, NULL, NULL, NULL, NULL, NULL, 96, 37),
(442, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 8, 1, NULL, NULL, NULL, NULL, NULL, NULL, 97, 37),
(443, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 9, 1, NULL, NULL, NULL, NULL, NULL, NULL, 98, 37),
(444, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 10, 1, NULL, NULL, NULL, NULL, NULL, NULL, 99, 37),
(445, NULL, '2021-06-23 09:35:03', NULL, '2021-06-24 12:04:55', 11, 1, NULL, NULL, '', '#vulnerability_repository.id', '#vulnerability_repository.id', NULL, 100, 37),
(446, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 1, 0, NULL, NULL, NULL, '#SELECTIONID', NULL, NULL, 90, 38),
(447, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 2, 0, NULL, NULL, NULL, NULL, NULL, NULL, 91, 38),
(448, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, 92, 38),
(449, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, 93, 38),
(450, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 5, 0, NULL, NULL, NULL, NULL, NULL, NULL, 94, 38),
(451, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 6, 0, NULL, NULL, NULL, NULL, NULL, NULL, 95, 38),
(452, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 7, 0, NULL, NULL, NULL, NULL, NULL, NULL, 96, 38),
(453, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 8, 0, NULL, NULL, NULL, NULL, NULL, NULL, 97, 38),
(454, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 9, 0, NULL, NULL, NULL, NULL, NULL, NULL, 98, 38),
(455, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 10, 0, NULL, NULL, NULL, NULL, NULL, NULL, 99, 38),
(456, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 11, 0, NULL, NULL, NULL, NULL, NULL, NULL, 100, 38),
(457, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 1, 0, NULL, NULL, NULL, '#threat_repository.vulnerability_repository_id', '#threat_repository.vulnerability_repository_id', NULL, 101, 39),
(458, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 2, 0, NULL, NULL, NULL, NULL, NULL, NULL, 102, 39),
(459, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, 103, 39),
(460, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, 104, 39),
(461, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 5, 0, NULL, NULL, NULL, NULL, NULL, NULL, 105, 39),
(462, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 6, 0, NULL, NULL, NULL, NULL, NULL, NULL, 106, 39),
(463, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 7, 0, NULL, NULL, NULL, NULL, NULL, NULL, 107, 39),
(464, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 8, 0, NULL, NULL, NULL, NULL, NULL, NULL, 108, 39),
(465, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 9, 0, NULL, NULL, NULL, NULL, NULL, NULL, 109, 39),
(466, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 10, 0, NULL, NULL, NULL, NULL, NULL, NULL, 110, 39),
(467, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 11, 0, NULL, NULL, NULL, NULL, NULL, NULL, 111, 39),
(468, NULL, '2021-06-23 09:37:39', NULL, '2021-06-23 09:37:39', 12, 0, NULL, NULL, NULL, NULL, NULL, NULL, 112, 39),
(469, NULL, '2021-07-01 10:45:58', NULL, '2021-07-01 10:45:58', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 134, 1),
(470, NULL, '2021-07-01 11:20:45', NULL, '2021-07-01 11:20:45', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 135, 1),
(471, NULL, '2021-07-01 11:20:45', NULL, '2021-07-01 11:20:45', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 134, 24),
(472, NULL, '2021-07-01 11:20:45', NULL, '2021-07-01 11:20:45', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 135, 24),
(473, NULL, '2021-07-01 11:59:11', NULL, '2021-07-01 11:59:11', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 136, 1),
(474, NULL, '2021-07-01 11:59:11', NULL, '2021-07-01 11:59:11', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 136, 24),
(475, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 1, 5, NULL, NULL, NULL, '#SELECTIONID', NULL, NULL, 75, 40),
(476, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 2, 5, NULL, NULL, NULL, NULL, NULL, NULL, 76, 40),
(477, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 3, 5, NULL, NULL, NULL, NULL, NULL, NULL, 77, 40),
(478, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 4, 5, NULL, NULL, NULL, NULL, NULL, NULL, 78, 40),
(479, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 5, 5, NULL, NULL, NULL, NULL, NULL, NULL, 79, 40),
(480, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 6, 5, NULL, NULL, NULL, NULL, NULL, NULL, 80, 40),
(481, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 7, 5, NULL, NULL, NULL, NULL, NULL, NULL, 85, 40),
(482, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 1, 5, NULL, NULL, NULL, '#entity.user_id', '#entity.user_id', NULL, 62, 41),
(483, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 2, 5, NULL, NULL, NULL, NULL, NULL, NULL, 63, 41),
(484, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 3, 5, NULL, NULL, NULL, NULL, NULL, NULL, 64, 41),
(485, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 4, 5, NULL, NULL, NULL, NULL, NULL, NULL, 65, 41),
(486, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 5, 5, NULL, NULL, NULL, NULL, NULL, NULL, 66, 41),
(487, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 6, 5, NULL, NULL, NULL, NULL, NULL, NULL, 67, 41),
(488, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 7, 5, NULL, NULL, NULL, NULL, NULL, NULL, 68, 41),
(489, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 8, 5, NULL, NULL, NULL, NULL, NULL, NULL, 69, 41),
(490, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 9, 5, NULL, NULL, NULL, NULL, NULL, NULL, 70, 41),
(491, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 10, 5, NULL, NULL, NULL, NULL, NULL, NULL, 71, 41),
(492, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 11, 5, NULL, NULL, NULL, NULL, NULL, NULL, 72, 41),
(493, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 12, 5, NULL, NULL, NULL, NULL, NULL, NULL, 73, 41),
(494, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 13, 5, NULL, NULL, NULL, NULL, NULL, NULL, 74, 41),
(495, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 1, 5, NULL, NULL, NULL, NULL, NULL, NULL, 1, 42),
(496, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 2, 5, NULL, NULL, NULL, NULL, NULL, NULL, 2, 42),
(497, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 3, 5, NULL, NULL, NULL, NULL, NULL, NULL, 3, 42),
(498, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 4, 5, NULL, NULL, NULL, NULL, NULL, NULL, 4, 42),
(499, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 5, 5, NULL, NULL, NULL, NULL, NULL, NULL, 5, 42),
(500, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 6, 5, NULL, NULL, NULL, NULL, NULL, NULL, 6, 42),
(501, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 7, 5, NULL, NULL, NULL, NULL, NULL, NULL, 7, 42),
(502, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 8, 5, NULL, NULL, NULL, NULL, NULL, NULL, 8, 42),
(503, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 9, 5, NULL, NULL, NULL, NULL, NULL, NULL, 9, 42),
(504, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 10, 5, NULL, NULL, NULL, '#entity.id', '#entity.id', NULL, 81, 42),
(505, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 11, 5, NULL, NULL, NULL, NULL, NULL, NULL, 134, 42),
(506, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 12, 5, NULL, NULL, NULL, NULL, NULL, NULL, 135, 42),
(507, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 13, 5, NULL, NULL, NULL, NULL, NULL, NULL, 136, 42),
(508, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 1, 5, NULL, NULL, NULL, '#asset.asset_repository_id', NULL, NULL, 124, 43),
(509, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 2, 5, NULL, NULL, NULL, NULL, NULL, NULL, 125, 43),
(510, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 3, 5, NULL, NULL, NULL, NULL, NULL, NULL, 126, 43),
(511, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 4, 5, NULL, NULL, NULL, NULL, NULL, NULL, 127, 43),
(512, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 5, 5, NULL, NULL, NULL, NULL, NULL, NULL, 128, 43),
(513, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 6, 5, NULL, NULL, NULL, NULL, NULL, NULL, 129, 43),
(514, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 7, 5, NULL, NULL, NULL, NULL, NULL, NULL, 130, 43),
(515, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 8, 5, NULL, NULL, NULL, NULL, NULL, NULL, 131, 43),
(516, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 9, 5, NULL, NULL, NULL, NULL, NULL, NULL, 132, 43),
(517, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 10, 5, NULL, NULL, NULL, NULL, NULL, NULL, 133, 43),
(518, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 1, 5, NULL, NULL, NULL, NULL, NULL, NULL, 10, 44),
(519, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 2, 5, NULL, NULL, NULL, NULL, NULL, NULL, 11, 44),
(520, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 3, 5, NULL, NULL, NULL, NULL, NULL, NULL, 12, 44),
(521, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 4, 5, NULL, NULL, NULL, NULL, NULL, NULL, 13, 44),
(522, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 5, 5, NULL, NULL, NULL, NULL, NULL, NULL, 14, 44),
(523, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 6, 5, NULL, NULL, NULL, NULL, NULL, NULL, 15, 44),
(524, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 7, 5, NULL, NULL, NULL, NULL, NULL, NULL, 16, 44),
(525, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 8, 5, NULL, NULL, NULL, NULL, NULL, NULL, 17, 44),
(526, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 9, 5, NULL, NULL, NULL, NULL, NULL, NULL, 18, 44),
(527, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 10, 5, NULL, NULL, '', '#asset.id', '#asset.id', NULL, 19, 44),
(528, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 11, 5, NULL, NULL, NULL, NULL, NULL, NULL, 61, 44),
(529, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 1, 5, NULL, NULL, NULL, NULL, NULL, NULL, 32, 45),
(530, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 2, 5, NULL, NULL, NULL, NULL, NULL, NULL, 33, 45),
(531, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 3, 5, NULL, NULL, NULL, NULL, NULL, NULL, 34, 45),
(532, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 4, 5, NULL, NULL, NULL, NULL, NULL, NULL, 35, 45),
(533, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 5, 5, NULL, NULL, NULL, NULL, NULL, NULL, 36, 45),
(534, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 6, 5, NULL, NULL, NULL, NULL, NULL, NULL, 37, 45),
(535, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 7, 5, NULL, NULL, NULL, NULL, NULL, NULL, 38, 45),
(536, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 8, 5, NULL, NULL, NULL, NULL, NULL, NULL, 39, 45),
(537, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 9, 5, NULL, NULL, NULL, NULL, NULL, NULL, 40, 45),
(538, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 10, 5, NULL, NULL, NULL, NULL, NULL, NULL, 41, 45),
(539, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 11, 5, NULL, NULL, NULL, NULL, NULL, NULL, 42, 45),
(540, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 12, 5, NULL, NULL, NULL, '#interface.id', '#interface.id', NULL, 43, 45),
(541, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 1, 5, NULL, NULL, NULL, NULL, NULL, NULL, 20, 46),
(542, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 2, 5, NULL, NULL, NULL, NULL, NULL, NULL, 21, 46),
(543, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 3, 5, NULL, NULL, NULL, NULL, NULL, NULL, 22, 46),
(544, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 4, 5, NULL, NULL, NULL, NULL, NULL, NULL, 23, 46),
(545, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 5, 5, NULL, NULL, NULL, NULL, NULL, NULL, 24, 46),
(546, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 6, 5, NULL, NULL, NULL, NULL, NULL, NULL, 25, 46),
(547, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 7, 5, NULL, NULL, NULL, NULL, NULL, NULL, 26, 46),
(548, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 8, 5, NULL, NULL, NULL, NULL, NULL, NULL, 27, 46),
(549, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 9, 5, NULL, NULL, NULL, NULL, NULL, NULL, 28, 46),
(550, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 10, 5, NULL, NULL, NULL, NULL, NULL, NULL, 29, 46),
(551, NULL, '2021-07-01 13:05:14', NULL, '2021-07-02 08:06:45', 11, 5, NULL, NULL, '', '#vulnerability.id', '#vulnerability.id', NULL, 30, 46),
(556, NULL, '2021-07-08 13:47:37', NULL, '2021-07-08 14:49:18', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 138, 27),
(557, NULL, '2021-07-08 13:47:37', NULL, '2021-07-08 13:47:37', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 138, 32),
(558, NULL, '2021-07-08 13:47:37', NULL, '2021-07-08 13:47:37', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 138, 43),
(559, NULL, '2021-07-08 13:49:18', NULL, '2021-07-08 14:49:18', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 139, 27),
(560, NULL, '2021-07-08 13:49:18', NULL, '2021-07-08 13:49:18', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 139, 32),
(561, NULL, '2021-07-08 13:49:18', NULL, '2021-07-08 13:49:18', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 139, 43),
(562, NULL, '2021-07-08 14:17:56', NULL, '2021-07-08 14:18:23', 1, 1, NULL, NULL, NULL, '#SELECTIONID', NULL, NULL, 140, 47),
(563, NULL, '2021-07-08 14:17:56', NULL, '2021-07-08 14:18:23', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 141, 47),
(564, NULL, '2021-07-08 14:17:56', NULL, '2021-07-08 14:18:23', 3, 1, NULL, NULL, NULL, NULL, NULL, NULL, 142, 47),
(565, NULL, '2021-07-08 14:17:56', NULL, '2021-07-08 14:18:23', 4, 1, NULL, NULL, NULL, NULL, NULL, NULL, 143, 47),
(566, NULL, '2021-07-08 14:17:56', NULL, '2021-07-08 14:18:23', 5, 1, NULL, NULL, NULL, NULL, NULL, NULL, 144, 47),
(567, NULL, '2021-07-08 14:17:56', NULL, '2021-07-08 14:18:23', 6, 1, NULL, NULL, NULL, NULL, NULL, NULL, 145, 47),
(568, NULL, '2021-07-08 14:17:56', NULL, '2021-07-08 14:18:23', 7, 1, NULL, NULL, NULL, NULL, NULL, NULL, 146, 47),
(569, NULL, '2021-07-08 14:17:56', NULL, '2021-07-08 14:18:23', 8, 1, NULL, NULL, NULL, NULL, NULL, NULL, 147, 47),
(570, NULL, '2021-07-08 14:17:56', NULL, '2021-07-08 14:18:23', 9, 1, NULL, NULL, NULL, NULL, NULL, NULL, 148, 47),
(571, NULL, '2021-07-08 14:17:56', NULL, '2021-07-08 14:18:23', 10, 1, NULL, NULL, NULL, NULL, NULL, NULL, 149, 47),
(572, NULL, '2021-07-08 14:49:18', NULL, '2021-07-08 14:49:18', 1, 0, NULL, NULL, NULL, '#assetRepository.asset_category_id', '', NULL, 140, 48),
(573, NULL, '2021-07-08 14:49:18', NULL, '2021-07-08 14:49:18', 2, 0, NULL, NULL, NULL, NULL, NULL, NULL, 141, 48),
(574, NULL, '2021-07-08 14:49:18', NULL, '2021-07-08 14:49:18', 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, 142, 48),
(575, NULL, '2021-07-08 14:49:18', NULL, '2021-07-08 14:49:18', 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, 143, 48),
(576, NULL, '2021-07-08 14:49:18', NULL, '2021-07-08 14:49:18', 5, 0, NULL, NULL, NULL, NULL, NULL, NULL, 144, 48),
(577, NULL, '2021-07-08 14:49:18', NULL, '2021-07-08 14:49:18', 6, 0, NULL, NULL, NULL, NULL, NULL, NULL, 145, 48),
(578, NULL, '2021-07-08 14:49:18', NULL, '2021-07-08 14:49:18', 7, 0, NULL, NULL, NULL, NULL, NULL, NULL, 146, 48),
(579, NULL, '2021-07-08 14:49:18', NULL, '2021-07-08 14:49:18', 8, 0, NULL, NULL, NULL, NULL, NULL, NULL, 147, 48),
(580, NULL, '2021-07-08 14:49:18', NULL, '2021-07-08 14:49:18', 9, 0, NULL, NULL, NULL, NULL, NULL, NULL, 148, 48),
(581, NULL, '2021-07-08 14:49:18', NULL, '2021-07-08 14:49:18', 10, 0, NULL, NULL, NULL, NULL, NULL, NULL, 149, 48);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `component_persist_entity_field_assignment`
--

CREATE TABLE `component_persist_entity_field_assignment` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `css` varchar(255) DEFAULT NULL,
  `decimals` int(11) DEFAULT NULL,
  `default_value` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `editable` bit(1) DEFAULT NULL,
  `editor` varchar(255) DEFAULT NULL,
  `field_id` bigint(20) DEFAULT NULL,
  `field_value` varchar(255) DEFAULT NULL,
  `fieldtype` varchar(255) DEFAULT NULL,
  `form_id` bigint(20) DEFAULT NULL,
  `required` bit(1) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `entity_id` varchar(255) DEFAULT NULL,
  `entity_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `component_persist_entity_field_assignment`
--

INSERT INTO `component_persist_entity_field_assignment` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `css`, `decimals`, `default_value`, `description`, `editable`, `editor`, `field_id`, `field_value`, `fieldtype`, `form_id`, `required`, `type`, `visible`, `entity_id`, `entity_type`) VALUES
(2312, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'id', b'1', NULL, 237, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2313, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'created_by', b'1', NULL, 238, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2314, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'created_on', b'1', NULL, 239, NULL, NULL, 3, b'0', 'datetime', b'1', '3', 'form'),
(2315, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'modified_by', b'1', NULL, 240, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2316, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'modified_on', b'1', NULL, 241, NULL, NULL, 3, b'0', 'datetime', b'1', '3', 'form'),
(2317, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'short_order', b'1', NULL, 242, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2318, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'version', b'1', NULL, 243, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2319, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'dateformat', b'1', NULL, 244, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2320, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'email', b'1', NULL, 245, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2321, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'password', b'1', NULL, 246, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2322, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'status', b'1', NULL, 247, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2323, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'username', b'1', NULL, 248, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2324, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'menu_id', b'1', NULL, 249, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2325, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 1, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2326, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 2, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2327, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 3, NULL, NULL, 3, b'0', 'datetime', b'1', '3', 'form'),
(2328, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 4, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2329, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 5, NULL, NULL, 3, b'0', 'datetime', b'1', '3', 'form'),
(2330, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 6, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2331, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'User', b'1', 'POPUPLIST[LOCATE:(ID=1),TITLE:invoices List,RETURN:C5,DISPLAY:(user_2.username;user_2.email),FORM:1,FORM-NEW-TAB:yes]', 7, NULL, NULL, 3, b'0', 'list', b'1', '3', 'form'),
(2332, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'Asset Code', b'1', NULL, 8, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2333, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'Asset Name', b'1', NULL, 9, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2334, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'entity_id', b'1', NULL, 208, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2335, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 10, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2336, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 11, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2337, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 12, NULL, NULL, 3, b'0', 'datetime', b'1', '3', 'form'),
(2338, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 13, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2339, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 14, NULL, NULL, 3, b'0', 'datetime', b'1', '3', 'form'),
(2340, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'short order', b'1', 'POPUPLIST[LOCATE:(ID=1),TITLE:invoices List,RETURN:C5,DISPLAY:(user_1.username;user_1.email),ENTITY:user]', 15, NULL, NULL, 3, b'0', 'list', b'1', '3', 'form'),
(2341, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 16, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2342, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'Code', b'1', NULL, 17, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2343, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'Name', b'1', NULL, 18, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2344, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'Asset Id', b'1', '', 19, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2345, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 20, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2346, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 21, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2347, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 22, NULL, NULL, 3, b'0', 'datetime', b'1', '3', 'form'),
(2348, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 23, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2349, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 24, NULL, NULL, 3, b'0', 'datetime', b'1', '3', 'form'),
(2350, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 25, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2351, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 26, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2352, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 27, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2353, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 28, NULL, NULL, 3, b'0', 'double', b'1', '3', 'form'),
(2354, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 29, NULL, NULL, 3, b'0', 'double', b'1', '3', 'form'),
(2355, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 30, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2356, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, NULL, b'1', NULL, 31, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2357, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'id', b'1', NULL, 90, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2358, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'created_by', b'1', NULL, 91, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2359, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'created_on', b'1', NULL, 92, NULL, NULL, 3, b'0', 'datetime', b'1', '3', 'form'),
(2360, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'modified_by', b'1', NULL, 93, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2361, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'modified_on', b'1', NULL, 94, NULL, NULL, 3, b'0', 'datetime', b'1', '3', 'form'),
(2362, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'short_order', b'1', NULL, 95, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2363, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'version', b'1', NULL, 96, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(2364, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'dateformat', b'1', NULL, 97, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2365, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'email', b'1', NULL, 98, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2366, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'password', b'1', NULL, 99, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2367, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'status', b'1', NULL, 100, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2368, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'username', b'1', NULL, 101, NULL, NULL, 3, b'0', 'varchar', b'1', '3', 'form'),
(2369, NULL, '2021-04-20 11:05:19', NULL, '2021-04-20 11:05:19', NULL, 0, NULL, NULL, NULL, 'menu_id', b'1', NULL, 102, NULL, NULL, 3, b'0', 'bigint', b'1', '3', 'form'),
(11648, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'id', b'1', NULL, 300, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11649, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'created_by', b'1', NULL, 301, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11650, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'created_on', b'1', NULL, 302, NULL, NULL, NULL, b'0', 'datetime', b'1', '4', 'form'),
(11651, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'modified_by', b'1', NULL, 303, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11652, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'modified_on', b'1', NULL, 304, NULL, NULL, NULL, b'0', 'datetime', b'1', '4', 'form'),
(11653, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'short_order', b'1', NULL, 305, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11654, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'version', b'1', NULL, 306, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11655, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'dateformat', b'1', NULL, 307, NULL, NULL, NULL, b'0', 'varchar', b'1', '4', 'form'),
(11656, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'email', b'1', NULL, 308, NULL, NULL, NULL, b'0', 'varchar', b'1', '4', 'form'),
(11657, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'password', b'1', NULL, 309, NULL, NULL, NULL, b'0', 'varchar', b'1', '4', 'form'),
(11658, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'status', b'1', NULL, 310, NULL, NULL, NULL, b'0', 'varchar', b'1', '4', 'form'),
(11659, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'username', b'1', NULL, 311, NULL, NULL, NULL, b'0', 'varchar', b'1', '4', 'form'),
(11660, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'menu_id', b'1', NULL, 312, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11661, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 313, NULL, NULL, NULL, NULL, 'bigint', NULL, '4', 'form'),
(11662, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 314, NULL, NULL, NULL, NULL, 'bigint', NULL, '4', 'form'),
(11663, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 315, NULL, NULL, NULL, NULL, 'datetime', NULL, '4', 'form'),
(11664, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 316, NULL, NULL, NULL, NULL, 'bigint', NULL, '4', 'form'),
(11665, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 317, NULL, NULL, NULL, NULL, 'datetime', NULL, '4', 'form'),
(11666, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 318, NULL, NULL, NULL, NULL, 'bigint', NULL, '4', 'form'),
(11667, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 319, NULL, NULL, NULL, NULL, 'bigint', NULL, '4', 'form'),
(11668, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 320, NULL, NULL, NULL, NULL, 'varchar', NULL, '4', 'form'),
(11669, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 321, NULL, NULL, NULL, NULL, 'varchar', NULL, '4', 'form'),
(11670, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 322, NULL, NULL, NULL, NULL, 'bigint', NULL, '4', 'form'),
(11671, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 323, NULL, NULL, NULL, NULL, 'bigint', NULL, '4', 'form'),
(11672, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'id', b'1', NULL, 290, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11673, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'created_by', b'1', NULL, 291, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11674, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'created_on', b'1', NULL, 292, NULL, NULL, NULL, b'0', 'datetime', b'1', '4', 'form'),
(11675, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'modified_by', b'1', NULL, 293, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11676, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'modified_on', b'1', NULL, 294, NULL, NULL, NULL, b'0', 'datetime', b'1', '4', 'form'),
(11677, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'short_order', b'1', NULL, 295, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11678, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'version', b'1', NULL, 296, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11679, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'code', b'1', NULL, 297, NULL, NULL, NULL, b'0', 'varchar', b'1', '4', 'form'),
(11680, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'name', b'1', NULL, 298, NULL, NULL, NULL, b'0', 'varchar', b'1', '4', 'form'),
(11681, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'entity_id', b'1', NULL, 299, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11682, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'id', b'1', NULL, 210, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11683, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, '', 'name', b'1', '', 211, NULL, NULL, NULL, b'0', 'varchar', b'1', '4', 'form'),
(11684, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'created_by', b'1', NULL, 212, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11685, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, 'dateNowPlus(0)', 'Created On', b'1', NULL, 213, NULL, NULL, NULL, b'0', 'datetime', b'1', '4', 'form'),
(11686, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'modified_by', b'1', NULL, 214, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11687, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'modified_on', b'1', NULL, 215, NULL, NULL, NULL, b'0', 'datetime', b'1', '4', 'form'),
(11688, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, 'systemParameter(\'userId\')', 'User', b'1', 'POPUPLIST[LOCATE:(ID=1),RETURN:C5,DISPLAY:(user.username;user.email),REFRESH:user]', 226, NULL, NULL, NULL, b'0', 'list', b'1', '4', 'form'),
(11689, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'id', b'1', NULL, 286, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11690, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'entity_id', b'1', NULL, 287, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11691, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'Asset', b'1', 'POPUPLIST[LOCATE:(ID=7),RETURN:asset_id,DISPLAY:(asset.code;asset.name),FORM:1,FORM-NEW-TAB:yes,REFRESH:asset]', 288, NULL, NULL, NULL, b'0', 'list', b'1', '4', 'form'),
(11692, NULL, '2021-06-20 11:54:01', NULL, '2021-06-20 11:54:01', NULL, 0, NULL, NULL, NULL, 'asset_prototype_id', b'1', NULL, 289, NULL, NULL, NULL, b'0', 'bigint', b'1', '4', 'form'),
(11825, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 357, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11826, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 358, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11827, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 359, NULL, NULL, NULL, NULL, 'datetime', NULL, '1', 'xls_import'),
(11828, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 360, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11829, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 361, NULL, NULL, NULL, NULL, 'datetime', NULL, '1', 'xls_import'),
(11830, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 362, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11831, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 363, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11832, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, 'importColumn(\'g\')', NULL, NULL, 'NEWLINEONUPDATE', 364, NULL, NULL, NULL, NULL, 'varchar', NULL, '1', 'xls_import'),
(11833, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, 'importColumn(\'h\')', NULL, NULL, NULL, 365, NULL, NULL, NULL, NULL, 'varchar', NULL, '1', 'xls_import'),
(11834, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 366, NULL, NULL, NULL, NULL, 'double', NULL, '1', 'xls_import'),
(11835, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 367, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11836, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 345, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11837, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 346, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11838, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 347, NULL, NULL, NULL, NULL, 'datetime', NULL, '1', 'xls_import'),
(11839, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 348, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11840, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 349, NULL, NULL, NULL, NULL, 'datetime', NULL, '1', 'xls_import'),
(11841, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 350, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11842, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 351, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11843, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, 'importColumn(\'e\')', NULL, NULL, 'NEWLINEONUPDATE', 352, NULL, NULL, NULL, NULL, 'varchar', NULL, '1', 'xls_import'),
(11844, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 353, NULL, NULL, NULL, NULL, 'double', NULL, '1', 'xls_import'),
(11845, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 354, NULL, NULL, NULL, NULL, 'double', NULL, '1', 'xls_import'),
(11846, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, 'importColumn(\'f\')', NULL, NULL, NULL, 355, NULL, NULL, NULL, NULL, 'varchar', NULL, '1', 'xls_import'),
(11847, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 356, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11848, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 334, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11849, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 335, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11850, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 336, NULL, NULL, NULL, NULL, 'datetime', NULL, '1', 'xls_import'),
(11851, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 337, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11852, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 338, NULL, NULL, NULL, NULL, 'datetime', NULL, '1', 'xls_import'),
(11853, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 339, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11854, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 340, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11855, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, 'importColumn(\'c\')', NULL, NULL, 'NEWLINEONUPDATE', 341, NULL, NULL, NULL, NULL, 'varchar', NULL, '1', 'xls_import'),
(11856, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, 'importColumn(\'d\')', NULL, NULL, NULL, 342, NULL, NULL, NULL, NULL, 'varchar', NULL, '1', 'xls_import'),
(11857, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 343, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11858, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 344, NULL, NULL, NULL, NULL, 'varchar', NULL, '1', 'xls_import'),
(11859, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, '', NULL, NULL, '', 324, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11860, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, '', NULL, NULL, '', 325, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11861, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 326, NULL, NULL, NULL, NULL, 'datetime', NULL, '1', 'xls_import'),
(11862, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 327, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11863, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 328, NULL, NULL, NULL, NULL, 'datetime', NULL, '1', 'xls_import'),
(11864, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 329, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11865, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 330, NULL, NULL, NULL, NULL, 'bigint', NULL, '1', 'xls_import'),
(11866, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, 'importColumn(\'a\')', NULL, NULL, 'SAVEONUPDATE', 331, NULL, NULL, NULL, NULL, 'varchar', NULL, '1', 'xls_import'),
(11867, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, 'importColumn(\'b\')', NULL, NULL, NULL, 332, NULL, NULL, NULL, NULL, 'varchar', NULL, '1', 'xls_import'),
(11868, NULL, '2021-06-22 13:23:55', NULL, '2021-06-22 13:23:55', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 333, NULL, NULL, NULL, NULL, 'varchar', NULL, '1', 'xls_import'),
(16390, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 482, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16391, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 483, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16392, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 484, NULL, NULL, NULL, NULL, 'datetime', NULL, '7', 'form'),
(16393, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 485, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16394, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 486, NULL, NULL, NULL, NULL, 'datetime', NULL, '7', 'form'),
(16395, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 487, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16396, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 488, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16397, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 489, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16398, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 490, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16399, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 491, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16400, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 492, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16401, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 493, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16402, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 494, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16403, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 541, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16404, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 542, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16405, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 543, NULL, NULL, NULL, NULL, 'datetime', NULL, '7', 'form'),
(16406, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 544, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16407, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 545, NULL, NULL, NULL, NULL, 'datetime', NULL, '7', 'form'),
(16408, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 546, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16409, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 547, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16410, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 548, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16411, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 549, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16412, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 550, NULL, NULL, NULL, NULL, 'double', NULL, '7', 'form'),
(16413, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 551, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16414, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 529, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16415, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 530, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16416, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 531, NULL, NULL, NULL, NULL, 'datetime', NULL, '7', 'form'),
(16417, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 532, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16418, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 533, NULL, NULL, NULL, NULL, 'datetime', NULL, '7', 'form'),
(16419, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 534, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16420, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 535, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16421, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 536, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16422, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 537, NULL, NULL, NULL, NULL, 'double', NULL, '7', 'form'),
(16423, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 538, NULL, NULL, NULL, NULL, 'double', NULL, '7', 'form'),
(16424, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 539, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16425, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 540, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16426, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 508, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16427, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 509, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16428, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 510, NULL, NULL, NULL, NULL, 'datetime', NULL, '7', 'form'),
(16429, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 511, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16430, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 512, NULL, NULL, NULL, NULL, 'datetime', NULL, '7', 'form'),
(16431, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 513, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16432, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 514, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16433, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 515, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16434, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 516, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16435, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 517, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16436, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 518, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16437, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 519, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16438, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 520, NULL, NULL, NULL, NULL, 'datetime', NULL, '7', 'form'),
(16439, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 521, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16440, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 522, NULL, NULL, NULL, NULL, 'datetime', NULL, '7', 'form'),
(16441, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 523, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16442, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 524, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16443, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 525, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16444, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 526, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16445, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 527, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16446, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 528, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16447, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 475, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16448, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 476, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16449, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 477, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16450, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, 'dateNowPlus(0)', NULL, NULL, NULL, 478, NULL, NULL, NULL, NULL, 'datetime', NULL, '7', 'form'),
(16451, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 479, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16452, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 480, NULL, NULL, NULL, NULL, 'datetime', NULL, '7', 'form'),
(16453, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, 'systemParameter(\'userId\')', NULL, NULL, 'POPUPLIST[LOCATE:(ID=1),RETURN:C5,DISPLAY:(user.username;user.email),REFRESH:user]', 481, NULL, NULL, NULL, NULL, 'list', NULL, '7', 'form'),
(16454, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 495, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16455, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 496, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16456, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 497, NULL, NULL, NULL, NULL, 'datetime', NULL, '7', 'form'),
(16457, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 498, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16458, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 499, NULL, NULL, NULL, NULL, 'datetime', NULL, '7', 'form'),
(16459, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 500, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16460, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 501, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16461, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 502, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16462, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 503, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16463, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 504, NULL, NULL, NULL, NULL, 'bigint', NULL, '7', 'form'),
(16464, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 505, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16465, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 506, NULL, NULL, NULL, NULL, 'varchar', NULL, '7', 'form'),
(16466, NULL, '2021-07-06 12:24:51', NULL, '2021-07-06 12:24:51', NULL, 0, NULL, NULL, NULL, NULL, NULL, 'POPUPLIST[LOCATE:(ID=13),RETURN:cf_id,DISPLAY:(asset_repository.code;asset_repository.name),REFRESH:asset_repository]', 507, NULL, NULL, NULL, NULL, 'list', NULL, '7', 'form'),
(16467, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 562, NULL, NULL, NULL, NULL, 'bigint', NULL, '8', 'form'),
(16468, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 563, NULL, NULL, NULL, NULL, 'bigint', NULL, '8', 'form'),
(16469, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 564, NULL, NULL, NULL, NULL, 'datetime', NULL, '8', 'form'),
(16470, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 565, NULL, NULL, NULL, NULL, 'bigint', NULL, '8', 'form'),
(16471, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 566, NULL, NULL, NULL, NULL, 'datetime', NULL, '8', 'form'),
(16472, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 567, NULL, NULL, NULL, NULL, 'bigint', NULL, '8', 'form'),
(16473, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 568, NULL, NULL, NULL, NULL, 'bigint', NULL, '8', 'form'),
(16474, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 569, NULL, NULL, NULL, NULL, 'varchar', NULL, '8', 'form'),
(16475, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 570, NULL, NULL, NULL, NULL, 'varchar', NULL, '8', 'form'),
(16476, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 571, NULL, NULL, NULL, NULL, 'varchar', NULL, '8', 'form'),
(17343, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 357, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17344, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 358, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17345, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 359, NULL, NULL, NULL, NULL, 'datetime', NULL, '6', 'form'),
(17346, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 360, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17347, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 361, NULL, NULL, NULL, NULL, 'datetime', NULL, '6', 'form'),
(17348, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 362, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17349, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 363, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17350, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 364, NULL, NULL, NULL, NULL, 'varchar', NULL, '6', 'form'),
(17351, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 365, NULL, NULL, NULL, NULL, 'varchar', NULL, '6', 'form'),
(17352, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 366, NULL, NULL, NULL, NULL, 'double', NULL, '6', 'form'),
(17353, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 367, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17354, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 345, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17355, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 346, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17356, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 347, NULL, NULL, NULL, NULL, 'datetime', NULL, '6', 'form'),
(17357, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 348, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17358, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 349, NULL, NULL, NULL, NULL, 'datetime', NULL, '6', 'form'),
(17359, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 350, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17360, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 351, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17361, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 352, NULL, NULL, NULL, NULL, 'varchar', NULL, '6', 'form'),
(17362, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 353, NULL, NULL, NULL, NULL, 'double', NULL, '6', 'form'),
(17363, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 354, NULL, NULL, NULL, NULL, 'double', NULL, '6', 'form'),
(17364, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 355, NULL, NULL, NULL, NULL, 'varchar', NULL, '6', 'form'),
(17365, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 356, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17366, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 334, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17367, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 335, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17368, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 336, NULL, NULL, NULL, NULL, 'datetime', NULL, '6', 'form'),
(17369, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 337, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17370, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 338, NULL, NULL, NULL, NULL, 'datetime', NULL, '6', 'form'),
(17371, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 339, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17372, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 340, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17373, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 341, NULL, NULL, NULL, NULL, 'varchar', NULL, '6', 'form'),
(17374, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 342, NULL, NULL, NULL, NULL, 'varchar', NULL, '6', 'form'),
(17375, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 343, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17376, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 344, NULL, NULL, NULL, NULL, 'varchar', NULL, '6', 'form'),
(17377, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 572, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17378, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 573, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17379, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 574, NULL, NULL, NULL, NULL, 'datetime', NULL, '6', 'form'),
(17380, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 575, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17381, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 576, NULL, NULL, NULL, NULL, 'datetime', NULL, '6', 'form'),
(17382, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 577, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17383, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 578, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17384, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 579, NULL, NULL, NULL, NULL, 'varchar', NULL, '6', 'form'),
(17385, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 580, NULL, NULL, NULL, NULL, 'varchar', NULL, '6', 'form'),
(17386, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 581, NULL, NULL, NULL, NULL, 'varchar', NULL, '6', 'form'),
(17387, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 324, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17388, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 325, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17389, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 326, NULL, NULL, NULL, NULL, 'datetime', NULL, '6', 'form'),
(17390, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 327, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17391, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 328, NULL, NULL, NULL, NULL, 'datetime', NULL, '6', 'form'),
(17392, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 329, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17393, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 330, NULL, NULL, NULL, NULL, 'bigint', NULL, '6', 'form'),
(17394, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 331, NULL, NULL, NULL, NULL, 'varchar', NULL, '6', 'form'),
(17395, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 332, NULL, NULL, NULL, NULL, 'varchar', NULL, '6', 'form');
INSERT INTO `component_persist_entity_field_assignment` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `css`, `decimals`, `default_value`, `description`, `editable`, `editor`, `field_id`, `field_value`, `fieldtype`, `form_id`, `required`, `type`, `visible`, `entity_id`, `entity_type`) VALUES
(17396, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 333, NULL, NULL, NULL, NULL, 'varchar', NULL, '6', 'form'),
(17397, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, '{\"data\":[{\"value\":\"null\",\"display\": \"---\"},{\"value\": \"1\",\"display\": \"category 01\"},{\"value\": \"2\",\"display\": \"category 02\"},{\"value\": \"3\",\"display\": \"category 03\"}]}', 556, NULL, NULL, NULL, NULL, 'combo', NULL, '6', 'form'),
(17398, NULL, '2021-07-09 12:21:00', NULL, '2021-07-09 12:21:00', NULL, 0, NULL, NULL, NULL, NULL, NULL, 'POPUPLIST[LOCATE:(ID=20),RETURN:cf_id,FOCUS:header-filter-cf_name,DISPLAY:(asset_category.name),REFRESH:asset_category]', 559, NULL, NULL, NULL, NULL, 'list', NULL, '6', 'form');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `dashboard`
--

CREATE TABLE `dashboard` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `dashboard`
--

INSERT INTO `dashboard` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `description`) VALUES
(1, NULL, '2021-06-02 11:49:59', NULL, '2021-07-08 11:51:32', NULL, 12, 'Main Dasiboard'),
(2, NULL, '2021-07-08 11:52:22', NULL, '2021-07-08 11:52:22', NULL, 0, 'Test');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `dashboard_area`
--

CREATE TABLE `dashboard_area` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `css_style` varchar(255) DEFAULT NULL,
  `cssclass` varchar(255) DEFAULT NULL,
  `dashboard_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `dashboard_area`
--

INSERT INTO `dashboard_area` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `css_style`, `cssclass`, `dashboard_id`) VALUES
(1, NULL, '2021-06-02 11:49:59', NULL, '2021-07-08 11:51:32', 1, 12, '', 'col-12 col-xl-6', 1),
(2, NULL, '2021-06-02 12:11:06', NULL, '2021-07-08 11:51:32', 2, 9, '', 'col-12 col-xl-6', 1),
(3, NULL, '2021-07-08 11:52:22', NULL, '2021-07-08 11:52:22', 1, 0, '', 'col-12', 2);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `dashboard_item`
--

CREATE TABLE `dashboard_item` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `cssclass` varchar(255) DEFAULT NULL,
  `entity_id` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `dashboard_area_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `dashboard_item`
--

INSERT INTO `dashboard_item` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `cssclass`, `entity_id`, `type`, `dashboard_area_id`) VALUES
(2, NULL, '2021-06-02 11:49:59', NULL, '2021-07-08 11:51:32', 4, 12, 'col-12', 2, 'chart', 1),
(3, NULL, '2021-06-02 11:49:59', NULL, '2021-07-08 11:51:32', 6, 12, 'col-12', 4, 'chart', 1),
(5, NULL, '2021-06-02 12:09:52', NULL, '2021-07-08 11:51:32', 0, 9, 'col-12 col-md-6', 1, 'infoCard', 1),
(6, NULL, '2021-06-02 12:09:52', NULL, '2021-07-08 11:51:32', 1, 9, 'col-12 col-md-6', 1, 'infoCard', 1),
(9, NULL, '2021-06-02 12:11:06', NULL, '2021-07-08 11:51:32', 1, 8, 'col-12', 8, 'list', 2),
(10, NULL, '2021-06-02 12:11:56', NULL, '2021-07-08 11:51:32', 2, 7, 'col-12', 1, 'chart', 2),
(11, NULL, '2021-06-02 14:45:51', NULL, '2021-07-08 11:51:32', 3, 6, 'col-12 col-md-6', 1, 'infoCard', 2),
(12, NULL, '2021-06-02 14:45:51', NULL, '2021-07-08 11:51:32', 4, 6, 'col-12 col-md-6', 1, 'infoCard', 2),
(13, NULL, '2021-06-02 14:47:46', NULL, '2021-07-08 11:51:32', 5, 5, 'col-12 col-md-6', 1, 'infoCard', 2),
(14, NULL, '2021-06-02 14:47:46', NULL, '2021-07-08 11:51:32', 6, 5, 'col-12 col-md-6', 1, 'infoCard', 2),
(15, NULL, '2021-07-08 11:52:22', NULL, '2021-07-08 11:52:22', 1, 0, 'col-3', 1, 'infoCard', 3),
(16, NULL, '2021-07-08 11:52:22', NULL, '2021-07-08 11:52:22', 2, 0, 'col-3', 1, 'infoCard', 3),
(17, NULL, '2021-07-08 11:52:22', NULL, '2021-07-08 11:52:22', 3, 0, 'col-3', 1, 'infoCard', 3),
(18, NULL, '2021-07-08 11:52:22', NULL, '2021-07-08 11:52:22', 4, 0, 'col-3', 1, 'infoCard', 3),
(19, NULL, '2021-07-08 11:52:22', NULL, '2021-07-08 11:52:22', 5, 0, 'col-12', 3, 'chart', 3);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `entity`
--

CREATE TABLE `entity` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` datetime DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` datetime DEFAULT current_timestamp(),
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `entity`
--

INSERT INTO `entity` (`id`, `name`, `created_by`, `created_on`, `modified_by`, `modified_on`, `user_id`) VALUES
(1, 'test', NULL, '2021-07-02 17:31:03', NULL, '2021-07-02 17:31:03', 1),
(2, 'test2', NULL, '2021-04-16 21:00:00', NULL, '2021-04-16 17:26:39', NULL),
(3, 'τεστ2', NULL, '2021-04-16 14:37:49', NULL, '2021-04-16 17:38:14', 1),
(6, 't1', NULL, '2021-04-26 21:46:24', NULL, '2021-04-27 00:46:39', 1),
(7, 't2', NULL, '2021-04-28 10:40:00', NULL, '2021-04-28 13:54:02', 1),
(8, 'Hello', NULL, '2021-05-03 21:00:00', NULL, '2021-05-04 02:17:02', 1),
(9, 'Test', NULL, '2021-05-05 07:00:58', NULL, '2021-05-05 10:01:15', 1),
(10, '5.6.21', NULL, '2021-05-06 12:58:24', NULL, '2021-05-06 15:58:50', 1),
(11, '5.6.21b67', NULL, '2021-05-05 21:00:00', NULL, '2021-05-06 15:59:03', 1),
(12, 'Hello112233aaa', NULL, '2021-05-24 09:10:16', NULL, '2021-05-24 12:11:03', 2),
(14, 'hello11223345aa', NULL, '2021-05-23 21:00:00', NULL, '2021-05-24 12:14:38', 2),
(15, 'asas', NULL, '2021-07-02 09:28:27', NULL, '2021-07-02 17:39:38', 1);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `entity_asset_assignments`
--

CREATE TABLE `entity_asset_assignments` (
  `id` bigint(20) NOT NULL,
  `entity_id` bigint(20) NOT NULL,
  `asset_id` bigint(20) NOT NULL,
  `asset_prototype_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `entity_asset_assignments`
--

INSERT INTO `entity_asset_assignments` (`id`, `entity_id`, `asset_id`, `asset_prototype_id`) VALUES
(5, 6, 51, NULL),
(6, 6, 41, NULL),
(7, 6, 51, NULL),
(8, 7, 41, NULL),
(11, 6, 45, NULL),
(12, 9, 41, NULL),
(13, 9, 51, NULL),
(14, 8, 41, NULL),
(15, 10, 44, NULL),
(16, 10, 41, NULL),
(19, 11, 51, NULL),
(21, 11, 41, NULL),
(22, 12, 41, NULL),
(23, 12, 51, NULL),
(25, 14, 41, NULL),
(26, 14, 63, NULL);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `form`
--

CREATE TABLE `form` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `component_id` bigint(20) DEFAULT NULL,
  `version_id` varchar(255) DEFAULT NULL,
  `script` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `form`
--

INSERT INTO `form` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `name`, `component_id`, `version_id`, `script`) VALUES
(3, 0, '2021-03-28 12:02:38', 0, '2021-04-20 08:05:19', NULL, 44, 'Asset', 1, NULL, NULL),
(4, 1, '2021-04-15 11:48:27', 1, '2021-06-20 08:54:01', NULL, 224, 'Entity', 3, '90ac4de7-9f1b-42e5-8684-e51ae1a0c6d4', 'CmZ1bmN0aW9uIG5hdGl2ZUJ1dHRvbkNsaWNrSGFuZGxlcihidG5Db2RlKSB7CmlmKChidG5Db2RlID09ICdzZWxlY3RfYXNzZXQnKSAmJiAodHlwZW9mIGJ0bl9zZWxlY3RfYXNzZXRfY2xpY2sgPT0gImZ1bmN0aW9uIikpIGJ0bl9zZWxlY3RfYXNzZXRfY2xpY2soKTsKaWYoKGJ0bkNvZGUgPT0gJ3ByaW50UmVwb3J0JykgJiYgKHR5cGVvZiBidG5fcHJpbnRSZXBvcnRfY2xpY2sgPT0gImZ1bmN0aW9uIikpIGJ0bl9wcmludFJlcG9ydF9jbGljaygpOwppZigoYnRuQ29kZSA9PSAnVGVzdCcpICYmICh0eXBlb2YgYnRuX1Rlc3RfY2xpY2sgPT0gImZ1bmN0aW9uIikpIGJ0bl9UZXN0X2NsaWNrKCk7CmlmKChidG5Db2RlID09ICdhc3NldF9pbnRlcmZhY2VzJykgJiYgKHR5cGVvZiBidG5fYXNzZXRfaW50ZXJmYWNlc19jbGljayA9PSAiZnVuY3Rpb24iKSkgYnRuX2Fzc2V0X2ludGVyZmFjZXNfY2xpY2soKTsKaWYoKGJ0bkNvZGUgPT0gJ3Rlc3QnKSAmJiAodHlwZW9mIGJ0bl90ZXN0X2NsaWNrID09ICJmdW5jdGlvbiIpKSBidG5fdGVzdF9jbGljaygpOwppZigoYnRuQ29kZSA9PSAnY2FsbGJhY2sxJykgJiYgKHR5cGVvZiBidG5fY2FsbGJhY2sxX2NsaWNrID09ICJmdW5jdGlvbiIpKSBidG5fY2FsbGJhY2sxX2NsaWNrKCk7CmlmKChidG5Db2RlID09ICdjbG9zZV9hc3NldF9wb3B1cCcpICYmICh0eXBlb2YgYnRuX2Nsb3NlX2Fzc2V0X3BvcHVwX2NsaWNrID09ICJmdW5jdGlvbiIpKSBidG5fY2xvc2VfYXNzZXRfcG9wdXBfY2xpY2soKTsKfQoKZnVuY3Rpb24gbmF0aXZlRmllbGRFdmVudHNIYW5kbGVyKGVudGl0eUNvZGUsIGZpZWxkTmFtZSwgdHlwZSwgZXZlbnQpIHsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAna2V5ZG93bicpICYmICh0eXBlb2Ygb25fZW50aXR5X25hbWVfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X25hbWVfa2V5ZG93bihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2tleXVwJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfbmFtZV9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X25hbWVfa2V5dXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdkYmxjbGljaycpICYmICh0eXBlb2Ygb25fZW50aXR5X25hbWVfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9uYW1lX2RibGNsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnY2xpY2snKSAmJiAodHlwZW9mIG9uX2VudGl0eV9uYW1lX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfbmFtZV9jbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2NoYW5nZScpICYmICh0eXBlb2Ygb25fZW50aXR5X25hbWVfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfbmFtZV9jaGFuZ2UoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdmb2N1cycpICYmICh0eXBlb2Ygb25fZW50aXR5X25hbWVfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9uYW1lX2ZvY3VzKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZm9jdXNvdXQnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9uYW1lX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfbmFtZV9mb2N1c291dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RyYWcnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9uYW1lX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9uYW1lX2RyYWcoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdkcmFnZW5kJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfbmFtZV9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfbmFtZV9kcmFnZW5kKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2Vtb3ZlJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfbmFtZV9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9uYW1lX21vdXNlbW92ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNlb3V0JykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfbmFtZV9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X25hbWVfbW91c2VvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZW92ZXInKSAmJiAodHlwZW9mIG9uX2VudGl0eV9uYW1lX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X25hbWVfbW91c2VvdmVyKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2V1cCcpICYmICh0eXBlb2Ygb25fZW50aXR5X25hbWVfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X25hbWVfbW91c2V1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ3Jlc2l6ZScpICYmICh0eXBlb2Ygb25fZW50aXR5X25hbWVfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfbmFtZV9yZXNpemUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnY3JlYXRlZF9vbicpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfY3JlYXRlZF9vbl9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfY3JlYXRlZF9vbl9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2NyZWF0ZWRfb24nKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9jcmVhdGVkX29uX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfY3JlYXRlZF9vbl9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICdjcmVhdGVkX29uJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfY3JlYXRlZF9vbl9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2NyZWF0ZWRfb25fZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnY3JlYXRlZF9vbicpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fZW50aXR5X2NyZWF0ZWRfb25fY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9jcmVhdGVkX29uX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2NyZWF0ZWRfb24nKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfY3JlYXRlZF9vbl9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9jcmVhdGVkX29uX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICdjcmVhdGVkX29uJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfY3JlYXRlZF9vbl9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2NyZWF0ZWRfb25fZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnY3JlYXRlZF9vbicpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fZW50aXR5X2NyZWF0ZWRfb25fZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9jcmVhdGVkX29uX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2NyZWF0ZWRfb24nKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fZW50aXR5X2NyZWF0ZWRfb25fZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2NyZWF0ZWRfb25fZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICdjcmVhdGVkX29uJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9jcmVhdGVkX29uX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9jcmVhdGVkX29uX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnY3JlYXRlZF9vbicpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9jcmVhdGVkX29uX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2NyZWF0ZWRfb25fbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2NyZWF0ZWRfb24nKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9jcmVhdGVkX29uX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfY3JlYXRlZF9vbl9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICdjcmVhdGVkX29uJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fZW50aXR5X2NyZWF0ZWRfb25fbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfY3JlYXRlZF9vbl9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnY3JlYXRlZF9vbicpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfY3JlYXRlZF9vbl9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfY3JlYXRlZF9vbl9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2NyZWF0ZWRfb24nKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfY3JlYXRlZF9vbl9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9jcmVhdGVkX29uX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICd1c2VyX2lkJykgJiYgKHR5cGUgPT0gJ2tleWRvd24nKSAmJiAodHlwZW9mIG9uX2VudGl0eV91c2VyX2lkX2tleWRvd24gPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV91c2VyX2lkX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAndXNlcl9pZCcpICYmICh0eXBlID09ICdrZXl1cCcpICYmICh0eXBlb2Ygb25fZW50aXR5X3VzZXJfaWRfa2V5dXAgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV91c2VyX2lkX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ3VzZXJfaWQnKSAmJiAodHlwZSA9PSAnZGJsY2xpY2snKSAmJiAodHlwZW9mIG9uX2VudGl0eV91c2VyX2lkX2RibGNsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfdXNlcl9pZF9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICd1c2VyX2lkJykgJiYgKHR5cGUgPT0gJ2NsaWNrJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfdXNlcl9pZF9jbGljayA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X3VzZXJfaWRfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAndXNlcl9pZCcpICYmICh0eXBlID09ICdjaGFuZ2UnKSAmJiAodHlwZW9mIG9uX2VudGl0eV91c2VyX2lkX2NoYW5nZSA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X3VzZXJfaWRfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ3VzZXJfaWQnKSAmJiAodHlwZSA9PSAnZm9jdXMnKSAmJiAodHlwZW9mIG9uX2VudGl0eV91c2VyX2lkX2ZvY3VzID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfdXNlcl9pZF9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICd1c2VyX2lkJykgJiYgKHR5cGUgPT0gJ2ZvY3Vzb3V0JykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfdXNlcl9pZF9mb2N1c291dCA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X3VzZXJfaWRfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAndXNlcl9pZCcpICYmICh0eXBlID09ICdkcmFnJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfdXNlcl9pZF9kcmFnID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfdXNlcl9pZF9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ3VzZXJfaWQnKSAmJiAodHlwZSA9PSAnZHJhZ2VuZCcpICYmICh0eXBlb2Ygb25fZW50aXR5X3VzZXJfaWRfZHJhZ2VuZCA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X3VzZXJfaWRfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICd1c2VyX2lkJykgJiYgKHR5cGUgPT0gJ21vdXNlbW92ZScpICYmICh0eXBlb2Ygb25fZW50aXR5X3VzZXJfaWRfbW91c2Vtb3ZlID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfdXNlcl9pZF9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAndXNlcl9pZCcpICYmICh0eXBlID09ICdtb3VzZW91dCcpICYmICh0eXBlb2Ygb25fZW50aXR5X3VzZXJfaWRfbW91c2VvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV91c2VyX2lkX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ3VzZXJfaWQnKSAmJiAodHlwZSA9PSAnbW91c2VvdmVyJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfdXNlcl9pZF9tb3VzZW92ZXIgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV91c2VyX2lkX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICd1c2VyX2lkJykgJiYgKHR5cGUgPT0gJ21vdXNldXAnKSAmJiAodHlwZW9mIG9uX2VudGl0eV91c2VyX2lkX21vdXNldXAgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV91c2VyX2lkX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAndXNlcl9pZCcpICYmICh0eXBlID09ICdyZXNpemUnKSAmJiAodHlwZW9mIG9uX2VudGl0eV91c2VyX2lkX3Jlc2l6ZSA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X3VzZXJfaWRfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3VzZXInKSAmJiAoZmllbGROYW1lID09ICdlbWFpbCcpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl91c2VyX2VtYWlsX2tleWRvd24gPT0gImZ1bmN0aW9uIikpIG9uX3VzZXJfZW1haWxfa2V5ZG93bihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd1c2VyJykgJiYgKGZpZWxkTmFtZSA9PSAnZW1haWwnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX3VzZXJfZW1haWxfa2V5dXAgPT0gImZ1bmN0aW9uIikpIG9uX3VzZXJfZW1haWxfa2V5dXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndXNlcicpICYmIChmaWVsZE5hbWUgPT0gJ2VtYWlsJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl91c2VyX2VtYWlsX2RibGNsaWNrID09ICJmdW5jdGlvbiIpKSBvbl91c2VyX2VtYWlsX2RibGNsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3VzZXInKSAmJiAoZmllbGROYW1lID09ICdlbWFpbCcpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fdXNlcl9lbWFpbF9jbGljayA9PSAiZnVuY3Rpb24iKSkgb25fdXNlcl9lbWFpbF9jbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd1c2VyJykgJiYgKGZpZWxkTmFtZSA9PSAnZW1haWwnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl91c2VyX2VtYWlsX2NoYW5nZSA9PSAiZnVuY3Rpb24iKSkgb25fdXNlcl9lbWFpbF9jaGFuZ2UoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndXNlcicpICYmIChmaWVsZE5hbWUgPT0gJ2VtYWlsJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl91c2VyX2VtYWlsX2ZvY3VzID09ICJmdW5jdGlvbiIpKSBvbl91c2VyX2VtYWlsX2ZvY3VzKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3VzZXInKSAmJiAoZmllbGROYW1lID09ICdlbWFpbCcpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fdXNlcl9lbWFpbF9mb2N1c291dCA9PSAiZnVuY3Rpb24iKSkgb25fdXNlcl9lbWFpbF9mb2N1c291dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd1c2VyJykgJiYgKGZpZWxkTmFtZSA9PSAnZW1haWwnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fdXNlcl9lbWFpbF9kcmFnID09ICJmdW5jdGlvbiIpKSBvbl91c2VyX2VtYWlsX2RyYWcoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndXNlcicpICYmIChmaWVsZE5hbWUgPT0gJ2VtYWlsJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX3VzZXJfZW1haWxfZHJhZ2VuZCA9PSAiZnVuY3Rpb24iKSkgb25fdXNlcl9lbWFpbF9kcmFnZW5kKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3VzZXInKSAmJiAoZmllbGROYW1lID09ICdlbWFpbCcpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX3VzZXJfZW1haWxfbW91c2Vtb3ZlID09ICJmdW5jdGlvbiIpKSBvbl91c2VyX2VtYWlsX21vdXNlbW92ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd1c2VyJykgJiYgKGZpZWxkTmFtZSA9PSAnZW1haWwnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX3VzZXJfZW1haWxfbW91c2VvdXQgPT0gImZ1bmN0aW9uIikpIG9uX3VzZXJfZW1haWxfbW91c2VvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndXNlcicpICYmIChmaWVsZE5hbWUgPT0gJ2VtYWlsJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fdXNlcl9lbWFpbF9tb3VzZW92ZXIgPT0gImZ1bmN0aW9uIikpIG9uX3VzZXJfZW1haWxfbW91c2VvdmVyKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3VzZXInKSAmJiAoZmllbGROYW1lID09ICdlbWFpbCcpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl91c2VyX2VtYWlsX21vdXNldXAgPT0gImZ1bmN0aW9uIikpIG9uX3VzZXJfZW1haWxfbW91c2V1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd1c2VyJykgJiYgKGZpZWxkTmFtZSA9PSAnZW1haWwnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl91c2VyX2VtYWlsX3Jlc2l6ZSA9PSAiZnVuY3Rpb24iKSkgb25fdXNlcl9lbWFpbF9yZXNpemUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndXNlcicpICYmIChmaWVsZE5hbWUgPT0gJ3Bhc3N3b3JkJykgJiYgKHR5cGUgPT0gJ2tleWRvd24nKSAmJiAodHlwZW9mIG9uX3VzZXJfcGFzc3dvcmRfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25fdXNlcl9wYXNzd29yZF9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3VzZXInKSAmJiAoZmllbGROYW1lID09ICdwYXNzd29yZCcpICYmICh0eXBlID09ICdrZXl1cCcpICYmICh0eXBlb2Ygb25fdXNlcl9wYXNzd29yZF9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25fdXNlcl9wYXNzd29yZF9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd1c2VyJykgJiYgKGZpZWxkTmFtZSA9PSAncGFzc3dvcmQnKSAmJiAodHlwZSA9PSAnZGJsY2xpY2snKSAmJiAodHlwZW9mIG9uX3VzZXJfcGFzc3dvcmRfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX3VzZXJfcGFzc3dvcmRfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndXNlcicpICYmIChmaWVsZE5hbWUgPT0gJ3Bhc3N3b3JkJykgJiYgKHR5cGUgPT0gJ2NsaWNrJykgJiYgKHR5cGVvZiBvbl91c2VyX3Bhc3N3b3JkX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl91c2VyX3Bhc3N3b3JkX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3VzZXInKSAmJiAoZmllbGROYW1lID09ICdwYXNzd29yZCcpICYmICh0eXBlID09ICdjaGFuZ2UnKSAmJiAodHlwZW9mIG9uX3VzZXJfcGFzc3dvcmRfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl91c2VyX3Bhc3N3b3JkX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd1c2VyJykgJiYgKGZpZWxkTmFtZSA9PSAncGFzc3dvcmQnKSAmJiAodHlwZSA9PSAnZm9jdXMnKSAmJiAodHlwZW9mIG9uX3VzZXJfcGFzc3dvcmRfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX3VzZXJfcGFzc3dvcmRfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndXNlcicpICYmIChmaWVsZE5hbWUgPT0gJ3Bhc3N3b3JkJykgJiYgKHR5cGUgPT0gJ2ZvY3Vzb3V0JykgJiYgKHR5cGVvZiBvbl91c2VyX3Bhc3N3b3JkX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl91c2VyX3Bhc3N3b3JkX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3VzZXInKSAmJiAoZmllbGROYW1lID09ICdwYXNzd29yZCcpICYmICh0eXBlID09ICdkcmFnJykgJiYgKHR5cGVvZiBvbl91c2VyX3Bhc3N3b3JkX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX3VzZXJfcGFzc3dvcmRfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd1c2VyJykgJiYgKGZpZWxkTmFtZSA9PSAncGFzc3dvcmQnKSAmJiAodHlwZSA9PSAnZHJhZ2VuZCcpICYmICh0eXBlb2Ygb25fdXNlcl9wYXNzd29yZF9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl91c2VyX3Bhc3N3b3JkX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndXNlcicpICYmIChmaWVsZE5hbWUgPT0gJ3Bhc3N3b3JkJykgJiYgKHR5cGUgPT0gJ21vdXNlbW92ZScpICYmICh0eXBlb2Ygb25fdXNlcl9wYXNzd29yZF9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX3VzZXJfcGFzc3dvcmRfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3VzZXInKSAmJiAoZmllbGROYW1lID09ICdwYXNzd29yZCcpICYmICh0eXBlID09ICdtb3VzZW91dCcpICYmICh0eXBlb2Ygb25fdXNlcl9wYXNzd29yZF9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25fdXNlcl9wYXNzd29yZF9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd1c2VyJykgJiYgKGZpZWxkTmFtZSA9PSAncGFzc3dvcmQnKSAmJiAodHlwZSA9PSAnbW91c2VvdmVyJykgJiYgKHR5cGVvZiBvbl91c2VyX3Bhc3N3b3JkX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25fdXNlcl9wYXNzd29yZF9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndXNlcicpICYmIChmaWVsZE5hbWUgPT0gJ3Bhc3N3b3JkJykgJiYgKHR5cGUgPT0gJ21vdXNldXAnKSAmJiAodHlwZW9mIG9uX3VzZXJfcGFzc3dvcmRfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25fdXNlcl9wYXNzd29yZF9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3VzZXInKSAmJiAoZmllbGROYW1lID09ICdwYXNzd29yZCcpICYmICh0eXBlID09ICdyZXNpemUnKSAmJiAodHlwZW9mIG9uX3VzZXJfcGFzc3dvcmRfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl91c2VyX3Bhc3N3b3JkX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHlfYXNzZXRfYXNzaWdubWVudHMnKSAmJiAoZmllbGROYW1lID09ICdlbnRpdHlfaWQnKSAmJiAodHlwZSA9PSAna2V5ZG93bicpICYmICh0eXBlb2Ygb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2VudGl0eV9pZF9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfZW50aXR5X2lkX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzJykgJiYgKGZpZWxkTmFtZSA9PSAnZW50aXR5X2lkJykgJiYgKHR5cGUgPT0gJ2tleXVwJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfZW50aXR5X2lkX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfZW50aXR5X2lkX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eV9hc3NldF9hc3NpZ25tZW50cycpICYmIChmaWVsZE5hbWUgPT0gJ2VudGl0eV9pZCcpICYmICh0eXBlID09ICdkYmxjbGljaycpICYmICh0eXBlb2Ygb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2VudGl0eV9pZF9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2VudGl0eV9pZF9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHlfYXNzZXRfYXNzaWdubWVudHMnKSAmJiAoZmllbGROYW1lID09ICdlbnRpdHlfaWQnKSAmJiAodHlwZSA9PSAnY2xpY2snKSAmJiAodHlwZW9mIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19lbnRpdHlfaWRfY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19lbnRpdHlfaWRfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzJykgJiYgKGZpZWxkTmFtZSA9PSAnZW50aXR5X2lkJykgJiYgKHR5cGUgPT0gJ2NoYW5nZScpICYmICh0eXBlb2Ygb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2VudGl0eV9pZF9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19lbnRpdHlfaWRfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eV9hc3NldF9hc3NpZ25tZW50cycpICYmIChmaWVsZE5hbWUgPT0gJ2VudGl0eV9pZCcpICYmICh0eXBlID09ICdmb2N1cycpICYmICh0eXBlb2Ygb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2VudGl0eV9pZF9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2VudGl0eV9pZF9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHlfYXNzZXRfYXNzaWdubWVudHMnKSAmJiAoZmllbGROYW1lID09ICdlbnRpdHlfaWQnKSAmJiAodHlwZSA9PSAnZm9jdXNvdXQnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19lbnRpdHlfaWRfZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19lbnRpdHlfaWRfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzJykgJiYgKGZpZWxkTmFtZSA9PSAnZW50aXR5X2lkJykgJiYgKHR5cGUgPT0gJ2RyYWcnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19lbnRpdHlfaWRfZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2VudGl0eV9pZF9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eV9hc3NldF9hc3NpZ25tZW50cycpICYmIChmaWVsZE5hbWUgPT0gJ2VudGl0eV9pZCcpICYmICh0eXBlID09ICdkcmFnZW5kJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfZW50aXR5X2lkX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19lbnRpdHlfaWRfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHlfYXNzZXRfYXNzaWdubWVudHMnKSAmJiAoZmllbGROYW1lID09ICdlbnRpdHlfaWQnKSAmJiAodHlwZSA9PSAnbW91c2Vtb3ZlJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfZW50aXR5X2lkX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2VudGl0eV9pZF9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzJykgJiYgKGZpZWxkTmFtZSA9PSAnZW50aXR5X2lkJykgJiYgKHR5cGUgPT0gJ21vdXNlb3V0JykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfZW50aXR5X2lkX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfZW50aXR5X2lkX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eV9hc3NldF9hc3NpZ25tZW50cycpICYmIChmaWVsZE5hbWUgPT0gJ2VudGl0eV9pZCcpICYmICh0eXBlID09ICdtb3VzZW92ZXInKSAmJiAodHlwZW9mIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19lbnRpdHlfaWRfbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfZW50aXR5X2lkX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHlfYXNzZXRfYXNzaWdubWVudHMnKSAmJiAoZmllbGROYW1lID09ICdlbnRpdHlfaWQnKSAmJiAodHlwZSA9PSAnbW91c2V1cCcpICYmICh0eXBlb2Ygb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2VudGl0eV9pZF9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfZW50aXR5X2lkX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzJykgJiYgKGZpZWxkTmFtZSA9PSAnZW50aXR5X2lkJykgJiYgKHR5cGUgPT0gJ3Jlc2l6ZScpICYmICh0eXBlb2Ygb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2VudGl0eV9pZF9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19lbnRpdHlfaWRfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jb2RlX2tleWRvd24gPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NvZGVfa2V5ZG93bihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NvZGVfa2V5dXAgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NvZGVfa2V5dXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jb2RlX2RibGNsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jb2RlX2RibGNsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fYXNzZXRfY29kZV9jbGljayA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY29kZV9jbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jb2RlX2NoYW5nZSA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY29kZV9jaGFuZ2UoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jb2RlX2ZvY3VzID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jb2RlX2ZvY3VzKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fYXNzZXRfY29kZV9mb2N1c291dCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY29kZV9mb2N1c291dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fYXNzZXRfY29kZV9kcmFnID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jb2RlX2RyYWcoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NvZGVfZHJhZ2VuZCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY29kZV9kcmFnZW5kKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NvZGVfbW91c2Vtb3ZlID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jb2RlX21vdXNlbW92ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NvZGVfbW91c2VvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NvZGVfbW91c2VvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fYXNzZXRfY29kZV9tb3VzZW92ZXIgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NvZGVfbW91c2VvdmVyKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jb2RlX21vdXNldXAgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NvZGVfbW91c2V1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jb2RlX3Jlc2l6ZSA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY29kZV9yZXNpemUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2tleWRvd24nKSAmJiAodHlwZW9mIG9uX2Fzc2V0X25hbWVfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfbmFtZV9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdrZXl1cCcpICYmICh0eXBlb2Ygb25fYXNzZXRfbmFtZV9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfbmFtZV9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZGJsY2xpY2snKSAmJiAodHlwZW9mIG9uX2Fzc2V0X25hbWVfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X25hbWVfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2NsaWNrJykgJiYgKHR5cGVvZiBvbl9hc3NldF9uYW1lX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9uYW1lX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdjaGFuZ2UnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X25hbWVfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9uYW1lX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZm9jdXMnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X25hbWVfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X25hbWVfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2ZvY3Vzb3V0JykgJiYgKHR5cGVvZiBvbl9hc3NldF9uYW1lX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9uYW1lX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdkcmFnJykgJiYgKHR5cGVvZiBvbl9hc3NldF9uYW1lX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X25hbWVfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZHJhZ2VuZCcpICYmICh0eXBlb2Ygb25fYXNzZXRfbmFtZV9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9uYW1lX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNlbW92ZScpICYmICh0eXBlb2Ygb25fYXNzZXRfbmFtZV9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X25hbWVfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZW91dCcpICYmICh0eXBlb2Ygb25fYXNzZXRfbmFtZV9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfbmFtZV9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2VvdmVyJykgJiYgKHR5cGVvZiBvbl9hc3NldF9uYW1lX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfbmFtZV9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNldXAnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X25hbWVfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfbmFtZV9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdyZXNpemUnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X25hbWVfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9uYW1lX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHlfYXNzZXRfYXNzaWdubWVudHMnKSAmJiAoZmllbGROYW1lID09ICdhc3NldF9pZCcpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfYXNzZXRfaWRfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2Fzc2V0X2lkX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzJykgJiYgKGZpZWxkTmFtZSA9PSAnYXNzZXRfaWQnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19hc3NldF9pZF9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2Fzc2V0X2lkX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eV9hc3NldF9hc3NpZ25tZW50cycpICYmIChmaWVsZE5hbWUgPT0gJ2Fzc2V0X2lkJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfYXNzZXRfaWRfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19hc3NldF9pZF9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHlfYXNzZXRfYXNzaWdubWVudHMnKSAmJiAoZmllbGROYW1lID09ICdhc3NldF9pZCcpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2Fzc2V0X2lkX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfYXNzZXRfaWRfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzJykgJiYgKGZpZWxkTmFtZSA9PSAnYXNzZXRfaWQnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfYXNzZXRfaWRfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfYXNzZXRfaWRfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eV9hc3NldF9hc3NpZ25tZW50cycpICYmIChmaWVsZE5hbWUgPT0gJ2Fzc2V0X2lkJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfYXNzZXRfaWRfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19hc3NldF9pZF9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHlfYXNzZXRfYXNzaWdubWVudHMnKSAmJiAoZmllbGROYW1lID09ICdhc3NldF9pZCcpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2Fzc2V0X2lkX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfYXNzZXRfaWRfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzJykgJiYgKGZpZWxkTmFtZSA9PSAnYXNzZXRfaWQnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2Fzc2V0X2lkX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19hc3NldF9pZF9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eV9hc3NldF9hc3NpZ25tZW50cycpICYmIChmaWVsZE5hbWUgPT0gJ2Fzc2V0X2lkJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19hc3NldF9pZF9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfYXNzZXRfaWRfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHlfYXNzZXRfYXNzaWdubWVudHMnKSAmJiAoZmllbGROYW1lID09ICdhc3NldF9pZCcpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19hc3NldF9pZF9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19hc3NldF9pZF9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzJykgJiYgKGZpZWxkTmFtZSA9PSAnYXNzZXRfaWQnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9hc3NldF9hc3NpZ25tZW50c19hc3NldF9pZF9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2Fzc2V0X2lkX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eV9hc3NldF9hc3NpZ25tZW50cycpICYmIChmaWVsZE5hbWUgPT0gJ2Fzc2V0X2lkJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2Fzc2V0X2lkX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2Fzc2V0X2lkX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHlfYXNzZXRfYXNzaWdubWVudHMnKSAmJiAoZmllbGROYW1lID09ICdhc3NldF9pZCcpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfYXNzZXRfaWRfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzX2Fzc2V0X2lkX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5X2Fzc2V0X2Fzc2lnbm1lbnRzJykgJiYgKGZpZWxkTmFtZSA9PSAnYXNzZXRfaWQnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfYXNzZXRfaWRfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfYXNzZXRfYXNzaWdubWVudHNfYXNzZXRfaWRfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAna2V5ZG93bicpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX2NvZGVfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX2NvZGVfa2V5ZG93bihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2tleXVwJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfY29kZV9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX2NvZGVfa2V5dXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdkYmxjbGljaycpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX2NvZGVfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9jb2RlX2RibGNsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnY2xpY2snKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9jb2RlX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfY29kZV9jbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2NoYW5nZScpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX2NvZGVfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfY29kZV9jaGFuZ2UoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdmb2N1cycpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX2NvZGVfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9jb2RlX2ZvY3VzKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnZm9jdXNvdXQnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9jb2RlX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfY29kZV9mb2N1c291dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2RyYWcnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9jb2RlX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9jb2RlX2RyYWcoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdkcmFnZW5kJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfY29kZV9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfY29kZV9kcmFnZW5kKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbW91c2Vtb3ZlJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfY29kZV9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9jb2RlX21vdXNlbW92ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ21vdXNlb3V0JykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfY29kZV9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX2NvZGVfbW91c2VvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZW92ZXInKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9jb2RlX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX2NvZGVfbW91c2VvdmVyKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbW91c2V1cCcpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX2NvZGVfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX2NvZGVfbW91c2V1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ3Jlc2l6ZScpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX2NvZGVfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfY29kZV9yZXNpemUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfbmFtZV9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfbmFtZV9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9uYW1lX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfbmFtZV9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfbmFtZV9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX25hbWVfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX25hbWVfY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9uYW1lX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfbmFtZV9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9uYW1lX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfbmFtZV9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX25hbWVfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX25hbWVfZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9uYW1lX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX25hbWVfZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX25hbWVfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9uYW1lX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9uYW1lX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9uYW1lX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX25hbWVfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9uYW1lX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfbmFtZV9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX25hbWVfbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfbmFtZV9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfbmFtZV9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfbmFtZV9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfbmFtZV9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9uYW1lX3Jlc2l6ZShldmVudCk7Cn0KCnZhciBzZXRTZWxlY3RlZFRhYk51bWJlcjsKdmFyIHRleHRJbnB1dERpYWxvZzsKdmFyIHRleHREaWFsb2c7CnZhciBvcGVuUG9wdXA7CnZhciBjbG9zZVBvcHVwOwp2YXIgcHJpbnRSZXBvcnQ7CnZhciBkYXRhU2V0Owp2YXIgZ2V0RmllbGRWYWx1ZTsKdmFyIHNldEZpZWxkVmFsdWU7CgpmdW5jdGlvbiBkZWZpbmVTZWxlY3RlZFRhYk51bWJlckZ1bmN0aW9uKG15Q2FsbGJhY2spe3NldFNlbGVjdGVkVGFiTnVtYmVyID0gbXlDYWxsYmFjazt9CmZ1bmN0aW9uIGRlZmluZVNlbGVjdGVkVGV4dElucHV0RGlhbG9nKG15Q2FsbGJhY2spe3RleHRJbnB1dERpYWxvZyA9IG15Q2FsbGJhY2s7fQpmdW5jdGlvbiBkZWZpbmVTZWxlY3RlZFRleHREaWFsb2cobXlDYWxsYmFjayl7dGV4dERpYWxvZyA9IG15Q2FsbGJhY2s7fQpmdW5jdGlvbiBkZWZpbmVTZWxlY3RlZE9wZW5Qb3B1cERpYWxvZyhteUNhbGxiYWNrKXtvcGVuUG9wdXAgPSBteUNhbGxiYWNrO30KZnVuY3Rpb24gZGVmaW5lU2VsZWN0ZWRDbG9zZVBvcHVwRGlhbG9nKG15Q2FsbGJhY2spe2Nsb3NlUG9wdXAgPSBteUNhbGxiYWNrO30KZnVuY3Rpb24gZGVmaW5lUHJpbnRSZXBvcnQobXlDYWxsYmFjayl7cHJpbnRSZXBvcnQgPSBteUNhbGxiYWNrO30KZnVuY3Rpb24gZGVmaW5lRGF0YXNldChteURhdGFTZXQpe2RhdGFTZXQgPSBteURhdGFTZXQ7fQpmdW5jdGlvbiBkZWZpbmVHZXRGaWVsZFZhbHVlKG15Q2FsbGJhY2spe2dldEZpZWxkVmFsdWUgPSBteUNhbGxiYWNrO30KZnVuY3Rpb24gZGVmaW5lU2V0RmllbGRWYWx1ZShteUNhbGxiYWNrKXtzZXRGaWVsZFZhbHVlID0gbXlDYWxsYmFjazt9Cg==');
INSERT INTO `form` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `name`, `component_id`, `version_id`, `script`) VALUES
(6, 1, '2021-06-24 05:50:58', 1, '2021-07-09 09:21:00', NULL, 33, 'Asset Repository', 4, NULL, 'CmZ1bmN0aW9uIG5hdGl2ZUJ1dHRvbkNsaWNrSGFuZGxlcihidG5Db2RlKSB7CmlmKChidG5Db2RlID09ICdzZWxlY3RfaW50ZXJmYWNlJykgJiYgKHR5cGVvZiBidG5fc2VsZWN0X2ludGVyZmFjZV9jbGljayA9PSAiZnVuY3Rpb24iKSkgYnRuX3NlbGVjdF9pbnRlcmZhY2VfY2xpY2soKTsKaWYoKGJ0bkNvZGUgPT0gJ3NlbGVjdF92dWxuZXJhYmlsaXR5JykgJiYgKHR5cGVvZiBidG5fc2VsZWN0X3Z1bG5lcmFiaWxpdHlfY2xpY2sgPT0gImZ1bmN0aW9uIikpIGJ0bl9zZWxlY3RfdnVsbmVyYWJpbGl0eV9jbGljaygpOwppZigoYnRuQ29kZSA9PSAndmlld192dWxuZXJhYmlsaXRpZXMnKSAmJiAodHlwZW9mIGJ0bl92aWV3X3Z1bG5lcmFiaWxpdGllc19jbGljayA9PSAiZnVuY3Rpb24iKSkgYnRuX3ZpZXdfdnVsbmVyYWJpbGl0aWVzX2NsaWNrKCk7CmlmKChidG5Db2RlID09ICd2aWV3X3RocmVhdHMnKSAmJiAodHlwZW9mIGJ0bl92aWV3X3RocmVhdHNfY2xpY2sgPT0gImZ1bmN0aW9uIikpIGJ0bl92aWV3X3RocmVhdHNfY2xpY2soKTsKaWYoKGJ0bkNvZGUgPT0gJ2Nsb3NlX2ludGVyZmFjZV9wb3B1cCcpICYmICh0eXBlb2YgYnRuX2Nsb3NlX2ludGVyZmFjZV9wb3B1cF9jbGljayA9PSAiZnVuY3Rpb24iKSkgYnRuX2Nsb3NlX2ludGVyZmFjZV9wb3B1cF9jbGljaygpOwppZigoYnRuQ29kZSA9PSAnY2xvc2VfdnVsbmVyYWJpbGl0eV9wb3B1cCcpICYmICh0eXBlb2YgYnRuX2Nsb3NlX3Z1bG5lcmFiaWxpdHlfcG9wdXBfY2xpY2sgPT0gImZ1bmN0aW9uIikpIGJ0bl9jbG9zZV92dWxuZXJhYmlsaXR5X3BvcHVwX2NsaWNrKCk7Cn0KCmZ1bmN0aW9uIG5hdGl2ZUZpZWxkRXZlbnRzSGFuZGxlcihlbnRpdHlDb2RlLCBmaWVsZE5hbWUsIHR5cGUsIGV2ZW50KSB7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2tleWRvd24nKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9jb2RlX2tleWRvd24gPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9jb2RlX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdrZXl1cCcpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2NvZGVfa2V5dXAgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9jb2RlX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnZGJsY2xpY2snKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9jb2RlX2RibGNsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfY29kZV9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2NsaWNrJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfY29kZV9jbGljayA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2NvZGVfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdjaGFuZ2UnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9jb2RlX2NoYW5nZSA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2NvZGVfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnZm9jdXMnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9jb2RlX2ZvY3VzID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfY29kZV9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2ZvY3Vzb3V0JykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfY29kZV9mb2N1c291dCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2NvZGVfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdkcmFnJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfY29kZV9kcmFnID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfY29kZV9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnZHJhZ2VuZCcpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2NvZGVfZHJhZ2VuZCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2NvZGVfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ21vdXNlbW92ZScpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2NvZGVfbW91c2Vtb3ZlID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfY29kZV9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZW91dCcpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2NvZGVfbW91c2VvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9jb2RlX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbW91c2VvdmVyJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfY29kZV9tb3VzZW92ZXIgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9jb2RlX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ21vdXNldXAnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9jb2RlX21vdXNldXAgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9jb2RlX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdyZXNpemUnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9jb2RlX3Jlc2l6ZSA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2NvZGVfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbGlzdHNlbGVjdGVkJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfY29kZV9saXN0c2VsZWN0ZWQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9jb2RlX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2tleWRvd24nKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9uYW1lX2tleWRvd24gPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9uYW1lX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdrZXl1cCcpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X25hbWVfa2V5dXAgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9uYW1lX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZGJsY2xpY2snKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9uYW1lX2RibGNsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfbmFtZV9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2NsaWNrJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfbmFtZV9jbGljayA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X25hbWVfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdjaGFuZ2UnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9uYW1lX2NoYW5nZSA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X25hbWVfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZm9jdXMnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9uYW1lX2ZvY3VzID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfbmFtZV9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2ZvY3Vzb3V0JykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfbmFtZV9mb2N1c291dCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X25hbWVfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdkcmFnJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfbmFtZV9kcmFnID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfbmFtZV9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZHJhZ2VuZCcpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X25hbWVfZHJhZ2VuZCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X25hbWVfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNlbW92ZScpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X25hbWVfbW91c2Vtb3ZlID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfbmFtZV9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZW91dCcpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X25hbWVfbW91c2VvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9uYW1lX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2VvdmVyJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfbmFtZV9tb3VzZW92ZXIgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9uYW1lX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNldXAnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9uYW1lX21vdXNldXAgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9uYW1lX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdyZXNpemUnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9uYW1lX3Jlc2l6ZSA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X25hbWVfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbGlzdHNlbGVjdGVkJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfbmFtZV9saXN0c2VsZWN0ZWQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9uYW1lX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjcmVhdGVkX29uJykgJiYgKHR5cGUgPT0gJ2tleWRvd24nKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9jcmVhdGVkX29uX2tleWRvd24gPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9jcmVhdGVkX29uX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY3JlYXRlZF9vbicpICYmICh0eXBlID09ICdrZXl1cCcpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2NyZWF0ZWRfb25fa2V5dXAgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9jcmVhdGVkX29uX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NyZWF0ZWRfb24nKSAmJiAodHlwZSA9PSAnZGJsY2xpY2snKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9jcmVhdGVkX29uX2RibGNsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfY3JlYXRlZF9vbl9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjcmVhdGVkX29uJykgJiYgKHR5cGUgPT0gJ2NsaWNrJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfY3JlYXRlZF9vbl9jbGljayA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2NyZWF0ZWRfb25fY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY3JlYXRlZF9vbicpICYmICh0eXBlID09ICdjaGFuZ2UnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9jcmVhdGVkX29uX2NoYW5nZSA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2NyZWF0ZWRfb25fY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NyZWF0ZWRfb24nKSAmJiAodHlwZSA9PSAnZm9jdXMnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9jcmVhdGVkX29uX2ZvY3VzID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfY3JlYXRlZF9vbl9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjcmVhdGVkX29uJykgJiYgKHR5cGUgPT0gJ2ZvY3Vzb3V0JykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfY3JlYXRlZF9vbl9mb2N1c291dCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2NyZWF0ZWRfb25fZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY3JlYXRlZF9vbicpICYmICh0eXBlID09ICdkcmFnJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfY3JlYXRlZF9vbl9kcmFnID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfY3JlYXRlZF9vbl9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NyZWF0ZWRfb24nKSAmJiAodHlwZSA9PSAnZHJhZ2VuZCcpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2NyZWF0ZWRfb25fZHJhZ2VuZCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2NyZWF0ZWRfb25fZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjcmVhdGVkX29uJykgJiYgKHR5cGUgPT0gJ21vdXNlbW92ZScpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2NyZWF0ZWRfb25fbW91c2Vtb3ZlID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfY3JlYXRlZF9vbl9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY3JlYXRlZF9vbicpICYmICh0eXBlID09ICdtb3VzZW91dCcpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2NyZWF0ZWRfb25fbW91c2VvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9jcmVhdGVkX29uX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NyZWF0ZWRfb24nKSAmJiAodHlwZSA9PSAnbW91c2VvdmVyJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfY3JlYXRlZF9vbl9tb3VzZW92ZXIgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9jcmVhdGVkX29uX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjcmVhdGVkX29uJykgJiYgKHR5cGUgPT0gJ21vdXNldXAnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9jcmVhdGVkX29uX21vdXNldXAgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9jcmVhdGVkX29uX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY3JlYXRlZF9vbicpICYmICh0eXBlID09ICdyZXNpemUnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9jcmVhdGVkX29uX3Jlc2l6ZSA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2NyZWF0ZWRfb25fcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NyZWF0ZWRfb24nKSAmJiAodHlwZSA9PSAnbGlzdHNlbGVjdGVkJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfY3JlYXRlZF9vbl9saXN0c2VsZWN0ZWQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9jcmVhdGVkX29uX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdhc3NldF9jYXRlZ29yeV9pZCcpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfYXNzZXRfY2F0ZWdvcnlfaWRfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2Fzc2V0X2NhdGVnb3J5X2lkX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnYXNzZXRfY2F0ZWdvcnlfaWQnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9hc3NldF9jYXRlZ29yeV9pZF9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2Fzc2V0X2NhdGVnb3J5X2lkX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2Fzc2V0X2NhdGVnb3J5X2lkJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfYXNzZXRfY2F0ZWdvcnlfaWRfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9hc3NldF9jYXRlZ29yeV9pZF9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdhc3NldF9jYXRlZ29yeV9pZCcpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2Fzc2V0X2NhdGVnb3J5X2lkX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfYXNzZXRfY2F0ZWdvcnlfaWRfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnYXNzZXRfY2F0ZWdvcnlfaWQnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfYXNzZXRfY2F0ZWdvcnlfaWRfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfYXNzZXRfY2F0ZWdvcnlfaWRfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2Fzc2V0X2NhdGVnb3J5X2lkJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfYXNzZXRfY2F0ZWdvcnlfaWRfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9hc3NldF9jYXRlZ29yeV9pZF9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdhc3NldF9jYXRlZ29yeV9pZCcpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2Fzc2V0X2NhdGVnb3J5X2lkX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfYXNzZXRfY2F0ZWdvcnlfaWRfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnYXNzZXRfY2F0ZWdvcnlfaWQnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2Fzc2V0X2NhdGVnb3J5X2lkX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9hc3NldF9jYXRlZ29yeV9pZF9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2Fzc2V0X2NhdGVnb3J5X2lkJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9hc3NldF9jYXRlZ29yeV9pZF9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfYXNzZXRfY2F0ZWdvcnlfaWRfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdhc3NldF9jYXRlZ29yeV9pZCcpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9hc3NldF9jYXRlZ29yeV9pZF9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9hc3NldF9jYXRlZ29yeV9pZF9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnYXNzZXRfY2F0ZWdvcnlfaWQnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9hc3NldF9jYXRlZ29yeV9pZF9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2Fzc2V0X2NhdGVnb3J5X2lkX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2Fzc2V0X2NhdGVnb3J5X2lkJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2Fzc2V0X2NhdGVnb3J5X2lkX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2Fzc2V0X2NhdGVnb3J5X2lkX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdhc3NldF9jYXRlZ29yeV9pZCcpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfYXNzZXRfY2F0ZWdvcnlfaWRfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2Fzc2V0X2NhdGVnb3J5X2lkX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnYXNzZXRfY2F0ZWdvcnlfaWQnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfYXNzZXRfY2F0ZWdvcnlfaWRfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfYXNzZXRfY2F0ZWdvcnlfaWRfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2Fzc2V0X2NhdGVnb3J5X2lkJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2Fzc2V0X2NhdGVnb3J5X2lkX2xpc3RzZWxlY3RlZCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2Fzc2V0X2NhdGVnb3J5X2lkX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdidXNpbmVzc192YWx1ZScpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfYnVzaW5lc3NfdmFsdWVfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2J1c2luZXNzX3ZhbHVlX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnYnVzaW5lc3NfdmFsdWUnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9idXNpbmVzc192YWx1ZV9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2J1c2luZXNzX3ZhbHVlX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2J1c2luZXNzX3ZhbHVlJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfYnVzaW5lc3NfdmFsdWVfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9idXNpbmVzc192YWx1ZV9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdidXNpbmVzc192YWx1ZScpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2J1c2luZXNzX3ZhbHVlX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfYnVzaW5lc3NfdmFsdWVfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnYnVzaW5lc3NfdmFsdWUnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfYnVzaW5lc3NfdmFsdWVfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfYnVzaW5lc3NfdmFsdWVfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2J1c2luZXNzX3ZhbHVlJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfYnVzaW5lc3NfdmFsdWVfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9idXNpbmVzc192YWx1ZV9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdidXNpbmVzc192YWx1ZScpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2J1c2luZXNzX3ZhbHVlX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfYnVzaW5lc3NfdmFsdWVfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnYnVzaW5lc3NfdmFsdWUnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2J1c2luZXNzX3ZhbHVlX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9idXNpbmVzc192YWx1ZV9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2J1c2luZXNzX3ZhbHVlJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9idXNpbmVzc192YWx1ZV9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfYnVzaW5lc3NfdmFsdWVfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdidXNpbmVzc192YWx1ZScpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9idXNpbmVzc192YWx1ZV9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0UmVwb3NpdG9yeV9idXNpbmVzc192YWx1ZV9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnYnVzaW5lc3NfdmFsdWUnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX2Fzc2V0UmVwb3NpdG9yeV9idXNpbmVzc192YWx1ZV9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2J1c2luZXNzX3ZhbHVlX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2J1c2luZXNzX3ZhbHVlJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2J1c2luZXNzX3ZhbHVlX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2J1c2luZXNzX3ZhbHVlX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdidXNpbmVzc192YWx1ZScpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfYnVzaW5lc3NfdmFsdWVfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2J1c2luZXNzX3ZhbHVlX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnYnVzaW5lc3NfdmFsdWUnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl9hc3NldFJlcG9zaXRvcnlfYnVzaW5lc3NfdmFsdWVfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldFJlcG9zaXRvcnlfYnVzaW5lc3NfdmFsdWVfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2J1c2luZXNzX3ZhbHVlJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25fYXNzZXRSZXBvc2l0b3J5X2J1c2luZXNzX3ZhbHVlX2xpc3RzZWxlY3RlZCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRSZXBvc2l0b3J5X2J1c2luZXNzX3ZhbHVlX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2VSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2NvZGVfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9jb2RlX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlUmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfY29kZV9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9jb2RlX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2NvZGVfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfY29kZV9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2VSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9jb2RlX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2NvZGVfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlUmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2NvZGVfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2NvZGVfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2NvZGVfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfY29kZV9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2VSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9jb2RlX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2NvZGVfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlUmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9jb2RlX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfY29kZV9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfY29kZV9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2NvZGVfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2VSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfY29kZV9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfY29kZV9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlUmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfY29kZV9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9jb2RlX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9jb2RlX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9jb2RlX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2VSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2NvZGVfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9jb2RlX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlUmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2NvZGVfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2NvZGVfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9jb2RlX2xpc3RzZWxlY3RlZCA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9jb2RlX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2VSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X25hbWVfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9uYW1lX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlUmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfbmFtZV9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9uYW1lX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X25hbWVfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfbmFtZV9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2VSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9uYW1lX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X25hbWVfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlUmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X25hbWVfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X25hbWVfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X25hbWVfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfbmFtZV9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2VSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9uYW1lX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X25hbWVfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlUmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9uYW1lX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfbmFtZV9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfbmFtZV9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X25hbWVfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2VSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfbmFtZV9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfbmFtZV9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlUmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfbmFtZV9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9uYW1lX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9uYW1lX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9uYW1lX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2VSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X25hbWVfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9uYW1lX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlUmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X25hbWVfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X25hbWVfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9uYW1lX2xpc3RzZWxlY3RlZCA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9uYW1lX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAna2V5ZG93bicpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfY29kZV9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9jb2RlX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2tleXVwJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9jb2RlX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9jb2RlX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdkYmxjbGljaycpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfY29kZV9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfY29kZV9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnY2xpY2snKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2NvZGVfY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2NvZGVfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2NoYW5nZScpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfY29kZV9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2NvZGVfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdmb2N1cycpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfY29kZV9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfY29kZV9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnZm9jdXNvdXQnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2NvZGVfZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2NvZGVfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2RyYWcnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2NvZGVfZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfY29kZV9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdkcmFnZW5kJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9jb2RlX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2NvZGVfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbW91c2Vtb3ZlJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9jb2RlX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfY29kZV9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ21vdXNlb3V0JykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9jb2RlX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9jb2RlX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZW92ZXInKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2NvZGVfbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9jb2RlX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbW91c2V1cCcpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfY29kZV9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9jb2RlX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ3Jlc2l6ZScpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfY29kZV9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2NvZGVfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdsaXN0c2VsZWN0ZWQnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2NvZGVfbGlzdHNlbGVjdGVkID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9jb2RlX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAna2V5ZG93bicpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbmFtZV9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9uYW1lX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2tleXVwJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9uYW1lX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9uYW1lX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdkYmxjbGljaycpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbmFtZV9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbmFtZV9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnY2xpY2snKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X25hbWVfY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X25hbWVfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2NoYW5nZScpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbmFtZV9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X25hbWVfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdmb2N1cycpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbmFtZV9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbmFtZV9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZm9jdXNvdXQnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X25hbWVfZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X25hbWVfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RyYWcnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X25hbWVfZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbmFtZV9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdkcmFnZW5kJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9uYW1lX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X25hbWVfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2Vtb3ZlJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9uYW1lX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbmFtZV9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNlb3V0JykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9uYW1lX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9uYW1lX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZW92ZXInKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X25hbWVfbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9uYW1lX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2V1cCcpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbmFtZV9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9uYW1lX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ3Jlc2l6ZScpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbmFtZV9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X25hbWVfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdsaXN0c2VsZWN0ZWQnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X25hbWVfbGlzdHNlbGVjdGVkID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9uYW1lX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2VSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnZGVzY3JpcHRpb24nKSAmJiAodHlwZSA9PSAna2V5ZG93bicpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9kZXNjcmlwdGlvbl9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2Rlc2NyaXB0aW9uX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlUmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2Rlc2NyaXB0aW9uJykgJiYgKHR5cGUgPT0gJ2tleXVwJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2Rlc2NyaXB0aW9uX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2Rlc2NyaXB0aW9uX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdkZXNjcmlwdGlvbicpICYmICh0eXBlID09ICdkYmxjbGljaycpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9kZXNjcmlwdGlvbl9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9kZXNjcmlwdGlvbl9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2VSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnZGVzY3JpcHRpb24nKSAmJiAodHlwZSA9PSAnY2xpY2snKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfZGVzY3JpcHRpb25fY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfZGVzY3JpcHRpb25fY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlUmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2Rlc2NyaXB0aW9uJykgJiYgKHR5cGUgPT0gJ2NoYW5nZScpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9kZXNjcmlwdGlvbl9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfZGVzY3JpcHRpb25fY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdkZXNjcmlwdGlvbicpICYmICh0eXBlID09ICdmb2N1cycpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9kZXNjcmlwdGlvbl9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9kZXNjcmlwdGlvbl9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2VSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnZGVzY3JpcHRpb24nKSAmJiAodHlwZSA9PSAnZm9jdXNvdXQnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfZGVzY3JpcHRpb25fZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfZGVzY3JpcHRpb25fZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlUmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2Rlc2NyaXB0aW9uJykgJiYgKHR5cGUgPT0gJ2RyYWcnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfZGVzY3JpcHRpb25fZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9kZXNjcmlwdGlvbl9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdkZXNjcmlwdGlvbicpICYmICh0eXBlID09ICdkcmFnZW5kJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2Rlc2NyaXB0aW9uX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfZGVzY3JpcHRpb25fZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2VSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnZGVzY3JpcHRpb24nKSAmJiAodHlwZSA9PSAnbW91c2Vtb3ZlJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2Rlc2NyaXB0aW9uX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlUmVwb3NpdG9yeV9kZXNjcmlwdGlvbl9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlUmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2Rlc2NyaXB0aW9uJykgJiYgKHR5cGUgPT0gJ21vdXNlb3V0JykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2Rlc2NyaXB0aW9uX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2Rlc2NyaXB0aW9uX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdkZXNjcmlwdGlvbicpICYmICh0eXBlID09ICdtb3VzZW92ZXInKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfZGVzY3JpcHRpb25fbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2Rlc2NyaXB0aW9uX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2VSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnZGVzY3JpcHRpb24nKSAmJiAodHlwZSA9PSAnbW91c2V1cCcpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9kZXNjcmlwdGlvbl9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2Rlc2NyaXB0aW9uX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlUmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2Rlc2NyaXB0aW9uJykgJiYgKHR5cGUgPT0gJ3Jlc2l6ZScpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlUmVwb3NpdG9yeV9kZXNjcmlwdGlvbl9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfZGVzY3JpcHRpb25fcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdkZXNjcmlwdGlvbicpICYmICh0eXBlID09ICdsaXN0c2VsZWN0ZWQnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZVJlcG9zaXRvcnlfZGVzY3JpcHRpb25fbGlzdHNlbGVjdGVkID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VSZXBvc2l0b3J5X2Rlc2NyaXB0aW9uX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2xldmVsJykgJiYgKHR5cGUgPT0gJ2tleWRvd24nKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2xldmVsX2tleWRvd24gPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2xldmVsX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdsZXZlbCcpICYmICh0eXBlID09ICdrZXl1cCcpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbGV2ZWxfa2V5dXAgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2xldmVsX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbGV2ZWwnKSAmJiAodHlwZSA9PSAnZGJsY2xpY2snKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2xldmVsX2RibGNsaWNrID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9sZXZlbF9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2xldmVsJykgJiYgKHR5cGUgPT0gJ2NsaWNrJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9sZXZlbF9jbGljayA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbGV2ZWxfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdsZXZlbCcpICYmICh0eXBlID09ICdjaGFuZ2UnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2xldmVsX2NoYW5nZSA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbGV2ZWxfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbGV2ZWwnKSAmJiAodHlwZSA9PSAnZm9jdXMnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2xldmVsX2ZvY3VzID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9sZXZlbF9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2xldmVsJykgJiYgKHR5cGUgPT0gJ2ZvY3Vzb3V0JykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9sZXZlbF9mb2N1c291dCA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbGV2ZWxfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdsZXZlbCcpICYmICh0eXBlID09ICdkcmFnJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9sZXZlbF9kcmFnID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9sZXZlbF9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbGV2ZWwnKSAmJiAodHlwZSA9PSAnZHJhZ2VuZCcpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbGV2ZWxfZHJhZ2VuZCA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbGV2ZWxfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2xldmVsJykgJiYgKHR5cGUgPT0gJ21vdXNlbW92ZScpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbGV2ZWxfbW91c2Vtb3ZlID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9sZXZlbF9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdsZXZlbCcpICYmICh0eXBlID09ICdtb3VzZW91dCcpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbGV2ZWxfbW91c2VvdXQgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2xldmVsX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbGV2ZWwnKSAmJiAodHlwZSA9PSAnbW91c2VvdmVyJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9sZXZlbF9tb3VzZW92ZXIgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2xldmVsX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2xldmVsJykgJiYgKHR5cGUgPT0gJ21vdXNldXAnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2xldmVsX21vdXNldXAgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2xldmVsX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdsZXZlbCcpICYmICh0eXBlID09ICdyZXNpemUnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2xldmVsX3Jlc2l6ZSA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfbGV2ZWxfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbGV2ZWwnKSAmJiAodHlwZSA9PSAnbGlzdHNlbGVjdGVkJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9sZXZlbF9saXN0c2VsZWN0ZWQgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2xldmVsX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2ltcGFjdF9sZXZlbCcpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9pbXBhY3RfbGV2ZWxfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfaW1wYWN0X2xldmVsX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdpbXBhY3RfbGV2ZWwnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2ltcGFjdF9sZXZlbF9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfaW1wYWN0X2xldmVsX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnaW1wYWN0X2xldmVsJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9pbXBhY3RfbGV2ZWxfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2ltcGFjdF9sZXZlbF9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2ltcGFjdF9sZXZlbCcpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfaW1wYWN0X2xldmVsX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9pbXBhY3RfbGV2ZWxfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdpbXBhY3RfbGV2ZWwnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9pbXBhY3RfbGV2ZWxfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9pbXBhY3RfbGV2ZWxfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnaW1wYWN0X2xldmVsJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9pbXBhY3RfbGV2ZWxfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2ltcGFjdF9sZXZlbF9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2ltcGFjdF9sZXZlbCcpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfaW1wYWN0X2xldmVsX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9pbXBhY3RfbGV2ZWxfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdpbXBhY3RfbGV2ZWwnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfaW1wYWN0X2xldmVsX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2ltcGFjdF9sZXZlbF9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnaW1wYWN0X2xldmVsJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2ltcGFjdF9sZXZlbF9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9pbXBhY3RfbGV2ZWxfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2ltcGFjdF9sZXZlbCcpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2ltcGFjdF9sZXZlbF9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2ltcGFjdF9sZXZlbF9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdpbXBhY3RfbGV2ZWwnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5X2ltcGFjdF9sZXZlbF9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfaW1wYWN0X2xldmVsX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnaW1wYWN0X2xldmVsJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfaW1wYWN0X2xldmVsX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfaW1wYWN0X2xldmVsX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2ltcGFjdF9sZXZlbCcpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9pbXBhY3RfbGV2ZWxfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfaW1wYWN0X2xldmVsX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eVJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdpbXBhY3RfbGV2ZWwnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9pbXBhY3RfbGV2ZWxfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5UmVwb3NpdG9yeV9pbXBhY3RfbGV2ZWxfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHlSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnaW1wYWN0X2xldmVsJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfaW1wYWN0X2xldmVsX2xpc3RzZWxlY3RlZCA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eVJlcG9zaXRvcnlfaW1wYWN0X2xldmVsX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X2NvZGVfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9jb2RlX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX3RocmVhdFJlcG9zaXRvcnlfY29kZV9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9jb2RlX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X2NvZGVfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdFJlcG9zaXRvcnlfY29kZV9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fdGhyZWF0UmVwb3NpdG9yeV9jb2RlX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRSZXBvc2l0b3J5X2NvZGVfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X2NvZGVfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRSZXBvc2l0b3J5X2NvZGVfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X2NvZGVfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdFJlcG9zaXRvcnlfY29kZV9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fdGhyZWF0UmVwb3NpdG9yeV9jb2RlX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRSZXBvc2l0b3J5X2NvZGVfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fdGhyZWF0UmVwb3NpdG9yeV9jb2RlX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdFJlcG9zaXRvcnlfY29kZV9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX3RocmVhdFJlcG9zaXRvcnlfY29kZV9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRSZXBvc2l0b3J5X2NvZGVfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX3RocmVhdFJlcG9zaXRvcnlfY29kZV9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdFJlcG9zaXRvcnlfY29kZV9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX3RocmVhdFJlcG9zaXRvcnlfY29kZV9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9jb2RlX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fdGhyZWF0UmVwb3NpdG9yeV9jb2RlX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9jb2RlX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X2NvZGVfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9jb2RlX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X2NvZGVfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRSZXBvc2l0b3J5X2NvZGVfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25fdGhyZWF0UmVwb3NpdG9yeV9jb2RlX2xpc3RzZWxlY3RlZCA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9jb2RlX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X25hbWVfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9uYW1lX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX3RocmVhdFJlcG9zaXRvcnlfbmFtZV9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9uYW1lX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X25hbWVfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdFJlcG9zaXRvcnlfbmFtZV9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fdGhyZWF0UmVwb3NpdG9yeV9uYW1lX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRSZXBvc2l0b3J5X25hbWVfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X25hbWVfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRSZXBvc2l0b3J5X25hbWVfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X25hbWVfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdFJlcG9zaXRvcnlfbmFtZV9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fdGhyZWF0UmVwb3NpdG9yeV9uYW1lX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRSZXBvc2l0b3J5X25hbWVfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fdGhyZWF0UmVwb3NpdG9yeV9uYW1lX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdFJlcG9zaXRvcnlfbmFtZV9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX3RocmVhdFJlcG9zaXRvcnlfbmFtZV9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRSZXBvc2l0b3J5X25hbWVfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX3RocmVhdFJlcG9zaXRvcnlfbmFtZV9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdFJlcG9zaXRvcnlfbmFtZV9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX3RocmVhdFJlcG9zaXRvcnlfbmFtZV9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9uYW1lX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fdGhyZWF0UmVwb3NpdG9yeV9uYW1lX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9uYW1lX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X25hbWVfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9uYW1lX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X25hbWVfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRSZXBvc2l0b3J5X25hbWVfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25fdGhyZWF0UmVwb3NpdG9yeV9uYW1lX2xpc3RzZWxlY3RlZCA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9uYW1lX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnb2NjdXJyZW5jZV9wcm9iYWJpbGl0eScpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ29jY3VycmVuY2VfcHJvYmFiaWxpdHknKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX3RocmVhdFJlcG9zaXRvcnlfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdvY2N1cnJlbmNlX3Byb2JhYmlsaXR5JykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdFJlcG9zaXRvcnlfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnb2NjdXJyZW5jZV9wcm9iYWJpbGl0eScpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fdGhyZWF0UmVwb3NpdG9yeV9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRSZXBvc2l0b3J5X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ29jY3VycmVuY2VfcHJvYmFiaWxpdHknKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRSZXBvc2l0b3J5X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdvY2N1cnJlbmNlX3Byb2JhYmlsaXR5JykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdFJlcG9zaXRvcnlfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnb2NjdXJyZW5jZV9wcm9iYWJpbGl0eScpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fdGhyZWF0UmVwb3NpdG9yeV9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRSZXBvc2l0b3J5X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ29jY3VycmVuY2VfcHJvYmFiaWxpdHknKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fdGhyZWF0UmVwb3NpdG9yeV9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdFJlcG9zaXRvcnlfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdvY2N1cnJlbmNlX3Byb2JhYmlsaXR5JykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX3RocmVhdFJlcG9zaXRvcnlfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRSZXBvc2l0b3J5X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnb2NjdXJyZW5jZV9wcm9iYWJpbGl0eScpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX3RocmVhdFJlcG9zaXRvcnlfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdFJlcG9zaXRvcnlfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ29jY3VycmVuY2VfcHJvYmFiaWxpdHknKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX3RocmVhdFJlcG9zaXRvcnlfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdvY2N1cnJlbmNlX3Byb2JhYmlsaXR5JykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fdGhyZWF0UmVwb3NpdG9yeV9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXRSZXBvc2l0b3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnb2NjdXJyZW5jZV9wcm9iYWJpbGl0eScpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0UmVwb3NpdG9yeScpICYmIChmaWVsZE5hbWUgPT0gJ29jY3VycmVuY2VfcHJvYmFiaWxpdHknKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl90aHJlYXRSZXBvc2l0b3J5X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRSZXBvc2l0b3J5X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdFJlcG9zaXRvcnknKSAmJiAoZmllbGROYW1lID09ICdvY2N1cnJlbmNlX3Byb2JhYmlsaXR5JykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25fdGhyZWF0UmVwb3NpdG9yeV9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X2xpc3RzZWxlY3RlZCA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0UmVwb3NpdG9yeV9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X2xpc3RzZWxlY3RlZChldmVudCk7Cn0KCnZhciBzZXRTZWxlY3RlZFRhYk51bWJlcjsKdmFyIHRleHRJbnB1dERpYWxvZzsKdmFyIHRleHREaWFsb2c7CnZhciBvcGVuUG9wdXA7CnZhciBjbG9zZVBvcHVwOwp2YXIgcHJpbnRSZXBvcnQ7CnZhciBkYXRhU2V0Owp2YXIgZ2V0RmllbGRWYWx1ZTsKdmFyIHNldEZpZWxkVmFsdWU7CnZhciBnZXRDb21wb25lbnREYXRhOwp2YXIgYXBwZW5kTGluZVRvVGFibGU7CgpmdW5jdGlvbiBkZWZpbmVTZWxlY3RlZFRhYk51bWJlckZ1bmN0aW9uKG15Q2FsbGJhY2spe3NldFNlbGVjdGVkVGFiTnVtYmVyID0gbXlDYWxsYmFjazt9CmZ1bmN0aW9uIGRlZmluZVNlbGVjdGVkVGV4dElucHV0RGlhbG9nKG15Q2FsbGJhY2spe3RleHRJbnB1dERpYWxvZyA9IG15Q2FsbGJhY2s7fQpmdW5jdGlvbiBkZWZpbmVTZWxlY3RlZFRleHREaWFsb2cobXlDYWxsYmFjayl7dGV4dERpYWxvZyA9IG15Q2FsbGJhY2s7fQpmdW5jdGlvbiBkZWZpbmVTZWxlY3RlZE9wZW5Qb3B1cERpYWxvZyhteUNhbGxiYWNrKXtvcGVuUG9wdXAgPSBteUNhbGxiYWNrO30KZnVuY3Rpb24gZGVmaW5lU2VsZWN0ZWRDbG9zZVBvcHVwRGlhbG9nKG15Q2FsbGJhY2spe2Nsb3NlUG9wdXAgPSBteUNhbGxiYWNrO30KZnVuY3Rpb24gZGVmaW5lUHJpbnRSZXBvcnQobXlDYWxsYmFjayl7cHJpbnRSZXBvcnQgPSBteUNhbGxiYWNrO30KZnVuY3Rpb24gZGVmaW5lRGF0YXNldChteURhdGFTZXQpe2RhdGFTZXQgPSBteURhdGFTZXQ7fQpmdW5jdGlvbiBkZWZpbmVHZXRGaWVsZFZhbHVlKG15Q2FsbGJhY2spe2dldEZpZWxkVmFsdWUgPSBteUNhbGxiYWNrO30KZnVuY3Rpb24gZGVmaW5lU2V0RmllbGRWYWx1ZShteUNhbGxiYWNrKXtzZXRGaWVsZFZhbHVlID0gbXlDYWxsYmFjazt9CmZ1bmN0aW9uIGRlZmluZUdldENvbXBvbmVudERhdGEobXlDYWxsYmFjayl7Z2V0Q29tcG9uZW50RGF0YSA9IG15Q2FsbGJhY2s7fQpmdW5jdGlvbiBkZWZpbmVBcHBlbmRMaW5lVG9UYWJsZShteUNhbGxiYWNrKXthcHBlbmRMaW5lVG9UYWJsZSA9IG15Q2FsbGJhY2s7fQo=');
INSERT INTO `form` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `name`, `component_id`, `version_id`, `script`) VALUES
(7, 1, '2021-07-01 10:22:33', 1, '2021-07-06 09:24:51', NULL, 49, 'Entity Form', 8, NULL, 'CmZ1bmN0aW9uIG5hdGl2ZUJ1dHRvbkNsaWNrSGFuZGxlcihidG5Db2RlKSB7CmlmKChidG5Db2RlID09ICdzZWxlY3RfYXNzZXQnKSAmJiAodHlwZW9mIGJ0bl9zZWxlY3RfYXNzZXRfY2xpY2sgPT0gImZ1bmN0aW9uIikpIGJ0bl9zZWxlY3RfYXNzZXRfY2xpY2soKTsKaWYoKGJ0bkNvZGUgPT0gJ2Nsb3NlX2Fzc2V0X3BvcHVwJykgJiYgKHR5cGVvZiBidG5fY2xvc2VfYXNzZXRfcG9wdXBfY2xpY2sgPT0gImZ1bmN0aW9uIikpIGJ0bl9jbG9zZV9hc3NldF9wb3B1cF9jbGljaygpOwp9CgpmdW5jdGlvbiBuYXRpdmVGaWVsZEV2ZW50c0hhbmRsZXIoZW50aXR5Q29kZSwgZmllbGROYW1lLCB0eXBlLCBldmVudCkgewppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfbmFtZV9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfbmFtZV9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9uYW1lX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfbmFtZV9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfbmFtZV9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X25hbWVfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fZW50aXR5X25hbWVfY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9uYW1lX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfbmFtZV9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9uYW1lX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfbmFtZV9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X25hbWVfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fZW50aXR5X25hbWVfZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9uYW1lX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fZW50aXR5X25hbWVfZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X25hbWVfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9uYW1lX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9uYW1lX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9uYW1lX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X25hbWVfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9uYW1lX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfbmFtZV9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fZW50aXR5X25hbWVfbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfbmFtZV9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfbmFtZV9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfbmFtZV9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfbmFtZV9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9uYW1lX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25fZW50aXR5X25hbWVfbGlzdHNlbGVjdGVkID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfbmFtZV9saXN0c2VsZWN0ZWQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnY3JlYXRlZF9vbicpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfY3JlYXRlZF9vbl9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfY3JlYXRlZF9vbl9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2NyZWF0ZWRfb24nKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9jcmVhdGVkX29uX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfY3JlYXRlZF9vbl9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICdjcmVhdGVkX29uJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfY3JlYXRlZF9vbl9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2NyZWF0ZWRfb25fZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnY3JlYXRlZF9vbicpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fZW50aXR5X2NyZWF0ZWRfb25fY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9jcmVhdGVkX29uX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2NyZWF0ZWRfb24nKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfY3JlYXRlZF9vbl9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9jcmVhdGVkX29uX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICdjcmVhdGVkX29uJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfY3JlYXRlZF9vbl9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2NyZWF0ZWRfb25fZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnY3JlYXRlZF9vbicpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fZW50aXR5X2NyZWF0ZWRfb25fZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9jcmVhdGVkX29uX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2NyZWF0ZWRfb24nKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fZW50aXR5X2NyZWF0ZWRfb25fZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2NyZWF0ZWRfb25fZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICdjcmVhdGVkX29uJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9jcmVhdGVkX29uX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9jcmVhdGVkX29uX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnY3JlYXRlZF9vbicpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9jcmVhdGVkX29uX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X2NyZWF0ZWRfb25fbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2NyZWF0ZWRfb24nKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX2VudGl0eV9jcmVhdGVkX29uX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfY3JlYXRlZF9vbl9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICdjcmVhdGVkX29uJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fZW50aXR5X2NyZWF0ZWRfb25fbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfY3JlYXRlZF9vbl9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnY3JlYXRlZF9vbicpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfY3JlYXRlZF9vbl9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfY3JlYXRlZF9vbl9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2NyZWF0ZWRfb24nKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfY3JlYXRlZF9vbl9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV9jcmVhdGVkX29uX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICdjcmVhdGVkX29uJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25fZW50aXR5X2NyZWF0ZWRfb25fbGlzdHNlbGVjdGVkID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfY3JlYXRlZF9vbl9saXN0c2VsZWN0ZWQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAndXNlcl9pZCcpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfdXNlcl9pZF9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfdXNlcl9pZF9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ3VzZXJfaWQnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX2VudGl0eV91c2VyX2lkX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfdXNlcl9pZF9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICd1c2VyX2lkJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfdXNlcl9pZF9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X3VzZXJfaWRfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAndXNlcl9pZCcpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fZW50aXR5X3VzZXJfaWRfY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV91c2VyX2lkX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ3VzZXJfaWQnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfdXNlcl9pZF9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV91c2VyX2lkX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICd1c2VyX2lkJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfdXNlcl9pZF9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X3VzZXJfaWRfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAndXNlcl9pZCcpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fZW50aXR5X3VzZXJfaWRfZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV91c2VyX2lkX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ3VzZXJfaWQnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fZW50aXR5X3VzZXJfaWRfZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X3VzZXJfaWRfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICd1c2VyX2lkJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX2VudGl0eV91c2VyX2lkX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV91c2VyX2lkX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAndXNlcl9pZCcpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX2VudGl0eV91c2VyX2lkX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25fZW50aXR5X3VzZXJfaWRfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ3VzZXJfaWQnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX2VudGl0eV91c2VyX2lkX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfdXNlcl9pZF9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICd1c2VyX2lkJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fZW50aXR5X3VzZXJfaWRfbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfdXNlcl9pZF9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnZW50aXR5JykgJiYgKGZpZWxkTmFtZSA9PSAndXNlcl9pZCcpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfdXNlcl9pZF9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfdXNlcl9pZF9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2VudGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ3VzZXJfaWQnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl9lbnRpdHlfdXNlcl9pZF9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX2VudGl0eV91c2VyX2lkX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdlbnRpdHknKSAmJiAoZmllbGROYW1lID09ICd1c2VyX2lkJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25fZW50aXR5X3VzZXJfaWRfbGlzdHNlbGVjdGVkID09ICJmdW5jdGlvbiIpKSBvbl9lbnRpdHlfdXNlcl9pZF9saXN0c2VsZWN0ZWQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2tleWRvd24nKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NvZGVfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY29kZV9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdrZXl1cCcpICYmICh0eXBlb2Ygb25fYXNzZXRfY29kZV9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY29kZV9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnZGJsY2xpY2snKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NvZGVfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NvZGVfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2NsaWNrJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jb2RlX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jb2RlX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdjaGFuZ2UnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NvZGVfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jb2RlX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnZm9jdXMnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NvZGVfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NvZGVfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2ZvY3Vzb3V0JykgJiYgKHR5cGVvZiBvbl9hc3NldF9jb2RlX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jb2RlX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdkcmFnJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jb2RlX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NvZGVfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnZHJhZ2VuZCcpICYmICh0eXBlb2Ygb25fYXNzZXRfY29kZV9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jb2RlX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ21vdXNlbW92ZScpICYmICh0eXBlb2Ygb25fYXNzZXRfY29kZV9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NvZGVfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZW91dCcpICYmICh0eXBlb2Ygb25fYXNzZXRfY29kZV9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY29kZV9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbW91c2VvdmVyJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jb2RlX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY29kZV9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ21vdXNldXAnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NvZGVfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY29kZV9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdyZXNpemUnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NvZGVfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jb2RlX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbGlzdHNlbGVjdGVkJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jb2RlX2xpc3RzZWxlY3RlZCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY29kZV9saXN0c2VsZWN0ZWQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2tleWRvd24nKSAmJiAodHlwZW9mIG9uX2Fzc2V0X25hbWVfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfbmFtZV9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdrZXl1cCcpICYmICh0eXBlb2Ygb25fYXNzZXRfbmFtZV9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfbmFtZV9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZGJsY2xpY2snKSAmJiAodHlwZW9mIG9uX2Fzc2V0X25hbWVfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X25hbWVfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2NsaWNrJykgJiYgKHR5cGVvZiBvbl9hc3NldF9uYW1lX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9uYW1lX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdjaGFuZ2UnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X25hbWVfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9uYW1lX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZm9jdXMnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X25hbWVfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X25hbWVfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2ZvY3Vzb3V0JykgJiYgKHR5cGVvZiBvbl9hc3NldF9uYW1lX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9uYW1lX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdkcmFnJykgJiYgKHR5cGVvZiBvbl9hc3NldF9uYW1lX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X25hbWVfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZHJhZ2VuZCcpICYmICh0eXBlb2Ygb25fYXNzZXRfbmFtZV9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9uYW1lX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNlbW92ZScpICYmICh0eXBlb2Ygb25fYXNzZXRfbmFtZV9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X25hbWVfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZW91dCcpICYmICh0eXBlb2Ygb25fYXNzZXRfbmFtZV9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfbmFtZV9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2VvdmVyJykgJiYgKHR5cGVvZiBvbl9hc3NldF9uYW1lX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfbmFtZV9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNldXAnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X25hbWVfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfbmFtZV9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdyZXNpemUnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X25hbWVfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9uYW1lX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbGlzdHNlbGVjdGVkJykgJiYgKHR5cGVvZiBvbl9hc3NldF9uYW1lX2xpc3RzZWxlY3RlZCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfbmFtZV9saXN0c2VsZWN0ZWQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdhc3NldF9yZXBvc2l0b3J5X2lkJykgJiYgKHR5cGUgPT0gJ2tleWRvd24nKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2Fzc2V0X3JlcG9zaXRvcnlfaWRfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfYXNzZXRfcmVwb3NpdG9yeV9pZF9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnYXNzZXRfcmVwb3NpdG9yeV9pZCcpICYmICh0eXBlID09ICdrZXl1cCcpICYmICh0eXBlb2Ygb25fYXNzZXRfYXNzZXRfcmVwb3NpdG9yeV9pZF9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfYXNzZXRfcmVwb3NpdG9yeV9pZF9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2Fzc2V0X3JlcG9zaXRvcnlfaWQnKSAmJiAodHlwZSA9PSAnZGJsY2xpY2snKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2Fzc2V0X3JlcG9zaXRvcnlfaWRfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2Fzc2V0X3JlcG9zaXRvcnlfaWRfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdhc3NldF9yZXBvc2l0b3J5X2lkJykgJiYgKHR5cGUgPT0gJ2NsaWNrJykgJiYgKHR5cGVvZiBvbl9hc3NldF9hc3NldF9yZXBvc2l0b3J5X2lkX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9hc3NldF9yZXBvc2l0b3J5X2lkX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnYXNzZXRfcmVwb3NpdG9yeV9pZCcpICYmICh0eXBlID09ICdjaGFuZ2UnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2Fzc2V0X3JlcG9zaXRvcnlfaWRfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9hc3NldF9yZXBvc2l0b3J5X2lkX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2Fzc2V0X3JlcG9zaXRvcnlfaWQnKSAmJiAodHlwZSA9PSAnZm9jdXMnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2Fzc2V0X3JlcG9zaXRvcnlfaWRfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2Fzc2V0X3JlcG9zaXRvcnlfaWRfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdhc3NldF9yZXBvc2l0b3J5X2lkJykgJiYgKHR5cGUgPT0gJ2ZvY3Vzb3V0JykgJiYgKHR5cGVvZiBvbl9hc3NldF9hc3NldF9yZXBvc2l0b3J5X2lkX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9hc3NldF9yZXBvc2l0b3J5X2lkX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnYXNzZXRfcmVwb3NpdG9yeV9pZCcpICYmICh0eXBlID09ICdkcmFnJykgJiYgKHR5cGVvZiBvbl9hc3NldF9hc3NldF9yZXBvc2l0b3J5X2lkX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2Fzc2V0X3JlcG9zaXRvcnlfaWRfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2Fzc2V0X3JlcG9zaXRvcnlfaWQnKSAmJiAodHlwZSA9PSAnZHJhZ2VuZCcpICYmICh0eXBlb2Ygb25fYXNzZXRfYXNzZXRfcmVwb3NpdG9yeV9pZF9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9hc3NldF9yZXBvc2l0b3J5X2lkX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdhc3NldF9yZXBvc2l0b3J5X2lkJykgJiYgKHR5cGUgPT0gJ21vdXNlbW92ZScpICYmICh0eXBlb2Ygb25fYXNzZXRfYXNzZXRfcmVwb3NpdG9yeV9pZF9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2Fzc2V0X3JlcG9zaXRvcnlfaWRfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnYXNzZXRfcmVwb3NpdG9yeV9pZCcpICYmICh0eXBlID09ICdtb3VzZW91dCcpICYmICh0eXBlb2Ygb25fYXNzZXRfYXNzZXRfcmVwb3NpdG9yeV9pZF9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfYXNzZXRfcmVwb3NpdG9yeV9pZF9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2Fzc2V0X3JlcG9zaXRvcnlfaWQnKSAmJiAodHlwZSA9PSAnbW91c2VvdmVyJykgJiYgKHR5cGVvZiBvbl9hc3NldF9hc3NldF9yZXBvc2l0b3J5X2lkX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfYXNzZXRfcmVwb3NpdG9yeV9pZF9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdhc3NldF9yZXBvc2l0b3J5X2lkJykgJiYgKHR5cGUgPT0gJ21vdXNldXAnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2Fzc2V0X3JlcG9zaXRvcnlfaWRfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfYXNzZXRfcmVwb3NpdG9yeV9pZF9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnYXNzZXRfcmVwb3NpdG9yeV9pZCcpICYmICh0eXBlID09ICdyZXNpemUnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2Fzc2V0X3JlcG9zaXRvcnlfaWRfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9hc3NldF9yZXBvc2l0b3J5X2lkX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2Fzc2V0X3JlcG9zaXRvcnlfaWQnKSAmJiAodHlwZSA9PSAnbGlzdHNlbGVjdGVkJykgJiYgKHR5cGVvZiBvbl9hc3NldF9hc3NldF9yZXBvc2l0b3J5X2lkX2xpc3RzZWxlY3RlZCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfYXNzZXRfcmVwb3NpdG9yeV9pZF9saXN0c2VsZWN0ZWQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdkZXNjcmlwdGlvbicpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl9hc3NldF9kZXNjcmlwdGlvbl9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9kZXNjcmlwdGlvbl9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnZGVzY3JpcHRpb24nKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2Rlc2NyaXB0aW9uX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9kZXNjcmlwdGlvbl9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2Rlc2NyaXB0aW9uJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl9hc3NldF9kZXNjcmlwdGlvbl9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfZGVzY3JpcHRpb25fZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdkZXNjcmlwdGlvbicpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fYXNzZXRfZGVzY3JpcHRpb25fY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2Rlc2NyaXB0aW9uX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnZGVzY3JpcHRpb24nKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl9hc3NldF9kZXNjcmlwdGlvbl9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2Rlc2NyaXB0aW9uX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2Rlc2NyaXB0aW9uJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl9hc3NldF9kZXNjcmlwdGlvbl9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfZGVzY3JpcHRpb25fZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdkZXNjcmlwdGlvbicpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fYXNzZXRfZGVzY3JpcHRpb25fZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2Rlc2NyaXB0aW9uX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnZGVzY3JpcHRpb24nKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fYXNzZXRfZGVzY3JpcHRpb25fZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfZGVzY3JpcHRpb25fZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2Rlc2NyaXB0aW9uJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2Rlc2NyaXB0aW9uX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2Rlc2NyaXB0aW9uX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdkZXNjcmlwdGlvbicpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2Rlc2NyaXB0aW9uX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfZGVzY3JpcHRpb25fbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnZGVzY3JpcHRpb24nKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2Rlc2NyaXB0aW9uX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9kZXNjcmlwdGlvbl9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2Rlc2NyaXB0aW9uJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fYXNzZXRfZGVzY3JpcHRpb25fbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9kZXNjcmlwdGlvbl9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXQnKSAmJiAoZmllbGROYW1lID09ICdkZXNjcmlwdGlvbicpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl9hc3NldF9kZXNjcmlwdGlvbl9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9kZXNjcmlwdGlvbl9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0JykgJiYgKGZpZWxkTmFtZSA9PSAnZGVzY3JpcHRpb24nKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl9hc3NldF9kZXNjcmlwdGlvbl9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2Rlc2NyaXB0aW9uX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldCcpICYmIChmaWVsZE5hbWUgPT0gJ2Rlc2NyaXB0aW9uJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25fYXNzZXRfZGVzY3JpcHRpb25fbGlzdHNlbGVjdGVkID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9kZXNjcmlwdGlvbl9saXN0c2VsZWN0ZWQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfY29kZV9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfY29kZV9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9jb2RlX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfY29kZV9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfY29kZV9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX2NvZGVfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX2NvZGVfY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9jb2RlX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfY29kZV9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9jb2RlX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfY29kZV9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX2NvZGVfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX2NvZGVfZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9jb2RlX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX2NvZGVfZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX2NvZGVfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9jb2RlX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9jb2RlX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9jb2RlX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX2NvZGVfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9jb2RlX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfY29kZV9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX2NvZGVfbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfY29kZV9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfY29kZV9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfY29kZV9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfY29kZV9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9jb2RlX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX2NvZGVfbGlzdHNlbGVjdGVkID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfY29kZV9saXN0c2VsZWN0ZWQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfbmFtZV9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfbmFtZV9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9uYW1lX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfbmFtZV9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfbmFtZV9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX25hbWVfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX25hbWVfY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9uYW1lX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfbmFtZV9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9uYW1lX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfbmFtZV9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX25hbWVfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX25hbWVfZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9uYW1lX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX25hbWVfZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX25hbWVfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9uYW1lX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9uYW1lX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9uYW1lX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25faW50ZXJmYWNlX25hbWVfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX2ludGVyZmFjZV9uYW1lX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfbmFtZV9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX25hbWVfbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfbmFtZV9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnaW50ZXJmYWNlJykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfbmFtZV9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfbmFtZV9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2ludGVyZmFjZScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl9pbnRlcmZhY2VfbmFtZV9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX2ludGVyZmFjZV9uYW1lX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdpbnRlcmZhY2UnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25faW50ZXJmYWNlX25hbWVfbGlzdHNlbGVjdGVkID09ICJmdW5jdGlvbiIpKSBvbl9pbnRlcmZhY2VfbmFtZV9saXN0c2VsZWN0ZWQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAna2V5ZG93bicpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9jb2RlX2tleWRvd24gPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfY29kZV9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2tleXVwJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X2NvZGVfa2V5dXAgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfY29kZV9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdkYmxjbGljaycpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9jb2RlX2RibGNsaWNrID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X2NvZGVfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnY2xpY2snKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfY29kZV9jbGljayA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9jb2RlX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2NoYW5nZScpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9jb2RlX2NoYW5nZSA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9jb2RlX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdmb2N1cycpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9jb2RlX2ZvY3VzID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X2NvZGVfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnZm9jdXNvdXQnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfY29kZV9mb2N1c291dCA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9jb2RlX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2RyYWcnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfY29kZV9kcmFnID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X2NvZGVfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdkcmFnZW5kJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X2NvZGVfZHJhZ2VuZCA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9jb2RlX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbW91c2Vtb3ZlJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X2NvZGVfbW91c2Vtb3ZlID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X2NvZGVfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ21vdXNlb3V0JykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X2NvZGVfbW91c2VvdXQgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfY29kZV9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZW92ZXInKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfY29kZV9tb3VzZW92ZXIgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfY29kZV9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbW91c2V1cCcpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9jb2RlX21vdXNldXAgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfY29kZV9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ3Jlc2l6ZScpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9jb2RlX3Jlc2l6ZSA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9jb2RlX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdsaXN0c2VsZWN0ZWQnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfY29kZV9saXN0c2VsZWN0ZWQgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfY29kZV9saXN0c2VsZWN0ZWQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAna2V5ZG93bicpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9uYW1lX2tleWRvd24gPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfbmFtZV9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2tleXVwJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X25hbWVfa2V5dXAgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfbmFtZV9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdkYmxjbGljaycpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9uYW1lX2RibGNsaWNrID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X25hbWVfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnY2xpY2snKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfbmFtZV9jbGljayA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9uYW1lX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2NoYW5nZScpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9uYW1lX2NoYW5nZSA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9uYW1lX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdmb2N1cycpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9uYW1lX2ZvY3VzID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X25hbWVfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZm9jdXNvdXQnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfbmFtZV9mb2N1c291dCA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9uYW1lX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RyYWcnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfbmFtZV9kcmFnID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X25hbWVfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdkcmFnZW5kJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X25hbWVfZHJhZ2VuZCA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9uYW1lX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2Vtb3ZlJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X25hbWVfbW91c2Vtb3ZlID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X25hbWVfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNlb3V0JykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X25hbWVfbW91c2VvdXQgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfbmFtZV9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZW92ZXInKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfbmFtZV9tb3VzZW92ZXIgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfbmFtZV9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2V1cCcpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9uYW1lX21vdXNldXAgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfbmFtZV9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ3Jlc2l6ZScpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9uYW1lX3Jlc2l6ZSA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9uYW1lX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdsaXN0c2VsZWN0ZWQnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfbmFtZV9saXN0c2VsZWN0ZWQgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfbmFtZV9saXN0c2VsZWN0ZWQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2xldmVsJykgJiYgKHR5cGUgPT0gJ2tleWRvd24nKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfbGV2ZWxfa2V5ZG93biA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9sZXZlbF9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICdsZXZlbCcpICYmICh0eXBlID09ICdrZXl1cCcpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9sZXZlbF9rZXl1cCA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9sZXZlbF9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbGV2ZWwnKSAmJiAodHlwZSA9PSAnZGJsY2xpY2snKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfbGV2ZWxfZGJsY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfbGV2ZWxfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2xldmVsJykgJiYgKHR5cGUgPT0gJ2NsaWNrJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X2xldmVsX2NsaWNrID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X2xldmVsX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICdsZXZlbCcpICYmICh0eXBlID09ICdjaGFuZ2UnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfbGV2ZWxfY2hhbmdlID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X2xldmVsX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbGV2ZWwnKSAmJiAodHlwZSA9PSAnZm9jdXMnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfbGV2ZWxfZm9jdXMgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfbGV2ZWxfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2xldmVsJykgJiYgKHR5cGUgPT0gJ2ZvY3Vzb3V0JykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X2xldmVsX2ZvY3Vzb3V0ID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X2xldmVsX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICdsZXZlbCcpICYmICh0eXBlID09ICdkcmFnJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X2xldmVsX2RyYWcgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfbGV2ZWxfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbGV2ZWwnKSAmJiAodHlwZSA9PSAnZHJhZ2VuZCcpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9sZXZlbF9kcmFnZW5kID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X2xldmVsX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2xldmVsJykgJiYgKHR5cGUgPT0gJ21vdXNlbW92ZScpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9sZXZlbF9tb3VzZW1vdmUgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfbGV2ZWxfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICdsZXZlbCcpICYmICh0eXBlID09ICdtb3VzZW91dCcpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9sZXZlbF9tb3VzZW91dCA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9sZXZlbF9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbGV2ZWwnKSAmJiAodHlwZSA9PSAnbW91c2VvdmVyJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X2xldmVsX21vdXNlb3ZlciA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9sZXZlbF9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2xldmVsJykgJiYgKHR5cGUgPT0gJ21vdXNldXAnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfbGV2ZWxfbW91c2V1cCA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9sZXZlbF9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICdsZXZlbCcpICYmICh0eXBlID09ICdyZXNpemUnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfbGV2ZWxfcmVzaXplID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X2xldmVsX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnbGV2ZWwnKSAmJiAodHlwZSA9PSAnbGlzdHNlbGVjdGVkJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X2xldmVsX2xpc3RzZWxlY3RlZCA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9sZXZlbF9saXN0c2VsZWN0ZWQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2ltcGFjdF9sZXZlbCcpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X2ltcGFjdF9sZXZlbF9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X2ltcGFjdF9sZXZlbF9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICdpbXBhY3RfbGV2ZWwnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfaW1wYWN0X2xldmVsX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X2ltcGFjdF9sZXZlbF9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnaW1wYWN0X2xldmVsJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X2ltcGFjdF9sZXZlbF9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9pbXBhY3RfbGV2ZWxfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2ltcGFjdF9sZXZlbCcpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9pbXBhY3RfbGV2ZWxfY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfaW1wYWN0X2xldmVsX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICdpbXBhY3RfbGV2ZWwnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X2ltcGFjdF9sZXZlbF9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfaW1wYWN0X2xldmVsX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnaW1wYWN0X2xldmVsJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X2ltcGFjdF9sZXZlbF9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9pbXBhY3RfbGV2ZWxfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2ltcGFjdF9sZXZlbCcpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9pbXBhY3RfbGV2ZWxfZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfaW1wYWN0X2xldmVsX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICdpbXBhY3RfbGV2ZWwnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9pbXBhY3RfbGV2ZWxfZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9pbXBhY3RfbGV2ZWxfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnaW1wYWN0X2xldmVsJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfaW1wYWN0X2xldmVsX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfaW1wYWN0X2xldmVsX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2ltcGFjdF9sZXZlbCcpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfaW1wYWN0X2xldmVsX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25fdnVsbmVyYWJpbGl0eV9pbXBhY3RfbGV2ZWxfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICdpbXBhY3RfbGV2ZWwnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX3Z1bG5lcmFiaWxpdHlfaW1wYWN0X2xldmVsX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X2ltcGFjdF9sZXZlbF9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnaW1wYWN0X2xldmVsJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9pbXBhY3RfbGV2ZWxfbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X2ltcGFjdF9sZXZlbF9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndnVsbmVyYWJpbGl0eScpICYmIChmaWVsZE5hbWUgPT0gJ2ltcGFjdF9sZXZlbCcpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X2ltcGFjdF9sZXZlbF9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X2ltcGFjdF9sZXZlbF9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3Z1bG5lcmFiaWxpdHknKSAmJiAoZmllbGROYW1lID09ICdpbXBhY3RfbGV2ZWwnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl92dWxuZXJhYmlsaXR5X2ltcGFjdF9sZXZlbF9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX3Z1bG5lcmFiaWxpdHlfaW1wYWN0X2xldmVsX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd2dWxuZXJhYmlsaXR5JykgJiYgKGZpZWxkTmFtZSA9PSAnaW1wYWN0X2xldmVsJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25fdnVsbmVyYWJpbGl0eV9pbXBhY3RfbGV2ZWxfbGlzdHNlbGVjdGVkID09ICJmdW5jdGlvbiIpKSBvbl92dWxuZXJhYmlsaXR5X2ltcGFjdF9sZXZlbF9saXN0c2VsZWN0ZWQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfY29kZV9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfY29kZV9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdCcpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX3RocmVhdF9jb2RlX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfY29kZV9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXQnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfY29kZV9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0X2NvZGVfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fdGhyZWF0X2NvZGVfY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdF9jb2RlX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdCcpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfY29kZV9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdF9jb2RlX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXQnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfY29kZV9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0X2NvZGVfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fdGhyZWF0X2NvZGVfZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdF9jb2RlX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdCcpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fdGhyZWF0X2NvZGVfZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0X2NvZGVfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXQnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX3RocmVhdF9jb2RlX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdF9jb2RlX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX3RocmVhdF9jb2RlX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0X2NvZGVfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdCcpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX3RocmVhdF9jb2RlX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfY29kZV9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXQnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fdGhyZWF0X2NvZGVfbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfY29kZV9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfY29kZV9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfY29kZV9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdCcpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfY29kZV9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdF9jb2RlX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXQnKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25fdGhyZWF0X2NvZGVfbGlzdHNlbGVjdGVkID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfY29kZV9saXN0c2VsZWN0ZWQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfbmFtZV9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfbmFtZV9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdCcpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX3RocmVhdF9uYW1lX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfbmFtZV9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXQnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfbmFtZV9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0X25hbWVfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fdGhyZWF0X25hbWVfY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdF9uYW1lX2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdCcpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfbmFtZV9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdF9uYW1lX2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXQnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfbmFtZV9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0X25hbWVfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fdGhyZWF0X25hbWVfZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdF9uYW1lX2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdCcpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fdGhyZWF0X25hbWVfZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0X25hbWVfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXQnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX3RocmVhdF9uYW1lX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdF9uYW1lX2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX3RocmVhdF9uYW1lX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0X25hbWVfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdCcpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX3RocmVhdF9uYW1lX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfbmFtZV9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXQnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fdGhyZWF0X25hbWVfbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfbmFtZV9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfbmFtZV9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfbmFtZV9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdCcpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfbmFtZV9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdF9uYW1lX3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXQnKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25fdGhyZWF0X25hbWVfbGlzdHNlbGVjdGVkID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfbmFtZV9saXN0c2VsZWN0ZWQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0JykgJiYgKGZpZWxkTmFtZSA9PSAnb2NjdXJyZW5jZV9wcm9iYWJpbGl0eScpICYmICh0eXBlID09ICdrZXlkb3duJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9rZXlkb3duKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdCcpICYmIChmaWVsZE5hbWUgPT0gJ29jY3VycmVuY2VfcHJvYmFiaWxpdHknKSAmJiAodHlwZSA9PSAna2V5dXAnKSAmJiAodHlwZW9mIG9uX3RocmVhdF9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9rZXl1cChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXQnKSAmJiAoZmllbGROYW1lID09ICdvY2N1cnJlbmNlX3Byb2JhYmlsaXR5JykgJiYgKHR5cGUgPT0gJ2RibGNsaWNrJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfZGJsY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0JykgJiYgKGZpZWxkTmFtZSA9PSAnb2NjdXJyZW5jZV9wcm9iYWJpbGl0eScpICYmICh0eXBlID09ICdjbGljaycpICYmICh0eXBlb2Ygb25fdGhyZWF0X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdF9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X2NsaWNrKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdCcpICYmIChmaWVsZE5hbWUgPT0gJ29jY3VycmVuY2VfcHJvYmFiaWxpdHknKSAmJiAodHlwZSA9PSAnY2hhbmdlJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdF9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X2NoYW5nZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXQnKSAmJiAoZmllbGROYW1lID09ICdvY2N1cnJlbmNlX3Byb2JhYmlsaXR5JykgJiYgKHR5cGUgPT0gJ2ZvY3VzJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfZm9jdXMoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0JykgJiYgKGZpZWxkTmFtZSA9PSAnb2NjdXJyZW5jZV9wcm9iYWJpbGl0eScpICYmICh0eXBlID09ICdmb2N1c291dCcpICYmICh0eXBlb2Ygb25fdGhyZWF0X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdF9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X2ZvY3Vzb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdCcpICYmIChmaWVsZE5hbWUgPT0gJ29jY3VycmVuY2VfcHJvYmFiaWxpdHknKSAmJiAodHlwZSA9PSAnZHJhZycpICYmICh0eXBlb2Ygb25fdGhyZWF0X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfZHJhZyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXQnKSAmJiAoZmllbGROYW1lID09ICdvY2N1cnJlbmNlX3Byb2JhYmlsaXR5JykgJiYgKHR5cGUgPT0gJ2RyYWdlbmQnKSAmJiAodHlwZW9mIG9uX3RocmVhdF9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdF9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X2RyYWdlbmQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0JykgJiYgKGZpZWxkTmFtZSA9PSAnb2NjdXJyZW5jZV9wcm9iYWJpbGl0eScpICYmICh0eXBlID09ICdtb3VzZW1vdmUnKSAmJiAodHlwZW9mIG9uX3RocmVhdF9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25fdGhyZWF0X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfbW91c2Vtb3ZlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdCcpICYmIChmaWVsZE5hbWUgPT0gJ29jY3VycmVuY2VfcHJvYmFiaWxpdHknKSAmJiAodHlwZSA9PSAnbW91c2VvdXQnKSAmJiAodHlwZW9mIG9uX3RocmVhdF9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9tb3VzZW91dChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXQnKSAmJiAoZmllbGROYW1lID09ICdvY2N1cnJlbmNlX3Byb2JhYmlsaXR5JykgJiYgKHR5cGUgPT0gJ21vdXNlb3ZlcicpICYmICh0eXBlb2Ygb25fdGhyZWF0X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9tb3VzZW92ZXIoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAndGhyZWF0JykgJiYgKGZpZWxkTmFtZSA9PSAnb2NjdXJyZW5jZV9wcm9iYWJpbGl0eScpICYmICh0eXBlID09ICdtb3VzZXVwJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9tb3VzZXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ3RocmVhdCcpICYmIChmaWVsZE5hbWUgPT0gJ29jY3VycmVuY2VfcHJvYmFiaWxpdHknKSAmJiAodHlwZSA9PSAncmVzaXplJykgJiYgKHR5cGVvZiBvbl90aHJlYXRfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX3RocmVhdF9vY2N1cnJlbmNlX3Byb2JhYmlsaXR5X3Jlc2l6ZShldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICd0aHJlYXQnKSAmJiAoZmllbGROYW1lID09ICdvY2N1cnJlbmNlX3Byb2JhYmlsaXR5JykgJiYgKHR5cGUgPT0gJ2xpc3RzZWxlY3RlZCcpICYmICh0eXBlb2Ygb25fdGhyZWF0X29jY3VycmVuY2VfcHJvYmFiaWxpdHlfbGlzdHNlbGVjdGVkID09ICJmdW5jdGlvbiIpKSBvbl90aHJlYXRfb2NjdXJyZW5jZV9wcm9iYWJpbGl0eV9saXN0c2VsZWN0ZWQoZXZlbnQpOwp9Cgp2YXIgc2V0U2VsZWN0ZWRUYWJOdW1iZXI7CnZhciB0ZXh0SW5wdXREaWFsb2c7CnZhciB0ZXh0RGlhbG9nOwp2YXIgb3BlblBvcHVwOwp2YXIgY2xvc2VQb3B1cDsKdmFyIHByaW50UmVwb3J0Owp2YXIgZGF0YVNldDsKdmFyIGdldEZpZWxkVmFsdWU7CnZhciBzZXRGaWVsZFZhbHVlOwp2YXIgZ2V0Q29tcG9uZW50RGF0YTsKdmFyIGFwcGVuZExpbmVUb1RhYmxlOwoKZnVuY3Rpb24gZGVmaW5lU2VsZWN0ZWRUYWJOdW1iZXJGdW5jdGlvbihteUNhbGxiYWNrKXtzZXRTZWxlY3RlZFRhYk51bWJlciA9IG15Q2FsbGJhY2s7fQpmdW5jdGlvbiBkZWZpbmVTZWxlY3RlZFRleHRJbnB1dERpYWxvZyhteUNhbGxiYWNrKXt0ZXh0SW5wdXREaWFsb2cgPSBteUNhbGxiYWNrO30KZnVuY3Rpb24gZGVmaW5lU2VsZWN0ZWRUZXh0RGlhbG9nKG15Q2FsbGJhY2spe3RleHREaWFsb2cgPSBteUNhbGxiYWNrO30KZnVuY3Rpb24gZGVmaW5lU2VsZWN0ZWRPcGVuUG9wdXBEaWFsb2cobXlDYWxsYmFjayl7b3BlblBvcHVwID0gbXlDYWxsYmFjazt9CmZ1bmN0aW9uIGRlZmluZVNlbGVjdGVkQ2xvc2VQb3B1cERpYWxvZyhteUNhbGxiYWNrKXtjbG9zZVBvcHVwID0gbXlDYWxsYmFjazt9CmZ1bmN0aW9uIGRlZmluZVByaW50UmVwb3J0KG15Q2FsbGJhY2spe3ByaW50UmVwb3J0ID0gbXlDYWxsYmFjazt9CmZ1bmN0aW9uIGRlZmluZURhdGFzZXQobXlEYXRhU2V0KXtkYXRhU2V0ID0gbXlEYXRhU2V0O30KZnVuY3Rpb24gZGVmaW5lR2V0RmllbGRWYWx1ZShteUNhbGxiYWNrKXtnZXRGaWVsZFZhbHVlID0gbXlDYWxsYmFjazt9CmZ1bmN0aW9uIGRlZmluZVNldEZpZWxkVmFsdWUobXlDYWxsYmFjayl7c2V0RmllbGRWYWx1ZSA9IG15Q2FsbGJhY2s7fQpmdW5jdGlvbiBkZWZpbmVHZXRDb21wb25lbnREYXRhKG15Q2FsbGJhY2spe2dldENvbXBvbmVudERhdGEgPSBteUNhbGxiYWNrO30KZnVuY3Rpb24gZGVmaW5lQXBwZW5kTGluZVRvVGFibGUobXlDYWxsYmFjayl7YXBwZW5kTGluZVRvVGFibGUgPSBteUNhbGxiYWNrO30K');
INSERT INTO `form` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `name`, `component_id`, `version_id`, `script`) VALUES
(8, 1, '2021-07-08 11:19:32', 1, '2021-07-08 11:19:32', NULL, 0, 'Asset Category', 9, NULL, 'CmZ1bmN0aW9uIG5hdGl2ZUJ1dHRvbkNsaWNrSGFuZGxlcihidG5Db2RlKSB7Cn0KCmZ1bmN0aW9uIG5hdGl2ZUZpZWxkRXZlbnRzSGFuZGxlcihlbnRpdHlDb2RlLCBmaWVsZE5hbWUsIHR5cGUsIGV2ZW50KSB7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ2lkJykgJiYgKHR5cGUgPT0gJ2tleWRvd24nKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2lkX2tleWRvd24gPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2lkX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICdpZCcpICYmICh0eXBlID09ICdrZXl1cCcpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfaWRfa2V5dXAgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2lkX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnaWQnKSAmJiAodHlwZSA9PSAnZGJsY2xpY2snKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2lkX2RibGNsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9pZF9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ2lkJykgJiYgKHR5cGUgPT0gJ2NsaWNrJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9pZF9jbGljayA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfaWRfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICdpZCcpICYmICh0eXBlID09ICdjaGFuZ2UnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2lkX2NoYW5nZSA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfaWRfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnaWQnKSAmJiAodHlwZSA9PSAnZm9jdXMnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2lkX2ZvY3VzID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9pZF9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ2lkJykgJiYgKHR5cGUgPT0gJ2ZvY3Vzb3V0JykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9pZF9mb2N1c291dCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfaWRfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICdpZCcpICYmICh0eXBlID09ICdkcmFnJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9pZF9kcmFnID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9pZF9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnaWQnKSAmJiAodHlwZSA9PSAnZHJhZ2VuZCcpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfaWRfZHJhZ2VuZCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfaWRfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ2lkJykgJiYgKHR5cGUgPT0gJ21vdXNlbW92ZScpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfaWRfbW91c2Vtb3ZlID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9pZF9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICdpZCcpICYmICh0eXBlID09ICdtb3VzZW91dCcpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfaWRfbW91c2VvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2lkX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnaWQnKSAmJiAodHlwZSA9PSAnbW91c2VvdmVyJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9pZF9tb3VzZW92ZXIgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2lkX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ2lkJykgJiYgKHR5cGUgPT0gJ21vdXNldXAnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2lkX21vdXNldXAgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2lkX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICdpZCcpICYmICh0eXBlID09ICdyZXNpemUnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2lkX3Jlc2l6ZSA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfaWRfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnaWQnKSAmJiAodHlwZSA9PSAnbGlzdHNlbGVjdGVkJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9pZF9saXN0c2VsZWN0ZWQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2lkX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAna2V5ZG93bicpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfY29kZV9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9jb2RlX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2tleXVwJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9jb2RlX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9jb2RlX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdkYmxjbGljaycpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfY29kZV9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfY29kZV9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnY2xpY2snKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2NvZGVfY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2NvZGVfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2NoYW5nZScpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfY29kZV9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2NvZGVfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdmb2N1cycpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfY29kZV9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfY29kZV9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnZm9jdXNvdXQnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2NvZGVfZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2NvZGVfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ2RyYWcnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2NvZGVfZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfY29kZV9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdkcmFnZW5kJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9jb2RlX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2NvZGVfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbW91c2Vtb3ZlJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9jb2RlX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfY29kZV9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ21vdXNlb3V0JykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9jb2RlX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9jb2RlX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdtb3VzZW92ZXInKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2NvZGVfbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9jb2RlX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ2NvZGUnKSAmJiAodHlwZSA9PSAnbW91c2V1cCcpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfY29kZV9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9jb2RlX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICdjb2RlJykgJiYgKHR5cGUgPT0gJ3Jlc2l6ZScpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfY29kZV9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2NvZGVfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnY29kZScpICYmICh0eXBlID09ICdsaXN0c2VsZWN0ZWQnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2NvZGVfbGlzdHNlbGVjdGVkID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9jb2RlX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAna2V5ZG93bicpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfbmFtZV9rZXlkb3duID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9uYW1lX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2tleXVwJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9uYW1lX2tleXVwID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9uYW1lX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdkYmxjbGljaycpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfbmFtZV9kYmxjbGljayA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfbmFtZV9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnY2xpY2snKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X25hbWVfY2xpY2sgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X25hbWVfY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2NoYW5nZScpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfbmFtZV9jaGFuZ2UgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X25hbWVfY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdmb2N1cycpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfbmFtZV9mb2N1cyA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfbmFtZV9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnZm9jdXNvdXQnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X25hbWVfZm9jdXNvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X25hbWVfZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ2RyYWcnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X25hbWVfZHJhZyA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfbmFtZV9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdkcmFnZW5kJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9uYW1lX2RyYWdlbmQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X25hbWVfZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2Vtb3ZlJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9uYW1lX21vdXNlbW92ZSA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfbmFtZV9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ21vdXNlb3V0JykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9uYW1lX21vdXNlb3V0ID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9uYW1lX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdtb3VzZW92ZXInKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X25hbWVfbW91c2VvdmVyID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9uYW1lX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ25hbWUnKSAmJiAodHlwZSA9PSAnbW91c2V1cCcpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfbmFtZV9tb3VzZXVwID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9uYW1lX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICduYW1lJykgJiYgKHR5cGUgPT0gJ3Jlc2l6ZScpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfbmFtZV9yZXNpemUgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X25hbWVfcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnbmFtZScpICYmICh0eXBlID09ICdsaXN0c2VsZWN0ZWQnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X25hbWVfbGlzdHNlbGVjdGVkID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9uYW1lX2xpc3RzZWxlY3RlZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ2Rlc2NyaXB0aW9uJykgJiYgKHR5cGUgPT0gJ2tleWRvd24nKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2Rlc2NyaXB0aW9uX2tleWRvd24gPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2Rlc2NyaXB0aW9uX2tleWRvd24oZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICdkZXNjcmlwdGlvbicpICYmICh0eXBlID09ICdrZXl1cCcpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfZGVzY3JpcHRpb25fa2V5dXAgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2Rlc2NyaXB0aW9uX2tleXVwKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnZGVzY3JpcHRpb24nKSAmJiAodHlwZSA9PSAnZGJsY2xpY2snKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2Rlc2NyaXB0aW9uX2RibGNsaWNrID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9kZXNjcmlwdGlvbl9kYmxjbGljayhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ2Rlc2NyaXB0aW9uJykgJiYgKHR5cGUgPT0gJ2NsaWNrJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9kZXNjcmlwdGlvbl9jbGljayA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfZGVzY3JpcHRpb25fY2xpY2soZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICdkZXNjcmlwdGlvbicpICYmICh0eXBlID09ICdjaGFuZ2UnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2Rlc2NyaXB0aW9uX2NoYW5nZSA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfZGVzY3JpcHRpb25fY2hhbmdlKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnZGVzY3JpcHRpb24nKSAmJiAodHlwZSA9PSAnZm9jdXMnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2Rlc2NyaXB0aW9uX2ZvY3VzID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9kZXNjcmlwdGlvbl9mb2N1cyhldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ2Rlc2NyaXB0aW9uJykgJiYgKHR5cGUgPT0gJ2ZvY3Vzb3V0JykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9kZXNjcmlwdGlvbl9mb2N1c291dCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfZGVzY3JpcHRpb25fZm9jdXNvdXQoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICdkZXNjcmlwdGlvbicpICYmICh0eXBlID09ICdkcmFnJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9kZXNjcmlwdGlvbl9kcmFnID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9kZXNjcmlwdGlvbl9kcmFnKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnZGVzY3JpcHRpb24nKSAmJiAodHlwZSA9PSAnZHJhZ2VuZCcpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfZGVzY3JpcHRpb25fZHJhZ2VuZCA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfZGVzY3JpcHRpb25fZHJhZ2VuZChldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ2Rlc2NyaXB0aW9uJykgJiYgKHR5cGUgPT0gJ21vdXNlbW92ZScpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfZGVzY3JpcHRpb25fbW91c2Vtb3ZlID09ICJmdW5jdGlvbiIpKSBvbl9hc3NldF9jYXRlZ29yeV9kZXNjcmlwdGlvbl9tb3VzZW1vdmUoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICdkZXNjcmlwdGlvbicpICYmICh0eXBlID09ICdtb3VzZW91dCcpICYmICh0eXBlb2Ygb25fYXNzZXRfY2F0ZWdvcnlfZGVzY3JpcHRpb25fbW91c2VvdXQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2Rlc2NyaXB0aW9uX21vdXNlb3V0KGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnZGVzY3JpcHRpb24nKSAmJiAodHlwZSA9PSAnbW91c2VvdmVyJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9kZXNjcmlwdGlvbl9tb3VzZW92ZXIgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2Rlc2NyaXB0aW9uX21vdXNlb3ZlcihldmVudCk7CmlmKChlbnRpdHlDb2RlID09ICdhc3NldF9jYXRlZ29yeScpICYmIChmaWVsZE5hbWUgPT0gJ2Rlc2NyaXB0aW9uJykgJiYgKHR5cGUgPT0gJ21vdXNldXAnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2Rlc2NyaXB0aW9uX21vdXNldXAgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2Rlc2NyaXB0aW9uX21vdXNldXAoZXZlbnQpOwppZigoZW50aXR5Q29kZSA9PSAnYXNzZXRfY2F0ZWdvcnknKSAmJiAoZmllbGROYW1lID09ICdkZXNjcmlwdGlvbicpICYmICh0eXBlID09ICdyZXNpemUnKSAmJiAodHlwZW9mIG9uX2Fzc2V0X2NhdGVnb3J5X2Rlc2NyaXB0aW9uX3Jlc2l6ZSA9PSAiZnVuY3Rpb24iKSkgb25fYXNzZXRfY2F0ZWdvcnlfZGVzY3JpcHRpb25fcmVzaXplKGV2ZW50KTsKaWYoKGVudGl0eUNvZGUgPT0gJ2Fzc2V0X2NhdGVnb3J5JykgJiYgKGZpZWxkTmFtZSA9PSAnZGVzY3JpcHRpb24nKSAmJiAodHlwZSA9PSAnbGlzdHNlbGVjdGVkJykgJiYgKHR5cGVvZiBvbl9hc3NldF9jYXRlZ29yeV9kZXNjcmlwdGlvbl9saXN0c2VsZWN0ZWQgPT0gImZ1bmN0aW9uIikpIG9uX2Fzc2V0X2NhdGVnb3J5X2Rlc2NyaXB0aW9uX2xpc3RzZWxlY3RlZChldmVudCk7Cn0KCnZhciBzZXRTZWxlY3RlZFRhYk51bWJlcjsKdmFyIHRleHRJbnB1dERpYWxvZzsKdmFyIHRleHREaWFsb2c7CnZhciBvcGVuUG9wdXA7CnZhciBjbG9zZVBvcHVwOwp2YXIgcHJpbnRSZXBvcnQ7CnZhciBkYXRhU2V0Owp2YXIgZ2V0RmllbGRWYWx1ZTsKdmFyIHNldEZpZWxkVmFsdWU7CnZhciBnZXRDb21wb25lbnREYXRhOwp2YXIgYXBwZW5kTGluZVRvVGFibGU7CgpmdW5jdGlvbiBkZWZpbmVTZWxlY3RlZFRhYk51bWJlckZ1bmN0aW9uKG15Q2FsbGJhY2spe3NldFNlbGVjdGVkVGFiTnVtYmVyID0gbXlDYWxsYmFjazt9CmZ1bmN0aW9uIGRlZmluZVNlbGVjdGVkVGV4dElucHV0RGlhbG9nKG15Q2FsbGJhY2spe3RleHRJbnB1dERpYWxvZyA9IG15Q2FsbGJhY2s7fQpmdW5jdGlvbiBkZWZpbmVTZWxlY3RlZFRleHREaWFsb2cobXlDYWxsYmFjayl7dGV4dERpYWxvZyA9IG15Q2FsbGJhY2s7fQpmdW5jdGlvbiBkZWZpbmVTZWxlY3RlZE9wZW5Qb3B1cERpYWxvZyhteUNhbGxiYWNrKXtvcGVuUG9wdXAgPSBteUNhbGxiYWNrO30KZnVuY3Rpb24gZGVmaW5lU2VsZWN0ZWRDbG9zZVBvcHVwRGlhbG9nKG15Q2FsbGJhY2spe2Nsb3NlUG9wdXAgPSBteUNhbGxiYWNrO30KZnVuY3Rpb24gZGVmaW5lUHJpbnRSZXBvcnQobXlDYWxsYmFjayl7cHJpbnRSZXBvcnQgPSBteUNhbGxiYWNrO30KZnVuY3Rpb24gZGVmaW5lRGF0YXNldChteURhdGFTZXQpe2RhdGFTZXQgPSBteURhdGFTZXQ7fQpmdW5jdGlvbiBkZWZpbmVHZXRGaWVsZFZhbHVlKG15Q2FsbGJhY2spe2dldEZpZWxkVmFsdWUgPSBteUNhbGxiYWNrO30KZnVuY3Rpb24gZGVmaW5lU2V0RmllbGRWYWx1ZShteUNhbGxiYWNrKXtzZXRGaWVsZFZhbHVlID0gbXlDYWxsYmFjazt9CmZ1bmN0aW9uIGRlZmluZUdldENvbXBvbmVudERhdGEobXlDYWxsYmFjayl7Z2V0Q29tcG9uZW50RGF0YSA9IG15Q2FsbGJhY2s7fQpmdW5jdGlvbiBkZWZpbmVBcHBlbmRMaW5lVG9UYWJsZShteUNhbGxiYWNrKXthcHBlbmRMaW5lVG9UYWJsZSA9IG15Q2FsbGJhY2s7fQo=');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `form_area`
--

CREATE TABLE `form_area` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `cssclass` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `form_tab_id` bigint(20) DEFAULT NULL,
  `form_popup_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `form_area`
--

INSERT INTO `form_area` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `cssclass`, `description`, `icon`, `form_tab_id`, `form_popup_id`) VALUES
(5, NULL, '2021-03-28 12:02:38', NULL, '2021-04-20 08:05:19', 1, 44, 'col-12', 'Asset', 'fa-tasks', 3, NULL),
(6, NULL, '2021-03-28 12:03:09', NULL, '2021-04-20 08:05:19', 2, 44, 'col-12', 'Interfaces', 'fa-hdd-o', 3, NULL),
(8, NULL, '2021-04-15 11:48:27', NULL, '2021-06-20 11:54:01', 1, 224, 'col-12', 'Entity', 'fa-sitemap', 5, NULL),
(10, NULL, '2021-04-21 04:36:06', NULL, '2021-06-20 11:54:01', 2, 206, 'col-12', 'Assets', 'fa-tasks', 5, NULL),
(12, NULL, '2021-05-01 10:43:40', NULL, '2021-06-20 11:54:01', 3, 173, 'col-12', 'Current Asset', 'fa-tasks', 5, NULL),
(13, NULL, '2021-05-24 20:20:12', NULL, '2021-06-20 11:54:01', 1, 166, 'col-12', 'Asset Interfaces', 'fa-network-wired', 7, NULL),
(14, NULL, '2021-05-26 15:12:08', NULL, '2021-06-20 11:54:01', 4, 155, 'col-12', 'Interfaces', NULL, 5, NULL),
(15, NULL, '2021-06-03 09:06:52', NULL, '2021-06-20 11:54:01', 1, 96, 'col-12', 'Area1', NULL, NULL, 1),
(17, NULL, '2021-06-24 08:50:58', NULL, '2021-07-09 12:21:00', 1, 33, 'col-12', 'Asset', 'fa-tasks', 10, NULL),
(18, NULL, '2021-06-24 08:50:58', NULL, '2021-07-09 12:21:00', 2, 33, 'col-12', 'Interfaces', 'fa-share-alt', 10, NULL),
(19, NULL, '2021-06-24 09:44:56', NULL, '2021-07-09 12:21:00', 1, 31, 'col-12', 'Selected Interface', 'fa-share-alt', 12, NULL),
(20, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', 2, 30, 'col-12', 'Vulnerabilities', 'fa-bug', 12, NULL),
(21, NULL, '2021-06-24 10:02:08', NULL, '2021-07-09 12:21:00', 1, 29, 'col-12', 'Interfaces', 'fa-share-alt', NULL, 3),
(22, NULL, '2021-06-24 10:06:09', NULL, '2021-07-09 12:21:00', 1, 28, 'col-12', 'Selected Vulnerability', 'fa-bug', 13, NULL),
(23, NULL, '2021-06-24 10:06:09', NULL, '2021-07-09 12:21:00', 2, 28, 'col-12', 'Threats', 'fa-exclamation-circle', 13, NULL),
(24, NULL, '2021-06-24 10:21:21', NULL, '2021-07-09 12:21:00', 1, 27, 'col-12', 'Vulnerabilities', 'fa-bug', NULL, 4),
(25, NULL, '2021-07-01 13:22:33', NULL, '2021-07-06 12:24:52', 1, 49, 'col-12', 'Entity', 'fa-sitemap', 14, NULL),
(26, NULL, '2021-07-01 13:35:59', NULL, '2021-07-06 12:24:52', 2, 48, 'col-12', 'Assets', 'fa-tasks', 14, NULL),
(27, NULL, '2021-07-02 14:58:20', NULL, '2021-07-06 12:24:52', 1, 42, 'col-12', 'Asset', 'fa-tasks', 15, NULL),
(28, NULL, '2021-07-02 14:58:20', NULL, '2021-07-06 12:24:52', 2, 42, 'col-12', 'Interfaces', 'fa-share-alt', 15, NULL),
(29, NULL, '2021-07-02 14:58:20', NULL, '2021-07-06 12:24:52', 1, 42, 'col-12', 'Interface', 'fa-share-alt', 16, NULL),
(30, NULL, '2021-07-02 14:58:20', NULL, '2021-07-06 12:24:52', 2, 42, 'col-12', 'Vulnerabilities', 'fa-bug', 16, NULL),
(31, NULL, '2021-07-02 14:58:20', NULL, '2021-07-06 12:24:52', 1, 42, 'col-12', 'Vulnerability', 'fa-bug', 17, NULL),
(32, NULL, '2021-07-02 14:58:20', NULL, '2021-07-06 12:24:52', 2, 42, 'col-12', 'Threats', 'fa-exclamation-circle', 17, NULL),
(33, NULL, '2021-07-06 07:47:42', NULL, '2021-07-06 12:24:52', 1, 13, 'col-12', 'Assets', 'fa-sitemap', NULL, 5),
(34, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', 1, 0, 'col-12', 'Area1', NULL, 18, NULL);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `form_control`
--

CREATE TABLE `form_control` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `cssclass` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `form_control_field_id` bigint(20) DEFAULT NULL,
  `form_control_table_id` bigint(20) DEFAULT NULL,
  `form_area_id` bigint(20) DEFAULT NULL,
  `form_control_button_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `form_control`
--

INSERT INTO `form_control` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `cssclass`, `type`, `form_control_field_id`, `form_control_table_id`, `form_area_id`, `form_control_button_id`) VALUES
(11, NULL, '2021-03-28 12:02:38', NULL, '2021-04-20 08:05:19', 1, 44, 'col-12', 'field', 35, NULL, 5, NULL),
(12, NULL, '2021-03-28 12:02:38', NULL, '2021-04-20 08:05:19', 2, 44, 'col-12', 'field', 36, NULL, 5, NULL),
(13, NULL, '2021-03-28 12:03:09', NULL, '2021-04-20 08:05:19', 1, 43, 'col-12', 'table', NULL, 11, 6, NULL),
(14, NULL, '2021-04-06 05:29:39', NULL, '2021-04-20 08:05:19', 3, 17, 'col-12', 'field', 42, NULL, 5, NULL),
(15, NULL, '2021-04-15 11:48:27', NULL, '2021-06-20 11:54:01', 1, 224, 'col-10', 'field', 43, NULL, 8, NULL),
(16, NULL, '2021-04-15 11:48:27', NULL, '2021-06-20 11:54:01', 2, 224, 'col-2', 'field', 44, NULL, 8, NULL),
(18, NULL, '2021-04-20 08:05:19', NULL, '2021-04-20 08:05:19', 4, 0, 'col-12', 'field', 49, NULL, 5, NULL),
(19, NULL, '2021-04-21 04:37:56', NULL, '2021-06-20 11:54:01', 1, 204, 'col-12', 'table', NULL, 13, 10, NULL),
(31, NULL, '2021-04-23 20:26:15', NULL, '2021-06-20 11:54:01', 3, 188, 'col-12', 'field', 62, NULL, 8, NULL),
(32, NULL, '2021-04-23 20:26:15', NULL, '2021-06-20 11:54:01', 4, 188, 'col-12', 'field', 63, NULL, 8, NULL),
(33, NULL, '2021-04-23 20:26:15', NULL, '2021-06-20 11:54:01', 5, 188, 'col-12', 'field', 64, NULL, 8, NULL),
(37, NULL, '2021-05-01 10:43:40', NULL, '2021-06-20 11:54:01', 1, 172, 'col-4', 'field', 72, NULL, 12, NULL),
(38, NULL, '2021-05-01 10:43:40', NULL, '2021-06-20 11:54:01', 2, 172, 'col-4', 'field', 73, NULL, 12, NULL),
(39, NULL, '2021-05-01 10:43:40', NULL, '2021-06-20 11:54:01', 3, 172, 'col-4', 'field', 74, NULL, 12, NULL),
(40, NULL, '2021-05-24 20:20:12', NULL, '2021-06-20 11:54:01', 4, 165, 'col-12', 'table', NULL, 14, 13, NULL),
(41, NULL, '2021-05-26 15:12:08', NULL, '2021-06-20 11:54:01', 1, 154, 'col-12', 'table', NULL, 15, 14, NULL),
(42, NULL, '2021-05-27 09:21:18', NULL, '2021-06-20 11:54:01', 2, 151, 'col-3', 'field', 79, NULL, 13, NULL),
(43, NULL, '2021-05-27 09:21:18', NULL, '2021-06-20 11:54:01', 3, 151, 'col-7', 'field', 80, NULL, 13, NULL),
(44, NULL, '2021-06-03 09:06:52', NULL, '2021-06-20 11:54:01', 1, 95, 'col-12', 'table', NULL, 16, 15, NULL),
(49, NULL, '2021-06-03 15:23:02', NULL, '2021-06-20 11:54:01', 1, 91, 'col-2', 'button', NULL, NULL, 13, 12),
(53, NULL, '2021-06-10 08:36:37', NULL, '2021-06-20 11:54:01', 5, 52, 'col-2', 'button', NULL, NULL, 13, 16),
(54, NULL, '2021-06-11 08:53:33', NULL, '2021-06-20 11:54:01', 6, 41, 'col-2', 'button', NULL, NULL, 13, 17),
(55, NULL, '2021-06-24 08:50:58', NULL, '2021-07-09 12:21:00', 1, 33, 'col-4', 'field', 87, NULL, 17, NULL),
(56, NULL, '2021-06-24 08:50:58', NULL, '2021-07-09 12:21:00', 3, 33, 'col-2', 'field', 88, NULL, 17, NULL),
(57, NULL, '2021-06-24 08:50:58', NULL, '2021-07-09 12:21:00', 2, 33, 'col-6', 'field', 89, NULL, 17, NULL),
(58, NULL, '2021-06-24 08:50:58', NULL, '2021-07-09 12:21:00', 1, 33, 'col-12', 'table', NULL, 17, 18, NULL),
(60, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', 1, 29, 'col-2', 'button', NULL, NULL, 19, 19),
(61, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', 2, 29, 'col-3', 'field', 97, NULL, 19, NULL),
(62, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', 3, 29, 'col-7', 'field', 98, NULL, 19, NULL),
(63, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', 1, 29, 'col-12', 'table', NULL, 19, 20, NULL),
(64, NULL, '2021-06-24 10:02:08', NULL, '2021-07-09 12:21:00', 1, 28, 'col-12', 'table', NULL, 20, 21, NULL),
(65, NULL, '2021-06-24 10:06:09', NULL, '2021-07-09 12:21:00', 1, 27, 'col-2', 'button', NULL, NULL, 22, 22),
(66, NULL, '2021-06-24 10:06:09', NULL, '2021-07-09 12:21:00', 2, 27, 'col-3', 'field', 106, NULL, 22, NULL),
(67, NULL, '2021-06-24 10:06:09', NULL, '2021-07-09 12:21:00', 3, 27, 'col-7', 'field', 107, NULL, 22, NULL),
(68, NULL, '2021-06-24 10:06:09', NULL, '2021-07-09 12:21:00', 1, 27, 'col-12', 'table', NULL, 21, 23, NULL),
(69, NULL, '2021-06-24 10:21:21', NULL, '2021-07-09 12:21:00', 2, 26, 'col-12', 'table', NULL, 22, 24, NULL),
(70, NULL, '2021-07-01 13:27:47', NULL, '2021-07-06 12:24:52', 1, 48, 'col-10', 'field', 113, NULL, 25, NULL),
(71, NULL, '2021-07-01 13:27:47', NULL, '2021-07-06 12:24:52', 2, 48, 'col-2', 'field', 114, NULL, 25, NULL),
(72, NULL, '2021-07-01 13:27:47', NULL, '2021-07-06 12:24:52', 3, 48, 'col-12', 'field', 115, NULL, 25, NULL),
(73, NULL, '2021-07-01 13:35:59', NULL, '2021-07-06 12:24:52', 1, 47, 'col-12', 'table', NULL, 23, 26, NULL),
(74, NULL, '2021-07-02 15:02:18', NULL, '2021-07-06 12:24:52', 1, 39, 'col-2', 'button', NULL, NULL, 27, 27),
(75, NULL, '2021-07-02 15:02:18', NULL, '2021-07-06 12:24:52', 1, 39, 'col-2', 'button', NULL, NULL, 29, 28),
(76, NULL, '2021-07-02 15:02:18', NULL, '2021-07-06 12:24:52', 1, 39, 'col-2', 'button', NULL, NULL, 31, 29),
(77, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', 1, 16, 'col-12', 'table', NULL, 24, 28, NULL),
(78, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', 1, 16, 'col-12', 'table', NULL, 25, 30, NULL),
(79, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', 1, 16, 'col-12', 'table', NULL, 26, 32, NULL),
(80, NULL, '2021-07-06 07:21:05', NULL, '2021-07-06 12:24:52', 2, 14, 'col-3', 'field', 129, NULL, 27, NULL),
(81, NULL, '2021-07-06 07:21:05', NULL, '2021-07-06 12:24:52', 3, 14, 'col-7', 'field', 130, NULL, 27, NULL),
(82, NULL, '2021-07-06 07:47:42', NULL, '2021-07-06 12:24:52', 1, 12, 'col-12', 'table', NULL, 27, 33, NULL),
(83, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', 1, 0, 'col-12', 'field', 133, NULL, 34, NULL),
(84, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', 2, 0, 'col-12', 'field', 134, NULL, 34, NULL),
(85, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', 3, 0, 'col-12', 'field', 135, NULL, 34, NULL),
(86, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', 4, 0, 'col-12', 'field', 136, NULL, 34, NULL),
(87, NULL, '2021-07-08 14:45:00', NULL, '2021-07-09 12:21:00', 4, 15, 'col-6', 'field', 137, NULL, 17, NULL),
(88, NULL, '2021-07-09 09:41:39', NULL, '2021-07-09 12:21:00', 5, 9, 'col-6', 'field', 138, NULL, 17, NULL);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `form_control_button`
--

CREATE TABLE `form_control_button` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `css_class` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `editor` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `form_control_button`
--

INSERT INTO `form_control_button` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `css_class`, `description`, `editor`, `icon`, `visible`) VALUES
(1, NULL, '2021-05-25 12:50:49', NULL, '2021-06-20 11:54:01', NULL, 164, '', 'btn-outline-info', 'Demo Nav on new Tab', 'FORM[LOCATE:(ID=4),TAB:new,SIDEBAR-STATUS:minimized]', 'fa-share', b'1'),
(2, NULL, '2021-05-25 12:50:49', NULL, '2021-06-20 11:54:01', NULL, 164, '', 'btn-outline-danger', 'Remove Line', '#deleteLine', 'fa-times', b'1'),
(3, NULL, '2021-05-26 08:24:21', NULL, '2021-06-20 11:54:01', NULL, 157, 'asset_interfaces', 'btn-outline-success', 'View Interfaces of asset', '#selectLine', 'fa-search', b'1'),
(4, NULL, '2021-05-26 15:17:22', NULL, '2021-06-20 11:54:01', NULL, 153, '', 'btn-outline-danger', '', '#deleteLine', 'fa-times', b'1'),
(5, NULL, '2021-05-28 13:06:22', NULL, '2021-06-20 11:54:01', NULL, 113, 'test', 'btn-outline-info', '', '', 'fa-search', b'1'),
(6, NULL, '2021-05-31 08:42:01', NULL, '2021-06-20 11:54:01', NULL, 106, 'callback1', 'btn-outline-success', '', '', 'fa-search', b'1'),
(7, NULL, '2021-06-03 09:06:52', NULL, '2021-06-20 11:54:01', NULL, 95, 'close_asset_popup', 'btn-outline-success', 'Select', '#selectLine', 'fa-search', b'1'),
(12, NULL, '2021-06-03 15:23:02', NULL, '2021-06-20 11:54:01', NULL, 91, 'select_asset', 'btn-outline-success', 'Select Asset', '', 'fa-search', b'1'),
(16, NULL, '2021-06-10 08:36:37', NULL, '2021-06-20 11:54:01', NULL, 52, 'printReport', 'btn-outline-success', 'Print Report', '', 'fa-search', b'1'),
(17, NULL, '2021-06-11 08:53:33', NULL, '2021-06-20 11:54:01', NULL, 41, 'Test', 'btn-outline-success', 'Test', '', 'fa-search', b'1'),
(18, NULL, '2021-06-24 09:00:56', NULL, '2021-07-09 12:21:00', NULL, 32, 'view_vulnerabilities', 'btn-outline-info', 'View Vulnerabilities of Interface', '#selectLine', 'fa-share', b'1'),
(19, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', NULL, 29, 'select_interface', 'btn-outline-info', 'Select Interface', '', 'fa-search', b'1'),
(20, NULL, '2021-06-24 10:02:08', NULL, '2021-07-09 12:21:00', NULL, 28, 'close_interface_popup', 'btn-outline-success', 'Select', '#selectLine', 'fa-search', b'1'),
(21, NULL, '2021-06-24 10:06:09', NULL, '2021-07-09 12:21:00', NULL, 27, 'view_threats', 'btn-outline-info', 'View Threats of Vulnerability', '#selectLine', 'fa-share', b'1'),
(22, NULL, '2021-06-24 10:06:09', NULL, '2021-07-09 12:21:00', NULL, 27, 'select_vulnerability', 'btn-outline-info', 'Select Vulnerability', '', 'fa-search', b'1'),
(23, NULL, '2021-06-24 10:27:34', NULL, '2021-07-09 12:21:00', NULL, 24, 'close_vulnerability_popup', 'btn-outline-success', 'Select', '#selectLine', 'fa-search', b'1'),
(24, NULL, '2021-06-24 10:30:01', NULL, '2021-07-09 12:21:00', NULL, 22, '', 'btn-outline-danger', 'Remove Line', '#deleteLine', 'fa-times', b'1'),
(25, NULL, '2021-06-24 10:30:01', NULL, '2021-07-09 12:21:00', NULL, 22, '', 'btn-outline-danger', 'Remove Line', '#deleteLine', 'fa-times', b'1'),
(26, NULL, '2021-06-24 10:30:01', NULL, '2021-07-09 12:21:00', NULL, 22, '', 'btn-outline-danger', 'Remove Line', '#deleteLine', 'fa-times', b'1'),
(27, NULL, '2021-07-02 15:02:18', NULL, '2021-07-06 12:24:52', NULL, 39, 'select_asset', 'btn-outline-success', 'Select Asset', '', 'fa-search', b'1'),
(28, NULL, '2021-07-02 15:02:18', NULL, '2021-07-06 12:24:52', NULL, 39, '', 'btn-outline-success', 'Button', '', 'fa-search', b'1'),
(29, NULL, '2021-07-02 15:02:18', NULL, '2021-07-06 12:24:52', NULL, 39, '', 'btn-outline-success', 'Button', '', 'fa-search', b'1'),
(30, NULL, '2021-07-06 07:47:42', NULL, '2021-07-06 12:24:52', NULL, 12, 'close_asset_popup', 'btn-outline-success', 'Select', '#selectLine', 'fa-search', b'1');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `form_control_field`
--

CREATE TABLE `form_control_field` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `css` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `editable` bit(1) DEFAULT NULL,
  `editor` varchar(255) DEFAULT NULL,
  `required` bit(1) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `component_persist_entity_id` bigint(20) DEFAULT NULL,
  `component_persist_entity_field_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `form_control_field`
--

INSERT INTO `form_control_field` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `css`, `description`, `editable`, `editor`, `required`, `visible`, `component_persist_entity_id`, `component_persist_entity_field_id`) VALUES
(35, NULL, '2021-03-28 12:02:38', NULL, '2021-04-20 08:05:19', NULL, 44, NULL, NULL, NULL, NULL, NULL, NULL, 1, 8),
(36, NULL, '2021-03-28 12:02:38', NULL, '2021-04-20 08:05:19', NULL, 44, NULL, NULL, NULL, NULL, NULL, NULL, 1, 9),
(38, NULL, '2021-03-28 14:34:45', NULL, '2021-04-20 08:05:19', NULL, 37, NULL, NULL, NULL, NULL, NULL, NULL, 2, 18),
(39, NULL, '2021-03-28 14:35:04', NULL, '2021-04-20 08:05:19', NULL, 36, NULL, NULL, NULL, NULL, NULL, NULL, 2, 17),
(40, NULL, '2021-03-28 14:35:04', NULL, '2021-04-20 08:05:19', NULL, 36, NULL, NULL, NULL, NULL, NULL, NULL, 2, 19),
(41, NULL, '2021-03-28 14:35:04', NULL, '2021-04-20 08:05:19', NULL, 36, NULL, NULL, NULL, NULL, NULL, NULL, 2, 15),
(42, NULL, '2021-04-06 05:29:39', NULL, '2021-04-20 08:05:19', NULL, 17, NULL, NULL, NULL, NULL, NULL, NULL, 1, 7),
(43, NULL, '2021-04-15 11:48:27', NULL, '2021-06-20 11:54:01', NULL, 224, NULL, 'Name', b'1', NULL, b'0', b'1', 13, 211),
(44, NULL, '2021-04-15 11:48:27', NULL, '2021-06-20 11:54:01', NULL, 224, NULL, 'Created on', b'0', NULL, b'0', b'1', 13, 213),
(49, NULL, '2021-04-20 08:05:19', NULL, '2021-04-20 08:05:19', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 16, 248),
(50, NULL, '2021-04-21 04:37:56', NULL, '2021-06-20 11:54:01', NULL, 204, NULL, 'Asset', b'1', NULL, b'0', b'1', 23, 288),
(51, NULL, '2021-04-23 06:25:51', NULL, '2021-04-23 07:41:21', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, 13, 226),
(53, NULL, '2021-04-23 07:38:59', NULL, '2021-04-23 07:41:21', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 25, NULL),
(54, NULL, '2021-04-23 07:38:59', NULL, '2021-04-23 07:41:21', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 25, NULL),
(55, NULL, '2021-04-23 10:55:18', NULL, '2021-04-23 10:55:18', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 13, 226),
(56, NULL, '2021-04-23 10:55:18', NULL, '2021-04-23 10:55:18', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 25, 304),
(62, NULL, '2021-04-23 20:26:15', NULL, '2021-06-20 11:54:01', NULL, 188, NULL, 'User', b'1', NULL, b'0', b'1', 13, 226),
(63, NULL, '2021-04-23 20:26:15', NULL, '2021-06-20 11:54:01', NULL, 188, NULL, 'Email', b'1', NULL, b'0', b'1', 25, 308),
(64, NULL, '2021-04-23 20:26:15', NULL, '2021-06-20 11:54:01', NULL, 188, NULL, 'Password', b'1', NULL, b'0', b'1', 25, 309),
(67, NULL, '2021-04-30 06:04:21', NULL, '2021-06-20 11:54:01', NULL, 182, NULL, 'Asset Code', b'0', NULL, b'0', b'1', 24, 297),
(68, NULL, '2021-04-30 06:04:21', NULL, '2021-06-20 11:54:01', NULL, 182, NULL, 'Asset Name', b'1', NULL, b'0', b'1', 24, 298),
(72, NULL, '2021-05-01 10:43:40', NULL, '2021-06-20 11:54:01', NULL, 172, NULL, 'entity_id', b'0', NULL, b'0', b'1', 23, 287),
(73, NULL, '2021-05-01 10:43:40', NULL, '2021-06-20 11:54:01', NULL, 172, NULL, 'asset code', b'1', NULL, b'0', b'1', 24, 297),
(74, NULL, '2021-05-01 10:43:40', NULL, '2021-06-20 11:54:01', NULL, 172, NULL, 'asset name', b'0', NULL, b'0', b'1', 24, 298),
(75, NULL, '2021-05-24 20:20:12', NULL, '2021-06-20 11:54:01', NULL, 165, NULL, NULL, b'1', NULL, b'0', b'1', 26, 320),
(76, NULL, '2021-05-24 20:20:12', NULL, '2021-06-20 11:54:01', NULL, 165, NULL, NULL, b'1', NULL, b'0', b'1', 26, 321),
(77, NULL, '2021-05-26 15:12:08', NULL, '2021-06-20 11:54:01', NULL, 154, NULL, 'code', b'1', NULL, b'0', b'1', 26, 320),
(78, NULL, '2021-05-26 15:12:08', NULL, '2021-06-20 11:54:01', NULL, 154, NULL, 'name', b'1', NULL, b'0', b'1', 26, 321),
(79, NULL, '2021-05-27 09:21:18', NULL, '2021-06-20 11:54:01', NULL, 151, NULL, 'Selected Asset Code', b'0', NULL, b'0', b'1', 24, 297),
(80, NULL, '2021-05-27 09:21:18', NULL, '2021-06-20 11:54:01', NULL, 151, NULL, 'Selected Asset Name', b'0', NULL, b'0', b'1', 24, 298),
(85, NULL, '2021-06-04 11:39:53', NULL, '2021-06-20 11:54:01', NULL, 75, NULL, 'Code', b'0', NULL, b'0', b'1', 24, 297),
(86, NULL, '2021-06-04 11:39:53', NULL, '2021-06-20 11:54:01', NULL, 75, NULL, 'Name', b'0', NULL, b'0', b'1', 24, 298),
(87, NULL, '2021-06-24 08:50:58', NULL, '2021-07-09 12:21:00', NULL, 33, NULL, 'Code', b'1', NULL, b'0', b'1', 27, 331),
(88, NULL, '2021-06-24 08:50:58', NULL, '2021-07-09 12:21:00', NULL, 33, NULL, 'Created on', b'0', NULL, b'0', b'1', 27, 326),
(89, NULL, '2021-06-24 08:50:58', NULL, '2021-07-09 12:21:00', NULL, 33, NULL, 'Name', b'1', NULL, b'0', b'1', 27, 332),
(90, NULL, '2021-06-24 09:00:56', NULL, '2021-07-09 12:21:00', NULL, 32, NULL, 'Code', b'1', NULL, b'0', b'1', 28, 341),
(91, NULL, '2021-06-24 09:00:56', NULL, '2021-07-09 12:21:00', NULL, 32, NULL, 'Name', b'1', NULL, b'0', b'1', 28, 342),
(92, NULL, '2021-06-24 09:00:56', NULL, '2021-07-09 12:21:00', NULL, 32, NULL, 'Description', b'1', NULL, b'0', b'1', 28, 344),
(97, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', NULL, 29, NULL, 'Code', b'0', NULL, b'0', b'1', 28, 341),
(98, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', NULL, 29, NULL, 'Name', b'0', NULL, b'0', b'1', 28, 342),
(99, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', NULL, 29, NULL, 'Code', b'1', NULL, b'0', b'1', 29, 352),
(100, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', NULL, 29, NULL, 'Name', b'1', NULL, b'0', b'1', 29, 355),
(101, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', NULL, 29, NULL, 'Level', b'1', NULL, b'0', b'1', 29, 354),
(102, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', NULL, 29, NULL, 'Impact Level', b'1', NULL, b'0', b'1', 29, 353),
(103, NULL, '2021-06-24 10:02:08', NULL, '2021-07-09 12:21:00', NULL, 28, NULL, 'CODE', b'0', NULL, b'0', b'1', 28, 341),
(104, NULL, '2021-06-24 10:02:08', NULL, '2021-07-09 12:21:00', NULL, 28, NULL, 'Name', b'0', NULL, b'0', b'1', 28, 342),
(105, NULL, '2021-06-24 10:02:08', NULL, '2021-07-09 12:21:00', NULL, 28, NULL, 'Description', b'0', NULL, b'0', b'1', 28, 344),
(106, NULL, '2021-06-24 10:06:09', NULL, '2021-07-09 12:21:00', NULL, 27, NULL, 'Code', b'0', NULL, b'0', b'1', 29, 352),
(107, NULL, '2021-06-24 10:06:09', NULL, '2021-07-09 12:21:00', NULL, 27, NULL, 'Name', b'0', NULL, b'0', b'1', 29, 355),
(108, NULL, '2021-06-24 10:22:52', NULL, '2021-07-09 12:21:00', NULL, 25, NULL, NULL, b'0', NULL, b'0', b'1', 29, 352),
(109, NULL, '2021-06-24 10:22:52', NULL, '2021-07-09 12:21:00', NULL, 25, NULL, NULL, b'0', NULL, b'0', b'1', 29, 355),
(110, NULL, '2021-06-24 10:22:52', NULL, '2021-07-09 12:21:00', NULL, 25, NULL, 'Code', b'1', NULL, b'0', b'1', 30, 364),
(111, NULL, '2021-06-24 10:22:52', NULL, '2021-07-09 12:21:00', NULL, 25, NULL, 'Name', b'1', NULL, b'0', b'1', 30, 365),
(112, NULL, '2021-06-24 10:22:52', NULL, '2021-07-09 12:21:00', NULL, 25, NULL, 'OCCURRENCE PROBABILITY', b'1', NULL, b'0', b'1', 30, 366),
(113, NULL, '2021-07-01 13:27:47', NULL, '2021-07-06 12:24:52', NULL, 48, NULL, 'Name', b'1', NULL, b'0', b'1', 40, 476),
(114, NULL, '2021-07-01 13:27:47', NULL, '2021-07-06 12:24:52', NULL, 48, NULL, 'Created on', b'1', NULL, b'0', b'1', 40, 478),
(115, NULL, '2021-07-01 13:27:47', NULL, '2021-07-06 12:24:52', NULL, 48, NULL, 'User', b'0', NULL, b'0', b'1', 40, 481),
(116, NULL, '2021-07-01 13:35:59', NULL, '2021-07-06 12:24:52', NULL, 47, NULL, 'Asset', b'1', NULL, b'0', b'1', 42, 507),
(117, NULL, '2021-07-01 13:35:59', NULL, '2021-07-06 12:24:52', NULL, 47, NULL, 'Code', b'1', NULL, b'0', b'1', 42, 502),
(118, NULL, '2021-07-01 13:35:59', NULL, '2021-07-06 12:24:52', NULL, 47, NULL, 'Name', b'1', NULL, b'0', b'1', 42, 503),
(119, NULL, '2021-07-01 13:35:59', NULL, '2021-07-06 12:24:52', NULL, 47, NULL, 'Description', b'1', NULL, b'0', b'1', 42, 505),
(120, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', NULL, 16, NULL, 'Code', b'1', NULL, b'0', b'1', 44, 525),
(121, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', NULL, 16, NULL, 'Name', b'1', NULL, b'0', b'1', 44, 526),
(122, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', NULL, 16, NULL, NULL, b'1', NULL, b'0', b'1', 45, 536),
(123, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', NULL, 16, NULL, NULL, b'1', NULL, b'0', b'1', 45, 539),
(124, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', NULL, 16, NULL, NULL, b'1', NULL, b'0', b'1', 45, 538),
(125, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', NULL, 16, NULL, NULL, b'1', NULL, b'0', b'1', 45, 537),
(126, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', NULL, 16, NULL, NULL, b'1', NULL, b'0', b'1', 46, 548),
(127, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', NULL, 16, NULL, NULL, b'1', NULL, b'0', b'1', 46, 549),
(128, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', NULL, 16, NULL, NULL, b'1', NULL, b'0', b'1', 46, 550),
(129, NULL, '2021-07-06 07:21:05', NULL, '2021-07-06 12:24:52', NULL, 14, NULL, 'Code', b'0', NULL, b'0', b'1', 42, 502),
(130, NULL, '2021-07-06 07:21:05', NULL, '2021-07-06 12:24:52', NULL, 14, NULL, 'Name', b'0', NULL, b'0', b'1', 42, 503),
(131, NULL, '2021-07-06 07:47:42', NULL, '2021-07-06 12:24:52', NULL, 12, NULL, 'code', b'0', NULL, b'0', b'1', 42, 502),
(132, NULL, '2021-07-06 07:47:42', NULL, '2021-07-06 12:24:52', NULL, 12, NULL, 'name', b'0', NULL, b'0', b'1', 42, 503),
(133, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', NULL, 0, NULL, 'id', b'1', NULL, b'0', b'1', 47, 562),
(134, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', NULL, 0, NULL, 'code', b'1', NULL, b'0', b'1', 47, 569),
(135, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', NULL, 0, NULL, 'name', b'1', NULL, b'0', b'1', 47, 570),
(136, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', NULL, 0, NULL, 'description', b'1', NULL, b'0', b'1', 47, 571),
(137, NULL, '2021-07-08 14:45:00', NULL, '2021-07-09 12:21:00', NULL, 15, NULL, 'Category', b'1', NULL, b'0', b'1', 27, 559),
(138, NULL, '2021-07-09 09:41:39', NULL, '2021-07-09 12:21:00', NULL, 9, NULL, 'Business value', b'1', NULL, b'0', b'1', 27, 556);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `form_control_table`
--

CREATE TABLE `form_control_table` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `css` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `editable` bit(1) DEFAULT NULL,
  `required` bit(1) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `component_persist_entity_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `form_control_table`
--

INSERT INTO `form_control_table` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `css`, `description`, `editable`, `required`, `visible`, `component_persist_entity_id`) VALUES
(11, NULL, '2021-03-28 15:03:09', NULL, '2021-04-20 11:05:19', NULL, 44, NULL, 'interface', b'1', b'0', b'1', 2),
(13, NULL, '2021-04-21 07:37:56', NULL, '2021-06-20 11:54:01', NULL, 205, NULL, 'entity_asset_assignments', b'1', b'0', b'1', 23),
(14, NULL, '2021-05-24 23:20:12', NULL, '2021-06-20 11:54:01', NULL, 166, NULL, 'interface', b'1', b'0', b'1', 26),
(15, NULL, '2021-05-26 15:12:08', NULL, '2021-06-20 11:54:01', NULL, 155, NULL, 'interface', b'1', b'0', b'1', 26),
(16, NULL, '2021-06-03 09:06:52', NULL, '2021-06-20 11:54:01', NULL, 96, NULL, 'entity_asset_assignments', b'0', b'0', b'1', 23),
(17, NULL, '2021-06-24 08:50:58', NULL, '2021-07-09 12:21:00', NULL, 33, NULL, 'interfaceRepository', b'1', b'0', b'1', 28),
(19, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', NULL, 30, NULL, 'vulnerabilityRepository', b'1', b'0', b'1', 29),
(20, NULL, '2021-06-24 10:02:08', NULL, '2021-07-09 12:21:00', NULL, 29, NULL, 'interfaceRepository', b'0', b'0', b'1', 28),
(21, NULL, '2021-06-24 10:06:09', NULL, '2021-07-09 12:21:00', NULL, 28, NULL, 'threatRepository', b'1', b'0', b'1', 30),
(22, NULL, '2021-06-24 10:21:21', NULL, '2021-07-09 12:21:00', NULL, 27, NULL, 'vulnerabilityRepository', b'0', b'0', b'1', 29),
(23, NULL, '2021-07-01 13:35:59', NULL, '2021-07-06 12:24:52', NULL, 48, NULL, 'asset', b'1', b'0', b'1', 42),
(24, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', NULL, 17, NULL, 'interface', b'1', b'0', b'1', 44),
(25, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', NULL, 17, NULL, 'vulnerability', b'1', b'0', b'1', 45),
(26, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', NULL, 17, NULL, 'threat', b'1', b'0', b'1', 46),
(27, NULL, '2021-07-06 07:47:42', NULL, '2021-07-06 12:24:52', NULL, 13, NULL, 'asset', b'0', b'0', b'1', 42);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `form_control_table_button_control`
--

CREATE TABLE `form_control_table_button_control` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `cssclass` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `form_control_table_button_id` bigint(20) DEFAULT NULL,
  `form_control_table_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `form_control_table_button_control`
--

INSERT INTO `form_control_table_button_control` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `cssclass`, `type`, `form_control_table_button_id`, `form_control_table_id`) VALUES
(1, NULL, '2021-05-25 12:50:49', NULL, '2021-06-20 11:54:01', 1, 164, 'col-12', 'button', 1, 13),
(2, NULL, '2021-05-25 12:50:49', NULL, '2021-06-20 11:54:01', 1, 164, 'col-12', 'button', 2, 13),
(3, NULL, '2021-05-26 08:24:21', NULL, '2021-06-20 11:54:01', 1, 157, 'col-12', 'button', 3, 13),
(4, NULL, '2021-05-26 15:17:22', NULL, '2021-06-20 11:54:01', 1, 153, 'col-12', 'button', 4, 15),
(5, NULL, '2021-05-28 13:06:22', NULL, '2021-06-20 11:54:01', 1, 113, 'col-12', 'button', 5, 13),
(6, NULL, '2021-05-31 08:42:01', NULL, '2021-06-20 11:54:01', 1, 106, 'col-12', 'button', 6, 13),
(7, NULL, '2021-06-03 09:06:52', NULL, '2021-06-20 11:54:01', 1, 95, 'col-12', 'button', 7, 16),
(8, NULL, '2021-06-24 09:00:56', NULL, '2021-07-09 12:21:00', 1, 32, 'col-12', 'button', 18, 17),
(9, NULL, '2021-06-24 10:02:08', NULL, '2021-07-09 12:21:00', 1, 28, 'col-12', 'button', 20, 20),
(10, NULL, '2021-06-24 10:06:09', NULL, '2021-07-09 12:21:00', 1, 27, 'col-12', 'button', 21, 19),
(11, NULL, '2021-06-24 10:27:34', NULL, '2021-07-09 12:21:00', 1, 24, 'col-12', 'button', 23, 22),
(12, NULL, '2021-06-24 10:30:01', NULL, '2021-07-09 12:21:00', 1, 22, 'col-12', 'button', 24, 17),
(13, NULL, '2021-06-24 10:30:01', NULL, '2021-07-09 12:21:00', 1, 22, 'col-12', 'button', 25, 19),
(14, NULL, '2021-06-24 10:30:01', NULL, '2021-07-09 12:21:00', 1, 22, 'col-12', 'button', 26, 21),
(15, NULL, '2021-07-06 07:47:42', NULL, '2021-07-06 12:24:52', 1, 12, 'col-12', 'button', 30, 27);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `form_control_table_control`
--

CREATE TABLE `form_control_table_control` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `cssclass` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `form_component_field_id` bigint(20) DEFAULT NULL,
  `form_control_table_id` bigint(20) DEFAULT NULL,
  `form_control_field_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `form_control_table_control`
--

INSERT INTO `form_control_table_control` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `cssclass`, `type`, `form_component_field_id`, `form_control_table_id`, `form_control_field_id`) VALUES
(29, NULL, '2021-03-28 14:34:45', NULL, '2021-04-20 08:05:19', 1, 37, 'col-12', 'field', 38, 11, 38),
(30, NULL, '2021-03-28 14:35:04', NULL, '2021-04-20 08:05:19', 2, 36, 'col-12', 'field', 39, 11, 39),
(31, NULL, '2021-03-28 14:35:04', NULL, '2021-04-20 08:05:19', 3, 36, 'col-12', 'field', 40, 11, 40),
(32, NULL, '2021-03-28 14:35:04', NULL, '2021-04-20 08:05:19', 4, 36, 'col-12', 'field', 41, 11, 41),
(37, NULL, '2021-04-21 04:37:56', NULL, '2021-06-20 11:54:01', 1, 204, 'col-12', 'field', 50, 13, 50),
(40, NULL, '2021-04-30 06:04:21', NULL, '2021-06-20 11:54:01', 1, 182, 'col-12', 'field', 67, 13, 67),
(41, NULL, '2021-04-30 06:04:21', NULL, '2021-06-20 11:54:01', 1, 182, 'col-12', 'field', 68, 13, 68),
(42, NULL, '2021-05-24 20:20:12', NULL, '2021-06-20 11:54:01', 1, 165, 'col-12', 'field', 75, 14, 75),
(43, NULL, '2021-05-24 20:20:12', NULL, '2021-06-20 11:54:01', 1, 165, 'col-12', 'field', 76, 14, 76),
(44, NULL, '2021-05-26 15:12:08', NULL, '2021-06-20 11:54:01', 1, 154, 'col-12', 'field', NULL, 15, 77),
(45, NULL, '2021-05-26 15:12:08', NULL, '2021-06-20 11:54:01', 1, 154, 'col-12', 'field', NULL, 15, 78),
(47, NULL, '2021-06-04 11:39:53', NULL, '2021-06-20 11:54:01', 1, 75, 'col-12', 'field', NULL, 16, 85),
(48, NULL, '2021-06-04 11:39:53', NULL, '2021-06-20 11:54:01', 1, 75, 'col-12', 'field', NULL, 16, 86),
(49, NULL, '2021-06-24 09:00:56', NULL, '2021-07-09 12:21:00', 1, 32, 'col-12', 'field', NULL, 17, 90),
(50, NULL, '2021-06-24 09:00:56', NULL, '2021-07-09 12:21:00', 1, 32, 'col-12', 'field', NULL, 17, 91),
(51, NULL, '2021-06-24 09:00:56', NULL, '2021-07-09 12:21:00', 1, 32, 'col-12', 'field', NULL, 17, 92),
(56, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', 1, 29, 'col-12', 'field', NULL, 19, 99),
(57, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', 1, 29, 'col-12', 'field', NULL, 19, 100),
(58, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', 1, 29, 'col-12', 'field', NULL, 19, 101),
(59, NULL, '2021-06-24 09:50:57', NULL, '2021-07-09 12:21:00', 1, 29, 'col-12', 'field', NULL, 19, 102),
(60, NULL, '2021-06-24 10:02:08', NULL, '2021-07-09 12:21:00', 1, 28, 'col-12', 'field', NULL, 20, 103),
(61, NULL, '2021-06-24 10:02:08', NULL, '2021-07-09 12:21:00', 1, 28, 'col-12', 'field', NULL, 20, 104),
(62, NULL, '2021-06-24 10:02:08', NULL, '2021-07-09 12:21:00', 1, 28, 'col-12', 'field', NULL, 20, 105),
(63, NULL, '2021-06-24 10:22:52', NULL, '2021-07-09 12:21:00', 1, 25, 'col-12', 'field', NULL, 22, 108),
(64, NULL, '2021-06-24 10:22:52', NULL, '2021-07-09 12:21:00', 1, 25, 'col-12', 'field', NULL, 22, 109),
(65, NULL, '2021-06-24 10:22:52', NULL, '2021-07-09 12:21:00', 1, 25, 'col-12', 'field', NULL, 21, 110),
(66, NULL, '2021-06-24 10:22:52', NULL, '2021-07-09 12:21:00', 1, 25, 'col-12', 'field', NULL, 21, 111),
(67, NULL, '2021-06-24 10:22:52', NULL, '2021-07-09 12:21:00', 1, 25, 'col-12', 'field', NULL, 21, 112),
(68, NULL, '2021-07-01 13:35:59', NULL, '2021-07-06 12:24:52', 1, 47, 'col-12', 'field', NULL, 23, 116),
(69, NULL, '2021-07-01 13:35:59', NULL, '2021-07-06 12:24:52', 1, 47, 'col-12', 'field', NULL, 23, 117),
(70, NULL, '2021-07-01 13:35:59', NULL, '2021-07-06 12:24:52', 1, 47, 'col-12', 'field', NULL, 23, 118),
(71, NULL, '2021-07-01 13:35:59', NULL, '2021-07-06 12:24:52', 1, 47, 'col-12', 'field', NULL, 23, 119),
(72, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', 1, 16, 'col-12', 'field', NULL, 24, 120),
(73, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', 1, 16, 'col-12', 'field', NULL, 24, 121),
(74, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', 1, 16, 'col-12', 'field', NULL, 25, 122),
(75, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', 1, 16, 'col-12', 'field', NULL, 25, 123),
(76, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', 1, 16, 'col-12', 'field', NULL, 25, 124),
(77, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', 1, 16, 'col-12', 'field', NULL, 25, 125),
(78, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', 1, 16, 'col-12', 'field', NULL, 26, 126),
(79, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', 1, 16, 'col-12', 'field', NULL, 26, 127),
(80, NULL, '2021-07-05 22:58:31', NULL, '2021-07-06 12:24:52', 1, 16, 'col-12', 'field', NULL, 26, 128),
(81, NULL, '2021-07-06 07:47:42', NULL, '2021-07-06 12:24:52', 1, 12, 'col-12', 'field', NULL, 27, 131),
(82, NULL, '2021-07-06 07:47:42', NULL, '2021-07-06 12:24:52', 1, 12, 'col-12', 'field', NULL, 27, 132);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `form_popup`
--

CREATE TABLE `form_popup` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `form_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `form_popup`
--

INSERT INTO `form_popup` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `description`, `icon`, `form_id`) VALUES
(1, NULL, '2021-06-03 09:06:52', NULL, '2021-06-20 11:54:01', 1, 96, 'assetSelector', 'Select Asset to Navigate to Interfaces', 'fa-sitemap', 4),
(3, NULL, '2021-06-24 10:02:08', NULL, '2021-07-09 12:21:00', 1, 29, 'interfaceSelector', 'Select Interface to Navigate to Vulnerabilities', 'fa-share-alt', 6),
(4, NULL, '2021-06-24 10:21:21', NULL, '2021-07-09 12:21:00', 2, 27, 'vulnerabilitySelector', 'Select Vulnerability to Navigate to Threats', 'fa-bug', 6),
(5, NULL, '2021-07-06 07:47:42', NULL, '2021-07-06 12:24:51', 1, 13, 'assetSelector', 'Select Asset to Navigate to Interfaces', 'fa-sitemap', 7);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `form_script`
--

CREATE TABLE `form_script` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `script` text DEFAULT NULL,
  `form_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `form_script`
--

INSERT INTO `form_script` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `name`, `script`, `form_id`) VALUES
(1, NULL, '2021-05-27 10:31:17', NULL, '2021-05-28 11:15:53', 1, 21, 'button clicked', 'ZnVuY3Rpb24gYnRuX2Fzc2V0X2ludGVyZmFjZXNfY2xpY2soKSB7CiAgYWxlcnQoJ0hlbGxvJyk7Cn0=', NULL),
(4, NULL, '2021-05-28 11:15:53', NULL, '2021-05-28 11:16:14', 2, 1, 'Another Button Clicked', 'ZnVuY3Rpb24gYnRuX2Fzc2V0X2ludGVyZmFjZXNfY2xpY2syKCkgewogIGFsZXJ0KCdIZWxsbzInKTsKfQ==', NULL),
(5, NULL, '2021-05-28 11:16:13', NULL, '2021-05-28 11:16:30', 3, 1, 'Script3', 'ZnVuY3Rpb24gYnRuX2Fzc2V0X2ludGVyZmFjZXNfY2xpY2soKSB7CiAgYWxlcnQoJ0hlbGxvJyk7Cn0=', NULL),
(6, NULL, '2021-05-28 11:26:06', NULL, '2021-06-20 11:54:01', 1, 119, 'Button Interfaces', 'ZnVuY3Rpb24gYnRuX2Fzc2V0X2ludGVyZmFjZXNfY2xpY2soKSB7CiAgc2V0U2VsZWN0ZWRUYWJOdW1iZXIoMSk7Cn0KCmZ1bmN0aW9uIGJ0bl9zZWxlY3RfYXNzZXRfY2xpY2soKSB7CiAgb3BlblBvcHVwKCdhc3NldFNlbGVjdG9yJyk7Cn0KCmZ1bmN0aW9uIGJ0bl9jbG9zZV9hc3NldF9wb3B1cF9jbGljaygpIHsKICBjbG9zZVBvcHVwKCdhc3NldFNlbGVjdG9yJyk7Cn0KCmZ1bmN0aW9uIG9uX2VudGl0eV9uYW1lX2NsaWNrKGV2ZW50KSB7CiAgIC8vIGFsZXJ0KCdvbl9lbnRpdHlfbmFtZV9jbGljaycpOwp9CgpmdW5jdGlvbiBvbl9lbnRpdHlfbmFtZV9jaGFuZ2UoZXZlbnQpIHsKICAgLy8gYWxlcnQoJ29uX2VudGl0eV9uYW1lX2NoYW5nZScpOwogIC8vIHZhciBlbnRpdHlOYW1lPSAgZ2V0RmllbGRWYWx1ZSgnZW50aXR5Lm5hbWUnKTsKIC8vICBlbnRpdHlOYW1lICs9ICcxJzsKICAvLyBzZXRGaWVsZFZhbHVlKCdlbnRpdHkubmFtZScsIGVudGl0eU5hbWUpOwogIC8vIGNvbnNvbGUubG9nKGV2ZW50KTsKfQoKZnVuY3Rpb24gb25fZW50aXR5X25hbWVfZm9jdXNvdXQoZXZlbnQpIHsKICAgICAgdmFyIGVudGl0eU5hbWU9ICBnZXRGaWVsZFZhbHVlKCdlbnRpdHkubmFtZScpOwogICAgICBlbnRpdHlOYW1lICs9ICcxJzsKICAgICAgc2V0RmllbGRWYWx1ZSgnZW50aXR5Lm5hbWUnLCBlbnRpdHlOYW1lKTsKfQoKZnVuY3Rpb24gb25fZW50aXR5X25hbWVfa2V5dXAoZXZlbnQpIHsKICAgLy8gYWxlcnQoJ29uX2VudGl0eV9uYW1lX2NoYW5nZScpOwogIC8vIHZhciBlbnRpdHlOYW1lPSAgZ2V0RmllbGRWYWx1ZSgnZW50aXR5Lm5hbWUnKTsKIC8vICBlbnRpdHlOYW1lICs9ICcxJzsKICAvLyBzZXRGaWVsZFZhbHVlKCdlbnRpdHkubmFtZScsIGVudGl0eU5hbWUpOwogLy8gIGNvbnNvbGUubG9nKGV2ZW50KTsKIC8vICBjb25zb2xlLmxvZyhldmVudFsna2V5J10pOwp9CgpmdW5jdGlvbiBvbl9pbnRlcmZhY2VfY29kZV9mb2N1c291dChldmVudCkgewogICAgICAvLyBhbGVydCgnVGVzdCcpOwogICAgICAgdmFyIGVudGl0eU5hbWU9ICBnZXRGaWVsZFZhbHVlKCdpbnRlcmZhY2UuY29kZScpOwogICAgICAgLy9hbGVydChlbnRpdHlOYW1lKTsKICAgICAgIGVudGl0eU5hbWUgKz0gJzEnOwogICAgICAgc2V0RmllbGRWYWx1ZSgnaW50ZXJmYWNlLmNvZGUnLCBlbnRpdHlOYW1lKTsKfQ==', 4),
(7, NULL, '2021-05-28 11:26:06', NULL, '2021-06-20 11:54:01', 2, 119, 'Button Test', 'ZnVuY3Rpb24gYnRuX3Rlc3RfY2xpY2soKSB7CiAgYWxlcnQoJ0J1dHRvbiBUZXN0IENsaWNrZWQnKTsKfQ==', 4),
(8, NULL, '2021-05-31 08:11:22', NULL, '2021-06-20 11:54:01', 3, 109, 'Dialogs example', 'ZnVuY3Rpb24gYnRuX2NhbGxiYWNrMV9jbGljaygpIHsKdGV4dElucHV0RGlhbG9nKAoJJ1BvcHVwIERpYWxvZycsCgknQXJlIHlvdSBzdXJlIGFib3V0IHRoaXM/JywgICAKCWZ1bmN0aW9uIChzb21lKSB7CgkJYWxlcnQoc29tZSk7CgkgICB9KTsKfQoKZnVuY3Rpb24gIGJ0bl9UZXN0X2NsaWNrKGV2ZW50KSB7CiAgICAgLy8gIHZhciBlbnRpdHlOYW1lPSAgZ2V0RmllbGRWYWx1ZSgnaW50ZXJmYWNlLmNvZGUnKTsKICAgICAvLyAgZW50aXR5TmFtZSArPSAnMSc7CiAgICAvLyAgIHNldEZpZWxkVmFsdWUoJ2ludGVyZmFjZS5jb2RlJywgZW50aXR5TmFtZSk7CiAgICAgdGV4dERpYWxvZygKCSdCZSBjYXJlZnVsIScsCgknSGVsbG8gVGhpcyBpcyBhIGRpc3BsYXkgYm94IScpOwp9', 4),
(9, NULL, '2021-06-09 15:08:13', NULL, '2021-06-20 11:54:01', 4, 64, 'Print Report on Button Click', 'ZnVuY3Rpb24gYnRuX3ByaW50UmVwb3J0X2NsaWNrKCkgewogIAogICB2YXIgcGFyYW1ldGVycz0gW107CiAgIHBhcmFtZXRlcnNbImlkIl0gPSAiMSI7CgogIGZvciAoaSA9IDA7IGkgPCAzMDsgaSsrKSB7CiAgICAgICBwcmludFJlcG9ydCgzLHBhcmFtZXRlcnMpOwogICAgICAgdmFyIHVzZXJFbWFpbCA9ICBnZXRGaWVsZFZhbHVlKCd1c2VyLmVtYWlsJyk7CiAgICAgICB1c2VyRW1haWwgKz0gJzEnOwogICAgICAgc2V0RmllbGRWYWx1ZSgndXNlci5lbWFpbCcsIHVzZXJFbWFpbCk7CiAgfQoKICAgY29uc29sZS5sb2coIkhlbGxvIHdvcmxkISIpOwogICBjb25zb2xlLmxvZyhkYXRhU2V0KTsKfQ==', 4),
(10, NULL, '2021-06-24 09:34:05', NULL, '2021-07-09 12:21:00', 1, 31, 'Button Clicks', 'ZnVuY3Rpb24gYnRuX3ZpZXdfdnVsbmVyYWJpbGl0aWVzX2NsaWNrKCkgewogIHNldFNlbGVjdGVkVGFiTnVtYmVyKDEpOwp9CgpmdW5jdGlvbiBidG5fc2VsZWN0X2ludGVyZmFjZV9jbGljaygpIHsKICBvcGVuUG9wdXAoJ2ludGVyZmFjZVNlbGVjdG9yJyk7Cn0KCmZ1bmN0aW9uIGJ0bl9jbG9zZV9pbnRlcmZhY2VfcG9wdXBfY2xpY2soKSB7CiAgY2xvc2VQb3B1cCgnaW50ZXJmYWNlU2VsZWN0b3InKTsKfQoKZnVuY3Rpb24gYnRuX3ZpZXdfdGhyZWF0c19jbGljaygpIHsKICBzZXRTZWxlY3RlZFRhYk51bWJlcigyKTsKfQoKZnVuY3Rpb24gYnRuX3NlbGVjdF92dWxuZXJhYmlsaXR5X2NsaWNrKCkgewogIG9wZW5Qb3B1cCgndnVsbmVyYWJpbGl0eVNlbGVjdG9yJyk7Cn0KCmZ1bmN0aW9uIGJ0bl9jbG9zZV92dWxuZXJhYmlsaXR5X3BvcHVwX2NsaWNrKCkgewogIGNsb3NlUG9wdXAoJ3Z1bG5lcmFiaWxpdHlTZWxlY3RvcicpOwp9Cg==', 6),
(11, NULL, '2021-07-03 14:02:48', NULL, '2021-07-06 12:24:52', 1, 38, 'Add Asset Repository', 'ZnVuY3Rpb24gb25fYXNzZXRfYXNzZXRfcmVwb3NpdG9yeV9pZF9saXN0c2VsZWN0ZWQoZXZlbnQpIHsKCiAgICB2YXIgYXNzZXRSZXBvc2l0b3J5SWQgPSBnZXRGaWVsZFZhbHVlKCdhc3NldC5hc3NldF9yZXBvc2l0b3J5X2lkJyk7CiAgICB2YXIgYXNzZXRSZXBvc2l0b3J5Q29tcG9uZW50ID0gZ2V0Q29tcG9uZW50RGF0YSgKICAgICAgICA0LAogICAgICAgIGFzc2V0UmVwb3NpdG9yeUlkLAogICAgICAgIGZ1bmN0aW9uIChkYXRhc2V0KSB7CiAgICAgICAgICAgIHRoaXMuYWRkQXNzZXRWYWx1ZXMoZGF0YXNldCk7CiAgICAgICAgICAgIC8vIGNvbnNvbGUubG9nKGRhdGFzZXQpOwogICAgICAgIH0KICAgICk7Cn0KCi8qCiAqCiAqIEl0dGVyYXRlIEFzc2V0UmVwb3NpdG9yeSBmaWVsZHMKICogYW5kIGFkZCB0byBjdXJyZW50IGFzc2V0IGZpZWxkcwogKgogKi8KZnVuY3Rpb24gYWRkQXNzZXRWYWx1ZXMoZGF0YXNldCkgewoKICAgIGZvciAoY29uc3QgZmllbGQgb2YgZGF0YXNldC5jb21wb25lbnRQZXJzaXN0RW50aXR5TGlzdFswXS5jb21wb25lbnRQZXJzaXN0RW50aXR5RmllbGRMaXN0KSB7CiAgICAgICAgaWYgKGZpZWxkLnBlcnNpc3RFbnRpdHlGaWVsZC5uYW1lID09ICdjb2RlJykgewogICAgICAgICAgICBzZXRGaWVsZFZhbHVlKCdhc3NldC5jb2RlJywgZmllbGQudmFsdWUpOwogICAgICAgIH0KICAgICAgICBpZiAoZmllbGQucGVyc2lzdEVudGl0eUZpZWxkLm5hbWUgPT0gJ25hbWUnKSB7CiAgICAgICAgICAgIHNldEZpZWxkVmFsdWUoJ2Fzc2V0Lm5hbWUnLCBmaWVsZC52YWx1ZSk7CiAgICAgICAgfQogICAgICAgIGlmIChmaWVsZC5wZXJzaXN0RW50aXR5RmllbGQubmFtZSA9PSAnZGVzY3JpcHRpb24nKSB7CiAgICAgICAgICAgIHNldEZpZWxkVmFsdWUoJ2Fzc2V0LmRlc2NyaXB0aW9uJywgZmllbGQudmFsdWUpOwogICAgICAgIH0KICAgIH0KICAgIGFkZEludGVyZmFjZUxpbmVzKGRhdGFzZXQuY29tcG9uZW50UGVyc2lzdEVudGl0eUxpc3RbMF0pOwp9CgpmdW5jdGlvbiBhZGRJbnRlcmZhY2VMaW5lcyhkYXRhc2V0KSB7CiAgICBmb3IgKGNvbnN0IGRhdGFMaW5lIG9mIGRhdGFzZXQuY29tcG9uZW50UGVyc2lzdEVudGl0eUxpc3RbMF0uY29tcG9uZW50UGVyc2lzdEVudGl0eURhdGFMaW5lcykgewogICAgICAgIHRoaXMuYXBwZW5kTGluZVRvVGFibGUoImludGVyZmFjZSIpOwogICAgICAgIGZvciAoY29uc3QgZmllbGQgb2YgZGF0YUxpbmUuY29tcG9uZW50UGVyc2lzdEVudGl0eUZpZWxkTGlzdCkgewogICAgICAgICAgICBpZiAoZmllbGQucGVyc2lzdEVudGl0eUZpZWxkLm5hbWUgPT0gJ2NvZGUnKSB7CiAgICAgICAgICAgICAgICAvLyBhbGVydCgndGVzdCcpOwogICAgICAgICAgICAgICAgc2V0RmllbGRWYWx1ZSgnaW50ZXJmYWNlLmNvZGUnLCBmaWVsZC52YWx1ZSk7CiAgICAgICAgICAgIH0KICAgICAgICAgICAgaWYgKGZpZWxkLnBlcnNpc3RFbnRpdHlGaWVsZC5uYW1lID09ICduYW1lJykgewogICAgICAgICAgICAgICAgc2V0RmllbGRWYWx1ZSgnaW50ZXJmYWNlLm5hbWUnLCBmaWVsZC52YWx1ZSk7CiAgICAgICAgICAgIH0KICAgICAgICB9CiAgICB9Cn0=', 7),
(12, NULL, '2021-07-06 07:47:42', NULL, '2021-07-06 12:24:52', 2, 12, 'Script2', 'ZnVuY3Rpb24gYnRuX3NlbGVjdF9hc3NldF9jbGljaygpIHsKICBvcGVuUG9wdXAoJ2Fzc2V0U2VsZWN0b3InKTsKfQoKZnVuY3Rpb24gYnRuX2Nsb3NlX2Fzc2V0X3BvcHVwX2NsaWNrKCkgewogIGNsb3NlUG9wdXAoJ2Fzc2V0U2VsZWN0b3InKTsKfQ==', 7);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `form_tab`
--

CREATE TABLE `form_tab` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `form_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `form_tab`
--

INSERT INTO `form_tab` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `description`, `icon`, `form_id`) VALUES
(3, NULL, '2021-03-28 15:02:38', NULL, '2021-04-20 11:05:19', 1, 44, 'Tab1', NULL, 3),
(5, NULL, '2021-04-15 14:48:27', NULL, '2021-06-20 11:54:01', 1, 224, 'Entity & Assets', 'fa-sitemap', 4),
(7, NULL, '2021-05-24 23:20:12', NULL, '2021-06-20 11:54:01', 2, 166, 'Asset Interfaces', 'fa-network-wired', 4),
(10, NULL, '2021-06-24 08:50:58', NULL, '2021-07-09 12:21:00', 1, 33, 'Asset & Interfaces', 'fa-tasks', 6),
(12, NULL, '2021-06-24 08:50:58', NULL, '2021-07-09 12:21:00', 2, 33, 'Vulnerabilities', 'fa-bug', 6),
(13, NULL, '2021-06-24 08:50:58', NULL, '2021-07-09 12:21:00', 3, 33, 'Threats', 'fa-exclamation-circle', 6),
(14, NULL, '2021-07-01 13:22:33', NULL, '2021-07-06 12:24:52', 1, 49, 'Entity and assets', 'fa-sitemap', 7),
(15, NULL, '2021-07-02 14:58:20', NULL, '2021-07-06 12:24:52', 2, 42, 'Interfaces', 'fa-share-alt', 7),
(16, NULL, '2021-07-02 14:58:20', NULL, '2021-07-06 12:24:52', 3, 42, 'Vulnerabilities', 'fa-bug', 7),
(17, NULL, '2021-07-02 14:58:20', NULL, '2021-07-06 12:24:52', 4, 42, 'Threats', 'fa-exclamation-circle', 7),
(18, NULL, '2021-07-08 14:19:32', NULL, '2021-07-08 14:19:32', 1, 0, 'Tab1', NULL, 8);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `info_card`
--

CREATE TABLE `info_card` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `card_text` varchar(255) DEFAULT NULL,
  `command` varchar(255) DEFAULT NULL,
  `command_icon` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `execute_periodically` bit(1) DEFAULT NULL,
  `execution_interval` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `query` text DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `info_card`
--

INSERT INTO `info_card` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `card_text`, `command`, `command_icon`, `description`, `execute_periodically`, `execution_interval`, `icon`, `query`, `title`) VALUES
(1, NULL, '2021-05-10 22:46:21', NULL, '2021-05-12 08:48:57', NULL, 10, '43455.8€/Year', 'STATICPAGE[NAME:table-designer-form,LOCATE:(ID=1),TAB:new,SIDEBAR-STATUS:minimized]', 'fa-search', 'View Details', NULL, NULL, 'nc-vector text-danger', 'select \'43455.8€/Year\' as data', 'Your Assets');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `interface`
--

CREATE TABLE `interface` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `asset_id` bigint(20) DEFAULT NULL,
  `tmp_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `interface`
--

INSERT INTO `interface` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `name`, `asset_id`, `tmp_id`) VALUES
(34, NULL, '2021-03-21 18:00:00', NULL, '2021-03-22 14:46:10', 1, NULL, 'code11', 'name1', 41, NULL),
(38, NULL, '2021-04-01 06:39:37', NULL, '2021-04-01 06:39:37', 9898, NULL, 'code11', 'name11', 43, NULL),
(39, NULL, '2021-04-01 06:39:37', NULL, '2021-04-01 06:39:37', 3434, NULL, 'code22', 'name22', 43, NULL),
(40, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code11', 'name11', 44, NULL),
(41, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 44, NULL),
(42, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code11', 'name11', 45, NULL),
(43, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 45, NULL),
(44, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code11', 'name11', 46, NULL),
(45, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 46, NULL),
(46, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code11', 'name11', 47, NULL),
(47, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 47, NULL),
(48, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code11', 'name11', 48, NULL),
(49, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 48, NULL),
(50, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code11', 'name11', 49, NULL),
(51, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 49, NULL),
(52, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code11', 'name11', 50, NULL),
(53, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 50, NULL),
(54, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code11', 'name11', 52, NULL),
(55, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 52, NULL),
(56, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code12', 'name11', 53, NULL),
(57, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 53, NULL),
(58, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code12', 'name11', 54, NULL),
(59, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 54, NULL),
(60, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code11', 'name11', 55, NULL),
(61, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 55, NULL),
(62, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code112', 'name11', 56, NULL),
(63, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 56, NULL),
(64, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code112', 'name11', 57, NULL),
(65, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 57, NULL),
(66, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code112', 'name11', 58, NULL),
(67, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 58, NULL),
(68, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code112', 'name11', 59, NULL),
(69, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 59, NULL),
(70, NULL, '2021-03-17 18:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code112', 'name11', 60, NULL),
(71, NULL, '2021-04-07 15:00:00', NULL, '2021-03-22 18:28:39', NULL, NULL, 'code22', 'name22', 60, NULL),
(73, NULL, '2021-04-12 21:15:06', NULL, '2021-04-12 21:15:06', 2, NULL, 'jkljkl', 'jkjlk', 49, NULL),
(74, NULL, '2021-04-14 06:56:21', NULL, '2021-04-14 06:56:21', 3, NULL, 'line1', 'line1', 61, NULL),
(75, NULL, '2021-04-14 06:56:21', NULL, '2021-04-14 06:56:21', 19, NULL, 'line2', 'line2', 61, NULL),
(76, NULL, '2021-04-20 12:14:05', NULL, '2021-04-20 12:14:05', 2, NULL, 'l1', 'l1', 66, NULL),
(77, NULL, '2021-04-20 12:14:05', NULL, '2021-04-20 12:14:05', 19, NULL, 'l2', 'l2', 66, NULL),
(78, NULL, '2021-05-27 09:17:44', NULL, '2021-05-27 09:17:44', NULL, NULL, 'aaa', 'aaa', 63, NULL),
(79, NULL, '2021-05-27 09:18:13', NULL, '2021-05-27 09:18:13', NULL, NULL, 'C31', 'C3', 41, NULL),
(80, NULL, '2021-05-27 09:18:13', NULL, '2021-05-27 09:18:13', NULL, NULL, 'RRRq', 'RRRq', 63, NULL),
(81, NULL, '2021-06-04 12:51:13', NULL, '2021-06-04 12:51:13', NULL, NULL, 't11', 't2', 41, NULL),
(82, NULL, '2021-06-15 08:30:11', NULL, '2021-06-15 08:30:11', NULL, NULL, 'h1', 'h1', 51, NULL);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `interface_repository`
--

CREATE TABLE `interface_repository` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` datetime NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `asset_repository_id` bigint(20) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `interface_repository`
--

INSERT INTO `interface_repository` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `name`, `asset_repository_id`, `description`) VALUES
(107, NULL, '2021-06-25 18:59:06', NULL, '2021-06-25 18:59:06', NULL, NULL, 'INTERFACE CODE1', 'INTERFACE NAME1', 89, NULL),
(108, NULL, '2021-06-25 18:59:06', NULL, '2021-06-25 18:59:06', NULL, NULL, 'INTERFACE CODE2', 'INTERFACE NAME2', 89, NULL),
(113, NULL, '2021-07-06 15:20:43', NULL, '2021-07-06 15:20:43', NULL, NULL, 'INTERFACE CODE7', 'INTERFACE NAME7', 92, NULL),
(114, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'INTERFACE CODE1', 'INTERFACE NAME1', 93, NULL),
(115, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'INTERFACE CODE2', 'INTERFACE NAME2', 93, NULL),
(116, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'INTERFACE CODE7', 'INTERFACE NAME7', 94, NULL),
(117, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'INTERFACE CODE1', 'INTERFACE NAME1', 95, NULL),
(118, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'INTERFACE CODE2', 'INTERFACE NAME2', 95, NULL),
(119, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'INTERFACE CODE7', 'INTERFACE NAME7', 96, NULL),
(120, NULL, '2021-07-08 17:51:53', NULL, '2021-07-08 17:51:53', NULL, NULL, 'sasa', 'asasa', 97, 'sas'),
(121, NULL, '2021-07-09 11:23:23', NULL, '2021-07-09 11:23:23', NULL, NULL, 'ss', 'saa', 98, 'sas'),
(122, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'INTERFACE CODE1', 'INTERFACE NAME1', 99, NULL),
(123, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'INTERFACE CODE2', 'INTERFACE NAME2', 99, NULL),
(124, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'INTERFACE CODE7', 'INTERFACE NAME7', 100, NULL),
(125, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'INTERFACE CODE1', 'INTERFACE NAME1', 101, NULL),
(126, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'INTERFACE CODE2', 'INTERFACE NAME2', 101, NULL),
(127, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'INTERFACE CODE7', 'INTERFACE NAME7', 102, NULL),
(128, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'INTERFACE CODE1', 'INTERFACE NAME1', 103, NULL),
(129, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'INTERFACE CODE2', 'INTERFACE NAME2', 103, NULL),
(130, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'INTERFACE CODE7', 'INTERFACE NAME7', 104, NULL);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `list`
--

CREATE TABLE `list` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `header_filters` bit(1) DEFAULT NULL,
  `auto_run` bit(1) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `current_page` bigint(20) DEFAULT NULL,
  `custom_filter_field_structure` bit(1) DEFAULT NULL,
  `default_page` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `export_excel` bit(1) DEFAULT NULL,
  `filter_field_structure` text DEFAULT NULL,
  `filter_visible` bit(1) DEFAULT NULL,
  `has_max_size` bit(1) DEFAULT NULL,
  `has_pagination` bit(1) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `list_visible` bit(1) DEFAULT NULL,
  `max_size` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `page_size` bigint(20) DEFAULT NULL,
  `row_navigation` varchar(255) DEFAULT NULL,
  `selector` varchar(255) DEFAULT NULL,
  `total_pages` bigint(20) DEFAULT NULL,
  `total_rows` bigint(20) DEFAULT NULL,
  `component_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `list`
--

INSERT INTO `list` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `header_filters`, `auto_run`, `code`, `current_page`, `custom_filter_field_structure`, `default_page`, `description`, `export_excel`, `filter_field_structure`, `filter_visible`, `has_max_size`, `has_pagination`, `icon`, `list_visible`, `max_size`, `name`, `page_size`, `row_navigation`, `selector`, `total_pages`, `total_rows`, `component_id`) VALUES
(1, 1, '2021-03-09 11:57:01', 1, '2021-05-06 10:40:26', NULL, 19, b'0', b'1', NULL, NULL, b'0', 'list', 'Users Selection', b'0', '', b'0', b'0', b'0', 'fa-user', b'0', NULL, 'Users with Menus', NULL, NULL, NULL, NULL, NULL, 2),
(2, 0, '2021-04-05 12:39:01', 1, '2021-05-24 10:28:11', NULL, 41, b'1', b'1', NULL, NULL, b'0', 'list', 'Assets list', NULL, '', b'0', b'0', b'0', 'fa-th-list', b'0', 0, 'Asset', 0, NULL, NULL, NULL, NULL, 1),
(3, 1, '2021-04-15 11:29:45', 1, '2021-06-17 05:10:25', NULL, 24, b'1', b'1', '', NULL, b'0', 'list', 'My Entities List', NULL, '', b'0', NULL, NULL, 'fa-object-ungroup', b'0', NULL, 'Entity', NULL, NULL, '', NULL, NULL, 3),
(7, 1, '2021-04-23 04:25:23', 1, '2021-07-01 10:39:10', NULL, 4, b'1', b'1', '', NULL, b'0', 'list', 'Select Asset', b'0', '', b'0', b'0', b'0', 'fa-th-list', b'0', 0, 'Asset (Selector)', 0, NULL, '', NULL, NULL, 1),
(8, 1, '2021-05-19 08:29:25', 1, '2021-05-19 09:20:02', NULL, 9, NULL, b'1', '', NULL, b'0', 'list', 'Users', NULL, '', b'0', b'0', b'0', 'fa-user-circle', b'0', NULL, 'Users for Dashboard', 5, NULL, '', NULL, NULL, 2),
(9, 1, '2021-06-16 07:07:06', 1, '2021-07-08 10:14:30', NULL, 31, b'1', b'1', '', NULL, b'0', 'list', 'Assets List', b'0', '', b'0', b'0', b'0', 'fa-tasks', b'0', NULL, 'Assets Repository', NULL, NULL, '', NULL, NULL, 4),
(10, 1, '2021-06-23 06:53:29', 1, '2021-06-24 10:18:10', NULL, 16, b'1', b'1', '', NULL, b'0', 'list', 'Interfaces of asset', b'0', '', b'0', b'0', b'0', 'fa-share-alt', b'0', 100, 'Ιnterface Repository', 10, NULL, '', NULL, NULL, 5),
(11, 1, '2021-06-24 08:54:49', 1, '2021-06-24 10:18:17', NULL, 5, b'1', b'1', '', NULL, b'0', 'list', 'Vulnerabilities', NULL, '', b'0', NULL, NULL, 'fa-bug', b'0', 100, 'Vulnerability Repository', 10, NULL, '', NULL, NULL, 6),
(12, 1, '2021-06-24 09:33:42', 1, '2021-06-24 10:15:30', NULL, 2, b'1', b'1', '', NULL, b'0', 'list', 'Threat Repository', NULL, '', b'0', NULL, NULL, 'fa-exclamation-circle', b'0', NULL, 'Threat Repository', NULL, NULL, '', NULL, NULL, 7),
(13, 1, '2021-07-01 10:38:47', 1, '2021-07-01 11:56:51', NULL, 5, b'1', b'1', '', NULL, b'0', 'list', 'Asset Repositories', NULL, '', b'0', b'0', b'0', 'fa-tasks', b'0', NULL, 'Asset Repository (Selector)', NULL, NULL, '', NULL, NULL, 4),
(14, 1, '2021-07-01 10:57:25', 1, '2021-07-02 03:55:54', NULL, 2, b'1', b'1', '', NULL, b'0', 'list', 'Entities List', NULL, '', b'0', b'0', b'0', '', b'0', NULL, 'Entity List', NULL, NULL, '', NULL, NULL, 8),
(15, 1, '2021-07-08 11:22:37', 1, '2021-07-08 11:34:09', NULL, 3, b'1', b'1', '', NULL, b'0', 'list', 'Asset Categories', NULL, '', b'0', b'0', b'0', 'fa-layer-group', b'0', NULL, 'Asset Category', NULL, NULL, '', NULL, NULL, 9),
(20, 1, '2021-07-08 11:36:14', 1, '2021-07-09 03:33:19', NULL, 3, b'1', b'1', '', NULL, b'0', 'list', 'Asset Categories', NULL, '', b'0', b'0', b'0', 'fa-layer-group', b'0', NULL, 'Asset Category (Selector)', NULL, NULL, '', NULL, NULL, 9);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `list_action_button`
--

CREATE TABLE `list_action_button` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `css_class` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `editor` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `list_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `list_action_button`
--

INSERT INTO `list_action_button` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `css_class`, `description`, `editor`, `icon`, `visible`, `list_id`) VALUES
(3, NULL, '2021-04-14 09:52:02', NULL, '2021-05-24 10:28:11', 1, 19, 'ab_1', 'btn-outline-success', 'New', 'FORM[LOCATE:(ID=3)]', 'fa-plus-circle', b'0', 2),
(5, NULL, '2021-04-15 11:29:45', NULL, '2021-06-17 08:10:25', 1, 24, 'ab_1', 'btn-outline-success', 'New', 'FORM[LOCATE:(ID=4)]', 'fa-plus-circle', b'1', 3),
(6, NULL, '2021-06-24 12:56:34', NULL, '2021-07-08 13:14:30', 1, 11, 'ab_1', 'btn-outline-success', 'New', 'FORM[LOCATE:(ID=6)]', 'fa-plus-circle', b'1', 9),
(7, NULL, '2021-07-01 14:47:08', NULL, '2021-07-02 06:55:54', 1, 1, 'ab_1', 'btn-outline-success', 'New', 'FORM[LOCATE:(ID=7)]', 'fa-plus-circle', b'1', 14),
(8, NULL, '2021-07-08 12:17:04', NULL, '2021-07-08 13:14:30', 2, 3, 'ab_2', 'btn-outline-success', 'Import from Xls', 'STATICPAGE[NAME:xls-import,LOCATE:(ID=1),TAB:new,SIDEBAR-STATUS:minimized]', 'fa-upload', b'1', 9),
(9, NULL, '2021-07-08 14:28:37', NULL, '2021-07-08 14:34:09', 1, 1, 'ab_1', 'btn-outline-success', 'New', 'FORM[LOCATE:(ID=8),TITLE:New Asset Category]', 'fa-plus-circle', b'1', 15);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `list_component`
--

CREATE TABLE `list_component` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `list_component_field`
--

CREATE TABLE `list_component_field` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `bclass` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `css` text DEFAULT NULL,
  `decimals` int(11) DEFAULT NULL,
  `default_value` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `editable` bit(1) DEFAULT NULL,
  `editor` text DEFAULT NULL,
  `fieldtype` varchar(255) DEFAULT NULL,
  `formula_type` varchar(255) DEFAULT NULL,
  `header_filter` bit(1) DEFAULT NULL,
  `operator` text DEFAULT NULL,
  `required` bit(1) DEFAULT NULL,
  `short_location` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `component_persist_entity_id` bigint(20) DEFAULT NULL,
  `component_persist_entity_field_id` bigint(20) DEFAULT NULL,
  `top_group_list_component_id` bigint(20) DEFAULT NULL,
  `order_by_list_component_id` bigint(20) DEFAULT NULL,
  `side_group_list_component_id` bigint(20) DEFAULT NULL,
  `filter_list_component_id` bigint(20) DEFAULT NULL,
  `column_list_component_id` bigint(20) DEFAULT NULL,
  `action_list_component_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `list_component_field`
--

INSERT INTO `list_component_field` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `bclass`, `code`, `css`, `decimals`, `default_value`, `description`, `editable`, `editor`, `fieldtype`, `formula_type`, `header_filter`, `operator`, `required`, `short_location`, `type`, `visible`, `component_persist_entity_id`, `component_persist_entity_field_id`, `top_group_list_component_id`, `order_by_list_component_id`, `side_group_list_component_id`, `filter_list_component_id`, `column_list_component_id`, `action_list_component_id`) VALUES
(1, NULL, '2021-03-09 11:57:01', NULL, '2021-05-06 10:40:26', 0, 19, NULL, 'C1', NULL, NULL, NULL, 'username', b'1', '', NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 5, 54, NULL, NULL, NULL, NULL, 1, NULL),
(2, NULL, '2021-03-09 11:57:01', NULL, '2021-05-06 10:40:26', 1, 19, NULL, 'C2', NULL, NULL, NULL, 'email', b'1', '', NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 5, 51, NULL, NULL, NULL, NULL, 1, NULL),
(3, NULL, '2021-03-09 11:57:01', NULL, '2021-05-06 10:40:26', 3, 19, NULL, 'C4', NULL, NULL, NULL, 'menu id', b'1', '', NULL, NULL, b'1', 'like', b'0', NULL, 'bigint', b'1', 5, 55, NULL, NULL, NULL, NULL, 1, NULL),
(4, NULL, '2021-03-09 11:57:01', NULL, '2021-05-06 10:40:26', 2, 19, NULL, 'C3', NULL, NULL, NULL, 'menu name', b'1', '', NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 5, 56, NULL, NULL, NULL, NULL, 1, NULL),
(5, NULL, '2021-03-13 19:31:31', NULL, '2021-05-06 10:40:26', 4, 11, NULL, 'C5', NULL, NULL, NULL, '', b'0', '', NULL, NULL, NULL, NULL, b'0', NULL, 'bigint', b'0', 5, 43, NULL, NULL, NULL, NULL, 1, NULL),
(6, NULL, '2021-03-16 21:58:16', NULL, '2021-05-06 10:40:26', 0, 9, 'btn-outline-success', 'AC1', NULL, NULL, 'fa-plus', '', b'0', '#select', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
(7, NULL, '2021-03-22 12:00:00', NULL, '2021-05-06 10:40:26', 0, 8, 'col-12', 'F1', NULL, NULL, NULL, 'Created On', b'1', '', NULL, NULL, NULL, '=', b'0', NULL, 'datetime', b'1', 5, 45, NULL, NULL, NULL, 1, NULL, NULL),
(17, NULL, '2021-04-09 11:12:37', NULL, '2021-05-24 10:28:11', 1, 31, NULL, 'asset_code', NULL, NULL, NULL, 'Asset Code', b'0', '', NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 1, 8, NULL, NULL, NULL, NULL, 2, NULL),
(18, NULL, '2021-04-09 11:12:37', NULL, '2021-05-24 10:28:11', 2, 31, NULL, 'asset_name', NULL, NULL, NULL, 'Asset Name', b'0', '', NULL, NULL, NULL, NULL, b'0', NULL, 'varchar', b'1', 1, 9, NULL, NULL, NULL, NULL, 2, NULL),
(19, NULL, '2021-04-09 11:12:37', NULL, '2021-05-24 10:28:11', 3, 31, NULL, 'asset_created_on', NULL, NULL, NULL, 'Created On', b'0', '', NULL, NULL, NULL, NULL, b'0', NULL, 'datetime', b'1', 1, 3, NULL, NULL, NULL, NULL, 2, NULL),
(20, NULL, '2021-04-09 11:12:37', NULL, '2021-05-24 10:28:11', 4, 31, NULL, 'asset_created_by', NULL, NULL, NULL, 'Created By', b'0', '', NULL, NULL, NULL, NULL, b'0', NULL, 'bigint', b'1', 1, 2, NULL, NULL, NULL, NULL, 2, NULL),
(21, NULL, '2021-04-09 12:13:11', NULL, '2021-05-24 10:28:11', 0, 28, NULL, 'asset_id', NULL, NULL, NULL, 'Id', b'0', '', NULL, NULL, NULL, '=', b'0', NULL, 'bigint', b'1', 1, 1, NULL, NULL, NULL, NULL, 2, NULL),
(22, NULL, '2021-04-09 12:19:39', NULL, '2021-05-24 10:28:11', 0, 26, 'btn-outline-success', 'af_1', NULL, NULL, 'fa-pencil-alt', '', b'0', 'FORM[LOCATE:(ID=3;SELECTION-ID=#asset_id),TITLE:Test FOrm]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(25, NULL, '2021-04-15 06:48:12', NULL, '2021-05-24 10:28:11', 1, 14, 'btn-outline-danger', 'af_2', NULL, NULL, 'fa-times', '', b'0', '#delete', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(26, NULL, '2021-04-15 11:29:45', NULL, '2021-06-17 08:10:25', 0, 24, 'btn-outline-success', 'af_1', NULL, NULL, 'fa-pencil-alt', '', b'0', 'FORM[LOCATE:(ID=4;SELECTION-ID=#entity_id)]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3),
(27, NULL, '2021-04-15 11:29:45', NULL, '2021-06-17 08:10:25', 1, 24, 'btn-outline-danger', 'af_2', NULL, NULL, 'fa-times', '', b'0', 'DELETE[COMPONENT-ID:#current_component_id,SELECTION-ID:#entity_id]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3),
(28, NULL, '2021-04-15 11:29:45', NULL, '2021-06-17 08:10:25', 0, 24, NULL, 'cf_name', NULL, NULL, NULL, 'Name', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'varchar', b'1', 13, 211, NULL, NULL, NULL, NULL, 3, NULL),
(29, NULL, '2021-04-15 11:29:45', NULL, '2021-06-17 08:10:25', 1, 24, NULL, 'cf_created_on', NULL, NULL, NULL, 'Created On', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'datetime', b'1', 13, 213, NULL, NULL, NULL, NULL, 3, NULL),
(31, NULL, '2021-04-15 11:29:45', NULL, '2021-06-17 08:10:25', 0, 24, NULL, 'of_id', NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bigint', b'1', 13, 210, NULL, 3, NULL, NULL, NULL, NULL),
(32, NULL, '2021-04-16 10:33:30', NULL, '2021-06-17 08:10:25', 0, 16, 'col-12', 'ft_user_id', NULL, NULL, 'systemParameter(\'userId\')', NULL, b'0', NULL, NULL, NULL, NULL, '=', b'0', NULL, 'bigint', b'1', 13, 226, NULL, NULL, NULL, 3, NULL, NULL),
(33, NULL, '2021-04-16 10:48:16', NULL, '2021-06-17 08:10:25', 2, 15, NULL, 'entity_id', NULL, NULL, NULL, 'Entity Id', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'bigint', b'0', 13, 210, NULL, NULL, NULL, NULL, 3, NULL),
(37, NULL, '2021-04-19 19:40:58', NULL, '2021-05-24 10:28:11', 5, 5, NULL, 'cf_modified_on', NULL, NULL, NULL, 'cf_modified_on', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'datetime', b'1', 16, 241, NULL, NULL, NULL, NULL, 2, NULL),
(38, NULL, '2021-04-23 04:25:23', NULL, '2021-07-01 13:39:10', 0, 4, NULL, 'asset_id', NULL, NULL, NULL, 'Id', b'0', '', NULL, NULL, NULL, NULL, b'0', NULL, 'bigint', b'0', 1, 1, NULL, NULL, NULL, NULL, 7, NULL),
(39, NULL, '2021-04-23 04:25:23', NULL, '2021-07-01 13:39:10', 1, 4, NULL, 'asset_code', NULL, NULL, NULL, 'Code', b'1', '', NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 1, 8, NULL, NULL, NULL, NULL, 7, NULL),
(40, NULL, '2021-04-23 04:25:23', NULL, '2021-07-01 13:39:10', 2, 4, NULL, 'asset_name', NULL, NULL, NULL, 'Name', b'1', '', NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 1, 9, NULL, NULL, NULL, NULL, 7, NULL),
(41, NULL, '2021-04-23 04:25:23', NULL, '2021-07-01 13:39:10', 3, 4, NULL, 'asset_created_on', NULL, NULL, NULL, 'Created on', b'1', '', NULL, NULL, b'1', '=', b'0', NULL, 'datetime', b'1', 1, 3, NULL, NULL, NULL, NULL, 7, NULL),
(42, NULL, '2021-04-23 04:25:23', NULL, '2021-07-01 13:39:10', 4, 4, NULL, 'asset_created_by', NULL, NULL, NULL, 'Created by', b'1', '', NULL, NULL, b'1', '=', b'0', NULL, 'bigint', b'1', 1, 2, NULL, NULL, NULL, NULL, 7, NULL),
(43, NULL, '2021-04-23 04:25:23', NULL, '2021-07-01 13:39:10', 5, 4, NULL, 'asset_modified_on', NULL, NULL, NULL, 'Modified on', b'1', '', NULL, NULL, b'1', '=', b'0', NULL, 'bigint', b'1', 1, 4, NULL, NULL, NULL, NULL, 7, NULL),
(44, NULL, '2021-04-23 04:26:39', NULL, '2021-07-01 13:39:10', 0, 3, 'btn-outline-success', 'af_1', NULL, NULL, 'fa-plus', '', b'0', '#select', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7),
(45, NULL, '2021-05-19 08:29:25', NULL, '2021-05-19 09:20:02', 0, 9, NULL, 'cf_username', NULL, NULL, NULL, 'username', b'1', '', NULL, NULL, NULL, NULL, b'0', NULL, 'varchar', b'1', 5, 54, NULL, NULL, NULL, NULL, 8, NULL),
(46, NULL, '2021-05-19 08:29:25', NULL, '2021-05-19 09:20:02', 1, 9, NULL, 'cf_email', NULL, NULL, NULL, 'email', b'1', '', NULL, NULL, NULL, NULL, b'0', NULL, 'varchar', b'1', 5, 51, NULL, NULL, NULL, NULL, 8, NULL),
(47, NULL, '2021-05-19 08:29:25', NULL, '2021-05-19 09:20:02', 2, 9, NULL, 'cf_menu_name', NULL, NULL, NULL, 'menu name', b'1', '', NULL, NULL, NULL, NULL, b'0', NULL, 'varchar', b'1', 5, 56, NULL, NULL, NULL, NULL, 8, NULL),
(48, NULL, '2021-05-19 08:29:25', NULL, '2021-05-19 09:20:02', 3, 9, NULL, 'cf_menu_id', NULL, NULL, NULL, 'menu id', b'1', '', NULL, NULL, NULL, NULL, b'0', NULL, 'bigint', b'1', 5, 55, NULL, NULL, NULL, NULL, 8, NULL),
(49, NULL, '2021-05-19 08:29:25', NULL, '2021-05-19 09:20:02', 4, 9, NULL, 'cf_id', NULL, NULL, NULL, '', b'0', '', NULL, NULL, NULL, NULL, b'0', NULL, 'bigint', b'0', 5, 43, NULL, NULL, NULL, NULL, 8, NULL),
(50, NULL, '2021-05-19 08:29:25', NULL, '2021-05-19 09:20:02', 0, 9, NULL, 'of_created_on', NULL, NULL, NULL, '', b'0', 'ASC', NULL, NULL, NULL, NULL, NULL, NULL, 'timestamp', b'1', 5, 45, NULL, 8, NULL, NULL, NULL, NULL),
(51, NULL, '2021-05-19 08:34:54', NULL, '2021-05-19 09:20:02', 0, 7, 'btn-outline-success', 'af_1', NULL, NULL, 'fa-share', 'Show Users', b'0', 'STATICPAGE[NAME:user-form,TITLE:User,TAB:new,SIDEBAR-STATUS:minimized,LOCATE:(ID=#cf_id)]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8),
(52, NULL, '2021-05-21 06:30:01', NULL, '2021-06-17 08:10:25', 3, 12, NULL, 'cf_version', NULL, NULL, NULL, 'Version', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'bigint', b'1', 25, 306, NULL, NULL, NULL, NULL, 3, NULL),
(53, NULL, '2021-05-21 06:30:01', NULL, '2021-06-17 08:10:25', 4, 12, NULL, 'cf_dateformat', NULL, NULL, NULL, 'Date Format For User', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'varchar', b'1', 25, 307, NULL, NULL, NULL, NULL, 3, NULL),
(54, NULL, '2021-06-16 10:07:06', NULL, '2021-07-08 13:14:30', 0, 31, NULL, 'cf_code', NULL, NULL, NULL, 'Asset Code', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 27, 331, NULL, NULL, NULL, NULL, 9, NULL),
(55, NULL, '2021-06-16 10:07:06', NULL, '2021-07-08 13:14:30', 1, 31, NULL, 'cf_name', NULL, NULL, NULL, 'Asset Name', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 27, 332, NULL, NULL, NULL, NULL, 9, NULL),
(62, NULL, '2021-06-23 08:06:57', NULL, '2021-07-08 13:14:30', 0, 28, 'btn-outline-danger', 'af_1', NULL, NULL, 'fa-times', 'Delete', b'0', 'LIST-DELETE[COMPONENT-ID:#current_component_id,SELECTION-ID:#cf_id]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9),
(64, NULL, '2021-06-23 09:53:29', NULL, '2021-06-24 13:18:10', 0, 16, NULL, 'cf_code', NULL, NULL, NULL, 'asset code', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 32, 386, NULL, NULL, NULL, NULL, 10, NULL),
(65, NULL, '2021-06-23 09:53:29', NULL, '2021-06-24 13:18:10', 1, 16, NULL, 'cf_name', NULL, NULL, NULL, 'asset name', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 32, 387, NULL, NULL, NULL, NULL, 10, NULL),
(66, NULL, '2021-06-23 09:53:29', NULL, '2021-06-24 13:18:10', 2, 16, NULL, 'interface_code', NULL, NULL, NULL, 'interface code', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'varchar', b'1', 31, 375, NULL, NULL, NULL, NULL, 10, NULL),
(67, NULL, '2021-06-23 09:53:29', NULL, '2021-06-24 13:18:10', 3, 16, NULL, 'cf_name_1', NULL, NULL, NULL, 'interface name', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'varchar', b'1', 31, 376, NULL, NULL, NULL, NULL, 10, NULL),
(68, NULL, '2021-06-23 09:53:29', NULL, '2021-06-24 13:18:10', 4, 16, NULL, 'cf_description', NULL, NULL, NULL, 'interface description', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'varchar', b'1', 31, 378, NULL, NULL, NULL, NULL, 10, NULL),
(69, NULL, '2021-06-23 15:01:47', NULL, '2021-06-24 13:18:10', 0, 14, 'col-12', 'ft_id', NULL, NULL, NULL, NULL, b'1', '', NULL, NULL, NULL, '=', b'0', NULL, 'bigint', b'1', 32, 379, NULL, NULL, NULL, 10, NULL, NULL),
(71, NULL, '2021-06-24 08:18:10', NULL, '2021-07-08 13:14:30', 2, 24, NULL, 'cf_description', NULL, NULL, NULL, 'Asset description', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 27, 333, NULL, NULL, NULL, NULL, 9, NULL),
(72, NULL, '2021-06-24 08:18:52', NULL, '2021-07-08 13:14:30', 3, 23, NULL, 'cf_id', NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'bigint', b'0', 27, 324, NULL, NULL, NULL, NULL, 9, NULL),
(73, NULL, '2021-06-24 08:32:50', NULL, '2021-07-08 13:14:30', 2, 20, 'btn-outline-info', 'af_2', NULL, NULL, 'fa-share', 'Go to Interfaces List', b'0', 'LIST[LOCATE:(ID=10),DEFAULTS:(cf_code=#cf_code),TITLE:Interfaces]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9),
(74, NULL, '2021-06-24 09:05:45', NULL, '2021-07-08 13:14:30', 3, 18, 'btn-outline-success', 'af_2', '', NULL, 'fa-pencil-alt', 'Open', b'0', 'FORM[LOCATE:(ID=6;SELECTION-ID=#cf_id),TITLE:ASSET-#assetRepository.name#]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9),
(75, NULL, '2021-06-24 11:54:49', NULL, '2021-06-24 13:18:17', 0, 5, NULL, 'cf_code_1', NULL, NULL, NULL, 'interface code', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 36, 431, NULL, NULL, NULL, NULL, 11, NULL),
(76, NULL, '2021-06-24 11:54:49', NULL, '2021-06-24 13:18:17', 1, 5, NULL, 'cf_name_1', NULL, NULL, NULL, 'interface name', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 36, 432, NULL, NULL, NULL, NULL, 11, NULL),
(77, NULL, '2021-06-24 11:54:49', NULL, '2021-06-24 13:18:17', 2, 5, NULL, 'vulnerability_code', NULL, NULL, NULL, 'code', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'varchar', b'1', 35, 419, NULL, NULL, NULL, NULL, 11, NULL),
(78, NULL, '2021-06-24 11:54:49', NULL, '2021-06-24 13:18:17', 3, 5, NULL, 'cf_name', NULL, NULL, NULL, 'name', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'varchar', b'1', 35, 422, NULL, NULL, NULL, NULL, 11, NULL),
(79, NULL, '2021-06-24 11:54:49', NULL, '2021-06-24 13:18:17', 4, 5, NULL, 'cf_impact_level', NULL, NULL, NULL, 'impact level', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'double', b'1', 35, 420, NULL, NULL, NULL, NULL, 11, NULL),
(80, NULL, '2021-06-24 11:54:49', NULL, '2021-06-24 13:18:17', 5, 5, NULL, 'cf_level', NULL, NULL, NULL, 'level', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'double', b'1', 35, 421, NULL, NULL, NULL, NULL, 11, NULL),
(81, NULL, '2021-06-24 11:54:49', NULL, '2021-06-24 13:18:17', 6, 5, NULL, 'cf_created_on', NULL, NULL, NULL, 'created on', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'datetime', b'1', 35, 414, NULL, NULL, NULL, NULL, 11, NULL),
(82, NULL, '2021-06-24 11:54:49', NULL, '2021-06-24 13:18:17', 0, 5, NULL, 'of_created_on', NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'datetime', b'1', 36, 426, NULL, 11, NULL, NULL, NULL, NULL),
(83, NULL, '2021-06-24 11:56:39', NULL, '2021-06-24 13:18:10', 2, 7, 'btn-outline-info', 'af_1', NULL, NULL, 'fa-share', 'Go to Vulnerabilities List', b'0', 'LIST[LOCATE:(ID=11),DEFAULTS:(cf_code_1=#interface_code)]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 10),
(84, NULL, '2021-06-24 12:33:42', NULL, '2021-06-24 13:15:30', 0, 2, NULL, 'vulnerability_code', NULL, NULL, NULL, 'vulnerability code', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 39, 464, NULL, NULL, NULL, NULL, 12, NULL),
(85, NULL, '2021-06-24 12:33:42', NULL, '2021-06-24 13:15:30', 1, 2, NULL, 'vulnerability_name', NULL, NULL, NULL, 'vulnerability name', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 39, 467, NULL, NULL, NULL, NULL, 12, NULL),
(86, NULL, '2021-06-24 12:33:42', NULL, '2021-06-24 13:15:30', 2, 2, NULL, 'threat_repository_code', NULL, NULL, NULL, 'threat code', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'varchar', b'1', 38, 453, NULL, NULL, NULL, NULL, 12, NULL),
(87, NULL, '2021-06-24 12:33:42', NULL, '2021-06-24 13:15:30', 3, 2, NULL, 'threat_repository_name', NULL, NULL, NULL, 'threat name', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'varchar', b'1', 38, 454, NULL, NULL, NULL, NULL, 12, NULL),
(88, NULL, '2021-06-24 12:33:42', NULL, '2021-06-24 13:15:30', 4, 2, NULL, 'cf_occurrence_probability', NULL, NULL, NULL, 'threat occurrence probability', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'double', b'1', 38, 455, NULL, NULL, NULL, NULL, 12, NULL),
(89, NULL, '2021-06-24 12:33:42', NULL, '2021-06-24 13:15:30', 0, 2, NULL, 'of_short_order', NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bigint', b'1', 38, 451, NULL, 12, NULL, NULL, NULL, NULL),
(90, NULL, '2021-06-24 12:35:31', NULL, '2021-06-24 13:18:17', 1, 4, 'btn-outline-info', 'af_1', NULL, NULL, 'fa-sign-out-alt', 'Go to Therats List (New Tab)', b'0', 'LIST[LOCATE:(ID=12),DEFAULTS:(vulnerability_code=#vulnerability_code),TAB:new,SIDEBAR-STATUS:minimized]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 11),
(91, NULL, '2021-06-24 12:39:17', NULL, '2021-07-08 13:14:30', 1, 15, 'btn-outline-info', 'af_3', NULL, NULL, 'fa-sign-out-alt', 'Go to Interfaces List (New Tab)', b'0', 'LIST[LOCATE:(ID=10),DEFAULTS:(cf_code=#cf_code),TAB:new,SIDEBAR-STATUS:minimized]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9),
(92, NULL, '2021-06-24 12:44:52', NULL, '2021-06-24 13:18:10', 1, 4, 'btn-outline-info', 'af_1', NULL, NULL, 'fa-sign-out-alt', 'Go to Vulnerabilities List', b'0', 'LIST[LOCATE:(ID=11),DEFAULTS:(cf_code_1=#interface_code),TAB:new,SIDEBAR-STATUS:minimized]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 10),
(93, NULL, '2021-06-24 12:46:33', NULL, '2021-06-24 13:18:17', 2, 3, 'btn-outline-info', 'af_1', NULL, NULL, 'fa-share', 'Go to Therats List', b'0', 'LIST[LOCATE:(ID=12),DEFAULTS:(vulnerability_code=#vulnerability_code)]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 11),
(94, NULL, '2021-06-24 12:58:23', NULL, '2021-06-24 13:18:10', 0, 2, 'btn-outline-danger', 'af_2', NULL, NULL, 'fa-times', 'Delete', b'0', 'LIST-DELETE[COMPONENT-ID:#current_component_id,SELECTION-ID:#cf_id]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 10),
(95, NULL, '2021-06-24 12:58:23', NULL, '2021-06-24 13:18:10', 5, 2, NULL, 'cf_id', NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'bigint', b'0', 31, 368, NULL, NULL, NULL, NULL, 10, NULL),
(96, NULL, '2021-06-24 12:59:11', NULL, '2021-06-24 13:18:17', 0, 2, 'btn-outline-danger', 'af_2', NULL, NULL, 'fa-times', 'Delete', b'0', 'LIST-DELETE[COMPONENT-ID:#current_component_id,SELECTION-ID:#cf_id]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 11),
(97, NULL, '2021-06-24 12:59:11', NULL, '2021-06-24 13:18:17', 7, 2, NULL, 'cf_id', NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'bigint', b'0', 35, 412, NULL, NULL, NULL, NULL, 11, NULL),
(98, NULL, '2021-06-24 13:03:24', NULL, '2021-06-24 13:15:30', 0, 1, 'btn-outline-danger', 'af_1', NULL, NULL, 'fa-times', 'Delete', b'0', 'LIST-DELETE[COMPONENT-ID:#current_component_id,SELECTION-ID:#cf_id]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 12),
(99, NULL, '2021-06-24 13:03:24', NULL, '2021-06-24 13:15:30', 5, 1, NULL, 'cf_id', NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'bigint', b'0', 38, 446, NULL, NULL, NULL, NULL, 12, NULL),
(100, NULL, '2021-07-01 13:38:47', NULL, '2021-07-01 14:56:51', 0, 5, 'btn-outline-success', 'af_1', NULL, NULL, 'fa-plus', '', b'0', '#select', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 13),
(101, NULL, '2021-07-01 13:38:47', NULL, '2021-07-01 14:56:51', 1, 5, NULL, 'cf_code', NULL, NULL, NULL, 'Code', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 27, 331, NULL, NULL, NULL, NULL, 13, NULL),
(102, NULL, '2021-07-01 13:38:47', NULL, '2021-07-01 14:56:51', 2, 5, NULL, 'cf_name', NULL, NULL, NULL, 'Name', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 27, 332, NULL, NULL, NULL, NULL, 13, NULL),
(103, NULL, '2021-07-01 13:38:47', NULL, '2021-07-01 14:56:51', 3, 5, NULL, 'cf_description', NULL, NULL, NULL, 'Description', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 27, 333, NULL, NULL, NULL, NULL, 13, NULL),
(104, NULL, '2021-07-01 13:38:47', NULL, '2021-07-01 14:56:51', 0, 5, NULL, 'of_created_on', NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'datetime', b'1', 27, 326, NULL, 13, NULL, NULL, NULL, NULL),
(105, NULL, '2021-07-01 13:52:01', NULL, '2021-07-01 14:56:51', 0, 2, NULL, 'cf_id', NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'bigint', b'0', 27, 324, NULL, NULL, NULL, NULL, 13, NULL),
(106, NULL, '2021-07-01 13:57:25', NULL, '2021-07-02 06:55:54', 0, 2, NULL, 'cf_username', NULL, NULL, NULL, 'username', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'varchar', b'1', 41, 493, NULL, NULL, NULL, NULL, 14, NULL),
(107, NULL, '2021-07-01 13:57:25', NULL, '2021-07-02 06:55:54', 1, 2, NULL, 'cf_id', NULL, NULL, NULL, 'Entity id', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'bigint', b'1', 40, 475, NULL, NULL, NULL, NULL, 14, NULL),
(108, NULL, '2021-07-01 13:57:25', NULL, '2021-07-02 06:55:54', 2, 2, NULL, 'cf_name', NULL, NULL, NULL, 'Entity', b'0', NULL, NULL, NULL, NULL, NULL, b'0', NULL, 'varchar', b'1', 40, 476, NULL, NULL, NULL, NULL, 14, NULL),
(109, NULL, '2021-07-01 14:47:08', NULL, '2021-07-02 06:55:54', 0, 1, 'btn-outline-success', 'af_1', NULL, NULL, 'fa-pencil-alt', '', b'0', 'FORM[LOCATE:(ID=7;SELECTION-ID=#cf_id),TITLE:Test FOrm]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 14),
(110, NULL, '2021-07-01 14:47:08', NULL, '2021-07-02 06:55:54', 1, 1, 'btn-outline-danger', 'af_2', NULL, NULL, 'fa-times', '', b'0', '#delete', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 14),
(111, NULL, '2021-07-08 14:22:37', NULL, '2021-07-08 14:34:09', 0, 3, NULL, 'cf_id', NULL, NULL, NULL, 'id', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'bigint', b'1', 47, 562, NULL, NULL, NULL, NULL, 15, NULL),
(112, NULL, '2021-07-08 14:22:37', NULL, '2021-07-08 14:34:09', 1, 3, NULL, 'cf_code', NULL, NULL, NULL, 'code', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 47, 569, NULL, NULL, NULL, NULL, 15, NULL),
(113, NULL, '2021-07-08 14:22:37', NULL, '2021-07-08 14:34:09', 2, 3, NULL, 'cf_name', NULL, NULL, NULL, 'name', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 47, 570, NULL, NULL, NULL, NULL, 15, NULL),
(114, NULL, '2021-07-08 14:22:37', NULL, '2021-07-08 14:34:09', 3, 3, NULL, 'cf_description', NULL, NULL, NULL, 'description', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 47, 571, NULL, NULL, NULL, NULL, 15, NULL),
(115, NULL, '2021-07-08 14:22:37', NULL, '2021-07-08 14:34:09', 0, 3, NULL, 'of_id', NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bigint', b'1', 47, 562, NULL, 15, NULL, NULL, NULL, NULL),
(116, NULL, '2021-07-08 14:34:09', NULL, '2021-07-08 14:34:09', 0, 0, 'btn-outline-success', 'af_1', NULL, NULL, 'fa-pencil-alt', '', b'0', 'FORM[LOCATE:(ID=8;SELECTION-ID=#cf_id),TITLE:Asset Category]', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 15),
(117, NULL, '2021-07-08 14:34:09', NULL, '2021-07-08 14:34:09', 1, 0, 'btn-outline-danger', 'af_2', NULL, NULL, 'fa-times', '', b'0', '#delete', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 15),
(122, NULL, '2021-07-08 14:36:14', NULL, '2021-07-09 06:33:19', 0, 3, NULL, 'cf_id', NULL, NULL, NULL, 'id', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'bigint', b'1', 47, 562, NULL, NULL, NULL, NULL, 20, NULL),
(123, NULL, '2021-07-08 14:36:14', NULL, '2021-07-09 06:33:19', 1, 3, NULL, 'cf_code', NULL, NULL, NULL, 'code', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 47, 569, NULL, NULL, NULL, NULL, 20, NULL),
(124, NULL, '2021-07-08 14:36:14', NULL, '2021-07-09 06:33:19', 2, 3, NULL, 'cf_name', NULL, NULL, NULL, 'name', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 47, 570, NULL, NULL, NULL, NULL, 20, NULL),
(125, NULL, '2021-07-08 14:36:14', NULL, '2021-07-09 06:33:19', 3, 3, NULL, 'cf_description', NULL, NULL, NULL, 'description', b'1', NULL, NULL, NULL, b'1', 'like', b'0', NULL, 'varchar', b'1', 47, 571, NULL, NULL, NULL, NULL, 20, NULL),
(126, NULL, '2021-07-08 14:36:14', NULL, '2021-07-09 06:33:19', 0, 3, NULL, 'of_id', NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bigint', b'1', 47, 562, NULL, 20, NULL, NULL, NULL, NULL),
(127, NULL, '2021-07-08 14:37:43', NULL, '2021-07-09 06:33:19', 0, 1, 'btn-outline-success', 'af_1', NULL, NULL, 'fa-plus', '', b'0', '#select', NULL, NULL, NULL, NULL, b'0', NULL, 'list', b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 20);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `menu`
--

CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `menu`
--

INSERT INTO `menu` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `name`) VALUES
(2, NULL, '2020-09-08 00:51:51', 1, '2021-07-09 21:55:11', NULL, 95, 'Admin menu'),
(3, NULL, '2020-09-08 00:51:57', NULL, '2020-09-08 00:51:57', NULL, 0, 'menu2'),
(4, NULL, '2020-09-08 00:52:04', NULL, '2020-09-08 00:52:04', NULL, 0, 'menu3'),
(6, NULL, '2020-09-10 01:21:18', NULL, '2020-09-10 01:21:18', NULL, 0, 'test'),
(7, NULL, '2020-09-10 01:31:25', NULL, '2020-09-10 01:31:25', NULL, 0, 'test2'),
(21, 1, '2020-09-10 02:39:42', 1, '2020-09-10 02:39:42', NULL, 0, 'Admin menu2'),
(22, 1, '2020-10-06 05:35:45', 1, '2020-10-06 05:35:45', NULL, 0, 'Asset menu 01'),
(23, 1, '2021-06-30 08:05:19', 1, '2021-07-09 20:58:25', NULL, 21, 'Admin Header Menu'),
(24, 1, '2021-07-08 07:13:25', 1, '2021-07-09 20:55:22', NULL, 2, 'Author Menu'),
(25, 1, '2021-07-08 08:28:20', 1, '2021-07-08 11:24:57', NULL, 7, 'CityScape');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `menu_field`
--

CREATE TABLE `menu_field` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `command` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `menu_field_id` bigint(20) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `menu_field`
--

INSERT INTO `menu_field` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `command`, `icon`, `name`, `menu_field_id`, `menu_id`) VALUES
(4, NULL, '2020-09-08 21:45:10', NULL, '2021-07-10 00:55:11', 1, 106, '#parent-menu#', 'fa-folder', 'Admin menus', NULL, 2),
(5, NULL, '2020-09-08 21:45:10', NULL, '2021-07-10 00:55:11', 2, 106, 'STATICPAGE[NAME:table-designer-list,TITLE:Table Designer]', 'fa-cogs', 'Table Designer', 4, NULL),
(6, NULL, '2020-09-08 21:45:10', NULL, '2021-07-10 00:55:11', 3, 106, 'STATICPAGE[NAME:menu-designer-list,TITLE:Menu Designer]', 'fa-cogs', 'Menu Designer', 4, NULL),
(7, NULL, '2020-09-08 21:45:10', NULL, '2021-07-10 00:55:11', 4, 106, 'STATICPAGE[NAME:list-designer-list,TITLE:List Designer]', 'fa-cogs', 'List Designer', 4, NULL),
(8, NULL, '2020-09-08 21:57:17', NULL, '2021-07-10 00:55:11', 5, 105, 'STATICPAGE[NAME:component-designer-list,TITLE:Compoent Designer]', 'fa-cogs', 'Compoent Designer', 4, NULL),
(9, NULL, '2020-09-08 21:57:17', NULL, '2021-07-10 00:55:11', 6, 105, 'STATICPAGE[NAME:view-designer-list,TITLE:View Designer]', 'fa-cogs', 'View Designer', 4, NULL),
(10, NULL, '2020-09-08 21:57:17', NULL, '2021-07-10 00:55:11', 7, 105, 'STATICPAGE[NAME:appview-designer-list,TITLE:App View Designer]', 'fa-cogs', 'App View Designer', 4, NULL),
(12, NULL, '2020-09-08 21:57:17', NULL, '2021-07-10 00:55:11', 3, 105, 'STATICPAGE[NAME:user-list,TITLE:Users]', 'fa-users', 'Users', NULL, 2),
(13, NULL, '2020-09-09 00:43:18', NULL, '2021-07-10 00:55:11', 4, 99, '#parent-collapse#', 'fa-chevron-down', 'test collapse', NULL, 2),
(14, NULL, '2020-09-09 00:43:18', NULL, '2021-07-10 00:55:11', 1, 99, NULL, 'fa-sliders', 'menu1', 13, NULL),
(15, NULL, '2020-09-09 00:43:18', NULL, '2021-07-10 00:55:11', 2, 99, NULL, 'fa-sliders', 'menu2', 13, NULL),
(16, NULL, '2020-09-09 01:17:40', NULL, '2021-07-10 00:55:11', 3, 97, '#parent-collapse#', 'fa-chevron-down', 'Test Collapse 2', 13, NULL),
(17, NULL, '2020-09-09 01:17:40', NULL, '2021-07-10 00:55:11', 1, 97, NULL, 'fa-sliders', 'Menu 3', 16, NULL),
(18, NULL, '2020-09-09 02:16:53', NULL, '2021-04-09 11:18:47', 8, 46, 'fa-expandparent-collapse', 'fa-expand', 'parent-collapse', NULL, NULL),
(19, NULL, '2020-09-09 02:16:53', NULL, '2021-04-09 11:18:47', 1, 46, NULL, 'τεστ', 'τεστ', 18, NULL),
(21, NULL, '2020-09-09 04:19:01', NULL, '2021-04-09 11:21:18', 8, 42, 'fa-folder-oparent-menu', 'fa-folder-o', 'Admin menus 2', NULL, NULL),
(22, NULL, '2020-09-09 04:19:01', NULL, '2021-04-09 11:21:18', 1, 42, 'fa-slidersfa-slidersSTATICPAGE[NAME:table-designer-list,TITLE:Table Designer]', 'fa-sliders', 'test X', 21, NULL),
(51, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 1, 0, 'STATICPAGE[NAME:dashboard,TITLE:Dashboard]', 'fa-home', 'Dashboard', NULL, 21),
(52, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 2, 0, 'parent-menu', 'fa-folder-o', 'Admin menus', NULL, 21),
(53, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 3, 0, 'STATICPAGE[NAME:table-designer-list,TITLE:Table Designer]', 'fa-sliders', 'Table Designer', 52, NULL),
(54, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 5, 0, 'STATICPAGE[NAME:menu-designer-list,TITLE:Menu Designer]', 'fa-sliders', 'Menu Designer', 52, NULL),
(55, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 6, 0, 'STATICPAGE[NAME:list-designer-list,TITLE:List Designer]', 'fa-sliders', 'List Designer', 52, NULL),
(56, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 1, 0, 'STATICPAGE[NAME:component-designer-list,TITLE:Compoent Designer]', 'fa-sliders', 'Compoent Designer', 52, NULL),
(57, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 2, 0, 'STATICPAGE[NAME:view-designer-list,TITLE:View Designer]', 'fa-sliders', 'View Designer', 52, NULL),
(58, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 3, 0, 'STATICPAGE[NAME:appview-designer-list,TITLE:App View Designer]', 'fa-sliders', 'App View Designer', 52, NULL),
(59, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 1, 0, 'parent-collapse', 'fa-expand', 'parent-collapse', 52, NULL),
(60, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 2, 0, NULL, 'τεστ', 'τεστ', 59, NULL),
(61, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 1, 0, 'parent-menu', 'fa-folder-o', 'Admin menus 2', 52, NULL),
(62, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 2, 0, 'STATICPAGE[NAME:table-designer-list,TITLE:Table Designer]', 'fa-sliders', 'test X', 61, NULL),
(63, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 4, 0, 'LIST[LOCATE:(ID=5),TITLE:invoices List]', 'fa-th-list', 'Invoices List', NULL, 21),
(64, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 5, 0, 'STATICPAGE[NAME:user-list,TITLE:Users]', 'fa-users', 'Users', NULL, 21),
(65, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 1, 0, 'parent-collapse', 'fa-chevron-down', 'test collapse', NULL, 21),
(66, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 2, 0, NULL, 'fa-sliders', 'menu1', 65, NULL),
(67, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 3, 0, NULL, 'fa-sliders', 'menu2', 65, NULL),
(68, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 1, 0, 'parent-collapse', 'fa-chevron-down', 'Test Collapse 2', 65, NULL),
(69, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 2, 0, NULL, 'fa-sliders', 'Menu 3', 68, NULL),
(70, NULL, '2020-09-10 02:39:42', NULL, '2020-09-10 02:39:42', 2, 0, 'LIST[LOCATE:(ID=6),TITLE:List]', 'fa-th-list', 'List 6 Example', NULL, 21),
(71, NULL, '2020-09-25 05:04:04', NULL, '2021-07-10 00:55:11', 5, 87, 'LIST[LOCATE:(ID=1),TITLE:invoices List]', 'fa-th-list', 'Users List', NULL, 2),
(73, NULL, '2020-10-06 05:35:45', NULL, '2020-10-06 05:35:45', 5, 0, 'STATICPAGE[NAME:user-list,TITLE:Users]', 'fa-users', 'Users', NULL, 22),
(74, NULL, '2020-10-06 05:35:45', NULL, '2020-10-06 05:35:45', 2, 0, 'LIST[LOCATE:(ID=9),TITLE:invoices List]', 'fa-th-list', 'Assets', NULL, 22),
(75, NULL, '2020-10-06 05:35:45', NULL, '2020-10-06 05:35:45', 1, 0, '', 'fa-th-list', 'Vulnerabilities', NULL, 22),
(77, NULL, '2021-01-13 08:47:49', NULL, '2021-07-10 00:55:11', 8, 79, 'STATICPAGE[NAME:form-designer-list,TITLE:Form Designer]', 'fa-cogs', 'Form Designer', 4, NULL),
(78, NULL, '2021-01-18 07:28:06', NULL, '2021-07-10 00:55:11', 6, 77, 'STATICPAGE[NAME:form-designer-list,TITLE:Form Designer]', 'fa-cogs', 'Form Designer', NULL, 2),
(80, NULL, '2021-03-05 10:09:19', NULL, '2021-07-10 00:55:11', 7, 73, 'LIST[LOCATE:(ID=1),TITLE:Users with menus]', 'fa-th-list', 'Users with menus', NULL, 2),
(81, NULL, '2021-04-06 06:26:56', NULL, '2021-07-10 00:55:11', 8, 56, 'LIST[LOCATE:(ID=2),TITLE:invoices List]', 'fa-th-list', 'Assets list', NULL, 2),
(82, NULL, '2021-04-09 11:15:31', NULL, '2021-07-10 00:55:11', 1, 52, 'STATICPAGE[NAME:user-list,TITLE:Users]', 'fa-users', 'Users', 4, NULL),
(83, NULL, '2021-04-09 11:15:31', NULL, '2021-07-10 00:55:11', 2, 52, '#parent-menu#', 'fa-folder', 'Tests', NULL, 2),
(84, NULL, '2021-04-09 11:18:47', NULL, '2021-07-10 00:55:11', 1, 50, '#parent-collapse#', 'fa-chevron-down', 'parent-collapse', 83, NULL),
(85, NULL, '2021-04-09 11:18:47', NULL, '2021-07-10 00:55:11', 1, 50, 'STATICPAGE[NAME:table-designer-list,TITLE:Table Designer]', 'fa-sliders', 'Test', 84, NULL),
(86, NULL, '2021-04-09 11:20:16', NULL, '2021-07-10 00:55:11', 2, 48, '#parent-menu#', 'fa-folder', 'Menu 2 (Submenu)', 83, NULL),
(87, NULL, '2021-04-09 11:20:16', NULL, '2021-07-10 00:55:11', 1, 48, 'fa-slidersSTATICPAGE[NAME:table-designer-list,TITLE:Table Designer]', 'fa-sliders', 'Test', 86, NULL),
(88, NULL, '2021-04-09 11:21:18', NULL, '2021-07-10 00:55:11', 4, 47, 'LIST[LOCATE:(ID=1),TITLE:invoices List]', 'fa-signal', 'Menu Test 3', 13, NULL),
(89, NULL, '2021-04-14 06:50:48', NULL, '2021-07-10 00:55:11', 9, 44, 'FORM[LOCATE:(ID=3),TITLE:Test FOrm]', 'fas fa-clipboard-list', 'New Test Form', NULL, 2),
(90, NULL, '2021-04-16 04:14:48', NULL, '2021-07-10 00:55:11', 10, 43, 'FORM[LOCATE:(ID=4)]', 'fas fa-clipboard-list', 'Entity', NULL, 2),
(91, NULL, '2021-04-16 08:26:26', NULL, '2021-07-10 00:55:11', 11, 42, 'LIST[LOCATE:(ID=3)]', 'fa-th-list', 'Entities List', NULL, 2),
(92, NULL, '2021-05-07 04:13:44', NULL, '2021-07-10 00:55:11', 12, 40, 'STATICPAGE[NAME:chart-designer-list,TITLE:Table Designer]', 'fa-cogs', 'Chart Designer', NULL, 2),
(93, NULL, '2021-05-10 19:35:30', NULL, '2021-07-10 00:55:11', 13, 38, 'STATICPAGE[NAME:info-card-designer-list,TITLE:Table Designer]', 'fa-cogs', 'Info Card Designer ', NULL, 2),
(94, NULL, '2021-05-13 05:43:17', NULL, '2021-07-10 00:55:11', 14, 37, 'STATICPAGE[NAME:dashboard-designer-list,TITLE:Table Designer]', 'fa-cogs', 'Dashboard Designer', NULL, 2),
(96, NULL, '2021-05-16 09:50:55', NULL, '2021-07-10 00:55:11', 15, 30, 'STATICPAGE[NAME:default]', 'fa-sticky-note', 'Default empty page', NULL, 2),
(97, NULL, '2021-05-16 10:25:36', NULL, '2021-07-10 00:55:11', 16, 28, 'STATICPAGE[NAME:dashboard,LOCATE:(ID=1)]', 'fa-chart-area', 'Dashboard with id 1', NULL, 2),
(98, NULL, '2021-06-01 12:03:09', NULL, '2021-07-10 00:55:11', 17, 19, 'STATICPAGE[NAME:dashboard,LOCATE:(ID=2)]', 'fa-chart-area', 'Test Chart 2', NULL, 2),
(99, NULL, '2021-06-07 11:12:16', NULL, '2021-07-10 00:55:11', 18, 17, 'STATICPAGE[NAME:report-designer-list,TITLE:Table Designer]', 'fa-cogs', 'Report Designer', NULL, 2),
(100, NULL, '2021-06-16 10:08:27', NULL, '2021-07-10 00:55:11', 21, 15, 'LIST[LOCATE:(ID=9)]', 'fa-th-list', 'Assets Repository', NULL, 2),
(101, NULL, '2021-06-16 12:33:34', NULL, '2021-07-10 00:55:11', 19, 14, 'STATICPAGE[NAME:xls-import-designer-list]', 'fa-cogs', 'Xls Import Designer', NULL, 2),
(102, NULL, '2021-06-17 10:47:55', NULL, '2021-07-10 00:55:11', 20, 12, 'STATICPAGE[NAME:xls-import,LOCATE:(ID=1)]', 'fa-file-code', 'Import Xls', NULL, 2),
(103, NULL, '2021-06-23 10:19:49', NULL, '2021-07-10 00:55:11', 22, 11, 'LIST[LOCATE:(ID=10),DEFAULTS:(ft_id=84)]', 'fa-th-list', 'Interface Repo List', NULL, 2),
(104, NULL, '2021-06-24 13:31:35', NULL, '2021-07-10 00:55:11', 23, 4, '#parent-collapse#', 'fa-chevron-down', 'Repositories', NULL, 2),
(105, NULL, '2021-06-24 13:31:35', NULL, '2021-07-10 00:55:11', 1, 4, 'LIST[LOCATE:(ID=9)]', 'fa-th-list', 'Assets', 104, NULL),
(106, NULL, '2021-06-24 13:31:35', NULL, '2021-07-10 00:55:11', 2, 4, 'LIST[LOCATE:(ID=10)]', 'fa-th-list', 'Interfaces', 104, NULL),
(107, NULL, '2021-06-24 13:31:35', NULL, '2021-07-10 00:55:11', 3, 4, 'LIST[LOCATE:(ID=11)]', 'fa-th-list', 'Vulnerabilities', 104, NULL),
(108, NULL, '2021-06-24 13:31:35', NULL, '2021-07-10 00:55:11', 4, 4, 'LIST[LOCATE:(ID=12)]', 'fa-th-list', 'Threats', 104, NULL),
(109, NULL, '2021-06-30 11:05:19', NULL, '2021-07-09 23:58:25', 3, 21, '#loader#', '', 'Loader', NULL, 23),
(110, NULL, '2021-06-30 11:05:19', NULL, '2021-07-09 23:58:25', 2, 21, '#search#', NULL, 'search', NULL, 23),
(111, NULL, '2021-06-30 11:05:19', NULL, '2021-07-09 23:58:25', 1, 21, 'STATICPAGE[NAME:dashboard,LOCATE:(ID=1),TITLE:Dashboard]', 'fa-home', 'Home', NULL, 23),
(112, NULL, '2021-06-30 11:05:19', NULL, '2021-07-09 23:58:25', 7, 21, '#parent-menu#', 'fa-cog', 'Menu', NULL, 23),
(113, NULL, '2021-06-30 11:05:19', NULL, '2021-07-09 23:58:25', 1, 21, '#logout#', 'fa-power-off', 'Logout', 112, NULL),
(114, NULL, '2021-07-01 08:51:01', NULL, '2021-07-09 23:58:25', 5, 16, 'SIDEBARMENU[LOCATE:(ID=25)]', 'fa-rocket', 'Dev Menu', NULL, 23),
(115, NULL, '2021-07-01 08:51:01', NULL, '2021-07-09 23:58:25', 6, 16, 'SIDEBARMENU[LOCATE:(ID=24)]', 'fa-tools', 'Change Sidebar Menu', NULL, 23),
(116, NULL, '2021-07-01 14:48:09', NULL, '2021-07-10 00:55:11', 24, 2, 'LIST[LOCATE:(ID=14),TITLE:Entities List]', 'fa-th-list', 'Entities', NULL, 2),
(117, NULL, '2021-07-08 10:13:25', NULL, '2021-07-09 23:55:22', 1, 2, 'STATICPAGE[NAME:user-list,TITLE:Users]', 'fa-users', 'Users', NULL, 24),
(118, NULL, '2021-07-08 10:13:25', NULL, '2021-07-09 23:55:22', 2, 2, 'STATICPAGE[NAME:menu-designer-list,TITLE:Menu Designer]', 'fa-cogs', 'Menu Designer', NULL, 24),
(119, NULL, '2021-07-08 10:13:25', NULL, '2021-07-09 23:55:22', 3, 2, 'STATICPAGE[NAME:list-designer-list,TITLE:List Designer]', 'fa-cogs', 'List Designer', NULL, 24),
(120, NULL, '2021-07-08 10:13:25', NULL, '2021-07-09 23:55:22', 4, 2, 'STATICPAGE[NAME:form-designer-list,TITLE:Form Designer]', 'fa-cogs', 'Form Designer', NULL, 24),
(121, NULL, '2021-07-08 10:13:25', NULL, '2021-07-09 23:55:22', 5, 2, 'STATICPAGE[NAME:component-designer-list,TITLE:Compoent Designer]', 'fa-cogs', 'Compoent Designer', NULL, 24),
(122, NULL, '2021-07-08 10:13:25', NULL, '2021-07-09 23:55:22', 6, 2, '#parent-menu#', 'fa-folder', 'Persist Entities', NULL, 24),
(123, NULL, '2021-07-08 10:13:25', NULL, '2021-07-09 23:55:22', 1, 2, 'STATICPAGE[NAME:table-designer-list,TITLE:Table Designer]', 'fa-cogs', 'Table Designer', 122, NULL),
(124, NULL, '2021-07-08 10:13:25', NULL, '2021-07-09 23:55:22', 2, 2, 'STATICPAGE[NAME:view-designer-list,TITLE:View Designer]', 'fa-cogs', 'View Designer', 122, NULL),
(125, NULL, '2021-07-08 10:13:25', NULL, '2021-07-09 23:55:22', 3, 2, 'STATICPAGE[NAME:appview-designer-list,TITLE:App View Designer]', 'fa-cogs', 'App View Designer', 122, NULL),
(126, NULL, '2021-07-08 10:13:25', NULL, '2021-07-09 23:55:22', 7, 2, '#parent-menu#', 'fa-folder', 'Dashboard', NULL, 24),
(127, NULL, '2021-07-08 10:13:25', NULL, '2021-07-09 23:55:22', 1, 2, 'STATICPAGE[NAME:chart-designer-list,TITLE:Table Designer]', 'fa-cogs', 'Chart Designer', 126, NULL),
(128, NULL, '2021-07-08 10:13:25', NULL, '2021-07-09 23:55:22', 2, 2, 'STATICPAGE[NAME:info-card-designer-list,TITLE:Table Designer]', 'fa-cogs', 'Info Card Designer', 126, NULL),
(129, NULL, '2021-07-08 10:13:25', NULL, '2021-07-09 23:55:22', 3, 2, 'STATICPAGE[NAME:dashboard-designer-list,TITLE:Table Designer]', 'fa-cogs', 'Dashboard Designer', 126, NULL),
(130, NULL, '2021-07-08 10:13:25', NULL, '2021-07-09 23:55:22', 8, 2, 'STATICPAGE[NAME:report-designer-list,TITLE:Table Designer]', 'fa-cogs', 'Report Designer', NULL, 24),
(131, NULL, '2021-07-08 10:13:25', NULL, '2021-07-09 23:55:22', 9, 2, 'STATICPAGE[NAME:xls-import-designer-list]', 'fa-cogs', 'Xls Import Designer', NULL, 24),
(132, NULL, '2021-07-08 11:12:34', NULL, '2021-07-09 23:58:25', 4, 9, 'SIDEBARMENU[LOCATE:(ID=2)]', 'fa-leaf', 'My Menu', NULL, 23),
(133, NULL, '2021-07-08 11:28:20', NULL, '2021-07-08 14:24:57', 1, 7, 'STATICPAGE[NAME:default]', 'fas fa-suitcase', 'Business Services', NULL, 25),
(134, NULL, '2021-07-08 11:28:20', NULL, '2021-07-08 14:24:57', 2, 7, 'STATICPAGE[NAME:default]', 'fa-balance-scale', 'Risk Assessments', NULL, 25),
(135, NULL, '2021-07-08 11:28:20', NULL, '2021-07-08 14:24:57', 3, 7, 'STATICPAGE[NAME:default]', 'fa-flask', 'Mitigation Lab', NULL, 25),
(136, NULL, '2021-07-08 11:28:20', NULL, '2021-07-08 14:24:57', 4, 7, 'STATICPAGE[NAME:default]', 'fa-stethoscope', 'Defensive Strategies', NULL, 25),
(138, NULL, '2021-07-08 11:34:25', NULL, '2021-07-08 14:24:57', 5, 4, '#parent-menu#', 'fa-folder', 'Repositories', NULL, 25),
(139, NULL, '2021-07-08 11:34:25', NULL, '2021-07-08 14:24:57', 1, 4, 'LIST[LOCATE:(ID=9)]', 'fa-tasks', 'Assets', 138, NULL),
(141, NULL, '2021-07-08 12:12:56', NULL, '2021-07-08 14:24:57', 2, 3, 'LIST[LOCATE:(ID=15)]', 'fa-layer-group', 'Asset Categories', 138, NULL),
(142, NULL, '2021-07-09 23:32:19', NULL, '2021-07-09 23:55:22', 10, 2, 'STATICPAGE[NAME:search-designer-list]', 'fa-cogs', 'Search Designer', NULL, 24),
(143, NULL, '2021-07-10 00:55:11', NULL, '2021-07-10 00:55:11', 25, 1, 'STATICPAGE[NAME:search]', 'search', 'Search', NULL, 2);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `persist_entity`
--

CREATE TABLE `persist_entity` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `creation_version` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `entity_type` varchar(255) NOT NULL,
  `indexes` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `query` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `persist_entity`
--

INSERT INTO `persist_entity` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `creation_version`, `description`, `entity_type`, `indexes`, `name`, `query`) VALUES
(1, 1, '2021-03-05 21:17:39', 1, '2021-07-01 08:59:11', NULL, 28, NULL, NULL, 'Table', NULL, 'asset', NULL),
(2, 1, '2021-03-05 21:24:45', 1, '2021-03-23 12:20:11', NULL, 3, NULL, NULL, 'Table', NULL, 'interface', NULL),
(3, 1, '2021-03-05 21:25:13', 1, '2021-03-05 21:25:13', NULL, 0, NULL, NULL, 'Table', NULL, 'threat', NULL),
(5, 1, '2021-03-05 21:25:29', 1, '2021-03-05 21:25:29', NULL, 0, NULL, NULL, 'Table', NULL, 'vulnerability', NULL),
(6, 1, '2021-03-05 21:27:40', 1, '2021-03-05 21:28:02', NULL, 1, NULL, NULL, 'Table', NULL, 'testtest', NULL),
(7, NULL, '2021-03-09 13:45:40', NULL, '2021-03-09 13:45:40', NULL, 0, NULL, NULL, 'AppView', NULL, 'users_view', 'SELECT u.* , m.name as menu_name\nFROM user u\nLEFT OUTER JOIN menu m on m.id = u.menu_id '),
(8, 0, '2021-04-06 01:41:48', 0, '2021-04-06 01:41:48', NULL, 0, NULL, NULL, 'Table', NULL, 'user', NULL),
(9, 1, '2021-04-15 02:44:36', 1, '2021-04-15 08:12:27', NULL, 1, NULL, 'Entity that contains assets', 'Table', NULL, 'entity', NULL),
(11, 0, '2021-04-20 15:52:28', 0, '2021-04-20 15:52:28', NULL, 0, NULL, NULL, 'Table', NULL, 'entity_asset_assignments', NULL),
(12, 1, '2021-06-16 04:08:11', 1, '2021-07-08 10:48:01', NULL, 2, NULL, NULL, 'Table', NULL, 'threat_repository', NULL),
(13, 1, '2021-06-16 04:09:02', 1, '2021-06-16 04:09:02', NULL, 0, NULL, NULL, 'Table', NULL, 'vulnerability_repository', NULL),
(14, 1, '2021-06-16 04:10:30', 1, '2021-06-16 04:10:30', NULL, 0, NULL, NULL, 'Table', NULL, 'interface_repository', NULL),
(15, 1, '2021-06-16 04:11:16', 1, '2021-07-08 10:49:18', NULL, 2, NULL, NULL, 'Table', NULL, 'asset_repository', NULL),
(16, 1, '2021-07-08 11:01:44', 1, '2021-07-08 11:01:44', NULL, 0, NULL, NULL, 'Table', NULL, 'asset_category', NULL);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `persist_entity_field`
--

CREATE TABLE `persist_entity_field` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `auto_increment` bit(1) DEFAULT NULL,
  `default_value` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `entity_type` varchar(255) NOT NULL,
  `has_default` bit(1) DEFAULT NULL,
  `has_not_null` bit(1) DEFAULT NULL,
  `is_unsigned` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `primary_key` bit(1) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `persist_entity_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `persist_entity_field`
--

INSERT INTO `persist_entity_field` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `auto_increment`, `default_value`, `description`, `entity_type`, `has_default`, `has_not_null`, `is_unsigned`, `name`, `primary_key`, `size`, `type`, `persist_entity_id`) VALUES
(1, 1, '2021-03-05 21:17:39', 1, '2021-07-01 08:59:11', NULL, 28, b'1', NULL, '', 'TableField', b'0', b'1', b'0', 'id', b'1', 20, 'bigint', 1),
(2, 1, '2021-03-05 21:17:39', 1, '2021-07-01 08:59:11', NULL, 28, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'created_by', b'0', 20, 'bigint', 1),
(3, 1, '2021-03-05 21:17:39', 1, '2021-07-01 08:59:11', NULL, 28, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'created_on', b'0', NULL, 'datetime', 1),
(4, 1, '2021-03-05 21:17:39', 1, '2021-07-01 08:59:11', NULL, 28, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'modified_by', b'0', 20, 'bigint', 1),
(5, 1, '2021-03-05 21:17:39', 1, '2021-07-01 08:59:11', NULL, 28, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'modified_on', b'0', NULL, 'datetime', 1),
(6, 1, '2021-03-05 21:17:39', 1, '2021-07-01 08:59:11', NULL, 28, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'short_order', b'0', 20, 'bigint', 1),
(7, 1, '2021-03-05 21:17:39', 1, '2021-07-01 08:59:11', NULL, 28, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'version', b'0', 20, 'bigint', 1),
(8, 1, '2021-03-05 21:17:39', 1, '2021-07-01 08:59:11', NULL, 28, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'code', b'0', 255, 'varchar', 1),
(9, 1, '2021-03-05 21:17:39', 1, '2021-07-01 08:59:11', NULL, 28, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'name', b'0', 255, 'varchar', 1),
(10, 1, '2021-03-05 21:24:45', 1, '2021-03-23 12:20:11', NULL, 3, b'1', NULL, '', 'TableField', b'0', b'1', b'0', 'id', b'1', 20, 'bigint', 2),
(11, 1, '2021-03-05 21:24:45', 1, '2021-03-23 12:20:11', NULL, 3, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'created_by', b'0', 20, 'bigint', 2),
(12, 1, '2021-03-05 21:24:45', 1, '2021-03-23 12:20:11', NULL, 3, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'created_on', b'0', NULL, 'datetime', 2),
(13, 1, '2021-03-05 21:24:45', 1, '2021-03-23 12:20:11', NULL, 3, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'modified_by', b'0', 20, 'bigint', 2),
(14, 1, '2021-03-05 21:24:45', 1, '2021-03-23 12:20:11', NULL, 3, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'modified_on', b'0', NULL, 'datetime', 2),
(15, 1, '2021-03-05 21:24:45', 1, '2021-03-23 12:20:11', NULL, 3, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'short_order', b'0', 20, 'bigint', 2),
(16, 1, '2021-03-05 21:24:45', 1, '2021-03-23 12:20:11', NULL, 3, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'version', b'0', 20, 'bigint', 2),
(17, 1, '2021-03-05 21:24:45', 1, '2021-03-23 12:20:11', NULL, 3, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'code', b'0', 255, 'varchar', 2),
(18, 1, '2021-03-05 21:24:45', 1, '2021-03-23 12:20:11', NULL, 3, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'name', b'0', 255, 'varchar', 2),
(19, 1, '2021-03-05 21:24:45', 1, '2021-03-23 12:20:11', NULL, 3, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'asset_id', b'0', 20, 'bigint', 2),
(20, 1, '2021-03-05 21:25:13', 1, '2021-03-05 21:25:13', NULL, 0, b'1', NULL, '', 'TableField', b'0', b'1', b'0', 'id', b'1', 20, 'bigint', 3),
(21, 1, '2021-03-05 21:25:13', 1, '2021-03-05 21:25:13', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'created_by', b'0', 20, 'bigint', 3),
(22, 1, '2021-03-05 21:25:13', 1, '2021-03-05 21:25:13', NULL, 0, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'created_on', b'0', NULL, 'datetime', 3),
(23, 1, '2021-03-05 21:25:13', 1, '2021-03-05 21:25:13', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'modified_by', b'0', 20, 'bigint', 3),
(24, 1, '2021-03-05 21:25:13', 1, '2021-03-05 21:25:13', NULL, 0, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'modified_on', b'0', NULL, 'datetime', 3),
(25, 1, '2021-03-05 21:25:13', 1, '2021-03-05 21:25:13', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'short_order', b'0', 20, 'bigint', 3),
(26, 1, '2021-03-05 21:25:13', 1, '2021-03-05 21:25:13', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'version', b'0', 20, 'bigint', 3),
(27, 1, '2021-03-05 21:25:13', 1, '2021-03-05 21:25:13', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'code', b'0', 255, 'varchar', 3),
(28, 1, '2021-03-05 21:25:13', 1, '2021-03-05 21:25:13', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'name', b'0', 255, 'varchar', 3),
(29, 1, '2021-03-05 21:25:13', 1, '2021-03-05 21:25:13', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'occurrence_probability', b'0', NULL, 'double', 3),
(30, 1, '2021-03-05 21:25:13', 1, '2021-03-05 21:25:13', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'vulnerability_id', b'0', 20, 'bigint', 3),
(32, 1, '2021-03-05 21:25:29', 1, '2021-03-05 21:25:29', NULL, 0, b'1', NULL, '', 'TableField', b'0', b'1', b'0', 'id', b'1', 20, 'bigint', 5),
(33, 1, '2021-03-05 21:25:29', 1, '2021-03-05 21:25:29', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'created_by', b'0', 20, 'bigint', 5),
(34, 1, '2021-03-05 21:25:29', 1, '2021-03-05 21:25:29', NULL, 0, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'created_on', b'0', NULL, 'datetime', 5),
(35, 1, '2021-03-05 21:25:29', 1, '2021-03-05 21:25:29', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'modified_by', b'0', 20, 'bigint', 5),
(36, 1, '2021-03-05 21:25:29', 1, '2021-03-05 21:25:29', NULL, 0, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'modified_on', b'0', NULL, 'datetime', 5),
(37, 1, '2021-03-05 21:25:29', 1, '2021-03-05 21:25:29', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'short_order', b'0', 20, 'bigint', 5),
(38, 1, '2021-03-05 21:25:29', 1, '2021-03-05 21:25:29', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'version', b'0', 20, 'bigint', 5),
(39, 1, '2021-03-05 21:25:29', 1, '2021-03-05 21:25:29', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'code', b'0', 255, 'varchar', 5),
(40, 1, '2021-03-05 21:25:29', 1, '2021-03-05 21:25:29', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'impact_level', b'0', NULL, 'double', 5),
(41, 1, '2021-03-05 21:25:29', 1, '2021-03-05 21:25:29', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'level', b'0', NULL, 'double', 5),
(42, 1, '2021-03-05 21:25:29', 1, '2021-03-05 21:25:29', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'name', b'0', 255, 'varchar', 5),
(43, 1, '2021-03-05 21:25:29', 1, '2021-03-05 21:25:29', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'interface_id', b'0', 20, 'bigint', 5),
(44, 1, '2021-03-05 21:27:40', 1, '2021-03-05 21:28:02', 0, 1, b'1', '', '', 'TableField', b'0', b'0', b'0', 'id', b'1', NULL, 'bigint', 6),
(45, 1, '2021-03-05 21:28:02', 1, '2021-03-05 21:28:02', 1, 0, b'0', '', '', 'TableField', b'0', b'0', b'0', 'name', b'0', 200, 'varchar', 6),
(46, NULL, '2021-03-09 13:45:40', NULL, '2021-03-09 13:45:40', NULL, 0, NULL, NULL, '', 'AppViewField', NULL, NULL, NULL, 'id', NULL, 20, 'bigint', 7),
(47, NULL, '2021-03-09 13:45:40', NULL, '2021-03-09 13:45:40', NULL, 0, NULL, NULL, '', 'AppViewField', NULL, NULL, NULL, 'created_by', NULL, 20, 'bigint', 7),
(48, NULL, '2021-03-09 13:45:40', NULL, '2021-03-09 13:45:40', NULL, 0, NULL, NULL, '', 'AppViewField', NULL, NULL, NULL, 'created_on', NULL, NULL, 'timestamp', 7),
(49, NULL, '2021-03-09 13:45:40', NULL, '2021-03-09 13:45:40', NULL, 0, NULL, NULL, '', 'AppViewField', NULL, NULL, NULL, 'modified_by', NULL, 20, 'bigint', 7),
(50, NULL, '2021-03-09 13:45:40', NULL, '2021-03-09 13:45:40', NULL, 0, NULL, NULL, '', 'AppViewField', NULL, NULL, NULL, 'modified_on', NULL, NULL, 'timestamp', 7),
(51, NULL, '2021-03-09 13:45:40', NULL, '2021-03-09 13:45:40', NULL, 0, NULL, NULL, '', 'AppViewField', NULL, NULL, NULL, 'short_order', NULL, 20, 'bigint', 7),
(52, NULL, '2021-03-09 13:45:40', NULL, '2021-03-09 13:45:40', NULL, 0, NULL, NULL, '', 'AppViewField', NULL, NULL, NULL, 'version', NULL, 20, 'bigint', 7),
(53, NULL, '2021-03-09 13:45:40', NULL, '2021-03-09 13:45:40', NULL, 0, NULL, NULL, '', 'AppViewField', NULL, NULL, NULL, 'dateformat', NULL, 255, 'varchar', 7),
(54, NULL, '2021-03-09 13:45:40', NULL, '2021-03-09 13:45:40', NULL, 0, NULL, NULL, '', 'AppViewField', NULL, NULL, NULL, 'email', NULL, 255, 'varchar', 7),
(55, NULL, '2021-03-09 13:45:40', NULL, '2021-03-09 13:45:40', NULL, 0, NULL, NULL, '', 'AppViewField', NULL, NULL, NULL, 'password', NULL, 255, 'varchar', 7),
(56, NULL, '2021-03-09 13:45:40', NULL, '2021-03-09 13:45:40', NULL, 0, NULL, NULL, '', 'AppViewField', NULL, NULL, NULL, 'status', NULL, 255, 'varchar', 7),
(57, NULL, '2021-03-09 13:45:40', NULL, '2021-03-09 13:45:40', NULL, 0, NULL, NULL, '', 'AppViewField', NULL, NULL, NULL, 'username', NULL, 255, 'varchar', 7),
(58, NULL, '2021-03-09 13:45:40', NULL, '2021-03-09 13:45:40', NULL, 0, NULL, NULL, '', 'AppViewField', NULL, NULL, NULL, 'menu_id', NULL, 20, 'bigint', 7),
(59, NULL, '2021-03-09 13:45:40', NULL, '2021-03-09 13:45:40', NULL, 0, NULL, NULL, '', 'AppViewField', NULL, NULL, NULL, 'menu_name', NULL, 255, 'varchar', 7),
(61, 1, '2021-03-23 12:20:11', 1, '2021-03-23 12:20:11', 1, 0, b'0', '', '', 'TableField', b'0', b'0', b'0', 'tmp_id', b'0', 20, 'bigint', 2),
(62, 0, '2021-04-06 01:41:48', 0, '2021-04-06 01:41:48', NULL, 0, b'1', NULL, '', 'TableField', b'0', b'1', b'0', 'id', b'1', 20, 'bigint', 8),
(63, 0, '2021-04-06 01:41:48', 0, '2021-04-06 01:41:48', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'created_by', b'0', 20, 'bigint', 8),
(64, 0, '2021-04-06 01:41:48', 0, '2021-04-06 01:41:48', NULL, 0, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'created_on', b'0', NULL, 'datetime', 8),
(65, 0, '2021-04-06 01:41:48', 0, '2021-04-06 01:41:48', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'modified_by', b'0', 20, 'bigint', 8),
(66, 0, '2021-04-06 01:41:48', 0, '2021-04-06 01:41:48', NULL, 0, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'modified_on', b'0', NULL, 'datetime', 8),
(67, 0, '2021-04-06 01:41:48', 0, '2021-04-06 01:41:48', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'short_order', b'0', 20, 'bigint', 8),
(68, 0, '2021-04-06 01:41:48', 0, '2021-04-06 01:41:48', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'version', b'0', 20, 'bigint', 8),
(69, 0, '2021-04-06 01:41:48', 0, '2021-04-06 01:41:48', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'dateformat', b'0', 255, 'varchar', 8),
(70, 0, '2021-04-06 01:41:48', 0, '2021-04-06 01:41:48', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'1', b'0', 'email', b'0', 255, 'varchar', 8),
(71, 0, '2021-04-06 01:41:48', 0, '2021-04-06 01:41:48', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'1', b'0', 'password', b'0', 255, 'varchar', 8),
(72, 0, '2021-04-06 01:41:48', 0, '2021-04-06 01:41:48', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'status', b'0', 255, 'varchar', 8),
(73, 0, '2021-04-06 01:41:48', 0, '2021-04-06 01:41:48', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'1', b'0', 'username', b'0', 255, 'varchar', 8),
(74, 0, '2021-04-06 01:41:48', 0, '2021-04-06 01:41:48', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'menu_id', b'0', 20, 'bigint', 8),
(75, 1, '2021-04-15 02:44:36', 1, '2021-04-15 08:12:27', 0, 1, b'1', '', '', 'TableField', b'0', b'1', b'0', 'id', b'1', 20, 'bigint', 9),
(76, 1, '2021-04-15 02:44:36', 1, '2021-04-15 08:12:27', 1, 1, b'0', '', '', 'TableField', b'0', b'0', b'0', 'name', b'0', 255, 'varchar', 9),
(77, 1, '2021-04-15 02:44:36', 1, '2021-04-15 08:12:27', 2, 1, b'0', '', '', 'TableField', b'0', b'0', b'0', 'created_by', b'0', 20, 'bigint', 9),
(78, 1, '2021-04-15 02:44:36', 1, '2021-04-15 08:12:27', 3, 1, b'0', 'current_timestamp()', '', 'TableField', b'1', b'0', b'0', 'created_on', b'0', NULL, 'datetime', 9),
(79, 1, '2021-04-15 02:44:36', 1, '2021-04-15 08:12:27', 4, 1, b'0', '', '', 'TableField', b'0', b'0', b'0', 'modified_by', b'0', 20, 'bigint', 9),
(80, 1, '2021-04-15 02:44:36', 1, '2021-04-15 08:12:27', 5, 1, b'0', 'current_timestamp()', '', 'TableField', b'1', b'0', b'0', 'modified_on', b'0', NULL, 'datetime', 9),
(81, 1, '2021-04-15 02:48:09', 1, '2021-07-01 08:59:11', 1, 25, b'0', '', '', 'TableField', b'0', b'0', b'0', 'entity_id', b'0', 20, 'bigint', 1),
(85, 1, '2021-04-15 08:12:27', 1, '2021-04-15 08:12:27', 1, 0, b'0', '', '', 'TableField', b'0', b'0', b'0', 'user_id', b'0', 20, 'bigint', 9),
(86, 0, '2021-04-20 15:52:28', 0, '2021-04-20 15:52:28', 0, 0, b'1', '', '', 'TableField', b'0', b'1', b'0', 'id', b'1', 20, 'bigint', 11),
(87, 0, '2021-04-20 15:52:28', 0, '2021-04-20 15:52:28', 1, 0, b'0', '', '', 'TableField', b'0', b'1', b'0', 'entity_id', b'0', 20, 'bigint', 11),
(88, 0, '2021-04-20 15:52:28', 0, '2021-04-20 15:52:28', 2, 0, b'0', '', '', 'TableField', b'0', b'1', b'0', 'asset_id', b'0', 20, 'bigint', 11),
(89, 0, '2021-04-20 15:52:28', 0, '2021-04-20 15:52:28', 3, 0, b'0', '', '', 'TableField', b'0', b'0', b'0', 'asset_prototype_id', b'0', 20, 'bigint', 11),
(90, 1, '2021-06-16 04:08:11', 1, '2021-07-08 10:48:01', NULL, 2, b'1', NULL, '', 'TableField', b'0', b'1', b'0', 'id', b'1', 20, 'bigint', 12),
(91, 1, '2021-06-16 04:08:11', 1, '2021-07-08 10:48:01', NULL, 2, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'created_by', b'0', 20, 'bigint', 12),
(92, 1, '2021-06-16 04:08:11', 1, '2021-07-08 10:48:01', NULL, 2, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'created_on', b'0', NULL, 'datetime', 12),
(93, 1, '2021-06-16 04:08:11', 1, '2021-07-08 10:48:01', NULL, 2, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'modified_by', b'0', 20, 'bigint', 12),
(94, 1, '2021-06-16 04:08:11', 1, '2021-07-08 10:48:01', NULL, 2, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'modified_on', b'0', NULL, 'datetime', 12),
(95, 1, '2021-06-16 04:08:11', 1, '2021-07-08 10:48:01', NULL, 2, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'short_order', b'0', 20, 'bigint', 12),
(96, 1, '2021-06-16 04:08:11', 1, '2021-07-08 10:48:01', NULL, 2, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'version', b'0', 20, 'bigint', 12),
(97, 1, '2021-06-16 04:08:11', 1, '2021-07-08 10:48:01', NULL, 2, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'code', b'0', 255, 'varchar', 12),
(98, 1, '2021-06-16 04:08:11', 1, '2021-07-08 10:48:01', NULL, 2, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'name', b'0', 255, 'varchar', 12),
(99, 1, '2021-06-16 04:08:11', 1, '2021-07-08 10:48:01', NULL, 2, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'occurrence_probability', b'0', NULL, 'double', 12),
(100, 1, '2021-06-16 04:08:11', 1, '2021-07-08 10:48:01', NULL, 2, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'vulnerability_repository_id', b'0', 20, 'bigint', 12),
(101, 1, '2021-06-16 04:09:02', 1, '2021-06-16 04:09:02', NULL, 0, b'1', NULL, '', 'TableField', b'0', b'1', b'0', 'id', b'1', 20, 'bigint', 13),
(102, 1, '2021-06-16 04:09:02', 1, '2021-06-16 04:09:02', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'created_by', b'0', 20, 'bigint', 13),
(103, 1, '2021-06-16 04:09:02', 1, '2021-06-16 04:09:02', NULL, 0, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'created_on', b'0', NULL, 'datetime', 13),
(104, 1, '2021-06-16 04:09:02', 1, '2021-06-16 04:09:02', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'modified_by', b'0', 20, 'bigint', 13),
(105, 1, '2021-06-16 04:09:02', 1, '2021-06-16 04:09:02', NULL, 0, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'modified_on', b'0', NULL, 'datetime', 13),
(106, 1, '2021-06-16 04:09:02', 1, '2021-06-16 04:09:02', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'short_order', b'0', 20, 'bigint', 13),
(107, 1, '2021-06-16 04:09:02', 1, '2021-06-16 04:09:02', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'version', b'0', 20, 'bigint', 13),
(108, 1, '2021-06-16 04:09:02', 1, '2021-06-16 04:09:02', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'code', b'0', 255, 'varchar', 13),
(109, 1, '2021-06-16 04:09:02', 1, '2021-06-16 04:09:02', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'impact_level', b'0', NULL, 'double', 13),
(110, 1, '2021-06-16 04:09:02', 1, '2021-06-16 04:09:02', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'level', b'0', NULL, 'double', 13),
(111, 1, '2021-06-16 04:09:02', 1, '2021-06-16 04:09:02', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'name', b'0', 255, 'varchar', 13),
(112, 1, '2021-06-16 04:09:02', 1, '2021-06-16 04:09:02', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'interface_repository_id', b'0', 20, 'bigint', 13),
(113, 1, '2021-06-16 04:10:30', 1, '2021-06-16 04:10:30', NULL, 0, b'1', NULL, '', 'TableField', b'0', b'1', b'0', 'id', b'1', 20, 'bigint', 14),
(114, 1, '2021-06-16 04:10:30', 1, '2021-06-16 04:10:30', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'created_by', b'0', 20, 'bigint', 14),
(115, 1, '2021-06-16 04:10:30', 1, '2021-06-16 04:10:30', NULL, 0, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'created_on', b'0', NULL, 'datetime', 14),
(116, 1, '2021-06-16 04:10:30', 1, '2021-06-16 04:10:30', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'modified_by', b'0', 20, 'bigint', 14),
(117, 1, '2021-06-16 04:10:30', 1, '2021-06-16 04:10:30', NULL, 0, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'modified_on', b'0', NULL, 'datetime', 14),
(118, 1, '2021-06-16 04:10:30', 1, '2021-06-16 04:10:30', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'short_order', b'0', 20, 'bigint', 14),
(119, 1, '2021-06-16 04:10:30', 1, '2021-06-16 04:10:30', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'version', b'0', 20, 'bigint', 14),
(120, 1, '2021-06-16 04:10:30', 1, '2021-06-16 04:10:30', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'code', b'0', 255, 'varchar', 14),
(121, 1, '2021-06-16 04:10:30', 1, '2021-06-16 04:10:30', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'name', b'0', 255, 'varchar', 14),
(122, 1, '2021-06-16 04:10:30', 1, '2021-06-16 04:10:30', NULL, 0, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'asset_repository_id', b'0', 20, 'bigint', 14),
(123, 1, '2021-06-16 04:10:30', 1, '2021-06-16 04:10:30', 1, 0, b'0', '', '', 'TableField', b'0', b'0', b'0', 'description', b'0', 1000, 'varchar', 14),
(124, 1, '2021-06-16 04:11:16', 1, '2021-07-08 10:49:18', NULL, 2, b'1', NULL, '', 'TableField', b'0', b'1', b'0', 'id', b'1', 20, 'bigint', 15),
(125, 1, '2021-06-16 04:11:16', 1, '2021-07-08 10:49:18', NULL, 2, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'created_by', b'0', 20, 'bigint', 15),
(126, 1, '2021-06-16 04:11:16', 1, '2021-07-08 10:49:18', NULL, 2, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'created_on', b'0', NULL, 'datetime', 15),
(127, 1, '2021-06-16 04:11:16', 1, '2021-07-08 10:49:18', NULL, 2, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'modified_by', b'0', 20, 'bigint', 15),
(128, 1, '2021-06-16 04:11:16', 1, '2021-07-08 10:49:18', NULL, 2, b'0', 'current_timestamp()', '', 'TableField', b'1', b'1', b'0', 'modified_on', b'0', NULL, 'datetime', 15),
(129, 1, '2021-06-16 04:11:16', 1, '2021-07-08 10:49:18', NULL, 2, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'short_order', b'0', 20, 'bigint', 15),
(130, 1, '2021-06-16 04:11:16', 1, '2021-07-08 10:49:18', NULL, 2, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'version', b'0', 20, 'bigint', 15),
(131, 1, '2021-06-16 04:11:16', 1, '2021-07-08 10:49:18', NULL, 2, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'code', b'0', 255, 'varchar', 15),
(132, 1, '2021-06-16 04:11:16', 1, '2021-07-08 10:49:18', NULL, 2, b'0', NULL, '', 'TableField', b'0', b'0', b'0', 'name', b'0', 255, 'varchar', 15),
(133, 1, '2021-06-16 04:11:16', 1, '2021-07-08 10:49:18', 1, 2, b'0', '', '', 'TableField', b'0', b'0', b'0', 'description', b'0', 1000, 'varchar', 15),
(134, 1, '2021-07-01 07:45:58', 1, '2021-07-01 08:59:11', 1, 6, b'0', '', '', 'TableField', b'0', b'0', b'0', 'description', b'0', 500, 'varchar', 1),
(135, 1, '2021-07-01 08:17:52', 1, '2021-07-01 08:59:11', 1, 5, b'0', '', '', 'TableField', b'0', b'0', b'0', 'description2', b'0', 500, 'varchar', 1),
(136, 1, '2021-07-01 08:47:26', 1, '2021-07-01 08:59:11', 1, 1, b'0', '', '', 'TableField', b'0', b'0', b'0', 'asset_repository_id', b'0', 20, 'bigint', 1),
(138, 1, '2021-07-08 10:47:37', 1, '2021-07-08 10:49:18', 1, 1, b'0', '', '', 'TableField', b'0', b'0', b'0', 'business_value', b'0', 20, 'int', 15),
(139, 1, '2021-07-08 10:49:18', 1, '2021-07-08 10:49:18', 1, 0, b'0', '', '', 'TableField', b'0', b'0', b'0', 'asset_category_id', b'0', 20, 'bigint', 15),
(140, 1, '2021-07-08 11:01:44', 1, '2021-07-08 11:01:44', 0, 0, b'1', '', '', 'TableField', b'0', b'1', b'0', 'id', b'1', 20, 'bigint', 16),
(141, 1, '2021-07-08 11:01:44', 1, '2021-07-08 11:01:44', 1, 0, b'0', '', '', 'TableField', b'0', b'0', b'0', 'created_by', b'0', 20, 'bigint', 16),
(142, 1, '2021-07-08 11:01:44', 1, '2021-07-08 11:01:44', 2, 0, b'0', 'current_timestamp()', '', 'TableField', b'0', b'0', b'0', 'created_on', b'0', NULL, 'datetime', 16),
(143, 1, '2021-07-08 11:01:44', 1, '2021-07-08 11:01:44', 3, 0, b'0', '', '', 'TableField', b'0', b'0', b'0', 'modified_by', b'0', 20, 'bigint', 16),
(144, 1, '2021-07-08 11:01:44', 1, '2021-07-08 11:01:44', 4, 0, b'0', 'current_timestamp()', '', 'TableField', b'0', b'0', b'0', 'modified_on', b'0', NULL, 'datetime', 16),
(145, 1, '2021-07-08 11:01:44', 1, '2021-07-08 11:01:44', 5, 0, b'0', '', '', 'TableField', b'0', b'0', b'0', 'short_order', b'0', 20, 'bigint', 16),
(146, 1, '2021-07-08 11:01:44', 1, '2021-07-08 11:01:44', 6, 0, b'0', '', '', 'TableField', b'0', b'0', b'0', 'version', b'0', 20, 'bigint', 16),
(147, 1, '2021-07-08 11:01:44', 1, '2021-07-08 11:01:44', 7, 0, b'0', '', '', 'TableField', b'0', b'0', b'0', 'code', b'0', 100, 'varchar', 16),
(148, 1, '2021-07-08 11:01:44', 1, '2021-07-08 11:01:44', 8, 0, b'0', '', '', 'TableField', b'0', b'0', b'0', 'name', b'0', 255, 'varchar', 16),
(149, 1, '2021-07-08 11:01:44', 1, '2021-07-08 11:01:44', 9, 0, b'0', '', '', 'TableField', b'0', b'0', b'0', 'description', b'0', 255, 'varchar', 16);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `report`
--

CREATE TABLE `report` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `report_file_data` longblob DEFAULT NULL,
  `report_filename` varchar(255) DEFAULT NULL,
  `report_uuid` varchar(255) DEFAULT NULL,
  `report_extension` varchar(255) DEFAULT NULL,
  `report_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `report`
--

INSERT INTO `report` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `name`, `type`, `report_file_data`, `report_filename`, `report_uuid`, `report_extension`, `report_type`) VALUES
(1, 1, '2021-06-07 11:10:08', 1, '2021-06-10 11:06:58', NULL, 15, 'Report Code', 'Report Name', NULL, 0x3c3f786d6c2076657273696f6e3d22312e302220656e636f64696e673d225554462d38223f3e0a3c212d2d20437265617465642077697468204a6173706572736f66742053747564696f2076657273696f6e20362e31322e322e66696e616c207573696e67204a61737065725265706f727473204c6962726172792076657273696f6e20362e31322e322d3735633565393061323232616234303665343136636266353930613533393730323861353264653320202d2d3e0a3c6a61737065725265706f727420786d6c6e733d22687474703a2f2f6a61737065727265706f7274732e736f75726365666f7267652e6e65742f6a61737065727265706f7274732220786d6c6e733a7873693d22687474703a2f2f7777772e77332e6f72672f323030312f584d4c536368656d612d696e7374616e636522207873693a736368656d614c6f636174696f6e3d22687474703a2f2f6a61737065727265706f7274732e736f75726365666f7267652e6e65742f6a61737065727265706f72747320687474703a2f2f6a61737065727265706f7274732e736f75726365666f7267652e6e65742f7873642f6a61737065727265706f72742e78736422206e616d653d224c697374446174614578706f727422207061676557696474683d223539352220706167654865696768743d223834322220636f6c756d6e57696474683d2235333522206c6566744d617267696e3d223230222072696768744d617267696e3d2232302220746f704d617267696e3d2232302220626f74746f6d4d617267696e3d2232302220757569643d2239343061303038342d343736372d343038622d613962302d336239346339613631366535223e0a093c6669656c64206e616d653d2269642220636c6173733d226a6176612e6c616e672e496e7465676572222f3e0a093c6669656c64206e616d653d226e616d652220636c6173733d226a6176612e6c616e672e537472696e67222f3e0a093c6669656c64206e616d653d2264657369676e6174696f6e2220636c6173733d226a6176612e6c616e672e537472696e67222f3e0a093c6669656c64206e616d653d2273616c6172792220636c6173733d226a6176612e6c616e672e446f75626c65222f3e0a093c6669656c64206e616d653d22646f6a2220636c6173733d226a6176612e6c616e672e537472696e67222f3e0a093c6261636b67726f756e643e0a09093c62616e642f3e0a093c2f6261636b67726f756e643e0a093c7469746c653e0a09093c62616e64206865696768743d223732223e0a0909093c6672616d653e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d222d32302220793d222d3230222077696474683d2235393522206865696768743d2239322220757569643d2237653365646663342d336264362d346665662d623664352d623662333565306139653065222f3e0a090909093c737461746963546578743e0a09090909093c7265706f7274456c656d656e7420783d2232302220793d223230222077696474683d2232333422206865696768743d2234332220757569643d2236643839343763392d353030352d346562612d393133372d653935306434396236386331222f3e0a09090909093c74657874456c656d656e743e0a0909090909093c666f6e742073697a653d22333422206973426f6c643d2274727565222f3e0a09090909093c2f74657874456c656d656e743e0a09090909093c746578743e3c215b43444154415b536f666961206578706f72745d5d3e3c2f746578743e0a090909093c2f737461746963546578743e0a090909093c737461746963546578743e0a09090909093c7265706f7274456c656d656e7420783d223339352220793d223433222077696474683d2231383022206865696768743d2232302220757569643d2232393035386636612d633464642d346634652d393931392d306265346538373336646234222f3e0a09090909093c74657874456c656d656e742074657874416c69676e6d656e743d225269676874223e0a0909090909093c666f6e742073697a653d22313422206973426f6c643d2266616c7365222f3e0a09090909093c2f74657874456c656d656e743e0a09090909093c746578743e3c215b43444154415b457863656c206578706f72745d5d3e3c2f746578743e0a090909093c2f737461746963546578743e0a0909093c2f6672616d653e0a09093c2f62616e643e0a093c2f7469746c653e0a093c706167654865616465723e0a09093c62616e64206865696768743d223133222f3e0a093c2f706167654865616465723e0a093c636f6c756d6e4865616465723e0a09093c62616e64206865696768743d223231223e0a0909093c6c696e653e0a090909093c7265706f7274456c656d656e7420783d222d32302220793d223230222077696474683d2235393522206865696768743d22312220757569643d2261373864393864382d386336322d343532342d613131382d306437393965353834643133222f3e0a0909093c2f6c696e653e0a0909093c737461746963546578743e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d22302220793d2230222077696474683d2231313122206865696768743d2232302220757569643d2231356361383566322d616465642d346338622d396364372d623034396239396233356362223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2235313332666432362d383962652d346230632d616237642d656631663938383737316161222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e742074657874416c69676e6d656e743d2243656e746572223e0a09090909093c666f6e742073697a653d22313422206973426f6c643d2274727565222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578743e3c215b43444154415b49645d5d3e3c2f746578743e0a0909093c2f737461746963546578743e0a0909093c737461746963546578743e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d223131312220793d2230222077696474683d2231313122206865696768743d2232302220757569643d2230656465346164332d363537352d346465662d616234322d376436633261376333666363223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2237343335366366312d666163392d346561652d613736342d626464653865383031366139222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e742074657874416c69676e6d656e743d2243656e746572223e0a09090909093c666f6e742073697a653d22313422206973426f6c643d2274727565222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578743e3c215b43444154415b4e616d655d5d3e3c2f746578743e0a0909093c2f737461746963546578743e0a0909093c737461746963546578743e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d223232322220793d2230222077696474683d2231313122206865696768743d2232302220757569643d2239323439346466322d306164352d343432362d616335622d646261616666316430376264223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2239366330373061652d346163362d346166322d383432312d653735346562326332396461222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e742074657874416c69676e6d656e743d2243656e746572223e0a09090909093c666f6e742073697a653d22313422206973426f6c643d2274727565222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578743e3c215b43444154415b44657369676e61746f725d5d3e3c2f746578743e0a0909093c2f737461746963546578743e0a0909093c737461746963546578743e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d223333332220793d2230222077696474683d2231313122206865696768743d2232302220757569643d2231616364663466352d373938622d346431622d396537332d353533663366626330373365223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2261343039666465632d333066642d346330662d613037642d633562306234303830653731222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e742074657874416c69676e6d656e743d2243656e746572223e0a09090909093c666f6e742073697a653d22313422206973426f6c643d2274727565222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578743e3c215b43444154415b53616c6172795d5d3e3c2f746578743e0a0909093c2f737461746963546578743e0a0909093c737461746963546578743e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d223434342220793d2230222077696474683d2231313122206865696768743d2232302220757569643d2230316637333934362d353937652d343061352d623963642d386333643730373833663436223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2238303736386531662d633963372d343961322d623037632d633239356264323766363866222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e742074657874416c69676e6d656e743d2243656e746572223e0a09090909093c666f6e742073697a653d22313422206973426f6c643d2274727565222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578743e3c215b43444154415b442e4f2e4a5d5d3e3c2f746578743e0a0909093c2f737461746963546578743e0a09093c2f62616e643e0a093c2f636f6c756d6e4865616465723e0a093c64657461696c3e0a09093c62616e64206865696768743d223230223e0a0909093c6c696e653e0a090909093c7265706f7274456c656d656e7420706f736974696f6e547970653d2246697852656c6174697665546f426f74746f6d2220783d22302220793d223139222077696474683d2235353522206865696768743d22312220757569643d2230373233613637322d646139342d346164392d613764352d313964353830393337383665222f3e0a0909093c2f6c696e653e0a0909093c746578744669656c643e0a090909093c7265706f7274456c656d656e7420783d22302220793d2230222077696474683d2231313122206865696768743d2232302220757569643d2261646265363862392d306539382d346562322d383665322d626465616433353231626331223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2235313332666432362d383962652d346230632d616237642d656631663938383737316161222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e743e0a09090909093c666f6e742073697a653d223134222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b24467b69647d5d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a0909093c746578744669656c643e0a090909093c7265706f7274456c656d656e7420783d223131312220793d2230222077696474683d2231313122206865696768743d2232302220757569643d2230663533313833662d303262612d346333392d626432332d343365386137333963343732223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2237343335366366312d666163392d346561652d613736342d626464653865383031366139222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e743e0a09090909093c666f6e742073697a653d223134222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b24467b6e616d657d5d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a0909093c746578744669656c643e0a090909093c7265706f7274456c656d656e7420783d223232322220793d2230222077696474683d2231313122206865696768743d2232302220757569643d2237393333393430622d636235612d343931362d396230312d623339656335643136383237223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2239366330373061652d346163362d346166322d383432312d653735346562326332396461222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e743e0a09090909093c666f6e742073697a653d223134222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b24467b64657369676e6174696f6e7d5d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a0909093c746578744669656c643e0a090909093c7265706f7274456c656d656e7420783d223333332220793d2230222077696474683d2231313122206865696768743d2232302220757569643d2265323462393337342d333861372d343563662d383731332d663937643666306130396537223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2261343039666465632d333066642d346330662d613037642d633562306234303830653731222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e743e0a09090909093c666f6e742073697a653d223134222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b24467b73616c6172797d5d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a0909093c746578744669656c643e0a090909093c7265706f7274456c656d656e7420783d223434342220793d2230222077696474683d2231313122206865696768743d2232302220757569643d2236376365306662642d326435322d343962302d626462322d303534333637613136333639223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2238303736386531662d633963372d343961322d623037632d633239356264323766363866222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e743e0a09090909093c666f6e742073697a653d223134222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b24467b646f6a7d5d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a09093c2f62616e643e0a093c2f64657461696c3e0a093c636f6c756d6e466f6f7465723e0a09093c62616e642f3e0a093c2f636f6c756d6e466f6f7465723e0a093c70616765466f6f7465723e0a09093c62616e64206865696768743d223137223e0a0909093c746578744669656c643e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d22302220793d2234222077696474683d2235313522206865696768743d2231332220757569643d2231636234306331382d326666612d343430622d623766322d366266323730313864646136222f3e0a090909093c74657874456c656d656e742074657874416c69676e6d656e743d225269676874222f3e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b225061676520222b24567b504147455f4e554d4245527d2b22206f66225d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a0909093c746578744669656c64206576616c756174696f6e54696d653d225265706f7274223e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d223531352220793d2234222077696474683d22343022206865696768743d2231332220757569643d2236343366366137352d396339652d343163312d613331612d333332363237613733353030222f3e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b222022202b2024567b504147455f4e554d4245527d5d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a0909093c746578744669656c64207061747465726e3d224545454545206464204d4d4d4d4d2079797979223e0a090909093c7265706f7274456c656d656e7420783d22302220793d2234222077696474683d2231303022206865696768743d2231332220757569643d2263396338363163632d383964302d343764642d623636362d643434623534653065306532222f3e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b6e6577206a6176612e7574696c2e4461746528295d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a09093c2f62616e643e0a093c2f70616765466f6f7465723e0a093c73756d6d6172793e0a09093c62616e642f3e0a093c2f73756d6d6172793e0a3c2f6a61737065725265706f72743e0a, 'ListDataExport.jrxml', '440848ef-5f6a-4305-b0f8-9bf5edcd8ae1', 'jrxml', 'pdf'),
(3, 0, '2021-06-08 11:35:25', 0, '2021-06-11 04:22:50', NULL, 40, 'test', 'lkkk', NULL, 0x3c3f786d6c2076657273696f6e3d22312e302220656e636f64696e673d225554462d38223f3e0a3c212d2d20437265617465642077697468204a6173706572736f66742053747564696f2076657273696f6e20362e31322e322e66696e616c207573696e67204a61737065725265706f727473204c6962726172792076657273696f6e20362e31322e322d3735633565393061323232616234303665343136636266353930613533393730323861353264653320202d2d3e0a3c6a61737065725265706f727420786d6c6e733d22687474703a2f2f6a61737065727265706f7274732e736f75726365666f7267652e6e65742f6a61737065727265706f7274732220786d6c6e733a7873693d22687474703a2f2f7777772e77332e6f72672f323030312f584d4c536368656d612d696e7374616e636522207873693a736368656d614c6f636174696f6e3d22687474703a2f2f6a61737065727265706f7274732e736f75726365666f7267652e6e65742f6a61737065727265706f72747320687474703a2f2f6a61737065727265706f7274732e736f75726365666f7267652e6e65742f7873642f6a61737065727265706f72742e78736422206e616d653d226872657022207061676557696474683d223539352220706167654865696768743d223834322220636f6c756d6e57696474683d2235333522206c6566744d617267696e3d223230222072696768744d617267696e3d2232302220746f704d617267696e3d2232302220626f74746f6d4d617267696e3d2232302220757569643d2238323433623332632d363362632d346538612d383364332d663662316537326434323766223e0a093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e646174612e73716c2e7461626c6573222076616c75653d226332396d615745304c6e567a5a5849674c4445314c4445314c475978593259795954526c4c54466d4f4749744e44566b4e5331695954646d4c574d3359574e6b4e444e694e6a41775954733d222f3e0a093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e646174612e73716c2e53514c517565727944657369676e65722e736173682e7731222076616c75653d22343739222f3e0a093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e646174612e73716c2e53514c517565727944657369676e65722e736173682e7732222076616c75653d22353231222f3e0a093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e646174612e64656661756c746461746161646170746572222076616c75653d224d7953716c204f6e20446f636b6572222f3e0a093c706172616d65746572206e616d653d2269642220636c6173733d226a6176612e6c616e672e537472696e6722206973466f7250726f6d7074696e673d2266616c7365222f3e0a093c7175657279537472696e67206c616e67756167653d2253514c223e0a09093c215b43444154415b53454c454354202a0a46524f4d20736f666961342e75736572205748455245206964203d2024507b69647d5d5d3e0a093c2f7175657279537472696e673e0a093c6669656c64206e616d653d22637265617465645f62792220636c6173733d226a6176612e6c616e672e4c6f6e67223e0a09093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e6669656c642e6c6162656c222076616c75653d22637265617465645f6279222f3e0a09093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e6669656c642e747265652e70617468222076616c75653d2275736572222f3e0a093c2f6669656c643e0a093c6669656c64206e616d653d22637265617465645f6f6e2220636c6173733d226a6176612e73716c2e54696d657374616d70223e0a09093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e6669656c642e6c6162656c222076616c75653d22637265617465645f6f6e222f3e0a09093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e6669656c642e747265652e70617468222076616c75653d2275736572222f3e0a093c2f6669656c643e0a093c6669656c64206e616d653d226d6f6469666965645f62792220636c6173733d226a6176612e6c616e672e4c6f6e67223e0a09093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e6669656c642e6c6162656c222076616c75653d226d6f6469666965645f6279222f3e0a09093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e6669656c642e747265652e70617468222076616c75653d2275736572222f3e0a093c2f6669656c643e0a093c6669656c64206e616d653d226d6f6469666965645f6f6e2220636c6173733d226a6176612e73716c2e54696d657374616d70223e0a09093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e6669656c642e6c6162656c222076616c75653d226d6f6469666965645f6f6e222f3e0a09093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e6669656c642e747265652e70617468222076616c75653d2275736572222f3e0a093c2f6669656c643e0a093c6669656c64206e616d653d22656d61696c2220636c6173733d226a6176612e6c616e672e537472696e67223e0a09093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e6669656c642e6c6162656c222076616c75653d22656d61696c222f3e0a09093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e6669656c642e747265652e70617468222076616c75653d2275736572222f3e0a093c2f6669656c643e0a093c6669656c64206e616d653d2270617373776f72642220636c6173733d226a6176612e6c616e672e537472696e67223e0a09093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e6669656c642e6c6162656c222076616c75653d2270617373776f7264222f3e0a09093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e6669656c642e747265652e70617468222076616c75653d2275736572222f3e0a093c2f6669656c643e0a093c6669656c64206e616d653d22757365726e616d652220636c6173733d226a6176612e6c616e672e537472696e67223e0a09093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e6669656c642e6c6162656c222076616c75653d22757365726e616d65222f3e0a09093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e6669656c642e747265652e70617468222076616c75653d2275736572222f3e0a093c2f6669656c643e0a093c6261636b67726f756e643e0a09093c62616e642f3e0a093c2f6261636b67726f756e643e0a093c7469746c653e0a09093c62616e64206865696768743d223732223e0a0909093c6672616d653e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d222d32302220793d222d3230222077696474683d2235393522206865696768743d22393222206261636b636f6c6f723d22233030363639392220757569643d2232613765396639352d646338352d346336332d396436372d383164343264363237386539222f3e0a090909093c737461746963546578743e0a09090909093c7265706f7274456c656d656e7420783d2232302220793d223230222077696474683d2232333422206865696768743d2234332220666f7265636f6c6f723d22234646464646462220757569643d2266653232363962622d616337342d343230352d383930382d386466383338376532353933222f3e0a09090909093c74657874456c656d656e743e0a0909090909093c666f6e742073697a653d22333422206973426f6c643d2274727565222f3e0a09090909093c2f74657874456c656d656e743e0a09090909093c746578743e3c215b43444154415b5469746c655d5d3e3c2f746578743e0a090909093c2f737461746963546578743e0a090909093c737461746963546578743e0a09090909093c7265706f7274456c656d656e7420783d223339352220793d223433222077696474683d2231383022206865696768743d2232302220666f7265636f6c6f723d22234646464646462220757569643d2265653936643662392d633039352d343130662d626538652d363066353861373663353965222f3e0a09090909093c74657874456c656d656e742074657874416c69676e6d656e743d225269676874223e0a0909090909093c666f6e742073697a653d22313422206973426f6c643d2266616c7365222f3e0a09090909093c2f74657874456c656d656e743e0a09090909093c746578743e3c215b43444154415b4164642061206465736372697074696f6e20686572655d5d3e3c2f746578743e0a090909093c2f737461746963546578743e0a0909093c2f6672616d653e0a09093c2f62616e643e0a093c2f7469746c653e0a093c706167654865616465723e0a09093c62616e64206865696768743d223133222f3e0a093c2f706167654865616465723e0a093c636f6c756d6e4865616465723e0a09093c62616e64206865696768743d223231223e0a0909093c6c696e653e0a090909093c7265706f7274456c656d656e7420783d222d32302220793d223230222077696474683d2235393522206865696768743d22312220666f7265636f6c6f723d22233636363636362220757569643d2264646263373939332d376263382d346561652d613738392d623239366631393430333731222f3e0a0909093c2f6c696e653e0a0909093c737461746963546578743e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d22302220793d2230222077696474683d22373922206865696768743d2232302220666f7265636f6c6f723d222330303636393922206261636b636f6c6f723d22234536453645362220757569643d2265643761636639622d626261362d346330362d613533662d303332363033633039653465223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2232656335666364642d343961392d343232612d613162332d353633316139326565613632222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e742074657874416c69676e6d656e743d2243656e746572223e0a09090909093c666f6e742073697a653d22313422206973426f6c643d2274727565222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578743e3c215b43444154415b637265617465645f62795d5d3e3c2f746578743e0a0909093c2f737461746963546578743e0a0909093c737461746963546578743e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d2237392220793d2230222077696474683d22373922206865696768743d2232302220666f7265636f6c6f723d222330303636393922206261636b636f6c6f723d22234536453645362220757569643d2266393632643461662d656239622d346537652d386266622d356132653463333734356533223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2266373065313738652d663030342d346430392d393532322d636632303233383565303563222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e742074657874416c69676e6d656e743d2243656e746572223e0a09090909093c666f6e742073697a653d22313422206973426f6c643d2274727565222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578743e3c215b43444154415b637265617465645f6f6e5d5d3e3c2f746578743e0a0909093c2f737461746963546578743e0a0909093c737461746963546578743e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d223135382220793d2230222077696474683d22373922206865696768743d2232302220666f7265636f6c6f723d222330303636393922206261636b636f6c6f723d22234536453645362220757569643d2261316231323864322d316231352d343530632d393063382d653661356563313330633232223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2236346531633832372d323835322d346466362d623739362d323762373231373765356430222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e742074657874416c69676e6d656e743d2243656e746572223e0a09090909093c666f6e742073697a653d22313422206973426f6c643d2274727565222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578743e3c215b43444154415b6d6f6469666965645f62795d5d3e3c2f746578743e0a0909093c2f737461746963546578743e0a0909093c737461746963546578743e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d223233372220793d2230222077696474683d22373922206865696768743d2232302220666f7265636f6c6f723d222330303636393922206261636b636f6c6f723d22234536453645362220757569643d2263383961373165642d396335352d343861342d383930392d396430616132633334313961223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2262363733396339652d326461612d346661302d616233612d623964373532643438636533222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e742074657874416c69676e6d656e743d2243656e746572223e0a09090909093c666f6e742073697a653d22313422206973426f6c643d2274727565222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578743e3c215b43444154415b6d6f6469666965645f6f6e5d5d3e3c2f746578743e0a0909093c2f737461746963546578743e0a0909093c737461746963546578743e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d223331362220793d2230222077696474683d22373922206865696768743d2232302220666f7265636f6c6f723d222330303636393922206261636b636f6c6f723d22234536453645362220757569643d2236333262373737612d643862642d346339652d626139652d313965346263376134303434223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2265306438313765372d643363642d346336662d613836662d666161313366643462343365222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e742074657874416c69676e6d656e743d2243656e746572223e0a09090909093c666f6e742073697a653d22313422206973426f6c643d2274727565222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578743e3c215b43444154415b656d61696c5d5d3e3c2f746578743e0a0909093c2f737461746963546578743e0a0909093c737461746963546578743e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d223339352220793d2230222077696474683d22373922206865696768743d2232302220666f7265636f6c6f723d222330303636393922206261636b636f6c6f723d22234536453645362220757569643d2230353061343436392d303930392d346637612d623434372d663036366635383537306365223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2238303035656430642d383535312d343331622d393063332d663663356136306634353337222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e742074657874416c69676e6d656e743d2243656e746572223e0a09090909093c666f6e742073697a653d22313422206973426f6c643d2274727565222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578743e3c215b43444154415b70617373776f72645d5d3e3c2f746578743e0a0909093c2f737461746963546578743e0a0909093c737461746963546578743e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d223437342220793d2230222077696474683d22373922206865696768743d2232302220666f7265636f6c6f723d222330303636393922206261636b636f6c6f723d22234536453645362220757569643d2236306333363864372d366432302d346438332d383865312d393666306630666537643734223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2239353133343830302d613036632d346530652d613334312d316166346364653836633637222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e742074657874416c69676e6d656e743d2243656e746572223e0a09090909093c666f6e742073697a653d22313422206973426f6c643d2274727565222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578743e3c215b43444154415b757365726e616d655d5d3e3c2f746578743e0a0909093c2f737461746963546578743e0a09093c2f62616e643e0a093c2f636f6c756d6e4865616465723e0a093c64657461696c3e0a09093c62616e64206865696768743d223230223e0a0909093c6c696e653e0a090909093c7265706f7274456c656d656e7420706f736974696f6e547970653d2246697852656c6174697665546f426f74746f6d2220783d22302220793d223139222077696474683d2235353522206865696768743d22312220757569643d2237643364306435382d613061622d343832372d393862372d623066643161626439356566222f3e0a0909093c2f6c696e653e0a0909093c746578744669656c643e0a090909093c7265706f7274456c656d656e7420783d22302220793d2230222077696474683d22373922206865696768743d2232302220757569643d2234333333326664652d643339632d343865342d393533662d326334376533366264356335223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2232656335666364642d343961392d343232612d613162332d353633316139326565613632222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e743e0a09090909093c666f6e742073697a653d223134222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b24467b637265617465645f62797d5d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a0909093c746578744669656c643e0a090909093c7265706f7274456c656d656e7420783d2237392220793d2230222077696474683d22373922206865696768743d2232302220757569643d2238373731303932312d646637372d346162392d613831322d623364613232643937346261223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2266373065313738652d663030342d346430392d393532322d636632303233383565303563222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e743e0a09090909093c666f6e742073697a653d223134222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b24467b637265617465645f6f6e7d5d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a0909093c746578744669656c643e0a090909093c7265706f7274456c656d656e7420783d223135382220793d2230222077696474683d22373922206865696768743d2232302220757569643d2264373364353861382d383038312d343262342d613931612d303264633539303133653464223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2236346531633832372d323835322d346466362d623739362d323762373231373765356430222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e743e0a09090909093c666f6e742073697a653d223134222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b24467b6d6f6469666965645f62797d5d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a0909093c746578744669656c643e0a090909093c7265706f7274456c656d656e7420783d223233372220793d2230222077696474683d22373922206865696768743d2232302220757569643d2262343163386335312d363635652d343831362d386436622d346664306663666435646261223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2262363733396339652d326461612d346661302d616233612d623964373532643438636533222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e743e0a09090909093c666f6e742073697a653d223134222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b24467b6d6f6469666965645f6f6e7d5d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a0909093c746578744669656c643e0a090909093c7265706f7274456c656d656e7420783d223331362220793d2230222077696474683d22373922206865696768743d2232302220757569643d2233333337336564302d373338372d343830392d616161362d653639316166343137666234223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2265306438313765372d643363642d346336662d613836662d666161313366643462343365222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e743e0a09090909093c666f6e742073697a653d223134222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b24467b656d61696c7d5d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a0909093c746578744669656c643e0a090909093c7265706f7274456c656d656e7420783d223339352220793d2230222077696474683d22373922206865696768743d2232302220757569643d2261313762393864612d366131312d343133652d393835392d373761643933353666333865223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2238303035656430642d383535312d343331622d393063332d663663356136306634353337222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e743e0a09090909093c666f6e742073697a653d223134222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b24467b70617373776f72647d5d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a0909093c746578744669656c643e0a090909093c7265706f7274456c656d656e7420783d223437342220793d2230222077696474683d22373922206865696768743d2232302220757569643d2265646638636364392d383730352d346238352d626266662d393031663539333336656261223e0a09090909093c70726f7065727479206e616d653d22636f6d2e6a6173706572736f66742e73747564696f2e73707265616473686565742e636f6e6e656374696f6e4944222076616c75653d2239353133343830302d613036632d346530652d613334312d316166346364653836633637222f3e0a090909093c2f7265706f7274456c656d656e743e0a090909093c74657874456c656d656e743e0a09090909093c666f6e742073697a653d223134222f3e0a090909093c2f74657874456c656d656e743e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b24467b757365726e616d657d5d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a09093c2f62616e643e0a093c2f64657461696c3e0a093c636f6c756d6e466f6f7465723e0a09093c62616e642f3e0a093c2f636f6c756d6e466f6f7465723e0a093c70616765466f6f7465723e0a09093c62616e64206865696768743d223137223e0a0909093c746578744669656c643e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d22302220793d2234222077696474683d2235313522206865696768743d22313322206261636b636f6c6f723d22234536453645362220757569643d2264323830663135392d363130632d343731662d613764332d636464326262623234616662222f3e0a090909093c74657874456c656d656e742074657874416c69676e6d656e743d225269676874222f3e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b225061676520222b24567b504147455f4e554d4245527d2b22206f66225d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a0909093c746578744669656c64206576616c756174696f6e54696d653d225265706f7274223e0a090909093c7265706f7274456c656d656e74206d6f64653d224f70617175652220783d223531352220793d2234222077696474683d22343022206865696768743d22313322206261636b636f6c6f723d22234536453645362220757569643d2265346165656661322d643639382d346264642d393636372d393634636466656437323462222f3e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b222022202b2024567b504147455f4e554d4245527d5d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a0909093c746578744669656c64207061747465726e3d224545454545206464204d4d4d4d4d2079797979223e0a090909093c7265706f7274456c656d656e7420783d22302220793d2234222077696474683d2231303022206865696768743d2231332220757569643d2238653834323663322d336638632d343032652d396266342d343330363638333333326439222f3e0a090909093c746578744669656c6445787072657373696f6e3e3c215b43444154415b6e6577206a6176612e7574696c2e4461746528295d5d3e3c2f746578744669656c6445787072657373696f6e3e0a0909093c2f746578744669656c643e0a09093c2f62616e643e0a093c2f70616765466f6f7465723e0a093c73756d6d6172793e0a09093c62616e642f3e0a093c2f73756d6d6172793e0a3c2f6a61737065725265706f72743e0a, 'hrep.jrxml', 'df5a14a2-f1b1-40a7-a36b-3adcf9d5c11b', 'jrxml', 'pdf');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `report_parameter`
--

CREATE TABLE `report_parameter` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `report_code` varchar(255) DEFAULT NULL,
  `report_id` bigint(20) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `report_parameter`
--

INSERT INTO `report_parameter` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `description`, `report_code`, `report_id`, `value`) VALUES
(1, NULL, '2021-06-07 14:10:08', NULL, '2021-06-10 14:06:58', NULL, 15, 'r1', 'rr1 description', 'rr1', 1, '1'),
(3, NULL, '2021-06-07 14:10:16', NULL, '2021-06-10 14:06:58', NULL, 13, 'd', '33', 'f', 1, '2'),
(4, NULL, '2021-06-07 14:10:16', NULL, '2021-06-10 14:06:58', NULL, 13, 'd2', '44', 'j2', 1, '3'),
(5, NULL, '2021-06-08 14:35:25', NULL, '2021-06-11 07:22:50', NULL, 40, 'id', 'we', 'dds', 3, '1');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `search`
--

CREATE TABLE `search` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `query` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `search`
--

INSERT INTO `search` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `name`, `query`) VALUES
(1, NULL, '2021-07-10 00:12:00', NULL, '2021-07-10 00:12:00', NULL, 0, 'test', 'test2');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `testtest`
--

CREATE TABLE `testtest` (
  `id` bigint(20) NOT NULL,
  `name` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `threat`
--

CREATE TABLE `threat` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `occurrence_probability` double DEFAULT NULL,
  `vulnerability_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `threat`
--

INSERT INTO `threat` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `name`, `occurrence_probability`, `vulnerability_id`) VALUES
(13825, NULL, '2021-04-05 09:25:04', NULL, '2021-04-05 09:25:04', NULL, NULL, NULL, NULL, NULL, 2320),
(13826, NULL, '2021-04-05 09:28:15', NULL, '2021-04-05 09:28:15', NULL, NULL, NULL, NULL, NULL, 2321),
(13827, NULL, '2021-04-05 09:29:23', NULL, '2021-04-05 09:29:23', NULL, NULL, NULL, NULL, NULL, 2322),
(13828, NULL, '2021-04-05 09:29:34', NULL, '2021-04-05 09:29:34', NULL, NULL, NULL, NULL, NULL, 2323),
(13829, NULL, '2021-04-05 09:30:17', NULL, '2021-04-05 09:30:17', NULL, NULL, NULL, NULL, NULL, 2324);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `threat_repository`
--

CREATE TABLE `threat_repository` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` datetime NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `occurrence_probability` double DEFAULT NULL,
  `vulnerability_repository_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `threat_repository`
--

INSERT INTO `threat_repository` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `name`, `occurrence_probability`, `vulnerability_repository_id`) VALUES
(346, NULL, '2021-06-25 18:59:06', NULL, '2021-06-25 18:59:06', NULL, NULL, 'THREAT CODE1', 'THREAT NAME1', NULL, 176),
(347, NULL, '2021-06-25 18:59:06', NULL, '2021-06-25 18:59:06', NULL, NULL, 'THREAT CODE2', 'THREAT NAME2', NULL, 176),
(348, NULL, '2021-06-25 18:59:06', NULL, '2021-06-25 18:59:06', NULL, NULL, 'THREAT CODE3', 'THREAT NAME3', NULL, 177),
(349, NULL, '2021-06-25 18:59:06', NULL, '2021-06-25 18:59:06', NULL, NULL, 'THREAT CODE5', 'THREAT NAME5', NULL, 178),
(356, NULL, '2021-07-06 15:20:43', NULL, '2021-07-06 15:20:43', NULL, NULL, 'THREAT CODE7', 'THREAT NAME7', NULL, 184),
(357, NULL, '2021-07-06 15:20:43', NULL, '2021-07-06 15:20:43', NULL, NULL, 'THREAT CODE8', 'THREAT NAME8', NULL, 185),
(358, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'THREAT CODE1', 'THREAT NAME1', NULL, 186),
(359, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'THREAT CODE2', 'THREAT NAME2', NULL, 186),
(360, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'THREAT CODE3', 'THREAT NAME3', NULL, 187),
(361, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'THREAT CODE5', 'THREAT NAME5', NULL, 188),
(362, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'THREAT CODE7', 'THREAT NAME7', NULL, 189),
(363, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'THREAT CODE8', 'THREAT NAME8', NULL, 190),
(364, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'THREAT CODE1', 'THREAT NAME1', NULL, 191),
(365, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'THREAT CODE2', 'THREAT NAME2', NULL, 191),
(366, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'THREAT CODE3', 'THREAT NAME3', NULL, 192),
(367, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'THREAT CODE5', 'THREAT NAME5', NULL, 193),
(368, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'THREAT CODE7', 'THREAT NAME7', NULL, 194),
(369, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'THREAT CODE8', 'THREAT NAME8', NULL, 195),
(370, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'THREAT CODE1', 'THREAT NAME1', NULL, 196),
(371, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'THREAT CODE2', 'THREAT NAME2', NULL, 196),
(372, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'THREAT CODE3', 'THREAT NAME3', NULL, 197),
(373, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'THREAT CODE5', 'THREAT NAME5', NULL, 198),
(374, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'THREAT CODE7', 'THREAT NAME7', NULL, 199),
(375, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'THREAT CODE8', 'THREAT NAME8', NULL, 200),
(376, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'THREAT CODE1', 'THREAT NAME1', NULL, 201),
(377, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'THREAT CODE2', 'THREAT NAME2', NULL, 201),
(378, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'THREAT CODE3', 'THREAT NAME3', NULL, 202),
(379, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'THREAT CODE5', 'THREAT NAME5', NULL, 203),
(380, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'THREAT CODE7', 'THREAT NAME7', NULL, 204),
(381, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'THREAT CODE8', 'THREAT NAME8', NULL, 205),
(382, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'THREAT CODE1', 'THREAT NAME1', NULL, 206),
(383, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'THREAT CODE2', 'THREAT NAME2', NULL, 206),
(384, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'THREAT CODE3', 'THREAT NAME3', NULL, 207),
(385, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'THREAT CODE5', 'THREAT NAME5', NULL, 208),
(386, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'THREAT CODE7', 'THREAT NAME7', NULL, 209),
(387, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'THREAT CODE8', 'THREAT NAME8', NULL, 210);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `dateformat` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `login_nav_command` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  `header_menu_id` bigint(20) DEFAULT NULL,
  `sidebar_menu_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `user`
--

INSERT INTO `user` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `dateformat`, `email`, `login_nav_command`, `password`, `status`, `username`, `menu_id`, `header_menu_id`, `sidebar_menu_id`) VALUES
(1, 0, '2020-09-06 01:47:09', 1, '2021-07-08 09:13:29', NULL, 6, NULL, 'admin@admin.gr', 'STATICPAGE[NAME:dashboard,LOCATE:(ID=1)]', '$2a$10$5G7Hq15Jm5wGzuzWENMv3.iUVvk/NbprO8vd9S/tExnVdi7VKrEbS', 'enabled', 'admin', 2, 23, 25),
(2, 0, '2020-09-07 21:33:01', 1, '2020-10-06 05:36:08', NULL, 1, NULL, 'karagozidis', NULL, '$2a$10$fGJDW/vPWu8Y5O.2KnsrwuxN/aMckEeXD1aZzN4t6D0J16b8EhJeu', 'enabled', 'helias', 22, NULL, 22),
(3, 0, '2020-09-07 22:11:45', 1, '2020-09-28 23:34:01', NULL, 3, NULL, 'test', NULL, '$2a$10$L4pBwBuCcMfUW28SK8okI.SsNRoNrIyjI9T4bEn58X5KFKN7x5gee', 'enabled', 'test', 2, NULL, 2),
(19, 1, '2020-09-29 00:19:01', 1, '2020-09-29 01:30:14', NULL, 2, NULL, 'testtest@gmail.com', NULL, '$2a$10$JNLGdqUT7aGTzt6bXXMNZOsAu6dz0fnprlBrdZT5qL4c8/qAjj6wG', 'enabled', 'testtest', 3, NULL, 3);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `vulnerability`
--

CREATE TABLE `vulnerability` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `impact_level` double DEFAULT NULL,
  `level` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `interface_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `vulnerability`
--

INSERT INTO `vulnerability` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `impact_level`, `level`, `name`, `interface_id`) VALUES
(2320, NULL, '2021-04-05 09:25:04', NULL, '2021-04-05 09:25:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2321, NULL, '2021-04-05 09:28:15', NULL, '2021-04-05 09:28:15', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2322, NULL, '2021-04-05 09:29:23', NULL, '2021-04-05 09:29:23', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2323, NULL, '2021-04-05 09:29:34', NULL, '2021-04-05 09:29:34', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2324, NULL, '2021-04-05 09:30:17', NULL, '2021-04-05 09:30:17', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2325, NULL, '2021-04-14 06:56:21', NULL, '2021-04-14 06:56:21', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2326, NULL, '2021-04-20 12:14:05', NULL, '2021-04-20 12:14:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `vulnerability_repository`
--

CREATE TABLE `vulnerability_repository` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` datetime NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `impact_level` double DEFAULT NULL,
  `level` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `interface_repository_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `vulnerability_repository`
--

INSERT INTO `vulnerability_repository` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `impact_level`, `level`, `name`, `interface_repository_id`) VALUES
(176, NULL, '2021-06-25 18:59:06', NULL, '2021-06-25 18:59:06', NULL, NULL, 'VULNERABILITY CODE1', NULL, NULL, 'VULNERABILITY NAME1', 107),
(177, NULL, '2021-06-25 18:59:06', NULL, '2021-06-25 18:59:06', NULL, NULL, 'VULNERABILITY CODE2', NULL, NULL, 'VULNERABILITY NAME2', 107),
(178, NULL, '2021-06-25 18:59:06', NULL, '2021-06-25 18:59:06', NULL, NULL, 'VULNERABILITY CODE5', NULL, NULL, 'VULNERABILITY NAME5', 108),
(184, NULL, '2021-07-06 15:20:43', NULL, '2021-07-06 15:20:43', NULL, NULL, 'VULNERABILITY CODE7', NULL, NULL, 'VULNERABILITY NAME7', 113),
(185, NULL, '2021-07-06 15:20:43', NULL, '2021-07-06 15:20:43', NULL, NULL, 'VULNERABILITY CODE8', NULL, NULL, 'VULNERABILITY NAME8', 113),
(186, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'VULNERABILITY CODE1', NULL, NULL, 'VULNERABILITY NAME1', 114),
(187, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'VULNERABILITY CODE2', NULL, NULL, 'VULNERABILITY NAME2', 114),
(188, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'VULNERABILITY CODE5', NULL, NULL, 'VULNERABILITY NAME5', 115),
(189, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'VULNERABILITY CODE7', NULL, NULL, 'VULNERABILITY NAME7', 116),
(190, NULL, '2021-07-08 16:11:23', NULL, '2021-07-08 16:11:23', NULL, NULL, 'VULNERABILITY CODE8', NULL, NULL, 'VULNERABILITY NAME8', 116),
(191, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'VULNERABILITY CODE1', NULL, NULL, 'VULNERABILITY NAME1', 117),
(192, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'VULNERABILITY CODE2', NULL, NULL, 'VULNERABILITY NAME2', 117),
(193, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'VULNERABILITY CODE5', NULL, NULL, 'VULNERABILITY NAME5', 118),
(194, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'VULNERABILITY CODE7', NULL, NULL, 'VULNERABILITY NAME7', 119),
(195, NULL, '2021-07-08 16:14:44', NULL, '2021-07-08 16:14:44', NULL, NULL, 'VULNERABILITY CODE8', NULL, NULL, 'VULNERABILITY NAME8', 119),
(196, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'VULNERABILITY CODE1', NULL, NULL, 'VULNERABILITY NAME1', 122),
(197, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'VULNERABILITY CODE2', NULL, NULL, 'VULNERABILITY NAME2', 122),
(198, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'VULNERABILITY CODE5', NULL, NULL, 'VULNERABILITY NAME5', 123),
(199, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'VULNERABILITY CODE7', NULL, NULL, 'VULNERABILITY NAME7', 124),
(200, NULL, '2021-07-09 18:10:19', NULL, '2021-07-09 18:10:19', NULL, NULL, 'VULNERABILITY CODE8', NULL, NULL, 'VULNERABILITY NAME8', 124),
(201, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'VULNERABILITY CODE1', NULL, NULL, 'VULNERABILITY NAME1', 125),
(202, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'VULNERABILITY CODE2', NULL, NULL, 'VULNERABILITY NAME2', 125),
(203, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'VULNERABILITY CODE5', NULL, NULL, 'VULNERABILITY NAME5', 126),
(204, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'VULNERABILITY CODE7', NULL, NULL, 'VULNERABILITY NAME7', 127),
(205, NULL, '2021-07-09 18:10:42', NULL, '2021-07-09 18:10:42', NULL, NULL, 'VULNERABILITY CODE8', NULL, NULL, 'VULNERABILITY NAME8', 127),
(206, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'VULNERABILITY CODE1', NULL, NULL, 'VULNERABILITY NAME1', 128),
(207, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'VULNERABILITY CODE2', NULL, NULL, 'VULNERABILITY NAME2', 128),
(208, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'VULNERABILITY CODE5', NULL, NULL, 'VULNERABILITY NAME5', 129),
(209, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'VULNERABILITY CODE7', NULL, NULL, 'VULNERABILITY NAME7', 130),
(210, NULL, '2021-07-09 18:11:01', NULL, '2021-07-09 18:11:01', NULL, NULL, 'VULNERABILITY CODE8', NULL, NULL, 'VULNERABILITY NAME8', 130);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `xls_import`
--

CREATE TABLE `xls_import` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `component_id` bigint(20) DEFAULT NULL,
  `first_line` bigint(20) DEFAULT NULL,
  `xls_iteration_column` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `xls_import`
--

INSERT INTO `xls_import` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `description`, `icon`, `name`, `component_id`, `first_line`, `xls_iteration_column`) VALUES
(1, NULL, '2021-06-17 07:37:54', NULL, '2021-06-22 13:23:55', NULL, 13, '', 'test1', '', 'test1', 4, 1, 'a');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `xls_import_line`
--

CREATE TABLE `xls_import_line` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `short_order` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `level` bigint(20) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `xls_import_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `xls_import_line`
--

INSERT INTO `xls_import_line` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `short_order`, `version`, `code`, `level`, `value`, `xls_import_id`) VALUES
(10, NULL, '2021-06-17 14:00:46', NULL, '2021-06-22 13:23:55', NULL, 4, 'assetRepository.code', NULL, 'importCol(\'a\')', 1),
(11, NULL, '2021-06-17 14:00:46', NULL, '2021-06-22 13:23:55', NULL, 4, 'assetRepository.name', NULL, 'importCol(\'b\')', 1),
(12, NULL, '2021-06-17 14:00:46', NULL, '2021-06-22 13:23:55', NULL, 4, 'interfaceRepository.code', NULL, 'importCol(\'c\')', 1),
(13, NULL, '2021-06-17 14:00:46', NULL, '2021-06-22 13:23:55', NULL, 4, 'interfaceRepository.name', NULL, 'importCol(\'d\')', 1),
(14, NULL, '2021-06-17 14:00:46', NULL, '2021-06-22 13:23:55', NULL, 4, 'vulnerabilityRepository.code', NULL, 'importCol(\'e\')', 1),
(15, NULL, '2021-06-17 14:00:46', NULL, '2021-06-22 13:23:55', NULL, 4, 'vulnerabilityRepository.name', NULL, 'importCol(\'f\')', 1),
(16, NULL, '2021-06-17 14:00:46', NULL, '2021-06-22 13:23:55', NULL, 4, 'threatRepository.code', NULL, 'importCol(\'g\')', 1),
(17, NULL, '2021-06-17 14:00:46', NULL, '2021-06-22 13:23:55', NULL, 4, 'threatRepository.name', NULL, 'importCol(\'h\')', 1);

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `asset`
--
ALTER TABLE `asset`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6i87rvbyn4uunx7kng9avbbpe` (`asset_entity_id`);

--
-- Ευρετήρια για πίνακα `assets_entity`
--
ALTER TABLE `assets_entity`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `asset_category`
--
ALTER TABLE `asset_category`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `asset_repository`
--
ALTER TABLE `asset_repository`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `chart`
--
ALTER TABLE `chart`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `chart_field`
--
ALTER TABLE `chart_field`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs2ww9rrljne6i1fx7jo8d8teu` (`chart_id`);

--
-- Ευρετήρια για πίνακα `component`
--
ALTER TABLE `component`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `component_persist_entity`
--
ALTER TABLE `component_persist_entity`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKof2hnkhcbsded3jkn8mnuti5o` (`persist_entity_id`),
  ADD KEY `FK8mqfeyda4tgeygqehipiks9ej` (`parent_id`),
  ADD KEY `FKth6q56g27ipff2jgcqkrc35ki` (`component_id`);

--
-- Ευρετήρια για πίνακα `component_persist_entity_field`
--
ALTER TABLE `component_persist_entity_field`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2bwjwcucjaycp75elbi314u1b` (`join_persist_entity_id`),
  ADD KEY `FK65r0rx9qd275e1dsafy1unyyu` (`persist_entity_field_id`),
  ADD KEY `FKkcmuy4fdfgk6xdj9pfnom9lxm` (`component_persist_entity_id`);

--
-- Ευρετήρια για πίνακα `component_persist_entity_field_assignment`
--
ALTER TABLE `component_persist_entity_field_assignment`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `dashboard`
--
ALTER TABLE `dashboard`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `dashboard_area`
--
ALTER TABLE `dashboard_area`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkvg7xh53h2uirmwu1d54ox4jc` (`dashboard_id`);

--
-- Ευρετήρια για πίνακα `dashboard_item`
--
ALTER TABLE `dashboard_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnqalempaowhwqu5tph5xmle83` (`dashboard_area_id`);

--
-- Ευρετήρια για πίνακα `entity`
--
ALTER TABLE `entity`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `form`
--
ALTER TABLE `form`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa6o5wk90ymn85abxrjcsexi9l` (`component_id`);

--
-- Ευρετήρια για πίνακα `form_area`
--
ALTER TABLE `form_area`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1ed822tgaxwve23pheu3frlhg` (`form_tab_id`),
  ADD KEY `FKciirei59u6k574y6jj406e65o` (`form_popup_id`);

--
-- Ευρετήρια για πίνακα `form_control`
--
ALTER TABLE `form_control`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1pvahjseqdlfufao3lds9g1fx` (`form_control_field_id`),
  ADD KEY `FKk7mrqc4ummp5tjexmslnacgim` (`form_control_table_id`),
  ADD KEY `FK52xv0r8uha79pnt3xjyir7xw5` (`form_area_id`),
  ADD KEY `FKv2uvnk4yg8u1p13mnb2v8udi` (`form_control_button_id`);

--
-- Ευρετήρια για πίνακα `form_control_button`
--
ALTER TABLE `form_control_button`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `form_control_field`
--
ALTER TABLE `form_control_field`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3wpmk0exbupb19y26svq3wo3a` (`component_persist_entity_id`),
  ADD KEY `FK9dytr71ghw1vv3ybfb7shmqbe` (`component_persist_entity_field_id`);

--
-- Ευρετήρια για πίνακα `form_control_table`
--
ALTER TABLE `form_control_table`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK450i25eysp0w7orp97hng59cn` (`component_persist_entity_id`);

--
-- Ευρετήρια για πίνακα `form_control_table_button_control`
--
ALTER TABLE `form_control_table_button_control`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKg3k65s82m69i8hod8c2syrom8` (`form_control_table_button_id`),
  ADD KEY `FKa7m1uie0tkgkfp0qmiu5bdgiy` (`form_control_table_id`);

--
-- Ευρετήρια για πίνακα `form_control_table_control`
--
ALTER TABLE `form_control_table_control`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1iidq6chl9xqva2662repg51k` (`form_component_field_id`),
  ADD KEY `FK3auiolwfsrrjj9gnkmophcq16` (`form_control_table_id`),
  ADD KEY `FKf4jewja02s8h2kaqa3lr8rvby` (`form_control_field_id`);

--
-- Ευρετήρια για πίνακα `form_popup`
--
ALTER TABLE `form_popup`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmxjj0jirfvagdxrn7o0xspld9` (`form_id`);

--
-- Ευρετήρια για πίνακα `form_script`
--
ALTER TABLE `form_script`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKasgegf360hdiy1vk9rwjbtks1` (`form_id`);

--
-- Ευρετήρια για πίνακα `form_tab`
--
ALTER TABLE `form_tab`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbo89lk1jb1kh94w0f5fi2omvv` (`form_id`);

--
-- Ευρετήρια για πίνακα `info_card`
--
ALTER TABLE `info_card`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `interface`
--
ALTER TABLE `interface`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcu2q3b0ngxjxmr4eeba8uij33` (`asset_id`);

--
-- Ευρετήρια για πίνακα `interface_repository`
--
ALTER TABLE `interface_repository`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `list`
--
ALTER TABLE `list`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK76wmmk95ph9xtm6yne6mtacpd` (`component_id`);

--
-- Ευρετήρια για πίνακα `list_action_button`
--
ALTER TABLE `list_action_button`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKo6hy6ajqgc42hgmrjr1g1ew3y` (`list_id`);

--
-- Ευρετήρια για πίνακα `list_component`
--
ALTER TABLE `list_component`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `list_component_field`
--
ALTER TABLE `list_component_field`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKe6xebo5jgdkcuk5j1j2n2tkw9` (`component_persist_entity_id`),
  ADD KEY `FKesymei4s304r3v482iyuks1qk` (`component_persist_entity_field_id`),
  ADD KEY `FK9dl90kj2tk18qajrcv5agf5ju` (`top_group_list_component_id`),
  ADD KEY `FKt7u6eoqtabl8vggwh0o4aojfw` (`order_by_list_component_id`),
  ADD KEY `FKat508p9f4eb934hy91pqgfyf6` (`side_group_list_component_id`),
  ADD KEY `FKooog11twt1b23sh7vnkko4fnc` (`filter_list_component_id`),
  ADD KEY `FK204ii36ufyn029bltegw59a9j` (`column_list_component_id`),
  ADD KEY `FKhmu9ys44tvyuddbw8mb163xfv` (`action_list_component_id`);

--
-- Ευρετήρια για πίνακα `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `menu_field`
--
ALTER TABLE `menu_field`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdrtnwhrc6ti7555c8nu8aic1h` (`menu_field_id`),
  ADD KEY `FKes87wk6vpalg98xtc0cu4hc34` (`menu_id`);

--
-- Ευρετήρια για πίνακα `persist_entity`
--
ALTER TABLE `persist_entity`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `persist_entity_field`
--
ALTER TABLE `persist_entity_field`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKo7ka4ubncg6iwp39p7dhq6uo3` (`persist_entity_id`);

--
-- Ευρετήρια για πίνακα `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `report_parameter`
--
ALTER TABLE `report_parameter`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKsodctshdfqa3kap0rxj6osqe1` (`report_id`);

--
-- Ευρετήρια για πίνακα `search`
--
ALTER TABLE `search`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `threat`
--
ALTER TABLE `threat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmqsuemy459gj7mervch6oc2jh` (`vulnerability_id`);

--
-- Ευρετήρια για πίνακα `threat_repository`
--
ALTER TABLE `threat_repository`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9xbiyup87vgi1chjxh3snc9b3` (`menu_id`),
  ADD KEY `FKsv3cmohxnau2ncgaj0k6wy51s` (`header_menu_id`),
  ADD KEY `FKpg1ggg4lyv3wgwv55ane8h39o` (`sidebar_menu_id`);

--
-- Ευρετήρια για πίνακα `vulnerability`
--
ALTER TABLE `vulnerability`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5xawj4wrmuwgypxa3b8oerpbe` (`interface_id`);

--
-- Ευρετήρια για πίνακα `vulnerability_repository`
--
ALTER TABLE `vulnerability_repository`
  ADD PRIMARY KEY (`id`);

--
-- Ευρετήρια για πίνακα `xls_import`
--
ALTER TABLE `xls_import`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKetipuvisr2ga3n6a6lkms3cq3` (`component_id`);

--
-- Ευρετήρια για πίνακα `xls_import_line`
--
ALTER TABLE `xls_import_line`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtg6x1h4ntmw7ya6mic49k0gg6` (`xls_import_id`);

--
-- AUTO_INCREMENT για άχρηστους πίνακες
--

--
-- AUTO_INCREMENT για πίνακα `asset`
--
ALTER TABLE `asset`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT για πίνακα `assets_entity`
--
ALTER TABLE `assets_entity`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT για πίνακα `asset_category`
--
ALTER TABLE `asset_category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT για πίνακα `asset_repository`
--
ALTER TABLE `asset_repository`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT για πίνακα `chart`
--
ALTER TABLE `chart`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT για πίνακα `chart_field`
--
ALTER TABLE `chart_field`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT για πίνακα `component`
--
ALTER TABLE `component`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT για πίνακα `component_persist_entity`
--
ALTER TABLE `component_persist_entity`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT για πίνακα `component_persist_entity_field`
--
ALTER TABLE `component_persist_entity_field`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=582;

--
-- AUTO_INCREMENT για πίνακα `component_persist_entity_field_assignment`
--
ALTER TABLE `component_persist_entity_field_assignment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17399;

--
-- AUTO_INCREMENT για πίνακα `dashboard`
--
ALTER TABLE `dashboard`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT για πίνακα `dashboard_area`
--
ALTER TABLE `dashboard_area`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT για πίνακα `dashboard_item`
--
ALTER TABLE `dashboard_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT για πίνακα `entity`
--
ALTER TABLE `entity`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT για πίνακα `form`
--
ALTER TABLE `form`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT για πίνακα `form_area`
--
ALTER TABLE `form_area`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT για πίνακα `form_control`
--
ALTER TABLE `form_control`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- AUTO_INCREMENT για πίνακα `form_control_button`
--
ALTER TABLE `form_control_button`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT για πίνακα `form_control_field`
--
ALTER TABLE `form_control_field`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=139;

--
-- AUTO_INCREMENT για πίνακα `form_control_table`
--
ALTER TABLE `form_control_table`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT για πίνακα `form_control_table_button_control`
--
ALTER TABLE `form_control_table_button_control`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT για πίνακα `form_control_table_control`
--
ALTER TABLE `form_control_table_control`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- AUTO_INCREMENT για πίνακα `form_popup`
--
ALTER TABLE `form_popup`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT για πίνακα `form_script`
--
ALTER TABLE `form_script`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT για πίνακα `form_tab`
--
ALTER TABLE `form_tab`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT για πίνακα `info_card`
--
ALTER TABLE `info_card`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT για πίνακα `interface`
--
ALTER TABLE `interface`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- AUTO_INCREMENT για πίνακα `interface_repository`
--
ALTER TABLE `interface_repository`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=131;

--
-- AUTO_INCREMENT για πίνακα `list`
--
ALTER TABLE `list`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT για πίνακα `list_action_button`
--
ALTER TABLE `list_action_button`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT για πίνακα `list_component`
--
ALTER TABLE `list_component`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT για πίνακα `list_component_field`
--
ALTER TABLE `list_component_field`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=128;

--
-- AUTO_INCREMENT για πίνακα `menu`
--
ALTER TABLE `menu`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT για πίνακα `menu_field`
--
ALTER TABLE `menu_field`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=144;

--
-- AUTO_INCREMENT για πίνακα `persist_entity`
--
ALTER TABLE `persist_entity`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT για πίνακα `persist_entity_field`
--
ALTER TABLE `persist_entity_field`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=150;

--
-- AUTO_INCREMENT για πίνακα `report`
--
ALTER TABLE `report`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT για πίνακα `report_parameter`
--
ALTER TABLE `report_parameter`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT για πίνακα `search`
--
ALTER TABLE `search`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT για πίνακα `threat`
--
ALTER TABLE `threat`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13830;

--
-- AUTO_INCREMENT για πίνακα `threat_repository`
--
ALTER TABLE `threat_repository`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=388;

--
-- AUTO_INCREMENT για πίνακα `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT για πίνακα `vulnerability`
--
ALTER TABLE `vulnerability`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2327;

--
-- AUTO_INCREMENT για πίνακα `vulnerability_repository`
--
ALTER TABLE `vulnerability_repository`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=211;

--
-- AUTO_INCREMENT για πίνακα `xls_import`
--
ALTER TABLE `xls_import`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT για πίνακα `xls_import_line`
--
ALTER TABLE `xls_import_line`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Περιορισμοί για άχρηστους πίνακες
--

--
-- Περιορισμοί για πίνακα `asset`
--
ALTER TABLE `asset`
  ADD CONSTRAINT `FK6i87rvbyn4uunx7kng9avbbpe` FOREIGN KEY (`asset_entity_id`) REFERENCES `assets_entity` (`id`);

--
-- Περιορισμοί για πίνακα `chart_field`
--
ALTER TABLE `chart_field`
  ADD CONSTRAINT `FKs2ww9rrljne6i1fx7jo8d8teu` FOREIGN KEY (`chart_id`) REFERENCES `chart` (`id`);

--
-- Περιορισμοί για πίνακα `component_persist_entity`
--
ALTER TABLE `component_persist_entity`
  ADD CONSTRAINT `FK8mqfeyda4tgeygqehipiks9ej` FOREIGN KEY (`parent_id`) REFERENCES `component_persist_entity` (`id`),
  ADD CONSTRAINT `FKof2hnkhcbsded3jkn8mnuti5o` FOREIGN KEY (`persist_entity_id`) REFERENCES `persist_entity` (`id`),
  ADD CONSTRAINT `FKth6q56g27ipff2jgcqkrc35ki` FOREIGN KEY (`component_id`) REFERENCES `component` (`id`);

--
-- Περιορισμοί για πίνακα `component_persist_entity_field`
--
ALTER TABLE `component_persist_entity_field`
  ADD CONSTRAINT `FK2bwjwcucjaycp75elbi314u1b` FOREIGN KEY (`join_persist_entity_id`) REFERENCES `component_persist_entity` (`id`),
  ADD CONSTRAINT `FK65r0rx9qd275e1dsafy1unyyu` FOREIGN KEY (`persist_entity_field_id`) REFERENCES `persist_entity_field` (`id`),
  ADD CONSTRAINT `FKkcmuy4fdfgk6xdj9pfnom9lxm` FOREIGN KEY (`component_persist_entity_id`) REFERENCES `component_persist_entity` (`id`);

--
-- Περιορισμοί για πίνακα `dashboard_area`
--
ALTER TABLE `dashboard_area`
  ADD CONSTRAINT `FKkvg7xh53h2uirmwu1d54ox4jc` FOREIGN KEY (`dashboard_id`) REFERENCES `dashboard` (`id`);

--
-- Περιορισμοί για πίνακα `dashboard_item`
--
ALTER TABLE `dashboard_item`
  ADD CONSTRAINT `FKnqalempaowhwqu5tph5xmle83` FOREIGN KEY (`dashboard_area_id`) REFERENCES `dashboard_area` (`id`);

--
-- Περιορισμοί για πίνακα `form`
--
ALTER TABLE `form`
  ADD CONSTRAINT `FKa6o5wk90ymn85abxrjcsexi9l` FOREIGN KEY (`component_id`) REFERENCES `component` (`id`);

--
-- Περιορισμοί για πίνακα `form_area`
--
ALTER TABLE `form_area`
  ADD CONSTRAINT `FK1ed822tgaxwve23pheu3frlhg` FOREIGN KEY (`form_tab_id`) REFERENCES `form_tab` (`id`),
  ADD CONSTRAINT `FKciirei59u6k574y6jj406e65o` FOREIGN KEY (`form_popup_id`) REFERENCES `form_popup` (`id`);

--
-- Περιορισμοί για πίνακα `form_control`
--
ALTER TABLE `form_control`
  ADD CONSTRAINT `FK1pvahjseqdlfufao3lds9g1fx` FOREIGN KEY (`form_control_field_id`) REFERENCES `form_control_field` (`id`),
  ADD CONSTRAINT `FK52xv0r8uha79pnt3xjyir7xw5` FOREIGN KEY (`form_area_id`) REFERENCES `form_area` (`id`),
  ADD CONSTRAINT `FKk7mrqc4ummp5tjexmslnacgim` FOREIGN KEY (`form_control_table_id`) REFERENCES `form_control_table` (`id`),
  ADD CONSTRAINT `FKv2uvnk4yg8u1p13mnb2v8udi` FOREIGN KEY (`form_control_button_id`) REFERENCES `form_control_button` (`id`);

--
-- Περιορισμοί για πίνακα `form_control_field`
--
ALTER TABLE `form_control_field`
  ADD CONSTRAINT `FK3wpmk0exbupb19y26svq3wo3a` FOREIGN KEY (`component_persist_entity_id`) REFERENCES `component_persist_entity` (`id`),
  ADD CONSTRAINT `FK9dytr71ghw1vv3ybfb7shmqbe` FOREIGN KEY (`component_persist_entity_field_id`) REFERENCES `component_persist_entity_field` (`id`);

--
-- Περιορισμοί για πίνακα `form_control_table`
--
ALTER TABLE `form_control_table`
  ADD CONSTRAINT `FK450i25eysp0w7orp97hng59cn` FOREIGN KEY (`component_persist_entity_id`) REFERENCES `component_persist_entity` (`id`);

--
-- Περιορισμοί για πίνακα `form_control_table_button_control`
--
ALTER TABLE `form_control_table_button_control`
  ADD CONSTRAINT `FKa7m1uie0tkgkfp0qmiu5bdgiy` FOREIGN KEY (`form_control_table_id`) REFERENCES `form_control_table` (`id`),
  ADD CONSTRAINT `FKg3k65s82m69i8hod8c2syrom8` FOREIGN KEY (`form_control_table_button_id`) REFERENCES `form_control_button` (`id`);

--
-- Περιορισμοί για πίνακα `form_control_table_control`
--
ALTER TABLE `form_control_table_control`
  ADD CONSTRAINT `FK1iidq6chl9xqva2662repg51k` FOREIGN KEY (`form_component_field_id`) REFERENCES `form_control_field` (`id`),
  ADD CONSTRAINT `FK3auiolwfsrrjj9gnkmophcq16` FOREIGN KEY (`form_control_table_id`) REFERENCES `form_control_table` (`id`),
  ADD CONSTRAINT `FKf4jewja02s8h2kaqa3lr8rvby` FOREIGN KEY (`form_control_field_id`) REFERENCES `form_control_field` (`id`);

--
-- Περιορισμοί για πίνακα `form_popup`
--
ALTER TABLE `form_popup`
  ADD CONSTRAINT `FKmxjj0jirfvagdxrn7o0xspld9` FOREIGN KEY (`form_id`) REFERENCES `form` (`id`);

--
-- Περιορισμοί για πίνακα `form_script`
--
ALTER TABLE `form_script`
  ADD CONSTRAINT `FKasgegf360hdiy1vk9rwjbtks1` FOREIGN KEY (`form_id`) REFERENCES `form` (`id`);

--
-- Περιορισμοί για πίνακα `form_tab`
--
ALTER TABLE `form_tab`
  ADD CONSTRAINT `FKbo89lk1jb1kh94w0f5fi2omvv` FOREIGN KEY (`form_id`) REFERENCES `form` (`id`);

--
-- Περιορισμοί για πίνακα `interface`
--
ALTER TABLE `interface`
  ADD CONSTRAINT `FKcu2q3b0ngxjxmr4eeba8uij33` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`);

--
-- Περιορισμοί για πίνακα `list`
--
ALTER TABLE `list`
  ADD CONSTRAINT `FK76wmmk95ph9xtm6yne6mtacpd` FOREIGN KEY (`component_id`) REFERENCES `component` (`id`);

--
-- Περιορισμοί για πίνακα `list_action_button`
--
ALTER TABLE `list_action_button`
  ADD CONSTRAINT `FKo6hy6ajqgc42hgmrjr1g1ew3y` FOREIGN KEY (`list_id`) REFERENCES `list` (`id`);

--
-- Περιορισμοί για πίνακα `list_component_field`
--
ALTER TABLE `list_component_field`
  ADD CONSTRAINT `FK204ii36ufyn029bltegw59a9j` FOREIGN KEY (`column_list_component_id`) REFERENCES `list` (`id`),
  ADD CONSTRAINT `FK9dl90kj2tk18qajrcv5agf5ju` FOREIGN KEY (`top_group_list_component_id`) REFERENCES `list` (`id`),
  ADD CONSTRAINT `FKat508p9f4eb934hy91pqgfyf6` FOREIGN KEY (`side_group_list_component_id`) REFERENCES `list` (`id`),
  ADD CONSTRAINT `FKe6xebo5jgdkcuk5j1j2n2tkw9` FOREIGN KEY (`component_persist_entity_id`) REFERENCES `component_persist_entity` (`id`),
  ADD CONSTRAINT `FKesymei4s304r3v482iyuks1qk` FOREIGN KEY (`component_persist_entity_field_id`) REFERENCES `component_persist_entity_field` (`id`),
  ADD CONSTRAINT `FKhmu9ys44tvyuddbw8mb163xfv` FOREIGN KEY (`action_list_component_id`) REFERENCES `list` (`id`),
  ADD CONSTRAINT `FKooog11twt1b23sh7vnkko4fnc` FOREIGN KEY (`filter_list_component_id`) REFERENCES `list` (`id`),
  ADD CONSTRAINT `FKt7u6eoqtabl8vggwh0o4aojfw` FOREIGN KEY (`order_by_list_component_id`) REFERENCES `list` (`id`);

--
-- Περιορισμοί για πίνακα `menu_field`
--
ALTER TABLE `menu_field`
  ADD CONSTRAINT `FKdrtnwhrc6ti7555c8nu8aic1h` FOREIGN KEY (`menu_field_id`) REFERENCES `menu_field` (`id`),
  ADD CONSTRAINT `FKes87wk6vpalg98xtc0cu4hc34` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`);

--
-- Περιορισμοί για πίνακα `persist_entity_field`
--
ALTER TABLE `persist_entity_field`
  ADD CONSTRAINT `FKo7ka4ubncg6iwp39p7dhq6uo3` FOREIGN KEY (`persist_entity_id`) REFERENCES `persist_entity` (`id`);

--
-- Περιορισμοί για πίνακα `report_parameter`
--
ALTER TABLE `report_parameter`
  ADD CONSTRAINT `FKsodctshdfqa3kap0rxj6osqe1` FOREIGN KEY (`report_id`) REFERENCES `report` (`id`);

--
-- Περιορισμοί για πίνακα `threat`
--
ALTER TABLE `threat`
  ADD CONSTRAINT `FKmqsuemy459gj7mervch6oc2jh` FOREIGN KEY (`vulnerability_id`) REFERENCES `vulnerability` (`id`);

--
-- Περιορισμοί για πίνακα `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK9xbiyup87vgi1chjxh3snc9b3` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`),
  ADD CONSTRAINT `FKpg1ggg4lyv3wgwv55ane8h39o` FOREIGN KEY (`sidebar_menu_id`) REFERENCES `menu` (`id`),
  ADD CONSTRAINT `FKsv3cmohxnau2ncgaj0k6wy51s` FOREIGN KEY (`header_menu_id`) REFERENCES `menu` (`id`);

--
-- Περιορισμοί για πίνακα `vulnerability`
--
ALTER TABLE `vulnerability`
  ADD CONSTRAINT `FK5xawj4wrmuwgypxa3b8oerpbe` FOREIGN KEY (`interface_id`) REFERENCES `interface` (`id`);

--
-- Περιορισμοί για πίνακα `xls_import`
--
ALTER TABLE `xls_import`
  ADD CONSTRAINT `FKetipuvisr2ga3n6a6lkms3cq3` FOREIGN KEY (`component_id`) REFERENCES `component` (`id`);

--
-- Περιορισμοί για πίνακα `xls_import_line`
--
ALTER TABLE `xls_import_line`
  ADD CONSTRAINT `FKtg6x1h4ntmw7ya6mic49k0gg6` FOREIGN KEY (`xls_import_id`) REFERENCES `xls_import` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

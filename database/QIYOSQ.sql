-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 20, 2018 at 06:49 
-- Server version: 10.1.10-MariaDB
-- PHP Version: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `QIYOSQDUA`
--

-- --------------------------------------------------------

--
-- Table structure for table `sys_audit_trail`
--

CREATE TABLE `sys_audit_trail` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `disabled` bit(1) NOT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` varchar(5) DEFAULT NULL,
  `action_type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sys_authorization`
--

CREATE TABLE `sys_authorization` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `disabled` bit(1) NOT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` varchar(5) DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `is_insert` bit(1) DEFAULT NULL,
  `is_read` bit(1) DEFAULT NULL,
  `is_update` bit(1) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `sys_menu_id` bigint(20) DEFAULT NULL,
  `sys_roles_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sys_authorization`
--

INSERT INTO `sys_authorization` (`id`, `created_by`, `created_time`, `disabled`, `modified_by`, `modified_time`, `version`, `is_delete`, `is_insert`, `is_read`, `is_update`, `parent_id`, `sys_menu_id`, `sys_roles_id`) VALUES
(69, NULL, '2016-12-11 15:01:14', b'0', NULL, '2017-12-28 05:58:48', '1.0', b'0', b'1', b'1', b'0', NULL, 1, 1),
(70, NULL, '2016-12-11 15:01:14', b'1', NULL, '2017-12-17 16:29:18', '1.0', b'1', b'0', b'1', b'1', 69, 2, 1),
(71, NULL, '2016-12-11 15:01:14', b'0', NULL, '2017-12-24 15:43:37', '1.0', b'0', b'0', b'1', b'0', 69, 3, 1),
(72, NULL, '2016-12-11 15:01:14', b'0', NULL, '2017-12-17 16:29:35', '1.0', b'0', b'1', b'1', b'1', NULL, 4, 1),
(73, NULL, '2016-12-11 15:01:14', b'0', NULL, '2017-12-17 12:08:30', '1.0', b'0', b'0', b'1', b'0', 72, 5, 1),
(74, NULL, '2016-12-11 15:01:14', b'0', NULL, '2016-12-11 15:01:14', '1.0', b'0', b'1', b'1', b'1', 73, 6, 1),
(75, NULL, '2016-12-11 15:01:14', b'0', NULL, '2016-12-11 15:01:14', '1.0', b'0', b'1', b'1', b'1', 73, 7, 1),
(76, NULL, '2016-12-11 15:01:14', b'1', NULL, '2016-12-11 15:01:14', '1.0', b'1', b'1', b'1', b'1', 72, 8, 1),
(77, NULL, '2016-12-11 15:01:15', b'1', NULL, '2016-12-11 15:01:15', '1.0', b'0', b'1', b'1', b'1', 72, 9, 1),
(78, NULL, '2016-12-11 15:01:15', b'1', NULL, '2016-12-11 15:01:15', '1.0', b'0', b'1', b'1', b'1', 72, 10, 1),
(90, NULL, '2017-02-11 12:21:30', b'0', NULL, '2017-02-11 12:21:30', '1.0', b'0', b'0', b'1', b'0', NULL, 1, 3),
(132, NULL, '2017-02-12 10:16:01', b'1', NULL, '2017-02-12 10:16:01', '1.0', b'1', b'1', b'1', b'1', NULL, 1, 2),
(139, NULL, '2017-02-12 15:09:17', b'1', NULL, '2017-02-12 15:09:17', '1.0', b'1', b'1', b'1', b'1', NULL, 1, 2),
(140, NULL, '2017-02-13 15:05:11', b'1', NULL, '2017-02-13 15:05:11', '1.0', b'1', b'1', b'1', b'1', 132, 1, 2),
(142, NULL, '2017-02-15 02:56:44', b'1', NULL, '2017-02-15 02:56:44', '1.0', b'1', b'1', b'1', b'1', NULL, 1, 2),
(143, NULL, '2017-02-15 02:59:49', b'0', NULL, '2017-02-15 02:59:49', '1.0', b'1', b'1', b'1', b'1', NULL, 1, 2),
(155, NULL, '2017-02-15 04:24:54', b'0', NULL, '2017-02-15 04:24:54', '1.0', b'0', b'0', b'1', b'0', 132, 2, 2),
(157, NULL, '2017-02-15 04:55:37', b'1', NULL, '2017-02-15 04:55:37', '1.0', b'1', b'1', b'1', b'1', NULL, 1, 2),
(163, NULL, '2017-02-15 11:36:54', b'1', NULL, '2017-02-15 11:36:54', '1.0', b'1', b'1', b'1', b'1', NULL, 1, 2),
(165, NULL, '2017-02-15 11:55:11', b'1', NULL, '2017-02-15 11:55:11', '1.0', b'1', b'1', b'1', b'1', NULL, 2, 2),
(166, NULL, '2017-02-15 11:56:32', b'1', NULL, '2017-02-15 11:56:32', '1.0', b'1', b'1', b'1', b'1', NULL, 2, 2),
(176, NULL, '2017-02-15 12:49:04', b'1', NULL, '2017-02-15 12:49:04', '1.0', b'1', b'1', b'1', b'1', 132, 1, 2),
(177, NULL, '2017-02-15 12:49:45', b'1', NULL, '2017-02-15 12:49:45', '1.0', b'1', b'1', b'1', b'1', 132, 1, 2),
(179, NULL, '2017-02-15 12:56:30', b'1', NULL, '2017-02-15 12:56:30', '1.0', b'1', b'1', b'1', b'1', 132, 1, 2),
(181, NULL, '2017-02-15 13:01:36', b'1', NULL, '2017-02-15 13:01:36', '1.0', b'1', b'1', b'1', b'1', 132, 1, 2),
(183, NULL, '2017-02-15 15:07:43', b'1', NULL, '2017-02-15 15:07:43', '1.0', b'1', b'1', b'1', b'1', NULL, 2, 2),
(189, NULL, '2017-02-15 15:19:18', b'1', NULL, '2017-02-15 15:19:18', '1.0', b'1', b'1', b'1', b'1', NULL, 2, 2),
(197, NULL, '2017-02-16 15:40:37', b'1', NULL, '2017-02-16 15:40:37', '1.0', b'1', b'1', b'1', b'1', NULL, 1, 2),
(198, NULL, '2017-02-16 15:41:28', b'1', NULL, '2017-02-16 15:41:28', '1.0', b'1', b'1', b'1', b'1', 181, 1, 2),
(203, NULL, '2017-02-18 07:37:32', b'1', NULL, '2017-02-18 07:37:32', '1.0', b'1', b'1', b'1', b'1', NULL, 1, 2),
(204, NULL, '2017-02-18 07:38:04', b'1', NULL, '2017-02-18 07:38:04', '1.0', b'1', b'1', b'1', b'1', 189, 1, 2),
(209, NULL, '2017-02-18 10:10:14', b'1', NULL, '2017-02-18 10:10:14', '1.0', b'1', b'1', b'1', b'1', NULL, 1, 2),
(237, NULL, '2017-02-21 15:44:45', b'1', NULL, '2017-02-21 15:44:45', '1.0', b'1', b'1', b'1', b'1', NULL, 1, 2),
(238, NULL, '2017-02-21 15:46:25', b'1', NULL, '2017-02-21 15:46:25', '1.0', b'1', b'1', b'1', b'1', NULL, 1, 2),
(239, NULL, '2017-02-21 15:49:03', b'1', NULL, '2017-02-21 15:49:03', '1.0', b'1', b'1', b'1', b'1', NULL, 1, 2),
(240, NULL, '2017-04-19 08:52:41', b'1', NULL, '2017-04-19 08:52:41', '1.0', b'1', b'1', b'1', b'1', 73, 13, 1),
(243, NULL, '2017-04-19 08:56:24', b'1', NULL, '2017-04-19 08:56:24', '1.0', b'1', b'1', b'1', b'1', 72, 11, 1),
(244, NULL, '2017-04-19 08:57:01', b'1', NULL, '2017-04-19 08:57:01', '1.0', b'1', b'1', b'1', b'1', 243, 12, 1),
(246, NULL, '2017-04-19 11:32:40', b'1', NULL, '2017-04-19 11:32:40', '1.0', b'1', b'1', b'1', b'1', NULL, 15, 1),
(254, NULL, '2017-12-27 14:02:19', b'0', NULL, '2017-12-27 14:02:19', '1.0', b'1', b'1', b'1', b'1', 72, 1, 1),
(255, NULL, '2017-12-27 14:07:05', b'0', NULL, '2017-12-27 14:07:05', '1.0', b'1', b'1', b'1', b'1', 71, 1, 1),
(256, NULL, '2017-12-27 14:21:28', b'1', NULL, '2017-12-27 14:21:28', '1.0', b'1', b'1', b'1', b'1', 71, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sys_menu`
--

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `disabled` bit(1) NOT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` varchar(5) DEFAULT NULL,
  `menus_name` varchar(255) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sys_menu`
--

INSERT INTO `sys_menu` (`id`, `created_by`, `created_time`, `disabled`, `modified_by`, `modified_time`, `version`, `menus_name`, `url`) VALUES
(1, 'ari', '2017-02-07 10:00:00', b'0', 'ari', '2017-02-07 10:00:00', '1.0', 'Dashboard', NULL),
(2, 'ari', '2017-02-07 10:00:00', b'0', 'ari', '2017-02-07 10:00:00', '1.0', 'Dashboard V1', '/Dashboard/V1'),
(3, 'ari', '2017-02-07 10:00:00', b'0', 'ari', '2017-02-07 10:00:00', '1.0', 'Dashboard V2', '/Dashboard/V2'),
(4, 'ari', '2017-02-07 10:00:00', b'0', 'ari', '2017-02-07 10:00:00', '1.0', 'Utility', NULL),
(5, 'ari', '2017-02-07 10:00:00', b'0', 'ari', '2017-02-07 10:00:00', '1.0', 'Setting User', NULL),
(6, 'ari', '2017-02-07 10:00:00', b'0', 'ari', '2017-11-26 05:35:26', '1.0', 'User', '../../../admin/v1/view/main#/user/'),
(7, 'ari', '2017-02-07 10:00:00', b'0', 'ari', '2017-12-09 20:43:51', '1.0', 'Authorization', '../../../admin/v1/view/main#/authorization/'),
(8, 'ari', '2017-02-07 10:00:00', b'0', 'ari', '2017-02-07 10:00:00', '1.0', 'Profile Setting', 'admin/v1/setting/calendar'),
(9, 'ari', '2017-02-07 10:00:00', b'0', 'ari', '2017-02-07 10:00:00', '1.0', 'Report Setting', 'admin/v1/reportsetting '),
(10, 'ari', '2017-02-07 10:00:00', b'0', 'ari', '2017-02-07 10:00:00', '1.0', 'Calendar Setting', 'admin/v1/setting/calendar'),
(11, 'NULL', '2017-04-18 10:00:00', b'0', 'NULL', '2017-04-18 10:00:00', '1.0', 'Application Setting', NULL),
(12, 'NULL', '2017-04-18 10:00:00', b'0', 'NULL', '2017-11-26 05:36:07', '1.0', 'Add Menu', '../../../admin/v1/view/menu'),
(13, 'NULL', '2017-04-18 10:00:00', b'0', 'NULL', '2018-01-20 05:45:58', '1.0', 'User Group', '../../../admin/v1/view/main#/usergroup/'),
(14, 'NULL', '2017-04-18 10:00:00', b'0', 'NULL', '2017-11-26 05:36:35', '1.0', 'Basic Web', '../../../admin/v1/view/basicweb'),
(15, NULL, '2017-11-03 22:07:08', b'1', NULL, '2017-11-26 05:36:53', '1.0', 'basic SPA', '../../../admin/v1/view/main#/sample-spa/');

-- --------------------------------------------------------

--
-- Table structure for table `sys_roles`
--

CREATE TABLE `sys_roles` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `disabled` bit(1) NOT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` varchar(5) DEFAULT NULL,
  `role_name` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sys_roles`
--

INSERT INTO `sys_roles` (`id`, `created_by`, `created_time`, `disabled`, `modified_by`, `modified_time`, `version`, `role_name`) VALUES
(1, NULL, '2016-11-19 18:38:52', b'0', NULL, '2016-11-19 18:38:52', '1.0', 'admin'),
(2, NULL, '2016-11-19 18:38:52', b'0', NULL, '2016-11-19 18:38:52', '1.0', 'approval'),
(3, NULL, '2016-11-19 18:38:52', b'0', NULL, '2016-11-19 18:38:52', '1.0', 'user'),
(4, NULL, '2016-11-19 18:38:52', b'0', NULL, '2016-11-19 18:38:52', '1.0', 'public');

-- --------------------------------------------------------

--
-- Table structure for table `sys_user`
--

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `disabled` bit(1) NOT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` varchar(5) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `no_hp` varchar(13) NOT NULL,
  `password` varchar(150) NOT NULL,
  `username` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sys_user`
--

INSERT INTO `sys_user` (`id`, `created_by`, `created_time`, `disabled`, `modified_by`, `modified_time`, `version`, `email`, `is_active`, `name`, `no_hp`, `password`, `username`) VALUES
(1, NULL, '2017-04-16 01:14:39', b'0', NULL, '2017-12-09 11:15:58', NULL, 'prasetiyooo@gmail.com', b'1', 'notblank', '085645480401', 'dummay', 'ari'),
(3, NULL, '2017-12-09 13:26:42', b'0', NULL, '2017-12-15 16:47:06', NULL, 'aria@adasd.com', b'1', 'aria p', '12345667889', '123456789', 'aria'),
(4, NULL, '2017-12-09 23:54:28', b'0', NULL, '2017-12-09 23:54:28', NULL, 'hh@ghhh.vom', b'1', 'hadeh', '1234566773434', '12345678', 'userCoab'),
(5, NULL, '2017-12-16 03:41:58', b'0', NULL, '2017-12-16 03:42:11', NULL, 'aritest@gmail.com', b'0', 'aritest', '1234556788', 'aritest1', 'AriTest');

-- --------------------------------------------------------

--
-- Table structure for table `sys_user_roles`
--

CREATE TABLE `sys_user_roles` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `disabled` bit(1) NOT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` varchar(5) DEFAULT NULL,
  `sys_roles_id` bigint(20) NOT NULL,
  `sys_user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sys_user_roles`
--

INSERT INTO `sys_user_roles` (`id`, `created_by`, `created_time`, `disabled`, `modified_by`, `modified_time`, `version`, `sys_roles_id`, `sys_user_id`) VALUES
(6, NULL, '2017-12-09 11:15:58', b'0', NULL, '2017-12-09 11:15:58', NULL, 1, 1),
(36, NULL, '2017-12-09 23:54:28', b'0', NULL, '2017-12-09 23:54:28', NULL, 3, 4),
(38, NULL, '2017-12-16 03:42:11', b'0', NULL, '2017-12-16 03:42:11', NULL, 3, 5),
(39, NULL, '2017-12-15 16:47:06', b'0', NULL, '2017-12-15 16:47:06', NULL, 2, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sys_audit_trail`
--
ALTER TABLE `sys_audit_trail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sys_authorization`
--
ALTER TABLE `sys_authorization`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhq2ryvy52dy43k5ddyhhc2xgy` (`parent_id`),
  ADD KEY `FK26plj38hw0p4qguic3jid2ep0` (`sys_menu_id`),
  ADD KEY `FK4ddvput2y3elnxqf1cj10rx26` (`sys_roles_id`);

--
-- Indexes for table `sys_menu`
--
ALTER TABLE `sys_menu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sys_roles`
--
ALTER TABLE `sys_roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sys_user`
--
ALTER TABLE `sys_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ahtq5ew3v0kt1n7hf1sgp7p8l` (`email`),
  ADD UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi4` (`username`);

--
-- Indexes for table `sys_user_roles`
--
ALTER TABLE `sys_user_roles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5rp9sy8avs7wgdwvg4q827imf` (`sys_roles_id`),
  ADD KEY `FKd0ut7sloes191bygyf7a3pk52` (`sys_user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sys_audit_trail`
--
ALTER TABLE `sys_audit_trail`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `sys_authorization`
--
ALTER TABLE `sys_authorization`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=257;
--
-- AUTO_INCREMENT for table `sys_menu`
--
ALTER TABLE `sys_menu`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `sys_roles`
--
ALTER TABLE `sys_roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `sys_user`
--
ALTER TABLE `sys_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `sys_user_roles`
--
ALTER TABLE `sys_user_roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `sys_authorization`
--
ALTER TABLE `sys_authorization`
  ADD CONSTRAINT `FK26plj38hw0p4qguic3jid2ep0` FOREIGN KEY (`sys_menu_id`) REFERENCES `sys_menu` (`id`),
  ADD CONSTRAINT `FK4ddvput2y3elnxqf1cj10rx26` FOREIGN KEY (`sys_roles_id`) REFERENCES `sys_roles` (`id`),
  ADD CONSTRAINT `FKhq2ryvy52dy43k5ddyhhc2xgy` FOREIGN KEY (`parent_id`) REFERENCES `sys_authorization` (`id`);

--
-- Constraints for table `sys_user_roles`
--
ALTER TABLE `sys_user_roles`
  ADD CONSTRAINT `FK5rp9sy8avs7wgdwvg4q827imf` FOREIGN KEY (`sys_roles_id`) REFERENCES `sys_roles` (`id`),
  ADD CONSTRAINT `FKd0ut7sloes191bygyf7a3pk52` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

--------------------
VER 1 20220409

create table pos_header_tmp(
 transaction_id varchar(50) not null primary key,
  customer_name varchar(200) not null,
  customer_id varchar(50) not null,
  phone_number varchar(200) not null,
  address varchar(255) not null,
  payment_channel varchar(50),
  payment_method varchar(50) not null,
  total_trx_amount float,
  total_discount_amount float,
  total_paid_amount float,
  created_by varchar(50) DEFAULT NULL,
  created_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table pos_detail_tmp (
  seq int(6) not null,
  transaction_id varchar(50) not null,
  item_code varchar(200) not null,
  item_name varchar(200) not null,
  qty integer(8) not null,
  seller_price float not null,
  price_detail json not null,
  basic_price float not null,
  status varchar(20) not null,
  index(transaction_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table invoice_sales_header(
 transaction_id varchar(50) not null,
 customer_name varchar(200) not null,
 customer_id varchar(50) not null,
 phone_number varchar(200) not null,
 address varchar(255) not null,
 payment_channel varchar(50),
 payment_method varchar(50),
 total_trx_amount float,
 total_discount_amount float,
 total_paid_amount float,
 created_by varchar(50) DEFAULT NULL,
 created_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 primary key (transaction_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table invoice_sales_detail (
 seq int(6) not null,
 transaction_id varchar(50) not null,
 item_code varchar(50) not null,
 item_name varchar(200) not null,
 qty integer(8) not null,
 unit_measure varchar(50) not null,
 seller_price double not null,
 price_detail json not null,
 basic_price double not null,
 status varchar(20) not null,
 index(transaction_id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table inventory_master_item(
 item_code varchar(50) not null,
 item_code_label varchar(200) not null,
 item_name varchar(200) not null,
 description varchar(500) not null,
 sell_price float not null default 0,
 price_detail json not null,
 basic_price float not null default 0,
 unit_measure varchar(50) not null,
 is_active boolean default true,
 item_type varchar(50) default null,
 created_time timestamp not null default current_timestamp,
 modified_time timestamp not null default current_timestamp,
 primary key (item_code)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table inventory_master_item_stock (
 item_code varchar(50) not null,
 stock integer not null default 0,
 primary key (item_code)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table inventory_stock (
item_code varchar(50) not null,
qty integer default 0,
index(item_code)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table user_costumers (
 customer_id varchar(50) not null primary key,
 customer_name varchar(200) not null,
 phone_number varchar(200) not null,
 address varchar(255) not null,
 created_by varchar(50) DEFAULT NULL,
 created_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 is_active boolean default false,
 inactive_time timestamp
)ENGINE=InnoDB DEFAULT CHARSET=latin1;




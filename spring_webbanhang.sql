-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 02, 2022 lúc 02:50 PM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `spring_webbanhang`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `name`, `parent_id`) VALUES
(12, 'Bàn phím', 9),
(9, 'Tai nghe', 0),
(10, 'Chuột fulhen', 11),
(11, 'Chuột', 0),
(13, 'Chuột logitechaaa', 11);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `order_date` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_detail`
--

CREATE TABLE `order_detail` (
  `id` int(11) NOT NULL,
  `discount` float DEFAULT NULL,
  `unit_price` float DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `view_count` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `created` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `description`, `image`, `name`, `view_count`, `category_id`, `quantity`, `discount`, `price`, `created`) VALUES
(4, '<p><strong>Chất lượng</strong></p>', '1645778381116.jpg', 'Chuột logitech ', 19, 11, 10, 10, 2500000, '2022-02-24'),
(5, '<p>Đẹp</p>', '1645778399095.jpg', 'Chuột logitech 1', 5, 11, 10, 25, 10000000, '2022-02-23'),
(6, '<p><strong>sds</strong></p>', '1645778415947.jpg', 'Chuột logitech 2', 18, 11, 10, 5, 3000000, '2022-02-22'),
(7, '<p>dsds</p>', '1645778432601.jpg', 'Chuột logitech 4', 4, 11, 10, 10, 5000000, '2022-02-21'),
(8, '<p>dsd</p>', '1645778447688.jpg', 'Chuột logitech 5', 20, 11, 10, 15, 6000000, '2022-02-25'),
(9, '<p><strong>dsds</strong></p>', '1645779882449.jpg', 'Bàn phím', 0, 12, 10, 25, 2000000, '2022-02-25'),
(10, '<p><strong>fd</strong></p>', '1645779901828.jpg', 'Bàn phím 1', 5, 12, 14, 25, 2000000, '2022-02-18'),
(11, '<p><strong>fd</strong></p>', '1645779918553.jpg', 'Bàn phím 2', 4, 12, 15, 25, 2300000, '2022-02-25'),
(12, '<p><strong>ds</strong></p>', '1645779941157.jpg', 'Bàn phím 4', 0, 12, 12, 25, 2700000, '2022-02-19'),
(13, '<p><strong>ds</strong></p>', '1645779972122.jpg', 'Bàn phím 5', 2, 12, 13, 25, 2900000, '2022-02-20'),
(15, '<p><strong>dsds</strong></p>', '1645781614585.jpg', 'dsds', 2, 10, 12, 10, 2300000, '2022-02-25');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_EMPLOYEE');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enable` int(11) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `email`, `enable`, `full_name`, `password`, `phone_number`, `role_id`, `user_name`) VALUES
(1, 'admin@gmail.com', 1, 'admin', '$2a$10$urQnI9lepRj/imtivJGd..1lr/9/kpE226XIbgOEmKRd4EuL1Rrz2', '01223415449', 1, 'admin'),
(2, 'congty1@gmail.com', 0, 'ádasd', '25f9e794323b453885f5181f1b624d0b', '1231232132', NULL, NULL),
(3, 'congty@gmail.com', 0, 'dsdsds', '25f9e794323b453885f5181f1b624d0b', '1231212132', 2, NULL),
(4, 'minhnhadsds2308@gmail.com', 0, 'ádasd', '25f9e794323b453885f5181f1b624d0b', '1231232132', 2, NULL),
(5, 'minhnha2308@gmail.com', 1, 'sâs', '25f9e794323b453885f5181f1b624d0b', '1231232132', 2, NULL),
(6, 'consssgty1@gmail.com', 0, 'ádasdss', '25f9e794323b453885f5181f1b624d0b', '1231232132', 2, NULL);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`);

--
-- Chỉ mục cho bảng `order_detail`
--
ALTER TABLE `order_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrws2q0si6oyd6il8gqe2aennc` (`order_id`),
  ADD KEY `FKb8bg2bkty0oksa3wiq5mp5qnc` (`product_id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKowomku74u72o6h8q0khj7id8q` (`category_id`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `order_detail`
--
ALTER TABLE `order_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

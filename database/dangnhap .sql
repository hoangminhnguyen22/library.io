-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 04, 2022 lúc 10:22 AM
-- Phiên bản máy phục vụ: 10.4.21-MariaDB
-- Phiên bản PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `dangnhap`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `books`
--

CREATE TABLE `books` (
  `book_id` varchar(255) NOT NULL,
  `book_title` varchar(255) NOT NULL,
  `book_author` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `book_status` int(3) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `books`
--

INSERT INTO `books` (`book_id`, `book_title`, `book_author`, `image`, `book_status`) VALUES
('1', 'Khi bạn đang mơ thì người khác đang nổ lực', 'Vĩ Nhân', '1.jpg', 1),
('2', 'Đắc Nhân Tâm', 'Dale Carnegie', '2.jpg', 1),
('3', 'Nhà giả kim', 'Paulo Coelho', '3.jpg', 1),
('4', 'Tuổi Trẻ Đáng Giá Bao Nhiêu?', 'Rosie Nguyễn', '5.jpg', 1),
('5', 'Tội Ác Và Hình Phạt', 'Fyodor Dostoevsky', '4.jpg', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `borrow`
--

CREATE TABLE `borrow` (
  `borrow_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `borrow_status` int(3) NOT NULL DEFAULT 0,
  `borrow_begindate` timestamp NOT NULL DEFAULT current_timestamp(),
  `borrow_returndate` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `borrow`
--

INSERT INTO `borrow` (`borrow_id`, `user_id`, `borrow_status`, `borrow_begindate`, `borrow_returndate`) VALUES
(1, 1, 1, '2022-04-26 07:27:28', '2022-04-28 07:27:11'),
(2, 1, 0, '2022-04-26 08:14:05', '2022-04-29 08:13:54'),
(3, 1, 1, '2022-04-27 06:52:38', NULL),
(4, 1, 1, '2022-04-27 06:53:19', NULL),
(5, 1, 1, '2022-04-27 06:53:52', NULL),
(6, 2, 0, '2022-04-27 06:54:23', NULL),
(7, 1, 0, '2022-04-27 07:01:45', NULL),
(8, 1, 1, '2022-04-27 07:03:26', NULL),
(9, 1, 0, '2022-04-27 07:05:24', NULL),
(10, 1, 0, '2022-04-27 07:08:06', NULL),
(11, 1, 0, '2022-04-27 07:08:06', NULL),
(12, 1, 0, '2022-04-27 07:09:16', NULL),
(13, 1, 0, '2022-04-27 07:12:06', NULL),
(14, 1, 0, '2022-04-27 07:12:31', NULL),
(15, 1, 0, '2022-04-27 07:13:44', NULL),
(16, 1, 0, '2022-04-27 07:13:55', NULL),
(17, 1, 0, '2022-04-27 07:17:29', NULL),
(18, 1, 0, '2022-04-29 15:10:42', NULL),
(19, 1, 0, '2022-04-28 10:15:34', '2022-04-30 00:17:00'),
(20, 1, 0, '2022-05-04 06:28:42', '2022-05-19 06:28:00'),
(21, 1, 0, '2022-05-04 06:54:22', '2022-05-18 06:54:00'),
(22, 1, 0, '2022-05-04 06:56:46', '2022-05-21 06:56:00'),
(23, 1, 0, '2022-05-04 07:01:48', '2022-05-05 07:01:00'),
(24, 1, 1, '2022-05-04 07:08:30', '2022-05-14 07:09:00'),
(25, 1, 1, '2022-05-04 07:13:58', '2022-05-07 07:13:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `borrowdetail`
--

CREATE TABLE `borrowdetail` (
  `borrow_id` varchar(255) NOT NULL,
  `book_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `borrowdetail`
--

INSERT INTO `borrowdetail` (`borrow_id`, `book_id`) VALUES
('1', '2'),
('1', '2'),
('2', '2'),
('2', '3'),
('6', 'E28011606000020958CDC43E'),
('6', 'E28011606000020958CD98EE'),
('6', '3'),
('7', 'E28011606000020958CD98EE'),
('10', 'E28011606000020958CD98EE'),
('12', 'E28011606000020958CD98EE'),
('13', 'E28011606000020958CD98EE'),
('13', '3'),
('13', 'E28011606000020958CDC43E'),
('13', '2'),
('13', '1'),
('14', 'E28011606000020958CD98EE'),
('15', '3'),
('16', 'E28011606000020958CDC43E'),
('17', '1'),
('18', '1'),
('19', 'E28011606000020958CDC43E'),
('20', 'E28011606000020958CDC43E'),
('20', '3'),
('20', '1'),
('21', '4'),
('21', '2'),
('21', '1'),
('22', '4'),
('22', '3'),
('22', '1'),
('23', '5'),
('23', '2'),
('23', '4'),
('23', '1'),
('24', '4'),
('24', '3'),
('24', '2'),
('25', '5'),
('25', '4'),
('25', '3'),
('25', '2'),
('25', '1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `failed_jobs`
--

CREATE TABLE `failed_jobs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `uuid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `connection` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `payload` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `exception` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1),
(3, '2019_08_19_000000_create_failed_jobs_table', 1),
(4, '2019_12_14_000001_create_personal_access_tokens_table', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `personal_access_tokens`
--

CREATE TABLE `personal_access_tokens` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `tokenable_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tokenable_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `abilities` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_used_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tag_read`
--

CREATE TABLE `tag_read` (
  `tag_rfid` varchar(255) NOT NULL,
  `book_id` varchar(255) NOT NULL,
  `tag_time` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `user_phone` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `level` int(3) NOT NULL DEFAULT 3,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `user_name`, `user_phone`, `level`, `username`, `password`) VALUES
(1, 'Nguyễn Minh Hoàng', '0586666213', 1, 'hoangvn1212', '$2y$10$2CYxpkns6lqSl3ooK4AWAO/iSGmoBvKpxOJoJivPLBOMf0D7fTUXO'),
(2, 'Minh Đạt', '0966666666', 3, 'datvnv1212', '$2y$10$2CYxpkns6lqSl3ooK4AWAO/iSGmoBvKpxOJoJivPLBOMf0D7fTUXO');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`);

--
-- Chỉ mục cho bảng `borrow`
--
ALTER TABLE `borrow`
  ADD PRIMARY KEY (`borrow_id`);

--
-- Chỉ mục cho bảng `failed_jobs`
--
ALTER TABLE `failed_jobs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `failed_jobs_uuid_unique` (`uuid`);

--
-- Chỉ mục cho bảng `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`);

--
-- Chỉ mục cho bảng `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `personal_access_tokens_token_unique` (`token`),
  ADD KEY `personal_access_tokens_tokenable_type_tokenable_id_index` (`tokenable_type`,`tokenable_id`);

--
-- Chỉ mục cho bảng `tag_read`
--
ALTER TABLE `tag_read`
  ADD PRIMARY KEY (`tag_rfid`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `borrow`
--
ALTER TABLE `borrow`
  MODIFY `borrow_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT cho bảng `failed_jobs`
--
ALTER TABLE `failed_jobs`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

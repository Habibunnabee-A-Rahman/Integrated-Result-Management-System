-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2025 at 11:02 AM
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
-- Database: `irms_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `ID` varchar(20) NOT NULL,
  `university_id` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`ID`, `university_id`, `password`) VALUES
('SCHS@admin', 'UV-0011', '81dc9bdb52d04dc20036dbd8313ed055'),
('SUB@admin', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055'),
('Super-Admin', 'UV-0000', '81dc9bdb52d04dc20036dbd8313ed055');

-- --------------------------------------------------------

--
-- Table structure for table `employee_login`
--

CREATE TABLE `employee_login` (
  `ID` varchar(50) NOT NULL,
  `university_id` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `T16_id_fk` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee_login`
--

INSERT INTO `employee_login` (`ID`, `university_id`, `password`, `T16_id_fk`) VALUES
('EMP-0001', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 12),
('EMP-0002', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 13),
('EMP-0003', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 14),
('EMP-0004', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 15);

-- --------------------------------------------------------

--
-- Table structure for table `student_login`
--

CREATE TABLE `student_login` (
  `ID` varchar(50) NOT NULL,
  `university_id` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `T20_id_fk` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_login`
--

INSERT INTO `student_login` (`ID`, `university_id`, `password`, `T20_id_fk`) VALUES
('UG02-56-20-001', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 2),
('UG02-56-20-002', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 3),
('UG02-56-20-003', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 4),
('UG02-57-20-001', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 22),
('UG02-57-20-002', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 23),
('UG02-57-20-003', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 24),
('UG02-57-20-004', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 25),
('UG02-57-20-005', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 26),
('UG02-57-20-006', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 27),
('UG02-57-20-007', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 28),
('UG02-58-21-001', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 8),
('UG02-58-21-002', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 9),
('UG02-58-21-003', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 10),
('UG02-58-21-004', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 11),
('UG02-58-21-005', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 29),
('UG02-58-21-006', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 30),
('UG02-58-21-007', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 31),
('UG02-59-22-001', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 5),
('UG02-59-22-002', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 6),
('UG02-59-22-016', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 7),
('UG02-60-22-001', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 12),
('UG02-60-22-002', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 16),
('UG02-60-22-003', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 17),
('UG02-60-22-004', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 18),
('UG02-60-22-005', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 19),
('UG02-60-22-006', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 20),
('UG02-60-22-007', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 21),
('UG03-66-25-001', 'UV-0001', '81dc9bdb52d04dc20036dbd8313ed055', 32);

-- --------------------------------------------------------

--
-- Table structure for table `t01_university`
--

CREATE TABLE `t01_university` (
  `T01_id` bigint(20) NOT NULL,
  `university_id` varchar(50) NOT NULL,
  `university_name` varchar(100) NOT NULL,
  `acronym` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t01_university`
--

INSERT INTO `t01_university` (`T01_id`, `university_id`, `university_name`, `acronym`) VALUES
(0, 'UV-0000', 'All', 'A'),
(3, 'UV-0001', 'State', 'SUB'),
(4, 'UV-0002', 'asd', 'asd'),
(5, 'UV-0010', 'State University of Bangladesh', 'SUB'),
(7, 'UV-0011', 'State', 'SUB'),
(9, 'sss', 's', 's'),
(10, 'qqqsd', 'd', 'd'),
(11, 'qweq', 'sda', 'sdf'),
(14, 'UV00119', 'Ahsanullah', 'AUST');

-- --------------------------------------------------------

--
-- Table structure for table `t02_job`
--

CREATE TABLE `t02_job` (
  `T02_id` bigint(20) NOT NULL,
  `job_id` varchar(50) NOT NULL,
  `job_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t02_job`
--

INSERT INTO `t02_job` (`T02_id`, `job_id`, `job_name`) VALUES
(1, 'J001', 'admin'),
(2, 'J002', 'teacher');

-- --------------------------------------------------------

--
-- Table structure for table `t03_school`
--

CREATE TABLE `t03_school` (
  `T03_id` bigint(20) NOT NULL,
  `school_id` varchar(50) NOT NULL,
  `T01_id_fk` bigint(20) NOT NULL,
  `school_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t03_school`
--

INSERT INTO `t03_school` (`T03_id`, `school_id`, `T01_id_fk`, `school_name`) VALUES
(0, 'SCH00', 0, 'Default_School'),
(8, 'SCH02', 3, 'Medical School'),
(9, 'SCH01', 3, 'Engineering School'),
(11, 'SCH03', 3, 'Business-School');

-- --------------------------------------------------------

--
-- Table structure for table `t04_department`
--

CREATE TABLE `t04_department` (
  `T04_id` bigint(20) NOT NULL,
  `department_id` varchar(50) NOT NULL,
  `T03_id_fk` bigint(20) NOT NULL,
  `department_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t04_department`
--

INSERT INTO `t04_department` (`T04_id`, `department_id`, `T03_id_fk`, `department_name`) VALUES
(0, 'DPT00', 0, 'Default_Department'),
(10, 'CSE', 9, 'Computer Science and Engineering'),
(11, 'EEE', 8, 'Electronics'),
(12, 'EEE', 9, 'Electronics'),
(14, 'CVL', 9, 'Civil Engineering'),
(15, 'BBA', 11, 'Business Adminstration');

-- --------------------------------------------------------

--
-- Table structure for table `t05_program`
--

CREATE TABLE `t05_program` (
  `T05_id` bigint(20) NOT NULL,
  `program_id` varchar(50) NOT NULL,
  `T04_id_fk` bigint(20) NOT NULL,
  `program_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t05_program`
--

INSERT INTO `t05_program` (`T05_id`, `program_id`, `T04_id_fk`, `program_name`) VALUES
(0, 'PGM00', 0, 'Default_Program'),
(7, 'BSC-01', 10, 'Bachelor in CSE'),
(8, 'MSC-02', 10, 'Mastersin CSE'),
(10, 'BSC-01', 14, 'B.Sc. in Civil Engineering'),
(11, 'MSC-01', 14, 'M.Sc. in Civil Engineering'),
(12, 'PGM01', 15, 'Bachelor in Business Adminstration');

-- --------------------------------------------------------

--
-- Table structure for table `t06_syllabus`
--

CREATE TABLE `t06_syllabus` (
  `T06_id` bigint(20) NOT NULL,
  `syllabus_id` varchar(50) NOT NULL,
  `T05_id_fk` bigint(20) NOT NULL,
  `syllabus_name` varchar(100) NOT NULL,
  `default_T08_id_fk` bigint(20) DEFAULT NULL,
  `syllabus_status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t06_syllabus`
--

INSERT INTO `t06_syllabus` (`T06_id`, `syllabus_id`, `T05_id_fk`, `syllabus_name`, `default_T08_id_fk`, `syllabus_status`) VALUES
(0, 'SYL00', 0, 'Default_Syllabus', NULL, NULL),
(8, 'SB-02', 7, 'TriSemester', 11, 1),
(10, 'SB-01', 7, 'TriSemester2025', 11, 1),
(11, 'SB-01', 11, 'One Year', NULL, NULL),
(12, 'SB-01', 10, 'TriSemester2025', NULL, NULL),
(13, 'SB-02', 11, 'One&Half year', NULL, NULL),
(14, 'SB-02', 10, 'Bi-Semester2025', NULL, NULL),
(15, 'S01', 12, 'Syllabus one', 11, 1);

-- --------------------------------------------------------

--
-- Table structure for table `t07_course`
--

CREATE TABLE `t07_course` (
  `T07_id` bigint(20) NOT NULL,
  `course_id` varchar(50) NOT NULL,
  `T06_id_fk` bigint(20) NOT NULL,
  `course_name` varchar(150) NOT NULL,
  `course_credit` double NOT NULL,
  `course_status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t07_course`
--

INSERT INTO `t07_course` (`T07_id`, `course_id`, `T06_id_fk`, `course_name`, `course_credit`, `course_status`) VALUES
(0, 'CRS0000', 0, 'default_course', 0, NULL),
(55, 'CSE0101', 8, 'Fund', 3, 1),
(56, 'CSE0102', 8, 'Fund LAB', 0.75, 1),
(57, 'CSE0105', 8, 'JAVA', 3, 1),
(58, 'CSE0106', 8, 'JAVA lab', 1.5, 1),
(59, 'CSE0201', 8, 'Discreate', 2, 1),
(60, 'EEE0205', 8, 'Circuit', 3, 1),
(61, 'CSE0400', 8, 'Project', 4, 1),
(63, 'CSE0101', 10, 'Fundamenatal', 3, 1),
(64, 'CSE0102', 10, 'Fun LAB', 1.5, 1),
(65, 'CSE0105', 10, 'Java', 3, 1),
(66, 'CSE0106', 10, 'Java Lab', 1.5, 1),
(67, 'MAT0101', 10, 'Calculus 1', 3, 1),
(68, 'MAT0201', 10, 'Calculus 2', 3, 1),
(69, 'CSE0400', 10, 'Project', 4, 1),
(70, 'EEE0107', 10, 'Basic Circuits', 3, 1),
(71, 'CSE0437', 10, 'Bio Informatics', 0.75, 1),
(72, 'E0001', 10, 'Ethics', 0, 1),
(73, 'BBA0101', 15, 'Business', 3, 1),
(74, 'BBA0105', 15, 'xxxx', 0.75, 1),
(75, 'BBA0106', 15, 'xyz', 1.5, 1),
(76, 'BBA0201', 15, 'yxz', 3, 1),
(77, 'BB0205', 15, 'asd', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `t08_evaluation_set`
--

CREATE TABLE `t08_evaluation_set` (
  `T08_id` bigint(20) NOT NULL,
  `evaluation_set_id` varchar(50) NOT NULL,
  `T01_id_fk` bigint(20) NOT NULL,
  `total_marks` double NOT NULL,
  `editable` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t08_evaluation_set`
--

INSERT INTO `t08_evaluation_set` (`T08_id`, `evaluation_set_id`, `T01_id_fk`, `total_marks`, `editable`) VALUES
(0, 'EVLTN0000', 0, -1, NULL),
(11, 'Evaluation01(100)', 3, 100, 1),
(12, 'Evaluation01(200)', 3, 200, 1),
(13, 'EVA01', 3, 100, NULL),
(15, 'Evaluation03(50)', 3, 50, 1),
(16, 'Ev', 3, 120, NULL),
(17, 'Evaluation75', 3, 75, 1);

-- --------------------------------------------------------

--
-- Table structure for table `t09_syllabus_plo`
--

CREATE TABLE `t09_syllabus_plo` (
  `T09_id` bigint(20) NOT NULL,
  `plo_id` int(50) NOT NULL,
  `T06_id_fk` bigint(20) NOT NULL,
  `plo_name` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t09_syllabus_plo`
--

INSERT INTO `t09_syllabus_plo` (`T09_id`, `plo_id`, `T06_id_fk`, `plo_name`) VALUES
(22, 1, 8, 'A'),
(23, 2, 8, 'B'),
(24, 3, 8, 'C'),
(25, 4, 8, 'D'),
(26, 5, 8, 'E'),
(27, 6, 8, 'F'),
(28, 1, 10, 'Basic'),
(29, 2, 10, 'Pre Intermediate'),
(30, 3, 10, 'Intermediate'),
(31, 4, 10, 'Advance Intermediate'),
(32, 5, 10, 'Expert'),
(33, 6, 10, 'Professional'),
(34, 1, 15, 'basic'),
(35, 2, 15, 'inter'),
(36, 3, 15, 'professional'),
(37, 4, 15, 'expert');

-- --------------------------------------------------------

--
-- Table structure for table `t10_course_clo`
--

CREATE TABLE `t10_course_clo` (
  `T10_id` bigint(20) NOT NULL,
  `T07_id_fk` bigint(20) NOT NULL,
  `clo_number` int(11) NOT NULL,
  `T15_id_fk` bigint(20) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t10_course_clo`
--

INSERT INTO `t10_course_clo` (`T10_id`, `T07_id_fk`, `clo_number`, `T15_id_fk`) VALUES
(130, 55, 1, 0),
(131, 55, 2, 0),
(132, 55, 3, 0),
(133, 55, 4, 0),
(134, 56, 1, 0),
(136, 57, 1, 0),
(137, 58, 1, 0),
(153, 58, 1, 51),
(154, 58, 2, 51),
(135, 59, 1, 0),
(145, 60, 1, 0),
(138, 61, 1, 0),
(146, 61, 1, 52),
(139, 61, 2, 0),
(147, 61, 2, 52),
(140, 61, 3, 0),
(148, 61, 3, 52),
(141, 61, 4, 0),
(149, 61, 4, 52),
(142, 61, 5, 0),
(150, 61, 5, 52),
(143, 61, 6, 0),
(144, 61, 7, 0),
(155, 63, 1, 0),
(156, 63, 2, 0),
(157, 63, 3, 0),
(158, 63, 4, 0),
(159, 63, 5, 0),
(160, 64, 1, 0),
(229, 64, 1, 59),
(161, 64, 2, 0),
(230, 64, 2, 59),
(162, 64, 3, 0),
(231, 64, 3, 59),
(163, 64, 4, 0),
(232, 64, 4, 59),
(164, 65, 1, 0),
(165, 65, 2, 0),
(166, 65, 3, 0),
(167, 65, 4, 0),
(168, 66, 1, 0),
(169, 66, 2, 0),
(170, 66, 3, 0),
(180, 67, 1, 0),
(181, 67, 2, 0),
(182, 67, 3, 0),
(189, 68, 1, 0),
(190, 68, 2, 0),
(191, 68, 3, 0),
(192, 68, 4, 0),
(171, 69, 1, 0),
(219, 69, 1, 55),
(172, 69, 2, 0),
(220, 69, 2, 55),
(173, 69, 3, 0),
(221, 69, 3, 55),
(174, 69, 4, 0),
(222, 69, 4, 55),
(175, 69, 5, 0),
(223, 69, 5, 55),
(176, 69, 6, 0),
(224, 69, 6, 55),
(177, 69, 7, 0),
(225, 69, 7, 55),
(178, 69, 8, 0),
(226, 69, 8, 55),
(179, 69, 9, 0),
(227, 69, 9, 55),
(228, 69, 10, 55),
(193, 70, 1, 0),
(194, 70, 2, 0),
(195, 70, 3, 0),
(203, 70, 4, 0),
(204, 70, 5, 0),
(205, 70, 6, 0),
(206, 70, 7, 0),
(207, 70, 8, 0),
(208, 70, 9, 0),
(183, 71, 1, 0),
(184, 71, 2, 0),
(185, 71, 3, 0),
(186, 71, 4, 0),
(187, 71, 5, 0),
(188, 71, 6, 0),
(196, 72, 1, 0),
(197, 72, 2, 0),
(198, 72, 3, 0),
(199, 72, 4, 0),
(200, 72, 5, 0),
(201, 72, 6, 0),
(202, 72, 7, 0),
(238, 73, 1, 0),
(243, 73, 1, 63),
(239, 73, 2, 0),
(244, 73, 2, 63),
(245, 73, 3, 63),
(240, 74, 1, 0),
(241, 75, 1, 0),
(242, 76, 1, 0),
(233, 77, 1, 0),
(234, 77, 2, 0),
(235, 77, 3, 0),
(236, 77, 4, 0),
(237, 77, 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table `t11_evaluation`
--

CREATE TABLE `t11_evaluation` (
  `T11_id` bigint(20) NOT NULL,
  `T08_id_fk` bigint(20) NOT NULL,
  `evaluation_name` varchar(50) NOT NULL,
  `marks` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t11_evaluation`
--

INSERT INTO `t11_evaluation` (`T11_id`, `T08_id_fk`, `evaluation_name`, `marks`) VALUES
(26, 11, 'Attendance', 10),
(27, 11, 'Final', 40),
(28, 11, 'Mid', 30),
(29, 11, 'ClassTest', 20),
(30, 12, 'Attendance', 20),
(31, 12, 'ClassTest', 30),
(32, 12, 'Mid', 50),
(33, 12, 'Final', 80),
(34, 12, 'Presentation', 20),
(35, 13, 'Final', 50),
(36, 13, 'Presentation', 10),
(37, 13, 'Attendance', 10),
(38, 13, 'Classtest', 30),
(39, 15, 'Class Work', 10),
(40, 15, 'Presentation', 20),
(41, 15, 'Assignment', 5),
(42, 15, 'Final', 15),
(43, 16, 'asd', 50),
(44, 16, 'asd1', 10),
(45, 16, 'asd2', 60),
(46, 17, 'Presentation', 30),
(47, 17, 'Assignment', 20),
(48, 17, 'Qui', 25);

-- --------------------------------------------------------

--
-- Table structure for table `t12_unique_course_evaluation`
--

CREATE TABLE `t12_unique_course_evaluation` (
  `T12_id` bigint(20) NOT NULL,
  `T07_id_fk` bigint(20) NOT NULL,
  `T08_id_fk` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t12_unique_course_evaluation`
--

INSERT INTO `t12_unique_course_evaluation` (`T12_id`, `T07_id_fk`, `T08_id_fk`) VALUES
(14, 61, 12),
(15, 69, 12),
(16, 72, 15),
(17, 75, 17);

-- --------------------------------------------------------

--
-- Table structure for table `t13_clo_plo_connect`
--

CREATE TABLE `t13_clo_plo_connect` (
  `T13_id` bigint(20) NOT NULL,
  `T10_id_fk` bigint(20) NOT NULL,
  `T09_id_fk` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t13_clo_plo_connect`
--

INSERT INTO `t13_clo_plo_connect` (`T13_id`, `T10_id_fk`, `T09_id_fk`) VALUES
(187, 130, 22),
(188, 131, 24),
(189, 132, 25),
(190, 132, 26),
(191, 133, 22),
(192, 134, 22),
(193, 135, 22),
(194, 136, 22),
(195, 137, 23),
(196, 137, 24),
(197, 138, 22),
(198, 139, 23),
(199, 139, 24),
(200, 140, 24),
(201, 141, 24),
(202, 142, 26),
(203, 142, 27),
(204, 143, 24),
(205, 144, 27),
(206, 145, 22),
(207, 146, 22),
(208, 146, 24),
(209, 147, 23),
(210, 147, 24),
(211, 147, 26),
(212, 147, 27),
(213, 148, 22),
(214, 148, 23),
(215, 149, 23),
(216, 149, 24),
(217, 150, 22),
(218, 150, 23),
(224, 150, 26),
(225, 150, 27),
(221, 153, 23),
(222, 153, 24),
(223, 154, 23),
(226, 155, 28),
(227, 156, 29),
(228, 156, 31),
(229, 157, 30),
(230, 158, 31),
(231, 159, 29),
(232, 159, 32),
(233, 159, 33),
(234, 160, 28),
(235, 161, 31),
(236, 162, 30),
(237, 163, 28),
(238, 164, 28),
(239, 165, 29),
(240, 166, 30),
(241, 167, 33),
(242, 168, 29),
(243, 169, 28),
(244, 169, 31),
(245, 170, 28),
(246, 171, 28),
(247, 171, 29),
(248, 172, 28),
(249, 172, 30),
(250, 172, 32),
(251, 173, 29),
(252, 174, 28),
(253, 174, 30),
(254, 175, 32),
(255, 175, 33),
(256, 176, 28),
(257, 176, 30),
(258, 176, 33),
(259, 177, 28),
(260, 177, 33),
(261, 178, 28),
(262, 178, 30),
(263, 178, 32),
(264, 178, 33),
(265, 179, 29),
(266, 179, 31),
(267, 179, 32),
(268, 179, 33),
(271, 180, 29),
(272, 181, 29),
(273, 182, 29),
(274, 182, 30),
(275, 183, 28),
(276, 183, 30),
(277, 184, 30),
(278, 185, 28),
(279, 185, 30),
(280, 186, 32),
(281, 186, 33),
(282, 187, 29),
(283, 188, 29),
(284, 188, 30),
(285, 188, 31),
(286, 188, 32),
(287, 189, 30),
(288, 189, 32),
(289, 190, 28),
(290, 190, 30),
(291, 190, 33),
(292, 191, 29),
(293, 191, 33),
(294, 192, 29),
(295, 192, 32),
(296, 193, 28),
(297, 193, 31),
(298, 193, 33),
(299, 194, 29),
(300, 195, 29),
(312, 195, 32),
(301, 196, 28),
(302, 197, 29),
(303, 197, 30),
(304, 198, 28),
(305, 198, 33),
(306, 199, 29),
(307, 200, 32),
(308, 201, 28),
(309, 202, 28),
(310, 202, 29),
(311, 202, 32),
(313, 203, 29),
(314, 204, 28),
(315, 205, 28),
(316, 206, 29),
(317, 207, 28),
(318, 208, 31),
(319, 208, 32),
(347, 219, 29),
(348, 220, 30),
(349, 220, 32),
(350, 221, 29),
(351, 221, 31),
(352, 222, 28),
(353, 222, 30),
(354, 222, 31),
(355, 223, 31),
(356, 223, 32),
(357, 223, 33),
(358, 224, 28),
(359, 224, 30),
(360, 224, 33),
(361, 225, 28),
(362, 225, 33),
(363, 226, 28),
(364, 226, 30),
(365, 226, 32),
(366, 226, 33),
(367, 227, 29),
(368, 227, 31),
(369, 227, 32),
(370, 227, 33),
(371, 228, 28),
(372, 228, 29),
(373, 228, 30),
(374, 228, 31),
(375, 228, 32),
(376, 229, 28),
(377, 229, 30),
(378, 229, 31),
(379, 230, 31),
(380, 231, 30),
(381, 232, 28),
(382, 233, 34),
(383, 234, 35),
(384, 235, 34),
(385, 235, 36),
(386, 236, 36),
(387, 237, 35),
(388, 238, 34),
(389, 239, 35),
(390, 240, 34),
(391, 241, 34),
(392, 242, 34),
(393, 243, 34),
(394, 244, 35),
(395, 245, 34);

-- --------------------------------------------------------

--
-- Table structure for table `t14_semester`
--

CREATE TABLE `t14_semester` (
  `T14_id` bigint(20) NOT NULL,
  `semester_id` varchar(50) NOT NULL,
  `T06_id_fk` bigint(20) NOT NULL,
  `semester_name` varchar(100) NOT NULL,
  `semester_status` int(11) NOT NULL DEFAULT 0,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t14_semester`
--

INSERT INTO `t14_semester` (`T14_id`, `semester_id`, `T06_id_fk`, `semester_name`, `semester_status`, `start_date`, `end_date`) VALUES
(0, 'SEM00', 0, 'Default_Semester', 0, NULL, NULL),
(18, 'SEM01', 8, 'SUMMER2024', 1, '2025-04-17', NULL),
(19, 'SEM02', 8, 'Spring', 0, NULL, NULL),
(20, 'SEM007', 8, 'RAIN2025', 1, '2025-01-07', NULL),
(22, 'SEM01', 10, 'SUMMER 2024', 1, '2024-08-24', NULL),
(23, 'SEM02', 10, 'SPRING 2024', 1, '2025-02-24', NULL),
(24, 'SEM01', 15, 'SPRING2025', 1, '2025-01-01', NULL),
(25, 'SEM04', 10, 'Fall2024', 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t15_offered_course`
--

CREATE TABLE `t15_offered_course` (
  `T15_id` bigint(20) NOT NULL,
  `T07_id_fk` bigint(20) NOT NULL,
  `T14_id_fk` bigint(20) NOT NULL,
  `T16_id_fk` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t15_offered_course`
--

INSERT INTO `t15_offered_course` (`T15_id`, `T07_id_fk`, `T14_id_fk`, `T16_id_fk`) VALUES
(0, 0, 0, NULL),
(42, 55, 18, 13),
(43, 57, 18, 14),
(44, 58, 18, 15),
(45, 61, 18, 14),
(46, 55, 19, NULL),
(47, 56, 19, NULL),
(48, 59, 19, NULL),
(49, 61, 19, NULL),
(50, 57, 20, 14),
(51, 58, 20, 14),
(52, 61, 20, 13),
(54, 63, 22, 15),
(55, 69, 22, 13),
(56, 72, 22, 14),
(57, 70, 22, 13),
(58, 68, 22, 14),
(59, 64, 23, 14),
(60, 69, 23, 14),
(61, 67, 23, 13),
(62, 68, 23, 13),
(63, 73, 24, 15),
(64, 76, 24, 14),
(65, 65, 25, NULL),
(66, 69, 25, 13),
(67, 72, 25, NULL),
(68, 67, 25, NULL),
(69, 68, 25, 14);

-- --------------------------------------------------------

--
-- Table structure for table `t16_employee`
--

CREATE TABLE `t16_employee` (
  `T16_id` bigint(20) NOT NULL,
  `employee_id` varchar(100) NOT NULL,
  `T01_id_fk` bigint(20) NOT NULL,
  `T02_id_fk` bigint(20) NOT NULL,
  `employee_name` varchar(100) NOT NULL,
  `designation` varchar(100) NOT NULL,
  `contact` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t16_employee`
--

INSERT INTO `t16_employee` (`T16_id`, `employee_id`, `T01_id_fk`, `T02_id_fk`, `employee_name`, `designation`, `contact`, `status`) VALUES
(12, 'EMP-0001', 3, 1, 'Himaloy', 'ADMIN', '', 0),
(13, 'EMP-0002', 3, 2, 'Habibunnabee', 'Instructor', '', 1),
(14, 'EMP-0003', 3, 2, 'Farzana', 'Lec', '', 1),
(15, 'EMP-0004', 3, 2, 'Sithi', 'Lec', '', 1),
(16, 'EMP-0005', 3, 1, 'Alvy', 'ADMIN', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `t17_batch`
--

CREATE TABLE `t17_batch` (
  `T17_id` bigint(20) NOT NULL,
  `batch_id` varchar(100) NOT NULL,
  `T01_id_fk` bigint(20) NOT NULL,
  `T14_id_fk` bigint(20) NOT NULL,
  `batch_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t17_batch`
--

INSERT INTO `t17_batch` (`T17_id`, `batch_id`, `T01_id_fk`, `T14_id_fk`, `batch_name`) VALUES
(13, 'B56', 3, 19, 'Batch of 56'),
(15, 'CSE-B59', 3, 20, 'CSE batch of 59'),
(16, 'B55', 3, 18, 'Batch of 55'),
(17, 'CSE-B58', 3, 22, 'Batch 58'),
(18, 'CSE-B60', 3, 23, 'Batch 60'),
(19, 'CSE-B57', 3, 22, 'Batch 57'),
(20, 'BBA-66', 3, 24, 'Batch of 66'),
(21, 'CSE-B66', 3, 25, 'Batch of 66');

-- --------------------------------------------------------

--
-- Table structure for table `t18_exam`
--

CREATE TABLE `t18_exam` (
  `T18_id` bigint(20) NOT NULL,
  `T15_id_fk` bigint(20) NOT NULL,
  `T11_id_fk` bigint(20) NOT NULL,
  `exam_status` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t18_exam`
--

INSERT INTO `t18_exam` (`T18_id`, `T15_id_fk`, `T11_id_fk`, `exam_status`) VALUES
(56, 42, 26, 0),
(57, 42, 29, 0),
(58, 42, 27, 0),
(59, 42, 28, 0),
(60, 43, 26, 0),
(61, 43, 29, 0),
(62, 43, 27, 0),
(63, 43, 28, 0),
(64, 44, 26, 0),
(65, 44, 29, 0),
(66, 44, 27, 0),
(67, 44, 28, 0),
(68, 45, 30, 0),
(69, 45, 31, 0),
(70, 45, 33, 0),
(71, 45, 32, 0),
(72, 45, 34, 0),
(73, 46, 26, 0),
(74, 46, 29, 0),
(75, 46, 27, 0),
(76, 46, 28, 0),
(77, 47, 26, 0),
(78, 47, 29, 0),
(79, 47, 27, 0),
(80, 47, 28, 0),
(81, 48, 26, 0),
(82, 48, 29, 0),
(83, 48, 27, 0),
(84, 48, 28, 0),
(85, 49, 30, 0),
(86, 49, 31, 0),
(87, 49, 33, 0),
(88, 49, 32, 0),
(89, 49, 34, 0),
(90, 50, 26, 0),
(91, 50, 29, 0),
(92, 50, 27, 0),
(93, 50, 28, 0),
(94, 51, 26, 0),
(95, 51, 29, 0),
(96, 51, 27, 0),
(97, 51, 28, 0),
(98, 52, 30, 0),
(99, 52, 31, 0),
(100, 52, 33, 0),
(101, 52, 32, 0),
(102, 52, 34, 0),
(103, 54, 26, 1),
(104, 54, 29, 0),
(105, 54, 27, 0),
(106, 54, 28, 0),
(107, 55, 30, 1),
(108, 55, 31, 1),
(109, 55, 33, 1),
(110, 55, 32, 1),
(111, 55, 34, 1),
(112, 56, 41, 1),
(113, 56, 39, 1),
(114, 56, 42, 1),
(115, 56, 40, 1),
(116, 57, 26, 1),
(117, 57, 29, 1),
(118, 57, 27, 1),
(119, 57, 28, 1),
(120, 58, 26, 1),
(121, 58, 29, 1),
(122, 58, 27, 1),
(123, 58, 28, 1),
(124, 59, 26, 1),
(125, 59, 29, 1),
(126, 59, 27, 1),
(127, 59, 28, 1),
(128, 60, 30, 1),
(129, 60, 31, 1),
(130, 60, 33, 1),
(131, 60, 32, 1),
(132, 60, 34, 1),
(133, 61, 26, 1),
(134, 61, 29, 1),
(135, 61, 27, 1),
(136, 61, 28, 1),
(137, 62, 26, 1),
(138, 62, 29, 1),
(139, 62, 27, 1),
(140, 62, 28, 1),
(141, 63, 26, 0),
(142, 63, 29, 0),
(143, 63, 27, 0),
(144, 63, 28, 0),
(145, 64, 26, 0),
(146, 64, 29, 0),
(147, 64, 27, 0),
(148, 64, 28, 0),
(149, 65, 26, 0),
(150, 65, 29, 0),
(151, 65, 27, 0),
(152, 65, 28, 0),
(153, 66, 30, 0),
(154, 66, 31, 0),
(155, 66, 33, 0),
(156, 66, 32, 0),
(157, 66, 34, 0),
(158, 67, 41, 0),
(159, 67, 39, 0),
(160, 67, 42, 0),
(161, 67, 40, 0),
(162, 68, 26, 0),
(163, 68, 29, 0),
(164, 68, 27, 0),
(165, 68, 28, 0),
(166, 69, 26, 0),
(167, 69, 29, 0),
(168, 69, 27, 0),
(169, 69, 28, 0);

-- --------------------------------------------------------

--
-- Table structure for table `t19_question`
--

CREATE TABLE `t19_question` (
  `T19_id` bigint(20) NOT NULL,
  `T18_id_fk` bigint(20) NOT NULL,
  `question_no` int(11) NOT NULL,
  `sub_question_no` varchar(2) NOT NULL,
  `question_mark` double NOT NULL,
  `T10_id_fk` bigint(20) NOT NULL,
  `T09_id_fk` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t19_question`
--

INSERT INTO `t19_question` (`T19_id`, `T18_id_fk`, `question_no`, `sub_question_no`, `question_mark`, `T10_id_fk`, `T09_id_fk`) VALUES
(23, 101, 1, 'a', 10, 138, 22),
(24, 101, 1, 'b', 10, 140, 24),
(25, 101, 1, 'c', 5, 139, 23),
(26, 101, 2, 'a', 5, 144, 27),
(27, 101, 3, 'a', 10, 143, 24),
(28, 101, 2, 'b', 10, 142, 27),
(29, 102, 1, 'a', 3, 142, 26),
(30, 102, 1, 'b', 7, 140, 24),
(31, 102, 2, 'a', 4, 138, 22),
(32, 102, 2, 'b', 6, 142, 27),
(33, 107, 1, 'a', 20, 222, 28),
(34, 111, 1, 'a', 10, 219, 30),
(35, 111, 2, 'a', 10, 220, 32),
(36, 108, 1, 'a', 10, 222, 30),
(37, 108, 2, 'a', 5, 222, 31),
(38, 108, 2, 'b', 5, 220, 32),
(39, 108, 3, 'a', 5, 221, 29),
(40, 108, 3, 'b', 5, 221, 31),
(41, 110, 1, 'a', 10, 223, 31),
(42, 110, 1, 'b', 10, 223, 32),
(43, 110, 2, 'a', 10, 223, 33),
(44, 110, 2, 'b', 5, 225, 33),
(45, 110, 3, 'a', 15, 225, 28),
(46, 109, 1, 'a', 10, 224, 28),
(47, 109, 1, 'b', 10, 224, 30),
(48, 109, 2, 'a', 10, 224, 33),
(49, 109, 2, 'b', 10, 226, 28),
(50, 109, 3, 'a', 10, 226, 30),
(51, 109, 3, 'b', 10, 226, 32),
(52, 109, 4, 'a', 5, 226, 33),
(53, 109, 4, 'b', 5, 228, 30),
(54, 109, 4, 'c', 5, 228, 32),
(55, 109, 4, 'd', 5, 228, 31),
(56, 116, 1, 'a', 10, 193, 28),
(57, 117, 1, 'a', 5, 194, 29),
(58, 117, 2, 'a', 5, 195, 32),
(59, 117, 3, 'a', 3, 203, 29),
(60, 117, 3, 'b', 7, 204, 28),
(61, 119, 1, 'a', 10, 205, 28),
(62, 119, 2, 'a', 10, 206, 29),
(63, 119, 3, 'a', 10, 207, 28),
(64, 118, 1, 'a', 3, 207, 28),
(65, 118, 1, 'b', 7, 208, 31),
(66, 118, 2, 'a', 2, 206, 29),
(67, 118, 2, 'b', 8, 203, 29),
(68, 118, 3, 'a', 4, 194, 29),
(69, 118, 3, 'b', 6, 193, 31),
(70, 118, 4, 'a', 5, 193, 33),
(71, 118, 4, 'b', 5, 205, 28),
(72, 112, 1, 'a', 5, 196, 28),
(73, 113, 1, 'a', 3, 197, 29),
(74, 113, 2, 'a', 2, 198, 33),
(75, 113, 3, 'a', 2, 199, 29),
(76, 113, 4, 'a', 3, 200, 32),
(77, 114, 1, 'a', 5, 200, 32),
(78, 114, 1, 'b', 5, 201, 28),
(79, 114, 1, 'c', 5, 202, 28),
(80, 115, 1, 'a', 7, 202, 29),
(81, 115, 1, 'b', 13, 201, 28),
(82, 121, 1, 'a', 10, 189, 30),
(83, 121, 2, 'a', 10, 191, 29),
(84, 123, 1, 'a', 10, 192, 32),
(85, 123, 2, 'a', 10, 191, 33),
(86, 123, 3, 'a', 10, 190, 30),
(87, 122, 1, 'a', 10, 189, 30),
(88, 122, 2, 'a', 10, 190, 30),
(89, 122, 3, 'a', 10, 192, 32),
(90, 122, 4, 'a', 10, 191, 33),
(91, 120, 1, 'a', 10, 190, 28),
(92, 133, 1, 'a', 10, 180, 29),
(93, 134, 1, 'a', 5, 181, 29),
(94, 134, 1, 'b', 5, 180, 29),
(95, 134, 2, 'a', 5, 180, 29),
(96, 134, 2, 'b', 5, 182, 30),
(97, 136, 1, 'a', 10, 180, 29),
(98, 136, 2, 'a', 10, 181, 29),
(99, 136, 3, 'a', 10, 182, 29),
(100, 135, 1, 'a', 10, 180, 29),
(101, 135, 2, 'a', 10, 181, 29),
(102, 135, 3, 'a', 10, 182, 30),
(103, 135, 4, 'a', 10, 180, 29),
(104, 137, 1, 'a', 10, 190, 28),
(105, 138, 1, 'a', 10, 189, 30),
(106, 138, 1, 'b', 10, 189, 32),
(107, 140, 1, 'a', 10, 190, 28),
(108, 140, 2, 'a', 10, 191, 29),
(109, 140, 3, 'a', 10, 191, 33),
(110, 139, 1, 'a', 10, 191, 33),
(111, 139, 2, 'a', 10, 192, 29),
(112, 139, 3, 'a', 10, 192, 32),
(113, 139, 4, 'a', 5, 190, 33),
(114, 139, 4, 'b', 5, 190, 30),
(115, 124, 1, 'a', 10, 232, 28),
(116, 125, 1, 'a', 10, 229, 28),
(117, 125, 1, 'b', 10, 229, 30),
(118, 127, 1, 'a', 10, 229, 31),
(119, 127, 2, 'a', 10, 230, 31),
(120, 127, 3, 'a', 10, 231, 30),
(121, 126, 1, 'a', 20, 232, 28),
(122, 126, 2, 'a', 20, 229, 31),
(123, 128, 1, 'a', 20, 171, 28),
(124, 132, 1, 'a', 20, 171, 29),
(125, 129, 1, 'a', 10, 172, 30),
(126, 129, 2, 'a', 10, 172, 32),
(127, 129, 3, 'a', 10, 179, 31),
(128, 131, 1, 'a', 25, 173, 29),
(129, 131, 2, 'a', 25, 174, 30),
(130, 130, 1, 'a', 20, 179, 33),
(131, 130, 2, 'a', 20, 179, 32),
(132, 130, 3, 'a', 20, 177, 33),
(133, 130, 4, 'a', 20, 175, 32),
(134, 103, 1, 'a', 5, 155, 28),
(135, 103, 1, 'b', 5, 159, 33),
(136, 141, 1, 'a', 10, 244, 35),
(137, 144, 1, 'a', 5, 243, 34),
(138, 144, 1, 'b', 5, 245, 34),
(139, 144, 2, 'a', 7, 245, 34),
(140, 144, 2, 'b', 3, 244, 35),
(141, 144, 3, 'a', 2, 244, 35),
(142, 144, 3, 'b', 2, 245, 34),
(143, 144, 3, 'c', 6, 244, 35);

-- --------------------------------------------------------

--
-- Table structure for table `t20_student`
--

CREATE TABLE `t20_student` (
  `T20_id` bigint(20) NOT NULL,
  `student_id` varchar(100) NOT NULL,
  `T01_id_fk` bigint(20) NOT NULL,
  `T17_id_fk` bigint(20) NOT NULL,
  `student_name` varchar(150) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `address` varchar(300) DEFAULT NULL,
  `student_status` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t20_student`
--

INSERT INTO `t20_student` (`T20_id`, `student_id`, `T01_id_fk`, `T17_id_fk`, `student_name`, `mobile`, `address`, `student_status`) VALUES
(2, 'UG02-56-20-001', 3, 13, 'Siam', 'XXXX', '', 1),
(3, 'UG02-56-20-002', 3, 13, 'Tahsin', 'XXX', '', 1),
(4, 'UG02-56-20-003', 3, 13, 'Rabbi', 'XXX', '', 0),
(5, 'UG02-59-22-001', 3, 15, 'Farhan Rahat', 'XXXX', '', 1),
(6, 'UG02-59-22-002', 3, 15, 'Atik', 'XXXX', '', 0),
(7, 'UG02-59-22-016', 3, 15, 'Habibunnabee A Rahman', 'XXX', '', 1),
(8, 'UG02-58-21-001', 3, 17, 'Jihad', 'XXX', 'addersdf', 1),
(9, 'UG02-58-21-002', 3, 17, 'Alvy', 'xxxx', 'asdzxc', 1),
(10, 'UG02-58-21-003', 3, 17, 'Muntaha', '1234', '1223', 1),
(11, 'UG02-58-21-004', 3, 17, 'Farjana', 'asd', 'asd', 1),
(12, 'UG02-60-22-001', 3, 18, 'Sithi', 'asd', 'asd', 1),
(16, 'UG02-60-22-002', 3, 18, 'Purno', '1234', '1234', 1),
(17, 'UG02-60-22-003', 3, 18, 'sumona', '4324', 'htghrty', 1),
(18, 'UG02-60-22-004', 3, 18, 'Dola', '5644', 'dgdfgdrg', 1),
(19, 'UG02-60-22-005', 3, 18, 'Anisha', '56456', 'thfthrt', 1),
(20, 'UG02-60-22-006', 3, 18, 'Tanisha', '6757567', 'hrthrthrt', 1),
(21, 'UG02-60-22-007', 3, 18, 'Tonima', '78667', 'thrthrt', 1),
(22, 'UG02-57-20-001', 3, 19, 'Jabed', '686867', 'trhrthy', 1),
(23, 'UG02-57-20-002', 3, 19, 'Mim', 'hrty', 'yrty566', 1),
(24, 'UG02-57-20-003', 3, 19, 'Trisha', 'fghfdh', '546456', 1),
(25, 'UG02-57-20-004', 3, 19, 'Tamim', '3455', 'rthrth', 1),
(26, 'UG02-57-20-005', 3, 19, 'Dipto', '68768', 'fthjrth', 1),
(27, 'UG02-57-20-006', 3, 19, 'Hafsa', '6875657', 'htrfhrth', 1),
(28, 'UG02-57-20-007', 3, 19, 'Parshia', '45654', 'hrfthf', 1),
(29, 'UG02-58-21-005', 3, 17, 'Himal', '6786', 'hjtyhj', 1),
(30, 'UG02-58-21-006', 3, 17, 'Promi', '456456', 'rhfthrfth', 1),
(31, 'UG02-58-21-007', 3, 17, 'Saima', '54674', 'tyutyu', 1),
(32, 'UG03-66-25-001', 3, 20, 'axy', 'asd', 'asd', 1);

-- --------------------------------------------------------

--
-- Table structure for table `t21_student_course_registration`
--

CREATE TABLE `t21_student_course_registration` (
  `T21_id` bigint(20) NOT NULL,
  `T15_id_fk` bigint(20) NOT NULL,
  `T20_id_fk` bigint(20) NOT NULL,
  `registration_status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t21_student_course_registration`
--

INSERT INTO `t21_student_course_registration` (`T21_id`, `T15_id_fk`, `T20_id_fk`, `registration_status`) VALUES
(1, 46, 2, NULL),
(2, 49, 2, NULL),
(3, 46, 3, NULL),
(4, 48, 3, NULL),
(5, 49, 3, NULL),
(7, 47, 3, NULL),
(8, 52, 7, NULL),
(9, 50, 7, NULL),
(10, 51, 5, NULL),
(11, 50, 3, NULL),
(12, 51, 3, NULL),
(14, 52, 2, NULL),
(15, 50, 2, NULL),
(16, 55, 22, NULL),
(17, 58, 22, NULL),
(19, 55, 23, NULL),
(20, 58, 23, NULL),
(21, 55, 24, NULL),
(22, 58, 24, NULL),
(23, 55, 25, NULL),
(24, 58, 25, NULL),
(25, 55, 26, NULL),
(26, 58, 26, NULL),
(27, 55, 27, NULL),
(28, 58, 27, NULL),
(29, 55, 28, NULL),
(30, 58, 28, NULL),
(31, 56, 8, NULL),
(32, 55, 8, NULL),
(33, 56, 9, NULL),
(34, 55, 9, NULL),
(35, 58, 9, NULL),
(36, 55, 10, NULL),
(37, 56, 10, NULL),
(38, 55, 11, NULL),
(39, 56, 11, NULL),
(40, 56, 29, NULL),
(41, 55, 29, NULL),
(42, 55, 30, NULL),
(43, 56, 30, NULL),
(45, 56, 31, NULL),
(46, 55, 31, NULL),
(47, 54, 12, NULL),
(48, 57, 12, NULL),
(49, 54, 16, NULL),
(50, 57, 16, NULL),
(51, 54, 17, NULL),
(52, 57, 17, NULL),
(53, 54, 18, NULL),
(54, 57, 18, NULL),
(55, 54, 19, NULL),
(56, 57, 19, NULL),
(57, 54, 20, NULL),
(58, 57, 20, NULL),
(59, 54, 21, NULL),
(60, 57, 21, NULL),
(61, 57, 24, NULL),
(62, 56, 26, NULL),
(63, 57, 9, NULL),
(64, 59, 9, NULL),
(65, 60, 9, NULL),
(66, 61, 9, NULL),
(67, 62, 9, NULL),
(68, 59, 10, NULL),
(69, 60, 10, NULL),
(70, 61, 10, NULL),
(71, 62, 10, NULL),
(72, 59, 12, NULL),
(73, 60, 12, NULL),
(74, 61, 12, NULL),
(75, 62, 12, NULL),
(76, 59, 22, NULL),
(77, 60, 22, NULL),
(78, 62, 22, NULL),
(79, 59, 11, NULL),
(80, 60, 11, NULL),
(81, 62, 11, NULL),
(82, 61, 11, NULL),
(83, 63, 32, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t22_marks_obtained`
--

CREATE TABLE `t22_marks_obtained` (
  `T22_id` bigint(20) NOT NULL,
  `T19_id_fk` bigint(20) NOT NULL,
  `T20_id_fk` bigint(20) NOT NULL,
  `marks` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t22_marks_obtained`
--

INSERT INTO `t22_marks_obtained` (`T22_id`, `T19_id_fk`, `T20_id_fk`, `marks`) VALUES
(13, 33, 26, 20),
(14, 33, 27, 13),
(15, 33, 28, 15),
(16, 33, 8, 16),
(17, 33, 9, 12),
(18, 33, 10, 19),
(19, 33, 11, 18),
(20, 33, 22, 20),
(21, 33, 23, 12),
(22, 33, 24, 10),
(23, 33, 25, 12),
(24, 33, 29, 14),
(25, 33, 30, 6),
(26, 33, 31, 5),
(27, 34, 23, 7),
(28, 35, 23, 4),
(29, 34, 24, 7),
(30, 35, 24, 5),
(31, 35, 25, 4),
(32, 34, 25, 5),
(33, 35, 26, 8),
(34, 34, 26, 8),
(35, 34, 27, 7),
(36, 35, 27, 5),
(37, 34, 28, 5),
(38, 35, 28, 3),
(39, 34, 8, 10),
(40, 35, 8, 10),
(41, 35, 9, 7.5),
(42, 34, 9, 7),
(43, 35, 10, 8),
(44, 34, 10, 5),
(45, 34, 11, 7),
(46, 35, 11, 3),
(47, 34, 22, 9),
(48, 35, 22, 10),
(49, 35, 29, -1),
(50, 34, 29, -1),
(51, 34, 30, 5),
(52, 35, 30, 6),
(53, 34, 31, 4),
(54, 35, 31, 4),
(55, 38, 23, 3),
(56, 39, 23, 3),
(57, 40, 23, 4),
(58, 36, 23, 7),
(59, 37, 23, 2.5),
(60, 36, 24, 2),
(61, 37, 24, 2),
(62, 38, 24, 2),
(63, 39, 24, 2),
(64, 40, 24, 2),
(65, 39, 25, 5),
(66, 40, 25, 5),
(67, 36, 25, 5),
(68, 37, 25, 5),
(69, 38, 25, 5),
(70, 37, 26, 4),
(71, 38, 26, 4),
(72, 39, 26, 4),
(73, 40, 26, 4),
(74, 36, 26, 7),
(75, 39, 27, 3),
(76, 40, 27, 3),
(77, 36, 27, 8),
(78, 37, 27, 2),
(79, 38, 27, 2),
(80, 36, 28, 10),
(81, 37, 28, 5),
(82, 38, 28, 5),
(83, 39, 28, 5),
(84, 40, 28, 5),
(85, 39, 8, 2),
(86, 40, 8, 2),
(87, 36, 8, 8),
(88, 37, 8, 3),
(89, 38, 8, 3),
(90, 37, 9, 4),
(91, 38, 9, 3),
(92, 39, 9, 3),
(93, 40, 9, 5),
(94, 36, 9, 9),
(95, 40, 10, 4),
(96, 36, 10, 8),
(97, 37, 10, 4),
(98, 38, 10, 4),
(99, 39, 10, 4.5),
(100, 37, 11, 1),
(101, 38, 11, 3),
(102, 39, 11, 3),
(103, 40, 11, 3.5),
(104, 36, 11, 5),
(105, 36, 22, 10),
(106, 37, 22, 5),
(107, 38, 22, 3.5),
(108, 39, 22, 5),
(109, 40, 22, 4),
(110, 40, 29, -1),
(111, 36, 29, -1),
(112, 37, 29, -1),
(113, 38, 29, -1),
(114, 39, 29, -1),
(115, 36, 30, 10),
(116, 37, 30, 5),
(117, 38, 30, 5),
(118, 39, 30, 2),
(119, 40, 30, 0),
(120, 38, 31, 5),
(121, 39, 31, 5),
(122, 40, 31, 2),
(123, 36, 31, 0),
(124, 37, 31, 5),
(125, 44, 23, 4),
(126, 45, 23, 12),
(127, 41, 23, 7.5),
(128, 42, 23, 8),
(129, 43, 23, 8),
(130, 42, 24, 10),
(131, 43, 24, 8),
(132, 44, 24, 5),
(133, 45, 24, 12),
(134, 41, 24, 10),
(135, 45, 25, 11),
(136, 41, 25, 7),
(137, 42, 25, 5),
(138, 43, 25, 9),
(139, 44, 25, 2),
(140, 43, 26, 9),
(141, 44, 26, 2),
(142, 45, 26, 12),
(143, 41, 26, 8),
(144, 42, 26, 5),
(145, 41, 27, 9),
(146, 42, 27, 8),
(147, 43, 27, 8),
(148, 44, 27, 2),
(149, 45, 27, 15),
(150, 43, 28, 7),
(151, 44, 28, 2),
(152, 45, 28, 5),
(153, 41, 28, 7),
(154, 42, 28, 8),
(155, 45, 8, 9),
(156, 41, 8, 7),
(157, 42, 8, 7),
(158, 43, 8, 5),
(159, 44, 8, 3),
(160, 43, 9, 10),
(161, 44, 9, 4),
(162, 45, 9, 11),
(163, 41, 9, 7),
(164, 42, 9, 6),
(165, 41, 10, 7),
(166, 42, 10, 8),
(167, 43, 10, 9),
(168, 44, 10, 4),
(169, 45, 10, 11),
(170, 43, 11, 8),
(171, 44, 11, 3),
(172, 45, 11, 9),
(173, 41, 11, 6.5),
(174, 42, 11, 5),
(175, 42, 22, 9),
(176, 43, 22, 7),
(177, 44, 22, 5),
(178, 45, 22, 14),
(179, 41, 22, 9),
(180, 41, 29, -1),
(181, 42, 29, -1),
(182, 43, 29, -1),
(183, 44, 29, -1),
(184, 45, 29, -1),
(185, 43, 30, 10),
(186, 44, 30, 3),
(187, 45, 30, 8),
(188, 41, 30, 2),
(189, 42, 30, 5),
(190, 45, 31, 8),
(191, 41, 31, 5),
(192, 42, 31, 5),
(193, 43, 31, 8),
(194, 44, 31, 3),
(195, 52, 23, 4.5),
(196, 51, 23, 8),
(197, 47, 23, 9),
(198, 54, 23, 4),
(199, 53, 23, 3.5),
(200, 48, 23, 8),
(201, 46, 23, 8),
(202, 55, 23, 3),
(203, 50, 23, 7),
(204, 49, 23, 7),
(205, 46, 24, 5),
(206, 51, 24, 5),
(207, 50, 24, 5),
(208, 48, 24, 8),
(209, 47, 24, 4),
(210, 52, 24, 5),
(211, 49, 24, 10),
(212, 55, 24, 5),
(213, 54, 24, 4),
(214, 53, 24, 4),
(215, 55, 25, 4),
(216, 54, 25, 4),
(217, 51, 25, 4),
(218, 52, 25, 5),
(219, 50, 25, 5),
(220, 53, 25, 4),
(221, 49, 25, 10),
(222, 48, 25, 8),
(223, 47, 25, 4),
(224, 46, 25, 7),
(225, 52, 26, 4),
(226, 50, 26, 8),
(227, 51, 26, 4),
(228, 48, 26, 7),
(229, 55, 26, 5),
(230, 49, 26, 7),
(231, 46, 26, 7),
(232, 47, 26, 4),
(233, 54, 26, 3),
(234, 53, 26, 4),
(235, 52, 27, 4),
(236, 53, 27, 5),
(237, 50, 27, 8),
(238, 49, 27, 7),
(239, 54, 27, 1),
(240, 48, 27, 7),
(241, 47, 27, 4),
(242, 46, 27, 8),
(243, 55, 27, 5),
(244, 51, 27, 10),
(245, 46, 28, 8),
(246, 54, 28, 2),
(247, 53, 28, 5),
(248, 48, 28, 6),
(249, 47, 28, 10),
(250, 51, 28, 9),
(251, 50, 28, 9),
(252, 49, 28, 8),
(253, 55, 28, 5),
(254, 52, 28, 4),
(255, 55, 8, 4),
(256, 54, 8, 5),
(257, 49, 8, 8),
(258, 48, 8, 6),
(259, 46, 8, 8),
(260, 51, 8, 4.5),
(261, 50, 8, 9),
(262, 47, 8, 1),
(263, 53, 8, 5),
(264, 52, 8, 4),
(265, 54, 9, 4),
(266, 53, 9, 4),
(267, 50, 9, 5),
(268, 51, 9, 2),
(269, 48, 9, 9),
(270, 52, 9, 4),
(271, 49, 9, 5),
(272, 47, 9, 9),
(273, 46, 9, 9),
(274, 55, 9, 3.5),
(275, 49, 10, 5),
(276, 46, 10, 8),
(277, 47, 10, 9),
(278, 54, 10, 3),
(279, 51, 10, 8),
(280, 55, 10, 3),
(281, 52, 10, 4),
(282, 53, 10, 3),
(283, 50, 10, 7),
(284, 48, 10, 6),
(285, 48, 11, 4),
(286, 49, 11, 4),
(287, 46, 11, 8),
(288, 53, 11, 4),
(289, 51, 11, 0),
(290, 54, 11, 5),
(291, 55, 11, 3),
(292, 52, 11, 1),
(293, 50, 11, 5),
(294, 47, 11, 8),
(295, 46, 22, 9),
(296, 54, 22, 4),
(297, 51, 22, 8),
(298, 48, 22, 7.5),
(299, 47, 22, 10),
(300, 53, 22, 4),
(301, 50, 22, 9),
(302, 49, 22, 8),
(303, 55, 22, 3.5),
(304, 52, 22, 5),
(305, 46, 29, -1),
(306, 52, 29, -1),
(307, 51, 29, -1),
(308, 48, 29, -1),
(309, 49, 29, -1),
(310, 53, 29, -1),
(311, 50, 29, -1),
(312, 47, 29, -1),
(313, 54, 29, -1),
(314, 55, 29, -1),
(315, 53, 30, 0),
(316, 50, 30, 2),
(317, 47, 30, 5),
(318, 48, 30, 5),
(319, 55, 30, 2),
(320, 49, 30, 8),
(321, 46, 30, 8),
(322, 54, 30, 0),
(323, 51, 30, 0),
(324, 52, 30, 0),
(325, 55, 31, 5),
(326, 52, 31, 5),
(327, 53, 31, 1),
(328, 50, 31, 2),
(329, 49, 31, 7),
(330, 51, 31, 1),
(331, 48, 31, 10),
(332, 47, 31, 5),
(333, 46, 31, 8),
(334, 54, 31, 5),
(335, 56, 21, 10),
(336, 56, 24, 10),
(337, 56, 9, 8),
(338, 56, 12, 9),
(339, 56, 16, 2),
(340, 56, 17, 5),
(341, 56, 18, 3),
(342, 56, 19, 7),
(343, 56, 20, 8),
(344, 59, 21, 2),
(345, 57, 21, 5),
(346, 58, 21, 2.5),
(347, 60, 21, 3),
(348, 58, 24, 5),
(349, 60, 24, 7),
(350, 57, 24, 4),
(351, 59, 24, 2.5),
(352, 58, 9, 3),
(353, 60, 9, 5.5),
(354, 57, 9, 4),
(355, 59, 9, 2.5),
(356, 59, 12, 3),
(357, 57, 12, 3),
(358, 58, 12, 2),
(359, 60, 12, 6),
(360, 60, 16, 6),
(361, 58, 16, 5),
(362, 59, 16, 3),
(363, 57, 16, 5),
(364, 57, 17, 2),
(365, 59, 17, 3),
(366, 60, 17, 6),
(367, 58, 17, 3),
(368, 58, 18, 2),
(369, 60, 18, 6),
(370, 57, 18, 4),
(371, 59, 18, 2),
(372, 58, 19, 4),
(373, 60, 19, 6),
(374, 57, 19, 2),
(375, 59, 19, 2),
(376, 58, 20, 3.5),
(377, 60, 20, 6),
(378, 57, 20, 1),
(379, 59, 20, 2),
(380, 61, 24, -1),
(381, 62, 24, -1),
(382, 63, 24, -1),
(383, 61, 9, 7),
(384, 62, 9, 8),
(385, 63, 9, 9),
(386, 61, 12, 4),
(387, 62, 12, 5),
(388, 63, 12, 6),
(389, 61, 16, 2),
(390, 62, 16, 2),
(391, 63, 16, 2),
(392, 61, 17, 5),
(393, 62, 17, 5),
(394, 63, 17, 7),
(395, 61, 18, 10),
(396, 62, 18, 10),
(397, 63, 18, 4),
(398, 61, 19, 8),
(399, 62, 19, 8.5),
(400, 63, 19, 4),
(401, 61, 20, 5),
(402, 62, 20, 6),
(403, 63, 20, 4),
(404, 61, 21, 4),
(405, 62, 21, 5),
(406, 63, 21, 10),
(407, 69, 21, 5),
(408, 64, 21, 1),
(409, 67, 21, 2),
(410, 71, 21, 2),
(411, 66, 21, 2),
(412, 70, 21, 2),
(413, 65, 21, 1),
(414, 68, 21, 2),
(415, 67, 24, -1),
(416, 70, 24, -1),
(417, 65, 24, -1),
(418, 69, 24, -1),
(419, 64, 24, -1),
(420, 68, 24, -1),
(421, 71, 24, -1),
(422, 66, 24, -1),
(423, 68, 9, 3),
(424, 71, 9, 3.5),
(425, 66, 9, 2),
(426, 70, 9, 3),
(427, 65, 9, 6),
(428, 69, 9, 5),
(429, 64, 9, 2),
(430, 67, 9, 6),
(431, 69, 12, 5),
(432, 64, 12, 2),
(433, 67, 12, 6),
(434, 71, 12, 5),
(435, 66, 12, 2),
(436, 70, 12, 4),
(437, 65, 12, 5),
(438, 68, 12, 4),
(439, 70, 16, -1),
(440, 65, 16, -1),
(441, 68, 16, -1),
(442, 64, 16, -1),
(443, 67, 16, -1),
(444, 71, 16, -1),
(445, 66, 16, -1),
(446, 69, 16, -1),
(447, 64, 17, 3),
(448, 67, 17, 5),
(449, 70, 17, 4),
(450, 66, 17, 2),
(451, 69, 17, 4),
(452, 65, 17, 4),
(453, 68, 17, 4),
(454, 71, 17, 4),
(455, 66, 18, 1),
(456, 69, 18, 3),
(457, 64, 18, 2),
(458, 68, 18, 3),
(459, 71, 18, 2),
(460, 67, 18, 6),
(461, 70, 18, 3),
(462, 65, 18, 3),
(463, 66, 19, 2),
(464, 69, 19, 4),
(465, 64, 19, 2),
(466, 68, 19, 4),
(467, 71, 19, 4),
(468, 67, 19, 4),
(469, 70, 19, 4),
(470, 65, 19, 5),
(471, 67, 20, 0),
(472, 70, 20, 0),
(473, 65, 20, 0),
(474, 69, 20, 4),
(475, 64, 20, 0),
(476, 68, 20, 4),
(477, 71, 20, 1),
(478, 66, 20, 0),
(479, 72, 26, 3),
(480, 72, 8, 3),
(481, 72, 9, 5),
(482, 72, 10, 5),
(483, 72, 11, 5),
(484, 72, 29, 5),
(485, 72, 30, 3),
(486, 72, 31, 3),
(519, 77, 26, 3),
(520, 78, 26, 5),
(521, 79, 26, 0),
(522, 77, 8, 3),
(523, 78, 8, 5),
(524, 79, 8, 0),
(525, 77, 9, 3.5),
(526, 78, 9, 5),
(527, 79, 9, 3),
(528, 77, 10, 5),
(529, 78, 10, 5),
(530, 79, 10, 4),
(531, 77, 11, 2),
(532, 78, 11, 5),
(533, 79, 11, 5),
(534, 77, 29, 1),
(535, 78, 29, 3),
(536, 79, 29, 3),
(537, 77, 30, 3),
(538, 78, 30, 3),
(539, 79, 30, 4),
(540, 77, 31, 2),
(541, 78, 31, 2),
(542, 79, 31, 4),
(543, 80, 26, 0),
(544, 81, 26, 5),
(545, 80, 8, 4),
(546, 81, 8, 1),
(547, 80, 9, 7),
(548, 81, 9, 13),
(549, 80, 10, 3),
(550, 81, 10, 3),
(551, 80, 11, 3),
(552, 81, 11, 10),
(553, 80, 29, 7),
(554, 81, 29, 13),
(555, 80, 30, 0),
(556, 81, 30, 1),
(557, 80, 31, -1),
(558, 81, 31, -1),
(559, 73, 11, 1),
(560, 74, 11, 1),
(561, 75, 11, 1),
(562, 76, 11, 1),
(563, 74, 26, 2),
(564, 75, 26, 2),
(565, 76, 26, 1),
(566, 73, 26, 3),
(567, 75, 8, 1),
(568, 76, 8, 1),
(569, 73, 8, 2),
(570, 74, 8, 2),
(571, 75, 9, 2),
(572, 76, 9, 2.5),
(573, 73, 9, 3),
(574, 74, 9, 2),
(575, 76, 10, 2),
(576, 73, 10, 2),
(577, 74, 10, 2),
(578, 75, 10, 2),
(579, 73, 29, 0),
(580, 74, 29, 0),
(581, 75, 29, 1),
(582, 76, 29, 1),
(583, 74, 30, 2),
(584, 75, 30, 2),
(585, 76, 30, 2),
(586, 73, 30, 2),
(587, 75, 31, 2),
(588, 76, 31, 2),
(589, 73, 31, 2),
(590, 74, 31, 2),
(591, 91, 22, 10),
(592, 91, 23, 4),
(593, 91, 24, 4),
(594, 91, 25, 4),
(595, 91, 26, 4),
(596, 91, 27, 4),
(597, 91, 28, 2),
(598, 91, 9, 10),
(599, 82, 22, 4),
(600, 83, 22, 4),
(601, 82, 23, 4),
(602, 83, 23, 10),
(603, 82, 24, 10),
(604, 83, 24, 10),
(605, 82, 25, 7),
(606, 83, 25, 7),
(607, 82, 26, 6),
(608, 83, 26, 8),
(609, 82, 27, 4),
(610, 83, 27, 7),
(611, 82, 28, 4),
(612, 83, 28, 7),
(613, 82, 9, 5),
(614, 83, 9, 2),
(615, 84, 22, 7),
(616, 85, 22, 5),
(617, 86, 22, 6),
(618, 84, 23, 8),
(619, 85, 23, 6),
(620, 86, 23, 5),
(621, 84, 24, 6),
(622, 85, 24, 4),
(623, 86, 24, 4),
(624, 84, 25, 5),
(625, 85, 25, 3),
(626, 86, 25, 7),
(627, 84, 26, 7),
(628, 85, 26, 4),
(629, 86, 26, 9),
(630, 84, 27, 5),
(631, 85, 27, 7),
(632, 86, 27, 9),
(633, 84, 28, 5),
(634, 85, 28, 6),
(635, 86, 28, 9),
(636, 84, 9, 4),
(637, 85, 9, 5),
(638, 86, 9, 5),
(639, 87, 22, 7),
(640, 88, 22, 3),
(641, 89, 22, 2),
(642, 90, 22, 5),
(643, 87, 23, 5),
(644, 88, 23, 5),
(645, 89, 23, 4),
(646, 90, 23, 5),
(647, 87, 24, 7),
(648, 88, 24, 4),
(649, 89, 24, 6),
(650, 90, 24, 7),
(651, 87, 25, 4),
(652, 88, 25, 8),
(653, 89, 25, 4),
(654, 90, 25, 6),
(655, 87, 26, 3),
(656, 88, 26, 7),
(657, 89, 26, 6),
(658, 90, 26, 8),
(659, 87, 27, 6),
(660, 88, 27, 7),
(661, 89, 27, 4),
(662, 90, 27, 4),
(663, 87, 28, 5),
(664, 88, 28, 7),
(665, 89, 28, 7),
(666, 90, 28, 8),
(667, 87, 9, 5),
(668, 88, 9, 6),
(669, 89, 9, 5),
(670, 90, 9, 4),
(671, 92, 9, 0),
(672, 92, 10, 5),
(673, 92, 11, 8),
(674, 92, 12, 8),
(675, 93, 9, 2),
(676, 94, 9, 2),
(677, 95, 9, 2),
(678, 96, 9, 1),
(679, 93, 10, 5),
(680, 94, 10, 5),
(681, 95, 10, 5),
(682, 96, 10, 5),
(683, 93, 11, 4),
(684, 94, 11, 4),
(685, 95, 11, 4),
(686, 96, 11, 4),
(687, 93, 12, 5),
(688, 94, 12, 5),
(689, 95, 12, 5),
(690, 96, 12, 5),
(691, 97, 9, 8),
(692, 98, 9, 2),
(693, 99, 9, 0),
(694, 97, 10, 5),
(695, 98, 10, 8),
(696, 99, 10, 10),
(697, 97, 11, -1),
(698, 98, 11, -1),
(699, 99, 11, -1),
(700, 97, 12, 5),
(701, 98, 12, 7),
(702, 99, 12, 4),
(703, 100, 9, 8),
(704, 101, 9, 0),
(705, 102, 9, 0),
(706, 103, 9, 0),
(707, 100, 10, 5),
(708, 101, 10, 8),
(709, 102, 10, 4),
(710, 103, 10, 10),
(711, 100, 11, -1),
(712, 101, 11, -1),
(713, 102, 11, -1),
(714, 103, 11, -1),
(715, 100, 12, 10),
(716, 101, 12, 8),
(717, 102, 12, 10),
(718, 103, 12, 10),
(719, 104, 22, 8),
(720, 104, 9, 10),
(721, 104, 10, -1),
(722, 104, 11, -1),
(723, 104, 12, -1),
(724, 105, 22, 8),
(725, 106, 22, 5),
(726, 105, 9, 8),
(727, 106, 9, 8),
(728, 105, 10, -1),
(729, 106, 10, -1),
(730, 105, 11, -1),
(731, 106, 11, -1),
(732, 105, 12, -1),
(733, 106, 12, -1),
(734, 107, 22, 7),
(735, 108, 22, 5),
(736, 109, 22, 4),
(737, 107, 9, 10),
(738, 108, 9, 10),
(739, 109, 9, 8),
(740, 107, 10, -1),
(741, 108, 10, -1),
(742, 109, 10, -1),
(743, 107, 11, -1),
(744, 108, 11, -1),
(745, 109, 11, -1),
(746, 107, 12, -1),
(747, 108, 12, -1),
(748, 109, 12, -1),
(749, 110, 22, 8),
(750, 111, 22, 8),
(751, 112, 22, 7),
(752, 113, 22, 4),
(753, 114, 22, 4),
(754, 110, 9, 8),
(755, 111, 9, 10),
(756, 112, 9, 10),
(757, 113, 9, 5),
(758, 114, 9, 5),
(759, 110, 10, -1),
(760, 111, 10, -1),
(761, 112, 10, -1),
(762, 113, 10, -1),
(763, 114, 10, -1),
(764, 110, 11, -1),
(765, 111, 11, -1),
(766, 112, 11, -1),
(767, 113, 11, -1),
(768, 114, 11, -1),
(769, 110, 12, -1),
(770, 111, 12, -1),
(771, 112, 12, -1),
(772, 113, 12, -1),
(773, 114, 12, -1),
(774, 115, 22, 10),
(775, 115, 9, 8),
(776, 115, 10, -1),
(777, 115, 11, -1),
(778, 115, 12, 10),
(779, 116, 22, 8),
(780, 117, 22, 8),
(781, 116, 9, 7),
(782, 117, 9, 6.5),
(783, 116, 10, -1),
(784, 117, 10, -1),
(785, 116, 11, -1),
(786, 117, 11, -1),
(787, 116, 12, 8),
(788, 117, 12, 8),
(789, 118, 22, 8),
(790, 119, 22, 10),
(791, 120, 22, 9),
(792, 118, 9, 7),
(793, 119, 9, 7),
(794, 120, 9, 10),
(795, 118, 10, -1),
(796, 119, 10, -1),
(797, 120, 10, -1),
(798, 118, 11, -1),
(799, 119, 11, -1),
(800, 120, 11, -1),
(801, 118, 12, 5),
(802, 119, 12, 5),
(803, 120, 12, 5),
(804, 121, 22, 8),
(805, 122, 22, 8),
(806, 121, 9, 15),
(807, 122, 9, 17),
(808, 121, 10, -1),
(809, 122, 10, -1),
(810, 121, 11, -1),
(811, 122, 11, -1),
(812, 121, 12, 8),
(813, 122, 12, 20),
(814, 123, 22, 10),
(815, 123, 9, 10),
(816, 123, 10, 10),
(817, 123, 11, 10),
(818, 123, 12, 12),
(819, 124, 22, 12),
(820, 124, 9, 9),
(821, 124, 10, 20),
(822, 124, 11, 20),
(823, 124, 12, 18),
(824, 125, 22, 8),
(825, 126, 22, 8),
(826, 127, 22, 8),
(827, 125, 9, 7),
(828, 126, 9, 4),
(829, 127, 9, 1),
(830, 125, 10, 8),
(831, 126, 10, 8),
(832, 127, 10, 1),
(833, 125, 11, 1),
(834, 126, 11, 10),
(835, 127, 11, 10),
(836, 125, 12, 9),
(837, 126, 12, 9),
(838, 127, 12, 10),
(839, 128, 22, 20),
(840, 129, 22, 20),
(841, 128, 9, 12),
(842, 129, 9, 8),
(843, 128, 10, 20),
(844, 129, 10, 20),
(845, 128, 11, 20),
(846, 129, 11, 20),
(847, 128, 12, 10),
(848, 129, 12, 15),
(849, 130, 22, 20),
(850, 131, 22, 20),
(851, 132, 22, 20),
(852, 133, 22, 18),
(853, 130, 9, 10),
(854, 131, 9, 8),
(855, 132, 9, 9),
(856, 133, 9, 7),
(857, 130, 10, 5),
(858, 131, 10, 5),
(859, 132, 10, 20),
(860, 133, 10, 20),
(861, 130, 11, -1),
(862, 131, 11, -1),
(863, 132, 11, -1),
(864, 133, 11, -1),
(865, 130, 12, -1),
(866, 131, 12, -1),
(867, 132, 12, -1),
(868, 133, 12, -1),
(869, 134, 12, 5),
(870, 135, 12, 2),
(871, 134, 16, -1),
(872, 135, 16, -1),
(873, 134, 17, 5),
(874, 135, 17, 3),
(875, 134, 18, 4),
(876, 135, 18, 4),
(877, 134, 19, -1),
(878, 135, 19, -1),
(879, 134, 20, 2),
(880, 135, 20, 3),
(881, 134, 21, 5),
(882, 135, 21, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_university_id` (`university_id`);

--
-- Indexes for table `employee_login`
--
ALTER TABLE `employee_login`
  ADD PRIMARY KEY (`T16_id_fk`),
  ADD UNIQUE KEY `ID` (`ID`,`university_id`),
  ADD KEY `FK_university_id2` (`university_id`);

--
-- Indexes for table `student_login`
--
ALTER TABLE `student_login`
  ADD PRIMARY KEY (`ID`,`university_id`),
  ADD KEY `FK_university_id1` (`university_id`),
  ADD KEY `fk_T20` (`T20_id_fk`);

--
-- Indexes for table `t01_university`
--
ALTER TABLE `t01_university`
  ADD PRIMARY KEY (`T01_id`),
  ADD UNIQUE KEY `university` (`university_id`);

--
-- Indexes for table `t02_job`
--
ALTER TABLE `t02_job`
  ADD PRIMARY KEY (`T02_id`),
  ADD UNIQUE KEY `job` (`job_id`);

--
-- Indexes for table `t03_school`
--
ALTER TABLE `t03_school`
  ADD PRIMARY KEY (`T03_id`),
  ADD UNIQUE KEY `school` (`school_id`,`T01_id_fk`),
  ADD KEY `fk_uni_idT03` (`T01_id_fk`) USING BTREE;

--
-- Indexes for table `t04_department`
--
ALTER TABLE `t04_department`
  ADD PRIMARY KEY (`T04_id`),
  ADD UNIQUE KEY `department` (`department_id`,`T03_id_fk`),
  ADD KEY `fk_school_idT04` (`T03_id_fk`);

--
-- Indexes for table `t05_program`
--
ALTER TABLE `t05_program`
  ADD PRIMARY KEY (`T05_id`),
  ADD UNIQUE KEY `program` (`program_id`,`T04_id_fk`),
  ADD KEY `fk_department_id_T05` (`T04_id_fk`);

--
-- Indexes for table `t06_syllabus`
--
ALTER TABLE `t06_syllabus`
  ADD PRIMARY KEY (`T06_id`),
  ADD UNIQUE KEY `syllabus` (`syllabus_id`,`T05_id_fk`),
  ADD KEY `fk_program_T06` (`T05_id_fk`),
  ADD KEY `fk_default_evaluation_set_T06` (`default_T08_id_fk`);

--
-- Indexes for table `t07_course`
--
ALTER TABLE `t07_course`
  ADD PRIMARY KEY (`T07_id`),
  ADD UNIQUE KEY `course` (`course_id`,`T06_id_fk`),
  ADD KEY `fk_syllabus_T07` (`T06_id_fk`);

--
-- Indexes for table `t08_evaluation_set`
--
ALTER TABLE `t08_evaluation_set`
  ADD PRIMARY KEY (`T08_id`),
  ADD UNIQUE KEY `evaluation_set` (`evaluation_set_id`,`T01_id_fk`),
  ADD KEY `fk_university_T08` (`T01_id_fk`);

--
-- Indexes for table `t09_syllabus_plo`
--
ALTER TABLE `t09_syllabus_plo`
  ADD PRIMARY KEY (`T09_id`),
  ADD UNIQUE KEY `plo` (`plo_id`,`T06_id_fk`) USING BTREE,
  ADD KEY `fk_syllabus_T09` (`T06_id_fk`);

--
-- Indexes for table `t10_course_clo`
--
ALTER TABLE `t10_course_clo`
  ADD PRIMARY KEY (`T10_id`),
  ADD UNIQUE KEY `clo` (`T07_id_fk`,`clo_number`,`T15_id_fk`) USING BTREE,
  ADD KEY `fk_offered_course_T10` (`T15_id_fk`);

--
-- Indexes for table `t11_evaluation`
--
ALTER TABLE `t11_evaluation`
  ADD PRIMARY KEY (`T11_id`),
  ADD UNIQUE KEY `evaluation` (`T08_id_fk`,`evaluation_name`);

--
-- Indexes for table `t12_unique_course_evaluation`
--
ALTER TABLE `t12_unique_course_evaluation`
  ADD PRIMARY KEY (`T12_id`),
  ADD UNIQUE KEY `unique_course_evaluation` (`T07_id_fk`) USING BTREE,
  ADD KEY `fk_evaluation_set_T12` (`T08_id_fk`);

--
-- Indexes for table `t13_clo_plo_connect`
--
ALTER TABLE `t13_clo_plo_connect`
  ADD PRIMARY KEY (`T13_id`),
  ADD UNIQUE KEY `clo_plo_connect` (`T10_id_fk`,`T09_id_fk`),
  ADD KEY `fk_plo_T13` (`T09_id_fk`);

--
-- Indexes for table `t14_semester`
--
ALTER TABLE `t14_semester`
  ADD PRIMARY KEY (`T14_id`),
  ADD UNIQUE KEY `semester` (`semester_id`,`T06_id_fk`),
  ADD KEY `fk_syllabus_T14` (`T06_id_fk`);

--
-- Indexes for table `t15_offered_course`
--
ALTER TABLE `t15_offered_course`
  ADD PRIMARY KEY (`T15_id`),
  ADD UNIQUE KEY `offer_course` (`T07_id_fk`,`T14_id_fk`),
  ADD KEY `fk_semester_T15` (`T14_id_fk`),
  ADD KEY `fk_teacher_T15` (`T16_id_fk`);

--
-- Indexes for table `t16_employee`
--
ALTER TABLE `t16_employee`
  ADD PRIMARY KEY (`T16_id`),
  ADD UNIQUE KEY `employee` (`employee_id`,`T01_id_fk`),
  ADD KEY `fk_university_T16` (`T01_id_fk`),
  ADD KEY `fk_job_T16` (`T02_id_fk`);

--
-- Indexes for table `t17_batch`
--
ALTER TABLE `t17_batch`
  ADD PRIMARY KEY (`T17_id`),
  ADD UNIQUE KEY `batch` (`batch_id`,`T01_id_fk`) USING BTREE,
  ADD KEY `fk_semester_T17` (`T14_id_fk`),
  ADD KEY `fk_university_T17` (`T01_id_fk`);

--
-- Indexes for table `t18_exam`
--
ALTER TABLE `t18_exam`
  ADD PRIMARY KEY (`T18_id`),
  ADD UNIQUE KEY `exam` (`T15_id_fk`,`T11_id_fk`),
  ADD KEY `fk_evaluation_T18` (`T11_id_fk`);

--
-- Indexes for table `t19_question`
--
ALTER TABLE `t19_question`
  ADD PRIMARY KEY (`T19_id`),
  ADD UNIQUE KEY `question` (`T18_id_fk`,`question_no`,`sub_question_no`),
  ADD KEY `fk_clo_T19` (`T10_id_fk`),
  ADD KEY `fk_plo_T19` (`T09_id_fk`);

--
-- Indexes for table `t20_student`
--
ALTER TABLE `t20_student`
  ADD PRIMARY KEY (`T20_id`),
  ADD UNIQUE KEY `student` (`student_id`,`T01_id_fk`),
  ADD KEY `fk_university_T20` (`T01_id_fk`),
  ADD KEY `fk_batch_T20` (`T17_id_fk`);

--
-- Indexes for table `t21_student_course_registration`
--
ALTER TABLE `t21_student_course_registration`
  ADD PRIMARY KEY (`T21_id`),
  ADD UNIQUE KEY `registration` (`T15_id_fk`,`T20_id_fk`) USING BTREE,
  ADD KEY `fk_student_T21` (`T20_id_fk`);

--
-- Indexes for table `t22_marks_obtained`
--
ALTER TABLE `t22_marks_obtained`
  ADD PRIMARY KEY (`T22_id`),
  ADD UNIQUE KEY `student-question` (`T19_id_fk`,`T20_id_fk`),
  ADD KEY `fk_student_T22` (`T20_id_fk`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t01_university`
--
ALTER TABLE `t01_university`
  MODIFY `T01_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `t02_job`
--
ALTER TABLE `t02_job`
  MODIFY `T02_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `t03_school`
--
ALTER TABLE `t03_school`
  MODIFY `T03_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `t04_department`
--
ALTER TABLE `t04_department`
  MODIFY `T04_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `t05_program`
--
ALTER TABLE `t05_program`
  MODIFY `T05_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `t06_syllabus`
--
ALTER TABLE `t06_syllabus`
  MODIFY `T06_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `t07_course`
--
ALTER TABLE `t07_course`
  MODIFY `T07_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT for table `t08_evaluation_set`
--
ALTER TABLE `t08_evaluation_set`
  MODIFY `T08_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `t09_syllabus_plo`
--
ALTER TABLE `t09_syllabus_plo`
  MODIFY `T09_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `t10_course_clo`
--
ALTER TABLE `t10_course_clo`
  MODIFY `T10_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=246;

--
-- AUTO_INCREMENT for table `t11_evaluation`
--
ALTER TABLE `t11_evaluation`
  MODIFY `T11_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `t12_unique_course_evaluation`
--
ALTER TABLE `t12_unique_course_evaluation`
  MODIFY `T12_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `t13_clo_plo_connect`
--
ALTER TABLE `t13_clo_plo_connect`
  MODIFY `T13_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=396;

--
-- AUTO_INCREMENT for table `t14_semester`
--
ALTER TABLE `t14_semester`
  MODIFY `T14_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `t15_offered_course`
--
ALTER TABLE `t15_offered_course`
  MODIFY `T15_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT for table `t16_employee`
--
ALTER TABLE `t16_employee`
  MODIFY `T16_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `t17_batch`
--
ALTER TABLE `t17_batch`
  MODIFY `T17_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `t18_exam`
--
ALTER TABLE `t18_exam`
  MODIFY `T18_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=170;

--
-- AUTO_INCREMENT for table `t19_question`
--
ALTER TABLE `t19_question`
  MODIFY `T19_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=144;

--
-- AUTO_INCREMENT for table `t20_student`
--
ALTER TABLE `t20_student`
  MODIFY `T20_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `t21_student_course_registration`
--
ALTER TABLE `t21_student_course_registration`
  MODIFY `T21_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT for table `t22_marks_obtained`
--
ALTER TABLE `t22_marks_obtained`
  MODIFY `T22_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=883;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `FK_university_id` FOREIGN KEY (`university_id`) REFERENCES `t01_university` (`university_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `employee_login`
--
ALTER TABLE `employee_login`
  ADD CONSTRAINT `fk_T16` FOREIGN KEY (`T16_id_fk`) REFERENCES `t16_employee` (`T16_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_employeeID_empLogin` FOREIGN KEY (`ID`) REFERENCES `t16_employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_universityID_empLogin` FOREIGN KEY (`university_id`) REFERENCES `t01_university` (`university_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student_login`
--
ALTER TABLE `student_login`
  ADD CONSTRAINT `fk_T20` FOREIGN KEY (`T20_id_fk`) REFERENCES `t20_student` (`T20_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_studentID_stuLogin` FOREIGN KEY (`ID`) REFERENCES `t20_student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_universityID_stuLogin` FOREIGN KEY (`university_id`) REFERENCES `t01_university` (`university_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t03_school`
--
ALTER TABLE `t03_school`
  ADD CONSTRAINT `fk_university_T03` FOREIGN KEY (`T01_id_fk`) REFERENCES `t01_university` (`T01_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t04_department`
--
ALTER TABLE `t04_department`
  ADD CONSTRAINT `fk_school_T04` FOREIGN KEY (`T03_id_fk`) REFERENCES `t03_school` (`T03_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t05_program`
--
ALTER TABLE `t05_program`
  ADD CONSTRAINT `fk_department_T05` FOREIGN KEY (`T04_id_fk`) REFERENCES `t04_department` (`T04_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t06_syllabus`
--
ALTER TABLE `t06_syllabus`
  ADD CONSTRAINT `fk_default_evaluation_set_T06` FOREIGN KEY (`default_T08_id_fk`) REFERENCES `t08_evaluation_set` (`T08_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_program_T06` FOREIGN KEY (`T05_id_fk`) REFERENCES `t05_program` (`T05_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t07_course`
--
ALTER TABLE `t07_course`
  ADD CONSTRAINT `fk_syllabus_T07` FOREIGN KEY (`T06_id_fk`) REFERENCES `t06_syllabus` (`T06_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t08_evaluation_set`
--
ALTER TABLE `t08_evaluation_set`
  ADD CONSTRAINT `fk_university_T08` FOREIGN KEY (`T01_id_fk`) REFERENCES `t01_university` (`T01_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t09_syllabus_plo`
--
ALTER TABLE `t09_syllabus_plo`
  ADD CONSTRAINT `fk_syllabus_T09` FOREIGN KEY (`T06_id_fk`) REFERENCES `t06_syllabus` (`T06_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t10_course_clo`
--
ALTER TABLE `t10_course_clo`
  ADD CONSTRAINT `fk_course_T10` FOREIGN KEY (`T07_id_fk`) REFERENCES `t07_course` (`T07_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_offered_course_T10` FOREIGN KEY (`T15_id_fk`) REFERENCES `t15_offered_course` (`T15_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t11_evaluation`
--
ALTER TABLE `t11_evaluation`
  ADD CONSTRAINT `fk_evaluation_set_T11` FOREIGN KEY (`T08_id_fk`) REFERENCES `t08_evaluation_set` (`T08_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t12_unique_course_evaluation`
--
ALTER TABLE `t12_unique_course_evaluation`
  ADD CONSTRAINT `fk_course_T12` FOREIGN KEY (`T07_id_fk`) REFERENCES `t07_course` (`T07_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_evaluation_set_T12` FOREIGN KEY (`T08_id_fk`) REFERENCES `t08_evaluation_set` (`T08_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t13_clo_plo_connect`
--
ALTER TABLE `t13_clo_plo_connect`
  ADD CONSTRAINT `fk_clo_T13` FOREIGN KEY (`T10_id_fk`) REFERENCES `t10_course_clo` (`T10_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_plo_T13` FOREIGN KEY (`T09_id_fk`) REFERENCES `t09_syllabus_plo` (`T09_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t14_semester`
--
ALTER TABLE `t14_semester`
  ADD CONSTRAINT `fk_syllabus_T14` FOREIGN KEY (`T06_id_fk`) REFERENCES `t06_syllabus` (`T06_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t15_offered_course`
--
ALTER TABLE `t15_offered_course`
  ADD CONSTRAINT `fk_course_T15` FOREIGN KEY (`T07_id_fk`) REFERENCES `t07_course` (`T07_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_semester_T15` FOREIGN KEY (`T14_id_fk`) REFERENCES `t14_semester` (`T14_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_teacher_T15` FOREIGN KEY (`T16_id_fk`) REFERENCES `t16_employee` (`T16_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t16_employee`
--
ALTER TABLE `t16_employee`
  ADD CONSTRAINT `fk_job_T16` FOREIGN KEY (`T02_id_fk`) REFERENCES `t02_job` (`T02_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_university_T16` FOREIGN KEY (`T01_id_fk`) REFERENCES `t01_university` (`T01_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t17_batch`
--
ALTER TABLE `t17_batch`
  ADD CONSTRAINT `fk_semester_T17` FOREIGN KEY (`T14_id_fk`) REFERENCES `t14_semester` (`T14_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_university_T17` FOREIGN KEY (`T01_id_fk`) REFERENCES `t01_university` (`T01_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t18_exam`
--
ALTER TABLE `t18_exam`
  ADD CONSTRAINT `fk_evaluation_T18` FOREIGN KEY (`T11_id_fk`) REFERENCES `t11_evaluation` (`T11_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_offered_course_T18` FOREIGN KEY (`T15_id_fk`) REFERENCES `t15_offered_course` (`T15_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t19_question`
--
ALTER TABLE `t19_question`
  ADD CONSTRAINT `fk_clo_T19` FOREIGN KEY (`T10_id_fk`) REFERENCES `t13_clo_plo_connect` (`T10_id_fk`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_exam_T19` FOREIGN KEY (`T18_id_fk`) REFERENCES `t18_exam` (`T18_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_plo_T19` FOREIGN KEY (`T09_id_fk`) REFERENCES `t13_clo_plo_connect` (`T09_id_fk`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t20_student`
--
ALTER TABLE `t20_student`
  ADD CONSTRAINT `fk_batch_T20` FOREIGN KEY (`T17_id_fk`) REFERENCES `t17_batch` (`T17_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_university_T20` FOREIGN KEY (`T01_id_fk`) REFERENCES `t01_university` (`T01_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t21_student_course_registration`
--
ALTER TABLE `t21_student_course_registration`
  ADD CONSTRAINT `fk_offered_course_T21` FOREIGN KEY (`T15_id_fk`) REFERENCES `t15_offered_course` (`T15_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_student_T21` FOREIGN KEY (`T20_id_fk`) REFERENCES `t20_student` (`T20_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t22_marks_obtained`
--
ALTER TABLE `t22_marks_obtained`
  ADD CONSTRAINT `fk_question_T22` FOREIGN KEY (`T19_id_fk`) REFERENCES `t19_question` (`T19_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_student_T22` FOREIGN KEY (`T20_id_fk`) REFERENCES `t20_student` (`T20_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

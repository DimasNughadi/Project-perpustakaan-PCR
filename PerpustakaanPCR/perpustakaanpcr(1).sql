-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 17, 2020 at 03:36 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpustakaanpcr`
--

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `id_buku` int(11) NOT NULL,
  `judul_buku` varchar(50) NOT NULL,
  `penulis` varchar(50) NOT NULL,
  `penerbit` varchar(50) NOT NULL,
  `thn_terbit` varchar(4) NOT NULL,
  `jml_buku` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id_buku`, `judul_buku`, `penulis`, `penerbit`, `thn_terbit`, `jml_buku`) VALUES
(1, 'Sebuah Seni Untuk Bersikap Bodo Amat', 'Mark Manson', 'HarperOne', '2016', 10),
(2, 'Mimpi-mimpi Lintang Maryamah Karpov', 'Andrea Hirata', 'Penerbit Bentang', '2008', 5),
(3, 'Praktikum Basis Data Lanjut', 'Wawan Yunanto S.Kom', 'Politeknik Caltex Riau', '2007', 250),
(4, 'Praktikum Sistem Operasi', 'Muhammad Arif Fadhly Ridha', 'Politeknik Caltex Riau', '2014', 200),
(5, 'English 2 Textbook', 'Zusanti Syahrial', 'Politeknik Caltex Riau', '2013', 400),
(6, 'Dilan, dia adalah Dilanku tahun 1990', 'Pidi Baiq', 'PT MizanPustaka', '2016', 5),
(7, 'Pergi', 'Tere Liye', 'Republika penerbit', '2018', 3),
(8, 'Pulang', 'Tere Liye', 'Republika Penerbit', '2015', 6),
(9, 'Amira Cinta Dari Tanah Surga', 'Suliwe', 'GEMA INSANI', '2011', 3);

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `nim` varchar(20) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `prodi` varchar(80) NOT NULL,
  `generasi` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`nim`, `nama`, `prodi`, `generasi`) VALUES
('1955301039', 'Dimas Nugroho', 'D4 Teknik Informatika', 'G19'),
('1955301010', 'Bima Tauladan', 'D4 Teknik Informatika', 'G19');

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id_peminjaman` int(11) NOT NULL,
  `nim` varchar(20) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `prodi` varchar(80) NOT NULL,
  `id_buku` int(11) NOT NULL,
  `judul` varchar(50) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`id_peminjaman`, `nim`, `nama`, `prodi`, `id_buku`, `judul`, `tgl_pinjam`, `tgl_kembali`, `status`) VALUES
(1, '1955301039', 'Dimas Nugroho', 'D4 Teknik Informatika', 6, 'Dilan, dia adalah Dilanku tahun 1990', '2020-07-15', '2020-07-21', 'sudah'),
(3, '1955301039', 'Dimas Nugroho', 'D4 Teknik Informatika', 1, 'Sebuah Seni Untuk Bersikap Bodo Amat', '2020-07-15', '2020-07-22', 'sudah'),
(4, '1955301010', 'Bima Tauladan', 'D4 Teknik Informatika', 2, 'Mimpi-mimpi Lintang Maryamah Karpov', '2020-07-14', '2020-07-22', 'belum'),
(5, '1955301039', 'Dimas Nugroho', 'D4 Teknik Informatika', 1, 'Sebuah Seni Untuk Bersikap Bodo Amat', '2020-07-14', '2020-07-18', 'sudah'),
(6, '1955301039', 'Dimas Nugroho', 'D4 Teknik Informatika', 8, 'Pulang', '2020-07-15', '2020-07-31', 'belum'),
(7, '1955301039', 'Dimas Nugroho', 'D4 Teknik Informatika', 9, 'Amira Cinta Dari Tanah Surga', '2020-07-16', '2020-08-16', 'sudah'),
(8, '1955301010', 'Bima Tauladan', 'D4 Teknik Informatika', 8, 'Pulang', '2020-07-16', '2020-07-30', 'sudah'),
(9, '1955301039', 'Dimas Nugroho', 'D4 Teknik Informatika', 6, 'Dilan, dia adalah Dilanku tahun 1990', '2020-07-17', '2020-09-17', 'belum');

-- --------------------------------------------------------

--
-- Table structure for table `pengembalian`
--

CREATE TABLE `pengembalian` (
  `id_pengembalian` int(11) NOT NULL,
  `id_peminjaman` int(11) NOT NULL,
  `tgl_pengembalian` date NOT NULL,
  `telat` int(11) NOT NULL,
  `denda` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengembalian`
--

INSERT INTO `pengembalian` (`id_pengembalian`, `id_peminjaman`, `tgl_pengembalian`, `telat`, `denda`) VALUES
(2, 2, '2020-07-20', 0, 0),
(3, 3, '2020-07-19', 0, 0),
(4, 1, '2020-07-20', 0, 0),
(5, 2, '2020-07-26', 2, 10000),
(6, 5, '2020-07-20', 0, 0),
(7, 7, '2020-07-16', 0, 0),
(8, 8, '2020-07-30', 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id_buku`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id_peminjaman`);

--
-- Indexes for table `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`id_pengembalian`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buku`
--
ALTER TABLE `buku`
  MODIFY `id_buku` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `id_peminjaman` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `pengembalian`
--
ALTER TABLE `pengembalian`
  MODIFY `id_pengembalian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

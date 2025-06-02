-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 02 Jun 2025 pada 12.58
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `data_mahasiswa`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `akun`
--

CREATE TABLE `akun` (
  `username` varchar(16) NOT NULL,
  `password` varchar(36) NOT NULL,
  `role` enum('DOSEN','ADMIN') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `akun`
--

INSERT INTO `akun` (`username`, `password`, `role`) VALUES
('admin', 'admin123', 'ADMIN'),
('albert', '123100005', 'DOSEN'),
('diablo', '123100001', 'DOSEN'),
('john', '123100002', 'DOSEN'),
('thomas', '123100004', 'DOSEN'),
('tom', '123100003', 'DOSEN');

-- --------------------------------------------------------

--
-- Struktur dari tabel `dosen`
--

CREATE TABLE `dosen` (
  `nip` varchar(16) NOT NULL,
  `username` varchar(16) NOT NULL,
  `nama` varchar(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `dosen`
--

INSERT INTO `dosen` (`nip`, `username`, `nama`) VALUES
('123100005', 'albert', 'Albert Hawk'),
('123100001', 'diablo', 'Diablo Dewantara'),
('123100002', 'john', 'John Weck'),
('123100004', 'thomas', 'Thomas Edison'),
('123100003', 'tom', 'Tom Holland');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kelas`
--

CREATE TABLE `kelas` (
  `id` varchar(36) NOT NULL DEFAULT uuid(),
  `nama` varchar(100) NOT NULL,
  `username_dosen` varchar(16) NOT NULL,
  `mata_kuliah_id` varchar(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kelas`
--

INSERT INTO `kelas` (`id`, `nama`, `username_dosen`, `mata_kuliah_id`) VALUES
('54be4ffd-3f9a-11f0-a342-bceca0044cdd', 'IF-A', 'albert', 'c672955e-3f99-11f0-a342-bceca0044cdd'),
('5e4e6bda-3f9a-11f0-a342-bceca0044cdd', 'IF-B', 'diablo', 'c672955e-3f99-11f0-a342-bceca0044cdd'),
('75b30d7a-3f9a-11f0-a342-bceca0044cdd', 'IF-A', 'diablo', 'ce566938-3f99-11f0-a342-bceca0044cdd'),
('7bf5020e-3f9a-11f0-a342-bceca0044cdd', 'IF-A', 'john', 'e4662ab5-3f99-11f0-a342-bceca0044cdd'),
('8272b6bf-3f9a-11f0-a342-bceca0044cdd', 'IF-A', 'thomas', 'e9bd3538-3f99-11f0-a342-bceca0044cdd'),
('87578992-3f9a-11f0-a342-bceca0044cdd', 'IF-A', 'tom', 'efc99e3b-3f99-11f0-a342-bceca0044cdd'),
('93601bf0-3f9a-11f0-a342-bceca0044cdd', 'IF-A', 'tom', 'f9e33ad6-3f99-11f0-a342-bceca0044cdd'),
('9c266647-3f9a-11f0-a342-bceca0044cdd', 'IF-B', 'john', 'ce566938-3f99-11f0-a342-bceca0044cdd'),
('b1b940ca-3f9a-11f0-a342-bceca0044cdd', 'IF-B', 'thomas', 'e4662ab5-3f99-11f0-a342-bceca0044cdd'),
('b5ffcf14-3f9a-11f0-a342-bceca0044cdd', 'IF-B', 'thomas', 'e9bd3538-3f99-11f0-a342-bceca0044cdd'),
('b8d35f8f-3f9a-11f0-a342-bceca0044cdd', 'IF-B', 'tom', 'efc99e3b-3f99-11f0-a342-bceca0044cdd'),
('baf0fc59-3f9a-11f0-a342-bceca0044cdd', 'IF-B', 'albert', 'f9e33ad6-3f99-11f0-a342-bceca0044cdd'),
('c67247b2-3f9a-11f0-a342-bceca0044cdd', 'IF-A', 'albert', '08e7ae3b-3f9a-11f0-a342-bceca0044cdd'),
('cad879fc-3f9a-11f0-a342-bceca0044cdd', 'IF-A', 'albert', '13e7af79-3f9a-11f0-a342-bceca0044cdd'),
('cd9be11d-3f9a-11f0-a342-bceca0044cdd', 'IF-A', 'diablo', '1dd1de67-3f9a-11f0-a342-bceca0044cdd'),
('d01c8e31-3f9a-11f0-a342-bceca0044cdd', 'IF-A', 'john', '2620016e-3f9a-11f0-a342-bceca0044cdd'),
('d375d028-3f9a-11f0-a342-bceca0044cdd', 'IF-A', 'thomas', '2c1de93c-3f9a-11f0-a342-bceca0044cdd'),
('d7705979-3f9a-11f0-a342-bceca0044cdd', 'IF-A', 'john', '3b4bbf9b-3f9a-11f0-a342-bceca0044cdd'),
('df574116-3f9a-11f0-a342-bceca0044cdd', 'IF-B', 'albert', '08e7ae3b-3f9a-11f0-a342-bceca0044cdd'),
('e39e7fc5-3f9a-11f0-a342-bceca0044cdd', 'IF-B', 'tom', '13e7af79-3f9a-11f0-a342-bceca0044cdd'),
('e670c4b7-3f9a-11f0-a342-bceca0044cdd', 'IF-B', 'tom', '1dd1de67-3f9a-11f0-a342-bceca0044cdd'),
('e92c233e-3f9a-11f0-a342-bceca0044cdd', 'IF-B', 'thomas', '2620016e-3f9a-11f0-a342-bceca0044cdd'),
('ed104ea2-3f9a-11f0-a342-bceca0044cdd', 'IF-B', 'thomas', '2c1de93c-3f9a-11f0-a342-bceca0044cdd'),
('f165fc94-3f9a-11f0-a342-bceca0044cdd', 'IF-B', 'diablo', '3b4bbf9b-3f9a-11f0-a342-bceca0044cdd');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `nim` varchar(10) NOT NULL,
  `nama` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `mahasiswa`
--

INSERT INTO `mahasiswa` (`nim`, `nama`) VALUES
('12308216', 'Andi'),
('123230001', 'Akmal'),
('123230002', 'Mutir'),
('123230003', 'Kemal'),
('123230004', 'Kumal'),
('123230005', 'Kamil'),
('123230006', 'Kumul'),
('123230007', 'Kumal'),
('123230008', 'Umar'),
('123230010', 'Bayu'),
('123230011', 'Adel'),
('123230012', 'Adit'),
('123230013', 'Denis'),
('123230014', 'Sopo'),
('123230015', 'JArwo'),
('123230016', 'Ucup'),
('123230017', 'Krisrna'),
('123230018', 'Bima'),
('123230019', 'Dewi'),
('12323009', 'Amur'),
('123230100', 'Alfonsus Sitanggang'),
('123230109', 'Pare'),
('123230127', 'Levi Rizki Saputra');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswakelas`
--

CREATE TABLE `mahasiswakelas` (
  `mahasiswa_nim` varchar(10) NOT NULL,
  `kelas_id` varchar(36) NOT NULL,
  `nilai` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `mahasiswakelas`
--

INSERT INTO `mahasiswakelas` (`mahasiswa_nim`, `kelas_id`, `nilai`) VALUES
('12308216', '54be4ffd-3f9a-11f0-a342-bceca0044cdd', 80),
('12308216', '75b30d7a-3f9a-11f0-a342-bceca0044cdd', 80),
('12308216', '7bf5020e-3f9a-11f0-a342-bceca0044cdd', 80),
('12308216', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('12308216', '93601bf0-3f9a-11f0-a342-bceca0044cdd', 70),
('12308216', 'b8d35f8f-3f9a-11f0-a342-bceca0044cdd', 80),
('12308216', 'c67247b2-3f9a-11f0-a342-bceca0044cdd', 85),
('12308216', 'cad879fc-3f9a-11f0-a342-bceca0044cdd', 90),
('12308216', 'cd9be11d-3f9a-11f0-a342-bceca0044cdd', 88),
('12308216', 'd01c8e31-3f9a-11f0-a342-bceca0044cdd', 80),
('12308216', 'd375d028-3f9a-11f0-a342-bceca0044cdd', 90),
('12308216', 'd7705979-3f9a-11f0-a342-bceca0044cdd', 99),
('123230001', '54be4ffd-3f9a-11f0-a342-bceca0044cdd', 80),
('123230001', '7bf5020e-3f9a-11f0-a342-bceca0044cdd', 80),
('123230001', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('123230001', '93601bf0-3f9a-11f0-a342-bceca0044cdd', 70),
('123230001', '9c266647-3f9a-11f0-a342-bceca0044cdd', 70),
('123230001', 'b8d35f8f-3f9a-11f0-a342-bceca0044cdd', 88),
('123230001', 'c67247b2-3f9a-11f0-a342-bceca0044cdd', 85),
('123230001', 'cad879fc-3f9a-11f0-a342-bceca0044cdd', 90),
('123230001', 'cd9be11d-3f9a-11f0-a342-bceca0044cdd', 80),
('123230001', 'd01c8e31-3f9a-11f0-a342-bceca0044cdd', 80),
('123230001', 'd375d028-3f9a-11f0-a342-bceca0044cdd', 99),
('123230001', 'd7705979-3f9a-11f0-a342-bceca0044cdd', 90),
('123230002', '54be4ffd-3f9a-11f0-a342-bceca0044cdd', 80),
('123230002', '75b30d7a-3f9a-11f0-a342-bceca0044cdd', 80),
('123230002', '93601bf0-3f9a-11f0-a342-bceca0044cdd', 77),
('123230002', 'b1b940ca-3f9a-11f0-a342-bceca0044cdd', 80),
('123230002', 'b5ffcf14-3f9a-11f0-a342-bceca0044cdd', 80),
('123230002', 'b8d35f8f-3f9a-11f0-a342-bceca0044cdd', 80),
('123230002', 'c67247b2-3f9a-11f0-a342-bceca0044cdd', 85),
('123230002', 'cad879fc-3f9a-11f0-a342-bceca0044cdd', 90),
('123230002', 'cd9be11d-3f9a-11f0-a342-bceca0044cdd', 80),
('123230002', 'd01c8e31-3f9a-11f0-a342-bceca0044cdd', 80),
('123230002', 'd375d028-3f9a-11f0-a342-bceca0044cdd', 90),
('123230002', 'd7705979-3f9a-11f0-a342-bceca0044cdd', 90),
('123230003', '54be4ffd-3f9a-11f0-a342-bceca0044cdd', 80),
('123230003', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('123230003', '93601bf0-3f9a-11f0-a342-bceca0044cdd', 70),
('123230003', '9c266647-3f9a-11f0-a342-bceca0044cdd', 70),
('123230003', 'b1b940ca-3f9a-11f0-a342-bceca0044cdd', 80),
('123230003', 'b8d35f8f-3f9a-11f0-a342-bceca0044cdd', 80),
('123230003', 'c67247b2-3f9a-11f0-a342-bceca0044cdd', 85),
('123230003', 'cad879fc-3f9a-11f0-a342-bceca0044cdd', 90),
('123230003', 'cd9be11d-3f9a-11f0-a342-bceca0044cdd', 80),
('123230003', 'd01c8e31-3f9a-11f0-a342-bceca0044cdd', 80),
('123230003', 'd375d028-3f9a-11f0-a342-bceca0044cdd', 90),
('123230003', 'd7705979-3f9a-11f0-a342-bceca0044cdd', 90),
('123230004', '54be4ffd-3f9a-11f0-a342-bceca0044cdd', 80),
('123230004', '75b30d7a-3f9a-11f0-a342-bceca0044cdd', 80),
('123230004', '7bf5020e-3f9a-11f0-a342-bceca0044cdd', 80),
('123230004', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('123230004', '93601bf0-3f9a-11f0-a342-bceca0044cdd', 70),
('123230004', 'b8d35f8f-3f9a-11f0-a342-bceca0044cdd', 80),
('123230004', 'c67247b2-3f9a-11f0-a342-bceca0044cdd', 85),
('123230004', 'cad879fc-3f9a-11f0-a342-bceca0044cdd', 90),
('123230004', 'cd9be11d-3f9a-11f0-a342-bceca0044cdd', 80),
('123230004', 'd01c8e31-3f9a-11f0-a342-bceca0044cdd', 80),
('123230004', 'd375d028-3f9a-11f0-a342-bceca0044cdd', 90),
('123230004', 'd7705979-3f9a-11f0-a342-bceca0044cdd', 99),
('123230005', '54be4ffd-3f9a-11f0-a342-bceca0044cdd', 80),
('123230005', '7bf5020e-3f9a-11f0-a342-bceca0044cdd', 80),
('123230005', '93601bf0-3f9a-11f0-a342-bceca0044cdd', 70),
('123230005', '9c266647-3f9a-11f0-a342-bceca0044cdd', 70),
('123230005', 'b5ffcf14-3f9a-11f0-a342-bceca0044cdd', 80),
('123230005', 'b8d35f8f-3f9a-11f0-a342-bceca0044cdd', 80),
('123230005', 'c67247b2-3f9a-11f0-a342-bceca0044cdd', 85),
('123230005', 'cad879fc-3f9a-11f0-a342-bceca0044cdd', 90),
('123230005', 'cd9be11d-3f9a-11f0-a342-bceca0044cdd', 80),
('123230005', 'd01c8e31-3f9a-11f0-a342-bceca0044cdd', 80),
('123230005', 'd375d028-3f9a-11f0-a342-bceca0044cdd', 99),
('123230005', 'd7705979-3f9a-11f0-a342-bceca0044cdd', 90),
('123230006', '54be4ffd-3f9a-11f0-a342-bceca0044cdd', 80),
('123230006', '75b30d7a-3f9a-11f0-a342-bceca0044cdd', 80),
('123230006', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('123230006', '93601bf0-3f9a-11f0-a342-bceca0044cdd', 70),
('123230006', 'b1b940ca-3f9a-11f0-a342-bceca0044cdd', 80),
('123230006', 'b8d35f8f-3f9a-11f0-a342-bceca0044cdd', 80),
('123230006', 'c67247b2-3f9a-11f0-a342-bceca0044cdd', 85),
('123230006', 'cad879fc-3f9a-11f0-a342-bceca0044cdd', 90),
('123230006', 'cd9be11d-3f9a-11f0-a342-bceca0044cdd', 88),
('123230006', 'd01c8e31-3f9a-11f0-a342-bceca0044cdd', 80),
('123230006', 'd375d028-3f9a-11f0-a342-bceca0044cdd', 90),
('123230006', 'd7705979-3f9a-11f0-a342-bceca0044cdd', 90),
('123230007', '54be4ffd-3f9a-11f0-a342-bceca0044cdd', 80),
('123230007', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('123230007', '93601bf0-3f9a-11f0-a342-bceca0044cdd', 70),
('123230007', '9c266647-3f9a-11f0-a342-bceca0044cdd', 70),
('123230007', 'b1b940ca-3f9a-11f0-a342-bceca0044cdd', 80),
('123230007', 'b8d35f8f-3f9a-11f0-a342-bceca0044cdd', 80),
('123230007', 'c67247b2-3f9a-11f0-a342-bceca0044cdd', 85),
('123230007', 'cad879fc-3f9a-11f0-a342-bceca0044cdd', 90),
('123230007', 'cd9be11d-3f9a-11f0-a342-bceca0044cdd', 80),
('123230007', 'd01c8e31-3f9a-11f0-a342-bceca0044cdd', 80),
('123230007', 'd375d028-3f9a-11f0-a342-bceca0044cdd', 90),
('123230007', 'd7705979-3f9a-11f0-a342-bceca0044cdd', 90),
('123230008', '54be4ffd-3f9a-11f0-a342-bceca0044cdd', 78),
('123230008', '75b30d7a-3f9a-11f0-a342-bceca0044cdd', 88),
('123230008', '7bf5020e-3f9a-11f0-a342-bceca0044cdd', 80),
('123230008', '93601bf0-3f9a-11f0-a342-bceca0044cdd', 70),
('123230008', 'b5ffcf14-3f9a-11f0-a342-bceca0044cdd', 80),
('123230008', 'b8d35f8f-3f9a-11f0-a342-bceca0044cdd', 80),
('123230008', 'c67247b2-3f9a-11f0-a342-bceca0044cdd', 85),
('123230008', 'cad879fc-3f9a-11f0-a342-bceca0044cdd', 90),
('123230008', 'cd9be11d-3f9a-11f0-a342-bceca0044cdd', 80),
('123230008', 'd01c8e31-3f9a-11f0-a342-bceca0044cdd', 80),
('123230008', 'd375d028-3f9a-11f0-a342-bceca0044cdd', 90),
('123230008', 'd7705979-3f9a-11f0-a342-bceca0044cdd', 90),
('123230010', '54be4ffd-3f9a-11f0-a342-bceca0044cdd', 98),
('123230010', '7bf5020e-3f9a-11f0-a342-bceca0044cdd', 80),
('123230010', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('123230010', '93601bf0-3f9a-11f0-a342-bceca0044cdd', 70),
('123230010', '9c266647-3f9a-11f0-a342-bceca0044cdd', 70),
('123230010', 'b8d35f8f-3f9a-11f0-a342-bceca0044cdd', 80),
('123230010', 'c67247b2-3f9a-11f0-a342-bceca0044cdd', 85),
('123230010', 'cad879fc-3f9a-11f0-a342-bceca0044cdd', 90),
('123230010', 'cd9be11d-3f9a-11f0-a342-bceca0044cdd', 80),
('123230010', 'd01c8e31-3f9a-11f0-a342-bceca0044cdd', 80),
('123230010', 'd375d028-3f9a-11f0-a342-bceca0044cdd', 90),
('123230010', 'd7705979-3f9a-11f0-a342-bceca0044cdd', 90),
('123230011', '54be4ffd-3f9a-11f0-a342-bceca0044cdd', 89),
('123230011', '75b30d7a-3f9a-11f0-a342-bceca0044cdd', 80),
('123230011', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('123230011', '93601bf0-3f9a-11f0-a342-bceca0044cdd', 70),
('123230011', 'b1b940ca-3f9a-11f0-a342-bceca0044cdd', 80),
('123230011', 'b8d35f8f-3f9a-11f0-a342-bceca0044cdd', 80),
('123230011', 'c67247b2-3f9a-11f0-a342-bceca0044cdd', 85),
('123230011', 'cad879fc-3f9a-11f0-a342-bceca0044cdd', 90),
('123230011', 'cd9be11d-3f9a-11f0-a342-bceca0044cdd', 88),
('123230011', 'd01c8e31-3f9a-11f0-a342-bceca0044cdd', 80),
('123230011', 'd375d028-3f9a-11f0-a342-bceca0044cdd', 90),
('123230011', 'f165fc94-3f9a-11f0-a342-bceca0044cdd', 90),
('123230012', '54be4ffd-3f9a-11f0-a342-bceca0044cdd', 99),
('123230012', '93601bf0-3f9a-11f0-a342-bceca0044cdd', 70),
('123230012', '9c266647-3f9a-11f0-a342-bceca0044cdd', 70),
('123230012', 'b1b940ca-3f9a-11f0-a342-bceca0044cdd', 80),
('123230012', 'b5ffcf14-3f9a-11f0-a342-bceca0044cdd', 80),
('123230012', 'b8d35f8f-3f9a-11f0-a342-bceca0044cdd', 80),
('123230012', 'cad879fc-3f9a-11f0-a342-bceca0044cdd', 90),
('123230012', 'd01c8e31-3f9a-11f0-a342-bceca0044cdd', 80),
('123230012', 'd7705979-3f9a-11f0-a342-bceca0044cdd', 90),
('123230012', 'df574116-3f9a-11f0-a342-bceca0044cdd', 85),
('123230012', 'e39e7fc5-3f9a-11f0-a342-bceca0044cdd', 90),
('123230012', 'e670c4b7-3f9a-11f0-a342-bceca0044cdd', 90),
('123230012', 'ed104ea2-3f9a-11f0-a342-bceca0044cdd', 80),
('123230013', '54be4ffd-3f9a-11f0-a342-bceca0044cdd', 70),
('123230013', '75b30d7a-3f9a-11f0-a342-bceca0044cdd', 80),
('123230013', '7bf5020e-3f9a-11f0-a342-bceca0044cdd', 80),
('123230013', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('123230013', 'b8d35f8f-3f9a-11f0-a342-bceca0044cdd', 80),
('123230013', 'baf0fc59-3f9a-11f0-a342-bceca0044cdd', 79),
('123230013', 'df574116-3f9a-11f0-a342-bceca0044cdd', 85),
('123230013', 'e39e7fc5-3f9a-11f0-a342-bceca0044cdd', 90),
('123230013', 'e670c4b7-3f9a-11f0-a342-bceca0044cdd', 90),
('123230013', 'e92c233e-3f9a-11f0-a342-bceca0044cdd', 90),
('123230013', 'ed104ea2-3f9a-11f0-a342-bceca0044cdd', 80),
('123230013', 'f165fc94-3f9a-11f0-a342-bceca0044cdd', 90),
('123230014', '54be4ffd-3f9a-11f0-a342-bceca0044cdd', 87),
('123230014', '7bf5020e-3f9a-11f0-a342-bceca0044cdd', 80),
('123230014', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('123230014', '87578992-3f9a-11f0-a342-bceca0044cdd', 88),
('123230014', '9c266647-3f9a-11f0-a342-bceca0044cdd', 70),
('123230014', 'baf0fc59-3f9a-11f0-a342-bceca0044cdd', 79),
('123230014', 'df574116-3f9a-11f0-a342-bceca0044cdd', 85),
('123230014', 'e39e7fc5-3f9a-11f0-a342-bceca0044cdd', 90),
('123230014', 'e670c4b7-3f9a-11f0-a342-bceca0044cdd', 90),
('123230014', 'e92c233e-3f9a-11f0-a342-bceca0044cdd', 90),
('123230014', 'ed104ea2-3f9a-11f0-a342-bceca0044cdd', 80),
('123230014', 'f165fc94-3f9a-11f0-a342-bceca0044cdd', 90),
('123230015', '54be4ffd-3f9a-11f0-a342-bceca0044cdd', 70),
('123230015', '75b30d7a-3f9a-11f0-a342-bceca0044cdd', 80),
('123230015', '87578992-3f9a-11f0-a342-bceca0044cdd', 80),
('123230015', 'b1b940ca-3f9a-11f0-a342-bceca0044cdd', 80),
('123230015', 'b5ffcf14-3f9a-11f0-a342-bceca0044cdd', 80),
('123230015', 'baf0fc59-3f9a-11f0-a342-bceca0044cdd', 79),
('123230015', 'df574116-3f9a-11f0-a342-bceca0044cdd', 85),
('123230015', 'e39e7fc5-3f9a-11f0-a342-bceca0044cdd', 90),
('123230015', 'e670c4b7-3f9a-11f0-a342-bceca0044cdd', 90),
('123230015', 'e92c233e-3f9a-11f0-a342-bceca0044cdd', 90),
('123230015', 'ed104ea2-3f9a-11f0-a342-bceca0044cdd', 80),
('123230015', 'f165fc94-3f9a-11f0-a342-bceca0044cdd', 90),
('123230016', '5e4e6bda-3f9a-11f0-a342-bceca0044cdd', 85),
('123230016', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('123230016', '87578992-3f9a-11f0-a342-bceca0044cdd', 80),
('123230016', '9c266647-3f9a-11f0-a342-bceca0044cdd', 70),
('123230016', 'b1b940ca-3f9a-11f0-a342-bceca0044cdd', 80),
('123230016', 'baf0fc59-3f9a-11f0-a342-bceca0044cdd', 79),
('123230016', 'df574116-3f9a-11f0-a342-bceca0044cdd', 85),
('123230016', 'e39e7fc5-3f9a-11f0-a342-bceca0044cdd', 90),
('123230016', 'e670c4b7-3f9a-11f0-a342-bceca0044cdd', 90),
('123230016', 'e92c233e-3f9a-11f0-a342-bceca0044cdd', 90),
('123230016', 'ed104ea2-3f9a-11f0-a342-bceca0044cdd', 80),
('123230016', 'f165fc94-3f9a-11f0-a342-bceca0044cdd', 90),
('123230017', '5e4e6bda-3f9a-11f0-a342-bceca0044cdd', 80),
('123230017', '75b30d7a-3f9a-11f0-a342-bceca0044cdd', 80),
('123230017', '7bf5020e-3f9a-11f0-a342-bceca0044cdd', 80),
('123230017', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('123230017', '87578992-3f9a-11f0-a342-bceca0044cdd', 80),
('123230017', 'baf0fc59-3f9a-11f0-a342-bceca0044cdd', 79),
('123230017', 'df574116-3f9a-11f0-a342-bceca0044cdd', 85),
('123230017', 'e39e7fc5-3f9a-11f0-a342-bceca0044cdd', 90),
('123230017', 'e670c4b7-3f9a-11f0-a342-bceca0044cdd', 90),
('123230017', 'e92c233e-3f9a-11f0-a342-bceca0044cdd', 90),
('123230017', 'ed104ea2-3f9a-11f0-a342-bceca0044cdd', 80),
('123230017', 'f165fc94-3f9a-11f0-a342-bceca0044cdd', 90),
('123230018', '5e4e6bda-3f9a-11f0-a342-bceca0044cdd', 80),
('123230018', '7bf5020e-3f9a-11f0-a342-bceca0044cdd', 80),
('123230018', '87578992-3f9a-11f0-a342-bceca0044cdd', 80),
('123230018', '9c266647-3f9a-11f0-a342-bceca0044cdd', 70),
('123230018', 'b5ffcf14-3f9a-11f0-a342-bceca0044cdd', 80),
('123230018', 'baf0fc59-3f9a-11f0-a342-bceca0044cdd', 79),
('123230018', 'df574116-3f9a-11f0-a342-bceca0044cdd', 85),
('123230018', 'e39e7fc5-3f9a-11f0-a342-bceca0044cdd', 90),
('123230018', 'e670c4b7-3f9a-11f0-a342-bceca0044cdd', 90),
('123230018', 'e92c233e-3f9a-11f0-a342-bceca0044cdd', 90),
('123230018', 'ed104ea2-3f9a-11f0-a342-bceca0044cdd', 80),
('123230018', 'f165fc94-3f9a-11f0-a342-bceca0044cdd', 90),
('123230019', '5e4e6bda-3f9a-11f0-a342-bceca0044cdd', 80),
('123230019', '75b30d7a-3f9a-11f0-a342-bceca0044cdd', 80),
('123230019', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('123230019', '87578992-3f9a-11f0-a342-bceca0044cdd', 80),
('123230019', 'b1b940ca-3f9a-11f0-a342-bceca0044cdd', 80),
('123230019', 'baf0fc59-3f9a-11f0-a342-bceca0044cdd', 79),
('123230019', 'df574116-3f9a-11f0-a342-bceca0044cdd', 85),
('123230019', 'e39e7fc5-3f9a-11f0-a342-bceca0044cdd', 90),
('123230019', 'e670c4b7-3f9a-11f0-a342-bceca0044cdd', 90),
('123230019', 'e92c233e-3f9a-11f0-a342-bceca0044cdd', 90),
('123230019', 'ed104ea2-3f9a-11f0-a342-bceca0044cdd', 80),
('123230019', 'f165fc94-3f9a-11f0-a342-bceca0044cdd', 90),
('12323009', '5e4e6bda-3f9a-11f0-a342-bceca0044cdd', 80),
('12323009', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('12323009', '87578992-3f9a-11f0-a342-bceca0044cdd', 80),
('12323009', '9c266647-3f9a-11f0-a342-bceca0044cdd', 70),
('12323009', 'b1b940ca-3f9a-11f0-a342-bceca0044cdd', 80),
('12323009', 'baf0fc59-3f9a-11f0-a342-bceca0044cdd', 79),
('12323009', 'df574116-3f9a-11f0-a342-bceca0044cdd', 85),
('12323009', 'e39e7fc5-3f9a-11f0-a342-bceca0044cdd', 99),
('12323009', 'e670c4b7-3f9a-11f0-a342-bceca0044cdd', 99),
('12323009', 'e92c233e-3f9a-11f0-a342-bceca0044cdd', 90),
('12323009', 'ed104ea2-3f9a-11f0-a342-bceca0044cdd', 80),
('12323009', 'f165fc94-3f9a-11f0-a342-bceca0044cdd', 90),
('123230100', '5e4e6bda-3f9a-11f0-a342-bceca0044cdd', 80),
('123230100', '75b30d7a-3f9a-11f0-a342-bceca0044cdd', 80),
('123230100', '7bf5020e-3f9a-11f0-a342-bceca0044cdd', 80),
('123230100', '87578992-3f9a-11f0-a342-bceca0044cdd', 80),
('123230100', 'b5ffcf14-3f9a-11f0-a342-bceca0044cdd', 80),
('123230100', 'baf0fc59-3f9a-11f0-a342-bceca0044cdd', 79),
('123230100', 'df574116-3f9a-11f0-a342-bceca0044cdd', 85),
('123230100', 'e39e7fc5-3f9a-11f0-a342-bceca0044cdd', 90),
('123230100', 'e670c4b7-3f9a-11f0-a342-bceca0044cdd', 90),
('123230100', 'e92c233e-3f9a-11f0-a342-bceca0044cdd', 90),
('123230100', 'ed104ea2-3f9a-11f0-a342-bceca0044cdd', 80),
('123230100', 'f165fc94-3f9a-11f0-a342-bceca0044cdd', 90),
('123230109', '5e4e6bda-3f9a-11f0-a342-bceca0044cdd', 80),
('123230109', '7bf5020e-3f9a-11f0-a342-bceca0044cdd', 80),
('123230109', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('123230109', '87578992-3f9a-11f0-a342-bceca0044cdd', 80),
('123230109', '9c266647-3f9a-11f0-a342-bceca0044cdd', 70),
('123230109', 'baf0fc59-3f9a-11f0-a342-bceca0044cdd', 79),
('123230109', 'df574116-3f9a-11f0-a342-bceca0044cdd', 85),
('123230109', 'e39e7fc5-3f9a-11f0-a342-bceca0044cdd', 90),
('123230109', 'e670c4b7-3f9a-11f0-a342-bceca0044cdd', 90),
('123230109', 'e92c233e-3f9a-11f0-a342-bceca0044cdd', 90),
('123230109', 'ed104ea2-3f9a-11f0-a342-bceca0044cdd', 80),
('123230109', 'f165fc94-3f9a-11f0-a342-bceca0044cdd', 90),
('123230127', '5e4e6bda-3f9a-11f0-a342-bceca0044cdd', 80),
('123230127', '75b30d7a-3f9a-11f0-a342-bceca0044cdd', 80),
('123230127', '8272b6bf-3f9a-11f0-a342-bceca0044cdd', 70),
('123230127', '87578992-3f9a-11f0-a342-bceca0044cdd', 80),
('123230127', 'b1b940ca-3f9a-11f0-a342-bceca0044cdd', 80),
('123230127', 'baf0fc59-3f9a-11f0-a342-bceca0044cdd', 79),
('123230127', 'df574116-3f9a-11f0-a342-bceca0044cdd', 85),
('123230127', 'e39e7fc5-3f9a-11f0-a342-bceca0044cdd', 90),
('123230127', 'e670c4b7-3f9a-11f0-a342-bceca0044cdd', 90),
('123230127', 'e92c233e-3f9a-11f0-a342-bceca0044cdd', 90),
('123230127', 'ed104ea2-3f9a-11f0-a342-bceca0044cdd', 80),
('123230127', 'f165fc94-3f9a-11f0-a342-bceca0044cdd', 90);

-- --------------------------------------------------------

--
-- Struktur dari tabel `matakuliah`
--

CREATE TABLE `matakuliah` (
  `id` varchar(36) NOT NULL DEFAULT uuid(),
  `nama` varchar(100) NOT NULL,
  `periode_id` varchar(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `matakuliah`
--

INSERT INTO `matakuliah` (`id`, `nama`, `periode_id`) VALUES
('08e7ae3b-3f9a-11f0-a342-bceca0044cdd', 'Bahasa Indonesia', '93e6d6a2-3f99-11f0-a342-bceca0044cdd'),
('13e7af79-3f9a-11f0-a342-bceca0044cdd', 'Geo Informatika', '93e6d6a2-3f99-11f0-a342-bceca0044cdd'),
('1dd1de67-3f9a-11f0-a342-bceca0044cdd', 'Sistem dan Teknologi Informasi', '93e6d6a2-3f99-11f0-a342-bceca0044cdd'),
('2620016e-3f9a-11f0-a342-bceca0044cdd', 'Struktur Data', '93e6d6a2-3f99-11f0-a342-bceca0044cdd'),
('2c1de93c-3f9a-11f0-a342-bceca0044cdd', 'Pemograman Web', '93e6d6a2-3f99-11f0-a342-bceca0044cdd'),
('3b4bbf9b-3f9a-11f0-a342-bceca0044cdd', 'Pendidikan Pancasila', '93e6d6a2-3f99-11f0-a342-bceca0044cdd'),
('c672955e-3f99-11f0-a342-bceca0044cdd', 'Algoritma dan Pemograman Lanjut', '89325d1e-3f99-11f0-a342-bceca0044cdd'),
('ce566938-3f99-11f0-a342-bceca0044cdd', 'Bahasa Inggris', '89325d1e-3f99-11f0-a342-bceca0044cdd'),
('e4662ab5-3f99-11f0-a342-bceca0044cdd', 'Jaringan Komputer', '89325d1e-3f99-11f0-a342-bceca0044cdd'),
('e9bd3538-3f99-11f0-a342-bceca0044cdd', 'Kalkulus Lanjut', '89325d1e-3f99-11f0-a342-bceca0044cdd'),
('efc99e3b-3f99-11f0-a342-bceca0044cdd', 'Matrik dan Ruang Vektor', '89325d1e-3f99-11f0-a342-bceca0044cdd'),
('f9e33ad6-3f99-11f0-a342-bceca0044cdd', 'Statistika', '89325d1e-3f99-11f0-a342-bceca0044cdd');

-- --------------------------------------------------------

--
-- Struktur dari tabel `periode`
--

CREATE TABLE `periode` (
  `id` varchar(36) NOT NULL DEFAULT uuid(),
  `tahun` int(11) NOT NULL,
  `is_genap` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `periode`
--

INSERT INTO `periode` (`id`, `tahun`, `is_genap`) VALUES
('89325d1e-3f99-11f0-a342-bceca0044cdd', 2024, 1),
('93e6d6a2-3f99-11f0-a342-bceca0044cdd', 2024, 0);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `akun`
--
ALTER TABLE `akun`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `dosen`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mata_kuliah_id` (`mata_kuliah_id`),
  ADD KEY `username_dosen` (`username_dosen`);

--
-- Indeks untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`nim`);

--
-- Indeks untuk tabel `mahasiswakelas`
--
ALTER TABLE `mahasiswakelas`
  ADD PRIMARY KEY (`mahasiswa_nim`,`kelas_id`),
  ADD KEY `kelas_id` (`kelas_id`);

--
-- Indeks untuk tabel `matakuliah`
--
ALTER TABLE `matakuliah`
  ADD PRIMARY KEY (`id`),
  ADD KEY `periode_id` (`periode_id`);

--
-- Indeks untuk tabel `periode`
--
ALTER TABLE `periode`
  ADD PRIMARY KEY (`id`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `dosen`
--
ALTER TABLE `dosen`
  ADD CONSTRAINT `dosen_ibfk_1` FOREIGN KEY (`username`) REFERENCES `akun` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `kelas`
--
ALTER TABLE `kelas`
  ADD CONSTRAINT `kelas_ibfk_1` FOREIGN KEY (`mata_kuliah_id`) REFERENCES `matakuliah` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `kelas_ibfk_2` FOREIGN KEY (`username_dosen`) REFERENCES `dosen` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `mahasiswakelas`
--
ALTER TABLE `mahasiswakelas`
  ADD CONSTRAINT `mahasiswakelas_ibfk_1` FOREIGN KEY (`mahasiswa_nim`) REFERENCES `mahasiswa` (`nim`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mahasiswakelas_ibfk_2` FOREIGN KEY (`kelas_id`) REFERENCES `kelas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `matakuliah`
--
ALTER TABLE `matakuliah`
  ADD CONSTRAINT `matakuliah_ibfk_1` FOREIGN KEY (`periode_id`) REFERENCES `periode` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

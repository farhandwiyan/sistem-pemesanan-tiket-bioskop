# Sistem Pemesanan Tiket Bioskop 
Aplikasi berbasis CLI untuk pemesanan tiket bioskop yang dibangun menggunakan konsep OOP dan menggunakan bahasa pemrograman Java.

## Features
- **Autentikasi** — Register dan login pengguna
- **Kelola Studio** — Tambah dan hapus studio, kursi otomatis dibuat sesuai kapasitas
- **Kelola Film** — Tambah film beserta jadwal dan tiket secara otomatis akan mengikuti kapasitas studio yang dipilih
- **Kelola Produk** — Tambah makanan dan tiket
- **Pemesanan Tiket** — Pilih jadwal, lihat denah kursi, pilih kursi, dan pilih makanan
- **Manajemen Stok** — Stok tiket dan makanan terupdate otomatis setelah pemesanan

## Tech Stack
* **Language:** Java
* **Build:** Maven 
* **Database:** MySQL 

## Project Structure
<pre>
src/
├── main/
│   └── java/          
│       ├── model/     
│       ├── repository/     
│       ├── controller/
│       ├── view/      
│       ├── util/      
│       └── App.java   
└── test/java/
</pre>

## Clone Repository
<pre>
git clone https://github.com/farhandwiyan/sistem-pemesanan-tiket-bioskop
</pre>

## Database
```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(10) NOT NULL,
    peran VARCHAR(5) NOT NULL
);	

CREATE TABLE studios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    no_studio VARCHAR(3) NOT NULL,
    kapasitas INT NOT NULL
);

CREATE TABLE kursi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    studio_id INT,
    nomor_kursi VARCHAR(5) NOT NULL, -- misal: A1, A2
    is_tersedia BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (studio_id) REFERENCES studios(id) ON DELETE CASCADE
);

CREATE TABLE films (
    id INT AUTO_INCREMENT PRIMARY KEY,
    judul VARCHAR(40) NOT NULL,
    durasi INT NOT NULL,
    genre VARCHAR(15)
);

CREATE TABLE jadwal (
    id INT AUTO_INCREMENT PRIMARY KEY,
    film_id INT,
    studio_id INT,
    jam_tayang DATETIME NOT NULL,  
    harga DOUBLE NOT NULL,
    FOREIGN KEY (film_id) REFERENCES films(id) ON DELETE CASCADE,
    FOREIGN KEY (studio_id) REFERENCES studios(id) ON DELETE CASCADE
);

CREATE TABLE produk (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    harga DOUBLE NOT NULL,
    jenis VARCHAR(10) NOT NULL,
    stok INT NOT NULL
);

CREATE TABLE tiket (
    id INT PRIMARY KEY,
    jumlah INT NOT NULL,
    FOREIGN KEY (id) REFERENCES produk(id) ON DELETE CASCADE
);

CREATE TABLE makanan (
    id INT PRIMARY KEY,
    jumlah INT NOT NULL,
    FOREIGN KEY (id) REFERENCES produk(id) ON DELETE CASCADE
);

CREATE TABLE pemesanan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pemesan INT NOT NULL,
    tanggal_pesan DATETIME NOT NULL,
    id_tiket INT NOT NULL,
    id_makanan INT, 
    FOREIGN KEY (id_pemesan) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (id_tiket) REFERENCES tiket(id) ON DELETE CASCADE,
    FOREIGN KEY (id_makanan) REFERENCES makanan(id) ON DELETE SET NULL,
    FOREIGN KEY (id_jadwal) REFERENCES jadwal(id) ON DELETE CASCADE;
);

CREATE TABLE pemesanan_kursi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pemesanan_id INT,
    no_kursi VARCHAR(5),
    FOREIGN KEY (pemesanan_id) REFERENCES pemesanan(id) ON DELETE CASCADE
);
```

## Database Configuration
Database configuration in `src/main/java/util/ConnectionUtil.java` 

_Project ini dibuat untuk memenuhi tugas mata kuliah Pemrograman Berorientasi Objek._
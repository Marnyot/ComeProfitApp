> **Final Project ComeProfit**  
> Mata Kuliah: Pengembangan Aplikasi Bergerak  
> Kelompok 6:  
> Mario Valentino Ardhana (L0123079)  
> Mohammad Adzka Crosamer (L0123083)  
> Nayla Amira (L0123108)  

---

## 📌 Deskripsi Proyek

Aplikasi ini merupakan implementasi dari algoritma **Singular Value Decomposition (SVD)** untuk melakukan **image compression** dalam bentuk **website lokal**.  
Tujuan dari kompresi adalah untuk **mengurangi ukuran file image digital** tanpa mengurangi kualitas secara signifikan.

Website memungkinkan pengguna:
- Mengunggah gambar (format JPG/PNG),
- Menentukan tingkat kompresi (%),
- Melihat hasil gambar sebelum dan sesudah kompresi,
- Mengetahui runtime kompresi dan persentase pengurangan ukuran data,
- Mengunduh hasil gambar terkompresi.

---

# 💳 Aplikasi Simulasi Kasir (Kasirku)

Aplikasi Android berbasis Kotlin yang berfungsi sebagai simulasi sistem kasir sederhana. Cocok untuk pembelajaran, tugas kuliah, atau prototipe sistem Point of Sale (POS) dengan fitur-fitur dasar transaksi.

## 📱 Fitur Utama

- 👤 Manajemen data pelanggan (opsional)
- 📦 Input dan daftar produk/barang
- 🛒 Tambah ke keranjang belanja
- 💰 Perhitungan total, diskon, dan kembalian otomatis
- 🧾 Cetak struk sederhana (dalam bentuk tampilan)
- 💾 Penyimpanan data lokal menggunakan SQLite/Room
- 🎨 Tampilan antarmuka sederhana dan responsif (Jetpack Compose/XML)

## 🛠️ Teknologi yang Digunakan

- Kotlin
- Android Studio
- SQLite / Room Database
- View Binding / Jetpack Compose
- MVVM Architecture (optional)

## 🚀 Cara Menjalankan Proyek

```bash
1. Clone repository ini ke komputer Anda menggunakan Git:
   git clone https://github.com/username/kasirku.git

2. Buka proyek menggunakan Android Studio:
   - Klik File > Open lalu arahkan ke folder proyek hasil clone.
   - Tunggu hingga Android Studio menyelesaikan sinkronisasi Gradle.

3. Jalankan aplikasi:
   - Sambungkan emulator atau perangkat Android.
   - Klik tombol ▶️ (Run) untuk menjalankan aplikasi.

4. (Opsional) Jika menggunakan Room atau database lokal:
   - Jalankan aplikasi setidaknya sekali agar database terbentuk.
   - Cek logcat untuk memastikan tidak ada error saat inisialisasi.

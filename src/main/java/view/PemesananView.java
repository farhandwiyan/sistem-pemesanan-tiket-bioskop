package view;

import java.util.ArrayList;
import java.util.List;

import controller.FilmController;
import controller.JadwalController;
import controller.KursiController;
import controller.PemesananController;
import controller.ProdukController;
import model.Jadwal;
import model.Kursi;
import model.Makanan;
import model.Pemesanan;
import model.Tiket;

public class PemesananView extends View {
    private FilmController filmControl;
    private JadwalController jadwalControl;
    private KursiController kursiControl;
    private ProdukController produkControl;
    private PemesananController pemesananControl;
    private int idUser;

    public PemesananView(FilmController filmControl, JadwalController jadwalControl, KursiController kursiControl,
                         ProdukController produkControl, PemesananController pemesananControl, int idUser) {
        this.filmControl = filmControl;
        this.jadwalControl = jadwalControl;
        this.kursiControl = kursiControl;
        this.produkControl = produkControl;
        this.pemesananControl = pemesananControl;
        this.idUser = idUser;
    }

    @Override
    public void showMenu() throws Exception {
        System.out.println("\n=== Menu Pemesanan ===");
        System.out.println("1. Pesan Tiket");
        System.out.println("Ketik apa saja untuk keluar");
        System.out.println("=================");
        System.out.print("Masukkan pilihan: ");

        String pilihan = input.nextLine().trim();
        switch (pilihan) {
            case "1":
                showPilihJadwal();
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
            }
    }

    public void showPilihJadwal() throws Exception {
        List<Jadwal> listJadwal = jadwalControl.getAll();

        if (listJadwal.isEmpty()) {
            System.out.println("Belum ada jadwal tersedia.");
            return;
        }

        System.out.println("\n=== Daftar Jadwal ===");
        int index = 1;
        for (Jadwal jadwal : listJadwal) {
            System.out.println(index + ". Film ID: " + jadwal.getFilmId() + " Judul Film: " + filmControl.getById(jadwal.getFilmId()).getJudul() +
                    " | Studio ID: " + jadwal.getStudioId() +
                    " | Tayang: " + jadwal.getTanggalTayang() +
                    " | Harga: Rp " + String.format("%,.0f", jadwal.getHarga()));
            index++;
        }

        System.out.print("Pilih nomor jadwal: ");
        int pilihanJadwal = Integer.parseInt(input.nextLine().trim());

        if (pilihanJadwal < 1 || pilihanJadwal > listJadwal.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        Jadwal jadwalDipilih = listJadwal.get(pilihanJadwal - 1);
        showPilihKursi(jadwalDipilih);
    }

    public void showPilihKursi(Jadwal jadwal) throws Exception {
        List<Kursi> listKursi = kursiControl.getAllByStudioId(jadwal.getStudioId());

        if (listKursi.isEmpty()) {
            System.out.println("Tidak ada kursi tersedia.");
            return;
        }

        System.out.println("\n=== Denah Kursi Studio " + jadwal.getStudioId() + " ===");
        System.out.println("X = sudah dipesan\n");

        // tampilkan kursi 3 baris, dibagi 2 kolom (kiri 3, kanan 3)
        int total = listKursi.size();
        int tengah = (int) Math.ceil(total / 2.0); // bagi 2 kelompok

        for (int i = 0; i < tengah; i++) {
            // kolom kiri
            Kursi kiri = listKursi.get(i);
            String labelKiri = kiri.getIsTersedia() ? kiri.getNoKursi() : "X";
            System.out.printf("%-6s", labelKiri);

            // kolom kanan 
            int indexKanan = i + tengah;
            if (indexKanan < total) {
                Kursi kanan = listKursi.get(indexKanan);
                String labelKanan = kanan.getIsTersedia() ? kanan.getNoKursi() : "X";
                System.out.printf("%-6s", labelKanan);
            }

            // baris baru setiap 3 kursi per kolom
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
        System.out.println();

        // pilih jumlah kursi
        System.out.print("Berapa kursi yang ingin dipesan? ");
        Integer jumlahKursi = Integer.parseInt(input.nextLine().trim());

        int studioId = jadwal.getStudioId();

        List<String> kursiDipilih = new ArrayList<>();
        for (int i = 0; i < jumlahKursi; i++) {
            System.out.print("Masukan no kursi ke-" + (i+1) + ": ");
            String noKursi = input.nextLine().trim();

            // validasi kursi
            Kursi kursi = kursiControl.get(noKursi, studioId);
            if (!(kursi != null && kursi.getIsTersedia())) {
                System.out.println("Kursi " + noKursi + " tidak tersedia. Silakan pilih lain.");
                i--; // ulangi input
                continue;  
            } 

            kursiDipilih.add(noKursi);
        }   

        showPilihMakanan(jadwal, kursiDipilih);
    }

    public void showPilihMakanan(Jadwal jadwal, List<String> kursiDipilih) throws Exception {
        System.out.println("\n=== Pilih Makanan (opsional) ===");

        List<Makanan> listMakanan = produkControl.getAllMakanan();

        if (listMakanan.isEmpty()) {
            System.out.println("Tidak ada makanan tersedia.");
            showKonfirmasi(jadwal, kursiDipilih, null, 0);
            return;
        }

        int index = 1;
        for (Makanan makanan : listMakanan) {
            System.out.println(index + ". " + makanan.getNama() +
                    " | Harga: Rp " + String.format("%,.0f", makanan.getHarga()) +
                    " | Stok: " + makanan.getStok());
            index++;
        }
        System.out.println("0. Lewati");

        System.out.print("Pilih makanan: ");
        int pilihanMakanan = Integer.parseInt(input.nextLine().trim());

        Makanan makananDipilih = null;

        if (pilihanMakanan == 0) {
            showKonfirmasi(jadwal, kursiDipilih, null, 0);
            return;
        }

        int jumlahMakanan = 0;

        if (pilihanMakanan > 0 && pilihanMakanan <= listMakanan.size()) {
            System.out.print("Jumlah: ");
            jumlahMakanan = Integer.parseInt(input.nextLine().trim());
            makananDipilih = listMakanan.get(pilihanMakanan - 1);
        } else {
            System.out.println("Pilihan tidak valid.");
        }

        showKonfirmasi(jadwal, kursiDipilih, makananDipilih, jumlahMakanan);
    }

    public void showKonfirmasi(Jadwal jadwal, List<String> kursiDipilih, Makanan makanan, int jumlahMakanan) throws Exception {
        String namaFilm = filmControl.getById(jadwal.getFilmId()).getJudul(); // tampilkan nama film

        System.out.println("\n=== Konfirmasi Pemesanan ===");
        System.out.println("Jadwal   : Film " + namaFilm + " | " + jadwal.getTanggalTayang());
        System.out.println("Kursi    : " + String.join(", ", kursiDipilih));
        System.out.println("Makanan  : " + (makanan != null ? makanan.getNama() : "Tidak ada"));

        // hitung harga tiket + pajak
        int jumlahTiketDipesan = kursiDipilih.size();
        double hargaTiket = produkControl.getTiketByNama(namaFilm).hitungTotalHarga(jumlahTiketDipesan);

        // hitun harga makanan + pajak
        double hargaMakanan = 0;
        if (makanan != null) {
            hargaMakanan = makanan.hitungTotalHarga(jumlahMakanan);
        };

        // hitung total harga
        double totalHarga = hargaTiket + hargaMakanan;
        System.out.println("Total    : Rp " + String.format("%,.0f", totalHarga));

        System.out.print("\nKonfirmasi pemesanan? (y/n): ");
        String konfirmasi = input.nextLine().trim();

        if (konfirmasi.equalsIgnoreCase("y")) {
            try {
                // ambil tiket berdasarkan jadwal
                Tiket tiket = produkControl.getTiketByNama(namaFilm);

                Pemesanan pemesanan = new Pemesanan(idUser, tiket, makanan, jadwal.getId(), kursiDipilih);
                pemesananControl.handleSave(pemesanan);

                // update stok dan jumlah terbeli makanan
               if (makanan != null) {
                    produkControl.handleUpdateJumlahMakanan(makanan.getId(), jumlahMakanan);
                    produkControl.handleUpdateStokMakanan(makanan.getId(), -jumlahMakanan);
                }

                // update stok dan jumlah terbeli makanan
                produkControl.handleUpdateJumlahTiket(tiket.getId(), kursiDipilih.size());
                produkControl.handleUpdateStokTiket(tiket.getId(), -kursiDipilih.size());

                System.out.println("Pemesanan berhasil!");
            } catch (Exception e) {
                System.out.println("Pemesanan gagal: " + e.getMessage());
            }
        } else {
            System.out.println("Pemesanan dibatalkan.");
        }
    }
}

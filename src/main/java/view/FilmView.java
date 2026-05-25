package view;

import java.util.List;

import controller.FilmController;
import controller.JadwalController;
import controller.ProdukController;
import controller.StudioController;

import model.Film;
import model.Jadwal;
import model.Studio;
import model.Tiket;

public class FilmView extends View {
    private FilmController filmControl;
    private StudioController studioControl; 
    private JadwalController jadwalControl;
    private ProdukController produkControl;

    public FilmView(FilmController filmController, StudioController studioControl, JadwalController jadwalControl, ProdukController produkControl) {
        this.filmControl = filmController;
        this.studioControl = studioControl;
        this.jadwalControl = jadwalControl;
        this.produkControl = produkControl;
    }

    @Override
    public void showMenu() throws Exception {
        System.out.println("\n=== Menu Kelola Film ===");
        System.out.println("1. Tambah Film");
        System.out.println("2. Lihat semua Film");
        System.out.println("Ketik apa saja untuk keluar");
        System.out.println("=================");
        System.out.print("Masukkan pilihan: ");

        String pilihan = View.input.nextLine(); 

        switch (pilihan) {
            case "1":
                showTambahFilm();
                break;
            case "2":
                showAllFilm();
                break;
            default:
                System.out.println("Terima kasih telah menggunakan aplikasi!");
                break;
        }
    }

    public void showTambahFilm() {
        System.out.print("Judul: ");
        String judul = input.nextLine();
        System.out.print("Durasi dalam menit: ");
        Integer durasi = Integer.parseInt(input.nextLine());
        System.out.print("Genre: ");
        String genre = input.nextLine();

        try {
            Film film = new Film(judul, durasi, genre);
            filmControl.handleSave(film);
            System.out.println("Berhasil menambahkan film baru");

            // atur jadwal film
            showAturJadwal(film);
        } catch (Exception e) {
            System.out.println("Gagal tambah film baru: " + e.getMessage());
        }
    }

    public void showAturJadwal(Film film) throws Exception {
        System.out.println("\n=== Tambah Jadwal untuk " + film.getJudul() + " ===");

        // tampilkan daftar studio
        List<Studio> listStudio = studioControl.getAll();
        if (listStudio.isEmpty()) {
            System.out.println("Belum ada Studio. Silahkan buat studio terlebih dahulu");
            return;
        }

        System.out.println("Pilih Studio:");
        for (Studio studio : listStudio) {
            System.out.println("- ID: " + studio.getId() + " | No Studio: " + studio.getNoStudio() + " | Kapasitas: " + studio.getKapasitas());
        }

        System.out.print("Studio ID: ");
        int studioId = Integer.parseInt(input.nextLine());
        System.out.print("Tanggal tayang (yyyy-MM-dd HH:mm): ");
        String tanggalTayang = input.nextLine();
        System.out.print("Harga: ");
        double harga = Double.parseDouble(input.nextLine());

        try {
            // ambil film id dari database
            int filmId = filmControl.getByJudul(film.getJudul()).getId();

            Jadwal jadwal = new Jadwal(filmId, studioId, harga, tanggalTayang);
            jadwalControl.handleSave(jadwal);
            System.out.println("Berhasil menambahkan jadwal!");

            // buat tiket sesuai dengan kapasitas
            int kapasitas = studioControl.getById(studioId).getKapasitas();
            System.out.println("Kapasitas: " + kapasitas);
            Tiket t = new Tiket(film.getJudul(), harga, "tiket", 0, kapasitas);
            produkControl.handleSave(t);
        } catch (Exception e) {
            System.out.println("Gagal tambah jadwal: " + e.getMessage());
        }
    }

    public void showAllFilm() throws Exception {
        try {
            List<Film> listFilm = filmControl.getAll();

            if (listFilm.isEmpty()) {
                System.out.println("Belum ada film");
                return;
            }

            System.out.println("\n=== Daftar Film ===");
            for (Film film : listFilm) {
                film.printDetail();
                System.err.println();
            }
  
        } catch (Exception e) {
            System.out.println("Gagal ambil data film: " + e.getMessage());
        }
    }

    public void showAllJadwal() throws Exception {
        try {
            List<Jadwal> listJadwal = jadwalControl.getAll();

            if (listJadwal.isEmpty()) {
                System.out.println("Tidak ada jadwal film yang tersedia.");
                return;
            }

            for (Jadwal jadwal : listJadwal) {
                String judul = filmControl.getById(jadwal.getFilmId()).getJudul();
                int studio = studioControl.getById(jadwal.getStudioId()).getNoStudio();
                int stok = produkControl.getTiketByNama(judul).getStok();
                
                System.out.println("\n=== "+ judul +" ===");
                System.out.println("Studio\t: " + studio);
                jadwal.printDetail();
                System.out.println("Stok\t: " + stok);
                
                System.out.println();

            }
        } catch (Exception e) {}
    }
}

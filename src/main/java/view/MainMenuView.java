package view;

import controller.FilmController;
import controller.JadwalController;
import controller.KursiController;
import controller.PemesananController;
import controller.ProdukController;
import controller.StudioController;
import controller.UserController;

public class MainMenuView extends View {
    StudioController studioControl;
    FilmController filmControl;
    JadwalController jadwalControl;
    KursiController kursiControl;
    ProdukController produkControl;
    PemesananController pemesananControl;

    public MainMenuView(StudioController sc, FilmController fc, JadwalController jc, KursiController kc, ProdukController pc, PemesananController pec) {
        this.studioControl = sc;
        this.filmControl = fc;
        this.jadwalControl = jc;
        this.kursiControl = kc;
        this.produkControl = pc;
        this.pemesananControl = pec;
    }
    
    public void showMenu() throws Exception {
        boolean running = true;

        while (running) {
            System.out.println("\n========================================");
            System.out.println("              MENU UTAMA                ");
            System.out.println("========================================");
            System.out.println("Hi, " + UserController.getLoggedInUser().getUsername());
            System.out.println("1. Pesan Tiket");
            System.out.println("2. Cek Jadwal");
            System.out.println("3. Kelola bioskop");
            System.out.println("Ketik apa saja untuk keluar");
            System.out.println("=================");
            System.out.print("Masukkan pilihan: ");

            String pilihan = View.input.nextLine(); 

            switch (pilihan) {
                case "1":
                    PemesananView pv = new PemesananView(filmControl, jadwalControl, kursiControl, produkControl, pemesananControl, UserController.getLoggedInUser().getId());
                    pv.showMenu();
                    break;
                case "2":
                    FilmView fv = new FilmView(filmControl, studioControl, jadwalControl, produkControl);
                    fv.showAllJadwal();
                    break;
                case "3":
                    showMenuKelola();
                    break;
                default:
                    System.out.println("Terima kasih telah menggunakan aplikasi!");
                    
                    running = false;
                    break;
            }
        }
        
    }

    public void showMenuKelola() throws Exception {
        System.out.println("\n=== Menu Kelola Bioskop ===");
        System.out.println("1. Kelola Studio");
        System.out.println("2. Kelola Produk");
        System.out.println("3. Kelola Film");
        System.out.println("Ketik apa saja untuk keluar");
        System.out.println("=================");
        System.out.print("Masukkan pilihan: ");

        String pilihan = View.input.nextLine(); 

        switch (pilihan) {
            case "1":
                StudioView sv = new StudioView(studioControl);
                sv.showMenu();
                break;
            case "2":
                ProdukView pv = new ProdukView(produkControl);
                pv.showMenu();
                break;
            case "3":
                FilmView fv = new FilmView(filmControl, studioControl, jadwalControl, produkControl);
                fv.showMenu();
            default:
                System.out.println("Terima kasih telah menggunakan aplikasi!");
                break;
        }
    }
}

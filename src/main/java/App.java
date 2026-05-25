import controller.FilmController;
import controller.JadwalController;
import controller.KursiController;
import controller.PemesananController;
import controller.ProdukController;
import controller.StudioController;
import controller.UserController;
import repository.FilmRepository;
import repository.JadwalRepository;
import repository.KursiRepository;
import repository.MakananRepository;
import repository.PemesananRepository;
import repository.ProdukRepository;
import repository.StudioRepository;
import repository.TiketRepository;
import repository.UserRepository;

import util.ConnectionUtil;

import view.MainMenuView;
import view.UserView;

import java.util.Scanner;
import javax.sql.DataSource;

public class App {
    public static void main(String[] args) throws Exception {
        boolean running = true;
        
        DataSource ds = ConnectionUtil.getDataSource();

        // user
        UserRepository userRepo = new UserRepository(ds);
        UserController userControl = new UserController(userRepo);

        // kursi repository
        KursiRepository kursiRepo = new KursiRepository(ds);
        
        // studio
        StudioRepository studioRepo = new StudioRepository(ds);
        StudioController studioControl = new StudioController(studioRepo, kursiRepo);

        // kursi controller
        KursiController kursiControl = new KursiController(kursiRepo, studioRepo);

        // film
        FilmRepository filmRepo = new FilmRepository(ds);
        FilmController filmControl = new FilmController(filmRepo);

        // jadwal
        JadwalRepository jadwalRepo = new JadwalRepository(ds);
        JadwalController jadwalControl = new JadwalController(jadwalRepo);

        // Tiket
        TiketRepository tiketRepo = new TiketRepository(ds);

        // makanan
        MakananRepository makananRepo = new MakananRepository(ds);

        // produk
        ProdukRepository produkRepo = new ProdukRepository(ds, tiketRepo, makananRepo);
        ProdukController produkControl = new ProdukController(produkRepo, tiketRepo, makananRepo);

        // pemesanan
        PemesananRepository pemesananRepo = new PemesananRepository(ds, tiketRepo, makananRepo, kursiRepo, jadwalRepo);
        PemesananController pemesananControl = new PemesananController(pemesananRepo);

        // view
        MainMenuView mv = new MainMenuView(studioControl, filmControl, jadwalControl, kursiControl, produkControl, pemesananControl);
        UserView uv = new UserView(userControl, mv);
        
        try (Scanner scanner = new Scanner(System.in)) {
            while (running) {
                System.out.println("=== Sistem Pemesanan Tiket Bioskop ===");
                uv.showMenu();
            }
        }
    }
}

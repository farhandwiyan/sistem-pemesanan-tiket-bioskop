package view;

import java.util.List;

import controller.ProdukController;
import model.Makanan;
import model.Tiket;

public class ProdukView extends View {
    private ProdukController produkControl;

    public ProdukView(ProdukController produkControl) {
        this.produkControl = produkControl;
    }

    @Override
    public void showMenu() throws Exception {
        System.out.println("\n=== Menu Kelola Produk ===");
        System.out.println("1. Tambah Makanan");
        System.out.println("2. Lihat semua makanan");
        System.out.println("3. Lihat semua tiket");
        System.out.println("Ketik apa saja untuk keluar");
        System.out.println("=================");
        System.out.print("Masukkan pilihan: ");

        String pilihan = View.input.nextLine(); 

        switch (pilihan) {
            case "1":
                showTambahMakanan();
                break;
            case "2":
                showAllMakanan();
                break;
            case "3":
                showAllTiket();
                break;
            default:
                System.out.println("Terima kasih telah menggunakan aplikasi!");
                break;
        }
    }

    public void showTambahMakanan() throws Exception {
        System.out.print("Nama: ");
        String nama = input.nextLine();
        System.out.print("Harga: ");
        Double harga = Double.parseDouble(input.nextLine());
        System.out.print("Jenis: ");
        String jenis = input.nextLine();
        System.out.print("Stok: ");
        Integer stok = Integer.parseInt(input.nextLine());

        try {
            Makanan makanan = new Makanan(nama, harga, jenis, 0, stok);
            produkControl.handleSave(makanan);
            System.out.println("Berhasil menambahkan makanan baru");
        } catch (Exception e) {
            System.out.println("Gagal tambah makanan baru: " + e.getMessage());
        }
    }

    public void showAllMakanan() throws Exception {
        List<Makanan> listMakanan = produkControl.getAllMakanan();
        
        if (listMakanan.isEmpty()) {
            System.out.println("Makanan tidak tersedia.");
            return;
        } else {
            System.out.println("\n=== Daftar Makanan ===");
            for (Makanan m : listMakanan) {
                m.printDetail();
                System.out.println();
            }
        }
    }

    public void showAllTiket() throws Exception {
        List<Tiket> listTiket = produkControl.getAllTiket();

        if (listTiket.isEmpty()) {
            System.out.println("Tiket tidak tersedia");
            return;
        } else {
            System.out.println("\n=== Daftar Tiket ===");
            for (Tiket t : listTiket) {
                t.printDetail();
                System.out.println();
            }
        }
    }
}

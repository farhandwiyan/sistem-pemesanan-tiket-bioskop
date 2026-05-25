package view;

import java.util.List;

import controller.StudioController;
import model.Studio;

public class StudioView extends View {
    private StudioController studioControl;

    public StudioView(StudioController studioController) {
        this.studioControl = studioController;
    }

    @Override
    public void showMenu() throws Exception {
        System.out.println("\n=== Menu Kelola Studio ===");
        System.out.println("1. Tambah Studio");
        System.out.println("2. Hapus Studio");
        System.out.println("3. Lihat semua studio");
        System.out.println("Ketik apa saja untuk keluar");
        System.out.println("=================");
        System.out.print("Masukkan pilihan: ");

        String pilihan = View.input.nextLine(); 

        switch (pilihan) {
            case "1":
                showTambahStudio();
                break;
            case "2":
                showHapusStudio();
                break;
            case "3":
                showAllStudio();
                break;
            default:
                System.out.println("Terima kasih telah menggunakan aplikasi!");
                break;
        }
    }

    public void showTambahStudio() throws Exception {
        System.out.print("No Studio: ");
        Integer noStudio = Integer.parseInt(input.nextLine());
        System.out.print("Kapasitas: ");
        Integer kapasitas = Integer.parseInt(input.nextLine());

        try {
            studioControl.handleSave(new Studio(noStudio, kapasitas));
            System.out.println("Berhasil menambahkan studio baru");
        } catch (Exception e) {
            System.out.println("Gagal tambah studio baru: " + e.getMessage());
        }
    }

    public void showHapusStudio() {
        System.out.print("No Studio: ");
        String noStudio = input.nextLine();

        try {
            studioControl.handleDeleteStudio(noStudio);
            System.out.println("Berhasil menghapus studio baru");
        } catch (Exception e) {
            System.out.println("Gagal hapus studio: " + e.getMessage());
        }
    }

    public void showAllStudio() {
        try {
            List<Studio> listStudio = studioControl.getAll(); 

            if (listStudio.isEmpty()) {
                System.out.println("Belum ada studio.");
                return;
            }

            System.out.println("\n=== Daftar Studio ===");
            int index = 1; 
            for (Studio studio : listStudio) {
                System.out.print(index + ". ");
                studio.printDetail();
                index++;
            }

        } catch (Exception e) {
            System.out.println("Gagal ambil data studio: " + e.getMessage());
        }
    }
}

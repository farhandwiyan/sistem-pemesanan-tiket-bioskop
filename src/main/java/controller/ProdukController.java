package controller;

import java.util.List;

import model.Makanan;
import model.Tiket;
import repository.MakananRepository;
import repository.ProdukRepository;
import repository.TiketRepository;

public class ProdukController implements Controller<Object> {
    ProdukRepository produkRepo;
    TiketRepository tiketRepo;
    MakananRepository makananRepo;

    public ProdukController(ProdukRepository produkRepo, TiketRepository tiketRepo, MakananRepository makananRepo) {
        this.produkRepo = produkRepo;
        this.tiketRepo = tiketRepo;
        this.makananRepo = makananRepo;
    }

    @Override
    public void handleSave(Object item) throws Exception {
        produkRepo.save(item);
    }

    public void handleUpdateStokTiket(int id, int jumlah) throws Exception {
        tiketRepo.updateStok(id, jumlah);
    }

    public void handleUpdateJumlahTiket(int id, int jumlah) throws Exception {
        tiketRepo.updateJumlah(id, jumlah);
    }

    public Tiket getTiketByNama(String nama) throws Exception {
        return tiketRepo.findTiketLengkapByNama(nama);
    }

    public void handleUpdateStokMakanan(int id, int jumlah) throws Exception {
        makananRepo.updateStok(id, jumlah);
    }

    public void handleUpdateJumlahMakanan(int id, int jumlah) throws Exception {
        makananRepo.updateJumlah(id, jumlah);
    }

    public Makanan getMakananByNama(String nama) throws Exception {
        return makananRepo.findTiketLengkapByNama(nama);
    }

    public List<Tiket> getAllTiket() throws Exception {
        return tiketRepo.findAll();
    }

    public List<Makanan> getAllMakanan() throws Exception {
        return makananRepo.findAll();
    }

    @Override
    public List<Object> getAll() throws Exception {
        return produkRepo.findAll();
    }
}

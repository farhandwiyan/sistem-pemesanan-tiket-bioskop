package controller;

import java.util.List;

import model.Pemesanan;

import repository.PemesananRepository;

public class PemesananController implements Controller<Pemesanan> {
    private PemesananRepository pemesananRepo;

    public PemesananController(PemesananRepository pemesananRepo) {
        this.pemesananRepo = pemesananRepo;
    }

    @Override
    public void handleSave(Pemesanan pemesanan) throws Exception {
        pemesananRepo.save(pemesanan);

        
    }

    public Pemesanan getByIdPemesan(int idPemesan) throws Exception {
        return pemesananRepo.findByPemesanId(idPemesan);
    }

    @Override
    public List<Pemesanan> getAll() throws Exception {
        return pemesananRepo.findAll();
    }
}

package controller;

import java.util.List;

import model.Kursi;
import model.Studio;
import repository.KursiRepository;
import repository.StudioRepository;

public class StudioController implements Controller<Studio> {
    private StudioRepository studioRepo;
    private KursiRepository kursiRepo;

    public StudioController(StudioRepository studioRepo, KursiRepository kursiRepo) {
        this.studioRepo = studioRepo;
        this.kursiRepo = kursiRepo;
    }

    @Override
    public void handleSave(Studio studio) throws Exception{
        if (studio.getKapasitas() <= 0) {
            throw new IllegalArgumentException("Kapasitas tidak boleh <= 0");
        } else {
            studioRepo.save(studio);

            // cari id studio
            int studioId = studioRepo.findByNo(String.valueOf(studio.getNoStudio())).getId();

            // buat kursi sebanyak kapastias studio
            for (int i = 1; i <= studio.getKapasitas(); i++) {
                String noKursi = "K" + i; // K1, K2, K3, dst
                
                Kursi kursi = new Kursi(studioId, noKursi, true);
                kursiRepo.save(kursi);
             }
        }
    }
 
    public void handleDeleteStudio(String noStudio) throws IllegalArgumentException, Exception {
        Studio studio = studioRepo.findByNo(noStudio);

        if (studio != null) {
            studioRepo.deleteStudio(noStudio);
        } else {
            throw new IllegalArgumentException("Studio tidak ditemukan");
        }
    }

    public Studio getById(int id) throws Exception {
        return studioRepo.findById(id);
    }

    public List<Studio> getAllStudio() throws Exception {
        return studioRepo.findAll();
    }

    @Override
    public List<Studio> getAll() throws Exception {
        return studioRepo.findAll();
    }
}

package controller;

import java.util.List;

import model.Kursi;
import repository.KursiRepository;
import repository.StudioRepository;

public class KursiController {
    private KursiRepository kursiRepo;
    private StudioRepository studioRepo;

    public KursiController(KursiRepository kursiRepo, StudioRepository studioRepo) {
        this.kursiRepo = kursiRepo;
        this.studioRepo = studioRepo;
    }

    public Kursi get(String noKursi, int studioId) throws Exception {
        return kursiRepo.find(noKursi, studioId);
    }

    public void handlePesan(String noKursi, int studioId, boolean status) throws Exception {
        if (kursiRepo.find(noKursi, studioId) != null) {
            kursiRepo.updateStatus(noKursi, studioId, status);
        }
    }

    public List<Kursi> getAllByStudioId(int StudioId) throws Exception {
        if (studioRepo.findById(StudioId) != null) {
            return kursiRepo.findAllByStudioId(StudioId);
        }
        throw new IllegalArgumentException("Studio tidak ditemukan");
    }
}

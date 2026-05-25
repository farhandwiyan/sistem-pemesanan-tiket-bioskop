package controller;

import java.util.List;

import model.Jadwal;
import repository.JadwalRepository;

public class JadwalController implements Controller<Jadwal> {
    private JadwalRepository jadwalRepo;

    public JadwalController(JadwalRepository jadwalRepo) {
        this.jadwalRepo = jadwalRepo;
    }

    @Override
    public void handleSave(Jadwal jadwal) throws Exception {
        jadwalRepo.save(jadwal);
    }

    @Override 
    public List<Jadwal> getAll() throws Exception {
        return jadwalRepo.findAll();
    }
}

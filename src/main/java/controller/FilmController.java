package controller;

import java.util.List;

import model.Film;
import repository.FilmRepository;

public class FilmController implements Controller<Film> {
    FilmRepository filmRepo;
    
    public FilmController(FilmRepository filmRepo) {
        this.filmRepo = filmRepo;
    }

    @Override
    public void handleSave(Film film) throws Exception {
        filmRepo.save(film);
    }

    public Film getByJudul(String judul) throws Exception {
        return filmRepo.FindByJudul(judul);
    }

    public Film getById(int id) throws Exception {
        return filmRepo.FindById(id);
    }

    @Override 
    public List<Film> getAll() throws Exception {
        return filmRepo.findAll();
    }
}

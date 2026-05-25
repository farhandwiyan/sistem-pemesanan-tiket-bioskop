package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Film;

public class FilmRepository extends BaseRepository implements Repository<Film> {
    public FilmRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void save(Film film) throws Exception {
        String query = 
            "INSERT INTO films (judul, durasi, genre) VALUES ('" + 
            film.getJudul() + "', " + 
            film.getDurasi() + ", '" + 
            film.getGenre() + "')";

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        }
    } 

    public Film FindById(int id) throws SQLException {
        String query = "SELECT * FROM films WHERE id = " + id;

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return new Film (
                    rs.getInt("id"),
                    rs.getString("judul"),
                    rs.getInt("durasi"),
                    rs.getString("genre")
                );
            }
        }

        return null;
    }

    public Film FindByJudul(String judul) throws Exception {
        String query = "SELECT * FROM films WHERE judul = '"+ judul + "'";

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return new Film (
                    rs.getInt("id"),
                    rs.getString("judul"),
                    rs.getInt("durasi"),
                    rs.getString("genre")
                );
            }
        }

        return null;
    }

    public List<Film> findAll() throws Exception {
        List<Film> listFilm = new ArrayList<>();
        String query = "SELECT * FROM films";

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Film film = new Film (
                    rs.getInt("id"),
                    rs.getString("judul"),
                    rs.getInt("durasi"),
                    rs.getString("genre")
                );

                listFilm.add(film);
            }
        }

        return listFilm;
    }
}

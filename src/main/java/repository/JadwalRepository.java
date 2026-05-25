package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Jadwal;

public class JadwalRepository extends BaseRepository implements Repository<Jadwal> {
    public JadwalRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void save(Jadwal jadwal) throws Exception {
        String query = 
            "INSERT INTO jadwal (film_id, studio_id, jam_tayang, harga) VALUES (" +
            jadwal.getFilmId() + ", " +
            jadwal.getStudioId() + ", '" +
            jadwal.getTanggalTayang().toString().replace("T", " ") + "', " +
            jadwal.getHarga() + ")";

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        }
    }

    public Jadwal findById(int id) throws Exception {
        String query = "SELECT * FROM jadwal WHERE id = " + id;

        try (Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query)) {

            if (rs.next()) {
                return new Jadwal(
                    rs.getInt("id"),
                    rs.getInt("film_id"),
                    rs.getInt("studio_id"),
                    rs.getDouble("harga"),
                    rs.getTimestamp("jam_tayang").toLocalDateTime()
                );
            }
        }

        return null;

    }

    @Override
    public List<Jadwal> findAll() throws Exception {
        List<Jadwal> listJadwal = new ArrayList<>();
        String query = "SELECT * FROM jadwal";

        try (Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                Jadwal jadwal = new Jadwal(
                    rs.getInt("id"),
                    rs.getInt("film_id"),
                    rs.getInt("studio_id"),
                    rs.getDouble("harga"),
                    rs.getTimestamp("jam_tayang").toLocalDateTime()
                );
                
                listJadwal.add(jadwal);
            }
        }

        return listJadwal;
    }
    
}

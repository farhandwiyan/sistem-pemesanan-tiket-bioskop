package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Kursi;

public class KursiRepository extends BaseRepository implements Repository<Kursi>{
    public KursiRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void save(Kursi kursi) throws Exception {
        String query = "INSERT INTO kursi(studio_id, nomor_kursi, is_tersedia) VALUES(" +
            kursi.getNoStudio() + ", '" +
            kursi.getNoKursi() + "', " +
            kursi.getIsTersedia() + ")";
        
        try (Connection con = dataSource.getConnection();) {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        }
    }

    public Kursi find(String noKursi, int studioId) throws SQLException {
        String query = "SELECT * FROM kursi WHERE nomor_kursi = '" + noKursi + "' AND studio_id = " + studioId;

        try (Connection con = dataSource.getConnection();) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                 return new Kursi(
                    rs.getInt("id"),
                    rs.getInt("studio_id"),
                    rs.getString("nomor_kursi"),
                    rs.getBoolean("is_tersedia")
                );
            }
        }

        return null; 
    }

    public void updateStatus(String noKursi, int studioId, boolean status) throws Exception {
        String query = "UPDATE kursi SET is_tersedia = " + status + " WHERE nomor_kursi = '" + noKursi + "' AND studio_id = " + studioId;

        try (Connection con = dataSource.getConnection();) {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        }
    }

    public List<Kursi> findAllByStudioId(int studioId) throws Exception {
        List<Kursi> listKursi = new ArrayList<>();
        String query = "SELECT * FROM kursi WHERE studio_id = '"+ studioId + "'";

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Kursi kursi = new Kursi(
                    rs.getInt("id"),
                    rs.getInt("studio_id"),
                    rs.getString("nomor_kursi"),
                    rs.getBoolean("is_tersedia")
                );

                listKursi.add(kursi);
            }
        }

        return listKursi;
    }

    @Override
    public List<Kursi> findAll() throws Exception {
        List<Kursi> listKursi = new ArrayList<>();
        String query = "SELECT * FROM kursi";

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Kursi kursi = new Kursi(
                    rs.getInt("id"),
                    rs.getInt("studio_id"),
                    rs.getString("nomor_kursi"),
                    rs.getBoolean("is_tersedia")
                );

                listKursi.add(kursi);
            }
        }

        return listKursi;
    }
}

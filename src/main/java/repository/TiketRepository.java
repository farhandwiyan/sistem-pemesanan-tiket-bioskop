package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Tiket;

public class TiketRepository extends BaseRepository implements Repository<Tiket> {

    public TiketRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void save(Tiket tiket) throws Exception {
        int id = findByNama(tiket.getNama()).getId();

        String query = "INSERT INTO tiket (id, jumlah) VALUES (" 
                        + id + ", " + tiket.getJumlah() + ")";

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        }
    } 

    public void updateStok(int id, int jumlah) throws Exception {
        String query = "UPDATE produk SET stok = stok + " + jumlah + " WHERE id = " + id;

        try (Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();) {
            
            statement.executeUpdate(query);
        }
    }

    public void updateJumlah(int id, int jumlah) throws Exception {
        String query = "UPDATE tiket SET jumlah = jumlah + " + jumlah + " WHERE id = " + id;

        try (Connection con = dataSource.getConnection();
            Statement statement = con.createStatement()) {
                
            statement.executeUpdate(query);
        }
    }

    // helper untuk insert data
    public Tiket findByNama(String nama) throws Exception {
        String query =
            "SELECT * FROM produk WHERE nama = '"+ nama +"'";
        
        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return new Tiket(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getDouble("harga"),
                    rs.getString("jenis"),
                    rs.getInt("stok")
                );
            }
        }

        return null;
    }

    public Tiket findTiketLengkapByNama(String nama) throws Exception {
        String query = "SELECT p.*, t.jumlah FROM produk p " +
                    "JOIN tiket t ON p.id = t.id " +
                    "WHERE p.nama = '" + nama + "'";

        try (Connection con = dataSource.getConnection()){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return new Tiket(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getDouble("harga"),
                    rs.getString("jenis"),
                    rs.getInt("jumlah"),
                    rs.getInt("stok")
                );
            }
        }
        return null;
    }

    public Tiket findById(int id) throws Exception {
        String query = "SELECT p.*, t.jumlah FROM produk p " +
                    "JOIN tiket t ON p.id=t.id " +
                    "WHERE p.id=" + id;

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return new Tiket(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getDouble("harga"),
                    rs.getString("jenis"),
                    rs.getInt("jumlah"),
                    rs.getInt("stok")
                );
            }  
        } 

        return null;
    }

    @Override
    public List<Tiket> findAll() throws Exception {
        List<Tiket> listTiket = new ArrayList<>();
        String query = "SELECT p.*, t.jumlah FROM produk p " +
                        "JOIN tiket t ON p.id=t.id";

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Tiket tiket = new Tiket(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getDouble("harga"),
                    rs.getString("jenis"),
                    rs.getInt("jumlah"),
                    rs.getInt("stok")
                );

                listTiket.add(tiket);
            }
        }

        return listTiket;
    }
}

package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Makanan;

public class MakananRepository extends BaseRepository implements Repository<Makanan> {
    public MakananRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void save(Makanan makanan) throws Exception {
        try (Connection con = dataSource.getConnection()) {
            int id = findByNama(makanan.getNama()).getId();
                
            String query = "INSERT INTO makanan (id, jumlah) VALUES (" 
                        + id + ", " + makanan.getJumlah() + ")";
                
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
        String query = "UPDATE makanan SET jumlah = jumlah + " + jumlah + " WHERE id = " + id;

        try (Connection con = dataSource.getConnection();
            Statement statement = con.createStatement()) {
                
            statement.executeUpdate(query);
        }
    }

    public Makanan findByNama(String nama) throws Exception {
        String query =
            "SELECT * FROM produk WHERE nama = '"+ nama +"'";
        
        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return new Makanan(
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

    public Makanan findTiketLengkapByNama(String nama) throws Exception {
        String query = "SELECT p.*, m.jumlah FROM produk p " +
                    "JOIN makanan m ON p.id = m.id " +
                    "WHERE p.nama = '" + nama + "'";

        try (Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query)) {

            if (rs.next()) {
                return new Makanan(
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

    public Makanan findById(int id) throws Exception {
        String query = "SELECT p.*, m.jumlah FROM produk p " +
                    "JOIN makanan m ON p.id=m.id " +
                    "WHERE p.id=" + id;

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return new Makanan(
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
    public List<Makanan> findAll() throws Exception {
        List<Makanan> listMakanan = new ArrayList<>();
        String query = "SELECT p.*, m.jumlah FROM produk p " +
                        "JOIN makanan m ON p.id=m.id";

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Makanan makanan = new Makanan(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getDouble("harga"),
                    rs.getString("jenis"),
                    rs.getInt("jumlah"),
                    rs.getInt("stok")
                );

                listMakanan.add(makanan);
            }
        }

        return listMakanan;
    }
    
}

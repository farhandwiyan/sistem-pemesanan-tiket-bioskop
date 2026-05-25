package repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Makanan;
import model.Tiket;

public class ProdukRepository extends BaseRepository implements Repository<Object> {
    TiketRepository tiketRepo;
    MakananRepository makananRepo;

    public ProdukRepository(DataSource dataSource, TiketRepository tiketRepo, MakananRepository makananRepo) {
        super(dataSource);
        this.makananRepo = makananRepo;
        this.tiketRepo = tiketRepo;
    }

    @Override
    public void save(Object item) throws Exception {
        try (Connection con = dataSource.getConnection()) {
            if (item instanceof Tiket) {
                Tiket t = (Tiket) item;
                String query = "INSERT INTO produk (nama, harga, jenis, stok) VALUES ('" 
                    + t.getNama() + "', " 
                    + t.getHarga() + ", " 
                    + "'tiket', " 
                    + t.getStok() + ")";
                
                Statement statement = con.createStatement();
                statement.executeUpdate(query);

                // Simpan ke tabel tiket
                tiketRepo.save(t);
            } else if (item instanceof Makanan) {
                Makanan m = (Makanan) item;
                String query = "INSERT INTO produk (nama, harga, jenis, stok) VALUES ('" 
                    + m.getNama() + "', " 
                    + m.getHarga() + ", " 
                    + "'makanan', " 
                    + m.getStok() + ")";

                Statement statement = con.createStatement();
                statement.executeUpdate(query);

                // simpan ke tabel makanan
                makananRepo.save(m);
            }
        } 
    }

    @Override
    public List<Object> findAll() throws Exception {
        List<Object> listProduk = new ArrayList<>();
        String query = "SELECT p.*, t.jumlah AS jumlah_tiket, m.jumlah AS jumlah_makanan " +
                    "FROM produk p " +
                    "LEFT JOIN tiket t ON p.id = t.id " +
                    "LEFT JOIN makanan m ON p.id = m.id";
        
        try (Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query)) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                double harga = rs.getDouble("harga");
                String jenis = rs.getString("jenis");
                int stok = rs.getInt("stok");
                
                if ("tiket".equals(jenis)) {
                    int jumlah = rs.getInt("jumlah");
                    Tiket t = new Tiket(id, nama, harga, jenis, jumlah, stok);

                    listProduk.add(t);
                } else if ("makanan".equals(jenis)) {
                    int jumlah = rs.getInt("jumlah");
                    Makanan m = new Makanan(id, nama, harga, jenis, jumlah, stok);

                    listProduk.add(m);
                }
            }
        }
        
        return listProduk;
    }
    
}

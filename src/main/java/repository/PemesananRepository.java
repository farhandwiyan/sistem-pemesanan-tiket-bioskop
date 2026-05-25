package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import model.Makanan;
import model.Pemesanan;
import model.Tiket;

public class PemesananRepository extends BaseRepository implements Repository<Pemesanan> {
    private TiketRepository tiketRepo;
    private MakananRepository makananRepo;
    private KursiRepository kursiRepo;
    private JadwalRepository jadwalRepo;

    public PemesananRepository(DataSource dataSource, TiketRepository tiketRepo, MakananRepository makananRepo, KursiRepository kursiRepo, JadwalRepository jadwalRepo) {
        super(dataSource);
        this.tiketRepo = tiketRepo;
        this.makananRepo = makananRepo;
        this.kursiRepo = kursiRepo;
        this.jadwalRepo = jadwalRepo;

    }

    @Override
    public void save(Pemesanan pemesanan) throws Exception {
        int idUser = pemesanan.getIdPemesan();
        String tanggal = pemesanan.getTanggalPesan().toString().replace("T", " ");
        int idTiket = pemesanan.getTiket().getId();

        String idMakananStr = "NULL";
        if (pemesanan.getMakanan() != null) {
            idMakananStr = String.valueOf(pemesanan.getMakanan().getId());
        }

        String query = "INSERT INTO pemesanan (id_pemesan, tanggal_pesan, id_tiket, id_makanan, id_jadwal) " +
                "VALUES (" + idUser + ", '" + tanggal + "', " + idTiket + ", " + idMakananStr + ", "+ pemesanan.getIdJadwal() + ")";

        try (Connection con = dataSource.getConnection();
            Statement statement = con.createStatement()) {

            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            // tangkap id pemesanan yang baru dibuat
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int idPemesanan = rs.getInt(1);
                int studioId = jadwalRepo.findById(pemesanan.getIdJadwal()).getStudioId();
                

                // simpan setiap kursi ke tabel pemesanan_kursi
                for (String noKursi : pemesanan.getNoKursi()) {
                    String kursiQuery = "INSERT INTO pemesanan_kursi (pemesanan_id, no_kursi) " +
                            "VALUES (" + idPemesanan + ", '" + noKursi + "')";
                    statement.executeUpdate(kursiQuery);

                    kursiRepo.updateStatus(noKursi, studioId, false);
                }
            }
        }
    }

    public Pemesanan findByPemesanId(int idPemesan) throws Exception {
        String query = "SELECT p.*, GROUP_CONCAT(pk.no_kursi) AS no_kursi " +
                   "FROM pemesanan p " +
                   "LEFT JOIN pemesanan_kursi pk ON p.id = pk.pemesanan_id " +
                   "GROUP BY p.id " +
                   "WHERE p.id_pemesan=" + idPemesan;
        
        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                int idTiket = rs.getInt("id_tiket");
                Tiket tiket = tiketRepo.findById(idTiket);

                Integer idMakanan = (Integer) rs.getObject("id_makanan");
                Makanan makanan = null;
                if (idMakanan != null) {
                    makanan = makananRepo.findById(idMakanan);
                }

                String noKursi = rs.getString("no_kursi");
                List<String> listKursi = new ArrayList<>();
                if (noKursi != null && !noKursi.isEmpty()) {
                    listKursi = Arrays.asList(noKursi.split(","));
                }

                return new Pemesanan(
                    rs.getInt("id"),
                    rs.getInt("id_pemesan"),
                    rs.getTimestamp("tanggal_pesan").toLocalDateTime(),
                    tiket,
                    makanan,
                    rs.getInt("id_jadwal"),
                    listKursi
                );
            }
        }

        return null;
    }

    @Override
    public List<Pemesanan> findAll() throws Exception {
        String query = "SELECT p.*, GROUP_CONCAT(pk.no_kursi) AS no_kursi " +
                   "FROM pemesanan p " +
                   "LEFT JOIN pemesanan_kursi pk ON p.id = pk.pemesanan_id " +
                   "GROUP BY p.id";

        List<Pemesanan> pemesananList = new ArrayList<>();

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int idTiket = rs.getInt("id_tiket");
                Tiket tiket = tiketRepo.findById(idTiket);

                Integer idMakanan = (Integer) rs.getObject("id_makanan");
                Makanan makanan = null;
                if (idMakanan != null) {
                    makanan = makananRepo.findById(idMakanan);
                }

                String noKursi = rs.getString("no_kursi");
                List<String> listKursi = new ArrayList<>();
                if (noKursi != null && !noKursi.isEmpty()) {
                    listKursi = Arrays.asList(noKursi.split(","));
                }

                Pemesanan pemesanan = new Pemesanan(
                    rs.getInt("id"),
                    rs.getInt("id_pemesan"),
                    rs.getTimestamp("tanggal_pesan").toLocalDateTime(),
                    tiket,
                    makanan,
                    rs.getInt("id_jadwal"),
                    listKursi
                );

                pemesananList.add(pemesanan);
            }
        }

        return pemesananList;
    }
}

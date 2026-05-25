package model;

import java.time.LocalDateTime;
import java.util.List;

public class Pemesanan {
    private int id;
    private int idPemesan;
    private LocalDateTime tanggalPesan;
    private Tiket tiket;
    private Makanan makanan;
    private int idJadwal;
    private List<String> noKursi;

    public Pemesanan(int idPemesan, Tiket tiket, Makanan makanan, int idJadwal, List<String> noKursi) {
        this.idPemesan = idPemesan;
        this.tanggalPesan = LocalDateTime.now();
        this.tiket = tiket;
        this.makanan = makanan;
        this.idJadwal = idJadwal;
        this.noKursi = noKursi;
    }

    public Pemesanan(int id, int idPemesan, LocalDateTime tanggalPesan, Tiket tiket, Makanan makanan, int idJadwal, List<String> noKursi) {
        this.id = id;
        this.idPemesan = idPemesan;
        this.tanggalPesan = tanggalPesan;
        this.tiket = tiket;
        this.makanan = makanan;
        this.idJadwal = idJadwal;
        this.noKursi = noKursi;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPemesan() {
        return this.idPemesan;
    }

    public void setIdPemesan(int idPemesan) {
        this.idPemesan = idPemesan;
    }

    public LocalDateTime getTanggalPesan() {
        return this.tanggalPesan;
    }

    public void setTanggalPesan(LocalDateTime tanggalPesan) {
        this.tanggalPesan = tanggalPesan;
    }

    public Tiket getTiket() {
        return this.tiket;
    }

    public void setTiket(Tiket tiket) {
        this.tiket = tiket;
    }

    public Makanan getMakanan() {
        return this.makanan;
    }

    public void setMakanan(Makanan makanan) {
        this.makanan = makanan;
    }    

    public int getIdJadwal() { 
        return this.idJadwal; 
    }

    public void setIdJadwal(int idJadwal) { 
        this.idJadwal = idJadwal; 
    }

    public List<String> getNoKursi() {
        return this.noKursi;
    }

    public void setNoKursi(List<String> noKursi) {
        this.noKursi = noKursi;
    }
}

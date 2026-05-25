package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Jadwal implements PrintInfo {
    private int id;
    private int filmId;
    private int studioId;
    private double harga;
    private LocalDateTime tanggalTayang;

    // kontruktor untuk buat jadwal baru
    public Jadwal(int filmId, int studioId, double harga, String tanggalTayang) {
        this.filmId = filmId;
        this.studioId = studioId;
        this.harga = harga;
        setTanggalTayang(tanggalTayang);
    }

    // konstruktor untuk ambil jadwal dari database
    public Jadwal(int id, int filmId, int studioId, double harga, LocalDateTime tanggalTayang) {
        this.id = id;
        this.filmId = filmId;
        this.studioId = studioId;
        this.harga = harga;
        this.tanggalTayang = tanggalTayang;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
         this.id = id;
    }

    public int getFilmId() {
        return this.filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getStudioId() {
        return this.studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public double getHarga() {
        return this.harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public LocalDateTime getTanggalTayang() {
        return this.tanggalTayang;
    }

    public void setTanggalTayang(String tanggal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.tanggalTayang = LocalDateTime.parse(tanggal, formatter);
    }

    @Override
    public void printDetail() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        System.out.println("Tayang\t: " + tanggalTayang.format(formatter));
        System.out.println("Harga\t: Rp " + String.format("%,.0f", harga));
    }
}

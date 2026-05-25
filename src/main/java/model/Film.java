package model;

public class Film implements PrintInfo {
    private int id;
    private String judul;
    private int durasi;
    private String genre;

    // konstruktor untuk buat film baru
    public Film(String judul, int durasi, String genre) {
        this.judul = judul;
        this.durasi = durasi;
        this.genre = genre;
    }

    // konstruktor untuk ambil film dari database
    public Film(int id, String judul, int durasi, String genre) {
        this.id = id;
        this.judul = judul;
        this.durasi = durasi;
        this.genre = genre;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return this.judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getDurasi() {
        return this.durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void printDetail() {
        System.out.println("=== " + getJudul() + " ===");
        System.out.println("Durasi: " + getDurasi());
        System.out.println("Genre: " + getGenre());
    }
}

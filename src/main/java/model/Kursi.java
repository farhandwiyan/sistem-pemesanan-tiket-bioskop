package model;

public class Kursi {
    private int id;
    private int noStudio;
    private String noKursi;
    private boolean isTersedia;

    // konstruktor untuk buat kursi baru
    public Kursi(int noStudio, String noKursi, boolean IsTersedia) {
        this.noStudio = noStudio;
        this.noKursi = noKursi;
        this.isTersedia = IsTersedia;
    }

    // konstruktor untuk ambil kursi dari database
    public Kursi(int id, int noStudio, String noKursi, boolean IsTersedia) {
        this.id = id;
        this.noStudio = noStudio;
        this.noKursi = noKursi;
        this.isTersedia = IsTersedia;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoStudio() {
        return this.noStudio;
    }

    public void setNoStudio(int noStudio) {
        this.noStudio = noStudio;
    }

    public String getNoKursi() {
        return this.noKursi;
    }

    public void setNoKursi(String noKursi) {
        this.noKursi = noKursi;
    }

    public boolean getIsTersedia() {
        return this.isTersedia;
    }

    public void setIsTersedia(boolean status) {
        this.isTersedia = status;
    }
}

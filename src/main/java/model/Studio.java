package model;

public class Studio implements PrintInfo {
    private int id;
    private int noStudio;
    private int kapasitas;

    // konstruktor untuk amnbil data dari database
    public Studio(int id, int noStudio, int kapasitas) {
        this.id = id;
        this.noStudio = noStudio;
        this.kapasitas = kapasitas;
    }

    // konstruktor untuk buat studio baru
    public Studio(int noStudio, int kapasitas) {
        this.noStudio = noStudio;
        this.kapasitas = kapasitas;
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

    public void setNoStudio(int nomor) {
        this.noStudio = nomor;
    }

    public int getKapasitas() {
        return this.kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    @Override
    public void printDetail() {
        System.out.println("Studio " + getNoStudio() + " memiliki kapasitas " + getKapasitas());
    }
}

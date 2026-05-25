package model;

public class Tiket extends Produk implements PrintInfo {
    private int jumlah; // jumlah tiket yang sudah terjual

    // konstruktor untuk membuat ke database
    public Tiket(String nama, double harga, String jenis, int jumlah, int stok) {
        super(nama, harga, jenis, stok);
        this.jumlah = jumlah;
    }

    // konstruktor untuk menerima data dari database
    public Tiket(int id, String nama, double harga, String jenis, int jumlah, int stok) {
        super(id, nama, harga, jenis, stok);
        this.jumlah = jumlah;
    }

    // konstruktor helper
    public Tiket(int id, String nama, double harga, String jenis, int stok) {
        super(id, nama, harga, jenis, stok);
    }

    public int getJumlah() {
        return this.jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    @Override
    public double hitungTotalHarga(int jumlah) {
        return this.getHarga() * jumlah * 1.1; 
    }

    @Override
    public void printDetail() {
        System.out.println("Nama\t: "+ getNama());
        System.out.println("Harga\t: " + getHarga());
        System.out.println("Stok\t: " + getStok());
    }
}

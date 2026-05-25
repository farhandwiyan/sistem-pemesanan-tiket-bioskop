package model;

public abstract class Produk {
    private int id;
    private String nama;
    private double harga;
    private String jenis;
    private int stok;

    // konstruktor untuk membuat produk ke database
    public Produk(String nama, double harga, String jenis, int stok) {
        this.nama = nama;
        this.harga = harga;
        try {
            setJenis(jenis);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        this.stok = stok;
    }
    
    // konstruktor untuk menerima data dari database
    public Produk(int id, String nama, double harga, String jenis, int stok) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.jenis = jenis;
        try {
            setJenis(jenis);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        this.stok = stok;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return this.harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    } 

    public String getJenis() {
        return this.jenis;
    }

    public void setJenis(String jenis) {
        if (!jenis.toLowerCase().equals("makanan") && !jenis.toLowerCase().equals("tiket")) {
            throw new IllegalArgumentException("Jenis produk tidak sesuai");
        } else {
            this.jenis = jenis;
        }
    }

    public int getStok() {
        return this.stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public abstract double hitungTotalHarga(int jumlah);
}

package model;

public class User {
    private int id;
    private String username;
    private String password;
    private String peran;

    // konstruktor untuk buat user baru
    public User(String username, String password, String peran) {
        this.username = username;
        this.password = password;
        try {
            setPeran(peran);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
    }

    // konstruktor untuk ambil user dari database
    public User(int id, String username, String password, String peran) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.peran = peran;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPeran() {
        return this.peran;
    }

    public void setPeran(String peran){
        if (peran == null) {
            throw new IllegalArgumentException("Peran tidak boleh kosong");
        }
        
        if (peran.toLowerCase() == "user") {
            this.peran = peran;
        } else {
            throw new IllegalArgumentException("Peran tidak sesuai");
        }
    }
}

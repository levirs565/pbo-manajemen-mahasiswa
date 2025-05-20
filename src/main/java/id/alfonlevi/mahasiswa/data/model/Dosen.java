package id.alfonlevi.mahasiswa.data.model;

public class Dosen extends Akun {
    private String mNip;
    private String mNama;

    public Dosen(String username, String password, String nip, String nama) {
        super(username, password, Role.DOSEN);
        mNip = nip;
        mNama = nama;
    }

/*************  ✨ Windsurf Command ⭐  *************/
    /**
     * Mendapatkan NIP dosen.
     * 
     * @return NIP dosen
     */
/*******  9e7976b1-5187-4642-9d01-5c907d4b1b10  *******/
    public String getNip() {
        return mNip;
    }

    public String getNama() {
        return mNama;
    }
}

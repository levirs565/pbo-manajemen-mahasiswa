package id.alfonlevi.mahasiswa.data.model;

public class Dosen extends Akun {
    private String mNip;
    private String mNama;

    public Dosen(String username, String password, String nip, String nama) {
        super(username, password, Role.DOSEN);
        mNip = nip;
        mNama = nama;
    }

    public String getNip() {
        return mNip;
    }

    public String getNama() {
        return mNama;
    }
}

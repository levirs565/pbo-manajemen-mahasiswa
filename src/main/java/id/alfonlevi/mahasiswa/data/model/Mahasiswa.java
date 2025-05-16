package id.alfonlevi.mahasiswa.data.model;

//Mahasiswa
public class Mahasiswa {
    private String mNim;
    private String mNama;

    public Mahasiswa(String nim, String nama) {
        mNim = nim;
        mNama = nama;
    }

    public String getNim() {
        return mNim;
    }

    public String getNama() {
        return mNama;
    }
}


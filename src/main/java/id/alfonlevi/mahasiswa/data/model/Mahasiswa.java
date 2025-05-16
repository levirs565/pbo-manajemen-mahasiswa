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

//MataKuliah
public class MataKuliah {
    private String id;
    private String nama;

    public MataKuliah(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}

//Kelas
public class Kelas {
    private String id;
    private String nama;
    private String mataKuliahId;

    public Kelas(String id, String nama, String mataKuliahId) {
        this.id = id;
        this.nama = nama;
        this.mataKuliahId = mataKuliahId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getMataKuliahId() {
        return mataKuliahId;
    }

    public void setMataKuliahId(String mataKuliahId) {
        this.mataKuliahId = mataKuliahId;
    }
}


package id.alfonlevi.mahasiswa.data.model;

//MataKuliah
public class MataKuliah {
    private String id;
    private String mPeriodeId;
    private String nama;

    public MataKuliah(String id, String periodeId, String nama) {
        this.id = id;
        this.mPeriodeId = periodeId;
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

    public String getPeriodeId() {
        return mPeriodeId;
    }
}
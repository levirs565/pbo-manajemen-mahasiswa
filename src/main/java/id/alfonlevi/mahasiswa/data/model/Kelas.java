package id.alfonlevi.mahasiswa.data.model;

//Kelas
public class Kelas {
    private String id;
    private String nama;
    private String mataKuliahId;
    private String usernameDosen;

    public Kelas(String id, String nama, String mataKuliahId, String usernameDosen) {
        this.id = id;
        this.nama = nama;
        this.mataKuliahId = mataKuliahId;
        this.usernameDosen = usernameDosen;
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

    public String getUsernameDosen(){
        return usernameDosen;
    }
}
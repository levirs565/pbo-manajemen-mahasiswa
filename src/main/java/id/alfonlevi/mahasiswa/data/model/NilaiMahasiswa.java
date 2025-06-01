
package id.alfonlevi.mahasiswa.data.model;
public class NilaiMahasiswa {
    private Kelas mKelas;
    private MataKuliah mMataKuliah;
    private int mNilai;

    public NilaiMahasiswa(Kelas kelas, MataKuliah mataKuliah, int nilai) {
        mKelas = kelas;
        mMataKuliah = mataKuliah;
        mNilai = nilai;
    }

    public Kelas getKelas() {
        return mKelas;
    }

    public MataKuliah getMataKuliah() {
        return mMataKuliah;
    }

    public int getNilai() {
        return mNilai;
    }

    
}

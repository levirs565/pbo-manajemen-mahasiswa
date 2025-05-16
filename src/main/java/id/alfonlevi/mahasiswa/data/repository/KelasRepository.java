package id.alfonlevi.mahasiswa.data.repository;

import id.alfonlevi.mahasiswa.data.model.Kelas;
import id.alfonlevi.mahasiswa.data.model.Mahasiswa;

import java.util.List;

public interface KelasRepository extends BaseRepository {
    List<Kelas> getAll(String mataKuliahId);
    boolean update(Kelas kelas);
    Kelas get(String id);
    boolean add(Kelas kelas);
    boolean delete(String id);

    List<Mahasiswa> getAnggotaKelas(String id);
    void updateAnggotaKelas(String id, List<String> nimList);
}

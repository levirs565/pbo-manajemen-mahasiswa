package id.alfonlevi.mahasiswa.data.repository;

import id.alfonlevi.mahasiswa.data.model.Kelas;
import id.alfonlevi.mahasiswa.data.model.Mahasiswa;

import java.util.List;
import java.util.Map;

public interface KelasRepository extends BaseRepository {
    List<Kelas> getAll(String mataKuliahId);
    List<Kelas> getAll(String mataKuliahId, String usernameDosen);
    boolean update(Kelas kelas);
    Kelas get(String id);
    boolean add(Kelas kelas);
    boolean delete(String id);

    List<Mahasiswa> getAnggotaKelas(String id);
    Map<String, Integer> getNilaiKelas(String id);
    void updateAnggotaKelas(String id, List<String> nimList);
    boolean updateNilaiKelas(String id, Map<String, Integer> nilai);
}

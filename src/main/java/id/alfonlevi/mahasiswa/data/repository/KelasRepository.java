package id.alfonlevi.mahasiswa.data.repository;

import id.alfonlevi.mahasiswa.data.model.Kelas;
import java.util.List;

public interface KelasRepository extends BaseRepository {
    List<Kelas> getAll();
    boolean update(Kelas kelas);
    Kelas get(String id);
    boolean add(Kelas kelas);
    boolean delete(String id);
}

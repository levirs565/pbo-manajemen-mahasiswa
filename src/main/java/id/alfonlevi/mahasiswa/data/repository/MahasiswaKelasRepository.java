package id.alfonlevi.mahasiswa.data.repository;

import id.alfonlevi.mahasiswa.data.model.MahasiswaKelas;
import java.util.List;

public interface MahasiswaKelasRepository extends BaseRepository {
    List<MahasiswaKelas> getAll();
    boolean add(MahasiswaKelas mahasiswaKelas);
    boolean delete(String mahasiswaNim, String kelasId);
}

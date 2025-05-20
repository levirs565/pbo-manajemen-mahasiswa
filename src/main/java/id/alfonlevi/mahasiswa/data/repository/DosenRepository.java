package id.alfonlevi.mahasiswa.data.repository;

import java.util.List;

import id.alfonlevi.mahasiswa.data.model.Dosen;

public interface DosenRepository extends BaseRepository {
    List<Dosen> getAll();
    Dosen get(String username);
    boolean add(Dosen dosen);
    boolean update(Dosen dosen);
    boolean delete(String username);
}



package id.alfonlevi.mahasiswa.data.repository;

import id.alfonlevi.mahasiswa.data.model.Mahasiswa;

import java.util.List;

public interface MahasiswaRepository extends BaseRepository{
    List<Mahasiswa> getAll();
    boolean update(Mahasiswa mahasiswa);
    Mahasiswa get(String nim);
    boolean add(Mahasiswa mahasiswa);
    boolean delete(String nim);
}
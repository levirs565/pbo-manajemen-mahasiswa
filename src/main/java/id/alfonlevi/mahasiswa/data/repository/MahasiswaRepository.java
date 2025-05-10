package id.alfonlevi.mahasiswa.data.repository;

import id.alfonlevi.mahasiswa.data.model.Mahasiswa;

import java.util.List;

public interface MahasiswaRepository {
    List<Mahasiswa> getAll();
    boolean update(Mahasiswa mahasiswa);
    boolean add(Mahasiswa mahasiswa);
    boolean delete(String nim);
}
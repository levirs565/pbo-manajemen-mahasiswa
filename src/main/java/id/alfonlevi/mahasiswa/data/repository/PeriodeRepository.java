package id.alfonlevi.mahasiswa.data.repository;

import id.alfonlevi.mahasiswa.data.model.Periode;

import java.util.List;

public interface PeriodeRepository extends BaseRepository {
    List<Periode> getAll();
    Periode get(String id);
    boolean add(Periode periode);
    boolean update(Periode periode);
    boolean delete(String id);
}

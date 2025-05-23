package id.alfonlevi.mahasiswa.data.repository;

import id.alfonlevi.mahasiswa.data.model.MataKuliah;
import java.util.List;

public interface MataKuliahRepository extends BaseRepository {
    List<MataKuliah> getAll(String periodeId);
    List<MataKuliah> getAllByDosen(String periodeId, String usernameDosen);
    boolean update(MataKuliah mataKuliah);
    MataKuliah get(String id);
    boolean add(MataKuliah mataKuliah);
    boolean delete(String id);
}

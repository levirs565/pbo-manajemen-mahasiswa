package id.alfonlevi.mahasiswa.data.repository;

import id.alfonlevi.mahasiswa.data.model.Akun;

public interface AkunRepository extends BaseRepository {
    Akun get(String username);
    boolean updatePassword(String username, String password);
}

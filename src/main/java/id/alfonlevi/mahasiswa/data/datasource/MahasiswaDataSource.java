package id.alfonlevi.mahasiswa.data.datasource;

import id.alfonlevi.mahasiswa.data.model.Kelas;
import id.alfonlevi.mahasiswa.data.model.Mahasiswa;
import id.alfonlevi.mahasiswa.data.model.MataKuliah;
import id.alfonlevi.mahasiswa.data.model.NilaiMahasiswa;
import id.alfonlevi.mahasiswa.data.repository.MahasiswaRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDataSource extends BaseDataSource implements MahasiswaRepository {
    private final Connection mConnection;

    public MahasiswaDataSource(Connection connection) {
        this.mConnection = connection;
    }

    @Override
    public List<Mahasiswa> getAll() {
        var result = new ArrayList<Mahasiswa>();

        try (var statement = mConnection.prepareStatement("SELECT * FROM Mahasiswa")) {
            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(Utils.mapMahasiswa(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public Mahasiswa get(String nim) {
        try (var statement = mConnection.prepareStatement("SELECT * FROM Mahasiswa WHERE nim = ?")) {
            statement.setString(1, nim);
            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Utils.mapMahasiswa(resultSet);
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Mahasiswa mahasiswa) {
        try (var statement = mConnection.prepareStatement("UPDATE Mahasiswa SET nama = ? WHERE nim = ?")) {
            statement.setString(1, mahasiswa.getNama());
            statement.setString(2, mahasiswa.getNim());

            boolean result = statement.executeUpdate() > 0;
            invokeListener();

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Mahasiswa mahasiswa) {
        try (var statement = mConnection.prepareStatement("INSERT INTO Mahasiswa(nim, nama) VALUES (?, ?)")) {
            statement.setString(1, mahasiswa.getNim());
            statement.setString(2, mahasiswa.getNama());

            boolean result = statement.executeUpdate() > 0;
            invokeListener();

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String nim) {
        try (var statement = mConnection.prepareStatement("DELETE FROM Mahasiswa WHERE nim = ?")) {
            statement.setString(1, nim);

            boolean result = statement.executeUpdate() > 0;
            invokeListener();

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<NilaiMahasiswa> getNilai(String periodeId,String nim ) {
        var result = new ArrayList<NilaiMahasiswa>();
        try (var statement = mConnection
                .prepareStatement("SELECT mahasiswakelas.nilai, kelas.*, matakuliah.* FROM mahasiswakelas  " +
                        "JOIN kelas ON kelas.id = mahasiswakelas.kelas_id  " +
                        "JOIN matakuliah ON matakuliah.id = kelas.mata_kuliah_id " +
                        "WHERE matakuliah.periode_id = ? AND mahasiswakelas.mahasiswa_nim = ?")) {
            statement.setString(1, periodeId);
            statement.setString(2, nim);

            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(new NilaiMahasiswa(
                    new Kelas(
                            resultSet.getString("kelas.id"),
                            resultSet.getString("kelas.nama"),
                            resultSet.getString("kelas.mata_kuliah_id"),
                            resultSet.getString("kelas.username_dosen")),
                   
                    new MataKuliah(  
                            resultSet.getString("matakuliah.id"),
                            resultSet.getString("matakuliah.periode_id"),
                            resultSet.getString("matakuliah.nama")
                            ),
                    resultSet.getInt("nilai")));


            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

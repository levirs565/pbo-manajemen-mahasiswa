package id.alfonlevi.mahasiswa.data.datasource;

import id.alfonlevi.mahasiswa.data.model.MahasiswaKelas;
import id.alfonlevi.mahasiswa.data.repository.MahasiswaKelasRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaKelasDataSource extends BaseDataSource implements MahasiswaKelasRepository {
    private final Connection mConnection;

    public MahasiswaKelasDataSource(Connection connection) {
        mConnection = connection;
    }

    private MahasiswaKelas fromResultSet(ResultSet resultSet) throws SQLException {
        return new MahasiswaKelas(
            resultSet.getString("mahasiswa_nim"),
            resultSet.getString("kelas_id")
        );
    }

    @Override
    public List<MahasiswaKelas> getAll() {
        var result = new ArrayList<MahasiswaKelas>();
        try (var statement = mConnection.prepareStatement("SELECT * FROM MahasiswaKelas")) {
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(fromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean add(MahasiswaKelas mahasiswaKelas) {
        try (var statement = mConnection.prepareStatement(
                "INSERT INTO MahasiswaKelas(mahasiswa_nim, kelas_id) VALUES (?, ?)")) {
            statement.setString(1, mahasiswaKelas.getMahasiswaNim());
            statement.setString(2, mahasiswaKelas.getKelasId());
            boolean result = statement.executeUpdate() > 0;
            invokeListener();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String mahasiswaNim, String kelasId) {
        try (var statement = mConnection.prepareStatement(
                "DELETE FROM MahasiswaKelas WHERE mahasiswa_nim = ? AND kelas_id = ?")) {
            statement.setString(1, mahasiswaNim);
            statement.setString(2, kelasId);
            boolean result = statement.executeUpdate() > 0;
            invokeListener();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package id.alfonlevi.mahasiswa.data.datasource;

import id.alfonlevi.mahasiswa.data.model.Kelas;
import id.alfonlevi.mahasiswa.data.repository.KelasRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KelasDataSource extends BaseDataSource implements KelasRepository {
    private final Connection mConnection;

    public KelasDataSource(Connection connection) {
        mConnection = connection;
    }

    private Kelas fromResultSet(ResultSet resultSet) throws SQLException {
        return new Kelas(
            resultSet.getString("id"),
            resultSet.getString("nama"),
            resultSet.getString("mata_kuliah_id")
        );
    }

    @Override
    public List<Kelas> getAll() {
        var result = new ArrayList<Kelas>();
        try (var statement = mConnection.prepareStatement("SELECT * FROM Kelas")) {
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
    public Kelas get(String id) {
        try (var statement = mConnection.prepareStatement("SELECT * FROM Kelas WHERE id = ?")) {
            statement.setString(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return fromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Kelas kelas) {
        try (var statement = mConnection.prepareStatement(
                "UPDATE Kelas SET nama = ?, mata_kuliah_id = ? WHERE id = ?")) {
            statement.setString(1, kelas.getNama());
            statement.setString(2, kelas.getMataKuliahId());
            statement.setString(3, kelas.getId());
            boolean result = statement.executeUpdate() > 0;
            invokeListener();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Kelas kelas) {
        try (var statement = mConnection.prepareStatement(
                "INSERT INTO Kelas(id, nama, mata_kuliah_id) VALUES (?, ?, ?)")) {
            statement.setString(1, kelas.getId());
            statement.setString(2, kelas.getNama());
            statement.setString(3, kelas.getMataKuliahId());
            boolean result = statement.executeUpdate() > 0;
            invokeListener();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        try (var statement = mConnection.prepareStatement("DELETE FROM Kelas WHERE id = ?")) {
            statement.setString(1, id);
            boolean result = statement.executeUpdate() > 0;
            invokeListener();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

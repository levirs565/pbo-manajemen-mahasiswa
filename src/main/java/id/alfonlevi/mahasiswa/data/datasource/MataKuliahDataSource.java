package id.alfonlevi.mahasiswa.data.datasource;

import id.alfonlevi.mahasiswa.data.model.MataKuliah;
import id.alfonlevi.mahasiswa.data.repository.MataKuliahRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MataKuliahDataSource extends BaseDataSource implements MataKuliahRepository {
    private final Connection mConnection;

    public MataKuliahDataSource(Connection connection) {
        mConnection = connection;
    }

    private MataKuliah fromResultSet(ResultSet resultSet) throws SQLException {
        return new MataKuliah(
            resultSet.getString("id"),
            resultSet.getString("periode_id"),
            resultSet.getString("nama")
        );
    }

    @Override
    public List<MataKuliah> getAll(String periodeId) {
        var result = new ArrayList<MataKuliah>();
        try (var statement = mConnection.prepareStatement("SELECT * FROM MataKuliah WHERE periode_id = ?")) {  
            statement .setString(1, periodeId);
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
    public List<MataKuliah> getAllByDosen(String periodeId, String usernameDosen) {
        var result = new ArrayList<MataKuliah>();
        try (var statement = mConnection.prepareStatement("SELECT DISTINCT MataKuliah.* " +
                        "FROM Kelas " + 
                        "JOIN MataKuliah ON Kelas.mata_kuliah_id = MataKuliah.id " + 
                        "WHERE Kelas.username_dosen = ? AND MataKuliah.periode_id = ?")) {  
            statement.setString(1, usernameDosen);
            statement.setString(2, periodeId);
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
    public MataKuliah get(String id) {
        try (var statement = mConnection.prepareStatement("SELECT * FROM MataKuliah WHERE id = ?")) {
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
    public boolean update(MataKuliah mataKuliah) {
        try (var statement = mConnection.prepareStatement("UPDATE MataKuliah SET nama = ? WHERE id = ?")) {
            statement.setString(1, mataKuliah.getNama());
            statement.setString(2, mataKuliah.getId());
            boolean result = statement.executeUpdate() > 0;
            invokeListener();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(MataKuliah mataKuliah) {
        try (var statement = mConnection.prepareStatement("INSERT INTO MataKuliah(periode_id, nama) VALUES (?, ?)")) {
            statement.setString(1, mataKuliah.getPeriodeId());
            statement.setString(2, mataKuliah.getNama());
            boolean result = statement.executeUpdate() > 0;
            invokeListener();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        try (var statement = mConnection.prepareStatement("DELETE FROM MataKuliah WHERE id = ?")) {
            statement.setString(1, id);
            boolean result = statement.executeUpdate() > 0;
            invokeListener();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

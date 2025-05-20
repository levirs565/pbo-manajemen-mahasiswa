package id.alfonlevi.mahasiswa.data.datasource;

import id.alfonlevi.mahasiswa.data.model.Kelas;
import id.alfonlevi.mahasiswa.data.model.Mahasiswa;
import id.alfonlevi.mahasiswa.data.repository.KelasRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class KelasDataSource extends BaseDataSource implements KelasRepository {
    private final Connection mConnection;

    public KelasDataSource(Connection connection) {
        mConnection = connection;
    }

    private Kelas fromResultSet(ResultSet resultSet) throws SQLException {
        return new Kelas(
            resultSet.getString("id"),
            resultSet.getString("nama"),
            resultSet.getString("mata_kuliah_id"),
            resultSet.getString("username_dosen")
        );
    }

    @Override
    public List<Kelas> getAll(String mataKuliahId) {
        var result = new ArrayList<Kelas>();
        try (var statement = mConnection.prepareStatement("SELECT * FROM Kelas where mata_kuliah_id = ?")) {
            statement.setString(1, mataKuliahId);   
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
                "UPDATE Kelas SET nama = ? WHERE id = ?")) {
            statement.setString(1, kelas.getNama());
            statement.setString(2, kelas.getId());
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
                "INSERT INTO Kelas(nama, mata_kuliah_id, username_dosen) VALUES (?, ?, ?)")) {
            statement.setString(1, kelas.getNama());
            statement.setString(2, kelas.getMataKuliahId());
            statement.setString(3, kelas.getUsernameDosen());
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

    @Override
    public List<Mahasiswa> getAnggotaKelas(String id) {
        var result = new ArrayList<Mahasiswa>();

        try (var statement = mConnection.prepareStatement("select * from MahasiswaKelas " + //
                        "inner join Mahasiswa on MahasiswaKelas.mahasiswa_nim = Mahasiswa.nim " + //
                        "where MahasiswaKelas.kelas_id = ? ")) {
            statement.setString(1, id);
            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(Utils.mapMahasiswa(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;    }

    @Override
    public void updateAnggotaKelas(String id, List<String> nimList) {
        var paramIds = nimList.stream().map((v) -> "?").collect(Collectors.joining(","));
        var param = nimList.isEmpty() ? "" : String.format("AND mahasiswa_nim NOT IN (%s)", paramIds);
        try (var statement = mConnection.prepareStatement("DELETE FROM MahasiswaKelas WHERE kelas_id = ? " + param)) {
            statement.setString(1, id);
            int index = 2;
            for (String nim : nimList) {
                statement.setString(index, nim);
                index++;
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        try (var statement = mConnection.prepareStatement("INSERT ignore INTO MahasiswaKelas(kelas_id, mahasiswa_nim) VALUES (?, ?)")) {
            for (String nim : nimList) {
                statement.setString(1, id);
                statement.setString(2, nim);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        invokeListener();
    }
}

package id.alfonlevi.mahasiswa.data.datasource;

import id.alfonlevi.mahasiswa.data.model.Mahasiswa;
import id.alfonlevi.mahasiswa.data.repository.MahasiswaRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDataSource implements MahasiswaRepository {
    private final Connection mConnection;

    public MahasiswaDataSource(Connection connection) {
        mConnection = connection;
    }

    @Override
    public List<Mahasiswa> getAll() {
        var result = new ArrayList<Mahasiswa>();

        try (var statement = mConnection.prepareStatement("SELECT * FROM Mahasiswa")) {
            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(
                        new Mahasiswa(
                                resultSet.getString("nim"),
                                resultSet.getString("nama")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public boolean update(Mahasiswa mahasiswa) {
        try (var statement = mConnection.prepareStatement("UPDATE Mahasiswa SET nama = ? WHERE nim = ?")) {
            statement.setString(1, mahasiswa.getNama());
            statement.setString(2, mahasiswa.getNim());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Mahasiswa mahasiswa) {
        try (var statement = mConnection.prepareStatement("INSERT INTO Mahasiswa(nim, nama) VALUES (?, ?)")) {
            statement.setString(1, mahasiswa.getNim());
            statement.setString(2, mahasiswa.getNama());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String nim) {
        try (var statement = mConnection.prepareStatement("DELETE FROM Mahasiswa WHERE nim = ?")) {
            statement.setString(1, nim);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package id.alfonlevi.mahasiswa.data.datasource;

import id.alfonlevi.mahasiswa.data.model.Akun;
import id.alfonlevi.mahasiswa.data.model.Dosen;
import id.alfonlevi.mahasiswa.data.repository.DosenRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DosenDataSource extends BaseDataSource implements DosenRepository {
    private final Connection mConnection;
    private final AkunDataSource mAkunDataSource;

    public DosenDataSource(Connection connection, AkunDataSource akunDataSource) {
        this.mConnection = connection;
        this.mAkunDataSource = akunDataSource;
    }

    private Dosen fromResultSet(java.sql.ResultSet resultSet) throws SQLException {
        return new Dosen(
            resultSet.getString("username"),
            resultSet.getString("password"),
            Akun.Role.valueOf(resultSet.getString("role")),
            resultSet.getString("nip"),
            resultSet.getString("nama")
        );
    }

    @Override
    public List<Dosen> getAll() {
        List<Dosen> result = new ArrayList<>();
        try (var statement = mConnection.prepareStatement(
                "SELECT Dosen.nip, Dosen.username, Dosen.nama, Akun.password, Akun.role " +
                "FROM Dosen JOIN Akun ON Dosen.username = Akun.username")) {

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
    public Dosen get(String username) {
        try (var statement = mConnection.prepareStatement(
                "SELECT Dosen.nip, Dosen.username, Dosen.nama, Akun.password, Akun.role " +
                "FROM Dosen JOIN Akun ON Dosen.username = Akun.username " +
                "WHERE Dosen.username = ?")) {

            statement.setString(1, username);
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
    public boolean add(Dosen dosen) {
        if (!mAkunDataSource.add(dosen)) {
            return false;
        }
        try (var statement = mConnection.prepareStatement(
                "INSERT INTO Dosen(nip, username, nama) VALUES (?, ?, ?)")) {

            statement.setString(1, dosen.getNip());
            statement.setString(2, dosen.getUsername());
            statement.setString(3, dosen.getNama());

            boolean result = statement.executeUpdate() > 0;
            invokeListener();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Dosen dosen) {
        try (var statement = mConnection.prepareStatement(
                "UPDATE Dosen SET nip = ?, nama = ? WHERE username = ?")) {

            statement.setString(1, dosen.getNip());
            statement.setString(2, dosen.getNama());
            statement.setString(3, dosen.getUsername());

            boolean result = statement.executeUpdate() > 0;
            invokeListener();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String username) {
        try (var statement = mConnection.prepareStatement(
                "DELETE FROM Dosen WHERE username = ?")) {

            statement.setString(1, username);
            boolean result = statement.executeUpdate() > 0;
            invokeListener();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

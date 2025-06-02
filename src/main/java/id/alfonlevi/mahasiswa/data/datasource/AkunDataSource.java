package id.alfonlevi.mahasiswa.data.datasource;

import id.alfonlevi.mahasiswa.data.model.Akun;
import id.alfonlevi.mahasiswa.data.model.Akun.Role;
import id.alfonlevi.mahasiswa.data.repository.AkunRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AkunDataSource extends BaseDataSource implements AkunRepository {
    private final Connection mConnection;

    public AkunDataSource(Connection connection) {
        mConnection = connection;
    }

    private Akun fromResultSet(ResultSet resultSet) throws SQLException {
        return new Akun(
                resultSet.getString("username"),
                resultSet.getString("password"),
                Role.valueOf(resultSet.getString("role")) // Enum.fromString
        );
    }

    @Override
    public Akun get(String username) {
        try (var statement = mConnection.prepareStatement("SELECT * FROM Akun WHERE username = ?")) {
            statement.setString(1, username);
            var rs = statement.executeQuery();

            if (rs.next()) {
                return fromResultSet(rs);
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updatePassword(String username, String password) {
        try (var statement = mConnection.prepareStatement("UPDATE Akun SET password = ? WHERE username = ?")) {
            statement.setString(1, password);
            statement.setString(2, username);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean add(Akun akun) {
        try (var statement = mConnection.prepareStatement("INSERT INTO Akun(username, password, role) VALUES (?, ?, ?)")) {
            statement.setString(1, akun.getUsername());
            statement.setString(2, akun.getPassword());
            statement.setString(3, akun.getRole().name());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String username) {
        try (var statement = mConnection.prepareStatement("DELETE FROM Akun WHERE username = ?")) {
            statement.setString(1, username);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

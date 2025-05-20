package id.alfonlevi.mahasiswa.data.datasource;

import id.alfonlevi.mahasiswa.data.model.Periode;
import id.alfonlevi.mahasiswa.data.repository.PeriodeRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeriodeDataSource extends BaseDataSource implements PeriodeRepository {
    private final Connection mConnection;

    public PeriodeDataSource(Connection connection) {
        mConnection = connection;
    }

    private Periode fromResultSet(ResultSet resultSet) throws SQLException {
        return new Periode(
            resultSet.getString("id"),
            resultSet.getInt("tahun"),
            resultSet.getBoolean("is_genap")
        );
    }

    @Override
    public List<Periode> getAll() {
        var result = new ArrayList<Periode>();
        try (var statement = mConnection.prepareStatement("SELECT * FROM Periode")) {
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
    public Periode get(String id) {
        try (var statement = mConnection.prepareStatement("SELECT * FROM Periode WHERE id = ?")) {
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
    public boolean add(Periode periode) {
        try (var statement = mConnection.prepareStatement("INSERT INTO Periode (tahun, is_genap) VALUES (?, ?)")) {
            statement.setInt(1, periode.getTahun());
            statement.setBoolean(2, periode.isGenap());
            boolean result = statement.executeUpdate() > 0;
            invokeListener();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Periode periode) {
        try (var statement = mConnection.prepareStatement("UPDATE Periode SET tahun = ?, is_genap = ? WHERE id = ?")) {
            statement.setInt(1, periode.getTahun());
            statement.setBoolean(2, periode.isGenap());
            statement.setString(3, periode.getId());
            boolean result = statement.executeUpdate() > 0;
            invokeListener();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        try (var statement = mConnection.prepareStatement("DELETE FROM Periode WHERE id = ?")) {
            statement.setString(1, id);
            boolean result = statement.executeUpdate() > 0;
            invokeListener();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

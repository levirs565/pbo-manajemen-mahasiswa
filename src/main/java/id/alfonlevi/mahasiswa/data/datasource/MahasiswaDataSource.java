package id.alfonlevi.mahasiswa.data.datasource;

import id.alfonlevi.mahasiswa.data.model.Mahasiswa;
import id.alfonlevi.mahasiswa.data.repository.MahasiswaRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDataSource extends BaseDataSource implements MahasiswaRepository {
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
               
            var result = statement.executeUpdate() > 0;
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
            
            var result = statement.executeUpdate() > 0;
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
            
            var result = statement.executeUpdate() > 0;
            invokeListener();
            
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

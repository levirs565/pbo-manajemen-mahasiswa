package id.alfonlevi.mahasiswa.data;

import id.alfonlevi.mahasiswa.data.datasource.MahasiswaDataSource;
import id.alfonlevi.mahasiswa.data.repository.MahasiswaRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RepositoryProvider {
    private static final String sDB_URL = "jdbc:mysql://localhost:3306/data_mahasiswa";
    private static final String sDB_USER = "root";
    private static final String sDB_PASSWORD = "";
    private static final RepositoryProvider sInstance = new RepositoryProvider();

    public static RepositoryProvider get() {
        return sInstance;
    }

    private Connection mConnection;
    private MahasiswaDataSource mMahasiswaDataSource;

    private RepositoryProvider() {
        try {
            mConnection = DriverManager.getConnection(sDB_URL, sDB_USER, sDB_PASSWORD);

            try(var statement = mConnection.createStatement()) {
                statement.addBatch("CREATE TABLE IF NOT EXISTS Mahasiswa(" +
                        "nim VARCHAR(10) PRIMARY KEY NOT NULL," +
                        "nama VARCHAR(100) NOT NULL" +
                        ")");

                statement.executeBatch();
            }

            mMahasiswaDataSource = new MahasiswaDataSource(mConnection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MahasiswaRepository getMahasiswaRepository() {
        return mMahasiswaDataSource;
    }
}

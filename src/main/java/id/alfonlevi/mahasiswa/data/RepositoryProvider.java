package id.alfonlevi.mahasiswa.data;

import id.alfonlevi.mahasiswa.data.datasource.MahasiswaDataSource;
import id.alfonlevi.mahasiswa.data.datasource.MataKuliahDataSource;
import id.alfonlevi.mahasiswa.data.datasource.KelasDataSource;
import id.alfonlevi.mahasiswa.data.datasource.PeriodeDataSource;
import id.alfonlevi.mahasiswa.data.repository.MahasiswaRepository;
import id.alfonlevi.mahasiswa.data.repository.MataKuliahRepository;
import id.alfonlevi.mahasiswa.data.repository.KelasRepository;

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
    private MataKuliahDataSource mMataKuliahDataSource;
    private KelasDataSource mKelasDataSource;
    private PeriodeDataSource mPeriodeDataSource;

    private RepositoryProvider() {
        try {
            mConnection = DriverManager.getConnection(sDB_URL, sDB_USER, sDB_PASSWORD);

            try (var statement = mConnection.createStatement()) {
                statement.addBatch("CREATE TABLE IF NOT EXISTS Mahasiswa(" +
                        "nim VARCHAR(10) PRIMARY KEY NOT NULL," +
                        "nama VARCHAR(100) NOT NULL)");

                statement.addBatch("CREATE TABLE IF NOT EXISTS MataKuliah(" +
                        "id VARCHAR(36) PRIMARY KEY NOT NULL DEFAULT(UUID())," +
                        "nama VARCHAR(100) NOT NULL," +
                        "periode_id VARCHAR(36) NOT NULL," +
                        "FOREIGN KEY (periode_id) REFERENCES Periode(id) ON DELETE CASCADE ON UPDATE CASCADE)");

                statement.addBatch("CREATE TABLE IF NOT EXISTS Kelas(" +
                        "id VARCHAR(36) PRIMARY KEY NOT NULL DEFAULT(UUID())," +
                        "nama VARCHAR(100) NOT NULL," +
                        "mata_kuliah_id VARCHAR(36) NOT NULL," +
                        "FOREIGN KEY (mata_kuliah_id) REFERENCES MataKuliah(id) ON DELETE CASCADE ON UPDATE CASCADE)");

                statement.addBatch("CREATE TABLE IF NOT EXISTS MahasiswaKelas(" +
                        "mahasiswa_nim VARCHAR(10) NOT NULL," +
                        "kelas_id VARCHAR(36) NOT NULL," +
                        "PRIMARY KEY (mahasiswa_nim, kelas_id)," +
                        "FOREIGN KEY (mahasiswa_nim) REFERENCES Mahasiswa(nim) ON DELETE CASCADE ON UPDATE CASCADE," +
                        "FOREIGN KEY (kelas_id) REFERENCES Kelas(id) ON DELETE CASCADE ON UPDATE CASCADE)");

                statement.addBatch("CREATE TABLE IF NOT EXISTS Periode(" +
                        "id VARCHAR(36) PRIMARY KEY NOT NULL DEFAULT(UUID())," +
                        "tahun INTEGER NOT NULL," +
                        "is_genap BOOLEAN NOT NULL)");


                statement.addBatch("CREATE TABLE IF NOT EXISTS Akun(" +
                        "username VARCHAR(16) NOT NULL," +
                        "password VARCHAR(36) NOT NULL," +
                        "role ENUM('DOSEN','ADMIN') NOT NULL," +
                        "PRIMARY KEY (username)");;

                statement.addBatch("CREATE TABLE IF NOT EXISTS Dosen(" +
                        "nip VARCHAR(16) NOT NULL," +
                        "username VARCHAR(36) NOT NULL," +
                        "nama VARCHAR(36) NOT NULL," +
                        "PRIMARY KEY (username)," +
                        "FOREIGN KEY (username) REFERENCES Akun(username) ON DELETE CASCADE ON UPDATE CASCADE");;

                statement.executeBatch();
            }

            mMahasiswaDataSource = new MahasiswaDataSource(mConnection);
            mMataKuliahDataSource = new MataKuliahDataSource(mConnection);
            mKelasDataSource = new KelasDataSource(mConnection);
            mPeriodeDataSource = new PeriodeDataSource(mConnection);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MahasiswaRepository getMahasiswaRepository() {
        return mMahasiswaDataSource;
    }

    public MataKuliahRepository getMataKuliahRepository() {
        return mMataKuliahDataSource;
    }

    public KelasRepository getKelasRepository() {
        return mKelasDataSource;
    }

    public PeriodeDataSource getPeriodeRepository() {
        return mPeriodeDataSource;
    }
}

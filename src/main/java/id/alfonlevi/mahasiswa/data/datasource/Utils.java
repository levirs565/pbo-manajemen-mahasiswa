package id.alfonlevi.mahasiswa.data.datasource;

import java.sql.ResultSet;
import java.sql.SQLException;

import id.alfonlevi.mahasiswa.data.model.Mahasiswa;

public class Utils {
    private Utils() {
    }

    public static Mahasiswa mapMahasiswa(ResultSet resultSet) throws SQLException {
        return new Mahasiswa(
                resultSet.getString("nim"),
                resultSet.getString("nama"));
    }
}

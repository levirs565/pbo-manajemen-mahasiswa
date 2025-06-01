package id.alfonlevi.mahasiswa.view.nilaimahasiswa;

import id.alfonlevi.mahasiswa.data.model.Periode;

import javax.swing.*;
import javax.swing.table.TableModel;

public interface NilaiMahasiswaView {
    void setPeriodeModel(ComboBoxModel<Periode> model);
    void setTableModel(TableModel model);
    void setError(String error);
}

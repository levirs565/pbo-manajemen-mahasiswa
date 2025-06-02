package id.alfonlevi.mahasiswa.view.matakuliahlist;

import id.alfonlevi.mahasiswa.data.model.MataKuliah;
import id.alfonlevi.mahasiswa.data.model.Periode;

import javax.swing.*;
import java.util.List;

public interface MataKuliahListView {
    void setPeriodeModel(ComboBoxModel<Periode> model);
    void setData(List<MataKuliah> data);
}

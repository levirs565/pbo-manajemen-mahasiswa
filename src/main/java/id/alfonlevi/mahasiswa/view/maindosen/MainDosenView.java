package id.alfonlevi.mahasiswa.view.maindosen;

import id.alfonlevi.mahasiswa.data.model.MataKuliah;
import id.alfonlevi.mahasiswa.data.model.Periode;

import javax.swing.*;
import java.util.List;

public interface MainDosenView {
    void setPeriodeModel(ComboBoxModel<Periode> model);
    void setData(List<MataKuliah> data);
    void setTitle(String title);
}

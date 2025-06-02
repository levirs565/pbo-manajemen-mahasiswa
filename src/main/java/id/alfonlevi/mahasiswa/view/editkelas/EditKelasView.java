package id.alfonlevi.mahasiswa.view.editkelas;

import id.alfonlevi.mahasiswa.data.model.Dosen;
import id.alfonlevi.mahasiswa.data.model.Kelas;

import javax.swing.*;

public interface EditKelasView {
    void setDosenComboboxModel(ComboBoxModel<Dosen> model);
    void showData(boolean isNew, Kelas kelas);
    void showError(String error);
}

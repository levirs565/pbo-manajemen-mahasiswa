package id.alfonlevi.mahasiswa.view.editperiode;

import id.alfonlevi.mahasiswa.data.model.Periode;

import javax.swing.*;
import java.util.List;

public interface EditPeriodeView {
    void setData(Periode periode, boolean isNew);
    void setTahunModel(ComboBoxModel<Integer> model);
    void setSemesterModel(ComboBoxModel<String> model);
}

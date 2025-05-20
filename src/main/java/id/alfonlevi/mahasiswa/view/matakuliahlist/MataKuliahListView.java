package id.alfonlevi.mahasiswa.view.matakuliahlist;

import id.alfonlevi.mahasiswa.data.model.MataKuliah;
import id.alfonlevi.mahasiswa.data.model.Periode;

import java.util.List;

public interface MataKuliahListView {
    void setPeriodeList(List<Periode> periodeList);
    void setData(List<MataKuliah> data);
}

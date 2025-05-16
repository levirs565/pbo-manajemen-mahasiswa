package id.alfonlevi.mahasiswa.view.matakuliah;

import id.alfonlevi.mahasiswa.data.model.Kelas;

import java.util.List;

public interface MataKuliahView {
    void setTitle(String title);
    void setKelasList(List<Kelas> data);
}

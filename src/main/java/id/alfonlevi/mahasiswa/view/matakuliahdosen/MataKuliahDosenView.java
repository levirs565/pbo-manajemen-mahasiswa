package id.alfonlevi.mahasiswa.view.matakuliahdosen;

import id.alfonlevi.mahasiswa.data.model.Kelas;

import java.util.List;

public interface MataKuliahDosenView {
    void setTitle(String title);
    void setKelasList(List<Kelas> data);
}

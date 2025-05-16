package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.model.Mahasiswa;
import id.alfonlevi.mahasiswa.data.repository.KelasRepository;
import id.alfonlevi.mahasiswa.data.repository.MahasiswaRepository;
import id.alfonlevi.mahasiswa.view.kelas.KelasView;

import javax.swing.table.DefaultTableModel;

public class KelasController {
    private final KelasView mView;
    private final String mId;
    private final KelasRepository mKelasRepository;
    private final MahasiswaRepository mMahasiswaRepository;
    private final DefaultTableModel mTableModel = new DefaultTableModel(
            new String[]{"NIM", "Nama"},
            0
    );

    public KelasController(KelasView view, String id) {
        mView = view;
        mId = id;
        mKelasRepository = RepositoryProvider.get().getKelasRepository();
        mMahasiswaRepository = RepositoryProvider.get().getMahasiswaRepository();

        refreshTitle();
        mView.setTableModel(mTableModel);
        refreshMahasiswa();

        mKelasRepository.registerListener(() -> {
            refreshTitle();
            refreshMahasiswa();
        });

        mMahasiswaRepository.registerListener(() -> {
            refreshMahasiswa();
        });
    }

    private void refreshTitle() {
        var data = mKelasRepository.get(mId);
        mView.setTitle(data.getNama());
    }

    private void refreshMahasiswa() {
        var data = mKelasRepository.getAnggotaKelas(mId);
        mTableModel.setRowCount(0);

        for (var mahasiswa : data) {
            mTableModel.addRow(new Object[]{mahasiswa.getNim(), mahasiswa.getNama()});
        }
    }

    public String getId() {
        return mId;
    }
}

package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.repository.BaseRepository;
import id.alfonlevi.mahasiswa.data.repository.KelasRepository;
import id.alfonlevi.mahasiswa.data.repository.MahasiswaRepository;
import id.alfonlevi.mahasiswa.view.kelas.KelasView;
import id.alfonlevi.mahasiswa.view.kelasdosen.KelasDosenView;

import javax.swing.table.DefaultTableModel;

public class KelasDosenController {
    private final KelasDosenView mView;
    private final String mId;
    private final KelasRepository mKelasRepository;
    private final DefaultTableModel mTableModel = new DefaultTableModel(
            new String[]{"NIM", "Nama", "Nilai"},
            0
    );
    private final BaseRepository.Listener mKelasListener = () -> {
        refreshTitle();
        refreshMahasiswa();
    };

    public KelasDosenController(KelasDosenView view, String id) {
        mView = view;
        mId = id;
        mKelasRepository = RepositoryProvider.get().getKelasRepository();

        refreshTitle();
        mView.setTableModel(mTableModel);
        refreshMahasiswa();

        mKelasRepository.registerListener(mKelasListener);
    }

    private void refreshTitle() {
        var data = mKelasRepository.get(mId);
        mView.setTitle(data.getNama());
    }

    private void refreshMahasiswa() {
        var data = mKelasRepository.getAnggotaKelas(mId);
        var nilai = mKelasRepository.getNilaiKelas(mId);
        mTableModel.setRowCount(0);

        for (var mahasiswa : data) {
            mTableModel.addRow(new Object[]{mahasiswa.getNim(), mahasiswa.getNama(), nilai.get(mahasiswa.getNim())});
        }
    }

    public String getId() {
        return mId;
    }

    public void dispose() {
        mKelasRepository.registerListener(mKelasListener);
    }
}

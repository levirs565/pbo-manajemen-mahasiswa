package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.repository.KelasRepository;
import id.alfonlevi.mahasiswa.data.repository.MataKuliahRepository;
import id.alfonlevi.mahasiswa.view.matakuliah.MataKuliahView;

public class MataKuliahController {
    private String mId;
    private MataKuliahView mView;
    private MataKuliahRepository mMataKuliahRepository;
    private KelasRepository mKelasRepository;

    public MataKuliahController(MataKuliahView view, String id) {
        mId = id;
        mView = view;

        mMataKuliahRepository = RepositoryProvider.get().getMataKuliahRepository();
        mKelasRepository = RepositoryProvider.get().getKelasRepository();

        refreshTitle();
        refreshKelas();

        mMataKuliahRepository.registerListener(() -> {
            refreshTitle();
        });

        mKelasRepository.registerListener(() -> {
            refreshKelas();
        });
    }

    private void refreshTitle() {
        var data = mMataKuliahRepository.get(mId);
        mView.setTitle(data.getNama());
    }

    private void refreshKelas() {
        var data = mKelasRepository.getAll(mId);
        mView.setKelasList(data);
    }

    public String getId() {
        return mId;
    }
}

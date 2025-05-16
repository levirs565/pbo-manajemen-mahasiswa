package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.repository.MataKuliahRepository;
import id.alfonlevi.mahasiswa.view.matakuliah.MataKuliahView;

public class MataKuliahController {
    private final MataKuliahRepository mRepository;
    private final MataKuliahView mView;

    public MataKuliahController(MataKuliahView view) {
        mView = view;

        mRepository = RepositoryProvider.get().getMataKuliahRepository();
        refresh();

        mRepository.registerListener(() -> {
            refresh();
        });
    }

    private void refresh() {
        var data = mRepository.getAll();
        mView.setData(data);
    }
}

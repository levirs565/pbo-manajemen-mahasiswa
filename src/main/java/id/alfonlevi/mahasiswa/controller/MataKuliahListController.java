package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.repository.MataKuliahRepository;
import id.alfonlevi.mahasiswa.view.matakuliahlist.MataKuliahListView;

public class MataKuliahListController {
    private final MataKuliahRepository mRepository;
    private final MataKuliahListView mView;

    public MataKuliahListController(MataKuliahListView view) {
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

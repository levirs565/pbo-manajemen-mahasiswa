package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.repository.MataKuliahRepository;
import id.alfonlevi.mahasiswa.data.repository.PeriodeRepository;
import id.alfonlevi.mahasiswa.view.matakuliahlist.MataKuliahListView;

public class MataKuliahListController {
    private final MataKuliahRepository mMataKuliahRepository;
    private final PeriodeRepository mPeriodeRepository;
    private final MataKuliahListView mView;
    private String mPeriodeId = null;

    public MataKuliahListController(MataKuliahListView view) {
        mView = view;

        mMataKuliahRepository = RepositoryProvider.get().getMataKuliahRepository();
        mPeriodeRepository = RepositoryProvider.get().getPeriodeRepository();

        refreshPeriode();
        refresh();


        mPeriodeRepository.registerListener(() -> {
            refreshPeriode();
        });

        mMataKuliahRepository.registerListener(() -> {
            refresh();
        });
    }

    private void refreshPeriode() {
        var data = mPeriodeRepository.getAll();
        mView.setPeriodeList(data);
    }

    private void refresh() {
        if (mPeriodeId != null) {
            var data = mMataKuliahRepository.getAll(mPeriodeId);
            mView.setData(data);
        } else {
            mView.setData(null);
        }
    }

    public void setSelectedPeriode(String periodeId) {
        mPeriodeId = periodeId;
        refresh();
    }
}

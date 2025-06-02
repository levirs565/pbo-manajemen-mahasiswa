package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.model.Periode;
import id.alfonlevi.mahasiswa.data.repository.BaseRepository;
import id.alfonlevi.mahasiswa.data.repository.MataKuliahRepository;
import id.alfonlevi.mahasiswa.data.repository.PeriodeRepository;
import id.alfonlevi.mahasiswa.view.matakuliahlist.MataKuliahListView;

import javax.swing.*;

public class MataKuliahListController {
    private final MataKuliahRepository mMataKuliahRepository;
    private final PeriodeRepository mPeriodeRepository;
    private final MataKuliahListView mView;
    private final DefaultComboBoxModel<Periode> mPeriodeModel = new DefaultComboBoxModel<>();

    private BaseRepository.Listener mPeriodeListener = () -> {
        refreshPeriode();
    };
    private BaseRepository.Listener mMataKuliahListener = () -> {
        refresh();
    };

    public MataKuliahListController(MataKuliahListView view) {
        mView = view;

        mMataKuliahRepository = RepositoryProvider.get().getMataKuliahRepository();
        mPeriodeRepository = RepositoryProvider.get().getPeriodeRepository();

        refreshPeriode();
        refresh();

        mPeriodeRepository.registerListener(mPeriodeListener);
        mMataKuliahRepository.registerListener(mMataKuliahListener);

        mView.setPeriodeModel(mPeriodeModel);
    }

    private void refreshPeriode() {
        var isEmpty = mPeriodeModel.getSelectedItem() == null;
        var data = mPeriodeRepository.getAll();
        mPeriodeModel.removeAllElements();
        for (var periode : data) {
            mPeriodeModel.addElement(periode);
        }
        if (isEmpty) {
            mPeriodeModel.setSelectedItem(null);
        }
    }

    public void refresh() {
        var selected = getSelectedPeriodeId();
        if (selected != null) {
            var data = mMataKuliahRepository.getAll(selected);
            mView.setData(data);
        } else {
            mView.setData(null);
        }
    }

    public String getSelectedPeriodeId() {
        var selected = mPeriodeModel.getSelectedItem();
        return selected != null ? ((Periode) selected).getId() : null;
    }

    public void dispose() {
        mPeriodeRepository.unregisterListener(mPeriodeListener);
        mMataKuliahRepository.unregisterListener(mMataKuliahListener);
    }
}

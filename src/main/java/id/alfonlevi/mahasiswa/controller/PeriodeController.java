package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.model.Periode;
import id.alfonlevi.mahasiswa.data.repository.BaseRepository;
import id.alfonlevi.mahasiswa.data.repository.PeriodeRepository;
import id.alfonlevi.mahasiswa.view.periode.PeriodeView;

import javax.swing.*;

public class PeriodeController {
    private final PeriodeRepository mRepository;
    private final PeriodeView mView;
    private final DefaultListModel<Periode> mListModel = new DefaultListModel<>();

    private final BaseRepository.Listener mRepositoryListener = () -> {
        refresh();
    };

    public PeriodeController(PeriodeView view) {
        mView = view;
        mRepository = RepositoryProvider.get().getPeriodeRepository();

        mView.setListModel(mListModel);

        refresh();
        mRepository.registerListener(mRepositoryListener);
    }

    private void refresh() {
        var data = mRepository.getAll();
        mListModel.clear();
        mListModel.addAll(data);
    }

    public void dispose() {
        mRepository.unregisterListener(mRepositoryListener);
    }
}

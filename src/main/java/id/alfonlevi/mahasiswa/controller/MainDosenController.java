package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.Auth;
import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.model.Periode;
import id.alfonlevi.mahasiswa.data.repository.AkunRepository;
import id.alfonlevi.mahasiswa.data.repository.MataKuliahRepository;
import id.alfonlevi.mahasiswa.data.repository.PeriodeRepository;
import id.alfonlevi.mahasiswa.view.maindosen.MainDosenView;

import javax.swing.*;

public class MainDosenController {
    private final MainDosenView mView;
    private final PeriodeRepository mPeriodeRepository;
    private final MataKuliahRepository mRepository;
    private final DefaultComboBoxModel<Periode> mPeriodeModel = new DefaultComboBoxModel<>();

    public MainDosenController(MainDosenView view) {
        mView = view;
        mPeriodeRepository = RepositoryProvider.get().getPeriodeRepository();
        mRepository = RepositoryProvider.get().getMataKuliahRepository();

        mView.setPeriodeModel(mPeriodeModel);

        for (var item : mPeriodeRepository.getAll())
            mPeriodeModel.addElement(item);

        var akun = RepositoryProvider.get().getDosenRepository().get(Auth.USERNAME);
        mView.setTitle(String.format("Dosen - %s (%s)", akun.getNama(), akun.getNip()));
    }

    public void setSelectedPeriode(String id) {
        if (id == null) {
            mView.setData(null);
            return;
        }
        var data = mRepository.getAllByDosen(id, Auth.USERNAME);
        mView.setData(data);
    }
}

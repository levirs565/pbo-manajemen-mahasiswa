package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.Auth;
import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.repository.BaseRepository;
import id.alfonlevi.mahasiswa.data.repository.KelasRepository;
import id.alfonlevi.mahasiswa.data.repository.MataKuliahRepository;
import id.alfonlevi.mahasiswa.view.matakuliah.MataKuliahView;
import id.alfonlevi.mahasiswa.view.matakuliahdosen.MataKuliahDosenView;

public class MataKuliahDosenController {
    private String mId;
    private MataKuliahDosenView mView;
    private MataKuliahRepository mMataKuliahRepository;
    private KelasRepository mKelasRepository;

    public MataKuliahDosenController(MataKuliahDosenView view, String id) {
        mId = id;
        mView = view;

        mMataKuliahRepository = RepositoryProvider.get().getMataKuliahRepository();
        mKelasRepository = RepositoryProvider.get().getKelasRepository();

        var mataKuliah = mMataKuliahRepository.get(mId);
        mView.setTitle(mataKuliah.getNama());

        var kelasData = mKelasRepository.getAll(mId, Auth.USERNAME);
        mView.setKelasList(kelasData);
    }

    public String getId() {
        return mId;
    }
}

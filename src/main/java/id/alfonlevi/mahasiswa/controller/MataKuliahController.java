package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.repository.BaseRepository;
import id.alfonlevi.mahasiswa.data.repository.KelasRepository;
import id.alfonlevi.mahasiswa.data.repository.MataKuliahRepository;
import id.alfonlevi.mahasiswa.view.matakuliah.MataKuliahView;

public class MataKuliahController {
    private String mId;
    private MataKuliahView mView;
    private MataKuliahRepository mMataKuliahRepository;
    private KelasRepository mKelasRepository;

    private final BaseRepository.Listener mMataKuliahListener = () -> {
        refreshTitle();
    };
    private final BaseRepository.Listener mKelasListener = () -> {
        refreshKelas();
    };
    
    public MataKuliahController(MataKuliahView view, String id) {
        mId = id;
        mView = view;

        mMataKuliahRepository = RepositoryProvider.get().getMataKuliahRepository();
        mKelasRepository = RepositoryProvider.get().getKelasRepository();

        refreshTitle();
        refreshKelas();

        mMataKuliahRepository.registerListener(mMataKuliahListener);
        mKelasRepository.registerListener(mKelasListener);
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

    public void dispose() {
        mMataKuliahRepository.unregisterListener(mMataKuliahListener);
        mKelasRepository.unregisterListener(mKelasListener);
    }
}

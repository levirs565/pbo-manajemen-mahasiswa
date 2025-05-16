package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.model.Kelas;
import id.alfonlevi.mahasiswa.data.repository.KelasRepository;
import id.alfonlevi.mahasiswa.view.editkelas.EditKelasView;

public class EditKelasController {
    private final String mId;
    private String mMataKuliahId;
    private final EditKelasView mView;
    private final KelasRepository mRepository;

    public EditKelasController(EditKelasView view, String id, String mataKuliahId) {
        mView = view;
        mId = id;
        mMataKuliahId = mataKuliahId;
        mRepository = RepositoryProvider.get().getKelasRepository();

        if (id == null) {
            mView.showData(true, null);
        } else {
            var data = mRepository.get(mId);
            mMataKuliahId = data.getMataKuliahId();
            mView.showData(false, data);
        }
    }

    public boolean submit(String nama) {
        var data = new Kelas(
                mId,
                nama,
                mMataKuliahId
        );
        if (mId == null) {
            return mRepository.add(data);
        } else {
            return mRepository.update(data);
        }
    }
}

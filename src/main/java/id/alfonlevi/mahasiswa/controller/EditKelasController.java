package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.model.Dosen;
import id.alfonlevi.mahasiswa.data.model.Kelas;
import id.alfonlevi.mahasiswa.data.repository.DosenRepository;
import id.alfonlevi.mahasiswa.data.repository.KelasRepository;
import id.alfonlevi.mahasiswa.view.editkelas.EditKelasView;

import javax.swing.*;

public class EditKelasController {
    private final String mId;
    private String mMataKuliahId;
    private final EditKelasView mView;
    private final KelasRepository mRepository;
    private final DosenRepository mDosenRepository;
    private final DefaultComboBoxModel<Dosen> mDosenModel = new DefaultComboBoxModel<>();

    public EditKelasController(EditKelasView view, String id, String mataKuliahId) {
        mView = view;
        mId = id;
        mMataKuliahId = mataKuliahId;
        mRepository = RepositoryProvider.get().getKelasRepository();
        mDosenRepository = RepositoryProvider.get().getDosenRepository();

        mView.setDosenComboboxModel(mDosenModel);
        var dosenList = mDosenRepository.getAll();
        for (var item : dosenList) {
            mDosenModel.addElement(item);
        }

        if (id == null) {
            mView.showData(true, null);
        } else {
            var data = mRepository.get(mId);
            mMataKuliahId = data.getMataKuliahId();
            mView.showData(false, data);
            mDosenModel.setSelectedItem(
                    dosenList.stream()
                            .filter(x -> x.getUsername().equals(data.getUsernameDosen()))
                            .findFirst().get());
        }
    }

    public boolean submit(String nama) {
        var data = new Kelas(
                mId,
                nama,
                mMataKuliahId,
                ((Dosen) mDosenModel.getSelectedItem()).getUsername()
        );
        if (mId == null) {
            return mRepository.add(data);
        } else {
            return mRepository.update(data);
        }
    }
}

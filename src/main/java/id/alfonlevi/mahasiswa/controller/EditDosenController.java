package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.model.Dosen;
import id.alfonlevi.mahasiswa.data.repository.DosenRepository;
import id.alfonlevi.mahasiswa.view.editdosen.EditDosenView;

public class EditDosenController {
    private final EditDosenView mView;
    private final DosenRepository mRepository;
    private final String mUsername;
    private final boolean mIsEdit;
    private Dosen mItem;

    public EditDosenController(EditDosenView view, String username) {
        mView = view;
        mRepository = RepositoryProvider.get().getDosenRepository();
        mUsername = username;
        mIsEdit = username != null;

        if (username == null) {
            view.showData(true, null);
        } else {
            mItem = mRepository.get(username);
            mView.showData(false, mItem);
        }


    }

    public boolean submit(String username, String nip, String nama, String password) {
        if (username.isEmpty()) {
            mView.showError("Username tidak boleh kosong");
            return false;
        }
        if (!mIsEdit && mRepository.get(username) != null) {
            mView.showError("NIP telah digunakan");
            return false;
        }
        if (nip.isEmpty()) {
            mView.showError("NIP tidak boleh kosong");
            return false;
        }
        if (nama.isEmpty()) {
            mView.showError("Password tidak boleh kosong");
            return false;
        }
        if (!mIsEdit && password.isEmpty()) {
            mView.showError("Password tidak boleh kosong");
            return false;
        }
        var data = new Dosen(mIsEdit ? mUsername : username, mIsEdit ? mItem.getPassword() : password, nip, nama);
        if (mIsEdit) {
            return mRepository.update(data);
        } else {
            return mRepository.add(data);
        }
    }
}

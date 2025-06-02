package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.Auth;
import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.repository.AkunRepository;
import id.alfonlevi.mahasiswa.view.updatepassword.UpdatePasswordView;

import java.util.Objects;

public class UpdatePasswordController {
    private final UpdatePasswordView mView;
    private final AkunRepository mAkunRepository;

    public UpdatePasswordController(UpdatePasswordView view) {
        mView = view;
        mAkunRepository = RepositoryProvider.get().getAkunRepository();
    }

    public boolean submit(String old, String password, String passwordConfirm) {
        var akun = mAkunRepository.get(Auth.USERNAME);
        if (!Objects.equals(akun.getPassword(), old)) {
            mView.setError("Password salah");
            return false;
        }
        if (password.isBlank()) {
            mView.setError("Password tidak boleh kosong");
            return false;
        }
        if (!Objects.equals(passwordConfirm, password)) {
            mView.setError("Password dan Konfirmasi password harus sama");
            return false;
        }

        return mAkunRepository.updatePassword(Auth.USERNAME, password);
    }
}

package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.Auth;
import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.model.Akun;
import id.alfonlevi.mahasiswa.data.repository.AkunRepository;
import id.alfonlevi.mahasiswa.view.login.LoginView;

import java.util.Optional;

public class LoginController {
    private final LoginView mView;
    private final AkunRepository mAkunRepository;

    public LoginController(LoginView view) {
        mView = view;
        mAkunRepository = RepositoryProvider.get().getAkunRepository();
    }

    public Optional<Akun.Role> login(String username, String password) {
        var akun = mAkunRepository.get(username);
        if (akun == null || !akun.getPassword().equals(password)) {
            mView.showError("Username atau password salah");
            return Optional.empty();
        }

        Auth.USERNAME = akun.getUsername();
        return Optional.of(akun.getRole());
    }
}

package id.alfonlevi.mahasiswa.data.model;

public class Akun {
    public enum Role {
        DOSEN, ADMIN;
    }

    private String mUsername;
    private String mPassword;
    private Role mRole;

    public Akun(String username, String password, Role role) {
        mUsername = username;
        mPassword = password;
        mRole = role;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public Role getRole() {
        return mRole;
    }
}

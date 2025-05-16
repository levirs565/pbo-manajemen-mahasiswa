/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.model.Mahasiswa;
import id.alfonlevi.mahasiswa.data.repository.MahasiswaRepository;
import id.alfonlevi.mahasiswa.view.editmahasiswa.EditMahasiswaView;

/**
 *
 * @author levir
 */
public class EditMahasiswaController {
    private final EditMahasiswaView mView;
    private final MahasiswaRepository mRepository;
    private boolean mIsEdit;

    public EditMahasiswaController(EditMahasiswaView view, String nim) {
        this.mView = view;
        this.mRepository = RepositoryProvider.get().getMahasiswaRepository();

        mIsEdit = nim != null;
        if (!mIsEdit) {
            mView.showData(true, null);
        } else {
            var data = mRepository.get(nim);
            mView.showData(false, data);
        }
    }

    public boolean submit(Mahasiswa mahasiswa) {
        if (!mIsEdit && this.mRepository.get(mahasiswa.getNim()) != null) {
            mView.showError("NIM sudah digunakan");
            return false;
        }
        
        if (!mIsEdit) return this.mRepository.add(mahasiswa);
        else return this.mRepository.update(mahasiswa);
    }
}

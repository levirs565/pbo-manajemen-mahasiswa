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

    public boolean submit(String nim, String nama) {
        try {
            Utils.ensureNotBlank("NIM", nim);

            if (nim.length() > 10)
                throw new Utils.ControllerException("NIM tidak boleh lebih dari 10 karakter");
            Utils.ensureNotBlank("Nama", nama);

            if (!mIsEdit && this.mRepository.get(nim) != null)
                throw new Utils.ControllerException("NIM sudah digunakan");


            var mahasiswa = new Mahasiswa(nim, nama);
            if (!mIsEdit) return this.mRepository.add(mahasiswa);
            else return this.mRepository.update(mahasiswa);
        } catch (Utils.ControllerException e) {
            mView.showError(e.getMessage());
            return false;
        }
    }
}

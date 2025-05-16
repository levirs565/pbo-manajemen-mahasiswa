/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.model.MataKuliah;
import id.alfonlevi.mahasiswa.data.repository.MataKuliahRepository;
import id.alfonlevi.mahasiswa.view.editmatakuliah.EditMataKuliahView;

public class EditMataKuliahController {
    private MataKuliahRepository mRepository;
    private EditMataKuliahView mView;
    private String mId;

    public EditMataKuliahController(EditMataKuliahView view, String id) {
        mView = view;
        mRepository = RepositoryProvider.get().getMataKuliahRepository();

        mId = id;
        if (mId != null) {
            var data = mRepository.get(id);
            mView.showData(false, data);
        } else {
            mView.showData(true, null);
        }
    }

    public boolean submit(String nama) {
        var mataKuliah = new MataKuliah(mId, nama);
        if (mId == null) {
            return mRepository.add(mataKuliah);
        } else {
            return mRepository.update(mataKuliah);
        }
    }
}

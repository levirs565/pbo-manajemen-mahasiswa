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
    private final MataKuliahRepository mRepository;
    private final EditMataKuliahView mView;
    private final String mId;
    private String mPeriodeId;

    public EditMataKuliahController(EditMataKuliahView view, String id, String periodeId) {
        mView = view;
        mRepository = RepositoryProvider.get().getMataKuliahRepository();
        mPeriodeId = periodeId;

        mId = id;
        if (mId != null) {
            var data = mRepository.get(id);
            mPeriodeId = data.getPeriodeId();
            mView.showData(false, data);
        } else {
            mView.showData(true, null);
        }
    }

    public boolean submit(String nama) {
        try {
            Utils.ensureNotBlank("Nama", nama);
            var mataKuliah = new MataKuliah(mId, mPeriodeId, nama);
            if (mId == null) {
                return mRepository.add(mataKuliah);
            } else {
                return mRepository.update(mataKuliah);
            }
        } catch (Utils.ControllerException e) {
            mView.showError(e.getMessage());
            return false;
        }
    }
}

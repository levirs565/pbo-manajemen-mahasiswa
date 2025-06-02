package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.model.Periode;
import id.alfonlevi.mahasiswa.data.repository.PeriodeRepository;
import id.alfonlevi.mahasiswa.view.editperiode.EditPeriodeView;

import javax.swing.*;
import java.util.*;

public class EditPeriodeController {
    private final PeriodeRepository mRepository;
    private final EditPeriodeView mView;
    private final String mId;
    private final DefaultComboBoxModel<Integer> mTahunModel = new DefaultComboBoxModel<>();
    private final DefaultComboBoxModel<String> mSemesterModel = new DefaultComboBoxModel<>();

    public EditPeriodeController(EditPeriodeView view, String id) {
        mView = view;
        mId = id;

        mRepository = RepositoryProvider.get().getPeriodeRepository();


        var tahunSekarang = Calendar.getInstance().get(Calendar.YEAR);
        for (var tahun = 2000; tahun <= tahunSekarang; tahun++) {
            mTahunModel.addElement(tahun);
        }
        mView.setTahunModel(mTahunModel);

        mSemesterModel.addAll(Arrays.asList(Periode.sSEMESTER));
        mView.setSemesterModel(mSemesterModel);

        if (mId == null) {
            mView.setData(null, true);
        } else {
            var data = mRepository.get(mId);
            mView.setData(data, false);
        }
    }

    public boolean submit() {
        try {
            var tahun = mTahunModel.getSelectedItem();
            var semester = mSemesterModel.getSelectedItem();
            Utils.ensureNotBlank("Tahun", tahun);
            Utils.ensureNotBlank("Semester", semester);

            var periode = new Periode(
                    mId,
                    (Integer) tahun,
                    Objects.equals(semester, Periode.sSEMESTER[1])
            );

            if (mId == null) {
                return mRepository.add(periode);
            } else {
                return mRepository.update(periode);
            }
        } catch (Utils.ControllerException e) {
            mView.showError(e.getMessage());
            return false;
        }
    }
}

package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.model.Periode;
import id.alfonlevi.mahasiswa.data.repository.MahasiswaRepository;
import id.alfonlevi.mahasiswa.data.repository.PeriodeRepository;
import id.alfonlevi.mahasiswa.view.nilaimahasiswa.NilaiMahasiswaView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NilaiMahasiswaController {
    private NilaiMahasiswaView mView;
    private final PeriodeRepository mPeriodeRepository;
    private final MahasiswaRepository mMahasiswaRepository;
    private final DefaultComboBoxModel<Periode> mPeriodeModel = new DefaultComboBoxModel<>();
    private final DefaultTableModel mTableModel = new DefaultTableModel(
            new String[]{"Mata Kuliah", "Kelas", "Nilai"},
            0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public NilaiMahasiswaController(NilaiMahasiswaView view) {
        mView = view;
        mMahasiswaRepository = RepositoryProvider.get().getMahasiswaRepository();
        mPeriodeRepository = RepositoryProvider.get().getPeriodeRepository();

        mView.setPeriodeModel(mPeriodeModel);
        mView.setTableModel(mTableModel);

        for (var item : mPeriodeRepository.getAll())
            mPeriodeModel.addElement(item);
    }

    public void show(String NIM, String nama) {
        var mahasiswa = mMahasiswaRepository.get(NIM);
        if ((mahasiswa == null) || !mahasiswa.getNama().equals(nama)) {
            mView.setError("Mahasiswa tidak ditemukan");
            return;
        }
        var periode = mPeriodeModel.getSelectedItem();
        if (periode == null) {
            mView.setError("Belum ada periode yang dipilih");
            return;
        }
        mView.setError("");

        var data = mMahasiswaRepository.getNilai(((Periode) periode).getId(), NIM);
        mTableModel.setRowCount(0);

        for (var nilai : data) {
            mTableModel.addRow(new Object[]{nilai.getMataKuliah().getNama(), nilai.getKelas().getNama(), nilai.getNilai()});
        }
    }
}

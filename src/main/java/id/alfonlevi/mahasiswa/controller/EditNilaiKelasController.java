package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.repository.KelasRepository;
import id.alfonlevi.mahasiswa.data.repository.MahasiswaRepository;
import id.alfonlevi.mahasiswa.view.editanggotakelas.EditAnggotaKelasView;
import id.alfonlevi.mahasiswa.view.editnilaikelas.EditNilaiKelasView;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class EditNilaiKelasController {
    private final String mId;
    private final KelasRepository mKelasRepository;
    private final EditNilaiKelasView mView;
    private final DefaultTableModel mTableModel = new DefaultTableModel(
            new String[]{"Dipilih", "NIM", "Nama"},
            0
    ) {
        private static final Class<?>[] sCOLUMN_CLASSES = {String.class, String.class, Integer.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return sCOLUMN_CLASSES[columnIndex];
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 2;
        }
    };

    public EditNilaiKelasController(String id, EditNilaiKelasView view) {
        mId = id;
        mView = view;

        mKelasRepository = RepositoryProvider.get().getKelasRepository();

        var anggotaKelas = mKelasRepository.getAnggotaKelas(id);
        var nilai = mKelasRepository.getNilaiKelas(id);

        for (var mahasiswa : anggotaKelas) {
            mTableModel.addRow(new Object[]{
                    mahasiswa.getNim(),
                    mahasiswa.getNama(),
                    nilai.get(mahasiswa.getNim())
            });
        }

        mView.setTableModel(mTableModel);
    }

    public boolean submit() {
        var nilai = new HashMap<String, Integer>();
        for (int row = 0; row < mTableModel.getRowCount(); row++) {
            var value =  (Integer) mTableModel.getValueAt(row, 2);
            if (value == null) {
                value = 0;
            }
            nilai.put(mTableModel.getValueAt(row, 0).toString(), value);
        }

        return mKelasRepository.updateNilaiKelas(mId, nilai);
    }
}

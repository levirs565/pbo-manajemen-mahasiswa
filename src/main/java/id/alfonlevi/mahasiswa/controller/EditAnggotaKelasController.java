package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.model.Mahasiswa;
import id.alfonlevi.mahasiswa.data.repository.KelasRepository;
import id.alfonlevi.mahasiswa.data.repository.MahasiswaRepository;
import id.alfonlevi.mahasiswa.view.editanggotakelas.EditAnggotaKelasView;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EditAnggotaKelasController {
    private final String mId;
    private final MahasiswaRepository mMahasiswaRepository;
    private final KelasRepository mKelasRepository;
    private final EditAnggotaKelasView mView;
    private final DefaultTableModel mTableModel = new DefaultTableModel(
            new String[]{"Dipilih", "NIM", "Nama"},
            0
    ) {
        private static final Class<?>[] sCOLUMN_CLASSES = {Boolean.class, String.class, String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return sCOLUMN_CLASSES[columnIndex];
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 0;
        }
    };

    public EditAnggotaKelasController(String id, EditAnggotaKelasView view) {
        mId = id;
        mView = view;

        mMahasiswaRepository = RepositoryProvider.get().getMahasiswaRepository();
        mKelasRepository = RepositoryProvider.get().getKelasRepository();

        var mahasiswaList = mMahasiswaRepository.getAll();
        var anggotaKelas = mKelasRepository.getAnggotaKelas(id);
        var anggotaIdSet = anggotaKelas.stream().map((mahasiswa) -> mahasiswa.getNim()).collect(Collectors.toSet());

        for (var mahasiswa : mahasiswaList) {
            mTableModel.addRow(new Object[]{
                    anggotaIdSet.contains(mahasiswa.getNim()),
                    mahasiswa.getNim(),
                    mahasiswa.getNama(),
            });
        }

        mView.setTableModel(mTableModel);
    }

    public boolean submit() {
        var anggotaIdList = new ArrayList<String>();
        for (int row = 0; row < mTableModel.getRowCount(); row++) {
            if (!((boolean) mTableModel.getValueAt(row, 0)))
                continue;

            var nim = (String) mTableModel.getValueAt(row, 1);
            anggotaIdList.add(nim);
        }

        mKelasRepository.updateAnggotaKelas(mId, anggotaIdList);
        return true;
    }
}

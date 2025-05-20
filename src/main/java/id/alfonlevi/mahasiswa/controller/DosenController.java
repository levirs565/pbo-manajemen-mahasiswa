package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.repository.BaseRepository;
import id.alfonlevi.mahasiswa.data.repository.DosenRepository;
import id.alfonlevi.mahasiswa.view.dosen.DosenView;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DosenController {
    private final DosenRepository mRepository;
    private final DosenView mView;
    private final DefaultTableModel mTableModel = new DefaultTableModel(
            new String[]{"NIP", "Username", "Nama"},
            0
    );

    private BaseRepository.Listener mListener = () -> {
        refresh();
    };

    public DosenController(DosenView view) {
        mView = view;
        mRepository = RepositoryProvider.get().getDosenRepository();

        mView.setTableModel(mTableModel);

        refresh();
        mRepository.registerListener(mListener);
    }

    private void refresh() {
        mTableModel.setRowCount(0);

        var data = mRepository.getAll();
        for (var dosen : data) {
            mTableModel.addRow(new Object[]{dosen.getNip(), dosen.getUsername(), dosen.getNama()});
        }
    }

    public void dispose() {
        mRepository.unregisterListener(mListener);
    }
}

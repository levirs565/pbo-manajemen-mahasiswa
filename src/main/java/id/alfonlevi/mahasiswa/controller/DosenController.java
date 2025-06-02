package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.model.Dosen;
import id.alfonlevi.mahasiswa.data.repository.BaseRepository;
import id.alfonlevi.mahasiswa.data.repository.DosenRepository;
import id.alfonlevi.mahasiswa.view.dosen.DosenView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class DosenController {
    private final DosenRepository mRepository;
    private final DosenView mView;
    private List<Dosen> mList = List.of();
    private final DefaultTableModel mTableModel = new DefaultTableModel(
            new String[]{"NIP", "Username", "Nama"},
            0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final DefaultListSelectionModel mTableSelectionModel = new DefaultListSelectionModel();

    private BaseRepository.Listener mListener = () -> {
        refresh();
    };

    public DosenController(DosenView view) {
        mView = view;
        mRepository = RepositoryProvider.get().getDosenRepository();

        mView.setTableModel(mTableModel, mTableSelectionModel);

        refresh();
        mRepository.registerListener(mListener);
    }

    private void refresh() {
        mTableModel.setRowCount(0);

        mList = mRepository.getAll();
        for (var dosen : mList) {
            mTableModel.addRow(new Object[]{dosen.getNip(), dosen.getUsername(), dosen.getNama()});
        }
    }

    public String getSelectedUsername() {
        var selection = mTableSelectionModel.getMinSelectionIndex();
        if (selection == -1) {
            mView.showError("Belum ada yang dipilih");
            return null;
        }
        return mList.get(selection).getUsername();
    }

    public void deleteSelected() {
        var selected = getSelectedUsername();
        if (selected == null) return;

        mRepository.delete(selected);
    }

    public void dispose() {
        mRepository.unregisterListener(mListener);
    }
}

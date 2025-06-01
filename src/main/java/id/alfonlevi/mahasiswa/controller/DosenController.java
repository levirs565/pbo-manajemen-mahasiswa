package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.model.Dosen;
import id.alfonlevi.mahasiswa.data.repository.BaseRepository;
import id.alfonlevi.mahasiswa.data.repository.DosenRepository;
import id.alfonlevi.mahasiswa.view.dosen.DosenView;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.List;

public class DosenController {
    private final DosenRepository mRepository;
    private final DosenView mView;
    private List<Dosen> mList = List.of();
    private final DefaultTableModel mTableModel = new DefaultTableModel(
            new String[]{"NIP", "Username", "Nama"},
            0
    );
    private String mSelectedUsername = null;

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

        mList = mRepository.getAll();
        for (var dosen : mList) {
            mTableModel.addRow(new Object[]{dosen.getNip(), dosen.getUsername(), dosen.getNama()});
        }
        mSelectedUsername = null;
    }

    public void updateSelected(int index) {
        if (index == -1) {
            mSelectedUsername = null;
            return;
        }
        mSelectedUsername = mList.get(index).getUsername();
    }

    public String getSelectedUsername() {
        if (mSelectedUsername == null) {
            mView.showError("Belum ada yang dipilih");
            return null;
        }
        return mSelectedUsername;
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

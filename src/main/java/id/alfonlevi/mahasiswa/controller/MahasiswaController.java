/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.repository.BaseRepository;
import id.alfonlevi.mahasiswa.data.repository.MahasiswaRepository;
import id.alfonlevi.mahasiswa.view.mahasiswa.MahasiswaView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author levir
 */
public class MahasiswaController {
    private final MahasiswaView mView;
    private final MahasiswaRepository mRepository;
    
    private final DefaultTableModel mModel = new DefaultTableModel(
        new String[]{"NIM", "Nama"},
        0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final DefaultListSelectionModel mSelectionModel = new DefaultListSelectionModel();
    private final BaseRepository.Listener mListener = () -> {
        refresh();
    };
    
    public MahasiswaController(MahasiswaView view) {
        this.mView = view;
        this.mRepository = RepositoryProvider.get().getMahasiswaRepository();
        
        mView.setTableModel(mModel, mSelectionModel);
        refresh();
        
        mRepository.registerListener(mListener);
    }

    private void refresh() {
        mModel.setRowCount(0);
        
        var list = mRepository.getAll();
        for (var data : list) {
            mModel.addRow(new Object[]{data.getNim(), data.getNama()});
        }
    }

    public String getSelectedNim() {
        var selectionIndex = mSelectionModel.getMinSelectionIndex();
        if (selectionIndex == -1) {
            mView.showError("Belum ada yang dipilih");
            return null;
        }

        return mModel.getValueAt(selectionIndex, 0).toString();
    }
    
    public void deleteSelected() {
        var nim = getSelectedNim();
        if (nim == null) return;
        mRepository.delete(nim);
    }

    public void dispose() {
        mRepository.unregisterListener(mListener);
    }
}

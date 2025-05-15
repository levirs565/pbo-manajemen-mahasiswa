/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.alfonlevi.mahasiswa.controller;

import id.alfonlevi.mahasiswa.data.RepositoryProvider;
import id.alfonlevi.mahasiswa.data.repository.MahasiswaRepository;
import id.alfonlevi.mahasiswa.view.mahasiswa.MahasiswaView;
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
    );
    
    public MahasiswaController(MahasiswaView view) {
        this.mView = view;
        this.mRepository = RepositoryProvider.get().getMahasiswaRepository();
        
        mView.setTableModel(mModel);
        refresh();
        
        mRepository.registerListener(() -> {
            refresh();
        });
    }
    
    private void refresh() {
        mModel.setRowCount(0);
        
        var list = mRepository.getAll();
        for (var data : list) {
            mModel.addRow(new Object[]{data.getNim(), data.getNama()});
        }
    }
    
    public void delete(String nim) {
        mRepository.delete(nim);
    }
}

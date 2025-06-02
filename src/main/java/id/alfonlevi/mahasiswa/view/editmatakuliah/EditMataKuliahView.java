/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package id.alfonlevi.mahasiswa.view.editmatakuliah;

import id.alfonlevi.mahasiswa.data.model.MataKuliah;

/**
 *
 * @author levir
 */
public interface EditMataKuliahView {
    void showData(boolean isNew, MataKuliah mahasiswa);
    void showError(String error);
}

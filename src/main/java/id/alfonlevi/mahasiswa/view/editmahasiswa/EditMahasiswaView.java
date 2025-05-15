/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package id.alfonlevi.mahasiswa.view.editmahasiswa;

import id.alfonlevi.mahasiswa.data.model.Mahasiswa;

/**
 *
 * @author levir
 */
public interface EditMahasiswaView {
    void showData(boolean isNew, Mahasiswa mahasiswa);
    void showError(String error);
}

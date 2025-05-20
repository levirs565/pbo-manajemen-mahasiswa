/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.alfonlevi.mahasiswa.view.editdosen;

import id.alfonlevi.mahasiswa.data.model.Dosen;

/**
 *
 * @author LENOVO
 */
public interface EditDosenView {
    void showData(boolean isNew, Dosen dosen);
    void showError(String error);
}

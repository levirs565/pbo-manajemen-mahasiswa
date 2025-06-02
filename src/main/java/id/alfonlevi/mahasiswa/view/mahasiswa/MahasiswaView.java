/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package id.alfonlevi.mahasiswa.view.mahasiswa;

import javax.swing.*;
import javax.swing.table.TableModel;

public interface MahasiswaView {
    void setTableModel(TableModel model, ListSelectionModel selectionModel);
    void showError(String error);
}

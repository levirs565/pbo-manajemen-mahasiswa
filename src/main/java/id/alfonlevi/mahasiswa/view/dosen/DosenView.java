package id.alfonlevi.mahasiswa.view.dosen;

import javax.swing.*;
import javax.swing.table.TableModel;

public interface DosenView {
    void setTableModel(TableModel model, ListSelectionModel selectionModel);
    void showError(String message);
}

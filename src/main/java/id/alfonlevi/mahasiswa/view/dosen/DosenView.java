package id.alfonlevi.mahasiswa.view.dosen;

import javax.swing.table.TableModel;

public interface DosenView {
    void setTableModel(TableModel model);
    void showError(String message);
}

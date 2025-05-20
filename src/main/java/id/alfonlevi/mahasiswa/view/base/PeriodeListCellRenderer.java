package id.alfonlevi.mahasiswa.view.base;

import id.alfonlevi.mahasiswa.data.model.Periode;

import javax.swing.*;
import java.awt.*;

public class PeriodeListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(
            JList<?> list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus
    ) {
        if (value instanceof Periode) {
            Periode periode = (Periode) value;
            value = String.format(
                    "%d/%d - %s",
                    periode.getTahun(),
                    periode.getTahun() + 1,
                    periode.isGenap() ? "Genap" : "Ganjil"
            );
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }
}

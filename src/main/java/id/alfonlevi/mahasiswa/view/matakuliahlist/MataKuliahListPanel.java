/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package id.alfonlevi.mahasiswa.view.matakuliahlist;

import com.formdev.flatlaf.FlatClientProperties;
import id.alfonlevi.mahasiswa.controller.MataKuliahListController;
import id.alfonlevi.mahasiswa.data.model.MataKuliah;
import id.alfonlevi.mahasiswa.data.model.Periode;
import id.alfonlevi.mahasiswa.view.base.DisposableView;
import id.alfonlevi.mahasiswa.view.base.PeriodeListCellRenderer;
import id.alfonlevi.mahasiswa.view.base.TabbedPaneHelper;
import id.alfonlevi.mahasiswa.view.editmatakuliah.EditMataKuliahDialog;
import id.alfonlevi.mahasiswa.view.matakuliah.MataKuliahPanel;
import id.alfonlevi.mahasiswa.view.periode.PeriodeDialog;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author levir
 */
public class MataKuliahListPanel extends javax.swing.JPanel implements MataKuliahListView, DisposableView {
    private JLabel mEmptyLabel = new JLabel("Belum ada mata kuliah", SwingConstants.CENTER);
    private JLabel mPeriodeEmptyLabel = new JLabel("Belum Memilih Periode. Jika periode kosong, tambah periode dauhulu", SwingConstants.CENTER);
    private MataKuliahListController mController;
    private TabbedPaneHelper mTabbedPaneHelper;
    private JButton mAddButton;
    private JComboBox<Periode> mPeriodeComboBox;

    /**
     * Creates new form MataKuliahPanel
     */
    public MataKuliahListPanel() {
        initComponents();

        var boxLayout = Box.createVerticalBox();
        boxLayout.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        var titleLabel = new JLabel();
        titleLabel.setText("Mata Kuliah");
        titleLabel.putClientProperty(FlatClientProperties.STYLE_CLASS, "h2");
        titleLabel.setAlignmentX(0);
        boxLayout.add(titleLabel);

        var periodeBox = Box.createHorizontalBox();
        mPeriodeComboBox = new JComboBox<>();
        mPeriodeComboBox.setRenderer(new PeriodeListCellRenderer());
        periodeBox.add(mPeriodeComboBox);

        var periodeEditButton = new JButton("...");
        periodeEditButton.addActionListener((v) -> {
            new PeriodeDialog().setVisible(true);
        });
        periodeBox.add(periodeEditButton);

        periodeBox.setAlignmentX(0);
        boxLayout.add(periodeBox);

        mAddButton = new JButton();
        mAddButton.setText("Tambah");
        mAddButton.addActionListener((v) -> {
            new EditMataKuliahDialog(null, mController.getSelectedPeriodeId()).setVisible(true);
        });
        mAddButton.setAlignmentX(0);
        boxLayout.add(mAddButton);

        mTabPane.putClientProperty(FlatClientProperties.TABBED_PANE_LEADING_COMPONENT, boxLayout);
        mTabPane.putClientProperty(FlatClientProperties.TABBED_PANE_MINIMUM_TAB_WIDTH, 175);

        mTabbedPaneHelper = new TabbedPaneHelper(mTabPane, (id) -> {
            if (id.equals("kosong")) return mEmptyLabel;
            if (id.equals("periode-ajar-kosong")) return mPeriodeEmptyLabel;

            return new MataKuliahPanel(id);
        });
        mController = new MataKuliahListController(this);

        mPeriodeComboBox.addActionListener((v) -> {
            mController.refresh();
        });
        mPeriodeComboBox.setSelectedIndex(-1);
    }

    @Override
    public void setData(List<MataKuliah> data) {
        var items = new ArrayList<TabbedPaneHelper.Item>();
        mAddButton.setVisible(data != null);
        if (data == null) {
            items.add(new TabbedPaneHelper.Item("periode-ajar-kosong", "Belum Memilih Periode Ajar"));
        } else if (data.isEmpty()) {
            items.add(new TabbedPaneHelper.Item("kosong", "Masih Kosong"));
        } else {
            for (var mataKuliah : data) {
                items.add(new TabbedPaneHelper.Item(mataKuliah.getId(), mataKuliah.getNama()));
            }
        }
        mTabbedPaneHelper.setItems(items);
    }

    @Override
    public void setPeriodeModel(ComboBoxModel<Periode> model) {
        mPeriodeComboBox.setModel(model);
    }

    @Override
    public void dispose() {
        mController.dispose();
        mTabbedPaneHelper.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mTabPane = new javax.swing.JTabbedPane();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        mTabPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        add(mTabPane);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane mTabPane;
    // End of variables declaration//GEN-END:variables
}

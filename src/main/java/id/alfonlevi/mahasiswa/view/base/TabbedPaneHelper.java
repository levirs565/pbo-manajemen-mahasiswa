package id.alfonlevi.mahasiswa.view.base;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class TabbedPaneHelper implements ChangeListener {
    private static final String EMPTY_TAG = "TABBED-PANE-HELPER-EMPTY";
    private final JTabbedPane mTabbedPane;
    private final ArrayList<Item> mItems = new ArrayList<>();
    private final Adapter mAdapter;
    private String mActiveId = EMPTY_TAG;
    private Component mActiveComponent = null;
    private JPanel mActivePanel = null;

    public TabbedPaneHelper(JTabbedPane tabbedPane, Adapter adapter) {
        mTabbedPane = tabbedPane;
        mAdapter = adapter;

        mTabbedPane.addChangeListener(this);
    }

    private void updateState() {
        var index = mTabbedPane.getSelectedIndex();
        if (index == -1) {
            if (mActiveComponent instanceof DisposableView) {
                ((DisposableView) mActiveComponent).dispose();
            }
            if (mActivePanel != null) {
                mActivePanel.removeAll();
            }

            mActiveComponent = null;
            mActivePanel = null;
            mActiveId = EMPTY_TAG;
            return;
        }
        var id = mItems.get(index).id();
        var panel = (JPanel) mTabbedPane.getComponentAt(index);
        if (Objects.equals(mActiveId, id) && mActivePanel != panel) {
            mActivePanel.removeAll();
            mActivePanel = panel;
            mActivePanel.add(mActiveComponent);
        }
        if (!Objects.equals(mActiveId, id)) {
            if (mActiveComponent instanceof DisposableView) {
                ((DisposableView) mActiveComponent).dispose();
            }
            if (mActivePanel != null)
                mActivePanel.removeAll();

            mActiveId = id;
            mActiveComponent = mAdapter.getItem(mActiveId);
            mActivePanel = panel;

            mActivePanel.add(mActiveComponent);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        updateState();
    }

    public void setItems(List<Item> data) {
        mTabbedPane.removeChangeListener(this);

        mTabbedPane.removeAll();
        mItems.clear();
        mItems.addAll(data);
        for (var item : data) {
            mTabbedPane.add(item.title(), new JPanel(new BorderLayout()));
        }

        IntStream.range(0, mItems.size())
                .filter(index -> Objects.equals(mItems.get(index).id, mActiveId))
                .findFirst()
                .ifPresent(index -> {
                    mTabbedPane.setSelectedIndex(index);
                });


        mTabbedPane.addChangeListener(this);
        updateState();
    }

    public void dispose() {
        setItems(List.of());
    }

    public record Item(String id, String title) {
    }

    public interface Adapter {
        Component getItem(String id);
    }
}

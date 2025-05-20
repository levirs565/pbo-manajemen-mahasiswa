package id.alfonlevi.mahasiswa.view.base;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class TabbedPaneHelper {
    private final JTabbedPane mTabbedPane;
    private HashSet<String> mCurrentIds = new HashSet<>();
    private final HashMap<String, Component> mComponentCache = new HashMap<>();
    private final Adapter mAdapter;

    public TabbedPaneHelper(JTabbedPane tabbedPane, Adapter adapter) {
        mTabbedPane = tabbedPane;
        mAdapter = adapter;
    }

    public void setItems(List<Item> data) {
        mTabbedPane.removeAll();

        var unused = mCurrentIds;
        mCurrentIds = new HashSet<>();

        for (var item : data) {
            unused.remove(item.id);
            mCurrentIds.add(item.id);

            var component = mComponentCache.get(item.id);
            if (component == null) {
                component = mAdapter.getItem(item.id);
                mComponentCache.put(item.id, component);
            }

            mTabbedPane.add(item.title, component);
        }

        for (var id : unused) {
            var component = mComponentCache.remove(id);
            if (component instanceof DisposableView)
                ((DisposableView) component).dispose();
        }
    }

    public record Item(String id, String title) {}

    public interface Adapter {
        Component getItem(String id);
    }
}

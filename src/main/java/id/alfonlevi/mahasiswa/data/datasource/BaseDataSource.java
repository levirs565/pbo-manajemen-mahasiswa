/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.alfonlevi.mahasiswa.data.datasource;

import id.alfonlevi.mahasiswa.data.repository.BaseRepository;
import java.util.ArrayList;

public class BaseDataSource implements BaseRepository {
    private ArrayList<BaseRepository.Listener> mListeners = new ArrayList<>();
    
    @Override
    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }
    
    protected void invokeListener() {
        for (var listener : mListeners) {
            listener.onRepositoryChanged();
        }
    }
}

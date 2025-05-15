/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package id.alfonlevi.mahasiswa.data.repository;

/**
 *
 * @author levir
 */
public interface BaseRepository {
    void registerListener(Listener listener);
    
    public interface Listener {
        void onRepositoryChanged();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package id.alfonlevi.mahasiswa;

import com.formdev.flatlaf.FlatIntelliJLaf;
import id.alfonlevi.mahasiswa.view.login.LoginFrame;
import id.alfonlevi.mahasiswa.view.main.MainFrame;
import java.awt.EventQueue;

/**
 *
 * @author levir
 */
public class Main {

    public static void main(String[] args) {
//        var mahasiswaRepository = RepositoryProvider.get().getMahasiswaRepository();
//
//        mahasiswaRepository.add(new Mahasiswa("123230127", "Levi"));
//        mahasiswaRepository.getAll().forEach((v) -> {
//            System.out.println(v.getNama() + " "+ v.getNim());
//        });
//
//        mahasiswaRepository.update(new Mahasiswa("123230127", "Levi Rizki Saputra"));
//        mahasiswaRepository.getAll().forEach((v) -> {
//            System.out.println(v.getNama() + " "+ v.getNim());
//        });
//        mahasiswaRepository.delete("123230127");
        FlatIntelliJLaf.setup();
 
        EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}

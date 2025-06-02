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
        FlatIntelliJLaf.setup();

        EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}

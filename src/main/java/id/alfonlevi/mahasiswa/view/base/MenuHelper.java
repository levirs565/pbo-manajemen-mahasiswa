package id.alfonlevi.mahasiswa.view.base;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import id.alfonlevi.mahasiswa.data.Auth;
import id.alfonlevi.mahasiswa.view.login.LoginFrame;
import id.alfonlevi.mahasiswa.view.updatepassword.UpdatePasswordDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuHelper {
    private MenuHelper() {}

    public static void applyMenu(JFrame frame, boolean akun) {
        var menu = new JMenuBar();

        if (akun) {
            var akunMenu = new JMenu("Akun");
            akunMenu.add(new AbstractAction("Ubah Password") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new UpdatePasswordDialog().setVisible(true);
                }
            });
            akunMenu.add(new AbstractAction("Logout") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Auth.USERNAME = null;
                    new LoginFrame().setVisible(true);
                    frame.dispose();
                }
            });
            menu.add(akunMenu);
        }

        var themeMenu = new JMenu("Theme");
        themeMenu.add(new AbstractAction("Light") {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlatIntelliJLaf.setup();
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });
        themeMenu.add(new AbstractAction("Dark") {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlatDarculaLaf.setup();
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });
        menu.add(themeMenu);

        frame.setJMenuBar(menu);
    }
}

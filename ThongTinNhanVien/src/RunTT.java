

import javax.swing.*;

public class RunTT {
    public static void main(String[] args) {
        Gui gui = new Gui();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
        gui.setLocationRelativeTo(null);
        gui.setResizable(false);
        gui.setSize(800,500);
    }
}

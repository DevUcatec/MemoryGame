import View.MainGUI;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    JFrame frame = new MainGUI();
                    frame.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

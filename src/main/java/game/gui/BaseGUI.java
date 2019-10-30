package game.gui;

import javax.swing.*;
import java.awt.*;

public class BaseGUI extends JFrame {

    public BaseGUI() {
        initUI();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new BaseGUI();
            ex.setVisible(true);
        });
    }

    private void initUI() {
        JPanel drawPanel = new MapPanel();
        add(drawPanel);

        setSize(350, 250);
        setTitle("Stars");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
package game.gui;

import game.CommonData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

import static game.CommonData.mapHeight;
import static game.CommonData.mapWidth;

public class BaseGUI extends JFrame implements Runnable{
    public BaseGUI(){
        initUI();
    }

    public void run() {
        EventQueue.invokeLater(() -> {
            JFrame ex = new BaseGUI();
            ex.setVisible(true);
        });
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel mapPanel = new MapPanel(CommonData.galaxy.starSystems);
        JPanel playerDataPanel = new PlayerDataPanel(CommonData.players.get(0));

        mainPanel.add(mapPanel, BorderLayout.CENTER);
        mainPanel.add(playerDataPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setSize(mapWidth, mapHeight+50);
        setTitle("Stars");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                CommonData.continueGame = false;
                System.exit(0);
            }
        });
    }
}
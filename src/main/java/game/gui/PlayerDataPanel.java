package game.gui;

import game.CommonData;
import game.player.Player;

import javax.swing.*;

public class PlayerDataPanel extends JPanel {
    JLabel incomeLabel;
    JLabel sizeLabel;
    JLabel planetsLabel;
    JLabel shipsLabel;
    Player p;

    PlayerDataPanel(Player p) {
        this.p = p;
        incomeLabel = new JLabel("Money: " + p.money + " (" + p.getIncome() + ")");
        sizeLabel = new JLabel("Systems: " + p.getEmpireSize());
        planetsLabel = new JLabel("Planets: " + p.getPlanetCount());
        shipsLabel = new JLabel("Ships: " + p.getShipCount());

        add(incomeLabel);
        add(sizeLabel);
        add(planetsLabel);
        add(shipsLabel);

        Thread gameThread = new Thread(() -> {
            while (CommonData.continueGame) {
                updateData();
                repaint();
                try {
                    Thread.sleep(1000 / 24);  // milliseconds
                } catch (InterruptedException ex) {
                }
            }
        });
        gameThread.start();

    }

    private void updateData(){
        incomeLabel.setText("Money: " + p.money + " (" + p.getIncome() + ")");
        sizeLabel.setText("Systems: " + p.getEmpireSize());
        planetsLabel.setText("Planets: " + p.getPlanetCount());
        shipsLabel.setText("Ships: " + p.getShipCount());
    }
}

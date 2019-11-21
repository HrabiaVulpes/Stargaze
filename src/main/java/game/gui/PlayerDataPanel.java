package game.gui;

import game.player.Player;

import javax.swing.*;

public class PlayerDataPanel extends JPanel {
    PlayerDataPanel(Player p) {
        JLabel incomeLabel = new JLabel("Money: " + p.money + " (" + p.getIncome() + ")");
        JLabel sizeLabel = new JLabel("Systems: " + p.getEmpireSize());
        JLabel planetsLabel = new JLabel("Planets: " + p.getPlanetCount());
        JLabel shipsLabel = new JLabel("Ships: " + p.getShipCount());

        add(incomeLabel);
        add(sizeLabel);
        add(planetsLabel);
        add(shipsLabel);

    }
}

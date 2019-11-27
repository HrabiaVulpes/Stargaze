package game.gui;

import game.CommonData;
import game.map.StarSystem;
import game.ship.Ship;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class MapPanel extends JPanel {
    Set<StarSystem> galaxy;
    private int pointSize = 10;

    public MapPanel(Set<StarSystem> galaxy) {
        this.galaxy = galaxy;
        setSize(CommonData.mapWidth, CommonData.mapHeight);
        Thread gameThread = new Thread(() -> {
            while (CommonData.continueGame) {
                repaint();
                try {
                    Thread.sleep(1000 / 24);  // milliseconds
                } catch (InterruptedException ex) {
                }
            }
        });
        gameThread.start();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);

        for (StarSystem starSystem : galaxy) {
            int x = starSystem.getX();
            int y = starSystem.getY();
            g2d.fillOval(x - pointSize, y - pointSize, pointSize, pointSize);

            for (StarSystem connection : starSystem.getConnections()) {
                int x1 = connection.getX();
                int y1 = connection.getY();
                g2d.drawLine(x, y, x1, y1);
            }
        }

        g2d.setColor(Color.red);
        for (Ship ship : CommonData.ships) {
            int x = ship.whereIsShip.getX();
            int y = ship.whereIsShip.getY();

            g2d.fillRect(x, y - pointSize, pointSize, pointSize);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
package game.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Set;
import game.map.System;

public class MapPanel extends JPanel {
    Set<System> galaxy;
    int pointSize = 100;

    public MapPanel(Set<System> galaxy) {
        this.galaxy = galaxy;
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);

        for (System system : galaxy) {
            int x = system.getX();
            int y = system.getY();
            g2d.drawLine(x, y, x, y);

            for (System connection : system.getConnections()){
                int x1 = connection.getX();
                int y1 = connection.getY();
                g2d.fillOval(x-pointSize, y-pointSize, pointSize, pointSize);
                g2d.drawLine(x, y, x1, y1);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
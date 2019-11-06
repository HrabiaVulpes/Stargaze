package game.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Set;
import game.map.StarSystem;

public class MapPanel extends JPanel {
    Set<StarSystem> galaxy;
    int pointSize = 10;

    public MapPanel(Set<StarSystem> galaxy) {
        this.galaxy = galaxy;
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);

        for (StarSystem starSystem : galaxy) {
            int x = starSystem.getX();
            int y = starSystem.getY();
            g2d.fillOval(x-pointSize, y-pointSize, pointSize, pointSize);

            for (StarSystem connection : starSystem.getConnections()){
                int x1 = connection.getX();
                int y1 = connection.getY();
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
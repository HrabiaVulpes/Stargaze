package game.gui;

import game.CommonData;
import game.map.StarSystem;

import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;

public class MapPanel extends JPanel {
   Set<StarSystem> galaxy;
   private int elementSize = 15;
   private int systemSize = 10;

   public MapPanel(Set<StarSystem> galaxy) {
      this.galaxy = galaxy;
      setSize(CommonData.mapWidth, CommonData.mapHeight);
      Thread gameThread = new Thread(() -> {
         while (CommonData.continueGame) {
            repaint();
            try {
               Thread.sleep(1000 / 24);  // milliseconds
            } catch (InterruptedException ignored) {
            }
         }
      });
      gameThread.start();
   }

   private Polygon triangleInPlace(int x, int y) {
      Polygon polygon = new Polygon();
      polygon.xpoints = new int[]{x + elementSize / 2, x + elementSize, x};
      polygon.ypoints = new int[]{y - elementSize, y, y};
      polygon.npoints = 3;

      return polygon;
   }

   private void doDrawing(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;

      for (StarSystem starSystem : galaxy) {
         g2d.setColor(Color.GRAY);
         int x = starSystem.getX();
         int y = starSystem.getY();

         for (StarSystem connection : starSystem.getConnections()) {
            int x1 = connection.getX();
            int y1 = connection.getY();
            g2d.drawLine(x, y, x1, y1);
         }

         g2d.setColor(starSystem.getOwner().color);
         g2d.fillOval(x - systemSize / 2, y - systemSize / 2, systemSize, systemSize);

         final int[] offset = {0};
         CommonData.ships.stream()
                 .filter(ship -> ship.whereIsShip == starSystem)
                 .map(ship -> ship.owner)
                 .collect(Collectors.toSet())
                 .forEach(
                         player -> {
                            g2d.setColor(player.color);
                            g2d.fillPolygon(triangleInPlace(x + offset[0], y));
                            offset[0] += elementSize / 2;
                         }
                 );

         offset[0] = 0;
         CommonData.fortresses.stream()
                 .filter(fortress -> fortress.whereIsFortress == starSystem)
                 .map(fortress -> fortress.owner)
                 .collect(Collectors.toSet())
                 .forEach(
                         player -> {
                            g2d.setColor(player.color);
                            g2d.fillRect(x + offset[0], y, x + offset[0] + elementSize, y + elementSize);
                            offset[0] += elementSize / 2;
                         }
                 );

         CommonData.planets.stream()
                 .filter(planets -> planets.whereIsPlanet == starSystem)
                 .map(planets -> planets.owner)
                 .collect(Collectors.toSet())
                 .forEach(
                         player -> {
                            g2d.setColor(player.color);
                            g2d.fillOval(x - elementSize, y - elementSize, x, y);
                            offset[0] += elementSize / 2;
                         }
                 );
      }
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      doDrawing(g);
   }
}
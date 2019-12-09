package game.gui;

import game.CommonData;
import game.map.StarSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;
import java.util.stream.Collectors;

public class MapPanel extends JPanel implements MouseListener {
   Set<StarSystem> galaxy;

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

   private void doDrawing(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;

      g2d.setColor(Color.GRAY);
      galaxy.forEach(system -> system.getConnections().forEach(system1 -> g2d.drawLine(system.getX(), system.getY(), system1.getX(), system1.getY())));

      galaxy.forEach(system -> {
         g2d.setColor(system.owner.color);
         g2d.fill(system.shape(0));
      });

      final int[] offset = {0};
      CommonData.players.forEach(player -> {
         g2d.setColor(player.color);
         CommonData.ships.stream().filter(ship -> ship.owner == player).forEach(ship -> g2d.fill(ship.shape(offset[0])));
         CommonData.fortresses.stream().filter(fortress -> fortress.owner == player).forEach(fortress -> g2d.fill(fortress.shape(offset[0])));
         CommonData.planets.stream().filter(planet -> planet.owner == player).forEach(planet -> g2d.fill(planet.shape(offset[0])));
         offset[0] += 5;
      });
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      doDrawing(g);
   }

   @Override
   public void mouseClicked(MouseEvent mouseEvent) {

   }

   @Override
   public void mousePressed(MouseEvent mouseEvent) {

   }

   @Override
   public void mouseReleased(MouseEvent mouseEvent) {

   }

   @Override
   public void mouseEntered(MouseEvent mouseEvent) {

   }

   @Override
   public void mouseExited(MouseEvent mouseEvent) {

   }
}
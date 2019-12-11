package game.gui;

import game.CommonData;
import game.map.MapElement;
import game.map.StarSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MapPanel extends JPanel implements MouseListener {
   private Set<StarSystem> galaxy;

   public MapPanel(Set<StarSystem> galaxy) {
      this.galaxy = galaxy;
      this.addMouseListener(this);
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
         offset[0] += 1;
      });
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      doDrawing(g);
   }

   @Override
   public void mouseClicked(MouseEvent mouseEvent) {
      List<MapElement> clickedElements = new ArrayList<>();
      clickedElements.addAll(galaxy.stream().filter(system -> system.shape(0).contains(mouseEvent.getX(), mouseEvent.getY())).collect(Collectors.toList()));
      clickedElements.addAll(CommonData.ships.stream().filter(ship -> ship.shape(0).contains(mouseEvent.getX(), mouseEvent.getY())).collect(Collectors.toList()));
      clickedElements.addAll(CommonData.fortresses.stream().filter(fortress -> fortress.shape(0).contains(mouseEvent.getX(), mouseEvent.getY())).collect(Collectors.toList()));
      clickedElements.addAll(CommonData.planets.stream().filter(planet -> planet.shape(0).contains(mouseEvent.getX(), mouseEvent.getY())).collect(Collectors.toList()));

      if (!clickedElements.isEmpty()) {
         if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
            LeftClickMenu leftClickMenu = new LeftClickMenu(clickedElements);
            leftClickMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
         } else if (SwingUtilities.isRightMouseButton(mouseEvent)) {
            RightClickMenu rightClickMenu = new RightClickMenu(clickedElements);
            rightClickMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
         }
      }else CommonData.selected = null;
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
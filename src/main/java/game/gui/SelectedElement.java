package game.gui;

import game.CommonData;
import game.map.MapElement;

import javax.swing.*;

public class SelectedElement extends JPanel {
   private MapElement mapElementPicked;

   private JLabel nameLabel;
   private JLabel ownerLabel;
   private JLabel typeLabel;
   private JLabel ordersLabel;

   public SelectedElement(MapElement mapElementPicked) {
      this.mapElementPicked = mapElementPicked;
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

      nameLabel = new JLabel();
      ownerLabel = new JLabel();
      typeLabel = new JLabel();
      ordersLabel = new JLabel();

      add(nameLabel);
      add(ownerLabel);
      add(typeLabel);
      add(ordersLabel);

      Thread gameThread = new Thread(() -> {
         while (CommonData.continueGame) {
            if (CommonData.selected != null) {
               this.mapElementPicked = CommonData.selected;
               this.setVisible(true);
               updateData();
               repaint();
            } else {
               this.setVisible(false);
            }
            try {
               Thread.sleep(1000 / 24);  // milliseconds
            } catch (InterruptedException ignored) {
            }
         }
      });
      gameThread.start();
   }

   private void updateData() {
      nameLabel.setText("Selected Item: " + mapElementPicked.getClass().getSimpleName() + ":" + mapElementPicked.ID);
      ownerLabel.setText("Owner: " + mapElementPicked.owner.name);
      typeLabel.setText("Type: " + mapElementPicked.getClass().getSimpleName());
      ordersLabel.setText("Current Order: " + CommonData.orders.stream()
              .filter(order -> order.connectedMapElements.contains(mapElementPicked))
              .map(Object::toString)
              .findFirst().orElse("NoOrders"));
   }
}

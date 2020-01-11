package game.gui;

import game.staticData.CommonData;
import game.map.MapElement;
import game.planet.Planet;
import game.orders.*;
import game.ship.ShipTypes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class RightClickMenu extends JPopupMenu {

   public RightClickMenu(List<MapElement> clickedElements) {
      if (CommonData.selected != null) {
         switch (CommonData.selected.getClass().getSimpleName()) {
            case "Ship":
               addShipOrders(clickedElements);
               break;
            case "Fortress":
               addFortressOrders(clickedElements);
               break;
            case "Planet":
               addPlanetOrders(clickedElements);
               break;
            case "StarSystem":
               addSystemOrders(clickedElements);
               break;
         }
      }
   }

   private void addSystemOrders(List<MapElement> clickedElements) {
      add(new AbstractAction("Build fortress") {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {
            CommonData.orders.add(new OrderBuildFortress(CommonData.players.get(0), CommonData.selected.ID));
         }
      });
   }

   private void addPlanetOrders(List<MapElement> clickedElements) {
      add(new AbstractAction("Upgrade " + CommonData.selected.owner.name + ":" + CommonData.selected.getClass().getSimpleName()) {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {
            CommonData.orders.add(new OrderPlanetUpgrade(CommonData.players.get(0), CommonData.selected.ID));
         }
      });

      add(new AbstractAction("Downgrade " + CommonData.selected.owner.name + ":" + CommonData.selected.getClass().getSimpleName()) {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {
            CommonData.orders.add(new OrderPlanetDowngrade(CommonData.players.get(0), CommonData.selected.ID));
         }
      });

      for (int i = 1; i <= ((Planet) CommonData.selected).level; i++) {
         int finalI = i;
         add(new AbstractAction("Build " + ShipTypes.bySize(finalI).name()) {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               CommonData.orders.add(new OrderPlanetBuildShip(CommonData.players.get(0), CommonData.selected.ID, ShipTypes.bySize(finalI)));
            }
         });
      }
   }

   private void addFortressOrders(List<MapElement> clickedElements) {
      add(new AbstractAction("Upgrade " + CommonData.selected.owner.name + ":" + CommonData.selected.getClass().getSimpleName()) {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {
            CommonData.orders.add(new OrderFortressUpgrade(CommonData.players.get(0), CommonData.selected.ID));
         }
      });

      add(new AbstractAction("Downgrade " + CommonData.selected.owner.name + ":" + CommonData.selected.getClass().getSimpleName()) {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {
            CommonData.orders.add(new OrderFortressDowngrade(CommonData.players.get(0), CommonData.selected.ID));
         }
      });
   }

   private void addShipOrders(List<MapElement> clickedElements) {
      for (MapElement element : clickedElements) {
         switch (element.getClass().getSimpleName()) {
            case "Ship":
               add(new AbstractAction("Attack " + element.owner.name + ":" + element.getClass().getSimpleName()) {
                  @Override
                  public void actionPerformed(ActionEvent actionEvent) {
                     CommonData.orders.add(new OrderShoot(CommonData.players.get(0), CommonData.selected.ID, element.ID));
                  }
               });
               break;
            case "Fortress":
               break;
            case "Planet":
               break;
            case "StarSystem":
               add(new AbstractAction("Move to " + element.owner.name + ":" + element.getClass().getSimpleName()) {
                  @Override
                  public void actionPerformed(ActionEvent actionEvent) {
                     CommonData.orders.add(new OrderMove(CommonData.players.get(0), CommonData.selected.ID, element.ID));
                  }
               });
               add(new AbstractAction("Take " + element.owner.name + ":" + element.getClass().getSimpleName()) {
                  @Override
                  public void actionPerformed(ActionEvent actionEvent) {
                     CommonData.orders.add(new OrderTakeSystem(CommonData.players.get(0), CommonData.selected.ID));
                  }
               });
               break;
         }
      }
   }
}

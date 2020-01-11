package game;

import game.gui.BaseGUI;
import game.fortress.Fortress;
import game.map.Galaxy;
import game.map.galaxyGeneration.GalaxyGenerator;
import game.player.Player;
import game.orders.OrderError;
import game.orders.OrderType;
import game.staticData.CommonData;

import java.util.stream.Collectors;

import static game.staticData.CommonData.*;
import static game.staticData.MapSetting.mapHeight;
import static game.staticData.MapSetting.mapWidth;

public class Game {
   private static Object LOCK = new Object();
   private BaseGUI baseGUI;

   public void mainLoop() {
      initData();
      baseGUI = new BaseGUI();

      Thread t = new Thread(baseGUI);
      t.start();

      while (continueGame) {
         long startTime = System.currentTimeMillis();

         economics();
         diplomacy();
         orders();
         movement();
         warfare();
         development();

         synchronized (LOCK) {
            try {
               long waitTime = gameSpeed + startTime - System.currentTimeMillis();
               LOCK.wait(waitTime > 0 ? waitTime : 0);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }

         cleanUp();
      }
   }

   private void initData() {
      galaxy = new Galaxy(GalaxyGenerator.generateGalaxy(mapWidth, mapHeight));
      CommonData.initData();
   }

   private void economics() {
      for (Player player : players) {
         player.money += galaxy.starSystems.stream().filter(system -> system.getOwner() == player).count();
         player.money -= ships.stream().filter(ship -> ship.owner == player).count();
         player.money -= fortresses.stream().filter(fortress -> fortress.owner == player).mapToInt(fortress -> fortress.level).sum();
      }
   }

   private void diplomacy() {

   }

   private void orders() {

   }

   private void movement() {
      doOrdersByType(OrderType.SHIP_MOVE);
      orders = orders.stream().filter(order -> order.type != OrderType.SHIP_MOVE).collect(Collectors.toList());
   }

   private void warfare() {
      doOrdersByType(OrderType.SHIP_TAKE_SYSTEM);
      doOrdersByType(OrderType.SHIP_SHOOT);

      orders = orders.stream()
              .filter(order -> order.type != OrderType.SHIP_SHOOT)
              .filter(order -> order.type != OrderType.SHIP_TAKE_SYSTEM)
              .collect(Collectors.toList());

      ships = ships.stream().filter(ship -> ship.hullPoints > 0).collect(Collectors.toList());
      fortresses.forEach(Fortress::siege);
   }

   private void development() {
      doOrdersByType(OrderType.PLANET_UPGRADE);
      doOrdersByType(OrderType.PLANET_DOWNGRADE);
      doOrdersByType(OrderType.PLANET_BUILD_SHIP);
      doOrdersByType(OrderType.FORTRESS_UPGRADE);
      doOrdersByType(OrderType.FORTRESS_DOWNGRADE);

      orders = orders.stream()
              .filter(order -> order.type != OrderType.PLANET_UPGRADE)
              .filter(order -> order.type != OrderType.PLANET_DOWNGRADE)
              .filter(order -> order.type != OrderType.PLANET_BUILD_SHIP)
              .filter(order -> order.type != OrderType.FORTRESS_UPGRADE)
              .filter(order -> order.type != OrderType.FORTRESS_DOWNGRADE)
              .collect(Collectors.toList());
   }

   private void cleanUp() {
      fortresses.forEach(fortress -> fortress.orderedAlready = false);
      planets.forEach(planet -> planet.orderedAlready = false);
      ships.forEach(ship -> ship.orderedAlready = false);
      orders.addAll(futureOrders);
      futureOrders.clear();
   }

   private void doOrdersByType(OrderType type) {
      if (orders.stream().anyMatch(order -> order.type == type)) {
         System.out.println("Doing " + orders.stream().filter(order -> order.type == type).count() + " " + type + " ");
         orders.stream().filter(order -> order.type == type)
                 .forEach(
                         order -> {
                            try {
                               order.runOrder();
                            } catch (OrderError orderError) {
                               System.out.println(orderError.getMessage());
                            }
                         }
                 );
      }
   }
}

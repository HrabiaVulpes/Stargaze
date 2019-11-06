package game;

import game.map.Fortress;
import game.player.Player;
import game.player.orders.OrderError;
import game.player.orders.OrderType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import static game.CommonData.*;

public class Game {
   public void mainLoop() {
      initData();
      while (continueGame) {
         long startTime = System.currentTimeMillis();

         economics();
         diplomacy();
         orders();
         movement();
         warfare();
         development();

         try {
            wait(gameSpeed + startTime - System.currentTimeMillis());
         } catch (Exception e) {
            e.printStackTrace();
         }
         cleanUp();
      }
   }

   private void initData() {
      players = new HashSet<>();
      players.add(new Player("Red"));
      players.add(new Player("Green"));
      players.add(new Player("Blue"));
      players.add(new Player("Black"));
      players.add(new Player("White"));
   }

   private void economics() {
      for (Player player : players) {
         player.money += galaxy.stream().filter(system -> system.getOwner().equals(player.name)).count();
         player.money -= ships.stream().filter(ship -> ship.owner.equals(player.name)).count();
         player.money -= fortresses.stream().filter(fortress -> fortress.owner.equals(player.name)).mapToInt(fortress -> fortress.level).sum();
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

      ships = ships.stream().filter(ship -> ship.hullPoints > 0).collect(Collectors.toSet());
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
      orders = new ArrayList<>(futureOrders);
      futureOrders.clear();
   }

   private void doOrdersByType(OrderType type) {
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

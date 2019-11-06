package game;

import game.player.Player;
import game.player.government.Order;
import game.player.government.OrderType;

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
      orders.stream().filter(order -> order.type == OrderType.SHIP_MOVE).forEach(Order::runOrder);
      orders = orders.stream().filter(order -> order.type != OrderType.SHIP_MOVE).collect(Collectors.toSet());
   }

   private void warfare() {
      orders.stream().filter(order -> order.type == OrderType.SHIP_TAKE_SYSTEM).forEach(Order::runOrder);
      orders.stream().filter(order -> order.type == OrderType.SHIP_SHOOT).forEach(Order::runOrder);

      orders = orders.stream()
              .filter(order -> order.type != OrderType.SHIP_SHOOT)
              .filter(order -> order.type != OrderType.SHIP_TAKE_SYSTEM)
              .collect(Collectors.toSet());
   }

   private void development() {
      orders.stream().filter(order -> order.type == OrderType.PLANET_UPGRADE).forEach(Order::runOrder);
      orders.stream().filter(order -> order.type == OrderType.PLANET_DOWNGRADE).forEach(Order::runOrder);
      orders.stream().filter(order -> order.type == OrderType.PLANET_BUILD_SHIP).forEach(Order::runOrder);
      orders.stream().filter(order -> order.type == OrderType.FORTRESS_UPGRADE).forEach(Order::runOrder);
      orders.stream().filter(order -> order.type == OrderType.FORTRESS_DOWNGRADE).forEach(Order::runOrder);

      orders = orders.stream()
              .filter(order -> order.type != OrderType.PLANET_UPGRADE)
              .filter(order -> order.type != OrderType.PLANET_DOWNGRADE)
              .filter(order -> order.type != OrderType.PLANET_BUILD_SHIP)
              .filter(order -> order.type != OrderType.FORTRESS_UPGRADE)
              .filter(order -> order.type != OrderType.FORTRESS_DOWNGRADE)
              .collect(Collectors.toSet());
   }
}

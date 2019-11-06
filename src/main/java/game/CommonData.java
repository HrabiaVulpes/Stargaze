package game;

import game.map.Fortress;
import game.map.Galaxy;
import game.map.Planet;
import game.player.Player;
import game.player.diplomacy.Message;
import game.player.orders.Order;
import game.ship.Ship;

import java.util.List;
import java.util.Set;

public class CommonData {
   public static Boolean continueGame = true;
   public static Long gameSpeed = 10000L;                 // miliseconds per turn
   public static Set<Player> players;
   public static Set<Message> messages;
   public static Galaxy galaxy;
   public static List<Order> orders;
   public static List<Order> futureOrders;
   public static Set<Planet> planets;
   public static Set<Fortress> fortresses;
   public static Set<Ship> ships;
}
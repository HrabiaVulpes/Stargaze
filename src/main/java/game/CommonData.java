package game;

import game.map.Fortress;
import game.map.Galaxy;
import game.map.MapElement;
import game.map.Planet;
import game.player.Player;
import game.player.diplomacy.Message;
import game.player.orders.Order;
import game.player.orders.OrderError;
import game.ship.Ship;
import game.ship.ShipTypes;

import java.awt.*;
import java.util.List;
import java.util.*;

public class CommonData {
   public static final int mapWidth = 1000;
   public static final int mapHeight = 700;

   public static MapElement selected = null;
   public static Boolean continueGame = true;
   public static Long gameSpeed = 1000L;                 // miliseconds per turn
   public static List<Player> players = new ArrayList<>();
   public static Set<Message> messages = new HashSet<>();
   public static Galaxy galaxy;
   public static List<Order> orders = Collections.synchronizedList(new ArrayList<>());
   public static List<Order> futureOrders = new ArrayList<>();
   public static Set<Planet> planets = new HashSet<>();
   public static Set<Fortress> fortresses = new HashSet<>();
   public static List<Ship> ships = new ArrayList<>();

   public static void initData() {
      Player red = new Player("Red", Color.RED);
      Player green = new Player("Green", Color.GREEN);
      players.add(red);
      players.add(green);

      try {
         galaxy.getSystemByID("1").setOwner(red);
         planets.stream().filter(planet -> planet.whereIsPlanet.ID.equals("1")).forEach(planet -> planet.owner = red);
         ships.add(new Ship(red, ShipTypes.CORVETTE, galaxy.getSystemByID("1")));

         galaxy.getSystemByID("9").setOwner(green);
         planets.stream().filter(planet -> planet.whereIsPlanet.ID.equals("9")).forEach(planet -> planet.owner = green);
         ships.add(new Ship(green, ShipTypes.CORVETTE, galaxy.getSystemByID("9")));

      } catch (OrderError orderError) {
         System.out.println(orderError.getMessage());
      }
   }
}
package game.staticData;

import game.fortress.Fortress;
import game.map.*;
import game.planet.Planet;
import game.player.Player;
import game.diplomacy.Message;
import game.orders.Order;
import game.orders.OrderError;
import game.ship.Ship;
import game.ship.ShipTypes;

import java.awt.*;
import java.util.List;
import java.util.*;

public class CommonData {
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

   public static Planet getPlanetByOwnerAndID(Player owner, String ID) throws OrderError {
      return CommonData.planets.stream()
              .filter(planet -> planet.owner == owner)
              .filter(planet -> planet.ID.equals(ID))
              .findFirst().orElseThrow(() -> new OrderError("Planet " + ID + " not found!"));
   }

   public static Fortress getFortressByOwnerAndID(Player owner, String ID) throws OrderError {
      return CommonData.fortresses.stream()
              .filter(fortress -> fortress.owner == owner)
              .filter(fortress -> fortress.ID.equals(ID))
              .findFirst().orElseThrow(() -> new OrderError("Fortress " + ID + " not found!"));
   }

   public static Ship getShipByID(String ID) throws OrderError {
      return CommonData.ships.stream()
              .filter(ship -> ship.ID.equals(ID))
              .findFirst().orElseThrow(() -> new OrderError("Ship " + ID + " not found!"));
   }

   public static StarSystem getSystemByID(String ID) throws OrderError{
      return CommonData.galaxy.starSystems.stream().filter(starSystem -> starSystem.ID.equals(ID)).findFirst()
              .orElseThrow(() -> new OrderError("System " + ID + " not found!"));
   }
}
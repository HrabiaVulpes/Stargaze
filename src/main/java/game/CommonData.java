package game;

import game.map.Fortress;
import game.map.Planet;
import game.ship.Ship;
import game.map.StarSystem;
import game.player.Player;
import game.player.diplomacy.Message;
import game.player.government.Order;

import java.util.Set;

public class CommonData {
   public static Boolean continueGame = true;
   public static Long gameSpeed = 10000L;                 // miliseconds per turn
   public static Set<Player> players;
   public static Set<StarSystem> galaxy;
   public static Set<Message> messages;
   public static Set<Order> orders;
   public static Set<Planet> planets;
   public static Set<Fortress> fortresses;
   public static Set<Ship> ships;
}

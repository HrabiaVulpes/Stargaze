package game;

import game.map.Fortress;
import game.map.Galaxy;
import game.map.Planet;
import game.player.Player;
import game.player.diplomacy.Message;
import game.player.orders.Order;
import game.ship.Ship;

import java.util.*;

public class CommonData {
    public static final int mapWidth = 1000;
    public static final int mapHeight = 700;

    public static Boolean continueGame = true;
    public static Long gameSpeed = 100L;                 // miliseconds per turn
    public static List<Player> players = new ArrayList<>();
    public static Set<Message> messages = new HashSet<>();
    public static Galaxy galaxy;
    public static List<Order> orders = Collections.synchronizedList(new ArrayList<>());
    public static List<Order> futureOrders = new ArrayList<>();
    public static Set<Planet> planets = new HashSet<>();
    public static Set<Fortress> fortresses = new HashSet<>();
    public static volatile List<Ship> ships = new ArrayList<>();
}
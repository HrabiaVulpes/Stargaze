package game;

import game.player.Player;
import game.player.diplomacy.Message;
import game.player.government.Order;

import java.util.Set;

public class CommonData {
   public static Boolean continueGame = true;
   public static Long gameSpeed = 10000L;                 // miliseconds per turn
   public static Set<Player> players;
   public static Set<System> galaxy;
   public static Set<Message> messages;
   public static Set<Order> orders;
}

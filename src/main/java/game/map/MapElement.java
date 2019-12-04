package game.map;

import game.player.Player;

import java.util.UUID;

public class MapElement {
   public final String ID;
   public Player owner;
   public boolean orderedAlready = false;

   public MapElement(Player owner) {
      this.owner = owner;
      ID = UUID.randomUUID().toString();
   }
}

package game.map;

import game.player.Player;

import java.awt.*;
import java.util.UUID;

public abstract class MapElement {
   public String ID;
   public Player owner;
   public boolean orderedAlready = false;

   protected int elementSize = 15;

   public MapElement(Player owner) {
      this.owner = owner;
      ID = UUID.randomUUID().toString();
   }

   public abstract Shape shape(int offset);
}

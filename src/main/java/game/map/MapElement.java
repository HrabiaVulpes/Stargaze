package game.map;

import game.Utils;
import game.player.Player;

import java.awt.*;

public abstract class MapElement {
   public String ID;
   public Player owner;
   public boolean orderedAlready = false;

   protected int elementSize = 15;

   public MapElement(Player owner) {
      this.owner = owner;
      ID = "" + Utils.nextID();
   }

   public abstract Shape shape(int offset);
}

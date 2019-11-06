package game.map;

import java.util.UUID;

public class MapElement {
   public final String ID;
   public String owner;
   public boolean orderedAlready = false;

   public MapElement(String owner) {
      this.owner = owner;
      ID = UUID.randomUUID().toString();
   }
}

package game.map;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StarSystem {
   public final String ID;
   private int x;
   private int y;
   private Set<StarSystem> connections;
   private String owner;

   public StarSystem(int x, int y) {
      ID = UUID.randomUUID().toString();
      this.x = x;
      this.y = y;
      connections = Collections.emptySet();
   }

   public Set<StarSystem> getConnections() {
      return connections;
   }

   public void setConnections(Set<StarSystem> connections) {
      this.connections = connections;
   }

   public void addConnection(StarSystem connection) {
      if (this.connections == null) {
         this.connections = new HashSet<StarSystem>();
      }

      this.connections.add(connection);
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   public String getOwner() {
      return owner;
   }

   public void setOwner(String owner) {
      this.owner = owner;
   }
}

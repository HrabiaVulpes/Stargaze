package game.map;

import game.player.Player;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class StarSystem {
   public String ID;
   public Integer distance = -1;
   private Set<StarSystem> connections;
   private Player owner = new Player("none", Color.DARK_GRAY);
   private int x;
   private int y;

   public StarSystem(int x, int y) {
      this.x = x;
      this.y = y;
      ID = UUID.randomUUID().toString();
      connections = new HashSet<>();
   }

   public StarSystem(int x, int y, String ID) {
      this.x = x;
      this.y = y;
      this.ID = ID;
      connections = new HashSet<>();
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

   public Player getOwner() {
      return owner;
   }

   public void setOwner(Player owner) {
      this.owner = owner;
   }

   public double distance(StarSystem system) {
      return Math.sqrt(Math.pow(system.getX() - this.getX(), 2) + Math.pow(system.getY() - this.getY(), 2));
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   public List<StarSystem> roadTo(List<StarSystem> currentRoad, StarSystem goal) {
      List<StarSystem> result = new ArrayList<>(currentRoad);
      result.add(this);

      if (this.ID.equals(goal.ID)) {
         return result;
      }

      connections.forEach(system -> system.distance = system.distance == -1 ? this.distance + 1 : system.distance);
      List<StarSystem> nextSystems = connections.stream()
              .filter(system -> system.distance > this.distance)
              .sorted(Comparator.comparing(system -> system.distance(goal)))
              .collect(Collectors.toList());

      for (StarSystem system : nextSystems) {
         List<StarSystem> continuation = system.roadTo(result, goal);
         if (continuation.contains(goal)) {
            return continuation;
         }
      }

      return new ArrayList<>();
   }

   public void setX(int x) {
      this.x = x;
   }

   public void setY(int y) {
      this.y = y;
   }
}

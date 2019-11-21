package game.map;

import game.player.Player;
import game.player.orders.OrderError;

import java.util.Set;
import java.util.stream.Collectors;

public class Galaxy {
   public Set<StarSystem> starSystems;

   public Galaxy(Set<StarSystem> starSystems) {
      this.starSystems = starSystems;
   }

   public Set<StarSystem> getSystemsByOwner(Player player) {
      return starSystems.stream().filter(system -> system.getOwner().equals(player.name)).collect(Collectors.toSet());
   }

   public StarSystem getSystemByID(String ID) throws OrderError {
      return starSystems.stream().filter(system -> system.ID.equals(ID)).findFirst().orElseThrow(() -> new OrderError("System " + ID + " not found!"));
   }

   public StarSystem getSystemByOwnerAndID(Player player, String ID) throws OrderError {
      return starSystems.stream()
              .filter(system -> system.getOwner().equals(player.name))
              .filter(system -> system.ID.equals(ID)).findFirst()
              .orElseThrow(() -> new OrderError("System " + ID + " not found!"));
   }


}

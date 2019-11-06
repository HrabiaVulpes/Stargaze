package game.player.government;

import game.CommonData;
import game.map.Planet;
import game.player.Player;

import java.util.NoSuchElementException;

public class OrderPlanetDowngrade extends Order {
   private String planetID;

   public OrderPlanetDowngrade(Player owner, String planetID) {
      super(owner, OrderType.PLANET_DOWNGRADE);
      this.planetID = planetID;
   }

   @Override
   public void runOrder() {
      Planet orderedPlanet = CommonData.planets.stream()
              .filter(planet -> planet.owner.equals(owner.name))
              .filter(planet -> planet.ID.equals(planetID))
              .findFirst().orElseThrow(NoSuchElementException::new);

      if (orderedPlanet.level > 1) {
         orderedPlanet.downgrade();
      }
   }
}

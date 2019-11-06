package game.player.government;

import game.CommonData;
import game.map.Planet;
import game.player.Player;

public class OrderPlanetDowngrade extends Order {
   private String planetID;

   public OrderPlanetDowngrade(Player owner, String planetID) {
      super(owner, OrderType.PLANET_DOWNGRADE);
      this.planetID = planetID;
   }

   @Override
   public void runOrder() throws OrderError {
      Planet orderedPlanet = CommonData.planets.stream()
              .filter(planet -> planet.owner.equals(owner.name))
              .filter(planet -> planet.ID.equals(planetID))
              .findFirst().orElseThrow(() -> new OrderError("Planet " + planetID + " not found!"));

      if (orderedPlanet.orderedAlready) {
         throw new OrderError("Planet " + planetID + " already moved!");
      }

      if (orderedPlanet.level > 1) {
         orderedPlanet.downgrade();
      } else {
         throw new OrderError("Planet " + planetID + " already lowest level!");
      }
   }
}

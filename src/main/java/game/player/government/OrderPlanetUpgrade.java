package game.player.government;

import game.CommonData;
import game.map.Planet;
import game.player.Player;

public class OrderPlanetUpgrade extends Order {
   private String planetID;

   public OrderPlanetUpgrade(Player owner, String planetID) {
      super(owner, OrderType.PLANET_UPGRADE);
      this.planetID = planetID;
   }

   @Override
   public void runOrder() throws OrderError {
      Planet orderedPlanet = CommonData.planets.stream()
              .filter(planet -> planet.owner.equals(owner.name))
              .filter(planet -> planet.ID.equals(planetID))
              .findFirst().orElseThrow(() -> new OrderError("Planet " + planetID + " not found!"));

      if (owner.money >= orderedPlanet.level) {
         owner.money -= orderedPlanet.level;
         orderedPlanet.upgrade();
      } else {
         throw new OrderError("Insufficient funds to upgrade planet " + planetID);
      }
   }
}

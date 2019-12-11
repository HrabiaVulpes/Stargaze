package game.player.orders;

import game.CommonData;
import game.map.Planet;
import game.player.Player;

import java.util.Collections;

public class OrderPlanetUpgrade extends Order {
   private String planetID;

   public OrderPlanetUpgrade(Player owner, String planetID) {
      super(owner, OrderType.PLANET_UPGRADE);
      this.planetID = planetID;
      connectedMapElements = Collections.singletonList(CommonData.planets.stream().filter(planet -> planet.ID.equals(planetID)).findFirst().orElse(null));
   }

   @Override
   public void runOrder() throws OrderError {
      Planet orderedPlanet = CommonData.planets.stream()
              .filter(planet -> planet.owner.equals(owner.name))
              .filter(planet -> planet.ID.equals(planetID))
              .findFirst().orElseThrow(() -> new OrderError("Planet " + planetID + " not found!"));

      if (orderedPlanet.orderedAlready) {
         reAddOrder();
         throw new OrderError("Planet " + planetID + " already moved!");
      }

      if (owner.money >= orderedPlanet.level) {
         owner.money -= orderedPlanet.level;
         orderedPlanet.upgrade();
      } else {
         throw new OrderError("Insufficient funds to upgrade planet " + planetID);
      }
   }
}

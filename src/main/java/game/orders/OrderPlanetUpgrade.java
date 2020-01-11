package game.orders;

import game.staticData.CommonData;
import game.planet.Planet;
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
      Planet orderedPlanet = CommonData.getPlanetByOwnerAndID(owner, planetID);

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

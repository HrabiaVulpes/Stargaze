package game.orders;

import game.staticData.CommonData;
import game.planet.Planet;
import game.player.Player;
import game.ship.Ship;
import game.ship.ShipTypes;

import java.util.Collections;

public class OrderPlanetBuildShip extends Order {
   private String planetID;
   private ShipTypes shipType;

   public OrderPlanetBuildShip(Player owner, String planetID, ShipTypes shipType) {
      super(owner, OrderType.PLANET_BUILD_SHIP);
      this.planetID = planetID;
      this.shipType = shipType;
      connectedMapElements = Collections.singletonList(CommonData.planets.stream().filter(planet -> planet.ID.equals(planetID)).findFirst().orElse(null));
   }

   @Override
   public void runOrder() throws OrderError {
      Planet orderedPlanet = CommonData.getPlanetByOwnerAndID(owner, planetID);

      if (orderedPlanet.orderedAlready) {
         reAddOrder();
         throw new OrderError("Planet " + planetID + " already moved!");
      }

      if (orderedPlanet.level >= shipType.getSize() && owner.money >= shipType.getSize()) {
         owner.money -= shipType.getSize();

         Ship newShip = new Ship(owner, shipType, orderedPlanet.whereIsPlanet);
         CommonData.ships.add(newShip);
      } else {
         throw new OrderError("Can't build ship!");
      }
   }
}

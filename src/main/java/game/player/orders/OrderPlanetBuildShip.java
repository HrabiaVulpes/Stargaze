package game.player.orders;

import game.CommonData;
import game.map.Planet;
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
      Planet orderedPlanet = CommonData.planets.stream()                   // TODO: sprawdzić czy optymalne
              .filter(planet -> planet.owner == owner)
              .filter(planet -> planet.ID.equals(planetID))
              .findFirst().orElseThrow(() -> new OrderError("Planet " + planetID + " not found!"));

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

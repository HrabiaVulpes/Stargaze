package game.orders;

import game.staticData.CommonData;
import game.fortress.Fortress;
import game.map.StarSystem;
import game.player.Player;
import game.ship.Ship;

import java.awt.*;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderTakeSystem extends Order {
   private String shipID;

   public OrderTakeSystem(Player owner, String shipID) {
      super(owner, OrderType.SHIP_TAKE_SYSTEM);
      this.shipID = shipID;
      connectedMapElements = Collections.singletonList(CommonData.ships.stream().filter(ship -> ship.ID.equals(shipID)).findFirst().orElse(null));
   }

   @Override
   public void runOrder() throws OrderError {
      Ship orderedShip = CommonData.ships.stream()
              .filter(ship -> ship.owner == owner)
              .filter(ship -> ship.ID.equals(shipID))
              .findFirst().orElseThrow(() -> new OrderError("Ship " + shipID + " not found!"));

      if (orderedShip.orderedAlready) {
         reAddOrder();
         throw new OrderError("Ship " + shipID + " already moved!");
      }

      StarSystem system = orderedShip.whereIsShip;
      Fortress systemFortress = CommonData.fortresses.stream()
              .filter(fortress -> fortress.whereIsFortress == system)
              .findFirst().orElse(new Fortress(new Player("none", Color.DARK_GRAY), system));

      Set<Ship> foreignShipsPresent = CommonData.ships.stream()
              .filter(ship -> ship.whereIsShip == system)
              .filter(ship -> !(ship.owner == owner))
              .collect(Collectors.toSet());

      if (systemFortress.fortifiedLevel <= 0 && foreignShipsPresent.size() == 0) {
         system.setOwner(orderedShip.owner);
      } else {
         throw new OrderError("Ship " + shipID + " cannot take system " + system.ID);
      }
   }
}

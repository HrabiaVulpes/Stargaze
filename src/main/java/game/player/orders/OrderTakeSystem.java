package game.player.orders;

import game.CommonData;
import game.map.Fortress;
import game.map.StarSystem;
import game.player.Player;
import game.ship.Ship;

import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderTakeSystem extends Order {
   private String shipID;

   public OrderTakeSystem(Player owner, String shipID) {
      super(owner, OrderType.SHIP_TAKE_SYSTEM);
      this.shipID = shipID;
   }

   @Override
   public void runOrder() throws OrderError {
      Ship orderedShip = CommonData.ships.stream()
              .filter(ship -> ship.owner.equals(owner.name))
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
